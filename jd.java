import java.io.IOException;

public class jd
  implements ff<ig>
{
  private cj a;
  private String[] b;
  
  public jd() {}
  
  public jd(cj ☃, eu[] ☃)
  {
    this.a = ☃;
    this.b = new String[] { ☃[0].c(), ☃[1].c(), ☃[2].c(), ☃[3].c() };
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.e();
    this.b = new String[4];
    for (int ☃ = 0; ☃ < 4; ☃++) {
      this.b[☃] = ☃.c(384);
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a);
    for (int ☃ = 0; ☃ < 4; ☃++) {
      ☃.a(this.b[☃]);
    }
  }
  
  public void a(ig ☃)
  {
    ☃.a(this);
  }
  
  public cj a()
  {
    return this.a;
  }
  
  public String[] b()
  {
    return this.b;
  }
}
