import java.io.IOException;

public class ho
  implements ff<fi>
{
  private int a;
  private rw b;
  private adq c;
  
  public ho() {}
  
  public ho(int ☃, rw ☃, adq ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = (☃ == null ? null : ☃.k());
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.b = ((rw)☃.a(rw.class));
    this.c = ☃.k();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
    ☃.a(this.b);
    ☃.a(this.c);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public adq a()
  {
    return this.c;
  }
  
  public int b()
  {
    return this.a;
  }
  
  public rw c()
  {
    return this.b;
  }
}
