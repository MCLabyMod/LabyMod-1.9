import java.io.IOException;

public class gm
  implements ff<fi>
{
  private int a;
  private int b;
  
  public gm() {}
  
  public gm(int ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readInt();
    this.b = ☃.readInt();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeInt(this.a);
    ☃.writeInt(this.b);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public int b()
  {
    return this.b;
  }
}
