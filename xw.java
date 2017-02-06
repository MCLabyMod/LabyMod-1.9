import de.labystudio.utils.DualHand;
import java.util.List;
import java.util.Random;

public class xw
  extends rr
{
  private static final ke<Integer> c = kh.a(xw.class, kg.b);
  private int d;
  private int e;
  private int f;
  private ajt g;
  private boolean h;
  public zj a;
  private int as;
  private int at;
  private int au;
  private int av;
  private int aw;
  private float ax;
  public rr b;
  private int ay;
  private double az;
  private double aA;
  private double aB;
  private double aC;
  private double aD;
  private double aE;
  private double aF;
  private double aG;
  
  public xw(aht worldIn)
  {
    super(worldIn);
    this.d = -1;
    this.e = -1;
    this.f = -1;
    a(0.25F, 0.25F);
    this.ah = true;
  }
  
  public xw(aht worldIn, double x, double y, double z, zj anglerIn)
  {
    this(worldIn);
    b(x, y, z);
    this.ah = true;
    this.a = anglerIn;
    anglerIn.bP = this;
  }
  
  public xw(aht worldIn, zj fishingPlayer)
  {
    super(worldIn);
    this.d = -1;
    this.e = -1;
    this.f = -1;
    this.ah = true;
    this.a = fishingPlayer;
    this.a.bP = this;
    a(0.25F, 0.25F);
    b(fishingPlayer.p, fishingPlayer.q + fishingPlayer.bn(), fishingPlayer.r, fishingPlayer.v, fishingPlayer.w);
    this.p -= on.b(this.v * 0.017453292F) * 0.16F;
    this.q -= 0.10000000149011612D;
    this.r -= on.a(this.v * 0.017453292F) * 0.16F;
    b(this.p, this.q, this.r);
    float f = 0.4F;
    this.s = (-on.a(this.v * 0.017453292F) * on.b(this.w * 0.017453292F) * f);
    this.u = (on.b(this.v * 0.017453292F) * on.b(this.w * 0.017453292F) * f);
    this.t = (-on.a(this.w * 0.017453292F) * f);
    c(this.s, this.t, this.u, 1.5F, 1.0F);
  }
  
  protected void i()
  {
    R().a(c, Integer.valueOf(0));
  }
  
  public void a(ke<?> key)
  {
    if (c.equals(key))
    {
      int i = ((Integer)R().a(c)).intValue();
      if ((i > 0) && (this.b != null)) {
        this.b = null;
      }
    }
    super.a(key);
  }
  
  public boolean a(double distance)
  {
    double d0 = bl().a() * 4.0D;
    if (Double.isNaN(d0)) {
      d0 = 4.0D;
    }
    d0 *= 64.0D;
    return distance < d0 * d0;
  }
  
  public void c(double p_146035_1_, double p_146035_3_, double p_146035_5_, float p_146035_7_, float p_146035_8_)
  {
    float f = on.a(p_146035_1_ * p_146035_1_ + p_146035_3_ * p_146035_3_ + p_146035_5_ * p_146035_5_);
    p_146035_1_ /= f;
    p_146035_3_ /= f;
    p_146035_5_ /= f;
    p_146035_1_ += this.S.nextGaussian() * 0.007499999832361937D * p_146035_8_;
    p_146035_3_ += this.S.nextGaussian() * 0.007499999832361937D * p_146035_8_;
    p_146035_5_ += this.S.nextGaussian() * 0.007499999832361937D * p_146035_8_;
    p_146035_1_ *= p_146035_7_;
    p_146035_3_ *= p_146035_7_;
    p_146035_5_ *= p_146035_7_;
    this.s = p_146035_1_;
    this.t = p_146035_3_;
    this.u = p_146035_5_;
    float f1 = on.a(p_146035_1_ * p_146035_1_ + p_146035_5_ * p_146035_5_);
    this.x = (this.v = (float)(on.b(p_146035_1_, p_146035_5_) * 57.29577951308232D));
    this.y = (this.w = (float)(on.b(p_146035_3_, f1) * 57.29577951308232D));
    this.as = 0;
  }
  
  public void a(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean p_180426_10_)
  {
    this.az = x;
    this.aA = y;
    this.aB = z;
    this.aC = yaw;
    this.aD = pitch;
    this.ay = posRotationIncrements;
    this.s = this.aE;
    this.t = this.aF;
    this.u = this.aG;
  }
  
  public void i(double x, double y, double z)
  {
    this.aE = (this.s = x);
    this.aF = (this.t = y);
    this.aG = (this.u = z);
  }
  
  public void m()
  {
    super.m();
    if (this.l.E)
    {
      int i = ((Integer)R().a(c)).intValue();
      if ((i > 0) && (this.b == null)) {
        this.b = this.l.a(i - 1);
      }
    }
    else
    {
      adq itemstack = null;
      if (DualHand.getItemIdInMainHand() == 346) {
        itemstack = this.a.cb();
      }
      if (DualHand.getItemIdInOffHand() == 346) {
        itemstack = this.a.cc();
      }
      if ((this.a.F) || (!this.a.au()) || (itemstack == null) || (itemstack.b() != ads.aY) || (h(this.a) > 1024.0D))
      {
        T();
        this.a.bP = null;
        return;
      }
    }
    if (this.b != null)
    {
      if (!this.b.F)
      {
        this.p = this.b.p;
        double d17 = this.b.H;
        this.q = (this.b.bl().b + d17 * 0.8D);
        this.r = this.b.r;
        return;
      }
      this.b = null;
    }
    if (this.ay > 0)
    {
      double d3 = this.p + (this.az - this.p) / this.ay;
      double d4 = this.q + (this.aA - this.q) / this.ay;
      double d6 = this.r + (this.aB - this.r) / this.ay;
      double d8 = on.g(this.aC - this.v);
      this.v = ((float)(this.v + d8 / this.ay));
      this.w = ((float)(this.w + (this.aD - this.w) / this.ay));
      this.ay -= 1;
      b(d3, d4, d6);
      b(this.v, this.w);
    }
    else
    {
      if (this.h)
      {
        if (this.l.o(new cj(this.d, this.e, this.f)).t() == this.g)
        {
          this.as += 1;
          if (this.as == 1200) {
            T();
          }
          return;
        }
        this.h = false;
        this.s *= this.S.nextFloat() * 0.2F;
        this.t *= this.S.nextFloat() * 0.2F;
        this.u *= this.S.nextFloat() * 0.2F;
        this.as = 0;
        this.at = 0;
      }
      else
      {
        this.at += 1;
      }
      if (!this.l.E)
      {
        bbj vec3d1 = new bbj(this.p, this.q, this.r);
        bbj vec3d = new bbj(this.p + this.s, this.q + this.t, this.r + this.u);
        bbi raytraceresult = this.l.a(vec3d1, vec3d);
        vec3d1 = new bbj(this.p, this.q, this.r);
        vec3d = new bbj(this.p + this.s, this.q + this.t, this.r + this.u);
        if (raytraceresult != null) {
          vec3d = new bbj(raytraceresult.c.b, raytraceresult.c.c, raytraceresult.c.d);
        }
        rr entity = null;
        List<rr> list = this.l.b(this, bl().a(this.s, this.t, this.u).g(1.0D));
        double d0 = 0.0D;
        for (int j = 0; j < list.size(); j++)
        {
          rr entity1 = (rr)list.get(j);
          if ((entity1.ap()) && ((entity1 != this.a) || (this.at >= 5)))
          {
            bbh axisalignedbb1 = entity1.bl().g(0.30000001192092896D);
            bbi raytraceresult1 = axisalignedbb1.a(vec3d1, vec3d);
            if (raytraceresult1 != null)
            {
              double d1 = vec3d1.g(raytraceresult1.c);
              if ((d1 < d0) || (d0 == 0.0D))
              {
                entity = entity1;
                d0 = d1;
              }
            }
          }
        }
        if (entity != null) {
          raytraceresult = new bbi(entity);
        }
        if (raytraceresult != null) {
          if (raytraceresult.d != null)
          {
            this.b = raytraceresult.d;
            R().b(c, Integer.valueOf(this.b.O() + 1));
          }
          else
          {
            this.h = true;
          }
        }
      }
      if (!this.h)
      {
        d(this.s, this.t, this.u);
        float f2 = on.a(this.s * this.s + this.u * this.u);
        this.v = ((float)(on.b(this.s, this.u) * 57.29577951308232D));
        for (this.w = ((float)(on.b(this.t, f2) * 57.29577951308232D)); this.w - this.y < -180.0F; this.y -= 360.0F) {}
        while (this.w - this.y >= 180.0F) {
          this.y += 360.0F;
        }
        while (this.v - this.x < -180.0F) {
          this.x -= 360.0F;
        }
        while (this.v - this.x >= 180.0F) {
          this.x += 360.0F;
        }
        this.w = (this.y + (this.w - this.y) * 0.2F);
        this.v = (this.x + (this.v - this.x) * 0.2F);
        float f3 = 0.92F;
        if ((this.z) || (this.A)) {
          f3 = 0.5F;
        }
        int k = 5;
        double d5 = 0.0D;
        for (int l = 0; l < k; l++)
        {
          bbh axisalignedbb = bl();
          double d9 = axisalignedbb.e - axisalignedbb.b;
          double d10 = axisalignedbb.b + d9 * l / k;
          double d11 = axisalignedbb.b + d9 * (l + 1) / k;
          bbh axisalignedbb2 = new bbh(axisalignedbb.a, d10, axisalignedbb.c, axisalignedbb.d, d11, axisalignedbb.f);
          if (this.l.b(axisalignedbb2, axe.h)) {
            d5 += 1.0D / k;
          }
        }
        if ((!this.l.E) && (d5 > 0.0D))
        {
          lp worldserver = (lp)this.l;
          int i1 = 1;
          cj blockpos = new cj(this).a();
          if ((this.S.nextFloat() < 0.25F) && (this.l.B(blockpos))) {
            i1 = 2;
          }
          if ((this.S.nextFloat() < 0.5F) && (!this.l.h(blockpos))) {
            i1--;
          }
          if (this.au > 0)
          {
            this.au -= 1;
            if (this.au <= 0)
            {
              this.av = 0;
              this.aw = 0;
            }
          }
          else if (this.aw > 0)
          {
            this.aw -= i1;
            if (this.aw <= 0)
            {
              this.t -= 0.20000000298023224D;
              a(ng.G, 0.25F, 1.0F + (this.S.nextFloat() - this.S.nextFloat()) * 0.4F);
              float f6 = on.c(bl().b);
              worldserver.a(cy.e, this.p, f6 + 1.0F, this.r, (int)(1.0F + this.G * 20.0F), this.G, 0.0D, this.G, 0.20000000298023224D, new int[0]);
              worldserver.a(cy.g, this.p, f6 + 1.0F, this.r, (int)(1.0F + this.G * 20.0F), this.G, 0.0D, this.G, 0.20000000298023224D, new int[0]);
              this.au = on.a(this.S, 10, 30);
            }
            else
            {
              this.ax = ((float)(this.ax + this.S.nextGaussian() * 4.0D));
              float f5 = this.ax * 0.017453292F;
              float f8 = on.a(f5);
              float f10 = on.b(f5);
              double d13 = this.p + f8 * this.aw * 0.1F;
              double d15 = on.c(bl().b) + 1.0F;
              double d16 = this.r + f10 * this.aw * 0.1F;
              ajt block1 = worldserver.o(new cj((int)d13, (int)d15 - 1, (int)d16)).t();
              if ((block1 == aju.j) || (block1 == aju.i))
              {
                if (this.S.nextFloat() < 0.15F) {
                  worldserver.a(cy.e, d13, d15 - 0.10000000149011612D, d16, 1, f8, 0.1D, f10, 0.0D, new int[0]);
                }
                float f = f8 * 0.04F;
                float f1 = f10 * 0.04F;
                worldserver.a(cy.g, d13, d15, d16, 0, f1, 0.01D, -f, 1.0D, new int[0]);
                worldserver.a(cy.g, d13, d15, d16, 0, -f1, 0.01D, f, 1.0D, new int[0]);
              }
            }
          }
          else if (this.av > 0)
          {
            this.av -= i1;
            float f4 = 0.15F;
            if (this.av < 20) {
              f4 = (float)(f4 + (20 - this.av) * 0.05D);
            } else if (this.av < 40) {
              f4 = (float)(f4 + (40 - this.av) * 0.02D);
            } else if (this.av < 60) {
              f4 = (float)(f4 + (60 - this.av) * 0.01D);
            }
            if (this.S.nextFloat() < f4)
            {
              float f7 = on.a(this.S, 0.0F, 360.0F) * 0.017453292F;
              float f9 = on.a(this.S, 25.0F, 60.0F);
              double d12 = this.p + on.a(f7) * f9 * 0.1F;
              double d14 = on.c(bl().b) + 1.0F;
              double d2 = this.r + on.b(f7) * f9 * 0.1F;
              ajt block = worldserver.o(new cj((int)d12, (int)d14 - 1, (int)d2)).t();
              if ((block == aju.j) || (block == aju.i)) {
                worldserver.a(cy.f, d12, d14, d2, 2 + this.S.nextInt(2), 0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.0D, new int[0]);
              }
            }
            if (this.av <= 0)
            {
              this.ax = on.a(this.S, 0.0F, 360.0F);
              this.aw = on.a(this.S, 20, 80);
            }
          }
          else
          {
            this.av = on.a(this.S, 100, 900);
            this.av -= ago.g(this.a) * 20 * 5;
          }
          if (this.au > 0) {
            this.t -= this.S.nextFloat() * this.S.nextFloat() * this.S.nextFloat() * 0.2D;
          }
        }
        double d7 = d5 * 2.0D - 1.0D;
        this.t += 0.03999999910593033D * d7;
        if (d5 > 0.0D)
        {
          f3 = (float)(f3 * 0.9D);
          this.t *= 0.8D;
        }
        this.s *= f3;
        this.t *= f3;
        this.u *= f3;
        b(this.p, this.q, this.r);
      }
    }
  }
  
  public void b(dn tagCompound)
  {
    tagCompound.a("xTile", this.d);
    tagCompound.a("yTile", this.e);
    tagCompound.a("zTile", this.f);
    kk resourcelocation = (kk)ajt.h.b(this.g);
    tagCompound.a("inTile", resourcelocation == null ? "" : resourcelocation.toString());
    tagCompound.a("inGround", (byte)(this.h ? 1 : 0));
  }
  
  public void a(dn tagCompund)
  {
    this.d = tagCompund.h("xTile");
    this.e = tagCompund.h("yTile");
    this.f = tagCompund.h("zTile");
    if (tagCompund.b("inTile", 8)) {
      this.g = ajt.b(tagCompund.l("inTile"));
    } else {
      this.g = ajt.b(tagCompund.f("inTile") & 0xFF);
    }
    this.h = (tagCompund.f("inGround") == 1);
  }
  
  public int j()
  {
    if (this.l.E) {
      return 0;
    }
    int i = 0;
    if (this.b != null)
    {
      k();
      this.l.a(this, (byte)31);
      i = (this.b instanceof yd) ? 3 : 5;
    }
    else if (this.au > 0)
    {
      azz.a lootcontext$builder = new azz.a((lp)this.l);
      lootcontext$builder.a(ago.f(this.a) + this.a.db());
      for (adq itemstack : this.l.ak().a(azt.al).a(this.S, lootcontext$builder.a()))
      {
        yd entityitem = new yd(this.l, this.p, this.q, this.r, itemstack);
        double d0 = this.a.p - this.p;
        double d1 = this.a.q - this.q;
        double d2 = this.a.r - this.r;
        double d3 = on.a(d0 * d0 + d1 * d1 + d2 * d2);
        double d4 = 0.1D;
        entityitem.s = (d0 * d4);
        entityitem.t = (d1 * d4 + on.a(d3) * 0.08D);
        entityitem.u = (d2 * d4);
        this.l.a(entityitem);
        this.a.l.a(new rx(this.a.l, this.a.p, this.a.q + 0.5D, this.a.r + 0.5D, this.S.nextInt(6) + 1));
      }
      i = 1;
    }
    if (this.h) {
      i = 2;
    }
    T();
    this.a.bP = null;
    return i;
  }
  
  public void a(byte id)
  {
    if ((id == 31) && (this.l.E) && ((this.b instanceof zj)) && (((zj)this.b).cJ())) {
      k();
    }
    super.a(id);
  }
  
  protected void k()
  {
    double d0 = this.a.p - this.p;
    double d1 = this.a.q - this.q;
    double d2 = this.a.r - this.r;
    double d3 = on.a(d0 * d0 + d1 * d1 + d2 * d2);
    double d4 = 0.1D;
    this.b.s += d0 * d4;
    this.b.t += d1 * d4 + on.a(d3) * 0.08D;
    this.b.u += d2 * d4;
  }
  
  public void T()
  {
    super.T();
    if (this.a != null) {
      this.a.bP = null;
    }
  }
}
