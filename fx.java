import java.io.IOException;

public class fx
  implements ff<fi>
{
  private String[] a;
  
  public fx() {}
  
  public fx(String[] ☃)
  {
    this.a = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = new String[☃.g()];
    for (int ☃ = 0; ☃ < this.a.length; ☃++) {
      this.a[☃] = ☃.c(32767);
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a.length);
    for (String ☃ : this.a) {
      ☃.a(☃);
    }
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public String[] a()
  {
    return this.a;
  }
}
