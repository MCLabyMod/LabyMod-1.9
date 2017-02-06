import java.io.IOException;

public class gf
  implements ff<fi>
{
  private int a;
  private int b;
  private adq c;
  
  public gf() {}
  
  public gf(int ☃, int ☃, adq ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = (☃ == null ? null : ☃.k());
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readByte();
    this.b = ☃.readShort();
    this.c = ☃.k();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeByte(this.a);
    ☃.writeShort(this.b);
    ☃.a(this.c);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public int b()
  {
    return this.b;
  }
  
  public adq c()
  {
    return this.c;
  }
}
