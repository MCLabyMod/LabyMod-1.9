import de.labystudio.cosmetic.ModelCosmetics;

public class bjf
  extends ModelCosmetics
{
  public bkm a;
  public bkm b;
  public bkm c;
  public bkm d;
  public bkm u;
  private bkm v;
  private bkm w;
  private boolean x;
  
  public bjf(float p_i46304_1_, boolean p_i46304_2_)
  {
    super(p_i46304_1_, 0.0F, 64, 64);
    this.x = p_i46304_2_;
    this.w = new bkm(this, 24, 0);
    this.w.a(-3.0F, -6.0F, -1.0F, 6, 6, 1, p_i46304_1_);
    this.v = new bkm(this, 0, 0);
    this.v.b(64, 32);
    this.v.a(-5.0F, 0.0F, -1.0F, 10, 16, 1, p_i46304_1_);
    if (p_i46304_2_)
    {
      this.i = new bkm(this, 32, 48);
      this.i.a(-1.0F, -2.0F, -2.0F, 3, 12, 4, p_i46304_1_);
      this.i.a(5.0F, 2.5F, 0.0F);
      this.h = new bkm(this, 40, 16);
      this.h.a(-2.0F, -2.0F, -2.0F, 3, 12, 4, p_i46304_1_);
      this.h.a(-5.0F, 2.5F, 0.0F);
      this.a = new bkm(this, 48, 48);
      this.a.a(-1.0F, -2.0F, -2.0F, 3, 12, 4, p_i46304_1_ + 0.25F);
      this.a.a(5.0F, 2.5F, 0.0F);
      this.b = new bkm(this, 40, 32);
      this.b.a(-2.0F, -2.0F, -2.0F, 3, 12, 4, p_i46304_1_ + 0.25F);
      this.b.a(-5.0F, 2.5F, 10.0F);
    }
    else
    {
      this.i = new bkm(this, 32, 48);
      this.i.a(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i46304_1_);
      this.i.a(5.0F, 2.0F, 0.0F);
      this.a = new bkm(this, 48, 48);
      this.a.a(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i46304_1_ + 0.25F);
      this.a.a(5.0F, 2.0F, 0.0F);
      this.b = new bkm(this, 40, 32);
      this.b.a(-3.0F, -2.0F, -2.0F, 4, 12, 4, p_i46304_1_ + 0.25F);
      this.b.a(-5.0F, 2.0F, 10.0F);
    }
    this.k = new bkm(this, 16, 48);
    this.k.a(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i46304_1_);
    this.k.a(1.9F, 12.0F, 0.0F);
    this.c = new bkm(this, 0, 48);
    this.c.a(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i46304_1_ + 0.25F);
    this.c.a(1.9F, 12.0F, 0.0F);
    this.d = new bkm(this, 0, 32);
    this.d.a(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i46304_1_ + 0.25F);
    this.d.a(-1.9F, 12.0F, 0.0F);
    this.u = new bkm(this, 16, 32);
    this.u.a(-4.0F, 0.0F, -2.0F, 8, 12, 4, p_i46304_1_ + 0.25F);
    this.u.a(0.0F, 0.0F, 0.0F);
  }
  
  public void a(rr entityIn, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale)
  {
    super.a(entityIn, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale);
    bni.G();
    if (this.q)
    {
      float f = 2.0F;
      bni.b(1.0F / f, 1.0F / f, 1.0F / f);
      bni.c(0.0F, 24.0F * scale, 0.0F);
      this.c.a(scale);
      this.d.a(scale);
      this.a.a(scale);
      this.b.a(scale);
      this.u.a(scale);
    }
    else
    {
      if (entityIn.aK()) {
        bni.c(0.0F, 0.2F, 0.0F);
      }
      this.c.a(scale);
      this.d.a(scale);
      this.a.a(scale);
      this.b.a(scale);
      this.u.a(scale);
    }
    bni.H();
  }
  
  public void a(float p_178727_1_)
  {
    a(this.e, this.w);
    this.w.c = 0.0F;
    this.w.d = 0.0F;
    this.w.a(p_178727_1_);
  }
  
  public void b(float p_178728_1_)
  {
    this.v.a(p_178728_1_);
  }
  
  public void a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, rr entityIn)
  {
    super.a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    a(this.k, this.c);
    a(this.j, this.d);
    a(this.i, this.a);
    a(this.h, this.b);
    a(this.g, this.u);
    if (entityIn.aK()) {
      this.v.d = 2.0F;
    } else {
      this.v.d = 0.0F;
    }
  }
  
  public void a(boolean invisible)
  {
    super.a(invisible);
    this.a.j = invisible;
    this.b.j = invisible;
    this.c.j = invisible;
    this.d.j = invisible;
    this.u.j = invisible;
    this.v.j = invisible;
    this.w.j = invisible;
  }
  
  public void a(float p_187073_1_, rz p_187073_2_)
  {
    bkm modelrenderer = a(p_187073_2_);
    if (this.x)
    {
      float f = 0.5F * (p_187073_2_ == rz.b ? 1 : -1);
      modelrenderer.c += f;
      modelrenderer.c(p_187073_1_);
      modelrenderer.c -= f;
    }
    else
    {
      modelrenderer.c(p_187073_1_);
    }
  }
}
