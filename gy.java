import java.io.IOException;

public class gy
  implements ff<fi>
{
  public gy.a a;
  public int b;
  public int c;
  public int d;
  public eu e;
  
  public gy() {}
  
  public gy(rb ☃, gy.a ☃)
  {
    this(☃, ☃, true);
  }
  
  public gy(rb ☃, gy.a ☃, boolean ☃)
  {
    this.a = ☃;
    
    sa ☃ = ☃.c();
    switch (gy.1.a[☃.ordinal()])
    {
    case 1: 
      this.d = ☃.f();
      this.c = (☃ == null ? -1 : ☃.O());
      break;
    case 2: 
      this.b = ☃.h().O();
      this.c = (☃ == null ? -1 : ☃.O());
      if (☃) {
        this.e = ☃.b();
      } else {
        this.e = new fa("");
      }
      break;
    }
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ((gy.a)☃.a(gy.a.class));
    if (this.a == gy.a.b)
    {
      this.d = ☃.g();
      this.c = ☃.readInt();
    }
    else if (this.a == gy.a.c)
    {
      this.b = ☃.g();
      this.c = ☃.readInt();
      this.e = ☃.f();
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a);
    if (this.a == gy.a.b)
    {
      ☃.b(this.d);
      ☃.writeInt(this.c);
    }
    else if (this.a == gy.a.c)
    {
      ☃.b(this.b);
      ☃.writeInt(this.c);
      ☃.a(this.e);
    }
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public static enum a
  {
    private a() {}
  }
}
