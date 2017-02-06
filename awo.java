import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class awo
{
  private final List<awo.a> a;
  private final List<awo.b> b;
  private cj c;
  private String d;
  
  public awo()
  {
    this.a = Lists.newArrayList();
    this.b = Lists.newArrayList();
    this.c = cj.a;
    this.d = "?";
  }
  
  public cj a()
  {
    return this.c;
  }
  
  public void a(String ☃)
  {
    this.d = ☃;
  }
  
  public String b()
  {
    return this.d;
  }
  
  public void a(aht ☃, cj ☃, cj ☃, boolean ☃, ajt ☃)
  {
    if ((☃.p() < 1) || (☃.q() < 1) || (☃.r() < 1)) {
      return;
    }
    cj ☃ = ☃.a(☃).a(-1, -1, -1);
    List<awo.a> ☃ = Lists.newArrayList();
    List<awo.a> ☃ = Lists.newArrayList();
    List<awo.a> ☃ = Lists.newArrayList();
    
    cj ☃ = new cj(Math.min(☃.p(), ☃.p()), Math.min(☃.q(), ☃.q()), Math.min(☃.r(), ☃.r()));
    cj ☃ = new cj(Math.max(☃.p(), ☃.p()), Math.max(☃.q(), ☃.q()), Math.max(☃.r(), ☃.r()));
    this.c = ☃;
    for (cj.a ☃ : cj.b(☃, ☃))
    {
      cj ☃ = ☃.b(☃);
      arc ☃ = ☃.o(☃);
      if ((☃ == null) || (☃ != ☃.t()))
      {
        apv ☃ = ☃.r(☃);
        if (☃ != null)
        {
          dn ☃ = new dn();
          ☃.b(☃);
          ☃.q("x");
          ☃.q("y");
          ☃.q("z");
          ☃.add(new awo.a(☃, ☃, ☃, null));
        }
        else if ((☃.b()) || (☃.h()))
        {
          ☃.add(new awo.a(☃, ☃, null, null));
        }
        else
        {
          ☃.add(new awo.a(☃, ☃, null, null));
        }
      }
    }
    this.a.clear();
    this.a.addAll(☃);
    this.a.addAll(☃);
    this.a.addAll(☃);
    if (☃) {
      a(☃, ☃, ☃.a(1, 1, 1));
    } else {
      this.b.clear();
    }
  }
  
  private void a(aht ☃, cj ☃, cj ☃)
  {
    List<rr> ☃ = ☃.a(rr.class, new bbh(☃, ☃), new Predicate()
    {
      public boolean a(rr ☃)
      {
        return !(☃ instanceof zj);
      }
    });
    this.b.clear();
    for (rr ☃ : ☃)
    {
      bbj ☃ = new bbj(☃.p - ☃.p(), ☃.q - ☃.q(), ☃.r - ☃.r());
      dn ☃ = new dn();
      ☃.d(☃);
      cj ☃;
      cj ☃;
      if ((☃ instanceof xu)) {
        ☃ = ((xu)☃).q().b(☃);
      } else {
        ☃ = new cj(☃);
      }
      this.b.add(new awo.b(☃, ☃, ☃, null));
    }
  }
  
  public Map<cj, String> a(cj ☃, awn ☃)
  {
    Map<cj, String> ☃ = Maps.newHashMap();
    avp ☃ = ☃.g();
    for (awo.a ☃ : this.a)
    {
      cj ☃ = a(☃, ☃.a).a(☃);
      if ((☃ == null) || (☃.b(☃)))
      {
        arc ☃ = ☃.b;
        if ((☃.t() == aju.df) && (☃.c != null))
        {
          aqp.a ☃ = aqp.a.valueOf(☃.c.l("mode"));
          if (☃ == aqp.a.d) {
            ☃.put(☃, ☃.c.l("metadata"));
          }
        }
      }
    }
    return ☃;
  }
  
  public cj a(awn ☃, cj ☃, awn ☃, cj ☃)
  {
    cj ☃ = a(☃, ☃);
    cj ☃ = a(☃, ☃);
    return ☃.b(☃);
  }
  
  public static cj a(awn ☃, cj ☃)
  {
    return a(☃, ☃.b(), ☃.c());
  }
  
  public void a(aht ☃, cj ☃, awn ☃)
  {
    ☃.i();
    b(☃, ☃, ☃);
  }
  
  public void b(aht ☃, cj ☃, awn ☃)
  {
    if ((this.a.isEmpty()) || (this.c.p() < 1) || (this.c.q() < 1) || (this.c.r() < 1)) {
      return;
    }
    ajt ☃ = ☃.f();
    avp ☃ = ☃.g();
    for (awo.a ☃ : this.a)
    {
      ajt ☃ = ☃.b.t();
      if (((☃ == null) || (☃ != ☃)) && (
      
        (!☃.h()) || (☃ != aju.df)))
      {
        cj ☃ = a(☃, ☃.a).a(☃);
        if ((☃ == null) || (☃.b(☃)))
        {
          arc ☃ = ☃.b.a(☃.b());
          arc ☃ = ☃.a(☃.c());
          if (☃.c != null)
          {
            apv ☃ = ☃.r(☃);
            if (☃ != null)
            {
              if ((☃ instanceof qg)) {
                ((qg)☃).l();
              }
              ☃.a(☃, aju.cv.u(), 4);
            }
          }
          if ((☃.a(☃, ☃, 2)) && 
            (☃.c != null))
          {
            apv ☃ = ☃.r(☃);
            if (☃ != null)
            {
              ☃.c.a("x", ☃.p());
              ☃.c.a("y", ☃.q());
              ☃.c.a("z", ☃.r());
              ☃.a(☃.c);
            }
          }
        }
      }
    }
    for (awo.a ☃ : this.a) {
      if ((☃ == null) || (☃ != ☃.b.t()))
      {
        cj ☃ = a(☃, ☃.a).a(☃);
        if ((☃ == null) || (☃.b(☃)))
        {
          ☃.c(☃, ☃.b.t());
          if (☃.c != null)
          {
            apv ☃ = ☃.r(☃);
            if (☃ != null) {
              ☃.v_();
            }
          }
        }
      }
    }
    if (!☃.e()) {
      a(☃, ☃, ☃.b(), ☃.c(), ☃);
    }
  }
  
  private void a(aht ☃, cj ☃, amr ☃, aoe ☃, avp ☃)
  {
    for (awo.b ☃ : this.b)
    {
      cj ☃ = a(☃.b, ☃, ☃).a(☃);
      if ((☃ == null) || (☃.b(☃)))
      {
        dn ☃ = ☃.c;
        bbj ☃ = a(☃.a, ☃, ☃);
        bbj ☃ = ☃.b(☃.p(), ☃.q(), ☃.r());
        
        du ☃ = new du();
        ☃.a(new dp(☃.b));
        ☃.a(new dp(☃.c));
        ☃.a(new dp(☃.d));
        ☃.a("Pos", ☃);
        
        ☃.a("UUID", UUID.randomUUID());
        rr ☃;
        try
        {
          ☃ = rt.a(☃, ☃);
        }
        catch (Exception ☃)
        {
          ☃ = null;
        }
        if (☃ != null)
        {
          if ((☃ instanceof xu))
          {
            ☃.a(☃);
            ☃.a(☃);
            ☃.b(☃.p(), ☃.q(), ☃.r());
            ☃.b(☃.b, ☃.c, ☃.d, ☃.v, ☃.w);
          }
          else
          {
            float ☃ = ☃.a(☃);
            ☃ += ☃.v - ☃.a(☃);
            ☃.b(☃.b, ☃.c, ☃.d, ☃, ☃.w);
          }
          ☃.a(☃);
        }
      }
    }
  }
  
  public cj a(aoe ☃)
  {
    switch (awo.2.a[☃.ordinal()])
    {
    case 1: 
    case 2: 
      return new cj(this.c.r(), this.c.q(), this.c.p());
    }
    return this.c;
  }
  
  private static cj a(cj ☃, amr ☃, aoe ☃)
  {
    int ☃ = ☃.p();
    int ☃ = ☃.q();
    int ☃ = ☃.r();
    
    boolean ☃ = true;
    switch (awo.2.b[☃.ordinal()])
    {
    case 1: 
      ☃ = -☃;
      break;
    case 2: 
      ☃ = -☃;
      break;
    default: 
      ☃ = false;
    }
    switch (awo.2.a[☃.ordinal()])
    {
    case 3: 
      return new cj(-☃, ☃, -☃);
    case 1: 
      return new cj(☃, ☃, -☃);
    case 2: 
      return new cj(-☃, ☃, ☃);
    }
    return ☃ ? new cj(☃, ☃, ☃) : ☃;
  }
  
  private static bbj a(bbj ☃, amr ☃, aoe ☃)
  {
    double ☃ = ☃.b;
    double ☃ = ☃.c;
    double ☃ = ☃.d;
    
    boolean ☃ = true;
    switch (awo.2.b[☃.ordinal()])
    {
    case 1: 
      ☃ = 1.0D - ☃;
      break;
    case 2: 
      ☃ = 1.0D - ☃;
      break;
    default: 
      ☃ = false;
    }
    switch (awo.2.a[☃.ordinal()])
    {
    case 3: 
      return new bbj(1.0D - ☃, ☃, 1.0D - ☃);
    case 1: 
      return new bbj(☃, ☃, 1.0D - ☃);
    case 2: 
      return new bbj(1.0D - ☃, ☃, ☃);
    }
    return ☃ ? new bbj(☃, ☃, ☃) : ☃;
  }
  
  public void a(dn ☃)
  {
    du ☃ = new du();
    for (awo.a ☃ : this.a)
    {
      dn ☃ = new dn();
      ☃.a("pos", a(new int[] { ☃.a.p(), ☃.a.q(), ☃.a.r() }));
      ☃.a("state", ajt.j(☃.b));
      if (☃.c != null) {
        ☃.a("nbt", ☃.c);
      }
      ☃.a(☃);
    }
    du ☃ = new du();
    for (awo.b ☃ : this.b)
    {
      dn ☃ = new dn();
      ☃.a("pos", a(new double[] { ☃.a.b, ☃.a.c, ☃.a.d }));
      ☃.a("blockPos", a(new int[] { ☃.b.p(), ☃.b.q(), ☃.b.r() }));
      if (☃.c != null) {
        ☃.a("nbt", ☃.c);
      }
      ☃.a(☃);
    }
    ☃.a("blocks", ☃);
    ☃.a("entities", ☃);
    ☃.a("size", a(new int[] { this.c.p(), this.c.q(), this.c.r() }));
    ☃.a("version", 1);
    ☃.a("author", this.d);
  }
  
  public void b(dn ☃)
  {
    this.a.clear();
    this.b.clear();
    
    du ☃ = ☃.c("size", 3);
    this.c = new cj(☃.c(0), ☃.c(1), ☃.c(2));
    this.d = ☃.l("author");
    
    du ☃ = ☃.c("blocks", 10);
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      dn ☃ = ☃.b(☃);
      du ☃ = ☃.c("pos", 3);
      cj ☃ = new cj(☃.c(0), ☃.c(1), ☃.c(2));
      int ☃ = ☃.h("state");
      arc ☃ = ajt.c(☃);
      dn ☃;
      dn ☃;
      if (☃.e("nbt")) {
        ☃ = ☃.o("nbt");
      } else {
        ☃ = null;
      }
      this.a.add(new awo.a(☃, ☃, ☃, null));
    }
    du ☃ = ☃.c("entities", 10);
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      dn ☃ = ☃.b(☃);
      du ☃ = ☃.c("pos", 6);
      bbj ☃ = new bbj(☃.e(0), ☃.e(1), ☃.e(2));
      du ☃ = ☃.c("blockPos", 3);
      cj ☃ = new cj(☃.c(0), ☃.c(1), ☃.c(2));
      if (☃.e("nbt"))
      {
        dn ☃ = ☃.o("nbt");
        this.b.add(new awo.b(☃, ☃, ☃, null));
      }
    }
  }
  
  private du a(int... ☃)
  {
    du ☃ = new du();
    for (int ☃ : ☃) {
      ☃.a(new dt(☃));
    }
    return ☃;
  }
  
  private du a(double... ☃)
  {
    du ☃ = new du();
    for (double ☃ : ☃) {
      ☃.a(new dp(☃));
    }
    return ☃;
  }
  
  static class a
  {
    public final cj a;
    public final arc b;
    public final dn c;
    
    private a(cj ☃, arc ☃, dn ☃)
    {
      this.a = ☃;
      this.b = ☃;
      this.c = ☃;
    }
  }
  
  static class b
  {
    public final bbj a;
    public final cj b;
    public final dn c;
    
    private b(bbj ☃, cj ☃, dn ☃)
    {
      this.a = ☃;
      this.b = ☃;
      this.c = ☃;
    }
  }
}
