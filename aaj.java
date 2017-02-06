import io.netty.buffer.ByteBuf;
import net.minecraft.server.MinecraftServer;

public class aaj
  extends aah
{
  private static final ke<String> a = kh.a(aaj.class, kg.d);
  private static final ke<eu> b = kh.a(aaj.class, kg.e);
  private final ahj c = new ahj()
  {
    public void i()
    {
      aaj.this.R().b(aaj.k(), m());
      aaj.this.R().b(aaj.l(), l());
    }
    
    public int j()
    {
      return 1;
    }
    
    public void a(ByteBuf ☃)
    {
      ☃.writeInt(aaj.this.O());
    }
    
    public cj c()
    {
      return new cj(aaj.this.p, aaj.this.q + 0.5D, aaj.this.r);
    }
    
    public bbj d()
    {
      return new bbj(aaj.this.p, aaj.this.q, aaj.this.r);
    }
    
    public aht e()
    {
      return aaj.this.l;
    }
    
    public rr f()
    {
      return aaj.this;
    }
    
    public MinecraftServer h()
    {
      return aaj.this.l.u();
    }
  };
  private int d = 0;
  
  public aaj(aht ☃)
  {
    super(☃);
  }
  
  public aaj(aht ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃);
  }
  
  protected void i()
  {
    super.i();
    R().a(a, "");
    R().a(b, new fa(""));
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    this.c.b(☃);
    R().b(a, j().m());
    R().b(b, j().l());
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    this.c.a(☃);
  }
  
  public aah.a v()
  {
    return aah.a.g;
  }
  
  public arc x()
  {
    return aju.bX.u();
  }
  
  public ahj j()
  {
    return this.c;
  }
  
  public void a(int ☃, int ☃, int ☃, boolean ☃)
  {
    if ((☃) && 
      (this.T - this.d >= 4))
    {
      j().a(this.l);
      this.d = this.T;
    }
  }
  
  public boolean a(zj ☃, adq ☃, qm ☃)
  {
    this.c.a(☃);
    return false;
  }
  
  public void a(ke<?> ☃)
  {
    super.a(☃);
    if (b.equals(☃)) {
      try
      {
        this.c.b((eu)R().a(b));
      }
      catch (Throwable localThrowable) {}
    } else if (a.equals(☃)) {
      this.c.a((String)R().a(a));
    }
  }
  
  public boolean br()
  {
    return true;
  }
}
