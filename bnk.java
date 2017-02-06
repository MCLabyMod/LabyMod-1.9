import com.google.common.base.Objects;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.ModSettings;
import de.labystudio.utils.Allowed;

public class bnk
{
  private static final kk a = new kk("textures/map/map_background.png");
  private static final kk b = new kk("textures/misc/underwater.png");
  private final bcf c;
  private adq d;
  private adq e;
  private float f;
  private float g;
  private float h;
  private float i;
  private final brm j;
  private final brz k;
  
  public bnk(bcf mcIn)
  {
    this.c = mcIn;
    this.j = mcIn.ac();
    this.k = mcIn.ad();
  }
  
  public void a(sa entityIn, adq heldStack, bos.b transform)
  {
    a(entityIn, heldStack, transform, false);
  }
  
  public void a(sa p_187462_1_, adq p_187462_2_, bos.b p_187462_3_, boolean p_187462_4_)
  {
    if (p_187462_2_ != null)
    {
      ado item = p_187462_2_.b();
      ajt block = ajt.a(item);
      bni.G();
      boolean flag = (this.k.a(p_187462_2_)) && (a(block));
      if (flag) {
        bni.a(false);
      }
      this.k.a(p_187462_2_, p_187462_1_, p_187462_3_, p_187462_4_);
      if (flag) {
        bni.a(true);
      }
      bni.H();
    }
  }
  
  private boolean a(ajt blockIn)
  {
    return (blockIn != null) && (blockIn.f() == ahm.d);
  }
  
  private void a(float angle, float angleY)
  {
    bni.G();
    bni.b(angle, 1.0F, 0.0F, 0.0F);
    bni.b(angleY, 0.0F, 1.0F, 0.0F);
    bcd.b();
    bni.H();
  }
  
  private void b()
  {
    bmq abstractclientplayer = this.c.h;
    int i = this.c.f.b(new cj(abstractclientplayer.p, abstractclientplayer.q + abstractclientplayer.bn(), abstractclientplayer.r), 0);
    float f = i & 0xFFFF;
    float f1 = i >> 16;
    bzg.a(bzg.r, f, f1);
  }
  
  private void c(float p_187458_1_)
  {
    bmt entityplayersp = this.c.h;
    float f = entityplayersp.bT + (entityplayersp.bR - entityplayersp.bT) * p_187458_1_;
    float f1 = entityplayersp.bS + (entityplayersp.bQ - entityplayersp.bS) * p_187458_1_;
    bni.b((entityplayersp.w - f) * 0.1F, 1.0F, 0.0F, 0.0F);
    bni.b((entityplayersp.v - f1) * 0.1F, 0.0F, 1.0F, 0.0F);
  }
  
  private float d(float pitch)
  {
    float f = 1.0F - pitch / 45.0F + 0.1F;
    f = on.a(f, 0.0F, 1.0F);
    f = -on.b(f * 3.1415927F) * 0.5F + 0.5F;
    return f;
  }
  
  private void c()
  {
    if (!this.c.h.aN())
    {
      bni.r();
      bni.G();
      bni.b(90.0F, 0.0F, 1.0F, 0.0F);
      a(rz.b);
      a(rz.a);
      bni.H();
      bni.q();
    }
  }
  
  private void a(rz p_187455_1_)
  {
    this.c.N().a(this.c.h.o());
    brn<bmq> render = this.j.a(this.c.h);
    buk renderplayer = (buk)render;
    bni.G();
    float f = p_187455_1_ == rz.b ? 1.0F : -1.0F;
    bni.b(92.0F, 0.0F, 1.0F, 0.0F);
    bni.b(45.0F, 1.0F, 0.0F, 0.0F);
    bni.b(f * -41.0F, 0.0F, 0.0F, 1.0F);
    bni.c(f * 0.3F, -1.1F, 0.45F);
    if (p_187455_1_ == rz.b) {
      renderplayer.b(this.c.h);
    } else {
      renderplayer.c(this.c.h);
    }
    bni.H();
  }
  
