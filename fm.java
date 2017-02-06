import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class fm
  implements ff<fi>
{
  private int a;
  private UUID b;
  private int c;
  private double d;
  private double e;
  private double f;
  private int g;
  private int h;
  private int i;
  private byte j;
  private byte k;
  private byte l;
  private kh m;
  private List<kh.a<?>> n;
  
  public fm() {}
  
  public fm(sa ☃)
  {
    this.a = ☃.O();
    this.b = ☃.bc();
    
    this.c = ((byte)rt.a(☃));
    this.d = ☃.p;
    this.e = ☃.q;
    this.f = ☃.r;
    this.j = ((byte)(int)(☃.v * 256.0F / 360.0F));
    this.k = ((byte)(int)(☃.w * 256.0F / 360.0F));
    this.l = ((byte)(int)(☃.aO * 256.0F / 360.0F));
    
    double ☃ = 3.9D;
    double ☃ = ☃.s;
    double ☃ = ☃.t;
    double ☃ = ☃.u;
    if (☃ < -☃) {
      ☃ = -☃;
    }
    if (☃ < -☃) {
      ☃ = -☃;
    }
    if (☃ < -☃) {
      ☃ = -☃;
    }
    if (☃ > ☃) {
      ☃ = ☃;
    }
    if (☃ > ☃) {
      ☃ = ☃;
    }
    if (☃ > ☃) {
      ☃ = ☃;
    }
    this.g = ((int)(☃ * 8000.0D));
    this.h = ((int)(☃ * 8000.0D));
    this.i = ((int)(☃ * 8000.0D));
    
    this.m = ☃.R();
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.b = ☃.i();
    this.c = (☃.readByte() & 0xFF);
    this.d = ☃.readDouble();
    this.e = ☃.readDouble();
    this.f = ☃.readDouble();
    this.j = ☃.readByte();
    this.k = ☃.readByte();
    this.l = ☃.readByte();
    this.g = ☃.readShort();
    this.h = ☃.readShort();
    this.i = ☃.readShort();
    this.n = kh.b(☃);
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
    ☃.a(this.b);
    ☃.writeByte(this.c & 0xFF);
    ☃.writeDouble(this.d);
    ☃.writeDouble(this.e);
    ☃.writeDouble(this.f);
    ☃.writeByte(this.j);
    ☃.writeByte(this.k);
    ☃.writeByte(this.l);
    ☃.writeShort(this.g);
    ☃.writeShort(this.h);
    ☃.writeShort(this.i);
    this.m.a(☃);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public List<kh.a<?>> a()
  {
    if (this.n == null) {
      this.n = this.m.c();
    }
    return this.n;
  }
  
  public int b()
  {
    return this.a;
  }
  
  public UUID c()
  {
    return this.b;
  }
  
  public int d()
  {
    return this.c;
  }
  
  public double e()
  {
    return this.d;
  }
  
  public double f()
  {
    return this.e;
  }
  
  public double g()
  {
    return this.f;
  }
  
  public int h()
  {
    return this.g;
  }
  
  public int i()
  {
    return this.h;
  }
  
  public int j()
  {
    return this.i;
  }
  
  public byte k()
  {
    return this.j;
  }
  
  public byte l()
  {
    return this.k;
  }
  
  public byte m()
  {
    return this.l;
  }
}
