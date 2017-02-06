public class ql
{
  private final qk a;
  private final float b;
  
  public ql(qk ☃, long ☃, long ☃, float ☃)
  {
    this.a = ☃;
    this.b = a(☃, ☃, ☃, ☃);
  }
  
  public float b()
  {
    return this.b;
  }
  
  public float c()
  {
    if (this.b < 2.0F) {
      return 0.0F;
    }
    if (this.b > 4.0F) {
      return 1.0F;
    }
    return (this.b - 2.0F) / 2.0F;
  }
  
  private float a(qk ☃, long ☃, long ☃, float ☃)
  {
    if (☃ == qk.a) {
      return 0.0F;
    }
    boolean ☃ = ☃ == qk.d;
    float ☃ = 0.75F;
    
    float ☃ = on.a(((float)☃ + -72000.0F) / 1440000.0F, 0.0F, 1.0F) * 0.25F;
    ☃ += ☃;
    
    float ☃ = 0.0F;
    
    ☃ += on.a((float)☃ / 3600000.0F, 0.0F, 1.0F) * (☃ ? 1.0F : 0.75F);
    ☃ += on.a(☃ * 0.25F, 0.0F, ☃);
    if (☃ == qk.b) {
      ☃ *= 0.5F;
    }
    ☃ += ☃;
    
    return ☃.a() * ☃;
  }
}
