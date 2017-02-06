import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Random;
import java.util.Set;

public class aad
  extends zm
{
  private static final ke<Integer> f = kh.a(aad.class, kg.b);
  private afe g = afh.a;
  private final Set<rl> h = Sets.newHashSet();
  
  public aad(aht ☃)
  {
    super(☃);
  }
  
  public aad(aht ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃);
  }
  
  public aad(aht ☃, sa ☃)
  {
    super(☃, ☃);
  }
  
  public void a(adq ☃)
  {
    if (☃.b() == ads.i)
    {
      this.g = afg.c(☃.o());
      Collection<rl> ☃ = afg.b(☃);
      if (!☃.isEmpty()) {
        for (rl ☃ : ☃) {
          this.h.add(new rl(☃));
        }
      }
      this.Z.b(f, Integer.valueOf(afg.a(afg.a(this.g, ☃))));
    }
    else if (☃.b() == ads.g)
    {
      this.g = afh.a;
      this.h.clear();
      this.Z.b(f, Integer.valueOf(0));
    }
  }
  
  public void a(rl ☃)
  {
    this.h.add(☃);
    R().b(f, Integer.valueOf(afg.a(afg.a(this.g, this.h))));
  }
  
  protected void i()
  {
    super.i();
    this.Z.a(f, Integer.valueOf(0));
  }
  
  public void m()
  {
    super.m();
    if (this.l.E)
    {
      if (this.a)
      {
        if (this.b % 5 == 0) {
          b(1);
        }
      }
      else {
        b(2);
      }
    }
    else if ((this.a) && (this.b != 0) && 
      (!this.h.isEmpty()) && (this.b >= 600))
    {
      this.l.a(this, (byte)0);
      this.g = afh.a;
      this.h.clear();
      this.Z.b(f, Integer.valueOf(0));
    }
  }
  
  private void b(int ☃)
  {
    int ☃ = n();
    if ((☃ == 0) || (☃ <= 0)) {
      return;
    }
    double ☃ = (☃ >> 16 & 0xFF) / 255.0D;
    double ☃ = (☃ >> 8 & 0xFF) / 255.0D;
    double ☃ = (☃ >> 0 & 0xFF) / 255.0D;
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      this.l.a(cy.p, this.p + (this.S.nextDouble() - 0.5D) * this.G, this.q + this.S.nextDouble() * this.H, this.r + (this.S.nextDouble() - 0.5D) * this.G, ☃, ☃, ☃, new int[0]);
    }
  }
  
  public int n()
  {
    return ((Integer)this.Z.a(f)).intValue();
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    if ((this.g != afh.a) && (this.g != null)) {
      ☃.a("Potion", ((kk)afe.a.b(this.g)).toString());
    }
    if (!this.h.isEmpty())
    {
      du ☃ = new du();
      for (rl ☃ : this.h) {
        ☃.a(☃.a(new dn()));
      }
      ☃.a("CustomPotionEffects", ☃);
    }
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    if (☃.b("Potion", 8)) {
      this.g = afg.c(☃);
    }
    for (rl ☃ : afg.b(☃)) {
      a(☃);
    }
    if ((this.g != afh.a) || (!this.h.isEmpty())) {
      this.Z.b(f, Integer.valueOf(afg.a(afg.a(this.g, this.h))));
    }
  }
  
  protected void a(sa ☃)
  {
    super.a(☃);
    for (rl ☃ : this.g.a()) {
      ☃.c(new rl(☃.a(), ☃.b() / 8, ☃.c(), ☃.d(), ☃.e()));
    }
    if (!this.h.isEmpty()) {
      for (rl ☃ : this.h) {
        ☃.c(☃);
      }
    }
  }
  
  protected adq j()
  {
    if ((this.h.isEmpty()) && (this.g == afh.a)) {
      return new adq(ads.g);
    }
    adq ☃ = new adq(ads.i);
    afg.a(☃, this.g);
    afg.a(☃, this.h);
    return ☃;
  }
  
  public void a(byte ☃)
  {
    if (☃ == 0)
    {
      int ☃ = n();
      if (☃ > 0)
      {
        double ☃ = (☃ >> 16 & 0xFF) / 255.0D;
        double ☃ = (☃ >> 8 & 0xFF) / 255.0D;
        double ☃ = (☃ >> 0 & 0xFF) / 255.0D;
        for (int ☃ = 0; ☃ < 20; ☃++) {
          this.l.a(cy.p, this.p + (this.S.nextDouble() - 0.5D) * this.G, this.q + this.S.nextDouble() * this.H, this.r + (this.S.nextDouble() - 0.5D) * this.G, ☃, ☃, ☃, new int[0]);
        }
      }
    }
    else
    {
      super.a(☃);
    }
  }
}
