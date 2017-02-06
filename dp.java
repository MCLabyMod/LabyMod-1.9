import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class dp
  extends eb.a
{
  private double b;
  
  dp() {}
  
  public dp(double ☃)
  {
    this.b = ☃;
  }
  
  void a(DataOutput ☃)
    throws IOException
  {
    ☃.writeDouble(this.b);
  }
  
  void a(DataInput ☃, int ☃, dw ☃)
    throws IOException
  {
    ☃.a(128L);
    this.b = ☃.readDouble();
  }
  
  public byte a()
  {
    return 6;
  }
  
  public String toString()
  {
    return "" + this.b + "d";
  }
  
  public eb b()
  {
    return new dp(this.b);
  }
  
  public boolean equals(Object ☃)
  {
    if (super.equals(☃))
    {
      dp ☃ = (dp)☃;
      return this.b == ☃.b;
    }
    return false;
  }
  
  public int hashCode()
  {
    long ☃ = Double.doubleToLongBits(this.b);
    return super.hashCode() ^ (int)(☃ ^ ☃ >>> 32);
  }
  
  public long c()
  {
    return Math.floor(this.b);
  }
  
  public int d()
  {
    return on.c(this.b);
  }
  
  public short e()
  {
    return (short)(on.c(this.b) & 0xFFFF);
  }
  
  public byte f()
  {
    return (byte)(on.c(this.b) & 0xFF);
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
