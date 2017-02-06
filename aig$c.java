public class aig$c
  extends ov.a
{
  public Class<? extends sb> b;
  public int c;
  public int d;
  
  public aig$c(Class<? extends sb> ☃, int ☃, int ☃, int ☃)
  {
    super(☃);
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
  }
  
  public String toString()
  {
    return this.b.getSimpleName() + "*(" + this.c + "-" + this.d + "):" + this.a;
  }
}
