import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public abstract class sb
  extends sa
{
  private static final ke<Byte> a = kh.a(sb.class, kg.a);
  public int a_;
  protected int b_;
  private sx b;
  protected sy f;
  protected sw g;
  private su c;
  protected vf h;
  protected final tl bp;
  protected final tl bq;
  private sa bt;
  private vk bu;
  private adq[] bv = new adq[2];
  protected float[] br = new float[2];
  private adq[] bw = new adq[4];
  protected float[] bs = new float[4];
  private boolean bx;
  private boolean by;
  private Map<aym, Float> bz = Maps.newEnumMap(aym.class);
  private kk bA;
  private long bB;
  private boolean bC;
  private rr bD;
  private dn bE;
  public int randomMobsId = 0;
  public aig spawnBiome = null;
  public cj spawnPosition = null;
  
  public sb(aht worldIn)
  {
    super(worldIn);
    this.bp = new tl((worldIn != null) && (worldIn.C != null) ? worldIn.C : null);
    this.bq = new tl((worldIn != null) && (worldIn.C != null) ? worldIn.C : null);
    this.b = new sx(this);
    this.f = new sy(this);
    this.g = new sw(this);
    this.c = s();
    this.h = b(worldIn);
    this.bu = new vk(this);
    for (int i = 0; i < this.bs.length; i++) {
      this.bs[i] = 0.085F;
    }
    for (int j = 0; j < this.br.length; j++) {
      this.br[j] = 0.085F;
    }
    if ((worldIn != null) && (!worldIn.E)) {
      r();
    }
    UUID uuid = bc();
    
    long uuidLow = uuid.getLeastSignificantBits();
    
    this.randomMobsId = ((int)(uuidLow & 0x7FFFFFFF));
  }
  
  protected void r() {}
  
  protected void bA()
  {
    super.bA();
    bZ().b(yt.b).a(16.0D);
  }
  
  protected vf b(aht worldIn)
  {
    return new ve(this, worldIn);
  }
  
  public float a(aym nodeType)
  {
    return this.bz.containsKey(nodeType) ? ((Float)this.bz.get(nodeType)).floatValue() : nodeType.a();
  }
  
  public void a(aym nodeType, float p_184644_2_)
  {
    this.bz.put(nodeType, Float.valueOf(p_184644_2_));
  }
  
  protected su s()
  {
    return new su(this);
  }
  
  public sx t()
  {
    return this.b;
  }
  
  public sy u()
  {
    return this.f;
  }
  
  public sw w()
  {
    return this.g;
  }
  
  public vf x()
  {
    return this.h;
  }
  
  public vk y()
  {
    return this.bu;
  }
  
  public sa A()
  {
    return this.bt;
  }
  
  public void c(sa entitylivingbaseIn)
  {
    this.bt = entitylivingbaseIn;
    
    Reflector.callVoid(Reflector.ForgeHooks_onLivingSetAttackTarget, new Object[] { this, entitylivingbaseIn });
  }
  
  public boolean d(Class<? extends sa> cls)
  {
    return cls != ym.class;
  }
  
  public void B() {}
  
  protected void i()
  {
    super.i();
    this.Z.a(a, Byte.valueOf((byte)0));
  }
  
  public int C()
  {
    return 80;
  }
  
  public void D()
  {
    nf soundevent = G();
    if (soundevent != null) {
      a(soundevent, cd(), ce());
    }
  }
  
  public void U()
  {
    super.U();
    this.l.C.a("mobBaseTick");
    if ((au()) && (this.S.nextInt(1000) < this.a_++))
    {
      o();
      D();
    }
    this.l.C.b();
  }
  
  protected void c(rc p_184581_1_)
  {
    o();
    super.c(p_184581_1_);
  }
  
  private void o()
  {
    this.a_ = (-C());
  }
  
  protected int b(zj player)
  {
    if (this.b_ > 0)
    {
      int i = this.b_;
      for (int j = 0; j < this.bw.length; j++) {
        if ((this.bw[j] != null) && (this.bs[j] <= 1.0F)) {
          i += 1 + this.S.nextInt(3);
        }
      }
      for (int k = 0; k < this.bv.length; k++) {
        if ((this.bv[k] != null) && (this.br[k] <= 1.0F)) {
          i += 1 + this.S.nextInt(3);
        }
      }
      return i;
    }
    return this.b_;
  }
  
  public void E()
  {
    if (this.l.E) {
      for (int i = 0; i < 20; i++)
      {
        double d0 = this.S.nextGaussian() * 0.02D;
        double d1 = this.S.nextGaussian() * 0.02D;
        double d2 = this.S.nextGaussian() * 0.02D;
        double d3 = 10.0D;
        this.l.a(cy.a, this.p + this.S.nextFloat() * this.G * 2.0F - this.G - d0 * d3, this.q + this.S.nextFloat() * this.H - d1 * d3, this.r + this.S.nextFloat() * this.G * 2.0F - this.G - d2 * d3, d0, d1, d2, new int[0]);
      }
    } else {
      this.l.a(this, (byte)20);
    }
  }
  
  public void a(byte id)
  {
    if (id == 20) {
      E();
    } else {
      super.a(id);
    }
  }
  
  public void m()
  {
    if (Config.isSmoothWorld()) {
      if (canSkipUpdate())
      {
        onUpdateMinimal();
        return;
      }
    }
    super.m();
    if (!this.l.E)
    {
      cO();
      if (this.T % 5 == 0)
      {
        boolean flag = !(bt() instanceof sb);
        boolean flag1 = !(by() instanceof aag);
        this.bp.a(5, (flag) && (flag1));
        this.bp.a(2, flag);
      }
    }
  }
  
  protected float h(float p_110146_1_, float p_110146_2_)
  {
    this.c.a();
    return p_110146_2_;
  }
  
  protected nf G()
  {
    return null;
  }
  
  protected ado I()
  {
    return null;
  }
  
  protected void b(boolean wasRecentlyHit, int lootingModifier)
  {
    ado item = I();
    if (item != null)
    {
      int i = this.S.nextInt(3);
      if (lootingModifier > 0) {
        i += this.S.nextInt(lootingModifier + 1);
      }
      for (int j = 0; j < i; j++) {
        a(item, 1);
      }
    }
  }
  
  public void b(dn tagCompound)
  {
    super.b(tagCompound);
    tagCompound.a("CanPickUpLoot", cM());
    tagCompound.a("PersistenceRequired", this.by);
    du nbttaglist = new du();
    for (int i = 0; i < this.bw.length; i++)
    {
      dn nbttagcompound = new dn();
      if (this.bw[i] != null) {
        this.bw[i].b(nbttagcompound);
      }
      nbttaglist.a(nbttagcompound);
    }
    tagCompound.a("ArmorItems", nbttaglist);
    du nbttaglist1 = new du();
    for (int k = 0; k < this.bv.length; k++)
    {
      dn nbttagcompound1 = new dn();
      if (this.bv[k] != null) {
        this.bv[k].b(nbttagcompound1);
      }
      nbttaglist1.a(nbttagcompound1);
    }
    tagCompound.a("HandItems", nbttaglist1);
    du nbttaglist2 = new du();
    for (int l = 0; l < this.bs.length; l++) {
      nbttaglist2.a(new dr(this.bs[l]));
    }
    tagCompound.a("ArmorDropChances", nbttaglist2);
    du nbttaglist3 = new du();
    for (int j = 0; j < this.br.length; j++) {
      nbttaglist3.a(new dr(this.br[j]));
    }
    tagCompound.a("HandDropChances", nbttaglist3);
    tagCompound.a("Leashed", this.bC);
    if (this.bD != null)
    {
      dn nbttagcompound2 = new dn();
      if ((this.bD instanceof sa))
      {
        UUID uuid = this.bD.bc();
        nbttagcompound2.a("UUID", uuid);
      }
      else if ((this.bD instanceof xr))
      {
        cj blockpos = ((xr)this.bD).q();
        nbttagcompound2.a("X", blockpos.p());
        nbttagcompound2.a("Y", blockpos.q());
        nbttagcompound2.a("Z", blockpos.r());
      }
      tagCompound.a("Leash", nbttagcompound2);
    }
    tagCompound.a("LeftHanded", cS());
    if (this.bA != null)
    {
      tagCompound.a("DeathLootTable", this.bA.toString());
      if (this.bB != 0L) {
        tagCompound.a("DeathLootTableSeed", this.bB);
      }
    }
    if (cR()) {
      tagCompound.a("NoAI", cR());
    }
  }
  
  public void a(dn tagCompund)
  {
    super.a(tagCompund);
    if (tagCompund.b("CanPickUpLoot", 1)) {
      l(tagCompund.p("CanPickUpLoot"));
    }
    this.by = tagCompund.p("PersistenceRequired");
    if (tagCompund.b("ArmorItems", 9))
    {
      du nbttaglist = tagCompund.c("ArmorItems", 10);
      for (int i = 0; i < this.bw.length; i++) {
        this.bw[i] = adq.a(nbttaglist.b(i));
      }
    }
    if (tagCompund.b("HandItems", 9))
    {
      du nbttaglist1 = tagCompund.c("HandItems", 10);
      for (int j = 0; j < this.bv.length; j++) {
        this.bv[j] = adq.a(nbttaglist1.b(j));
      }
    }
    if (tagCompund.b("ArmorDropChances", 9))
    {
      du nbttaglist2 = tagCompund.c("ArmorDropChances", 5);
      for (int k = 0; k < nbttaglist2.c(); k++) {
        this.bs[k] = nbttaglist2.f(k);
      }
    }
    if (tagCompund.b("HandDropChances", 9))
    {
      du nbttaglist3 = tagCompund.c("HandDropChances", 5);
      for (int l = 0; l < nbttaglist3.c(); l++) {
        this.br[l] = nbttaglist3.f(l);
      }
    }
    this.bC = tagCompund.p("Leashed");
    if ((this.bC) && (tagCompund.b("Leash", 10))) {
      this.bE = tagCompund.o("Leash");
    }
    n(tagCompund.p("LeftHanded"));
    if (tagCompund.b("DeathLootTable", 8))
    {
      this.bA = new kk(tagCompund.l("DeathLootTable"));
      this.bB = tagCompund.i("DeathLootTableSeed");
    }
    m(tagCompund.p("NoAI"));
  }
  
  protected kk J()
  {
    return null;
  }
  
  protected void a(boolean p_184610_1_, int p_184610_2_, rc source)
  {
    kk resourcelocation = this.bA;
    if (resourcelocation == null) {
      resourcelocation = J();
    }
    if (resourcelocation != null)
    {
      azy loottable = this.l.ak().a(resourcelocation);
      this.bA = null;
      azz.a lootcontext$builder = new azz.a((lp)this.l).a(this).a(source);
      if ((p_184610_1_) && (this.aR != null)) {
        lootcontext$builder = lootcontext$builder.a(this.aR).a(this.aR.db());
      }
      for (adq itemstack : loottable.a(this.bB == 0L ? this.S : new Random(this.bB), lootcontext$builder.a())) {
        a(itemstack, 0.0F);
      }
      a(p_184610_1_, p_184610_2_);
    }
    else
    {
      super.a(p_184610_1_, p_184610_2_, source);
    }
  }
  
  public void o(float p_70657_1_)
  {
    this.be = p_70657_1_;
  }
  
  public void p(float p_184646_1_)
  {
    this.bd = p_184646_1_;
  }
  
  public void l(float speedIn)
  {
    super.l(speedIn);
    o(speedIn);
  }
  
  public void n()
  {
    super.n();
    this.l.C.a("looting");
    if ((!this.l.E) && (cM()) && (!this.aT) && (this.l.U().b("mobGriefing"))) {
      for (yd entityitem : this.l.a(yd.class, bl().b(1.0D, 0.0D, 1.0D))) {
        if ((!entityitem.F) && (entityitem.k() != null) && (!entityitem.t())) {
          a(entityitem);
        }
      }
    }
    this.l.C.b();
  }
  
  protected void a(yd itemEntity)
  {
    adq itemstack = itemEntity.k();
    rw entityequipmentslot = d(itemstack);
    boolean flag = true;
    adq itemstack1 = a(entityequipmentslot);
    if (itemstack1 != null) {
      if (entityequipmentslot.a() == rw.a.a)
      {
        if (((itemstack.b() instanceof aex)) && (!(itemstack1.b() instanceof aex)))
        {
          flag = true;
        }
        else if (((itemstack.b() instanceof aex)) && ((itemstack1.b() instanceof aex)))
        {
          aex itemsword = (aex)itemstack.b();
          aex itemsword1 = (aex)itemstack1.b();
          if (itemsword.g() == itemsword1.g()) {
            flag = (itemstack.i() > itemstack1.i()) || ((itemstack.n()) && (!itemstack1.n()));
          } else {
            flag = itemsword.g() > itemsword1.g();
          }
        }
        else if (((itemstack.b() instanceof ach)) && ((itemstack1.b() instanceof ach)))
        {
          flag = (itemstack.n()) && (!itemstack1.n());
        }
        else
        {
          flag = false;
        }
      }
      else if (((itemstack.b() instanceof abw)) && (!(itemstack1.b() instanceof abw)))
      {
        flag = true;
      }
      else if (((itemstack.b() instanceof abw)) && ((itemstack1.b() instanceof abw)))
      {
        abw itemarmor = (abw)itemstack.b();
        abw itemarmor1 = (abw)itemstack1.b();
        if (itemarmor.d == itemarmor1.d) {
          flag = (itemstack.i() > itemstack1.i()) || ((itemstack.n()) && (!itemstack1.n()));
        } else {
          flag = itemarmor.d > itemarmor1.d;
        }
      }
      else
      {
        flag = false;
      }
    }
    if ((flag) && (c(itemstack)))
    {
      double d0;
      switch (entityequipmentslot.a())
      {
      case a: 
        d0 = this.br[entityequipmentslot.b()];
        break;
      case b: 
        d0 = this.bs[entityequipmentslot.b()];
        break;
      default: 
        d0 = 0.0D;
      }
      if ((itemstack1 != null) && (this.S.nextFloat() - 0.1F < d0)) {
        a(itemstack1, 0.0F);
      }
      if ((itemstack.b() == ads.k) && (itemEntity.n() != null))
      {
        zj entityplayer = this.l.a(itemEntity.n());
        if (entityplayer != null) {
          entityplayer.b(nk.x);
        }
      }
      a(entityequipmentslot, itemstack);
      switch (entityequipmentslot.a())
      {
      case a: 
        this.br[entityequipmentslot.b()] = 2.0F;
        break;
      case b: 
        this.bs[entityequipmentslot.b()] = 2.0F;
      }
      this.by = true;
      a(itemEntity, 1);
      itemEntity.T();
    }
  }
  
  protected boolean c(adq stack)
  {
    return true;
  }
  
  protected boolean K()
  {
    return true;
  }
  
  protected void L()
  {
    Object result = null;
    Object Result_DEFAULT = Reflector.getFieldValue(Reflector.Event_Result_DEFAULT);
    Object Result_DENY = Reflector.getFieldValue(Reflector.Event_Result_DENY);
    if (this.by)
    {
      this.aU = 0;
    }
    else
    {
      if ((this.aU & 0x1F) == 31) {
        if ((result = Reflector.call(Reflector.ForgeEventFactory_canEntityDespawn, new Object[] { this })) != Result_DEFAULT)
        {
          if (result == Result_DENY)
          {
            this.aU = 0; return;
          }
          T(); return;
        }
      }
      rr entity = this.l.a(this, -1.0D);
      if (entity != null)
      {
        double d0 = entity.p - this.p;
        double d1 = entity.q - this.q;
        double d2 = entity.r - this.r;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;
        if ((K()) && (d3 > 16384.0D)) {
          T();
        }
        if ((this.aU > 600) && (this.S.nextInt(800) == 0) && (d3 > 1024.0D) && (K())) {
          T();
        } else if (d3 < 1024.0D) {
          this.aU = 0;
        }
      }
    }
  }
  
  protected final void cm()
  {
    this.aU += 1;
    this.l.C.a("checkDespawn");
    L();
    this.l.C.b();
    this.l.C.a("sensing");
    this.bu.a();
    this.l.C.b();
    this.l.C.a("targetSelector");
    this.bq.a();
    this.l.C.b();
    this.l.C.a("goalSelector");
    this.bp.a();
    this.l.C.b();
    this.l.C.a("navigation");
    this.h.l();
    this.l.C.b();
    this.l.C.a("mob tick");
    M();
    this.l.C.b();
    if ((aI()) && ((by() instanceof sb)))
    {
      sb entityliving = (sb)by();
      entityliving.x().a(x().k(), 1.5D);
      entityliving.u().a(u());
    }
    this.l.C.a("controls");
    this.l.C.a("move");
    this.f.c();
    this.l.C.c("look");
    this.b.a();
    this.l.C.c("jump");
    this.g.b();
    this.l.C.b();
    this.l.C.b();
  }
  
  protected void M() {}
  
  public int N()
  {
    return 40;
  }
  
  public int cE()
  {
    return 10;
  }
  
  public void a(rr entityIn, float p_70625_2_, float p_70625_3_)
  {
    double d0 = entityIn.p - this.p;
    double d2 = entityIn.r - this.r;
    double d1;
    double d1;
    if ((entityIn instanceof sa))
    {
      sa entitylivingbase = (sa)entityIn;
      d1 = entitylivingbase.q + entitylivingbase.bn() - (this.q + bn());
    }
    else
    {
      d1 = (entityIn.bl().b + entityIn.bl().e) / 2.0D - (this.q + bn());
    }
    double d3 = on.a(d0 * d0 + d2 * d2);
    float f = (float)(on.b(d2, d0) * 57.29577951308232D) - 90.0F;
    float f1 = (float)-(on.b(d1, d3) * 57.29577951308232D);
    this.w = b(this.w, f1, p_70625_3_);
    this.v = b(this.v, f, p_70625_2_);
  }
  
  private float b(float p_70663_1_, float p_70663_2_, float p_70663_3_)
  {
    float f = on.g(p_70663_2_ - p_70663_1_);
    if (f > p_70663_3_) {
      f = p_70663_3_;
    }
    if (f < -p_70663_3_) {
      f = -p_70663_3_;
    }
    return p_70663_1_ + f;
  }
  
  public boolean cF()
  {
    return true;
  }
  
  public boolean cG()
  {
    return (!this.l.e(bl())) && (this.l.a(this, bl()).isEmpty()) && (this.l.a(bl(), this));
  }
  
  public float cH()
  {
    return 1.0F;
  }
  
  public int cJ()
  {
    return 4;
  }
  
  public int aW()
  {
    if (A() == null) {
      return 3;
    }
    int i = (int)(bQ() - bW() * 0.33F);
    i -= (3 - this.l.ae().a()) * 4;
    if (i < 0) {
      i = 0;
    }
    return i + 3;
  }
  
  public Iterable<adq> aE()
  {
    return Arrays.asList(this.bv);
  }
  
  public Iterable<adq> aF()
  {
    return Arrays.asList(this.bw);
  }
  
  public adq a(rw slotIn)
  {
    adq itemstack = null;
    switch (slotIn.a())
    {
    case a: 
      itemstack = this.bv[slotIn.b()];
      break;
    case b: 
      itemstack = this.bw[slotIn.b()];
    }
    return itemstack;
  }
  
  public void a(rw slotIn, adq stack)
  {
    switch (slotIn.a())
    {
    case a: 
      this.bv[slotIn.b()] = stack;
      break;
    case b: 
      this.bw[slotIn.b()] = stack;
    }
  }
  
  protected void a(boolean wasRecentlyHit, int lootingModifier)
  {
    for (rw entityequipmentslot : )
    {
      adq itemstack = a(entityequipmentslot);
      double d0;
      switch (entityequipmentslot.a())
      {
      case a: 
        d0 = this.br[entityequipmentslot.b()];
        break;
      case b: 
        d0 = this.bs[entityequipmentslot.b()];
        break;
      default: 
        d0 = 0.0D;
      }
      boolean flag = d0 > 1.0D;
      if ((itemstack != null) && ((wasRecentlyHit) || (flag)) && (this.S.nextFloat() - lootingModifier * 0.01F < d0))
      {
        if ((!flag) && (itemstack.e()))
        {
          int i = Math.max(itemstack.j() - 25, 1);
          int j = itemstack.j() - this.S.nextInt(this.S.nextInt(i) + 1);
          if (j > i) {
            j = i;
          }
          if (j < 1) {
            j = 1;
          }
          itemstack.b(j);
        }
        a(itemstack, 0.0F);
      }
    }
  }
  
  protected void a(ql difficulty)
  {
    if (this.S.nextFloat() < 0.15F * difficulty.c())
    {
      int i = this.S.nextInt(2);
      float f = this.l.ae() == qk.d ? 0.1F : 0.25F;
      if (this.S.nextFloat() < 0.095F) {
        i++;
      }
      if (this.S.nextFloat() < 0.095F) {
        i++;
      }
      if (this.S.nextFloat() < 0.095F) {
        i++;
      }
      boolean flag = true;
      for (rw entityequipmentslot : rw.values()) {
        if (entityequipmentslot.a() == rw.a.b)
        {
          adq itemstack = a(entityequipmentslot);
          if ((!flag) && (this.S.nextFloat() < f)) {
            break;
          }
          flag = false;
          if (itemstack == null)
          {
            ado item = a(entityequipmentslot, i);
            if (item != null) {
              a(entityequipmentslot, new adq(item));
            }
          }
        }
      }
    }
  }
  
  public static rw d(adq p_184640_0_)
  {
    return (p_184640_0_.b() != ado.a(aju.aU)) && (p_184640_0_.b() != ads.ch) ? rw.a : p_184640_0_.b() == ads.cR ? rw.e : (p_184640_0_.b() instanceof abw) ? ((abw)p_184640_0_.b()).c : p_184640_0_.b() == ads.cR ? rw.e : rw.f;
  }
  
  public static ado a(rw p_184636_0_, int p_184636_1_)
  {
    switch (p_184636_0_)
    {
    case f: 
      if (p_184636_1_ == 0) {
        return ads.S;
      }
      if (p_184636_1_ == 1) {
        return ads.ai;
      }
      if (p_184636_1_ == 2) {
        return ads.W;
      }
      if (p_184636_1_ == 3) {
        return ads.aa;
      }
      if (p_184636_1_ == 4) {
        return ads.ae;
      }
    case e: 
      if (p_184636_1_ == 0) {
        return ads.T;
      }
      if (p_184636_1_ == 1) {
        return ads.aj;
      }
      if (p_184636_1_ == 2) {
        return ads.X;
      }
      if (p_184636_1_ == 3) {
        return ads.ab;
      }
      if (p_184636_1_ == 4) {
        return ads.af;
      }
    case d: 
      if (p_184636_1_ == 0) {
        return ads.U;
      }
      if (p_184636_1_ == 1) {
        return ads.ak;
      }
      if (p_184636_1_ == 2) {
        return ads.Y;
      }
      if (p_184636_1_ == 3) {
        return ads.ac;
      }
      if (p_184636_1_ == 4) {
        return ads.ag;
      }
    case c: 
      if (p_184636_1_ == 0) {
        return ads.V;
      }
      if (p_184636_1_ == 1) {
        return ads.al;
      }
      if (p_184636_1_ == 2) {
        return ads.Z;
      }
      if (p_184636_1_ == 3) {
        return ads.ad;
      }
      if (p_184636_1_ == 4) {
        return ads.ah;
      }
      break;
    }
    return null;
  }
  
  protected void b(ql difficulty)
  {
    float f = difficulty.c();
    if ((cb() != null) && (this.S.nextFloat() < 0.25F * f)) {
      ago.a(this.S, cb(), (int)(5.0F + f * this.S.nextInt(18)), false);
    }
    for (rw entityequipmentslot : rw.values()) {
      if (entityequipmentslot.a() == rw.a.b)
      {
        adq itemstack = a(entityequipmentslot);
        if ((itemstack != null) && (this.S.nextFloat() < 0.5F * f)) {
          ago.a(this.S, itemstack, (int)(5.0F + f * this.S.nextInt(18)), false);
        }
      }
    }
  }
  
  public sd a(ql difficulty, sd livingdata)
  {
    a(yt.b).b(new sn("Random spawn bonus", this.S.nextGaussian() * 0.05D, 1));
    if (this.S.nextFloat() < 0.05F) {
      n(true);
    } else {
      n(false);
    }
    return livingdata;
  }
  
  public boolean cK()
  {
    return false;
  }
  
  public void cL()
  {
    this.by = true;
  }
  
  public void a(rw slotIn, float chance)
  {
    switch (slotIn.a())
    {
    case a: 
      this.br[slotIn.b()] = chance;
      break;
    case b: 
      this.bs[slotIn.b()] = chance;
    }
  }
  
  public boolean cM()
  {
    return this.bx;
  }
  
  public void l(boolean canPickup)
  {
    this.bx = canPickup;
  }
  
  public boolean cN()
  {
    return this.by;
  }
  
  public final boolean a(zj player, adq stack, qm hand)
  {
    if ((cP()) && (cQ() == player))
    {
      a(true, !player.bJ.d);
      return true;
    }
    if ((stack != null) && (stack.b() == ads.cx) && (a(player)))
    {
      b(player, true);
      stack.b -= 1;
      return true;
    }
    return a(player, hand, stack) ? true : super.a(player, stack, hand);
  }
  
  protected boolean a(zj player, qm p_184645_2_, adq stack)
  {
    return false;
  }
  
  protected void cO()
  {
    if (this.bE != null) {
      cT();
    }
    if (this.bC)
    {
      if (!au()) {
        a(true, true);
      }
      if ((this.bD == null) || (this.bD.F)) {
        a(true, true);
      }
    }
  }
  
  public void a(boolean sendPacket, boolean dropLead)
  {
    if (this.bC)
    {
      this.bC = false;
      this.bD = null;
      if ((!this.l.E) && (dropLead)) {
        a(ads.cx, 1);
      }
      if ((!this.l.E) && (sendPacket) && ((this.l instanceof lp))) {
        ((lp)this.l).v().a(this, new hm(this, (rr)null));
      }
    }
  }
  
  public boolean a(zj player)
  {
    return (!cP()) && (!(this instanceof yl));
  }
  
  public boolean cP()
  {
    return this.bC;
  }
  
  public rr cQ()
  {
    return this.bD;
  }
  
  public void b(rr entityIn, boolean sendAttachNotification)
  {
    this.bC = true;
    this.bD = entityIn;
    if ((!this.l.E) && (sendAttachNotification) && ((this.l instanceof lp))) {
      ((lp)this.l).v().a(this, new hm(this, this.bD));
    }
    if (aI()) {
      p();
    }
  }
  
  public boolean a(rr entityIn, boolean force)
  {
    boolean flag = super.a(entityIn, force);
    if ((flag) && (cP())) {
      a(true, true);
    }
    return flag;
  }
  
  private void cT()
  {
    if ((this.bC) && (this.bE != null))
    {
      UUID uuid;
      if (this.bE.b("UUID"))
      {
        uuid = this.bE.a("UUID");
        for (sa entitylivingbase : this.l.a(sa.class, bl().g(10.0D))) {
          if (entitylivingbase.bc().equals(uuid))
          {
            this.bD = entitylivingbase;
            break;
          }
        }
      }
      else if ((this.bE.b("X", 99)) && (this.bE.b("Y", 99)) && (this.bE.b("Z", 99)))
      {
        cj blockpos = new cj(this.bE.h("X"), this.bE.h("Y"), this.bE.h("Z"));
        xt entityleashknot = xt.b(this.l, blockpos);
        if (entityleashknot == null) {
          entityleashknot = xt.a(this.l, blockpos);
        }
        this.bD = entityleashknot;
      }
      else
      {
        a(false, true);
      }
    }
    this.bE = null;
  }
  
  public boolean c(int inventorySlot, adq itemStackIn)
  {
    rw entityequipmentslot;
    rw entityequipmentslot;
    if (inventorySlot == 98)
    {
      entityequipmentslot = rw.a;
    }
    else
    {
      rw entityequipmentslot;
      if (inventorySlot == 99)
      {
        entityequipmentslot = rw.b;
      }
      else
      {
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
              if (inventorySlot != 100 + rw.c.b()) {
                return false;
              }
              entityequipmentslot = rw.c;
            }
          }
        }
      }
    }
    if ((itemStackIn != null) && (!b(entityequipmentslot, itemStackIn)) && (entityequipmentslot != rw.f)) {
      return false;
    }
    a(entityequipmentslot, itemStackIn);
    return true;
  }
  
  public static boolean b(rw p_184648_0_, adq p_184648_1_)
  {
    rw entityequipmentslot = d(p_184648_1_);
    return (entityequipmentslot == p_184648_0_) || ((entityequipmentslot == rw.a) && (p_184648_0_ == rw.b));
  }
  
  public boolean co()
  {
    return (super.co()) && (!cR());
  }
  
  public void m(boolean disable)
  {
    byte b0 = ((Byte)this.Z.a(a)).byteValue();
    this.Z.b(a, Byte.valueOf(disable ? (byte)(b0 | 0x1) : (byte)(b0 & 0xFFFFFFFE)));
  }
  
  public void n(boolean p_184641_1_)
  {
    byte b0 = ((Byte)this.Z.a(a)).byteValue();
    this.Z.b(a, Byte.valueOf(p_184641_1_ ? (byte)(b0 | 0x2) : (byte)(b0 & 0xFFFFFFFD)));
  }
  
  public boolean cR()
  {
    return (((Byte)this.Z.a(a)).byteValue() & 0x1) != 0;
  }
  
  public boolean cS()
  {
    return (((Byte)this.Z.a(a)).byteValue() & 0x2) != 0;
  }
  
  public rz cr()
  {
    return cS() ? rz.a : rz.b;
  }
  
  public boolean av()
  {
    if (this.Q) {
      return false;
    }
    BlockPosM posM = new BlockPosM(0, 0, 0);
    for (int var1 = 0; var1 < 8; var1++)
    {
      double var2 = this.p + ((var1 >> 0) % 2 - 0.5F) * this.G * 0.8F;
      double var4 = this.q + ((var1 >> 1) % 2 - 0.5F) * 0.1F;
      double var6 = this.r + ((var1 >> 2) % 2 - 0.5F) * this.G * 0.8F;
      
      posM.setXyz(var2, var4 + bn(), var6);
      if (this.l.o(posM).t().j()) {
        return true;
      }
    }
    return false;
  }
  
  private boolean canSkipUpdate()
  {
    if (m_()) {
      return false;
    }
    if (this.ax > 0) {
      return false;
    }
    if (this.T < 20) {
      return false;
    }
    aht world = e();
    if (world == null) {
      return false;
    }
    if (world.i.size() != 1) {
      return false;
    }
    rr player = (rr)world.i.get(0);
    
    double dx = Math.max(Math.abs(this.p - player.p) - 16.0D, 0.0D);
    double dz = Math.max(Math.abs(this.r - player.r) - 16.0D, 0.0D);
    double distSq = dx * dx + dz * dz;
    if (a(distSq)) {
      return false;
    }
    return true;
  }
  
  private void onUpdateMinimal()
  {
    this.aU += 1;
    if ((this instanceof yq))
    {
      float brightness = e(1.0F);
      if (brightness > 0.5F) {
        this.aU += 2;
      }
    }
    L();
  }
  
  public static enum a
  {
    private a() {}
  }
}
