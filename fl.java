import java.io.IOException;

public class fl
  implements ff<fi>
{
  private int a;
  private double b;
  private double c;
  private double d;
  private int e;
  
  public fl() {}
  
  public fl(rr ☃)
  {
    this.a = ☃.O();
    this.b = ☃.p;
    this.c = ☃.q;
    this.d = ☃.r;
    if ((☃ instanceof ya)) {
      this.e = 1;
    }
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.e = ☃.readByte();
    this.b = ☃.readDouble();
    this.c = ☃.readDouble();
    this.d = ☃.readDouble();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
    ☃.writeByte(this.e);
    ☃.writeDouble(this.b);
    ☃.writeDouble(this.c);
    ☃.writeDouble(this.d);
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
