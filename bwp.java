public class bwp
  implements Comparable<bwp>
{
  private final String a;
  private final String b;
  private final String c;
  private final boolean d;
  
  public bwp(String ☃, String ☃, String ☃, boolean ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
  }
  
  public String a()
  {
    return this.a;
  }
  
  public boolean b()
  {
    return this.d;
  }
  
  public String toString()
  {
    return String.format("%s (%s)", new Object[] { this.c, this.b });
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if (!(☃ instanceof bwp)) {
      return false;
    }
    return this.a.equals(((bwp)☃).a);
  }
  
  public int hashCode()
  {
    return this.a.hashCode();
  }
  
  public int a(bwp ☃)
  {
    return this.a.compareTo(☃.a);
  }
}
