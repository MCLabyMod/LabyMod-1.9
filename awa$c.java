import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class awa$c
  extends awa.d
{
  private boolean e;
  private boolean f;
  private boolean g;
  private boolean h;
  private static final List<ow> i = Lists.newArrayList(new ow[] { new ow(ads.g, 0, 2, 7, 30) });
  
  public awa$c() {}
  
  public awa$c(Random ☃, int ☃, int ☃)
  {
    super(☃, ☃, 64, ☃, 12, 10, 15);
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    ☃.a("placedMainChest", this.e);
    ☃.a("placedHiddenChest", this.f);
    ☃.a("placedTrap1", this.g);
    ☃.a("placedTrap2", this.h);
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    this.e = ☃.p("placedMainChest");
    this.f = ☃.p("placedHiddenChest");
    this.g = ☃.p("placedTrap1");
    this.h = ☃.p("placedTrap2");
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (!a(☃, ☃, 0)) {
      return false;
    }
    a(☃, ☃, 0, -4, 0, this.a - 1, 0, this.c - 1, false, ☃, j);
    
    a(☃, ☃, 2, 1, 2, 9, 2, 2, false, ☃, j);
    a(☃, ☃, 2, 1, 12, 9, 2, 12, false, ☃, j);
    a(☃, ☃, 2, 1, 3, 2, 2, 11, false, ☃, j);
    a(☃, ☃, 9, 1, 3, 9, 2, 11, false, ☃, j);
    
    a(☃, ☃, 1, 3, 1, 10, 6, 1, false, ☃, j);
    a(☃, ☃, 1, 3, 13, 10, 6, 13, false, ☃, j);
    a(☃, ☃, 1, 3, 2, 1, 6, 12, false, ☃, j);
    a(☃, ☃, 10, 3, 2, 10, 6, 12, false, ☃, j);
    
    a(☃, ☃, 2, 3, 2, 9, 3, 12, false, ☃, j);
    a(☃, ☃, 2, 6, 2, 9, 6, 12, false, ☃, j);
    a(☃, ☃, 3, 7, 3, 8, 7, 11, false, ☃, j);
    a(☃, ☃, 4, 8, 4, 7, 8, 10, false, ☃, j);
    
    a(☃, ☃, 3, 1, 3, 8, 2, 11);
    a(☃, ☃, 4, 3, 6, 7, 3, 9);
    a(☃, ☃, 2, 4, 2, 9, 5, 12);
    a(☃, ☃, 4, 6, 5, 7, 6, 9);
    a(☃, ☃, 5, 7, 6, 6, 7, 8);
    
    a(☃, ☃, 5, 1, 2, 6, 2, 2);
    a(☃, ☃, 5, 2, 12, 6, 2, 12);
    a(☃, ☃, 5, 5, 1, 6, 5, 1);
    a(☃, ☃, 5, 5, 13, 6, 5, 13);
    a(☃, aju.a.u(), 1, 5, 5, ☃);
    a(☃, aju.a.u(), 10, 5, 5, ☃);
    a(☃, aju.a.u(), 1, 5, 9, ☃);
    a(☃, aju.a.u(), 10, 5, 9, ☃);
    for (int ☃ = 0; ☃ <= 14; ☃ += 14)
    {
      a(☃, ☃, 2, 4, ☃, 2, 5, ☃, false, ☃, j);
      a(☃, ☃, 4, 4, ☃, 4, 5, ☃, false, ☃, j);
      a(☃, ☃, 7, 4, ☃, 7, 5, ☃, false, ☃, j);
      a(☃, ☃, 9, 4, ☃, 9, 5, ☃, false, ☃, j);
    }
    a(☃, ☃, 5, 6, 0, 6, 6, 0, false, ☃, j);
    for (int ☃ = 0; ☃ <= 11; ☃ += 11)
    {
      for (int ☃ = 2; ☃ <= 12; ☃ += 2) {
        a(☃, ☃, ☃, 4, ☃, ☃, 5, ☃, false, ☃, j);
      }
      a(☃, ☃, ☃, 6, 5, ☃, 6, 5, false, ☃, j);
      a(☃, ☃, ☃, 6, 9, ☃, 6, 9, false, ☃, j);
    }
    a(☃, ☃, 2, 7, 2, 2, 9, 2, false, ☃, j);
    a(☃, ☃, 9, 7, 2, 9, 9, 2, false, ☃, j);
    a(☃, ☃, 2, 7, 12, 2, 9, 12, false, ☃, j);
    a(☃, ☃, 9, 7, 12, 9, 9, 12, false, ☃, j);
    a(☃, ☃, 4, 9, 4, 4, 9, 4, false, ☃, j);
    a(☃, ☃, 7, 9, 4, 7, 9, 4, false, ☃, j);
    a(☃, ☃, 4, 9, 10, 4, 9, 10, false, ☃, j);
    a(☃, ☃, 7, 9, 10, 7, 9, 10, false, ☃, j);
    a(☃, ☃, 5, 9, 7, 6, 9, 7, false, ☃, j);
    
    arc ☃ = aju.aw.u().a(aot.a, cq.f);
    arc ☃ = aju.aw.u().a(aot.a, cq.e);
    arc ☃ = aju.aw.u().a(aot.a, cq.d);
    arc ☃ = aju.aw.u().a(aot.a, cq.c);
    
    a(☃, ☃, 5, 9, 6, ☃);
    a(☃, ☃, 6, 9, 6, ☃);
    a(☃, ☃, 5, 9, 8, ☃);
    a(☃, ☃, 6, 9, 8, ☃);
    
    a(☃, ☃, 4, 0, 0, ☃);
    a(☃, ☃, 5, 0, 0, ☃);
    a(☃, ☃, 6, 0, 0, ☃);
    a(☃, ☃, 7, 0, 0, ☃);
    
    a(☃, ☃, 4, 1, 8, ☃);
    a(☃, ☃, 4, 2, 9, ☃);
    a(☃, ☃, 4, 3, 10, ☃);
    a(☃, ☃, 7, 1, 8, ☃);
    a(☃, ☃, 7, 2, 9, ☃);
    a(☃, ☃, 7, 3, 10, ☃);
    a(☃, ☃, 4, 1, 9, 4, 1, 9, false, ☃, j);
    a(☃, ☃, 7, 1, 9, 7, 1, 9, false, ☃, j);
    a(☃, ☃, 4, 1, 10, 7, 2, 10, false, ☃, j);
    
    a(☃, ☃, 5, 4, 5, 6, 4, 5, false, ☃, j);
    a(☃, ☃, 4, 4, 5, ☃);
    a(☃, ☃, 7, 4, 5, ☃);
    for (int ☃ = 0; ☃ < 4; ☃++)
    {
      a(☃, ☃, 5, 0 - ☃, 6 + ☃, ☃);
      a(☃, ☃, 6, 0 - ☃, 6 + ☃, ☃);
      a(☃, ☃, 5, 0 - ☃, 7 + ☃, 6, 0 - ☃, 9 + ☃);
    }
    a(☃, ☃, 1, -3, 12, 10, -1, 13);
    a(☃, ☃, 1, -3, 1, 3, -1, 13);
    a(☃, ☃, 1, -3, 1, 9, -1, 5);
    for (int ☃ = 1; ☃ <= 13; ☃ += 2) {
      a(☃, ☃, 1, -3, ☃, 1, -2, ☃, false, ☃, j);
    }
    for (int ☃ = 2; ☃ <= 12; ☃ += 2) {
      a(☃, ☃, 1, -1, ☃, 3, -1, ☃, false, ☃, j);
    }
    a(☃, ☃, 2, -2, 1, 5, -2, 1, false, ☃, j);
    a(☃, ☃, 7, -2, 1, 9, -2, 1, false, ☃, j);
    a(☃, ☃, 6, -3, 1, 6, -3, 1, false, ☃, j);
    a(☃, ☃, 6, -1, 1, 6, -1, 1, false, ☃, j);
    
    a(☃, aju.bR.u().a(api.a, cq.f).a(api.c, Boolean.valueOf(true)), 1, -3, 8, ☃);
    a(☃, aju.bR.u().a(api.a, cq.e).a(api.c, Boolean.valueOf(true)), 4, -3, 8, ☃);
    a(☃, aju.bS.u().a(aph.b, Boolean.valueOf(true)), 2, -3, 8, ☃);
    a(☃, aju.bS.u().a(aph.b, Boolean.valueOf(true)), 3, -3, 8, ☃);
    a(☃, aju.af.u(), 5, -3, 7, ☃);
    a(☃, aju.af.u(), 5, -3, 6, ☃);
    a(☃, aju.af.u(), 5, -3, 5, ☃);
    a(☃, aju.af.u(), 5, -3, 4, ☃);
    a(☃, aju.af.u(), 5, -3, 3, ☃);
    a(☃, aju.af.u(), 5, -3, 2, ☃);
    a(☃, aju.af.u(), 5, -3, 1, ☃);
    a(☃, aju.af.u(), 4, -3, 1, ☃);
    a(☃, aju.Y.u(), 3, -3, 1, ☃);
    if (!this.g) {
      this.g = a(☃, ☃, ☃, 3, -2, 1, cq.c, i, 2);
    }
    a(☃, aju.bn.u().a(apj.d, Boolean.valueOf(true)), 3, -2, 2, ☃);
    
    a(☃, aju.bR.u().a(api.a, cq.c).a(api.c, Boolean.valueOf(true)), 7, -3, 1, ☃);
    a(☃, aju.bR.u().a(api.a, cq.d).a(api.c, Boolean.valueOf(true)), 7, -3, 5, ☃);
    a(☃, aju.bS.u().a(aph.b, Boolean.valueOf(true)), 7, -3, 2, ☃);
    a(☃, aju.bS.u().a(aph.b, Boolean.valueOf(true)), 7, -3, 3, ☃);
    a(☃, aju.bS.u().a(aph.b, Boolean.valueOf(true)), 7, -3, 4, ☃);
    a(☃, aju.af.u(), 8, -3, 6, ☃);
    a(☃, aju.af.u(), 9, -3, 6, ☃);
    a(☃, aju.af.u(), 9, -3, 5, ☃);
    a(☃, aju.Y.u(), 9, -3, 4, ☃);
    a(☃, aju.af.u(), 9, -2, 4, ☃);
    if (!this.h) {
      this.h = a(☃, ☃, ☃, 9, -2, 3, cq.e, i, 2);
    }
    a(☃, aju.bn.u().a(apj.c, Boolean.valueOf(true)), 8, -1, 3, ☃);
    a(☃, aju.bn.u().a(apj.c, Boolean.valueOf(true)), 8, -2, 3, ☃);
    if (!this.e) {
      this.e = a(☃, ☃, ☃, 8, -3, 3, azt.l);
    }
    a(☃, aju.Y.u(), 9, -3, 2, ☃);
    a(☃, aju.Y.u(), 8, -3, 1, ☃);
    a(☃, aju.Y.u(), 4, -3, 5, ☃);
    a(☃, aju.Y.u(), 5, -2, 5, ☃);
    a(☃, aju.Y.u(), 5, -1, 5, ☃);
    a(☃, aju.Y.u(), 6, -3, 5, ☃);
    a(☃, aju.Y.u(), 7, -2, 5, ☃);
    a(☃, aju.Y.u(), 7, -1, 5, ☃);
    a(☃, aju.Y.u(), 8, -3, 5, ☃);
    a(☃, ☃, 9, -1, 1, 9, -1, 5, false, ☃, j);
    
    a(☃, ☃, 8, -3, 8, 10, -1, 10);
    a(☃, aju.bf.a(aoy.e), 8, -2, 11, ☃);
    a(☃, aju.bf.a(aoy.e), 9, -2, 11, ☃);
    a(☃, aju.bf.a(aoy.e), 10, -2, 11, ☃);
    arc ☃ = aju.ay.u().a(amn.a, amn.a.e);
    a(☃, ☃, 8, -2, 12, ☃);
    a(☃, ☃, 9, -2, 12, ☃);
    a(☃, ☃, 10, -2, 12, ☃);
    a(☃, ☃, 8, -3, 8, 8, -3, 10, false, ☃, j);
    a(☃, ☃, 10, -3, 8, 10, -3, 10, false, ☃, j);
    a(☃, aju.Y.u(), 10, -2, 9, ☃);
    a(☃, aju.af.u(), 8, -2, 9, ☃);
    a(☃, aju.af.u(), 8, -2, 10, ☃);
    a(☃, aju.af.u(), 10, -1, 9, ☃);
    a(☃, aju.F.u().a(aqu.H, cq.b), 9, -2, 8, ☃);
    a(☃, aju.F.u().a(aqu.H, cq.e), 10, -2, 8, ☃);
    a(☃, aju.F.u().a(aqu.H, cq.e), 10, -1, 8, ☃);
    a(☃, aju.bb.u().a(aoc.D, cq.c), 10, -2, 10, ☃);
    if (!this.f) {
      this.f = a(☃, ☃, ☃, 9, -3, 10, azt.l);
    }
    return true;
  }
  
  static class a
    extends awg.a
  {
    public void a(Random ☃, int ☃, int ☃, int ☃, boolean ☃)
    {
      if (☃.nextFloat() < 0.4F) {
        this.a = aju.e.u();
      } else {
        this.a = aju.Y.u();
      }
    }
  }
  
  private static awa.c.a j = new awa.c.a(null);
}
