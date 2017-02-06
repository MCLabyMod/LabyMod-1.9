import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;

public abstract class aau
{
  public List<adq> b = Lists.newArrayList();
  public List<abt> c = Lists.newArrayList();
  public int d;
  private short a;
  private int f = -1;
  private int g;
  private final Set<abt> h = Sets.newHashSet();
  protected List<aba> e = Lists.newArrayList();
  
  protected abt a(abt ☃)
  {
    ☃.e = this.c.size();
    this.c.add(☃);
    this.b.add(null);
    return ☃;
  }
  
  public void a(aba ☃)
  {
    if (this.e.contains(☃)) {
      throw new IllegalArgumentException("Listener already listening");
    }
    this.e.add(☃);
    
    ☃.a(this, a());
    b();
  }
  
  public void b(aba ☃)
  {
    this.e.remove(☃);
  }
  
  public List<adq> a()
  {
    List<adq> ☃ = Lists.newArrayList();
    for (int ☃ = 0; ☃ < this.c.size(); ☃++) {
      ☃.add(((abt)this.c.get(☃)).d());
    }
    return ☃;
  }
  
  public void b()
  {
    for (int ☃ = 0; ☃ < this.c.size(); ☃++)
    {
      adq ☃ = ((abt)this.c.get(☃)).d();
      adq ☃ = (adq)this.b.get(☃);
      if (!adq.b(☃, ☃))
      {
        ☃ = ☃ == null ? null : ☃.k();
        this.b.set(☃, ☃);
        for (int ☃ = 0; ☃ < this.e.size(); ☃++) {
          ((aba)this.e.get(☃)).a(this, ☃, ☃);
        }
      }
    }
  }
  
  public boolean a(zj ☃, int ☃)
  {
    return false;
  }
  
  public abt a(qg ☃, int ☃)
  {
    for (int ☃ = 0; ☃ < this.c.size(); ☃++)
    {
      abt ☃ = (abt)this.c.get(☃);
      if (☃.a(☃, ☃)) {
        return ☃;
      }
    }
    return null;
  }
  
  public abt a(int ☃)
  {
    return (abt)this.c.get(☃);
  }
  
  public adq b(zj ☃, int ☃)
  {
    abt ☃ = (abt)this.c.get(☃);
    if (☃ != null) {
      return ☃.d();
    }
    return null;
  }
  
