import java.util.List;
import java.util.concurrent.Callable;
import org.lwjgl.util.vector.Vector3f;

public class brz
  implements bwh
{
  private static final kk b = new kk("textures/misc/enchanted_item_glint.png");
  private boolean c = true;
  public float a;
  private final bnl d;
  private final bvi e;
  private final bcr f;
  private bxt modelLocation = null;
  
  public brz(bvi p_i46552_1_, bxs p_i46552_2_, bcr p_i46552_3_)
  {
    this.e = p_i46552_1_;
    
    Config.setModelManager(p_i46552_2_);
    if (Reflector.ItemModelMesherForge_Constructor.exists()) {
      this.d = ((bnl)Reflector.newInstance(Reflector.ItemModelMesherForge_Constructor, new Object[] { p_i46552_2_ }));
    } else {
      this.d = new bnl(p_i46552_2_);
    }
    b();
    this.f = p_i46552_3_;
  }
  
  public void a(boolean isNot)
  {
    this.c = isNot;
  }
  
  public bnl a()
  {
    return this.d;
  }
  
  protected void a(ado itm, int subType, String identifier)
  {
    this.d.a(itm, subType, new bxt(identifier, "inventory"));
  }
  
  protected void a(ajt blk, int subType, String identifier)
  {
    a(ado.a(blk), subType, identifier);
  }
  
  private void a(ajt blk, String identifier)
  {
    a(blk, 0, identifier);
  }
  
  private void a(ado itm, String identifier)
  {
    a(itm, 0, identifier);
  }
  
  private void a(bxo model, adq stack)
  {
    a(model, -1, stack);
  }
  
  public void a(bxo model, int color)
  {
    a(model, color, (adq)null);
  }
  
  private void a(bxo model, int color, adq stack)
  {
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    
    boolean renderTextureMap = bcf.z().R().isTextureBound();
    
    boolean multiTexture = (Config.isMultiTexture()) && (renderTextureMap);
    if (multiTexture) {
      vertexbuffer.setBlockLayer(ahm.a);
    }
    vertexbuffer.a(7, bvp.b);
    for (cq enumfacing : cq.n) {
      a(vertexbuffer, model.a((arc)null, enumfacing, 0L), color, stack);
    }
    a(vertexbuffer, model.a((arc)null, (cq)null, 0L), color, stack);
    tessellator.b();
    if (multiTexture)
    {
      vertexbuffer.setBlockLayer(null);
      
      bni.bindCurrentTexture();
    }
  }
  
  public void a(adq stack, bxo model)
  {
    if (stack != null)
    {
      bni.G();
      bni.c(-0.5F, -0.5F, -0.5F);
      if (model.c())
      {
        bni.c(1.0F, 1.0F, 1.0F, 1.0F);
        bni.D();
        bne.a.a(stack);
      }
      else
      {
        if (Config.isCustomItems()) {
          model = CustomItems.getCustomItemModel(stack, model, this.modelLocation);
        }
        a(model, stack);
        if (stack.t()) {
          if ((!Config.isCustomItems()) || (!CustomItems.renderCustomEffect(this, stack, model))) {
            a(model);
          }
        }
      }
      bni.H();
    }
  }
  
  private void a(bxo model)
  {
    if (Config.isCustomItems()) {
      if (!CustomItems.isUseGlint()) {
        return;
      }
    }
    bni.a(false);
    bni.c(514);
    bni.g();
    bni.a(bni.r.n, bni.l.e);
    this.e.a(b);
    bni.n(5890);
    bni.G();
    bni.b(8.0F, 8.0F, 8.0F);
    float f = (float)(bcf.I() % 3000L) / 3000.0F / 8.0F;
    bni.c(f, 0.0F, 0.0F);
    bni.b(-50.0F, 0.0F, 0.0F, 1.0F);
    a(model, -8372020);
    bni.H();
    bni.G();
    bni.b(8.0F, 8.0F, 8.0F);
    float f1 = (float)(bcf.I() % 4873L) / 4873.0F / 8.0F;
    bni.c(-f1, 0.0F, 0.0F);
    bni.b(10.0F, 0.0F, 0.0F, 1.0F);
    a(model, -8372020);
    bni.H();
    bni.n(5888);
    bni.a(bni.r.l, bni.l.j);
    bni.f();
    bni.c(515);
    bni.a(true);
    this.e.a(bvg.g);
  }
  
  private void a(bmz renderer, bof quad)
  {
    df vec3i = quad.e().n();
    renderer.b(vec3i.p(), vec3i.q(), vec3i.r());
  }
  
  private void a(bmz renderer, bof quad, int color)
  {
    if (renderer.isMultiTexture())
    {
      renderer.a(quad.getVertexDataSingle());
      renderer.putSprite(quad.a());
    }
    else
    {
      renderer.a(quad.b());
    }
    if ((Reflector.IColoredBakedQuad.exists()) && (Reflector.IColoredBakedQuad.isInstance(quad))) {
      forgeHooksClient_putQuadColor(renderer, quad, color);
    } else {
      renderer.a(color);
    }
    a(renderer, quad);
  }
  
  private void a(bmz renderer, List<bof> quads, int color, adq stack)
  {
    boolean flag = (color == -1) && (stack != null);
    int i = 0;
    for (int j = quads.size(); i < j; i++)
    {
      bof bakedquad = (bof)quads.get(i);
      int k = color;
      if ((flag) && (bakedquad.c()))
      {
        k = this.f.a(stack, bakedquad.d());
        if (Config.isCustomColors()) {
          k = CustomColors.getColorFromItemStack(stack, bakedquad.d(), k);
        }
        if (bng.a) {
          k = bvk.c(k);
        }
        k |= 0xFF000000;
      }
      a(renderer, bakedquad, k);
    }
  }
  
  public boolean a(adq stack)
  {
    bxo ibakedmodel = this.d.a(stack);
    return ibakedmodel == null ? false : ibakedmodel.b();
  }
  
  public void a(adq stack, bos.b cameraTransformType)
  {
    if (stack != null)
    {
      bxo ibakedmodel = a(stack, (aht)null, (sa)null);
      a(stack, ibakedmodel, cameraTransformType, false);
    }
  }
  
  public bxo a(adq p_184393_1_, aht p_184393_2_, sa p_184393_3_)
  {
    bxo ibakedmodel = this.d.a(p_184393_1_);
    ado item = p_184393_1_.b();
    if ((item != null) && (item.i()))
    {
      kk resourcelocation = ibakedmodel.f().a(p_184393_1_, p_184393_2_, p_184393_3_);
      return resourcelocation == null ? ibakedmodel : this.d.a().a(new bxt(resourcelocation, "inventory"));
    }
    return ibakedmodel;
  }
  
  public void a(adq p_184392_1_, sa p_184392_2_, bos.b p_184392_3_, boolean p_184392_4_)
  {
    if ((p_184392_1_ != null) && (p_184392_2_ != null) && (p_184392_1_.b() != null))
    {
      bxo ibakedmodel = a(p_184392_1_, p_184392_2_.l, p_184392_2_);
      a(p_184392_1_, ibakedmodel, p_184392_3_, p_184392_4_);
    }
  }
  
  protected void a(adq p_184394_1_, bxo p_184394_2_, bos.b p_184394_3_, boolean p_184394_4_)
  {
    if (p_184394_1_.b() != null)
    {
      this.e.a(bvg.g);
      this.e.b(bvg.g).b(false, false);
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      bni.D();
      bni.a(516, 0.1F);
      bni.m();
      bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
      bni.G();
      bos itemcameratransforms = p_184394_2_.e();
      bos.a(itemcameratransforms.b(p_184394_3_), p_184394_4_);
      if (a(itemcameratransforms.b(p_184394_3_))) {
        bni.a(bni.i.a);
      }
      a(p_184394_1_, p_184394_2_);
      bni.a(bni.i.b);
      bni.H();
      bni.E();
      bni.l();
      this.e.a(bvg.g);
      this.e.b(bvg.g).a();
    }
  }
  
  private boolean a(bor itemTranformVec)
  {
    return (itemTranformVec.d.x < 0.0F ? 1 : 0) ^ (itemTranformVec.d.y < 0.0F ? 1 : 0) ^ (itemTranformVec.d.z < 0.0F ? 1 : 0);
  }
  
  public void a(adq stack, int x, int y)
  {
    a(stack, x, y, a(stack, (aht)null, (sa)null));
  }
  
  protected void a(adq p_184390_1_, int p_184390_2_, int p_184390_3_, bxo p_184390_4_)
  {
    bni.G();
    this.e.a(bvg.g);
    this.e.b(bvg.g).b(false, false);
    bni.D();
    bni.e();
    bni.a(516, 0.1F);
    bni.m();
    bni.a(bni.r.l, bni.l.j);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    a(p_184390_2_, p_184390_3_, p_184390_4_.b());
    p_184390_4_.e().a(bos.b.g);
    a(p_184390_1_, p_184390_4_);
    bni.d();
    bni.E();
    bni.g();
    bni.H();
    this.e.a(bvg.g);
    this.e.b(bvg.g).a();
  }
  
  private void a(int xPosition, int yPosition, boolean isGui3d)
  {
    bni.c(xPosition, yPosition, 100.0F + this.a);
    bni.c(8.0F, 8.0F, 0.0F);
    bni.b(1.0F, -1.0F, 1.0F);
    bni.b(16.0F, 16.0F, 16.0F);
    if (isGui3d) {
      bni.f();
    } else {
      bni.g();
    }
  }
  
  public void b(adq stack, int xPosition, int yPosition)
  {
    a(bcf.z().h, stack, xPosition, yPosition);
  }
  
  public void a(sa p_184391_1_, final adq p_184391_2_, int p_184391_3_, int p_184391_4_)
  {
    if ((p_184391_2_ != null) && (p_184391_2_.b() != null))
    {
      this.a += 50.0F;
      try
      {
        a(p_184391_2_, p_184391_3_, p_184391_4_, a(p_184391_2_, (aht)null, p_184391_1_));
      }
      catch (Throwable throwable)
      {
        b crashreport = b.a(throwable, "Rendering item");
        c crashreportcategory = crashreport.a("Item being rendered");
        crashreportcategory.a("Item Type", new Callable()
        {
          public String a()
            throws Exception
          {
            return String.valueOf(p_184391_2_.b());
          }
        });
        crashreportcategory.a("Item Aux", new Callable()
        {
          public String a()
            throws Exception
          {
            return String.valueOf(p_184391_2_.i());
          }
        });
        crashreportcategory.a("Item NBT", new Callable()
        {
          public String a()
            throws Exception
          {
            return String.valueOf(p_184391_2_.o());
          }
        });
        crashreportcategory.a("Item Foil", new Callable()
        {
          public String a()
            throws Exception
          {
            return String.valueOf(p_184391_2_.t());
          }
        });
        throw new e(crashreport);
      }
      this.a -= 50.0F;
    }
  }
  
  public void a(bct fr, adq stack, int xPosition, int yPosition)
  {
    a(fr, stack, xPosition, yPosition, (String)null);
  }
  
  public void a(bct fr, adq stack, int xPosition, int yPosition, String text)
  {
    if (stack != null)
    {
      if ((stack.b != 1) || (text != null))
      {
        String s = text == null ? String.valueOf(stack.b) : text;
        if ((text == null) && (stack.b < 1)) {
          s = a.m + String.valueOf(stack.b);
        }
        bni.g();
        bni.j();
        bni.l();
        fr.a(s, xPosition + 19 - 2 - fr.a(s), yPosition + 6 + 3, 16777215);
        bni.f();
        bni.k();
      }
      if (stack.g())
      {
        int j = (int)Math.round(13.0D - stack.h() * 13.0D / stack.j());
        int i = (int)Math.round(255.0D - stack.h() * 255.0D / stack.j());
        bni.g();
        bni.j();
        bni.z();
        bni.d();
        bni.l();
        bnu tessellator = bnu.a();
        bmz vertexbuffer = tessellator.c();
        a(vertexbuffer, xPosition + 2, yPosition + 13, 13, 2, 0, 0, 0, 255);
        a(vertexbuffer, xPosition + 2, yPosition + 13, 12, 1, (255 - i) / 4, 64, 0, 255);
        a(vertexbuffer, xPosition + 2, yPosition + 13, j, 1, 255 - i, i, 0, 255);
        bni.m();
        bni.e();
        bni.y();
        bni.f();
        bni.k();
      }
      bmt entityplayersp = bcf.z().h;
      float f = entityplayersp == null ? 0.0F : entityplayersp.da().a(stack.b(), bcf.z().ak());
      if (f > 0.0F)
      {
        bni.g();
        bni.j();
        bni.z();
        bnu tessellator1 = bnu.a();
        bmz vertexbuffer1 = tessellator1.c();
        a(vertexbuffer1, xPosition, yPosition + on.d(16.0F * (1.0F - f)), 16, on.f(16.0F * f), 255, 255, 255, 127);
        bni.y();
        bni.f();
        bni.k();
      }
    }
  }
  
  private void a(bmz renderer, int x, int y, int width, int height, int red, int green, int blue, int alpha)
  {
    renderer.a(7, bvp.f);
    renderer.b(x + 0, y + 0, 0.0D).b(red, green, blue, alpha).d();
    renderer.b(x + 0, y + height, 0.0D).b(red, green, blue, alpha).d();
    renderer.b(x + width, y + height, 0.0D).b(red, green, blue, alpha).d();
    renderer.b(x + width, y + 0, 0.0D).b(red, green, blue, alpha).d();
    bnu.a().b();
  }
  
  private void b()
  {
    a(aju.cf, "anvil_intact");
    a(aju.cf, 1, "anvil_slightly_damaged");
    a(aju.cf, 2, "anvil_very_damaged");
    a(aju.cy, act.p.a(), "black_carpet");
    a(aju.cy, act.l.a(), "blue_carpet");
    a(aju.cy, act.m.a(), "brown_carpet");
    a(aju.cy, act.j.a(), "cyan_carpet");
    a(aju.cy, act.h.a(), "gray_carpet");
    a(aju.cy, act.n.a(), "green_carpet");
    a(aju.cy, act.d.a(), "light_blue_carpet");
    a(aju.cy, act.f.a(), "lime_carpet");
    a(aju.cy, act.c.a(), "magenta_carpet");
    a(aju.cy, act.b.a(), "orange_carpet");
    a(aju.cy, act.g.a(), "pink_carpet");
    a(aju.cy, act.k.a(), "purple_carpet");
    a(aju.cy, act.o.a(), "red_carpet");
    a(aju.cy, act.i.a(), "silver_carpet");
    a(aju.cy, act.a.a(), "white_carpet");
    a(aju.cy, act.e.a(), "yellow_carpet");
    a(aju.bZ, apk.a.b.a(), "mossy_cobblestone_wall");
    a(aju.bZ, apk.a.a.a(), "cobblestone_wall");
    a(aju.d, akt.a.b.a(), "coarse_dirt");
    a(aju.d, akt.a.a.a(), "dirt");
    a(aju.d, akt.a.c.a(), "podzol");
    a(aju.cF, akw.b.d.a(), "double_fern");
    a(aju.cF, akw.b.c.a(), "double_grass");
    a(aju.cF, akw.b.f.a(), "paeonia");
    a(aju.cF, akw.b.e.a(), "double_rose");
    a(aju.cF, akw.b.a.a(), "sunflower");
    a(aju.cF, akw.b.b.a(), "syringa");
    a(aju.t, anj.a.c.a(), "birch_leaves");
    a(aju.t, anj.a.d.a(), "jungle_leaves");
    a(aju.t, anj.a.a.a(), "oak_leaves");
    a(aju.t, anj.a.b.a(), "spruce_leaves");
    a(aju.u, anj.a.e.a() - 4, "acacia_leaves");
    a(aju.u, anj.a.f.a() - 4, "dark_oak_leaves");
    a(aju.r, anj.a.c.a(), "birch_log");
    a(aju.r, anj.a.d.a(), "jungle_log");
    a(aju.r, anj.a.a.a(), "oak_log");
    a(aju.r, anj.a.b.a(), "spruce_log");
    a(aju.s, anj.a.e.a() - 4, "acacia_log");
    a(aju.s, anj.a.f.a() - 4, "dark_oak_log");
    a(aju.be, amt.a.f.a(), "chiseled_brick_monster_egg");
    a(aju.be, amt.a.b.a(), "cobblestone_monster_egg");
    a(aju.be, amt.a.e.a(), "cracked_brick_monster_egg");
    a(aju.be, amt.a.d.a(), "mossy_brick_monster_egg");
    a(aju.be, amt.a.a.a(), "stone_monster_egg");
    a(aju.be, amt.a.c.a(), "stone_brick_monster_egg");
    a(aju.f, anj.a.e.a(), "acacia_planks");
    a(aju.f, anj.a.c.a(), "birch_planks");
    a(aju.f, anj.a.f.a(), "dark_oak_planks");
    a(aju.f, anj.a.d.a(), "jungle_planks");
    a(aju.f, anj.a.a.a(), "oak_planks");
    a(aju.f, anj.a.b.a(), "spruce_planks");
    a(aju.cI, anp.a.b.a(), "prismarine_bricks");
    a(aju.cI, anp.a.c.a(), "dark_prismarine");
    a(aju.cI, anp.a.a.a(), "prismarine");
    a(aju.cq, ans.a.b.a(), "chiseled_quartz_block");
    a(aju.cq, ans.a.a.a(), "quartz_block");
    a(aju.cq, ans.a.c.a(), "quartz_column");
    a(aju.O, alm.a.d.b(), "allium");
    a(aju.O, alm.a.c.b(), "blue_orchid");
    a(aju.O, alm.a.e.b(), "houstonia");
    a(aju.O, alm.a.g.b(), "orange_tulip");
    a(aju.O, alm.a.j.b(), "oxeye_daisy");
    a(aju.O, alm.a.i.b(), "pink_tulip");
    a(aju.O, alm.a.b.b(), "poppy");
    a(aju.O, alm.a.f.b(), "red_tulip");
    a(aju.O, alm.a.h.b(), "white_tulip");
    a(aju.m, aof.a.b.a(), "red_sand");
    a(aju.m, aof.a.a.a(), "sand");
    a(aju.A, aog.a.b.a(), "chiseled_sandstone");
    a(aju.A, aog.a.a.a(), "sandstone");
    a(aju.A, aog.a.c.a(), "smooth_sandstone");
    a(aju.cM, anv.a.b.a(), "chiseled_red_sandstone");
    a(aju.cM, anv.a.a.a(), "red_sandstone");
    a(aju.cM, anv.a.c.a(), "smooth_red_sandstone");
    a(aju.g, anj.a.e.a(), "acacia_sapling");
    a(aju.g, anj.a.c.a(), "birch_sapling");
    a(aju.g, anj.a.f.a(), "dark_oak_sapling");
    a(aju.g, anj.a.d.a(), "jungle_sapling");
    a(aju.g, anj.a.a.a(), "oak_sapling");
    a(aju.g, anj.a.b.a(), "spruce_sapling");
    a(aju.v, 0, "sponge");
    a(aju.v, 1, "sponge_wet");
    a(aju.cG, act.p.a(), "black_stained_glass");
    a(aju.cG, act.l.a(), "blue_stained_glass");
    a(aju.cG, act.m.a(), "brown_stained_glass");
    a(aju.cG, act.j.a(), "cyan_stained_glass");
    a(aju.cG, act.h.a(), "gray_stained_glass");
    a(aju.cG, act.n.a(), "green_stained_glass");
    a(aju.cG, act.d.a(), "light_blue_stained_glass");
    a(aju.cG, act.f.a(), "lime_stained_glass");
    a(aju.cG, act.c.a(), "magenta_stained_glass");
    a(aju.cG, act.b.a(), "orange_stained_glass");
    a(aju.cG, act.g.a(), "pink_stained_glass");
    a(aju.cG, act.k.a(), "purple_stained_glass");
    a(aju.cG, act.o.a(), "red_stained_glass");
    a(aju.cG, act.i.a(), "silver_stained_glass");
    a(aju.cG, act.a.a(), "white_stained_glass");
    a(aju.cG, act.e.a(), "yellow_stained_glass");
    a(aju.cH, act.p.a(), "black_stained_glass_pane");
    a(aju.cH, act.l.a(), "blue_stained_glass_pane");
    a(aju.cH, act.m.a(), "brown_stained_glass_pane");
    a(aju.cH, act.j.a(), "cyan_stained_glass_pane");
    a(aju.cH, act.h.a(), "gray_stained_glass_pane");
    a(aju.cH, act.n.a(), "green_stained_glass_pane");
    a(aju.cH, act.d.a(), "light_blue_stained_glass_pane");
    a(aju.cH, act.f.a(), "lime_stained_glass_pane");
    a(aju.cH, act.c.a(), "magenta_stained_glass_pane");
    a(aju.cH, act.b.a(), "orange_stained_glass_pane");
    a(aju.cH, act.g.a(), "pink_stained_glass_pane");
    a(aju.cH, act.k.a(), "purple_stained_glass_pane");
    a(aju.cH, act.o.a(), "red_stained_glass_pane");
    a(aju.cH, act.i.a(), "silver_stained_glass_pane");
    a(aju.cH, act.a.a(), "white_stained_glass_pane");
    a(aju.cH, act.e.a(), "yellow_stained_glass_pane");
    a(aju.cu, act.p.a(), "black_stained_hardened_clay");
    a(aju.cu, act.l.a(), "blue_stained_hardened_clay");
    a(aju.cu, act.m.a(), "brown_stained_hardened_clay");
    a(aju.cu, act.j.a(), "cyan_stained_hardened_clay");
    a(aju.cu, act.h.a(), "gray_stained_hardened_clay");
    a(aju.cu, act.n.a(), "green_stained_hardened_clay");
    a(aju.cu, act.d.a(), "light_blue_stained_hardened_clay");
    a(aju.cu, act.f.a(), "lime_stained_hardened_clay");
    a(aju.cu, act.c.a(), "magenta_stained_hardened_clay");
    a(aju.cu, act.b.a(), "orange_stained_hardened_clay");
    a(aju.cu, act.g.a(), "pink_stained_hardened_clay");
    a(aju.cu, act.k.a(), "purple_stained_hardened_clay");
    a(aju.cu, act.o.a(), "red_stained_hardened_clay");
    a(aju.cu, act.i.a(), "silver_stained_hardened_clay");
    a(aju.cu, act.a.a(), "white_stained_hardened_clay");
    a(aju.cu, act.e.a(), "yellow_stained_hardened_clay");
    a(aju.b, aox.a.f.a(), "andesite");
    a(aju.b, aox.a.g.a(), "andesite_smooth");
    a(aju.b, aox.a.d.a(), "diorite");
    a(aju.b, aox.a.e.a(), "diorite_smooth");
    a(aju.b, aox.a.b.a(), "granite");
    a(aju.b, aox.a.c.a(), "granite_smooth");
    a(aju.b, aox.a.a.a(), "stone");
    a(aju.bf, aoy.a.c.a(), "cracked_stonebrick");
    a(aju.bf, aoy.a.a.a(), "stonebrick");
    a(aju.bf, aoy.a.d.a(), "chiseled_stonebrick");
    a(aju.bf, aoy.a.b.a(), "mossy_stonebrick");
    a(aju.U, apa.a.e.a(), "brick_slab");
    a(aju.U, apa.a.d.a(), "cobblestone_slab");
    a(aju.U, apa.a.c.a(), "old_wood_slab");
    a(aju.U, apa.a.g.a(), "nether_brick_slab");
    a(aju.U, apa.a.h.a(), "quartz_slab");
    a(aju.U, apa.a.b.a(), "sandstone_slab");
    a(aju.U, apa.a.f.a(), "stone_brick_slab");
    a(aju.U, apa.a.a.a(), "stone_slab");
    a(aju.cP, anb.a.a.a(), "red_sandstone_slab");
    a(aju.H, apc.a.a.a(), "dead_bush");
    a(aju.H, apc.a.c.a(), "fern");
    a(aju.H, apc.a.b.a(), "tall_grass");
    a(aju.bM, anj.a.e.a(), "acacia_slab");
    a(aju.bM, anj.a.c.a(), "birch_slab");
    a(aju.bM, anj.a.f.a(), "dark_oak_slab");
    a(aju.bM, anj.a.d.a(), "jungle_slab");
    a(aju.bM, anj.a.a.a(), "oak_slab");
    a(aju.bM, anj.a.b.a(), "spruce_slab");
    a(aju.L, act.p.a(), "black_wool");
    a(aju.L, act.l.a(), "blue_wool");
    a(aju.L, act.m.a(), "brown_wool");
    a(aju.L, act.j.a(), "cyan_wool");
    a(aju.L, act.h.a(), "gray_wool");
    a(aju.L, act.n.a(), "green_wool");
    a(aju.L, act.d.a(), "light_blue_wool");
    a(aju.L, act.f.a(), "lime_wool");
    a(aju.L, act.c.a(), "magenta_wool");
    a(aju.L, act.b.a(), "orange_wool");
    a(aju.L, act.g.a(), "pink_wool");
    a(aju.L, act.k.a(), "purple_wool");
    a(aju.L, act.o.a(), "red_wool");
    a(aju.L, act.i.a(), "silver_wool");
    a(aju.L, act.a.a(), "white_wool");
    a(aju.L, act.e.a(), "yellow_wool");
    a(aju.ak, "farmland");
    a(aju.cC, "acacia_stairs");
    a(aju.cs, "activator_rail");
    a(aju.bY, "beacon");
    a(aju.h, "bedrock");
    a(aju.bV, "birch_stairs");
    a(aju.X, "bookshelf");
    a(aju.V, "brick_block");
    a(aju.V, "brick_block");
    a(aju.bu, "brick_stairs");
    a(aju.P, "brown_mushroom");
    a(aju.aK, "cactus");
    a(aju.aL, "clay");
    a(aju.cA, "coal_block");
    a(aju.q, "coal_ore");
    a(aju.e, "cobblestone");
    a(aju.ai, "crafting_table");
    a(aju.cD, "dark_oak_stairs");
    a(aju.cl, "daylight_detector");
    a(aju.I, "dead_bush");
    a(aju.E, "detector_rail");
    a(aju.ah, "diamond_block");
    a(aju.ag, "diamond_ore");
    a(aju.z, "dispenser");
    a(aju.ct, "dropper");
    a(aju.bT, "emerald_block");
    a(aju.bP, "emerald_ore");
    a(aju.bC, "enchanting_table");
    a(aju.bG, "end_portal_frame");
    a(aju.bH, "end_stone");
    a(aju.aO, "oak_fence");
    a(aju.aP, "spruce_fence");
    a(aju.aQ, "birch_fence");
    a(aju.aR, "jungle_fence");
    a(aju.aS, "dark_oak_fence");
    a(aju.aT, "acacia_fence");
    a(aju.bo, "oak_fence_gate");
    a(aju.bp, "spruce_fence_gate");
    a(aju.bq, "birch_fence_gate");
    a(aju.br, "jungle_fence_gate");
    a(aju.bs, "dark_oak_fence_gate");
    a(aju.bt, "acacia_fence_gate");
    a(aju.al, "furnace");
    a(aju.w, "glass");
    a(aju.bj, "glass_pane");
    a(aju.aX, "glowstone");
    a(aju.D, "golden_rail");
    a(aju.R, "gold_block");
    a(aju.o, "gold_ore");
    a(aju.c, "grass");
    a(aju.da, "grass_path");
    a(aju.n, "gravel");
    a(aju.cz, "hardened_clay");
    a(aju.cx, "hay_block");
    a(aju.ci, "heavy_weighted_pressure_plate");
    a(aju.cp, "hopper");
    a(aju.aI, "ice");
    a(aju.bi, "iron_bars");
    a(aju.S, "iron_block");
    a(aju.p, "iron_ore");
    a(aju.cw, "iron_trapdoor");
    a(aju.aN, "jukebox");
    a(aju.bW, "jungle_stairs");
    a(aju.au, "ladder");
    a(aju.y, "lapis_block");
    a(aju.x, "lapis_ore");
    a(aju.ay, "lever");
    a(aju.ch, "light_weighted_pressure_plate");
    a(aju.aZ, "lit_pumpkin");
    a(aju.bk, "melon_block");
    a(aju.Y, "mossy_cobblestone");
    a(aju.bw, "mycelium");
    a(aju.aV, "netherrack");
    a(aju.by, "nether_brick");
    a(aju.bz, "nether_brick_fence");
    a(aju.bA, "nether_brick_stairs");
    a(aju.B, "noteblock");
    a(aju.ad, "oak_stairs");
    a(aju.Z, "obsidian");
    a(aju.cB, "packed_ice");
    a(aju.J, "piston");
    a(aju.aU, "pumpkin");
    a(aju.co, "quartz_ore");
    a(aju.cr, "quartz_stairs");
    a(aju.av, "rail");
    a(aju.cn, "redstone_block");
    a(aju.bJ, "redstone_lamp");
    a(aju.aC, "redstone_ore");
    a(aju.aF, "redstone_torch");
    a(aju.Q, "red_mushroom");
    a(aju.bO, "sandstone_stairs");
    a(aju.cN, "red_sandstone_stairs");
    a(aju.cJ, "sea_lantern");
    a(aju.cE, "slime");
    a(aju.aJ, "snow");
    a(aju.aH, "snow_layer");
    a(aju.aW, "soul_sand");
    a(aju.bU, "spruce_stairs");
    a(aju.F, "sticky_piston");
    a(aju.bv, "stone_brick_stairs");
    a(aju.aG, "stone_button");
    a(aju.az, "stone_pressure_plate");
    a(aju.aw, "stone_stairs");
    a(aju.W, "tnt");
    a(aju.aa, "torch");
    a(aju.bd, "trapdoor");
    a(aju.bR, "tripwire_hook");
    a(aju.bn, "vine");
    a(aju.bx, "waterlily");
    a(aju.G, "web");
    a(aju.cd, "wooden_button");
    a(aju.aB, "wooden_pressure_plate");
    a(aju.N, alm.a.a.b(), "dandelion");
    a(aju.cQ, "end_rod");
    a(aju.cR, "chorus_plant");
    a(aju.cS, "chorus_flower");
    a(aju.cT, "purpur_block");
    a(aju.cU, "purpur_pillar");
    a(aju.cV, "purpur_stairs");
    a(aju.cX, "purpur_slab");
    a(aju.cW, "purpur_double_slab");
    a(aju.cY, "end_bricks");
    a(aju.ae, "chest");
    a(aju.cg, "trapped_chest");
    a(aju.bQ, "ender_chest");
    a(ads.a, "iron_shovel");
    a(ads.b, "iron_pickaxe");
    a(ads.c, "iron_axe");
    a(ads.d, "flint_and_steel");
    a(ads.e, "apple");
    a(ads.f, "bow");
    a(ads.g, "arrow");
    a(ads.h, "spectral_arrow");
    a(ads.i, "tipped_arrow");
    a(ads.j, 0, "coal");
    a(ads.j, 1, "charcoal");
    a(ads.k, "diamond");
    a(ads.l, "iron_ingot");
    a(ads.m, "gold_ingot");
    a(ads.n, "iron_sword");
    a(ads.o, "wooden_sword");
    a(ads.p, "wooden_shovel");
    a(ads.q, "wooden_pickaxe");
    a(ads.r, "wooden_axe");
    a(ads.s, "stone_sword");
    a(ads.t, "stone_shovel");
    a(ads.u, "stone_pickaxe");
    a(ads.v, "stone_axe");
    a(ads.w, "diamond_sword");
    a(ads.x, "diamond_shovel");
    a(ads.y, "diamond_pickaxe");
    a(ads.z, "diamond_axe");
    a(ads.A, "stick");
    a(ads.B, "bowl");
    a(ads.C, "mushroom_stew");
    a(ads.D, "golden_sword");
    a(ads.E, "golden_shovel");
    a(ads.F, "golden_pickaxe");
    a(ads.G, "golden_axe");
    a(ads.H, "string");
    a(ads.I, "feather");
    a(ads.J, "gunpowder");
    a(ads.K, "wooden_hoe");
    a(ads.L, "stone_hoe");
    a(ads.M, "iron_hoe");
    a(ads.N, "diamond_hoe");
    a(ads.O, "golden_hoe");
    a(ads.P, "wheat_seeds");
    a(ads.Q, "wheat");
    a(ads.R, "bread");
    a(ads.S, "leather_helmet");
    a(ads.T, "leather_chestplate");
    a(ads.U, "leather_leggings");
    a(ads.V, "leather_boots");
    a(ads.W, "chainmail_helmet");
    a(ads.X, "chainmail_chestplate");
    a(ads.Y, "chainmail_leggings");
    a(ads.Z, "chainmail_boots");
    a(ads.aa, "iron_helmet");
    a(ads.ab, "iron_chestplate");
    a(ads.ac, "iron_leggings");
    a(ads.ad, "iron_boots");
    a(ads.ae, "diamond_helmet");
    a(ads.af, "diamond_chestplate");
    a(ads.ag, "diamond_leggings");
    a(ads.ah, "diamond_boots");
    a(ads.ai, "golden_helmet");
    a(ads.aj, "golden_chestplate");
    a(ads.ak, "golden_leggings");
    a(ads.al, "golden_boots");
    a(ads.am, "flint");
    a(ads.an, "porkchop");
    a(ads.ao, "cooked_porkchop");
    a(ads.ap, "painting");
    a(ads.aq, "golden_apple");
    a(ads.aq, 1, "golden_apple");
    a(ads.ar, "sign");
    a(ads.as, "oak_door");
    a(ads.at, "spruce_door");
    a(ads.au, "birch_door");
    a(ads.av, "jungle_door");
    a(ads.aw, "acacia_door");
    a(ads.ax, "dark_oak_door");
    a(ads.ay, "bucket");
    a(ads.az, "water_bucket");
    a(ads.aA, "lava_bucket");
    a(ads.aB, "minecart");
    a(ads.aC, "saddle");
    a(ads.aD, "iron_door");
    a(ads.aE, "redstone");
    a(ads.aF, "snowball");
    a(ads.aG, "oak_boat");
    a(ads.aH, "spruce_boat");
    a(ads.aI, "birch_boat");
    a(ads.aJ, "jungle_boat");
    a(ads.aK, "acacia_boat");
    a(ads.aL, "dark_oak_boat");
    a(ads.aM, "leather");
    a(ads.aN, "milk_bucket");
    a(ads.aO, "brick");
    a(ads.aP, "clay_ball");
    a(ads.aQ, "reeds");
    a(ads.aR, "paper");
    a(ads.aS, "book");
    a(ads.aT, "slime_ball");
    a(ads.aU, "chest_minecart");
    a(ads.aV, "furnace_minecart");
    a(ads.aW, "egg");
    a(ads.aX, "compass");
    a(ads.aY, "fishing_rod");
    a(ads.aZ, "clock");
    a(ads.ba, "glowstone_dust");
    a(ads.bb, adh.a.a.a(), "cod");
    a(ads.bb, adh.a.b.a(), "salmon");
    a(ads.bb, adh.a.c.a(), "clownfish");
    a(ads.bb, adh.a.d.a(), "pufferfish");
    a(ads.bc, adh.a.a.a(), "cooked_cod");
    a(ads.bc, adh.a.b.a(), "cooked_salmon");
    a(ads.bd, act.p.b(), "dye_black");
    a(ads.bd, act.o.b(), "dye_red");
    a(ads.bd, act.n.b(), "dye_green");
    a(ads.bd, act.m.b(), "dye_brown");
    a(ads.bd, act.l.b(), "dye_blue");
    a(ads.bd, act.k.b(), "dye_purple");
    a(ads.bd, act.j.b(), "dye_cyan");
    a(ads.bd, act.i.b(), "dye_silver");
    a(ads.bd, act.h.b(), "dye_gray");
    a(ads.bd, act.g.b(), "dye_pink");
    a(ads.bd, act.f.b(), "dye_lime");
    a(ads.bd, act.e.b(), "dye_yellow");
    a(ads.bd, act.d.b(), "dye_light_blue");
    a(ads.bd, act.c.b(), "dye_magenta");
    a(ads.bd, act.b.b(), "dye_orange");
    a(ads.bd, act.a.b(), "dye_white");
    a(ads.be, "bone");
    a(ads.bf, "sugar");
    a(ads.bg, "cake");
    a(ads.bh, "bed");
    a(ads.bi, "repeater");
    a(ads.bj, "cookie");
    a(ads.bl, "shears");
    a(ads.bm, "melon");
    a(ads.bn, "pumpkin_seeds");
    a(ads.bo, "melon_seeds");
    a(ads.bp, "beef");
    a(ads.bq, "cooked_beef");
    a(ads.br, "chicken");
    a(ads.bs, "cooked_chicken");
    a(ads.bv, "rabbit");
    a(ads.bw, "cooked_rabbit");
    a(ads.bt, "mutton");
    a(ads.bu, "cooked_mutton");
    a(ads.by, "rabbit_foot");
    a(ads.bz, "rabbit_hide");
    a(ads.bx, "rabbit_stew");
    a(ads.bA, "rotten_flesh");
    a(ads.bB, "ender_pearl");
    a(ads.bC, "blaze_rod");
    a(ads.bD, "ghast_tear");
    a(ads.bE, "gold_nugget");
    a(ads.bF, "nether_wart");
    a(ads.cV, "beetroot");
    a(ads.cU, "beetroot_seeds");
    a(ads.cW, "beetroot_soup");
    a(ads.bG, "bottle_drinkable");
    a(ads.bH, "bottle_splash");
    a(ads.bI, "bottle_lingering");
    a(ads.bJ, "glass_bottle");
    a(ads.bK, "dragon_breath");
    a(ads.bL, "spider_eye");
    a(ads.bM, "fermented_spider_eye");
    a(ads.bN, "blaze_powder");
    a(ads.bO, "magma_cream");
    a(ads.bP, "brewing_stand");
    a(ads.bQ, "cauldron");
    a(ads.bR, "ender_eye");
    a(ads.bS, "speckled_melon");
    this.d.a(ads.bT, new bnm()
    {
      public bxt a(adq stack)
      {
        return new bxt("spawn_egg", "inventory");
      }
    });
    a(ads.bU, "experience_bottle");
    a(ads.bV, "fire_charge");
    a(ads.bW, "writable_book");
    a(ads.bY, "emerald");
    a(ads.bZ, "item_frame");
    a(ads.ca, "flower_pot");
    a(ads.cb, "carrot");
    a(ads.cc, "potato");
    a(ads.cd, "baked_potato");
    a(ads.ce, "poisonous_potato");
    a(ads.cf, "map");
    a(ads.cg, "golden_carrot");
    a(ads.ch, 0, "skull_skeleton");
    a(ads.ch, 1, "skull_wither");
    a(ads.ch, 2, "skull_zombie");
    a(ads.ch, 3, "skull_char");
    a(ads.ch, 4, "skull_creeper");
    a(ads.ch, 5, "skull_dragon");
    a(ads.ci, "carrot_on_a_stick");
    a(ads.cj, "nether_star");
    a(ads.cP, "end_crystal");
    a(ads.ck, "pumpkin_pie");
    a(ads.cm, "firework_charge");
    a(ads.co, "comparator");
    a(ads.cp, "netherbrick");
    a(ads.cq, "quartz");
    a(ads.cr, "tnt_minecart");
    a(ads.cs, "hopper_minecart");
    a(ads.ct, "armor_stand");
    a(ads.cu, "iron_horse_armor");
    a(ads.cv, "golden_horse_armor");
    a(ads.cw, "diamond_horse_armor");
    a(ads.cx, "lead");
    a(ads.cy, "name_tag");
    this.d.a(ads.cO, new bnm()
    {
      public bxt a(adq stack)
      {
        return new bxt("banner", "inventory");
      }
    });
    this.d.a(ads.cQ, new bnm()
    {
      public bxt a(adq stack)
      {
        return new bxt("shield", "inventory");
      }
    });
    a(ads.cR, "elytra");
    a(ads.cS, "chorus_fruit");
    a(ads.cT, "chorus_fruit_popped");
    a(ads.cA, "record_13");
    a(ads.cB, "record_cat");
    a(ads.cC, "record_blocks");
    a(ads.cD, "record_chirp");
    a(ads.cE, "record_far");
    a(ads.cF, "record_mall");
    a(ads.cG, "record_mellohi");
    a(ads.cH, "record_stal");
    a(ads.cI, "record_strad");
    a(ads.cJ, "record_ward");
    a(ads.cK, "record_11");
    a(ads.cL, "record_wait");
    a(ads.cM, "prismarine_shard");
    a(ads.cN, "prismarine_crystals");
    this.d.a(ads.cn, new bnm()
    {
      public bxt a(adq stack)
      {
        return new bxt("enchanted_book", "inventory");
      }
    });
    this.d.a(ads.bk, new bnm()
    {
      public bxt a(adq stack)
      {
        return new bxt("filled_map", "inventory");
      }
    });
    a(aju.bX, "command_block");
    a(ads.cl, "fireworks");
    a(ads.cz, "command_block_minecart");
    a(aju.cv, "barrier");
    a(aju.ac, "mob_spawner");
    a(ads.bX, "written_book");
    a(aju.bg, amh.a.k.a(), "brown_mushroom_block");
    a(aju.bh, amh.a.k.a(), "red_mushroom_block");
    a(aju.bI, "dragon_egg");
    a(aju.dc, "repeating_command_block");
    a(aju.dd, "chain_command_block");
    a(aju.df, aqp.a.a.a(), "structure_block");
    a(aju.df, aqp.a.b.a(), "structure_block");
    a(aju.df, aqp.a.c.a(), "structure_block");
    a(aju.df, aqp.a.d.a(), "structure_block");
    if (Reflector.ModelLoader_onRegisterItems.exists()) {
      Reflector.call(Reflector.ModelLoader_onRegisterItems, new Object[] { this.d });
    }
  }
  
  public void a(bwg resourceManager)
  {
    this.d.b();
  }
  
  public static void forgeHooksClient_putQuadColor(bmz renderer, bof quad, int color)
  {
    float cr = color & 0xFF;
    float cg = color >>> 8 & 0xFF;
    float cb = color >>> 16 & 0xFF;
    float ca = color >>> 24 & 0xFF;
    
    int[] vd = quad.b();
    int step = vd.length / 4;
    for (int i = 0; i < 4; i++)
    {
      int vc = vd[(3 + step * i)];
      float vcr = vc & 0xFF;
      float vcg = vc >>> 8 & 0xFF;
      float vcb = vc >>> 16 & 0xFF;
      float vca = vc >>> 24 & 0xFF;
      int ncr = Math.min(255, (int)(cr * vcr / 255.0F));
      int ncg = Math.min(255, (int)(cg * vcg / 255.0F));
      int ncb = Math.min(255, (int)(cb * vcb / 255.0F));
      int nca = Math.min(255, (int)(ca * vca / 255.0F));
      renderer.a(renderer.c(4 - i), ncr, ncg, ncb, nca);
    }
  }
}
