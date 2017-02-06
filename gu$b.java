import java.io.IOException;

public class gu$b
  extends gu
{
  public gu$b()
  {
    this.h = true;
  }
  
  public gu$b(int ☃, long ☃, long ☃, long ☃, byte ☃, byte ☃, boolean ☃)
  {
    super(☃);
    
    this.b = ((int)☃);
    this.c = ((int)☃);
    this.d = ((int)☃);
    this.e = ☃;
    this.f = ☃;
    this.g = ☃;
    this.h = true;
  }
  
  public void a(em ☃)
    throws IOException
  {
    super.a(☃);
    this.b = ☃.readShort();
    this.c = ☃.readShort();
    this.d = ☃.readShort();
    this.e = ☃.readByte();
    this.f = ☃.readByte();
    this.g = ☃.readBoolean();
  }
  
  public void b(em ☃)
    throws IOException
  {
    super.b(☃);
    ☃.writeShort(this.b);
    ☃.writeShort(this.c);
    ☃.writeShort(this.d);
    ☃.writeByte(this.e);
    ☃.writeByte(this.f);
    ☃.writeBoolean(this.g);
  }
}
