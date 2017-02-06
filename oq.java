public class oq
{
  private float a;
  private float b;
  private float c;
  
  public float a(float ☃, float ☃)
  {
    this.a += ☃;
    
    ☃ = (this.a - this.b) * ☃;
    this.c += (☃ - this.c) * 0.5F;
    if (((☃ > 0.0F) && (☃ > this.c)) || ((☃ < 0.0F) && (☃ < this.c))) {
      ☃ = this.c;
    }
    this.b += ☃;
    
    return ☃;
  }
  
  public void a()
  {
    this.a = 0.0F;
    this.b = 0.0F;
    this.c = 0.0F;
  }
}
