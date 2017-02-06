import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class aaw
  extends aau
{
  private static final Logger f = ;
  private qg g = new abr();
  private qg h = new qv("Repair", true, 2)
  {
    public void v_()
    {
      super.v_();
      aaw.this.a(this);
    }
  };
  private aht i;
  private cj j;
  public int a;
  private int k;
  private String l;
  private final zj m;
  
  public aaw(zi ☃, aht ☃, zj ☃)
  {
    this(☃, ☃, cj.a, ☃);
  }
  
  public aaw(zi ☃, final aht ☃, final cj ☃, zj ☃)
  {
    this.j = ☃;
    this.i = ☃;
    this.m = ☃;
    
    a(new abt(this.h, 0, 27, 47));
    a(new abt(this.h, 1, 76, 47));
    a(new abt(this.g, 2, 134, 47)
    {
      public boolean a(adq ☃)
      {
        return false;
      }
      
      public boolean a(zj ☃)
      {
        return ((☃.bJ.d) || (☃.bK >= aaw.this.a)) && (aaw.this.a > 0) && (e());
      }
      
      public void a(zj ☃, adq ☃)
      {
        if (!☃.bJ.d) {
          ☃.a(-aaw.this.a);
        }
        aaw.a(aaw.this).a(0, null);
        if (aaw.b(aaw.this) > 0)
        {
          adq ☃ = aaw.a(aaw.this).a(1);
          if ((☃ != null) && (☃.b > aaw.b(aaw.this)))
          {
            ☃.b -= aaw.b(aaw.this);
            aaw.a(aaw.this).a(1, ☃);
          }
          else
          {
            aaw.a(aaw.this).a(1, null);
          }
        }
        else
        {
          aaw.a(aaw.this).a(1, null);
        }
        aaw.this.a = 0;
        
        arc ☃ = ☃.o(☃);
        if ((!☃.bJ.d) && (!☃.E) && (☃.t() == aju.cf) && (☃.bF().nextFloat() < 0.12F))
        {
          int ☃ = ((Integer)☃.c(ajk.b)).intValue();
          ☃++;
          if (☃ > 2)
          {
            ☃.g(☃);
            ☃.b(1029, ☃, 0);
          }
          else
          {
            ☃.a(☃, ☃.a(ajk.b, Integer.valueOf(☃)), 2);
            ☃.b(1030, ☃, 0);
          }
        }
        else if (!☃.E)
        {
          ☃.b(1030, ☃, 0);
        }
      }
    });
    for (int ☃ = 0; ☃ < 3; ☃++) {
      for (int ☃ = 0; ☃ < 9; ☃++) {
        a(new abt(☃, ☃ + ☃ * 9 + 9, 8 + ☃ * 18, 84 + ☃ * 18));
      }
    }
    for (int ☃ = 0; ☃ < 9; ☃++) {
      a(new abt(☃, ☃, 8 + ☃ * 18, 142));
    }
  }
  
  public void a(qg ☃)
  {
    super.a(☃);
    if (☃ == this.h) {
      e();
    }
  }
  
  public void e()
  {
    adq ☃ = this.h.a(0);
    this.a = 1;
    int ☃ = 0;
    int ☃ = 0;
    int ☃ = 0;
    if (☃ == null)
    {
      this.g.a(0, null);
      this.a = 0;
      return;
    }
    adq ☃ = ☃.k();
    adq ☃ = this.h.a(1);
    Map<agm, Integer> ☃ = ago.a(☃);
    
    ☃ += ☃.A() + (☃ == null ? 0 : ☃.A());
    
    this.k = 0;
    boolean ☃;
    Map<agm, Integer> ☃;
    if (☃ != null)
    {
      ☃ = (☃.b() == ads.cn) && (!ads.cn.h(☃).c_());
      if ((☃.e()) && (☃.b().a(☃, ☃)))
      {
        int ☃ = Math.min(☃.h(), ☃.j() / 4);
        if (☃ <= 0)
        {
          this.g.a(0, null);
          this.a = 0;
          return;
        }
        int ☃ = 0;
        while ((☃ > 0) && (☃ < ☃.b))
        {
          int ☃ = ☃.h() - ☃;
          ☃.b(☃);
          ☃++;
          
          ☃ = Math.min(☃.h(), ☃.j() / 4);
          ☃++;
        }
        this.k = ☃;
      }
      else
      {
        if ((!☃) && ((☃.b() != ☃.b()) || (!☃.e())))
        {
          this.g.a(0, null);
          this.a = 0;
          return;
        }
        if ((☃.e()) && (!☃))
        {
          int ☃ = ☃.j() - ☃.h();
          int ☃ = ☃.j() - ☃.h();
          int ☃ = ☃ + ☃.j() * 12 / 100;
          int ☃ = ☃ + ☃;
          int ☃ = ☃.j() - ☃;
          if (☃ < 0) {
            ☃ = 0;
          }
          if (☃ < ☃.i())
          {
            ☃.b(☃);
            ☃ += 2;
          }
        }
        ☃ = ago.a(☃);
        for (agm ☃ : ☃.keySet()) {
          if (☃ != null)
          {
            int ☃ = ☃.containsKey(☃) ? ((Integer)☃.get(☃)).intValue() : 0;
            int ☃ = ((Integer)☃.get(☃)).intValue();
            ☃ = ☃ == ☃ ? ☃ + 1 : Math.max(☃, ☃);
            
            boolean ☃ = ☃.a(☃);
            if ((this.m.bJ.d) || (☃.b() == ads.cn)) {
              ☃ = true;
            }
            for (agm ☃ : ☃.keySet()) {
              if ((☃ != ☃) && (!☃.a(☃)))
              {
                ☃ = false;
                ☃++;
              }
            }
            if (☃)
            {
              if (☃ > ☃.b()) {
                ☃ = ☃.b();
              }
              ☃.put(☃, Integer.valueOf(☃));
              int ☃ = 0;
              switch (aaw.3.a[☃.c().ordinal()])
              {
              case 1: 
                ☃ = 1;
                break;
              case 2: 
                ☃ = 2;
                break;
              case 3: 
                ☃ = 4;
                break;
              case 4: 
                ☃ = 8;
              }
              if (☃) {
                ☃ = Math.max(1, ☃ / 2);
              }
              ☃ += ☃ * ☃;
            }
          }
        }
      }
    }
    if (StringUtils.isBlank(this.l))
    {
      if (☃.s())
      {
        ☃ = 1;
        
        ☃ += ☃;
        
        ☃.r();
      }
    }
    else if (!this.l.equals(☃.q()))
    {
      ☃ = 1;
      
      ☃ += ☃;
      
      ☃.c(this.l);
    }
    this.a = (☃ + ☃);
    if (☃ <= 0) {
      ☃ = null;
    }
    if ((☃ == ☃) && (☃ > 0) && (this.a >= 40)) {
      this.a = 39;
    }
    if ((this.a >= 40) && (!this.m.bJ.d)) {
      ☃ = null;
    }
    if (☃ != null)
    {
      int ☃ = ☃.A();
      if ((☃ != null) && (☃ < ☃.A())) {
        ☃ = ☃.A();
      }
      if ((☃ != ☃) || (☃ == 0)) {
        ☃ = ☃ * 2 + 1;
      }
      ☃.c(☃);
      ago.a(☃, ☃);
    }
    this.g.a(0, ☃);
    
    b();
  }
  
  public void a(aba ☃)
  {
    super.a(☃);
    ☃.a(this, 0, this.a);
  }
  
  public void b(int ☃, int ☃)
  {
    if (☃ == 0) {
      this.a = ☃;
    }
  }
  
  public void b(zj ☃)
  {
    super.b(☃);
    if (this.i.E) {
      return;
    }
    for (int ☃ = 0; ☃ < this.h.u_(); ☃++)
    {
      adq ☃ = this.h.b(☃);
      if (☃ != null) {
        ☃.a(☃, false);
      }
    }
  }
  
  public boolean a(zj ☃)
  {
    if (this.i.o(this.j).t() != aju.cf) {
      return false;
    }
    if (☃.e(this.j.p() + 0.5D, this.j.q() + 0.5D, this.j.r() + 0.5D) > 64.0D) {
      return false;
    }
    return true;
  }
  
  public adq b(zj ☃, int ☃)
  {
    adq ☃ = null;
    abt ☃ = (abt)this.c.get(☃);
    if ((☃ != null) && (☃.e()))
    {
      adq ☃ = ☃.d();
      ☃ = ☃.k();
      if (☃ == 2)
      {
        if (!a(☃, 3, 39, true)) {
          return null;
        }
        ☃.a(☃, ☃);
      }
      else if ((☃ == 0) || (☃ == 1))
      {
        if (!a(☃, 3, 39, false)) {
          return null;
        }
      }
      else if ((☃ >= 3) && (☃ < 39) && 
        (!a(☃, 0, 2, false)))
      {
        return null;
      }
      if (☃.b == 0) {
        ☃.d(null);
      } else {
        ☃.f();
      }
      if (☃.b == ☃.b) {
        return null;
      }
      ☃.a(☃, ☃);
    }
    return ☃;
  }
  
  public void a(String ☃)
  {
    this.l = ☃;
    if (a(2).e())
    {
      adq ☃ = a(2).d();
      if (StringUtils.isBlank(☃)) {
        ☃.r();
      } else {
        ☃.c(this.l);
      }
    }
    e();
  }
}
