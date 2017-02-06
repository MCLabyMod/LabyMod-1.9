import java.util.List;
import java.util.Random;

public abstract class awg
{
  protected avp l;
  private cq a;
  private amr b;
  private aoe c;
  protected int m;
  
  public awg() {}
  
  protected awg(int ☃)
  {
    this.m = ☃;
  }
  
  public final dn b()
  {
    dn ☃ = new dn();
    
    ☃.a("id", awe.a(this));
    ☃.a("BB", this.l.g());
    cq ☃ = e();
    ☃.a("O", ☃ == null ? -1 : ☃.b());
    ☃.a("GD", this.m);
    
    a(☃);
    
    return ☃;
  }
  
  protected abstract void a(dn paramdn);
  
  public void a(aht ☃, dn ☃)
  {
    if (☃.e("BB")) {
      this.l = new avp(☃.n("BB"));
    }
    int ☃ = ☃.h("O");
    a(☃ == -1 ? null : cq.b(☃));
    this.m = ☃.h("GD");
    
    b(☃);
  }
  
  protected abstract void b(dn paramdn);
  
  public void a(awg ☃, List<awg> ☃, Random ☃) {}
  
  public abstract boolean a(aht paramaht, Random paramRandom, avp paramavp);
  
  public avp c()
  {
    return this.l;
  }
  
  public int d()
  {
    return this.m;
  }
  
  public static awg a(List<awg> ☃, avp ☃)
  {
    for (awg ☃ : ☃) {
      if ((☃.c() != null) && (☃.c().a(☃))) {
        return ☃;
      }
    }
    return null;
  }
  
  public cj a()
  {
    return new cj(this.l.f());
  }
  
  protected boolean a(aht ☃, avp ☃)
  {
    int ☃ = Math.max(this.l.a - 1, ☃.a);
    int ☃ = Math.max(this.l.b - 1, ☃.b);
    int ☃ = Math.max(this.l.c - 1, ☃.c);
    int ☃ = Math.min(this.l.d + 1, ☃.d);
    int ☃ = Math.min(this.l.e + 1, ☃.e);
    int ☃ = Math.min(this.l.f + 1, ☃.f);
    
    cj.a ☃ = new cj.a();
    for (int ☃ = ☃; ☃ <= ☃; ☃++) {
      for (int ☃ = ☃; ☃ <= ☃; ☃++)
      {
        if (☃.o(☃.c(☃, ☃, ☃)).a().d()) {
          return true;
        }
        if (☃.o(☃.c(☃, ☃, ☃)).a().d()) {
          return true;
        }
      }
    }
    for (int ☃ = ☃; ☃ <= ☃; ☃++) {
      for (int ☃ = ☃; ☃ <= ☃; ☃++)
      {
        if (☃.o(☃.c(☃, ☃, ☃)).a().d()) {
          return true;
        }
        if (☃.o(☃.c(☃, ☃, ☃)).a().d()) {
          return true;
        }
      }
    }
    for (int ☃ = ☃; ☃ <= ☃; ☃++) {
      for (int ☃ = ☃; ☃ <= ☃; ☃++)
      {
        if (☃.o(☃.c(☃, ☃, ☃)).a().d()) {
          return true;
        }
        if (☃.o(☃.c(☃, ☃, ☃)).a().d()) {
          return true;
        }
      }
    }
    return false;
  }
  
  protected int a(int ☃, int ☃)
  {
    cq ☃ = e();
    if (☃ == null) {
      return ☃;
    }
    switch (awg.1.a[☃.ordinal()])
    {
    case 1: 
    case 2: 
      return this.l.a + ☃;
    case 3: 
      return this.l.d - ☃;
    case 4: 
      return this.l.a + ☃;
    }
    return ☃;
  }
  
  protected int d(int ☃)
  {
    if (e() == null) {
      return ☃;
    }
    return ☃ + this.l.b;
  }
  
  protected int b(int ☃, int ☃)
  {
    cq ☃ = e();
    if (☃ == null) {
      return ☃;
    }
    switch (awg.1.a[☃.ordinal()])
    {
    case 1: 
      return this.l.f - ☃;
    case 2: 
      return this.l.c + ☃;
    case 3: 
    case 4: 
      return this.l.c + ☃;
    }
    return ☃;
  }
  
