import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.ModSettings;
import de.labystudio.minecraft.ThePlayerMod;
import de.labystudio.utils.Allowed;
import io.netty.buffer.Unpooled;

public class bkt
{
  private final bcf a;
  private final bks b;
  private cj c = new cj(-1, -1, -1);
  private adq d;
  private float e;
  private float f;
  private int g;
  private boolean h;
  private ahw.a i = ahw.a.b;
  private int j;
  
  public bkt(bcf mcIn, bks netHandler)
  {
    this.a = mcIn;
    this.b = netHandler;
  }
  
  public static void a(bcf mcIn, bkt playerController, cj pos, cq facing)
  {
    if (!mcIn.f.a(mcIn.h, pos, facing)) {
      playerController.a(pos);
    }
  }
  
  public void a(zj player)
  {
    this.i.a(player.bJ);
  }
  
  public boolean a()
  {
    return this.i == ahw.a.e;
  }
  
  public void a(ahw.a type)
  {
    this.i = type;
    this.i.a(this.a.h.bJ);
  }
  
  public void b(zj playerIn)
  {
    playerIn.v = -180.0F;
  }
  
  public boolean b()
  {
    return this.i.e();
  }
  
  public boolean a(cj p_187103_1_)
  {
    if (this.i.c())
    {
      if (this.i == ahw.a.e) {
        return false;
      }
      if (!this.a.h.cU())
      {
        adq itemstack = this.a.h.cb();
        if (itemstack == null) {
          return false;
        }
        if (!itemstack.a(this.a.f.o(p_187103_1_).t())) {
          return false;
        }
      }
    }
    if ((this.i.d()) && (this.a.h.cb() != null) && ((this.a.h.cb().b() instanceof aex))) {
      return false;
    }
    aht world = this.a.f;
    arc iblockstate = world.o(p_187103_1_);
    ajt block = iblockstate.t();
    if (((block instanceof akk)) && (!this.a.h.a(2, ""))) {
      return false;
    }
    if (iblockstate.a() == axe.a) {
      return false;
    }
    world.b(2001, p_187103_1_, ajt.j(iblockstate));
    block.a(world, p_187103_1_, iblockstate, this.a.h);
    boolean flag = world.a(p_187103_1_, aju.a.u(), 11);
    if (flag) {
      block.d(world, p_187103_1_, iblockstate);
    }
    this.c = new cj(this.c.p(), -1, this.c.r());
    if (!this.i.d())
    {
      adq itemstack1 = this.a.h.cb();
      if (itemstack1 != null)
      {
        itemstack1.a(world, iblockstate, p_187103_1_, this.a.h);
        if (itemstack1.b == 0) {
          this.a.h.a(qm.a, (adq)null);
        }
      }
    }
    return flag;
  }
  
  public boolean a(cj loc, cq face)
  {
    if (this.i.c())
    {
      if (this.i == ahw.a.e) {
        return false;
      }
      if (!this.a.h.cU())
      {
        adq itemstack = this.a.h.cb();
        if (itemstack == null) {
          return false;
        }
        if (!itemstack.a(this.a.f.o(loc).t())) {
          return false;
        }
      }
    }
    if (!this.a.f.aj().a(loc)) {
      return false;
    }
    if (this.i.d())
    {
      this.b.a(new ix(ix.a.a, loc, face));
      a(this.a, this, loc, face);
      this.g = 5;
    }
    else if ((!this.h) || (!b(loc)))
    {
      if (this.h) {
        this.b.a(new ix(ix.a.b, this.c, face));
      }
      this.b.a(new ix(ix.a.a, loc, face));
      arc iblockstate = this.a.f.o(loc);
      boolean flag = iblockstate.a() != axe.a;
      if ((flag) && (this.e == 0.0F)) {
        iblockstate.t().a(this.a.f, loc, this.a.h);
      }
      if ((flag) && (iblockstate.a(this.a.h, this.a.h.l, loc) >= 1.0F))
      {
        a(loc);
      }
      else
      {
        this.h = true;
        this.c = loc;
        this.d = this.a.h.cb();
        this.e = 0.0F;
        this.f = 0.0F;
        this.a.f.c(this.a.h.O(), this.c, (int)(this.e * 10.0F) - 1);
      }
    }
    return true;
  }
  
  public void c()
  {
    if (this.h)
    {
      this.b.a(new ix(ix.a.b, this.c, cq.a));
      this.h = false;
      this.e = 0.0F;
      this.a.f.c(this.a.h.O(), this.c, -1);
      this.a.h.cZ();
    }
  }
  