  private void a(float p_187465_1_, rz p_187465_2_, float p_187465_3_, adq p_187465_4_)
  {
    float f = p_187465_2_ == rz.b ? 1.0F : -1.0F;
    bni.c(f * 0.125F, -0.125F, 0.0F);
    if (!this.c.h.aN())
    {
      bni.G();
      bni.b(f * 10.0F, 0.0F, 0.0F, 1.0F);
      a(p_187465_1_, p_187465_3_, p_187465_2_);
      bni.H();
    }
    bni.G();
    bni.c(f * 0.51F, -0.08F + p_187465_1_ * -1.2F, -0.75F);
    float f1 = on.c(p_187465_3_);
    float f2 = on.a(f1 * 3.1415927F);
    float f3 = -0.5F * f2;
    float f4 = 0.4F * on.a(f1 * 6.2831855F);
    float f5 = -0.3F * on.a(p_187465_3_ * 3.1415927F);
    bni.c(f * f3, f4 - 0.3F * f2, f5);
    bni.b(f2 * -45.0F, 1.0F, 0.0F, 0.0F);
    bni.b(f * f2 * -30.0F, 0.0F, 1.0F, 0.0F);
    a(p_187465_4_);
    bni.H();
  }
  
  private void a(float p_187463_1_, float p_187463_2_, float p_187463_3_)
  {
    float f = on.c(p_187463_3_);
    float f1 = -0.2F * on.a(p_187463_3_ * 3.1415927F);
    float f2 = -0.4F * on.a(f * 3.1415927F);
    bni.c(0.0F, -f1 / 2.0F, f2);
    float f3 = d(p_187463_1_);
    bni.c(0.0F, 0.04F + p_187463_2_ * -1.2F + f3 * -0.5F, -0.72F);
    bni.b(f3 * -85.0F, 1.0F, 0.0F, 0.0F);
    c();
    float f4 = on.a(f * 3.1415927F);
    bni.b(f4 * 20.0F, 1.0F, 0.0F, 0.0F);
    bni.b(2.0F, 2.0F, 2.0F);
    a(this.d);
  }
  
  private void a(adq p_187461_1_)
  {
    bni.b(180.0F, 0.0F, 1.0F, 0.0F);
    bni.b(180.0F, 0.0F, 0.0F, 1.0F);
    bni.b(0.38F, 0.38F, 0.38F);
    bni.g();
    this.c.N().a(a);
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    bni.c(-0.5F, -0.5F, 0.0F);
    bni.b(0.0078125F, 0.0078125F, 0.0078125F);
    vertexbuffer.a(7, bvp.g);
    vertexbuffer.b(-7.0D, 135.0D, 0.0D).a(0.0D, 1.0D).d();
    vertexbuffer.b(135.0D, 135.0D, 0.0D).a(1.0D, 1.0D).d();
    vertexbuffer.b(135.0D, -7.0D, 0.0D).a(1.0D, 0.0D).d();
    vertexbuffer.b(-7.0D, -7.0D, 0.0D).a(0.0D, 0.0D).d();
    tessellator.b();
    ayz mapdata = ads.bk.a(p_187461_1_, this.c.f);
    if (mapdata != null) {
      this.c.o.k().a(mapdata, false);
    }
    bni.f();
  }
  
  private void a(float p_187456_1_, float p_187456_2_, rz p_187456_3_)
  {
    boolean flag = p_187456_3_ != rz.a;
    float f = flag ? 1.0F : -1.0F;
    float f1 = on.c(p_187456_2_);
    float f2 = -0.3F * on.a(f1 * 3.1415927F);
    float f3 = 0.4F * on.a(f1 * 6.2831855F);
    float f4 = -0.4F * on.a(p_187456_2_ * 3.1415927F);
    bni.c(f * (f2 + 0.64000005F), f3 + -0.6F + p_187456_1_ * -0.6F, f4 + -0.71999997F);
    bni.b(f * 45.0F, 0.0F, 1.0F, 0.0F);
    float f5 = on.a(p_187456_2_ * p_187456_2_ * 3.1415927F);
    float f6 = on.a(f1 * 3.1415927F);
    bni.b(f * f6 * 70.0F, 0.0F, 1.0F, 0.0F);
    bni.b(f * f5 * -20.0F, 0.0F, 0.0F, 1.0F);
    bmq abstractclientplayer = this.c.h;
    this.c.N().a(abstractclientplayer.o());
    bni.c(f * -1.0F, 3.6F, 3.5F);
    bni.b(f * 120.0F, 0.0F, 0.0F, 1.0F);
    bni.b(200.0F, 1.0F, 0.0F, 0.0F);
    bni.b(f * -135.0F, 0.0F, 1.0F, 0.0F);
    bni.c(f * 5.6F, 0.0F, 0.0F);
    buk renderplayer = (buk)this.j.a(abstractclientplayer);
    bni.r();
    if (flag) {
      renderplayer.b(abstractclientplayer);
    } else {
      renderplayer.c(abstractclientplayer);
    }
    bni.q();
  }
  
