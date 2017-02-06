import java.io.IOException;

public class gu$c
  extends gu
{
  public gu$c()
  {
    this.h = true;
  }
  
  public gu$c(int ☃, byte ☃, byte ☃, boolean ☃)
  {
    super(☃);
    this.e = ☃;
    this.f = ☃;
    this.h = true;
    this.g = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    super.a(☃);
    this.e = ☃.readByte();
    this.f = ☃.readByte();
    this.g = ☃.readBoolean();
  }
  
  public void b(em ☃)
    throws IOException
  {
    super.b(☃);
    ☃.writeByte(this.e);
    ☃.writeByte(this.f);
    ☃.writeBoolean(this.g);
  }
}
