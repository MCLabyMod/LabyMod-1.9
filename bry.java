public class bry
  extends brn<xs>
{
  private static final kk a = new kk("textures/map/map_background.png");
  private final bcf b = bcf.z();
  private final bxt g = new bxt("item_frame", "normal");
  private final bxt h = new bxt("item_frame", "map");
  private brz i;
  
  public bry(brm renderManagerIn, brz itemRendererIn)
  {
    super(renderManagerIn);
    this.i = itemRendererIn;
  }
  
  public void a(xs entity, double x, double y, double z, float entityYaw, float partialTicks)
  {
    bni.G();
    cj blockpos = entity.q();
    double d0 = blockpos.p() - entity.p + x;
    double d1 = blockpos.q() - entity.q + y;
    double d2 = blockpos.r() - entity.r + z;
    bni.b(d0 + 0.5D, d1 + 0.5D, d2 + 0.5D);
    bni.b(180.0F - entity.v, 0.0F, 1.0F, 0.0F);
    this.c.a.a(bvg.g);
    boc blockrendererdispatcher = this.b.ab();
    bxs modelmanager = blockrendererdispatcher.a().b();
    bxo ibakedmodel;
    bxo ibakedmodel;
    if ((entity.r() != null) && (entity.r().b() == ads.bk)) {
      ibakedmodel = modelmanager.a(this.h);
    } else {
      ibakedmodel = modelmanager.a(this.g);
    }
    bni.G();
    bni.c(-0.5F, -0.5F, -0.5F);
    if (this.f)
    {
      bni.h();
      bni.e(c(entity));
    }
    blockrendererdispatcher.b().a(ibakedmodel, 1.0F, 1.0F, 1.0F, 1.0F);
    if (this.f)
    {
      bni.n();
      bni.i();
    }
    bni.H();
    bni.c(0.0F, 0.0F, 0.4375F);
    b(entity);
    bni.H();
    a(entity, x + entity.b.g() * 0.3F, y - 0.25D, z + entity.b.i() * 0.3F);
  }
  
  protected kk a(xs entity)
  {
    return null;
  }
  
  private void b(xs itemFrame)
  {
    adq itemstack = itemFrame.r();
    if (itemstack != null)
    {
      yd entityitem = new yd(itemFrame.l, 0.0D, 0.0D, 0.0D, itemstack);
      ado item = entityitem.k().b();
      entityitem.k().b = 1;
      entityitem.a = 0.0F;
      bni.G();
      bni.g();
      int i = itemFrame.s();
      if ((item instanceof adw)) {
        i = i % 4 * 2;
      }
      bni.b(i * 360.0F / 8.0F, 0.0F, 0.0F, 1.0F);
      if (!Reflector.postForgeBusEvent(Reflector.RenderItemInFrameEvent_Constructor, new Object[] { itemFrame, this })) {
        if ((item instanceof adw))
        {
          this.c.a.a(a);
          bni.b(180.0F, 0.0F, 0.0F, 1.0F);
          float f = 0.0078125F;
          bni.b(f, f, f);
          bni.c(-64.0F, -64.0F, 0.0F);
          ayz mapdata = ads.bk.a(entityitem.k(), itemFrame.l);
          bni.c(0.0F, 0.0F, -1.0F);
          if (mapdata != null) {
            this.b.o.k().a(mapdata, true);
          }
        }
        else
        {
          bni.b(0.5F, 0.5F, 0.5F);
          if ((!this.i.a(entityitem.k())) || ((item instanceof aeq))) {
            bni.b(180.0F, 0.0F, 1.0F, 0.0F);
          }
          bni.a();
          bcd.b();
          this.i.a(entityitem.k(), bos.b.i);
          bcd.a();
          bni.c();
        }
      }
      bni.f();
      bni.H();
    }
    if (Config.isShaders()) {
      shadersmod.client.ShadersTex.updatingTex = null;
    }
  }
  
  protected void a(xs entity, double x, double y, double z)
  {
    if ((bcf.w()) && (entity.r() != null) && (entity.r().s()) && (this.c.d == entity))
    {
      double d0 = entity.h(this.c.c);
      float f = entity.aK() ? 32.0F : 64.0F;
      if (d0 < f * f)
      {
        String s = entity.r().q();
        a(entity, s, x, y, z, 64);
      }
    }
  }
}
