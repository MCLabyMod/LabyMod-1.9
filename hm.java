import java.io.IOException;

public class hm
  implements ff<fi>
{
  private int a;
  private int b;
  
  public hm() {}
  
  public hm(rr ☃, rr ☃)
  {
    this.a = ☃.O();
    this.b = (☃ != null ? ☃.O() : -1);
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readInt();
    this.b = ☃.readInt();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeInt(this.a);
    ☃.writeInt(this.b);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public int b()
  {
    return this.b;
  }
}