  private void a(float p_187454_1_, rz p_187454_2_, adq p_187454_3_)
  {
    float f = this.c.h.cw() - p_187454_1_ + 1.0F;
    float f1 = f / p_187454_3_.l();
    if (f1 < 0.8F)
    {
      float f2 = on.e(on.b(f / 4.0F * 3.1415927F) * 0.1F);
      bni.c(0.0F, f2, 0.0F);
    }
    float f3 = 1.0F - (float)Math.pow(f1, 27.0D);
    int i = p_187454_2_ == rz.b ? 1 : -1;
    bni.c(f3 * 0.6F * i, f3 * -0.5F, f3 * 0.0F);
    bni.b(i * f3 * 90.0F, 0.0F, 1.0F, 0.0F);
    bni.b(f3 * 10.0F, 1.0F, 0.0F, 0.0F);
    bni.b(i * f3 * 30.0F, 0.0F, 0.0F, 1.0F);
  }
  
  private void a(rz p_187453_1_, float p_187453_2_)
  {
    if (ConfigManager.settings.oldHit)
    {
      int i = p_187453_1_ == rz.b ? 1 : -1;
      float f = on.a(p_187453_2_ * p_187453_2_ * 3.1415927F);
      bni.b(i * (45.0F + f * -20.0F), 0.0F, 1.0F, 0.0F);
      float f1 = on.a(on.c(p_187453_2_) * 3.1415927F);
      bni.b(i * f1 * -10.0F, 0.0F, 0.0F, 1.0F);
      bni.b(f1 * -50.0F, 1.0F, 0.0F, 0.0F);
      bni.b(i * -45.0F, 0.0F, 1.0F, 0.0F);
    }
    else
    {
      int i = p_187453_1_ == rz.b ? 1 : -1;
      float f = on.a(p_187453_2_ * p_187453_2_ * 3.1415927F);
      bni.b(i * (45.0F + f * -20.0F), 0.0F, 1.0F, 0.0F);
      float f1 = on.a(on.c(p_187453_2_) * 3.1415927F);
      bni.b(i * f1 * -20.0F, 0.0F, 0.0F, 1.0F);
      bni.b(f1 * -80.0F, 1.0F, 0.0F, 0.0F);
      bni.b(i * -45.0F, 0.0F, 1.0F, 0.0F);
    }
  }
  
  private void b(rz p_187459_1_, float p_187459_2_)
  {
    if ((this.c != null) && (this.c.h != null))
    {
      adq itemStack = this.c.h.cb();
      boolean flag = p_187459_1_ != bcf.z().u.A;
      if (flag) {
        itemStack = this.c.h.cc();
      }
      if ((ConfigManager.settings.oldBow.booleanValue()) && (Allowed.animations()) && 
        (itemStack != null) && (itemStack.b() != null) && 
        (ado.a(itemStack.b()) == 261)) {
        if (flag) {
          bni.c(-0.0F, 0.0F, -0.08F);
        } else {
          bni.c(0.0F, 0.0F, -0.08F);
        }
      }
      if ((ConfigManager.settings.oldFishing) && (Allowed.animations()) && 
        (itemStack != null) && (itemStack.b() != null) && 
        (ado.a(itemStack.b()) == 346)) {
        if (flag) {
          bni.c(-0.1F, -0.02F, -0.335F);
        } else {
          bni.c(0.1F, -0.02F, -0.335F);
        }
      }
      if ((ConfigManager.settings.oldBlockhit.booleanValue()) && (itemStack != null) && ((itemStack.b() instanceof aex)) && (this.c.h.isBlocking(p_187459_1_))) {
        p_187459_2_ = 0.0F;
      }
    }
    int i = p_187459_1_ == rz.b ? 1 : -1;
    bni.c(i * 0.56F, -0.52F + p_187459_2_ * -0.6F, -0.72F);
  }
  
