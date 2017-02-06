import java.io.IOException;

public class ie
  implements ff<fi>
{
  private int a;
  private byte b;
  private byte c;
  private int d;
  private byte e;
  
  public ie() {}
  
  public ie(int ☃, rl ☃)
  {
    this.a = ☃;
    this.b = ((byte)(rk.a(☃.a()) & 0xFF));
    this.c = ((byte)(☃.c() & 0xFF));
    if (☃.b() > 32767) {
      this.d = 32767;
    } else {
      this.d = ☃.b();
    }
    this.e = 0;
    if (☃.d()) {
      this.e = ((byte)(this.e | 0x1));
    }
    if (☃.e()) {
      this.e = ((byte)(this.e | 0x2));
    }
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.b = ☃.readByte();
    this.c = ☃.readByte();
    this.d = ☃.g();
    this.e = ☃.readByte();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
    ☃.writeByte(this.b);
    ☃.writeByte(this.c);
    ☃.b(this.d);
    ☃.writeByte(this.e);
  }
  
  public boolean a()
  {
    return this.d == 32767;
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public int b()
  {
    return this.a;
  }
  
  public byte c()
  {
    return this.b;
  }
  
  public byte d()
  {
    return this.c;
  }
  
  public int e()
  {
    return this.d;
  }
  
  public boolean f()
  {
    return (this.e & 0x2) == 2;
  }
  
  public boolean g()
  {
    return (this.e & 0x1) == 1;
  }
}
