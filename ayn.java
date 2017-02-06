public class ayn
{
  public final int a;
  public final int b;
  public final int c;
  private final int n;
  public int d = -1;
  public float e;
  public float f;
  public float g;
  public ayn h;
  public boolean i;
  public float j = 0.0F;
  public float k = 0.0F;
  public float l = 0.0F;
  public aym m = aym.a;
  
  public ayn(int ☃, int ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    
    this.n = b(☃, ☃, ☃);
  }
  
  public ayn a(int ☃, int ☃, int ☃)
  {
    ayn ☃ = new ayn(☃, ☃, ☃);
    ☃.d = this.d;
    ☃.e = this.e;
    ☃.f = this.f;
    ☃.g = this.g;
    ☃.h = this.h;
    ☃.i = this.i;
    ☃.j = this.j;
    ☃.k = this.k;
    ☃.l = this.l;
    ☃.m = this.m;
    return ☃;
  }
  
  public static int b(int ☃, int ☃, int ☃)
  {
    return ☃ & 0xFF | (☃ & 0x7FFF) << 8 | (☃ & 0x7FFF) << 24 | (☃ < 0 ? Integer.MIN_VALUE : 0) | (☃ < 0 ? 32768 : 0);
  }
  
  public float a(ayn ☃)
  {
    float ☃ = ☃.a - this.a;
    float ☃ = ☃.b - this.b;
    float ☃ = ☃.c - this.c;
    return on.c(☃ * ☃ + ☃ * ☃ + ☃ * ☃);
  }
  
  public float b(ayn ☃)
  {
    float ☃ = ☃.a - this.a;
    float ☃ = ☃.b - this.b;
    float ☃ = ☃.c - this.c;
    return ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
  }
  
  public float c(ayn ☃)
  {
    float ☃ = Math.abs(☃.a - this.a);
    float ☃ = Math.abs(☃.b - this.b);
    float ☃ = Math.abs(☃.c - this.c);
    return ☃ + ☃ + ☃;
  }
  
  public boolean equals(Object ☃)
  {
    if ((☃ instanceof ayn))
    {
      ayn ☃ = (ayn)☃;
      return (this.n == ☃.n) && (this.a == ☃.a) && (this.b == ☃.b) && (this.c == ☃.c);
    }
    return false;
  }
  
  public int hashCode()
  {
    return this.n;
  }
  
  public boolean a()
  {
    return this.d >= 0;
  }
  
  public String toString()
  {
    return this.a + ", " + this.b + ", " + this.c;
  }
  
  public static ayn b(em ☃)
  {
    ayn ☃ = new ayn(☃.readInt(), ☃.readInt(), ☃.readInt());
    ☃.j = ☃.readFloat();
    ☃.k = ☃.readFloat();
    ☃.l = ☃.readFloat();
    ☃.i = ☃.readBoolean();
    ☃.m = aym.values()[☃.readInt()];
    ☃.g = ☃.readFloat();
    return ☃;
  }
}
