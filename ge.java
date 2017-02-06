import java.io.IOException;

public class ge
  implements ff<fi>
{
  private int a;
  private int b;
  private int c;
  
  public ge() {}
  
  public ge(int ☃, int ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readUnsignedByte();
    this.b = ☃.readShort();
    this.c = ☃.readShort();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeByte(this.a);
    ☃.writeShort(this.b);
    ☃.writeShort(this.c);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public int b()
  {
    return this.b;
  }
  
  public int c()
  {
    return this.c;
  }
}