  protected void a(aht ☃, arc ☃, int ☃, int ☃, int ☃, avp ☃)
  {
    cj ☃ = new cj(a(☃, ☃), d(☃), b(☃, ☃));
    if (!☃.b(☃)) {
      return;
    }
    if (this.b != amr.a) {
      ☃ = ☃.a(this.b);
    }
    if (this.c != aoe.a) {
      ☃ = ☃.a(this.c);
    }
    ☃.a(☃, ☃, 2);
  }
  
  protected arc a(aht ☃, int ☃, int ☃, int ☃, avp ☃)
  {
    int ☃ = a(☃, ☃);
    int ☃ = d(☃);
    int ☃ = b(☃, ☃);
    
    cj ☃ = new cj(☃, ☃, ☃);
    if (!☃.b(☃)) {
      return aju.a.u();
    }
    return ☃.o(☃);
  }
  
  protected void a(aht ☃, avp ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    for (int ☃ = ☃; ☃ <= ☃; ☃++) {
      for (int ☃ = ☃; ☃ <= ☃; ☃++) {
        for (int ☃ = ☃; ☃ <= ☃; ☃++) {
          a(☃, aju.a.u(), ☃, ☃, ☃, ☃);
        }
      }
    }
  }
  
  protected void a(aht ☃, avp ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, arc ☃, arc ☃, boolean ☃)
  {
    for (int ☃ = ☃; ☃ <= ☃; ☃++) {
      for (int ☃ = ☃; ☃ <= ☃; ☃++) {
        for (int ☃ = ☃; ☃ <= ☃; ☃++) {
          if ((!☃) || (a(☃, ☃, ☃, ☃, ☃).a() != axe.a)) {
            if ((☃ == ☃) || (☃ == ☃) || (☃ == ☃) || (☃ == ☃) || (☃ == ☃) || (☃ == ☃)) {
              a(☃, ☃, ☃, ☃, ☃, ☃);
            } else {
              a(☃, ☃, ☃, ☃, ☃, ☃);
            }
          }
        }
      }
    }
  }
  
  protected void a(aht ☃, avp ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, boolean ☃, Random ☃, awg.a ☃)
  {
    for (int ☃ = ☃; ☃ <= ☃; ☃++) {
      for (int ☃ = ☃; ☃ <= ☃; ☃++) {
        for (int ☃ = ☃; ☃ <= ☃; ☃++) {
          if ((!☃) || (a(☃, ☃, ☃, ☃, ☃).a() != axe.a))
          {
            ☃.a(☃, ☃, ☃, ☃, (☃ == ☃) || (☃ == ☃) || (☃ == ☃) || (☃ == ☃) || (☃ == ☃) || (☃ == ☃));
            a(☃, ☃.a(), ☃, ☃, ☃, ☃);
          }
        }
      }
    }
  }
  
  protected void a(aht ☃, avp ☃, Random ☃, float ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, arc ☃, arc ☃, boolean ☃)
  {
    for (int ☃ = ☃; ☃ <= ☃; ☃++) {
      for (int ☃ = ☃; ☃ <= ☃; ☃++) {
        for (int ☃ = ☃; ☃ <= ☃; ☃++) {
          if (☃.nextFloat() <= ☃) {
            if ((!☃) || (a(☃, ☃, ☃, ☃, ☃).a() != axe.a)) {
              if ((☃ == ☃) || (☃ == ☃) || (☃ == ☃) || (☃ == ☃) || (☃ == ☃) || (☃ == ☃)) {
                a(☃, ☃, ☃, ☃, ☃, ☃);
              } else {
                a(☃, ☃, ☃, ☃, ☃, ☃);
              }
            }
          }
        }
      }
    }
  }
  
