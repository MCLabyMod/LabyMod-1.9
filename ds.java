import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class ds
  extends eb
{
  private int[] b;
  
  ds() {}
  
  public ds(int[] ☃)
  {
    this.b = ☃;
  }
  
  void a(DataOutput ☃)
    throws IOException
  {
    ☃.writeInt(this.b.length);
    for (int ☃ = 0; ☃ < this.b.length; ☃++) {
      ☃.writeInt(this.b[☃]);
    }
  }
  
  void a(DataInput ☃, int ☃, dw ☃)
    throws IOException
  {
    ☃.a(192L);
    
    int ☃ = ☃.readInt();
    ☃.a(32 * ☃);
    this.b = new int[☃];
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      this.b[☃] = ☃.readInt();
    }
  }
  
  public byte a()
  {
    return 11;
  }
  
  public String toString()
  {
    String ☃ = "[";
    for (int ☃ : this.b) {
      ☃ = ☃ + ☃ + ",";
    }
    return ☃ + "]";
  }
  
  public eb b()
  {
    int[] ☃ = new int[this.b.length];
    System.arraycopy(this.b, 0, ☃, 0, this.b.length);
    return new ds(☃);
  }
  
  public boolean equals(Object ☃)
  {
    if (super.equals(☃)) {
      return Arrays.equals(this.b, ((ds)☃).b);
    }
    return false;
  }
  
  public int hashCode()
  {
    return super.hashCode() ^ Arrays.hashCode(this.b);
  }
  
  public int[] c()
  {
    return this.b;
  }
}