  public void a(float partialTicks)
  {
    bmq abstractclientplayer = this.c.h;
    float f = abstractclientplayer.m(partialTicks);
    qm enumhand = (qm)Objects.firstNonNull(abstractclientplayer.au, qm.a);
    float f1 = abstractclientplayer.y + (abstractclientplayer.w - abstractclientplayer.y) * partialTicks;
    float f2 = abstractclientplayer.x + (abstractclientplayer.v - abstractclientplayer.x) * partialTicks;
    boolean flag = true;
    boolean flag1 = true;
    if (abstractclientplayer.cs())
    {
      adq itemstack = abstractclientplayer.cv();
      if (itemstack.b() == ads.f)
      {
        qm enumhand1 = abstractclientplayer.ct();
        flag = enumhand1 == qm.a;
        flag1 = !flag;
      }
    }
    a(f1, f2);
    b();
    c(partialTicks);
    bni.D();
    if (flag)
    {
      float f3 = enumhand == qm.a ? f : 0.0F;
      float f5 = 1.0F - (this.g + (this.f - this.g) * partialTicks);
      a(abstractclientplayer, partialTicks, f1, qm.a, f3, this.d, f5);
    }
    if (flag1)
    {
      float f4 = enumhand == qm.b ? f : 0.0F;
      float f6 = 1.0F - (this.i + (this.h - this.i) * partialTicks);
      a(abstractclientplayer, partialTicks, f1, qm.b, f4, this.e, f6);
    }
    bni.E();
    bcd.a();
  }
  
  private void func_178103_d(rz enumhandside)
  {
    bni.c(-0.5F, 0.2F, 0.0F);
    bni.b(30.0F, 0.0F, 1.0F, 0.0F);
    bni.b(-80.0F, 1.0F, 0.0F, 0.0F);
    bni.b(60.0F, 0.0F, 1.0F, 0.0F);
    
    bni.c(-0.75F, -0.562F, -0.203F);
    bni.b(10.0F, 0.0F, 1.0F, 0.0F);
    bni.b(-45.0F, 1.0F, 1.0F, 0.0F);
    bni.b(30.0F, 0.0F, 0.0F, 1.0F);
    if (enumhandside == rz.a)
    {
      bni.b(2.0D, -0.22D, 0.17D);
      bni.b(115.0F, 1.0F, 0.0F, 0.0F);
      bni.b(3.0F, 3.0F, 3.0F);
    }
  }
  
