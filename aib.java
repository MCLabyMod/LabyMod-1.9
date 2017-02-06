import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class aib
{
  private final lp a;
  private final Random b;
  private final ol<aib.a> c = new ol();
  private final List<Long> d = Lists.newArrayList();
  
  public aib(lp ☃)
  {
    this.a = ☃;
    this.b = new Random(☃.O());
  }
  
  public void a(rr ☃, float ☃)
  {
    if (this.a.s.p().a() == 1)
    {
      int ☃ = on.c(☃.p);
      int ☃ = on.c(☃.q) - 1;
      int ☃ = on.c(☃.r);
      
      int ☃ = 1;
      int ☃ = 0;
      for (int ☃ = -2; ☃ <= 2; ☃++) {
        for (int ☃ = -2; ☃ <= 2; ☃++) {
          for (int ☃ = -1; ☃ < 3; ☃++)
          {
            int ☃ = ☃ + ☃ * ☃ + ☃ * ☃;
            int ☃ = ☃ + ☃;
            int ☃ = ☃ + ☃ * ☃ - ☃ * ☃;
            
            boolean ☃ = ☃ < 0;
            
            this.a.a(new cj(☃, ☃, ☃), ☃ ? aju.Z.u() : aju.a.u());
          }
        }
      }
      ☃.b(☃, ☃, ☃, ☃.v, 0.0F);
      ☃.s = (☃.t = ☃.u = 0.0D);
      
      return;
    }
    if (b(☃, ☃)) {
      return;
    }
    a(☃);
    b(☃, ☃);
  }
  
  public boolean b(rr ☃, float ☃)
  {
    int ☃ = 128;
    double ☃ = -1.0D;
    
    int ☃ = on.c(☃.p);
    int ☃ = on.c(☃.r);
    boolean ☃ = true;
    
    cj ☃ = cj.a;
    
    long ☃ = ahn.a(☃, ☃);
    if (this.c.b(☃))
    {
      aib.a ☃ = (aib.a)this.c.a(☃);
      
      ☃ = 0.0D;
      
      ☃ = ☃;
      ☃.c = this.a.P();
      ☃ = false;
    }
    else
    {
      cj ☃ = new cj(☃);
      for (int ☃ = -128; ☃ <= 128; ☃++) {
        for (int ☃ = -128; ☃ <= 128; ☃++)
        {
          cj ☃ = ☃.a(☃, this.a.Z() - 1 - ☃.q(), ☃);
          while (☃.q() >= 0)
          {
            cj ☃ = ☃.b();
            if (this.a.o(☃).t() == aju.aY)
            {
              while (this.a.o(☃ = ☃.b()).t() == aju.aY) {
                ☃ = ☃;
              }
              double ☃ = ☃.k(☃);
              if ((☃ < 0.0D) || (☃ < ☃))
              {
                ☃ = ☃;
                ☃ = ☃;
              }
            }
            ☃ = ☃;
          }
        }
      }
    }
    if (☃ >= 0.0D)
    {
      if (☃)
      {
        this.c.a(☃, new aib.a(☃, this.a.P()));
        this.d.add(Long.valueOf(☃));
      }
      double ☃ = ☃.p() + 0.5D;
      double ☃ = ☃.q() + 0.5D;
      double ☃ = ☃.r() + 0.5D;
      
      arg.b ☃ = aju.aY.c(this.a, ☃);
      boolean ☃ = ☃.b().e().c() == cq.b.b;
      double ☃ = ☃.b().k() == cq.a.a ? ☃.a().r() : ☃.a().p();
      ☃ = ☃.a().q() + 1 - ☃.aY().c * ☃.e();
      if (☃) {
        ☃ += 1.0D;
      }
      if (☃.b().k() == cq.a.a) {
        ☃ = ☃ + (1.0D - ☃.aY().b) * ☃.d() * ☃.b().e().c().a();
      } else {
        ☃ = ☃ + (1.0D - ☃.aY().b) * ☃.d() * ☃.b().e().c().a();
      }
      float ☃ = 0.0F;
      float ☃ = 0.0F;
      float ☃ = 0.0F;
      float ☃ = 0.0F;
      if (☃.b().d() == ☃.aZ())
      {
        ☃ = 1.0F;
        ☃ = 1.0F;
      }
      else if (☃.b().d() == ☃.aZ().d())
      {
        ☃ = -1.0F;
        ☃ = -1.0F;
      }
      else if (☃.b().d() == ☃.aZ().e())
      {
        ☃ = 1.0F;
        ☃ = -1.0F;
      }
      else
      {
        ☃ = -1.0F;
        ☃ = 1.0F;
      }
      double ☃ = ☃.s;
      double ☃ = ☃.u;
      ☃.s = (☃ * ☃ + ☃ * ☃);
      ☃.u = (☃ * ☃ + ☃ * ☃);
      ☃.v = (☃ - ☃.aZ().d().b() * 90 + ☃.b().b() * 90);
      if ((☃ instanceof lr)) {
        ((lr)☃).a.a(☃, ☃, ☃, ☃.v, ☃.w);
      } else {
        ☃.b(☃, ☃, ☃, ☃.v, ☃.w);
      }
      return true;
    }
    return false;
  }
  
  public boolean a(rr ☃)
  {
    int ☃ = 16;
    double ☃ = -1.0D;
    
    int ☃ = on.c(☃.p);
    int ☃ = on.c(☃.q);
    int ☃ = on.c(☃.r);
    
    int ☃ = ☃;
    int ☃ = ☃;
    int ☃ = ☃;
    int ☃ = 0;
    
    int ☃ = this.b.nextInt(4);
    
    cj.a ☃ = new cj.a();
    for (int ☃ = ☃ - ☃; ☃ <= ☃ + ☃; ☃++)
    {
      double ☃ = ☃ + 0.5D - ☃.p;
      for (int ☃ = ☃ - ☃; ☃ <= ☃ + ☃; ☃++)
      {
        double ☃ = ☃ + 0.5D - ☃.r;
        label461:
        for (int ☃ = this.a.Z() - 1; ☃ >= 0; ☃--) {
          if (this.a.d(☃.c(☃, ☃, ☃)))
          {
            while ((☃ > 0) && (this.a.d(☃.c(☃, ☃ - 1, ☃)))) {
              ☃--;
            }
            for (int ☃ = ☃; ☃ < ☃ + 4; ☃++)
            {
              int ☃ = ☃ % 2;
              int ☃ = 1 - ☃;
              if (☃ % 4 >= 2)
              {
                ☃ = -☃;
                ☃ = -☃;
              }
              for (int ☃ = 0; ☃ < 3; ☃++) {
                for (int ☃ = 0; ☃ < 4; ☃++) {
                  for (int ☃ = -1; ☃ < 4; ☃++)
                  {
                    int ☃ = ☃ + (☃ - 1) * ☃ + ☃ * ☃;
                    int ☃ = ☃ + ☃;
                    int ☃ = ☃ + (☃ - 1) * ☃ - ☃ * ☃;
                    
                    ☃.c(☃, ☃, ☃);
                    if ((☃ < 0) && (!this.a.o(☃).a().a())) {
                      break label461;
                    }
                    if ((☃ >= 0) && (!this.a.d(☃))) {
                      break label461;
                    }
                  }
                }
              }
              double ☃ = ☃ + 0.5D - ☃.q;
              double ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
              if ((☃ < 0.0D) || (☃ < ☃))
              {
                ☃ = ☃;
                ☃ = ☃;
                ☃ = ☃;
                ☃ = ☃;
                ☃ = ☃ % 4;
              }
            }
          }
        }
      }
    }
    if (☃ < 0.0D) {
      for (int ☃ = ☃ - ☃; ☃ <= ☃ + ☃; ☃++)
      {
        double ☃ = ☃ + 0.5D - ☃.p;
        for (int ☃ = ☃ - ☃; ☃ <= ☃ + ☃; ☃++)
        {
          double ☃ = ☃ + 0.5D - ☃.r;
          label833:
          for (int ☃ = this.a.Z() - 1; ☃ >= 0; ☃--) {
            if (this.a.d(☃.c(☃, ☃, ☃)))
            {
              while ((☃ > 0) && (this.a.d(☃.c(☃, ☃ - 1, ☃)))) {
                ☃--;
              }
              for (int ☃ = ☃; ☃ < ☃ + 2; ☃++)
              {
                int ☃ = ☃ % 2;
                int ☃ = 1 - ☃;
                for (int ☃ = 0; ☃ < 4; ☃++) {
                  for (int ☃ = -1; ☃ < 4; ☃++)
                  {
                    int ☃ = ☃ + (☃ - 1) * ☃;
                    int ☃ = ☃ + ☃;
                    int ☃ = ☃ + (☃ - 1) * ☃;
                    
                    ☃.c(☃, ☃, ☃);
                    if ((☃ < 0) && (!this.a.o(☃).a().a())) {
                      break label833;
                    }
                    if ((☃ >= 0) && (!this.a.d(☃))) {
                      break label833;
                    }
                  }
                }
                double ☃ = ☃ + 0.5D - ☃.q;
                double ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
                if ((☃ < 0.0D) || (☃ < ☃))
                {
                  ☃ = ☃;
                  ☃ = ☃;
                  ☃ = ☃;
                  ☃ = ☃;
                  ☃ = ☃ % 2;
                }
              }
            }
          }
        }
      }
    }
    int ☃ = ☃;
    
    int ☃ = ☃;
    int ☃ = ☃;
    int ☃ = ☃;
    
    int ☃ = ☃ % 2;
    int ☃ = 1 - ☃;
    if (☃ % 4 >= 2)
    {
      ☃ = -☃;
      ☃ = -☃;
    }
    if (☃ < 0.0D)
    {
      ☃ = on.a(☃, 70, this.a.Z() - 10);
      ☃ = ☃;
      for (int ☃ = -1; ☃ <= 1; ☃++) {
        for (int ☃ = 1; ☃ < 3; ☃++) {
          for (int ☃ = -1; ☃ < 3; ☃++)
          {
            int ☃ = ☃ + (☃ - 1) * ☃ + ☃ * ☃;
            int ☃ = ☃ + ☃;
            int ☃ = ☃ + (☃ - 1) * ☃ - ☃ * ☃;
            
            boolean ☃ = ☃ < 0;
            
            this.a.a(new cj(☃, ☃, ☃), ☃ ? aju.Z.u() : aju.a.u());
          }
        }
      }
    }
    arc ☃ = aju.aY.u().a(ank.a, ☃ != 0 ? cq.a.a : cq.a.c);
    for (int ☃ = 0; ☃ < 4; ☃++)
    {
      for (int ☃ = 0; ☃ < 4; ☃++) {
        for (int ☃ = -1; ☃ < 4; ☃++)
        {
          int ☃ = ☃ + (☃ - 1) * ☃;
          int ☃ = ☃ + ☃;
          int ☃ = ☃ + (☃ - 1) * ☃;
          
          boolean ☃ = (☃ == 0) || (☃ == 3) || (☃ == -1) || (☃ == 3);
          this.a.a(new cj(☃, ☃, ☃), ☃ ? aju.Z.u() : ☃, 2);
        }
      }
      for (int ☃ = 0; ☃ < 4; ☃++) {
        for (int ☃ = -1; ☃ < 4; ☃++)
        {
          int ☃ = ☃ + (☃ - 1) * ☃;
          int ☃ = ☃ + ☃;
          int ☃ = ☃ + (☃ - 1) * ☃;
          
          cj ☃ = new cj(☃, ☃, ☃);
          this.a.d(☃, this.a.o(☃).t());
        }
      }
    }
    return true;
  }
  
  public void a(long ☃)
  {
    if (☃ % 100L == 0L)
    {
      Iterator<Long> ☃ = this.d.iterator();
      long ☃ = ☃ - 300L;
      while (☃.hasNext())
      {
        Long ☃ = (Long)☃.next();
        aib.a ☃ = (aib.a)this.c.a(☃.longValue());
        if ((☃ == null) || (☃.c < ☃))
        {
          ☃.remove();
          this.c.d(☃.longValue());
        }
      }
    }
  }
  
  public class a
    extends cj
  {
    public long c;
    
    public a(cj ☃, long ☃)
    {
      super(☃.q(), ☃.r());
      this.c = ☃;
    }
  }
}
