import java.io.IOException;

public class gb
  implements ff<fi>
{
  private int a;
  
  public gb() {}
  
  public gb(int ☃)
  {
    this.a = ☃;
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readUnsignedByte();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeByte(this.a);
  }
}
