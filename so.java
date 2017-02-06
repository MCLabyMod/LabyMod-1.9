public abstract class so
  implements sl
{
  private final sl a;
  private final String b;
  private final double c;
  private boolean d;
  
  protected so(sl ☃, String ☃, double ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    if (☃ == null) {
      throw new IllegalArgumentException("Name cannot be null!");
    }
  }
  
  public String a()
  {
    return this.b;
  }
  
  public double b()
  {
    return this.c;
  }
  
  public boolean c()
  {
    return this.d;
  }
  
  public so a(boolean ☃)
  {
    this.d = ☃;
    return this;
  }
  
  public sl d()
  {
    return this.a;
  }
  
  public int hashCode()
  {
    return this.b.hashCode();
  }
  
  public boolean equals(Object ☃)
  {
    return ((☃ instanceof sl)) && (this.b.equals(((sl)☃).a()));
  }
}
