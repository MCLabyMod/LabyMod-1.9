import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpPipelineReceiver
  extends Thread
{
  private HttpPipelineConnection httpPipelineConnection = null;
  private static final Charset ASCII = Charset.forName("ASCII");
  private static final String HEADER_CONTENT_LENGTH = "Content-Length";
  private static final char CR = '\r';
  private static final char LF = '\n';
  
  public HttpPipelineReceiver(HttpPipelineConnection httpPipelineConnection)
  {
    super("HttpPipelineReceiver");
    
    this.httpPipelineConnection = httpPipelineConnection;
  }
  
  public void run()
  {
    while (!Thread.interrupted())
    {
      HttpPipelineRequest currentRequest = null;
      try
      {
        currentRequest = this.httpPipelineConnection.getNextRequestReceive();
        
        InputStream in = this.httpPipelineConnection.getInputStream();
        HttpResponse resp = readResponse(in);
        
        this.httpPipelineConnection.onResponseReceived(currentRequest, resp);
      }
      catch (InterruptedException e)
      {
        return;
      }
      catch (Exception e)
      {
        this.httpPipelineConnection.onExceptionReceive(currentRequest, e);
      }
    }
  }
  
  private HttpResponse readResponse(InputStream in)
    throws IOException
  {
    String statusLine = readLine(in);
    
    String[] parts = Config.tokenize(statusLine, " ");
    if (parts.length < 3) {
      throw new IOException("Invalid status line: " + statusLine);
    }
    String http = parts[0];
    int status = Config.parseInt(parts[1], 0);
    String message = parts[2];
    
    Map<String, String> headers = new LinkedHashMap();
    for (;;)
    {
      String line = readLine(in);
      if (line.length() <= 0) {
        break;
      }
      int pos = line.indexOf(":");
      if (pos > 0)
      {
        String key = line.substring(0, pos).trim();
        String val = line.substring(pos + 1).trim();
        headers.put(key, val);
      }
    }
    byte[] body = null;
    String lenStr = (String)headers.get("Content-Length");
    if (lenStr != null)
    {
      int len = Config.parseInt(lenStr, -1);
      if (len > 0)
      {
        body = new byte[len];
        readFull(body, in);
      }
    }
    else
    {
      String enc = (String)headers.get("Transfer-Encoding");
      if (Config.equals(enc, "chunked")) {
        body = readContentChunked(in);
      }
    }
    return new HttpResponse(status, statusLine, headers, body);
  }
  
  private byte[] readContentChunked(InputStream in)
    throws IOException
  {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    for (;;)
    {
      String line = readLine(in);
      String[] parts = Config.tokenize(line, "; ");
      int len = Integer.parseInt(parts[0], 16);
      byte[] buf = new byte[len];
      
      readFull(buf, in);
      baos.write(buf);
      
      readLine(in);
      if (len == 0) {
        break;
      }
    }
    return baos.toByteArray();
  }
  
  private void readFull(byte[] buf, InputStream in)
    throws IOException
  {
    int pos = 0;
    while (pos < buf.length)
    {
      int len = in.read(buf, pos, buf.length - pos);
      if (len < 0) {
        throw new EOFException();
      }
      pos += len;
    }
  }
  
  private String readLine(InputStream in)
    throws IOException
  {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    
    int prev = -1;
    boolean hasCRLF = false;
    for (;;)
    {
      int i = in.read();
      if (i < 0) {
        break;
      }
      baos.write(i);
      if ((prev == 13) && (i == 10))
      {
        hasCRLF = true;
        break;
      }
      prev = i;
    }
    byte[] bytes = baos.toByteArray();
    
    String str = new String(bytes, ASCII);
    if (hasCRLF) {
      str = str.substring(0, str.length() - 2);
    }
    return str;
  }
}
