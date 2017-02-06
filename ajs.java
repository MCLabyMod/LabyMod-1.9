import java.util.Random;

public class ajs
  extends akn
{
  public static final arq a = arq.a("age", 0, 3);
  private static final bbh[] d = { new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D) };
  
  protected arq e()
  {
    return a;
  }
  
  public int g()
  {
    return 3;
  }
  
  protected ado h()
  {
    return ads.cU;
  }
  
  protected ado i()
  {
    return ads.cV;
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (☃.nextInt(3) == 0) {
      e(☃, ☃, ☃);
    } else {
      super.b(☃, ☃, ☃, ☃);
    }
  }
  
  protected int b(aht ☃)
  {
    return super.b(☃) / 3;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return d[((Integer)☃.c(e())).intValue()];
  }
}
