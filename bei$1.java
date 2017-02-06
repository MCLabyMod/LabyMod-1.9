import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.logging.log4j.Logger;

class bei$1
  extends Thread
{
  bei$1(bei this$0, String x0, String paramString1, int paramInt)
  {
    super(x0);
  }
  
  public void run()
  {
    InetAddress inetaddress = null;
    try
    {
      if (bei.a(this.this$0)) {
        return;
      }
      inetaddress = InetAddress.getByName(this.val$ip);
      bei.a(this.this$0, ek.a(inetaddress, this.val$port, bei.access$200(this.this$0).u.f()));
      bei.b(this.this$0).a(new bkr(bei.b(this.this$0), bei.access$300(this.this$0), bei.c(this.this$0)));
      bei.b(this.this$0).a(new jj(107, this.val$ip, this.val$port, el.d));
      bei.b(this.this$0).a(new js(bei.access$500(this.this$0).K().e()));
    }
    catch (UnknownHostException unknownhostexception)
    {
      if (bei.a(this.this$0)) {
        return;
      }
      bei.a().error("Couldn't connect to server", unknownhostexception);
      bei.access$700(this.this$0).a(new bep(bei.c(this.this$0), "connect.failed", new fb("disconnect.genericReason", new Object[] { "Unknown host" })));
    }
    catch (Exception exception)
    {
      if (bei.a(this.this$0)) {
        return;
      }
      bei.a().error("Couldn't connect to server", exception);
      String s = exception.toString();
      if (inetaddress != null)
      {
        String s1 = inetaddress.toString() + ":" + this.val$port;
        s = s.replaceAll(s1, "");
      }
      bei.access$800(this.this$0).a(new bep(bei.c(this.this$0), "connect.failed", new fb("disconnect.genericReason", new Object[] { s })));
    }
  }
}
