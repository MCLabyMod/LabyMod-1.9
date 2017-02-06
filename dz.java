import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class dz
  extends eb.a
{
  private short b;
  
  public dz() {}
  
  public dz(short ☃)
  {
    this.b = ☃;
  }
  
  void a(DataOutput ☃)
    throws IOException
  {
    ☃.writeShort(this.b);
  }
  
  void a(DataInput ☃, int ☃, dw ☃)
    throws IOException
  {
    ☃.a(80L);
    this.b = ☃.readShort();
  }
  
  public byte a()
  {
    return 2;
  }
  
  public String toString()
  {
    return "" + this.b + "s";
  }
  
  public eb b()
  {
    return new dz(this.b);
  }
  
  public boolean equals(Object ☃)
  {
    if (super.equals(☃))
    {
      dz ☃ = (dz)☃;
      return this.b == ☃.b;
    }
    return false;
  }
  
  public int hashCode()
  {
    return super.hashCode() ^ this.b;
  }
  
  public long c()
  {
    return this.b;
  }
  
  public int d()
  {
    return this.b;
  }
  
  public short e()
  {
    return this.b;
  }
  
  public byte f()
  {
    return (byte)(this.b & 0xFF);
  }
  
  public double g()
  {
    return this.b;
  }
  
  public float h()
  {
    return this.b;
  }
}