  protected void a(aht ☃, avp ☃, Random ☃, float ☃, int ☃, int ☃, int ☃, arc ☃)
  {
    if (☃.nextFloat() < ☃) {
      a(☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
  
  protected void a(aht ☃, avp ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, arc ☃, boolean ☃)
  {
    float ☃ = ☃ - ☃ + 1;
    float ☃ = ☃ - ☃ + 1;
    float ☃ = ☃ - ☃ + 1;
    float ☃ = ☃ + ☃ / 2.0F;
    float ☃ = ☃ + ☃ / 2.0F;
    for (int ☃ = ☃; ☃ <= ☃; ☃++)
    {
      float ☃ = (☃ - ☃) / ☃;
      for (int ☃ = ☃; ☃ <= ☃; ☃++)
      {
        float ☃ = (☃ - ☃) / (☃ * 0.5F);
        for (int ☃ = ☃; ☃ <= ☃; ☃++)
        {
          float ☃ = (☃ - ☃) / (☃ * 0.5F);
          if ((!☃) || (a(☃, ☃, ☃, ☃, ☃).a() != axe.a))
          {
            float ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
            if (☃ <= 1.05F) {
              a(☃, ☃, ☃, ☃, ☃, ☃);
            }
          }
        }
      }
    }
  }
  
  protected void b(aht ☃, int ☃, int ☃, int ☃, avp ☃)
  {
    cj ☃ = new cj(a(☃, ☃), d(☃), b(☃, ☃));
    if (!☃.b(☃)) {
      return;
    }
    while ((!☃.d(☃)) && (☃.q() < 255))
    {
      ☃.a(☃, aju.a.u(), 2);
      ☃ = ☃.a();
    }
  }
  
  protected void b(aht ☃, arc ☃, int ☃, int ☃, int ☃, avp ☃)
  {
    int ☃ = a(☃, ☃);
    int ☃ = d(☃);
    int ☃ = b(☃, ☃);
    if (!☃.b(new cj(☃, ☃, ☃))) {
      return;
    }
    while (((☃.d(new cj(☃, ☃, ☃))) || (☃.o(new cj(☃, ☃, ☃)).a().d())) && (☃ > 1))
    {
      ☃.a(new cj(☃, ☃, ☃), ☃, 2);
      ☃--;
    }
  }
  
  protected boolean a(aht ☃, avp ☃, Random ☃, int ☃, int ☃, int ☃, kk ☃)
  {
    cj ☃ = new cj(a(☃, ☃), d(☃), b(☃, ☃));
    if ((☃.b(☃)) && 
      (☃.o(☃).t() != aju.ae))
    {
      arc ☃ = aju.ae.u();
      ☃.a(☃, aju.ae.f(☃, ☃, ☃), 2);
      
      apv ☃ = ☃.r(☃);
      if ((☃ instanceof apx)) {
        ((apx)☃).a(☃, ☃.nextLong());
      }
      return true;
    }
    return false;
  }
  
  protected boolean a(aht ☃, avp ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃, List<ow> ☃, int ☃)
  {
    cj ☃ = new cj(a(☃, ☃), d(☃), b(☃, ☃));
    if ((☃.b(☃)) && 
      (☃.o(☃).t() != aju.z))
    {
      a(☃, aju.z.u().a(aku.a, ☃), ☃, ☃, ☃, ☃);
      
      apv ☃ = ☃.r(☃);
      if ((☃ instanceof aqb)) {
        ow.a(☃, ☃, (aqb)☃, ☃);
      }
      return true;
    }
    return false;
  }
  
  protected void a(aht ☃, avp ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃)
  {
    a(☃, aju.ao.u().a(akv.a, ☃), ☃, ☃, ☃, ☃);
    a(☃, aju.ao.u().a(akv.a, ☃).a(akv.e, akv.a.a), ☃, ☃ + 1, ☃, ☃);
  }
  
  public void a(int ☃, int ☃, int ☃)
  {
    this.l.a(☃, ☃, ☃);
  }
  
  public cq e()
  {
    return this.a;
  }
  
  public void a(cq ☃)
  {
    this.a = ☃;
    if (☃ == null)
    {
      this.c = aoe.a;
      this.b = amr.a;
    }
    else
    {
      switch (awg.1.a[☃.ordinal()])
      {
      case 2: 
        this.b = amr.b;
        this.c = aoe.a;
        break;
      case 3: 
        this.b = amr.b;
        this.c = aoe.b;
        break;
      case 4: 
        this.b = amr.a;
        this.c = aoe.b;
        break;
      default: 
        this.b = amr.a;
        this.c = aoe.a;
      }
    }
  }
  
  public static abstract class a
  {
    protected arc a = aju.a.u();
    
    public abstract void a(Random paramRandom, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean);
    
    public arc a()
    {
      return this.a;
    }
  }
}
