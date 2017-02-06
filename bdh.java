public class bdh
  extends qe
{
  protected float h = 0.0F;
  protected long i = 0L;
  
  public bdh(fv ☃)
  {
    super(☃.a(), ☃.c(), ☃.e(), ☃.f());
    this.h = ☃.d();
    this.b = ☃.d();
    this.i = bcf.I();
    a(☃.g());
    b(☃.h());
    c(☃.i());
  }
  
  public void a(float ☃)
  {
    this.b = f();
    this.h = ☃;
    this.i = bcf.I();
  }
  
  public float f()
  {
    long ☃ = bcf.I() - this.i;
    float ☃ = on.a((float)☃ / 100.0F, 0.0F, 1.0F);
    return this.b + (this.h - this.b) * ☃;
  }
  
  public void a(fv ☃)
  {
    switch (bdh.1.a[☃.b().ordinal()])
    {
    case 1: 
      a(☃.c());
      break;
    case 2: 
      a(☃.d());
      break;
    case 3: 
      a(☃.e());
      a(☃.f());
      break;
    case 4: 
      a(☃.g());
      b(☃.h());
    }
  }
}
