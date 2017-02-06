import java.io.IOException;

public class fk
  implements ff<fi>
{
  private int a;
  private double b;
  private double c;
  private double d;
  private int e;
  
  public fk() {}
  
  public fk(rx ☃)
  {
    this.a = ☃.O();
    this.b = ☃.p;
    this.c = ☃.q;
    this.d = ☃.r;
    this.e = ☃.j();
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.b = ☃.readDouble();
    this.c = ☃.readDouble();
    this.d = ☃.readDouble();
    this.e = ☃.readShort();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
    ☃.writeDouble(this.b);
    ☃.writeDouble(this.c);
    ☃.writeDouble(this.d);
    ☃.writeShort(this.e);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public double b()
  {
    return this.b;
  }
  
  public double c()
  {
    return this.c;
  }
  
  public double d()
  {
    return this.d;
  }
  
  public int e()
  {
    return this.e;
  }
}
