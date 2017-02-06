import java.io.IOException;

public class hk
  implements ff<fi>
{
  private int a;
  private String b;
  
  public hk() {}
  
  public hk(int ☃, bbl ☃)
  {
    this.a = ☃;
    if (☃ == null) {
      this.b = "";
    } else {
      this.b = ☃.b();
    }
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readByte();
    this.b = ☃.c(16);
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeByte(this.a);
    ☃.a(this.b);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public String b()
  {
    return this.b;
  }
}
