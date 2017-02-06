import java.io.IOException;
import java.util.List;

public class hl
  implements ff<fi>
{
  private int a;
  private List<kh.a<?>> b;
  
  public hl() {}
  
  public hl(int ☃, kh ☃, boolean ☃)
  {
    this.a = ☃;
    if (☃) {
      this.b = ☃.c();
    } else {
      this.b = ☃.b();
    }
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.b = kh.b(☃);
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
    kh.a(this.b, ☃);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public List<kh.a<?>> a()
  {
    return this.b;
  }
  
  public int b()
  {
    return this.a;
  }
}
