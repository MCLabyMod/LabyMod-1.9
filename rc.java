public class rc
{
  public static rc a = new rc("inFire").n();
  public static rc b = new rc("lightningBolt");
  public static rc c = new rc("onFire").k().n();
  public static rc d = new rc("lava").n();
  public static rc e = new rc("inWall").k();
  public static rc f = new rc("drown").k();
  public static rc g = new rc("starve").k().m();
  public static rc h = new rc("cactus");
  public static rc i = new rc("fall").k();
  public static rc j = new rc("flyIntoWall").k();
  public static rc k = new rc("outOfWorld").k().l();
  public static rc l = new rc("generic").k();
  public static rc m = new rc("magic").k().t();
  public static rc n = new rc("wither").k();
  public static rc o = new rc("anvil");
  public static rc p = new rc("fallingBlock");
  public static rc q = new rc("dragonBreath").k();
  private boolean s;
  private boolean t;
  private boolean u;
  
  public static rc a(sa ☃)
  {
    return new rd("mob", ☃);
  }
  
  public static rc a(rr ☃, sa ☃)
  {
    return new re("mob", ☃, ☃);
  }
  
  public static rc a(zj ☃)
  {
    return new rd("player", ☃);
  }
  
  public static rc a(zm ☃, rr ☃)
  {
    return new re("arrow", ☃, ☃).b();
  }
  
  public static rc a(zp ☃, rr ☃)
  {
    if (☃ == null) {
      return new re("onFire", ☃, ☃).n().b();
    }
    return new re("fireball", ☃, ☃).n().b();
  }
  
  public static rc a(rr ☃, rr ☃)
  {
    return new re("thrown", ☃, ☃).b();
  }
  
  public static rc b(rr ☃, rr ☃)
  {
    return new re("indirectMagic", ☃, ☃).k().t();
  }
  
  public static rc a(rr ☃)
  {
    return new rd("thorns", ☃).w().t();
  }
  
  public static rc a(ahp ☃)
  {
    if ((☃ != null) && (☃.c() != null)) {
      return new rd("explosion.player", ☃.c()).q().d();
    }
    return new rc("explosion").q().d();
  }
  
  public static rc b(sa ☃)
  {
    if (☃ != null) {
      return new rd("explosion.player", ☃).q().d();
    }
    return new rc("explosion").q().d();
  }
  
  private float v = 0.3F;
  private boolean w;
  private boolean x;
  private boolean y;
  private boolean z;
  private boolean A;
  public String r;
  
  public boolean a()
  {
    return this.x;
  }
  
  public rc b()
  {
    this.x = true;
    return this;
  }
  
  public boolean c()
  {
    return this.A;
  }
  
  public rc d()
  {
    this.A = true;
    return this;
  }
  
  public boolean e()
  {
    return this.s;
  }
  
  public float f()
  {
    return this.v;
  }
  
  public boolean g()
  {
    return this.t;
  }
  
  public boolean h()
  {
    return this.u;
  }
  
  protected rc(String ☃)
  {
    this.r = ☃;
  }
  
  public rr i()
  {
    return j();
  }
  
  public rr j()
  {
    return null;
  }
  
  protected rc k()
  {
    this.s = true;
    
    this.v = 0.0F;
    return this;
  }
  
  protected rc l()
  {
    this.t = true;
    return this;
  }
  
  protected rc m()
  {
    this.u = true;
    
    this.v = 0.0F;
    return this;
  }
  
  protected rc n()
  {
    this.w = true;
    return this;
  }
  
  public eu c(sa ☃)
  {
    sa ☃ = ☃.bV();
    String ☃ = "death.attack." + this.r;
    String ☃ = ☃ + ".player";
    if ((☃ != null) && (di.c(☃))) {
      return new fb(☃, new Object[] { ☃.i_(), ☃.i_() });
    }
    return new fb(☃, new Object[] { ☃.i_() });
  }
  
  public boolean o()
  {
    return this.w;
  }
  
  public String p()
  {
    return this.r;
  }
  
  public rc q()
  {
    this.y = true;
    return this;
  }
  
  public boolean r()
  {
    return this.y;
  }
  
  public boolean s()
  {
    return this.z;
  }
  
  public rc t()
  {
    this.z = true;
    return this;
  }
  
  public boolean u()
  {
    rr ☃ = j();
    if (((☃ instanceof zj)) && (((zj)☃).bJ.d)) {
      return true;
    }
    return false;
  }
  
  public bbj v()
  {
    return null;
  }
}
