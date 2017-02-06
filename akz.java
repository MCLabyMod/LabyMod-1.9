import java.util.EnumSet;
import java.util.Random;
import java.util.Set;

public class akz
  extends amo
{
  int a;
  
  protected akz(axe ☃)
  {
    super(☃);
  }
  
  private void f(aht ☃, cj ☃, arc ☃)
  {
    ☃.a(☃, b(this.x).u().a(b, ☃.c(b)), 2);
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    int ☃ = ((Integer)☃.c(b)).intValue();
    
    int ☃ = 1;
    if ((this.x == axe.i) && (!☃.s.l())) {
      ☃ = 2;
    }
    int ☃ = a(☃);
    if (☃ > 0)
    {
      int ☃ = -100;
      this.a = 0;
      for (cq ☃ : cq.c.a) {
        ☃ = a(☃, ☃.a(☃), ☃);
      }
      int ☃ = ☃ + ☃;
      if ((☃ >= 8) || (☃ < 0)) {
        ☃ = -1;
      }
      if (c(☃, ☃.a()) >= 0)
      {
        int ☃ = c(☃, ☃.a());
        if (☃ >= 8) {
          ☃ = ☃;
        } else {
          ☃ = ☃ + 8;
        }
      }
      if ((this.a >= 2) && (this.x == axe.h))
      {
        arc ☃ = ☃.o(☃.b());
        if (☃.a().a()) {
          ☃ = 0;
        } else if ((☃.a() == this.x) && (((Integer)☃.c(b)).intValue() == 0)) {
          ☃ = 0;
        }
      }
      if ((this.x == axe.i) && 
        (☃ < 8) && (☃ < 8) && 
        (☃ > ☃) && 
        (☃.nextInt(4) != 0)) {
        ☃ *= 4;
      }
      if (☃ == ☃)
      {
        f(☃, ☃, ☃);
      }
      else
      {
        ☃ = ☃;
        if (☃ < 0)
        {
          ☃.g(☃);
        }
        else
        {
          ☃ = ☃.a(b, Integer.valueOf(☃));
          ☃.a(☃, ☃, 2);
          ☃.a(☃, this, ☃);
          ☃.d(☃, this);
        }
      }
    }
    else
    {
      f(☃, ☃, ☃);
    }
    arc ☃ = ☃.o(☃.b());
    int ☃;
    if (h(☃, ☃.b(), ☃))
    {
      if ((this.x == axe.i) && 
        (☃.o(☃.b()).a() == axe.h))
      {
        ☃.a(☃.b(), aju.b.u());
        b(☃, ☃.b());
        return;
      }
      if (☃ >= 8) {
        a(☃, ☃.b(), ☃, ☃);
      } else {
        a(☃, ☃.b(), ☃, ☃ + 8);
      }
    }
    else if ((☃ >= 0) && ((☃ == 0) || (g(☃, ☃.b(), ☃))))
    {
      Set<cq> ☃ = c(☃, ☃);
      ☃ = ☃ + ☃;
      if (☃ >= 8) {
        ☃ = 1;
      }
      if (☃ >= 8) {
        return;
      }
      for (cq ☃ : ☃) {
        a(☃, ☃.a(☃), ☃.o(☃.a(☃)), ☃);
      }
    }
  }
  
  private void a(aht ☃, cj ☃, arc ☃, int ☃)
  {
    if (h(☃, ☃, ☃))
    {
      if (☃.t() != aju.a) {
        if (this.x == axe.i) {
          b(☃, ☃);
        } else {
          ☃.t().b(☃, ☃, ☃, 0);
        }
      }
      ☃.a(☃, u().a(b, Integer.valueOf(☃)), 3);
    }
  }
  
  private int a(aht ☃, cj ☃, int ☃, cq ☃)
  {
    int ☃ = 1000;
    for (cq ☃ : cq.c.a) {
      if (☃ != ☃)
      {
        cj ☃ = ☃.a(☃);
        arc ☃ = ☃.o(☃);
        if ((!g(☃, ☃, ☃)) && ((☃.a() != this.x) || (((Integer)☃.c(b)).intValue() > 0))) {
          if (g(☃, ☃.b(), ☃))
          {
            if (☃ < b(☃))
            {
              int ☃ = a(☃, ☃, ☃ + 1, ☃.d());
              if (☃ < ☃) {
                ☃ = ☃;
              }
            }
          }
          else {
            return ☃;
          }
        }
      }
    }
    return ☃;
  }
  
  private int b(aht ☃)
  {
    if ((this.x == axe.i) && (!☃.s.l())) {
      return 2;
    }
    return 4;
  }
  
  private Set<cq> c(aht ☃, cj ☃)
  {
    int ☃ = 1000;
    Set<cq> ☃ = EnumSet.noneOf(cq.class);
    for (cq ☃ : cq.c.a)
    {
      cj ☃ = ☃.a(☃);
      arc ☃ = ☃.o(☃);
      if ((!g(☃, ☃, ☃)) && ((☃.a() != this.x) || (((Integer)☃.c(b)).intValue() > 0)))
      {
        int ☃;
        int ☃;
        if (g(☃, ☃.b(), ☃.o(☃.b()))) {
          ☃ = a(☃, ☃, 1, ☃.d());
        } else {
          ☃ = 0;
        }
        if (☃ < ☃) {
          ☃.clear();
        }
        if (☃ <= ☃)
        {
          ☃.add(☃);
          ☃ = ☃;
        }
      }
    }
    return ☃;
  }
  
  private boolean g(aht ☃, cj ☃, arc ☃)
  {
    ajt ☃ = ☃.o(☃).t();
    if (((☃ instanceof akv)) || (☃ == aju.an) || (☃ == aju.au) || (☃ == aju.aM)) {
      return true;
    }
    if (☃.x == axe.E) {
      return true;
    }
    return ☃.x.c();
  }
  
  protected int a(aht ☃, cj ☃, int ☃)
  {
    int ☃ = c(☃, ☃);
    if (☃ < 0) {
      return ☃;
    }
    if (☃ == 0) {
      this.a += 1;
    }
    if (☃ >= 8) {
      ☃ = 0;
    }
    return (☃ < 0) || (☃ < ☃) ? ☃ : ☃;
  }
  
  private boolean h(aht ☃, cj ☃, arc ☃)
  {
    axe ☃ = ☃.a();
    return (☃ != this.x) && (☃ != axe.i) && (!g(☃, ☃, ☃));
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    if (!e(☃, ☃, ☃)) {
      ☃.a(☃, this, a(☃));
    }
  }
}
