import java.util.List;
import java.util.Random;

public abstract class ajz
  extends aks
{
  public static final arn a = arn.a("powered");
  protected static final bbh b = new bbh(0.3125D, 0.875D, 0.375D, 0.6875D, 1.0D, 0.625D);
  protected static final bbh c = new bbh(0.3125D, 0.0D, 0.375D, 0.6875D, 0.125D, 0.625D);
  protected static final bbh d = new bbh(0.3125D, 0.375D, 0.875D, 0.6875D, 0.625D, 1.0D);
  protected static final bbh e = new bbh(0.3125D, 0.375D, 0.0D, 0.6875D, 0.625D, 0.125D);
  protected static final bbh f = new bbh(0.875D, 0.375D, 0.3125D, 1.0D, 0.625D, 0.6875D);
  protected static final bbh g = new bbh(0.0D, 0.375D, 0.3125D, 0.125D, 0.625D, 0.6875D);
  protected static final bbh B = new bbh(0.3125D, 0.9375D, 0.375D, 0.6875D, 1.0D, 0.625D);
  protected static final bbh C = new bbh(0.3125D, 0.0D, 0.375D, 0.6875D, 0.0625D, 0.625D);
  protected static final bbh D = new bbh(0.3125D, 0.375D, 0.9375D, 0.6875D, 0.625D, 1.0D);
  protected static final bbh E = new bbh(0.3125D, 0.375D, 0.0D, 0.6875D, 0.625D, 0.0625D);
  protected static final bbh F = new bbh(0.9375D, 0.375D, 0.3125D, 1.0D, 0.625D, 0.6875D);
  protected static final bbh G = new bbh(0.0D, 0.375D, 0.3125D, 0.0625D, 0.625D, 0.6875D);
  private final boolean I;
  
  protected ajz(boolean ☃)
  {
    super(axe.q);
    w(this.A.b().a(H, cq.c).a(a, Boolean.valueOf(false)));
    a(true);
    a(acq.d);
    this.I = ☃;
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    return k;
  }
  
  public int a(aht ☃)
  {
    return this.I ? 30 : 20;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean b(aht ☃, cj ☃, cq ☃)
  {
    return a(☃, ☃, ☃.d());
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    for (cq ☃ : ) {
      if (a(☃, ☃, ☃)) {
        return true;
      }
    }
    return false;
  }
  
  protected static boolean a(aht ☃, cj ☃, cq ☃)
  {
    cj ☃ = ☃.a(☃);
    if (☃ == cq.a) {
      return ☃.o(☃).q();
    }
    return ☃.o(☃).l();
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    if (a(☃, ☃, ☃.d())) {
      return u().a(H, ☃).a(a, Boolean.valueOf(false));
    }
    return u().a(H, cq.a).a(a, Boolean.valueOf(false));
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if ((e(☃, ☃, ☃)) && 
      (!a(☃, ☃, ((cq)☃.c(H)).d())))
    {
      b(☃, ☃, ☃, 0);
      ☃.g(☃);
    }
  }
  
  private boolean e(aht ☃, cj ☃, arc ☃)
  {
    if (a(☃, ☃)) {
      return true;
    }
    b(☃, ☃, ☃, 0);
    ☃.g(☃);
    return false;
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    cq ☃ = (cq)☃.c(H);
    boolean ☃ = ((Boolean)☃.c(a)).booleanValue();
    switch (ajz.1.a[☃.ordinal()])
    {
    case 1: 
      return ☃ ? G : g;
    case 2: 
      return ☃ ? F : f;
    case 3: 
      return ☃ ? E : e;
    case 4: 
    default: 
      return ☃ ? D : d;
    case 5: 
      return ☃ ? C : c;
    }
    return ☃ ? B : b;
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (((Boolean)☃.c(a)).booleanValue()) {
      return true;
    }
    ☃.a(☃, ☃.a(a, Boolean.valueOf(true)), 3);
    ☃.b(☃, ☃);
    
    a(☃, ☃, ☃);
    
    c(☃, ☃, (cq)☃.c(H));
    
    ☃.a(☃, this, a(☃));
    
    return true;
  }
  
  protected abstract void a(zj paramzj, aht paramaht, cj paramcj);
  
  protected abstract void b(aht paramaht, cj paramcj);
  
  public void b(aht ☃, cj ☃, arc ☃)
  {
    if (((Boolean)☃.c(a)).booleanValue()) {
      c(☃, ☃, (cq)☃.c(H));
    }
    super.b(☃, ☃, ☃);
  }
  
  public int b(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    return ((Boolean)☃.c(a)).booleanValue() ? 15 : 0;
  }
  
  public int c(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    if (!((Boolean)☃.c(a)).booleanValue()) {
      return 0;
    }
    if (☃.c(H) == ☃) {
      return 15;
    }
    return 0;
  }
  
  public boolean g(arc ☃)
  {
    return true;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, Random ☃) {}
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (☃.E) {
      return;
    }
    if (!((Boolean)☃.c(a)).booleanValue()) {
      return;
    }
    if (this.I)
    {
      e(☃, ☃, ☃);
    }
    else
    {
      ☃.a(☃, ☃.a(a, Boolean.valueOf(false)));
      
      c(☃, ☃, (cq)☃.c(H));
      
      b(☃, ☃);
      ☃.b(☃, ☃);
    }
  }
  
  public void a(aht ☃, cj ☃, arc ☃, rr ☃)
  {
    if (☃.E) {
      return;
    }
    if (!this.I) {
      return;
    }
    if (((Boolean)☃.c(a)).booleanValue()) {
      return;
    }
    e(☃, ☃, ☃);
  }
  
  private void e(arc ☃, aht ☃, cj ☃)
  {
    List<? extends rr> ☃ = ☃.a(zm.class, ☃.c(☃, ☃).a(☃));
    boolean ☃ = !☃.isEmpty();
    boolean ☃ = ((Boolean)☃.c(a)).booleanValue();
    if ((☃) && (!☃))
    {
      ☃.a(☃, ☃.a(a, Boolean.valueOf(true)));
      c(☃, ☃, (cq)☃.c(H));
      ☃.b(☃, ☃);
      
      a(null, ☃, ☃);
    }
    if ((!☃) && (☃))
    {
      ☃.a(☃, ☃.a(a, Boolean.valueOf(false)));
      c(☃, ☃, (cq)☃.c(H));
      ☃.b(☃, ☃);
      
      b(☃, ☃);
    }
    if (☃) {
      ☃.a(new cj(☃), this, a(☃));
    }
  }
  
  private void c(aht ☃, cj ☃, cq ☃)
  {
    ☃.d(☃, this);
    ☃.d(☃.a(☃.d()), this);
  }
  
  public arc a(int ☃)
  {
    cq ☃;
    switch (☃ & 0x7)
    {
    case 0: 
      ☃ = cq.a;
      break;
    case 1: 
      ☃ = cq.f;
      break;
    case 2: 
      ☃ = cq.e;
      break;
    case 3: 
      ☃ = cq.d;
      break;
    case 4: 
      ☃ = cq.c;
      break;
    case 5: 
    default: 
      ☃ = cq.b;
    }
    return u().a(H, ☃).a(a, Boolean.valueOf((☃ & 0x8) > 0));
  }
  
  public int e(arc ☃)
  {
    int ☃;
    switch (ajz.1.a[((cq)☃.c(H)).ordinal()])
    {
    case 6: 
      ☃ = 0;
      break;
    case 1: 
      ☃ = 1;
      break;
    case 2: 
      ☃ = 2;
      break;
    case 3: 
      ☃ = 3;
      break;
    case 4: 
      ☃ = 4;
      break;
    case 5: 
    default: 
      ☃ = 5;
    }
    if (((Boolean)☃.c(a)).booleanValue()) {
      ☃ |= 0x8;
    }
    return ☃;
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    return ☃.a(H, ☃.a((cq)☃.c(H)));
  }
  
  public arc a(arc ☃, amr ☃)
  {
    return ☃.a(☃.a((cq)☃.c(H)));
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { H, a });
  }
}
