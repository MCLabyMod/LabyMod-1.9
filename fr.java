import java.io.IOException;

public class fr
  implements ff<fi>
{
  private int a;
  private cj b;
  private int c;
  
  public fr() {}
  
  public fr(int ☃, cj ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.b = ☃.e();
    this.c = ☃.readUnsignedByte();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
    ☃.a(this.b);
    ☃.writeByte(this.c);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public cj b()
  {
    return this.b;
  }
  
  public int c()
  {
    return this.c;
  }
}
