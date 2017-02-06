import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import de.labystudio.utils.DualHand;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public abstract class sa
  extends rr
{
  private static final UUID a = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
  private static final sn b = new sn(a, "Sprinting speed boost", 0.30000001192092896D, 2).a(false);
  protected static final ke<Byte> as = kh.a(sa.class, kg.a);
  private static final ke<Float> c = kh.a(sa.class, kg.c);
  private static final ke<Integer> f = kh.a(sa.class, kg.b);
  private static final ke<Boolean> g = kh.a(sa.class, kg.h);
  private static final ke<Integer> h = kh.a(sa.class, kg.b);
  private sp bp;
  private final rb bq = new rb(this);
  private final Map<rk, rl> br = Maps.newHashMap();
  private final adq[] bs = new adq[2];
  private final adq[] bt = new adq[4];
  public boolean at;
  public qm au;
  public int av;
  public int aw;
  public int ax;
  public int ay;
  public float az;
  public int aA;
  public float aB;
  public float aC;
  protected int aD;
  public float aE;
  public float aF;
  public float aG;
  public int aH = 20;
  public float aI;
  public float aJ;
  public float aK;
  public float aL;
  public float aM;
  public float aN;
  public float aO;
  public float aP;
  public float aQ = 0.02F;
  protected zj aR;
  protected int aS;
  protected boolean aT;
  protected int aU;
  protected float aV;
  protected float aW;
  protected float aX;
  protected float aY;
  protected float aZ;
  protected int ba;
  protected float bb;
  protected boolean bc;
  public float bd;
  public float be;
  public float bf;
  protected int bg;
  protected double bh;
  protected double bi;
  protected double bj;
  protected double bk;
  protected double bl;
  private boolean bu = true;
  private sa bv;
  private int bw;
  private sa bx;
  private int by;
  private float bz;
  private int bA;
  private float bB;
  protected adq bm;
  protected int bn;
  protected int bo;
  private cj bC;
  
  public void Q()
  {
    a(rc.k, Float.MAX_VALUE);
  }
  
  public sa(aht worldIn)
  {
    super(worldIn);
    bA();
    c(bW());
    this.i = true;
    this.aL = ((float)((Math.random() + 1.0D) * 0.009999999776482582D));
    b(this.p, this.q, this.r);
    this.aK = ((float)Math.random() * 12398.0F);
    this.v = ((float)(Math.random() * 6.283185307179586D));
    this.aO = this.v;
    this.P = 0.6F;
  }
  
  protected void i()
  {
    this.Z.a(as, Byte.valueOf((byte)0));
    this.Z.a(f, Integer.valueOf(0));
    this.Z.a(g, Boolean.valueOf(false));
    this.Z.a(h, Integer.valueOf(0));
    this.Z.a(c, Float.valueOf(1.0F));
  }
  
  protected void bA()
  {
    bZ().b(yt.a);
    bZ().b(yt.c);
    bZ().b(yt.d);
    bZ().b(yt.g);
  }
  
  protected void a(double y, boolean onGroundIn, arc state, cj pos)
  {
    if (!ai()) {
      aj();
    }
    if ((!this.l.E) && (this.L > 3.0F) && (onGroundIn))
    {
      float f = on.f(this.L - 3.0F);
      if (state.a() != axe.a)
      {
        double d0 = Math.min(0.2F + f / 15.0F, 2.5D);
        int i = (int)(150.0D * d0);
        ((lp)this.l).a(cy.M, this.p, this.q, this.r, i, 0.0D, 0.0D, 0.0D, 0.15000000596046448D, new int[] { ajt.j(state) });
      }
    }
    super.a(y, onGroundIn, state, pos);
  }
  
  public boolean bB()
  {
    return false;
  }
  
  public void U()
  {
    this.aB = this.aC;
    super.U();
    this.l.C.a("livingEntityBaseTick");
    boolean flag = this instanceof zj;
    if (au()) {
      if (av())
      {
        a(rc.e, 1.0F);
      }
      else if ((flag) && (!this.l.aj().a(bl())))
      {
        double d0 = this.l.aj().a(this) + this.l.aj().m();
        if (d0 < 0.0D) {
          a(rc.e, Math.max(1, on.c(-d0 * this.l.aj().n())));
        }
      }
    }
    if ((ag()) || (this.l.E)) {
      X();
    }
    boolean flag1 = (flag) && (((zj)this).bJ.a);
    if (au())
    {
      if (!a(axe.h))
      {
        j(300);
      }
      else
      {
        if ((!bB()) && (!a(rm.m)) && (!flag1))
        {
          j(d(aP()));
          if (aP() == -20)
          {
            j(0);
            for (int i = 0; i < 8; i++)
            {
              float f = this.S.nextFloat() - this.S.nextFloat();
              float f1 = this.S.nextFloat() - this.S.nextFloat();
              float f2 = this.S.nextFloat() - this.S.nextFloat();
              this.l.a(cy.e, this.p + f, this.q + f1, this.r + f2, this.s, this.t, this.u, new int[0]);
            }
            a(rc.f, 2.0F);
          }
        }
        if ((!this.l.E) && (aI()) && ((by() instanceof sa))) {
          p();
        }
      }
      if (!this.l.E)
      {
        cj blockpos = new cj(this);
        if (!Objects.equal(this.bC, blockpos))
        {
          this.bC = blockpos;
          b(blockpos);
        }
      }
    }
    if ((au()) && (ah())) {
      X();
    }
    this.aI = this.aJ;
    if (this.ax > 0) {
      this.ax -= 1;
    }
    if ((this.W > 0) && (!(this instanceof lr))) {
      this.W -= 1;
    }
    if (bQ() <= 0.0F) {
      bC();
    }
    if (this.aS > 0) {
      this.aS -= 1;
    } else {
      this.aR = null;
    }
    if ((this.bx != null) && (!this.bx.au())) {
      this.bx = null;
    }
    if (this.bv != null) {
      if (!this.bv.au()) {
        a((sa)null);
      } else if (this.T - this.bw > 100) {
        a((sa)null);
      }
    }
    bL();
    this.aY = this.aX;
    this.aN = this.aM;
    this.aP = this.aO;
    this.x = this.v;
    this.y = this.w;
    this.l.C.b();
  }
  
  protected void b(cj p_184594_1_)
  {
    int i = ago.a(agq.j, this);
    if (i > 0) {
      agt.a(this, this.l, p_184594_1_, i);
    }
  }
  
  public boolean m_()
  {
    return false;
  }
  
  protected void bC()
  {
    this.aA += 1;
    if (this.aA == 20)
    {
      if ((!this.l.E) && ((bE()) || ((this.aS > 0) && (bD()) && (this.l.U().b("doMobLoot")))))
      {
        int i = b(this.aR);
        while (i > 0)
        {
          int j = rx.a(i);
          i -= j;
          this.l.a(new rx(this.l, this.p, this.q, this.r, j));
        }
      }
      T();
      for (int k = 0; k < 20; k++)
      {
        double d2 = this.S.nextGaussian() * 0.02D;
        double d0 = this.S.nextGaussian() * 0.02D;
        double d1 = this.S.nextGaussian() * 0.02D;
        this.l.a(cy.a, this.p + this.S.nextFloat() * this.G * 2.0F - this.G, this.q + this.S.nextFloat() * this.H, this.r + this.S.nextFloat() * this.G * 2.0F - this.G, d2, d0, d1, new int[0]);
      }
    }
  }
  
  protected boolean bD()
  {
    return !m_();
  }
  
  protected int d(int p_70682_1_)
  {
    int i = ago.c(this);
    return (i > 0) && (this.S.nextInt(i + 1) > 0) ? p_70682_1_ : p_70682_1_ - 1;
  }
  
  protected int b(zj player)
  {
    return 0;
  }
  
  protected boolean bE()
  {
    return false;
  }
  
  public Random bF()
  {
    return this.S;
  }
  
  public sa bG()
  {
    return this.bv;
  }
  
  public int bH()
  {
    return this.bw;
  }
  
  public void a(sa livingBase)
  {
    this.bv = livingBase;
    this.bw = this.T;
  }
  
  public sa bI()
  {
    return this.bx;
  }
  
  public int bJ()
  {
    return this.by;
  }
  
  public void z(rr entityIn)
  {
    if ((entityIn instanceof sa)) {
      this.bx = ((sa)entityIn);
    } else {
      this.bx = null;
    }
    this.by = this.T;
  }
  
  public int bK()
  {
    return this.aU;
  }
  
  protected void a_(adq p_184606_1_)
  {
    if (p_184606_1_ != null)
    {
      nf soundevent = ng.p;
      ado item = p_184606_1_.b();
      if ((item instanceof abw)) {
        soundevent = ((abw)item).d().b();
      } else if (item == ads.cR) {
        soundevent = ng.s;
      }
      a(soundevent, 1.0F, 1.0F);
    }
  }
  
  public void b(dn tagCompound)
  {
    tagCompound.a("Health", bQ());
    tagCompound.a("HurtTime", (short)this.ax);
    tagCompound.a("HurtByTimestamp", this.bw);
    tagCompound.a("DeathTime", (short)this.aA);
    tagCompound.a("AbsorptionAmount", cp());
    for (rw entityequipmentslot : rw.values())
    {
      adq itemstack = a(entityequipmentslot);
      if (itemstack != null) {
        bZ().a(itemstack.a(entityequipmentslot));
      }
    }
    tagCompound.a("Attributes", yt.a(bZ()));
    for (rw entityequipmentslot1 : rw.values())
    {
      adq itemstack1 = a(entityequipmentslot1);
      if (itemstack1 != null) {
        bZ().b(itemstack1.a(entityequipmentslot1));
      }
    }
    if (!this.br.isEmpty())
    {
      du nbttaglist = new du();
      for (rl potioneffect : this.br.values()) {
        nbttaglist.a(potioneffect.a(new dn()));
      }
      tagCompound.a("ActiveEffects", nbttaglist);
    }
  }
  
  public void a(dn tagCompund)
  {
    n(tagCompund.j("AbsorptionAmount"));
    if ((tagCompund.b("Attributes", 9)) && (this.l != null) && (!this.l.E)) {
      yt.a(bZ(), tagCompund.c("Attributes", 10));
    }
    if (tagCompund.b("ActiveEffects", 9))
    {
      du nbttaglist = tagCompund.c("ActiveEffects", 10);
      for (int i = 0; i < nbttaglist.c(); i++)
      {
        dn nbttagcompound = nbttaglist.b(i);
        rl potioneffect = rl.b(nbttagcompound);
        if (potioneffect != null) {
          this.br.put(potioneffect.a(), potioneffect);
        }
      }
    }
    if (tagCompund.b("Health", 99)) {
      c(tagCompund.j("Health"));
    }
    this.ax = tagCompund.g("HurtTime");
    this.aA = tagCompund.g("DeathTime");
    this.bw = tagCompund.h("HurtByTimestamp");
    if (tagCompund.b("Team", 8))
    {
      String s = tagCompund.l("Team");
      this.l.ad().a(bc().toString(), s);
    }
  }
  
  protected void bL()
  {
    Iterator<rk> iterator = this.br.keySet().iterator();
    while (iterator.hasNext())
    {
      rk potion = (rk)iterator.next();
      rl potioneffect = (rl)this.br.get(potion);
      if (!potioneffect.a(this))
      {
        if (!this.l.E)
        {
          iterator.remove();
          b(potioneffect);
        }
      }
      else if (potioneffect.b() % 600 == 0) {
        a(potioneffect, false);
      }
    }
    if (this.bu)
    {
      if (!this.l.E) {
        F();
      }
      this.bu = false;
    }
    int i = ((Integer)this.Z.a(f)).intValue();
    boolean flag1 = ((Boolean)this.Z.a(g)).booleanValue();
    if (i > 0)
    {
      boolean flag = false;
      if (!aN()) {
        flag = this.S.nextBoolean();
      } else {
        flag = this.S.nextInt(15) == 0;
      }
      if (flag1) {
        flag &= this.S.nextInt(5) == 0;
      }
      if ((flag) && (i > 0))
      {
        double d0 = (i >> 16 & 0xFF) / 255.0D;
        double d1 = (i >> 8 & 0xFF) / 255.0D;
        double d2 = (i >> 0 & 0xFF) / 255.0D;
        this.l.a(flag1 ? cy.q : cy.p, this.p + (this.S.nextDouble() - 0.5D) * this.G, this.q + this.S.nextDouble() * this.H, this.r + (this.S.nextDouble() - 0.5D) * this.G, d0, d1, d2, new int[0]);
      }
    }
  }
  
  protected void F()
  {
    if (this.br.isEmpty())
    {
      bM();
      g(false);
    }
    else
    {
      Collection<rl> collection = this.br.values();
      this.Z.b(g, Boolean.valueOf(a(collection)));
      this.Z.b(f, Integer.valueOf(afg.a(collection)));
      g(a(rm.n));
    }
  }
  
  public static boolean a(Collection<rl> p_184593_0_)
  {
    for (rl potioneffect : p_184593_0_) {
      if (!potioneffect.d()) {
        return false;
      }
    }
    return true;
  }
  
  protected void bM()
  {
    this.Z.b(g, Boolean.valueOf(false));
    this.Z.b(f, Integer.valueOf(0));
  }
  
  public void bN()
  {
    if (!this.l.E)
    {
      Iterator<rl> iterator = this.br.values().iterator();
      while (iterator.hasNext())
      {
        b((rl)iterator.next());
        iterator.remove();
      }
    }
  }
  
  public Collection<rl> bO()
  {
    return this.br.values();
  }
  
  public boolean a(rk potionIn)
  {
    return this.br.containsKey(potionIn);
  }
  
  public rl b(rk potionIn)
  {
    return (rl)this.br.get(potionIn);
  }
  
  public void c(rl potioneffectIn)
  {
    if (d(potioneffectIn))
    {
      rl potioneffect = (rl)this.br.get(potioneffectIn.a());
      if (potioneffect == null)
      {
        this.br.put(potioneffectIn.a(), potioneffectIn);
        a(potioneffectIn);
      }
      else
      {
        potioneffect.a(potioneffectIn);
        a(potioneffect, true);
      }
    }
  }
  
  public boolean d(rl potioneffectIn)
  {
    if (ca() == sf.b)
    {
      rk potion = potioneffectIn.a();
      if ((potion == rm.j) || (potion == rm.s)) {
        return false;
      }
    }
    return true;
  }
  
  public boolean bP()
  {
    return ca() == sf.b;
  }
  
  public rl c(rk p_184596_1_)
  {
    return (rl)this.br.remove(p_184596_1_);
  }
  
  public void d(rk p_184589_1_)
  {
    rl potioneffect = c(p_184589_1_);
    if (potioneffect != null) {
      b(potioneffect);
    }
  }
  
  protected void a(rl id)
  {
    this.bu = true;
    if (!this.l.E) {
      id.a().b(this, bZ(), id.c());
    }
  }
  
  protected void a(rl id, boolean p_70695_2_)
  {
    this.bu = true;
    if ((p_70695_2_) && (!this.l.E))
    {
      rk potion = id.a();
      potion.a(this, bZ(), id.c());
      potion.b(this, bZ(), id.c());
    }
  }
  
  protected void b(rl effect)
  {
    this.bu = true;
    if (!this.l.E) {
      effect.a().a(this, bZ(), effect.c());
    }
  }
  
  public void b(float healAmount)
  {
    float f = bQ();
    if (f > 0.0F) {
      c(f + healAmount);
    }
  }
  
  public final float bQ()
  {
    return ((Float)this.Z.a(c)).floatValue();
  }
  
  public void c(float health)
  {
    this.Z.b(c, Float.valueOf(on.a(health, 0.0F, bW())));
  }
  
  public boolean a(rc source, float amount)
  {
    if (b(source)) {
      return false;
    }
    if (this.l.E) {
      return false;
    }
    this.aU = 0;
    if (bQ() <= 0.0F) {
      return false;
    }
    if ((source.o()) && (a(rm.l))) {
      return false;
    }
    if (((source == rc.o) || (source == rc.p)) && (a(rw.f) != null))
    {
      a(rw.f).a((int)(amount * 4.0F + this.S.nextFloat() * amount * 2.0F), this);
      amount *= 0.75F;
    }
    boolean flag = false;
    if ((amount > 0.0F) && (d(source)))
    {
      k(amount);
      if (source.a())
      {
        amount = 0.0F;
      }
      else
      {
        amount *= 0.33F;
        if ((source.i() instanceof sa)) {
          ((sa)source.i()).a(this, 0.5F, this.p - source.i().p, this.r - source.i().r);
        }
      }
      flag = true;
    }
    this.aF = 1.5F;
    boolean flag1 = true;
    if (this.W > this.aH / 2.0F)
    {
      if (amount <= this.bb) {
        return false;
      }
      d(source, amount - this.bb);
      this.bb = amount;
      flag1 = false;
    }
    else
    {
      this.bb = amount;
      this.W = this.aH;
      d(source, amount);
      this.ax = (this.ay = 10);
    }
    this.az = 0.0F;
    rr entity = source.j();
    if (entity != null)
    {
      if ((entity instanceof sa)) {
        a((sa)entity);
      }
      if ((entity instanceof zj))
      {
        this.aS = 100;
        this.aR = ((zj)entity);
      }
      else if ((entity instanceof wj))
      {
        wj entitywolf = (wj)entity;
        if (entitywolf.cZ())
        {
          this.aS = 100;
          this.aR = null;
        }
      }
    }
    if (flag1)
    {
      if (flag) {
        this.l.a(this, (byte)29);
      } else if (((source instanceof rd)) && (((rd)source).x())) {
        this.l.a(this, (byte)33);
      } else {
        this.l.a(this, (byte)2);
      }
      if ((source != rc.f) && ((!flag) || (amount > 0.0F))) {
        ao();
      }
      if (entity != null)
      {
        double d1 = entity.p - this.p;
        for (double d0 = entity.r - this.r; d1 * d1 + d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D) {
          d1 = (Math.random() - Math.random()) * 0.01D;
        }
        this.az = ((float)(on.b(d0, d1) * 57.29577951308232D - this.v));
        a(entity, 0.4F, d1, d0);
      }
      else
      {
        this.az = ((int)(Math.random() * 2.0D) * 180);
      }
    }
    if (bQ() <= 0.0F)
    {
      nf soundevent = bS();
      if ((flag1) && (soundevent != null)) {
        a(soundevent, cd(), ce());
      }
      a(source);
    }
    else if (flag1)
    {
      c(source);
    }
    return (!flag) || (amount > 0.0F);
  }
  
  protected void c(rc p_184581_1_)
  {
    nf soundevent = bR();
    if (soundevent != null) {
      a(soundevent, cd(), ce());
    }
  }
  
  private boolean d(rc p_184583_1_)
  {
    if ((!p_184583_1_.e()) && (cA()))
    {
      bbj vec3d = p_184583_1_.v();
      if (vec3d != null)
      {
        bbj vec3d1 = f(1.0F);
        bbj vec3d2 = vec3d.a(new bbj(this.p, this.q, this.r)).a();
        vec3d2 = new bbj(vec3d2.b, 0.0D, vec3d2.d);
        if (vec3d2.b(vec3d1) < 0.0D) {
          return true;
        }
      }
    }
    return false;
  }
  
  public void b(adq stack)
  {
    a(ng.cT, 0.8F, 0.8F + this.l.r.nextFloat() * 0.4F);
    for (int i = 0; i < 5; i++)
    {
      bbj vec3d = new bbj((this.S.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
      vec3d = vec3d.a(-this.w * 0.017453292F);
      vec3d = vec3d.b(-this.v * 0.017453292F);
      double d0 = -this.S.nextFloat() * 0.6D - 0.3D;
      bbj vec3d1 = new bbj((this.S.nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
      vec3d1 = vec3d1.a(-this.w * 0.017453292F);
      vec3d1 = vec3d1.b(-this.v * 0.017453292F);
      vec3d1 = vec3d1.b(this.p, this.q + bn(), this.r);
      this.l.a(cy.K, vec3d1.b, vec3d1.c, vec3d1.d, vec3d.b, vec3d.c + 0.05D, vec3d.d, new int[] { ado.a(stack.b()) });
    }
  }
  
  public void a(rc cause)
  {
    if (!this.aT)
    {
      rr entity = cause.j();
      sa entitylivingbase = bV();
      if ((this.ba >= 0) && (entitylivingbase != null)) {
        entitylivingbase.b(this, this.ba);
      }
      if (entity != null) {
        entity.b(this);
      }
      this.aT = true;
      bU().g();
      if (!this.l.E)
      {
        int i = 0;
        if ((entity instanceof zj)) {
          i = ago.h((sa)entity);
        }
        if ((bD()) && (this.l.U().b("doMobLoot")))
        {
          boolean flag = this.aS > 0;
          a(flag, i, cause);
        }
      }
      this.l.a(this, (byte)3);
    }
  }
  
  protected void a(boolean p_184610_1_, int p_184610_2_, rc source)
  {
    b(p_184610_1_, p_184610_2_);
    a(p_184610_1_, p_184610_2_);
  }
  
  protected void a(boolean wasRecentlyHit, int lootingModifier) {}
  
  public void a(rr entityIn, float p_70653_2_, double p_70653_3_, double p_70653_5_)
  {
    if (this.S.nextDouble() >= a(yt.c).e())
    {
      this.ai = true;
      float f = on.a(p_70653_3_ * p_70653_3_ + p_70653_5_ * p_70653_5_);
      this.s /= 2.0D;
      this.u /= 2.0D;
      this.s -= p_70653_3_ / f * p_70653_2_;
      this.u -= p_70653_5_ / f * p_70653_2_;
      if (this.z)
      {
        this.t /= 2.0D;
        this.t += p_70653_2_;
        if (this.t > 0.4000000059604645D) {
          this.t = 0.4000000059604645D;
        }
      }
    }
  }
  
  protected nf bR()
  {
    return ng.bF;
  }
  
  protected nf bS()
  {
    return ng.bA;
  }
  
  protected nf e(int heightIn)
  {
    return heightIn > 4 ? ng.by : ng.bG;
  }
  
  protected void b(boolean wasRecentlyHit, int lootingModifier) {}
  
  public boolean n_()
  {
    int i = on.c(this.p);
    int j = on.c(bl().b);
    int k = on.c(this.r);
    if (((this instanceof zj)) && (((zj)this).y())) {
      return false;
    }
    cj blockpos = new cj(i, j, k);
    arc iblockstate = this.l.o(blockpos);
    ajt block = iblockstate.t();
    return ((block instanceof apg)) && (a(blockpos, iblockstate));
  }
  
  private boolean a(cj p_184604_1_, arc p_184604_2_)
  {
    if (((Boolean)p_184604_2_.c(apg.b)).booleanValue())
    {
      arc iblockstate = this.l.o(p_184604_1_.b());
      if ((iblockstate.t() == aju.au) && (iblockstate.c(amk.a) == p_184604_2_.c(apg.a))) {
        return true;
      }
    }
    return false;
  }
  
  public boolean au()
  {
    return (!this.F) && (bQ() > 0.0F);
  }
  
  public void e(float distance, float damageMultiplier)
  {
    super.e(distance, damageMultiplier);
    rl potioneffect = b(rm.h);
    float f = potioneffect == null ? 0.0F : potioneffect.c() + 1;
    int i = on.f((distance - 3.0F - f) * damageMultiplier);
    if (i > 0)
    {
      a(e(i), 1.0F, 1.0F);
      a(rc.i, i);
      int j = on.c(this.p);
      int k = on.c(this.q - 0.20000000298023224D);
      int l = on.c(this.r);
      arc iblockstate = this.l.o(new cj(j, k, l));
      if (iblockstate.a() != axe.a)
      {
        aop soundtype = iblockstate.t().w();
        a(soundtype.g(), soundtype.a() * 0.5F, soundtype.b() * 0.75F);
      }
    }
  }
  
  public void aD()
  {
    this.ax = (this.ay = 10);
    this.az = 0.0F;
  }
  
  public int bT()
  {
    sm iattributeinstance = a(yt.g);
    return on.c(iattributeinstance.e());
  }
  
  protected void j(float p_70675_1_) {}
  
  protected void k(float p_184590_1_) {}
  
  protected float b(rc source, float damage)
  {
    if (!source.e())
    {
      j(damage);
      damage = ra.a(damage, bT());
    }
    return damage;
  }
  
  protected float c(rc source, float damage)
  {
    if (source.h()) {
      return damage;
    }
    if ((a(rm.k)) && (source != rc.k))
    {
      int i = (b(rm.k).c() + 1) * 5;
      int j = 25 - i;
      float f = damage * j;
      damage = f / 25.0F;
    }
    if (damage <= 0.0F) {
      return 0.0F;
    }
    int k = ago.a(aF(), source);
    if (k > 0) {
      damage = ra.b(damage, k);
    }
    return damage;
  }
  
  protected void d(rc damageSrc, float damageAmount)
  {
    if (!b(damageSrc))
    {
      damageAmount = b(damageSrc, damageAmount);
      damageAmount = c(damageSrc, damageAmount);
      float f = damageAmount;
      damageAmount = Math.max(damageAmount - cp(), 0.0F);
      n(cp() - (f - damageAmount));
      if (damageAmount != 0.0F)
      {
        float f1 = bQ();
        c(f1 - damageAmount);
        bU().a(damageSrc, f1, damageAmount);
        n(cp() - damageAmount);
      }
    }
  }
  
  public rb bU()
  {
    return this.bq;
  }
  
  public sa bV()
  {
    return this.bv != null ? this.bv : this.aR != null ? this.aR : this.bq.c() != null ? this.bq.c() : null;
  }
  
  public final float bW()
  {
    return (float)a(yt.a).e();
  }
  
  public final int bX()
  {
    return ((Integer)this.Z.a(h)).intValue();
  }
  
  public final void k(int count)
  {
    this.Z.b(h, Integer.valueOf(count));
  }
  
  private int o()
  {
    return a(rm.d) ? 6 + (1 + b(rm.d).c()) * 2 : a(rm.c) ? 6 - (1 + b(rm.c).c()) : 6;
  }
  
  public void a(qm hand)
  {
    if ((!this.at) || (this.av >= o() / 2) || (this.av < 0))
    {
      this.av = -1;
      this.at = true;
      this.au = hand;
      if ((this.l instanceof lp)) {
        ((lp)this.l).v().a(this, new fp(this, hand == qm.a ? 0 : 3));
      }
    }
  }
  
  public void a(byte id)
  {
    boolean flag = id == 33;
    if ((id != 2) && (!flag))
    {
      if (id == 3)
      {
        nf soundevent1 = bS();
        if (soundevent1 != null) {
          a(soundevent1, cd(), (this.S.nextFloat() - this.S.nextFloat()) * 0.2F + 1.0F);
        }
        c(0.0F);
        a(rc.l);
      }
      else if (id == 30)
      {
        a(ng.eR, 0.8F, 0.8F + this.l.r.nextFloat() * 0.4F);
      }
      else if (id == 29)
      {
        a(ng.eQ, 1.0F, 0.8F + this.l.r.nextFloat() * 0.4F);
      }
      else
      {
        super.a(id);
      }
    }
    else
    {
      this.aF = 1.5F;
      this.W = this.aH;
      this.ax = (this.ay = 10);
      this.az = 0.0F;
      if (flag) {
        a(ng.gi, cd(), (this.S.nextFloat() - this.S.nextFloat()) * 0.2F + 1.0F);
      }
      nf soundevent = bR();
      if (soundevent != null) {
        a(soundevent, cd(), (this.S.nextFloat() - this.S.nextFloat()) * 0.2F + 1.0F);
      }
      a(rc.l, 0.0F);
    }
  }
  
  protected void Y()
  {
    a(rc.k, 4.0F);
  }
  
  protected void bY()
  {
    int i = o();
    if (this.at)
    {
      this.av += 1;
      if (this.av >= i)
      {
        this.av = 0;
        this.at = false;
      }
    }
    else
    {
      this.av = 0;
    }
    this.aC = (this.av / i);
  }
  
  public sm a(sl attribute)
  {
    return bZ().a(attribute);
  }
  
  public sp bZ()
  {
    if (this.bp == null) {
      this.bp = new sr();
    }
    return this.bp;
  }
  
  public sf ca()
  {
    return sf.a;
  }
  
  public adq cb()
  {
    return a(rw.a);
  }
  
  public adq cc()
  {
    return a(rw.b);
  }
  
  public adq b(qm hand)
  {
    if (hand == qm.a) {
      return a(rw.a);
    }
    if (hand == qm.b) {
      return a(rw.b);
    }
    throw new IllegalArgumentException("Invalid hand " + hand);
  }
  
  public void a(qm hand, adq stack)
  {
    if (hand == qm.a)
    {
      a(rw.a, stack);
    }
    else
    {
      if (hand != qm.b) {
        throw new IllegalArgumentException("Invalid hand " + hand);
      }
      a(rw.b, stack);
    }
  }
  
  public abstract Iterable<adq> aF();
  
  public abstract adq a(rw paramrw);
  
  public abstract void a(rw paramrw, adq paramadq);
  
  public void e(boolean sprinting)
  {
    super.e(sprinting);
    sm iattributeinstance = a(yt.d);
    if (iattributeinstance.a(a) != null) {
      iattributeinstance.c(b);
    }
    if (sprinting) {
      iattributeinstance.b(b);
    }
  }
  
  protected float cd()
  {
    return 1.0F;
  }
  
  protected float ce()
  {
    return m_() ? (this.S.nextFloat() - this.S.nextFloat()) * 0.2F + 1.5F : (this.S.nextFloat() - this.S.nextFloat()) * 0.2F + 1.0F;
  }
  
  protected boolean cf()
  {
    return bQ() <= 0.0F;
  }
  
  public void A(rr entityIn)
  {
    if ((!(entityIn instanceof aag)) && (!(entityIn instanceof wk)))
    {
      double d1 = entityIn.p;
      double d13 = entityIn.bl().b + entityIn.H;
      double d14 = entityIn.r;
      cq enumfacing1 = entityIn.bj();
      cq enumfacing = enumfacing1.e();
      int[][] aint1 = { { 0, 1 }, { 0, -1 }, { -1, 1 }, { -1, -1 }, { 1, 1 }, { 1, -1 }, { -1, 0 }, { 1, 0 }, { 0, 1 } };
      double d5 = Math.floor(this.p) + 0.5D;
      double d6 = Math.floor(this.r) + 0.5D;
      double d7 = bl().d - bl().a;
      double d8 = bl().f - bl().c;
      bbh axisalignedbb = new bbh(d5 - d7 / 2.0D, bl().b, d6 - d8 / 2.0D, d5 + d7 / 2.0D, bl().e, d6 + d8 / 2.0D);
      for (int[] aint : aint1)
      {
        double d9 = enumfacing1.g() * aint[0] + enumfacing.g() * aint[1];
        double d10 = enumfacing1.i() * aint[0] + enumfacing.i() * aint[1];
        double d11 = d5 + d9;
        double d12 = d6 + d10;
        bbh axisalignedbb1 = axisalignedbb.c(d9, 1.0D, d10);
        if (!this.l.b(axisalignedbb1))
        {
          if (this.l.o(new cj(d11, this.q, d12)).q())
          {
            a(d11, this.q + 1.0D, d12);
            return;
          }
          cj blockpos = new cj(d11, this.q - 1.0D, d12);
          if ((this.l.o(blockpos).q()) || (this.l.o(blockpos).a() == axe.h))
          {
            d1 = d11;
            d13 = this.q + 1.0D;
            d14 = d12;
          }
        }
        else if ((!this.l.b(axisalignedbb1.c(0.0D, 1.0D, 0.0D))) && (this.l.o(new cj(d11, this.q + 1.0D, d12)).q()))
        {
          d1 = d11;
          d13 = this.q + 2.0D;
          d14 = d12;
        }
      }
      a(d1, d13, d14);
    }
    else
    {
      double d0 = this.G / 2.0F + entityIn.G / 2.0F + 0.4D;
      float f;
      float f;
      if ((entityIn instanceof aag)) {
        f = 0.0F;
      } else {
        f = 1.5707964F * (cr() == rz.b ? -1 : 1);
      }
      float f1 = -on.a(-this.v * 0.017453292F - 3.1415927F + f);
      float f2 = -on.b(-this.v * 0.017453292F - 3.1415927F + f);
      double d2 = Math.abs(f1) > Math.abs(f2) ? d0 / Math.abs(f1) : d0 / Math.abs(f2);
      double d3 = this.p + f1 * d2;
      double d4 = this.r + f2 * d2;
      b(d3, entityIn.q + entityIn.H + 0.001D, d4);
      if (this.l.b(bl()))
      {
        b(d3, entityIn.q + entityIn.H + 1.001D, d4);
        if (this.l.b(bl())) {
          b(entityIn.p, entityIn.q + this.H + 0.001D, entityIn.r);
        }
      }
    }
  }
  
  public boolean bh()
  {
    return bg();
  }
  
  protected float cg()
  {
    return 0.42F;
  }
  
  protected void ch()
  {
    this.t = cg();
    if (a(rm.h)) {
      this.t += (b(rm.h).c() + 1) * 0.1F;
    }
    if (aL())
    {
      float f = this.v * 0.017453292F;
      this.s -= on.a(f) * 0.2F;
      this.u += on.b(f) * 0.2F;
    }
    this.ai = true;
  }
  
  protected void ci()
  {
    this.t += 0.03999999910593033D;
  }
  
  protected void cj()
  {
    this.t += 0.03999999910593033D;
  }
  
  public void g(float strafe, float forward)
  {
    if ((co()) || (bx())) {
      if ((!ai()) || (((this instanceof zj)) && (((zj)this).bJ.b)))
      {
        if ((!an()) || (((this instanceof zj)) && (((zj)this).bJ.b)))
        {
          if (cB())
          {
            if (this.t > -0.5D) {
              this.L = 1.0F;
            }
            bbj vec3d = aB();
            float f = this.w * 0.017453292F;
            double d6 = Math.sqrt(vec3d.b * vec3d.b + vec3d.d * vec3d.d);
            double d8 = Math.sqrt(this.s * this.s + this.u * this.u);
            double d1 = vec3d.b();
            float f4 = on.b(f);
            f4 = (float)(f4 * f4 * Math.min(1.0D, d1 / 0.4D));
            this.t += -0.08D + f4 * 0.06D;
            if ((this.t < 0.0D) && (d6 > 0.0D))
            {
              double d2 = this.t * -0.1D * f4;
              this.t += d2;
              this.s += vec3d.b * d2 / d6;
              this.u += vec3d.d * d2 / d6;
            }
            if (f < 0.0F)
            {
              double d9 = d8 * -on.a(f) * 0.04D;
              this.t += d9 * 3.2D;
              this.s -= vec3d.b * d9 / d6;
              this.u -= vec3d.d * d9 / d6;
            }
            if (d6 > 0.0D)
            {
              this.s += (vec3d.b / d6 * d8 - this.s) * 0.1D;
              this.u += (vec3d.d / d6 * d8 - this.u) * 0.1D;
            }
            this.s *= 0.9900000095367432D;
            this.t *= 0.9800000190734863D;
            this.u *= 0.9900000095367432D;
            d(this.s, this.t, this.u);
            if ((this.A) && (!this.l.E))
            {
              double d10 = Math.sqrt(this.s * this.s + this.u * this.u);
              double d3 = d8 - d10;
              float f5 = (float)(d3 * 10.0D - 3.0D);
              if (f5 > 0.0F)
              {
                a(e((int)f5), 1.0F, 1.0F);
                a(rc.j, f5);
              }
            }
            if ((this.z) && (!this.l.E)) {
              b(7, false);
            }
          }
          else
          {
            float f6 = 0.91F;
            cj.b blockpos$pooledmutableblockpos = cj.b.c(this.p, bl().b - 1.0D, this.r);
            if (this.z) {
              f6 = this.l.o(blockpos$pooledmutableblockpos).t().z * 0.91F;
            }
            float f7 = 0.16277136F / (f6 * f6 * f6);
            float f8;
            float f8;
            if (this.z) {
              f8 = ck() * f7;
            } else {
              f8 = this.aQ;
            }
            a(strafe, forward, f8);
            f6 = 0.91F;
            if (this.z) {
              f6 = this.l.o(blockpos$pooledmutableblockpos.d(this.p, bl().b - 1.0D, this.r)).t().z * 0.91F;
            }
            if (n_())
            {
              float f9 = 0.15F;
              this.s = on.a(this.s, -f9, f9);
              this.u = on.a(this.u, -f9, f9);
              this.L = 0.0F;
              if (this.t < -0.15D) {
                this.t = -0.15D;
              }
              boolean flag = (aK()) && ((this instanceof zj));
              if ((flag) && (this.t < 0.0D)) {
                this.t = 0.0D;
              }
            }
            d(this.s, this.t, this.u);
            if ((this.A) && (n_())) {
              this.t = 0.2D;
            }
            if (a(rm.y))
            {
              this.t += (0.05D * (b(rm.y).c() + 1) - this.t) * 0.2D;
            }
            else
            {
              blockpos$pooledmutableblockpos.d(this.p, 0.0D, this.r);
              if ((this.l.E) && ((!this.l.e(blockpos$pooledmutableblockpos)) || (!this.l.f(blockpos$pooledmutableblockpos).p())))
              {
                if (this.q > 0.0D) {
                  this.t = -0.1D;
                } else {
                  this.t = 0.0D;
                }
              }
              else {
                this.t -= 0.08D;
              }
            }
            this.t *= 0.9800000190734863D;
            this.s *= f6;
            this.u *= f6;
            blockpos$pooledmutableblockpos.t();
          }
        }
        else
        {
          double d4 = this.q;
          a(strafe, forward, 0.02F);
          d(this.s, this.t, this.u);
          this.s *= 0.5D;
          this.t *= 0.5D;
          this.u *= 0.5D;
          this.t -= 0.02D;
          if ((this.A) && (c(this.s, this.t + 0.6000000238418579D - this.q + d4, this.u))) {
            this.t = 0.30000001192092896D;
          }
        }
      }
      else
      {
        double d0 = this.q;
        float f1 = 0.8F;
        float f2 = 0.02F;
        float f3 = ago.d(this);
        if (f3 > 3.0F) {
          f3 = 3.0F;
        }
        if (!this.z) {
          f3 *= 0.5F;
        }
        if (f3 > 0.0F)
        {
          f1 += (0.54600006F - f1) * f3 / 3.0F;
          f2 += (ck() - f2) * f3 / 3.0F;
        }
        a(strafe, forward, f2);
        d(this.s, this.t, this.u);
        this.s *= f1;
        this.t *= 0.800000011920929D;
        this.u *= f1;
        this.t -= 0.02D;
        if ((this.A) && (c(this.s, this.t + 0.6000000238418579D - this.q + d0, this.u))) {
          this.t = 0.30000001192092896D;
        }
      }
    }
    this.aE = this.aF;
    double d5 = this.p - this.m;
    double d7 = this.r - this.o;
    float f10 = on.a(d5 * d5 + d7 * d7) * 4.0F;
    if (f10 > 1.0F) {
      f10 = 1.0F;
    }
    this.aF += (f10 - this.aF) * 0.4F;
    this.aG += this.aF;
  }
  
  public float ck()
  {
    return this.bz;
  }
  
  public void l(float speedIn)
  {
    this.bz = speedIn;
  }
  
  public boolean B(rr entityIn)
  {
    z(entityIn);
    return false;
  }
  
  public boolean cl()
  {
    return false;
  }
  
  public void m()
  {
    super.m();
    cu();
    if (!this.l.E)
    {
      int i = bX();
      if (i > 0)
      {
        if (this.aw <= 0) {
          this.aw = (20 * (30 - i));
        }
        this.aw -= 1;
        if (this.aw <= 0) {
          k(i - 1);
        }
      }
      for (rw entityequipmentslot : rw.values())
      {
        adq itemstack;
        adq itemstack;
        switch (entityequipmentslot.a())
        {
        case a: 
          itemstack = this.bs[entityequipmentslot.b()];
          break;
        case b: 
          itemstack = this.bt[entityequipmentslot.b()];
          break;
        }
        adq itemstack;
        adq itemstack1 = a(entityequipmentslot);
        if (!adq.b(itemstack1, itemstack))
        {
          ((lp)this.l).v().a(this, new ho(O(), entityequipmentslot, itemstack1));
          if (itemstack != null) {
            bZ().a(itemstack.a(entityequipmentslot));
          }
          if (itemstack1 != null) {
            bZ().b(itemstack1.a(entityequipmentslot));
          }
          switch (entityequipmentslot.a())
          {
          case a: 
            this.bs[entityequipmentslot.b()] = (itemstack1 == null ? null : itemstack1.k());
            break;
          case b: 
            this.bt[entityequipmentslot.b()] = (itemstack1 == null ? null : itemstack1.k());
          }
        }
      }
      if (this.T % 20 == 0) {
        bU().g();
      }
      if (!this.ar)
      {
        boolean flag = a(rm.x);
        if (i(6) != flag) {
          b(6, flag);
        }
      }
    }
    n();
    double d0 = this.p - this.m;
    double d1 = this.r - this.o;
    float f1 = (float)(d0 * d0 + d1 * d1);
    float f2 = this.aM;
    float f3 = 0.0F;
    this.aV = this.aW;
    float f = 0.0F;
    if (f1 > 0.0025000002F)
    {
      f = 1.0F;
      f3 = (float)Math.sqrt(f1) * 3.0F;
      f2 = (float)on.b(d1, d0) * 57.295776F - 90.0F;
    }
    if (this.aC > 0.0F) {
      f2 = this.v;
    }
    if (!this.z) {
      f = 0.0F;
    }
    this.aW += (f - this.aW) * 0.3F;
    this.l.C.a("headTurn");
    f3 = h(f2, f3);
    this.l.C.b();
    this.l.C.a("rangeChecks");
    while (this.v - this.x < -180.0F) {
      this.x -= 360.0F;
    }
    while (this.v - this.x >= 180.0F) {
      this.x += 360.0F;
    }
    while (this.aM - this.aN < -180.0F) {
      this.aN -= 360.0F;
    }
    while (this.aM - this.aN >= 180.0F) {
      this.aN += 360.0F;
    }
    while (this.w - this.y < -180.0F) {
      this.y -= 360.0F;
    }
    while (this.w - this.y >= 180.0F) {
      this.y += 360.0F;
    }
    while (this.aO - this.aP < -180.0F) {
      this.aP -= 360.0F;
    }
    while (this.aO - this.aP >= 180.0F) {
      this.aP += 360.0F;
    }
    this.l.C.b();
    this.aX += f3;
    if (cB()) {
      this.bo += 1;
    } else {
      this.bo = 0;
    }
  }
  
  protected float h(float p_110146_1_, float p_110146_2_)
  {
    float f = on.g(p_110146_1_ - this.aM);
    this.aM += f * 0.3F;
    float f1 = on.g(this.v - this.aM);
    boolean flag = (f1 < -90.0F) || (f1 >= 90.0F);
    if (f1 < -75.0F) {
      f1 = -75.0F;
    }
    if (f1 >= 75.0F) {
      f1 = 75.0F;
    }
    this.aM = (this.v - f1);
    if (f1 * f1 > 2500.0F) {
      this.aM += f1 * 0.2F;
    }
    if (flag) {
      p_110146_2_ *= -1.0F;
    }
    return p_110146_2_;
  }
  
  public void n()
  {
    if (this.bA > 0) {
      this.bA -= 1;
    }
    if ((this.bg > 0) && (!bx()))
    {
      double d0 = this.p + (this.bh - this.p) / this.bg;
      double d1 = this.q + (this.bi - this.q) / this.bg;
      double d2 = this.r + (this.bj - this.r) / this.bg;
      double d3 = on.g(this.bk - this.v);
      this.v = ((float)(this.v + d3 / this.bg));
      this.w = ((float)(this.w + (this.bl - this.w) / this.bg));
      this.bg -= 1;
      b(d0, d1, d2);
      b(this.v, this.w);
    }
    else if (!co())
    {
      this.s *= 0.98D;
      this.t *= 0.98D;
      this.u *= 0.98D;
    }
    if (Math.abs(this.s) < 0.003D) {
      this.s = 0.0D;
    }
    if (Math.abs(this.t) < 0.003D) {
      this.t = 0.0D;
    }
    if (Math.abs(this.u) < 0.003D) {
      this.u = 0.0D;
    }
    this.l.C.a("ai");
    if (cf())
    {
      this.bc = false;
      this.bd = 0.0F;
      this.be = 0.0F;
      this.bf = 0.0F;
    }
    else if (co())
    {
      this.l.C.a("newAi");
      cm();
      this.l.C.b();
    }
    this.l.C.b();
    this.l.C.a("jump");
    if (this.bc)
    {
      if (ai())
      {
        ci();
      }
      else if (an())
      {
        cj();
      }
      else if ((this.z) && (this.bA == 0))
      {
        ch();
        this.bA = 10;
      }
    }
    else {
      this.bA = 0;
    }
    this.l.C.b();
    this.l.C.a("travel");
    this.bd *= 0.98F;
    this.be *= 0.98F;
    this.bf *= 0.9F;
    r();
    g(this.bd, this.be);
    this.l.C.b();
    this.l.C.a("push");
    cn();
    this.l.C.b();
  }
  
  private void r()
  {
    boolean flag = i(7);
    if ((flag) && (!this.z) && (!aI()))
    {
      adq itemstack = a(rw.e);
      if ((itemstack != null) && (itemstack.b() == ads.cR) && (acx.d(itemstack)))
      {
        flag = true;
        if ((!this.l.E) && ((this.bo + 1) % 20 == 0)) {
          itemstack.a(1, this);
        }
      }
      else
      {
        flag = false;
      }
    }
    else
    {
      flag = false;
    }
    if (!this.l.E) {
      b(7, flag);
    }
  }
  
  protected void cm() {}
  
  protected void cn()
  {
    List<rr> list = this.l.a(this, bl(), rv.a(this));
    if (!list.isEmpty()) {
      for (int i = 0; i < list.size(); i++)
      {
        rr entity = (rr)list.get(i);
        C(entity);
      }
    }
  }
  
  protected void C(rr entityIn)
  {
    entityIn.i(this);
  }
  
  public void p()
  {
    rr entity = by();
    super.p();
    if ((entity != null) && (entity != by()) && (!this.l.E)) {
      A(entity);
    }
  }
  
  public void aw()
  {
    super.aw();
    this.aV = this.aW;
    this.aW = 0.0F;
    this.L = 0.0F;
  }
  
  public void a(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean p_180426_10_)
  {
    this.bh = x;
    this.bi = y;
    this.bj = z;
    this.bk = yaw;
    this.bl = pitch;
    this.bg = posRotationIncrements;
  }
  
  public void k(boolean jumping)
  {
    this.bc = jumping;
  }
  
  public void a(rr entityIn, int quantity)
  {
    if ((!entityIn.F) && (!this.l.E))
    {
      lm entitytracker = ((lp)this.l).v();
      if ((entityIn instanceof yd)) {
        entitytracker.a(entityIn, new ib(entityIn.O(), O()));
      }
      if ((entityIn instanceof zm)) {
        entitytracker.a(entityIn, new ib(entityIn.O(), O()));
      }
      if ((entityIn instanceof rx)) {
        entitytracker.a(entityIn, new ib(entityIn.O(), O()));
      }
    }
  }
  
  public boolean D(rr entityIn)
  {
    return this.l.a(new bbj(this.p, this.q + bn(), this.r), new bbj(entityIn.p, entityIn.q + entityIn.bn(), entityIn.r), false, true, false) == null;
  }
  
  public bbj aB()
  {
    return f(1.0F);
  }
  
  public bbj f(float partialTicks)
  {
    if (partialTicks == 1.0F) {
      return f(this.w, this.aO);
    }
    float f = this.y + (this.w - this.y) * partialTicks;
    float f1 = this.aP + (this.aO - this.aP) * partialTicks;
    return f(f, f1);
  }
  
  public float m(float partialTickTime)
  {
    float f = this.aC - this.aB;
    if (f < 0.0F) {
      f += 1.0F;
    }
    return this.aB + f * partialTickTime;
  }
  
  public boolean co()
  {
    return !this.l.E;
  }
  
  public boolean ap()
  {
    return !this.F;
  }
  
  public boolean aq()
  {
    return !this.F;
  }
  
  protected void ao()
  {
    this.D = (this.S.nextDouble() >= a(yt.c).e());
  }
  
  public float aS()
  {
    return this.aO;
  }
  
  public void h(float rotation)
  {
    this.aO = rotation;
  }
  
  public void i(float offset)
  {
    this.aM = offset;
  }
  
  public float cp()
  {
    return this.bB;
  }
  
  public void n(float amount)
  {
    if (amount < 0.0F) {
      amount = 0.0F;
    }
    this.bB = amount;
  }
  
  public void j() {}
  
  public void k() {}
  
  protected void cq()
  {
    this.bu = true;
  }
  
  public abstract rz cr();
  
  public boolean cs()
  {
    return (((Byte)this.Z.a(as)).byteValue() & 0x1) > 0;
  }
  
  public qm ct()
  {
    return (((Byte)this.Z.a(as)).byteValue() & 0x2) > 0 ? qm.b : qm.a;
  }
  
  protected void cu()
  {
    if (cs())
    {
      adq itemstack = b(ct());
      if (itemstack == this.bm)
      {
        if ((cw() <= 25) && (cw() % 4 == 0)) {
          a(this.bm, 5);
        }
        if ((--this.bn == 0) && (!this.l.E)) {
          v();
        }
      }
      else
      {
        cz();
      }
    }
  }
  
  public boolean isUsingItem()
  {
    return cw() != 0;
  }
  
  public void c(qm p_184598_1_)
  {
    adq itemstack = b(p_184598_1_);
    
    DualHand.setUsingHand(p_184598_1_);
    if ((itemstack != null) && (!cs()))
    {
      this.bm = itemstack;
      this.bn = itemstack.l();
      if (!this.l.E)
      {
        int i = 1;
        if (p_184598_1_ == qm.b) {
          i |= 0x2;
        }
        this.Z.b(as, Byte.valueOf((byte)i));
      }
    }
  }
  
  public void a(ke<?> key)
  {
    super.a(key);
    if ((as.equals(key)) && (this.l.E)) {
      if ((cs()) && (this.bm == null))
      {
        this.bm = b(ct());
        if (this.bm != null) {
          this.bn = this.bm.l();
        }
      }
      else if ((!cs()) && (this.bm != null))
      {
        this.bm = null;
        this.bn = 0;
      }
    }
  }
  
  protected void a(adq p_184584_1_, int p_184584_2_)
  {
    if ((p_184584_1_ != null) && (cs()))
    {
      if (p_184584_1_.m() == afa.c) {
        a(ng.bB, 0.5F, this.l.r.nextFloat() * 0.1F + 0.9F);
      }
      if (p_184584_1_.m() == afa.b)
      {
        for (int i = 0; i < p_184584_2_; i++)
        {
          bbj vec3d = new bbj((this.S.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
          vec3d = vec3d.a(-this.w * 0.017453292F);
          vec3d = vec3d.b(-this.v * 0.017453292F);
          double d0 = -this.S.nextFloat() * 0.6D - 0.3D;
          bbj vec3d1 = new bbj((this.S.nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
          vec3d1 = vec3d1.a(-this.w * 0.017453292F);
          vec3d1 = vec3d1.b(-this.v * 0.017453292F);
          vec3d1 = vec3d1.b(this.p, this.q + bn(), this.r);
          if (p_184584_1_.f()) {
            this.l.a(cy.K, vec3d1.b, vec3d1.c, vec3d1.d, vec3d.b, vec3d.c + 0.05D, vec3d.d, new int[] { ado.a(p_184584_1_.b()), p_184584_1_.i() });
          } else {
            this.l.a(cy.K, vec3d1.b, vec3d1.c, vec3d1.d, vec3d.b, vec3d.c + 0.05D, vec3d.d, new int[] { ado.a(p_184584_1_.b()) });
          }
        }
        a(ng.bC, 0.5F + 0.5F * this.S.nextInt(2), (this.S.nextFloat() - this.S.nextFloat()) * 0.2F + 1.0F);
      }
    }
  }
  
  protected void v()
  {
    if ((this.bm != null) && (cs()))
    {
      a(this.bm, 16);
      adq itemstack = this.bm.a(this.l, this);
      if ((itemstack != null) && (itemstack.b == 0)) {
        itemstack = null;
      }
      a(ct(), itemstack);
      cz();
    }
  }
  
  public adq cv()
  {
    return this.bm;
  }
  
  public int cw()
  {
    return this.bn;
  }
  
  public int cx()
  {
    return cs() ? this.bm.l() - cw() : 0;
  }
  
  public void cy()
  {
    if (this.bm != null) {
      this.bm.a(this.l, this, cw());
    }
    cz();
  }
  
  public void cz()
  {
    if (!this.l.E) {
      this.Z.b(as, Byte.valueOf((byte)0));
    }
    this.bm = null;
    this.bn = 0;
  }
  
  public boolean cA()
  {
    if ((cs()) && (this.bm != null))
    {
      ado item = this.bm.b();
      return item.f(this.bm) == afa.d;
    }
    return false;
  }
  
  public boolean cB()
  {
    return i(7);
  }
  
  public int cC()
  {
    return this.bo;
  }
  
  public boolean k(double p_184595_1_, double p_184595_3_, double p_184595_5_)
  {
    double d0 = this.p;
    double d1 = this.q;
    double d2 = this.r;
    this.p = p_184595_1_;
    this.q = p_184595_3_;
    this.r = p_184595_5_;
    boolean flag = false;
    cj blockpos = new cj(this);
    aht world = this.l;
    Random random = bF();
    if (world.e(blockpos))
    {
      boolean flag1 = false;
      while ((!flag1) && (blockpos.q() > 0))
      {
        cj blockpos1 = blockpos.b();
        arc iblockstate = world.o(blockpos1);
        if (iblockstate.a().c())
        {
          flag1 = true;
        }
        else
        {
          this.q -= 1.0D;
          blockpos = blockpos1;
        }
      }
      if (flag1)
      {
        a(this.p, this.q, this.r);
        if ((world.a(this, bl()).isEmpty()) && (!world.e(bl()))) {
          flag = true;
        }
      }
    }
    if (!flag)
    {
      a(d0, d1, d2);
      return false;
    }
    int i = 128;
    for (int j = 0; j < i; j++)
    {
      double d6 = j / (i - 1.0D);
      float f = (random.nextFloat() - 0.5F) * 0.2F;
      float f1 = (random.nextFloat() - 0.5F) * 0.2F;
      float f2 = (random.nextFloat() - 0.5F) * 0.2F;
      double d3 = d0 + (this.p - d0) * d6 + (random.nextDouble() - 0.5D) * this.G * 2.0D;
      double d4 = d1 + (this.q - d1) * d6 + random.nextDouble() * this.H;
      double d5 = d2 + (this.r - d2) * d6 + (random.nextDouble() - 0.5D) * this.G * 2.0D;
      world.a(cy.y, d3, d4, d5, f, f1, f2, new int[0]);
    }
    if ((this instanceof sh)) {
      ((sh)this).x().o();
    }
    return true;
  }
  
  public boolean cD()
  {
    return true;
  }
}
