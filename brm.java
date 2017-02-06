import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Map;

public class brm
{
  private Map<Class<? extends rr>, brn<? extends rr>> k = Maps.newHashMap();
  private Map<String, buk> l = Maps.newHashMap();
  private buk m;
  private bct n;
  private double o;
  private double p;
  private double q;
  public bvi a;
  public aht b;
  public rr c;
  public rr d;
  public float e;
  public float f;
  public bch g;
  public double h;
  public double i;
  public double j;
  private boolean r = false;
  private boolean s = true;
  private boolean t = false;
  
  public brm(bvi renderEngineIn, brz itemRendererIn)
  {
    this.a = renderEngineIn;
    this.k.put(yh.class, new brc(this));
    this.k.put(yy.class, new bsv(this));
    this.k.put(wc.class, new bsk(this, new bje(), 0.7F));
    this.k.put(we.class, new bsn(this, new bjk(), 0.7F));
    this.k.put(vy.class, new bre(this, new bio(), 0.7F));
    this.k.put(wa.class, new bsh(this, new bio(), 0.7F));
    this.k.put(wj.class, new bth(this, new bke(), 0.5F));
    this.k.put(vx.class, new brd(this, new bin(), 0.3F));
    this.k.put(wb.class, new bsi(this, new bjd(), 0.4F));
    this.k.put(wd.class, new bsm(this, new bji(), 0.3F));
    this.k.put(yv.class, new bsq(this));
    this.k.put(yk.class, new brl(this));
    this.k.put(yi.class, new brf(this));
    this.k.put(yj.class, new brk(this));
    this.k.put(wf.class, new bst(this));
    this.k.put(yw.class, new bsr(this));
    this.k.put(yz.class, new bte(this));
    this.k.put(yg.class, new bra(this));
    this.k.put(yr.class, new bsl(this));
    this.k.put(za.class, new bti(this));
    this.k.put(yx.class, new bss(this, new bjt(16), 0.25F));
    this.k.put(yp.class, new bsa(this));
    this.k.put(yn.class, new brt(this, new bkf(), 0.5F, 6.0F));
    this.k.put(ym.class, new brs(this));
    this.k.put(wg.class, new bsw(this, new bjw(), 0.7F));
    this.k.put(ze.class, new btd(this));
    this.k.put(wh.class, new btc(this));
    this.k.put(vu.class, new bqz(this));
    this.k.put(yo.class, new bru(this));
    this.k.put(yu.class, new bsp(this, new bjn()));
    this.k.put(wu.class, new brj(this));
    this.k.put(wt.class, new bri(this));
    this.k.put(xo.class, new btf(this));
    this.k.put(rr.class, new brg(this));
    this.k.put(xu.class, new bsj(this));
    this.k.put(xs.class, new bry(this, itemRendererIn));
    this.k.put(xt.class, new bsb(this));
    this.k.put(aad.class, new bsz(this));
    this.k.put(zx.class, new bsu(this));
    this.k.put(zw.class, new bsx(this, ads.aF, itemRendererIn));
    this.k.put(aaa.class, new bsx(this, ads.bB, itemRendererIn));
    this.k.put(zo.class, new bsx(this, ads.bR, itemRendererIn));
    this.k.put(zz.class, new bsx(this, ads.aW, itemRendererIn));
    this.k.put(aac.class, new bsy(this, itemRendererIn));
    this.k.put(aab.class, new bsx(this, ads.bU, itemRendererIn));
    this.k.put(zq.class, new bsx(this, ads.cl, itemRendererIn));
    this.k.put(zr.class, new brq(this, 2.0F));
    this.k.put(zv.class, new brq(this, 0.5F));
    this.k.put(zn.class, new brh(this));
    this.k.put(aae.class, new btg(this));
    this.k.put(zu.class, new bso(this));
    this.k.put(yd.class, new brx(this, itemRendererIn));
    this.k.put(rx.class, new bro(this));
    this.k.put(ye.class, new btb(this));
    this.k.put(yc.class, new brp(this));
    this.k.put(xq.class, new bqx(this));
    this.k.put(aap.class, new bta(this));
    this.k.put(aao.class, new bsf(this));
    this.k.put(aah.class, new bse(this));
    this.k.put(aag.class, new brb(this));
    this.k.put(xw.class, new brr(this));
    this.k.put(rp.class, new bqw(this));
    this.k.put(wk.class, new brv(this, new biv(), 0.75F));
    this.k.put(ya.class, new bsc(this));
    this.m = new buk(this);
    this.l.put("default", this.m);
    this.l.put("slim", new buk(this, true));
    
    PlayerItemsLayer.register(this.l);
    if (Reflector.RenderingRegistry_loadEntityRenderers.exists()) {
      Reflector.call(Reflector.RenderingRegistry_loadEntityRenderers, new Object[] { this, this.k });
    }
  }
  
