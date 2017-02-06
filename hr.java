import java.io.IOException;

public class hr
  implements ff<fi>
{
  private String a;
  private String b;
  private bbv.a c;
  private int d;
  
  public hr() {}
  
  public hr(bbl ☃, int ☃)
  {
    this.a = ☃.b();
    this.b = ☃.d();
    this.c = ☃.c().c();
    this.d = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.c(16);
    this.d = ☃.readByte();
    if ((this.d == 0) || (this.d == 2))
    {
      this.b = ☃.c(32);
      this.c = bbv.a.a(☃.c(16));
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a);
    ☃.writeByte(this.d);
    if ((this.d == 0) || (this.d == 2))
    {
      ☃.a(this.b);
      ☃.a(this.c.a());
    }
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public String a()
  {
    return this.a;
  }
  
  public String b()
  {
    return this.b;
  }
  
  public int c()
  {
    return this.d;
  }
  
  public bbv.a d()
  {
    return this.c;
  }
}
