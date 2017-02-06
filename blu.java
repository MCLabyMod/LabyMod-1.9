import java.util.Random;

public class blu
  extends blx
{
  private float a;
  
  protected blu(aht ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D);
    this.i *= 0.800000011920929D;
    this.j *= 0.800000011920929D;
    this.k *= 0.800000011920929D;
    this.j = (this.p.nextFloat() * 0.4F + 0.05F);
    
    this.y = (this.z = this.A = 1.0F);
    this.w *= (this.p.nextFloat() * 2.0F + 0.2F);
    this.a = this.w;
    
    this.v = ((int)(16.0D / (Math.random() * 0.8D + 0.2D)));
    b(49);
  }
  
  public int a(float ☃)
  {
    float ☃ = (this.u + ☃) / this.v;
    ☃ = on.a(☃, 0.0F, 1.0F);
    int ☃ = super.a(☃);
    
    int ☃ = 240;
    int ☃ = ☃ >> 16 & 0xFF;
    return ☃ | ☃ << 16;
  }
  
  public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    float ☃ = (this.u + ☃) / this.v;
    this.w = (this.a * (1.0F - ☃ * ☃));
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
    float ☃ = this.u / this.v;
    if (this.p.nextFloat() > ☃) {
      this.b.a(cy.l, this.f, this.g, this.h, this.i, this.j, this.k, new int[0]);
    }
    this.j -= 0.03D;
    a(this.i, this.j, this.k);
    this.i *= 0.9990000128746033D;
    this.j *= 0.9990000128746033D;
    this.k *= 0.9990000128746033D;
    if (this.l)
    {
      this.i *= 0.699999988079071D;
      this.k *= 0.699999988079071D;
    }
  }
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new blu(☃, ☃, ☃, ☃);
    }
  }
}