  public void a(double renderPosXIn, double renderPosYIn, double renderPosZIn)
  {
    this.o = renderPosXIn;
    this.p = renderPosYIn;
    this.q = renderPosZIn;
  }
  
  public <T extends rr> brn<T> a(Class<? extends rr> entityClass)
  {
    brn<? extends rr> render = (brn)this.k.get(entityClass);
    if ((render == null) && (entityClass != rr.class))
    {
      render = a(entityClass.getSuperclass());
      this.k.put(entityClass, render);
    }
    return render;
  }
  
  public <T extends rr> brn<T> a(T entityIn)
  {
    if ((entityIn instanceof bmq))
    {
      String s = ((bmq)entityIn).u();
      buk renderplayer = (buk)this.l.get(s);
      return renderplayer != null ? renderplayer : this.m;
    }
    return a(entityIn.getClass());
  }
  
  public void a(aht worldIn, bct textRendererIn, rr livingPlayerIn, rr pointedEntityIn, bch optionsIn, float partialTicks)
  {
    this.b = worldIn;
    this.g = optionsIn;
    this.c = livingPlayerIn;
    this.d = pointedEntityIn;
    this.n = textRendererIn;
    if (((livingPlayerIn instanceof sa)) && (((sa)livingPlayerIn).cl()))
    {
      arc iblockstate = worldIn.o(new cj(livingPlayerIn));
      ajt block = iblockstate.t();
      if (Reflector.callBoolean(Reflector.ForgeBlock_isBed, new Object[] { worldIn, new cj(livingPlayerIn), (sa)livingPlayerIn }))
      {
        cq facing = (cq)Reflector.call(block, Reflector.ForgeBlock_getBedDirection, new Object[] { worldIn, new cj(livingPlayerIn) });
        int i = facing.b();
        this.e = (i * 90 + 180);
        this.f = 0.0F;
      }
      else if (block == aju.C)
      {
        int i = ((cq)iblockstate.c(ajr.D)).b();
        this.e = (i * 90 + 180);
        this.f = 0.0F;
      }
    }
    else
    {
      this.e = (livingPlayerIn.x + (livingPlayerIn.v - livingPlayerIn.x) * partialTicks);
      this.f = (livingPlayerIn.y + (livingPlayerIn.w - livingPlayerIn.y) * partialTicks);
    }
    if (optionsIn.ap == 2) {
      this.e += 180.0F;
    }
    this.h = (livingPlayerIn.M + (livingPlayerIn.p - livingPlayerIn.M) * partialTicks);
    this.i = (livingPlayerIn.N + (livingPlayerIn.q - livingPlayerIn.N) * partialTicks);
    this.j = (livingPlayerIn.O + (livingPlayerIn.r - livingPlayerIn.O) * partialTicks);
  }
  
  public void a(float playerViewYIn)
  {
    this.e = playerViewYIn;
  }
  
  public boolean a()
  {
    return this.s;
  }
  
  public void a(boolean renderShadowIn)
  {
    this.s = renderShadowIn;
  }
  
  public void b(boolean debugBoundingBoxIn)
  {
    this.t = debugBoundingBoxIn;
  }
  
  public boolean b()
  {
    return this.t;
  }
  
  public boolean b(rr p_188390_1_)
  {
    return a(p_188390_1_).H_();
  }
  
  public boolean a(rr entityIn, bqm camera, double camX, double camY, double camZ)
  {
    brn<rr> render = a(entityIn);
    return (render != null) && (render.a(entityIn, camera, camX, camY, camZ));
  }
  
  public void a(rr p_188388_1_, float p_188388_2_, boolean p_188388_3_)
  {
    if (p_188388_1_.T == 0)
    {
      p_188388_1_.M = p_188388_1_.p;
      p_188388_1_.N = p_188388_1_.q;
      p_188388_1_.O = p_188388_1_.r;
    }
    double d0 = p_188388_1_.M + (p_188388_1_.p - p_188388_1_.M) * p_188388_2_;
    double d1 = p_188388_1_.N + (p_188388_1_.q - p_188388_1_.N) * p_188388_2_;
    double d2 = p_188388_1_.O + (p_188388_1_.r - p_188388_1_.O) * p_188388_2_;
    float f = p_188388_1_.x + (p_188388_1_.v - p_188388_1_.x) * p_188388_2_;
    int i = p_188388_1_.d(p_188388_2_);
    if (p_188388_1_.aH()) {
      i = 15728880;
    }
    int j = i % 65536;
    int k = i / 65536;
    bzg.a(bzg.r, j, k);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    a(p_188388_1_, d0 - this.o, d1 - this.p, d2 - this.q, f, p_188388_2_, p_188388_3_);
  }
  
