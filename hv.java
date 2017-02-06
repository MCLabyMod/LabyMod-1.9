import java.io.IOException;

public class hv
  implements ff<fi>
{
  private cj a;
  
  public hv() {}
  
  public hv(cj ☃)
  {
    this.a = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.e();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public cj a()
  {
    return this.a;
  }
}
