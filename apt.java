import com.google.common.collect.Lists;
import java.util.List;

public class apt
  extends apv
{
  private int a;
  private du f;
  private boolean g;
  private List<apt.a> h;
  private List<act> i;
  private String j;
  
  public static enum a
  {
    private String N;
    private String O;
    private String[] P = new String[3];
    private adq Q;
    
    private a(String ☃, String ☃)
    {
      this.N = ☃;
      this.O = ☃;
    }
    
    private a(String ☃, String ☃, adq ☃)
    {
      this(☃, ☃);
      this.Q = ☃;
    }
    
    private a(String ☃, String ☃, String ☃, String ☃, String ☃)
    {
      this(☃, ☃);
      this.P[0] = ☃;
      this.P[1] = ☃;
      this.P[2] = ☃;
    }
    
    public String a()
    {
      return this.N;
    }
    
    public String b()
    {
      return this.O;
    }
    
    public String[] c()
    {
      return this.P;
    }
    
    public boolean d()
    {
      return (this.Q != null) || (this.P[0] != null);
    }
    
    public boolean e()
    {
      return this.Q != null;
    }
    
    public adq f()
    {
      return this.Q;
    }
    
    public static a a(String ☃)
    {
      for (a ☃ : ) {
        if (☃.O.equals(☃)) {
          return ☃;
        }
      }
      return null;
    }
  }
  
  public void a(adq ☃)
  {
    this.f = null;
    if ((☃.n()) && (☃.o().b("BlockEntityTag", 10)))
    {
      dn ☃ = ☃.o().o("BlockEntityTag");
      if (☃.e("Patterns")) {
        this.f = ((du)☃.c("Patterns", 10).b());
      }
      if (☃.b("Base", 99)) {
        this.a = ☃.h("Base");
      } else {
        this.a = (☃.i() & 0xF);
      }
    }
    else
    {
      this.a = (☃.i() & 0xF);
    }
    this.h = null;
    this.i = null;
    this.j = "";
    this.g = true;
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    
    a(☃, this.a, this.f);
  }
  
  public static void a(dn ☃, int ☃, du ☃)
  {
    ☃.a("Base", ☃);
    if (☃ != null) {
      ☃.a("Patterns", ☃);
    }
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    
    this.a = ☃.h("Base");
    this.f = ☃.c("Patterns", 10);
    
    this.h = null;
    this.i = null;
    this.j = null;
    this.g = true;
  }
  
  public ff<?> D_()
  {
    dn ☃ = new dn();
    b(☃);
    return new fs(this.c, 6, ☃);
  }
  
  public int b()
  {
    return this.a;
  }
  
  public static int b(adq ☃)
  {
    dn ☃ = ☃.a("BlockEntityTag", false);
    if ((☃ != null) && (☃.e("Base"))) {
      return ☃.h("Base");
    }
    return ☃.i();
  }
  
  public static int c(adq ☃)
  {
    dn ☃ = ☃.a("BlockEntityTag", false);
    if ((☃ != null) && (☃.e("Patterns"))) {
      return ☃.c("Patterns", 10).c();
    }
    return 0;
  }
  
  public List<apt.a> c()
  {
    h();
    return this.h;
  }
  
  public du d()
  {
    return this.f;
  }
  
  public List<act> e()
  {
    h();
    return this.i;
  }
  
  public String g()
  {
    h();
    return this.j;
  }
  
  private void h()
  {
    if ((this.h != null) && (this.i != null) && (this.j != null)) {
      return;
    }
    if (!this.g)
    {
      this.j = "";
      return;
    }
    this.h = Lists.newArrayList();
    this.i = Lists.newArrayList();
    
    this.h.add(apt.a.a);
    this.i.add(act.a(this.a));
    this.j = ("b" + this.a);
    if (this.f != null) {
      for (int ☃ = 0; ☃ < this.f.c(); ☃++)
      {
        dn ☃ = this.f.b(☃);
        apt.a ☃ = apt.a.a(☃.l("Pattern"));
        if (☃ != null)
        {
          this.h.add(☃);
          int ☃ = ☃.h("Color");
          this.i.add(act.a(☃));
          
          this.j = (this.j + ☃.b() + ☃);
        }
      }
    }
  }
  
  public static void a(adq ☃, act ☃)
  {
    dn ☃ = ☃.a("BlockEntityTag", true);
    ☃.a("Base", ☃.b());
  }
  
  public static void e(adq ☃)
  {
    dn ☃ = ☃.a("BlockEntityTag", false);
    if ((☃ == null) || (!☃.b("Patterns", 9))) {
      return;
    }
    du ☃ = ☃.c("Patterns", 10);
    if (☃.c() <= 0) {
      return;
    }
    ☃.a(☃.c() - 1);
    if (☃.c_())
    {
      ☃.o().q("BlockEntityTag");
      if (☃.o().c_()) {
        ☃.d(null);
      }
    }
  }
}