  public void a(rr entityIn, double x, double y, double z, float yaw, float partialTicks, boolean p_188391_10_)
  {
    brn<rr> render = null;
    try
    {
      render = a(entityIn);
      if ((render != null) && (this.a != null))
      {
        try
        {
          render.a(this.r);
          render.a(entityIn, x, y, z, yaw, partialTicks);
        }
        catch (Throwable throwable1)
        {
          throw new e(b.a(throwable1, "Rendering entity in world"));
        }
        try
        {
          if (!this.r) {
            render.c(entityIn, x, y, z, yaw, partialTicks);
          }
        }
        catch (Throwable throwable2)
        {
          throw new e(b.a(throwable2, "Post-rendering entity in world"));
        }
        if ((this.t) && (!entityIn.aN()) && (!p_188391_10_)) {
          try
          {
            a(entityIn, x, y, z, yaw, partialTicks);
          }
          catch (Throwable throwable)
          {
            throw new e(b.a(throwable, "Rendering entity hitbox in world"));
          }
        }
      }
    }
    catch (Throwable throwable3)
    {
      b crashreport = b.a(throwable3, "Rendering entity in world");
      c crashreportcategory = crashreport.a("Entity being rendered");
      entityIn.a(crashreportcategory);
      c crashreportcategory1 = crashreport.a("Renderer details");
      crashreportcategory1.a("Assigned renderer", render);
      crashreportcategory1.a("Location", c.a(x, y, z));
      crashreportcategory1.a("Rotation", Float.valueOf(yaw));
      crashreportcategory1.a("Delta", Float.valueOf(partialTicks));
      throw new e(crashreport);
    }
  }
  
  public void a(rr p_188389_1_, float p_188389_2_)
  {
    if (p_188389_1_.T == 0)
    {
      p_188389_1_.M = p_188389_1_.p;
      p_188389_1_.N = p_188389_1_.q;
      p_188389_1_.O = p_188389_1_.r;
    }
    double d0 = p_188389_1_.M + (p_188389_1_.p - p_188389_1_.M) * p_188389_2_;
    double d1 = p_188389_1_.N + (p_188389_1_.q - p_188389_1_.N) * p_188389_2_;
    double d2 = p_188389_1_.O + (p_188389_1_.r - p_188389_1_.O) * p_188389_2_;
    float f = p_188389_1_.x + (p_188389_1_.v - p_188389_1_.x) * p_188389_2_;
    int i = p_188389_1_.d(p_188389_2_);
    if (p_188389_1_.aH()) {
      i = 15728880;
    }
    int j = i % 65536;
    int k = i / 65536;
    bzg.a(bzg.r, j, k);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    brn<rr> render = a(p_188389_1_);
    if ((render != null) && (this.a != null)) {
      render.b(p_188389_1_, d0 - this.o, d1 - this.p, d2 - this.q, f, p_188389_2_);
    }
  }
  
  private void a(rr entityIn, double x, double y, double z, float entityYaw, float partialTicks)
  {
    bni.a(false);
    bni.z();
    bni.g();
    bni.r();
    bni.l();
    float f = entityIn.G / 2.0F;
    bbh axisalignedbb = entityIn.bl();
    bbh axisalignedbb1 = new bbh(axisalignedbb.a - entityIn.p + x, axisalignedbb.b - entityIn.q + y, axisalignedbb.c - entityIn.r + z, axisalignedbb.d - entityIn.p + x, axisalignedbb.e - entityIn.q + y, axisalignedbb.f - entityIn.r + z);
    bno.a(axisalignedbb1, 255, 255, 255, 255);
    if ((entityIn instanceof sa))
    {
      float f1 = 0.01F;
      bno.a(new bbh(x - f, y + entityIn.bn() - 0.009999999776482582D, z - f, x + f, y + entityIn.bn() + 0.009999999776482582D, z + f), 255, 0, 0, 255);
    }
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    bbj vec3d = entityIn.f(partialTicks);
    vertexbuffer.a(3, bvp.f);
    vertexbuffer.b(x, y + entityIn.bn(), z).b(0, 0, 255, 255).d();
    vertexbuffer.b(x + vec3d.b * 2.0D, y + entityIn.bn() + vec3d.c * 2.0D, z + vec3d.d * 2.0D).b(0, 0, 255, 255).d();
    tessellator.b();
    bni.y();
    bni.f();
    bni.q();
    bni.l();
    bni.a(true);
  }
  
  public void a(aht worldIn)
  {
    this.b = worldIn;
    if (worldIn == null) {
      this.c = null;
    }
  }
  
  public double b(double x, double y, double z)
  {
    double d0 = x - this.h;
    double d1 = y - this.i;
    double d2 = z - this.j;
    return d0 * d0 + d1 * d1 + d2 * d2;
  }
  
  public bct c()
  {
    return this.n;
  }
  
  public void c(boolean renderOutlinesIn)
  {
    this.r = renderOutlinesIn;
  }
  
  public Map getEntityRenderMap()
  {
    return this.k;
  }
  
  public void setEntityRenderMap(Map entityRenderMap)
  {
    this.k = entityRenderMap;
  }
  
  public Map<String, buk> getSkinMap()
  {
    return Collections.unmodifiableMap(this.l);
  }
}
