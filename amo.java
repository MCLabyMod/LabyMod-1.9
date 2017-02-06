import java.util.Random;

public abstract class amo
  extends ajt
{
  public static final arq b = arq.a("level", 0, 15);
  
  protected amo(axe ☃)
  {
    super(☃);
    w(this.A.b().a(b, Integer.valueOf(0)));
    a(true);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return j;
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    return k;
  }
  
  public boolean b(ahx ☃, cj ☃)
  {
    return this.x != axe.i;
  }
  
  public static float e(int ☃)
  {
    if (☃ >= 8) {
      ☃ = 0;
    }
    return (☃ + 1) / 9.0F;
  }
  
  protected int c(ahx ☃, cj ☃)
  {
    if (☃.o(☃).a() == this.x) {
      return ((Integer)☃.o(☃).c(b)).intValue();
    }
    return -1;
  }
  
  protected int d(ahx ☃, cj ☃)
  {
    int ☃ = c(☃, ☃);
    
    return ☃ >= 8 ? 0 : ☃;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean a(arc ☃, boolean ☃)
  {
    return (☃) && (((Integer)☃.c(b)).intValue() == 0);
  }
  
  public boolean a(ahx ☃, cj ☃, cq ☃)
  {
    axe ☃ = ☃.o(☃).a();
    if (☃ == this.x) {
      return false;
    }
    if (☃ == cq.b) {
      return true;
    }
    if (☃ == axe.w) {
      return false;
    }
    return super.a(☃, ☃, ☃);
  }
  
  public boolean a(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    if (☃.o(☃.a(☃)).a() == this.x) {
      return false;
    }
    if (☃ == cq.b) {
      return true;
    }
    return super.a(☃, ☃, ☃, ☃);
  }
  
  public boolean e(ahx ☃, cj ☃)
  {
    for (int ☃ = -1; ☃ <= 1; ☃++) {
      for (int ☃ = -1; ☃ <= 1; ☃++)
      {
        arc ☃ = ☃.o(☃.a(☃, 0, ☃));
        if ((☃.a() != this.x) && (!☃.b())) {
          return true;
        }
      }
    }
    return false;
  }
  
  public aob a(arc ☃)
  {
    return aob.b;
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return null;
  }
  
  public int a(Random ☃)
  {
    return 0;
  }
  
  protected bbj f(ahx ☃, cj ☃)
  {
    double ☃ = 0.0D;
    double ☃ = 0.0D;
    double ☃ = 0.0D;
    
    int ☃ = d(☃, ☃);
    cj.b ☃ = cj.b.s();
    for (cq ☃ : cq.c.a)
    {
      ☃.h(☃).c(☃);
      
      int ☃ = d(☃, ☃);
      if (☃ < 0)
      {
        if (!☃.o(☃).a().c())
        {
          ☃ = d(☃, ☃.b());
          if (☃ >= 0)
          {
            int ☃ = ☃ - (☃ - 8);
            ☃ += ☃.g() * ☃;
            ☃ += ☃.h() * ☃;
            ☃ += ☃.i() * ☃;
          }
        }
      }
      else if (☃ >= 0)
      {
        int ☃ = ☃ - ☃;
        ☃ += ☃.g() * ☃;
        ☃ += ☃.h() * ☃;
        ☃ += ☃.i() * ☃;
      }
    }
    bbj ☃ = new bbj(☃, ☃, ☃);
    if (((Integer)☃.o(☃).c(b)).intValue() >= 8) {
      for (cq ☃ : cq.c.a)
      {
        ☃.h(☃).c(☃);
        if ((a(☃, ☃, ☃)) || (a(☃, ☃.a(), ☃)))
        {
          ☃ = ☃.a().b(0.0D, -6.0D, 0.0D);
          break;
        }
      }
    }
    ☃.t();
    return ☃.a();
  }
  
  public bbj a(aht ☃, cj ☃, rr ☃, bbj ☃)
  {
    return ☃.e(f(☃, ☃));
  }
  
  public int a(aht ☃)
  {
    if (this.x == axe.h) {
      return 5;
    }
    if (this.x == axe.i) {
      return ☃.s.m() ? 10 : 30;
    }
    return 0;
  }
  
  public int c(arc ☃, ahx ☃, cj ☃)
  {
    int ☃ = ☃.b(☃, 0);
    int ☃ = ☃.b(☃.a(), 0);
    
    int ☃ = ☃ & 0xFF;
    int ☃ = ☃ & 0xFF;
    int ☃ = ☃ >> 16 & 0xFF;
    int ☃ = ☃ >> 16 & 0xFF;
    
    return (☃ > ☃ ? ☃ : ☃) | (☃ > ☃ ? ☃ : ☃) << 16;
  }
  
  public ahm f()
  {
    return this.x == axe.h ? ahm.d : ahm.a;
  }
  
  public void a(arc ☃, aht ☃, cj ☃, Random ☃)
  {
    double ☃ = ☃.p();
    double ☃ = ☃.q();
    double ☃ = ☃.r();
    if (this.x == axe.h)
    {
      int ☃ = ((Integer)☃.c(b)).intValue();
      if ((☃ > 0) && (☃ < 8))
      {
        if (☃.nextInt(64) == 0) {
          ☃.a(☃ + 0.5D, ☃ + 0.5D, ☃ + 0.5D, ng.gw, nh.e, ☃.nextFloat() * 0.25F + 0.75F, ☃.nextFloat() + 0.5F, false);
        }
      }
      else if (☃.nextInt(10) == 0) {
        ☃.a(cy.h, ☃ + ☃.nextFloat(), ☃ + ☃.nextFloat(), ☃ + ☃.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
      }
    }
    if ((this.x == axe.i) && 
      (☃.o(☃.a()).a() == axe.a) && (!☃.o(☃.a()).p()))
    {
      if (☃.nextInt(100) == 0)
      {
        double ☃ = ☃ + ☃.nextFloat();
        double ☃ = ☃ + ☃.c(☃, ☃).e;
        double ☃ = ☃ + ☃.nextFloat();
        ☃.a(cy.B, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
        ☃.a(☃, ☃, ☃, ng.dc, nh.e, 0.2F + ☃.nextFloat() * 0.2F, 0.9F + ☃.nextFloat() * 0.15F, false);
      }
      if (☃.nextInt(200) == 0) {
        ☃.a(☃, ☃, ☃, ng.da, nh.e, 0.2F + ☃.nextFloat() * 0.2F, 0.9F + ☃.nextFloat() * 0.15F, false);
      }
    }
    if ((☃.nextInt(10) == 0) && (☃.o(☃.b()).q()))
    {
      axe ☃ = ☃.o(☃.c(2)).a();
      if ((!☃.c()) && (!☃.d()))
      {
        double ☃ = ☃ + ☃.nextFloat();
        double ☃ = ☃ - 1.05D;
        double ☃ = ☃ + ☃.nextFloat();
        if (this.x == axe.h) {
          ☃.a(cy.s, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
        } else {
          ☃.a(cy.t, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
        }
      }
    }
  }
  
  public static float a(ahx ☃, cj ☃, axe ☃)
  {
    bbj ☃ = a(☃).f(☃, ☃);
    if ((☃.b == 0.0D) && (☃.d == 0.0D)) {
      return -1000.0F;
    }
    return (float)on.b(☃.d, ☃.b) - 1.5707964F;
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    e(☃, ☃, ☃);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    e(☃, ☃, ☃);
  }
  
  public boolean e(aht ☃, cj ☃, arc ☃)
  {
    if (this.x == axe.i)
    {
      boolean ☃ = false;
      for (cq ☃ : cq.values()) {
        if ((☃ != cq.a) && (☃.o(☃.a(☃)).a() == axe.h))
        {
          ☃ = true;
          break;
        }
      }
      if (☃)
      {
        Integer ☃ = (Integer)☃.c(b);
        if (☃.intValue() == 0)
        {
          ☃.a(☃, aju.Z.u());
          b(☃, ☃);
          return true;
        }
        if (☃.intValue() <= 4)
        {
          ☃.a(☃, aju.e.u());
          b(☃, ☃);
          return true;
        }
      }
    }
    return false;
  }
  
  protected void b(aht ☃, cj ☃)
  {
    double ☃ = ☃.p();
    double ☃ = ☃.q();
    double ☃ = ☃.r();
    
    ☃.a(null, ☃, ng.db, nh.e, 0.5F, 2.6F + (☃.r.nextFloat() - ☃.r.nextFloat()) * 0.8F);
    for (int ☃ = 0; ☃ < 8; ☃++) {
      ☃.a(cy.m, ☃ + Math.random(), ☃ + 1.2D, ☃ + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
    }
  }
  
  public arc a(int ☃)
  {
    return u().a(b, Integer.valueOf(☃));
  }
  
  public int e(arc ☃)
  {
    return ((Integer)☃.c(b)).intValue();
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { b });
  }
  
  public static akz a(axe ☃)
  {
    if (☃ == axe.h) {
      return aju.i;
    }
    if (☃ == axe.i) {
      return aju.k;
    }
    throw new IllegalArgumentException("Invalid material");
  }
  
  public static aov b(axe ☃)
  {
    if (☃ == axe.h) {
      return aju.j;
    }
    if (☃ == axe.i) {
      return aju.l;
    }
    throw new IllegalArgumentException("Invalid material");
  }
}
