import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ea
  extends eb
{
  private String b;
  
  public ea()
  {
    this.b = "";
  }
  
  public ea(String ☃)
  {
    this.b = ☃;
    if (☃ == null) {
      throw new IllegalArgumentException("Empty string not allowed");
    }
  }
  
  void a(DataOutput ☃)
    throws IOException
  {
    ☃.writeUTF(this.b);
  }
  
  void a(DataInput ☃, int ☃, dw ☃)
    throws IOException
  {
    ☃.a(288L);
    
    this.b = ☃.readUTF();
    ☃.a(16 * this.b.length());
  }
  
  public byte a()
  {
    return 8;
  }
  
  public String toString()
  {
    return "\"" + this.b.replace("\"", "\\\"") + "\"";
  }
  
  public eb b()
  {
    return new ea(this.b);
  }
  
  public boolean c_()
  {
    return this.b.isEmpty();
  }
  
  public boolean equals(Object ☃)
  {
    if (super.equals(☃))
    {
      ea ☃ = (ea)☃;
      return ((this.b == null) && (☃.b == null)) || ((this.b != null) && (this.b.equals(☃.b)));
    }
    return false;
  }
  
  public int hashCode()
  {
    return super.hashCode() ^ this.b.hashCode();
  }
  
  public String a_()
  {
    return this.b;
  }
}
