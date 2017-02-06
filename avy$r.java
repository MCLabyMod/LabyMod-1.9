public abstract class avy$r
  extends awg
{
  protected static final arc a = aju.cI.a(anp.b);
  protected static final arc b = aju.cI.a(anp.c);
  protected static final arc c = aju.cI.a(anp.d);
  protected static final arc d = b;
  protected static final arc e = aju.cJ.u();
  protected static final arc f = aju.j.u();
  protected static final int g = b(2, 0, 0);
  protected static final int h = b(2, 2, 0);
  protected static final int i = b(0, 1, 0);
  protected static final int j = b(4, 1, 0);
  protected avy.v k;
  
  protected static final int b(int ☃, int ☃, int ☃)
  {
    return ☃ * 25 + ☃ * 5 + ☃;
  }
  
  public avy$r()
  {
    super(0);
  }
  
  public avy$r(int ☃)
  {
    super(☃);
  }
  
  public avy$r(cq ☃, avp ☃)
  {
    super(1);
    a(☃);
    this.l = ☃;
  }
  
  protected avy$r(int ☃, cq ☃, avy.v ☃, int ☃, int ☃, int ☃)
  {
    super(☃);
    a(☃);
    this.k = ☃;
    
    int ☃ = ☃.a;
    int ☃ = ☃ % 5;
    int ☃ = ☃ / 5 % 5;
    int ☃ = ☃ / 25;
    if ((☃ == cq.c) || (☃ == cq.d)) {
      this.l = new avp(0, 0, 0, ☃ * 8 - 1, ☃ * 4 - 1, ☃ * 8 - 1);
    } else {
      this.l = new avp(0, 0, 0, ☃ * 8 - 1, ☃ * 4 - 1, ☃ * 8 - 1);
    }
    switch (avy.1.a[☃.ordinal()])
    {
    case 1: 
      this.l.a(☃ * 8, ☃ * 4, -(☃ + ☃) * 8 + 1);
      break;
    case 2: 
      this.l.a(☃ * 8, ☃ * 4, ☃ * 8);
      break;
    case 3: 
      this.l.a(-(☃ + ☃) * 8 + 1, ☃ * 4, ☃ * 8);
      break;
    default: 
      this.l.a(☃ * 8, ☃ * 4, ☃ * 8);
    }
  }
  
  protected void a(dn ☃) {}
  
  protected void b(dn ☃) {}
  
  protected void a(aht ☃, avp ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    for (int ☃ = ☃; ☃ <= ☃; ☃++) {
      for (int ☃ = ☃; ☃ <= ☃; ☃++) {
        for (int ☃ = ☃; ☃ <= ☃; ☃++) {
          if ((!☃) || (a(☃, ☃, ☃, ☃, ☃).a() != axe.a)) {
            if (d(☃) >= ☃.K()) {
              a(☃, aju.a.u(), ☃, ☃, ☃, ☃);
            } else {
              a(☃, f, ☃, ☃, ☃, ☃);
            }
          }
        }
      }
    }
  }
  
  protected void a(aht ☃, avp ☃, int ☃, int ☃, boolean ☃)
  {
    if (☃)
    {
      a(☃, ☃, ☃ + 0, 0, ☃ + 0, ☃ + 2, 0, ☃ + 8 - 1, a, a, false);
      a(☃, ☃, ☃ + 5, 0, ☃ + 0, ☃ + 8 - 1, 0, ☃ + 8 - 1, a, a, false);
      a(☃, ☃, ☃ + 3, 0, ☃ + 0, ☃ + 4, 0, ☃ + 2, a, a, false);
      a(☃, ☃, ☃ + 3, 0, ☃ + 5, ☃ + 4, 0, ☃ + 8 - 1, a, a, false);
      
      a(☃, ☃, ☃ + 3, 0, ☃ + 2, ☃ + 4, 0, ☃ + 2, b, b, false);
      a(☃, ☃, ☃ + 3, 0, ☃ + 5, ☃ + 4, 0, ☃ + 5, b, b, false);
      a(☃, ☃, ☃ + 2, 0, ☃ + 3, ☃ + 2, 0, ☃ + 4, b, b, false);
      a(☃, ☃, ☃ + 5, 0, ☃ + 3, ☃ + 5, 0, ☃ + 4, b, b, false);
    }
    else
    {
      a(☃, ☃, ☃ + 0, 0, ☃ + 0, ☃ + 8 - 1, 0, ☃ + 8 - 1, a, a, false);
    }
  }
  
  protected void a(aht ☃, avp ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, arc ☃)
  {
    for (int ☃ = ☃; ☃ <= ☃; ☃++) {
      for (int ☃ = ☃; ☃ <= ☃; ☃++) {
        for (int ☃ = ☃; ☃ <= ☃; ☃++) {
          if (a(☃, ☃, ☃, ☃, ☃) == f) {
            a(☃, ☃, ☃, ☃, ☃, ☃);
          }
        }
      }
    }
  }
  
  protected boolean a(avp ☃, int ☃, int ☃, int ☃, int ☃)
  {
    int ☃ = a(☃, ☃);
    int ☃ = b(☃, ☃);
    int ☃ = a(☃, ☃);
    int ☃ = b(☃, ☃);
    return ☃.a(Math.min(☃, ☃), Math.min(☃, ☃), Math.max(☃, ☃), Math.max(☃, ☃));
  }
  
  protected boolean a(aht ☃, avp ☃, int ☃, int ☃, int ☃)
  {
    int ☃ = a(☃, ☃);
    int ☃ = d(☃);
    int ☃ = b(☃, ☃);
    if (☃.b(new cj(☃, ☃, ☃)))
    {
      yo ☃ = new yo(☃);
      ☃.a(true);
      ☃.b(☃.bW());
      ☃.b(☃ + 0.5D, ☃, ☃ + 0.5D, 0.0F, 0.0F);
      ☃.a(☃.D(new cj(☃)), null);
      ☃.a(☃);
      return true;
    }
    return false;
  }
}
