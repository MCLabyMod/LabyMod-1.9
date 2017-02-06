import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.util.concurrent.Futures;
import io.netty.buffer.Unpooled;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class mb
  implements ig, ky
{
  private static final Logger c = ;
  public final ek a;
  private final MinecraftServer d;
  public lr b;
  private int e;
  private int f;
  private long g;
  private long h;
  private int i;
  private int j;
  private final oh<Short> k = new oh();
  private double l;
  private double m;
  private double n;
  private double o;
  private double p;
  private double q;
  private rr r;
  private double s;
  private double t;
  private double u;
  private double v;
  private double w;
  private double x;
  private bbj y;
  private int z;
  private int A;
  private boolean B;
  private int C;
  private boolean D;
  private int E;
  private int F;
  private int G;
  
  public mb(MinecraftServer ☃, ek ☃, lr ☃)
  {
    this.d = ☃;
    this.a = ☃;
    ☃.a(this);
    this.b = ☃;
    ☃.a = this;
  }
  
  public void c()
  {
    d();
    this.b.k_();
    this.b.a(this.l, this.m, this.n, this.b.v, this.b.w);
    this.e += 1;
    this.G = this.F;
    if (this.B)
    {
      if (++this.C > 80)
      {
        c.warn(this.b.h_() + " was kicked for floating too long!");
        c("Flying is not enabled on this server");
      }
    }
    else
    {
      this.B = false;
      this.C = 0;
    }
    this.r = this.b.bw();
    if ((this.r == this.b) || (this.r.bt() != this.b))
    {
      this.r = null;
      this.D = false;
      this.E = 0;
    }
    else
    {
      this.s = this.r.p;
      this.t = this.r.q;
      this.u = this.r.r;
      this.v = this.r.p;
      this.w = this.r.q;
      this.x = this.r.r;
      if ((this.D) && (this.b.bw().bt() == this.b))
      {
        if (++this.E > 80)
        {
          c.warn(this.b.h_() + " was kicked for floating a vehicle too long!");
          c("Flying is not enabled on this server");
        }
      }
      else
      {
        this.D = false;
        this.E = 0;
      }
    }
    this.d.c.a("keepAlive");
    if (this.e - this.h > 40L)
    {
      this.h = this.e;
      this.g = e();
      this.f = ((int)this.g);
      a(new go(this.f));
    }
    this.d.c.b();
    if (this.i > 0) {
      this.i -= 1;
    }
    if (this.j > 0) {
      this.j -= 1;
    }
    if ((this.b.I() > 0L) && (this.d.aw() > 0) && (MinecraftServer.av() - this.b.I() > this.d.aw() * 1000 * 60)) {
      c("You have been idle for too long!");
    }
  }
  
  private void d()
  {
    this.l = this.b.p;
    this.m = this.b.q;
    this.n = this.b.r;
    this.o = this.b.p;
    this.p = this.b.q;
    this.q = this.b.r;
  }
  
  public ek a()
  {
    return this.a;
  }
  
  public void c(String ☃)
  {
    final fa ☃ = new fa(☃);
    this.a.a(new gj(☃), new GenericFutureListener()
    {
      public void operationComplete(Future<? super Void> ☃)
        throws Exception
      {
        mb.this.a.a(☃);
      }
    }, new GenericFutureListener[0]);
    
    this.a.k();
    Futures.getUnchecked(this.d.a(new Runnable()
    {
      public void run()
      {
        mb.this.a.l();
      }
    }));
  }
  
  public void a(iz ☃)
  {
    fh.a(☃, this, this.b.x());
    this.b.a(☃.a(), ☃.b(), ☃.c(), ☃.d());
  }
  
  private static boolean b(it ☃)
  {
    if ((Doubles.isFinite(☃.a(0.0D))) && (Doubles.isFinite(☃.b(0.0D))) && (Doubles.isFinite(☃.c(0.0D))) && (Floats.isFinite(☃.b(0.0F))) && (Floats.isFinite(☃.a(0.0F)))) {
      return false;
    }
    if ((Math.abs(☃.a(0.0D)) > 3.0E7D) || (Math.abs(☃.a(0.0D)) > 3.0E7D)) {
      return false;
    }
    return true;
  }
  
  private static boolean b(iu ☃)
  {
    if ((Doubles.isFinite(☃.a())) && (Doubles.isFinite(☃.b())) && (Doubles.isFinite(☃.c())) && (Floats.isFinite(☃.e())) && (Floats.isFinite(☃.d()))) {
      return false;
    }
    return true;
  }
  
  public void a(iu ☃)
  {
    fh.a(☃, this, this.b.x());
    if (b(☃))
    {
      c("Invalid move vehicle packet received");
      return;
    }
    rr ☃ = this.b.bw();
    if ((☃ != this.b) && (☃.bt() == this.b) && (☃ == this.r))
    {
      lp ☃ = this.b.x();
      double ☃ = ☃.p;
      double ☃ = ☃.q;
      double ☃ = ☃.r;
      
      double ☃ = ☃.a();
      double ☃ = ☃.b();
      double ☃ = ☃.c();
      
      float ☃ = ☃.d();
      float ☃ = ☃.e();
      
      double ☃ = ☃ - this.s;
      double ☃ = ☃ - this.t;
      double ☃ = ☃ - this.u;
      
      double ☃ = ☃.s * ☃.s + ☃.t * ☃.t + ☃.u * ☃.u;
      double ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
      if ((☃ - ☃ > 100.0D) && ((!this.d.R()) || (!this.d.Q().equals(☃.h_()))))
      {
        c.warn(☃.h_() + " (vehicle of " + this.b.h_() + ") moved too quickly! " + ☃ + "," + ☃ + "," + ☃);
        this.a.a(new gv(☃));
        return;
      }
      boolean ☃ = ☃.a(☃, ☃.bl().h(0.0625D)).isEmpty();
      
      ☃ = ☃ - this.v;
      ☃ = ☃ - this.w - 1.0E-6D;
      ☃ = ☃ - this.x;
      
      ☃.d(☃, ☃, ☃);
      
      double ☃ = ☃;
      
      ☃ = ☃ - ☃.p;
      ☃ = ☃ - ☃.q;
      if ((☃ > -0.5D) || (☃ < 0.5D)) {
        ☃ = 0.0D;
      }
      ☃ = ☃ - ☃.r;
      ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
      boolean ☃ = false;
      if (☃ > 0.0625D)
      {
        ☃ = true;
        c.warn(☃.h_() + " moved wrongly!");
      }
      ☃.a(☃, ☃, ☃, ☃, ☃);
      
      boolean ☃ = ☃.a(☃, ☃.bl().h(0.0625D)).isEmpty();
      if ((☃) && ((☃) || (!☃)))
      {
        ☃.a(☃, ☃, ☃, ☃, ☃);
        this.a.a(new gv(☃));
        return;
      }
      this.d.al().d(this.b);
      this.b.l(this.b.p - ☃, this.b.q - ☃, this.b.r - ☃);
      this.D = ((☃ >= -0.03125D) && (!this.d.ag()) && (!☃.d(☃.bl().g(0.0625D).a(0.0D, -0.55D, 0.0D))));
      
      this.v = ☃.p;
      this.w = ☃.q;
      this.x = ☃.r;
    }
  }
  
  public void a(ih ☃)
  {
    fh.a(☃, this, this.b.x());
    if (☃.a() == this.z)
    {
      this.b.a(this.y.b, this.y.c, this.y.d, this.b.v, this.b.w);
      if (this.b.K())
      {
        this.o = this.y.b;
        this.p = this.y.c;
        this.q = this.y.d;
        this.b.L();
      }
      this.y = null;
    }
  }
  
  public void a(it ☃)
  {
    fh.a(☃, this, this.b.x());
    if (b(☃))
    {
      c("Invalid move player packet received");
      return;
    }
    lp ☃ = this.d.a(this.b.am);
    if (this.b.h) {
      return;
    }
    if (this.e == 0) {
      d();
    }
    if (this.y != null)
    {
      if (this.e - this.A > 20)
      {
        this.A = this.e;
        a(this.y.b, this.y.c, this.y.d, this.b.v, this.b.w);
      }
      return;
    }
    this.A = this.e;
    if (this.b.aI())
    {
      this.b.a(this.b.p, this.b.q, this.b.r, ☃.a(this.b.v), ☃.b(this.b.w));
      this.d.al().d(this.b);
      return;
    }
    double ☃ = this.b.p;
    double ☃ = this.b.q;
    double ☃ = this.b.r;
    
    double ☃ = this.b.q;
    
    double ☃ = ☃.a(this.b.p);
    double ☃ = ☃.b(this.b.q);
    double ☃ = ☃.c(this.b.r);
    
    float ☃ = ☃.a(this.b.v);
    float ☃ = ☃.b(this.b.w);
    
    double ☃ = ☃ - this.l;
    double ☃ = ☃ - this.m;
    double ☃ = ☃ - this.n;
    
    double ☃ = this.b.s * this.b.s + this.b.t * this.b.t + this.b.u * this.b.u;
    double ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
    
    this.F += 1;
    int ☃ = this.F - this.G;
    if (☃ > 5)
    {
      c.debug(this.b.h_() + " is sending move packets too frequently (" + ☃ + " packets since last tick)");
      ☃ = 1;
    }
    if ((!this.b.K()) && (
      (!this.b.x().U().b("disableElytraMovementCheck")) || (!this.b.cB())))
    {
      float ☃ = this.b.cB() ? 300.0F : 100.0F;
      if ((☃ - ☃ > ☃ * ☃) && ((!this.d.R()) || (!this.d.Q().equals(this.b.h_()))))
      {
        c.warn(this.b.h_() + " moved too quickly! " + ☃ + "," + ☃ + "," + ☃);
        a(this.b.p, this.b.q, this.b.r, this.b.v, this.b.w);
        return;
      }
    }
    boolean ☃ = ☃.a(this.b, this.b.bl().h(0.0625D)).isEmpty();
    
    ☃ = ☃ - this.o;
    ☃ = ☃ - this.p;
    ☃ = ☃ - this.q;
    if ((this.b.z) && (!☃.a()) && (☃ > 0.0D)) {
      this.b.ch();
    }
    this.b.d(☃, ☃, ☃);
    this.b.z = ☃.a();
    
    double ☃ = ☃;
    
    ☃ = ☃ - this.b.p;
    ☃ = ☃ - this.b.q;
    if ((☃ > -0.5D) || (☃ < 0.5D)) {
      ☃ = 0.0D;
    }
    ☃ = ☃ - this.b.r;
    ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
    boolean ☃ = false;
    if ((!this.b.K()) && (☃ > 0.0625D) && (!this.b.cl()) && (!this.b.c.d()) && (this.b.c.b() != ahw.a.e))
    {
      ☃ = true;
      c.warn(this.b.h_() + " moved wrongly!");
    }
    this.b.a(☃, ☃, ☃, ☃, ☃);
    
    this.b.l(this.b.p - ☃, this.b.q - ☃, this.b.r - ☃);
    if ((!this.b.Q) && (!this.b.cl()))
    {
      boolean ☃ = ☃.a(this.b, this.b.bl().h(0.0625D)).isEmpty();
      if ((☃) && ((☃) || (!☃)))
      {
        a(☃, ☃, ☃, ☃, ☃);
        return;
      }
    }
    this.B = (☃ >= -0.03125D);
    this.B &= ((!this.d.ag()) && (!this.b.bJ.c));
    this.B &= ((!this.b.a(rm.y)) && (!this.b.cB()) && (!☃.d(this.b.bl().g(0.0625D).a(0.0D, -0.55D, 0.0D))));
    
    this.b.z = ☃.a();
    this.d.al().d(this.b);
    this.b.a(this.b.q - ☃, ☃.a());
    
    this.o = this.b.p;
    this.p = this.b.q;
    this.q = this.b.r;
  }
  
  public void a(double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    a(☃, ☃, ☃, ☃, ☃, Collections.emptySet());
  }
  
  public void a(double ☃, double ☃, double ☃, float ☃, float ☃, Set<ha.a> ☃)
  {
    double ☃ = ☃.contains(ha.a.a) ? this.b.p : 0.0D;
    double ☃ = ☃.contains(ha.a.b) ? this.b.q : 0.0D;
    double ☃ = ☃.contains(ha.a.c) ? this.b.r : 0.0D;
    this.y = new bbj(☃ + ☃, ☃ + ☃, ☃ + ☃);
    
    float ☃ = ☃;
    float ☃ = ☃;
    if (☃.contains(ha.a.d)) {
      ☃ += this.b.v;
    }
    if (☃.contains(ha.a.e)) {
      ☃ += this.b.w;
    }
    if (++this.z == Integer.MAX_VALUE) {
      this.z = 0;
    }
    this.A = this.e;
    this.b.a(this.y.b, this.y.c, this.y.d, ☃, ☃);
    this.b.a.a(new ha(☃, ☃, ☃, ☃, ☃, ☃, this.z));
  }
  
  public void a(ix ☃)
  {
    fh.a(☃, this, this.b.x());
    lp ☃ = this.d.a(this.b.am);
    cj ☃ = ☃.a();
    this.b.D();
    switch (mb.4.a[☃.c().ordinal()])
    {
    case 1: 
      if (!this.b.y())
      {
        adq ☃ = this.b.b(qm.b);
        this.b.a(qm.b, this.b.b(qm.a));
        this.b.a(qm.a, ☃);
      }
      return;
    case 2: 
      if (!this.b.y()) {
        this.b.a(false);
      }
      return;
    case 3: 
      if (!this.b.y()) {
        this.b.a(true);
      }
      return;
    case 4: 
      this.b.cy();
      adq ☃ = this.b.cb();
      if ((☃ != null) && (☃.b == 0)) {
        this.b.a(qm.a, null);
      }
      return;
    case 5: 
    case 6: 
    case 7: 
      double ☃ = this.b.p - (☃.p() + 0.5D);
      
      double ☃ = this.b.q - (☃.q() + 0.5D) + 1.5D;
      double ☃ = this.b.r - (☃.r() + 0.5D);
      double ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
      if (☃ > 36.0D) {
        return;
      }
      if (☃.q() >= this.d.aj()) {
        return;
      }
      break;
    default: 
      throw new IllegalArgumentException("Invalid player action");
    }
    if (☃.c() == ix.a.a)
    {
      if ((!this.d.a(☃, ☃, this.b)) && (☃.aj().a(☃))) {
        this.b.c.a(☃, ☃.b());
      } else {
        this.b.a.a(new fu(☃, ☃));
      }
    }
    else
    {
      if (☃.c() == ix.a.c) {
        this.b.c.a(☃);
      } else if (☃.c() == ix.a.b) {
        this.b.c.e();
      }
      if (☃.o(☃).a() != axe.a) {
        this.b.a.a(new fu(☃, ☃));
      }
    }
  }
  
  public void a(jg ☃)
  {
    fh.a(☃, this, this.b.x());
    
    lp ☃ = this.d.a(this.b.am);
    qm ☃ = ☃.c();
    adq ☃ = this.b.b(☃);
    cj ☃ = ☃.a();
    cq ☃ = ☃.b();
    this.b.D();
    if ((☃.q() < this.d.aj() - 1) || ((☃ != cq.b) && (☃.q() < this.d.aj())))
    {
      if ((this.y == null) && (this.b.e(☃.p() + 0.5D, ☃.q() + 0.5D, ☃.r() + 0.5D) < 64.0D) && 
        (!this.d.a(☃, ☃, this.b)) && (☃.aj().a(☃))) {
        this.b.c.a(this.b, ☃, ☃, ☃, ☃, ☃, ☃.d(), ☃.e(), ☃.f());
      }
    }
    else
    {
      fb ☃ = new fb("build.tooHigh", new Object[] { Integer.valueOf(this.d.aj()) });
      ☃.b().a(a.m);
      this.b.a.a(new fy(☃));
    }
    this.b.a.a(new fu(☃, ☃));
    this.b.a.a(new fu(☃, ☃.a(☃)));
    
    ☃ = this.b.b(☃);
    if ((☃ != null) && (☃.b == 0))
    {
      this.b.a(☃, null);
      ☃ = null;
    }
  }
  
  public void a(jh ☃)
  {
    fh.a(☃, this, this.b.x());
    
    lp ☃ = this.d.a(this.b.am);
    qm ☃ = ☃.a();
    adq ☃ = this.b.b(☃);
    this.b.D();
    if (☃ == null) {
      return;
    }
    this.b.c.a(this.b, ☃, ☃, ☃);
    
    ☃ = this.b.b(☃);
    if ((☃ != null) && (☃.b == 0))
    {
      this.b.a(☃, null);
      ☃ = null;
    }
  }
  
  public void a(jf ☃)
  {
    fh.a(☃, this, this.b.x());
    if (this.b.y())
    {
      rr ☃ = null;
      for (lp ☃ : this.d.d) {
        if (☃ != null)
        {
          ☃ = ☃.a(☃);
          if (☃ != null) {
            break;
          }
        }
      }
      if (☃ != null)
      {
        this.b.e(this.b);
        this.b.p();
        if (☃.l != this.b.l)
        {
          lp ☃ = this.b.x();
          lp ☃ = (lp)☃.l;
          
          this.b.am = ☃.am;
          a(new hf(this.b.am, ☃.ae(), ☃.T().t(), this.b.c.b()));
          this.d.al().f(this.b);
          
          ☃.f(this.b);
          this.b.F = false;
          
          this.b.b(☃.p, ☃.q, ☃.r, ☃.v, ☃.w);
          if (this.b.au())
          {
            ☃.a(this.b, false);
            ☃.a(this.b);
            ☃.a(this.b, false);
          }
          this.b.a(☃);
          
          this.d.al().a(this.b, ☃);
          
          this.b.a(☃.p, ☃.q, ☃.r);
          this.b.c.a(☃);
          this.d.al().b(this.b, ☃);
          this.d.al().g(this.b);
        }
        else
        {
          this.b.a(☃.p, ☃.q, ☃.r);
        }
      }
    }
  }
  
  public void a(ja ☃) {}
  
  public void a(iv ☃)
  {
    fh.a(☃, this, this.b.x());
    rr ☃ = this.b.by();
    if ((☃ instanceof aag)) {
      ((aag)☃).a(☃.a(), ☃.b());
    }
  }
  
  public void a(eu ☃)
  {
    c.info(this.b.h_() + " lost connection: " + ☃);
    this.d.aC();
    fb ☃ = new fb("multiplayer.player.left", new Object[] { this.b.i_() });
    ☃.b().a(a.o);
    this.d.al().a(☃);
    this.b.t();
    this.d.al().e(this.b);
    if ((this.d.R()) && (this.b.h_().equals(this.d.Q())))
    {
      c.info("Stopping singleplayer server as player logged out");
      this.d.x();
    }
  }
  
  public void a(final ff<?> ☃)
  {
    if ((☃ instanceof fy))
    {
      fy ☃ = (fy)☃;
      zj.b ☃ = this.b.C();
      if (☃ == zj.b.c) {
        return;
      }
      if ((☃ == zj.b.b) && (!☃.b())) {
        return;
      }
    }
    try
    {
      this.a.a(☃);
    }
    catch (Throwable ☃)
    {
      b ☃ = b.a(☃, "Sending packet");
      c ☃ = ☃.a("Packet being sent");
      
      ☃.a("Packet class", new Callable()
      {
        public String a()
          throws Exception
        {
          return ☃.getClass().getCanonicalName();
        }
      });
      throw new e(☃);
    }
  }
  
  public void a(jb ☃)
  {
    fh.a(☃, this, this.b.x());
    if ((☃.a() < 0) || (☃.a() >= zi.i()))
    {
      c.warn(this.b.h_() + " tried to set an invalid carried item");
      return;
    }
    this.b.br.d = ☃.a();
    this.b.D();
  }
  
  public void a(ij ☃)
  {
    fh.a(☃, this, this.b.x());
    if (this.b.C() == zj.b.c)
    {
      fb ☃ = new fb("chat.cannotSend", new Object[0]);
      ☃.b().a(a.m);
      a(new fy(☃));
      return;
    }
    this.b.D();
    
    String ☃ = ☃.a();
    ☃ = StringUtils.normalizeSpace(☃);
    for (int ☃ = 0; ☃ < ☃.length(); ☃++) {
      if (!f.a(☃.charAt(☃)))
      {
        c("Illegal characters in chat");
        return;
      }
    }
    if (☃.startsWith("/"))
    {
      d(☃);
    }
    else
    {
      eu ☃ = new fb("chat.type.text", new Object[] { this.b.i_(), ☃ });
      this.d.al().a(☃, false);
    }
    this.i += 20;
    if ((this.i > 200) && (!this.d.al().h(this.b.cK()))) {
      c("disconnect.spam");
    }
  }
  
  private void d(String ☃)
  {
    this.d.N().a(this.b, ☃);
  }
  
  public void a(je ☃)
  {
    fh.a(☃, this, this.b.x());
    this.b.D();
    this.b.a(☃.a());
  }
  
  public void a(iy ☃)
  {
    fh.a(☃, this, this.b.x());
    this.b.D();
    switch (mb.4.b[☃.b().ordinal()])
    {
    case 1: 
      this.b.d(true);
      break;
    case 2: 
      this.b.d(false);
      break;
    case 3: 
      this.b.e(true);
      break;
    case 4: 
      this.b.e(false);
      break;
    case 5: 
      this.b.a(false, true, true);
      this.y = new bbj(this.b.p, this.b.q, this.b.r);
      break;
    case 6: 
      if ((this.b.by() instanceof sj))
      {
        sj ☃ = (sj)this.b.by();
        int ☃ = ☃.c();
        if ((☃.b()) && (☃ > 0)) {
          ☃.b(☃);
        }
      }
      break;
    case 7: 
      if ((this.b.by() instanceof sj))
      {
        sj ☃ = (sj)this.b.by();
        ☃.r_();
      }
      break;
    case 8: 
      if ((this.b.by() instanceof wk)) {
        ((wk)this.b.by()).f(this.b);
      }
      break;
    case 9: 
      if ((!this.b.z) && (this.b.t < 0.0D) && (!this.b.cB()) && (!this.b.ai()))
      {
        adq ☃ = this.b.a(rw.e);
        if ((☃ != null) && (☃.b() == ads.cR) && (acx.d(☃))) {
          this.b.M();
        }
      }
      else
      {
        this.b.N();
      }
      break;
    default: 
      throw new IllegalArgumentException("Invalid client command!");
    }
  }
  
  public void a(ir ☃)
  {
    fh.a(☃, this, this.b.x());
    lp ☃ = this.d.a(this.b.am);
    rr ☃ = ☃.a(☃);
    this.b.D();
    if (☃ != null)
    {
      boolean ☃ = this.b.D(☃);
      double ☃ = 36.0D;
      if (!☃) {
        ☃ = 9.0D;
      }
      if (this.b.h(☃) < ☃) {
        if (☃.a() == ir.a.a)
        {
          qm ☃ = ☃.b();
          adq ☃ = this.b.b(☃);
          this.b.a(☃, ☃, ☃);
        }
        else if (☃.a() == ir.a.c)
        {
          qm ☃ = ☃.b();
          adq ☃ = this.b.b(☃);
          ☃.a(this.b, ☃.c(), ☃, ☃);
        }
        else if (☃.a() == ir.a.b)
        {
          if (((☃ instanceof yd)) || ((☃ instanceof rx)) || ((☃ instanceof zm)) || (☃ == this.b))
          {
            c("Attempting to attack an invalid entity");
            this.d.f("Player " + this.b.h_() + " tried to attack an invalid entity");
            return;
          }
          this.b.f(☃);
        }
      }
    }
  }
  
  public void a(ik ☃)
  {
    fh.a(☃, this, this.b.x());
    this.b.D();
    ik.a ☃ = ☃.a();
    switch (mb.4.c[☃.ordinal()])
    {
    case 1: 
      if (this.b.h)
      {
        this.b.h = false;
        this.b = this.d.al().a(this.b, 0, true);
      }
      else
      {
        if (this.b.bQ() > 0.0F) {
          return;
        }
        this.b = this.d.al().a(this.b, 0, false);
        if (this.d.p())
        {
          this.b.a(ahw.a.e);
          this.b.x().U().a("spectatorsGenerateChunks", "false");
        }
      }
      break;
    case 2: 
      this.b.E().a(this.b);
      break;
    case 3: 
      this.b.b(nk.f);
    }
  }
  
  public void a(ip ☃)
  {
    fh.a(☃, this, this.b.x());
    this.b.s();
  }
  
  public void a(io ☃)
  {
    fh.a(☃, this, this.b.x());
    this.b.D();
    if ((this.b.bt.d == ☃.a()) && (this.b.bt.c(this.b))) {
      if (this.b.y())
      {
        List<adq> ☃ = Lists.newArrayList();
        for (int ☃ = 0; ☃ < this.b.bt.c.size(); ☃++) {
          ☃.add(((abt)this.b.bt.c.get(☃)).d());
        }
        this.b.a(this.b.bt, ☃);
      }
      else
      {
        adq ☃ = this.b.bt.a(☃.b(), ☃.c(), ☃.f(), this.b);
        if (adq.b(☃.e(), ☃))
        {
          this.b.a.a(new ga(☃.a(), ☃.d(), true));
          this.b.f = true;
          this.b.bt.b();
          this.b.r();
          this.b.f = false;
        }
        else
        {
          this.k.a(this.b.bt.d, Short.valueOf(☃.d()));
          this.b.a.a(new ga(☃.a(), ☃.d(), false));
          this.b.bt.a(this.b, false);
          
          List<adq> ☃ = Lists.newArrayList();
          for (int ☃ = 0; ☃ < this.b.bt.c.size(); ☃++)
          {
            adq ☃ = ((abt)this.b.bt.c.get(☃)).d();
            adq ☃ = (☃ != null) && (☃.b > 0) ? ☃ : null;
            ☃.add(☃);
          }
          this.b.a(this.b.bt, ☃);
        }
      }
    }
  }
  
  public void a(in ☃)
  {
    fh.a(☃, this, this.b.x());
    this.b.D();
    if ((this.b.bt.d == ☃.a()) && (this.b.bt.c(this.b)) && (!this.b.y()))
    {
      this.b.bt.a(this.b, ☃.b());
      this.b.bt.b();
    }
  }
  
  public void a(jc ☃)
  {
    fh.a(☃, this, this.b.x());
    if (this.b.c.d())
    {
      boolean ☃ = ☃.a() < 0;
      adq ☃ = ☃.b();
      if ((☃ != null) && (☃.n()) && (☃.o().b("BlockEntityTag", 10)))
      {
        dn ☃ = ☃.o().o("BlockEntityTag");
        if ((☃.e("x")) && (☃.e("y")) && (☃.e("z")))
        {
          cj ☃ = new cj(☃.h("x"), ☃.h("y"), ☃.h("z"));
          apv ☃ = this.b.l.r(☃);
          if (☃ != null)
          {
            dn ☃ = new dn();
            ☃.b(☃);
            ☃.q("x");
            ☃.q("y");
            ☃.q("z");
            ☃.a("BlockEntityTag", ☃);
          }
        }
      }
      boolean ☃ = (☃.a() >= 1) && (☃.a() <= 45);
      boolean ☃ = (☃ == null) || (☃.b() != null);
      boolean ☃ = (☃ == null) || ((☃.i() >= 0) && (☃.b <= 64) && (☃.b > 0));
      if ((☃) && (☃) && (☃))
      {
        if (☃ == null) {
          this.b.bs.a(☃.a(), null);
        } else {
          this.b.bs.a(☃.a(), ☃);
        }
        this.b.bs.a(this.b, true);
      }
      else if ((☃) && (☃) && (☃) && 
        (this.j < 200))
      {
        this.j += 20;
        
        yd ☃ = this.b.a(☃, true);
        if (☃ != null) {
          ☃.j();
        }
      }
    }
  }
  
  public void a(im ☃)
  {
    fh.a(☃, this, this.b.x());
    Short ☃ = (Short)this.k.a(this.b.bt.d);
    if ((☃ != null) && (☃.b() == ☃.shortValue()) && (this.b.bt.d == ☃.a()) && (!this.b.bt.c(this.b)) && (!this.b.y())) {
      this.b.bt.a(this.b, true);
    }
  }
  
  public void a(jd ☃)
  {
    fh.a(☃, this, this.b.x());
    this.b.D();
    lp ☃ = this.d.a(this.b.am);
    cj ☃ = ☃.a();
    if (☃.e(☃))
    {
      arc ☃ = ☃.o(☃);
      apv ☃ = ☃.r(☃);
      if (!(☃ instanceof aqn)) {
        return;
      }
      aqn ☃ = (aqn)☃;
      if ((!☃.b()) || (☃.c() != this.b))
      {
        this.d.f("Player " + this.b.h_() + " just tried to change non-editable sign");
        return;
      }
      String[] ☃ = ☃.b();
      for (int ☃ = 0; ☃ < ☃.length; ☃++) {
        ☃.a[☃] = new fa(a.a(☃[☃]));
      }
      ☃.v_();
      ☃.a(☃, ☃, ☃, 3);
    }
  }
  
  public void a(is ☃)
  {
    if (☃.a() == this.f)
    {
      int ☃ = (int)(e() - this.g);
      this.b.g = ((this.b.g * 3 + ☃) / 4);
    }
  }
  
  private long e()
  {
    return System.nanoTime() / 1000000L;
  }
  
  public void a(iw ☃)
  {
    fh.a(☃, this, this.b.x());
    this.b.bJ.b = ((☃.b()) && (this.b.bJ.c));
  }
  
  public void a(ii ☃)
  {
    fh.a(☃, this, this.b.x());
    List<String> ☃ = Lists.newArrayList();
    for (String ☃ : this.d.a(this.b, ☃.a(), ☃.b(), ☃.c())) {
      ☃.add(☃);
    }
    this.b.a.a(new fx((String[])☃.toArray(new String[☃.size()])));
  }
  
  public void a(il ☃)
  {
    fh.a(☃, this, this.b.x());
    this.b.a(☃);
  }
  
  public void a(iq ☃)
  {
    fh.a(☃, this, this.b.x());
    String ☃ = ☃.a();
    if ("MC|BEdit".equals(☃))
    {
      em ☃ = new em(Unpooled.wrappedBuffer(☃.b()));
      try
      {
        adq ☃ = ☃.k();
        if (☃ == null) {
          return;
        }
        if (!afc.b(☃.o())) {
          throw new IOException("Invalid book tag!");
        }
        adq ☃ = this.b.cb();
        if (☃ == null) {
          return;
        }
        if ((☃.b() == ads.bW) && (☃.b() == ☃.b())) {
          ☃.a("pages", ☃.o().c("pages", 8));
        }
      }
      catch (Exception ☃)
      {
        c.error("Couldn't handle book info", ☃);
      }
      finally
      {
        ☃.release();
      }
    }
    else if ("MC|BSign".equals(☃))
    {
      em ☃ = new em(Unpooled.wrappedBuffer(☃.b()));
      try
      {
        adq ☃ = ☃.k();
        if (☃ == null) {
          return;
        }
        if (!afd.b(☃.o())) {
          throw new IOException("Invalid book tag!");
        }
        adq ☃ = this.b.cb();
        if (☃ == null) {
          return;
        }
        if ((☃.b() == ads.bW) && (☃.b() == ads.bW))
        {
          ☃.a("author", new ea(this.b.h_()));
          ☃.a("title", new ea(☃.o().l("title")));
          du ☃ = ☃.o().c("pages", 8);
          for (int ☃ = 0; ☃ < ☃.c(); ☃++)
          {
            String ☃ = ☃.g(☃);
            eu ☃ = new fa(☃);
            ☃ = eu.a.a(☃);
            ☃.a(☃, new ea(☃));
          }
          ☃.a("pages", ☃);
          ☃.a(ads.bX);
        }
      }
      catch (Exception ☃)
      {
        c.error("Couldn't sign book", ☃);
      }
      finally
      {
        ☃.release();
      }
    }
    else if ("MC|TrSel".equals(☃))
    {
      try
      {
        int ☃ = ☃.b().readInt();
        aau ☃ = this.b.bt;
        if ((☃ instanceof abo)) {
          ((abo)☃).d(☃);
        }
      }
      catch (Exception ☃)
      {
        c.error("Couldn't select trade", ☃);
      }
    }
    else if ("MC|AdvCmd".equals(☃))
    {
      if (!this.d.ah())
      {
        this.b.a(new fb("advMode.notEnabled", new Object[0]));
        return;
      }
      if ((!this.b.a(2, "")) || (!this.b.bJ.d))
      {
        this.b.a(new fb("advMode.notAllowed", new Object[0]));
        return;
      }
      em ☃ = ☃.b();
      try
      {
        int ☃ = ☃.readByte();
        ahj ☃ = null;
        if (☃ == 0)
        {
          apv ☃ = this.b.l.r(new cj(☃.readInt(), ☃.readInt(), ☃.readInt()));
          if ((☃ instanceof apy)) {
            ☃ = ((apy)☃).b();
          }
        }
        else if (☃ == 1)
        {
          rr ☃ = this.b.l.a(☃.readInt());
          if ((☃ instanceof aaj)) {
            ☃ = ((aaj)☃).j();
          }
        }
        String ☃ = ☃.c(☃.readableBytes());
        boolean ☃ = ☃.readBoolean();
        if (☃ != null)
        {
          ☃.a(☃);
          ☃.a(☃);
          if (!☃) {
            ☃.b(null);
          }
          ☃.i();
          this.b.a(new fb("advMode.setCommand.success", new Object[] { ☃ }));
        }
      }
      catch (Exception ☃)
      {
        c.error("Couldn't set command block", ☃);
      }
      finally
      {
        ☃.release();
      }
    }
    else if ("MC|AutoCmd".equals(☃))
    {
      if (!this.d.ah())
      {
        this.b.a(new fb("advMode.notEnabled", new Object[0]));
        return;
      }
      if ((!this.b.a(2, "")) || (!this.b.bJ.d))
      {
        this.b.a(new fb("advMode.notAllowed", new Object[0]));
        return;
      }
      em ☃ = ☃.b();
      try
      {
        ahj ☃ = null;
        apy ☃ = null;
        cj ☃ = new cj(☃.readInt(), ☃.readInt(), ☃.readInt());
        apv ☃ = this.b.l.r(☃);
        if ((☃ instanceof apy))
        {
          ☃ = (apy)☃;
          ☃ = ☃.b();
        }
        String ☃ = ☃.c(☃.readableBytes());
        boolean ☃ = ☃.readBoolean();
        apy.a ☃ = apy.a.valueOf(☃.c(16));
        boolean ☃ = ☃.readBoolean();
        boolean ☃ = ☃.readBoolean();
        if (☃ != null)
        {
          cq ☃ = (cq)this.b.l.o(☃).c(akk.a);
          arc ☃;
          switch (mb.4.d[☃.ordinal()])
          {
          case 1: 
            ☃ = aju.dd.u();
            this.b.l.a(☃, ☃.a(akk.a, ☃).a(akk.b, Boolean.valueOf(☃)), 2);
            break;
          case 2: 
            ☃ = aju.dc.u();
            this.b.l.a(☃, ☃.a(akk.a, ☃).a(akk.b, Boolean.valueOf(☃)), 2);
            break;
          case 3: 
            ☃ = aju.bX.u();
            this.b.l.a(☃, ☃.a(akk.a, ☃).a(akk.b, Boolean.valueOf(☃)), 2);
          }
          ☃.z();
          this.b.l.a(☃, ☃);
          
          ☃.a(☃);
          ☃.a(☃);
          if (!☃) {
            ☃.b(null);
          }
          ☃.b(☃);
          ☃.i();
          if (!os.b(☃)) {
            this.b.a(new fb("advMode.setCommand.success", new Object[] { ☃ }));
          }
        }
      }
      catch (Exception ☃)
      {
        c.error("Couldn't set command block", ☃);
      }
      finally
      {
        ☃.release();
      }
    }
    else if ("MC|Beacon".equals(☃))
    {
      if ((this.b.bt instanceof aax)) {
        try
        {
          em ☃ = ☃.b();
          int ☃ = ☃.readInt();
          int ☃ = ☃.readInt();
          
          aax ☃ = (aax)this.b.bt;
          abt ☃ = ☃.a(0);
          if (☃.e())
          {
            ☃.a(1);
            qg ☃ = ☃.e();
            ☃.b(1, ☃);
            ☃.b(2, ☃);
            ☃.v_();
          }
        }
        catch (Exception ☃)
        {
          c.error("Couldn't set beacon", ☃);
        }
      }
    }
    else if ("MC|ItemName".equals(☃))
    {
      if ((this.b.bt instanceof aaw))
      {
        aaw ☃ = (aaw)this.b.bt;
        if ((☃.b() == null) || (☃.b().readableBytes() < 1))
        {
          ☃.a("");
        }
        else
        {
          String ☃ = f.a(☃.b().c(32767));
          if (☃.length() <= 30) {
            ☃.a(☃);
          }
        }
      }
    }
    else if ("MC|Struct".equals(☃))
    {
      em ☃ = ☃.b();
      try
      {
        if ((this.b.a(4, "")) && (this.b.bJ.d))
        {
          cj ☃ = new cj(☃.readInt(), ☃.readInt(), ☃.readInt());
          arc ☃ = this.b.l.o(☃);
          apv ☃ = this.b.l.r(☃);
          if ((☃ instanceof aqp))
          {
            aqp ☃ = (aqp)☃;
            
            int ☃ = ☃.readByte();
            String ☃ = ☃.c(32);
            ☃.a(aqp.a.valueOf(☃));
            ☃.a(☃.c(64));
            ☃.b(new cj(☃.readInt(), ☃.readInt(), ☃.readInt()));
            ☃.c(new cj(☃.readInt(), ☃.readInt(), ☃.readInt()));
            String ☃ = ☃.c(32);
            ☃.a(amr.valueOf(☃));
            String ☃ = ☃.c(32);
            ☃.a(aoe.valueOf(☃));
            ☃.b(☃.c(128));
            ☃.a(☃.readBoolean());
            if (☃ == 2)
            {
              if (☃.m()) {
                this.b.b(new fa("Structure saved"));
              } else {
                this.b.b(new fa("Structure NOT saved"));
              }
            }
            else if (☃ == 3)
            {
              if (☃.n()) {
                this.b.b(new fa("Structure loaded"));
              } else {
                this.b.b(new fa("Structure prepared"));
              }
            }
            else if ((☃ == 4) && 
              (☃.l())) {
              this.b.b(new fa("Size detected"));
            }
            ☃.v_();
            this.b.l.a(☃, ☃, ☃, 3);
          }
        }
      }
      catch (Exception ☃)
      {
        c.error("Couldn't set structure block", ☃);
      }
      finally
      {
        ☃.release();
      }
    }
    else if ("MC|PickItem".equals(☃))
    {
      em ☃ = ☃.b();
      try
      {
        int ☃ = ☃.g();
        this.b.br.d(☃);
        this.b.a.a(new gf(-2, this.b.br.d, this.b.br.a(this.b.br.d)));
        this.b.a.a(new gf(-2, ☃, this.b.br.a(☃)));
        this.b.a.a(new hj(this.b.br.d));
      }
      catch (Exception ☃)
      {
        c.error("Couldn't pick item", ☃);
      }
      finally
      {
        ☃.release();
      }
    }
  }
}
