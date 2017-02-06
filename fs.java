import java.io.IOException;

public class fs
  implements ff<fi>
{
  private cj a;
  private int b;
  private dn c;
  
  public fs() {}
  
  public fs(cj ☃, int ☃, dn ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.e();
    this.b = ☃.readUnsignedByte();
    this.c = ☃.j();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a);
    ☃.writeByte((byte)this.b);
    ☃.a(this.c);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public cj a()
  {
    return this.a;
  }
  
  public int b()
  {
    return this.b;
  }
  
  public dn c()
  {
    return this.c;
  }
}
