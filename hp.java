import java.io.IOException;

public class hp
  implements ff<fi>
{
  private float a;
  private int b;
  private int c;
  
  public hp() {}
  
  public hp(float ☃, int ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readFloat();
    this.c = ☃.g();
    this.b = ☃.g();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeFloat(this.a);
    ☃.b(this.c);
    ☃.b(this.b);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public float a()
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