  public void a(bmq p_187457_1_, float p_187457_2_, float p_187457_3_, qm p_187457_4_, float p_187457_5_, adq p_187457_6_, float p_187457_7_)
  {
    boolean flag = p_187457_4_ == qm.a;
    rz enumhandside = flag ? p_187457_1_.cr() : p_187457_1_.cr().a();
    bni.G();
    
    boolean flag1 = enumhandside == rz.b;
    
    float f = -0.4F * on.a(on.c(p_187457_5_) * 3.1415927F);
    float f1 = 0.2F * on.a(on.c(p_187457_5_) * 6.2831855F);
    float f2 = -0.2F * on.a(p_187457_5_ * 3.1415927F);
    int i = flag1 ? 1 : -1;
    if (p_187457_6_ == null)
    {
      if ((flag) && (!p_187457_1_.aN())) {
        a(p_187457_7_, p_187457_5_, enumhandside);
      }
    }
    else if (p_187457_6_.b() == ads.bk)
    {
      if ((flag) && (this.e == null)) {
        a(p_187457_3_, p_187457_7_, p_187457_5_);
      } else {
        a(p_187457_7_, enumhandside, p_187457_5_, p_187457_6_);
      }
    }
    else
    {
      boolean flag3 = false;
      if (((p_187457_6_.b() instanceof aex)) && (ConfigManager.settings.animationSword != 2) && 
        (Allowed.animations()) && (Allowed.blocking()))
      {
        if ((!ConfigManager.settings.oldBlockhit.booleanValue()) && (p_187457_1_.isBlocking(enumhandside)))
        {
          b(enumhandside, 0.0F);
          a(enumhandside, 0.0F);
        }
        else
        {
          b(enumhandside, p_187457_7_);
          a(enumhandside, p_187457_5_);
        }
        flag3 = true;
      }
      afa action = p_187457_6_.m();
      qm pri = p_187457_1_.ct();
      if ((p_187457_1_.cs()) && (p_187457_1_.cw() > 0) && (pri == p_187457_4_))
      {
        int j = flag1 ? 1 : -1;
        switch (action)
        {
        case a: 
          b(enumhandside, p_187457_7_);
          break;
        case b: 
        case c: 
          a(p_187457_2_, enumhandside, p_187457_6_);
          b(enumhandside, p_187457_7_);
          break;
        case d: 
          if (!ConfigManager.settings.oldBlockhit.booleanValue()) {
            b(enumhandside, p_187457_7_);
          } else {
            b(enumhandside, p_187457_7_);
          }
          if ((p_187457_6_.b() instanceof aex)) {
            func_178103_d(enumhandside);
          }
          break;
        case e: 
          b(enumhandside, p_187457_7_);
          bni.c(j * -0.2785682F, 0.18344387F, 0.15731531F);
          bni.b(-13.935F, 1.0F, 0.0F, 0.0F);
          bni.b(j * 35.3F, 0.0F, 1.0F, 0.0F);
          bni.b(j * -9.785F, 0.0F, 0.0F, 1.0F);
          float f5 = p_187457_6_.l() - (this.c.h.cw() - p_187457_2_ + 1.0F);
          float f6 = f5 / 20.0F;
          f6 = (f6 * f6 + f6 * 2.0F) / 3.0F;
          if (f6 > 1.0F) {
            f6 = 1.0F;
          }
          if (f6 > 0.1F)
          {
            float f7 = on.a((f5 - 0.1F) * 1.3F);
            float f3 = f6 - 0.1F;
            float f4 = f7 * f3;
            bni.c(f4 * 0.0F, f4 * 0.004F, f4 * 0.0F);
          }
          bni.c(f6 * 0.0F, f6 * 0.0F, f6 * 0.04F);
          bni.b(1.0F, 1.0F, 1.0F + f6 * 0.2F);
          bni.b(j * 45.0F, 0.0F, -1.0F, 0.0F);
        }
      }
      else
      {
        if (!flag3)
        {
          b(enumhandside, p_187457_7_);
          a(enumhandside, p_187457_5_);
        }
        bni.c(i * f, f1, f2);
      }
      a(p_187457_1_, p_187457_6_, flag1 ? bos.b.e : bos.b.d, !flag1);
    }
    bni.H();
  }
  
  public void b(float partialTicks)
  {
    
    if (this.c.h.av())
    {
      arc iblockstate = this.c.f.o(new cj(this.c.h));
      zj entityplayer = this.c.h;
      for (int i = 0; i < 8; i++)
      {
        double d0 = entityplayer.p + ((i >> 0) % 2 - 0.5F) * entityplayer.G * 0.8F;
        double d1 = entityplayer.q + ((i >> 1) % 2 - 0.5F) * 0.1F;
        double d2 = entityplayer.r + ((i >> 2) % 2 - 0.5F) * entityplayer.G * 0.8F;
        cj blockpos = new cj(d0, d1 + entityplayer.bn(), d2);
        arc iblockstate1 = this.c.f.o(blockpos);
        if (iblockstate1.t().j()) {
          iblockstate = iblockstate1;
        }
      }
      if (iblockstate.i() != aob.a) {
        a(partialTicks, this.c.ab().a().a(iblockstate));
      }
    }
    if (!this.c.h.y())
    {
      if (this.c.h.a(axe.h)) {
        e(partialTicks);
      }
      if (this.c.h.aH()) {
        f(partialTicks);
      }
    }
    bni.e();
  }
  
  private void a(float partialTicks, bvh atlas)
  {
    this.c.N().a(bvg.g);
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    float f = 0.1F;
    bni.c(0.1F, 0.1F, 0.1F, 0.5F);
    bni.G();
    float f1 = -1.0F;
    float f2 = 1.0F;
    float f3 = -1.0F;
    float f4 = 1.0F;
    float f5 = -0.5F;
    float f6 = atlas.e();
    float f7 = atlas.f();
    float f8 = atlas.g();
    float f9 = atlas.h();
    vertexbuffer.a(7, bvp.g);
    vertexbuffer.b(-1.0D, -1.0D, -0.5D).a(f7, f9).d();
    vertexbuffer.b(1.0D, -1.0D, -0.5D).a(f6, f9).d();
    vertexbuffer.b(1.0D, 1.0D, -0.5D).a(f6, f8).d();
    vertexbuffer.b(-1.0D, 1.0D, -0.5D).a(f7, f8).d();
    tessellator.b();
    bni.H();
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
  }
  
