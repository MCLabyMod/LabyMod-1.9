public class dc
{
  protected final float a;
  protected final float b;
  protected final float c;
  
  public dc(float ☃, float ☃, float ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
  }
  
  public dc(du ☃)
  {
    this.a = ☃.f(0);
    this.b = ☃.f(1);
    this.c = ☃.f(2);
  }
  
  public du a()
  {
    du ☃ = new du();
    ☃.a(new dr(this.a));
    ☃.a(new dr(this.b));
    ☃.a(new dr(this.c));
    return ☃;
  }
  
  public boolean equals(Object ☃)
  {
    if (!(☃ instanceof dc)) {
      return false;
    }
    dc ☃ = (dc)☃;
    return (this.a == ☃.a) && (this.b == ☃.b) && (this.c == ☃.c);
  }
  
  public float b()
  {
    return this.a;
  }
  
  public float c()
  {
    return this.b;
  }
  
  public float d()
  {
    return this.c;
  }
}
