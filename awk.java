import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class awk
{
  public static void a()
  {
    awe.a(awk.a.class, "ViBH");
    awe.a(awk.b.class, "ViDF");
    awe.a(awk.c.class, "ViF");
    awe.a(awk.d.class, "ViL");
    awe.a(awk.f.class, "ViPH");
    awe.a(awk.g.class, "ViSH");
    awe.a(awk.h.class, "ViSmH");
    awe.a(awk.i.class, "ViST");
    awe.a(awk.j.class, "ViS");
    awe.a(awk.k.class, "ViStart");
    awe.a(awk.l.class, "ViSR");
    awe.a(awk.m.class, "ViTRH");
    awe.a(awk.p.class, "ViW");
  }
  
  public static class e
  {
    public Class<? extends awk.n> a;
    public final int b;
    public int c;
    public int d;
    
    public e(Class<? extends awk.n> ☃, int ☃, int ☃)
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
  
  public static List<awk.e> a(Random ☃, int ☃)
  {
    List<awk.e> ☃ = Lists.newArrayList();
    
    ☃.add(new awk.e(awk.g.class, 4, on.a(☃, 2 + ☃, 4 + ☃ * 2)));
    ☃.add(new awk.e(awk.i.class, 20, on.a(☃, 0 + ☃, 1 + ☃)));
    ☃.add(new awk.e(awk.a.class, 20, on.a(☃, 0 + ☃, 2 + ☃)));
    ☃.add(new awk.e(awk.h.class, 3, on.a(☃, 2 + ☃, 5 + ☃ * 3)));
    ☃.add(new awk.e(awk.f.class, 15, on.a(☃, 0 + ☃, 2 + ☃)));
    ☃.add(new awk.e(awk.b.class, 3, on.a(☃, 1 + ☃, 4 + ☃)));
    ☃.add(new awk.e(awk.c.class, 3, on.a(☃, 2 + ☃, 4 + ☃ * 2)));
    ☃.add(new awk.e(awk.j.class, 15, on.a(☃, 0, 1 + ☃)));
    ☃.add(new awk.e(awk.m.class, 8, on.a(☃, 0 + ☃, 3 + ☃ * 2)));
    
    Iterator<awk.e> ☃ = ☃.iterator();
    while (☃.hasNext()) {
      if (((awk.e)☃.next()).d == 0) {
        ☃.remove();
      }
    }
    return ☃;
  }
  
  private static int a(List<awk.e> ☃)
  {
    boolean ☃ = false;
    int ☃ = 0;
    for (awk.e ☃ : ☃)
    {
      if ((☃.d > 0) && (☃.c < ☃.d)) {
        ☃ = true;
      }
      ☃ += ☃.b;
    }
    return ☃ ? ☃ : -1;
  }
  
  private static awk.n a(awk.k ☃, awk.e ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    Class<? extends awk.n> ☃ = ☃.a;
    awk.n ☃ = null;
    if (☃ == awk.g.class) {
      ☃ = awk.g.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    } else if (☃ == awk.i.class) {
      ☃ = awk.i.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    } else if (☃ == awk.a.class) {
      ☃ = awk.a.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    } else if (☃ == awk.h.class) {
      ☃ = awk.h.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    } else if (☃ == awk.f.class) {
      ☃ = awk.f.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    } else if (☃ == awk.b.class) {
      ☃ = awk.b.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    } else if (☃ == awk.c.class) {
      ☃ = awk.c.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    } else if (☃ == awk.j.class) {
      ☃ = awk.j.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    } else if (☃ == awk.m.class) {
      ☃ = awk.m.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
    return ☃;
  }
  
  private static awk.n c(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    int ☃ = a(☃.e);
    if (☃ <= 0) {
      return null;
    }
    int ☃ = 0;
    int ☃;
    while (☃ < 5)
    {
      ☃++;
      
      ☃ = ☃.nextInt(☃);
      for (awk.e ☃ : ☃.e)
      {
        ☃ -= ☃.b;
        if (☃ < 0)
        {
          if ((!☃.a(☃)) || ((☃ == ☃.d) && (☃.e.size() > 1))) {
            break;
          }
          awk.n ☃ = a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
          if (☃ != null)
          {
            ☃.c += 1;
            ☃.d = ☃;
            if (!☃.a()) {
              ☃.e.remove(☃);
            }
            return ☃;
          }
        }
      }
    }
    avp ☃ = awk.d.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    if (☃ != null) {
      return new awk.d(☃, ☃, ☃, ☃, ☃);
    }
    return null;
  }
  
  private static awg d(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    if (☃ > 50) {
      return null;
    }
    if ((Math.abs(☃ - ☃.c().a) > 112) || (Math.abs(☃ - ☃.c().c) > 112)) {
      return null;
    }
    awg ☃ = c(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃ + 1);
    if (☃ != null)
    {
      int ☃ = (☃.l.a + ☃.l.d) / 2;
      int ☃ = (☃.l.c + ☃.l.f) / 2;
      int ☃ = ☃.l.d - ☃.l.a;
      int ☃ = ☃.l.f - ☃.l.c;
      int ☃ = ☃ > ☃ ? ☃ : ☃;
      if (☃.h().a(☃, ☃, ☃ / 2 + 4, awj.a))
      {
        ☃.add(☃);
        ☃.f.add(☃);
        return ☃;
      }
    }
    return null;
  }
  
  private static awg e(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    if (☃ > 3 + ☃.c) {
      return null;
    }
    if ((Math.abs(☃ - ☃.c().a) > 112) || (Math.abs(☃ - ☃.c().c) > 112)) {
      return null;
    }
    avp ☃ = awk.l.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    if ((☃ != null) && (☃.b > 10))
    {
      awg ☃ = new awk.l(☃, ☃, ☃, ☃, ☃);
      int ☃ = (☃.l.a + ☃.l.d) / 2;
      int ☃ = (☃.l.c + ☃.l.f) / 2;
      int ☃ = ☃.l.d - ☃.l.a;
      int ☃ = ☃.l.f - ☃.l.c;
      int ☃ = ☃ > ☃ ? ☃ : ☃;
      if (☃.h().a(☃, ☃, ☃ / 2 + 4, awj.a))
      {
        ☃.add(☃);
        ☃.g.add(☃);
        return ☃;
      }
    }
    return null;
  }
  
  static abstract class n
    extends awg
  {
    protected int h = -1;
    private int a;
    private boolean b;
    
    public n() {}
    
    protected n(awk.k ☃, int ☃)
    {
      super();
      if (☃ != null) {
        this.b = ☃.b;
      }
    }
    
    protected void a(dn ☃)
    {
      ☃.a("HPos", this.h);
      ☃.a("VCount", this.a);
      ☃.a("Desert", this.b);
    }
    
    protected void b(dn ☃)
    {
      this.h = ☃.h("HPos");
      this.a = ☃.h("VCount");
      this.b = ☃.p("Desert");
    }
    
    protected awg a(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃)
    {
      cq ☃ = e();
      if (☃ != null) {
        switch (awk.1.a[☃.ordinal()])
        {
        case 1: 
          return awk.a(☃, ☃, ☃, this.l.a - 1, this.l.b + ☃, this.l.c + ☃, cq.e, d());
        case 2: 
          return awk.a(☃, ☃, ☃, this.l.a - 1, this.l.b + ☃, this.l.c + ☃, cq.e, d());
        case 3: 
          return awk.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.c - 1, cq.c, d());
        case 4: 
          return awk.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.c - 1, cq.c, d());
        }
      }
      return null;
    }
    
    protected awg b(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃)
    {
      cq ☃ = e();
      if (☃ != null) {
        switch (awk.1.a[☃.ordinal()])
        {
        case 1: 
          return awk.a(☃, ☃, ☃, this.l.d + 1, this.l.b + ☃, this.l.c + ☃, cq.f, d());
        case 2: 
          return awk.a(☃, ☃, ☃, this.l.d + 1, this.l.b + ☃, this.l.c + ☃, cq.f, d());
        case 3: 
          return awk.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.f + 1, cq.d, d());
        case 4: 
          return awk.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.f + 1, cq.d, d());
        }
      }
      return null;
    }
    
    protected int b(aht ☃, avp ☃)
    {
      int ☃ = 0;
      int ☃ = 0;
      cj.a ☃ = new cj.a();
      for (int ☃ = this.l.c; ☃ <= this.l.f; ☃++) {
        for (int ☃ = this.l.a; ☃ <= this.l.d; ☃++)
        {
          ☃.c(☃, 64, ☃);
          if (☃.b(☃))
          {
            ☃ += Math.max(☃.q(☃).q(), ☃.s.i());
            ☃++;
          }
        }
      }
      if (☃ == 0) {
        return -1;
      }
      return ☃ / ☃;
    }
    
    protected static boolean a(avp ☃)
    {
      return (☃ != null) && (☃.b > 10);
    }
    
    protected void a(aht ☃, avp ☃, int ☃, int ☃, int ☃, int ☃)
    {
      if (this.a >= ☃) {
        return;
      }
      for (int ☃ = this.a; ☃ < ☃; ☃++)
      {
        int ☃ = a(☃ + ☃, ☃);
        int ☃ = d(☃);
        int ☃ = b(☃ + ☃, ☃);
        if (!☃.b(new cj(☃, ☃, ☃))) {
          break;
        }
        this.a += 1;
        
        ze ☃ = new ze(☃);
        ☃.b(☃ + 0.5D, ☃, ☃ + 0.5D, 0.0F, 0.0F);
        ☃.a(☃.D(new cj(☃)), null);
        ☃.l(c(☃, ☃.cZ()));
        ☃.a(☃);
      }
    }
    
    protected int c(int ☃, int ☃)
    {
      return ☃;
    }
    
    protected arc a(arc ☃)
    {
      if (this.b)
      {
        if ((☃.t() == aju.r) || (☃.t() == aju.s)) {
          return aju.A.u();
        }
        if (☃.t() == aju.e) {
          return aju.A.a(aog.a.a.a());
        }
        if (☃.t() == aju.f) {
          return aju.A.a(aog.a.c.a());
        }
        if (☃.t() == aju.ad) {
          return aju.bO.u().a(aot.a, ☃.c(aot.a));
        }
        if (☃.t() == aju.aw) {
          return aju.bO.u().a(aot.a, ☃.c(aot.a));
        }
        if (☃.t() == aju.n) {
          return aju.A.u();
        }
      }
      return ☃;
    }
    
    protected void a(aht ☃, arc ☃, int ☃, int ☃, int ☃, avp ☃)
    {
      arc ☃ = a(☃);
      super.a(☃, ☃, ☃, ☃, ☃, ☃);
    }
    
    protected void a(aht ☃, avp ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, arc ☃, arc ☃, boolean ☃)
    {
      arc ☃ = a(☃);
      arc ☃ = a(☃);
      super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
    
    protected void b(aht ☃, arc ☃, int ☃, int ☃, int ☃, avp ☃)
    {
      arc ☃ = a(☃);
      super.b(☃, ☃, ☃, ☃, ☃, ☃);
    }
    
    protected void a(boolean ☃)
    {
      this.b = ☃;
    }
  }
  
  public static class p
    extends awk.n
  {
    public p() {}
    
    public p(awk.k ☃, int ☃, Random ☃, int ☃, int ☃)
    {
      super(☃);
      
      a(cq.c.a.a(☃));
      if (e().k() == cq.a.c) {
        this.l = new avp(☃, 64, ☃, ☃ + 6 - 1, 78, ☃ + 6 - 1);
      } else {
        this.l = new avp(☃, 64, ☃, ☃ + 6 - 1, 78, ☃ + 6 - 1);
      }
    }
    
    public void a(awg ☃, List<awg> ☃, Random ☃)
    {
      awk.b((awk.k)☃, ☃, ☃, this.l.a - 1, this.l.e - 4, this.l.c + 1, cq.e, d());
      awk.b((awk.k)☃, ☃, ☃, this.l.d + 1, this.l.e - 4, this.l.c + 1, cq.f, d());
      awk.b((awk.k)☃, ☃, ☃, this.l.a + 1, this.l.e - 4, this.l.c - 1, cq.c, d());
      awk.b((awk.k)☃, ☃, ☃, this.l.a + 1, this.l.e - 4, this.l.f + 1, cq.d, d());
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (this.h < 0)
      {
        this.h = b(☃, ☃);
        if (this.h < 0) {
          return true;
        }
        this.l.a(0, this.h - this.l.e + 3, 0);
      }
      a(☃, ☃, 1, 0, 1, 4, 12, 4, aju.e.u(), aju.i.u(), false);
      a(☃, aju.a.u(), 2, 12, 2, ☃);
      a(☃, aju.a.u(), 3, 12, 2, ☃);
      a(☃, aju.a.u(), 2, 12, 3, ☃);
      a(☃, aju.a.u(), 3, 12, 3, ☃);
      
      a(☃, aju.aO.u(), 1, 13, 1, ☃);
      a(☃, aju.aO.u(), 1, 14, 1, ☃);
      a(☃, aju.aO.u(), 4, 13, 1, ☃);
      a(☃, aju.aO.u(), 4, 14, 1, ☃);
      a(☃, aju.aO.u(), 1, 13, 4, ☃);
      a(☃, aju.aO.u(), 1, 14, 4, ☃);
      a(☃, aju.aO.u(), 4, 13, 4, ☃);
      a(☃, aju.aO.u(), 4, 14, 4, ☃);
      a(☃, ☃, 1, 15, 1, 4, 15, 4, aju.e.u(), aju.e.u(), false);
      for (int ☃ = 0; ☃ <= 5; ☃++) {
        for (int ☃ = 0; ☃ <= 5; ☃++) {
          if ((☃ == 0) || (☃ == 5) || (☃ == 0) || (☃ == 5))
          {
            a(☃, aju.n.u(), ☃, 11, ☃, ☃);
            b(☃, ☃, 12, ☃, ☃);
          }
        }
      }
      return true;
    }
  }
  
  public static class k
    extends awk.p
  {
    public aik a;
    public boolean b;
    public int c;
    public awk.e d;
    public List<awk.e> e;
    public List<awg> f = Lists.newArrayList();
    public List<awg> g = Lists.newArrayList();
    
    public k() {}
    
    public k(aik ☃, int ☃, Random ☃, int ☃, int ☃, List<awk.e> ☃, int ☃)
    {
      super(0, ☃, ☃, ☃);
      this.a = ☃;
      this.e = ☃;
      this.c = ☃;
      
      aig ☃ = ☃.a(new cj(☃, 0, ☃), ail.b);
      this.b = ((☃ == ail.d) || (☃ == ail.s));
      a(this.b);
    }
    
    public aik h()
    {
      return this.a;
    }
  }
  
  public static abstract class o
    extends awk.n
  {
    public o() {}
    
    protected o(awk.k ☃, int ☃)
    {
      super(☃);
    }
  }
  
  public static class l
    extends awk.o
  {
    private int a;
    
    public l() {}
    
    public l(awk.k ☃, int ☃, Random ☃, avp ☃, cq ☃)
    {
      super(☃);
      
      a(☃);
      this.l = ☃;
      this.a = Math.max(☃.c(), ☃.e());
    }
    
    protected void a(dn ☃)
    {
      super.a(☃);
      ☃.a("Length", this.a);
    }
    
    protected void b(dn ☃)
    {
      super.b(☃);
      this.a = ☃.h("Length");
    }
    
    public void a(awg ☃, List<awg> ☃, Random ☃)
    {
      boolean ☃ = false;
      
      int ☃ = ☃.nextInt(5);
      while (☃ < this.a - 8)
      {
        awg ☃ = a((awk.k)☃, ☃, ☃, 0, ☃);
        if (☃ != null)
        {
          ☃ += Math.max(☃.l.c(), ☃.l.e());
          ☃ = true;
        }
        ☃ += 2 + ☃.nextInt(5);
      }
      ☃ = ☃.nextInt(5);
      while (☃ < this.a - 8)
      {
        awg ☃ = b((awk.k)☃, ☃, ☃, 0, ☃);
        if (☃ != null)
        {
          ☃ += Math.max(☃.l.c(), ☃.l.e());
          ☃ = true;
        }
        ☃ += 2 + ☃.nextInt(5);
      }
      cq ☃ = e();
      if ((☃) && (☃.nextInt(3) > 0) && (☃ != null)) {
        switch (awk.1.a[☃.ordinal()])
        {
        case 1: 
          awk.b((awk.k)☃, ☃, ☃, this.l.a - 1, this.l.b, this.l.c, cq.e, d());
          break;
        case 2: 
          awk.b((awk.k)☃, ☃, ☃, this.l.a - 1, this.l.b, this.l.f - 2, cq.e, d());
          break;
        case 4: 
          awk.b((awk.k)☃, ☃, ☃, this.l.d - 2, this.l.b, this.l.c - 1, cq.c, d());
          break;
        case 3: 
          awk.b((awk.k)☃, ☃, ☃, this.l.a, this.l.b, this.l.c - 1, cq.c, d());
        }
      }
      if ((☃) && (☃.nextInt(3) > 0) && (☃ != null)) {
        switch (awk.1.a[☃.ordinal()])
        {
        case 1: 
          awk.b((awk.k)☃, ☃, ☃, this.l.d + 1, this.l.b, this.l.c, cq.f, d());
          break;
        case 2: 
          awk.b((awk.k)☃, ☃, ☃, this.l.d + 1, this.l.b, this.l.f - 2, cq.f, d());
          break;
        case 4: 
          awk.b((awk.k)☃, ☃, ☃, this.l.d - 2, this.l.b, this.l.f + 1, cq.d, d());
          break;
        case 3: 
          awk.b((awk.k)☃, ☃, ☃, this.l.a, this.l.b, this.l.f + 1, cq.d, d());
        }
      }
    }
    
    public static avp a(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃)
    {
      int ☃ = 7 * on.a(☃, 3, 5);
      while (☃ >= 7)
      {
        avp ☃ = avp.a(☃, ☃, ☃, 0, 0, 0, 3, 3, ☃, ☃);
        if (awg.a(☃, ☃) == null) {
          return ☃;
        }
        ☃ -= 7;
      }
      return null;
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      arc ☃ = a(aju.n.u());
      arc ☃ = a(aju.e.u());
      for (int ☃ = this.l.a; ☃ <= this.l.d; ☃++) {
        for (int ☃ = this.l.c; ☃ <= this.l.f; ☃++)
        {
          cj ☃ = new cj(☃, 64, ☃);
          if (☃.b(☃))
          {
            ☃ = ☃.q(☃).b();
            ☃.a(☃, ☃, 2);
            ☃.a(☃.b(), ☃, 2);
          }
        }
      }
      return true;
    }
  }
  
  public static class g
    extends awk.n
  {
    private boolean a;
    
    public g() {}
    
    public g(awk.k ☃, int ☃, Random ☃, avp ☃, cq ☃)
    {
      super(☃);
      
      a(☃);
      this.l = ☃;
      this.a = ☃.nextBoolean();
    }
    
    protected void a(dn ☃)
    {
      super.a(☃);
      ☃.a("Terrace", this.a);
    }
    
    protected void b(dn ☃)
    {
      super.b(☃);
      this.a = ☃.p("Terrace");
    }
    
    public static g a(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, 0, 0, 0, 5, 6, 5, ☃);
      if (awg.a(☃, ☃) != null) {
        return null;
      }
      return new g(☃, ☃, ☃, ☃, ☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (this.h < 0)
      {
        this.h = b(☃, ☃);
        if (this.h < 0) {
          return true;
        }
        this.l.a(0, this.h - this.l.e + 6 - 1, 0);
      }
      a(☃, ☃, 0, 0, 0, 4, 0, 4, aju.e.u(), aju.e.u(), false);
      
      a(☃, ☃, 0, 4, 0, 4, 4, 4, aju.r.u(), aju.r.u(), false);
      a(☃, ☃, 1, 4, 1, 3, 4, 3, aju.f.u(), aju.f.u(), false);
      
      a(☃, aju.e.u(), 0, 1, 0, ☃);
      a(☃, aju.e.u(), 0, 2, 0, ☃);
      a(☃, aju.e.u(), 0, 3, 0, ☃);
      a(☃, aju.e.u(), 4, 1, 0, ☃);
      a(☃, aju.e.u(), 4, 2, 0, ☃);
      a(☃, aju.e.u(), 4, 3, 0, ☃);
      a(☃, aju.e.u(), 0, 1, 4, ☃);
      a(☃, aju.e.u(), 0, 2, 4, ☃);
      a(☃, aju.e.u(), 0, 3, 4, ☃);
      a(☃, aju.e.u(), 4, 1, 4, ☃);
      a(☃, aju.e.u(), 4, 2, 4, ☃);
      a(☃, aju.e.u(), 4, 3, 4, ☃);
      a(☃, ☃, 0, 1, 1, 0, 3, 3, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 4, 1, 1, 4, 3, 3, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 1, 1, 4, 3, 3, 4, aju.f.u(), aju.f.u(), false);
      a(☃, aju.bj.u(), 0, 2, 2, ☃);
      a(☃, aju.bj.u(), 2, 2, 4, ☃);
      a(☃, aju.bj.u(), 4, 2, 2, ☃);
      
      a(☃, aju.f.u(), 1, 1, 0, ☃);
      a(☃, aju.f.u(), 1, 2, 0, ☃);
      a(☃, aju.f.u(), 1, 3, 0, ☃);
      a(☃, aju.f.u(), 2, 3, 0, ☃);
      a(☃, aju.f.u(), 3, 3, 0, ☃);
      a(☃, aju.f.u(), 3, 2, 0, ☃);
      a(☃, aju.f.u(), 3, 1, 0, ☃);
      if ((a(☃, 2, 0, -1, ☃).a() == axe.a) && (a(☃, 2, -1, -1, ☃).a() != axe.a)) {
        a(☃, aju.aw.u().a(aot.a, cq.c), 2, 0, -1, ☃);
      }
      a(☃, ☃, 1, 1, 1, 3, 3, 3, aju.a.u(), aju.a.u(), false);
      if (this.a)
      {
        a(☃, aju.aO.u(), 0, 5, 0, ☃);
        a(☃, aju.aO.u(), 1, 5, 0, ☃);
        a(☃, aju.aO.u(), 2, 5, 0, ☃);
        a(☃, aju.aO.u(), 3, 5, 0, ☃);
        a(☃, aju.aO.u(), 4, 5, 0, ☃);
        a(☃, aju.aO.u(), 0, 5, 4, ☃);
        a(☃, aju.aO.u(), 1, 5, 4, ☃);
        a(☃, aju.aO.u(), 2, 5, 4, ☃);
        a(☃, aju.aO.u(), 3, 5, 4, ☃);
        a(☃, aju.aO.u(), 4, 5, 4, ☃);
        a(☃, aju.aO.u(), 4, 5, 1, ☃);
        a(☃, aju.aO.u(), 4, 5, 2, ☃);
        a(☃, aju.aO.u(), 4, 5, 3, ☃);
        a(☃, aju.aO.u(), 0, 5, 1, ☃);
        a(☃, aju.aO.u(), 0, 5, 2, ☃);
        a(☃, aju.aO.u(), 0, 5, 3, ☃);
      }
      if (this.a)
      {
        arc ☃ = aju.au.u().a(amk.a, cq.d);
        a(☃, ☃, 3, 1, 3, ☃);
        a(☃, ☃, 3, 2, 3, ☃);
        a(☃, ☃, 3, 3, 3, ☃);
        a(☃, ☃, 3, 4, 3, ☃);
      }
      a(☃, aju.aa.u().a(apf.a, cq.c), 2, 3, 1, ☃);
      for (int ☃ = 0; ☃ < 5; ☃++) {
        for (int ☃ = 0; ☃ < 5; ☃++)
        {
          b(☃, ☃, 6, ☃, ☃);
          b(☃, aju.e.u(), ☃, -1, ☃, ☃);
        }
      }
      a(☃, ☃, 1, 1, 2, 1);
      
      return true;
    }
  }
  
  public static class i
    extends awk.n
  {
    public i() {}
    
    public i(awk.k ☃, int ☃, Random ☃, avp ☃, cq ☃)
    {
      super(☃);
      
      a(☃);
      this.l = ☃;
    }
    
    public static i a(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, 0, 0, 0, 5, 12, 9, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
        return null;
      }
      return new i(☃, ☃, ☃, ☃, ☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (this.h < 0)
      {
        this.h = b(☃, ☃);
        if (this.h < 0) {
          return true;
        }
        this.l.a(0, this.h - this.l.e + 12 - 1, 0);
      }
      a(☃, ☃, 1, 1, 1, 3, 3, 7, aju.a.u(), aju.a.u(), false);
      a(☃, ☃, 1, 5, 1, 3, 9, 3, aju.a.u(), aju.a.u(), false);
      
      a(☃, ☃, 1, 0, 0, 3, 0, 8, aju.e.u(), aju.e.u(), false);
      
      a(☃, ☃, 1, 1, 0, 3, 10, 0, aju.e.u(), aju.e.u(), false);
      
      a(☃, ☃, 0, 1, 1, 0, 10, 3, aju.e.u(), aju.e.u(), false);
      
      a(☃, ☃, 4, 1, 1, 4, 10, 3, aju.e.u(), aju.e.u(), false);
      
      a(☃, ☃, 0, 0, 4, 0, 4, 7, aju.e.u(), aju.e.u(), false);
      
      a(☃, ☃, 4, 0, 4, 4, 4, 7, aju.e.u(), aju.e.u(), false);
      
      a(☃, ☃, 1, 1, 8, 3, 4, 8, aju.e.u(), aju.e.u(), false);
      
      a(☃, ☃, 1, 5, 4, 3, 10, 4, aju.e.u(), aju.e.u(), false);
      
      a(☃, ☃, 1, 5, 5, 3, 5, 7, aju.e.u(), aju.e.u(), false);
      
      a(☃, ☃, 0, 9, 0, 4, 9, 4, aju.e.u(), aju.e.u(), false);
      
      a(☃, ☃, 0, 4, 0, 4, 4, 4, aju.e.u(), aju.e.u(), false);
      a(☃, aju.e.u(), 0, 11, 2, ☃);
      a(☃, aju.e.u(), 4, 11, 2, ☃);
      a(☃, aju.e.u(), 2, 11, 0, ☃);
      a(☃, aju.e.u(), 2, 11, 4, ☃);
      
      a(☃, aju.e.u(), 1, 1, 6, ☃);
      a(☃, aju.e.u(), 1, 1, 7, ☃);
      a(☃, aju.e.u(), 2, 1, 7, ☃);
      a(☃, aju.e.u(), 3, 1, 6, ☃);
      a(☃, aju.e.u(), 3, 1, 7, ☃);
      
      arc ☃ = aju.aw.u().a(aot.a, cq.c);
      arc ☃ = aju.aw.u().a(aot.a, cq.e);
      arc ☃ = aju.aw.u().a(aot.a, cq.f);
      a(☃, ☃, 1, 1, 5, ☃);
      a(☃, ☃, 2, 1, 6, ☃);
      a(☃, ☃, 3, 1, 5, ☃);
      a(☃, ☃, 1, 2, 7, ☃);
      a(☃, ☃, 3, 2, 7, ☃);
      
      a(☃, aju.bj.u(), 0, 2, 2, ☃);
      a(☃, aju.bj.u(), 0, 3, 2, ☃);
      a(☃, aju.bj.u(), 4, 2, 2, ☃);
      a(☃, aju.bj.u(), 4, 3, 2, ☃);
      a(☃, aju.bj.u(), 0, 6, 2, ☃);
      a(☃, aju.bj.u(), 0, 7, 2, ☃);
      a(☃, aju.bj.u(), 4, 6, 2, ☃);
      a(☃, aju.bj.u(), 4, 7, 2, ☃);
      a(☃, aju.bj.u(), 2, 6, 0, ☃);
      a(☃, aju.bj.u(), 2, 7, 0, ☃);
      a(☃, aju.bj.u(), 2, 6, 4, ☃);
      a(☃, aju.bj.u(), 2, 7, 4, ☃);
      a(☃, aju.bj.u(), 0, 3, 6, ☃);
      a(☃, aju.bj.u(), 4, 3, 6, ☃);
      a(☃, aju.bj.u(), 2, 3, 8, ☃);
      
      a(☃, aju.aa.u().a(apf.a, cq.d), 2, 4, 7, ☃);
      a(☃, aju.aa.u().a(apf.a, cq.f), 1, 4, 6, ☃);
      a(☃, aju.aa.u().a(apf.a, cq.e), 3, 4, 6, ☃);
      a(☃, aju.aa.u().a(apf.a, cq.c), 2, 4, 5, ☃);
      
      arc ☃ = aju.au.u().a(amk.a, cq.e);
      for (int ☃ = 1; ☃ <= 9; ☃++) {
        a(☃, ☃, 3, ☃, 3, ☃);
      }
      a(☃, aju.a.u(), 2, 1, 0, ☃);
      a(☃, aju.a.u(), 2, 2, 0, ☃);
      a(☃, ☃, ☃, 2, 1, 0, cq.c);
      if ((a(☃, 2, 0, -1, ☃).a() == axe.a) && (a(☃, 2, -1, -1, ☃).a() != axe.a)) {
        a(☃, ☃, 2, 0, -1, ☃);
      }
      for (int ☃ = 0; ☃ < 9; ☃++) {
        for (int ☃ = 0; ☃ < 5; ☃++)
        {
          b(☃, ☃, 12, ☃, ☃);
          b(☃, aju.e.u(), ☃, -1, ☃, ☃);
        }
      }
      a(☃, ☃, 2, 1, 2, 1);
      
      return true;
    }
    
    protected int c(int ☃, int ☃)
    {
      return 2;
    }
  }
  
  public static class a
    extends awk.n
  {
    public a() {}
    
    public a(awk.k ☃, int ☃, Random ☃, avp ☃, cq ☃)
    {
      super(☃);
      
      a(☃);
      this.l = ☃;
    }
    
    public static a a(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, 0, 0, 0, 9, 9, 6, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
        return null;
      }
      return new a(☃, ☃, ☃, ☃, ☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (this.h < 0)
      {
        this.h = b(☃, ☃);
        if (this.h < 0) {
          return true;
        }
        this.l.a(0, this.h - this.l.e + 9 - 1, 0);
      }
      a(☃, ☃, 1, 1, 1, 7, 5, 4, aju.a.u(), aju.a.u(), false);
      
      a(☃, ☃, 0, 0, 0, 8, 0, 5, aju.e.u(), aju.e.u(), false);
      
      a(☃, ☃, 0, 5, 0, 8, 5, 5, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 0, 6, 1, 8, 6, 4, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 0, 7, 2, 8, 7, 3, aju.e.u(), aju.e.u(), false);
      for (int ☃ = -1; ☃ <= 2; ☃++) {
        for (int ☃ = 0; ☃ <= 8; ☃++)
        {
          a(☃, aju.ad.u().a(aot.a, cq.c), ☃, 6 + ☃, ☃, ☃);
          a(☃, aju.ad.u().a(aot.a, cq.d), ☃, 6 + ☃, 5 - ☃, ☃);
        }
      }
      a(☃, ☃, 0, 1, 0, 0, 1, 5, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 1, 1, 5, 8, 1, 5, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 8, 1, 0, 8, 1, 4, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 2, 1, 0, 7, 1, 0, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 0, 2, 0, 0, 4, 0, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 0, 2, 5, 0, 4, 5, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 8, 2, 5, 8, 4, 5, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 8, 2, 0, 8, 4, 0, aju.e.u(), aju.e.u(), false);
      
      a(☃, ☃, 0, 2, 1, 0, 4, 4, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 1, 2, 5, 7, 4, 5, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 8, 2, 1, 8, 4, 4, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 1, 2, 0, 7, 4, 0, aju.f.u(), aju.f.u(), false);
      
      a(☃, aju.bj.u(), 4, 2, 0, ☃);
      a(☃, aju.bj.u(), 5, 2, 0, ☃);
      a(☃, aju.bj.u(), 6, 2, 0, ☃);
      a(☃, aju.bj.u(), 4, 3, 0, ☃);
      a(☃, aju.bj.u(), 5, 3, 0, ☃);
      a(☃, aju.bj.u(), 6, 3, 0, ☃);
      a(☃, aju.bj.u(), 0, 2, 2, ☃);
      a(☃, aju.bj.u(), 0, 2, 3, ☃);
      a(☃, aju.bj.u(), 0, 3, 2, ☃);
      a(☃, aju.bj.u(), 0, 3, 3, ☃);
      a(☃, aju.bj.u(), 8, 2, 2, ☃);
      a(☃, aju.bj.u(), 8, 2, 3, ☃);
      a(☃, aju.bj.u(), 8, 3, 2, ☃);
      a(☃, aju.bj.u(), 8, 3, 3, ☃);
      a(☃, aju.bj.u(), 2, 2, 5, ☃);
      a(☃, aju.bj.u(), 3, 2, 5, ☃);
      a(☃, aju.bj.u(), 5, 2, 5, ☃);
      a(☃, aju.bj.u(), 6, 2, 5, ☃);
      
      a(☃, ☃, 1, 4, 1, 7, 4, 1, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 1, 4, 4, 7, 4, 4, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 1, 3, 4, 7, 3, 4, aju.X.u(), aju.X.u(), false);
      
      a(☃, aju.f.u(), 7, 1, 4, ☃);
      a(☃, aju.ad.u().a(aot.a, cq.f), 7, 1, 3, ☃);
      arc ☃ = aju.ad.u().a(aot.a, cq.c);
      a(☃, ☃, 6, 1, 4, ☃);
      a(☃, ☃, 5, 1, 4, ☃);
      a(☃, ☃, 4, 1, 4, ☃);
      a(☃, ☃, 3, 1, 4, ☃);
      
      a(☃, aju.aO.u(), 6, 1, 3, ☃);
      a(☃, aju.aB.u(), 6, 2, 3, ☃);
      a(☃, aju.aO.u(), 4, 1, 3, ☃);
      a(☃, aju.aB.u(), 4, 2, 3, ☃);
      a(☃, aju.ai.u(), 7, 1, 1, ☃);
      
      a(☃, aju.a.u(), 1, 1, 0, ☃);
      a(☃, aju.a.u(), 1, 2, 0, ☃);
      a(☃, ☃, ☃, 1, 1, 0, cq.c);
      if ((a(☃, 1, 0, -1, ☃).a() == axe.a) && (a(☃, 1, -1, -1, ☃).a() != axe.a)) {
        a(☃, aju.aw.u().a(aot.a, cq.c), 1, 0, -1, ☃);
      }
      for (int ☃ = 0; ☃ < 6; ☃++) {
        for (int ☃ = 0; ☃ < 9; ☃++)
        {
          b(☃, ☃, 9, ☃, ☃);
          b(☃, aju.e.u(), ☃, -1, ☃, ☃);
        }
      }
      a(☃, ☃, 2, 1, 2, 1);
      
      return true;
    }
    
    protected int c(int ☃, int ☃)
    {
      return 1;
    }
  }
  
  public static class h
    extends awk.n
  {
    private boolean a;
    private int b;
    
    public h() {}
    
    public h(awk.k ☃, int ☃, Random ☃, avp ☃, cq ☃)
    {
      super(☃);
      
      a(☃);
      this.l = ☃;
      this.a = ☃.nextBoolean();
      this.b = ☃.nextInt(3);
    }
    
    protected void a(dn ☃)
    {
      super.a(☃);
      ☃.a("T", this.b);
      ☃.a("C", this.a);
    }
    
    protected void b(dn ☃)
    {
      super.b(☃);
      this.b = ☃.h("T");
      this.a = ☃.p("C");
    }
    
    public static h a(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, 0, 0, 0, 4, 6, 5, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
        return null;
      }
      return new h(☃, ☃, ☃, ☃, ☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (this.h < 0)
      {
        this.h = b(☃, ☃);
        if (this.h < 0) {
          return true;
        }
        this.l.a(0, this.h - this.l.e + 6 - 1, 0);
      }
      a(☃, ☃, 1, 1, 1, 3, 5, 4, aju.a.u(), aju.a.u(), false);
      
      a(☃, ☃, 0, 0, 0, 3, 0, 4, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 1, 0, 1, 2, 0, 3, aju.d.u(), aju.d.u(), false);
      if (this.a) {
        a(☃, ☃, 1, 4, 1, 2, 4, 3, aju.r.u(), aju.r.u(), false);
      } else {
        a(☃, ☃, 1, 5, 1, 2, 5, 3, aju.r.u(), aju.r.u(), false);
      }
      a(☃, aju.r.u(), 1, 4, 0, ☃);
      a(☃, aju.r.u(), 2, 4, 0, ☃);
      a(☃, aju.r.u(), 1, 4, 4, ☃);
      a(☃, aju.r.u(), 2, 4, 4, ☃);
      a(☃, aju.r.u(), 0, 4, 1, ☃);
      a(☃, aju.r.u(), 0, 4, 2, ☃);
      a(☃, aju.r.u(), 0, 4, 3, ☃);
      a(☃, aju.r.u(), 3, 4, 1, ☃);
      a(☃, aju.r.u(), 3, 4, 2, ☃);
      a(☃, aju.r.u(), 3, 4, 3, ☃);
      
      a(☃, ☃, 0, 1, 0, 0, 3, 0, aju.r.u(), aju.r.u(), false);
      a(☃, ☃, 3, 1, 0, 3, 3, 0, aju.r.u(), aju.r.u(), false);
      a(☃, ☃, 0, 1, 4, 0, 3, 4, aju.r.u(), aju.r.u(), false);
      a(☃, ☃, 3, 1, 4, 3, 3, 4, aju.r.u(), aju.r.u(), false);
      
      a(☃, ☃, 0, 1, 1, 0, 3, 3, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 3, 1, 1, 3, 3, 3, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 1, 1, 0, 2, 3, 0, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 1, 1, 4, 2, 3, 4, aju.f.u(), aju.f.u(), false);
      
      a(☃, aju.bj.u(), 0, 2, 2, ☃);
      a(☃, aju.bj.u(), 3, 2, 2, ☃);
      if (this.b > 0)
      {
        a(☃, aju.aO.u(), this.b, 1, 3, ☃);
        a(☃, aju.aB.u(), this.b, 2, 3, ☃);
      }
      a(☃, aju.a.u(), 1, 1, 0, ☃);
      a(☃, aju.a.u(), 1, 2, 0, ☃);
      a(☃, ☃, ☃, 1, 1, 0, cq.c);
      if ((a(☃, 1, 0, -1, ☃).a() == axe.a) && (a(☃, 1, -1, -1, ☃).a() != axe.a)) {
        a(☃, aju.aw.u().a(aot.a, cq.c), 1, 0, -1, ☃);
      }
      for (int ☃ = 0; ☃ < 5; ☃++) {
        for (int ☃ = 0; ☃ < 4; ☃++)
        {
          b(☃, ☃, 6, ☃, ☃);
          b(☃, aju.e.u(), ☃, -1, ☃, ☃);
        }
      }
      a(☃, ☃, 1, 1, 2, 1);
      
      return true;
    }
  }
  
  public static class f
    extends awk.n
  {
    public f() {}
    
    public f(awk.k ☃, int ☃, Random ☃, avp ☃, cq ☃)
    {
      super(☃);
      
      a(☃);
      this.l = ☃;
    }
    
    public static f a(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, 0, 0, 0, 9, 7, 11, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
        return null;
      }
      return new f(☃, ☃, ☃, ☃, ☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (this.h < 0)
      {
        this.h = b(☃, ☃);
        if (this.h < 0) {
          return true;
        }
        this.l.a(0, this.h - this.l.e + 7 - 1, 0);
      }
      a(☃, ☃, 1, 1, 1, 7, 4, 4, aju.a.u(), aju.a.u(), false);
      a(☃, ☃, 2, 1, 6, 8, 4, 10, aju.a.u(), aju.a.u(), false);
      
      a(☃, ☃, 2, 0, 6, 8, 0, 10, aju.d.u(), aju.d.u(), false);
      a(☃, aju.e.u(), 6, 0, 6, ☃);
      
      a(☃, ☃, 2, 1, 6, 2, 1, 10, aju.aO.u(), aju.aO.u(), false);
      a(☃, ☃, 8, 1, 6, 8, 1, 10, aju.aO.u(), aju.aO.u(), false);
      a(☃, ☃, 3, 1, 10, 7, 1, 10, aju.aO.u(), aju.aO.u(), false);
      
      a(☃, ☃, 1, 0, 1, 7, 0, 4, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 0, 0, 0, 0, 3, 5, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 8, 0, 0, 8, 3, 5, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 1, 0, 0, 7, 1, 0, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 1, 0, 5, 7, 1, 5, aju.e.u(), aju.e.u(), false);
      
      a(☃, ☃, 1, 2, 0, 7, 3, 0, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 1, 2, 5, 7, 3, 5, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 0, 4, 1, 8, 4, 1, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 0, 4, 4, 8, 4, 4, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 0, 5, 2, 8, 5, 3, aju.f.u(), aju.f.u(), false);
      a(☃, aju.f.u(), 0, 4, 2, ☃);
      a(☃, aju.f.u(), 0, 4, 3, ☃);
      a(☃, aju.f.u(), 8, 4, 2, ☃);
      a(☃, aju.f.u(), 8, 4, 3, ☃);
      
      arc ☃ = aju.ad.u().a(aot.a, cq.c);
      arc ☃ = aju.ad.u().a(aot.a, cq.d);
      arc ☃ = aju.ad.u().a(aot.a, cq.e);
      for (int ☃ = -1; ☃ <= 2; ☃++) {
        for (int ☃ = 0; ☃ <= 8; ☃++)
        {
          a(☃, ☃, ☃, 4 + ☃, ☃, ☃);
          a(☃, ☃, ☃, 4 + ☃, 5 - ☃, ☃);
        }
      }
      a(☃, aju.r.u(), 0, 2, 1, ☃);
      a(☃, aju.r.u(), 0, 2, 4, ☃);
      a(☃, aju.r.u(), 8, 2, 1, ☃);
      a(☃, aju.r.u(), 8, 2, 4, ☃);
      a(☃, aju.bj.u(), 0, 2, 2, ☃);
      a(☃, aju.bj.u(), 0, 2, 3, ☃);
      a(☃, aju.bj.u(), 8, 2, 2, ☃);
      a(☃, aju.bj.u(), 8, 2, 3, ☃);
      a(☃, aju.bj.u(), 2, 2, 5, ☃);
      a(☃, aju.bj.u(), 3, 2, 5, ☃);
      a(☃, aju.bj.u(), 5, 2, 0, ☃);
      a(☃, aju.bj.u(), 6, 2, 5, ☃);
      
      a(☃, aju.aO.u(), 2, 1, 3, ☃);
      a(☃, aju.aB.u(), 2, 2, 3, ☃);
      a(☃, aju.f.u(), 1, 1, 4, ☃);
      a(☃, ☃, 2, 1, 4, ☃);
      a(☃, ☃, 1, 1, 3, ☃);
      
      a(☃, ☃, 5, 0, 1, 7, 0, 3, aju.T.u(), aju.T.u(), false);
      a(☃, aju.T.u(), 6, 1, 1, ☃);
      a(☃, aju.T.u(), 6, 1, 2, ☃);
      
      a(☃, aju.a.u(), 2, 1, 0, ☃);
      a(☃, aju.a.u(), 2, 2, 0, ☃);
      a(☃, aju.aa.u().a(apf.a, cq.c), 2, 3, 1, ☃);
      a(☃, ☃, ☃, 2, 1, 0, cq.c);
      if ((a(☃, 2, 0, -1, ☃).a() == axe.a) && (a(☃, 2, -1, -1, ☃).a() != axe.a)) {
        a(☃, ☃, 2, 0, -1, ☃);
      }
      a(☃, aju.a.u(), 6, 1, 5, ☃);
      a(☃, aju.a.u(), 6, 2, 5, ☃);
      a(☃, aju.aa.u().a(apf.a, cq.d), 6, 3, 4, ☃);
      a(☃, ☃, ☃, 6, 1, 5, cq.d);
      for (int ☃ = 0; ☃ < 5; ☃++) {
        for (int ☃ = 0; ☃ < 9; ☃++)
        {
          b(☃, ☃, 7, ☃, ☃);
          b(☃, aju.e.u(), ☃, -1, ☃, ☃);
        }
      }
      a(☃, ☃, 4, 1, 2, 2);
      
      return true;
    }
    
    protected int c(int ☃, int ☃)
    {
      if (☃ == 0) {
        return 4;
      }
      return super.c(☃, ☃);
    }
  }
  
  public static class m
    extends awk.n
  {
    public m() {}
    
    public m(awk.k ☃, int ☃, Random ☃, avp ☃, cq ☃)
    {
      super(☃);
      
      a(☃);
      this.l = ☃;
    }
    
    public static m a(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, 0, 0, 0, 9, 7, 12, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
        return null;
      }
      return new m(☃, ☃, ☃, ☃, ☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (this.h < 0)
      {
        this.h = b(☃, ☃);
        if (this.h < 0) {
          return true;
        }
        this.l.a(0, this.h - this.l.e + 7 - 1, 0);
      }
      a(☃, ☃, 1, 1, 1, 7, 4, 4, aju.a.u(), aju.a.u(), false);
      a(☃, ☃, 2, 1, 6, 8, 4, 10, aju.a.u(), aju.a.u(), false);
      
      a(☃, ☃, 2, 0, 5, 8, 0, 10, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 1, 0, 1, 7, 0, 4, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 0, 0, 0, 0, 3, 5, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 8, 0, 0, 8, 3, 10, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 1, 0, 0, 7, 2, 0, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 1, 0, 5, 2, 1, 5, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 2, 0, 6, 2, 3, 10, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 3, 0, 10, 7, 3, 10, aju.e.u(), aju.e.u(), false);
      
      a(☃, ☃, 1, 2, 0, 7, 3, 0, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 1, 2, 5, 2, 3, 5, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 0, 4, 1, 8, 4, 1, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 0, 4, 4, 3, 4, 4, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 0, 5, 2, 8, 5, 3, aju.f.u(), aju.f.u(), false);
      a(☃, aju.f.u(), 0, 4, 2, ☃);
      a(☃, aju.f.u(), 0, 4, 3, ☃);
      a(☃, aju.f.u(), 8, 4, 2, ☃);
      a(☃, aju.f.u(), 8, 4, 3, ☃);
      a(☃, aju.f.u(), 8, 4, 4, ☃);
      
      arc ☃ = aju.ad.u().a(aot.a, cq.c);
      arc ☃ = aju.ad.u().a(aot.a, cq.d);
      arc ☃ = aju.ad.u().a(aot.a, cq.e);
      arc ☃ = aju.ad.u().a(aot.a, cq.f);
      for (int ☃ = -1; ☃ <= 2; ☃++) {
        for (int ☃ = 0; ☃ <= 8; ☃++)
        {
          a(☃, ☃, ☃, 4 + ☃, ☃, ☃);
          if (((☃ > -1) || (☃ <= 1)) && ((☃ > 0) || (☃ <= 3)) && ((☃ > 1) || (☃ <= 4) || (☃ >= 6))) {
            a(☃, ☃, ☃, 4 + ☃, 5 - ☃, ☃);
          }
        }
      }
      a(☃, ☃, 3, 4, 5, 3, 4, 10, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 7, 4, 2, 7, 4, 10, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 4, 5, 4, 4, 5, 10, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 6, 5, 4, 6, 5, 10, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 5, 6, 3, 5, 6, 10, aju.f.u(), aju.f.u(), false);
      for (int ☃ = 4; ☃ >= 1; ☃--)
      {
        a(☃, aju.f.u(), ☃, 2 + ☃, 7 - ☃, ☃);
        for (int ☃ = 8 - ☃; ☃ <= 10; ☃++) {
          a(☃, ☃, ☃, 2 + ☃, ☃, ☃);
        }
      }
      a(☃, aju.f.u(), 6, 6, 3, ☃);
      a(☃, aju.f.u(), 7, 5, 4, ☃);
      a(☃, ☃, 6, 6, 4, ☃);
      for (int ☃ = 6; ☃ <= 8; ☃++) {
        for (int ☃ = 5; ☃ <= 10; ☃++) {
          a(☃, ☃, ☃, 12 - ☃, ☃, ☃);
        }
      }
      a(☃, aju.r.u(), 0, 2, 1, ☃);
      a(☃, aju.r.u(), 0, 2, 4, ☃);
      a(☃, aju.bj.u(), 0, 2, 2, ☃);
      a(☃, aju.bj.u(), 0, 2, 3, ☃);
      
      a(☃, aju.r.u(), 4, 2, 0, ☃);
      a(☃, aju.bj.u(), 5, 2, 0, ☃);
      a(☃, aju.r.u(), 6, 2, 0, ☃);
      
      a(☃, aju.r.u(), 8, 2, 1, ☃);
      a(☃, aju.bj.u(), 8, 2, 2, ☃);
      a(☃, aju.bj.u(), 8, 2, 3, ☃);
      a(☃, aju.r.u(), 8, 2, 4, ☃);
      a(☃, aju.f.u(), 8, 2, 5, ☃);
      a(☃, aju.r.u(), 8, 2, 6, ☃);
      a(☃, aju.bj.u(), 8, 2, 7, ☃);
      a(☃, aju.bj.u(), 8, 2, 8, ☃);
      a(☃, aju.r.u(), 8, 2, 9, ☃);
      a(☃, aju.r.u(), 2, 2, 6, ☃);
      a(☃, aju.bj.u(), 2, 2, 7, ☃);
      a(☃, aju.bj.u(), 2, 2, 8, ☃);
      a(☃, aju.r.u(), 2, 2, 9, ☃);
      
      a(☃, aju.r.u(), 4, 4, 10, ☃);
      a(☃, aju.bj.u(), 5, 4, 10, ☃);
      a(☃, aju.r.u(), 6, 4, 10, ☃);
      a(☃, aju.f.u(), 5, 5, 10, ☃);
      
      a(☃, aju.a.u(), 2, 1, 0, ☃);
      a(☃, aju.a.u(), 2, 2, 0, ☃);
      a(☃, aju.aa.u().a(apf.a, cq.c), 2, 3, 1, ☃);
      a(☃, ☃, ☃, 2, 1, 0, cq.c);
      a(☃, ☃, 1, 0, -1, 3, 2, -1, aju.a.u(), aju.a.u(), false);
      if ((a(☃, 2, 0, -1, ☃).a() == axe.a) && (a(☃, 2, -1, -1, ☃).a() != axe.a)) {
        a(☃, ☃, 2, 0, -1, ☃);
      }
      for (int ☃ = 0; ☃ < 5; ☃++) {
        for (int ☃ = 0; ☃ < 9; ☃++)
        {
          b(☃, ☃, 7, ☃, ☃);
          b(☃, aju.e.u(), ☃, -1, ☃, ☃);
        }
      }
      for (int ☃ = 5; ☃ < 11; ☃++) {
        for (int ☃ = 2; ☃ < 9; ☃++)
        {
          b(☃, ☃, 7, ☃, ☃);
          b(☃, aju.e.u(), ☃, -1, ☃, ☃);
        }
      }
      a(☃, ☃, 4, 1, 2, 2);
      
      return true;
    }
  }
  
  public static class j
    extends awk.n
  {
    private boolean a;
    
    public j() {}
    
    public j(awk.k ☃, int ☃, Random ☃, avp ☃, cq ☃)
    {
      super(☃);
      
      a(☃);
      this.l = ☃;
    }
    
    public static j a(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, 0, 0, 0, 10, 6, 7, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
        return null;
      }
      return new j(☃, ☃, ☃, ☃, ☃);
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
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (this.h < 0)
      {
        this.h = b(☃, ☃);
        if (this.h < 0) {
          return true;
        }
        this.l.a(0, this.h - this.l.e + 6 - 1, 0);
      }
      a(☃, ☃, 0, 1, 0, 9, 4, 6, aju.a.u(), aju.a.u(), false);
      
      a(☃, ☃, 0, 0, 0, 9, 0, 6, aju.e.u(), aju.e.u(), false);
      
      a(☃, ☃, 0, 4, 0, 9, 4, 6, aju.e.u(), aju.e.u(), false);
      a(☃, ☃, 0, 5, 0, 9, 5, 6, aju.U.u(), aju.U.u(), false);
      a(☃, ☃, 1, 5, 1, 8, 5, 5, aju.a.u(), aju.a.u(), false);
      
      a(☃, ☃, 1, 1, 0, 2, 3, 0, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 0, 1, 0, 0, 4, 0, aju.r.u(), aju.r.u(), false);
      a(☃, ☃, 3, 1, 0, 3, 4, 0, aju.r.u(), aju.r.u(), false);
      a(☃, ☃, 0, 1, 6, 0, 4, 6, aju.r.u(), aju.r.u(), false);
      a(☃, aju.f.u(), 3, 3, 1, ☃);
      a(☃, ☃, 3, 1, 2, 3, 3, 2, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 4, 1, 3, 5, 3, 3, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 0, 1, 1, 0, 3, 5, aju.f.u(), aju.f.u(), false);
      a(☃, ☃, 1, 1, 6, 5, 3, 6, aju.f.u(), aju.f.u(), false);
      
      a(☃, ☃, 5, 1, 0, 5, 3, 0, aju.aO.u(), aju.aO.u(), false);
      a(☃, ☃, 9, 1, 0, 9, 3, 0, aju.aO.u(), aju.aO.u(), false);
      
      a(☃, ☃, 6, 1, 4, 9, 4, 6, aju.e.u(), aju.e.u(), false);
      a(☃, aju.k.u(), 7, 1, 5, ☃);
      a(☃, aju.k.u(), 8, 1, 5, ☃);
      a(☃, aju.bi.u(), 9, 2, 5, ☃);
      a(☃, aju.bi.u(), 9, 2, 4, ☃);
      a(☃, ☃, 7, 2, 4, 8, 2, 5, aju.a.u(), aju.a.u(), false);
      a(☃, aju.e.u(), 6, 1, 3, ☃);
      a(☃, aju.al.u(), 6, 2, 3, ☃);
      a(☃, aju.al.u(), 6, 3, 3, ☃);
      a(☃, aju.T.u(), 8, 1, 1, ☃);
      
      a(☃, aju.bj.u(), 0, 2, 2, ☃);
      a(☃, aju.bj.u(), 0, 2, 4, ☃);
      a(☃, aju.bj.u(), 2, 2, 6, ☃);
      a(☃, aju.bj.u(), 4, 2, 6, ☃);
      
      a(☃, aju.aO.u(), 2, 1, 4, ☃);
      a(☃, aju.aB.u(), 2, 2, 4, ☃);
      a(☃, aju.f.u(), 1, 1, 5, ☃);
      a(☃, aju.ad.u().a(aot.a, cq.c), 2, 1, 5, ☃);
      a(☃, aju.ad.u().a(aot.a, cq.e), 1, 1, 4, ☃);
      if ((!this.a) && 
        (☃.b(new cj(a(5, 5), d(1), b(5, 5)))))
      {
        this.a = true;
        a(☃, ☃, ☃, 5, 1, 5, azt.e);
      }
      for (int ☃ = 6; ☃ <= 8; ☃++) {
        if ((a(☃, ☃, 0, -1, ☃).a() == axe.a) && (a(☃, ☃, -1, -1, ☃).a() != axe.a)) {
          a(☃, aju.aw.u().a(aot.a, cq.c), ☃, 0, -1, ☃);
        }
      }
      for (int ☃ = 0; ☃ < 7; ☃++) {
        for (int ☃ = 0; ☃ < 10; ☃++)
        {
          b(☃, ☃, 6, ☃, ☃);
          b(☃, aju.e.u(), ☃, -1, ☃, ☃);
        }
      }
      a(☃, ☃, 7, 1, 1, 1);
      
      return true;
    }
    
    protected int c(int ☃, int ☃)
    {
      return 3;
    }
  }
  
  public static class c
    extends awk.n
  {
    private ajt a;
    private ajt b;
    
    public c() {}
    
    public c(awk.k ☃, int ☃, Random ☃, avp ☃, cq ☃)
    {
      super(☃);
      
      a(☃);
      this.l = ☃;
      
      this.a = a(☃);
      this.b = a(☃);
    }
    
    protected void a(dn ☃)
    {
      super.a(☃);
      ☃.a("CA", ajt.h.a(this.a));
      ☃.a("CB", ajt.h.a(this.b));
    }
    
    protected void b(dn ☃)
    {
      super.b(☃);
      this.a = ajt.b(☃.h("CA"));
      this.b = ajt.b(☃.h("CB"));
    }
    
    private ajt a(Random ☃)
    {
      switch (☃.nextInt(10))
      {
      default: 
        return aju.aj;
      case 0: 
      case 1: 
        return aju.cb;
      case 2: 
      case 3: 
        return aju.cc;
      }
      return aju.cZ;
    }
    
    public static c a(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, 0, 0, 0, 7, 4, 9, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
        return null;
      }
      return new c(☃, ☃, ☃, ☃, ☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (this.h < 0)
      {
        this.h = b(☃, ☃);
        if (this.h < 0) {
          return true;
        }
        this.l.a(0, this.h - this.l.e + 4 - 1, 0);
      }
      a(☃, ☃, 0, 1, 0, 6, 4, 8, aju.a.u(), aju.a.u(), false);
      
      a(☃, ☃, 1, 0, 1, 2, 0, 7, aju.ak.u(), aju.ak.u(), false);
      a(☃, ☃, 4, 0, 1, 5, 0, 7, aju.ak.u(), aju.ak.u(), false);
      
      a(☃, ☃, 0, 0, 0, 0, 0, 8, aju.r.u(), aju.r.u(), false);
      a(☃, ☃, 6, 0, 0, 6, 0, 8, aju.r.u(), aju.r.u(), false);
      a(☃, ☃, 1, 0, 0, 5, 0, 0, aju.r.u(), aju.r.u(), false);
      a(☃, ☃, 1, 0, 8, 5, 0, 8, aju.r.u(), aju.r.u(), false);
      
      a(☃, ☃, 3, 0, 1, 3, 0, 7, aju.j.u(), aju.j.u(), false);
      for (int ☃ = 1; ☃ <= 7; ☃++)
      {
        int ☃ = ((akn)this.a).g();
        int ☃ = ☃ / 3;
        a(☃, this.a.a(on.a(☃, ☃, ☃)), 1, 1, ☃, ☃);
        a(☃, this.a.a(on.a(☃, ☃, ☃)), 2, 1, ☃, ☃);
        int ☃ = ((akn)this.b).g();
        int ☃ = ☃ / 3;
        a(☃, this.b.a(on.a(☃, ☃, ☃)), 4, 1, ☃, ☃);
        a(☃, this.b.a(on.a(☃, ☃, ☃)), 5, 1, ☃, ☃);
      }
      for (int ☃ = 0; ☃ < 9; ☃++) {
        for (int ☃ = 0; ☃ < 7; ☃++)
        {
          b(☃, ☃, 4, ☃, ☃);
          b(☃, aju.d.u(), ☃, -1, ☃, ☃);
        }
      }
      return true;
    }
  }
  
  public static class b
    extends awk.n
  {
    private ajt a;
    private ajt b;
    private ajt c;
    private ajt d;
    
    public b() {}
    
    public b(awk.k ☃, int ☃, Random ☃, avp ☃, cq ☃)
    {
      super(☃);
      
      a(☃);
      this.l = ☃;
      
      this.a = a(☃);
      this.b = a(☃);
      this.c = a(☃);
      this.d = a(☃);
    }
    
    protected void a(dn ☃)
    {
      super.a(☃);
      ☃.a("CA", ajt.h.a(this.a));
      ☃.a("CB", ajt.h.a(this.b));
      ☃.a("CC", ajt.h.a(this.c));
      ☃.a("CD", ajt.h.a(this.d));
    }
    
    protected void b(dn ☃)
    {
      super.b(☃);
      this.a = ajt.b(☃.h("CA"));
      this.b = ajt.b(☃.h("CB"));
      this.c = ajt.b(☃.h("CC"));
      this.d = ajt.b(☃.h("CD"));
      if (!(this.a instanceof akn)) {
        this.a = aju.aj;
      }
      if (!(this.b instanceof akn)) {
        this.b = aju.cb;
      }
      if (!(this.c instanceof akn)) {
        this.c = aju.cc;
      }
      if (!(this.d instanceof akn)) {
        this.d = aju.cZ;
      }
    }
    
    private ajt a(Random ☃)
    {
      switch (☃.nextInt(10))
      {
      default: 
        return aju.aj;
      case 0: 
      case 1: 
        return aju.cb;
      case 2: 
      case 3: 
        return aju.cc;
      }
      return aju.cZ;
    }
    
    public static b a(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, 0, 0, 0, 13, 4, 9, ☃);
      if ((!a(☃)) || (awg.a(☃, ☃) != null)) {
        return null;
      }
      return new b(☃, ☃, ☃, ☃, ☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (this.h < 0)
      {
        this.h = b(☃, ☃);
        if (this.h < 0) {
          return true;
        }
        this.l.a(0, this.h - this.l.e + 4 - 1, 0);
      }
      a(☃, ☃, 0, 1, 0, 12, 4, 8, aju.a.u(), aju.a.u(), false);
      
      a(☃, ☃, 1, 0, 1, 2, 0, 7, aju.ak.u(), aju.ak.u(), false);
      a(☃, ☃, 4, 0, 1, 5, 0, 7, aju.ak.u(), aju.ak.u(), false);
      a(☃, ☃, 7, 0, 1, 8, 0, 7, aju.ak.u(), aju.ak.u(), false);
      a(☃, ☃, 10, 0, 1, 11, 0, 7, aju.ak.u(), aju.ak.u(), false);
      
      a(☃, ☃, 0, 0, 0, 0, 0, 8, aju.r.u(), aju.r.u(), false);
      a(☃, ☃, 6, 0, 0, 6, 0, 8, aju.r.u(), aju.r.u(), false);
      a(☃, ☃, 12, 0, 0, 12, 0, 8, aju.r.u(), aju.r.u(), false);
      a(☃, ☃, 1, 0, 0, 11, 0, 0, aju.r.u(), aju.r.u(), false);
      a(☃, ☃, 1, 0, 8, 11, 0, 8, aju.r.u(), aju.r.u(), false);
      
      a(☃, ☃, 3, 0, 1, 3, 0, 7, aju.j.u(), aju.j.u(), false);
      a(☃, ☃, 9, 0, 1, 9, 0, 7, aju.j.u(), aju.j.u(), false);
      for (int ☃ = 1; ☃ <= 7; ☃++)
      {
        int ☃ = ((akn)this.a).g();
        int ☃ = ☃ / 3;
        a(☃, this.a.a(on.a(☃, ☃, ☃)), 1, 1, ☃, ☃);
        a(☃, this.a.a(on.a(☃, ☃, ☃)), 2, 1, ☃, ☃);
        int ☃ = ((akn)this.b).g();
        int ☃ = ☃ / 3;
        a(☃, this.b.a(on.a(☃, ☃, ☃)), 4, 1, ☃, ☃);
        a(☃, this.b.a(on.a(☃, ☃, ☃)), 5, 1, ☃, ☃);
        int ☃ = ((akn)this.c).g();
        int ☃ = ☃ / 3;
        a(☃, this.c.a(on.a(☃, ☃, ☃)), 7, 1, ☃, ☃);
        a(☃, this.c.a(on.a(☃, ☃, ☃)), 8, 1, ☃, ☃);
        int ☃ = ((akn)this.d).g();
        int ☃ = ☃ / 3;
        a(☃, this.d.a(on.a(☃, ☃, ☃)), 10, 1, ☃, ☃);
        a(☃, this.d.a(on.a(☃, ☃, ☃)), 11, 1, ☃, ☃);
      }
      for (int ☃ = 0; ☃ < 9; ☃++) {
        for (int ☃ = 0; ☃ < 13; ☃++)
        {
          b(☃, ☃, 4, ☃, ☃);
          b(☃, aju.d.u(), ☃, -1, ☃, ☃);
        }
      }
      return true;
    }
  }
  
  public static class d
    extends awk.n
  {
    public d() {}
    
    public d(awk.k ☃, int ☃, Random ☃, avp ☃, cq ☃)
    {
      super(☃);
      
      a(☃);
      this.l = ☃;
    }
    
    public static avp a(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃)
    {
      avp ☃ = avp.a(☃, ☃, ☃, 0, 0, 0, 3, 4, 2, ☃);
      if (awg.a(☃, ☃) != null) {
        return null;
      }
      return ☃;
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (this.h < 0)
      {
        this.h = b(☃, ☃);
        if (this.h < 0) {
          return true;
        }
        this.l.a(0, this.h - this.l.e + 4 - 1, 0);
      }
      a(☃, ☃, 0, 0, 0, 2, 3, 1, aju.a.u(), aju.a.u(), false);
      
      a(☃, aju.aO.u(), 1, 0, 0, ☃);
      a(☃, aju.aO.u(), 1, 1, 0, ☃);
      a(☃, aju.aO.u(), 1, 2, 0, ☃);
      
      a(☃, aju.L.a(act.a.b()), 1, 3, 0, ☃);
      
      a(☃, aju.aa.u().a(apf.a, cq.f), 2, 3, 0, ☃);
      a(☃, aju.aa.u().a(apf.a, cq.c), 1, 3, 1, ☃);
      a(☃, aju.aa.u().a(apf.a, cq.e), 0, 3, 0, ☃);
      a(☃, aju.aa.u().a(apf.a, cq.d), 1, 3, -1, ☃);
      
      return true;
    }
  }
}
