import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class dm
  extends eb.a
{
  private byte b;
  
  dm() {}
  
  public dm(byte ☃)
  {
    this.b = ☃;
  }
  
  void a(DataOutput ☃)
    throws IOException
  {
    ☃.writeByte(this.b);
  }
  
  void a(DataInput ☃, int ☃, dw ☃)
    throws IOException
  {
    ☃.a(72L);
    this.b = ☃.readByte();
  }
  
  public byte a()
  {
    return 1;
  }
  
  public String toString()
  {
    return "" + this.b + "b";
  }
  
  public eb b()
  {
    return new dm(this.b);
  }
  
  public boolean equals(Object ☃)
  {
    if (super.equals(☃))
    {
      dm ☃ = (dm)☃;
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
    return (short)this.b;
  }
  
  public byte f()
  {
    return this.b;
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
