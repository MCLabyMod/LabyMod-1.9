import java.util.Random;

public class ali
  extends ajt
{
  public static final arq a = arq.a("moisture", 0, 7);
  protected static final bbh b = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
  
  protected ali()
  {
    super(axe.c);
    w(this.A.b().a(a, Integer.valueOf(0)));
    a(true);
    d(255);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return b;
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    return j;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    int ☃ = ((Integer)☃.c(a)).intValue();
    if ((c(☃, ☃)) || (☃.B(☃.a())))
    {
      if (☃ < 7) {
        ☃.a(☃, ☃.a(a, Integer.valueOf(7)), 2);
      }
    }
    else if (☃ > 0) {
      ☃.a(☃, ☃.a(a, Integer.valueOf(☃ - 1)), 2);
    } else if (!b(☃, ☃)) {
      ☃.a(☃, aju.d.u());
    }
  }
  
  public void a(aht ☃, cj ☃, rr ☃, float ☃)
  {
    if ((!☃.E) && (☃.r.nextFloat() < ☃ - 0.5F) && ((☃ instanceof sa)) && (
      ((☃ instanceof zj)) || (☃.U().b("mobGriefing")))) {
      if (☃.G * ☃.G * ☃.H > 0.512F) {
        ☃.a(☃, aju.d.u());
      }
    }
    super.a(☃, ☃, ☃, ☃);
  }
  
  private boolean b(aht ☃, cj ☃)
  {
    ajt ☃ = ☃.o(☃.a()).t();
    return ((☃ instanceof akn)) || ((☃ instanceof aow));
  }
  
  private boolean c(aht ☃, cj ☃)
  {
    for (cj.a ☃ : cj.b(☃.a(-4, 0, -4), ☃.a(4, 1, 4))) {
      if (☃.o(☃).a() == axe.h) {
        return true;
      }
    }
    return false;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    super.a(☃, ☃, ☃, ☃);
    if (☃.o(☃.a()).a().a()) {
      ☃.a(☃, aju.d.u());
    }
  }
  
  public boolean a(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    switch (ali.1.a[☃.ordinal()])
    {
    case 1: 
      return true;
    case 2: 
    case 3: 
    case 4: 
    case 5: 
      arc ☃ = ☃.o(☃.a(☃));
      ajt ☃ = ☃.t();
      return (!☃.p()) && (☃ != aju.ak) && (☃ != aju.da);
    }
    return super.a(☃, ☃, ☃, ☃);
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return aju.d.a(aju.d.u().a(akt.a, akt.a.a), ☃, ☃);
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(aju.d);
  }
  
  public arc a(int ☃)
  {
    return u().a(a, Integer.valueOf(☃ & 0x7));
  }
  
  public int e(arc ☃)
  {
    return ((Integer)☃.c(a)).intValue();
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
}
