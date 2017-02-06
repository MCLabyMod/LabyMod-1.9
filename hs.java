import java.io.IOException;
import java.util.List;

public class hs
  implements ff<fi>
{
  private int a;
  private int[] b;
  
  public hs() {}
  
  public hs(rr ☃)
  {
    this.a = ☃.O();
    List<rr> ☃ = ☃.bu();
    this.b = new int[☃.size()];
    for (int ☃ = 0; ☃ < ☃.size(); ☃++) {
      this.b[☃] = ((rr)☃.get(☃)).O();
    }
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.b = ☃.b();
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
  
  public int[] a()
  {
    return this.b;
  }
  
  public int b()
  {
    return this.a;
  }
}
