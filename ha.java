import java.io.IOException;
import java.util.EnumSet;
import java.util.Set;

public class ha
  implements ff<fi>
{
  private double a;
  private double b;
  private double c;
  private float d;
  private float e;
  private Set<ha.a> f;
  private int g;
  
  public ha() {}
  
  public ha(double ☃, double ☃, double ☃, float ☃, float ☃, Set<ha.a> ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
    this.f = ☃;
    this.g = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readDouble();
    this.b = ☃.readDouble();
    this.c = ☃.readDouble();
    this.d = ☃.readFloat();
    this.e = ☃.readFloat();
    this.f = ha.a.a(☃.readUnsignedByte());
    this.g = ☃.g();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeDouble(this.a);
    ☃.writeDouble(this.b);
    ☃.writeDouble(this.c);
    ☃.writeFloat(this.d);
    ☃.writeFloat(this.e);
    ☃.writeByte(ha.a.a(this.f));
    ☃.b(this.g);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public double a()
  {
    return this.a;
  }
  
  public double b()
  {
    return this.b;
  }
  
  public double c()
  {
    return this.c;
  }
  
  public float d()
  {
    return this.d;
  }
  
  public float e()
  {
    return this.e;
  }
  
  public int f()
  {
    return this.g;
  }
  
  public Set<ha.a> g()
  {
    return this.f;
  }
  
  public static enum a
  {
    private final int f;
    
    private a(int ☃)
    {
      this.f = ☃;
    }
    
    private int a()
    {
      return 1 << this.f;
    }
    
    private boolean b(int ☃)
    {
      return (☃ & a()) == a();
    }
    
    public static Set<a> a(int ☃)
    {
      Set<a> ☃ = EnumSet.noneOf(a.class);
      for (a ☃ : values()) {
        if (☃.b(☃)) {
          ☃.add(☃);
        }
      }
      return ☃;
    }
    
    public static int a(Set<a> ☃)
    {
      int ☃ = 0;
      for (a ☃ : ☃) {
        ☃ |= ☃.a();
      }
      return ☃;
    }
  }
}
