import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public abstract class ahk
{
  private int a = 20;
  private final List<aid> b = Lists.newArrayList();
  private aid c = new aid();
  private double d;
  private double e;
  private int f = 200;
  private int g = 800;
  private int h = 4;
  private rr i;
  private int j = 6;
  private int k = 16;
  private int l = 4;
  
  private String g()
  {
    return this.c.b().l("id");
  }
  
  public void a(String ☃)
  {
    this.c.b().a("id", ☃);
  }
  
  private boolean h()
  {
    cj ☃ = b();
    return a().a(☃.p() + 0.5D, ☃.q() + 0.5D, ☃.r() + 0.5D, this.k);
  }
  
  public void c()
  {
    if (!h())
    {
      this.e = this.d;
      return;
    }
    cj ☃ = b();
    if (a().E)
    {
      double ☃ = ☃.p() + a().r.nextFloat();
      double ☃ = ☃.q() + a().r.nextFloat();
      double ☃ = ☃.r() + a().r.nextFloat();
      a().a(cy.l, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
      a().a(cy.A, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
      if (this.a > 0) {
        this.a -= 1;
      }
      this.e = this.d;
      this.d = ((this.d + 1000.0F / (this.a + 200.0F)) % 360.0D);
    }
    else
    {
      if (this.a == -1) {
        i();
      }
      if (this.a > 0)
      {
        this.a -= 1;
        return;
      }
      boolean ☃ = false;
      for (int ☃ = 0; ☃ < this.h; ☃++)
      {
        dn ☃ = this.c.b();
        du ☃ = ☃.c("Pos", 6);
        
        aht ☃ = a();
        int ☃ = ☃.c();
        double ☃ = ☃ >= 1 ? ☃.e(0) : ☃.p() + (☃.r.nextDouble() - ☃.r.nextDouble()) * this.l + 0.5D;
        double ☃ = ☃ >= 2 ? ☃.e(1) : ☃.q() + ☃.r.nextInt(3) - 1;
        double ☃ = ☃ >= 3 ? ☃.e(2) : ☃.r() + (☃.r.nextDouble() - ☃.r.nextDouble()) * this.l + 0.5D;
        
        rr ☃ = ass.a(☃, ☃, ☃, ☃, ☃, false);
        if (☃ == null) {
          return;
        }
        int ☃ = ☃.a(☃.getClass(), new bbh(☃.p(), ☃.q(), ☃.r(), ☃.p() + 1, ☃.q() + 1, ☃.r() + 1).g(this.l)).size();
        if (☃ >= this.j)
        {
          i();
          return;
        }
        sb ☃ = (☃ instanceof sb) ? (sb)☃ : null;
        
        ☃.b(☃.p, ☃.q, ☃.r, ☃.r.nextFloat() * 360.0F, 0.0F);
        if ((☃ == null) || ((☃.cF()) && (☃.cG())))
        {
          if ((this.c.b().d() == 1) && (this.c.b().b("id", 8)) && ((☃ instanceof sb))) {
            ((sb)☃).a(☃.D(new cj(☃)), null);
          }
          ass.a(☃, ☃);
          ☃.b(2004, ☃, 0);
          if (☃ != null) {
            ☃.E();
          }
          ☃ = true;
        }
      }
      if (☃) {
        i();
      }
    }
  }
  
  private void i()
  {
    if (this.g <= this.f) {
      this.a = this.f;
    } else {
      this.a = (this.f + a().r.nextInt(this.g - this.f));
    }
    if (!this.b.isEmpty()) {
      a((aid)ov.a(a().r, this.b));
    }
    a(1);
  }
  
  public void a(dn ☃)
  {
    this.a = ☃.g("Delay");
    
    this.b.clear();
    if (☃.b("SpawnPotentials", 9))
    {
      du ☃ = ☃.c("SpawnPotentials", 10);
      for (int ☃ = 0; ☃ < ☃.c(); ☃++) {
        this.b.add(new aid(☃.b(☃)));
      }
    }
    dn ☃ = ☃.o("SpawnData");
    if (!☃.b("id", 8)) {
      ☃.a("id", "Pig");
    }
    a(new aid(1, ☃));
    if (☃.b("MinSpawnDelay", 99))
    {
      this.f = ☃.g("MinSpawnDelay");
      this.g = ☃.g("MaxSpawnDelay");
      this.h = ☃.g("SpawnCount");
    }
    if (☃.b("MaxNearbyEntities", 99))
    {
      this.j = ☃.g("MaxNearbyEntities");
      this.k = ☃.g("RequiredPlayerRange");
    }
    if (☃.b("SpawnRange", 99)) {
      this.l = ☃.g("SpawnRange");
    }
    if (a() != null) {
      this.i = null;
    }
  }
  
  public void b(dn ☃)
  {
    String ☃ = g();
    if (os.b(☃)) {
      return;
    }
    ☃.a("Delay", (short)this.a);
    ☃.a("MinSpawnDelay", (short)this.f);
    ☃.a("MaxSpawnDelay", (short)this.g);
    ☃.a("SpawnCount", (short)this.h);
    ☃.a("MaxNearbyEntities", (short)this.j);
    ☃.a("RequiredPlayerRange", (short)this.k);
    ☃.a("SpawnRange", (short)this.l);
    ☃.a("SpawnData", this.c.b().b());
    
    du ☃ = new du();
    if (!this.b.isEmpty()) {
      for (aid ☃ : this.b) {
        ☃.a(☃.a());
      }
    } else {
      ☃.a(this.c.a());
    }
    ☃.a("SpawnPotentials", ☃);
  }
  
  public rr d()
  {
    if (this.i == null)
    {
      this.i = ass.a(this.c.b(), a(), false);
      if ((this.c.b().d() == 1) && (this.c.b().b("id", 8)) && ((this.i instanceof sb))) {
        ((sb)this.i).a(a().D(new cj(this.i)), null);
      }
    }
    return this.i;
  }
  
  public boolean b(int ☃)
  {
    if ((☃ == 1) && (a().E))
    {
      this.a = this.f;
      return true;
    }
    return false;
  }
  
  public void a(aid ☃)
  {
    this.c = ☃;
  }
  
  public abstract void a(int paramInt);
  
  public abstract aht a();
  
  public abstract cj b();
  
  public double e()
  {
    return this.d;
  }
  
  public double f()
  {
    return this.e;
  }
}