  public boolean b(cj posBlock, cq directionFacing)
  {
    if ((ConfigManager.settings.oldBlockBuild) && (Allowed.blockBuild()) && (this.a.h != null) && (this.a.h.isUsingItem())) {
      return true;
    }
    n();
    if (this.g > 0)
    {
      this.g -= 1;
      return true;
    }
    if ((this.i.d()) && (this.a.f.aj().a(posBlock)))
    {
      this.g = 5;
      this.b.a(new ix(ix.a.a, posBlock, directionFacing));
      a(this.a, this, posBlock, directionFacing);
      return true;
    }
    if (b(posBlock))
    {
      arc iblockstate = this.a.f.o(posBlock);
      ajt block = iblockstate.t();
      if (iblockstate.a() == axe.a)
      {
        this.h = false;
        return false;
      }
      this.e += iblockstate.a(this.a.h, this.a.h.l, posBlock);
      if (this.f % 4.0F == 0.0F)
      {
        aop soundtype = block.w();
        this.a.U().a(new bye(soundtype.f(), nh.g, (soundtype.a() + 1.0F) / 8.0F, soundtype.b() * 0.5F, posBlock));
      }
      this.f += 1.0F;
      if (this.e >= 1.0F)
      {
        this.h = false;
        this.b.a(new ix(ix.a.c, posBlock, directionFacing));
        a(posBlock);
        this.e = 0.0F;
        this.f = 0.0F;
        this.g = 5;
      }
      this.a.f.c(this.a.h.O(), this.c, (int)(this.e * 10.0F) - 1);
      return true;
    }
    return a(posBlock, directionFacing);
  }
  
  public float d()
  {
    return this.i.d() ? 5.0F : 4.5F;
  }
  
  public void e()
  {
    n();
    if (this.b.a().g()) {
      this.b.a().a();
    } else {
      this.b.a().l();
    }
  }
  
  private boolean b(cj pos)
  {
    adq itemstack = this.a.h.cb();
    boolean flag = (this.d == null) && (itemstack == null);
    if ((this.d != null) && (itemstack != null)) {
      flag = (itemstack.b() == this.d.b()) && (adq.a(itemstack, this.d)) && ((itemstack.e()) || (itemstack.i() == this.d.i()));
    }
    return (pos.equals(this.c)) && (flag);
  }
  
  private void n()
  {
    int i = this.a.h.br.d;
    if (i != this.j)
    {
      this.j = i;
      this.b.a(new jb(this.j));
    }
  }
  
  public qo a(bmt p_187099_1_, bku p_187099_2_, adq p_187099_3_, cj p_187099_4_, cq p_187099_5_, bbj p_187099_6_, qm p_187099_7_)
  {
    n();
    float f = (float)(p_187099_6_.b - p_187099_4_.p());
    float f1 = (float)(p_187099_6_.c - p_187099_4_.q());
    float f2 = (float)(p_187099_6_.d - p_187099_4_.r());
    boolean flag = false;
    if (!this.a.f.aj().a(p_187099_4_)) {
      return qo.c;
    }
    if (this.i != ahw.a.e)
    {
      arc iblockstate = p_187099_2_.o(p_187099_4_);
      if (((!p_187099_1_.aK()) || ((p_187099_1_.cb() == null) && (p_187099_1_.cc() == null))) && (iblockstate.t().a(p_187099_2_, p_187099_4_, iblockstate, p_187099_1_, p_187099_7_, p_187099_3_, p_187099_5_, f, f1, f2))) {
        flag = true;
      }
      if ((!flag) && (p_187099_3_ != null) && ((p_187099_3_.b() instanceof acc)))
      {
        acc itemblock = (acc)p_187099_3_.b();
        if (!itemblock.a(p_187099_2_, p_187099_4_, p_187099_5_, p_187099_1_, p_187099_3_)) {
          return qo.c;
        }
      }
    }
    this.b.a(new jg(p_187099_4_, p_187099_5_, p_187099_7_, f, f1, f2));
    if ((!flag) && (this.i != ahw.a.e))
    {
      if (p_187099_3_ == null) {
        return qo.b;
      }
      if (p_187099_1_.da().a(p_187099_3_.b())) {
        return qo.b;
      }
      if (((p_187099_3_.b() instanceof acc)) && ((((acc)p_187099_3_.b()).d() instanceof akk)) && (!p_187099_1_.a(2, ""))) {
        return qo.c;
      }
      if (this.i.d())
      {
        int i = p_187099_3_.i();
        int j = p_187099_3_.b;
        qo enumactionresult = p_187099_3_.a(p_187099_1_, p_187099_2_, p_187099_4_, p_187099_7_, p_187099_5_, f, f1, f2);
        p_187099_3_.b(i);
        p_187099_3_.b = j;
        return enumactionresult;
      }
      return p_187099_3_.a(p_187099_1_, p_187099_2_, p_187099_4_, p_187099_7_, p_187099_5_, f, f1, f2);
    }
    return qo.a;
  }
  
