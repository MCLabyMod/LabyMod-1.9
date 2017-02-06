import java.io.IOException;

public class im
  implements ff<ig>
{
  private int a;
  private short b;
  private boolean c;
  
  public im() {}
  
  public im(int ☃, short ☃, boolean ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
  }
  
  public void a(ig ☃)
  {
    ☃.a(this);
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readByte();
    this.b = ☃.readShort();
    this.c = (☃.readByte() != 0);
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeByte(this.a);
    ☃.writeShort(this.b);
    ☃.writeByte(this.c ? 1 : 0);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public short b()
  {
    return this.b;
  }
}
