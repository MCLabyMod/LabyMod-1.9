import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.ModSettings;
import de.labystudio.utils.Allowed;
import de.labystudio.utils.DualHand;

public class bix
  extends bjc
{
  public bkm e;
  public bkm f;
  public bkm g;
  public bkm h;
  public bkm i;
  public bkm j;
  public bkm k;
  public bix.a l;
  public bix.a m;
  public boolean n;
  
  public bix()
  {
    this(0.0F);
  }
  
  public bix(float modelSize)
  {
    this(modelSize, 0.0F, 64, 32);
  }
  
  public bix(float modelSize, float p_i1149_2_, int textureWidthIn, int textureHeightIn)
  {
    this.l = bix.a.a;
    this.m = bix.a.a;
    this.s = textureWidthIn;
    this.t = textureHeightIn;
    this.e = new bkm(this, 0, 0);
    this.e.a(-4.0F, -8.0F, -4.0F, 8, 8, 8, modelSize);
    this.e.a(0.0F, 0.0F + p_i1149_2_, 0.0F);
    this.f = new bkm(this, 32, 0);
    this.f.a(-4.0F, -8.0F, -4.0F, 8, 8, 8, modelSize + 0.5F);
    this.f.a(0.0F, 0.0F + p_i1149_2_, 0.0F);
    this.g = new bkm(this, 16, 16);
    this.g.a(-4.0F, 0.0F, -2.0F, 8, 12, 4, modelSize);
    this.g.a(0.0F, 0.0F + p_i1149_2_, 0.0F);
    this.h = new bkm(this, 40, 16);
    this.h.a(-3.0F, -2.0F, -2.0F, 4, 12, 4, modelSize);
    this.h.a(-5.0F, 2.0F + p_i1149_2_, 0.0F);
    this.i = new bkm(this, 40, 16);
    this.i.i = true;
    this.i.a(-1.0F, -2.0F, -2.0F, 4, 12, 4, modelSize);
    this.i.a(5.0F, 2.0F + p_i1149_2_, 0.0F);
    this.j = new bkm(this, 0, 16);
    this.j.a(-2.0F, 0.0F, -2.0F, 4, 12, 4, modelSize);
    this.j.a(-1.9F, 12.0F + p_i1149_2_, 0.0F);
    this.k = new bkm(this, 0, 16);
    this.k.i = true;
    this.k.a(-2.0F, 0.0F, -2.0F, 4, 12, 4, modelSize);
    this.k.a(1.9F, 12.0F + p_i1149_2_, 0.0F);
  }
  
  public void a(rr entityIn, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale)
  {
    a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale, entityIn);
    bni.G();
    if (this.q)
    {
      float f = 2.0F;
      bni.b(1.5F / f, 1.5F / f, 1.5F / f);
      bni.c(0.0F, 16.0F * scale, 0.0F);
      this.e.a(scale);
      bni.H();
      bni.G();
      bni.b(1.0F / f, 1.0F / f, 1.0F / f);
      bni.c(0.0F, 24.0F * scale, 0.0F);
      this.g.a(scale);
      this.h.a(scale);
      this.i.a(scale);
      this.j.a(scale);
      this.k.a(scale);
      this.f.a(scale);
    }
    else
    {
      if (entityIn.aK()) {
        bni.c(0.0F, 0.2F, 0.0F);
      }
      this.e.a(scale);
      this.g.a(scale);
      this.h.a(scale);
      this.i.a(scale);
      this.j.a(scale);
      this.k.a(scale);
      this.f.a(scale);
    }
    bni.H();
  }
  
  public void a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, rr entityIn)
  {
    boolean flag = ((entityIn instanceof sa)) && (((sa)entityIn).cC() > 4);
    this.e.g = (netHeadYaw * 0.017453292F);
    if (flag) {
      this.e.f = -0.7853982F;
    } else {
      this.e.f = (headPitch * 0.017453292F);
    }
    this.g.g = 0.0F;
    this.h.e = 0.0F;
    this.h.c = -5.0F;
    this.i.e = 0.0F;
    this.i.c = 5.0F;
    float f = 1.0F;
    if (flag)
    {
      f = (float)(entityIn.s * entityIn.s + entityIn.t * entityIn.t + entityIn.u * entityIn.u);
      f /= 0.2F;
      f = f * f * f;
    }
    if (f < 1.0F) {
      f = 1.0F;
    }
    this.h.f = (on.b(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F / f);
    this.i.f = (on.b(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f);
    this.h.h = 0.0F;
    this.i.h = 0.0F;
    this.j.f = (on.b(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f);
    this.k.f = (on.b(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount / f);
    this.j.g = 0.0F;
    this.k.g = 0.0F;
    this.j.h = 0.0F;
    this.k.h = 0.0F;
    if (this.p)
    {
      this.h.f += -0.62831855F;
      this.i.f += -0.62831855F;
      this.j.f = -1.4137167F;
      this.j.g = 0.31415927F;
      this.j.h = 0.07853982F;
      this.k.f = -1.4137167F;
      this.k.g = -0.31415927F;
      this.k.h = -0.07853982F;
    }
    this.h.g = 0.0F;
    this.h.h = 0.0F;
    switch (this.l)
    {
    case a: 
      this.i.g = 0.0F;
      break;
    case c: 
      if (DualHand.toEnumHandSide(DualHand.getUsingHand()) == rz.a) {
        this.i.f = (this.i.f * 0.5F - 0.9424779F);
      } else {
        this.i.f = (this.i.f * 0.5F - 0.31415927F);
      }
      if ((ConfigManager.settings.animationSword != 0) || (!Allowed.animations()) || (!Allowed.blocking())) {
        this.i.g = 0.5235988F;
      } else {
        this.i.g = 0.0F;
      }
      break;
    case b: 
      this.i.f = (this.i.f * 0.5F - 0.31415927F);
      this.i.g = 0.0F;
    }
    switch (this.m)
    {
    case a: 
      this.h.g = 0.0F;
      break;
    case c: 
      if (DualHand.toEnumHandSide(DualHand.getUsingHand()) == rz.b) {
        this.h.f = (this.h.f * 0.5F - 0.9424779F);
      } else {
        this.h.f = (this.h.f * 0.5F - 0.31415927F);
      }
      if ((ConfigManager.settings.animationSword != 0) || (!Allowed.animations()) || (!Allowed.blocking())) {
        this.h.g = -0.5235988F;
      } else {
        this.h.g = 0.0F;
      }
      break;
    case b: 
      this.h.f = (this.h.f * 0.5F - 0.31415927F);
      this.h.g = 0.0F;
    }
    if (this.o > 0.0F)
    {
      rz enumhandside = a(entityIn);
      bkm modelrenderer = a(enumhandside);
      a(enumhandside.a());
      float f1 = this.o;
      this.g.g = (on.a(on.c(f1) * 6.2831855F) * 0.2F);
      if (enumhandside == rz.a) {
        this.g.g *= -1.0F;
      }
      this.h.e = (on.a(this.g.g) * 5.0F);
      this.h.c = (-on.b(this.g.g) * 5.0F);
      this.i.e = (-on.a(this.g.g) * 5.0F);
      this.i.c = (on.b(this.g.g) * 5.0F);
      this.h.g += this.g.g;
      this.i.g += this.g.g;
      this.i.f += this.g.g;
      f1 = 1.0F - this.o;
      f1 *= f1;
      f1 *= f1;
      f1 = 1.0F - f1;
      float f2 = on.a(f1 * 3.1415927F);
      float f3 = on.a(this.o * 3.1415927F) * -(this.e.f - 0.7F) * 0.75F;
      modelrenderer.f = ((float)(modelrenderer.f - (f2 * 1.2D + f3)));
      modelrenderer.g += this.g.g * 2.0F;
      modelrenderer.h += on.a(this.o * 3.1415927F) * -0.4F;
    }
    if (this.n)
    {
      this.g.f = 0.5F;
      this.h.f += 0.4F;
      this.i.f += 0.4F;
      this.j.e = 4.0F;
      this.k.e = 4.0F;
      this.j.d = 9.0F;
      this.k.d = 9.0F;
      this.e.d = 1.0F;
    }
    else
    {
      this.g.f = 0.0F;
      this.j.e = 0.1F;
      this.k.e = 0.1F;
      this.j.d = 12.0F;
      this.k.d = 12.0F;
      this.e.d = 0.0F;
    }
    this.h.h += on.b(ageInTicks * 0.09F) * 0.05F + 0.05F;
    this.i.h -= on.b(ageInTicks * 0.09F) * 0.05F + 0.05F;
    this.h.f += on.a(ageInTicks * 0.067F) * 0.05F;
    this.i.f -= on.a(ageInTicks * 0.067F) * 0.05F;
    
    sa livingEntity = (sa)entityIn;
    if (this.m == bix.a.d)
    {
      this.h.g = (-0.1F + this.e.g);
      this.i.g = (0.1F + this.e.g + 0.4F);
      this.h.f = (-1.5707964F + this.e.f);
      this.i.f = (-1.5707964F + this.e.f);
    }
    else if ((DualHand.getItemIdInLeftHand() == 261) && (livingEntity.isUsingItem()))
    {
      this.h.g = (-0.1F + this.e.g - 0.4F);
      this.i.g = (0.1F + this.e.g);
      this.h.f = (-1.5707964F + this.e.f);
      this.i.f = (-1.5707964F + this.e.f);
    }
    a(this.e, this.f);
  }
  
  public void a(bjc model)
  {
    super.a(model);
    if ((model instanceof bix))
    {
      bix modelbiped = (bix)model;
      this.l = modelbiped.l;
      this.m = modelbiped.m;
      this.n = modelbiped.n;
    }
  }
  
  public void a(boolean invisible)
  {
    this.e.j = invisible;
    this.f.j = invisible;
    this.g.j = invisible;
    this.h.j = invisible;
    this.i.j = invisible;
    this.j.j = invisible;
    this.k.j = invisible;
  }
  
  public void a(float p_187073_1_, rz p_187073_2_)
  {
    a(p_187073_2_).c(p_187073_1_);
  }
  
  protected bkm a(rz p_187074_1_)
  {
    return p_187074_1_ == rz.a ? this.i : this.h;
  }
  
  protected rz a(rr p_187072_1_)
  {
    return (p_187072_1_ instanceof sa) ? ((sa)p_187072_1_).cr() : rz.b;
  }
  
  public static enum a
  {
    private a() {}
  }
}
