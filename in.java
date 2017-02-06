import java.io.IOException;

public class in
  implements ff<ig>
{
  private int a;
  private int b;
  
  public in() {}
  
  public in(int ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public void a(ig ☃)
  {
    ☃.a(this);
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readByte();
    this.b = ☃.readByte();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeByte(this.a);
    ☃.writeByte(this.b);
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
