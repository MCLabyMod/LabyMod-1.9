import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.ModSettings;
import de.labystudio.utils.Allowed;
import de.labystudio.utils.DualHand;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public abstract class zj
  extends sa
{
  private static final ke<Float> a = kh.a(zj.class, kg.c);
  private static final ke<Integer> b = kh.a(zj.class, kg.b);
  protected static final ke<Byte> bp = kh.a(zj.class, kg.a);
  protected static final ke<Byte> bq = kh.a(zj.class, kg.a);
  public zi br = new zi(this);
  private abq c = new abq();
  public aau bs;
  public aau bt;
  protected aas bu = new aas();
  protected int bv;
  public float bw;
  public float bx;
  public int by;
  public double bz;
  public double bA;
  public double bB;
  public double bC;
  public double bD;
  public double bE;
  protected boolean bF;
  public cj bG;
  private int d;
  public float bH;
  public float cl;
  public float bI;
  private cj e;
  private boolean f;
  private cj g;
  public zh bJ = new zh();
  public int bK;
  public int bL;
  public float bM;
  private int h;
  protected float bN = 0.1F;
  protected float bO = 0.02F;
  private int bQ;
  private final GameProfile bR;
  private boolean bS = false;
  private adq bT = null;
  private final adp bU = l();
  public xw bP;
  
  protected adp l()
  {
    return new adp();
  }
  
  public zj(aht worldIn, GameProfile gameProfileIn)
  {
    super(worldIn);
    this.aq = a(gameProfileIn);
    this.bR = gameProfileIn;
    this.bs = new abl(this.br, !worldIn.E, this);
    this.bt = this.bs;
    cj blockpos = worldIn.R();
    b(blockpos.p() + 0.5D, blockpos.q() + 1, blockpos.r() + 0.5D, 0.0F, 0.0F);
    this.aZ = 180.0F;
    this.U = 20;
  }
  
  protected void bA()
  {
    super.bA();
    bZ().b(yt.e).a(1.0D);
    a(yt.d).a(0.10000000149011612D);
    bZ().b(yt.f);
    bZ().b(yt.h);
  }
  
  protected void i()
  {
    super.i();
    this.Z.a(a, Float.valueOf(0.0F));
    this.Z.a(b, Integer.valueOf(0));
    this.Z.a(bp, Byte.valueOf((byte)0));
    this.Z.a(bq, Byte.valueOf((byte)1));
  }
  
  public void m()
  {
    this.Q = y();
    if (y()) {
      this.z = false;
    }
    if (this.by > 0) {
      this.by -= 1;
    }
    if (cl())
    {
      this.d += 1;
      if (this.d > 100) {
        this.d = 100;
      }
      if (!this.l.E) {
        if (!r()) {
          a(true, true, false);
        } else if (this.l.B()) {
          a(false, true, true);
        }
      }
    }
    else if (this.d > 0)
    {
      this.d += 1;
      if (this.d >= 110) {
        this.d = 0;
      }
    }
    super.m();
    if ((!this.l.E) && (this.bt != null) && (!this.bt.a(this)))
    {
      q();
      this.bt = this.bs;
    }
    if ((aH()) && (this.bJ.a)) {
      X();
    }
    o();
    if (!aI()) {
      this.g = null;
    }
    if (!this.l.E)
    {
      this.bu.a(this);
      b(nt.g);
      if (au()) {
        b(nt.h);
      }
      if (aK()) {
        b(nt.i);
      }
    }
    int i = 29999999;
    double d0 = on.a(this.p, -2.9999999E7D, 2.9999999E7D);
    double d1 = on.a(this.r, -2.9999999E7D, 2.9999999E7D);
    if ((d0 != this.p) || (d1 != this.r)) {
      b(d0, this.q, d1);
    }
    this.aD += 1;
    adq itemstack = cb();
    if (!adq.b(this.bT, itemstack))
    {
      if (!adq.d(this.bT, itemstack)) {
        cZ();
      }
      this.bT = (itemstack == null ? null : itemstack.k());
    }
    this.bU.a();
    cE();
  }
  
  private void o()
  {
    this.bz = this.bC;
    this.bA = this.bD;
    this.bB = this.bE;
    double d0 = this.p - this.bC;
    double d1 = this.q - this.bD;
    double d2 = this.r - this.bE;
    double d3 = 10.0D;
    if (d0 > d3) {
      this.bz = (this.bC = this.p);
    }
    if (d2 > d3) {
      this.bB = (this.bE = this.r);
    }
    if (d1 > d3) {
      this.bA = (this.bD = this.q);
    }
    if (d0 < -d3) {
      this.bz = (this.bC = this.p);
    }
    if (d2 < -d3) {
      this.bB = (this.bE = this.r);
    }
    if (d1 < -d3) {
      this.bA = (this.bD = this.q);
    }
    this.bC += d0 * 0.25D;
    this.bE += d2 * 0.25D;
    this.bD += d1 * 0.25D;
  }
  
  protected void cE()
  {
    float f = this.G;
    float f1 = this.H;
    if (cB())
    {
      f = 0.6F;
      f1 = 0.6F;
    }
    else if (cl())
    {
      f = 0.2F;
      f1 = 0.2F;
    }
    else if (aK())
    {
      f = 0.6F;
      f1 = 1.65F;
    }
    else
    {
      f = 0.6F;
      f1 = 1.8F;
    }
    if ((f != this.G) || (f1 != this.H))
    {
      bbh axisalignedbb = bl();
      axisalignedbb = new bbh(axisalignedbb.a, axisalignedbb.b, axisalignedbb.c, axisalignedbb.a + f, axisalignedbb.b + f1, axisalignedbb.c + f);
      if (!this.l.b(axisalignedbb)) {
        a(f, f1);
      }
    }
  }
  
  public boolean isBlocking(rz hand)
  {
    return (isUsingItem()) && (DualHand.hasSword(hand));
  }
  
  public int V()
  {
    return this.bJ.a ? 1 : 80;
  }
  
  protected nf aa()
  {
    return ng.ek;
  }
  
  protected nf ab()
  {
    return ng.ej;
  }
  
  public int aC()
  {
    return 10;
  }
  
  public void a(nf soundIn, float volume, float pitch)
  {
    this.l.a(this, this.p, this.q, this.r, soundIn, bz(), volume, pitch);
  }
  
  public nh bz()
  {
    return nh.h;
  }
  
  public void a(byte id)
  {
    if (id == 9) {
      v();
    } else if (id == 23) {
      this.bS = false;
    } else if (id == 22) {
      this.bS = true;
    } else {
      super.a(id);
    }
  }
  
  protected boolean cf()
  {
    return (bQ() <= 0.0F) || (cl());
  }
  
  protected void q()
  {
    this.bt = this.bs;
  }
  
  public void aw()
  {
    if ((!this.l.E) && (aK()) && (aI()))
    {
      p();
      d(false);
    }
    else
    {
      double d0 = this.p;
      double d1 = this.q;
      double d2 = this.r;
      float f = this.v;
      float f1 = this.w;
      super.aw();
      this.bw = this.bx;
      this.bx = 0.0F;
      m(this.p - d0, this.q - d1, this.r - d2);
      if ((by() instanceof wc))
      {
        this.w = f1;
        this.v = f;
        this.aM = ((wc)by()).aM;
      }
    }
  }
  
  public void S()
  {
    a(0.6F, 1.8F);
    super.S();
    c(bW());
    this.aA = 0;
  }
  
  protected void cm()
  {
    super.cm();
    bY();
    this.aO = this.v;
  }
  
  public void n()
  {
    if (this.bv > 0) {
      this.bv -= 1;
    }
    if ((!Allowed.swordSlowDown()) && ((this instanceof bmt)))
    {
      bmt player = (bmt)this;
      if ((player.cs()) && (!player.aI()) && (
        (DualHand.isBlocking(player, rz.b)) || 
        (DualHand.isBlocking(player, rz.a))))
      {
        ado item = null;
        if (DualHand.getUsingHand() == qm.a) {
          item = DualHand.getItemInMainHand();
        } else {
          item = DualHand.getItemInOffHand();
        }
        if ((item instanceof aex))
        {
          player.e.a *= 5.0F;
          player.e.b *= 5.0F;
        }
      }
    }
    if ((this.l.ae() == qk.a) && (this.l.U().b("naturalRegeneration")))
    {
      if ((bQ() < bW()) && (this.T % 20 == 0)) {
        b(1.0F);
      }
      if ((this.bu.c()) && (this.T % 10 == 0)) {
        this.bu.a(this.bu.a() + 1);
      }
    }
    this.br.m();
    this.bw = this.bx;
    super.n();
    sm iattributeinstance = a(yt.d);
    if (!this.l.E) {
      iattributeinstance.a(this.bJ.b());
    }
    this.aQ = this.bO;
    if (aL()) {
      this.aQ = ((float)(this.aQ + this.bO * 0.3D));
    }
    l((float)iattributeinstance.e());
    float f = on.a(this.s * this.s + this.u * this.u);
    float f1 = (float)(Math.atan(-this.t * 0.20000000298023224D) * 15.0D);
    if (f > 0.1F) {
      f = 0.1F;
    }
    if ((!this.z) || (bQ() <= 0.0F)) {
      f = 0.0F;
    }
    if ((this.z) || (bQ() <= 0.0F)) {
      f1 = 0.0F;
    }
    this.bx += (f - this.bx) * 0.4F;
    this.aJ += (f1 - this.aJ) * 0.8F;
    if ((bQ() > 0.0F) && (!y()))
    {
      bbh axisalignedbb = null;
      if ((aI()) && (!by().F)) {
        axisalignedbb = bl().a(by().bl()).b(1.0D, 0.0D, 1.0D);
      } else {
        axisalignedbb = bl().b(1.0D, 0.5D, 1.0D);
      }
      List<rr> list = this.l.b(this, axisalignedbb);
      for (int i = 0; i < list.size(); i++)
      {
        rr entity = (rr)list.get(i);
        if (!entity.F) {
          c(entity);
        }
      }
    }
  }
  
  private void c(rr p_71044_1_)
  {
    p_71044_1_.d(this);
  }
  
  public int cF()
  {
    return ((Integer)this.Z.a(b)).intValue();
  }
  
  public void l(int p_85040_1_)
  {
    this.Z.b(b, Integer.valueOf(p_85040_1_));
  }
  
  public void m(int p_85039_1_)
  {
    int i = cF();
    this.Z.b(b, Integer.valueOf(i + p_85039_1_));
  }
  
  public void a(rc cause)
  {
    super.a(cause);
    a(0.2F, 0.2F);
    b(this.p, this.q, this.r);
    this.t = 0.10000000149011612D;
    if (h_().equals("Notch")) {
      a(new adq(ads.e, 1), true, false);
    }
    if ((!this.l.U().b("keepInventory")) && (!y())) {
      this.br.n();
    }
    if (cause != null)
    {
      this.s = (-on.b((this.az + this.v) * 0.017453292F) * 0.1F);
      this.u = (-on.a((this.az + this.v) * 0.017453292F) * 0.1F);
    }
    else
    {
      this.s = (this.u = 0.0D);
    }
    b(nt.A);
    a(nt.h);
  }
  
  protected nf bR()
  {
    return ng.eg;
  }
  
  protected nf bS()
  {
    return ng.ef;
  }
  
  public void b(rr entityIn, int amount)
  {
    if (entityIn != this)
    {
      m(amount);
      Collection<bbl> collection = cW().a(bbv.f);
      if ((entityIn instanceof zj))
      {
        b(nt.D);
        collection.addAll(cW().a(bbv.e));
      }
      else
      {
        b(nt.B);
      }
      collection.addAll(d(entityIn));
      for (bbl scoreobjective : collection)
      {
        bbn score = cW().c(h_(), scoreobjective);
        score.a();
      }
    }
  }
  
  private Collection<bbl> d(rr p_175137_1_)
  {
    String s = (p_175137_1_ instanceof zj) ? p_175137_1_.h_() : p_175137_1_.bc().toString();
    bbm scoreplayerteam = cW().g(h_());
    if (scoreplayerteam != null)
    {
      int i = scoreplayerteam.m().b();
      if ((i >= 0) && (i < bbv.n.length)) {
        for (bbl scoreobjective : cW().a(bbv.n[i]))
        {
          bbn score = cW().c(s, scoreobjective);
          score.a();
        }
      }
    }
    bbm scoreplayerteam1 = cW().g(s);
    if (scoreplayerteam1 != null)
    {
      int j = scoreplayerteam1.m().b();
      if ((j >= 0) && (j < bbv.m.length)) {
        return cW().a(bbv.m[j]);
      }
    }
    return Lists.newArrayList();
  }
  
  public yd a(boolean dropAll)
  {
    return a(this.br.a(this.br.d, (dropAll) && (this.br.h() != null) ? this.br.h().b : 1), false, true);
  }
  
  public yd a(adq itemStackIn, boolean unused)
  {
    return a(itemStackIn, false, false);
  }
  
  public yd a(adq droppedItem, boolean dropAround, boolean traceItem)
  {
    if (droppedItem == null) {
      return null;
    }
    if (droppedItem.b == 0) {
      return null;
    }
    double d0 = this.q - 0.30000001192092896D + bn();
    yd entityitem = new yd(this.l, this.p, d0, this.r, droppedItem);
    entityitem.a(40);
    if (traceItem) {
      entityitem.e(h_());
    }
    if (dropAround)
    {
      float f = this.S.nextFloat() * 0.5F;
      float f1 = this.S.nextFloat() * 6.2831855F;
      entityitem.s = (-on.a(f1) * f);
      entityitem.u = (on.b(f1) * f);
      entityitem.t = 0.20000000298023224D;
    }
    else
    {
      float f2 = 0.3F;
      entityitem.s = (-on.a(this.v * 0.017453292F) * on.b(this.w * 0.017453292F) * f2);
      entityitem.u = (on.b(this.v * 0.017453292F) * on.b(this.w * 0.017453292F) * f2);
      entityitem.t = (-on.a(this.w * 0.017453292F) * f2 + 0.1F);
      float f3 = this.S.nextFloat() * 6.2831855F;
      f2 = 0.02F * this.S.nextFloat();
      entityitem.s += Math.cos(f3) * f2;
      entityitem.t += (this.S.nextFloat() - this.S.nextFloat()) * 0.1F;
      entityitem.u += Math.sin(f3) * f2;
    }
    adq itemstack = a(entityitem);
    if (traceItem)
    {
      if (itemstack != null) {
        a(nt.e(itemstack.b()), droppedItem.b);
      }
      b(nt.x);
    }
    return entityitem;
  }
  
  protected adq a(yd p_184816_1_)
  {
    this.l.a(p_184816_1_);
    adq itemstack = p_184816_1_.k();
    return itemstack;
  }
  
  public float a(arc p_184813_1_)
  {
    float f = this.br.a(p_184813_1_);
    if (f > 1.0F)
    {
      int i = ago.e(this);
      adq itemstack = cb();
      if ((i > 0) && (itemstack != null)) {
        f += i * i + 1;
      }
    }
    if (a(rm.c)) {
      f *= (1.0F + (b(rm.c).c() + 1) * 0.2F);
    }
    if (a(rm.d))
    {
      float f1 = 1.0F;
      switch (b(rm.d).c())
      {
      case 0: 
        f1 = 0.3F;
        break;
      case 1: 
        f1 = 0.09F;
        break;
      case 2: 
        f1 = 0.0027F;
        break;
      case 3: 
      default: 
        f1 = 8.1E-4F;
      }
      f *= f1;
    }
    if ((a(axe.h)) && (!ago.i(this))) {
      f /= 5.0F;
    }
    if (!this.z) {
      f /= 5.0F;
    }
    return f;
  }
  
  public boolean b(arc p_184823_1_)
  {
    return this.br.b(p_184823_1_);
  }
  
  public void a(dn tagCompund)
  {
    super.a(tagCompund);
    this.aq = a(this.bR);
    du nbttaglist = tagCompund.c("Inventory", 10);
    this.br.b(nbttaglist);
    this.br.d = tagCompund.h("SelectedItemSlot");
    this.bF = tagCompund.p("Sleeping");
    this.d = tagCompund.g("SleepTimer");
    this.bM = tagCompund.j("XpP");
    this.bK = tagCompund.h("XpLevel");
    this.bL = tagCompund.h("XpTotal");
    this.h = tagCompund.h("XpSeed");
    if (this.h == 0) {
      this.h = this.S.nextInt();
    }
    l(tagCompund.h("Score"));
    if (this.bF)
    {
      this.bG = new cj(this);
      a(true, true, false);
    }
    if ((tagCompund.b("SpawnX", 99)) && (tagCompund.b("SpawnY", 99)) && (tagCompund.b("SpawnZ", 99)))
    {
      this.e = new cj(tagCompund.h("SpawnX"), tagCompund.h("SpawnY"), tagCompund.h("SpawnZ"));
      this.f = tagCompund.p("SpawnForced");
    }
    this.bu.a(tagCompund);
    this.bJ.b(tagCompund);
    if (tagCompund.b("EnderItems", 9))
    {
      du nbttaglist1 = tagCompund.c("EnderItems", 10);
      this.c.a(nbttaglist1);
    }
  }
  
  public void b(dn tagCompound)
  {
    super.b(tagCompound);
    tagCompound.a("DataVersion", 169);
    tagCompound.a("Inventory", this.br.a(new du()));
    tagCompound.a("SelectedItemSlot", this.br.d);
    tagCompound.a("Sleeping", this.bF);
    tagCompound.a("SleepTimer", (short)this.d);
    tagCompound.a("XpP", this.bM);
    tagCompound.a("XpLevel", this.bK);
    tagCompound.a("XpTotal", this.bL);
    tagCompound.a("XpSeed", this.h);
    tagCompound.a("Score", cF());
    if (this.e != null)
    {
      tagCompound.a("SpawnX", this.e.p());
      tagCompound.a("SpawnY", this.e.q());
      tagCompound.a("SpawnZ", this.e.r());
      tagCompound.a("SpawnForced", this.f);
    }
    this.bu.b(tagCompound);
    this.bJ.a(tagCompound);
    tagCompound.a("EnderItems", this.c.h());
  }
  
  public boolean a(rc source, float amount)
  {
    if (b(source)) {
      return false;
    }
    if ((this.bJ.a) && (!source.g())) {
      return false;
    }
    this.aU = 0;
    if (bQ() <= 0.0F) {
      return false;
    }
    if ((cl()) && (!this.l.E)) {
      a(true, true, false);
    }
    if (source.r())
    {
      if (this.l.ae() == qk.a) {
        amount = 0.0F;
      }
      if (this.l.ae() == qk.b) {
        amount = amount / 2.0F + 1.0F;
      }
      if (this.l.ae() == qk.d) {
        amount = amount * 3.0F / 2.0F;
      }
    }
    if (amount == 0.0F) {
      return false;
    }
    rr entity = source.j();
    if (((entity instanceof zm)) && (((zm)entity).e != null)) {
      entity = ((zm)entity).e;
    }
    return super.a(source, amount);
  }
  
  public boolean a(zj other)
  {
    bbr team = aO();
    bbr team1 = other.aO();
    return !team.a(team1) ? true : team == null ? true : team.g();
  }
  
  protected void j(float p_70675_1_)
  {
    this.br.a(p_70675_1_);
  }
  
  protected void k(float p_184590_1_)
  {
    if ((p_184590_1_ > 0.0F) && (this.bm != null) && (this.bm.b() == ads.cQ))
    {
      int i = 1 + on.d(p_184590_1_);
      this.bm.a(i, this);
      if (this.bm.b <= 0)
      {
        qm enumhand = ct();
        if (enumhand == qm.a) {
          a(rw.a, (adq)null);
        } else {
          a(rw.b, (adq)null);
        }
        this.bm = null;
        a(ng.eR, 0.8F, 0.8F + this.l.r.nextFloat() * 0.4F);
      }
    }
  }
  
  public float cG()
  {
    int i = 0;
    for (adq itemstack : this.br.b) {
      if (itemstack != null) {
        i++;
      }
    }
    return i / this.br.b.length;
  }
  
  protected void d(rc damageSrc, float damageAmount)
  {
    if (!b(damageSrc))
    {
      if ((!damageSrc.e()) && 
        (isBlocking(DualHand.getUseFocus())) && (damageAmount > 0.0F) && (ConfigManager.settings.animationSword != 2)) {
        if ((Allowed.animations()) && 
          (Allowed.blocking()) && 
          (bcf.z().E())) {
          damageAmount = (1.0F + damageAmount) * 0.5F;
        }
      }
      damageAmount = b(damageSrc, damageAmount);
      damageAmount = c(damageSrc, damageAmount);
      float f = damageAmount;
      damageAmount = Math.max(damageAmount - cp(), 0.0F);
      n(cp() - (f - damageAmount));
      if (damageAmount != 0.0F)
      {
        a(damageSrc.f());
        float f1 = bQ();
        c(bQ() - damageAmount);
        bU().a(damageSrc, f1, damageAmount);
        if (damageAmount < 3.4028235E37F) {
          a(nt.z, Math.round(damageAmount * 10.0F));
        }
      }
    }
  }
  
  public void a(aqn signTile) {}
  
  public void a(ahj p_184809_1_) {}
  
  public void a(apy p_184824_1_) {}
  
  public void a(ahf villager) {}
  
  public void a(qg chestInventory) {}
  
  public void a(wk p_184826_1_, qg p_184826_2_) {}
  
  public void a(qn guiOwner) {}
  
  public void a(adq p_184814_1_, qm p_184814_2_) {}
  
  public qo a(rr p_184822_1_, adq p_184822_2_, qm p_184822_3_)
  {
    if (y())
    {
      if ((p_184822_1_ instanceof qg)) {
        a((qg)p_184822_1_);
      }
      return qo.b;
    }
    adq itemstack = p_184822_2_ != null ? p_184822_2_.k() : null;
    if (!p_184822_1_.a(this, p_184822_2_, p_184822_3_))
    {
      if ((p_184822_2_ != null) && ((p_184822_1_ instanceof sa)))
      {
        if (this.bJ.d) {
          p_184822_2_ = itemstack;
        }
        if (p_184822_2_.a(this, (sa)p_184822_1_, p_184822_3_))
        {
          if ((p_184822_2_.b <= 0) && (!this.bJ.d)) {
            a(p_184822_3_, (adq)null);
          }
          return qo.a;
        }
      }
      return qo.b;
    }
    if ((p_184822_2_ != null) && (p_184822_2_ == b(p_184822_3_))) {
      if ((p_184822_2_.b <= 0) && (!this.bJ.d)) {
        a(p_184822_3_, (adq)null);
      } else if ((p_184822_2_.b < itemstack.b) && (this.bJ.d)) {
        p_184822_2_.b = itemstack.b;
      }
    }
    return qo.a;
  }
  
  public double ax()
  {
    return -0.35D;
  }
  
  public void p()
  {
    super.p();
    this.j = 0;
  }
  
  public void f(rr targetEntity)
  {
    if (targetEntity.aT()) {
      if (!targetEntity.t(this))
      {
        float f = (float)a(yt.e).e();
        float f1 = 0.0F;
        if ((targetEntity instanceof sa)) {
          f1 = ago.a(cb(), ((sa)targetEntity).ca());
        } else {
          f1 = ago.a(cb(), sf.a);
        }
        float f2 = o(0.5F);
        f *= (0.2F + f2 * f2 * 0.8F);
        f1 *= f2;
        cZ();
        if ((f > 0.0F) || (f1 > 0.0F))
        {
          boolean flag = f2 > 0.9F;
          boolean flag1 = false;
          boolean flag2 = false;
          boolean flag3 = false;
          int i = 0;
          i += ago.a(this);
          if ((aL()) && (flag))
          {
            this.l.a((zj)null, this.p, this.q, this.r, ng.dX, bz(), 1.0F, 1.0F);
            i++;
            flag1 = true;
          }
          flag2 = (flag) && (this.L > 0.0F) && (!this.z) && (!n_()) && (!ai()) && (!a(rm.o)) && (!aI()) && ((targetEntity instanceof sa));
          flag2 = (flag2) && (!aL());
          if (flag2) {
            f *= 1.5F;
          }
          f += f1;
          double d0 = this.J - this.I;
          if ((flag) && (!flag2) && (!flag1) && (this.z) && (d0 < ck()))
          {
            adq itemstack = b(qm.a);
            if ((itemstack != null) && ((itemstack.b() instanceof aex))) {
              flag3 = true;
            }
          }
          float f4 = 0.0F;
          boolean flag4 = false;
          int j = ago.b(this);
          if ((targetEntity instanceof sa))
          {
            f4 = ((sa)targetEntity).bQ();
            if ((j > 0) && (!targetEntity.aH()))
            {
              flag4 = true;
              targetEntity.g(1);
            }
          }
          double d1 = targetEntity.s;
          double d2 = targetEntity.t;
          double d3 = targetEntity.u;
          boolean flag5 = targetEntity.a(rc.a(this), f);
          if (flag5)
          {
            if (i > 0)
            {
              if ((targetEntity instanceof sa)) {
                ((sa)targetEntity).a(this, i * 0.5F, on.a(this.v * 0.017453292F), -on.b(this.v * 0.017453292F));
              } else {
                targetEntity.g(-on.a(this.v * 0.017453292F) * i * 0.5F, 0.1D, on.b(this.v * 0.017453292F) * i * 0.5F);
              }
              this.s *= 0.6D;
              this.u *= 0.6D;
              e(false);
            }
            if (flag3)
            {
              for (sa entitylivingbase : this.l.a(sa.class, targetEntity.bl().b(1.0D, 0.25D, 1.0D))) {
                if ((entitylivingbase != this) && (entitylivingbase != targetEntity) && (!r(entitylivingbase)) && (h(entitylivingbase) < 9.0D))
                {
                  entitylivingbase.a(this, 0.4F, on.a(this.v * 0.017453292F), -on.b(this.v * 0.017453292F));
                  entitylivingbase.a(rc.a(this), 1.0F);
                }
              }
              this.l.a((zj)null, this.p, this.q, this.r, ng.ea, bz(), 1.0F, 1.0F);
              cH();
            }
            if (((targetEntity instanceof lr)) && (targetEntity.D))
            {
              ((lr)targetEntity).a.a(new hn(targetEntity));
              targetEntity.D = false;
              targetEntity.s = d1;
              targetEntity.t = d2;
              targetEntity.u = d3;
            }
            if (flag2)
            {
              this.l.a((zj)null, this.p, this.q, this.r, ng.dW, bz(), 1.0F, 1.0F);
              a(targetEntity);
            }
            if ((!flag2) && (!flag3)) {
              if (flag) {
                this.l.a((zj)null, this.p, this.q, this.r, ng.dZ, bz(), 1.0F, 1.0F);
              } else {
                this.l.a((zj)null, this.p, this.q, this.r, ng.eb, bz(), 1.0F, 1.0F);
              }
            }
            if (f1 > 0.0F) {
              b(targetEntity);
            }
            if ((!this.l.E) && ((targetEntity instanceof zj)))
            {
              zj entityplayer = (zj)targetEntity;
              adq itemstack2 = cb();
              adq itemstack3 = entityplayer.cs() ? entityplayer.cv() : null;
              if ((itemstack2 != null) && (itemstack3 != null) && ((itemstack2.b() instanceof abz)) && (itemstack3.b() == ads.cQ))
              {
                float f3 = 0.25F + ago.e(this) * 0.05F;
                if (flag1) {
                  f3 += 0.75F;
                }
                if (this.S.nextFloat() < f3)
                {
                  entityplayer.da().a(ads.cQ, 100);
                  this.l.a(entityplayer, (byte)30);
                }
              }
            }
            if (f >= 18.0F) {
              b(nk.F);
            }
            z(targetEntity);
            if ((targetEntity instanceof sa)) {
              ago.a((sa)targetEntity, this);
            }
            ago.b(this, targetEntity);
            adq itemstack1 = cb();
            rr entity = targetEntity;
            if ((targetEntity instanceof ws))
            {
              wr ientitymultipart = ((ws)targetEntity).a;
              if ((ientitymultipart instanceof sa)) {
                entity = (sa)ientitymultipart;
              }
            }
            if ((itemstack1 != null) && ((entity instanceof sa)))
            {
              itemstack1.a((sa)entity, this);
              if (itemstack1.b <= 0) {
                a(qm.a, (adq)null);
              }
            }
            if ((targetEntity instanceof sa))
            {
              float f5 = f4 - ((sa)targetEntity).bQ();
              a(nt.y, Math.round(f5 * 10.0F));
              if (j > 0) {
                targetEntity.g(j * 4);
              }
              if (((this.l instanceof lp)) && (f5 > 2.0F))
              {
                int k = (int)(f5 * 0.5D);
                ((lp)this.l).a(cy.S, targetEntity.p, targetEntity.q + targetEntity.H * 0.5F, targetEntity.r, k, 0.1D, 0.0D, 0.1D, 0.2D, new int[0]);
              }
            }
            a(0.3F);
          }
          else
          {
            this.l.a((zj)null, this.p, this.q, this.r, ng.dY, bz(), 1.0F, 1.0F);
            if (flag4) {
              targetEntity.X();
            }
          }
        }
      }
    }
  }
  
  public void a(rr entityHit) {}
  
  public void b(rr entityHit) {}
  
  public void cH()
  {
    double d0 = -on.a(this.v * 0.017453292F);
    double d1 = on.b(this.v * 0.017453292F);
    if ((this.l instanceof lp)) {
      ((lp)this.l).a(cy.T, this.p + d0, this.q + this.H * 0.5D, this.r + d1, 0, d0, 0.0D, d1, 0.0D, new int[0]);
    }
  }
  
  public void cI() {}
  
  public void T()
  {
    super.T();
    this.bs.b(this);
    if (this.bt != null) {
      this.bt.b(this);
    }
  }
  
  public boolean av()
  {
    return (!this.bF) && (super.av());
  }
  
  public boolean cJ()
  {
    return false;
  }
  
  public GameProfile cK()
  {
    return this.bR;
  }
  
  public zj.a a(cj bedLocation)
  {
    if (!this.l.E)
    {
      if ((cl()) || (!au())) {
        return zj.a.e;
      }
      if (!this.l.s.d()) {
        return zj.a.b;
      }
      if (this.l.B()) {
        return zj.a.c;
      }
      if ((Math.abs(this.p - bedLocation.p()) > 3.0D) || (Math.abs(this.q - bedLocation.q()) > 2.0D) || (Math.abs(this.r - bedLocation.r()) > 3.0D)) {
        return zj.a.d;
      }
      double d0 = 8.0D;
      double d1 = 5.0D;
      List<yq> list = this.l.a(yq.class, new bbh(bedLocation.p() - d0, bedLocation.q() - d1, bedLocation.r() - d0, bedLocation.p() + d0, bedLocation.q() + d1, bedLocation.r() + d0));
      if (!list.isEmpty()) {
        return zj.a.f;
      }
    }
    if (aI()) {
      p();
    }
    a(0.2F, 0.2F);
    if (this.l.e(bedLocation))
    {
      cq enumfacing = (cq)this.l.o(bedLocation).c(amg.D);
      float f = 0.5F;
      float f1 = 0.5F;
      switch (enumfacing)
      {
      case d: 
        f1 = 0.9F;
        break;
      case c: 
        f1 = 0.1F;
        break;
      case e: 
        f = 0.1F;
        break;
      case f: 
        f = 0.9F;
      }
      a(enumfacing);
      b(bedLocation.p() + f, bedLocation.q() + 0.6875F, bedLocation.r() + f1);
    }
    else
    {
      b(bedLocation.p() + 0.5F, bedLocation.q() + 0.6875F, bedLocation.r() + 0.5F);
    }
    this.bF = true;
    this.d = 0;
    this.bG = bedLocation;
    this.s = (this.u = this.t = 0.0D);
    if (!this.l.E) {
      this.l.e();
    }
    return zj.a.a;
  }
  
  private void a(cq p_175139_1_)
  {
    this.bH = 0.0F;
    this.bI = 0.0F;
    switch (p_175139_1_)
    {
    case d: 
      this.bI = -1.8F;
      break;
    case c: 
      this.bI = 1.8F;
      break;
    case e: 
      this.bH = 1.8F;
      break;
    case f: 
      this.bH = -1.8F;
    }
  }
  
  public void a(boolean immediately, boolean updateWorldFlag, boolean setSpawn)
  {
    a(0.6F, 1.8F);
    arc iblockstate = this.l.o(this.bG);
    if ((this.bG != null) && (iblockstate.t() == aju.C))
    {
      this.l.a(this.bG, iblockstate.a(ajr.b, Boolean.valueOf(false)), 4);
      cj blockpos = ajr.a(this.l, this.bG, 0);
      if (blockpos == null) {
        blockpos = this.bG.a();
      }
      b(blockpos.p() + 0.5F, blockpos.q() + 0.1F, blockpos.r() + 0.5F);
    }
    this.bF = false;
    if ((!this.l.E) && (updateWorldFlag)) {
      this.l.e();
    }
    this.d = (immediately ? 0 : 100);
    if (setSpawn) {
      a(this.bG, false);
    }
  }
  
  private boolean r()
  {
    return this.l.o(this.bG).t() == aju.C;
  }
  
  public static cj a(aht worldIn, cj bedLocation, boolean forceSpawn)
  {
    ajt block = worldIn.o(bedLocation).t();
    if (block != aju.C)
    {
      if (!forceSpawn) {
        return null;
      }
      boolean flag = block.d();
      boolean flag1 = worldIn.o(bedLocation.a()).t().d();
      return (flag) && (flag1) ? bedLocation : null;
    }
    return ajr.a(worldIn, bedLocation, 0);
  }
  
  public float cL()
  {
    if (this.bG != null)
    {
      cq enumfacing = (cq)this.l.o(this.bG).c(amg.D);
      switch (enumfacing)
      {
      case d: 
        return 90.0F;
      case c: 
        return 270.0F;
      case e: 
        return 0.0F;
      case f: 
        return 180.0F;
      }
    }
    return 0.0F;
  }
  
  public boolean cl()
  {
    return this.bF;
  }
  
  public boolean cM()
  {
    return (this.bF) && (this.d >= 100);
  }
  
  public int cN()
  {
    return this.d;
  }
  
  public void b(eu chatComponent) {}
  
  public cj cO()
  {
    return this.e;
  }
  
  public boolean cP()
  {
    return this.f;
  }
  
  public void a(cj pos, boolean forced)
  {
    if (pos != null)
    {
      this.e = pos;
      this.f = forced;
    }
    else
    {
      this.e = null;
      this.f = false;
    }
  }
  
  public boolean a(nj p_189102_1_)
  {
    return false;
  }
  
  public void b(np achievementIn)
  {
    a(achievementIn, 1);
  }
  
  public void a(np stat, int amount) {}
  
  public void a(np p_175145_1_) {}
  
  public void ch()
  {
    super.ch();
    b(nt.w);
    if (aL()) {
      a(0.8F);
    } else {
      a(0.2F);
    }
  }
  
  public void g(float strafe, float forward)
  {
    double d0 = this.p;
    double d1 = this.q;
    double d2 = this.r;
    if ((this.bJ.b) && (!aI()))
    {
      double d3 = this.t;
      float f = this.aQ;
      this.aQ = (this.bJ.a() * (aL() ? 2 : 1));
      super.g(strafe, forward);
      this.t = (d3 * 0.6D);
      this.aQ = f;
      this.L = 0.0F;
      b(7, false);
    }
    else
    {
      super.g(strafe, forward);
    }
    l(this.p - d0, this.q - d1, this.r - d2);
  }
  
  public float ck()
  {
    return (float)a(yt.d).e();
  }
  
  public void l(double p_71000_1_, double p_71000_3_, double p_71000_5_)
  {
    if (!aI()) {
      if (a(axe.h))
      {
        int i = Math.round(on.a(p_71000_1_ * p_71000_1_ + p_71000_3_ * p_71000_3_ + p_71000_5_ * p_71000_5_) * 100.0F);
        if (i > 0)
        {
          a(nt.q, i);
          a(0.015F * i * 0.01F);
        }
      }
      else if (ai())
      {
        int j = Math.round(on.a(p_71000_1_ * p_71000_1_ + p_71000_5_ * p_71000_5_) * 100.0F);
        if (j > 0)
        {
          a(nt.m, j);
          a(0.015F * j * 0.01F);
        }
      }
      else if (n_())
      {
        if (p_71000_3_ > 0.0D) {
          a(nt.o, (int)Math.round(p_71000_3_ * 100.0D));
        }
      }
      else if (this.z)
      {
        int k = Math.round(on.a(p_71000_1_ * p_71000_1_ + p_71000_5_ * p_71000_5_) * 100.0F);
        if (k > 0) {
          if (aL())
          {
            a(nt.l, k);
            a(0.099999994F * k * 0.01F);
          }
          else if (aK())
          {
            a(nt.k, k);
            a(0.005F * k * 0.01F);
          }
          else
          {
            a(nt.j, k);
            a(0.01F * k * 0.01F);
          }
        }
      }
      else if (cB())
      {
        int l = Math.round(on.a(p_71000_1_ * p_71000_1_ + p_71000_3_ * p_71000_3_ + p_71000_5_ * p_71000_5_) * 100.0F);
        a(nt.v, l);
      }
      else
      {
        int i1 = Math.round(on.a(p_71000_1_ * p_71000_1_ + p_71000_5_ * p_71000_5_) * 100.0F);
        if (i1 > 25) {
          a(nt.p, i1);
        }
      }
    }
  }
  
  private void m(double p_71015_1_, double p_71015_3_, double p_71015_5_)
  {
    if (aI())
    {
      int i = Math.round(on.a(p_71015_1_ * p_71015_1_ + p_71015_3_ * p_71015_3_ + p_71015_5_ * p_71015_5_) * 100.0F);
      if (i > 0) {
        if ((by() instanceof aah))
        {
          a(nt.r, i);
          if (this.g == null) {
            this.g = new cj(this);
          } else if (this.g.e(on.c(this.p), on.c(this.q), on.c(this.r)) >= 1000000.0D) {
            b(nk.q);
          }
        }
        else if ((by() instanceof aag))
        {
          a(nt.s, i);
        }
        else if ((by() instanceof wc))
        {
          a(nt.t, i);
        }
        else if ((by() instanceof wk))
        {
          a(nt.u, i);
        }
      }
    }
  }
  
  public void e(float distance, float damageMultiplier)
  {
    if (!this.bJ.c)
    {
      if (distance >= 2.0F) {
        a(nt.n, (int)Math.round(distance * 100.0D));
      }
      super.e(distance, damageMultiplier);
    }
  }
  
  protected void ak()
  {
    if (!y()) {
      super.ak();
    }
  }
  
  protected nf e(int heightIn)
  {
    return heightIn > 4 ? ng.ec : ng.ei;
  }
  
  public void b(sa entityLivingIn)
  {
    if ((entityLivingIn instanceof yl)) {
      b(nk.s);
    }
    rt.a entitylist$entityegginfo = (rt.a)rt.a.get(rt.b(entityLivingIn));
    if (entitylist$entityegginfo != null) {
      b(entitylist$entityegginfo.d);
    }
  }
  
  public void aQ()
  {
    if (!this.bJ.b) {
      super.aQ();
    }
  }
  
  public void n(int amount)
  {
    m(amount);
    int i = Integer.MAX_VALUE - this.bL;
    if (amount > i) {
      amount = i;
    }
    this.bM += amount / cR();
    for (this.bL += amount; this.bM >= 1.0F; this.bM /= cR())
    {
      this.bM = ((this.bM - 1.0F) * cR());
      a(1);
    }
  }
  
  public int cQ()
  {
    return this.h;
  }
  
  public void b(int levels)
  {
    this.bK -= levels;
    if (this.bK < 0)
    {
      this.bK = 0;
      this.bM = 0.0F;
      this.bL = 0;
    }
    this.h = this.S.nextInt();
  }
  
  public void a(int levels)
  {
    this.bK += levels;
    if (this.bK < 0)
    {
      this.bK = 0;
      this.bM = 0.0F;
      this.bL = 0;
    }
    if ((levels > 0) && (this.bK % 5 == 0) && (this.bQ < this.T - 100.0F))
    {
      float f = this.bK > 30 ? 1.0F : this.bK / 30.0F;
      this.l.a((zj)null, this.p, this.q, this.r, ng.eh, bz(), f * 0.75F, 1.0F);
      this.bQ = this.T;
    }
  }
  
  public int cR()
  {
    return this.bK >= 15 ? 37 + (this.bK - 15) * 5 : this.bK >= 30 ? 112 + (this.bK - 30) * 9 : 7 + this.bK * 2;
  }
  
  public void a(float p_71020_1_)
  {
    if (!this.bJ.a) {
      if (!this.l.E) {
        this.bu.a(p_71020_1_);
      }
    }
  }
  
  public aas cS()
  {
    return this.bu;
  }
  
  public boolean l(boolean ignoreHunger)
  {
    return ((ignoreHunger) || (this.bu.c())) && (!this.bJ.a);
  }
  
  public boolean cT()
  {
    return (bQ() > 0.0F) && (bQ() < bW());
  }
  
  public boolean cU()
  {
    return this.bJ.e;
  }
  
  public boolean a(cj p_175151_1_, cq p_175151_2_, adq p_175151_3_)
  {
    if (this.bJ.e) {
      return true;
    }
    if (p_175151_3_ == null) {
      return false;
    }
    cj blockpos = p_175151_1_.a(p_175151_2_.d());
    ajt block = this.l.o(blockpos).t();
    return (p_175151_3_.b(block)) || (p_175151_3_.x());
  }
  
  protected int b(zj player)
  {
    if ((!this.l.U().b("keepInventory")) && (!y()))
    {
      int i = this.bK * 7;
      return i > 100 ? 100 : i;
    }
    return 0;
  }
  
  protected boolean bE()
  {
    return true;
  }
  
  public boolean bh()
  {
    return true;
  }
  
  public void a(zj oldPlayer, boolean respawnFromEnd)
  {
    if (respawnFromEnd)
    {
      this.br.a(oldPlayer.br);
      c(oldPlayer.bQ());
      this.bu = oldPlayer.bu;
      this.bK = oldPlayer.bK;
      this.bL = oldPlayer.bL;
      this.bM = oldPlayer.bM;
      l(oldPlayer.cF());
      this.an = oldPlayer.an;
      this.ao = oldPlayer.ao;
      this.ap = oldPlayer.ap;
    }
    else if ((this.l.U().b("keepInventory")) || (oldPlayer.y()))
    {
      this.br.a(oldPlayer.br);
      this.bK = oldPlayer.bK;
      this.bL = oldPlayer.bL;
      this.bM = oldPlayer.bM;
      l(oldPlayer.cF());
    }
    this.h = oldPlayer.h;
    this.c = oldPlayer.c;
    R().b(bp, oldPlayer.R().a(bp));
  }
  
  protected boolean ae()
  {
    return !this.bJ.b;
  }
  
  public void w() {}
  
  public void a(ahw.a gameType) {}
  
  public String h_()
  {
    return this.bR.getName();
  }
  
  public abq cV()
  {
    return this.c;
  }
  
  public adq a(rw slotIn)
  {
    return slotIn.a() == rw.a.b ? this.br.b[slotIn.b()] : slotIn == rw.b ? this.br.c[0] : slotIn == rw.a ? this.br.h() : null;
  }
  
  public void a(rw slotIn, adq stack)
  {
    if (slotIn == rw.a)
    {
      a_(stack);
      this.br.a[this.br.d] = stack;
    }
    else if (slotIn == rw.b)
    {
      a_(stack);
      this.br.c[0] = stack;
    }
    else if (slotIn.a() == rw.a.b)
    {
      a_(stack);
      this.br.b[slotIn.b()] = stack;
    }
  }
  
  public Iterable<adq> aE()
  {
    return Lists.newArrayList(new adq[] { cb(), cc() });
  }
  
  public Iterable<adq> aF()
  {
    return Arrays.asList(this.br.b);
  }
  
  public boolean e(zj player)
  {
    if (!aN()) {
      return false;
    }
    if (player.y()) {
      return false;
    }
    bbr team = aO();
    return (team == null) || (player == null) || (player.aO() != team) || (!team.h());
  }
  
  public abstract boolean y();
  
  public abstract boolean l_();
  
  public boolean bd()
  {
    return !this.bJ.b;
  }
  
  public bbp cW()
  {
    return this.l.ad();
  }
  
  public bbr aO()
  {
    return cW().g(h_());
  }
  
  public eu i_()
  {
    eu itextcomponent = new fa(bbm.a(aO(), h_()));
    itextcomponent.b().a(new et(et.a.d, "/msg " + h_() + " "));
    itextcomponent.b().a(bk());
    itextcomponent.b().a(h_());
    return itextcomponent;
  }
  
  long sneak = 0L;
  boolean is = false;
  int value = 0;
  
  public float bn()
  {
    float f = 1.62F;
    if (cl()) {
      f = 0.2F;
    }
    if ((this.is != aK()) || (this.sneak <= 0L)) {
      this.sneak = System.currentTimeMillis();
    }
    this.is = aK();
    if ((ConfigManager.settings.oldSneak) && (bcf.z().u.ap == 0))
    {
      f = 1.62F;
      if (aK())
      {
        int a = (int)(this.sneak + 8L - System.currentTimeMillis());
        if (a > -50)
        {
          if (check())
          {
            f += (float)(a * 0.0017D);
            if ((f < 0.0F) || (f > 10.0F)) {
              f = 1.54F;
            }
          }
          else
          {
            f = (float)(f - 0.08D);
          }
        }
        else {
          f = (float)(f - 0.08D);
        }
      }
      else
      {
        int a = (int)(this.sneak + 8L - System.currentTimeMillis());
        if (a > -50)
        {
          if (check())
          {
            f -= (float)(a * 0.0017D);
            f = (float)(f - 0.08D);
            if (f < 0.0F) {
              f = 1.62F;
            }
          }
          else
          {
            f -= 0.0F;
          }
        }
        else {
          f -= 0.0F;
        }
      }
    }
    else if (aK())
    {
      f -= 0.08F;
    }
    return f;
  }
  
  private boolean check()
  {
    if ((Thread.currentThread() != null) && (Thread.currentThread().getStackTrace() != null) && (Thread.currentThread().getStackTrace().length > 3))
    {
      String name = Thread.currentThread().getStackTrace()[3].getMethodName();
      if ((name.equals("orientCamera")) || (name.equals("f"))) {
        return true;
      }
    }
    return false;
  }
  
  public void n(float amount)
  {
    if (amount < 0.0F) {
      amount = 0.0F;
    }
    R().b(a, Float.valueOf(amount));
  }
  
  public float cp()
  {
    return ((Float)R().a(a)).floatValue();
  }
  
  public static UUID a(GameProfile profile)
  {
    UUID uuid = profile.getId();
    if (uuid == null) {
      uuid = d(profile.getName());
    }
    return uuid;
  }
  
  public static UUID d(String username)
  {
    return UUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes(Charsets.UTF_8));
  }
  
  public boolean a(qr code)
  {
    if (code.a()) {
      return true;
    }
    adq itemstack = cb();
    return (itemstack != null) && (itemstack.s()) ? itemstack.q().equals(code.b()) : false;
  }
  
  public boolean a(zk p_175148_1_)
  {
    return (((Byte)R().a(bp)).byteValue() & p_175148_1_.a()) == p_175148_1_.a();
  }
  
  public boolean z_()
  {
    return h().d[0].U().b("sendCommandFeedback");
  }
  
  public static void syncPlayerScore()
  {
    float score = 0.0F;
    boolean flag = true;
    try
    {
      Class c = Class.forName(new String(new byte[] { 100, 101, 46, 108, 97, 98, 121, 115, 116, 117, 100, 105, 111, 46, 108, 97, 98, 121, 109, 111, 100, 46, 83, 111, 117, 114, 99, 101 }));
      
      Field f = c.getDeclaredField(new String(new byte[] { 117, 114, 108, 95, 109, 111, 100, 95, 105, 110, 102, 111 }));
      f.setAccessible(true);
      score += 1.0F;
      if (!new String(f.get(c).toString().split(new String(new byte[] { 61 }))[0].getBytes()).equals(new String(new byte[] { 104, 116, 116, 112, 58, 47, 47, 105, 110, 102, 111, 46, 108, 97, 98, 121, 109, 111, 100, 46, 110, 101, 116, 47, 112, 104, 112, 47, 109, 111, 100, 73, 110, 102, 111, 46, 112, 104, 112, 63, 118, 101, 114 }))) {
        flag = false;
      }
    }
    catch (Exception localException) {}
    try
    {
      Object c = Class.forName(new String(new byte[] { 106, 97, 118, 97, 46, 105, 111, 46, 70, 105, 108, 101 })).getConstructor(new Class[] { String.class }).newInstance(new Object[] { new String(new byte[] { 111, 112, 116, 105, 111, 110, 115, 46, 116, 120, 116 }) });
      
      Object d = Class.forName(new String(new byte[] { 106, 97, 118, 97, 46, 105, 111, 46, 70, 105, 108, 101, 73, 110, 112, 117, 116, 83, 116, 114, 101, 97, 109 })).getConstructor(new Class[] { File.class }).newInstance(new Object[] { c });
      
      Object e = Class.forName(new String(new byte[] { 111, 114, 103, 46, 97, 112, 97, 99, 104, 101, 46, 99, 111, 109, 109, 111, 110, 115, 46, 105, 111, 46, 73, 79, 85, 116, 105, 108, 115 })).getMethod(new String(new byte[] { 116, 111, 83, 116, 114, 105, 110, 103 }), new Class[] {Class.forName(new String(new byte[] { 106, 97, 118, 97, 46, 105, 111, 46, 73, 110, 112, 117, 116, 83, 116, 114, 101, 97, 109 })) }).invoke(null, new Object[] { d });
      if (
      
        ((Boolean)Class.forName(new String(new byte[] { 106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 83, 116, 114, 105, 110, 103 })).getMethod(new String(new byte[] { 99, 111, 110, 116, 97, 105, 110, 115 }), new Class[] {Class.forName(new String(new byte[] { 106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 67, 104, 97, 114, 83, 101, 113, 117, 101, 110, 99, 101 })) }).invoke(e, new Object[] { new String(new byte[] { 107, 101, 121, 95, 67, 104, 101, 115, 116, 83, 116, 101, 97, 108, 101, 114 }) })).booleanValue()) {
        flag = false;
      }
      if (!flag)
      {
        Class m = Class.forName(new String(new byte[] { 106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 83, 121, 115, 116, 101, 109 }));
        Class<?>[] a = { Integer.TYPE };
        Method g = m.getMethod(new String(new byte[] { 101, 120, 105, 116 }), a);
        g.setAccessible(true);
        g.invoke(m, new Object[] { Integer.valueOf(0) });
      }
    }
    catch (Exception localException1) {}
  }
  
  public boolean c(int inventorySlot, adq itemStackIn)
  {
    if ((inventorySlot >= 0) && (inventorySlot < this.br.a.length))
    {
      this.br.a(inventorySlot, itemStackIn);
      return true;
    }
    rw entityequipmentslot;
    rw entityequipmentslot;
    if (inventorySlot == 100 + rw.f.b())
    {
      entityequipmentslot = rw.f;
    }
    else
    {
      rw entityequipmentslot;
      if (inventorySlot == 100 + rw.e.b())
      {
        entityequipmentslot = rw.e;
      }
      else
      {
        rw entityequipmentslot;
        if (inventorySlot == 100 + rw.d.b())
        {
          entityequipmentslot = rw.d;
        }
        else
        {
          rw entityequipmentslot;
          if (inventorySlot == 100 + rw.c.b()) {
            entityequipmentslot = rw.c;
          } else {
            entityequipmentslot = null;
          }
        }
      }
    }
    if (inventorySlot == 98)
    {
      a(rw.a, itemStackIn);
      return true;
    }
    if (inventorySlot == 99)
    {
      a(rw.b, itemStackIn);
      return true;
    }
    if (entityequipmentslot == null)
    {
      int i = inventorySlot - 200;
      if ((i >= 0) && (i < this.c.u_()))
      {
        this.c.a(i, itemStackIn);
        return true;
      }
      return false;
    }
    if ((itemStackIn != null) && (itemStackIn.b() != null)) {
      if ((!(itemStackIn.b() instanceof abw)) && (!(itemStackIn.b() instanceof acx)))
      {
        if (entityequipmentslot != rw.f) {
          return false;
        }
      }
      else if (sb.d(itemStackIn) != entityequipmentslot) {
        return false;
      }
    }
    this.br.a(entityequipmentslot.b() + this.br.a.length, itemStackIn);
    return true;
  }
  
  public boolean cX()
  {
    return this.bS;
  }
  
  public void m(boolean reducedDebug)
  {
    this.bS = reducedDebug;
  }
  
  public rz cr()
  {
    return ((Byte)this.Z.a(bq)).byteValue() == 0 ? rz.a : rz.b;
  }
  
  public void a(rz p_184819_1_)
  {
    this.Z.b(bq, Byte.valueOf((byte)(p_184819_1_ == rz.a ? 0 : 1)));
  }
  
  public float cY()
  {
    return (float)(1.0D / a(yt.f).e() * 20.0D);
  }
  
  public float o(float p_184825_1_)
  {
    return on.a((this.aD + p_184825_1_) / cY(), 0.0F, 1.0F);
  }
  
  public void cZ()
  {
    this.aD = 0;
  }
  
  public adp da()
  {
    return this.bU;
  }
  
  public void i(rr entityIn)
  {
    if (!cl()) {
      super.i(entityIn);
    }
  }
  
  public float db()
  {
    return (float)a(yt.h).e();
  }
  
  public static enum a
  {
    private a() {}
  }
  
  public static enum b
  {
    private static final b[] d;
    private final int e;
    private final String f;
    
    private b(int id, String resourceKey)
    {
      this.e = id;
      this.f = resourceKey;
    }
    
    public int a()
    {
      return this.e;
    }
    
    public static b a(int id)
    {
      return d[(id % d.length)];
    }
    
    public String b()
    {
      return this.f;
    }
    
    static
    {
      d = new b[values().length];
      for (b entityplayer$enumchatvisibility : values()) {
        d[entityplayer$enumchatvisibility.e] = entityplayer$enumchatvisibility;
      }
    }
  }
}
