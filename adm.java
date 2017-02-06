public class adm
  extends ado
{
  private final Class<? extends xr> a;
  
  public adm(Class<? extends xr> ☃)
  {
    this.a = ☃;
    a(acq.c);
  }
  
  public qo a(adq ☃, zj ☃, aht ☃, cj ☃, qm ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    cj ☃ = ☃.a(☃);
    if ((☃ == cq.a) || (☃ == cq.b) || (!☃.a(☃, ☃, ☃))) {
      return qo.c;
    }
    xr ☃ = a(☃, ☃, ☃);
    if ((☃ != null) && (☃.k()))
    {
      if (!☃.E)
      {
        ☃.o();
        ☃.a(☃);
      }
      ☃.b -= 1;
    }
    return qo.a;
  }
  
  private xr a(aht ☃, cj ☃, cq ☃)
  {
    if (this.a == xu.class) {
      return new xu(☃, ☃, ☃);
    }
    if (this.a == xs.class) {
      return new xs(☃, ☃, ☃);
    }
    return null;
  }
}
