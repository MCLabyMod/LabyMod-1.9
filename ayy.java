public class ayy
{
  private byte a;
  private byte b;
  private byte c;
  private byte d;
  
  public ayy(byte ☃, byte ☃, byte ☃, byte ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
  }
  
  public ayy(ayy ☃)
  {
    this.a = ☃.a;
    this.b = ☃.b;
    this.c = ☃.c;
    this.d = ☃.d;
  }
  
  public byte a()
  {
    return this.a;
  }
  
  public byte b()
  {
    return this.b;
  }
  
  public byte c()
  {
    return this.c;
  }
  
  public byte d()
  {
    return this.d;
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if (!(☃ instanceof ayy)) {
      return false;
    }
    ayy ☃ = (ayy)☃;
    if (this.a != ☃.a) {
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
    int ☃ = this.a;
    ☃ = 31 * ☃ + this.b;
    ☃ = 31 * ☃ + this.c;
    ☃ = 31 * ☃ + this.d;
    return ☃;
  }
}
