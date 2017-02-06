import java.util.List;

public class abl
  extends aau
{
  private static final rw[] h = { rw.f, rw.e, rw.d, rw.c };
  public abc a = new abc(this, 2, 2);
  public qg f = new abr();
  public boolean g;
  private final zj i;
  
  public abl(zi ☃, boolean ☃, zj ☃)
  {
    this.g = ☃;
    this.i = ☃;
    a(new abs(☃.e, this.a, this.f, 0, 154, 28));
    for (int ☃ = 0; ☃ < 2; ☃++) {
      for (int ☃ = 0; ☃ < 2; ☃++) {
        a(new abt(this.a, ☃ + ☃ * 2, 98 + ☃ * 18, 18 + ☃ * 18));
      }
    }
    for (int ☃ = 0; ☃ < 4; ☃++)
    {
      final rw ☃ = h[☃];
      a(new abt(☃, 36 + (3 - ☃), 8, 8 + ☃ * 18)
      {
        public int a()
        {
          return 1;
        }
        
        public boolean a(adq ☃)
        {
          if (☃ == null) {
            return false;
          }
          rw ☃ = sb.d(☃);
          return ☃ == ☃;
        }
        
        public String c()
        {
          return abw.a[☃.b()];
        }
      });
    }
    for (int ☃ = 0; ☃ < 3; ☃++) {
      for (int ☃ = 0; ☃ < 9; ☃++) {
        a(new abt(☃, ☃ + (☃ + 1) * 9, 8 + ☃ * 18, 84 + ☃ * 18));
      }
    }
    for (int ☃ = 0; ☃ < 9; ☃++) {
      a(new abt(☃, ☃, 8 + ☃ * 18, 142));
    }
    a(new abt(☃, 40, 77, 62)
    {
      public boolean a(adq ☃)
      {
        return super.a(☃);
      }
      
      public String c()
      {
        return "minecraft:items/empty_armor_slot_shield";
      }
    });
    a(this.a);
  }
  
  public void a(qg ☃)
  {
    this.f.a(0, afv.a().a(this.a, this.i.l));
  }
  
  public void b(zj ☃)
  {
    super.b(☃);
    for (int ☃ = 0; ☃ < 4; ☃++)
    {
      adq ☃ = this.a.b(☃);
      if (☃ != null) {
        ☃.a(☃, false);
      }
    }
    this.f.a(0, null);
  }
  
  public boolean a(zj ☃)
  {
    return true;
  }
  
  public adq b(zj ☃, int ☃)
  {
    adq ☃ = null;
    abt ☃ = (abt)this.c.get(☃);
    if ((☃ != null) && (☃.e()))
    {
      adq ☃ = ☃.d();
      ☃ = ☃.k();
      
      rw ☃ = sb.d(☃);
      if (☃ == 0)
      {
        if (!a(☃, 9, 45, true)) {
          return null;
        }
        ☃.a(☃, ☃);
      }
      else if ((☃ >= 1) && (☃ < 5))
      {
        if (!a(☃, 9, 45, false)) {
          return null;
        }
      }
      else if ((☃ >= 5) && (☃ < 9))
      {
        if (!a(☃, 9, 45, false)) {
          return null;
        }
      }
      else if ((☃.a() == rw.a.b) && (!((abt)this.c.get(8 - ☃.b())).e()))
      {
        int ☃ = 8 - ☃.b();
        if (!a(☃, ☃, ☃ + 1, false)) {
          return null;
        }
      }
      else if ((☃ >= 9) && (☃ < 36))
      {
        if (!a(☃, 36, 45, false)) {
          return null;
        }
      }
      else if ((☃ >= 36) && (☃ < 45))
      {
        if (!a(☃, 9, 36, false)) {
          return null;
        }
      }
      else if (!a(☃, 9, 45, false))
      {
        return null;
      }
      if (☃.b == 0) {
        ☃.d(null);
      } else {
        ☃.f();
      }
      if (☃.b == ☃.b) {
        return null;
      }
      ☃.a(☃, ☃);
    }
    return ☃;
  }
  
  public boolean a(adq ☃, abt ☃)
  {
    return (☃.d != this.f) && (super.a(☃, ☃));
  }
}
