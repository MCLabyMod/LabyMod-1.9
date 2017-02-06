public class aey
  extends acc
{
  private String[] b;
  
  public aey(ajt ☃, boolean ☃)
  {
    super(☃);
    if (☃)
    {
      e(0);
      a(true);
    }
  }
  
  public int a(int ☃)
  {
    return ☃;
  }
  
  public aey a(String[] ☃)
  {
    this.b = ☃;
    return this;
  }
  
  public String f_(adq ☃)
  {
    if (this.b == null) {
      return super.f_(☃);
    }
    int ☃ = ☃.i();
    if ((☃ >= 0) && (☃ < this.b.length)) {
      return super.f_(☃) + "." + this.b[☃];
    }
    return super.f_(☃);
  }
}
