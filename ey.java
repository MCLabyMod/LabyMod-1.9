public class ey
  extends es
{
  private final String b;
  
  public ey(String ☃)
  {
    this.b = ☃;
  }
  
  public String g()
  {
    return this.b;
  }
  
  public String e()
  {
    return this.b;
  }
  
  public ey h()
  {
    ey ☃ = new ey(this.b);
    ☃.a(b().m());
    for (eu ☃ : a()) {
      ☃.a(☃.f());
    }
    return ☃;
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if ((☃ instanceof ey))
    {
      ey ☃ = (ey)☃;
      return (this.b.equals(☃.b)) && (super.equals(☃));
    }
    return false;
  }
  
  public String toString()
  {
    return "SelectorComponent{pattern='" + this.b + '\'' + ", siblings=" + this.a + ", style=" + b() + '}';
  }
}
