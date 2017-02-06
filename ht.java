import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collection;

public class ht
  implements ff<fi>
{
  private String a = "";
  private String b = "";
  private String c = "";
  private String d = "";
  private String e = bbr.b.a.e;
  private String f = bbr.a.a.e;
  private int g = -1;
  private Collection<String> h = Lists.newArrayList();
  private int i;
  private int j;
  
  public ht() {}
  
  public ht(bbm ☃, int ☃)
  {
    this.a = ☃.b();
    this.i = ☃;
    if ((☃ == 0) || (☃ == 2))
    {
      this.b = ☃.c();
      this.c = ☃.e();
      this.d = ☃.f();
      this.j = ☃.l();
      this.e = ☃.i().e;
      this.f = ☃.k().e;
      this.g = ☃.m().b();
    }
    if (☃ == 0) {
      this.h.addAll(☃.d());
    }
  }
  
  public ht(bbm ☃, Collection<String> ☃, int ☃)
  {
    if ((☃ != 3) && (☃ != 4)) {
      throw new IllegalArgumentException("Method must be join or leave for player constructor");
    }
    if ((☃ == null) || (☃.isEmpty())) {
      throw new IllegalArgumentException("Players cannot be null/empty");
    }
    this.i = ☃;
    this.a = ☃.b();
    this.h.addAll(☃);
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.c(16);
    this.i = ☃.readByte();
    if ((this.i == 0) || (this.i == 2))
    {
      this.b = ☃.c(32);
      this.c = ☃.c(16);
      this.d = ☃.c(16);
      this.j = ☃.readByte();
      this.e = ☃.c(32);
      this.f = ☃.c(32);
      this.g = ☃.readByte();
    }
    if ((this.i == 0) || (this.i == 3) || (this.i == 4))
    {
      int ☃ = ☃.g();
      for (int ☃ = 0; ☃ < ☃; ☃++) {
        this.h.add(☃.c(40));
      }
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a);
    ☃.writeByte(this.i);
    if ((this.i == 0) || (this.i == 2))
    {
      ☃.a(this.b);
      ☃.a(this.c);
      ☃.a(this.d);
      ☃.writeByte(this.j);
      ☃.a(this.e);
      ☃.a(this.f);
      ☃.writeByte(this.g);
    }
    if ((this.i == 0) || (this.i == 3) || (this.i == 4))
    {
      ☃.b(this.h.size());
      for (String ☃ : this.h) {
        ☃.a(☃);
      }
    }
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public String a()
  {
    return this.a;
  }
  
  public String b()
  {
    return this.b;
  }
  
  public String c()
  {
    return this.c;
  }
  
  public String d()
  {
    return this.d;
  }
  
  public Collection<String> e()
  {
    return this.h;
  }
  
  public int f()
  {
    return this.i;
  }
  
  public int g()
  {
    return this.j;
  }
  
  public int h()
  {
    return this.g;
  }
  
  public String i()
  {
    return this.e;
  }
  
  public String j()
  {
    return this.f;
  }
}
