import java.net.UnknownHostException;

class bgu$1
  implements Runnable
{
  bgu$1(bgu this$0) {}
  
  public void run()
  {
    try
    {
      bgu.b(this.this$0).g().a(bgu.a(this.this$0));
    }
    catch (UnknownHostException var2)
    {
      bgu.a(this.this$0).e = -1L;
      bgu.a(this.this$0).d = (a.e + "Can't resolve hostname");
    }
    catch (Exception var3)
    {
      bgu.a(this.this$0).e = -1L;
      bgu.a(this.this$0).d = (a.e + "Can't connect to server.");
    }
  }
}
