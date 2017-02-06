import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class aqp
  extends apv
{
  private String a;
  private String f;
  private String g;
  private cj h;
  private cj i;
  private amr j;
  private aoe k;
  private aqp.a l;
  private boolean m;
  
  public aqp()
  {
    this.a = "";
    this.f = "";
    this.g = "";
    this.h = new cj(1, 1, 1);
    this.i = cj.a;
    this.j = amr.a;
    this.k = aoe.a;
    this.l = aqp.a.d;
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("name", this.a);
    ☃.a("author", this.f);
    ☃.a("metadata", this.g);
    ☃.a("posX", this.h.p());
    ☃.a("posY", this.h.q());
    ☃.a("posZ", this.h.r());
    ☃.a("sizeX", this.i.p());
    ☃.a("sizeY", this.i.q());
    ☃.a("sizeZ", this.i.r());
    ☃.a("rotation", this.k.toString());
    ☃.a("mirror", this.j.toString());
    ☃.a("mode", this.l.toString());
    ☃.a("ignoreEntities", this.m);
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    this.a = ☃.l("name");
    this.f = ☃.l("author");
    this.g = ☃.l("metadata");
    this.h = new cj(☃.h("posX"), ☃.h("posY"), ☃.h("posZ"));
    this.i = new cj(☃.h("sizeX"), ☃.h("sizeY"), ☃.h("sizeZ"));
    try
    {
      this.k = aoe.valueOf(☃.l("rotation"));
    }
    catch (IllegalArgumentException ☃)
    {
      this.k = aoe.a;
    }
    try
    {
      this.j = amr.valueOf(☃.l("mirror"));
    }
    catch (IllegalArgumentException ☃)
    {
      this.j = amr.a;
    }
    try
    {
      this.l = aqp.a.valueOf(☃.l("mode"));
    }
    catch (IllegalArgumentException ☃)
    {
      this.l = aqp.a.d;
    }
    this.m = ☃.p("ignoreEntities");
  }
  
  public ff<?> D_()
  {
    dn ☃ = new dn();
    b(☃);
    return new fs(this.c, 7, ☃);
  }
  
  public void a(String ☃)
  {
    this.a = ☃;
  }
  
  public void b(cj ☃)
  {
    this.h = ☃;
  }
  
  public void c(cj ☃)
  {
    this.i = ☃;
  }
  
  public void a(amr ☃)
  {
    this.j = ☃;
  }
  
  public void a(aoe ☃)
  {
    this.k = ☃;
  }
  
  public void b(String ☃)
  {
    this.g = ☃;
  }
  
  public void a(aqp.a ☃)
  {
    this.l = ☃;
    arc ☃ = this.b.o(v());
    if (☃.t() == aju.df) {
      this.b.a(v(), ☃.a(apb.a, ☃), 2);
    }
  }
  
  public void a(boolean ☃)
  {
    this.m = ☃;
  }
  
  public boolean l()
  {
    if (this.l != aqp.a.a) {
      return false;
    }
    cj ☃ = v();
    int ☃ = 128;
    cj ☃ = new cj(☃.p() - 128, 0, ☃.r() - 128);
    cj ☃ = new cj(☃.p() + 128, 255, ☃.r() + 128);
    
    List<aqp> ☃ = a(☃, ☃);
    List<aqp> ☃ = a(☃);
    if (☃.size() < 1) {
      return false;
    }
    avp ☃ = a(☃, ☃);
    if ((☃.d - ☃.a > 1) && (☃.e - ☃.b > 1) && (☃.f - ☃.c > 1))
    {
      this.h = new cj(☃.a - ☃.p() + 1, ☃.b - ☃.q() + 1, ☃.c - ☃.r() + 1);
      this.i = new cj(☃.d - ☃.a - 1, ☃.e - ☃.b - 1, ☃.f - ☃.c - 1);
      v_();
      arc ☃ = this.b.o(☃);
      this.b.a(☃, ☃, ☃, 3);
      return true;
    }
    return false;
  }
  
  private List<aqp> a(List<aqp> ☃)
  {
    Iterable<aqp> ☃ = Iterables.filter(☃, new Predicate()
    {
      public boolean a(aqp ☃)
      {
        return (aqp.a(☃) == aqp.a.c) && (aqp.b(aqp.this).equals(aqp.b(☃)));
      }
    });
    return Lists.newArrayList(☃);
  }
  
  private List<aqp> a(cj ☃, cj ☃)
  {
    List<aqp> ☃ = Lists.newArrayList();
    for (cj.a ☃ : cj.b(☃, ☃))
    {
      arc ☃ = this.b.o(☃);
      if (☃.t() == aju.df)
      {
        apv ☃ = this.b.r(☃);
        if ((☃ != null) && ((☃ instanceof aqp))) {
          ☃.add((aqp)☃);
        }
      }
    }
    return ☃;
  }
  
  private avp a(cj ☃, List<aqp> ☃)
  {
    avp ☃;
    avp ☃;
    if (☃.size() > 1)
    {
      cj ☃ = ((aqp)☃.get(0)).v();
      ☃ = new avp(☃, ☃);
    }
    else
    {
      ☃ = new avp(☃, ☃);
    }
    for (aqp ☃ : ☃)
    {
      cj ☃ = ☃.v();
      if (☃.p() < ☃.a) {
        ☃.a = ☃.p();
      } else if (☃.p() > ☃.d) {
        ☃.d = ☃.p();
      }
      if (☃.q() < ☃.b) {
        ☃.b = ☃.q();
      } else if (☃.q() > ☃.e) {
        ☃.e = ☃.q();
      }
      if (☃.r() < ☃.c) {
        ☃.c = ☃.r();
      } else if (☃.r() > ☃.f) {
        ☃.f = ☃.r();
      }
    }
    return ☃;
  }
  
  public boolean m()
  {
    if ((this.l != aqp.a.a) || (this.b.E)) {
      return false;
    }
    cj ☃ = v().a(this.h);
    
    lp ☃ = (lp)this.b;
    MinecraftServer ☃ = this.b.u();
    awm ☃ = ☃.y();
    awo ☃ = ☃.a(☃, new kk(this.a));
    
    ☃.a(this.b, ☃, this.i, !this.m, aju.cv);
    ☃.a(this.f);
    ☃.c(☃, new kk(this.a));
    return true;
  }
  
  public boolean n()
  {
    if ((this.l != aqp.a.b) || (this.b.E)) {
      return false;
    }
    cj ☃ = v().a(this.h);
    
    lp ☃ = (lp)this.b;
    MinecraftServer ☃ = this.b.u();
    awm ☃ = ☃.y();
    awo ☃ = ☃.a(☃, new kk(this.a));
    if (!os.b(☃.b())) {
      this.f = ☃.b();
    }
    if (!this.i.equals(☃.a()))
    {
      this.i = ☃.a();
      return false;
    }
    cj ☃ = ☃.a(this.k);
    for (rr ☃ : this.b.b(null, new bbh(☃, ☃.a(☃).a(-1, -1, -1)))) {
      this.b.f(☃);
    }
    awn ☃ = new awn().a(this.j).a(this.k).a(this.m).a(null).a(null).b(false);
    
    ☃.a(this.b, ☃, ☃);
    return true;
  }
  
  public static enum a
    implements or
  {
    private static final a[] e;
    private final String f;
    private final int g;
    
    static
    {
      e = new a[values().length];
      for (a ☃ : values()) {
        e[☃.a()] = ☃;
      }
    }
    
    private a(String ☃, int ☃)
    {
      this.f = ☃;
      this.g = ☃;
    }
    
    public String m()
    {
      return this.f;
    }
    
    public int a()
    {
      return this.g;
    }
    
    public static a a(int ☃)
    {
      if ((☃ < 0) || (☃ >= e.length)) {
        ☃ = 0;
      }
      return e[☃];
    }
  }
}