  public qo a(zj p_187101_1_, aht p_187101_2_, adq p_187101_3_, qm p_187101_4_)
  {
    if (this.i == ahw.a.e) {
      return qo.b;
    }
    n();
    this.b.a(new jh(p_187101_4_));
    if (p_187101_1_.da().a(p_187101_3_.b())) {
      return qo.b;
    }
    int i = p_187101_3_.b;
    qp<adq> actionresult = p_187101_3_.a(p_187101_2_, p_187101_1_, p_187101_4_);
    adq itemstack = (adq)actionresult.b();
    if ((itemstack != p_187101_3_) || (itemstack.b != i))
    {
      p_187101_1_.a(p_187101_4_, itemstack);
      if (itemstack.b == 0) {
        p_187101_1_.a(p_187101_4_, (adq)null);
      }
    }
    return actionresult.a();
  }
  
  public bmt a(aht worldIn, nu statWriter)
  {
    return new ThePlayerMod(this.a, worldIn, this.b, statWriter);
  }
  
  public void a(zj playerIn, rr targetEntity)
  {
    n();
    this.b.a(new ir(targetEntity));
    if (this.i != ahw.a.e)
    {
      playerIn.f(targetEntity);
      playerIn.cZ();
    }
  }
  
  public qo a(zj p_187097_1_, rr p_187097_2_, adq p_187097_3_, qm p_187097_4_)
  {
    n();
    this.b.a(new ir(p_187097_2_, p_187097_4_));
    return this.i == ahw.a.e ? qo.b : p_187097_1_.a(p_187097_2_, p_187097_3_, p_187097_4_);
  }
  
  public qo a(zj p_187102_1_, rr p_187102_2_, bbi p_187102_3_, adq p_187102_4_, qm p_187102_5_)
  {
    n();
    bbj vec3d = new bbj(p_187102_3_.c.b - p_187102_2_.p, p_187102_3_.c.c - p_187102_2_.q, p_187102_3_.c.d - p_187102_2_.r);
    this.b.a(new ir(p_187102_2_, p_187102_5_, vec3d));
    return this.i == ahw.a.e ? qo.b : p_187102_2_.a(p_187102_1_, vec3d, p_187102_4_, p_187102_5_);
  }
  
  public adq a(int p_187098_1_, int p_187098_2_, int p_187098_3_, aaz p_187098_4_, zj p_187098_5_)
  {
    short short1 = p_187098_5_.bt.a(p_187098_5_.br);
    adq itemstack = p_187098_5_.bt.a(p_187098_2_, p_187098_3_, p_187098_4_, p_187098_5_);
    this.b.a(new io(p_187098_1_, p_187098_2_, p_187098_3_, p_187098_4_, itemstack, short1));
    return itemstack;
  }
  
  public void a(int windowID, int button)
  {
    this.b.a(new in(windowID, button));
  }
  
  public void a(adq itemStackIn, int slotId)
  {
    if (this.i.d()) {
      this.b.a(new jc(slotId, itemStackIn));
    }
  }
  
  public void a(adq itemStackIn)
  {
    if ((this.i.d()) && (itemStackIn != null)) {
      this.b.a(new jc(-1, itemStackIn));
    }
  }
  
  public void c(zj playerIn)
  {
    n();
    this.b.a(new ix(ix.a.f, cj.a, cq.a));
    playerIn.cy();
  }
  
  public boolean f()
  {
    return this.i.e();
  }
  
  public boolean g()
  {
    return !this.i.d();
  }
  
  public boolean h()
  {
    return this.i.d();
  }
  
  public boolean i()
  {
    return this.i.d();
  }
  
  public boolean j()
  {
    return (this.a.h.aI()) && ((this.a.h.by() instanceof wk));
  }
  
  public boolean k()
  {
    return this.i == ahw.a.e;
  }
  
  public ahw.a l()
  {
    return this.i;
  }
  
  public boolean m()
  {
    if ((ConfigManager.settings.oldBlockBuild) && (Allowed.blockBuild())) {
      return false;
    }
    return this.h;
  }
  
  public void a(int p_187100_1_)
  {
    this.b.a(new iq("MC|PickItem", new em(Unpooled.buffer()).b(p_187100_1_)));
  }
}
