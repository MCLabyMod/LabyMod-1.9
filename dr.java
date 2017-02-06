import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class dr
  extends eb.a
{
  private float b;
  
  dr() {}
  
  public dr(float ☃)
  {
    this.b = ☃;
  }
  
  void a(DataOutput ☃)
    throws IOException
  {
    ☃.writeFloat(this.b);
  }
  
  void a(DataInput ☃, int ☃, dw ☃)
    throws IOException
  {
    ☃.a(96L);
    this.b = ☃.readFloat();
  }
  
  public byte a()
  {
    return 5;
  }
  
  public String toString()
  {
    return "" + this.b + "f";
  }
  
  public eb b()
  {
    return new dr(this.b);
  }
  
  public boolean equals(Object ☃)
  {
    if (super.equals(☃))
    {
      dr ☃ = (dr)☃;
      return this.b == ☃.b;
    }
    return false;
  }
  
  public int hashCode()
  {
    return super.hashCode() ^ Float.floatToIntBits(this.b);
  }
  
  public long c()
  {
    return this.b;
  }
  
  public int d()
  {
    return on.d(this.b);
  }
  
  public short e()
  {
    return (short)(on.d(this.b) & 0xFFFF);
  }
  
  public byte f()
  {
    return (byte)(on.d(this.b) & 0xFF);
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
