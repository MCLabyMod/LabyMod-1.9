import java.io.IOException;
import java.util.UUID;

public class fj
  implements ff<fi>
{
  private int a;
  private UUID b;
  private double c;
  private double d;
  private double e;
  private int f;
  private int g;
  private int h;
  private int i;
  private int j;
  private int k;
  private int l;
  
  public fj() {}
  
  public fj(rr ☃, int ☃)
  {
    this(☃, ☃, 0);
  }
  
  public fj(rr ☃, int ☃, int ☃)
  {
    this.a = ☃.O();
    this.b = ☃.bc();
    this.c = ☃.p;
    this.d = ☃.q;
    this.e = ☃.r;
    this.i = on.d(☃.w * 256.0F / 360.0F);
    this.j = on.d(☃.v * 256.0F / 360.0F);
    this.k = ☃;
    this.l = ☃;
    
    double ☃ = 3.9D;
    this.f = ((int)(on.a(☃.s, -3.9D, 3.9D) * 8000.0D));
    this.g = ((int)(on.a(☃.t, -3.9D, 3.9D) * 8000.0D));
    this.h = ((int)(on.a(☃.u, -3.9D, 3.9D) * 8000.0D));
  }
  
  public fj(rr ☃, int ☃, int ☃, cj ☃)
  {
    this(☃, ☃, ☃);
    this.c = ☃.p();
    this.d = ☃.q();
    this.e = ☃.r();
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.b = ☃.i();
    this.k = ☃.readByte();
    this.c = ☃.readDouble();
    this.d = ☃.readDouble();
    this.e = ☃.readDouble();
    this.i = ☃.readByte();
    this.j = ☃.readByte();
    this.l = ☃.readInt();
    
    this.f = ☃.readShort();
    this.g = ☃.readShort();
    this.h = ☃.readShort();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
    ☃.a(this.b);
    ☃.writeByte(this.k);
    ☃.writeDouble(this.c);
    ☃.writeDouble(this.d);
    ☃.writeDouble(this.e);
    ☃.writeByte(this.i);
    ☃.writeByte(this.j);
    ☃.writeInt(this.l);
    
    ☃.writeShort(this.f);
    ☃.writeShort(this.g);
    ☃.writeShort(this.h);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public UUID b()
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
  
  public double e()
  {
    return this.e;
  }
  
  public int f()
  {
    return this.f;
  }
  
  public int g()
  {
    return this.g;
  }
  
  public int h()
  {
    return this.h;
  }
  
  public int i()
  {
    return this.i;
  }
  
  public int j()
  {
    return this.j;
  }
  
  public int k()
  {
    return this.k;
  }
  
  public int l()
  {
    return this.l;
  }
  
  public void a(int ☃)
  {
    this.f = ☃;
  }
  
  public void b(int ☃)
  {
    this.g = ☃;
  }
  
  public void c(int ☃)
  {
    this.h = ☃;
  }
  
  public void d(int ☃)
  {
    this.l = ☃;
  }
}
