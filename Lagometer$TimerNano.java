public class Lagometer$TimerNano
{
  public long timeStartNano = 0L;
  public long timeNano = 0L;
  
  public void start()
  {
    if (!Lagometer.active) {
      return;
    }
    if (this.timeStartNano == 0L) {
      this.timeStartNano = System.nanoTime();
    }
  }
  
  public void end()
  {
    if (!Lagometer.active) {
      return;
    }
    if (this.timeStartNano != 0L)
    {
      this.timeNano += System.nanoTime() - this.timeStartNano;
      this.timeStartNano = 0L;
    }
  }
  
  private void reset()
  {
    this.timeNano = 0L;
    this.timeStartNano = 0L;
  }
}
