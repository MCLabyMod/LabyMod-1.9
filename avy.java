import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class avy
{
  public static void a()
  {
    awe.a(avy.h.class, "OMB");
    awe.a(avy.j.class, "OMCR");
    awe.a(avy.k.class, "OMDXR");
    awe.a(avy.l.class, "OMDXYR");
    awe.a(avy.m.class, "OMDYR");
    awe.a(avy.n.class, "OMDYZR");
    awe.a(avy.o.class, "OMDZR");
    awe.a(avy.p.class, "OMEntry");
    awe.a(avy.q.class, "OMPenthouse");
    awe.a(avy.s.class, "OMSimple");
    awe.a(avy.t.class, "OMSimpleT");
  }
  
  public static abstract class r
    extends awg
  {
    protected static final arc a = aju.cI.a(anp.b);
    protected static final arc b = aju.cI.a(anp.c);
    protected static final arc c = aju.cI.a(anp.d);
    protected static final arc d = b;
    protected static final arc e = aju.cJ.u();
    protected static final arc f = aju.j.u();
    protected static final int g = b(2, 0, 0);
    protected static final int h = b(2, 2, 0);
    protected static final int i = b(0, 1, 0);
    protected static final int j = b(4, 1, 0);
    protected avy.v k;
    
    protected static final int b(int ☃, int ☃, int ☃)
    {
      return ☃ * 25 + ☃ * 5 + ☃;
    }
    
    public r()
    {
      super();
    }
    
    public r(int ☃)
    {
      super();
    }
    
    public r(cq ☃, avp ☃)
    {
      super();
      a(☃);
      this.l = ☃;
    }
    
    protected r(int ☃, cq ☃, avy.v ☃, int ☃, int ☃, int ☃)
    {
      super();
      a(☃);
      this.k = ☃;
      
      int ☃ = ☃.a;
      int ☃ = ☃ % 5;
      int ☃ = ☃ / 5 % 5;
      int ☃ = ☃ / 25;
      if ((☃ == cq.c) || (☃ == cq.d)) {
        this.l = new avp(0, 0, 0, ☃ * 8 - 1, ☃ * 4 - 1, ☃ * 8 - 1);
      } else {
        this.l = new avp(0, 0, 0, ☃ * 8 - 1, ☃ * 4 - 1, ☃ * 8 - 1);
      }
      switch (avy.1.a[☃.ordinal()])
      {
      case 1: 
        this.l.a(☃ * 8, ☃ * 4, -(☃ + ☃) * 8 + 1);
        break;
      case 2: 
        this.l.a(☃ * 8, ☃ * 4, ☃ * 8);
        break;
      case 3: 
        this.l.a(-(☃ + ☃) * 8 + 1, ☃ * 4, ☃ * 8);
        break;
      default: 
        this.l.a(☃ * 8, ☃ * 4, ☃ * 8);
      }
    }
    
    protected void a(dn ☃) {}
    
    protected void b(dn ☃) {}
    
    protected void a(aht ☃, avp ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, boolean ☃)
    {
      for (int ☃ = ☃; ☃ <= ☃; ☃++) {
        for (int ☃ = ☃; ☃ <= ☃; ☃++) {
          for (int ☃ = ☃; ☃ <= ☃; ☃++) {
            if ((!☃) || (a(☃, ☃, ☃, ☃, ☃).a() != axe.a)) {
              if (d(☃) >= ☃.K()) {
                a(☃, aju.a.u(), ☃, ☃, ☃, ☃);
              } else {
                a(☃, f, ☃, ☃, ☃, ☃);
              }
            }
          }
        }
      }
    }
    
    protected void a(aht ☃, avp ☃, int ☃, int ☃, boolean ☃)
    {
      if (☃)
      {
        a(☃, ☃, ☃ + 0, 0, ☃ + 0, ☃ + 2, 0, ☃ + 8 - 1, a, a, false);
        a(☃, ☃, ☃ + 5, 0, ☃ + 0, ☃ + 8 - 1, 0, ☃ + 8 - 1, a, a, false);
        a(☃, ☃, ☃ + 3, 0, ☃ + 0, ☃ + 4, 0, ☃ + 2, a, a, false);
        a(☃, ☃, ☃ + 3, 0, ☃ + 5, ☃ + 4, 0, ☃ + 8 - 1, a, a, false);
        
        a(☃, ☃, ☃ + 3, 0, ☃ + 2, ☃ + 4, 0, ☃ + 2, b, b, false);
        a(☃, ☃, ☃ + 3, 0, ☃ + 5, ☃ + 4, 0, ☃ + 5, b, b, false);
        a(☃, ☃, ☃ + 2, 0, ☃ + 3, ☃ + 2, 0, ☃ + 4, b, b, false);
        a(☃, ☃, ☃ + 5, 0, ☃ + 3, ☃ + 5, 0, ☃ + 4, b, b, false);
      }
      else
      {
        a(☃, ☃, ☃ + 0, 0, ☃ + 0, ☃ + 8 - 1, 0, ☃ + 8 - 1, a, a, false);
      }
    }
    
    protected void a(aht ☃, avp ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, arc ☃)
    {
      for (int ☃ = ☃; ☃ <= ☃; ☃++) {
        for (int ☃ = ☃; ☃ <= ☃; ☃++) {
          for (int ☃ = ☃; ☃ <= ☃; ☃++) {
            if (a(☃, ☃, ☃, ☃, ☃) == f) {
              a(☃, ☃, ☃, ☃, ☃, ☃);
            }
          }
        }
      }
    }
    
    protected boolean a(avp ☃, int ☃, int ☃, int ☃, int ☃)
    {
      int ☃ = a(☃, ☃);
      int ☃ = b(☃, ☃);
      int ☃ = a(☃, ☃);
      int ☃ = b(☃, ☃);
      return ☃.a(Math.min(☃, ☃), Math.min(☃, ☃), Math.max(☃, ☃), Math.max(☃, ☃));
    }
    
    protected boolean a(aht ☃, avp ☃, int ☃, int ☃, int ☃)
    {
      int ☃ = a(☃, ☃);
      int ☃ = d(☃);
      int ☃ = b(☃, ☃);
      if (☃.b(new cj(☃, ☃, ☃)))
      {
        yo ☃ = new yo(☃);
        ☃.a(true);
        ☃.b(☃.bW());
        ☃.b(☃ + 0.5D, ☃, ☃ + 0.5D, 0.0F, 0.0F);
        ☃.a(☃.D(new cj(☃)), null);
        ☃.a(☃);
        return true;
      }
      return false;
    }
  }
  
  public static class h
    extends avy.r
  {
    private avy.v n;
    private avy.v o;
    private List<avy.r> p = Lists.newArrayList();
    
    public h() {}
    
    public h(Random ☃, int ☃, int ☃, cq ☃)
    {
      super();
      
      a(☃);
      
      cq ☃ = e();
      if (☃.k() == cq.a.c) {
        this.l = new avp(☃, 39, ☃, ☃ + 58 - 1, 61, ☃ + 58 - 1);
      } else {
        this.l = new avp(☃, 39, ☃, ☃ + 58 - 1, 61, ☃ + 58 - 1);
      }
      List<avy.v> ☃ = a(☃);
      
      this.n.d = true;
      this.p.add(new avy.p(☃, this.n));
      this.p.add(new avy.j(☃, this.o, ☃));
      
      List<avy.i> ☃ = Lists.newArrayList();
      ☃.add(new avy.b(null));
      ☃.add(new avy.d(null));
      ☃.add(new avy.e(null));
      ☃.add(new avy.a(null));
      ☃.add(new avy.c(null));
      ☃.add(new avy.g(null));
      ☃.add(new avy.f(null));
      for (Iterator ☃ = ☃.iterator(); ☃.hasNext();)
      {
        ☃ = (avy.v)☃.next();
        if ((!☃.d) && (!☃.b())) {
          for (avy.i ☃ : ☃) {
            if (☃.a(☃))
            {
              this.p.add(☃.a(☃, ☃, ☃));
              break;
            }
          }
        }
      }
      avy.v ☃;
      int ☃ = this.l.b;
      int ☃ = a(9, 22);
      int ☃ = b(9, 22);
      for (avy.r ☃ : this.p) {
        ☃.c().a(☃, ☃, ☃);
      }
      avp ☃ = avp.a(a(1, 1), d(1), b(1, 1), a(23, 21), d(8), b(23, 21));
      avp ☃ = avp.a(a(34, 1), d(1), b(34, 1), a(56, 21), d(8), b(56, 21));
      avp ☃ = avp.a(a(22, 22), d(13), b(22, 22), a(35, 35), d(17), b(35, 35));
      
      int ☃ = ☃.nextInt();
      this.p.add(new avy.u(☃, ☃, ☃++));
      this.p.add(new avy.u(☃, ☃, ☃++));
      
      this.p.add(new avy.q(☃, ☃));
    }
    
    private List<avy.v> a(Random ☃)
    {
      avy.v[] ☃ = new avy.v[75];
      for (int ☃ = 0; ☃ < 5; ☃++) {
        for (int ☃ = 0; ☃ < 4; ☃++)
        {
          int ☃ = 0;
          int ☃ = b(☃, ☃, ☃);
          ☃[☃] = new avy.v(☃);
        }
      }
      for (int ☃ = 0; ☃ < 5; ☃++) {
        for (int ☃ = 0; ☃ < 4; ☃++)
        {
          int ☃ = 1;
          int ☃ = b(☃, ☃, ☃);
          ☃[☃] = new avy.v(☃);
        }
      }
      for (int ☃ = 1; ☃ < 4; ☃++) {
        for (int ☃ = 0; ☃ < 2; ☃++)
        {
          int ☃ = 2;
          int ☃ = b(☃, ☃, ☃);
          ☃[☃] = new avy.v(☃);
        }
      }
      this.n = ☃[g];
      for (int ☃ = 0; ☃ < 5; ☃++) {
        for (int ☃ = 0; ☃ < 5; ☃++) {
          for (int ☃ = 0; ☃ < 3; ☃++)
          {
            int ☃ = b(☃, ☃, ☃);
            if (☃[☃] != null) {
              for (cq ☃ : cq.values())
              {
                int ☃ = ☃ + ☃.g();
                int ☃ = ☃ + ☃.h();
                int ☃ = ☃ + ☃.i();
                if ((☃ >= 0) && (☃ < 5) && (☃ >= 0) && (☃ < 5) && (☃ >= 0) && (☃ < 3))
                {
                  int ☃ = b(☃, ☃, ☃);
                  if (☃[☃] != null) {
                    if (☃ != ☃) {
                      ☃[☃].a(☃.d(), ☃[☃]);
                    } else {
                      ☃[☃].a(☃, ☃[☃]);
                    }
                  }
                }
              }
            }
          }
        }
      }
      avy.v ☃;
      ☃[h].a(cq.b, ☃ = new avy.v(1003));
      avy.v ☃;
      ☃[i].a(cq.d, ☃ = new avy.v(1001));
      avy.v ☃;
      ☃[j].a(cq.d, ☃ = new avy.v(1002));
      ☃.d = true;
      ☃.d = true;
      ☃.d = true;
      this.n.e = true;
      
      this.o = ☃[b(☃.nextInt(4), 0, 2)];
      this.o.d = true;
      this.o.b[cq.f.a()].d = true;
      this.o.b[cq.c.a()].d = true;
      this.o.b[cq.f.a()].b[cq.c.a()].d = true;
      this.o.b[cq.b.a()].d = true;
      this.o.b[cq.f.a()].b[cq.b.a()].d = true;
      this.o.b[cq.c.a()].b[cq.b.a()].d = true;
      this.o.b[cq.f.a()].b[cq.c.a()].b[cq.b.a()].d = true;
      
      List<avy.v> ☃ = Lists.newArrayList();
      for (avy.v ☃ : ☃) {
        if (☃ != null)
        {
          ☃.a();
          ☃.add(☃);
        }
      }
      ☃.a();
      
      Collections.shuffle(☃, ☃);
      int ☃ = 1;
      for (avy.v ☃ : ☃)
      {
        int ☃ = 0;
        int ☃ = 0;
        while ((☃ < 2) && (☃ < 5))
        {
          ☃++;
          
          int ☃ = ☃.nextInt(6);
          if (☃.c[☃] != 0)
          {
            int ☃ = cq.a(☃).d().a();
            
            ☃.c[☃] = false;
            ☃.b[☃].c[☃] = false;
            if ((☃.a(☃++)) && (☃.b[☃].a(☃++)))
            {
              ☃++;
            }
            else
            {
              ☃.c[☃] = true;
              ☃.b[☃].c[☃] = true;
            }
          }
        }
      }
      ☃.add(☃);
      ☃.add(☃);
      ☃.add(☃);
      
      return ☃;
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      int ☃ = Math.max(☃.K(), 64) - this.l.b;
      
      a(☃, ☃, 0, 0, 0, 58, ☃, 58, false);
      
      a(false, 0, ☃, ☃, ☃);
      
      a(true, 33, ☃, ☃, ☃);
      
      b(☃, ☃, ☃);
      
      c(☃, ☃, ☃);
      d(☃, ☃, ☃);
      
      e(☃, ☃, ☃);
      f(☃, ☃, ☃);
      g(☃, ☃, ☃);
      int ☃;
      for (int ☃ = 0; ☃ < 7; ☃++) {
        for (☃ = 0; ☃ < 7;)
        {
          if ((☃ == 0) && (☃ == 3)) {
            ☃ = 6;
          }
          int ☃ = ☃ * 9;
          int ☃ = ☃ * 9;
          for (int ☃ = 0; ☃ < 4; ☃++) {
            for (int ☃ = 0; ☃ < 4; ☃++)
            {
              a(☃, b, ☃ + ☃, 0, ☃ + ☃, ☃);
              b(☃, b, ☃ + ☃, -1, ☃ + ☃, ☃);
            }
          }
          if ((☃ == 0) || (☃ == 6)) {
            ☃++;
          } else {
            ☃ += 6;
          }
        }
      }
      for (int ☃ = 0; ☃ < 5; ☃++)
      {
        a(☃, ☃, -1 - ☃, 0 + ☃ * 2, -1 - ☃, -1 - ☃, 23, 58 + ☃, false);
        a(☃, ☃, 58 + ☃, 0 + ☃ * 2, -1 - ☃, 58 + ☃, 23, 58 + ☃, false);
        a(☃, ☃, 0 - ☃, 0 + ☃ * 2, -1 - ☃, 57 + ☃, 23, -1 - ☃, false);
        a(☃, ☃, 0 - ☃, 0 + ☃ * 2, 58 + ☃, 57 + ☃, 23, 58 + ☃, false);
      }
      for (avy.r ☃ : this.p) {
        if (☃.c().a(☃)) {
          ☃.a(☃, ☃, ☃);
        }
      }
      return true;
    }
    
    private void a(boolean ☃, int ☃, aht ☃, Random ☃, avp ☃)
    {
      int ☃ = 24;
      if (a(☃, ☃, 0, ☃ + 23, 20))
      {
        a(☃, ☃, ☃ + 0, 0, 0, ☃ + 24, 0, 20, a, a, false);
        
        a(☃, ☃, ☃ + 0, 1, 0, ☃ + 24, 10, 20, false);
        for (int ☃ = 0; ☃ < 4; ☃++)
        {
          a(☃, ☃, ☃ + ☃, ☃ + 1, ☃, ☃ + ☃, ☃ + 1, 20, b, b, false);
          a(☃, ☃, ☃ + ☃ + 7, ☃ + 5, ☃ + 7, ☃ + ☃ + 7, ☃ + 5, 20, b, b, false);
          a(☃, ☃, ☃ + 17 - ☃, ☃ + 5, ☃ + 7, ☃ + 17 - ☃, ☃ + 5, 20, b, b, false);
          a(☃, ☃, ☃ + 24 - ☃, ☃ + 1, ☃, ☃ + 24 - ☃, ☃ + 1, 20, b, b, false);
          
          a(☃, ☃, ☃ + ☃ + 1, ☃ + 1, ☃, ☃ + 23 - ☃, ☃ + 1, ☃, b, b, false);
          a(☃, ☃, ☃ + ☃ + 8, ☃ + 5, ☃ + 7, ☃ + 16 - ☃, ☃ + 5, ☃ + 7, b, b, false);
        }
        a(☃, ☃, ☃ + 4, 4, 4, ☃ + 6, 4, 20, a, a, false);
        a(☃, ☃, ☃ + 7, 4, 4, ☃ + 17, 4, 6, a, a, false);
        a(☃, ☃, ☃ + 18, 4, 4, ☃ + 20, 4, 20, a, a, false);
        a(☃, ☃, ☃ + 11, 8, 11, ☃ + 13, 8, 20, a, a, false);
        a(☃, d, ☃ + 12, 9, 12, ☃);
        a(☃, d, ☃ + 12, 9, 15, ☃);
        a(☃, d, ☃ + 12, 9, 18, ☃);
        
        int ☃ = ☃ ? ☃ + 19 : ☃ + 5;
        int ☃ = ☃ ? ☃ + 5 : ☃ + 19;
        for (int ☃ = 20; ☃ >= 5; ☃ -= 3) {
          a(☃, d, ☃, 5, ☃, ☃);
        }
        for (int ☃ = 19; ☃ >= 7; ☃ -= 3) {
          a(☃, d, ☃, 5, ☃, ☃);
        }
        for (int ☃ = 0; ☃ < 4; ☃++)
        {
          int ☃ = ☃ ? ☃ + (24 - (17 - ☃ * 3)) : ☃ + 17 - ☃ * 3;
          a(☃, d, ☃, 5, 5, ☃);
        }
        a(☃, d, ☃, 5, 5, ☃);
        
        a(☃, ☃, ☃ + 11, 1, 12, ☃ + 13, 7, 12, a, a, false);
        a(☃, ☃, ☃ + 12, 1, 11, ☃ + 12, 7, 13, a, a, false);
      }
    }
    
    private void b(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, 22, 5, 35, 17))
      {
        a(☃, ☃, 25, 0, 0, 32, 8, 20, false);
        for (int ☃ = 0; ☃ < 4; ☃++)
        {
          a(☃, ☃, 24, 2, 5 + ☃ * 4, 24, 4, 5 + ☃ * 4, b, b, false);
          a(☃, ☃, 22, 4, 5 + ☃ * 4, 23, 4, 5 + ☃ * 4, b, b, false);
          a(☃, b, 25, 5, 5 + ☃ * 4, ☃);
          a(☃, b, 26, 6, 5 + ☃ * 4, ☃);
          a(☃, e, 26, 5, 5 + ☃ * 4, ☃);
          
          a(☃, ☃, 33, 2, 5 + ☃ * 4, 33, 4, 5 + ☃ * 4, b, b, false);
          a(☃, ☃, 34, 4, 5 + ☃ * 4, 35, 4, 5 + ☃ * 4, b, b, false);
          a(☃, b, 32, 5, 5 + ☃ * 4, ☃);
          a(☃, b, 31, 6, 5 + ☃ * 4, ☃);
          a(☃, e, 31, 5, 5 + ☃ * 4, ☃);
          
          a(☃, ☃, 27, 6, 5 + ☃ * 4, 30, 6, 5 + ☃ * 4, a, a, false);
        }
      }
    }
    
    private void c(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, 15, 20, 42, 21))
      {
        a(☃, ☃, 15, 0, 21, 42, 0, 21, a, a, false);
        
        a(☃, ☃, 26, 1, 21, 31, 3, 21, false);
        
        a(☃, ☃, 21, 12, 21, 36, 12, 21, a, a, false);
        a(☃, ☃, 17, 11, 21, 40, 11, 21, a, a, false);
        a(☃, ☃, 16, 10, 21, 41, 10, 21, a, a, false);
        a(☃, ☃, 15, 7, 21, 42, 9, 21, a, a, false);
        a(☃, ☃, 16, 6, 21, 41, 6, 21, a, a, false);
        a(☃, ☃, 17, 5, 21, 40, 5, 21, a, a, false);
        a(☃, ☃, 21, 4, 21, 36, 4, 21, a, a, false);
        a(☃, ☃, 22, 3, 21, 26, 3, 21, a, a, false);
        a(☃, ☃, 31, 3, 21, 35, 3, 21, a, a, false);
        a(☃, ☃, 23, 2, 21, 25, 2, 21, a, a, false);
        a(☃, ☃, 32, 2, 21, 34, 2, 21, a, a, false);
        
        a(☃, ☃, 28, 4, 20, 29, 4, 21, b, b, false);
        a(☃, b, 27, 3, 21, ☃);
        a(☃, b, 30, 3, 21, ☃);
        a(☃, b, 26, 2, 21, ☃);
        a(☃, b, 31, 2, 21, ☃);
        a(☃, b, 25, 1, 21, ☃);
        a(☃, b, 32, 1, 21, ☃);
        for (int ☃ = 0; ☃ < 7; ☃++)
        {
          a(☃, c, 28 - ☃, 6 + ☃, 21, ☃);
          a(☃, c, 29 + ☃, 6 + ☃, 21, ☃);
        }
        for (int ☃ = 0; ☃ < 4; ☃++)
        {
          a(☃, c, 28 - ☃, 9 + ☃, 21, ☃);
          a(☃, c, 29 + ☃, 9 + ☃, 21, ☃);
        }
        a(☃, c, 28, 12, 21, ☃);
        a(☃, c, 29, 12, 21, ☃);
        for (int ☃ = 0; ☃ < 3; ☃++)
        {
          a(☃, c, 22 - ☃ * 2, 8, 21, ☃);
          a(☃, c, 22 - ☃ * 2, 9, 21, ☃);
          
          a(☃, c, 35 + ☃ * 2, 8, 21, ☃);
          a(☃, c, 35 + ☃ * 2, 9, 21, ☃);
        }
        a(☃, ☃, 15, 13, 21, 42, 15, 21, false);
        a(☃, ☃, 15, 1, 21, 15, 6, 21, false);
        a(☃, ☃, 16, 1, 21, 16, 5, 21, false);
        a(☃, ☃, 17, 1, 21, 20, 4, 21, false);
        a(☃, ☃, 21, 1, 21, 21, 3, 21, false);
        a(☃, ☃, 22, 1, 21, 22, 2, 21, false);
        a(☃, ☃, 23, 1, 21, 24, 1, 21, false);
        a(☃, ☃, 42, 1, 21, 42, 6, 21, false);
        a(☃, ☃, 41, 1, 21, 41, 5, 21, false);
        a(☃, ☃, 37, 1, 21, 40, 4, 21, false);
        a(☃, ☃, 36, 1, 21, 36, 3, 21, false);
        a(☃, ☃, 33, 1, 21, 34, 1, 21, false);
        a(☃, ☃, 35, 1, 21, 35, 2, 21, false);
      }
    }
    
    private void d(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, 21, 21, 36, 36))
      {
        a(☃, ☃, 21, 0, 22, 36, 0, 36, a, a, false);
        
        a(☃, ☃, 21, 1, 22, 36, 23, 36, false);
        for (int ☃ = 0; ☃ < 4; ☃++)
        {
          a(☃, ☃, 21 + ☃, 13 + ☃, 21 + ☃, 36 - ☃, 13 + ☃, 21 + ☃, b, b, false);
          a(☃, ☃, 21 + ☃, 13 + ☃, 36 - ☃, 36 - ☃, 13 + ☃, 36 - ☃, b, b, false);
          a(☃, ☃, 21 + ☃, 13 + ☃, 22 + ☃, 21 + ☃, 13 + ☃, 35 - ☃, b, b, false);
          a(☃, ☃, 36 - ☃, 13 + ☃, 22 + ☃, 36 - ☃, 13 + ☃, 35 - ☃, b, b, false);
        }
        a(☃, ☃, 25, 16, 25, 32, 16, 32, a, a, false);
        a(☃, ☃, 25, 17, 25, 25, 19, 25, b, b, false);
        a(☃, ☃, 32, 17, 25, 32, 19, 25, b, b, false);
        a(☃, ☃, 25, 17, 32, 25, 19, 32, b, b, false);
        a(☃, ☃, 32, 17, 32, 32, 19, 32, b, b, false);
        
        a(☃, b, 26, 20, 26, ☃);
        a(☃, b, 27, 21, 27, ☃);
        a(☃, e, 27, 20, 27, ☃);
        a(☃, b, 26, 20, 31, ☃);
        a(☃, b, 27, 21, 30, ☃);
        a(☃, e, 27, 20, 30, ☃);
        a(☃, b, 31, 20, 31, ☃);
        a(☃, b, 30, 21, 30, ☃);
        a(☃, e, 30, 20, 30, ☃);
        a(☃, b, 31, 20, 26, ☃);
        a(☃, b, 30, 21, 27, ☃);
        a(☃, e, 30, 20, 27, ☃);
        
        a(☃, ☃, 28, 21, 27, 29, 21, 27, a, a, false);
        a(☃, ☃, 27, 21, 28, 27, 21, 29, a, a, false);
        a(☃, ☃, 28, 21, 30, 29, 21, 30, a, a, false);
        a(☃, ☃, 30, 21, 28, 30, 21, 29, a, a, false);
      }
    }
    
    private void e(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, 0, 21, 6, 58))
      {
        a(☃, ☃, 0, 0, 21, 6, 0, 57, a, a, false);
        
        a(☃, ☃, 0, 1, 21, 6, 7, 57, false);
        
        a(☃, ☃, 4, 4, 21, 6, 4, 53, a, a, false);
        for (int ☃ = 0; ☃ < 4; ☃++) {
          a(☃, ☃, ☃, ☃ + 1, 21, ☃, ☃ + 1, 57 - ☃, b, b, false);
        }
        for (int ☃ = 23; ☃ < 53; ☃ += 3) {
          a(☃, d, 5, 5, ☃, ☃);
        }
        a(☃, d, 5, 5, 52, ☃);
        for (int ☃ = 0; ☃ < 4; ☃++) {
          a(☃, ☃, ☃, ☃ + 1, 21, ☃, ☃ + 1, 57 - ☃, b, b, false);
        }
        a(☃, ☃, 4, 1, 52, 6, 3, 52, a, a, false);
        a(☃, ☃, 5, 1, 51, 5, 3, 53, a, a, false);
      }
      if (a(☃, 51, 21, 58, 58))
      {
        a(☃, ☃, 51, 0, 21, 57, 0, 57, a, a, false);
        
        a(☃, ☃, 51, 1, 21, 57, 7, 57, false);
        
        a(☃, ☃, 51, 4, 21, 53, 4, 53, a, a, false);
        for (int ☃ = 0; ☃ < 4; ☃++) {
          a(☃, ☃, 57 - ☃, ☃ + 1, 21, 57 - ☃, ☃ + 1, 57 - ☃, b, b, false);
        }
        for (int ☃ = 23; ☃ < 53; ☃ += 3) {
          a(☃, d, 52, 5, ☃, ☃);
        }
        a(☃, d, 52, 5, 52, ☃);
        
        a(☃, ☃, 51, 1, 52, 53, 3, 52, a, a, false);
        a(☃, ☃, 52, 1, 51, 52, 3, 53, a, a, false);
      }
      if (a(☃, 0, 51, 57, 57))
      {
        a(☃, ☃, 7, 0, 51, 50, 0, 57, a, a, false);
        
        a(☃, ☃, 7, 1, 51, 50, 10, 57, false);
        for (int ☃ = 0; ☃ < 4; ☃++) {
          a(☃, ☃, ☃ + 1, ☃ + 1, 57 - ☃, 56 - ☃, ☃ + 1, 57 - ☃, b, b, false);
        }
      }
    }
    
    private void f(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, 7, 21, 13, 50))
      {
        a(☃, ☃, 7, 0, 21, 13, 0, 50, a, a, false);
        
        a(☃, ☃, 7, 1, 21, 13, 10, 50, false);
        
        a(☃, ☃, 11, 8, 21, 13, 8, 53, a, a, false);
        for (int ☃ = 0; ☃ < 4; ☃++) {
          a(☃, ☃, ☃ + 7, ☃ + 5, 21, ☃ + 7, ☃ + 5, 54, b, b, false);
        }
        for (int ☃ = 21; ☃ <= 45; ☃ += 3) {
          a(☃, d, 12, 9, ☃, ☃);
        }
      }
      if (a(☃, 44, 21, 50, 54))
      {
        a(☃, ☃, 44, 0, 21, 50, 0, 50, a, a, false);
        
        a(☃, ☃, 44, 1, 21, 50, 10, 50, false);
        
        a(☃, ☃, 44, 8, 21, 46, 8, 53, a, a, false);
        for (int ☃ = 0; ☃ < 4; ☃++) {
          a(☃, ☃, 50 - ☃, ☃ + 5, 21, 50 - ☃, ☃ + 5, 54, b, b, false);
        }
        for (int ☃ = 21; ☃ <= 45; ☃ += 3) {
          a(☃, d, 45, 9, ☃, ☃);
        }
      }
      if (a(☃, 8, 44, 49, 54))
      {
        a(☃, ☃, 14, 0, 44, 43, 0, 50, a, a, false);
        
        a(☃, ☃, 14, 1, 44, 43, 10, 50, false);
        for (int ☃ = 12; ☃ <= 45; ☃ += 3)
        {
          a(☃, d, ☃, 9, 45, ☃);
          a(☃, d, ☃, 9, 52, ☃);
          if ((☃ == 12) || (☃ == 18) || (☃ == 24) || (☃ == 33) || (☃ == 39) || (☃ == 45))
          {
            a(☃, d, ☃, 9, 47, ☃);
            a(☃, d, ☃, 9, 50, ☃);
            a(☃, d, ☃, 10, 45, ☃);
            a(☃, d, ☃, 10, 46, ☃);
            a(☃, d, ☃, 10, 51, ☃);
            a(☃, d, ☃, 10, 52, ☃);
            a(☃, d, ☃, 11, 47, ☃);
            a(☃, d, ☃, 11, 50, ☃);
            a(☃, d, ☃, 12, 48, ☃);
            a(☃, d, ☃, 12, 49, ☃);
          }
        }
        for (int ☃ = 0; ☃ < 3; ☃++) {
          a(☃, ☃, 8 + ☃, 5 + ☃, 54, 49 - ☃, 5 + ☃, 54, a, a, false);
        }
        a(☃, ☃, 11, 8, 54, 46, 8, 54, b, b, false);
        a(☃, ☃, 14, 8, 44, 43, 8, 53, a, a, false);
      }
    }
    
    private void g(aht ☃, Random ☃, avp ☃)
    {
      if (a(☃, 14, 21, 20, 43))
      {
        a(☃, ☃, 14, 0, 21, 20, 0, 43, a, a, false);
        
        a(☃, ☃, 14, 1, 22, 20, 14, 43, false);
        
        a(☃, ☃, 18, 12, 22, 20, 12, 39, a, a, false);
        a(☃, ☃, 18, 12, 21, 20, 12, 21, b, b, false);
        for (int ☃ = 0; ☃ < 4; ☃++) {
          a(☃, ☃, ☃ + 14, ☃ + 9, 21, ☃ + 14, ☃ + 9, 43 - ☃, b, b, false);
        }
        for (int ☃ = 23; ☃ <= 39; ☃ += 3) {
          a(☃, d, 19, 13, ☃, ☃);
        }
      }
      if (a(☃, 37, 21, 43, 43))
      {
        a(☃, ☃, 37, 0, 21, 43, 0, 43, a, a, false);
        
        a(☃, ☃, 37, 1, 22, 43, 14, 43, false);
        
        a(☃, ☃, 37, 12, 22, 39, 12, 39, a, a, false);
        a(☃, ☃, 37, 12, 21, 39, 12, 21, b, b, false);
        for (int ☃ = 0; ☃ < 4; ☃++) {
          a(☃, ☃, 43 - ☃, ☃ + 9, 21, 43 - ☃, ☃ + 9, 43 - ☃, b, b, false);
        }
        for (int ☃ = 23; ☃ <= 39; ☃ += 3) {
          a(☃, d, 38, 13, ☃, ☃);
        }
      }
      if (a(☃, 15, 37, 42, 43))
      {
        a(☃, ☃, 21, 0, 37, 36, 0, 43, a, a, false);
        
        a(☃, ☃, 21, 1, 37, 36, 14, 43, false);
        
        a(☃, ☃, 21, 12, 37, 36, 12, 39, a, a, false);
        for (int ☃ = 0; ☃ < 4; ☃++) {
          a(☃, ☃, 15 + ☃, ☃ + 9, 43 - ☃, 42 - ☃, ☃ + 9, 43 - ☃, b, b, false);
        }
        for (int ☃ = 21; ☃ <= 36; ☃ += 3) {
          a(☃, d, ☃, 13, 38, ☃);
        }
      }
    }
  }
  
  public static class p
    extends avy.r
  {
    public p() {}
    
    public p(cq ☃, avy.v ☃)
    {
      super(☃, ☃, 1, 1, 1);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      a(☃, ☃, 0, 3, 0, 2, 3, 7, b, b, false);
      a(☃, ☃, 5, 3, 0, 7, 3, 7, b, b, false);
      a(☃, ☃, 0, 2, 0, 1, 2, 7, b, b, false);
      a(☃, ☃, 6, 2, 0, 7, 2, 7, b, b, false);
      a(☃, ☃, 0, 1, 0, 0, 1, 7, b, b, false);
      a(☃, ☃, 7, 1, 0, 7, 1, 7, b, b, false);
      
      a(☃, ☃, 0, 1, 7, 7, 3, 7, b, b, false);
      
      a(☃, ☃, 1, 1, 0, 2, 3, 0, b, b, false);
      a(☃, ☃, 5, 1, 0, 6, 3, 0, b, b, false);
      if (this.k.c[cq.c.a()] != 0) {
        a(☃, ☃, 3, 1, 7, 4, 2, 7, false);
      }
      if (this.k.c[cq.e.a()] != 0) {
        a(☃, ☃, 0, 1, 3, 1, 2, 4, false);
      }
      if (this.k.c[cq.f.a()] != 0) {
        a(☃, ☃, 6, 1, 3, 7, 2, 4, false);
      }
      return true;
    }
  }
  
  public static class s
    extends avy.r
  {
    private int n;
    
    public s() {}
    
    public s(cq ☃, avy.v ☃, Random ☃)
    {
      super(☃, ☃, 1, 1, 1);
      this.n = ☃.nextInt(3);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (this.k.a / 25 > 0) {
        a(☃, ☃, 0, 0, this.k.c[cq.a.a()]);
      }
      if (this.k.b[cq.b.a()] == null) {
        a(☃, ☃, 1, 4, 1, 6, 4, 6, a);
      }
      boolean ☃ = (this.n != 0) && (☃.nextBoolean()) && (this.k.c[cq.a.a()] == 0) && (this.k.c[cq.b.a()] == 0) && (this.k.c() > 1);
      if (this.n == 0)
      {
        a(☃, ☃, 0, 1, 0, 2, 1, 2, b, b, false);
        a(☃, ☃, 0, 3, 0, 2, 3, 2, b, b, false);
        a(☃, ☃, 0, 2, 0, 0, 2, 2, a, a, false);
        a(☃, ☃, 1, 2, 0, 2, 2, 0, a, a, false);
        a(☃, e, 1, 2, 1, ☃);
        
        a(☃, ☃, 5, 1, 0, 7, 1, 2, b, b, false);
        a(☃, ☃, 5, 3, 0, 7, 3, 2, b, b, false);
        a(☃, ☃, 7, 2, 0, 7, 2, 2, a, a, false);
        a(☃, ☃, 5, 2, 0, 6, 2, 0, a, a, false);
        a(☃, e, 6, 2, 1, ☃);
        
        a(☃, ☃, 0, 1, 5, 2, 1, 7, b, b, false);
        a(☃, ☃, 0, 3, 5, 2, 3, 7, b, b, false);
        a(☃, ☃, 0, 2, 5, 0, 2, 7, a, a, false);
        a(☃, ☃, 1, 2, 7, 2, 2, 7, a, a, false);
        a(☃, e, 1, 2, 6, ☃);
        
        a(☃, ☃, 5, 1, 5, 7, 1, 7, b, b, false);
        a(☃, ☃, 5, 3, 5, 7, 3, 7, b, b, false);
        a(☃, ☃, 7, 2, 5, 7, 2, 7, a, a, false);
        a(☃, ☃, 5, 2, 7, 6, 2, 7, a, a, false);
        a(☃, e, 6, 2, 6, ☃);
        if (this.k.c[cq.d.a()] != 0)
        {
          a(☃, ☃, 3, 3, 0, 4, 3, 0, b, b, false);
        }
        else
        {
          a(☃, ☃, 3, 3, 0, 4, 3, 1, b, b, false);
          a(☃, ☃, 3, 2, 0, 4, 2, 0, a, a, false);
          a(☃, ☃, 3, 1, 0, 4, 1, 1, b, b, false);
        }
        if (this.k.c[cq.c.a()] != 0)
        {
          a(☃, ☃, 3, 3, 7, 4, 3, 7, b, b, false);
        }
        else
        {
          a(☃, ☃, 3, 3, 6, 4, 3, 7, b, b, false);
          a(☃, ☃, 3, 2, 7, 4, 2, 7, a, a, false);
          a(☃, ☃, 3, 1, 6, 4, 1, 7, b, b, false);
        }
        if (this.k.c[cq.e.a()] != 0)
        {
          a(☃, ☃, 0, 3, 3, 0, 3, 4, b, b, false);
        }
        else
        {
          a(☃, ☃, 0, 3, 3, 1, 3, 4, b, b, false);
          a(☃, ☃, 0, 2, 3, 0, 2, 4, a, a, false);
          a(☃, ☃, 0, 1, 3, 1, 1, 4, b, b, false);
        }
        if (this.k.c[cq.f.a()] != 0)
        {
          a(☃, ☃, 7, 3, 3, 7, 3, 4, b, b, false);
        }
        else
        {
          a(☃, ☃, 6, 3, 3, 7, 3, 4, b, b, false);
          a(☃, ☃, 7, 2, 3, 7, 2, 4, a, a, false);
          a(☃, ☃, 6, 1, 3, 7, 1, 4, b, b, false);
        }
      }
      else if (this.n == 1)
      {
        a(☃, ☃, 2, 1, 2, 2, 3, 2, b, b, false);
        a(☃, ☃, 2, 1, 5, 2, 3, 5, b, b, false);
        a(☃, ☃, 5, 1, 5, 5, 3, 5, b, b, false);
        a(☃, ☃, 5, 1, 2, 5, 3, 2, b, b, false);
        a(☃, e, 2, 2, 2, ☃);
        a(☃, e, 2, 2, 5, ☃);
        a(☃, e, 5, 2, 5, ☃);
        a(☃, e, 5, 2, 2, ☃);
        
        a(☃, ☃, 0, 1, 0, 1, 3, 0, b, b, false);
        a(☃, ☃, 0, 1, 1, 0, 3, 1, b, b, false);
        a(☃, ☃, 0, 1, 7, 1, 3, 7, b, b, false);
        a(☃, ☃, 0, 1, 6, 0, 3, 6, b, b, false);
        a(☃, ☃, 6, 1, 7, 7, 3, 7, b, b, false);
        a(☃, ☃, 7, 1, 6, 7, 3, 6, b, b, false);
        a(☃, ☃, 6, 1, 0, 7, 3, 0, b, b, false);
        a(☃, ☃, 7, 1, 1, 7, 3, 1, b, b, false);
        a(☃, a, 1, 2, 0, ☃);
        a(☃, a, 0, 2, 1, ☃);
        a(☃, a, 1, 2, 7, ☃);
        a(☃, a, 0, 2, 6, ☃);
        a(☃, a, 6, 2, 7, ☃);
        a(☃, a, 7, 2, 6, ☃);
        a(☃, a, 6, 2, 0, ☃);
        a(☃, a, 7, 2, 1, ☃);
        if (this.k.c[cq.d.a()] == 0)
        {
          a(☃, ☃, 1, 3, 0, 6, 3, 0, b, b, false);
          a(☃, ☃, 1, 2, 0, 6, 2, 0, a, a, false);
          a(☃, ☃, 1, 1, 0, 6, 1, 0, b, b, false);
        }
        if (this.k.c[cq.c.a()] == 0)
        {
          a(☃, ☃, 1, 3, 7, 6, 3, 7, b, b, false);
          a(☃, ☃, 1, 2, 7, 6, 2, 7, a, a, false);
          a(☃, ☃, 1, 1, 7, 6, 1, 7, b, b, false);
        }
        if (this.k.c[cq.e.a()] == 0)
        {
          a(☃, ☃, 0, 3, 1, 0, 3, 6, b, b, false);
          a(☃, ☃, 0, 2, 1, 0, 2, 6, a, a, false);
          a(☃, ☃, 0, 1, 1, 0, 1, 6, b, b, false);
        }
        if (this.k.c[cq.f.a()] == 0)
        {
          a(☃, ☃, 7, 3, 1, 7, 3, 6, b, b, false);
          a(☃, ☃, 7, 2, 1, 7, 2, 6, a, a, false);
          a(☃, ☃, 7, 1, 1, 7, 1, 6, b, b, false);
        }
      }
      else if (this.n == 2)
      {
        a(☃, ☃, 0, 1, 0, 0, 1, 7, b, b, false);
        a(☃, ☃, 7, 1, 0, 7, 1, 7, b, b, false);
        a(☃, ☃, 1, 1, 0, 6, 1, 0, b, b, false);
        a(☃, ☃, 1, 1, 7, 6, 1, 7, b, b, false);
        
        a(☃, ☃, 0, 2, 0, 0, 2, 7, c, c, false);
        a(☃, ☃, 7, 2, 0, 7, 2, 7, c, c, false);
        a(☃, ☃, 1, 2, 0, 6, 2, 0, c, c, false);
        a(☃, ☃, 1, 2, 7, 6, 2, 7, c, c, false);
        
        a(☃, ☃, 0, 3, 0, 0, 3, 7, b, b, false);
        a(☃, ☃, 7, 3, 0, 7, 3, 7, b, b, false);
        a(☃, ☃, 1, 3, 0, 6, 3, 0, b, b, false);
        a(☃, ☃, 1, 3, 7, 6, 3, 7, b, b, false);
        
        a(☃, ☃, 0, 1, 3, 0, 2, 4, c, c, false);
        a(☃, ☃, 7, 1, 3, 7, 2, 4, c, c, false);
        a(☃, ☃, 3, 1, 0, 4, 2, 0, c, c, false);
        a(☃, ☃, 3, 1, 7, 4, 2, 7, c, c, false);
        if (this.k.c[cq.d.a()] != 0) {
          a(☃, ☃, 3, 1, 0, 4, 2, 0, false);
        }
        if (this.k.c[cq.c.a()] != 0) {
          a(☃, ☃, 3, 1, 7, 4, 2, 7, false);
        }
        if (this.k.c[cq.e.a()] != 0) {
          a(☃, ☃, 0, 1, 3, 0, 2, 4, false);
        }
        if (this.k.c[cq.f.a()] != 0) {
          a(☃, ☃, 7, 1, 3, 7, 2, 4, false);
        }
      }
      if (☃)
      {
        a(☃, ☃, 3, 1, 3, 4, 1, 4, b, b, false);
        a(☃, ☃, 3, 2, 3, 4, 2, 4, a, a, false);
        a(☃, ☃, 3, 3, 3, 4, 3, 4, b, b, false);
      }
      return true;
    }
  }
  
  public static class t
    extends avy.r
  {
    public t() {}
    
    public t(cq ☃, avy.v ☃, Random ☃)
    {
      super(☃, ☃, 1, 1, 1);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (this.k.a / 25 > 0) {
        a(☃, ☃, 0, 0, this.k.c[cq.a.a()]);
      }
      if (this.k.b[cq.b.a()] == null) {
        a(☃, ☃, 1, 4, 1, 6, 4, 6, a);
      }
      for (int ☃ = 1; ☃ <= 6; ☃++) {
        for (int ☃ = 1; ☃ <= 6; ☃++) {
          if (☃.nextInt(3) != 0)
          {
            int ☃ = 2 + (☃.nextInt(4) == 0 ? 0 : 1);
            a(☃, ☃, ☃, ☃, ☃, ☃, 3, ☃, aju.v.a(1), aju.v.a(1), false);
          }
        }
      }
      a(☃, ☃, 0, 1, 0, 0, 1, 7, b, b, false);
      a(☃, ☃, 7, 1, 0, 7, 1, 7, b, b, false);
      a(☃, ☃, 1, 1, 0, 6, 1, 0, b, b, false);
      a(☃, ☃, 1, 1, 7, 6, 1, 7, b, b, false);
      
      a(☃, ☃, 0, 2, 0, 0, 2, 7, c, c, false);
      a(☃, ☃, 7, 2, 0, 7, 2, 7, c, c, false);
      a(☃, ☃, 1, 2, 0, 6, 2, 0, c, c, false);
      a(☃, ☃, 1, 2, 7, 6, 2, 7, c, c, false);
      
      a(☃, ☃, 0, 3, 0, 0, 3, 7, b, b, false);
      a(☃, ☃, 7, 3, 0, 7, 3, 7, b, b, false);
      a(☃, ☃, 1, 3, 0, 6, 3, 0, b, b, false);
      a(☃, ☃, 1, 3, 7, 6, 3, 7, b, b, false);
      
      a(☃, ☃, 0, 1, 3, 0, 2, 4, c, c, false);
      a(☃, ☃, 7, 1, 3, 7, 2, 4, c, c, false);
      a(☃, ☃, 3, 1, 0, 4, 2, 0, c, c, false);
      a(☃, ☃, 3, 1, 7, 4, 2, 7, c, c, false);
      if (this.k.c[cq.d.a()] != 0) {
        a(☃, ☃, 3, 1, 0, 4, 2, 0, false);
      }
      return true;
    }
  }
  
  public static class m
    extends avy.r
  {
    public m() {}
    
    public m(cq ☃, avy.v ☃, Random ☃)
    {
      super(☃, ☃, 1, 2, 1);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (this.k.a / 25 > 0) {
        a(☃, ☃, 0, 0, this.k.c[cq.a.a()]);
      }
      avy.v ☃ = this.k.b[cq.b.a()];
      if (☃.b[cq.b.a()] == null) {
        a(☃, ☃, 1, 8, 1, 6, 8, 6, a);
      }
      a(☃, ☃, 0, 4, 0, 0, 4, 7, b, b, false);
      a(☃, ☃, 7, 4, 0, 7, 4, 7, b, b, false);
      a(☃, ☃, 1, 4, 0, 6, 4, 0, b, b, false);
      a(☃, ☃, 1, 4, 7, 6, 4, 7, b, b, false);
      
      a(☃, ☃, 2, 4, 1, 2, 4, 2, b, b, false);
      a(☃, ☃, 1, 4, 2, 1, 4, 2, b, b, false);
      a(☃, ☃, 5, 4, 1, 5, 4, 2, b, b, false);
      a(☃, ☃, 6, 4, 2, 6, 4, 2, b, b, false);
      a(☃, ☃, 2, 4, 5, 2, 4, 6, b, b, false);
      a(☃, ☃, 1, 4, 5, 1, 4, 5, b, b, false);
      a(☃, ☃, 5, 4, 5, 5, 4, 6, b, b, false);
      a(☃, ☃, 6, 4, 5, 6, 4, 5, b, b, false);
      
      avy.v ☃ = this.k;
      for (int ☃ = 1; ☃ <= 5; ☃ += 4)
      {
        int ☃ = 0;
        if (☃.c[cq.d.a()] != 0)
        {
          a(☃, ☃, 2, ☃, ☃, 2, ☃ + 2, ☃, b, b, false);
          a(☃, ☃, 5, ☃, ☃, 5, ☃ + 2, ☃, b, b, false);
          a(☃, ☃, 3, ☃ + 2, ☃, 4, ☃ + 2, ☃, b, b, false);
        }
        else
        {
          a(☃, ☃, 0, ☃, ☃, 7, ☃ + 2, ☃, b, b, false);
          a(☃, ☃, 0, ☃ + 1, ☃, 7, ☃ + 1, ☃, a, a, false);
        }
        ☃ = 7;
        if (☃.c[cq.c.a()] != 0)
        {
          a(☃, ☃, 2, ☃, ☃, 2, ☃ + 2, ☃, b, b, false);
          a(☃, ☃, 5, ☃, ☃, 5, ☃ + 2, ☃, b, b, false);
          a(☃, ☃, 3, ☃ + 2, ☃, 4, ☃ + 2, ☃, b, b, false);
        }
        else
        {
          a(☃, ☃, 0, ☃, ☃, 7, ☃ + 2, ☃, b, b, false);
          a(☃, ☃, 0, ☃ + 1, ☃, 7, ☃ + 1, ☃, a, a, false);
        }
        int ☃ = 0;
        if (☃.c[cq.e.a()] != 0)
        {
          a(☃, ☃, ☃, ☃, 2, ☃, ☃ + 2, 2, b, b, false);
          a(☃, ☃, ☃, ☃, 5, ☃, ☃ + 2, 5, b, b, false);
          a(☃, ☃, ☃, ☃ + 2, 3, ☃, ☃ + 2, 4, b, b, false);
        }
        else
        {
          a(☃, ☃, ☃, ☃, 0, ☃, ☃ + 2, 7, b, b, false);
          a(☃, ☃, ☃, ☃ + 1, 0, ☃, ☃ + 1, 7, a, a, false);
        }
        ☃ = 7;
        if (☃.c[cq.f.a()] != 0)
        {
          a(☃, ☃, ☃, ☃, 2, ☃, ☃ + 2, 2, b, b, false);
          a(☃, ☃, ☃, ☃, 5, ☃, ☃ + 2, 5, b, b, false);
          a(☃, ☃, ☃, ☃ + 2, 3, ☃, ☃ + 2, 4, b, b, false);
        }
        else
        {
          a(☃, ☃, ☃, ☃, 0, ☃, ☃ + 2, 7, b, b, false);
          a(☃, ☃, ☃, ☃ + 1, 0, ☃, ☃ + 1, 7, a, a, false);
        }
        ☃ = ☃;
      }
      return true;
    }
  }
  
  public static class k
    extends avy.r
  {
    public k() {}
    
    public k(cq ☃, avy.v ☃, Random ☃)
    {
      super(☃, ☃, 2, 1, 1);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      avy.v ☃ = this.k.b[cq.f.a()];
      avy.v ☃ = this.k;
      if (this.k.a / 25 > 0)
      {
        a(☃, ☃, 8, 0, ☃.c[cq.a.a()]);
        a(☃, ☃, 0, 0, ☃.c[cq.a.a()]);
      }
      if (☃.b[cq.b.a()] == null) {
        a(☃, ☃, 1, 4, 1, 7, 4, 6, a);
      }
      if (☃.b[cq.b.a()] == null) {
        a(☃, ☃, 8, 4, 1, 14, 4, 6, a);
      }
      a(☃, ☃, 0, 3, 0, 0, 3, 7, b, b, false);
      a(☃, ☃, 15, 3, 0, 15, 3, 7, b, b, false);
      a(☃, ☃, 1, 3, 0, 15, 3, 0, b, b, false);
      a(☃, ☃, 1, 3, 7, 14, 3, 7, b, b, false);
      a(☃, ☃, 0, 2, 0, 0, 2, 7, a, a, false);
      a(☃, ☃, 15, 2, 0, 15, 2, 7, a, a, false);
      a(☃, ☃, 1, 2, 0, 15, 2, 0, a, a, false);
      a(☃, ☃, 1, 2, 7, 14, 2, 7, a, a, false);
      a(☃, ☃, 0, 1, 0, 0, 1, 7, b, b, false);
      a(☃, ☃, 15, 1, 0, 15, 1, 7, b, b, false);
      a(☃, ☃, 1, 1, 0, 15, 1, 0, b, b, false);
      a(☃, ☃, 1, 1, 7, 14, 1, 7, b, b, false);
      
      a(☃, ☃, 5, 1, 0, 10, 1, 4, b, b, false);
      a(☃, ☃, 6, 2, 0, 9, 2, 3, a, a, false);
      a(☃, ☃, 5, 3, 0, 10, 3, 4, b, b, false);
      
      a(☃, e, 6, 2, 3, ☃);
      a(☃, e, 9, 2, 3, ☃);
      if (☃.c[cq.d.a()] != 0) {
        a(☃, ☃, 3, 1, 0, 4, 2, 0, false);
      }
      if (☃.c[cq.c.a()] != 0) {
        a(☃, ☃, 3, 1, 7, 4, 2, 7, false);
      }
      if (☃.c[cq.e.a()] != 0) {
        a(☃, ☃, 0, 1, 3, 0, 2, 4, false);
      }
      if (☃.c[cq.d.a()] != 0) {
        a(☃, ☃, 11, 1, 0, 12, 2, 0, false);
      }
      if (☃.c[cq.c.a()] != 0) {
        a(☃, ☃, 11, 1, 7, 12, 2, 7, false);
      }
      if (☃.c[cq.f.a()] != 0) {
        a(☃, ☃, 15, 1, 3, 15, 2, 4, false);
      }
      return true;
    }
  }
  
  public static class o
    extends avy.r
  {
    public o() {}
    
    public o(cq ☃, avy.v ☃, Random ☃)
    {
      super(☃, ☃, 1, 1, 2);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      avy.v ☃ = this.k.b[cq.c.a()];
      avy.v ☃ = this.k;
      if (this.k.a / 25 > 0)
      {
        a(☃, ☃, 0, 8, ☃.c[cq.a.a()]);
        a(☃, ☃, 0, 0, ☃.c[cq.a.a()]);
      }
      if (☃.b[cq.b.a()] == null) {
        a(☃, ☃, 1, 4, 1, 6, 4, 7, a);
      }
      if (☃.b[cq.b.a()] == null) {
        a(☃, ☃, 1, 4, 8, 6, 4, 14, a);
      }
      a(☃, ☃, 0, 3, 0, 0, 3, 15, b, b, false);
      a(☃, ☃, 7, 3, 0, 7, 3, 15, b, b, false);
      a(☃, ☃, 1, 3, 0, 7, 3, 0, b, b, false);
      a(☃, ☃, 1, 3, 15, 6, 3, 15, b, b, false);
      a(☃, ☃, 0, 2, 0, 0, 2, 15, a, a, false);
      a(☃, ☃, 7, 2, 0, 7, 2, 15, a, a, false);
      a(☃, ☃, 1, 2, 0, 7, 2, 0, a, a, false);
      a(☃, ☃, 1, 2, 15, 6, 2, 15, a, a, false);
      a(☃, ☃, 0, 1, 0, 0, 1, 15, b, b, false);
      a(☃, ☃, 7, 1, 0, 7, 1, 15, b, b, false);
      a(☃, ☃, 1, 1, 0, 7, 1, 0, b, b, false);
      a(☃, ☃, 1, 1, 15, 6, 1, 15, b, b, false);
      
      a(☃, ☃, 1, 1, 1, 1, 1, 2, b, b, false);
      a(☃, ☃, 6, 1, 1, 6, 1, 2, b, b, false);
      a(☃, ☃, 1, 3, 1, 1, 3, 2, b, b, false);
      a(☃, ☃, 6, 3, 1, 6, 3, 2, b, b, false);
      a(☃, ☃, 1, 1, 13, 1, 1, 14, b, b, false);
      a(☃, ☃, 6, 1, 13, 6, 1, 14, b, b, false);
      a(☃, ☃, 1, 3, 13, 1, 3, 14, b, b, false);
      a(☃, ☃, 6, 3, 13, 6, 3, 14, b, b, false);
      
      a(☃, ☃, 2, 1, 6, 2, 3, 6, b, b, false);
      a(☃, ☃, 5, 1, 6, 5, 3, 6, b, b, false);
      a(☃, ☃, 2, 1, 9, 2, 3, 9, b, b, false);
      a(☃, ☃, 5, 1, 9, 5, 3, 9, b, b, false);
      
      a(☃, ☃, 3, 2, 6, 4, 2, 6, b, b, false);
      a(☃, ☃, 3, 2, 9, 4, 2, 9, b, b, false);
      a(☃, ☃, 2, 2, 7, 2, 2, 8, b, b, false);
      a(☃, ☃, 5, 2, 7, 5, 2, 8, b, b, false);
      
      a(☃, e, 2, 2, 5, ☃);
      a(☃, e, 5, 2, 5, ☃);
      a(☃, e, 2, 2, 10, ☃);
      a(☃, e, 5, 2, 10, ☃);
      a(☃, b, 2, 3, 5, ☃);
      a(☃, b, 5, 3, 5, ☃);
      a(☃, b, 2, 3, 10, ☃);
      a(☃, b, 5, 3, 10, ☃);
      if (☃.c[cq.d.a()] != 0) {
        a(☃, ☃, 3, 1, 0, 4, 2, 0, false);
      }
      if (☃.c[cq.f.a()] != 0) {
        a(☃, ☃, 7, 1, 3, 7, 2, 4, false);
      }
      if (☃.c[cq.e.a()] != 0) {
        a(☃, ☃, 0, 1, 3, 0, 2, 4, false);
      }
      if (☃.c[cq.c.a()] != 0) {
        a(☃, ☃, 3, 1, 15, 4, 2, 15, false);
      }
      if (☃.c[cq.e.a()] != 0) {
        a(☃, ☃, 0, 1, 11, 0, 2, 12, false);
      }
      if (☃.c[cq.f.a()] != 0) {
        a(☃, ☃, 7, 1, 11, 7, 2, 12, false);
      }
      return true;
    }
  }
  
  public static class l
    extends avy.r
  {
    public l() {}
    
    public l(cq ☃, avy.v ☃, Random ☃)
    {
      super(☃, ☃, 2, 2, 1);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      avy.v ☃ = this.k.b[cq.f.a()];
      avy.v ☃ = this.k;
      avy.v ☃ = ☃.b[cq.b.a()];
      avy.v ☃ = ☃.b[cq.b.a()];
      if (this.k.a / 25 > 0)
      {
        a(☃, ☃, 8, 0, ☃.c[cq.a.a()]);
        a(☃, ☃, 0, 0, ☃.c[cq.a.a()]);
      }
      if (☃.b[cq.b.a()] == null) {
        a(☃, ☃, 1, 8, 1, 7, 8, 6, a);
      }
      if (☃.b[cq.b.a()] == null) {
        a(☃, ☃, 8, 8, 1, 14, 8, 6, a);
      }
      for (int ☃ = 1; ☃ <= 7; ☃++)
      {
        arc ☃ = b;
        if ((☃ == 2) || (☃ == 6)) {
          ☃ = a;
        }
        a(☃, ☃, 0, ☃, 0, 0, ☃, 7, ☃, ☃, false);
        a(☃, ☃, 15, ☃, 0, 15, ☃, 7, ☃, ☃, false);
        a(☃, ☃, 1, ☃, 0, 15, ☃, 0, ☃, ☃, false);
        a(☃, ☃, 1, ☃, 7, 14, ☃, 7, ☃, ☃, false);
      }
      a(☃, ☃, 2, 1, 3, 2, 7, 4, b, b, false);
      a(☃, ☃, 3, 1, 2, 4, 7, 2, b, b, false);
      a(☃, ☃, 3, 1, 5, 4, 7, 5, b, b, false);
      a(☃, ☃, 13, 1, 3, 13, 7, 4, b, b, false);
      a(☃, ☃, 11, 1, 2, 12, 7, 2, b, b, false);
      a(☃, ☃, 11, 1, 5, 12, 7, 5, b, b, false);
      
      a(☃, ☃, 5, 1, 3, 5, 3, 4, b, b, false);
      a(☃, ☃, 10, 1, 3, 10, 3, 4, b, b, false);
      
      a(☃, ☃, 5, 7, 2, 10, 7, 5, b, b, false);
      a(☃, ☃, 5, 5, 2, 5, 7, 2, b, b, false);
      a(☃, ☃, 10, 5, 2, 10, 7, 2, b, b, false);
      a(☃, ☃, 5, 5, 5, 5, 7, 5, b, b, false);
      a(☃, ☃, 10, 5, 5, 10, 7, 5, b, b, false);
      a(☃, b, 6, 6, 2, ☃);
      a(☃, b, 9, 6, 2, ☃);
      a(☃, b, 6, 6, 5, ☃);
      a(☃, b, 9, 6, 5, ☃);
      
      a(☃, ☃, 5, 4, 3, 6, 4, 4, b, b, false);
      a(☃, ☃, 9, 4, 3, 10, 4, 4, b, b, false);
      a(☃, e, 5, 4, 2, ☃);
      a(☃, e, 5, 4, 5, ☃);
      a(☃, e, 10, 4, 2, ☃);
      a(☃, e, 10, 4, 5, ☃);
      if (☃.c[cq.d.a()] != 0) {
        a(☃, ☃, 3, 1, 0, 4, 2, 0, false);
      }
      if (☃.c[cq.c.a()] != 0) {
        a(☃, ☃, 3, 1, 7, 4, 2, 7, false);
      }
      if (☃.c[cq.e.a()] != 0) {
        a(☃, ☃, 0, 1, 3, 0, 2, 4, false);
      }
      if (☃.c[cq.d.a()] != 0) {
        a(☃, ☃, 11, 1, 0, 12, 2, 0, false);
      }
      if (☃.c[cq.c.a()] != 0) {
        a(☃, ☃, 11, 1, 7, 12, 2, 7, false);
      }
      if (☃.c[cq.f.a()] != 0) {
        a(☃, ☃, 15, 1, 3, 15, 2, 4, false);
      }
      if (☃.c[cq.d.a()] != 0) {
        a(☃, ☃, 3, 5, 0, 4, 6, 0, false);
      }
      if (☃.c[cq.c.a()] != 0) {
        a(☃, ☃, 3, 5, 7, 4, 6, 7, false);
      }
      if (☃.c[cq.e.a()] != 0) {
        a(☃, ☃, 0, 5, 3, 0, 6, 4, false);
      }
      if (☃.c[cq.d.a()] != 0) {
        a(☃, ☃, 11, 5, 0, 12, 6, 0, false);
      }
      if (☃.c[cq.c.a()] != 0) {
        a(☃, ☃, 11, 5, 7, 12, 6, 7, false);
      }
      if (☃.c[cq.f.a()] != 0) {
        a(☃, ☃, 15, 5, 3, 15, 6, 4, false);
      }
      return true;
    }
  }
  
  public static class n
    extends avy.r
  {
    public n() {}
    
    public n(cq ☃, avy.v ☃, Random ☃)
    {
      super(☃, ☃, 1, 2, 2);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      avy.v ☃ = this.k.b[cq.c.a()];
      avy.v ☃ = this.k;
      avy.v ☃ = ☃.b[cq.b.a()];
      avy.v ☃ = ☃.b[cq.b.a()];
      if (this.k.a / 25 > 0)
      {
        a(☃, ☃, 0, 8, ☃.c[cq.a.a()]);
        a(☃, ☃, 0, 0, ☃.c[cq.a.a()]);
      }
      if (☃.b[cq.b.a()] == null) {
        a(☃, ☃, 1, 8, 1, 6, 8, 7, a);
      }
      if (☃.b[cq.b.a()] == null) {
        a(☃, ☃, 1, 8, 8, 6, 8, 14, a);
      }
      for (int ☃ = 1; ☃ <= 7; ☃++)
      {
        arc ☃ = b;
        if ((☃ == 2) || (☃ == 6)) {
          ☃ = a;
        }
        a(☃, ☃, 0, ☃, 0, 0, ☃, 15, ☃, ☃, false);
        a(☃, ☃, 7, ☃, 0, 7, ☃, 15, ☃, ☃, false);
        a(☃, ☃, 1, ☃, 0, 6, ☃, 0, ☃, ☃, false);
        a(☃, ☃, 1, ☃, 15, 6, ☃, 15, ☃, ☃, false);
      }
      for (int ☃ = 1; ☃ <= 7; ☃++)
      {
        arc ☃ = c;
        if ((☃ == 2) || (☃ == 6)) {
          ☃ = e;
        }
        a(☃, ☃, 3, ☃, 7, 4, ☃, 8, ☃, ☃, false);
      }
      if (☃.c[cq.d.a()] != 0) {
        a(☃, ☃, 3, 1, 0, 4, 2, 0, false);
      }
      if (☃.c[cq.f.a()] != 0) {
        a(☃, ☃, 7, 1, 3, 7, 2, 4, false);
      }
      if (☃.c[cq.e.a()] != 0) {
        a(☃, ☃, 0, 1, 3, 0, 2, 4, false);
      }
      if (☃.c[cq.c.a()] != 0) {
        a(☃, ☃, 3, 1, 15, 4, 2, 15, false);
      }
      if (☃.c[cq.e.a()] != 0) {
        a(☃, ☃, 0, 1, 11, 0, 2, 12, false);
      }
      if (☃.c[cq.f.a()] != 0) {
        a(☃, ☃, 7, 1, 11, 7, 2, 12, false);
      }
      if (☃.c[cq.d.a()] != 0) {
        a(☃, ☃, 3, 5, 0, 4, 6, 0, false);
      }
      if (☃.c[cq.f.a()] != 0)
      {
        a(☃, ☃, 7, 5, 3, 7, 6, 4, false);
        a(☃, ☃, 5, 4, 2, 6, 4, 5, b, b, false);
        a(☃, ☃, 6, 1, 2, 6, 3, 2, b, b, false);
        a(☃, ☃, 6, 1, 5, 6, 3, 5, b, b, false);
      }
      if (☃.c[cq.e.a()] != 0)
      {
        a(☃, ☃, 0, 5, 3, 0, 6, 4, false);
        a(☃, ☃, 1, 4, 2, 2, 4, 5, b, b, false);
        a(☃, ☃, 1, 1, 2, 1, 3, 2, b, b, false);
        a(☃, ☃, 1, 1, 5, 1, 3, 5, b, b, false);
      }
      if (☃.c[cq.c.a()] != 0) {
        a(☃, ☃, 3, 5, 15, 4, 6, 15, false);
      }
      if (☃.c[cq.e.a()] != 0)
      {
        a(☃, ☃, 0, 5, 11, 0, 6, 12, false);
        a(☃, ☃, 1, 4, 10, 2, 4, 13, b, b, false);
        a(☃, ☃, 1, 1, 10, 1, 3, 10, b, b, false);
        a(☃, ☃, 1, 1, 13, 1, 3, 13, b, b, false);
      }
      if (☃.c[cq.f.a()] != 0)
      {
        a(☃, ☃, 7, 5, 11, 7, 6, 12, false);
        a(☃, ☃, 5, 4, 10, 6, 4, 13, b, b, false);
        a(☃, ☃, 6, 1, 10, 6, 3, 10, b, b, false);
        a(☃, ☃, 6, 1, 13, 6, 3, 13, b, b, false);
      }
      return true;
    }
  }
  
  public static class j
    extends avy.r
  {
    public j() {}
    
    public j(cq ☃, avy.v ☃, Random ☃)
    {
      super(☃, ☃, 2, 2, 2);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      a(☃, ☃, 1, 8, 0, 14, 8, 14, a);
      
      int ☃ = 7;
      arc ☃ = b;
      a(☃, ☃, 0, ☃, 0, 0, ☃, 15, ☃, ☃, false);
      a(☃, ☃, 15, ☃, 0, 15, ☃, 15, ☃, ☃, false);
      a(☃, ☃, 1, ☃, 0, 15, ☃, 0, ☃, ☃, false);
      a(☃, ☃, 1, ☃, 15, 14, ☃, 15, ☃, ☃, false);
      for (int ☃ = 1; ☃ <= 6; ☃++)
      {
        arc ☃ = b;
        if ((☃ == 2) || (☃ == 6)) {
          ☃ = a;
        }
        for (int ☃ = 0; ☃ <= 15; ☃ += 15)
        {
          a(☃, ☃, ☃, ☃, 0, ☃, ☃, 1, ☃, ☃, false);
          a(☃, ☃, ☃, ☃, 6, ☃, ☃, 9, ☃, ☃, false);
          a(☃, ☃, ☃, ☃, 14, ☃, ☃, 15, ☃, ☃, false);
        }
        a(☃, ☃, 1, ☃, 0, 1, ☃, 0, ☃, ☃, false);
        a(☃, ☃, 6, ☃, 0, 9, ☃, 0, ☃, ☃, false);
        a(☃, ☃, 14, ☃, 0, 14, ☃, 0, ☃, ☃, false);
        
        a(☃, ☃, 1, ☃, 15, 14, ☃, 15, ☃, ☃, false);
      }
      a(☃, ☃, 6, 3, 6, 9, 6, 9, c, c, false);
      a(☃, ☃, 7, 4, 7, 8, 5, 8, aju.R.u(), aju.R.u(), false);
      for (int ☃ = 3; ☃ <= 6; ☃ += 3) {
        for (int ☃ = 6; ☃ <= 9; ☃ += 3)
        {
          a(☃, e, ☃, ☃, 6, ☃);
          a(☃, e, ☃, ☃, 9, ☃);
        }
      }
      a(☃, ☃, 5, 1, 6, 5, 2, 6, b, b, false);
      a(☃, ☃, 5, 1, 9, 5, 2, 9, b, b, false);
      a(☃, ☃, 10, 1, 6, 10, 2, 6, b, b, false);
      a(☃, ☃, 10, 1, 9, 10, 2, 9, b, b, false);
      a(☃, ☃, 6, 1, 5, 6, 2, 5, b, b, false);
      a(☃, ☃, 9, 1, 5, 9, 2, 5, b, b, false);
      a(☃, ☃, 6, 1, 10, 6, 2, 10, b, b, false);
      a(☃, ☃, 9, 1, 10, 9, 2, 10, b, b, false);
      
      a(☃, ☃, 5, 2, 5, 5, 6, 5, b, b, false);
      a(☃, ☃, 5, 2, 10, 5, 6, 10, b, b, false);
      a(☃, ☃, 10, 2, 5, 10, 6, 5, b, b, false);
      a(☃, ☃, 10, 2, 10, 10, 6, 10, b, b, false);
      
      a(☃, ☃, 5, 7, 1, 5, 7, 6, b, b, false);
      a(☃, ☃, 10, 7, 1, 10, 7, 6, b, b, false);
      a(☃, ☃, 5, 7, 9, 5, 7, 14, b, b, false);
      a(☃, ☃, 10, 7, 9, 10, 7, 14, b, b, false);
      
      a(☃, ☃, 1, 7, 5, 6, 7, 5, b, b, false);
      a(☃, ☃, 1, 7, 10, 6, 7, 10, b, b, false);
      a(☃, ☃, 9, 7, 5, 14, 7, 5, b, b, false);
      a(☃, ☃, 9, 7, 10, 14, 7, 10, b, b, false);
      
      a(☃, ☃, 2, 1, 2, 2, 1, 3, b, b, false);
      a(☃, ☃, 3, 1, 2, 3, 1, 2, b, b, false);
      a(☃, ☃, 13, 1, 2, 13, 1, 3, b, b, false);
      a(☃, ☃, 12, 1, 2, 12, 1, 2, b, b, false);
      a(☃, ☃, 2, 1, 12, 2, 1, 13, b, b, false);
      a(☃, ☃, 3, 1, 13, 3, 1, 13, b, b, false);
      a(☃, ☃, 13, 1, 12, 13, 1, 13, b, b, false);
      a(☃, ☃, 12, 1, 13, 12, 1, 13, b, b, false);
      
      return true;
    }
  }
  
  public static class u
    extends avy.r
  {
    private int n;
    
    public u() {}
    
    public u(cq ☃, avp ☃, int ☃)
    {
      super(☃);
      this.n = (☃ & 0x1);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      if (this.n == 0)
      {
        for (int ☃ = 0; ☃ < 4; ☃++) {
          a(☃, ☃, 10 - ☃, 3 - ☃, 20 - ☃, 12 + ☃, 3 - ☃, 20, b, b, false);
        }
        a(☃, ☃, 7, 0, 6, 15, 0, 16, b, b, false);
        a(☃, ☃, 6, 0, 6, 6, 3, 20, b, b, false);
        a(☃, ☃, 16, 0, 6, 16, 3, 20, b, b, false);
        a(☃, ☃, 7, 1, 7, 7, 1, 20, b, b, false);
        a(☃, ☃, 15, 1, 7, 15, 1, 20, b, b, false);
        
        a(☃, ☃, 7, 1, 6, 9, 3, 6, b, b, false);
        a(☃, ☃, 13, 1, 6, 15, 3, 6, b, b, false);
        a(☃, ☃, 8, 1, 7, 9, 1, 7, b, b, false);
        a(☃, ☃, 13, 1, 7, 14, 1, 7, b, b, false);
        a(☃, ☃, 9, 0, 5, 13, 0, 5, b, b, false);
        
        a(☃, ☃, 10, 0, 7, 12, 0, 7, c, c, false);
        a(☃, ☃, 8, 0, 10, 8, 0, 12, c, c, false);
        a(☃, ☃, 14, 0, 10, 14, 0, 12, c, c, false);
        for (int ☃ = 18; ☃ >= 7; ☃ -= 3)
        {
          a(☃, e, 6, 3, ☃, ☃);
          a(☃, e, 16, 3, ☃, ☃);
        }
        a(☃, e, 10, 0, 10, ☃);
        a(☃, e, 12, 0, 10, ☃);
        a(☃, e, 10, 0, 12, ☃);
        a(☃, e, 12, 0, 12, ☃);
        
        a(☃, e, 8, 3, 6, ☃);
        a(☃, e, 14, 3, 6, ☃);
        
        a(☃, b, 4, 2, 4, ☃);
        a(☃, e, 4, 1, 4, ☃);
        a(☃, b, 4, 0, 4, ☃);
        
        a(☃, b, 18, 2, 4, ☃);
        a(☃, e, 18, 1, 4, ☃);
        a(☃, b, 18, 0, 4, ☃);
        
        a(☃, b, 4, 2, 18, ☃);
        a(☃, e, 4, 1, 18, ☃);
        a(☃, b, 4, 0, 18, ☃);
        
        a(☃, b, 18, 2, 18, ☃);
        a(☃, e, 18, 1, 18, ☃);
        a(☃, b, 18, 0, 18, ☃);
        
        a(☃, b, 9, 7, 20, ☃);
        a(☃, b, 13, 7, 20, ☃);
        a(☃, ☃, 6, 0, 21, 7, 4, 21, b, b, false);
        a(☃, ☃, 15, 0, 21, 16, 4, 21, b, b, false);
        
        a(☃, ☃, 11, 2, 16);
      }
      else if (this.n == 1)
      {
        a(☃, ☃, 9, 3, 18, 13, 3, 20, b, b, false);
        a(☃, ☃, 9, 0, 18, 9, 2, 18, b, b, false);
        a(☃, ☃, 13, 0, 18, 13, 2, 18, b, b, false);
        int ☃ = 9;
        int ☃ = 20;
        int ☃ = 5;
        for (int ☃ = 0; ☃ < 2; ☃++)
        {
          a(☃, b, ☃, ☃ + 1, ☃, ☃);
          a(☃, e, ☃, ☃, ☃, ☃);
          a(☃, b, ☃, ☃ - 1, ☃, ☃);
          ☃ = 13;
        }
        a(☃, ☃, 7, 3, 7, 15, 3, 14, b, b, false);
        ☃ = 10;
        for (int ☃ = 0; ☃ < 2; ☃++)
        {
          a(☃, ☃, ☃, 0, 10, ☃, 6, 10, b, b, false);
          a(☃, ☃, ☃, 0, 12, ☃, 6, 12, b, b, false);
          a(☃, e, ☃, 0, 10, ☃);
          a(☃, e, ☃, 0, 12, ☃);
          a(☃, e, ☃, 4, 10, ☃);
          a(☃, e, ☃, 4, 12, ☃);
          ☃ = 12;
        }
        ☃ = 8;
        for (int ☃ = 0; ☃ < 2; ☃++)
        {
          a(☃, ☃, ☃, 0, 7, ☃, 2, 7, b, b, false);
          a(☃, ☃, ☃, 0, 14, ☃, 2, 14, b, b, false);
          ☃ = 14;
        }
        a(☃, ☃, 8, 3, 8, 8, 3, 13, c, c, false);
        a(☃, ☃, 14, 3, 8, 14, 3, 13, c, c, false);
        
        a(☃, ☃, 11, 5, 13);
      }
      return true;
    }
  }
  
  public static class q
    extends avy.r
  {
    public q() {}
    
    public q(cq ☃, avp ☃)
    {
      super(☃);
    }
    
    public boolean a(aht ☃, Random ☃, avp ☃)
    {
      a(☃, ☃, 2, -1, 2, 11, -1, 11, b, b, false);
      a(☃, ☃, 0, -1, 0, 1, -1, 11, a, a, false);
      a(☃, ☃, 12, -1, 0, 13, -1, 11, a, a, false);
      a(☃, ☃, 2, -1, 0, 11, -1, 1, a, a, false);
      a(☃, ☃, 2, -1, 12, 11, -1, 13, a, a, false);
      
      a(☃, ☃, 0, 0, 0, 0, 0, 13, b, b, false);
      a(☃, ☃, 13, 0, 0, 13, 0, 13, b, b, false);
      a(☃, ☃, 1, 0, 0, 12, 0, 0, b, b, false);
      a(☃, ☃, 1, 0, 13, 12, 0, 13, b, b, false);
      for (int ☃ = 2; ☃ <= 11; ☃ += 3)
      {
        a(☃, e, 0, 0, ☃, ☃);
        a(☃, e, 13, 0, ☃, ☃);
        a(☃, e, ☃, 0, 0, ☃);
      }
      a(☃, ☃, 2, 0, 3, 4, 0, 9, b, b, false);
      a(☃, ☃, 9, 0, 3, 11, 0, 9, b, b, false);
      a(☃, ☃, 4, 0, 9, 9, 0, 11, b, b, false);
      a(☃, b, 5, 0, 8, ☃);
      a(☃, b, 8, 0, 8, ☃);
      a(☃, b, 10, 0, 10, ☃);
      a(☃, b, 3, 0, 10, ☃);
      a(☃, ☃, 3, 0, 3, 3, 0, 7, c, c, false);
      a(☃, ☃, 10, 0, 3, 10, 0, 7, c, c, false);
      a(☃, ☃, 6, 0, 10, 7, 0, 10, c, c, false);
      
      int ☃ = 3;
      for (int ☃ = 0; ☃ < 2; ☃++)
      {
        for (int ☃ = 2; ☃ <= 8; ☃ += 3) {
          a(☃, ☃, ☃, 0, ☃, ☃, 2, ☃, b, b, false);
        }
        ☃ = 10;
      }
      a(☃, ☃, 5, 0, 10, 5, 2, 10, b, b, false);
      a(☃, ☃, 8, 0, 10, 8, 2, 10, b, b, false);
      
      a(☃, ☃, 6, -1, 7, 7, -1, 8, c, c, false);
      
      a(☃, ☃, 6, -1, 3, 7, -1, 4, false);
      
      a(☃, ☃, 6, 1, 6);
      
      return true;
    }
  }
  
  static class v
  {
    int a;
    v[] b = new v[6];
    boolean[] c = new boolean[6];
    boolean d;
    boolean e;
    int f;
    
    public v(int ☃)
    {
      this.a = ☃;
    }
    
    public void a(cq ☃, v ☃)
    {
      this.b[☃.a()] = ☃;
      ☃.b[☃.d().a()] = this;
    }
    
    public void a()
    {
      for (int ☃ = 0; ☃ < 6; ☃++) {
        this.c[☃] = (this.b[☃] != null ? 1 : false);
      }
    }
    
    public boolean a(int ☃)
    {
      if (this.e) {
        return true;
      }
      this.f = ☃;
      for (int ☃ = 0; ☃ < 6; ☃++) {
        if ((this.b[☃] != null) && (this.c[☃] != 0) && 
          (this.b[☃].f != ☃) && (this.b[☃].a(☃))) {
          return true;
        }
      }
      return false;
    }
    
    public boolean b()
    {
      return this.a >= 75;
    }
    
    public int c()
    {
      int ☃ = 0;
      for (int ☃ = 0; ☃ < 6; ☃++) {
        if (this.c[☃] != 0) {
          ☃++;
        }
      }
      return ☃;
    }
  }
  
  static abstract interface i
  {
    public abstract boolean a(avy.v paramv);
    
    public abstract avy.r a(cq paramcq, avy.v paramv, Random paramRandom);
  }
  
  static class f
    implements avy.i
  {
    public boolean a(avy.v ☃)
    {
      return true;
    }
    
    public avy.r a(cq ☃, avy.v ☃, Random ☃)
    {
      ☃.d = true;
      return new avy.s(☃, ☃, ☃);
    }
  }
  
  static class g
    implements avy.i
  {
    public boolean a(avy.v ☃)
    {
      return (☃.c[cq.e.a()] == 0) && (☃.c[cq.f.a()] == 0) && (☃.c[cq.c.a()] == 0) && (☃.c[cq.d.a()] == 0) && (☃.c[cq.b.a()] == 0);
    }
    
    public avy.r a(cq ☃, avy.v ☃, Random ☃)
    {
      ☃.d = true;
      return new avy.t(☃, ☃, ☃);
    }
  }
  
  static class c
    implements avy.i
  {
    public boolean a(avy.v ☃)
    {
      if ((☃.c[cq.b.a()] != 0) && (!☃.b[cq.b.a()].d)) {
        return true;
      }
      return false;
    }
    
    public avy.r a(cq ☃, avy.v ☃, Random ☃)
    {
      avy.v ☃ = ☃;
      ☃.d = true;
      ☃.b[cq.b.a()].d = true;
      return new avy.m(☃, ☃, ☃);
    }
  }
  
  static class a
    implements avy.i
  {
    public boolean a(avy.v ☃)
    {
      if ((☃.c[cq.f.a()] != 0) && (!☃.b[cq.f.a()].d)) {
        return true;
      }
      return false;
    }
    
    public avy.r a(cq ☃, avy.v ☃, Random ☃)
    {
      avy.v ☃ = ☃;
      ☃.d = true;
      ☃.b[cq.f.a()].d = true;
      return new avy.k(☃, ☃, ☃);
    }
  }
  
  static class e
    implements avy.i
  {
    public boolean a(avy.v ☃)
    {
      if ((☃.c[cq.c.a()] != 0) && (!☃.b[cq.c.a()].d)) {
        return true;
      }
      return false;
    }
    
    public avy.r a(cq ☃, avy.v ☃, Random ☃)
    {
      avy.v ☃ = ☃;
      if ((☃.c[cq.c.a()] == 0) || (☃.b[cq.c.a()].d)) {
        ☃ = ☃.b[cq.d.a()];
      }
      ☃.d = true;
      ☃.b[cq.c.a()].d = true;
      return new avy.o(☃, ☃, ☃);
    }
  }
  
  static class b
    implements avy.i
  {
    public boolean a(avy.v ☃)
    {
      if ((☃.c[cq.f.a()] != 0) && (!☃.b[cq.f.a()].d) && 
        (☃.c[cq.b.a()] != 0) && (!☃.b[cq.b.a()].d))
      {
        avy.v ☃ = ☃.b[cq.f.a()];
        
        return (☃.c[cq.b.a()] != 0) && (!☃.b[cq.b.a()].d);
      }
      return false;
    }
    
    public avy.r a(cq ☃, avy.v ☃, Random ☃)
    {
      ☃.d = true;
      ☃.b[cq.f.a()].d = true;
      ☃.b[cq.b.a()].d = true;
      ☃.b[cq.f.a()].b[cq.b.a()].d = true;
      return new avy.l(☃, ☃, ☃);
    }
  }
  
  static class d
    implements avy.i
  {
    public boolean a(avy.v ☃)
    {
      if ((☃.c[cq.c.a()] != 0) && (!☃.b[cq.c.a()].d) && 
        (☃.c[cq.b.a()] != 0) && (!☃.b[cq.b.a()].d))
      {
        avy.v ☃ = ☃.b[cq.c.a()];
        
        return (☃.c[cq.b.a()] != 0) && (!☃.b[cq.b.a()].d);
      }
      return false;
    }
    
    public avy.r a(cq ☃, avy.v ☃, Random ☃)
    {
      ☃.d = true;
      ☃.b[cq.c.a()].d = true;
      ☃.b[cq.b.a()].d = true;
      ☃.b[cq.c.a()].b[cq.b.a()].d = true;
      return new avy.n(☃, ☃, ☃);
    }
  }
}
