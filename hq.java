import java.io.IOException;

public class hq
  implements ff<fi>
{
  private float a;
  private int b;
  private float c;
  
  public hq() {}
  
  public hq(float ☃, int ☃, float ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readFloat();
    this.b = ☃.g();
    this.c = ☃.readFloat();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeFloat(this.a);
    ☃.b(this.b);
    ☃.writeFloat(this.c);
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
  
  public float c()
  {
    return this.c;
  }
}
