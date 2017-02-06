import java.util.Random;

public abstract class ajo
  extends ajt
{
  protected static final bbh a = new bbh(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.03125D, 0.9375D);
  protected static final bbh b = new bbh(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.0625D, 0.9375D);
  protected static final bbh c = new bbh(0.125D, 0.0D, 0.125D, 0.875D, 0.25D, 0.875D);
  
  protected ajo(axe ☃)
  {
    this(☃, ☃.r());
  }
  
  protected ajo(axe ☃, axf ☃)
  {
    super(☃, ☃);
    a(acq.d);
    a(true);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    boolean ☃ = i(☃) > 0;
    if (☃) {
      return a;
    }
    return b;
  }
  
  public int a(aht ☃)
  {
    return 20;
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    return k;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean b(ahx ☃, cj ☃)
  {
    return true;
  }
  
  public boolean d()
  {
    return true;
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    return i(☃, ☃.b());
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (!i(☃, ☃.b()))
    {
      b(☃, ☃, ☃, 0);
      ☃.g(☃);
    }
  }
  
  private boolean i(aht ☃, cj ☃)
  {
    return (☃.o(☃).q()) || ((☃.o(☃).t() instanceof alj));
  }
  
  public void a(aht ☃, cj ☃, arc ☃, Random ☃) {}
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (☃.E) {
      return;
    }
    int ☃ = i(☃);
    if (☃ > 0) {
      a(☃, ☃, ☃, ☃);
    }
  }
  
  public void a(aht ☃, cj ☃, arc ☃, rr ☃)
  {
    if (☃.E) {
      return;
    }
    int ☃ = i(☃);
    if (☃ == 0) {
      a(☃, ☃, ☃, ☃);
    }
  }
  
  protected void a(aht ☃, cj ☃, arc ☃, int ☃)
  {
    int ☃ = e(☃, ☃);
    boolean ☃ = ☃ > 0;
    boolean ☃ = ☃ > 0;
    if (☃ != ☃)
    {
      ☃ = a(☃, ☃);
      ☃.a(☃, ☃, 2);
      d(☃, ☃);
      ☃.b(☃, ☃);
    }
    if ((!☃) && (☃)) {
      c(☃, ☃);
    } else if ((☃) && (!☃)) {
      b(☃, ☃);
    }
    if (☃) {
      ☃.a(new cj(☃), this, a(☃));
    }
  }
  
  protected abstract void b(aht paramaht, cj paramcj);
  
  protected abstract void c(aht paramaht, cj paramcj);
  
  public void b(aht ☃, cj ☃, arc ☃)
  {
    if (i(☃) > 0) {
      d(☃, ☃);
    }
    super.b(☃, ☃, ☃);
  }
  
  protected void d(aht ☃, cj ☃)
  {
    ☃.d(☃, this);
    ☃.d(☃.b(), this);
  }
  
  public int b(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    return i(☃);
  }
  
  public int c(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    if (☃ == cq.b) {
      return i(☃);
    }
    return 0;
  }
  
  public boolean g(arc ☃)
  {
    return true;
  }
  
  public axh h(arc ☃)
  {
    return axh.b;
  }
  
  protected abstract int e(aht paramaht, cj paramcj);
  
  protected abstract int i(arc paramarc);
  
  protected abstract arc a(arc paramarc, int paramInt);
}
