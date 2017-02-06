import java.io.IOException;

public class hb
  implements ff<fi>
{
  private int a;
  private cj b;
  
  public hb() {}
  
  public hb(zj ☃, cj ☃)
  {
    this.a = ☃.O();
    this.b = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.b = ☃.e();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
    ☃.a(this.b);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public zj a(aht ☃)
  {
    return (zj)☃.a(this.a);
  }
  
  public cj a()
  {
    return this.b;
  }
}
