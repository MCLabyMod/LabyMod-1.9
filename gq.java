import java.io.IOException;

public class gq
  implements ff<fi>
{
  private int a;
  private cj b;
  private int c;
  private boolean d;
  
  public gq() {}
  
  public gq(int ☃, cj ☃, int ☃, boolean ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readInt();
    this.b = ☃.e();
    this.c = ☃.readInt();
    this.d = ☃.readBoolean();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeInt(this.a);
    ☃.a(this.b);
    ☃.writeInt(this.c);
    ☃.writeBoolean(this.d);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public boolean a()
  {
    return this.d;
  }
  
  public int b()
  {
    return this.a;
  }
  
  public int c()
  {
    return this.c;
  }
  
  public cj d()
  {
    return this.b;
  }
}
