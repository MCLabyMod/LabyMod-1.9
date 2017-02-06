public class alk
  extends amg
{
  public static final arn a = arn.a("open");
  public static final arn b = arn.a("powered");
  public static final arn c = arn.a("in_wall");
  protected static final bbh d = new bbh(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D);
  protected static final bbh e = new bbh(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D);
  protected static final bbh f = new bbh(0.0D, 0.0D, 0.375D, 1.0D, 0.8125D, 0.625D);
  protected static final bbh g = new bbh(0.375D, 0.0D, 0.0D, 0.625D, 0.8125D, 1.0D);
  protected static final bbh B = new bbh(0.0D, 0.0D, 0.375D, 1.0D, 1.5D, 0.625D);
  protected static final bbh C = new bbh(0.375D, 0.0D, 0.0D, 0.625D, 1.5D, 1.0D);
  
  public alk(anj.a ☃)
  {
    super(axe.d, ☃.c());
    
    w(this.A.b().a(a, Boolean.valueOf(false)).a(b, Boolean.valueOf(false)).a(c, Boolean.valueOf(false)));
    a(acq.d);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    ☃ = b(☃, ☃, ☃);
    if (((Boolean)☃.c(c)).booleanValue()) {
      return ((cq)☃.c(D)).k() == cq.a.a ? g : f;
    }
    return ((cq)☃.c(D)).k() == cq.a.a ? e : d;
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    cq.a ☃ = ((cq)☃.c(D)).k();
    if (((☃ == cq.a.c) && ((☃.o(☃.e()).t() == aju.bZ) || (☃.o(☃.f()).t() == aju.bZ))) || ((☃ == cq.a.a) && ((☃.o(☃.c()).t() == aju.bZ) || (☃.o(☃.d()).t() == aju.bZ)))) {
      ☃ = ☃.a(c, Boolean.valueOf(true));
    }
    return ☃;
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    return ☃.a(D, ☃.a((cq)☃.c(D)));
  }
  
  public arc a(arc ☃, amr ☃)
  {
    return ☃.a(☃.a((cq)☃.c(D)));
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    if (☃.o(☃.b()).a().a()) {
      return super.a(☃, ☃);
    }
    return false;
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    if (((Boolean)☃.c(a)).booleanValue()) {
      return k;
    }
    return ((cq)☃.c(D)).k() == cq.a.c ? B : C;
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
    return ((Boolean)☃.o(☃).c(a)).booleanValue();
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    return u().a(D, ☃.bi()).a(a, Boolean.valueOf(false)).a(b, Boolean.valueOf(false)).a(c, Boolean.valueOf(false));
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (((Boolean)☃.c(a)).booleanValue())
    {
      ☃ = ☃.a(a, Boolean.valueOf(false));
      ☃.a(☃, ☃, 10);
    }
    else
    {
      cq ☃ = cq.a(☃.v);
      if (☃.c(D) == ☃.d()) {
        ☃ = ☃.a(D, ☃);
      }
      ☃ = ☃.a(a, Boolean.valueOf(true));
      ☃.a(☃, ☃, 10);
    }
    ☃.a(☃, ((Boolean)☃.c(a)).booleanValue() ? 1008 : 1014, ☃, 0);
    return true;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (☃.E) {
      return;
    }
    boolean ☃ = ☃.y(☃);
    if ((☃) || (☃.u().m())) {
      if ((☃) && (!((Boolean)☃.c(a)).booleanValue()) && (!((Boolean)☃.c(b)).booleanValue()))
      {
        ☃.a(☃, ☃.a(a, Boolean.valueOf(true)).a(b, Boolean.valueOf(true)), 2);
        ☃.a(null, 1008, ☃, 0);
      }
      else if ((!☃) && (((Boolean)☃.c(a)).booleanValue()) && (((Boolean)☃.c(b)).booleanValue()))
      {
        ☃.a(☃, ☃.a(a, Boolean.valueOf(false)).a(b, Boolean.valueOf(false)), 2);
        ☃.a(null, 1014, ☃, 0);
      }
      else if (☃ != ((Boolean)☃.c(b)).booleanValue())
      {
        ☃.a(☃, ☃.a(b, Boolean.valueOf(☃)), 2);
      }
    }
  }
  
  public boolean a(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    return true;
  }
  
  public arc a(int ☃)
  {
    return u().a(D, cq.b(☃)).a(a, Boolean.valueOf((☃ & 0x4) != 0)).a(b, Boolean.valueOf((☃ & 0x8) != 0));
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((cq)☃.c(D)).b();
    if (((Boolean)☃.c(b)).booleanValue()) {
      ☃ |= 0x8;
    }
    if (((Boolean)☃.c(a)).booleanValue()) {
      ☃ |= 0x4;
    }
    return ☃;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { D, a, b, c });
  }
}
