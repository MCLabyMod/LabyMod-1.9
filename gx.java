import java.io.IOException;

public class gx
  implements ff<fi>
{
  private boolean a;
  private boolean b;
  private boolean c;
  private boolean d;
  private float e;
  private float f;
  
  public gx() {}
  
  public gx(zh ☃)
  {
    a(☃.a);
    b(☃.b);
    c(☃.c);
    d(☃.d);
    a(☃.a());
    b(☃.b());
  }
  
  public void a(em ☃)
    throws IOException
  {
    byte ☃ = ☃.readByte();
    
    a((☃ & 0x1) > 0);
    b((☃ & 0x2) > 0);
    c((☃ & 0x4) > 0);
    d((☃ & 0x8) > 0);
    a(☃.readFloat());
    b(☃.readFloat());
  }
  
  public void b(em ☃)
    throws IOException
  {
    byte ☃ = 0;
    if (a()) {
      ☃ = (byte)(☃ | 0x1);
    }
    if (b()) {
      ☃ = (byte)(☃ | 0x2);
    }
    if (c()) {
      ☃ = (byte)(☃ | 0x4);
    }
    if (d()) {
      ☃ = (byte)(☃ | 0x8);
    }
    ☃.writeByte(☃);
    ☃.writeFloat(this.e);
    ☃.writeFloat(this.f);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public boolean a()
  {
    return this.a;
  }
  
  public void a(boolean ☃)
  {
    this.a = ☃;
  }
  
  public boolean b()
  {
    return this.b;
  }
  
  public void b(boolean ☃)
  {
    this.b = ☃;
  }
  
  public boolean c()
  {
    return this.c;
  }
  
  public void c(boolean ☃)
  {
    this.c = ☃;
  }
  
  public boolean d()
  {
    return this.d;
  }
  
  public void d(boolean ☃)
  {
    this.d = ☃;
  }
  
  public float e()
  {
    return this.e;
  }
  
  public void a(float ☃)
  {
    this.e = ☃;
  }
  
  public float f()
  {
    return this.f;
  }
  
  public void b(float ☃)
  {
    this.f = ☃;
  }
}
