import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class dl
  extends eb
{
  private byte[] b;
  
  dl() {}
  
  public dl(byte[] ☃)
  {
    this.b = ☃;
  }
  
  void a(DataOutput ☃)
    throws IOException
  {
    ☃.writeInt(this.b.length);
    ☃.write(this.b);
  }
  
  void a(DataInput ☃, int ☃, dw ☃)
    throws IOException
  {
    ☃.a(192L);
    int ☃ = ☃.readInt();
    ☃.a(8 * ☃);
    this.b = new byte[☃];
    ☃.readFully(this.b);
  }
  
  public byte a()
  {
    return 7;
  }
  
  public String toString()
  {
    return "[" + this.b.length + " bytes]";
  }
  
  public eb b()
  {
    byte[] ☃ = new byte[this.b.length];
    System.arraycopy(this.b, 0, ☃, 0, this.b.length);
    return new dl(☃);
  }
  
  public boolean equals(Object ☃)
  {
    if (super.equals(☃)) {
      return Arrays.equals(this.b, ((dl)☃).b);
    }
    return false;
  }
  
  public int hashCode()
  {
    return super.hashCode() ^ Arrays.hashCode(this.b);
  }
  
  public byte[] c()
  {
    return this.b;
  }
}
