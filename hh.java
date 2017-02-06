import java.io.IOException;

public class hh
  implements ff<fi>
{
  private hh.a a;
  private int b;
  private double c;
  private double d;
  private double e;
  private double f;
  private long g;
  private int h;
  private int i;
  
  public hh() {}
  
  public hh(arv ☃, hh.a ☃)
  {
    this.a = ☃;
    this.c = ☃.f();
    this.d = ☃.g();
    this.f = ☃.h();
    this.e = ☃.j();
    this.g = ☃.i();
    this.b = ☃.l();
    this.i = ☃.q();
    this.h = ☃.p();
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ((hh.a)☃.a(hh.a.class));
    switch (hh.1.a[this.a.ordinal()])
    {
    case 1: 
      this.e = ☃.readDouble();
      break;
    case 2: 
      this.f = ☃.readDouble();
      this.e = ☃.readDouble();
      this.g = ☃.h();
      break;
    case 3: 
      this.c = ☃.readDouble();
      this.d = ☃.readDouble();
      break;
    case 4: 
      this.i = ☃.g();
      break;
    case 5: 
      this.h = ☃.g();
      break;
    case 6: 
      this.c = ☃.readDouble();
      this.d = ☃.readDouble();
      this.f = ☃.readDouble();
      this.e = ☃.readDouble();
      this.g = ☃.h();
      this.b = ☃.g();
      this.i = ☃.g();
      this.h = ☃.g();
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a);
    switch (hh.1.a[this.a.ordinal()])
    {
    case 1: 
      ☃.writeDouble(this.e);
      break;
    case 2: 
      ☃.writeDouble(this.f);
      ☃.writeDouble(this.e);
      ☃.b(this.g);
      break;
    case 3: 
      ☃.writeDouble(this.c);
      ☃.writeDouble(this.d);
      break;
    case 5: 
      ☃.b(this.h);
      break;
    case 4: 
      ☃.b(this.i);
      break;
    case 6: 
      ☃.writeDouble(this.c);
      ☃.writeDouble(this.d);
      ☃.writeDouble(this.f);
      ☃.writeDouble(this.e);
      ☃.b(this.g);
      ☃.b(this.b);
      ☃.b(this.i);
      ☃.b(this.h);
    }
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public void a(arv ☃)
  {
    switch (hh.1.a[this.a.ordinal()])
    {
    case 1: 
      ☃.a(this.e);
      break;
    case 2: 
      ☃.a(this.f, this.e, this.g);
      break;
    case 3: 
      ☃.c(this.c, this.d);
      break;
    case 6: 
      ☃.c(this.c, this.d);
      if (this.g > 0L) {
        ☃.a(this.f, this.e, this.g);
      } else {
        ☃.a(this.e);
      }
      ☃.a(this.b);
      ☃.c(this.i);
      ☃.b(this.h);
      break;
    case 5: 
      ☃.b(this.h);
      break;
    case 4: 
      ☃.c(this.i);
    }
  }
  
  public static enum a
  {
    private a() {}
  }
}
