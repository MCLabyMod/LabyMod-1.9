import java.util.concurrent.Callable;

class bcf$14
  implements Callable<String>
{
  bcf$14(bcf this$0) {}
  
  public String a()
    throws Exception
  {
    return this.this$0.B.a ? this.this$0.B.c() : "N/A (disabled)";
  }
}
