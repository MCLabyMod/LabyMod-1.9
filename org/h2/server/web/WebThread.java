package org.h2.server.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import org.h2.engine.Constants;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.mvstore.DataUtils;
import org.h2.util.IOUtils;
import org.h2.util.NetUtils;
import org.h2.util.StringUtils;

class WebThread
  extends WebApp
  implements Runnable
{
  protected OutputStream output;
  protected final Socket socket;
  private final Thread thread;
  private InputStream input;
  private int headerBytes;
  private String ifModifiedSince;
  
  WebThread(Socket socket, WebServer server)
  {
    super(server);
    this.socket = socket;
    this.thread = new Thread(this, "H2 Console thread");
  }
  
  void start()
  {
    this.thread.start();
  }
  
  void join(int millis)
    throws InterruptedException
  {
    this.thread.join(millis);
  }
  
  void stopNow()
  {
    this.stop = true;
    try
    {
      this.socket.close();
    }
    catch (IOException e) {}
  }
  
  private String getAllowedFile(String requestedFile)
  {
    if (!allow()) {
      return "notAllowed.jsp";
    }
    if (requestedFile.length() == 0) {
      return "index.do";
    }
    return requestedFile;
  }
  
  public void run()
  {
    try
    {
      this.input = new BufferedInputStream(this.socket.getInputStream());
      this.output = new BufferedOutputStream(this.socket.getOutputStream());
      while (!this.stop) {
        if (!process()) {
          break;
        }
      }
    }
    catch (Exception e)
    {
      DbException.traceThrowable(e);
    }
    IOUtils.closeSilently(this.output);
    IOUtils.closeSilently(this.input);
    try
    {
      this.socket.close();
    }
    catch (IOException e) {}finally
    {
      this.server.remove(this);
    }
  }
  
  private boolean process()
    throws IOException
  {
    boolean keepAlive = false;
    String head = readHeaderLine();
    if ((head.startsWith("GET ")) || (head.startsWith("POST ")))
    {
      int begin = head.indexOf('/');int end = head.lastIndexOf(' ');
      String file;
      if ((begin < 0) || (end < begin)) {
        file = "";
      } else {
        file = head.substring(begin + 1, end).trim();
      }
      trace(head + ": " + file);
      String file = getAllowedFile(file);
      this.attributes = new Properties();
      int paramIndex = file.indexOf("?");
      this.session = null;
      if (paramIndex >= 0)
      {
        String attrib = file.substring(paramIndex + 1);
        parseAttributes(attrib);
        String sessionId = this.attributes.getProperty("jsessionid");
        file = file.substring(0, paramIndex);
        this.session = this.server.getSession(sessionId);
      }
      keepAlive = parseHeader();
      String hostAddr = this.socket.getInetAddress().getHostAddress();
      file = processRequest(file, hostAddr);
      if (file.length() == 0) {
        return true;
      }
      String message;
      byte[] bytes;
      if ((this.cache) && (this.ifModifiedSince != null) && (this.ifModifiedSince.equals(this.server.getStartDateTime())))
      {
        byte[] bytes = null;
        message = "HTTP/1.1 304 Not Modified\r\n";
      }
      else
      {
        bytes = this.server.getFile(file);
        if (bytes == null)
        {
          String message = "HTTP/1.1 404 Not Found\r\n";
          bytes = ("File not found: " + file).getBytes(Constants.UTF8);
          message = message + "Content-Length: " + bytes.length + "\r\n";
        }
        else
        {
          if ((this.session != null) && (file.endsWith(".jsp")))
          {
            String page = new String(bytes, Constants.UTF8);
            if (SysProperties.CONSOLE_STREAM)
            {
              Iterator<String> it = (Iterator)this.session.map.remove("chunks");
              if (it != null)
              {
                String message = "HTTP/1.1 200 OK\r\n";
                message = message + "Content-Type: " + this.mimeType + "\r\n";
                message = message + "Cache-Control: no-cache\r\n";
                message = message + "Transfer-Encoding: chunked\r\n";
                message = message + "\r\n";
                trace(message);
                this.output.write(message.getBytes());
                while (it.hasNext())
                {
                  String s = (String)it.next();
                  s = PageParser.parse(s, this.session.map);
                  bytes = s.getBytes(Constants.UTF8);
                  if (bytes.length != 0)
                  {
                    this.output.write(Integer.toHexString(bytes.length).getBytes());
                    this.output.write("\r\n".getBytes());
                    this.output.write(bytes);
                    this.output.write("\r\n".getBytes());
                    this.output.flush();
                  }
                }
                this.output.write("0\r\n\r\n".getBytes());
                this.output.flush();
                return keepAlive;
              }
            }
            page = PageParser.parse(page, this.session.map);
            bytes = page.getBytes(Constants.UTF8);
          }
          message = "HTTP/1.1 200 OK\r\n";
          message = message + "Content-Type: " + this.mimeType + "\r\n";
          if (!this.cache)
          {
            message = message + "Cache-Control: no-cache\r\n";
          }
          else
          {
            message = message + "Cache-Control: max-age=10\r\n";
            message = message + "Last-Modified: " + this.server.getStartDateTime() + "\r\n";
          }
          message = message + "Content-Length: " + bytes.length + "\r\n";
        }
      }
      String message = message + "\r\n";
      trace(message);
      this.output.write(message.getBytes());
      if (bytes != null) {
        this.output.write(bytes);
      }
      this.output.flush();
    }
    return keepAlive;
  }
  
  private String readHeaderLine()
    throws IOException
  {
    StringBuilder buff = new StringBuilder();
    for (;;)
    {
      this.headerBytes += 1;
      int c = this.input.read();
      if (c == -1) {
        throw new IOException("Unexpected EOF");
      }
      if (c == 13)
      {
        this.headerBytes += 1;
        if (this.input.read() == 10) {
          return buff.length() > 0 ? buff.toString() : null;
        }
      }
      else
      {
        if (c == 10) {
          return buff.length() > 0 ? buff.toString() : null;
        }
        buff.append((char)c);
      }
    }
  }
  
  private void parseAttributes(String s)
  {
    trace("data=" + s);
    while (s != null)
    {
      int idx = s.indexOf('=');
      if (idx < 0) {
        break;
      }
      String property = s.substring(0, idx);
      s = s.substring(idx + 1);
      idx = s.indexOf('&');
      String value;
      if (idx >= 0)
      {
        String value = s.substring(0, idx);
        s = s.substring(idx + 1);
      }
      else
      {
        value = s;
      }
      String attr = StringUtils.urlDecode(value);
      this.attributes.put(property, attr);
    }
    trace(this.attributes.toString());
  }
  
  private boolean parseHeader()
    throws IOException
  {
    boolean keepAlive = false;
    trace("parseHeader");
    int len = 0;
    this.ifModifiedSince = null;
    boolean multipart = false;
    for (;;)
    {
      String line = readHeaderLine();
      if (line == null) {
        break;
      }
      trace(" " + line);
      String lower = StringUtils.toLowerEnglish(line);
      if (lower.startsWith("if-modified-since"))
      {
        this.ifModifiedSince = getHeaderLineValue(line);
      }
      else if (lower.startsWith("connection"))
      {
        String conn = getHeaderLineValue(line);
        if ("keep-alive".equals(conn)) {
          keepAlive = true;
        }
      }
      else if (lower.startsWith("content-type"))
      {
        String type = getHeaderLineValue(line);
        if (type.startsWith("multipart/form-data")) {
          multipart = true;
        }
      }
      else if (lower.startsWith("content-length"))
      {
        len = Integer.parseInt(getHeaderLineValue(line));
        trace("len=" + len);
      }
      else if (lower.startsWith("user-agent"))
      {
        boolean isWebKit = lower.contains("webkit/");
        if ((isWebKit) && (this.session != null))
        {
          this.session.put("frame-border", "1");
          this.session.put("frameset-border", "2");
        }
      }
      else if (lower.startsWith("accept-language"))
      {
        Locale locale = this.session == null ? null : this.session.locale;
        if (locale == null)
        {
          String languages = getHeaderLineValue(line);
          StringTokenizer tokenizer = new StringTokenizer(languages, ",;");
          for (; tokenizer.hasMoreTokens(); goto 483)
          {
            String token = tokenizer.nextToken();
            if ((!token.startsWith("q=")) && 
              (this.server.supportsLanguage(token)))
            {
              int dash = token.indexOf('-');
              if (dash >= 0)
              {
                String language = token.substring(0, dash);
                String country = token.substring(dash + 1);
                locale = new Locale(language, country);
              }
              else
              {
                locale = new Locale(token, "");
              }
              this.headerLanguage = locale.getLanguage();
              if (this.session == null) {
                break;
              }
              this.session.locale = locale;
              this.session.put("language", this.headerLanguage);
              this.server.readTranslations(this.session, this.headerLanguage);
            }
          }
        }
      }
      else
      {
        if (line.trim().length() == 0) {
          break;
        }
      }
    }
    if (multipart)
    {
      uploadMultipart(this.input, len);
    }
    else if ((this.session != null) && (len > 0))
    {
      byte[] bytes = DataUtils.newBytes(len);
      for (int pos = 0; pos < len;) {
        pos += this.input.read(bytes, pos, len - pos);
      }
      String s = new String(bytes);
      parseAttributes(s);
    }
    return keepAlive;
  }
  
  private void uploadMultipart(InputStream in, int len)
    throws IOException
  {
    if (!new File("transfer").exists()) {
      return;
    }
    String fileName = "temp.bin";
    this.headerBytes = 0;
    String boundary = readHeaderLine();
    for (;;)
    {
      String line = readHeaderLine();
      if (line == null) {
        break;
      }
      int index = line.indexOf("filename=\"");
      if (index > 0) {
        fileName = line.substring(index + "filename=\"".length(), line.lastIndexOf('"'));
      }
      trace(" " + line);
    }
    if (!WebServer.isSimpleName(fileName)) {
      return;
    }
    len -= this.headerBytes;
    File file = new File("transfer", fileName);
    OutputStream out = new FileOutputStream(file);
    IOUtils.copy(in, out, len);
    out.close();
    
    RandomAccessFile f = new RandomAccessFile(file, "rw");
    int testSize = (int)Math.min(f.length(), 4096L);
    f.seek(f.length() - testSize);
    byte[] bytes = DataUtils.newBytes(4096);
    f.readFully(bytes, 0, testSize);
    String s = new String(bytes, "ASCII");
    int x = s.lastIndexOf(boundary);
    f.setLength(f.length() - testSize + x - 2L);
    f.close();
  }
  
  private static String getHeaderLineValue(String line)
  {
    return line.substring(line.indexOf(':') + 1).trim();
  }
  
  protected String adminShutdown()
  {
    stopNow();
    return super.adminShutdown();
  }
  
  private boolean allow()
  {
    if (this.server.getAllowOthers()) {
      return true;
    }
    try
    {
      return NetUtils.isLocalAddress(this.socket);
    }
    catch (UnknownHostException e)
    {
      this.server.traceError(e);
    }
    return false;
  }
  
  private void trace(String s)
  {
    this.server.trace(s);
  }
}
