import java.util.Random;

public class aqd
  extends apv
  implements ky, qn
{
  public int a;
  public float f;
  public float g;
  public float h;
  public float i;
  public float j;
  public float k;
  public float l;
  public float m;
  public float n;
  private static Random o = new Random();
  private String p;
  
  public void b(dn ☃)
  {
    super.b(☃);
    if (o_()) {
      ☃.a("CustomName", this.p);
    }
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    if (☃.b("CustomName", 8)) {
      this.p = ☃.l("CustomName");
    }
  }
  
  public void c()
  {
    this.k = this.j;
    this.m = this.l;
    
    zj ☃ = this.b.a(this.c.p() + 0.5F, this.c.q() + 0.5F, this.c.r() + 0.5F, 3.0D, false);
    if (☃ != null)
    {
      double ☃ = ☃.p - (this.c.p() + 0.5F);
      double ☃ = ☃.r - (this.c.r() + 0.5F);
      
      this.n = ((float)on.b(☃, ☃));
      
      this.j += 0.1F;
      if ((this.j < 0.5F) || (o.nextInt(40) == 0))
      {
        float ☃ = this.h;
        do
        {
          this.h += o.nextInt(4) - o.nextInt(4);
        } while (☃ == this.h);
      }
    }
    else
    {
      this.n += 0.02F;
      this.j -= 0.1F;
    }
    while (this.l >= 3.1415927F) {
      this.l -= 6.2831855F;
    }
    while (this.l < -3.1415927F) {
      this.l += 6.2831855F;
    }
    while (this.n >= 3.1415927F) {
      this.n -= 6.2831855F;
    }
    while (this.n < -3.1415927F) {
      this.n += 6.2831855F;
    }
    float ☃ = this.n - this.l;
    while (☃ >= 3.1415927F) {
      ☃ -= 6.2831855F;
    }
    while (☃ < -3.1415927F) {
      ☃ += 6.2831855F;
    }
    this.l += ☃ * 0.4F;
    
    this.j = on.a(this.j, 0.0F, 1.0F);
    
    this.a += 1;
    this.g = this.f;
    
    float ☃ = (this.h - this.f) * 0.4F;
    float ☃ = 0.2F;
    ☃ = on.a(☃, -☃, ☃);
    this.i += (☃ - this.i) * 0.9F;
    
    this.f += this.i;
  }
  
  public String h_()
  {
    return o_() ? this.p : "container.enchant";
  }
  
  public boolean o_()
  {
    return (this.p != null) && (!this.p.isEmpty());
  }
  
  public void a(String ☃)
  {
    this.p = ☃;
  }
  
  public eu i_()
  {
    if (o_()) {
      return new fa(h_());
    }
    return new fb(h_(), new Object[0]);
  }
  
  public aau a(zi ☃, zj ☃)
  {
    return new abf(☃, this.b, this.c);
  }
  
  public String k()
  {
    return "minecraft:enchanting_table";
  }
}
