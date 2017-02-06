import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class awc
{
  public static void a()
  {
    awe.a(awc.a.class, "SHCC");
    awe.a(awc.b.class, "SHFC");
    awe.a(awc.c.class, "SH5C");
    awe.a(awc.d.class, "SHLT");
    awe.a(awc.e.class, "SHLi");
    awe.a(awc.g.class, "SHPR");
    awe.a(awc.h.class, "SHPH");
    awe.a(awc.i.class, "SHRT");
    awe.a(awc.j.class, "SHRC");
    awe.a(awc.l.class, "SHSD");
    awe.a(awc.m.class, "SHStart");
    awe.a(awc.n.class, "SHS");
    awe.a(awc.o.class, "SHSSD");
  }
  
  static class f
  {
    public Class<? extends awc.p> a;
    public final int b;
    public int c;
    public int d;
    
    public f(Class<? extends awc.p> ☃, int ☃, int ☃)
    {
      this.a = ☃;
      this.b = ☃;
      this.d = ☃;
    }
    
    public boolean a(int ☃)
    {
      return (this.d == 0) || (this.c < this.d);
    }
    
    public boolean a()
    {
      return (this.d == 0) || (this.c < this.d);
    }
  }
  
  private static final awc.f[] b = { new awc.f(awc.n.class, 40, 0), new awc.f(awc.h.class, 5, 5), new awc.f(awc.d.class, 20, 0), new awc.f(awc.i.class, 20, 0), new awc.f(awc.j.class, 10, 6), new awc.f(awc.o.class, 5, 5), new awc.f(awc.l.class, 5, 5), new awc.f(awc.c.class, 5, 4), new awc.f(awc.a.class, 5, 4), new awc.f(awc.e.class, 10, 2)new awc.f
  {
    public boolean a(int ☃)
    {
      return (super.a(☃)) && (☃ > 4);
    }
  }, new awc.f(awc.g.class, 20, 1)
  {
    public boolean a(int ☃)
    {
      return (super.a(☃)) && (☃ > 5);
    }
  } };
  private static List<awc.f> c;
  private static Class<? extends awc.p> d;
  static int a;
  
  public static void b()
  {
    c = Lists.newArrayList();
    for (awc.f ☃ : b)
    {
      ☃.c = 0;
      c.add(☃);
    }
    d = null;
  }
  
  private static boolean d()
  {
    boolean ☃ = false;
    a = 0;
    for (awc.f ☃ : c)
    {
      if ((☃.d > 0) && (☃.c < ☃.d)) {
        ☃ = true;
      }
      a += ☃.b;
    }
    return ☃;
  }
  
  private static awc.p a(Class<? extends awc.p> ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    awc.p ☃ = null;
    if (☃ == awc.n.class) {
      ☃ = awc.n.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    } else if (☃ == awc.h.class) {
      ☃ = awc.h.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    } else if (☃ == awc.d.class) {
      ☃ = awc.d.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    } else if (☃ == awc.i.class) {
      ☃ = awc.i.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    } else if (☃ == awc.j.class) {
      ☃ = awc.j.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    } else if (☃ == awc.o.class) {
      ☃ = awc.o.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    } else if (☃ == awc.l.class) {
      ☃ = awc.l.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    } else if (☃ == awc.c.class) {
      ☃ = awc.c.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    } else if (☃ == awc.a.class) {
      ☃ = awc.a.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    } else if (☃ == awc.e.class) {
      ☃ = awc.e.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    } else if (☃ == awc.g.class) {
      ☃ = awc.g.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
    return ☃;
  }
  
  private static awc.p b(awc.m ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    if (!d()) {
      return null;
    }
    if (d != null)
    {
      awc.p ☃ = a(d, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
      d = null;
      if (☃ != null) {
        return ☃;
      }
    }
    int ☃ = 0;
    int ☃;
    while (☃ < 5)
    {
      ☃++;
      
      ☃ = ☃.nextInt(a);
      for (awc.f ☃ : c)
      {
        ☃ -= ☃.b;
        if (☃ < 0)
        {
          if ((!☃.a(☃)) || (☃ == ☃.a)) {
            break;
          }
          awc.p ☃ = a(☃.a, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
          if (☃ != null)
          {
            ☃.c += 1;
            ☃.a = ☃;
            if (!☃.a()) {
              c.remove(☃);
            }
            return ☃;
          }
        }
      }
    }
    avp ☃ = awc.b.a(☃, ☃, ☃, ☃, ☃, ☃);
    if ((☃ != null) && (☃.b > 1)) {
      return new awc.b(☃, ☃, ☃, ☃);
    }
    return null;
  }
  
  private static awg c(awc.m ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    if (☃ > 50) {
      return null;
    }
    if ((Math.abs(☃ - ☃.c().a) > 112) || (Math.abs(☃ - ☃.c().c) > 112)) {
      return null;
    }
    awg ☃ = b(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃ + 1);
    if (☃ != null)
    {
      ☃.add(☃);
      ☃.c.add(☃);
    }
    return ☃;
  }
  
  static abstract class p
    extends awg
  {
    protected awc.p.a d = awc.p.a.a;
    
    public p() {}
    
    protected p(int ☃)
    {
      super();
    }
    
    protected void a(dn ☃)
    {
      ☃.a("EntryDoor", this.d.name());
    }
    
    protected void b(dn ☃)
    {
      this.d = awc.p.a.valueOf(☃.l("EntryDoor"));
    }
    
    protected void a(aht ☃, Random ☃, avp ☃, awc.p.a ☃, int ☃, int ☃, int ☃)
    {
      switch (awc.3.a[☃.ordinal()])
      {
      case 1: 
      default: 
        a(☃, ☃, ☃, ☃, ☃, ☃ + 3 - 1, ☃ + 3 - 1, ☃, aju.a.u(), aju.a.u(), false);
        break;
      case 2: 
        a(☃, aju.bf.u(), ☃, ☃, ☃, ☃);
        a(☃, aju.bf.u(), ☃, ☃ + 1, ☃, ☃);
        a(☃, aju.bf.u(), ☃, ☃ + 2, ☃, ☃);
        a(☃, aju.bf.u(), ☃ + 1, ☃ + 2, ☃, ☃);
        a(☃, aju.bf.u(), ☃ + 2, ☃ + 2, ☃, ☃);
        a(☃, aju.bf.u(), ☃ + 2, ☃ + 1, ☃, ☃);
        a(☃, aju.bf.u(), ☃ + 2, ☃, ☃, ☃);
        a(☃, aju.ao.u(), ☃ + 1, ☃, ☃, ☃);
        a(☃, aju.ao.u().a(akv.e, akv.a.a), ☃ + 1, ☃ + 1, ☃, ☃);
        break;
      case 3: 
        a(☃, aju.a.u(), ☃ + 1, ☃, ☃, ☃);
        a(☃, aju.a.u(), ☃ + 1, ☃ + 1, ☃, ☃);
        a(☃, aju.bi.u(), ☃, ☃, ☃, ☃);
        a(☃, aju.bi.u(), ☃, ☃ + 1, ☃, ☃);
        a(☃, aju.bi.u(), ☃, ☃ + 2, ☃, ☃);
        a(☃, aju.bi.u(), ☃ + 1, ☃ + 2, ☃, ☃);
        a(☃, aju.bi.u(), ☃ + 2, ☃ + 2, ☃, ☃);
        a(☃, aju.bi.u(), ☃ + 2, ☃ + 1, ☃, ☃);
        a(☃, aju.bi.u(), ☃ + 2, ☃, ☃, ☃);
        break;
      case 4: 
        a(☃, aju.bf.u(), ☃, ☃, ☃, ☃);
        a(☃, aju.bf.u(), ☃, ☃ + 1, ☃, ☃);
        a(☃, aju.bf.u(), ☃, ☃ + 2, ☃, ☃);
        a(☃, aju.bf.u(), ☃ + 1, ☃ + 2, ☃, ☃);
        a(☃, aju.bf.u(), ☃ + 2, ☃ + 2, ☃, ☃);
        a(☃, aju.bf.u(), ☃ + 2, ☃ + 1, ☃, ☃);
        a(☃, aju.bf.u(), ☃ + 2, ☃, ☃, ☃);
        a(☃, aju.aA.u(), ☃ + 1, ☃, ☃, ☃);
        a(☃, aju.aA.u().a(akv.e, akv.a.a), ☃ + 1, ☃ + 1, ☃, ☃);
        a(☃, aju.aG.u().a(ajz.H, cq.c), ☃ + 2, ☃ + 1, ☃ + 1, ☃);
        a(☃, aju.aG.u().a(ajz.H, cq.d), ☃ + 2, ☃ + 1, ☃ - 1, ☃);
      }
    }
    
    protected awc.p.a a(Random ☃)
    {
      int ☃ = ☃.nextInt(5);
      switch (☃)
      {
      case 0: 
      case 1: 
      default: 
        return awc.p.a.a;
      case 2: 
        return awc.p.a.b;
      case 3: 
        return awc.p.a.c;
      }
      return awc.p.a.d;
    }
    
    protected awg a(awc.m ☃, List<awg> ☃, Random ☃, int ☃, int ☃)
    {
      cq ☃ = e();
      if (☃ != null) {
        switch (awc.3.b[☃.ordinal()])
        {
        case 1: 
          return awc.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.c - 1, ☃, d());
        case 2: 
          return awc.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.f + 1, ☃, d());
        case 3: 
          return awc.a(☃, ☃, ☃, this.l.a - 1, this.l.b + ☃, this.l.c + ☃, ☃, d());
        case 4: 
          return awc.a(☃, ☃, ☃, this.l.d + 1, this.l.b + ☃, this.l.c + ☃, ☃, d());
        }
      }
      return null;
    }
    
    protected awg b(awc.m ☃, List<awg> ☃, Random ☃, int ☃, int ☃)
    {
      cq ☃ = e();
      if (☃ != null) {
        switch (awc.3.b[☃.ordinal()])
        {
        case 1: 
          return awc.a(☃, ☃, ☃, this.l.a - 1, this.l.b + ☃, this.l.c + ☃, cq.e, d());
        case 2: 
          return awc.a(☃, ☃, ☃, this.l.a - 1, this.l.b + ☃, this.l.c + ☃, cq.e, d());
        case 3: 
          return awc.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.c - 1, cq.c, d());
        case 4: 
          return awc.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.c - 1, cq.c, d());
        }
      }
      return null;
    }
    
    protected awg c(awc.m ☃, List<awg> ☃, Random ☃, int ☃, int ☃)
    {
      cq ☃ = e();
      if (☃ != null) {
        switch (awc.3.b[☃.ordinal()])
        {
        case 1: 
          return awc.a(☃, ☃, ☃, this.l.d + 1, this.l.b + ☃, this.l.c + ☃, cq.f, d());
        case 2: 
          return awc.a(☃, ☃, ☃, this.l.d + 1, this.l.b + ☃, this.l.c + ☃, cq.f, d());
        case 3: 
          return awc.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.f + 1, cq.d, d());
        case 4: 
          return awc.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.f + 1, cq.d, d());
        }
      }
      return null;
    }
    
    protected static boolean a(avp ☃)
    {
      return (☃ != null) && (☃.b > 10);
    }
    
    public static enum a
    {
      private a() {}
    }
  }
  
  public static class b
    extends awc.p
  {
    private int a;
    
    public b() {}
    
    public b(int ☃, Random ☃, avp ☃, cq ☃)
    {
      super();
      
      a(☃);
      this.l = ☃;
      this.a = ((☃ == cq.c) || (☃ == cq.d) ? ☃.e() : ☃.c());
    }
    
    protected void a(dn ☃)
    {
      super.a(☃);
      ☃.a("Steps", this.a);
    }
    
    protected void b(dn ☃)
    {
      super.b(☃);
      this.a = ☃.h("Steps");
    }
    
    public static avp a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃)
    {
      int ☃ = 3;
      
      avp ☃ = avp.a(☃, ☃, ☃, -1, -1, 0, 5, 5, 4, ☃);
      
      awg ☃ = awg.a(☃, ☃);
      if (☃ == null) {
        return null;
      }
      if (☃.c().b == ☃.b) {
        for (int ☃ = 3; ☃ >= 1; ☃--)
        {
          ☃ = avp.a(☃, ☃, ☃, -1, -1, 0, 5, 5, ☃ - 1, ☃);
          if (!☃.c().a(☃)) {
            return avp.a(☃, ☃, ☃, -1, -1, 0, 5, 5, ☃, ☃);
          }
        }
      }
      return null;
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, ☃)) {
        return false;
      }
      for (int ☃ = 0; ☃ < this.a; ☃++)
      {
        a(☃, aju.bf.u(), 0, 0, ☃, ☃);
        a(☃, aju.bf.u(), 1, 0, ☃, ☃);
        a(☃, aju.bf.u(), 2, 0, ☃, ☃);
        a(☃, aju.bf.u(), 3, 0, ☃, ☃);
        a(☃, aju.bf.u(), 4, 0, ☃, ☃);
        for (int ☃ = 1; ☃ <= 3; ☃++)
        {
          a(☃, aju.bf.u(), 0, ☃, ☃, ☃);
          a(☃, aju.a.u(), 1, ☃, ☃, ☃);
          a(☃, aju.a.u(), 2, ☃, ☃, ☃);
          a(☃, aju.a.u(), 3, ☃, ☃, ☃);
          a(☃, aju.bf.u(), 4, ☃, ☃, ☃);
        }
        a(☃, aju.bf.u(), 0, 4, ☃, ☃);
        a(☃, aju.bf.u(), 1, 4, ☃, ☃);
        a(☃, aju.bf.u(), 2, 4, ☃, ☃);
        a(☃, aju.bf.u(), 3, 4, ☃, ☃);
        a(☃, aju.bf.u(), 4, 4, ☃, ☃);
      }
      return true;
    }
  }
  
  public static class l
    extends awc.p
  {
    private boolean a;
    
    public l() {}
    
    public l(int ☃, Random ☃, int ☃, int ☃)
    {
      super();
      
      this.a = true;
      a(cq.c.a.a(☃));
      this.d = awc.p.a.a;
      if (e().k() == cq.a.c) {
        this.l = new avp(☃, 64, ☃, ☃ + 5 - 1, 74, ☃ + 5 - 1);
      } else {
        this.l = new avp(☃, 64, ☃, ☃ + 5 - 1, 74, ☃ + 5 - 1);
      }
    }
    
    public l(int ☃, Random ☃, avp ☃, cq ☃)
    {
      super();
      
      this.a = false;
      a(☃);
      this.d = a(☃);
      this.l = ☃;
    }
    
    protected void a(dn ☃)
    {
      super.a(☃);
      ☃.a("Source", this.a);
    }
    
    protected void b(dn ☃)
    {
      super.b(☃);
      this.a = ☃.p("Source");
    }
    
    public void a(awg ☃, List<awg> ☃, Random ☃)
    {
      if (this.a) {
        awc.a(awc.c.class);
      }
      a((awc.m)☃, ☃, ☃, 1, 1);
    }
    
    public static l a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, -1, -7, 0, 5, 11, 5, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
        return null;
      }
      return new l(☃, ☃, ☃, ☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, ☃)) {
        return false;
      }
      a(☃, ☃, 0, 0, 0, 4, 10, 4, true, ☃, awc.c());
      
      a(☃, ☃, ☃, this.d, 1, 7, 0);
      
      a(☃, ☃, ☃, awc.p.a.a, 1, 1, 4);
      
      a(☃, aju.bf.u(), 2, 6, 1, ☃);
      a(☃, aju.bf.u(), 1, 5, 1, ☃);
      a(☃, aju.U.a(apa.a.a.a()), 1, 6, 1, ☃);
      a(☃, aju.bf.u(), 1, 5, 2, ☃);
      a(☃, aju.bf.u(), 1, 4, 3, ☃);
      a(☃, aju.U.a(apa.a.a.a()), 1, 5, 3, ☃);
      a(☃, aju.bf.u(), 2, 4, 3, ☃);
      a(☃, aju.bf.u(), 3, 3, 3, ☃);
      a(☃, aju.U.a(apa.a.a.a()), 3, 4, 3, ☃);
      a(☃, aju.bf.u(), 3, 3, 2, ☃);
      a(☃, aju.bf.u(), 3, 2, 1, ☃);
      a(☃, aju.U.a(apa.a.a.a()), 3, 3, 1, ☃);
      a(☃, aju.bf.u(), 2, 2, 1, ☃);
      a(☃, aju.bf.u(), 1, 1, 1, ☃);
      a(☃, aju.U.a(apa.a.a.a()), 1, 2, 1, ☃);
      a(☃, aju.bf.u(), 1, 1, 2, ☃);
      a(☃, aju.U.a(apa.a.a.a()), 1, 1, 3, ☃);
      
      return true;
    }
  }
  
  public static class m
    extends awc.l
  {
    public awc.f a;
    public awc.g b;
    public List<awg> c = Lists.newArrayList();
    
    public m() {}
    
    public m(int ☃, Random ☃, int ☃, int ☃)
    {
      super(☃, ☃, ☃);
    }
    
    public cj a()
    {
      if (this.b != null) {
        return this.b.a();
      }
      return super.a();
    }
  }
  
  public static class n
    extends awc.p
  {
    private boolean a;
    private boolean b;
    
    public n() {}
    
    public n(int ☃, Random ☃, avp ☃, cq ☃)
    {
      super();
      
      a(☃);
      this.d = a(☃);
      this.l = ☃;
      
      this.a = (☃.nextInt(2) == 0);
      this.b = (☃.nextInt(2) == 0);
    }
    
    protected void a(dn ☃)
    {
      super.a(☃);
      ☃.a("Left", this.a);
      ☃.a("Right", this.b);
    }
    
    protected void b(dn ☃)
    {
      super.b(☃);
      this.a = ☃.p("Left");
      this.b = ☃.p("Right");
    }
    
    public void a(awg ☃, List<awg> ☃, Random ☃)
    {
      a((awc.m)☃, ☃, ☃, 1, 1);
      if (this.a) {
        b((awc.m)☃, ☃, ☃, 1, 2);
      }
      if (this.b) {
        c((awc.m)☃, ☃, ☃, 1, 2);
      }
    }
    
    public static n a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, -1, -1, 0, 5, 5, 7, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
        return null;
      }
      return new n(☃, ☃, ☃, ☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, ☃)) {
        return false;
      }
      a(☃, ☃, 0, 0, 0, 4, 4, 6, true, ☃, awc.c());
      
      a(☃, ☃, ☃, this.d, 1, 1, 0);
      
      a(☃, ☃, ☃, awc.p.a.a, 1, 1, 6);
      
      arc ☃ = aju.aa.u().a(apf.a, cq.f);
      arc ☃ = aju.aa.u().a(apf.a, cq.e);
      
      a(☃, ☃, ☃, 0.1F, 1, 2, 1, ☃);
      a(☃, ☃, ☃, 0.1F, 3, 2, 1, ☃);
      a(☃, ☃, ☃, 0.1F, 1, 2, 5, ☃);
      a(☃, ☃, ☃, 0.1F, 3, 2, 5, ☃);
      if (this.a) {
        a(☃, ☃, 0, 1, 2, 0, 3, 4, aju.a.u(), aju.a.u(), false);
      }
      if (this.b) {
        a(☃, ☃, 4, 1, 2, 4, 3, 4, aju.a.u(), aju.a.u(), false);
      }
      return true;
    }
  }
  
  public static class a
    extends awc.p
  {
    private boolean a;
    
    public a() {}
    
    public a(int ☃, Random ☃, avp ☃, cq ☃)
    {
      super();
      
      a(☃);
      this.d = a(☃);
      this.l = ☃;
    }
    
    protected void a(dn ☃)
    {
      super.a(☃);
      ☃.a("Chest", this.a);
    }
    
    protected void b(dn ☃)
    {
      super.b(☃);
      this.a = ☃.p("Chest");
    }
    
    public void a(awg ☃, List<awg> ☃, Random ☃)
    {
      a((awc.m)☃, ☃, ☃, 1, 1);
    }
    
    public static a a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, -1, -1, 0, 5, 5, 7, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
        return null;
      }
      return new a(☃, ☃, ☃, ☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, ☃)) {
        return false;
      }
      a(☃, ☃, 0, 0, 0, 4, 4, 6, true, ☃, awc.c());
      
      a(☃, ☃, ☃, this.d, 1, 1, 0);
      
      a(☃, ☃, ☃, awc.p.a.a, 1, 1, 6);
      
      a(☃, ☃, 3, 1, 2, 3, 1, 4, aju.bf.u(), aju.bf.u(), false);
      a(☃, aju.U.a(apa.a.f.a()), 3, 1, 1, ☃);
      a(☃, aju.U.a(apa.a.f.a()), 3, 1, 5, ☃);
      a(☃, aju.U.a(apa.a.f.a()), 3, 2, 2, ☃);
      a(☃, aju.U.a(apa.a.f.a()), 3, 2, 4, ☃);
      for (int ☃ = 2; ☃ <= 4; ☃++) {
        a(☃, aju.U.a(apa.a.f.a()), 2, 1, ☃, ☃);
      }
      if ((!this.a) && 
        (☃.b(new cj(a(3, 3), d(2), b(3, 3)))))
      {
        this.a = true;
        a(☃, ☃, ☃, 3, 2, 3, azt.j);
      }
      return true;
    }
  }
  
  public static class o
    extends awc.p
  {
    public o() {}
    
    public o(int ☃, Random ☃, avp ☃, cq ☃)
    {
      super();
      
      a(☃);
      this.d = a(☃);
      this.l = ☃;
    }
    
    public void a(awg ☃, List<awg> ☃, Random ☃)
    {
      a((awc.m)☃, ☃, ☃, 1, 1);
    }
    
    public static o a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, -1, -7, 0, 5, 11, 8, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
        return null;
      }
      return new o(☃, ☃, ☃, ☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, ☃)) {
        return false;
      }
      a(☃, ☃, 0, 0, 0, 4, 10, 7, true, ☃, awc.c());
      
      a(☃, ☃, ☃, this.d, 1, 7, 0);
      
      a(☃, ☃, ☃, awc.p.a.a, 1, 1, 7);
      
      arc ☃ = aju.aw.u().a(aot.a, cq.d);
      for (int ☃ = 0; ☃ < 6; ☃++)
      {
        a(☃, ☃, 1, 6 - ☃, 1 + ☃, ☃);
        a(☃, ☃, 2, 6 - ☃, 1 + ☃, ☃);
        a(☃, ☃, 3, 6 - ☃, 1 + ☃, ☃);
        if (☃ < 5)
        {
          a(☃, aju.bf.u(), 1, 5 - ☃, 1 + ☃, ☃);
          a(☃, aju.bf.u(), 2, 5 - ☃, 1 + ☃, ☃);
          a(☃, aju.bf.u(), 3, 5 - ☃, 1 + ☃, ☃);
        }
      }
      return true;
    }
  }
  
  public static class d
    extends awc.p
  {
    public d() {}
    
    public d(int ☃, Random ☃, avp ☃, cq ☃)
    {
      super();
      
      a(☃);
      this.d = a(☃);
      this.l = ☃;
    }
    
    public void a(awg ☃, List<awg> ☃, Random ☃)
    {
      cq ☃ = e();
      if ((☃ == cq.c) || (☃ == cq.f)) {
        b((awc.m)☃, ☃, ☃, 1, 1);
      } else {
        c((awc.m)☃, ☃, ☃, 1, 1);
      }
    }
    
    public static d a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, -1, -1, 0, 5, 5, 5, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
        return null;
      }
      return new d(☃, ☃, ☃, ☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, ☃)) {
        return false;
      }
      a(☃, ☃, 0, 0, 0, 4, 4, 4, true, ☃, awc.c());
      
      a(☃, ☃, ☃, this.d, 1, 1, 0);
      
      cq ☃ = e();
      if ((☃ == cq.c) || (☃ == cq.f)) {
        a(☃, ☃, 0, 1, 1, 0, 3, 3, aju.a.u(), aju.a.u(), false);
      } else {
        a(☃, ☃, 4, 1, 1, 4, 3, 3, aju.a.u(), aju.a.u(), false);
      }
      return true;
    }
  }
  
  public static class i
    extends awc.d
  {
    public void a(awg ☃, List<awg> ☃, Random ☃)
    {
      cq ☃ = e();
      if ((☃ == cq.c) || (☃ == cq.f)) {
        c((awc.m)☃, ☃, ☃, 1, 1);
      } else {
        b((awc.m)☃, ☃, ☃, 1, 1);
      }
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, ☃)) {
        return false;
      }
      a(☃, ☃, 0, 0, 0, 4, 4, 4, true, ☃, awc.c());
      
      a(☃, ☃, ☃, this.d, 1, 1, 0);
      
      cq ☃ = e();
      if ((☃ == cq.c) || (☃ == cq.f)) {
        a(☃, ☃, 4, 1, 1, 4, 3, 3, aju.a.u(), aju.a.u(), false);
      } else {
        a(☃, ☃, 0, 1, 1, 0, 3, 3, aju.a.u(), aju.a.u(), false);
      }
      return true;
    }
  }
  
  public static class j
    extends awc.p
  {
    protected int a;
    
    public j() {}
    
    public j(int ☃, Random ☃, avp ☃, cq ☃)
    {
      super();
      
      a(☃);
      this.d = a(☃);
      this.l = ☃;
      this.a = ☃.nextInt(5);
    }
    
    protected void a(dn ☃)
    {
      super.a(☃);
      ☃.a("Type", this.a);
    }
    
    protected void b(dn ☃)
    {
      super.b(☃);
      this.a = ☃.h("Type");
    }
    
    public void a(awg ☃, List<awg> ☃, Random ☃)
    {
      a((awc.m)☃, ☃, ☃, 4, 1);
      b((awc.m)☃, ☃, ☃, 1, 4);
      c((awc.m)☃, ☃, ☃, 1, 4);
    }
    
    public static j a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, -4, -1, 0, 11, 7, 11, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
        return null;
      }
      return new j(☃, ☃, ☃, ☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, ☃)) {
        return false;
      }
      a(☃, ☃, 0, 0, 0, 10, 6, 10, true, ☃, awc.c());
      
      a(☃, ☃, ☃, this.d, 4, 1, 0);
      
      a(☃, ☃, 4, 1, 10, 6, 3, 10, aju.a.u(), aju.a.u(), false);
      a(☃, ☃, 0, 1, 4, 0, 3, 6, aju.a.u(), aju.a.u(), false);
      a(☃, ☃, 10, 1, 4, 10, 3, 6, aju.a.u(), aju.a.u(), false);
      switch (this.a)
      {
      default: 
        break;
      case 0: 
        a(☃, aju.bf.u(), 5, 1, 5, ☃);
        a(☃, aju.bf.u(), 5, 2, 5, ☃);
        a(☃, aju.bf.u(), 5, 3, 5, ☃);
        a(☃, aju.aa.u().a(apf.a, cq.e), 4, 3, 5, ☃);
        a(☃, aju.aa.u().a(apf.a, cq.f), 6, 3, 5, ☃);
        a(☃, aju.aa.u().a(apf.a, cq.d), 5, 3, 4, ☃);
        a(☃, aju.aa.u().a(apf.a, cq.c), 5, 3, 6, ☃);
        a(☃, aju.U.u(), 4, 1, 4, ☃);
        a(☃, aju.U.u(), 4, 1, 5, ☃);
        a(☃, aju.U.u(), 4, 1, 6, ☃);
        a(☃, aju.U.u(), 6, 1, 4, ☃);
        a(☃, aju.U.u(), 6, 1, 5, ☃);
        a(☃, aju.U.u(), 6, 1, 6, ☃);
        a(☃, aju.U.u(), 5, 1, 4, ☃);
        a(☃, aju.U.u(), 5, 1, 6, ☃);
        break;
      case 1: 
        for (int ☃ = 0; ☃ < 5; ☃++)
        {
          a(☃, aju.bf.u(), 3, 1, 3 + ☃, ☃);
          a(☃, aju.bf.u(), 7, 1, 3 + ☃, ☃);
          a(☃, aju.bf.u(), 3 + ☃, 1, 3, ☃);
          a(☃, aju.bf.u(), 3 + ☃, 1, 7, ☃);
        }
        a(☃, aju.bf.u(), 5, 1, 5, ☃);
        a(☃, aju.bf.u(), 5, 2, 5, ☃);
        a(☃, aju.bf.u(), 5, 3, 5, ☃);
        a(☃, aju.i.u(), 5, 4, 5, ☃);
        break;
      case 2: 
        for (int ☃ = 1; ☃ <= 9; ☃++)
        {
          a(☃, aju.e.u(), 1, 3, ☃, ☃);
          a(☃, aju.e.u(), 9, 3, ☃, ☃);
        }
        for (int ☃ = 1; ☃ <= 9; ☃++)
        {
          a(☃, aju.e.u(), ☃, 3, 1, ☃);
          a(☃, aju.e.u(), ☃, 3, 9, ☃);
        }
        a(☃, aju.e.u(), 5, 1, 4, ☃);
        a(☃, aju.e.u(), 5, 1, 6, ☃);
        a(☃, aju.e.u(), 5, 3, 4, ☃);
        a(☃, aju.e.u(), 5, 3, 6, ☃);
        a(☃, aju.e.u(), 4, 1, 5, ☃);
        a(☃, aju.e.u(), 6, 1, 5, ☃);
        a(☃, aju.e.u(), 4, 3, 5, ☃);
        a(☃, aju.e.u(), 6, 3, 5, ☃);
        for (int ☃ = 1; ☃ <= 3; ☃++)
        {
          a(☃, aju.e.u(), 4, ☃, 4, ☃);
          a(☃, aju.e.u(), 6, ☃, 4, ☃);
          a(☃, aju.e.u(), 4, ☃, 6, ☃);
          a(☃, aju.e.u(), 6, ☃, 6, ☃);
        }
        a(☃, aju.aa.u(), 5, 3, 5, ☃);
        for (int ☃ = 2; ☃ <= 8; ☃++)
        {
          a(☃, aju.f.u(), 2, 3, ☃, ☃);
          a(☃, aju.f.u(), 3, 3, ☃, ☃);
          if ((☃ <= 3) || (☃ >= 7))
          {
            a(☃, aju.f.u(), 4, 3, ☃, ☃);
            a(☃, aju.f.u(), 5, 3, ☃, ☃);
            a(☃, aju.f.u(), 6, 3, ☃, ☃);
          }
          a(☃, aju.f.u(), 7, 3, ☃, ☃);
          a(☃, aju.f.u(), 8, 3, ☃, ☃);
        }
        arc ☃ = aju.au.u().a(amk.a, cq.e);
        a(☃, ☃, 9, 1, 3, ☃);
        a(☃, ☃, 9, 2, 3, ☃);
        a(☃, ☃, 9, 3, 3, ☃);
        
        a(☃, ☃, ☃, 3, 4, 8, azt.i);
      }
      return true;
    }
  }
  
  public static class h
    extends awc.p
  {
    public h() {}
    
    public h(int ☃, Random ☃, avp ☃, cq ☃)
    {
      super();
      
      a(☃);
      this.d = a(☃);
      this.l = ☃;
    }
    
    public void a(awg ☃, List<awg> ☃, Random ☃)
    {
      a((awc.m)☃, ☃, ☃, 1, 1);
    }
    
    public static h a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, -1, -1, 0, 9, 5, 11, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
        return null;
      }
      return new h(☃, ☃, ☃, ☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, ☃)) {
        return false;
      }
      a(☃, ☃, 0, 0, 0, 8, 4, 10, true, ☃, awc.c());
      
      a(☃, ☃, ☃, this.d, 1, 1, 0);
      
      a(☃, ☃, 1, 1, 10, 3, 3, 10, aju.a.u(), aju.a.u(), false);
      
      a(☃, ☃, 4, 1, 1, 4, 3, 1, false, ☃, awc.c());
      a(☃, ☃, 4, 1, 3, 4, 3, 3, false, ☃, awc.c());
      a(☃, ☃, 4, 1, 7, 4, 3, 7, false, ☃, awc.c());
      a(☃, ☃, 4, 1, 9, 4, 3, 9, false, ☃, awc.c());
      
      a(☃, ☃, 4, 1, 4, 4, 3, 6, aju.bi.u(), aju.bi.u(), false);
      a(☃, ☃, 5, 1, 5, 7, 3, 5, aju.bi.u(), aju.bi.u(), false);
      
      a(☃, aju.bi.u(), 4, 3, 2, ☃);
      a(☃, aju.bi.u(), 4, 3, 8, ☃);
      arc ☃ = aju.aA.u().a(akv.a, cq.e);
      arc ☃ = aju.aA.u().a(akv.a, cq.e).a(akv.e, akv.a.a);
      a(☃, ☃, 4, 1, 2, ☃);
      a(☃, ☃, 4, 2, 2, ☃);
      a(☃, ☃, 4, 1, 8, ☃);
      a(☃, ☃, 4, 2, 8, ☃);
      
      return true;
    }
  }
  
  public static class e
    extends awc.p
  {
    private boolean a;
    
    public e() {}
    
    public e(int ☃, Random ☃, avp ☃, cq ☃)
    {
      super();
      
      a(☃);
      this.d = a(☃);
      this.l = ☃;
      this.a = (☃.d() > 6);
    }
    
    protected void a(dn ☃)
    {
      super.a(☃);
      ☃.a("Tall", this.a);
    }
    
    protected void b(dn ☃)
    {
      super.b(☃);
      this.a = ☃.p("Tall");
    }
    
    public static e a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, -4, -1, 0, 14, 11, 15, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null))
      {
        ☃ = avp.a(☃, ☃, ☃, -4, -1, 0, 14, 6, 15, ☃);
        if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
          return null;
        }
      }
      return new e(☃, ☃, ☃, ☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, ☃)) {
        return false;
      }
      int ☃ = 11;
      if (!this.a) {
        ☃ = 6;
      }
      a(☃, ☃, 0, 0, 0, 13, ☃ - 1, 14, true, ☃, awc.c());
      
      a(☃, ☃, ☃, this.d, 4, 1, 0);
      
      a(☃, ☃, ☃, 0.07F, 2, 1, 1, 11, 4, 13, aju.G.u(), aju.G.u(), false);
      
      int ☃ = 1;
      int ☃ = 12;
      for (int ☃ = 1; ☃ <= 13; ☃++) {
        if ((☃ - 1) % 4 == 0)
        {
          a(☃, ☃, 1, 1, ☃, 1, 4, ☃, aju.f.u(), aju.f.u(), false);
          a(☃, ☃, 12, 1, ☃, 12, 4, ☃, aju.f.u(), aju.f.u(), false);
          
          a(☃, aju.aa.u().a(apf.a, cq.f), 2, 3, ☃, ☃);
          a(☃, aju.aa.u().a(apf.a, cq.e), 11, 3, ☃, ☃);
          if (this.a)
          {
            a(☃, ☃, 1, 6, ☃, 1, 9, ☃, aju.f.u(), aju.f.u(), false);
            a(☃, ☃, 12, 6, ☃, 12, 9, ☃, aju.f.u(), aju.f.u(), false);
          }
        }
        else
        {
          a(☃, ☃, 1, 1, ☃, 1, 4, ☃, aju.X.u(), aju.X.u(), false);
          a(☃, ☃, 12, 1, ☃, 12, 4, ☃, aju.X.u(), aju.X.u(), false);
          if (this.a)
          {
            a(☃, ☃, 1, 6, ☃, 1, 9, ☃, aju.X.u(), aju.X.u(), false);
            a(☃, ☃, 12, 6, ☃, 12, 9, ☃, aju.X.u(), aju.X.u(), false);
          }
        }
      }
      for (int ☃ = 3; ☃ < 12; ☃ += 2)
      {
        a(☃, ☃, 3, 1, ☃, 4, 3, ☃, aju.X.u(), aju.X.u(), false);
        a(☃, ☃, 6, 1, ☃, 7, 3, ☃, aju.X.u(), aju.X.u(), false);
        a(☃, ☃, 9, 1, ☃, 10, 3, ☃, aju.X.u(), aju.X.u(), false);
      }
      if (this.a)
      {
        a(☃, ☃, 1, 5, 1, 3, 5, 13, aju.f.u(), aju.f.u(), false);
        a(☃, ☃, 10, 5, 1, 12, 5, 13, aju.f.u(), aju.f.u(), false);
        a(☃, ☃, 4, 5, 1, 9, 5, 2, aju.f.u(), aju.f.u(), false);
        a(☃, ☃, 4, 5, 12, 9, 5, 13, aju.f.u(), aju.f.u(), false);
        
        a(☃, aju.f.u(), 9, 5, 11, ☃);
        a(☃, aju.f.u(), 8, 5, 11, ☃);
        a(☃, aju.f.u(), 9, 5, 10, ☃);
        
        a(☃, ☃, 3, 6, 2, 3, 6, 12, aju.aO.u(), aju.aO.u(), false);
        a(☃, ☃, 10, 6, 2, 10, 6, 10, aju.aO.u(), aju.aO.u(), false);
        a(☃, ☃, 4, 6, 2, 9, 6, 2, aju.aO.u(), aju.aO.u(), false);
        a(☃, ☃, 4, 6, 12, 8, 6, 12, aju.aO.u(), aju.aO.u(), false);
        a(☃, aju.aO.u(), 9, 6, 11, ☃);
        a(☃, aju.aO.u(), 8, 6, 11, ☃);
        a(☃, aju.aO.u(), 9, 6, 10, ☃);
        
        arc ☃ = aju.au.u().a(amk.a, cq.d);
        a(☃, ☃, 10, 1, 13, ☃);
        a(☃, ☃, 10, 2, 13, ☃);
        a(☃, ☃, 10, 3, 13, ☃);
        a(☃, ☃, 10, 4, 13, ☃);
        a(☃, ☃, 10, 5, 13, ☃);
        a(☃, ☃, 10, 6, 13, ☃);
        a(☃, ☃, 10, 7, 13, ☃);
        
        int ☃ = 7;
        int ☃ = 7;
        a(☃, aju.aO.u(), ☃ - 1, 9, ☃, ☃);
        a(☃, aju.aO.u(), ☃, 9, ☃, ☃);
        a(☃, aju.aO.u(), ☃ - 1, 8, ☃, ☃);
        a(☃, aju.aO.u(), ☃, 8, ☃, ☃);
        a(☃, aju.aO.u(), ☃ - 1, 7, ☃, ☃);
        a(☃, aju.aO.u(), ☃, 7, ☃, ☃);
        
        a(☃, aju.aO.u(), ☃ - 2, 7, ☃, ☃);
        a(☃, aju.aO.u(), ☃ + 1, 7, ☃, ☃);
        a(☃, aju.aO.u(), ☃ - 1, 7, ☃ - 1, ☃);
        a(☃, aju.aO.u(), ☃ - 1, 7, ☃ + 1, ☃);
        a(☃, aju.aO.u(), ☃, 7, ☃ - 1, ☃);
        a(☃, aju.aO.u(), ☃, 7, ☃ + 1, ☃);
        
        arc ☃ = aju.aa.u().a(apf.a, cq.b);
        a(☃, ☃, ☃ - 2, 8, ☃, ☃);
        a(☃, ☃, ☃ + 1, 8, ☃, ☃);
        a(☃, ☃, ☃ - 1, 8, ☃ - 1, ☃);
        a(☃, ☃, ☃ - 1, 8, ☃ + 1, ☃);
        a(☃, ☃, ☃, 8, ☃ - 1, ☃);
        a(☃, ☃, ☃, 8, ☃ + 1, ☃);
      }
      a(☃, ☃, ☃, 3, 3, 5, azt.h);
      if (this.a)
      {
        a(☃, aju.a.u(), 12, 9, 1, ☃);
        a(☃, ☃, ☃, 12, 8, 1, azt.h);
      }
      return true;
    }
  }
  
  public static class c
    extends awc.p
  {
    private boolean a;
    private boolean b;
    private boolean c;
    private boolean e;
    
    public c() {}
    
    public c(int ☃, Random ☃, avp ☃, cq ☃)
    {
      super();
      
      a(☃);
      this.d = a(☃);
      this.l = ☃;
      
      this.a = ☃.nextBoolean();
      this.b = ☃.nextBoolean();
      this.c = ☃.nextBoolean();
      this.e = (☃.nextInt(3) > 0);
    }
    
    protected void a(dn ☃)
    {
      super.a(☃);
      ☃.a("leftLow", this.a);
      ☃.a("leftHigh", this.b);
      ☃.a("rightLow", this.c);
      ☃.a("rightHigh", this.e);
    }
    
    protected void b(dn ☃)
    {
      super.b(☃);
      this.a = ☃.p("leftLow");
      this.b = ☃.p("leftHigh");
      this.c = ☃.p("rightLow");
      this.e = ☃.p("rightHigh");
    }
    
    public void a(awg ☃, List<awg> ☃, Random ☃)
    {
      int ☃ = 3;
      int ☃ = 5;
      
      cq ☃ = e();
      if ((☃ == cq.e) || (☃ == cq.c))
      {
        ☃ = 8 - ☃;
        ☃ = 8 - ☃;
      }
      a((awc.m)☃, ☃, ☃, 5, 1);
      if (this.a) {
        b((awc.m)☃, ☃, ☃, ☃, 1);
      }
      if (this.b) {
        b((awc.m)☃, ☃, ☃, ☃, 7);
      }
      if (this.c) {
        c((awc.m)☃, ☃, ☃, ☃, 1);
      }
      if (this.e) {
        c((awc.m)☃, ☃, ☃, ☃, 7);
      }
    }
    
    public static c a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, -4, -3, 0, 10, 9, 11, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
        return null;
      }
      return new c(☃, ☃, ☃, ☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, ☃)) {
        return false;
      }
      a(☃, ☃, 0, 0, 0, 9, 8, 10, true, ☃, awc.c());
      
      a(☃, ☃, ☃, this.d, 4, 3, 0);
      if (this.a) {
        a(☃, ☃, 0, 3, 1, 0, 5, 3, aju.a.u(), aju.a.u(), false);
      }
      if (this.c) {
        a(☃, ☃, 9, 3, 1, 9, 5, 3, aju.a.u(), aju.a.u(), false);
      }
      if (this.b) {
        a(☃, ☃, 0, 5, 7, 0, 7, 9, aju.a.u(), aju.a.u(), false);
      }
      if (this.e) {
        a(☃, ☃, 9, 5, 7, 9, 7, 9, aju.a.u(), aju.a.u(), false);
      }
      a(☃, ☃, 5, 1, 10, 7, 3, 10, aju.a.u(), aju.a.u(), false);
      
      a(☃, ☃, 1, 2, 1, 8, 2, 6, false, ☃, awc.c());
      
      a(☃, ☃, 4, 1, 5, 4, 4, 9, false, ☃, awc.c());
      a(☃, ☃, 8, 1, 5, 8, 4, 9, false, ☃, awc.c());
      
      a(☃, ☃, 1, 4, 7, 3, 4, 9, false, ☃, awc.c());
      
      a(☃, ☃, 1, 3, 5, 3, 3, 6, false, ☃, awc.c());
      a(☃, ☃, 1, 3, 4, 3, 3, 4, aju.U.u(), aju.U.u(), false);
      a(☃, ☃, 1, 4, 6, 3, 4, 6, aju.U.u(), aju.U.u(), false);
      
      a(☃, ☃, 5, 1, 7, 7, 1, 8, false, ☃, awc.c());
      a(☃, ☃, 5, 1, 9, 7, 1, 9, aju.U.u(), aju.U.u(), false);
      a(☃, ☃, 5, 2, 7, 7, 2, 7, aju.U.u(), aju.U.u(), false);
      
      a(☃, ☃, 4, 5, 7, 4, 5, 9, aju.U.u(), aju.U.u(), false);
      a(☃, ☃, 8, 5, 7, 8, 5, 9, aju.U.u(), aju.U.u(), false);
      a(☃, ☃, 5, 5, 7, 7, 5, 9, aju.T.u(), aju.T.u(), false);
      a(☃, aju.aa.u().a(apf.a, cq.d), 6, 5, 6, ☃);
      
      return true;
    }
  }
  
  public static class g
    extends awc.p
  {
    private boolean a;
    
    public g() {}
    
    public g(int ☃, Random ☃, avp ☃, cq ☃)
    {
      super();
      
      a(☃);
      this.l = ☃;
    }
    
    protected void a(dn ☃)
    {
      super.a(☃);
      ☃.a("Mob", this.a);
    }
    
    protected void b(dn ☃)
    {
      super.b(☃);
      this.a = ☃.p("Mob");
    }
    
    public void a(awg ☃, List<awg> ☃, Random ☃)
    {
      if (☃ != null) {
        ((awc.m)☃).b = this;
      }
    }
    
    public static g a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, -4, -1, 0, 11, 8, 16, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
        return null;
      }
      return new g(☃, ☃, ☃, ☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      a(☃, ☃, 0, 0, 0, 10, 7, 15, false, ☃, awc.c());
      
      a(☃, ☃, ☃, awc.p.a.c, 4, 1, 0);
      
      int ☃ = 6;
      a(☃, ☃, 1, ☃, 1, 1, ☃, 14, false, ☃, awc.c());
      a(☃, ☃, 9, ☃, 1, 9, ☃, 14, false, ☃, awc.c());
      a(☃, ☃, 2, ☃, 1, 8, ☃, 2, false, ☃, awc.c());
      a(☃, ☃, 2, ☃, 14, 8, ☃, 14, false, ☃, awc.c());
      
      a(☃, ☃, 1, 1, 1, 2, 1, 4, false, ☃, awc.c());
      a(☃, ☃, 8, 1, 1, 9, 1, 4, false, ☃, awc.c());
      a(☃, ☃, 1, 1, 1, 1, 1, 3, aju.k.u(), aju.k.u(), false);
      a(☃, ☃, 9, 1, 1, 9, 1, 3, aju.k.u(), aju.k.u(), false);
      
      a(☃, ☃, 3, 1, 8, 7, 1, 12, false, ☃, awc.c());
      a(☃, ☃, 4, 1, 9, 6, 1, 11, aju.k.u(), aju.k.u(), false);
      for (int ☃ = 3; ☃ < 14; ☃ += 2)
      {
        a(☃, ☃, 0, 3, ☃, 0, 4, ☃, aju.bi.u(), aju.bi.u(), false);
        a(☃, ☃, 10, 3, ☃, 10, 4, ☃, aju.bi.u(), aju.bi.u(), false);
      }
      for (int ☃ = 2; ☃ < 9; ☃ += 2) {
        a(☃, ☃, ☃, 3, 15, ☃, 4, 15, aju.bi.u(), aju.bi.u(), false);
      }
      arc ☃ = aju.bv.u().a(aot.a, cq.c);
      a(☃, ☃, 4, 1, 5, 6, 1, 7, false, ☃, awc.c());
      a(☃, ☃, 4, 2, 6, 6, 2, 7, false, ☃, awc.c());
      a(☃, ☃, 4, 3, 7, 6, 3, 7, false, ☃, awc.c());
      for (int ☃ = 4; ☃ <= 6; ☃++)
      {
        a(☃, ☃, ☃, 1, 4, ☃);
        a(☃, ☃, ☃, 2, 5, ☃);
        a(☃, ☃, ☃, 3, 6, ☃);
      }
      arc ☃ = aju.bG.u().a(ald.a, cq.c);
      arc ☃ = aju.bG.u().a(ald.a, cq.d);
      arc ☃ = aju.bG.u().a(ald.a, cq.f);
      arc ☃ = aju.bG.u().a(ald.a, cq.e);
      
      boolean ☃ = true;
      boolean[] ☃ = new boolean[12];
      for (int ☃ = 0; ☃ < ☃.length; ☃++)
      {
        ☃[☃] = (☃.nextFloat() > 0.9F ? 1 : false);
        ☃ &= ☃[☃];
      }
      a(☃, ☃.a(ald.b, Boolean.valueOf(☃[0])), 4, 3, 8, ☃);
      a(☃, ☃.a(ald.b, Boolean.valueOf(☃[1])), 5, 3, 8, ☃);
      a(☃, ☃.a(ald.b, Boolean.valueOf(☃[2])), 6, 3, 8, ☃);
      a(☃, ☃.a(ald.b, Boolean.valueOf(☃[3])), 4, 3, 12, ☃);
      a(☃, ☃.a(ald.b, Boolean.valueOf(☃[4])), 5, 3, 12, ☃);
      a(☃, ☃.a(ald.b, Boolean.valueOf(☃[5])), 6, 3, 12, ☃);
      a(☃, ☃.a(ald.b, Boolean.valueOf(☃[6])), 3, 3, 9, ☃);
      a(☃, ☃.a(ald.b, Boolean.valueOf(☃[7])), 3, 3, 10, ☃);
      a(☃, ☃.a(ald.b, Boolean.valueOf(☃[8])), 3, 3, 11, ☃);
      a(☃, ☃.a(ald.b, Boolean.valueOf(☃[9])), 7, 3, 9, ☃);
      a(☃, ☃.a(ald.b, Boolean.valueOf(☃[10])), 7, 3, 10, ☃);
      a(☃, ☃.a(ald.b, Boolean.valueOf(☃[11])), 7, 3, 11, ☃);
      if (☃)
      {
        arc ☃ = aju.bF.u();
        
        a(☃, ☃, 4, 3, 9, ☃);
        a(☃, ☃, 5, 3, 9, ☃);
        a(☃, ☃, 6, 3, 9, ☃);
        a(☃, ☃, 4, 3, 10, ☃);
        a(☃, ☃, 5, 3, 10, ☃);
        a(☃, ☃, 6, 3, 10, ☃);
        a(☃, ☃, 4, 3, 11, ☃);
        a(☃, ☃, 5, 3, 11, ☃);
        a(☃, ☃, 6, 3, 11, ☃);
      }
      if (!this.a)
      {
        ☃ = d(3);
        cj ☃ = new cj(a(5, 6), ☃, b(5, 6));
        if (☃.b(☃))
        {
          this.a = true;
          ☃.a(☃, aju.ac.u(), 2);
          
          apv ☃ = ☃.r(☃);
          if ((☃ instanceof aqk)) {
            ((aqk)☃).b().a("Silverfish");
          }
        }
      }
      return true;
    }
  }
  
  static class k
    extends awg.a
  {
    public void a(Random ☃, int ☃, int ☃, int ☃, boolean ☃)
    {
      if (☃)
      {
        float ☃ = ☃.nextFloat();
        if (☃ < 0.2F) {
          this.a = aju.bf.a(aoy.d);
        } else if (☃ < 0.5F) {
          this.a = aju.bf.a(aoy.c);
        } else if (☃ < 0.55F) {
          this.a = aju.be.a(amt.a.c.a());
        } else {
          this.a = aju.bf.u();
        }
      }
      else
      {
        this.a = aju.a.u();
      }
    }
  }
  
  private static final awc.k e = new awc.k(null);
}
