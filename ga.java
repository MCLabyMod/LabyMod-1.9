import java.io.IOException;

public class ga
  implements ff<fi>
{
  private int a;
  private short b;
  private boolean c;
  
  public ga() {}
  
  public ga(int ☃, short ☃, boolean ☃)
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
    this.c = ☃.readBoolean();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeByte(this.a);
    ☃.writeShort(this.b);
    ☃.writeBoolean(this.c);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public short b()
  {
    return this.b;
  }
  
  public boolean c()
  {
    return this.c;
  }
}
