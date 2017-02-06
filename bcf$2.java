class bcf$2
  extends Thread
{
  bcf$2(bcf this$0, String x0)
  {
    super(x0);
  }
  
  public void run()
  {
    while (this.this$0.C) {
      try
      {
        Thread.sleep(2147483647L);
      }
      catch (InterruptedException localInterruptedException) {}
    }
  }
}
