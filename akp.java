import java.util.Random;

public class akp
  extends ajy
{
  protected static final bbh a = new bbh(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
  
  protected akp()
  {
    super(axe.l);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return a;
  }
  
  public axf r(arc ☃)
  {
    return axf.o;
  }
  
  protected boolean i(arc ☃)
  {
    return (☃.t() == aju.m) || (☃.t() == aju.cz) || (☃.t() == aju.cu) || (☃.t() == aju.d);
  }
  
  public boolean a(ahx ☃, cj ☃)
  {
    return true;
  }
  
  public int a(Random ☃)
  {
    return ☃.nextInt(3);
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ads.A;
  }
  
  public void a(aht ☃, zj ☃, cj ☃, arc ☃, apv ☃, adq ☃)
  {
    if ((☃.E) || (☃ == null) || (☃.b() != ads.bl))
    {
      super.a(☃, ☃, ☃, ☃, ☃, ☃);
    }
    else
    {
      ☃.b(nt.a(this));
      
      a(☃, ☃, new adq(aju.I, 1, 0));
    }
  }
}
