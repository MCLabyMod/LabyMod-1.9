import java.io.IOException;
import java.util.UUID;

public class fv
  implements ff<fi>
{
  private UUID a;
  private fv.a b;
  private eu c;
  private float d;
  private qe.a e;
  private qe.b f;
  private boolean g;
  private boolean h;
  private boolean i;
  
  public fv() {}
  
  public fv(fv.a ☃, qe ☃)
  {
    this.b = ☃;
    this.a = ☃.d();
    this.c = ☃.e();
    this.d = ☃.f();
    this.e = ☃.g();
    this.f = ☃.h();
    this.g = ☃.i();
    this.h = ☃.j();
    this.i = ☃.k();
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.i();
    this.b = ((fv.a)☃.a(fv.a.class));
    switch (fv.1.a[this.b.ordinal()])
    {
    case 1: 
      this.c = ☃.f();
      this.d = ☃.readFloat();
      this.e = ((qe.a)☃.a(qe.a.class));
      this.f = ((qe.b)☃.a(qe.b.class));
      a(☃.readUnsignedByte());
      break;
    case 2: 
      break;
    case 3: 
      this.d = ☃.readFloat();
      break;
    case 4: 
      this.c = ☃.f();
      break;
    case 5: 
      this.e = ((qe.a)☃.a(qe.a.class));
      this.f = ((qe.b)☃.a(qe.b.class));
      break;
    case 6: 
      a(☃.readUnsignedByte());
    }
  }
  
  private void a(int ☃)
  {
    this.g = ((☃ & 0x1) > 0);
    this.h = ((☃ & 0x2) > 0);
    this.i = ((☃ & 0x2) > 0);
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a);
    ☃.a(this.b);
    switch (fv.1.a[this.b.ordinal()])
    {
    case 1: 
      ☃.a(this.c);
      ☃.writeFloat(this.d);
      ☃.a(this.e);
      ☃.a(this.f);
      ☃.writeByte(j());
      break;
    case 2: 
      break;
    case 3: 
      ☃.writeFloat(this.d);
      break;
    case 4: 
      ☃.a(this.c);
      break;
    case 5: 
      ☃.a(this.e);
      ☃.a(this.f);
      break;
    case 6: 
      ☃.writeByte(j());
    }
  }
  
  private int j()
  {
    int ☃ = 0;
    if (this.g) {
      ☃ |= 0x1;
    }
    if (this.h) {
      ☃ |= 0x2;
    }
    if (this.i) {
      ☃ |= 0x2;
    }
    return ☃;
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public UUID a()
  {
    return this.a;
  }
  
  public fv.a b()
  {
    return this.b;
  }
  
  public eu c()
  {
    return this.c;
  }
  
  public float d()
  {
    return this.d;
  }
  
  public qe.a e()
  {
    return this.e;
  }
  
  public qe.b f()
  {
    return this.f;
  }
  
  public boolean g()
  {
    return this.g;
  }
  
  public boolean h()
  {
    return this.h;
  }
  
  public boolean i()
  {
    return this.i;
  }
  
  public static enum a
  {
    private a() {}
  }
}