  public adq a(int ☃, int ☃, aaz ☃, zj ☃)
  {
    adq ☃ = null;
    zi ☃ = ☃.br;
    if (☃ == aaz.f)
    {
      int ☃ = this.g;
      this.g = c(☃);
      if (((☃ != 1) || (this.g != 2)) && (☃ != this.g))
      {
        d();
      }
      else if (☃.o() == null)
      {
        d();
      }
      else if (this.g == 0)
      {
        this.f = b(☃);
        if (a(this.f, ☃))
        {
          this.g = 1;
          this.h.clear();
        }
        else
        {
          d();
        }
      }
      else if (this.g == 1)
      {
        abt ☃ = (abt)this.c.get(☃);
        if ((☃ != null) && (a(☃, ☃.o(), true)) && (☃.a(☃.o())) && (☃.o().b > this.h.size()) && (b(☃))) {
          this.h.add(☃);
        }
      }
      else if (this.g == 2)
      {
        if (!this.h.isEmpty())
        {
          adq ☃ = ☃.o().k();
          int ☃ = ☃.o().b;
          for (abt ☃ : this.h) {
            if ((☃ != null) && (a(☃, ☃.o(), true)) && (☃.a(☃.o())) && (☃.o().b >= this.h.size()) && (b(☃)))
            {
              adq ☃ = ☃.k();
              int ☃ = ☃.e() ? ☃.d().b : 0;
              a(this.h, this.f, ☃, ☃);
              if (☃.b > ☃.c()) {
                ☃.b = ☃.c();
              }
              if (☃.b > ☃.b(☃)) {
                ☃.b = ☃.b(☃);
              }
              ☃ -= ☃.b - ☃;
              ☃.d(☃);
            }
          }
          ☃.b = ☃;
          if (☃.b <= 0) {
            ☃ = null;
          }
          ☃.e(☃);
        }
        d();
      }
      else
      {
        d();
      }
    }
    else if (this.g != 0)
    {
      d();
    }
    else if (((☃ == aaz.a) || (☃ == aaz.b)) && ((☃ == 0) || (☃ == 1)))
    {
      if (☃ == 64537)
      {
        if (☃.o() != null)
        {
          if (☃ == 0)
          {
            ☃.a(☃.o(), true);
            ☃.e(null);
          }
          if (☃ == 1)
          {
            ☃.a(☃.o().a(1), true);
            if (☃.o().b == 0) {
              ☃.e(null);
            }
          }
        }
      }
      else if (☃ == aaz.b)
      {
        if (☃ < 0) {
          return null;
        }
        abt ☃ = (abt)this.c.get(☃);
        if ((☃ != null) && (☃.a(☃)))
        {
          adq ☃ = ☃.d();
          if ((☃ != null) && (☃.b <= 0))
          {
            ☃ = ☃.k();
            ☃.d(null);
          }
          adq ☃ = b(☃, ☃);
          if (☃ != null)
          {
            ado ☃ = ☃.b();
            ☃ = ☃.k();
            if ((☃.d() != null) && (☃.d().b() == ☃)) {
              a(☃, ☃, true, ☃);
            }
          }
        }
      }
      else
      {
        if (☃ < 0) {
          return null;
        }
        abt ☃ = (abt)this.c.get(☃);
        if (☃ != null)
        {
          adq ☃ = ☃.d();
          adq ☃ = ☃.o();
          if (☃ != null) {
            ☃ = ☃.k();
          }
          if (☃ == null)
          {
            if ((☃ != null) && (☃.a(☃)))
            {
              int ☃ = ☃ == 0 ? ☃.b : 1;
              if (☃ > ☃.b(☃)) {
                ☃ = ☃.b(☃);
              }
              ☃.d(☃.a(☃));
              if (☃.b == 0) {
                ☃.e(null);
              }
            }
          }
          else if (☃.a(☃)) {
            if (☃ == null)
            {
              if (☃.b > 0)
              {
                int ☃ = ☃ == 0 ? ☃.b : (☃.b + 1) / 2;
                
                ☃.e(☃.a(☃));
                if (☃.b <= 0) {
                  ☃.d(null);
                }
                ☃.a(☃, ☃.o());
              }
              else
              {
                ☃.d(null);
                ☃.e(null);
              }
            }
            else if (☃.a(☃))
            {
              if ((☃.b() != ☃.b()) || (☃.i() != ☃.i()) || (!adq.a(☃, ☃)))
              {
                if (☃.b <= ☃.b(☃))
                {
                  ☃.d(☃);
                  ☃.e(☃);
                }
              }
              else
              {
                int ☃ = ☃ == 0 ? ☃.b : 1;
                if (☃ > ☃.b(☃) - ☃.b) {
                  ☃ = ☃.b(☃) - ☃.b;
                }
                if (☃ > ☃.c() - ☃.b) {
                  ☃ = ☃.c() - ☃.b;
                }
                ☃.a(☃);
                if (☃.b == 0) {
                  ☃.e(null);
                }
                ☃.b += ☃;
              }
            }
            else if ((☃.b() == ☃.b()) && (☃.c() > 1) && ((!☃.f()) || (☃.i() == ☃.i())) && (adq.a(☃, ☃)))
            {
              int ☃ = ☃.b;
              if ((☃ > 0) && (☃ + ☃.b <= ☃.c()))
              {
                ☃.b += ☃;
                ☃ = ☃.a(☃);
                if (☃.b == 0) {
                  ☃.d(null);
                }
                ☃.a(☃, ☃.o());
              }
            }
          }
          ☃.f();
        }
      }
    }
    else if ((☃ == aaz.c) && (☃ >= 0) && (☃ < 9))
    {
      abt ☃ = (abt)this.c.get(☃);
      adq ☃ = ☃.a(☃);
      if ((☃ != null) && (☃.b <= 0))
      {
        ☃ = null;
        ☃.a(☃, null);
      }
      adq ☃ = ☃.d();
      if ((☃ != null) || (☃ != null)) {
        if (☃ == null)
        {
          if (☃.a(☃))
          {
            adq ☃ = ☃;
            ☃.a(☃, ☃);
            ☃.d(null);
            ☃.a(☃, ☃);
          }
        }
        else if (☃ == null)
        {
          if (☃.a(☃))
          {
            int ☃ = ☃.b(☃);
            if (☃.b > ☃)
            {
              ☃.d(☃.a(☃));
            }
            else
            {
              ☃.d(☃);
              ☃.a(☃, null);
            }
          }
        }
        else if ((☃.a(☃)) && (☃.a(☃)))
        {
          int ☃ = ☃.b(☃);
          if (☃.b > ☃)
          {
            ☃.d(☃.a(☃));
            ☃.a(☃, ☃);
            if (!☃.c(☃)) {
              ☃.a(☃, true);
            }
          }
          else
          {
            ☃.d(☃);
            ☃.a(☃, ☃);
            ☃.a(☃, ☃);
          }
        }
      }
    }
    else if ((☃ == aaz.d) && (☃.bJ.d) && (☃.o() == null) && (☃ >= 0))
    {
      abt ☃ = (abt)this.c.get(☃);
      if ((☃ != null) && (☃.e())) {
        if (☃.d().b > 0)
        {
          adq ☃ = ☃.d().k();
          ☃.b = ☃.c();
          ☃.e(☃);
        }
        else
        {
          ☃.d(null);
        }
      }
    }
    else if ((☃ == aaz.e) && (☃.o() == null) && (☃ >= 0))
    {
      abt ☃ = (abt)this.c.get(☃);
      if ((☃ != null) && (☃.e()) && (☃.a(☃)))
      {
        adq ☃ = ☃.a(☃ == 0 ? 1 : ☃.d().b);
        ☃.a(☃, ☃);
        ☃.a(☃, true);
      }
    }
    else if ((☃ == aaz.g) && (☃ >= 0))
    {
      abt ☃ = (abt)this.c.get(☃);
      adq ☃ = ☃.o();
      if ((☃ != null) && ((☃ == null) || (!☃.e()) || (!☃.a(☃))))
      {
        int ☃ = ☃ == 0 ? 0 : this.c.size() - 1;
        int ☃ = ☃ == 0 ? 1 : -1;
        for (int ☃ = 0; ☃ < 2; ☃++) {
          for (int ☃ = ☃; (☃ >= 0) && (☃ < this.c.size()) && (☃.b < ☃.c()); ☃ += ☃)
          {
            abt ☃ = (abt)this.c.get(☃);
            if ((☃.e()) && (a(☃, ☃, true)) && (☃.a(☃)) && (a(☃, ☃)) && (
              (☃ != 0) || (☃.d().b != ☃.d().c())))
            {
              int ☃ = Math.min(☃.c() - ☃.b, ☃.d().b);
              adq ☃ = ☃.a(☃);
              ☃.b += ☃;
              if (☃.b <= 0) {
                ☃.d(null);
              }
              ☃.a(☃, ☃);
            }
          }
        }
      }
      b();
    }
    return ☃;
  }
  
