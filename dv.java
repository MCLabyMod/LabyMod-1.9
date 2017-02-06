import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class dv
  extends eb.a
{
  private long b;
  
  dv() {}
  
  public dv(long ☃)
  {
    this.b = ☃;
  }
  
  void a(DataOutput ☃)
    throws IOException
  {
    ☃.writeLong(this.b);
  }
  
  void a(DataInput ☃, int ☃, dw ☃)
    throws IOException
  {
    ☃.a(128L);
    this.b = ☃.readLong();
  }
  
  public byte a()
  {
    return 4;
  }
  
  public String toString()
  {
    return "" + this.b + "L";
  }
  
  public eb b()
  {
    return new dv(this.b);
  }
  
  public boolean equals(Object ☃)
  {
    if (super.equals(☃))
    {
      dv ☃ = (dv)☃;
      return this.b == ☃.b;
    }
    return false;
  }
  
  public int hashCode()
  {
    return super.hashCode() ^ (int)(this.b ^ this.b >>> 32);
  }
  
  public long c()
  {
    return this.b;
  }
  
  public int d()
  {
    return (int)(this.b & 0xFFFFFFFFFFFFFFFF);
  }
  
  public short e()
  {
    return (short)(int)(this.b & 0xFFFF);
  }
  
  public byte f()
  {
    return (byte)(int)(this.b & 0xFF);
  }
  
  public double g()
  {
    return this.b;
  }
  
  public float h()
  {
    return (float)this.b;
  }
}
