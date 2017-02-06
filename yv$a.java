import java.util.Random;

class yv$a
  extends ug
{
  private final yv a;
  private cq b;
  private boolean c;
  
  public yv$a(yv ☃)
  {
    super(☃, 1.0D, 10);
    this.a = ☃;
    
    a(1);
  }
  
  public boolean a()
  {
    if (!this.a.l.U().b("mobGriefing")) {
      return false;
    }
    if (this.a.A() != null) {
      return false;
    }
    if (!this.a.x().n()) {
      return false;
    }
    Random ☃ = this.a.bF();
    if (☃.nextInt(10) == 0)
    {
      this.b = cq.a(☃);
      
      cj ☃ = new cj(this.a.p, this.a.q + 0.5D, this.a.r).a(this.b);
      arc ☃ = this.a.l.o(☃);
      if (amt.i(☃))
      {
        this.c = true;
        return true;
      }
    }
    this.c = false;
    return super.a();
  }
  
  public boolean b()
  {
    if (this.c) {
      return false;
    }
    return super.b();
  }
  
  public void c()
  {
    if (!this.c)
    {
      super.c();
      return;
    }
    aht ☃ = this.a.l;
    cj ☃ = new cj(this.a.p, this.a.q + 0.5D, this.a.r).a(this.b);
    arc ☃ = ☃.o(☃);
    if (amt.i(☃))
    {
      ☃.a(☃, aju.be.u().a(amt.a, amt.a.a(☃)), 3);
      this.a.E();
      this.a.T();
    }
  }
}
