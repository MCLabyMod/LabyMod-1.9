import com.google.common.cache.LoadingCache;
import java.util.Random;

public class ank
  extends amb
{
  public static final arp<cq.a> a = arp.a("axis", cq.a.class, new cq.a[] { cq.a.a, cq.a.c });
  protected static final bbh b = new bbh(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D);
  protected static final bbh c = new bbh(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D);
  protected static final bbh d = new bbh(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
  
  public ank()
  {
    super(axe.E, false);
    w(this.A.b().a(a, cq.a.a));
    a(true);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    switch (ank.1.a[((cq.a)☃.c(a)).ordinal()])
    {
    case 1: 
      return b;
    case 2: 
    default: 
      return d;
    }
    return c;
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    super.b(☃, ☃, ☃, ☃);
    if ((☃.s.d()) && (☃.U().b("doMobSpawning")) && (☃.nextInt(2000) < ☃.ae().a()))
    {
      int ☃ = ☃.q();
      cj ☃ = ☃;
      while ((!☃.o(☃).q()) && (☃.q() > 0)) {
        ☃ = ☃.b();
      }
      if ((☃ > 0) && (!☃.o(☃.a()).l()))
      {
        rr ☃ = aeu.a(☃, rt.a(yr.class), ☃.p() + 0.5D, ☃.q() + 1.1D, ☃.r() + 0.5D);
        if (☃ != null) {
          ☃.aj = ☃.aC();
        }
      }
    }
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    return k;
  }
  
  public static int a(cq.a ☃)
  {
    if (☃ == cq.a.a) {
      return 1;
    }
    if (☃ == cq.a.c) {
      return 2;
    }
    return 0;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean b(aht ☃, cj ☃)
  {
    ank.a ☃ = new ank.a(☃, ☃, cq.a.a);
    if ((☃.d()) && (ank.a.a(☃) == 0))
    {
      ☃.e();
      return true;
    }
    ank.a ☃ = new ank.a(☃, ☃, cq.a.c);
    if ((☃.d()) && (ank.a.a(☃) == 0))
    {
      ☃.e();
      return true;
    }
    return false;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    cq.a ☃ = (cq.a)☃.c(a);
    if (☃ == cq.a.a)
    {
      ank.a ☃ = new ank.a(☃, ☃, cq.a.a);
      if ((!☃.d()) || (ank.a.a(☃) < ank.a.b(☃) * ank.a.c(☃))) {
        ☃.a(☃, aju.a.u());
      }
    }
    else if (☃ == cq.a.c)
    {
      ank.a ☃ = new ank.a(☃, ☃, cq.a.c);
      if ((!☃.d()) || (ank.a.a(☃) < ank.a.b(☃) * ank.a.c(☃))) {
        ☃.a(☃, aju.a.u());
      }
    }
  }
  
  public boolean a(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    ☃ = ☃.a(☃);
    
    cq.a ☃ = null;
    if (☃.t() == this)
    {
      ☃ = (cq.a)☃.c(a);
      if (☃ == null) {
        return false;
      }
      if ((☃ == cq.a.c) && (☃ != cq.f) && (☃ != cq.e)) {
        return false;
      }
      if ((☃ == cq.a.a) && (☃ != cq.d) && (☃ != cq.c)) {
        return false;
      }
    }
    boolean ☃ = (☃.o(☃.e()).t() == this) && (☃.o(☃.f(2)).t() != this);
    boolean ☃ = (☃.o(☃.f()).t() == this) && (☃.o(☃.g(2)).t() != this);
    
    boolean ☃ = (☃.o(☃.c()).t() == this) && (☃.o(☃.d(2)).t() != this);
    boolean ☃ = (☃.o(☃.d()).t() == this) && (☃.o(☃.e(2)).t() != this);
    
    boolean ☃ = (☃) || (☃) || (☃ == cq.a.a);
    boolean ☃ = (☃) || (☃) || (☃ == cq.a.c);
    if ((☃) && (☃ == cq.e)) {
      return true;
    }
    if ((☃) && (☃ == cq.f)) {
      return true;
    }
    if ((☃) && (☃ == cq.c)) {
      return true;
    }
    if ((☃) && (☃ == cq.d)) {
      return true;
    }
    return false;
  }
  
  public int a(Random ☃)
  {
    return 0;
  }
  
  public ahm f()
  {
    return ahm.d;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, rr ☃)
  {
    if ((!☃.aI()) && (!☃.aJ()) && (☃.aV())) {
      ☃.e(☃);
    }
  }
  
  public void a(arc ☃, aht ☃, cj ☃, Random ☃)
  {
    if (☃.nextInt(100) == 0) {
      ☃.a(☃.p() + 0.5D, ☃.q() + 0.5D, ☃.r() + 0.5D, ng.el, nh.e, 0.5F, ☃.nextFloat() * 0.4F + 0.8F, false);
    }
    for (int ☃ = 0; ☃ < 4; ☃++)
    {
      double ☃ = ☃.p() + ☃.nextFloat();
      double ☃ = ☃.q() + ☃.nextFloat();
      double ☃ = ☃.r() + ☃.nextFloat();
      double ☃ = (☃.nextFloat() - 0.5D) * 0.5D;
      double ☃ = (☃.nextFloat() - 0.5D) * 0.5D;
      double ☃ = (☃.nextFloat() - 0.5D) * 0.5D;
      
      int ☃ = ☃.nextInt(2) * 2 - 1;
      if ((☃.o(☃.e()).t() == this) || (☃.o(☃.f()).t() == this))
      {
        ☃ = ☃.r() + 0.5D + 0.25D * ☃;
        ☃ = ☃.nextFloat() * 2.0F * ☃;
      }
      else
      {
        ☃ = ☃.p() + 0.5D + 0.25D * ☃;
        ☃ = ☃.nextFloat() * 2.0F * ☃;
      }
      ☃.a(cy.y, ☃, ☃, ☃, ☃, ☃, ☃, new int[0]);
    }
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return null;
  }
  
  public arc a(int ☃)
  {
    return u().a(a, (☃ & 0x3) == 2 ? cq.a.c : cq.a.a);
  }
  
  public int e(arc ☃)
  {
    return a((cq.a)☃.c(a));
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    switch (ank.1.b[☃.ordinal()])
    {
    case 1: 
    case 2: 
      switch (ank.1.a[((cq.a)☃.c(a)).ordinal()])
      {
      case 1: 
        return ☃.a(a, cq.a.c);
      case 3: 
        return ☃.a(a, cq.a.a);
      }
      return ☃;
    }
    return ☃;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
  
  public arg.b c(aht ☃, cj ☃)
  {
    cq.a ☃ = cq.a.c;
    ank.a ☃ = new ank.a(☃, ☃, cq.a.a);
    LoadingCache<cj, arf> ☃ = arg.a(☃, true);
    if (!☃.d())
    {
      ☃ = cq.a.a;
      ☃ = new ank.a(☃, ☃, cq.a.c);
    }
    if (!☃.d()) {
      return new arg.b(☃, cq.c, cq.b, ☃, 1, 1, 1);
    }
    int[] ☃ = new int[cq.b.values().length];
    cq ☃ = ank.a.d(☃).f();
    cj ☃ = ank.a.e(☃).b(☃.a() - 1);
    for (cq.b ☃ : cq.b.values())
    {
      arg.b ☃ = new arg.b(☃.c() == ☃ ? ☃ : ☃.a(ank.a.d(☃), ☃.b() - 1), cq.a(☃, ☃), cq.b, ☃, ☃.b(), ☃.a(), 1);
      for (int ☃ = 0; ☃ < ☃.b(); ☃++) {
        for (int ☃ = 0; ☃ < ☃.a(); ☃++)
        {
          arf ☃ = ☃.a(☃, ☃, 1);
          if ((☃.a() != null) && (☃.a().a() != axe.a)) {
            ☃[☃.ordinal()] += 1;
          }
        }
      }
    }
    cq.b ☃ = cq.b.a;
    for (cq.b ☃ : cq.b.values()) {
      if (☃[☃.ordinal()] < ☃[☃.ordinal()]) {
        ☃ = ☃;
      }
    }
    return new arg.b(☃.c() == ☃ ? ☃ : ☃.a(ank.a.d(☃), ☃.b() - 1), cq.a(☃, ☃), cq.b, ☃, ☃.b(), ☃.a(), 1);
  }
  
  public static class a
  {
    private final aht a;
    private final cq.a b;
    private final cq c;
    private final cq d;
    private int e = 0;
    private cj f;
    private int g;
    private int h;
    
    public a(aht ☃, cj ☃, cq.a ☃)
    {
      this.a = ☃;
      this.b = ☃;
      if (☃ == cq.a.a)
      {
        this.d = cq.f;
        this.c = cq.e;
      }
      else
      {
        this.d = cq.c;
        this.c = cq.d;
      }
      cj ☃ = ☃;
      while ((☃.q() > ☃.q() - 21) && (☃.q() > 0) && (a(☃.o(☃.b()).t()))) {
        ☃ = ☃.b();
      }
      int ☃ = a(☃, this.d) - 1;
      if (☃ >= 0)
      {
        this.f = ☃.a(this.d, ☃);
        
        this.h = a(this.f, this.c);
        if ((this.h < 2) || (this.h > 21))
        {
          this.f = null;
          this.h = 0;
        }
      }
      if (this.f != null) {
        this.g = c();
      }
    }
    
    protected int a(cj ☃, cq ☃)
    {
      for (int ☃ = 0; ☃ < 22; ☃++)
      {
        cj ☃ = ☃.a(☃, ☃);
        if (!a(this.a.o(☃).t())) {
          break;
        }
        if (this.a.o(☃.b()).t() != aju.Z) {
          break;
        }
      }
      ajt ☃ = this.a.o(☃.a(☃, ☃)).t();
      if (☃ == aju.Z) {
        return ☃;
      }
      return 0;
    }
    
    public int a()
    {
      return this.g;
    }
    
    public int b()
    {
      return this.h;
    }
    
    protected int c()
    {
      for (this.g = 0; this.g < 21; this.g += 1) {
        for (int ☃ = 0; ☃ < this.h; ☃++)
        {
          cj ☃ = this.f.a(this.c, ☃).b(this.g);
          
          ajt ☃ = this.a.o(☃).t();
          if (!a(☃)) {
            break label181;
          }
          if (☃ == aju.aY) {
            this.e += 1;
          }
          if (☃ == 0)
          {
            ☃ = this.a.o(☃.a(this.d)).t();
            if (☃ != aju.Z) {
              break label181;
            }
          }
          else if (☃ == this.h - 1)
          {
            ☃ = this.a.o(☃.a(this.c)).t();
            if (☃ != aju.Z) {
              break label181;
            }
          }
        }
      }
      label181:
      for (int ☃ = 0; ☃ < this.h; ☃++) {
        if (this.a.o(this.f.a(this.c, ☃).b(this.g)).t() != aju.Z)
        {
          this.g = 0;
          break;
        }
      }
      if ((this.g > 21) || (this.g < 3))
      {
        this.f = null;
        this.h = 0;
        this.g = 0;
        return 0;
      }
      return this.g;
    }
    
    protected boolean a(ajt ☃)
    {
      return (☃.x == axe.a) || (☃ == aju.ab) || (☃ == aju.aY);
    }
    
    public boolean d()
    {
      return (this.f != null) && (this.h >= 2) && (this.h <= 21) && (this.g >= 3) && (this.g <= 21);
    }
    
    public void e()
    {
      for (int ☃ = 0; ☃ < this.h; ☃++)
      {
        cj ☃ = this.f.a(this.c, ☃);
        for (int ☃ = 0; ☃ < this.g; ☃++) {
          this.a.a(☃.b(☃), aju.aY.u().a(ank.a, this.b), 2);
        }
      }
    }
  }
}
