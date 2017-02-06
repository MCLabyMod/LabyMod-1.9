import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;

class bfn$d
  extends bdq
{
  private final List<rt.a> v = Lists.newArrayList();
  
  public bfn$d(bfn arg1, bcf ☃)
  {
    super(☃, ???.l, ???.m, 32, ???.m - 64, bfn.m(???).a * 4);
    
    b(false);
    for (rt.a ☃ : rt.a.values()) {
      if ((bfn.b(???).a(☃.d) > 0) || (bfn.b(???).a(☃.e) > 0)) {
        this.v.add(☃);
      }
    }
  }
  
  protected int b()
  {
    return this.v.size();
  }
  
  protected void a(int ☃, boolean ☃, int ☃, int ☃) {}
  
  protected boolean a(int ☃)
  {
    return false;
  }
  
  protected int k()
  {
    return b() * bfn.n(this.u).a * 4;
  }
  
  protected void a()
  {
    this.u.c();
  }
  
  protected void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    rt.a ☃ = (rt.a)this.v.get(☃);
    String ☃ = bwo.a("entity." + ☃.a + ".name", new Object[0]);
    int ☃ = bfn.b(this.u).a(☃.d);
    int ☃ = bfn.b(this.u).a(☃.e);
    String ☃ = bwo.a("stat.entityKills", new Object[] { Integer.valueOf(☃), ☃ });
    String ☃ = bwo.a("stat.entityKilledBy", new Object[] { ☃, Integer.valueOf(☃) });
    if (☃ == 0) {
      ☃ = bwo.a("stat.entityKills.none", new Object[] { ☃ });
    }
    if (☃ == 0) {
      ☃ = bwo.a("stat.entityKilledBy.none", new Object[] { ☃ });
    }
    this.u.c(bfn.o(this.u), ☃, ☃ + 2 - 10, ☃ + 1, 16777215);
    
    this.u.c(bfn.p(this.u), ☃, ☃ + 2, ☃ + 1 + bfn.q(this.u).a, ☃ == 0 ? 6316128 : 9474192);
    this.u.c(bfn.r(this.u), ☃, ☃ + 2, ☃ + 1 + bfn.s(this.u).a * 2, ☃ == 0 ? 6316128 : 9474192);
  }
}
