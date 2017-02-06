import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class avt
{
  public static void a()
  {
    awe.a(avt.a.class, "MSCorridor");
    awe.a(avt.b.class, "MSCrossing");
    awe.a(avt.c.class, "MSRoom");
    awe.a(avt.d.class, "MSStairs");
  }
  
  private static awg a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    int ☃ = ☃.nextInt(100);
    if (☃ >= 80)
    {
      avp ☃ = avt.b.a(☃, ☃, ☃, ☃, ☃, ☃);
      if (☃ != null) {
        return new avt.b(☃, ☃, ☃, ☃);
      }
    }
    else if (☃ >= 70)
    {
      avp ☃ = avt.d.a(☃, ☃, ☃, ☃, ☃, ☃);
      if (☃ != null) {
        return new avt.d(☃, ☃, ☃, ☃);
      }
    }
    else
    {
      avp ☃ = avt.a.a(☃, ☃, ☃, ☃, ☃, ☃);
      if (☃ != null) {
        return new avt.a(☃, ☃, ☃, ☃);
      }
    }
    return null;
  }
  
  private static awg b(awg ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, int ☃)
  {
    if (☃ > 8) {
      return null;
    }
    if ((Math.abs(☃ - ☃.c().a) > 80) || (Math.abs(☃ - ☃.c().c) > 80)) {
      return null;
    }
    awg ☃ = a(☃, ☃, ☃, ☃, ☃, ☃, ☃ + 1);
    if (☃ != null)
    {
      ☃.add(☃);
      ☃.a(☃, ☃, ☃);
    }
    return ☃;
  }
  
  public static class c
    extends awg
  {
    private List<avp> a = Lists.newLinkedList();
    
    public c() {}
    
    public c(int ☃, Random ☃, int ☃, int ☃)
    {
      super();
      
      this.l = new avp(☃, 50, ☃, ☃ + 7 + ☃.nextInt(6), 54 + ☃.nextInt(6), ☃ + 7 + ☃.nextInt(6));
    }
    
    public void a(awg ☃, List<awg> ☃, Random ☃)
    {
      int ☃ = d();
      
      int ☃ = this.l.d() - 3 - 1;
      if (☃ <= 0) {
        ☃ = 1;
      }
      int ☃ = 0;
      while (☃ < this.l.c())
      {
        ☃ += ☃.nextInt(this.l.c());
        if (☃ + 3 > this.l.c()) {
          break;
        }
        awg ☃ = avt.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃.nextInt(☃) + 1, this.l.c - 1, cq.c, ☃);
        if (☃ != null)
        {
          avp ☃ = ☃.c();
          this.a.add(new avp(☃.a, ☃.b, this.l.c, ☃.d, ☃.e, this.l.c + 1));
        }
        ☃ += 4;
      }
      ☃ = 0;
      while (☃ < this.l.c())
      {
        ☃ += ☃.nextInt(this.l.c());
        if (☃ + 3 > this.l.c()) {
          break;
        }
        awg ☃ = avt.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃.nextInt(☃) + 1, this.l.f + 1, cq.d, ☃);
        if (☃ != null)
        {
          avp ☃ = ☃.c();
          this.a.add(new avp(☃.a, ☃.b, this.l.f - 1, ☃.d, ☃.e, this.l.f));
        }
        ☃ += 4;
      }
      ☃ = 0;
      while (☃ < this.l.e())
      {
        ☃ += ☃.nextInt(this.l.e());
        if (☃ + 3 > this.l.e()) {
          break;
        }
        awg ☃ = avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b + ☃.nextInt(☃) + 1, this.l.c + ☃, cq.e, ☃);
        if (☃ != null)
        {
          avp ☃ = ☃.c();
          this.a.add(new avp(this.l.a, ☃.b, ☃.c, this.l.a + 1, ☃.e, ☃.f));
        }
        ☃ += 4;
      }
      ☃ = 0;
      while (☃ < this.l.e())
      {
        ☃ += ☃.nextInt(this.l.e());
        if (☃ + 3 > this.l.e()) {
          break;
        }
        awg ☃ = avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b + ☃.nextInt(☃) + 1, this.l.c + ☃, cq.f, ☃);
        if (☃ != null)
        {
          avp ☃ = ☃.c();
          this.a.add(new avp(this.l.d - 1, ☃.b, ☃.c, this.l.d, ☃.e, ☃.f));
        }
        ☃ += 4;
      }
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, ☃)) {
        return false;
      }
      a(☃, ☃, this.l.a, this.l.b, this.l.c, this.l.d, this.l.b, this.l.f, aju.d.u(), aju.a.u(), true);
      
      a(☃, ☃, this.l.a, this.l.b + 1, this.l.c, this.l.d, Math.min(this.l.b + 3, this.l.e), this.l.f, aju.a.u(), aju.a.u(), false);
      for (avp ☃ : this.a) {
        a(☃, ☃, ☃.a, ☃.e - 2, ☃.c, ☃.d, ☃.e, ☃.f, aju.a.u(), aju.a.u(), false);
      }
      a(☃, ☃, this.l.a, this.l.b + 4, this.l.c, this.l.d, this.l.e, this.l.f, aju.a.u(), false);
      
      return true;
    }
    
    public void a(int ☃, int ☃, int ☃)
    {
      super.a(☃, ☃, ☃);
      for (avp ☃ : this.a) {
        ☃.a(☃, ☃, ☃);
      }
    }
    
    protected void a(dn ☃)
    {
      du ☃ = new du();
      for (avp ☃ : this.a) {
        ☃.a(☃.g());
      }
      ☃.a("Entrances", ☃);
    }
    
    protected void b(dn ☃)
    {
      du ☃ = ☃.c("Entrances", 11);
      for (int ☃ = 0; ☃ < ☃.c(); ☃++) {
        this.a.add(new avp(☃.d(☃)));
      }
    }
  }
  
  public static class a
    extends awg
  {
    private boolean a;
    private boolean b;
    private boolean c;
    private int d;
    
    public a() {}
    
    protected void a(dn ☃)
    {
      ☃.a("hr", this.a);
      ☃.a("sc", this.b);
      ☃.a("hps", this.c);
      ☃.a("Num", this.d);
    }
    
    protected void b(dn ☃)
    {
      this.a = ☃.p("hr");
      this.b = ☃.p("sc");
      this.c = ☃.p("hps");
      this.d = ☃.h("Num");
    }
    
    public a(int ☃, Random ☃, avp ☃, cq ☃)
    {
      super();
      a(☃);
      this.l = ☃;
      this.a = (☃.nextInt(3) == 0);
      this.b = ((!this.a) && (☃.nextInt(23) == 0));
      if (e().k() == cq.a.c) {
        this.d = (☃.e() / 5);
      } else {
        this.d = (☃.c() / 5);
      }
    }
    
    public static avp a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃)
    {
      avp ☃ = new avp(☃, ☃, ☃, ☃, ☃ + 2, ☃);
      
      int ☃ = ☃.nextInt(3) + 2;
      while (☃ > 0)
      {
        int ☃ = ☃ * 5;
        switch (avt.1.a[☃.ordinal()])
        {
        case 1: 
          ☃.d = (☃ + 2);
          ☃.c = (☃ - (☃ - 1));
          break;
        case 2: 
          ☃.d = (☃ + 2);
          ☃.f = (☃ + (☃ - 1));
          break;
        case 3: 
          ☃.a = (☃ - (☃ - 1));
          ☃.f = (☃ + 2);
          break;
        case 4: 
          ☃.d = (☃ + (☃ - 1));
          ☃.f = (☃ + 2);
        }
        if (awg.a(☃, ☃) == null) {
          break;
        }
        ☃--;
      }
      if (☃ > 0) {
        return ☃;
      }
      return null;
    }
    
    public void a(awg ☃, List<awg> ☃, Random ☃)
    {
      int ☃ = d();
      int ☃ = ☃.nextInt(4);
      cq ☃ = e();
      if (☃ != null) {
        switch (avt.1.a[☃.ordinal()])
        {
        case 1: 
          if (☃ <= 1) {
            avt.a(☃, ☃, ☃, this.l.a, this.l.b - 1 + ☃.nextInt(3), this.l.c - 1, ☃, ☃);
          } else if (☃ == 2) {
            avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b - 1 + ☃.nextInt(3), this.l.c, cq.e, ☃);
          } else {
            avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b - 1 + ☃.nextInt(3), this.l.c, cq.f, ☃);
          }
          break;
        case 2: 
          if (☃ <= 1) {
            avt.a(☃, ☃, ☃, this.l.a, this.l.b - 1 + ☃.nextInt(3), this.l.f + 1, ☃, ☃);
          } else if (☃ == 2) {
            avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b - 1 + ☃.nextInt(3), this.l.f - 3, cq.e, ☃);
          } else {
            avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b - 1 + ☃.nextInt(3), this.l.f - 3, cq.f, ☃);
          }
          break;
        case 3: 
          if (☃ <= 1) {
            avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b - 1 + ☃.nextInt(3), this.l.c, ☃, ☃);
          } else if (☃ == 2) {
            avt.a(☃, ☃, ☃, this.l.a, this.l.b - 1 + ☃.nextInt(3), this.l.c - 1, cq.c, ☃);
          } else {
            avt.a(☃, ☃, ☃, this.l.a, this.l.b - 1 + ☃.nextInt(3), this.l.f + 1, cq.d, ☃);
          }
          break;
        case 4: 
          if (☃ <= 1) {
            avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b - 1 + ☃.nextInt(3), this.l.c, ☃, ☃);
          } else if (☃ == 2) {
            avt.a(☃, ☃, ☃, this.l.d - 3, this.l.b - 1 + ☃.nextInt(3), this.l.c - 1, cq.c, ☃);
          } else {
            avt.a(☃, ☃, ☃, this.l.d - 3, this.l.b - 1 + ☃.nextInt(3), this.l.f + 1, cq.d, ☃);
          }
          break;
        }
      }
      if (☃ < 8) {
        if ((☃ == cq.c) || (☃ == cq.d)) {
          for (int ☃ = this.l.c + 3; ☃ + 3 <= this.l.f; ☃ += 5)
          {
            int ☃ = ☃.nextInt(5);
            if (☃ == 0) {
              avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b, ☃, cq.e, ☃ + 1);
            } else if (☃ == 1) {
              avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b, ☃, cq.f, ☃ + 1);
            }
          }
        } else {
          for (int ☃ = this.l.a + 3; ☃ + 3 <= this.l.d; ☃ += 5)
          {
            int ☃ = ☃.nextInt(5);
            if (☃ == 0) {
              avt.a(☃, ☃, ☃, ☃, this.l.b, this.l.c - 1, cq.c, ☃ + 1);
            } else if (☃ == 1) {
              avt.a(☃, ☃, ☃, ☃, this.l.b, this.l.f + 1, cq.d, ☃ + 1);
            }
          }
        }
      }
    }
    
    protected boolean a(aht ☃, avp ☃, Random ☃, int ☃, int ☃, int ☃, kk ☃)
    {
      cj ☃ = new cj(a(☃, ☃), d(☃), b(☃, ☃));
      if ((☃.b(☃)) && 
        (☃.o(☃).a() == axe.a))
      {
        arc ☃ = aju.av.u().a(ant.d, ☃.nextBoolean() ? ajp.b.a : ajp.b.b);
        a(☃, ☃, ☃, ☃, ☃, ☃);
        aai ☃ = new aai(☃, ☃.p() + 0.5F, ☃.q() + 0.5F, ☃.r() + 0.5F);
        ☃.a(☃, ☃.nextLong());
        ☃.a(☃);
        return true;
      }
      return false;
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, ☃)) {
        return false;
      }
      int ☃ = 0;
      int ☃ = 2;
      int ☃ = 0;
      int ☃ = 2;
      int ☃ = this.d * 5 - 1;
      
      a(☃, ☃, 0, 0, 0, 2, 1, ☃, aju.a.u(), aju.a.u(), false);
      a(☃, ☃, ☃, 0.8F, 0, 2, 0, 2, 2, ☃, aju.a.u(), aju.a.u(), false);
      if (this.b) {
        a(☃, ☃, ☃, 0.6F, 0, 0, 0, 2, 1, ☃, aju.G.u(), aju.a.u(), false);
      }
      for (int ☃ = 0; ☃ < this.d; ☃++)
      {
        int ☃ = 2 + ☃ * 5;
        
        a(☃, ☃, 0, 0, ☃, 0, 1, ☃, aju.aO.u(), aju.a.u(), false);
        a(☃, ☃, 2, 0, ☃, 2, 1, ☃, aju.aO.u(), aju.a.u(), false);
        if (☃.nextInt(4) == 0)
        {
          a(☃, ☃, 0, 2, ☃, 0, 2, ☃, aju.f.u(), aju.a.u(), false);
          a(☃, ☃, 2, 2, ☃, 2, 2, ☃, aju.f.u(), aju.a.u(), false);
        }
        else
        {
          a(☃, ☃, 0, 2, ☃, 2, 2, ☃, aju.f.u(), aju.a.u(), false);
        }
        a(☃, ☃, ☃, 0.1F, 0, 2, ☃ - 1, aju.G.u());
        a(☃, ☃, ☃, 0.1F, 2, 2, ☃ - 1, aju.G.u());
        a(☃, ☃, ☃, 0.1F, 0, 2, ☃ + 1, aju.G.u());
        a(☃, ☃, ☃, 0.1F, 2, 2, ☃ + 1, aju.G.u());
        a(☃, ☃, ☃, 0.05F, 0, 2, ☃ - 2, aju.G.u());
        a(☃, ☃, ☃, 0.05F, 2, 2, ☃ - 2, aju.G.u());
        a(☃, ☃, ☃, 0.05F, 0, 2, ☃ + 2, aju.G.u());
        a(☃, ☃, ☃, 0.05F, 2, 2, ☃ + 2, aju.G.u());
        
        a(☃, ☃, ☃, 0.05F, 1, 2, ☃ - 1, aju.aa.u().a(apf.a, cq.d));
        a(☃, ☃, ☃, 0.05F, 1, 2, ☃ + 1, aju.aa.u().a(apf.a, cq.c));
        if (☃.nextInt(100) == 0) {
          a(☃, ☃, ☃, 2, 0, ☃ - 1, azt.f);
        }
        if (☃.nextInt(100) == 0) {
          a(☃, ☃, ☃, 0, 0, ☃ + 1, azt.f);
        }
        if ((this.b) && (!this.c))
        {
          int ☃ = d(0);int ☃ = ☃ - 1 + ☃.nextInt(3);
          int ☃ = a(1, ☃);
          ☃ = b(1, ☃);
          cj ☃ = new cj(☃, ☃, ☃);
          if (☃.b(☃))
          {
            this.c = true;
            ☃.a(☃, aju.ac.u(), 2);
            
            apv ☃ = ☃.r(☃);
            if ((☃ instanceof aqk)) {
              ((aqk)☃).b().a("CaveSpider");
            }
          }
        }
      }
      for (int ☃ = 0; ☃ <= 2; ☃++) {
        for (int ☃ = 0; ☃ <= ☃; ☃++)
        {
          int ☃ = -1;
          arc ☃ = a(☃, ☃, ☃, ☃, ☃);
          if (☃.a() == axe.a)
          {
            int ☃ = -1;
            a(☃, aju.f.u(), ☃, ☃, ☃, ☃);
          }
        }
      }
      if (this.a)
      {
        arc ☃ = aju.av.u().a(ant.d, ajp.b.a);
        for (int ☃ = 0; ☃ <= ☃; ☃++)
        {
          arc ☃ = a(☃, 1, -1, ☃, ☃);
          if ((☃.a() != axe.a) && (☃.b())) {
            a(☃, ☃, ☃, 0.7F, 1, 0, ☃, ☃);
          }
        }
      }
      return true;
    }
  }
  
  public static class b
    extends awg
  {
    private cq a;
    private boolean b;
    
    public b() {}
    
    protected void a(dn ☃)
    {
      ☃.a("tf", this.b);
      ☃.a("D", this.a.b());
    }
    
    protected void b(dn ☃)
    {
      this.b = ☃.p("tf");
      this.a = cq.b(☃.h("D"));
    }
    
    public b(int ☃, Random ☃, avp ☃, cq ☃)
    {
      super();
      
      this.a = ☃;
      this.l = ☃;
      this.b = (☃.d() > 3);
    }
    
    public static avp a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃)
    {
      avp ☃ = new avp(☃, ☃, ☃, ☃, ☃ + 2, ☃);
      if (☃.nextInt(4) == 0) {
        ☃.e += 4;
      }
      switch (avt.1.a[☃.ordinal()])
      {
      case 1: 
        ☃.a = (☃ - 1);
        ☃.d = (☃ + 3);
        ☃.c = (☃ - 4);
        break;
      case 2: 
        ☃.a = (☃ - 1);
        ☃.d = (☃ + 3);
        ☃.f = (☃ + 3 + 1);
        break;
      case 3: 
        ☃.a = (☃ - 4);
        ☃.c = (☃ - 1);
        ☃.f = (☃ + 3);
        break;
      case 4: 
        ☃.d = (☃ + 3 + 1);
        ☃.c = (☃ - 1);
        ☃.f = (☃ + 3);
      }
      if (awg.a(☃, ☃) != null) {
        return null;
      }
      return ☃;
    }
    
    public void a(awg ☃, List<awg> ☃, Random ☃)
    {
      int ☃ = d();
      switch (avt.1.a[this.a.ordinal()])
      {
      case 1: 
        avt.a(☃, ☃, ☃, this.l.a + 1, this.l.b, this.l.c - 1, cq.c, ☃);
        avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b, this.l.c + 1, cq.e, ☃);
        avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b, this.l.c + 1, cq.f, ☃);
        break;
      case 2: 
        avt.a(☃, ☃, ☃, this.l.a + 1, this.l.b, this.l.f + 1, cq.d, ☃);
        avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b, this.l.c + 1, cq.e, ☃);
        avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b, this.l.c + 1, cq.f, ☃);
        break;
      case 3: 
        avt.a(☃, ☃, ☃, this.l.a + 1, this.l.b, this.l.c - 1, cq.c, ☃);
        avt.a(☃, ☃, ☃, this.l.a + 1, this.l.b, this.l.f + 1, cq.d, ☃);
        avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b, this.l.c + 1, cq.e, ☃);
        break;
      case 4: 
        avt.a(☃, ☃, ☃, this.l.a + 1, this.l.b, this.l.c - 1, cq.c, ☃);
        avt.a(☃, ☃, ☃, this.l.a + 1, this.l.b, this.l.f + 1, cq.d, ☃);
        avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b, this.l.c + 1, cq.f, ☃);
      }
      if (this.b)
      {
        if (☃.nextBoolean()) {
          avt.a(☃, ☃, ☃, this.l.a + 1, this.l.b + 3 + 1, this.l.c - 1, cq.c, ☃);
        }
        if (☃.nextBoolean()) {
          avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b + 3 + 1, this.l.c + 1, cq.e, ☃);
        }
        if (☃.nextBoolean()) {
          avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b + 3 + 1, this.l.c + 1, cq.f, ☃);
        }
        if (☃.nextBoolean()) {
          avt.a(☃, ☃, ☃, this.l.a + 1, this.l.b + 3 + 1, this.l.f + 1, cq.d, ☃);
        }
      }
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, ☃)) {
        return false;
      }
      if (this.b)
      {
        a(☃, ☃, this.l.a + 1, this.l.b, this.l.c, this.l.d - 1, this.l.b + 3 - 1, this.l.f, aju.a.u(), aju.a.u(), false);
        a(☃, ☃, this.l.a, this.l.b, this.l.c + 1, this.l.d, this.l.b + 3 - 1, this.l.f - 1, aju.a.u(), aju.a.u(), false);
        a(☃, ☃, this.l.a + 1, this.l.e - 2, this.l.c, this.l.d - 1, this.l.e, this.l.f, aju.a.u(), aju.a.u(), false);
        a(☃, ☃, this.l.a, this.l.e - 2, this.l.c + 1, this.l.d, this.l.e, this.l.f - 1, aju.a.u(), aju.a.u(), false);
        a(☃, ☃, this.l.a + 1, this.l.b + 3, this.l.c + 1, this.l.d - 1, this.l.b + 3, this.l.f - 1, aju.a.u(), aju.a.u(), false);
      }
      else
      {
        a(☃, ☃, this.l.a + 1, this.l.b, this.l.c, this.l.d - 1, this.l.e, this.l.f, aju.a.u(), aju.a.u(), false);
        a(☃, ☃, this.l.a, this.l.b, this.l.c + 1, this.l.d, this.l.e, this.l.f - 1, aju.a.u(), aju.a.u(), false);
      }
      a(☃, ☃, this.l.a + 1, this.l.b, this.l.c + 1, this.l.a + 1, this.l.e, this.l.c + 1, aju.f.u(), aju.a.u(), false);
      a(☃, ☃, this.l.a + 1, this.l.b, this.l.f - 1, this.l.a + 1, this.l.e, this.l.f - 1, aju.f.u(), aju.a.u(), false);
      a(☃, ☃, this.l.d - 1, this.l.b, this.l.c + 1, this.l.d - 1, this.l.e, this.l.c + 1, aju.f.u(), aju.a.u(), false);
      a(☃, ☃, this.l.d - 1, this.l.b, this.l.f - 1, this.l.d - 1, this.l.e, this.l.f - 1, aju.f.u(), aju.a.u(), false);
      for (int ☃ = this.l.a; ☃ <= this.l.d; ☃++) {
        for (int ☃ = this.l.c; ☃ <= this.l.f; ☃++) {
          if (a(☃, ☃, this.l.b - 1, ☃, ☃).a() == axe.a) {
            a(☃, aju.f.u(), ☃, this.l.b - 1, ☃, ☃);
          }
        }
      }
      return true;
    }
  }
  
  public static class d
    extends awg
  {
    public d() {}
    
    public d(int ☃, Random ☃, avp ☃, cq ☃)
    {
      super();
      a(☃);
      this.l = ☃;
    }
    
    protected void a(dn ☃) {}
    
    protected void b(dn ☃) {}
    
    public static avp a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃)
    {
      avp ☃ = new avp(☃, ☃ - 5, ☃, ☃, ☃ + 2, ☃);
      switch (avt.1.a[☃.ordinal()])
      {
      case 1: 
        ☃.d = (☃ + 2);
        ☃.c = (☃ - 8);
        break;
      case 2: 
        ☃.d = (☃ + 2);
        ☃.f = (☃ + 8);
        break;
      case 3: 
        ☃.a = (☃ - 8);
        ☃.f = (☃ + 2);
        break;
      case 4: 
        ☃.d = (☃ + 8);
        ☃.f = (☃ + 2);
      }
      if (awg.a(☃, ☃) != null) {
        return null;
      }
      return ☃;
    }
    
    public void a(awg ☃, List<awg> ☃, Random ☃)
    {
      int ☃ = d();
      
      cq ☃ = e();
      if (☃ != null) {
        switch (avt.1.a[☃.ordinal()])
        {
        case 1: 
          avt.a(☃, ☃, ☃, this.l.a, this.l.b, this.l.c - 1, cq.c, ☃);
          break;
        case 2: 
          avt.a(☃, ☃, ☃, this.l.a, this.l.b, this.l.f + 1, cq.d, ☃);
          break;
        case 3: 
          avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b, this.l.c, cq.e, ☃);
          break;
        case 4: 
          avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b, this.l.c, cq.f, ☃);
        }
      }
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, ☃)) {
        return false;
      }
      a(☃, ☃, 0, 5, 0, 2, 7, 1, aju.a.u(), aju.a.u(), false);
      
      a(☃, ☃, 0, 0, 7, 2, 2, 8, aju.a.u(), aju.a.u(), false);
      for (int ☃ = 0; ☃ < 5; ☃++) {
        a(☃, ☃, 0, 5 - ☃ - (☃ < 4 ? 1 : 0), 2 + ☃, 2, 7 - ☃, 2 + ☃, aju.a.u(), aju.a.u(), false);
      }
      return true;
    }
  }
}
