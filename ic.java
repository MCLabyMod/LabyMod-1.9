import java.io.IOException;

public class ic
  implements ff<fi>
{
  private int a;
  private double b;
  private double c;
  private double d;
  private byte e;
  private byte f;
  private boolean g;
  
  public ic() {}
  
  public ic(rr ☃)
  {
    this.a = ☃.O();
    this.b = ☃.p;
    this.c = ☃.q;
    this.d = ☃.r;
    this.e = ((byte)(int)(☃.v * 256.0F / 360.0F));
    this.f = ((byte)(int)(☃.w * 256.0F / 360.0F));
    this.g = ☃.z;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.b = ☃.readDouble();
    this.c = ☃.readDouble();
    this.d = ☃.readDouble();
    this.e = ☃.readByte();
    this.f = ☃.readByte();
    this.g = ☃.readBoolean();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
    ☃.writeDouble(this.b);
    ☃.writeDouble(this.c);
    ☃.writeDouble(this.d);
    ☃.writeByte(this.e);
    ☃.writeByte(this.f);
    ☃.writeBoolean(this.g);
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
  
  public byte e()
  {
    return this.e;
  }
  
  public byte f()
  {
    return this.f;
  }
  
  public boolean g()
  {
    return this.g;
  }
}
