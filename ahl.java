public class ahl
{
  private cj a;
  private ajt b;
  private int c;
  private int d;
  
  public ahl(cj ☃, ajt ☃, int ☃, int ☃)
  {
    this.a = ☃;
    this.c = ☃;
    this.d = ☃;
    this.b = ☃;
  }
  
  public cj a()
  {
    return this.a;
  }
  
  public int b()
  {
    return this.c;
  }
  
  public int c()
  {
    return this.d;
  }
  
  public ajt d()
  {
    return this.b;
  }
  
  public boolean equals(Object ☃)
  {
    if ((☃ instanceof ahl))
    {
      ahl ☃ = (ahl)☃;
      return (this.a.equals(☃.a)) && (this.c == ☃.c) && (this.d == ☃.d) && (this.b == ☃.b);
    }
    return false;
  }
  
  public String toString()
  {
    return "TE(" + this.a + ")," + this.c + "," + this.d + "," + this.b;
  }
}
