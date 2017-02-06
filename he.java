import java.io.IOException;

public class he
  implements ff<fi>
{
  private String a;
  private String b;
  
  public he() {}
  
  public he(String ☃, String ☃)
  {
    this.a = ☃;
    this.b = ☃;
    if (☃.length() > 40) {
      throw new IllegalArgumentException("Hash is too long (max 40, was " + ☃.length() + ")");
    }
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.c(32767);
    this.b = ☃.c(40);
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a);
    ☃.a(this.b);
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
}
