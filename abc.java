public class abc
  implements qg
{
  private final adq[] a;
  private final int b;
  private final int c;
  private final aau d;
  
  public abc(aau ☃, int ☃, int ☃)
  {
    int ☃ = ☃ * ☃;
    this.a = new adq[☃];
    this.d = ☃;
    this.b = ☃;
    this.c = ☃;
  }
  
  public int u_()
  {
    return this.a.length;
  }
  
  public adq a(int ☃)
  {
    if (☃ >= u_()) {
      return null;
    }
    return this.a[☃];
  }
  
  public adq c(int ☃, int ☃)
  {
    if ((☃ < 0) || (☃ >= this.b) || (☃ < 0) || (☃ > this.c)) {
      return null;
    }
    return a(☃ + ☃ * this.b);
  }
  
  public String h_()
  {
    return "container.crafting";
  }
  
  public boolean o_()
  {
    return false;
  }
  
  public eu i_()
  {
    if (o_()) {
      return new fa(h_());
    }
    return new fb(h_(), new Object[0]);
  }
  
  public adq b(int ☃)
  {
    return qh.a(this.a, ☃);
  }
  
  public adq a(int ☃, int ☃)
  {
    adq ☃ = qh.a(this.a, ☃, ☃);
    if (☃ != null) {
      this.d.a(this);
    }
    return ☃;
  }
  
  public void a(int ☃, adq ☃)
  {
    this.a[☃] = ☃;
    this.d.a(this);
  }
  
  public int w_()
  {
    return 64;
  }
  
  public void v_() {}
  
  public boolean a(zj ☃)
  {
    return true;
  }
  
  public void b(zj ☃) {}
  
  public void c(zj ☃) {}
  
  public boolean b(int ☃, adq ☃)
  {
    return true;
  }
  
  public int c_(int ☃)
  {
    return 0;
  }
  
  public void b(int ☃, int ☃) {}
  
  public int g()
  {
    return 0;
  }
  
  public void l()
  {
    for (int ☃ = 0; ☃ < this.a.length; ☃++) {
      this.a[☃] = null;
    }
  }
  
  public int h()
  {
    return this.c;
  }
  
  public int i()
  {
    return this.b;
  }
}
