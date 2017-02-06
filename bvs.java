import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bvs
{
  private static final Logger a = ;
  private final bvs.a b;
  private final bvs.b c;
  private int d;
  private int e;
  
  public bvs(int ☃, bvs.a ☃, bvs.b ☃, int ☃)
  {
    if (!a(☃, ☃))
    {
      a.warn("Multiple vertex elements of the same type other than UVs are not supported. Forcing type to UV.");
      this.c = bvs.b.d;
    }
    else
    {
      this.c = ☃;
    }
    this.b = ☃;
    this.d = ☃;
    this.e = ☃;
  }
  
  private final boolean a(int ☃, bvs.b ☃)
  {
    return (☃ == 0) || (☃ == bvs.b.d);
  }
  
  public final bvs.a a()
  {
    return this.b;
  }
  
  public final bvs.b b()
  {
    return this.c;
  }
  
  public final int c()
  {
    return this.e;
  }
  
  public final int d()
  {
    return this.d;
  }
  
  public String toString()
  {
    return this.e + "," + this.c.a() + "," + this.b.b();
  }
  
  public final int e()
  {
    return this.b.a() * this.e;
  }
  
  public final boolean f()
  {
    return this.c == bvs.b.a;
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if ((☃ == null) || (getClass() != ☃.getClass())) {
      return false;
    }
    bvs ☃ = (bvs)☃;
    if (this.e != ☃.e) {
      return false;
    }
    if (this.d != ☃.d) {
      return false;
    }
    if (this.b != ☃.b) {
      return false;
    }
    if (this.c != ☃.c) {
      return false;
    }
    return true;
  }
  
  public int hashCode()
  {
    int ☃ = this.b.hashCode();
    ☃ = 31 * ☃ + this.c.hashCode();
    ☃ = 31 * ☃ + this.d;
    ☃ = 31 * ☃ + this.e;
    return ☃;
  }
  
  public static enum b
  {
    private final String h;
    
    private b(String ☃)
    {
      this.h = ☃;
    }
    
    public String a()
    {
      return this.h;
    }
  }
  
  public static enum a
  {
    private final int h;
    private final String i;
    private final int j;
    
    private a(int ☃, String ☃, int ☃)
    {
      this.h = ☃;
      this.i = ☃;
      this.j = ☃;
    }
    
    public int a()
    {
      return this.h;
    }
    
    public String b()
    {
      return this.i;
    }
    
    public int c()
    {
      return this.j;
    }
  }
}
