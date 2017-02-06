import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ata
{
  private static final Logger a = ;
  private static final Predicate<lr> b = Predicates.and(rv.a, rv.a(0.0D, 128.0D, 0.0D, 192.0D));
  private final ln c = (ln)new ln(new fb("entity.EnderDragon.name", new Object[0]), qe.a.a, qe.b.a).b(true).c(true);
  private final lp d;
  private final List<Integer> e = Lists.newArrayList();
  private final arg f;
  private int g = 0;
  private int h = 0;
  private int i = 0;
  private int j = 0;
  private boolean k = false;
  private boolean l = false;
  private UUID m = null;
  private boolean n = false;
  private cj o = null;
  private asz p;
  private int q = 0;
  private List<wt> r;
  
  public ata(lp ☃, dn ☃)
  {
    this.d = ☃;
    if (☃.b("DragonKilled", 1))
    {
      if (☃.b("DragonUUID")) {
        this.m = ☃.a("DragonUUID");
      }
      this.k = ☃.p("DragonKilled");
      this.l = ☃.p("PreviouslyKilled");
      if (☃.p("IsRespawning")) {
        this.p = asz.a;
      }
      if (☃.b("ExitPortalLocation", 10)) {
        this.o = dy.c(☃.o("ExitPortalLocation"));
      }
    }
    else
    {
      this.n = true;
      this.k = true;
      this.l = true;
    }
    if (☃.b("Gateways", 9))
    {
      du ☃ = ☃.c("Gateways", 3);
      for (int ☃ = 0; ☃ < ☃.c(); ☃++) {
        this.e.add(Integer.valueOf(☃.c(☃)));
      }
    }
    else
    {
      this.e.addAll(ContiguousSet.create(Range.closedOpen(Integer.valueOf(0), Integer.valueOf(20)), DiscreteDomain.integers()));
      Collections.shuffle(this.e, new Random(☃.O()));
    }
    this.f = arh.a().a(new String[] { "       ", "       ", "       ", "   #   ", "       ", "       ", "       " }).a(new String[] { "       ", "       ", "       ", "   #   ", "       ", "       ", "       " }).a(new String[] { "       ", "       ", "       ", "   #   ", "       ", "       ", "       " }).a(new String[] { "  ###  ", " #   # ", "#     #", "#  #  #", "#     #", " #   # ", "  ###  " }).a(new String[] { "       ", "  ###  ", " ##### ", " ##### ", " ##### ", "  ###  ", "       " }).a('#', arf.a(arj.a(aju.h))).b();
  }
  
  public dn a()
  {
    if (this.n) {
      return new dn();
    }
    dn ☃ = new dn();
    if (this.m != null) {
      ☃.a("DragonUUID", this.m);
    }
    ☃.a("DragonKilled", this.k);
    ☃.a("PreviouslyKilled", this.l);
    if (this.o != null) {
      ☃.a("ExitPortalLocation", dy.a(this.o));
    }
    du ☃ = new du();
    for (Iterator ☃ = this.e.iterator(); ☃.hasNext();)
    {
      int ☃ = ((Integer)☃.next()).intValue();
      ☃.a(new dt(☃));
    }
    ☃.a("Gateways", ☃);
    
    return ☃;
  }
  
  public void b()
  {
    this.c.d(!this.k);
    if (++this.j >= 20)
    {
      j();
      this.j = 0;
    }
    if (!this.c.c().isEmpty())
    {
      if (this.n)
      {
        a.info("Scanning for legacy world dragon fight...");
        i();
        this.n = false;
        if (g())
        {
          a.info("Found that the dragon has been killed in this world already.");
          this.l = true;
        }
        else
        {
          a.info("Found that the dragon has not yet been killed in this world.");
          this.l = false;
        }
        List<wu> ☃ = this.d.a(wu.class, rv.a);
        if (!☃.isEmpty())
        {
          wu ☃ = (wu)☃.get(0);
          this.m = ☃.bc();
          a.info("Found that there's a dragon still alive (" + ☃ + ")");
          this.k = false;
        }
        else
        {
          this.k = true;
        }
        if ((!this.l) && (this.k)) {
          this.k = false;
        }
      }
      if (this.p != null)
      {
        if (this.r == null)
        {
          this.p = null;
          e();
        }
        this.p.a(this.d, this, this.r, this.q++, this.o);
      }
      if (!this.k)
      {
        if ((this.m == null) || (++this.g >= 1200))
        {
          i();
          List<wu> ☃ = this.d.a(wu.class, rv.a);
          if (!☃.isEmpty())
          {
            a.debug("Haven't seen our dragon, but found another one to use.");
            this.m = ((wu)☃.get(0)).bc();
          }
          else
          {
            a.debug("Haven't seen the dragon, respawning it");
            m();
            a(false);
          }
          this.g = 0;
        }
        if (++this.i >= 100)
        {
          k();
          this.i = 0;
        }
      }
    }
  }
  
  protected void a(asz ☃)
  {
    if (this.p == null) {
      throw new IllegalStateException("Dragon respawn isn't in progress, can't skip ahead in the animation.");
    }
    this.q = 0;
    if (☃ == asz.e)
    {
      this.p = null;
      this.k = false;
      m();
    }
    else
    {
      this.p = ☃;
    }
  }
  
  private boolean g()
  {
    for (int ☃ = -8; ☃ <= 8; ☃++) {
      for (int ☃ = -8; ☃ <= 8; ☃++)
      {
        ase ☃ = this.d.a(☃, ☃);
        for (apv ☃ : ☃.s().values()) {
          if ((☃ instanceof aqr)) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  private arg.b h()
  {
    for (int ☃ = -8; ☃ <= 8; ☃++) {
      for (int ☃ = -8; ☃ <= 8; ☃++)
      {
        ase ☃ = this.d.a(☃, ☃);
        for (apv ☃ : ☃.s().values()) {
          if ((☃ instanceof aqr))
          {
            arg.b ☃ = this.f.a(this.d, ☃.v());
            if (☃ != null)
            {
              cj ☃ = ☃.a(3, 3, 4).d();
              if ((this.o == null) && (☃.p() == 0) && (☃.r() == 0)) {
                this.o = ☃;
              }
              return ☃;
            }
          }
        }
      }
    }
    int ☃ = this.d.l(auc.a).q();
    for (int ☃ = ☃; ☃ >= 0; ☃--)
    {
      arg.b ☃ = this.f.a(this.d, new cj(auc.a.p(), ☃, auc.a.r()));
      if (☃ != null)
      {
        if (this.o == null) {
          this.o = ☃.a(3, 3, 4).d();
        }
        return ☃;
      }
    }
    return null;
  }
  
  private void i()
  {
    for (int ☃ = -8; ☃ <= 8; ☃++) {
      for (int ☃ = -8; ☃ <= 8; ☃++) {
        this.d.a(☃, ☃);
      }
    }
  }
  
  private void j()
  {
    Set<lr> ☃ = Sets.newHashSet();
    for (lr ☃ : this.d.b(lr.class, b))
    {
      this.c.a(☃);
      ☃.add(☃);
    }
    Set<lr> ☃ = Sets.newHashSet(this.c.c());
    ☃.removeAll(☃);
    for (lr ☃ : ☃) {
      this.c.b(☃);
    }
  }
  
  private void k()
  {
    this.i = 0;
    this.h = 0;
    for (avb.a ☃ : ajf.a(this.d)) {
      this.h += this.d.a(wt.class, ☃.f()).size();
    }
    a.debug("Found {} end crystals still alive", new Object[] { Integer.valueOf(this.h) });
  }
  
  public void a(wu ☃)
  {
    if (☃.bc().equals(this.m))
    {
      this.c.a(0.0F);
      this.c.d(false);
      a(true);
      l();
      if (!this.l) {
        this.d.a(this.d.l(auc.a), aju.bI.u());
      }
      this.l = true;
      this.k = true;
    }
  }
  
  private void l()
  {
    if (this.e.isEmpty()) {
      return;
    }
    int ☃ = ((Integer)this.e.remove(this.e.size() - 1)).intValue();
    int ☃ = (int)(96.0D * Math.cos(2.0D * (-3.141592653589793D + 0.15707963267948966D * ☃)));
    int ☃ = (int)(96.0D * Math.sin(2.0D * (-3.141592653589793D + 0.15707963267948966D * ☃)));
    a(new cj(☃, 75, ☃));
  }
  
  private void a(cj ☃)
  {
    this.d.b(3000, ☃, 0);
    new aua().b(this.d, new Random(), ☃);
  }
  
  private void a(boolean ☃)
  {
    auc ☃ = new auc(☃);
    if (this.o == null)
    {
      this.o = this.d.q(auc.a).b();
      while ((this.d.o(this.o).t() == aju.h) && (this.o.q() > this.d.K())) {
        this.o = this.o.b();
      }
    }
    ☃.b(this.d, new Random(), this.o);
  }
  
  private void m()
  {
    this.d.f(new cj(0, 128, 0));
    wu ☃ = new wu(this.d);
    ☃.cT().a(xk.a);
    ☃.b(0.0D, 128.0D, 0.0D, this.d.r.nextFloat() * 360.0F, 0.0F);
    this.d.a(☃);
    this.m = ☃.bc();
  }
  
  public void b(wu ☃)
  {
    if (☃.bc().equals(this.m))
    {
      this.c.a(☃.bQ() / ☃.bW());
      this.g = 0;
    }
  }
  
  public int c()
  {
    return this.h;
  }
  
  public void a(wt ☃, rc ☃)
  {
    if ((this.p != null) && (this.r.contains(☃)))
    {
      a.debug("Aborting respawn sequence");
      this.p = null;
      this.q = 0;
      f();
      a(true);
    }
    else
    {
      k();
      rr ☃ = this.d.a(this.m);
      if ((☃ instanceof wu)) {
        ((wu)☃).a(☃, new cj(☃), ☃);
      }
    }
  }
  
  public boolean d()
  {
    return this.l;
  }
  
  public void e()
  {
    if ((this.k) && (this.p == null))
    {
      cj ☃ = this.o;
      if (☃ == null)
      {
        a.debug("Tried to respawn, but need to find the portal first.");
        arg.b ☃ = h();
        if (☃ == null)
        {
          a.debug("Couldn't find a portal, so we made one.");
          a(true);
          ☃ = this.o;
        }
        else
        {
          a.debug("Found the exit portal & temporarily using it.");
          ☃ = ☃.a(3, 3, 3).d();
        }
      }
      List<wt> ☃ = Lists.newArrayList();
      cj ☃ = ☃.b(1);
      for (cq ☃ : cq.c.a)
      {
        List<wt> ☃ = this.d.a(wt.class, new bbh(☃.a(☃, 2)));
        if (!☃.isEmpty()) {
          ☃.addAll(☃);
        } else {
          return;
        }
      }
      a.debug("Found all crystals, respawning dragon.");
      a(☃);
    }
  }
  
  private void a(List<wt> ☃)
  {
    if ((this.k) && (this.p == null))
    {
      arg.b ☃ = h();
      while (☃ != null)
      {
        for (int ☃ = 0; ☃ < this.f.c(); ☃++) {
          for (int ☃ = 0; ☃ < this.f.b(); ☃++) {
            for (int ☃ = 0; ☃ < this.f.a(); ☃++)
            {
              arf ☃ = ☃.a(☃, ☃, ☃);
              if ((☃.a().t() == aju.h) || (☃.a().t() == aju.bF)) {
                this.d.a(☃.d(), aju.bH.u());
              }
            }
          }
        }
        ☃ = h();
      }
      this.p = asz.a;
      this.q = 0;
      a(false);
      this.r = ☃;
    }
  }
  
  public void f()
  {
    for (avb.a ☃ : ajf.a(this.d))
    {
      List<wt> ☃ = this.d.a(wt.class, ☃.f());
      for (wt ☃ : ☃)
      {
        ☃.h(false);
        ☃.a(null);
      }
    }
  }
}
