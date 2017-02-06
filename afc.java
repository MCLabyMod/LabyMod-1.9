public class afc
  extends ado
{
  public afc()
  {
    d(1);
  }
  
  public qp<adq> a(adq ☃, aht ☃, zj ☃, qm ☃)
  {
    ☃.a(☃, ☃);
    ☃.b(nt.b(this));
    return new qp(qo.a, ☃);
  }
  
  public static boolean b(dn ☃)
  {
    if (☃ == null) {
      return false;
    }
    if (!☃.b("pages", 9)) {
      return false;
    }
    du ☃ = ☃.c("pages", 8);
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      String ☃ = ☃.g(☃);
      if (☃ == null) {
        return false;
      }
      if (☃.length() > 32767) {
        return false;
      }
    }
    return true;
  }
}