  private void e(float partialTicks)
  {
    this.c.N().a(b);
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    float f = this.c.h.e(partialTicks);
    bni.c(f, f, f, 0.5F);
    bni.m();
    bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
    bni.G();
    float f1 = 4.0F;
    float f2 = -1.0F;
    float f3 = 1.0F;
    float f4 = -1.0F;
    float f5 = 1.0F;
    float f6 = -0.5F;
    float f7 = -this.c.h.v / 64.0F;
    float f8 = this.c.h.w / 64.0F;
    vertexbuffer.a(7, bvp.g);
    vertexbuffer.b(-1.0D, -1.0D, -0.5D).a(4.0F + f7, 4.0F + f8).d();
    vertexbuffer.b(1.0D, -1.0D, -0.5D).a(0.0F + f7, 4.0F + f8).d();
    vertexbuffer.b(1.0D, 1.0D, -0.5D).a(0.0F + f7, 0.0F + f8).d();
    vertexbuffer.b(-1.0D, 1.0D, -0.5D).a(4.0F + f7, 0.0F + f8).d();
    tessellator.b();
    bni.H();
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.l();
  }
  
  private void f(float partialTicks)
  {
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    bni.c(1.0F, 1.0F, 1.0F, 0.9F);
    bni.c(519);
    bni.a(false);
    bni.m();
    bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
    float f = 1.0F;
    for (int i = 0; i < 2; i++)
    {
      bni.G();
      bvh textureatlassprite = this.c.R().a("minecraft:blocks/fire_layer_1");
      this.c.N().a(bvg.g);
      float f1 = textureatlassprite.e();
      float f2 = textureatlassprite.f();
      float f3 = textureatlassprite.g();
      float f4 = textureatlassprite.h();
      float f5 = -0.5F;
      float f6 = 0.5F;
      float f7 = -0.5F;
      float f8 = 0.5F;
      float f9 = -0.5F;
      bni.c(-(i * 2 - 1) * 0.24F, -0.3F, 0.0F);
      bni.b((i * 2 - 1) * 10.0F, 0.0F, 1.0F, 0.0F);
      vertexbuffer.a(7, bvp.g);
      vertexbuffer.b(-0.5D, -0.5D, -0.5D).a(f2, f4).d();
      vertexbuffer.b(0.5D, -0.5D, -0.5D).a(f1, f4).d();
      vertexbuffer.b(0.5D, 0.5D, -0.5D).a(f1, f3).d();
      vertexbuffer.b(-0.5D, 0.5D, -0.5D).a(f2, f3).d();
      tessellator.b();
      bni.H();
    }
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.l();
    bni.a(true);
    bni.c(515);
  }
  
  public void a()
  {
    this.g = this.f;
    this.i = this.h;
    bmt entityplayersp = this.c.h;
    adq itemstack = entityplayersp.cb();
    adq itemstack1 = entityplayersp.cc();
    if (entityplayersp.M())
    {
      this.f = on.a(this.f - 0.4F, 0.0F, 1.0F);
      this.h = on.a(this.h - 0.4F, 0.0F, 1.0F);
    }
    else
    {
      float f = entityplayersp.o(1.0F);
      if (((ConfigManager.settings.oldBlockhit.booleanValue()) && (itemstack != null) && ((itemstack.b() instanceof aex))) || (!ConfigManager.settings.oldSlotSwitch)) {
        f = 1.0F;
      }
      this.f += on.a((Objects.equal(this.d, itemstack) ? f * f * f : 0.0F) - this.f, -0.4F, 0.4F);
      this.h += on.a((Objects.equal(this.e, itemstack1) ? 1 : 0) - this.h, -0.4F, 0.4F);
    }
    if (this.f < 0.1F) {
      this.d = itemstack;
    }
    if (this.h < 0.1F) {
      this.e = itemstack1;
    }
  }
  
  public void a(qm p_187460_1_)
  {
    if (p_187460_1_ == qm.a) {
      this.f = 0.0F;
    } else {
      this.h = 0.0F;
    }
  }
}
