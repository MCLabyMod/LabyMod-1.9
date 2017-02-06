import java.io.IOException;

public class jc
  implements ff<ig>
{
  private int a;
  private adq b;
  
  public jc() {}
  
  public jc(int ☃, adq ☃)
  {
    this.a = ☃;
    this.b = (☃ != null ? ☃.k() : null);
  }
  
  public void a(ig ☃)
  {
    ☃.a(this);
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readShort();
    this.b = ☃.k();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeShort(this.a);
    ☃.a(this.b);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public adq b()
  {
    return this.b;
  }
}
