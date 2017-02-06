public class abn
  implements qg
{
  private final ahf a;
  private adq[] b = new adq[3];
  private final zj c;
  private ahg d;
  private int e;
  
  public abn(zj ☃, ahf ☃)
  {
    this.c = ☃;
    this.a = ☃;
  }
  
  public int u_()
  {
    return this.b.length;
  }
  
  public adq a(int ☃)
  {
    return this.b[☃];
  }
  
  public adq a(int ☃, int ☃)
  {
    if ((☃ == 2) && (this.b[☃] != null)) {
      return qh.a(this.b, ☃, this.b[☃].b);
    }
    adq ☃ = qh.a(this.b, ☃, ☃);
    if ((☃ != null) && (e(☃))) {
      h();
    }
    return ☃;
  }
  
  private boolean e(int ☃)
  {
    return (☃ == 0) || (☃ == 1);
  }
  
  public adq b(int ☃)
  {
    return qh.a(this.b, ☃);
  }
  
  public void a(int ☃, adq ☃)
  {
    this.b[☃] = ☃;
    if ((☃ != null) && (☃.b > w_())) {
      ☃.b = w_();
    }
    if (e(☃)) {
      h();
    }
  }
  
  public String h_()
  {
    return "mob.villager";
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
  
  public int w_()
  {
    return 64;
  }
  
  public boolean a(zj ☃)
  {
    return this.a.t_() == ☃;
  }
  
  public void b(zj ☃) {}
  
  public void c(zj ☃) {}
  
  public boolean b(int ☃, adq ☃)
  {
    return true;
  }
  
  public void v_()
  {
    h();
  }
  
  public void h()
  {
    this.d = null;
    
    adq ☃ = this.b[0];
    adq ☃ = this.b[1];
    if (☃ == null)
    {
      ☃ = ☃;
      ☃ = null;
    }
    if (☃ == null)
    {
      a(2, null);
    }
    else
    {
      ahh ☃ = this.a.b_(this.c);
      if (☃ != null)
      {
        ahg ☃ = ☃.a(☃, ☃, this.e);
        if ((☃ != null) && (!☃.h()))
        {
          this.d = ☃;
          a(2, ☃.d().k());
        }
        else if (☃ != null)
        {
          ☃ = ☃.a(☃, ☃, this.e);
          if ((☃ != null) && (!☃.h()))
          {
            this.d = ☃;
            a(2, ☃.d().k());
          }
          else
          {
            a(2, null);
          }
        }
        else
        {
          a(2, null);
        }
      }
    }
    this.a.a(a(2));
  }
  
  public ahg i()
  {
    return this.d;
  }
  
  public void d(int ☃)
  {
    this.e = ☃;
    h();
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
    for (int ☃ = 0; ☃ < this.b.length; ☃++) {
      this.b[☃] = null;
    }
  }
}
