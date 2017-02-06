public class zh
{
  public boolean a;
  public boolean b;
  public boolean c;
  public boolean d;
  public boolean e = true;
  private float f = 0.05F;
  private float g = 0.1F;
  
  public void a(dn ☃)
  {
    dn ☃ = new dn();
    
    ☃.a("invulnerable", this.a);
    ☃.a("flying", this.b);
    ☃.a("mayfly", this.c);
    ☃.a("instabuild", this.d);
    ☃.a("mayBuild", this.e);
    ☃.a("flySpeed", this.f);
    ☃.a("walkSpeed", this.g);
    ☃.a("abilities", ☃);
  }
  
  public void b(dn ☃)
  {
    if (☃.b("abilities", 10))
    {
      dn ☃ = ☃.o("abilities");
      
      this.a = ☃.p("invulnerable");
      this.b = ☃.p("flying");
      this.c = ☃.p("mayfly");
      this.d = ☃.p("instabuild");
      if (☃.b("flySpeed", 99))
      {
        this.f = ☃.j("flySpeed");
        this.g = ☃.j("walkSpeed");
      }
      if (☃.b("mayBuild", 1)) {
        this.e = ☃.p("mayBuild");
      }
    }
  }
  
  public float a()
  {
    return this.f;
  }
  
  public void a(float ☃)
  {
    this.f = ☃;
  }
  
  public float b()
  {
    return this.g;
  }
  
  public void b(float ☃)
  {
    this.g = ☃;
  }
}
