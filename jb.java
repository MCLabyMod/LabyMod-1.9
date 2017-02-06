import java.io.IOException;

public class jb
  implements ff<ig>
{
  private int a;
  
  public jb() {}
  
  public jb(int ☃)
  {
    this.a = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readShort();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeShort(this.a);
  }
  
  public void a(ig ☃)
  {
    ☃.a(this);
  }
  
  public int a()
  {
    return this.a;
  }
}
