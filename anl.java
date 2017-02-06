import java.util.Random;

public class anl
  extends akn
{
  private static final bbh[] a = { new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D) };
  
  protected ado h()
  {
    return ads.cc;
  }
  
  protected ado i()
  {
    return ads.cc;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, float ☃, int ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃);
    if (☃.E) {
      return;
    }
    if ((y(☃)) && (☃.r.nextInt(50) == 0)) {
      a(☃, ☃, new adq(ads.ce));
    }
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return a[((Integer)☃.c(e())).intValue()];
  }
}
