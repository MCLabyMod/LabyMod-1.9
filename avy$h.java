import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class avy$h
  extends avy.r
{
  private avy.v n;
  private avy.v o;
  private List<avy.r> p = Lists.newArrayList();
  
  public avy$h() {}
  
  public avy$h(Random ☃, int ☃, int ☃, cq ☃)
  {
    super(0);
    
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
