import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Proxy;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpPipeline
{
  private static Map mapConnections = new HashMap();
  public static final String HEADER_USER_AGENT = "User-Agent";
  public static final String HEADER_HOST = "Host";
  public static final String HEADER_ACCEPT = "Accept";
  public static final String HEADER_LOCATION = "Location";
  public static final String HEADER_KEEP_ALIVE = "Keep-Alive";
  public static final String HEADER_CONNECTION = "Connection";
  public static final String HEADER_VALUE_KEEP_ALIVE = "keep-alive";
  public static final String HEADER_TRANSFER_ENCODING = "Transfer-Encoding";
  public static final String HEADER_VALUE_CHUNKED = "chunked";
  
  public static void addRequest(String urlStr, HttpListener listener)
    throws IOException
  {
    addRequest(urlStr, listener, Proxy.NO_PROXY);
  }
  
  public static void addRequest(String urlStr, HttpListener listener, Proxy proxy)
    throws IOException
  {
    HttpRequest hr = makeRequest(urlStr, proxy);
    HttpPipelineRequest hpr = new HttpPipelineRequest(hr, listener);
    
    addRequest(hpr);
  }
  
  public static HttpRequest makeRequest(String urlStr, Proxy proxy)
    throws IOException
  {
    URL url = new URL(urlStr);
    if (!url.getProtocol().equals("http")) {
      throw new IOException("Only protocol http is supported: " + url);
    }
    String file = url.getFile();
    String host = url.getHost();
    int port = url.getPort();
    if (port <= 0) {
      port = 80;
    }
    String method = "GET";
    String http = "HTTP/1.1";
    
    Map<String, String> headers = new LinkedHashMap();
    headers.put("User-Agent", "Java/" + System.getProperty("java.version"));
    headers.put("Host", host);
    headers.put("Accept", "text/html, image/gif, image/png");
    headers.put("Connection", "keep-alive");
    
    byte[] body = new byte[0];
    
    HttpRequest req = new HttpRequest(host, port, proxy, method, file, http, headers, body);
    
    return req;
  }
  
  public static void addRequest(HttpPipelineRequest pr)
  {
    HttpRequest hr = pr.getHttpRequest();
    HttpPipelineConnection conn = getConnection(hr.getHost(), hr.getPort(), hr.getProxy());
    while (!conn.addRequest(pr))
    {
      removeConnection(hr.getHost(), hr.getPort(), hr.getProxy(), conn);
      conn = getConnection(hr.getHost(), hr.getPort(), hr.getProxy());
    }
  }
  
  private static synchronized HttpPipelineConnection getConnection(String host, int port, Proxy proxy)
  {
    String key = makeConnectionKey(host, port, proxy);
    HttpPipelineConnection conn = (HttpPipelineConnection)mapConnections.get(key);
    if (conn == null)
    {
      conn = new HttpPipelineConnection(host, port, proxy);
      mapConnections.put(key, conn);
    }
    return conn;
  }
  
  private static synchronized void removeConnection(String host, int port, Proxy proxy, HttpPipelineConnection hpc)
  {
    String key = makeConnectionKey(host, port, proxy);
    HttpPipelineConnection conn = (HttpPipelineConnection)mapConnections.get(key);
    if (conn == hpc) {
      mapConnections.remove(key);
    }
  }
  
  private static String makeConnectionKey(String host, int port, Proxy proxy)
  {
    String hostPort = host + ":" + port + "-" + proxy;
    return hostPort;
  }
  
  public static byte[] get(String urlStr)
    throws IOException
  {
    return get(urlStr, Proxy.NO_PROXY);
  }
  
  public static byte[] get(String urlStr, Proxy proxy)
    throws IOException
  {
    HttpRequest req = makeRequest(urlStr, proxy);
    HttpResponse resp = executeRequest(req);
    if (resp.getStatus() / 100 != 2) {
      throw new IOException("HTTP response: " + resp.getStatus());
    }
    return resp.getBody();
  }
  
  public static HttpResponse executeRequest(HttpRequest req)
    throws IOException
  {
    Map<String, Object> map = new HashMap();
    String KEY_RESPONSE = "Response";
    String KEY_EXCEPTION = "Exception";
    
    HttpListener l = new HttpListener()
    {
      public void finished(HttpRequest req, HttpResponse resp)
      {
        synchronized (this.val$map)
        {
          this.val$map.put("Response", resp);
          this.val$map.notifyAll();
        }
      }
      
      public void failed(HttpRequest req, Exception e)
      {
        synchronized (this.val$map)
        {
          this.val$map.put("Exception", e);
          this.val$map.notifyAll();
        }
      }
    };
    synchronized (map)
    {
      HttpPipelineRequest hpr = new HttpPipelineRequest(req, l);
      addRequest(hpr);
      try
      {
        map.wait();
      }
      catch (InterruptedException e1)
      {
        throw new InterruptedIOException("Interrupted");
      }
      Exception e = (Exception)map.get("Exception");
      if (e != null)
      {
        if ((e instanceof IOException)) {
          throw ((IOException)e);
        }
        if ((e instanceof RuntimeException)) {
          throw ((RuntimeException)e);
        }
        throw new RuntimeException(e.getMessage(), e);
      }
      HttpResponse resp = (HttpResponse)map.get("Response");
      if (resp == null) {
        throw new IOException("Response is null");
      }
      return resp;
    }
  }
  
  public static boolean hasActiveRequests()
  {
    Collection conns = mapConnections.values();
    for (Iterator it = conns.iterator(); it.hasNext();)
    {
      HttpPipelineConnection conn = (HttpPipelineConnection)it.next();
      if (conn.hasActiveRequests()) {
        return true;
      }
    }
    return false;
  }
}
