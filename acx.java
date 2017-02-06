public class acx
  extends ado
{
  public acx()
  {
    this.j = 1;
    e(432);
    a(acq.e);
    
    a(new kk("broken"), new adr()
    {
      public float a(adq ☃, aht ☃, sa ☃)
      {
        return acx.d(☃) ? 0.0F : 1.0F;
      }
    });
    aku.c.a(this, abw.b);
  }
  
  public static boolean d(adq ☃)
  {
    return ☃.h() < ☃.j() - 1;
  }
  
  public boolean a(adq ☃, adq ☃)
  {
    return ☃.b() == ads.aM;
  }
  
  public qp<adq> a(adq ☃, aht ☃, zj ☃, qm ☃)
  {
    rw ☃ = sb.d(☃);
    adq ☃ = ☃.a(☃);
    if (☃ == null)
    {
      ☃.a(☃, ☃.k());
      ☃.b = 0;
      return new qp(qo.a, ☃);
    }
    return new qp(qo.c, ☃);
  }
}
