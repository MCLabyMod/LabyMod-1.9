import java.util.Random;
import mods.togglesneak.CustomMovementInput;
import mods.togglesneak.ToggleSneakMod;

public class bmt
  extends bmq
{
  public final bks d;
  private final nu bW;
  private int bX = 0;
  private double bY;
  private double bZ;
  private double ca;
  private float cb;
  private float cc;
  private boolean cd;
  private boolean ce;
  private boolean cf;
  private int cg;
  private boolean ch;
  private String ci;
  public bmr e;
  protected bcf f;
  public int g;
  public int h;
  public float bQ;
  public float bR;
  public float bS;
  public float bT;
  private int cj;
  private float ck;
  public float bU;
  public float bV;
  private boolean cm;
  private qm cn;
  private boolean co;
  
  public bmt(bcf mcIn, aht worldIn, bks netHandler, nu statFile)
  {
    super(worldIn, netHandler.e());
    this.d = netHandler;
    this.bW = statFile;
    this.f = mcIn;
    this.am = 0;
  }
  
  public boolean a(rc source, float amount)
  {
    return false;
  }
  
  public void b(float healAmount) {}
  
  public void onLivingUpdateSuper()
  {
    super.n();
  }
  
  public boolean a(rr entityIn, boolean force)
  {
    if (!super.a(entityIn, force)) {
      return false;
    }
    if ((entityIn instanceof aah)) {
      this.f.U().a(new byd(this, (aah)entityIn));
    }
    if ((entityIn instanceof aag))
    {
      this.x = entityIn.v;
      this.v = entityIn.v;
      h(entityIn.v);
    }
    return true;
  }
  
  public void p()
  {
    super.p();
    this.co = false;
  }
  
  public void m()
  {
    if (this.l.e(new cj(this.p, 0.0D, this.r)))
    {
      super.m();
      if (aI())
      {
        this.d.a(new it.c(this.v, this.w, this.z));
        
        this.d.a(new iz(this.bd, this.be, this.e.g, this.e.h));
        
        rr entity = bw();
        if ((entity != this) && (entity.bx())) {
          this.d.a(new iu(entity));
        }
      }
      else
      {
        A();
      }
    }
  }
  
  public void A()
  {
    boolean flag = aL();
    if (flag != this.cf)
    {
      if (flag) {
        this.d.a(new iy(this, iy.a.d));
      } else {
        this.d.a(new iy(this, iy.a.e));
      }
      this.cf = flag;
    }
    boolean flag1 = aK();
    if (flag1 != this.ce)
    {
      if (flag1) {
        this.d.a(new iy(this, iy.a.a));
      } else {
        this.d.a(new iy(this, iy.a.b));
      }
      this.ce = flag1;
    }
    if (L())
    {
      bbh axisalignedbb = bl();
      double d0 = this.p - this.bY;
      double d1 = axisalignedbb.b - this.bZ;
      double d2 = this.r - this.ca;
      double d3 = this.v - this.cb;
      double d4 = this.w - this.cc;
      this.cg += 1;
      boolean flag2 = (d0 * d0 + d1 * d1 + d2 * d2 > 9.0E-4D) || (this.cg >= 20);
      
      boolean flag3 = (d3 != 0.0D) || (d4 != 0.0D);
      if (aI())
      {
        this.d.a(new it.b(this.s, -999.0D, this.u, this.v, this.w, this.z));
        
        flag2 = false;
      }
      else if ((flag2) && (flag3))
      {
        this.d.a(new it.b(this.p, axisalignedbb.b, this.r, this.v, this.w, this.z));
      }
      else if (flag2)
      {
        this.d.a(new it.a(this.p, axisalignedbb.b, this.r, this.z));
      }
      else if (flag3)
      {
        this.d.a(new it.c(this.v, this.w, this.z));
      }
      else if (this.cd != this.z)
      {
        this.d.a(new it(this.z));
      }
      if (flag2)
      {
        this.bY = this.p;
        this.bZ = axisalignedbb.b;
        this.ca = this.r;
        this.cg = 0;
      }
      if (flag3)
      {
        this.cb = this.v;
        this.cc = this.w;
      }
      this.cd = this.z;
    }
  }
  
  public yd a(boolean dropAll)
  {
    ix.a cpacketplayerdigging$action = dropAll ? ix.a.d : ix.a.e;
    
    this.d.a(new ix(cpacketplayerdigging$action, cj.a, cq.a));
    
    return null;
  }
  
  protected adq a(yd p_184816_1_)
  {
    return null;
  }
  
  public void g(String message)
  {
    this.d.a(new ij(message));
  }
  
  public void a(qm hand)
  {
    super.a(hand);
    this.d.a(new je(hand));
  }
  
  public void cI()
  {
    this.d.a(new ik(ik.a.a));
  }
  
  protected void d(rc damageSrc, float damageAmount)
  {
    if (!b(damageSrc)) {
      c(bQ() - damageAmount);
    }
  }
  
  public void q()
  {
    this.d.a(new ip(this.bt.d));
    
    B();
  }
  
  public void B()
  {
    this.br.e((adq)null);
    super.q();
    this.f.a((bfb)null);
  }
  
  public void p(float health)
  {
    if (this.ch)
    {
      float f = bQ() - health;
      if (f <= 0.0F)
      {
        c(health);
        if (f < 0.0F) {
          this.W = (this.aH / 2);
        }
      }
      else
      {
        this.bb = f;
        c(bQ());
        this.W = this.aH;
        d(rc.l, f);
        this.ax = (this.ay = 10);
      }
    }
    else
    {
      c(health);
      this.ch = true;
    }
  }
  
  public void a(np stat, int amount)
  {
    if ((stat != null) && 
      (stat.f)) {
      super.a(stat, amount);
    }
  }
  
  public void w()
  {
    this.d.a(new iw(this.bJ));
  }
  
  public boolean cJ()
  {
    return true;
  }
  
  protected void C()
  {
    this.d.a(new iy(this, iy.a.f, 
    
      on.d(K() * 100.0F)));
  }
  
  public void D()
  {
    this.d.a(new iy(this, iy.a.h));
  }
  
  public void h(String brand)
  {
    this.ci = brand;
  }
  
  public String E()
  {
    return this.ci;
  }
  
  public nu G()
  {
    return this.bW;
  }
  
  public int I()
  {
    return this.bX;
  }
  
  public void o(int p_184839_1_)
  {
    this.bX = p_184839_1_;
  }
  
  public void b(eu chatComponent)
  {
    this.f.r.d().a(chatComponent);
  }
  
  protected boolean j(double x, double y, double z)
  {
    if (this.Q) {
      return false;
    }
    cj blockpos = new cj(x, y, z);
    double d0 = x - blockpos.p();
    double d1 = z - blockpos.r();
    if (!f(blockpos))
    {
      int i = -1;
      double d2 = 9999.0D;
      if ((f(blockpos.e())) && (d0 < d2))
      {
        d2 = d0;
        i = 0;
      }
      if ((f(blockpos.f())) && (1.0D - d0 < d2))
      {
        d2 = 1.0D - d0;
        i = 1;
      }
      if ((f(blockpos.c())) && (d1 < d2))
      {
        d2 = d1;
        i = 4;
      }
      if ((f(blockpos.d())) && (1.0D - d1 < d2))
      {
        d2 = 1.0D - d1;
        i = 5;
      }
      float f = 0.1F;
      if (i == 0) {
        this.s = (-f);
      }
      if (i == 1) {
        this.s = f;
      }
      if (i == 4) {
        this.u = (-f);
      }
      if (i == 5) {
        this.u = f;
      }
    }
    return false;
  }
  
  private boolean f(cj pos)
  {
    return (!this.l.o(pos).l()) && (!this.l.o(pos.a()).l());
  }
  
  public void e(boolean sprinting)
  {
    super.e(sprinting);
    this.h = 0;
  }
  
  public void a(float currentXP, int maxXP, int level)
  {
    this.bM = currentXP;
    this.bL = maxXP;
    this.bK = level;
  }
  
  public void a(eu component)
  {
    this.f.r.d().a(component);
  }
  
  public boolean a(int permLevel, String commandName)
  {
    return permLevel <= I();
  }
  
  public void a(byte id)
  {
    if ((id >= 24) && (id <= 28)) {
      o(id - 24);
    } else {
      super.a(id);
    }
  }
  
  public cj c()
  {
    return new cj(this.p + 0.5D, this.q + 0.5D, this.r + 0.5D);
  }
  
  public void a(nf soundIn, float volume, float pitch)
  {
    this.l.a(this.p, this.q, this.r, soundIn, 
      bz(), volume, pitch, false);
  }
  
  public boolean co()
  {
    return true;
  }
  
  public void c(qm p_184598_1_)
  {
    adq itemstack = b(p_184598_1_);
    if ((itemstack != null) && (!cs()))
    {
      super.c(p_184598_1_);
      this.cm = true;
      this.cn = p_184598_1_;
    }
  }
  
  public boolean cs()
  {
    return this.cm;
  }
  
  public void cz()
  {
    super.cz();
    this.cm = false;
  }
  
  public qm ct()
  {
    return this.cn;
  }
  
  public void a(ke<?> key)
  {
    super.a(key);
    if (as.equals(key))
    {
      boolean flag = (((Byte)this.Z.a(as)).byteValue() & 0x1) > 0;
      
      qm enumhand = (((Byte)this.Z.a(as)).byteValue() & 0x2) > 0 ? qm.b : qm.a;
      if ((flag) && (!this.cm)) {
        c(enumhand);
      } else if ((!flag) && (this.cm)) {
        cz();
      }
    }
  }
  
  public boolean J()
  {
    rr entity = by();
    
    return (aI()) && ((entity instanceof sj)) && (((sj)entity).b());
  }
  
  public float K()
  {
    return this.ck;
  }
  
  public void a(aqn signTile)
  {
    this.f.a(new bgn(signTile));
  }
  
  public void a(ahj p_184809_1_)
  {
    if (a(2, "")) {
      this.f.a(new bgm(p_184809_1_));
    }
  }
  
  public void a(apy p_184824_1_)
  {
    if (a(2, "")) {
      this.f.a(new bfy(p_184824_1_));
    }
  }
  
  public void a(adq p_184814_1_, qm p_184814_2_)
  {
    ado item = p_184814_1_.b();
    if (item == ads.bW) {
      this.f.a(new bfw(this, p_184814_1_, true));
    }
  }
  
  public void a(qg chestInventory)
  {
    String s = (chestInventory instanceof qn) ? ((qn)chestInventory).k() : "minecraft:container";
    if ("minecraft:chest".equals(s)) {
      this.f.a(new bfz(this.br, chestInventory));
    } else if ("minecraft:hopper".equals(s)) {
      this.f.a(new bgi(this.br, chestInventory));
    } else if ("minecraft:furnace".equals(s)) {
      this.f.a(new bgh(this.br, chestInventory));
    } else if ("minecraft:brewing_stand".equals(s)) {
      this.f.a(new bfx(this.br, chestInventory));
    } else if ("minecraft:beacon".equals(s)) {
      this.f.a(new bfv(this.br, chestInventory));
    } else if ((!"minecraft:dispenser".equals(s)) && 
      (!"minecraft:dropper".equals(s))) {
      this.f.a(new bfz(this.br, chestInventory));
    } else {
      this.f.a(new bgd(this.br, chestInventory));
    }
  }
  
  public void a(wk p_184826_1_, qg p_184826_2_)
  {
    this.f.a(new bgj(this.br, p_184826_2_, p_184826_1_));
  }
  
  public void a(qn guiOwner)
  {
    String s = guiOwner.k();
    if ("minecraft:crafting_table".equals(s)) {
      this.f.a(new bga(this.br, this.l));
    } else if ("minecraft:enchanting_table".equals(s)) {
      this.f.a(new bgg(this.br, this.l, guiOwner));
    } else if ("minecraft:anvil".equals(s)) {
      this.f.a(new bfu(this.br, this.l));
    }
  }
  
  public void a(ahf villager)
  {
    this.f.a(new bgl(this.br, villager, this.l));
  }
  
  public void a(rr entityHit)
  {
    this.f.j.a(entityHit, cy.j);
  }
  
  public void b(rr entityHit)
  {
    this.f.j.a(entityHit, cy.k);
  }
  
  public boolean aK()
  {
    boolean flag = this.e != null ? this.e.h : false;
    
    return (flag) && (!this.bF);
  }
  
  public void cm()
  {
    super.cm();
    if (L())
    {
      this.bd = this.e.a;
      this.be = this.e.b;
      this.bc = this.e.g;
      this.bS = this.bQ;
      this.bT = this.bR;
      this.bR = ((float)(this.bR + (this.w - this.bR) * 0.5D));
      this.bQ = ((float)(this.bQ + (this.v - this.bQ) * 0.5D));
    }
  }
  
  protected boolean L()
  {
    return this.f.aa() == this;
  }
  
  private CustomMovementInput customMovementInput = new CustomMovementInput();
  
  public void n()
  {
    this.h += 1;
    if (this.g > 0) {
      this.g -= 1;
    }
    this.bV = this.bU;
    if (this.ak)
    {
      if ((this.f.m != null) && (!this.f.m.d())) {
        this.f.a((bfb)null);
      }
      if (this.bU == 0.0F) {
        this.f.U().a(bye.a(ng.en, this.S.nextFloat() * 0.4F + 0.8F));
      }
      this.bU += 0.0125F;
      if (this.bU >= 1.0F) {
        this.bU = 1.0F;
      }
      this.ak = false;
    }
    else if ((a(rm.i)) && (b(rm.i).b() > 60))
    {
      this.bU += 0.006666667F;
      if (this.bU > 1.0F) {
        this.bU = 1.0F;
      }
    }
    else
    {
      if (this.bU > 0.0F) {
        this.bU -= 0.05F;
      }
      if (this.bU < 0.0F) {
        this.bU = 0.0F;
      }
    }
    if (this.aj > 0) {
      this.aj -= 1;
    }
    boolean isJumping = this.e.g;
    
    float minSpeed = 0.8F;
    boolean isMovingForward = this.e.b >= minSpeed;
    this.customMovementInput.update(this.f, (bms)this.e, this);
    if ((cs()) && (!aI()))
    {
      this.e.a *= 0.2F;
      this.e.b *= 0.2F;
      this.g = 0;
    }
    bbh axisalignedbb = bl();
    j(this.p - this.G * 0.35D, axisalignedbb.b + 0.5D, this.r + this.G * 0.35D);
    j(this.p - this.G * 0.35D, axisalignedbb.b + 0.5D, this.r - this.G * 0.35D);
    j(this.p + this.G * 0.35D, axisalignedbb.b + 0.5D, this.r - this.G * 0.35D);
    j(this.p + this.G * 0.35D, axisalignedbb.b + 0.5D, this.r + this.G * 0.35D);
    
    boolean enoughHunger = (cS().a() > 6.0F) || (this.bJ.b);
    
    boolean isSprintDisabled = !ToggleSneakMod.optionToggleSprint;
    boolean canDoubleTap = ToggleSneakMod.optionDoubleTap;
    if (ToggleSneakMod.wasSprintDisabled)
    {
      e(false);
      this.customMovementInput.UpdateSprint(false, false);
      ToggleSneakMod.wasSprintDisabled = false;
    }
    if (isSprintDisabled)
    {
      if ((ToggleSneakMod.optionDoubleTap) && (this.z) && (!isMovingForward) && (this.e.b >= minSpeed)) {
        if ((!aL()) && (enoughHunger) && 
          (!cs()) && 
          (!a(rm.s))) {
          if ((this.g <= 0) && 
            (!this.f.u.V.e()))
          {
            this.g = 7;
          }
          else
          {
            e(true);
            this.customMovementInput.UpdateSprint(true, false);
          }
        }
      }
      if ((!aL()) && (this.e.b >= minSpeed) && (enoughHunger)) {
        if ((!cs()) && 
          (!a(rm.s)) && 
          (this.f.u.V.e()))
        {
          e(true);
          this.customMovementInput.UpdateSprint(true, false);
        }
      }
    }
    else
    {
      boolean state = this.customMovementInput.sprint;
      if ((enoughHunger) && (!cs()) && 
        (!a(rm.s)) && (!this.customMovementInput.sprintHeldAndReleased)) {
        if (((canDoubleTap) && (!aL())) || (!canDoubleTap)) {
          e(state);
        }
      }
      if ((canDoubleTap) && (!state) && (this.z) && (!isMovingForward) && (this.e.b >= minSpeed)) {
        if ((!aL()) && (enoughHunger) && 
          (!cs()) && 
          (!a(rm.s))) {
          if (this.g == 0)
          {
            this.g = 7;
          }
          else
          {
            e(true);
            this.customMovementInput.UpdateSprint(true, true);
            this.g = 0;
          }
        }
      }
    }
    if ((aL()) && ((this.e.b < minSpeed) || (this.A) || (!enoughHunger)))
    {
      e(false);
      if ((this.customMovementInput.sprintHeldAndReleased == true) || (isSprintDisabled) || (this.customMovementInput.sprintDoubleTapped) || (this.bJ.b) || 
      
        (aI())) {
        this.customMovementInput.UpdateSprint(false, false);
      }
    }
    if ((ToggleSneakMod.optionEnableFlyBoost) && (this.bJ.b)) {
      if ((this.f.u.V.e()) && (this.bJ.d))
      {
        this.bJ.a(0.05F * (float)ToggleSneakMod.optionFlyBoostAmount);
        if (this.e.h) {
          this.t -= 0.15D * ToggleSneakMod.optionFlyBoostAmount;
        }
        if (!this.e.g) {
          break label1170;
        }
        this.t += 0.15D * ToggleSneakMod.optionFlyBoostAmount;
        break label1170;
      }
    }
    if (this.bJ.a() != 0.05F) {
      this.bJ.a(0.05F);
    }
    label1170:
    if (this.bJ.c) {
      if (this.f.c.k())
      {
        if (!this.bJ.b)
        {
          this.bJ.b = true;
          w();
        }
      }
      else if ((!isJumping) && (this.e.g)) {
        if (this.bv == 0)
        {
          this.bv = 7;
        }
        else
        {
          this.bJ.b = (!this.bJ.b);
          w();
          this.bv = 0;
        }
      }
    }
    if ((this.e.g) && (!isJumping) && (!this.z) && (this.t < 0.0D) && (!cB()) && (!this.bJ.b))
    {
      adq itemstack = a(rw.e);
      if ((itemstack != null) && (itemstack.b() == ads.cR) && (acx.d(itemstack))) {
        this.d.a(new iy(this, iy.a.i));
      }
    }
    if ((this.bJ.b) && (L()))
    {
      if (this.e.h)
      {
        this.e.a = ((float)(this.e.a / 0.3D));
        this.e.b = ((float)(this.e.b / 0.3D));
        this.t -= this.bJ.a() * 3.0F;
      }
      if (this.e.g) {
        this.t += this.bJ.a() * 3.0F;
      }
    }
    if (J())
    {
      sj ijumpingmount = (sj)by();
      if (this.cj < 0)
      {
        this.cj += 1;
        if (this.cj == 0) {
          this.ck = 0.0F;
        }
      }
      if ((isJumping) && (!this.e.g))
      {
        this.cj = -10;
        ijumpingmount.a_(on.d(K() * 100.0F));
        C();
      }
      else if ((!isJumping) && (this.e.g))
      {
        this.cj = 0;
        this.ck = 0.0F;
      }
      else if (isJumping)
      {
        this.cj += 1;
        if (this.cj < 10) {
          this.ck = (this.cj * 0.1F);
        } else {
          this.ck = (0.8F + 2.0F / (this.cj - 9) * 0.1F);
        }
      }
    }
    else
    {
      this.ck = 0.0F;
    }
    super.n();
    if ((this.z) && (this.bJ.b) && (!this.f.c.k()))
    {
      this.bJ.b = false;
      w();
    }
  }
  
  public void aw()
  {
    super.aw();
    this.co = false;
    if ((by() instanceof aag))
    {
      aag entityboat = (aag)by();
      entityboat.a(this.e.e, this.e.f, this.e.c, this.e.d);
      
      this.co |= ((this.e.e) || (this.e.f) || (this.e.c) || (this.e.d));
    }
  }
  
  public boolean M()
  {
    return this.co;
  }
  
  public rl c(rk p_184596_1_)
  {
    if (p_184596_1_ == rm.i)
    {
      this.bV = 0.0F;
      this.bU = 0.0F;
    }
    return super.c(p_184596_1_);
  }
}
