import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HttpUtils
{
  public static final String SERVER_URL = "http://s.optifine.net";
  public static final String POST_URL = "http://optifine.net";
  
  public static byte[] get(String urlStr)
    throws IOException
  {
    HttpURLConnection conn = null;
    try
    {
      URL url = new URL(urlStr);
      conn = (HttpURLConnection)url.openConnection(bcf.z().M());
      conn.setDoInput(true);
      conn.setDoOutput(false);
      conn.connect();
      if (conn.getResponseCode() / 100 != 2)
      {
        if (conn.getErrorStream() != null) {
          Config.readAll(conn.getErrorStream());
        }
        throw new IOException("HTTP response: " + conn.getResponseCode());
      }
      InputStream in = conn.getInputStream();
      
      byte[] bytes = new byte[conn.getContentLength()];
      int pos = 0;
      int len;
      do
      {
        len = in.read(bytes, pos, bytes.length - pos);
        if (len < 0) {
          throw new IOException("Input stream closed: " + urlStr);
        }
        pos += len;
      } while (pos < bytes.length);
      return bytes;
    }
    finally
    {
      if (conn != null) {
        conn.disconnect();
      }
    }
  }
  
  public static String post(String urlStr, Map headers, byte[] content)
    throws IOException
  {
    HttpURLConnection conn = null;
    try
    {
      URL url = new URL(urlStr);
      conn = (HttpURLConnection)url.openConnection(bcf.z().M());
      conn.setRequestMethod("POST");
      Iterator it;
      if (headers != null)
      {
        Set keys = headers.keySet();
        for (it = keys.iterator(); it.hasNext();)
        {
          String key = (String)it.next();
          String val = "" + headers.get(key);
          conn.setRequestProperty(key, val);
        }
      }
      conn.setRequestProperty("Content-Type", "text/plain");
      conn.setRequestProperty("Content-Length", "" + content.length);
      conn.setRequestProperty("Content-Language", "en-US");
      
      conn.setUseCaches(false);
      conn.setDoInput(true);
      conn.setDoOutput(true);
      
      OutputStream os = conn.getOutputStream();
      os.write(content);
      os.flush();
      os.close();
      
      InputStream in = conn.getInputStream();
      InputStreamReader isr = new InputStreamReader(in, "ASCII");
      BufferedReader br = new BufferedReader(isr);
      StringBuffer sb = new StringBuffer();
      String line;
      while ((line = br.readLine()) != null)
      {
        sb.append(line);
        sb.append('\r');
      }
      br.close();
      return sb.toString();
    }
    finally
    {
      if (conn != null) {
        conn.disconnect();
      }
    }
  }
}
