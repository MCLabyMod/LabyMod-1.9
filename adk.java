import java.util.Random;

public class adk
  extends ado
{
  public final int a = 32;
  private final int b;
  private final float c;
  private final boolean d;
  private boolean e;
  private rl m;
  private float n;
  
  public adk(int ☃, float ☃, boolean ☃)
  {
    this.b = ☃;
    this.d = ☃;
    this.c = ☃;
    a(acq.h);
  }
  
  public adk(int ☃, boolean ☃)
  {
    this(☃, 0.6F, ☃);
  }
  
  public adq a(adq ☃, aht ☃, sa ☃)
  {
    ☃.b -= 1;
    if ((☃ instanceof zj))
    {
      zj ☃ = (zj)☃;
      ☃.cS().a(this, ☃);
      ☃.a(null, ☃.p, ☃.q, ☃.r, ng.ee, nh.h, 0.5F, ☃.r.nextFloat() * 0.1F + 0.9F);
      
      a(☃, ☃, ☃);
      ☃.b(nt.b(this));
    }
    return ☃;
  }
  
  protected void a(adq ☃, aht ☃, zj ☃)
  {
    if ((!☃.E) && (this.m != null) && (☃.r.nextFloat() < this.n)) {
      ☃.c(new rl(this.m));
    }
  }
  
  public int e(adq ☃)
  {
    return 32;
  }
  
  public afa f(adq ☃)
  {
    return afa.b;
  }
  
  public qp<adq> a(adq ☃, aht ☃, zj ☃, qm ☃)
  {
    if (☃.l(this.e))
    {
      ☃.c(☃);
      return new qp(qo.a, ☃);
    }
    return new qp(qo.c, ☃);
  }
  
  public int h(adq ☃)
  {
    return this.b;
  }
  
  public float i(adq ☃)
  {
    return this.c;
  }
  
  public boolean g()
  {
    return this.d;
  }
  
  public adk a(rl ☃, float ☃)
  {
    this.m = ☃;
    this.n = ☃;
    return this;
  }
  
  public adk h()
  {
    this.e = true;
    return this;
  }
}
