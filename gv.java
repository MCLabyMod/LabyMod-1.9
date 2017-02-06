import java.io.IOException;

public class gv
  implements ff<fi>
{
  private double a;
  private double b;
  private double c;
  private float d;
  private float e;
  
  public gv() {}
  
  public gv(rr ☃)
  {
    this.a = ☃.p;
    this.b = ☃.q;
    this.c = ☃.r;
    this.d = ☃.v;
    this.e = ☃.w;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readDouble();
    this.b = ☃.readDouble();
    this.c = ☃.readDouble();
    this.d = ☃.readFloat();
    this.e = ☃.readFloat();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeDouble(this.a);
    ☃.writeDouble(this.b);
    ☃.writeDouble(this.c);
    ☃.writeFloat(this.d);
    ☃.writeFloat(this.e);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public double a()
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
  
  public float d()
  {
    return this.d;
  }
  
  public float e()
  {
    return this.e;
  }
}
