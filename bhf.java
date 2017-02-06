import java.util.List;

public abstract class bhf
  extends bdl
{
  protected final bcf u;
  protected final List<bgz> v;
  
  public bhf(bcf ☃, int ☃, int ☃, List<bgz> ☃)
  {
    super(☃, ☃, ☃, 32, ☃ - 55 + 4, 36);
    this.u = ☃;
    this.v = ☃;
    this.k = false;
    
    a(true, (int)(☃.k.a * 1.5F));
  }
  
  protected void a(int ☃, int ☃, bnu ☃)
  {
    String ☃ = a.t + "" + a.r + e();
    this.u.k.a(☃, ☃ + this.b / 2 - this.u.k.a(☃) / 2, Math.min(this.d + 3, ☃), 16777215);
  }
  
  protected abstract String e();
  
  public List<bgz> f()
  {
    return this.v;
  }
  
  protected int b()
  {
    return f().size();
  }
  
  public bgz c(int ☃)
  {
    return (bgz)f().get(☃);
  }
  
  public int c()
  {
    return this.b;
  }
  
  protected int d()
  {
    return this.f - 6;
  }
}
