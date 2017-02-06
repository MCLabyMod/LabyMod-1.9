import java.util.List;

public class adl
  extends adk
{
  public adl(int ☃, float ☃, boolean ☃)
  {
    super(☃, ☃, ☃);
    
    a(true);
  }
  
  public boolean i_(adq ☃)
  {
    return ☃.i() > 0;
  }
  
  public aee g(adq ☃)
  {
    if (☃.i() == 0) {
      return aee.c;
    }
    return aee.d;
  }
  
  protected void a(adq ☃, aht ☃, zj ☃)
  {
    if (!☃.E) {
      if (☃.i() > 0)
      {
        ☃.b(nk.M);
        ☃.c(new rl(rm.j, 400, 1));
        ☃.c(new rl(rm.k, 6000, 0));
        ☃.c(new rl(rm.l, 6000, 0));
        ☃.c(new rl(rm.v, 2400, 3));
      }
      else
      {
        ☃.c(new rl(rm.j, 100, 1));
        ☃.c(new rl(rm.v, 2400, 0));
      }
    }
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    ☃.add(new adq(☃));
    ☃.add(new adq(☃, 1, 1));
  }
}
