public class aql
  extends apv
{
  public byte a;
  public boolean f;
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("note", this.a);
    ☃.a("powered", this.f);
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    this.a = ☃.f("note");
    this.a = ((byte)on.a(this.a, 0, 24));
    this.f = ☃.p("powered");
  }
  
  public void b()
  {
    this.a = ((byte)((this.a + 1) % 25));
    v_();
  }
  
  public void a(aht ☃, cj ☃)
  {
    if (☃.o(☃.a()).a() != axe.a) {
      return;
    }
    axe ☃ = ☃.o(☃.b()).a();
    
    int ☃ = 0;
    if (☃ == axe.e) {
      ☃ = 1;
    }
    if (☃ == axe.p) {
      ☃ = 2;
    }
    if (☃ == axe.s) {
      ☃ = 3;
    }
    if (☃ == axe.d) {
      ☃ = 4;
    }
    ☃.c(☃, aju.B, ☃, this.a);
  }
}
