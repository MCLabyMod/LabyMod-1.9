import java.io.IOException;

public class gu$a
  extends gu
{
  public gu$a() {}
  
  public gu$a(int ☃, long ☃, long ☃, long ☃, boolean ☃)
  {
    super(☃);
    
    this.b = ((int)☃);
    this.c = ((int)☃);
    this.d = ((int)☃);
    this.g = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    super.a(☃);
    this.b = ☃.readShort();
    this.c = ☃.readShort();
    this.d = ☃.readShort();
    this.g = ☃.readBoolean();
  }
  
  public void b(em ☃)
    throws IOException
  {
    super.b(☃);
    ☃.writeShort(this.b);
    ☃.writeShort(this.c);
    ☃.writeShort(this.d);
    ☃.writeBoolean(this.g);
  }
}
