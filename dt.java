import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class dt
  extends eb.a
{
  private int b;
  
  dt() {}
  
  public dt(int ☃)
  {
    this.b = ☃;
  }
  
  void a(DataOutput ☃)
    throws IOException
  {
    ☃.writeInt(this.b);
  }
  
  void a(DataInput ☃, int ☃, dw ☃)
    throws IOException
  {
    ☃.a(96L);
    this.b = ☃.readInt();
  }
  
  public byte a()
  {
    return 3;
  }
  
  public String toString()
  {
    return "" + this.b;
  }
  
  public eb b()
  {
    return new dt(this.b);
  }
  
  public boolean equals(Object ☃)
  {
    if (super.equals(☃))
    {
      dt ☃ = (dt)☃;
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
    return (short)(this.b & 0xFFFF);
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
