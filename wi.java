import java.util.Random;

public abstract class wi
  extends sb
  implements rq
{
  public wi(aht ☃)
  {
    super(☃);
  }
  
  public boolean bB()
  {
    return true;
  }
  
  public boolean cF()
  {
    return true;
  }
  
  public boolean cG()
  {
    return this.l.a(bl(), this);
  }
  
  public int C()
  {
    return 120;
  }
  
  protected boolean K()
  {
    return true;
  }
  
  protected int b(zj ☃)
  {
    return 1 + this.l.r.nextInt(3);
  }
  
  public void U()
  {
    int ☃ = aP();
    
    super.U();
    if ((au()) && (!ai()))
    {
      j(--☃);
      if (aP() == -20)
      {
        j(0);
        a(rc.f, 2.0F);
      }
    }
    else
    {
      j(300);
    }
  }
  
  public boolean bd()
  {
    return false;
  }
}
