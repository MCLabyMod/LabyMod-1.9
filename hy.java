import java.io.IOException;

public class hy
  implements ff<fi>
{
  private aht a;
  private cj b;
  private eu[] c;
  
  public hy() {}
  
  public hy(aht ☃, cj ☃, eu[] ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = new eu[] { ☃[0], ☃[1], ☃[2], ☃[3] };
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.b = ☃.e();
    this.c = new eu[4];
    for (int ☃ = 0; ☃ < 4; ☃++) {
      this.c[☃] = ☃.f();
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.b);
    for (int ☃ = 0; ☃ < 4; ☃++) {
      ☃.a(this.c[☃]);
    }
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public cj a()
  {
    return this.b;
  }
  
  public eu[] b()
  {
    return this.c;
  }
}
