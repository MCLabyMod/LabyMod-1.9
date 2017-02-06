import net.minecraft.server.MinecraftServer;

public class ll
  extends lp
{
  private lp a;
  
  public ll(MinecraftServer ☃, azi ☃, int ☃, lp ☃, oo ☃)
  {
    super(☃, ☃, new aze(☃.T()), ☃, ☃);
    this.a = ☃;
    
    ☃.aj().a(new art()
    {
      public void a(arv ☃, double ☃)
      {
        ll.this.aj().a(☃);
      }
      
      public void a(arv ☃, double ☃, double ☃, long ☃)
      {
        ll.this.aj().a(☃, ☃, ☃);
      }
      
      public void a(arv ☃, double ☃, double ☃)
      {
        ll.this.aj().c(☃, ☃);
      }
      
      public void a(arv ☃, int ☃)
      {
        ll.this.aj().b(☃);
      }
      
      public void b(arv ☃, int ☃)
      {
        ll.this.aj().c(☃);
      }
      
      public void b(arv ☃, double ☃)
      {
        ll.this.aj().c(☃);
      }
      
      public void c(arv ☃, double ☃)
      {
        ll.this.aj().b(☃);
      }
    });
  }
  
  protected void a() {}
  
  public aht b()
  {
    this.z = this.a.X();
    this.D = this.a.ad();
    this.B = this.a.ak();
    
    String ☃ = vr.a(this.s);
    vr ☃ = (vr)this.z.a(vr.class, ☃);
    if (☃ == null)
    {
      this.A = new vr(this);
      this.z.a(☃, this.A);
    }
    else
    {
      this.A = ☃;
      this.A.a(this);
    }
    return this;
  }
  
  public void c()
  {
    this.s.q();
  }
}
