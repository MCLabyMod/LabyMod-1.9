public class abq
  extends qv
{
  private aqe a;
  
  public abq()
  {
    super("container.enderchest", false, 27);
  }
  
  public void a(aqe ☃)
  {
    this.a = ☃;
  }
  
  public void a(du ☃)
  {
    for (int ☃ = 0; ☃ < u_(); ☃++) {
      a(☃, null);
    }
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      dn ☃ = ☃.b(☃);
      int ☃ = ☃.f("Slot") & 0xFF;
      if ((☃ >= 0) && (☃ < u_())) {
        a(☃, adq.a(☃));
      }
    }
  }
  
  public du h()
  {
    du ☃ = new du();
    for (int ☃ = 0; ☃ < u_(); ☃++)
    {
      adq ☃ = a(☃);
      if (☃ != null)
      {
        dn ☃ = new dn();
        ☃.a("Slot", (byte)☃);
        ☃.b(☃);
        ☃.a(☃);
      }
    }
    return ☃;
  }
  
  public boolean a(zj ☃)
  {
    if ((this.a != null) && (!this.a.a(☃))) {
      return false;
    }
    return super.a(☃);
  }
  
  public void b(zj ☃)
  {
    if (this.a != null) {
      this.a.b();
    }
    super.b(☃);
  }
  
  public void c(zj ☃)
  {
    if (this.a != null) {
      this.a.d();
    }
    super.c(☃);
    this.a = null;
  }
}
