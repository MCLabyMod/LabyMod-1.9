public class qf
  implements qs
{
  private String a;
  private qs b;
  private qs c;
  
  public qf(String ☃, qs ☃, qs ☃)
  {
    this.a = ☃;
    if (☃ == null) {
      ☃ = ☃;
    }
    if (☃ == null) {
      ☃ = ☃;
    }
    this.b = ☃;
    this.c = ☃;
    if (☃.x_()) {
      ☃.a(☃.y_());
    } else if (☃.x_()) {
      ☃.a(☃.y_());
    }
  }
  
  public int u_()
  {
    return this.b.u_() + this.c.u_();
  }
  
  public boolean a(qg ☃)
  {
    return (this.b == ☃) || (this.c == ☃);
  }
  
  public String h_()
  {
    if (this.b.o_()) {
      return this.b.h_();
    }
    if (this.c.o_()) {
      return this.c.h_();
    }
    return this.a;
  }
  
  public boolean o_()
  {
    return (this.b.o_()) || (this.c.o_());
  }
  
  public eu i_()
  {
    if (o_()) {
      return new fa(h_());
    }
    return new fb(h_(), new Object[0]);
  }
  
  public adq a(int ☃)
  {
    if (☃ >= this.b.u_()) {
      return this.c.a(☃ - this.b.u_());
    }
    return this.b.a(☃);
  }
  
  public adq a(int ☃, int ☃)
  {
    if (☃ >= this.b.u_()) {
      return this.c.a(☃ - this.b.u_(), ☃);
    }
    return this.b.a(☃, ☃);
  }
  
  public adq b(int ☃)
  {
    if (☃ >= this.b.u_()) {
      return this.c.b(☃ - this.b.u_());
    }
    return this.b.b(☃);
  }
  
  public void a(int ☃, adq ☃)
  {
    if (☃ >= this.b.u_()) {
      this.c.a(☃ - this.b.u_(), ☃);
    } else {
      this.b.a(☃, ☃);
    }
  }
  
  public int w_()
  {
    return this.b.w_();
  }
  
  public void v_()
  {
    this.b.v_();
    this.c.v_();
  }
  
  public boolean a(zj ☃)
  {
    return (this.b.a(☃)) && (this.c.a(☃));
  }
  
  public void b(zj ☃)
  {
    this.b.b(☃);
    this.c.b(☃);
  }
  
  public void c(zj ☃)
  {
    this.b.c(☃);
    this.c.c(☃);
  }
  
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
  
  public boolean x_()
  {
    return (this.b.x_()) || (this.c.x_());
  }
  
  public void a(qr ☃)
  {
    this.b.a(☃);
    this.c.a(☃);
  }
  
  public qr y_()
  {
    return this.b.y_();
  }
  
  public String k()
  {
    return this.b.k();
  }
  
  public aau a(zi ☃, zj ☃)
  {
    return new abb(☃, this, ☃);
  }
  
  public void l()
  {
    this.b.l();
    this.c.l();
  }
}
