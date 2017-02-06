import io.netty.buffer.ByteBuf;
import net.minecraft.server.MinecraftServer;

public class apy
  extends apv
{
  private boolean a;
  private boolean f;
  private boolean g;
  private boolean h;
  
  public void b(dn ☃)
  {
    super.b(☃);
    this.i.a(☃);
    ☃.a("powered", d());
    ☃.a("conditionMet", g());
    ☃.a("auto", e());
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    this.i.b(☃);
    a(☃.p("powered"));
    c(☃.p("conditionMet"));
    b(☃.p("auto"));
  }
  
  public ff<?> D_()
  {
    if (h())
    {
      d(false);
      dn ☃ = new dn();
      b(☃);
      return new fs(this.c, 2, ☃);
    }
    return null;
  }
  
  public boolean B()
  {
    return true;
  }
  
  public ahj b()
  {
    return this.i;
  }
  
  public n c()
  {
    return this.i.o();
  }
  
  public void a(boolean ☃)
  {
    this.a = ☃;
  }
  
  public boolean d()
  {
    return this.a;
  }
  
  public boolean e()
  {
    return this.f;
  }
  
  public void b(boolean ☃)
  {
    boolean ☃ = this.f;
    this.f = ☃;
    if ((!☃) && (☃) && (!this.a) && (this.b != null) && (i() != apy.a.a))
    {
      ajt ☃ = w();
      if ((☃ instanceof akk))
      {
        cj ☃ = v();
        akk ☃ = (akk)☃;
        this.g = ((!j()) || (☃.e(this.b, ☃, this.b.o(☃))));
        
        this.b.a(☃, ☃, ☃.a(this.b));
        if (this.g) {
          ☃.c(this.b, ☃);
        }
      }
    }
  }
  
  public boolean g()
  {
    return this.g;
  }
  
  public void c(boolean ☃)
  {
    this.g = ☃;
  }
  
  public boolean h()
  {
    return this.h;
  }
  
  public void d(boolean ☃)
  {
    this.h = ☃;
  }
  
  public apy.a i()
  {
    ajt ☃ = w();
    if (☃ == aju.bX) {
      return apy.a.c;
    }
    if (☃ == aju.dc) {
      return apy.a.b;
    }
    if (☃ == aju.dd) {
      return apy.a.a;
    }
    return apy.a.c;
  }
  
  public boolean j()
  {
    arc ☃ = this.b.o(v());
    if ((☃.t() instanceof akk)) {
      return ((Boolean)☃.c(akk.b)).booleanValue();
    }
    return false;
  }
  
  public void z()
  {
    this.e = null;
    super.z();
  }
  
  private final ahj i = new ahj()
  {
    public cj c()
    {
      return apy.this.c;
    }
    
    public bbj d()
    {
      return new bbj(apy.this.c.p() + 0.5D, apy.this.c.q() + 0.5D, apy.this.c.r() + 0.5D);
    }
    
    public aht e()
    {
      return apy.this.D();
    }
    
    public void a(String ☃)
    {
      super.a(☃);
      apy.this.v_();
    }
    
    public void i()
    {
      arc ☃ = apy.this.b.o(apy.this.c);
      apy.this.D().a(apy.this.c, ☃, ☃, 3);
    }
    
    public int j()
    {
      return 0;
    }
    
    public void a(ByteBuf ☃)
    {
      ☃.writeInt(apy.this.c.p());
      ☃.writeInt(apy.this.c.q());
      ☃.writeInt(apy.this.c.r());
    }
    
    public rr f()
    {
      return null;
    }
    
    public MinecraftServer h()
    {
      return apy.this.b.u();
    }
  };
  
  public static enum a
  {
    private a() {}
  }
}