  public boolean a(adq ☃, abt ☃)
  {
    return true;
  }
  
  protected void a(int ☃, int ☃, boolean ☃, zj ☃)
  {
    a(☃, ☃, aaz.b, ☃);
  }
  
  public void b(zj ☃)
  {
    zi ☃ = ☃.br;
    if (☃.o() != null)
    {
      ☃.a(☃.o(), false);
      ☃.e(null);
    }
  }
  
  public void a(qg ☃)
  {
    b();
  }
  
  public void a(int ☃, adq ☃)
  {
    a(☃).d(☃);
  }
  
  public void a(adq[] ☃)
  {
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      a(☃).d(☃[☃]);
    }
  }
  
  public void b(int ☃, int ☃) {}
  
  public short a(zi ☃)
  {
    this.a = ((short)(this.a + 1));
    return this.a;
  }
  
  private Set<zj> i = Sets.newHashSet();
  
  public boolean c(zj ☃)
  {
    return !this.i.contains(☃);
  }
  
  public void a(zj ☃, boolean ☃)
  {
    if (☃) {
      this.i.remove(☃);
    } else {
      this.i.add(☃);
    }
  }
  
  public abstract boolean a(zj paramzj);
  
  protected boolean a(adq ☃, int ☃, int ☃, boolean ☃)
  {
    boolean ☃ = false;
    
    int ☃ = ☃;
    if (☃) {
      ☃ = ☃ - 1;
    }
    if (☃.d()) {
      while ((☃.b > 0) && (((!☃) && (☃ < ☃)) || ((☃) && (☃ >= ☃))))
      {
        abt ☃ = (abt)this.c.get(☃);
        adq ☃ = ☃.d();
        if ((☃ != null) && (a(☃, ☃)))
        {
          int ☃ = ☃.b + ☃.b;
          if (☃ <= ☃.c())
          {
            ☃.b = 0;
            ☃.b = ☃;
            ☃.f();
            ☃ = true;
          }
          else if (☃.b < ☃.c())
          {
            ☃.b -= ☃.c() - ☃.b;
            ☃.b = ☃.c();
            ☃.f();
            ☃ = true;
          }
        }
        if (☃) {
          ☃--;
        } else {
          ☃++;
        }
      }
    }
    if (☃.b > 0)
    {
      if (☃) {
        ☃ = ☃ - 1;
      } else {
        ☃ = ☃;
      }
      while (((!☃) && (☃ < ☃)) || ((☃) && (☃ >= ☃)))
      {
        abt ☃ = (abt)this.c.get(☃);
        adq ☃ = ☃.d();
        if (☃ == null)
        {
          ☃.d(☃.k());
          ☃.f();
          ☃.b = 0;
          ☃ = true;
          break;
        }
        if (☃) {
          ☃--;
        } else {
          ☃++;
        }
      }
    }
    return ☃;
  }
  
  private static boolean a(adq ☃, adq ☃)
  {
    return (☃.b() == ☃.b()) && ((!☃.f()) || (☃.i() == ☃.i())) && (adq.a(☃, ☃));
  }
  
  public static int b(int ☃)
  {
    return ☃ >> 2 & 0x3;
  }
  
  public static int c(int ☃)
  {
    return ☃ & 0x3;
  }
  
  public static int d(int ☃, int ☃)
  {
    return ☃ & 0x3 | (☃ & 0x3) << 2;
  }
  
  public static boolean a(int ☃, zj ☃)
  {
    if (☃ == 0) {
      return true;
    }
    if (☃ == 1) {
      return true;
    }
    if ((☃ == 2) && (☃.bJ.d)) {
      return true;
    }
    return false;
  }
  
  protected void d()
  {
    this.g = 0;
    this.h.clear();
  }
  
  public static boolean a(abt ☃, adq ☃, boolean ☃)
  {
    boolean ☃ = (☃ == null) || (!☃.e());
    if ((☃ != null) && (☃.e()) && (☃ != null) && (☃.a(☃.d())) && (adq.a(☃.d(), ☃))) {
      ☃ |= ☃.d().b + (☃ ? 0 : ☃.b) <= ☃.c();
    }
    return ☃;
  }
  
  public static void a(Set<abt> ☃, int ☃, adq ☃, int ☃)
  {
    switch (☃)
    {
    case 0: 
      ☃.b = on.d(☃.b / ☃.size());
      break;
    case 1: 
      ☃.b = 1;
      break;
    case 2: 
      ☃.b = ☃.b().j();
    }
    ☃.b += ☃;
  }
  
  public boolean b(abt ☃)
  {
    return true;
  }
  
  public static int a(apv ☃)
  {
    if ((☃ instanceof qg)) {
      return b((qg)☃);
    }
    return 0;
  }
  
  public static int b(qg ☃)
  {
    if (☃ == null) {
      return 0;
    }
    int ☃ = 0;
    float ☃ = 0.0F;
    for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
    {
      adq ☃ = ☃.a(☃);
      if (☃ != null)
      {
        ☃ += ☃.b / Math.min(☃.w_(), ☃.c());
        ☃++;
      }
    }
    ☃ /= ☃.u_();
    return on.d(☃ * 14.0F) + (☃ > 0 ? 1 : 0);
  }
}
