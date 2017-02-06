import com.google.common.collect.Maps;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.mojang.authlib.GameProfile;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.utils.Allowed;
import de.labystudio.utils.DualHand;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.realms.DisconnectedRealmsScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bks
  implements fi
{
  private static final Logger b = ;
  private final ek c;
  private final GameProfile d;
  private final bfb e;
  private bcf f;
  private bku g;
  private boolean h;
  private final Map<UUID, bkv> i = Maps.newHashMap();
  public int a = 20;
  private boolean j = false;
  private final Random k = new Random();
  
  public bks(bcf mcIn, bfb p_i46300_2_, ek networkManagerIn, GameProfile profileIn)
  {
    this.f = mcIn;
    this.e = p_i46300_2_;
    this.c = networkManagerIn;
    this.d = profileIn;
  }
  
  public void b()
  {
    this.g = null;
  }
  
  public void a(gs packetIn)
  {
    fh.a(packetIn, this, this.f);
    this.f.c = new bkt(this.f, this);
    this.g = new bku(this, new ahw(0L, packetIn.c(), false, packetIn.b(), packetIn.g()), packetIn.d(), packetIn.e(), this.f.B);
    this.f.u.an = packetIn.e();
    this.f.a(this.g);
    this.f.h.am = packetIn.d();
    this.f.a(new bfa(this));
    this.f.h.f(packetIn.a());
    this.a = packetIn.f();
    this.f.h.m(packetIn.h());
    this.f.c.a(packetIn.c());
    this.f.u.c();
    this.c.a(new iq("MC|Brand", new em(Unpooled.buffer()).a(ClientBrandRetriever.getClientModName())));
  }
  
  public void a(fj packetIn)
  {
    fh.a(packetIn, this, this.f);
    double d0 = packetIn.c();
    double d1 = packetIn.d();
    double d2 = packetIn.e();
    rr entity = null;
    if (packetIn.k() == 10)
    {
      entity = aah.a(this.g, d0, d1, d2, aah.a.a(packetIn.l()));
    }
    else if (packetIn.k() == 90)
    {
      rr entity1 = this.g.a(packetIn.l());
      if ((entity1 instanceof zj)) {
        entity = new xw(this.g, d0, d1, d2, (zj)entity1);
      }
      packetIn.d(0);
    }
    else if (packetIn.k() == 60)
    {
      entity = new aad(this.g, d0, d1, d2);
    }
    else if (packetIn.k() == 91)
    {
      entity = new zx(this.g, d0, d1, d2);
    }
    else if (packetIn.k() == 61)
    {
      entity = new zw(this.g, d0, d1, d2);
    }
    else if (packetIn.k() == 71)
    {
      entity = new xs(this.g, new cj(d0, d1, d2), cq.b(packetIn.l()));
      packetIn.d(0);
    }
    else if (packetIn.k() == 77)
    {
      entity = new xt(this.g, new cj(on.c(d0), on.c(d1), on.c(d2)));
      packetIn.d(0);
    }
    else if (packetIn.k() == 65)
    {
      entity = new aaa(this.g, d0, d1, d2);
    }
    else if (packetIn.k() == 72)
    {
      entity = new zo(this.g, d0, d1, d2);
    }
    else if (packetIn.k() == 76)
    {
      entity = new zq(this.g, d0, d1, d2, (adq)null);
    }
    else if (packetIn.k() == 63)
    {
      entity = new zr(this.g, d0, d1, d2, packetIn.f() / 8000.0D, packetIn.g() / 8000.0D, packetIn.h() / 8000.0D);
      packetIn.d(0);
    }
    else if (packetIn.k() == 93)
    {
      entity = new zn(this.g, d0, d1, d2, packetIn.f() / 8000.0D, packetIn.g() / 8000.0D, packetIn.h() / 8000.0D);
      packetIn.d(0);
    }
    else if (packetIn.k() == 64)
    {
      entity = new zv(this.g, d0, d1, d2, packetIn.f() / 8000.0D, packetIn.g() / 8000.0D, packetIn.h() / 8000.0D);
      packetIn.d(0);
    }
    else if (packetIn.k() == 66)
    {
      entity = new aae(this.g, d0, d1, d2, packetIn.f() / 8000.0D, packetIn.g() / 8000.0D, packetIn.h() / 8000.0D);
      packetIn.d(0);
    }
    else if (packetIn.k() == 67)
    {
      entity = new zu(this.g, d0, d1, d2, packetIn.f() / 8000.0D, packetIn.g() / 8000.0D, packetIn.h() / 8000.0D);
      packetIn.d(0);
    }
    else if (packetIn.k() == 62)
    {
      entity = new zz(this.g, d0, d1, d2);
    }
    else if (packetIn.k() == 73)
    {
      entity = new aac(this.g, d0, d1, d2, (adq)null);
      packetIn.d(0);
    }
    else if (packetIn.k() == 75)
    {
      entity = new aab(this.g, d0, d1, d2);
      packetIn.d(0);
    }
    else if (packetIn.k() == 1)
    {
      entity = new aag(this.g, d0, d1, d2);
    }
    else if (packetIn.k() == 50)
    {
      entity = new ye(this.g, d0, d1, d2, (sa)null);
    }
    else if (packetIn.k() == 78)
    {
      entity = new xq(this.g, d0, d1, d2);
    }
    else if (packetIn.k() == 51)
    {
      entity = new wt(this.g, d0, d1, d2);
    }
    else if (packetIn.k() == 2)
    {
      entity = new yd(this.g, d0, d1, d2);
    }
    else if (packetIn.k() == 70)
    {
      entity = new yc(this.g, d0, d1, d2, ajt.c(packetIn.l() & 0xFFFF));
      packetIn.d(0);
    }
    else if (packetIn.k() == 3)
    {
      entity = new rp(this.g, d0, d1, d2);
    }
    if (entity != null)
    {
      lm.a(entity, d0, d1, d2);
      entity.w = (packetIn.i() * 360 / 256.0F);
      entity.v = (packetIn.j() * 360 / 256.0F);
      rr[] aentity = entity.aR();
      if (aentity != null)
      {
        int i = packetIn.a() - entity.O();
        for (int j = 0; j < aentity.length; j++) {
          aentity[j].f(aentity[j].O() + i);
        }
      }
      entity.f(packetIn.a());
      entity.a(packetIn.b());
      this.g.a(packetIn.a(), entity);
      if (packetIn.l() > 0)
      {
        if ((packetIn.k() == 60) || (packetIn.k() == 91))
        {
          rr entity2 = this.g.a(packetIn.l() - 1);
          if (((entity2 instanceof sa)) && ((entity instanceof zm))) {
            ((zm)entity).e = entity2;
          }
        }
        entity.i(packetIn.f() / 8000.0D, packetIn.g() / 8000.0D, packetIn.h() / 8000.0D);
      }
    }
  }
  
  public void a(fk packetIn)
  {
    fh.a(packetIn, this, this.f);
    double d0 = packetIn.b();
    double d1 = packetIn.c();
    double d2 = packetIn.d();
    rr entity = new rx(this.g, d0, d1, d2, packetIn.e());
    lm.a(entity, d0, d1, d2);
    entity.v = 0.0F;
    entity.w = 0.0F;
    entity.f(packetIn.a());
    this.g.a(packetIn.a(), entity);
  }
  
  public void a(fl packetIn)
  {
    fh.a(packetIn, this, this.f);
    double d0 = packetIn.b();
    double d1 = packetIn.c();
    double d2 = packetIn.d();
    rr entity = null;
    if (packetIn.e() == 1) {
      entity = new ya(this.g, d0, d1, d2, false);
    }
    if (entity != null)
    {
      lm.a(entity, d0, d1, d2);
      entity.v = 0.0F;
      entity.w = 0.0F;
      entity.f(packetIn.a());
      this.g.d(entity);
    }
  }
  
  public void a(fn packetIn)
  {
    fh.a(packetIn, this, this.f);
    xu entitypainting = new xu(this.g, packetIn.c(), packetIn.d(), packetIn.e());
    entitypainting.a(packetIn.b());
    this.g.a(packetIn.a(), entitypainting);
  }
  
  public void a(hn packetIn)
  {
    fh.a(packetIn, this, this.f);
    rr entity = this.g.a(packetIn.a());
    if (entity != null) {
      entity.i(packetIn.b() / 8000.0D, packetIn.c() / 8000.0D, packetIn.d() / 8000.0D);
    }
  }
  
  public void a(hl packetIn)
  {
    fh.a(packetIn, this, this.f);
    rr entity = this.g.a(packetIn.b());
    if ((entity != null) && (packetIn.a() != null)) {
      entity.R().a(packetIn.a());
    }
  }
  
  public void a(fo packetIn)
  {
    fh.a(packetIn, this, this.f);
    double d0 = packetIn.d();
    double d1 = packetIn.e();
    double d2 = packetIn.f();
    float f = packetIn.g() * 360 / 256.0F;
    float f1 = packetIn.h() * 360 / 256.0F;
    bmu entityotherplayermp = new bmu(this.f.f, a(packetIn.c()).a());
    entityotherplayermp.m = (entityotherplayermp.M = d0);
    entityotherplayermp.n = (entityotherplayermp.N = d1);
    entityotherplayermp.o = (entityotherplayermp.O = d2);
    lm.a(entityotherplayermp, d0, d1, d2);
    entityotherplayermp.a(d0, d1, d2, f, f1);
    this.g.a(packetIn.b(), entityotherplayermp);
    List<kh.a<?>> list = packetIn.a();
    if (list != null) {
      entityotherplayermp.R().a(list);
    }
  }
  
  public void a(ic packetIn)
  {
    fh.a(packetIn, this, this.f);
    rr entity = this.g.a(packetIn.a());
    if (entity != null)
    {
      double d0 = packetIn.b();
      double d1 = packetIn.c();
      double d2 = packetIn.d();
      lm.a(entity, d0, d1, d2);
      if (!entity.bx())
      {
        float f = packetIn.e() * 360 / 256.0F;
        float f1 = packetIn.f() * 360 / 256.0F;
        if ((Math.abs(entity.p - d0) < 0.03125D) && (Math.abs(entity.q - d1) < 0.015625D) && (Math.abs(entity.r - d2) < 0.03125D)) {
          entity.a(entity.p, entity.q, entity.r, f, f1, 0, true);
        } else {
          entity.a(d0, d1, d2, f, f1, 3, true);
        }
        entity.z = packetIn.g();
      }
    }
  }
  
  public void a(hj packetIn)
  {
    fh.a(packetIn, this, this.f);
    if (zi.e(packetIn.a())) {
      this.f.h.br.d = packetIn.a();
    }
  }
  
  public void a(gu packetIn)
  {
    fh.a(packetIn, this, this.f);
    rr entity = packetIn.a(this.g);
    if (entity != null)
    {
      entity.ae += packetIn.a();
      entity.af += packetIn.b();
      entity.ag += packetIn.c();
      double d0 = entity.ae / 4096.0D;
      double d1 = entity.af / 4096.0D;
      double d2 = entity.ag / 4096.0D;
      if (!entity.bx())
      {
        float f = packetIn.f() ? packetIn.d() * 360 / 256.0F : entity.v;
        float f1 = packetIn.f() ? packetIn.e() * 360 / 256.0F : entity.w;
        entity.a(d0, d1, d2, f, f1, 3, false);
        entity.z = packetIn.g();
      }
    }
  }
  
  public void a(hg packetIn)
  {
    fh.a(packetIn, this, this.f);
    rr entity = packetIn.a(this.g);
    if (entity != null)
    {
      float f = packetIn.a() * 360 / 256.0F;
      entity.h(f);
    }
  }
  
  public void a(hc packetIn)
  {
    fh.a(packetIn, this, this.f);
    for (int i = 0; i < packetIn.a().length; i++) {
      this.g.e(packetIn.a()[i]);
    }
  }
  
  public void a(ha packetIn)
  {
    fh.a(packetIn, this, this.f);
    zj entityplayer = this.f.h;
    double d0 = packetIn.a();
    double d1 = packetIn.b();
    double d2 = packetIn.c();
    float f = packetIn.d();
    float f1 = packetIn.e();
    if (packetIn.g().contains(ha.a.a)) {
      d0 += entityplayer.p;
    } else {
      entityplayer.s = 0.0D;
    }
    if (packetIn.g().contains(ha.a.b)) {
      d1 += entityplayer.q;
    } else {
      entityplayer.t = 0.0D;
    }
    if (packetIn.g().contains(ha.a.c)) {
      d2 += entityplayer.r;
    } else {
      entityplayer.u = 0.0D;
    }
    if (packetIn.g().contains(ha.a.e)) {
      f1 += entityplayer.w;
    }
    if (packetIn.g().contains(ha.a.d)) {
      f += entityplayer.v;
    }
    entityplayer.a(d0, d1, d2, f, f1);
    this.c.a(new ih(packetIn.f()));
    this.c.a(new it.b(entityplayer.p, entityplayer.bl().b, entityplayer.r, entityplayer.v, entityplayer.w, false));
    if (!this.h)
    {
      this.f.h.m = this.f.h.p;
      this.f.h.n = this.f.h.q;
      this.f.h.o = this.f.h.r;
      this.h = true;
      this.f.a((bfb)null);
    }
  }
  
  public void a(fz packetIn)
  {
    fh.a(packetIn, this, this.f);
    for (fz.a spacketmultiblockchange$blockupdatedata : packetIn.a()) {
      this.g.b(spacketmultiblockchange$blockupdatedata.a(), spacketmultiblockchange$blockupdatedata.c());
    }
  }
  
  public void a(gp packetIn)
  {
    fh.a(packetIn, this, this.f);
    if (packetIn.e()) {
      this.g.b(packetIn.b(), packetIn.c(), true);
    }
    this.g.a(packetIn.b() << 4, 0, packetIn.c() << 4, (packetIn.b() << 4) + 15, 256, (packetIn.c() << 4) + 15);
    ase chunk = this.g.a(packetIn.b(), packetIn.c());
    chunk.a(packetIn.a(), packetIn.d(), packetIn.e());
    this.g.b(packetIn.b() << 4, 0, packetIn.c() << 4, (packetIn.b() << 4) + 15, 256, (packetIn.c() << 4) + 15);
    if ((!packetIn.e()) || (!(this.g.s instanceof asy))) {
      chunk.m();
    }
  }
  
  public void a(gm packetIn)
  {
    fh.a(packetIn, this, this.f);
    this.g.b(packetIn.a(), packetIn.b(), false);
  }
  
  public void a(fu packetIn)
  {
    fh.a(packetIn, this, this.f);
    this.g.b(packetIn.b(), packetIn.a());
  }
  
  public void a(gj packetIn)
  {
    this.c.a(packetIn.a());
  }
  
  public void a(eu reason)
  {
    this.f.a((bku)null);
    if (this.e != null)
    {
      if ((this.e instanceof bdz)) {
        this.f.a(new DisconnectedRealmsScreen(((bdz)this.e).a(), "disconnect.lost", reason).getProxy());
      } else {
        this.f.a(new bep(this.e, "disconnect.lost", reason));
      }
    }
    else {
      this.f.a(new bep(new bgr(new bfi()), "disconnect.lost", reason));
    }
  }
  
  public void a(ff<?> packetIn)
  {
    if ((ConfigManager.settings.animationSword != 2) && (Allowed.blocking()) && (Allowed.animations()))
    {
      ado mainItem = DualHand.getItemInMainHand();
      ado offItem = DualHand.getItemInOffHand();
      boolean swordMain = DualHand.hasSword(DualHand.getDefaultHand());
      boolean swordOff = DualHand.hasSword(DualHand.getDefaultHand().a());
      if ((swordMain) && (offItem != null) && ((packetIn instanceof jh))) {
        this.c.a(new jh(qm.b));
      }
      if ((swordMain) && (offItem == null) && ((packetIn instanceof ix))) {
        if ((DualHand.getItemInOffHand() == null) && 
          (bcf.z().h.isUsingItem())) {
          return;
        }
      }
      if ((swordOff) && (mainItem == null) && ((packetIn instanceof ix))) {
        if ((DualHand.getItemInMainHand() == null) && 
          (bcf.z().h.isUsingItem())) {
          return;
        }
      }
    }
    this.c.a(packetIn);
  }
  
  public void a(ib packetIn)
  {
    fh.a(packetIn, this, this.f);
    rr entity = this.g.a(packetIn.a());
    sa entitylivingbase = (sa)this.g.a(packetIn.b());
    if (entitylivingbase == null) {
      entitylivingbase = this.f.h;
    }
    if (entity != null)
    {
      if ((entity instanceof rx)) {
        this.g.a(entity.p, entity.q, entity.r, ng.bh, nh.h, 0.2F, ((this.k.nextFloat() - this.k.nextFloat()) * 0.7F + 1.0F) * 2.0F, false);
      } else {
        this.g.a(entity.p, entity.q, entity.r, ng.cU, nh.h, 0.2F, ((this.k.nextFloat() - this.k.nextFloat()) * 0.7F + 1.0F) * 2.0F, false);
      }
      this.f.j.a(new bls(this.g, entity, entitylivingbase, 0.5F));
      this.g.e(packetIn.a());
    }
  }
  
  public void a(fy packetIn)
  {
    fh.a(packetIn, this, this.f);
    if (packetIn.c() == 2) {
      this.f.r.a(packetIn.a(), false);
    } else {
      this.f.r.d().a(packetIn.a());
    }
  }
  
  public void a(fp packetIn)
  {
    fh.a(packetIn, this, this.f);
    rr entity = this.g.a(packetIn.a());
    if (entity != null) {
      if (packetIn.b() == 0)
      {
        sa entitylivingbase = (sa)entity;
        entitylivingbase.a(qm.a);
      }
      else if (packetIn.b() == 3)
      {
        sa entitylivingbase1 = (sa)entity;
        entitylivingbase1.a(qm.b);
      }
      else if (packetIn.b() == 1)
      {
        entity.aD();
      }
      else if (packetIn.b() == 2)
      {
        zj entityplayer = (zj)entity;
        entityplayer.a(false, false, false);
      }
      else if (packetIn.b() == 4)
      {
        this.f.j.a(entity, cy.j);
      }
      else if (packetIn.b() == 5)
      {
        this.f.j.a(entity, cy.k);
      }
    }
  }
  
  public void a(hb packetIn)
  {
    fh.a(packetIn, this, this.f);
    packetIn.a(this.g).a(packetIn.a());
  }
  
  public void a(fm packetIn)
  {
    fh.a(packetIn, this, this.f);
    double d0 = packetIn.e();
    double d1 = packetIn.f();
    double d2 = packetIn.g();
    float f = packetIn.k() * 360 / 256.0F;
    float f1 = packetIn.l() * 360 / 256.0F;
    sa entitylivingbase = (sa)rt.a(packetIn.d(), this.f.f);
    lm.a(entitylivingbase, d0, d1, d2);
    entitylivingbase.aM = (entitylivingbase.aO = packetIn.m() * 360 / 256.0F);
    rr[] aentity = entitylivingbase.aR();
    if (aentity != null)
    {
      int i = packetIn.b() - entitylivingbase.O();
      for (int j = 0; j < aentity.length; j++) {
        aentity[j].f(aentity[j].O() + i);
      }
    }
    entitylivingbase.f(packetIn.b());
    entitylivingbase.a(packetIn.c());
    entitylivingbase.a(d0, d1, d2, f, f1);
    entitylivingbase.s = (packetIn.h() / 8000.0F);
    entitylivingbase.t = (packetIn.i() / 8000.0F);
    entitylivingbase.u = (packetIn.j() / 8000.0F);
    this.g.a(packetIn.b(), entitylivingbase);
    List<kh.a<?>> list = packetIn.a();
    if (list != null) {
      entitylivingbase.R().a(list);
    }
  }
  
  public void a(hw packetIn)
  {
    fh.a(packetIn, this, this.f);
    this.f.f.a(packetIn.a());
    this.f.f.b(packetIn.b());
  }
  
  public void a(hv packetIn)
  {
    fh.a(packetIn, this, this.f);
    this.f.h.a(packetIn.a(), true);
    this.f.f.T().a(packetIn.a());
  }
  
  public void a(hs packetIn)
  {
    fh.a(packetIn, this, this.f);
    rr entity = this.g.a(packetIn.b());
    if (entity == null)
    {
      b.warn("Received passengers for unknown entity");
    }
    else
    {
      boolean flag = entity.y(this.f.h);
      entity.az();
      for (int i : packetIn.a())
      {
        rr entity1 = this.g.a(i);
        if (entity1 == null)
        {
          b.warn("Received unknown passenger for " + entity);
        }
        else
        {
          entity1.a(entity, true);
          if ((entity1 == this.f.h) && (!flag)) {
            this.f.r.a(bwo.a("mount.onboard", new Object[] { bch.c(this.f.u.U.j()) }), false);
          }
        }
      }
    }
  }
  
  public void a(hm packetIn)
  {
    fh.a(packetIn, this, this.f);
    rr entity = this.g.a(packetIn.a());
    rr entity1 = this.g.a(packetIn.b());
    if ((entity instanceof sb)) {
      if (entity1 != null) {
        ((sb)entity).b(entity1, false);
      } else {
        ((sb)entity).a(false, false);
      }
    }
  }
  
  public void a(gk packetIn)
  {
    fh.a(packetIn, this, this.f);
    rr entity = packetIn.a(this.g);
    if (entity != null) {
      if (packetIn.a() == 21) {
        this.f.U().a(new byb((yo)entity));
      } else {
        entity.a(packetIn.a());
      }
    }
  }
  
  public void a(hq packetIn)
  {
    fh.a(packetIn, this, this.f);
    this.f.h.p(packetIn.a());
    this.f.h.cS().a(packetIn.b());
    this.f.h.cS().b(packetIn.c());
  }
  
  public void a(hp packetIn)
  {
    fh.a(packetIn, this, this.f);
    this.f.h.a(packetIn.a(), packetIn.b(), packetIn.c());
  }
  
  public void a(hf packetIn)
  {
    fh.a(packetIn, this, this.f);
    if (packetIn.a() != this.f.h.am)
    {
      this.h = false;
      bbp scoreboard = this.g.ad();
      this.g = new bku(this, new ahw(0L, packetIn.c(), false, this.f.f.T().s(), packetIn.d()), packetIn.a(), packetIn.b(), this.f.B);
      this.g.a(scoreboard);
      this.f.a(this.g);
      this.f.h.am = packetIn.a();
      this.f.a(new bfa(this));
    }
    this.f.a(packetIn.a());
    this.f.c.a(packetIn.c());
  }
  
  public void a(gl packetIn)
  {
    fh.a(packetIn, this, this.f);
    ahp explosion = new ahp(this.f.f, (rr)null, packetIn.d(), packetIn.e(), packetIn.f(), packetIn.g(), packetIn.h());
    explosion.a(true);
    this.f.h.s += packetIn.a();
    this.f.h.t += packetIn.b();
    this.f.h.u += packetIn.c();
  }
  
  public void a(gc packetIn)
  {
    fh.a(packetIn, this, this.f);
    bmt entityplayersp = this.f.h;
    if ("minecraft:container".equals(packetIn.b()))
    {
      entityplayersp.a(new qv(packetIn.c(), packetIn.d()));
      entityplayersp.bt.d = packetIn.a();
    }
    else if ("minecraft:villager".equals(packetIn.b()))
    {
      entityplayersp.a(new zc(entityplayersp, packetIn.c()));
      entityplayersp.bt.d = packetIn.a();
    }
    else if ("EntityHorse".equals(packetIn.b()))
    {
      rr entity = this.g.a(packetIn.e());
      if ((entity instanceof wk))
      {
        entityplayersp.a((wk)entity, new aav(packetIn.c(), packetIn.d()));
        entityplayersp.bt.d = packetIn.a();
      }
    }
    else if (!packetIn.f())
    {
      entityplayersp.a(new bmv(packetIn.b(), packetIn.c()));
      entityplayersp.bt.d = packetIn.a();
    }
    else
    {
      bmw containerlocalmenu = new bmw(packetIn.b(), packetIn.c(), packetIn.d());
      entityplayersp.a(containerlocalmenu);
      entityplayersp.bt.d = packetIn.a();
    }
  }
  
  public void a(gf packetIn)
  {
    fh.a(packetIn, this, this.f);
    zj entityplayer = this.f.h;
    if (packetIn.a() == -1)
    {
      entityplayer.br.e(packetIn.c());
    }
    else if (packetIn.a() == -2)
    {
      entityplayer.br.a(packetIn.b(), packetIn.c());
    }
    else
    {
      boolean flag = false;
      if ((this.f.m instanceof bgc))
      {
        bgc guicontainercreative = (bgc)this.f.m;
        flag = guicontainercreative.f() != acq.m.a();
      }
      if ((packetIn.a() == 0) && (packetIn.b() >= 36) && (packetIn.b() < 45))
      {
        adq itemstack = entityplayer.bs.a(packetIn.b()).d();
        if ((packetIn.c() != null) && ((itemstack == null) || (itemstack.b < packetIn.c().b))) {
          packetIn.c().c = 5;
        }
        entityplayer.bs.a(packetIn.b(), packetIn.c());
      }
      else if ((packetIn.a() == entityplayer.bt.d) && ((packetIn.a() != 0) || (!flag)))
      {
        entityplayer.bt.a(packetIn.b(), packetIn.c());
      }
    }
  }
  
  public void a(ga packetIn)
  {
    fh.a(packetIn, this, this.f);
    aau container = null;
    zj entityplayer = this.f.h;
    if (packetIn.a() == 0) {
      container = entityplayer.bs;
    } else if (packetIn.a() == entityplayer.bt.d) {
      container = entityplayer.bt;
    }
    if ((container != null) && (!packetIn.c())) {
      a(new im(packetIn.a(), packetIn.b(), true));
    }
  }
  
  public void a(gd packetIn)
  {
    fh.a(packetIn, this, this.f);
    zj entityplayer = this.f.h;
    if (packetIn.a() == 0) {
      entityplayer.bs.a(packetIn.b());
    } else if (packetIn.a() == entityplayer.bt.d) {
      entityplayer.bt.a(packetIn.b());
    }
  }
  
  public void a(gw packetIn)
  {
    fh.a(packetIn, this, this.f);
    apv tileentity = this.g.r(packetIn.a());
    if (!(tileentity instanceof aqn))
    {
      tileentity = new aqn();
      tileentity.a(this.g);
      tileentity.a(packetIn.a());
    }
    this.f.h.a((aqn)tileentity);
  }
  
  public void a(hy packetIn)
  {
    fh.a(packetIn, this, this.f);
    boolean flag = false;
    if (this.f.f.e(packetIn.a()))
    {
      apv tileentity = this.f.f.r(packetIn.a());
      if ((tileentity instanceof aqn))
      {
        aqn tileentitysign = (aqn)tileentity;
        if (tileentitysign.b())
        {
          System.arraycopy(packetIn.b(), 0, tileentitysign.a, 0, 4);
          tileentitysign.v_();
        }
        flag = true;
      }
    }
    if ((!flag) && (this.f.h != null)) {
      b.debug("Unable to locate sign at " + packetIn.a().p() + ", " + packetIn.a().q() + ", " + packetIn.a().r());
    }
  }
  
  public void a(fs packetIn)
  {
    fh.a(packetIn, this, this.f);
    if (this.f.f.e(packetIn.a()))
    {
      apv tileentity = this.f.f.r(packetIn.a());
      int i = packetIn.b();
      boolean flag = (i == 2) && ((tileentity instanceof apy));
      if (((i == 1) && ((tileentity instanceof aqk))) || (flag) || ((i == 3) && ((tileentity instanceof apu))) || ((i == 4) && ((tileentity instanceof aqo))) || ((i == 5) && ((tileentity instanceof aqf))) || ((i == 6) && ((tileentity instanceof apt))) || ((i == 7) && ((tileentity instanceof aqp))) || ((i == 8) && ((tileentity instanceof aqq)))) {
        tileentity.a(packetIn.c());
      }
      if ((flag) && ((this.f.m instanceof bfy))) {
        ((bfy)this.f.m).a();
      }
    }
  }
  
  public void a(ge packetIn)
  {
    fh.a(packetIn, this, this.f);
    zj entityplayer = this.f.h;
    if ((entityplayer.bt != null) && (entityplayer.bt.d == packetIn.a())) {
      entityplayer.bt.b(packetIn.b(), packetIn.c());
    }
  }
  
  public void a(ho packetIn)
  {
    fh.a(packetIn, this, this.f);
    rr entity = this.g.a(packetIn.b());
    if (entity != null) {
      entity.a(packetIn.c(), packetIn.a());
    }
  }
  
  public void a(gb packetIn)
  {
    fh.a(packetIn, this, this.f);
    this.f.h.B();
  }
  
  public void a(ft packetIn)
  {
    fh.a(packetIn, this, this.f);
    this.f.f.c(packetIn.a(), packetIn.d(), packetIn.b(), packetIn.c());
  }
  
  public void a(fr packetIn)
  {
    fh.a(packetIn, this, this.f);
    this.f.f.c(packetIn.a(), packetIn.b(), packetIn.c());
  }
  
  public void a(gn packetIn)
  {
    fh.a(packetIn, this, this.f);
    zj entityplayer = this.f.h;
    int i = packetIn.a();
    float f = packetIn.b();
    int j = on.d(f + 0.5F);
    if ((i >= 0) && (i < gn.a.length) && (gn.a[i] != null)) {
      entityplayer.b(new fb(gn.a[i], new Object[0]));
    }
    if (i == 1)
    {
      this.g.T().b(true);
      this.g.k(0.0F);
    }
    else if (i == 2)
    {
      this.g.T().b(false);
      this.g.k(1.0F);
    }
    else if (i == 3)
    {
      this.f.c.a(ahw.a.a(j));
    }
    else if (i == 4)
    {
      if (j == 0)
      {
        this.f.h.d.a(new ik(ik.a.a));
        this.f.a(new bfa(this));
      }
      else if (j == 1)
      {
        this.f.a(new bfk());
      }
    }
    else if (i == 5)
    {
      bch gamesettings = this.f.u;
      if (f == 0.0F) {
        this.f.a(new ben());
      } else if (f == 101.0F) {
        this.f.r.d().a(new fb("demo.help.movement", new Object[] { bch.c(gamesettings.P.j()), bch.c(gamesettings.Q.j()), bch.c(gamesettings.R.j()), bch.c(gamesettings.S.j()) }));
      } else if (f == 102.0F) {
        this.f.r.d().a(new fb("demo.help.jump", new Object[] { bch.c(gamesettings.T.j()) }));
      } else if (f == 103.0F) {
        this.f.r.d().a(new fb("demo.help.inventory", new Object[] { bch.c(gamesettings.W.j()) }));
      }
    }
    else if (i == 6)
    {
      this.g.a(entityplayer, entityplayer.p, entityplayer.q + entityplayer.bn(), entityplayer.r, ng.u, nh.h, 0.18F, 0.45F);
    }
    else if (i == 7)
    {
      this.g.k(f);
    }
    else if (i == 8)
    {
      this.g.i(f);
    }
    else if (i == 10)
    {
      this.g.a(cy.P, entityplayer.p, entityplayer.q, entityplayer.r, 0.0D, 0.0D, 0.0D, new int[0]);
      this.g.a(entityplayer, entityplayer.p, entityplayer.q, entityplayer.r, ng.aF, nh.f, 1.0F, 1.0F);
    }
  }
  
  public void a(gt packetIn)
  {
    fh.a(packetIn, this, this.f);
    ayz mapdata = adw.a(packetIn.a(), this.f.f);
    packetIn.a(mapdata);
    this.f.o.k().a(mapdata);
  }
  
  public void a(gq packetIn)
  {
    fh.a(packetIn, this, this.f);
    if (packetIn.a()) {
      this.f.f.a(packetIn.b(), packetIn.d(), packetIn.c());
    } else {
      this.f.f.b(packetIn.b(), packetIn.d(), packetIn.c());
    }
  }
  
  public void a(fq packetIn)
  {
    fh.a(packetIn, this, this.f);
    boolean flag = false;
    for (Map.Entry<np, Integer> entry : packetIn.a().entrySet())
    {
      np statbase = (np)entry.getKey();
      int i = ((Integer)entry.getValue()).intValue();
      if ((statbase.d()) && (i > 0))
      {
        if ((this.j) && (this.f.h.G().a(statbase) == 0))
        {
          nj achievement = (nj)statbase;
          this.f.q.a(achievement);
          if (statbase == nk.f)
          {
            this.f.u.I = false;
            this.f.u.b();
          }
        }
        flag = true;
      }
      this.f.h.G().a(this.f.h, statbase, i);
    }
    if ((!this.j) && (!flag) && (this.f.u.I)) {
      this.f.q.b(nk.f);
    }
    this.j = true;
    if ((this.f.m instanceof bfo)) {
      ((bfo)this.f.m).a();
    }
  }
  
  public void a(ie packetIn)
  {
    fh.a(packetIn, this, this.f);
    rr entity = this.g.a(packetIn.b());
    if ((entity instanceof sa))
    {
      rk potion = rk.a(packetIn.c());
      if (potion != null)
      {
        rl potioneffect = new rl(potion, packetIn.e(), packetIn.d(), packetIn.g(), packetIn.f());
        potioneffect.b(packetIn.a());
        ((sa)entity).c(potioneffect);
      }
    }
  }
  
  public void a(gy packetIn)
  {
    fh.a(packetIn, this, this.f);
    if (packetIn.a == gy.a.c)
    {
      rr entity = this.g.a(packetIn.b);
      if (entity == this.f.h) {
        this.f.a(new bem(packetIn.e));
      }
    }
  }
  
  public void a(fw packetIn)
  {
    fh.a(packetIn, this, this.f);
    this.f.f.T().a(packetIn.b());
    this.f.f.T().e(packetIn.a());
  }
  
  public void a(hi packetIn)
  {
    fh.a(packetIn, this, this.f);
    rr entity = packetIn.a(this.g);
    if (entity != null) {
      this.f.a(entity);
    }
  }
  
  public void a(hh packetIn)
  {
    fh.a(packetIn, this, this.f);
    packetIn.a(this.g.aj());
  }
  
  public void a(hx packetIn)
  {
    fh.a(packetIn, this, this.f);
    hx.a spackettitle$type = packetIn.a();
    String s = null;
    String s1 = null;
    String s2 = packetIn.b() != null ? packetIn.b().d() : "";
    switch (spackettitle$type)
    {
    case a: 
      s = s2;
      break;
    case b: 
      s1 = s2;
      break;
    case e: 
      this.f.r.a("", "", -1, -1, -1);
      this.f.r.a();
      return;
    }
    this.f.r.a(s, s1, packetIn.c(), packetIn.d(), packetIn.e());
  }
  
  public void a(ia packetIn)
  {
    this.f.r.h().b(packetIn.a().d().isEmpty() ? null : packetIn.a());
    this.f.r.h().a(packetIn.b().d().isEmpty() ? null : packetIn.b());
  }
  
  public void a(hd packetIn)
  {
    fh.a(packetIn, this, this.f);
    rr entity = packetIn.a(this.g);
    if ((entity instanceof sa)) {
      ((sa)entity).c(packetIn.a());
    }
  }
  
  public void a(gz packetIn)
  {
    fh.a(packetIn, this, this.f);
    for (gz.b spacketplayerlistitem$addplayerdata : packetIn.a()) {
      if (packetIn.b() == gz.a.e)
      {
        this.i.remove(spacketplayerlistitem$addplayerdata.a().getId());
      }
      else
      {
        bkv networkplayerinfo = (bkv)this.i.get(spacketplayerlistitem$addplayerdata.a().getId());
        if (packetIn.b() == gz.a.a)
        {
          networkplayerinfo = new bkv(spacketplayerlistitem$addplayerdata);
          this.i.put(networkplayerinfo.a().getId(), networkplayerinfo);
        }
        if (networkplayerinfo != null) {
          switch (packetIn.b())
          {
          case a: 
            networkplayerinfo.a(spacketplayerlistitem$addplayerdata.c());
            networkplayerinfo.a(spacketplayerlistitem$addplayerdata.b());
            break;
          case b: 
            networkplayerinfo.a(spacketplayerlistitem$addplayerdata.c());
            break;
          case c: 
            networkplayerinfo.a(spacketplayerlistitem$addplayerdata.b());
            break;
          case d: 
            networkplayerinfo.a(spacketplayerlistitem$addplayerdata.d());
          }
        }
      }
    }
  }
  
  public void a(go packetIn)
  {
    a(new is(packetIn.a()));
  }
  
  public void a(gx packetIn)
  {
    fh.a(packetIn, this, this.f);
    zj entityplayer = this.f.h;
    entityplayer.bJ.b = packetIn.b();
    entityplayer.bJ.d = packetIn.d();
    entityplayer.bJ.a = packetIn.a();
    entityplayer.bJ.c = packetIn.c();
    entityplayer.bJ.a(packetIn.e());
    entityplayer.bJ.b(packetIn.f());
  }
  
  public void a(fx packetIn)
  {
    fh.a(packetIn, this, this.f);
    String[] astring = packetIn.a();
    if ((this.f.m instanceof bfg)) {
      ((bfg)this.f.m).a(astring);
    }
  }
  
  public void a(hz packetIn)
  {
    fh.a(packetIn, this, this.f);
    this.f.f.a(this.f.h, packetIn.c(), packetIn.d(), packetIn.e(), packetIn.a(), packetIn.b(), packetIn.f(), packetIn.g());
  }
  
  public void a(gi packetIn)
  {
    fh.a(packetIn, this, this.f);
    this.f.U().a(new bye(new kk(packetIn.a()), packetIn.b(), packetIn.f(), packetIn.g(), false, 0, byi.a.b, (float)packetIn.c(), (float)packetIn.d(), (float)packetIn.e()));
  }
  
  public void a(he packetIn)
  {
    final String s = packetIn.a();
    final String s1 = packetIn.b();
    if (a(s, s1)) {
      if (s.startsWith("level://"))
      {
        String s2 = s.substring("level://".length());
        File file1 = new File(this.f.w, "saves");
        File file2 = new File(file1, s2);
        if (file2.isFile())
        {
          this.c.a(new ja(s1, ja.a.d));
          Futures.addCallback(this.f.P().a(file2), b(s1));
        }
        else
        {
          this.c.a(new ja(s1, ja.a.c));
        }
      }
      else if ((this.f.C() != null) && (this.f.C().b() == bkx.a.a))
      {
        this.c.a(new ja(s1, ja.a.d));
        Futures.addCallback(this.f.P().a(s, s1), b(s1));
      }
      else if ((this.f.C() != null) && (this.f.C().b() != bkx.a.c))
      {
        this.c.a(new ja(s1, ja.a.b));
      }
      else
      {
        this.f.a(new Runnable()
        {
          public void run()
          {
            bks.a(bks.this).a(new beh(new beg()
            {
              public void a(boolean result, int id)
              {
                bks.a(bks.this, bcf.z());
                if (result)
                {
                  if (bks.a(bks.this).C() != null) {
                    bks.a(bks.this).C().a(bkx.a.a);
                  }
                  bks.b(bks.this).a(new ja(bks.1.this.val$s1, ja.a.d));
                  Futures.addCallback(bks.a(bks.this).P().a(bks.1.this.val$s, bks.1.this.val$s1), bks.a(bks.this, bks.1.this.val$s1));
                }
                else
                {
                  if (bks.a(bks.this).C() != null) {
                    bks.a(bks.this).C().a(bkx.a.b);
                  }
                  bks.b(bks.this).a(new ja(bks.1.this.val$s1, ja.a.b));
                }
                bky.b(bks.a(bks.this).C());
                bks.a(bks.this).a((bfb)null);
              }
            }, bwo.a("multiplayer.texturePrompt.line1", new Object[0]), bwo.a("multiplayer.texturePrompt.line2", new Object[0]), 0));
          }
        });
      }
    }
  }
  
  private boolean a(String url, String hash)
  {
    try
    {
      URI uri = new URI(url.replace(' ', '+'));
      String s = uri.getScheme();
      boolean flag = "level".equals(s);
      if ((!"http".equals(s)) && (!"https".equals(s)) && (!flag)) {
        throw new URISyntaxException(url, "Wrong protocol");
      }
      if ((!flag) || ((!url.contains("..")) && (url.endsWith("/resources.zip")))) {
        return true;
      }
      throw new URISyntaxException(url, "Invalid levelstorage resourcepack path");
    }
    catch (URISyntaxException var6)
    {
      this.c.a(new ja(hash, ja.a.c));
    }
    return false;
  }
  
  private FutureCallback<Object> b(final String hash)
  {
    new FutureCallback()
    {
      public void onSuccess(Object p_onSuccess_1_)
      {
        bks.b(bks.this).a(new ja(hash, ja.a.a));
      }
      
      public void onFailure(Throwable p_onFailure_1_)
      {
        bks.b(bks.this).a(new ja(hash, ja.a.c));
      }
    };
  }
  
  public void a(fv packetIn)
  {
    fh.a(packetIn, this, this.f);
    this.f.r.j().a(packetIn);
  }
  
  public void a(gg packetIn)
  {
    fh.a(packetIn, this, this.f);
    if (packetIn.b() == 0) {
      this.f.h.da().b(packetIn.a());
    } else {
      this.f.h.da().a(packetIn.a(), packetIn.b());
    }
  }
  
  public void a(gv packetIn)
  {
    fh.a(packetIn, this, this.f);
    rr entity = this.f.h.bw();
    if ((entity != this.f.h) && (entity.bx()))
    {
      entity.a(packetIn.a(), packetIn.b(), packetIn.c(), packetIn.d(), packetIn.e());
      this.c.a(new iu(entity));
    }
  }
  
  public void a(gh packetIn)
  {
    fh.a(packetIn, this, this.f);
    if ("MC|TrList".equals(packetIn.a()))
    {
      em packetbuffer = packetIn.b();
      try
      {
        int i = packetbuffer.readInt();
        bfb guiscreen = this.f.m;
        if ((guiscreen != null) && ((guiscreen instanceof bgl)) && (i == this.f.h.bt.d))
        {
          ahf imerchant = ((bgl)guiscreen).a();
          ahh merchantrecipelist = ahh.b(packetbuffer);
          imerchant.a(merchantrecipelist);
        }
      }
      catch (IOException ioexception)
      {
        b.error("Couldn't load trade info", ioexception);
      }
      finally
      {
        packetbuffer.release();
      }
    }
    else if ("MC|Brand".equals(packetIn.a()))
    {
      this.f.h.h(packetIn.b().c(32767));
    }
    else if ("MC|BOpen".equals(packetIn.a()))
    {
      qm enumhand = (qm)packetIn.b().a(qm.class);
      adq itemstack = enumhand == qm.b ? this.f.h.cc() : this.f.h.cb();
      if ((itemstack != null) && (itemstack.b() == ads.bX)) {
        this.f.a(new bfw(this.f.h, itemstack, false));
      }
    }
    else if ("MC|DebugPath".equals(packetIn.a()))
    {
      em packetbuffer1 = packetIn.b();
      int j = packetbuffer1.readInt();
      float f = packetbuffer1.readFloat();
      ayp pathentity = ayp.b(packetbuffer1);
      this.f.p.a.a(j, pathentity, f);
    }
  }
  
  public void a(hr packetIn)
  {
    fh.a(packetIn, this, this.f);
    bbp scoreboard = this.g.ad();
    if (packetIn.c() == 0)
    {
      bbl scoreobjective = scoreboard.a(packetIn.a(), bbv.b);
      scoreobjective.a(packetIn.b());
      scoreobjective.a(packetIn.d());
    }
    else
    {
      bbl scoreobjective1 = scoreboard.b(packetIn.a());
      if (packetIn.c() == 1)
      {
        scoreboard.k(scoreobjective1);
      }
      else if (packetIn.c() == 2)
      {
        scoreobjective1.a(packetIn.b());
        scoreobjective1.a(packetIn.d());
      }
    }
  }
  
  public void a(hu packetIn)
  {
    fh.a(packetIn, this, this.f);
    bbp scoreboard = this.g.ad();
    bbl scoreobjective = scoreboard.b(packetIn.b());
    if (packetIn.d() == hu.a.a)
    {
      bbn score = scoreboard.c(packetIn.a(), scoreobjective);
      score.c(packetIn.c());
    }
    else if (packetIn.d() == hu.a.b)
    {
      if (os.b(packetIn.b())) {
        scoreboard.d(packetIn.a(), (bbl)null);
      } else if (scoreobjective != null) {
        scoreboard.d(packetIn.a(), scoreobjective);
      }
    }
  }
  
  public void a(hk packetIn)
  {
    fh.a(packetIn, this, this.f);
    bbp scoreboard = this.g.ad();
    if (packetIn.b().isEmpty())
    {
      scoreboard.a(packetIn.a(), (bbl)null);
    }
    else
    {
      bbl scoreobjective = scoreboard.b(packetIn.b());
      scoreboard.a(packetIn.a(), scoreobjective);
    }
  }
  
  public void a(ht packetIn)
  {
    fh.a(packetIn, this, this.f);
    bbp scoreboard = this.g.ad();
    bbm scoreplayerteam;
    bbm scoreplayerteam;
    if (packetIn.f() == 0) {
      scoreplayerteam = scoreboard.e(packetIn.a());
    } else {
      scoreplayerteam = scoreboard.d(packetIn.a());
    }
    bbr.b team$enumvisible;
    if ((packetIn.f() == 0) || (packetIn.f() == 2))
    {
      scoreplayerteam.a(packetIn.b());
      scoreplayerteam.b(packetIn.c());
      scoreplayerteam.c(packetIn.d());
      scoreplayerteam.a(a.a(packetIn.h()));
      scoreplayerteam.a(packetIn.g());
      team$enumvisible = bbr.b.a(packetIn.i());
      if (team$enumvisible != null) {
        scoreplayerteam.a(team$enumvisible);
      }
      bbr.a team$collisionrule = bbr.a.a(packetIn.j());
      if (team$collisionrule != null) {
        scoreplayerteam.a(team$collisionrule);
      }
    }
    if ((packetIn.f() == 0) || (packetIn.f() == 3)) {
      for (String s : packetIn.e()) {
        scoreboard.a(s, packetIn.a());
      }
    }
    if (packetIn.f() == 4) {
      for (String s1 : packetIn.e()) {
        scoreboard.a(s1, scoreplayerteam);
      }
    }
    if (packetIn.f() == 1) {
      scoreboard.d(scoreplayerteam);
    }
  }
  
  public void a(gr packetIn)
  {
    fh.a(packetIn, this, this.f);
    if (packetIn.j() == 0)
    {
      double d0 = packetIn.i() * packetIn.f();
      double d2 = packetIn.i() * packetIn.g();
      double d4 = packetIn.i() * packetIn.h();
      try
      {
        this.g.a(packetIn.a(), packetIn.b(), packetIn.c(), packetIn.d(), packetIn.e(), d0, d2, d4, packetIn.k());
      }
      catch (Throwable var17)
      {
        b.warn("Could not spawn particle effect " + packetIn.a());
      }
    }
    else
    {
      for (int i = 0; i < packetIn.j(); i++)
      {
        double d1 = this.k.nextGaussian() * packetIn.f();
        double d3 = this.k.nextGaussian() * packetIn.g();
        double d5 = this.k.nextGaussian() * packetIn.h();
        double d6 = this.k.nextGaussian() * packetIn.i();
        double d7 = this.k.nextGaussian() * packetIn.i();
        double d8 = this.k.nextGaussian() * packetIn.i();
        try
        {
          this.g.a(packetIn.a(), packetIn.b(), packetIn.c() + d1, packetIn.d() + d3, packetIn.e() + d5, d6, d7, d8, packetIn.k());
        }
        catch (Throwable var16)
        {
          b.warn("Could not spawn particle effect " + packetIn.a());
          return;
        }
      }
    }
  }
  
  public void a(id packetIn)
  {
    fh.a(packetIn, this, this.f);
    rr entity = this.g.a(packetIn.a());
    sp abstractattributemap;
    if (entity != null)
    {
      if (!(entity instanceof sa)) {
        throw new IllegalStateException("Server tried to update attributes of a non-living entity (actually: " + entity + ")");
      }
      abstractattributemap = ((sa)entity).bZ();
      for (id.a spacketentityproperties$snapshot : packetIn.b())
      {
        iattributeinstance = abstractattributemap.a(spacketentityproperties$snapshot.a());
        if (iattributeinstance == null) {
          if (LabyMod.protocolHack) {
            iattributeinstance = abstractattributemap.b(new ss((sl)null, spacketentityproperties$snapshot.a(), 3.0D, 2.2250738585072014E-308D, Double.MAX_VALUE));
          } else {
            iattributeinstance = abstractattributemap.b(new ss((sl)null, spacketentityproperties$snapshot.a(), 0.0D, 2.2250738585072014E-308D, Double.MAX_VALUE));
          }
        }
        iattributeinstance.a(spacketentityproperties$snapshot.b());
        iattributeinstance.d();
        for (sn attributemodifier : spacketentityproperties$snapshot.c()) {
          iattributeinstance.b(attributemodifier);
        }
      }
    }
    sm iattributeinstance;
  }
  
  public ek a()
  {
    return this.c;
  }
  
  public Collection<bkv> d()
  {
    return this.i.values();
  }
  
  public bkv a(UUID uniqueId)
  {
    return (bkv)this.i.get(uniqueId);
  }
  
  public bkv a(String name)
  {
    for (bkv networkplayerinfo : this.i.values()) {
      if (networkplayerinfo.a().getName().equals(name)) {
        return networkplayerinfo;
      }
    }
    return null;
  }
  
  public GameProfile e()
  {
    return this.d;
  }
}
