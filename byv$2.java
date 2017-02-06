import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

final class byv$2
  extends URLStreamHandler
{
  byv$2(kk paramkk) {}
  
  protected URLConnection openConnection(URL ☃)
  {
    new URLConnection(☃)
    {
      public void connect()
        throws IOException
      {}
      
      public InputStream getInputStream()
        throws IOException
      {
        return bcf.z().O().a(byv.2.this.a).b();
      }
    };
  }
}
