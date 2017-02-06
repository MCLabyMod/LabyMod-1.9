import com.google.common.collect.Lists;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class aoq
  extends ajt
{
  public static final arn a = arn.a("wet");
  
  protected aoq()
  {
    super(axe.m);
    w(this.A.b().a(a, Boolean.valueOf(false)));
    a(acq.b);
  }
  
  public String c()
  {
    return di.a(a() + ".dry.name");
  }
  
  public int d(arc ☃)
  {
    return ((Boolean)☃.c(a)).booleanValue() ? 1 : 0;
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    e(☃, ☃, ☃);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    e(☃, ☃, ☃);
    super.a(☃, ☃, ☃, ☃);
  }
  
  protected void e(aht ☃, cj ☃, arc ☃)
  {
    if ((!((Boolean)☃.c(a)).booleanValue()) && (b(☃, ☃)))
    {
      ☃.a(☃, ☃.a(a, Boolean.valueOf(true)), 2);
      ☃.b(2001, ☃, ajt.a(aju.j));
    }
  }
  
  private boolean b(aht ☃, cj ☃)
  {
    Queue<ou<cj, Integer>> ☃ = Lists.newLinkedList();
    List<cj> ☃ = Lists.newArrayList();
    ☃.add(new ou(☃, Integer.valueOf(0)));
    
    int ☃ = 0;
    while (!☃.isEmpty())
    {
      ou<cj, Integer> ☃ = (ou)☃.poll();
      cj ☃ = (cj)☃.a();
      int ☃ = ((Integer)☃.b()).intValue();
      for (cq ☃ : cq.values())
      {
        cj ☃ = ☃.a(☃);
        if (☃.o(☃).a() == axe.h)
        {
          ☃.a(☃, aju.a.u(), 2);
          ☃.add(☃);
          ☃++;
          if (☃ < 6) {
            ☃.add(new ou(☃, Integer.valueOf(☃ + 1)));
          }
        }
      }
      if (☃ > 64) {
        break;
      }
    }
    for (cj ☃ : ☃) {
      ☃.d(☃, aju.a);
    }
    return ☃ > 0;
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    ☃.add(new adq(☃, 1, 0));
    ☃.add(new adq(☃, 1, 1));
  }
  
  public arc a(int ☃)
  {
    return u().a(a, Boolean.valueOf((☃ & 0x1) == 1));
  }
  
  public int e(arc ☃)
  {
    return ((Boolean)☃.c(a)).booleanValue() ? 1 : 0;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
  
  public void a(arc ☃, aht ☃, cj ☃, Random ☃)
  {
    if (!((Boolean)☃.c(a)).booleanValue()) {
      return;
    }
    cq ☃ = cq.a(☃);
    if ((☃ == cq.b) || (☃.o(☃.a(☃)).q())) {
      return;
    }
    double ☃ = ☃.p();
    double ☃ = ☃.q();
    double ☃ = ☃.r();
    if (☃ == cq.a)
    {
      ☃ -= 0.05D;
      ☃ += ☃.nextDouble();
      ☃ += ☃.nextDouble();
    }
    else
    {
      ☃ += ☃.nextDouble() * 0.8D;
      if (☃.k() == cq.a.a)
      {
        ☃ += ☃.nextDouble();
        if (☃ == cq.f) {
          ☃ += 1.1D;
        } else {
          ☃ += 0.05D;
        }
      }
      else
      {
        ☃ += ☃.nextDouble();
        if (☃ == cq.d) {
          ☃ += 1.1D;
        } else {
          ☃ += 0.05D;
        }
      }
    }
    ☃.a(cy.s, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
  }
}
