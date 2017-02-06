import java.util.Random;

public class bmg
  extends blx
{
  private static final Random a = new Random();
  private int G = 128;
  
  protected bmg(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, 0.5D - a.nextDouble(), ☃, 0.5D - a.nextDouble());
    this.j *= 0.20000000298023224D;
    if ((☃ == 0.0D) && (☃ == 0.0D))
    {
      this.i *= 0.10000000149011612D;
      this.k *= 0.10000000149011612D;
    }
    this.w *= 0.75F;
    
    this.v = ((int)(8.0D / (Math.random() * 0.8D + 0.2D)));
  }
  
  public boolean c()
  {
    return true;
  }
  
  public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    float ☃ = (this.u + ☃) / this.v * 32.0F;
    ☃ = on.a(☃, 0.0F, 1.0F);
    
    super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public void a()
  {
    this.c = this.f;
    this.d = this.g;
    this.e = this.h;
    if (this.u++ >= this.v) {
      i();
    }
    b(this.G + (7 - this.u * 8 / this.v));
    
    this.j += 0.004D;
    a(this.i, this.j, this.k);
    if (this.g == this.d)
    {
      this.i *= 1.1D;
      this.k *= 1.1D;
    }
    this.i *= 0.9599999785423279D;
    this.j *= 0.9599999785423279D;
    this.k *= 0.9599999785423279D;
    if (this.l)
    {
      this.i *= 0.699999988079071D;
      this.k *= 0.699999988079071D;
    }
  }
  
  public void c(int ☃)
  {
    this.G = ☃;
  }
  
  public static class d
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new bmg(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
  
  public static class c
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      blx ☃ = new bmg(☃, ☃, ☃, ☃, ☃, ☃, ☃);
      ☃.a((float)☃, (float)☃, (float)☃);
      return ☃;
    }
  }
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      blx ☃ = new bmg(☃, ☃, ☃, ☃, ☃, ☃, ☃);
      ☃.e(0.15F);
      ☃.a((float)☃, (float)☃, (float)☃);
      return ☃;
    }
  }
  
  public static class e
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      blx ☃ = new bmg(☃, ☃, ☃, ☃, ☃, ☃, ☃);
      ((bmg)☃).c(144);
      float ☃ = ☃.r.nextFloat() * 0.5F + 0.35F;
      ☃.a(1.0F * ☃, 0.0F * ☃, 1.0F * ☃);
      return ☃;
    }
  }
  
  public static class b
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      blx ☃ = new bmg(☃, ☃, ☃, ☃, ☃, ☃, ☃);
      ((bmg)☃).c(144);
      return ☃;
    }
  }
}
