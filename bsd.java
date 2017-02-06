import com.google.common.collect.Lists;
import de.labystudio.cosmetic.CosmeticManager;
import de.labystudio.gommehd.GommeHDBed;
import de.labystudio.hologram.Hologram;
import de.labystudio.hologram.Manager;
import de.labystudio.hologram.SetColor;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.modapi.ModAPI;
import de.labystudio.modapi.events.RenderNametagEvent;
import de.labystudio.utils.Allowed;
import de.labystudio.utils.Color;
import de.labystudio.utils.FriendsLoader;
import java.nio.FloatBuffer;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public abstract class bsd<T extends sa>
  extends brn<T>
{
  private static final Logger a = ;
  private static final bux b = new bux(16, 16);
  protected bjc g;
  protected FloatBuffer h = bce.h(4);
  protected List<bty<T>> i = Lists.newArrayList();
  protected boolean j = false;
  
  public bsd(brm renderManagerIn, bjc modelBaseIn, float shadowSizeIn)
  {
    super(renderManagerIn);
    this.g = modelBaseIn;
    this.d = shadowSizeIn;
  }
  
  protected <V extends sa, U extends bty<V>> boolean a(U layer)
  {
    return this.i.add(layer);
  }
  
  protected <V extends sa, U extends bty<V>> boolean b(U layer)
  {
    return this.i.remove(layer);
  }
  
  public bjc b()
  {
    return this.g;
  }
  
  protected float a(float par1, float par2, float par3)
  {
    for (float f = par2 - par1; f < -180.0F; f += 360.0F) {}
    while (f >= 180.0F) {
      f -= 360.0F;
    }
    return par1 + par3 * f;
  }
  
  public void e() {}
  
  public void a(T entity, double x, double y, double z, float entityYaw, float partialTicks)
  {
    bni.G();
    bni.r();
    this.g.o = e(entity, partialTicks);
    this.g.p = entity.aI();
    this.g.q = entity.m_();
    try
    {
      float f = a(entity.aN, entity.aM, partialTicks);
      float f1 = a(entity.aP, entity.aO, partialTicks);
      float f2 = f1 - f;
      if ((entity.aI()) && ((entity.by() instanceof sa)))
      {
        sa entitylivingbase = (sa)entity.by();
        f = a(entitylivingbase.aN, entitylivingbase.aM, partialTicks);
        f2 = f1 - f;
        float f3 = on.g(f2);
        if (f3 < -85.0F) {
          f3 = -85.0F;
        }
        if (f3 >= 85.0F) {
          f3 = 85.0F;
        }
        f = f1 - f3;
        if (f3 * f3 > 2500.0F) {
          f += f3 * 0.2F;
        }
      }
      float f7 = entity.y + (entity.w - entity.y) * partialTicks;
      a(entity, x, y, z);
      float f8 = b(entity, partialTicks);
      a(entity, f8, f, partialTicks);
      float f4 = c(entity, partialTicks);
      float f5 = 0.0F;
      float f6 = 0.0F;
      if (!entity.aI())
      {
        f5 = entity.aE + (entity.aF - entity.aE) * partialTicks;
        f6 = entity.aG - entity.aF * (1.0F - partialTicks);
        if (entity.m_()) {
          f6 *= 3.0F;
        }
        if (f5 > 1.0F) {
          f5 = 1.0F;
        }
      }
      bni.e();
      this.g.a(entity, f6, f5, partialTicks);
      this.g.a(f6, f5, f8, f2, f7, f4, entity);
      if (this.f)
      {
        boolean flag1 = c(entity);
        bni.h();
        bni.e(c(entity));
        if (!this.j) {
          a(entity, f6, f5, f8, f2, f7, f4);
        }
        if ((!(entity instanceof zj)) || (!((zj)entity).y())) {
          a(entity, f6, f5, partialTicks, f8, f2, f7, f4);
        }
        bni.n();
        bni.i();
        if (flag1) {
          f();
        }
      }
      else
      {
        boolean flag = d(entity, partialTicks);
        a(entity, f6, f5, f8, f2, f7, f4);
        if (flag) {
          g();
        }
        bni.a(true);
        if ((!(entity instanceof zj)) || (!((zj)entity).y())) {
          a(entity, f6, f5, partialTicks, f8, f2, f7, f4);
        }
      }
      bni.E();
    }
    catch (Exception exception)
    {
      a.error("Couldn't render entity", exception);
    }
    bni.g(bzg.r);
    bni.y();
    bni.g(bzg.q);
    bni.q();
    bni.H();
    super.a(entity, x, y, z, entityYaw, partialTicks);
    
    renderHolograms();
  }
  
  public float c(T p_188322_1_, float p_188322_2_)
  {
    bni.D();
    bni.b(-1.0F, -1.0F, 1.0F);
    a(p_188322_1_, p_188322_2_);
    float f = 0.0625F;
    bni.c(0.0F, -1.501F, 0.0F);
    return 0.0625F;
  }
  
  protected boolean c(T entityLivingBaseIn)
  {
    bni.g();
    bni.g(bzg.r);
    bni.z();
    bni.g(bzg.q);
    return true;
  }
  
  protected void f()
  {
    bni.f();
    bni.g(bzg.r);
    bni.y();
    bni.g(bzg.q);
  }
  
  protected void a(T entitylivingbaseIn, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float scaleFactor)
  {
    boolean flag = (!entitylivingbaseIn.aN()) || (this.f);
    boolean flag1 = (!flag) && (!entitylivingbaseIn.e(bcf.z().h));
    if ((flag) || (flag1))
    {
      if (!d(entitylivingbaseIn)) {
        return;
      }
      if (flag1) {
        bni.a(bni.q.c);
      }
      this.g.a(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
      if (flag1) {
        bni.b(bni.q.c);
      }
    }
  }
  
  protected boolean d(T entityLivingBaseIn, float partialTicks)
  {
    return a(entityLivingBaseIn, partialTicks, true);
  }
  
  protected boolean a(T entitylivingbaseIn, float partialTicks, boolean combineTextures)
  {
    float f = entitylivingbaseIn.e(partialTicks);
    int i = a(entitylivingbaseIn, f, partialTicks);
    boolean flag = (i >> 24 & 0xFF) > 0;
    boolean flag1 = (entitylivingbaseIn.ax > 0) || (entitylivingbaseIn.aA > 0);
    if ((!flag) && (!flag1)) {
      return false;
    }
    if ((!flag) && (!combineTextures)) {
      return false;
    }
    bni.g(bzg.q);
    bni.y();
    bni.a(8960, 8704, bzg.t);
    bni.a(8960, bzg.y, 8448);
    bni.a(8960, bzg.z, bzg.q);
    bni.a(8960, bzg.A, bzg.v);
    bni.a(8960, bzg.C, 768);
    bni.a(8960, bzg.D, 768);
    bni.a(8960, bzg.F, 7681);
    bni.a(8960, bzg.G, bzg.q);
    bni.a(8960, bzg.J, 770);
    bni.g(bzg.r);
    bni.y();
    bni.a(8960, 8704, bzg.t);
    bni.a(8960, bzg.y, bzg.u);
    bni.a(8960, bzg.z, bzg.w);
    bni.a(8960, bzg.A, bzg.x);
    bni.a(8960, bzg.B, bzg.w);
    bni.a(8960, bzg.C, 768);
    bni.a(8960, bzg.D, 768);
    bni.a(8960, bzg.E, 770);
    bni.a(8960, bzg.F, 7681);
    bni.a(8960, bzg.G, bzg.x);
    bni.a(8960, bzg.J, 770);
    this.h.position(0);
    if (flag1)
    {
      if (ConfigManager.settings.oldDMG.booleanValue())
      {
        this.h.put(f);
        this.h.put(0.0F);
        this.h.put(0.0F);
        this.h.put(0.2F);
      }
      else
      {
        this.h.put(1.0F);
        this.h.put(0.0F);
        this.h.put(0.0F);
        this.h.put(0.3F);
      }
    }
    else
    {
      float f1 = (i >> 24 & 0xFF) / 255.0F;
      float f2 = (i >> 16 & 0xFF) / 255.0F;
      float f3 = (i >> 8 & 0xFF) / 255.0F;
      float f4 = (i & 0xFF) / 255.0F;
      this.h.put(f2);
      this.h.put(f3);
      this.h.put(f4);
      this.h.put(1.0F - f1);
    }
    this.h.flip();
    bni.b(8960, 8705, this.h);
    bni.g(bzg.s);
    bni.y();
    bni.i(b.b());
    bni.a(8960, 8704, bzg.t);
    bni.a(8960, bzg.y, 8448);
    bni.a(8960, bzg.z, bzg.x);
    bni.a(8960, bzg.A, bzg.r);
    bni.a(8960, bzg.C, 768);
    bni.a(8960, bzg.D, 768);
    bni.a(8960, bzg.F, 7681);
    bni.a(8960, bzg.G, bzg.x);
    bni.a(8960, bzg.J, 770);
    bni.g(bzg.q);
    return true;
  }
  
  protected void g()
  {
    bni.g(bzg.q);
    bni.y();
    bni.a(8960, 8704, bzg.t);
    bni.a(8960, bzg.y, 8448);
    bni.a(8960, bzg.z, bzg.q);
    bni.a(8960, bzg.A, bzg.v);
    bni.a(8960, bzg.C, 768);
    bni.a(8960, bzg.D, 768);
    bni.a(8960, bzg.F, 8448);
    bni.a(8960, bzg.G, bzg.q);
    bni.a(8960, bzg.H, bzg.v);
    bni.a(8960, bzg.J, 770);
    bni.a(8960, bzg.K, 770);
    bni.g(bzg.r);
    bni.a(8960, 8704, bzg.t);
    bni.a(8960, bzg.y, 8448);
    bni.a(8960, bzg.C, 768);
    bni.a(8960, bzg.D, 768);
    bni.a(8960, bzg.z, 5890);
    bni.a(8960, bzg.A, bzg.x);
    bni.a(8960, bzg.F, 8448);
    bni.a(8960, bzg.J, 770);
    bni.a(8960, bzg.G, 5890);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.g(bzg.s);
    bni.z();
    bni.i(0);
    bni.a(8960, 8704, bzg.t);
    bni.a(8960, bzg.y, 8448);
    bni.a(8960, bzg.C, 768);
    bni.a(8960, bzg.D, 768);
    bni.a(8960, bzg.z, 5890);
    bni.a(8960, bzg.A, bzg.x);
    bni.a(8960, bzg.F, 8448);
    bni.a(8960, bzg.J, 770);
    bni.a(8960, bzg.G, 5890);
    bni.g(bzg.q);
  }
  
  protected void a(T entityLivingBaseIn, double x, double y, double z)
  {
    bni.c((float)x, (float)y, (float)z);
  }
  
  protected void a(T bat, float p_77043_2_, float p_77043_3_, float partialTicks)
  {
    bni.b(180.0F - p_77043_3_, 0.0F, 1.0F, 0.0F);
    if (bat.aA > 0)
    {
      float f = (bat.aA + partialTicks - 1.0F) / 20.0F * 1.6F;
      f = on.c(f);
      if (f > 1.0F) {
        f = 1.0F;
      }
      bni.b(f * b(bat), 0.0F, 0.0F, 1.0F);
    }
    else
    {
      String s = a.a(bat.h_());
      if ((s != null) && ((s.equals("Dinnerbone")) || (s.equals("Grumm"))) && ((!(bat instanceof zj)) || (((zj)bat).a(zk.a))))
      {
        bni.c(0.0F, bat.H + 0.1F, 0.0F);
        bni.b(180.0F, 0.0F, 0.0F, 1.0F);
      }
    }
  }
  
  protected float e(T livingBase, float partialTickTime)
  {
    return livingBase.m(partialTickTime);
  }
  
  protected float b(T livingBase, float partialTicks)
  {
    return livingBase.T + partialTicks;
  }
  
  protected void a(T entitylivingbaseIn, float p_177093_2_, float p_177093_3_, float partialTicks, float p_177093_5_, float p_177093_6_, float p_177093_7_, float p_177093_8_)
  {
    for (bty<T> layerrenderer : this.i)
    {
      boolean var12 = layerrenderer.a();
      if ((ConfigManager.settings.oldDMG.booleanValue()) && (Allowed.animations()) && 
        ((layerrenderer instanceof btu))) {
        var12 = true;
      }
      boolean flag = a(entitylivingbaseIn, partialTicks, var12);
      layerrenderer.a(entitylivingbaseIn, p_177093_2_, p_177093_3_, partialTicks, p_177093_5_, p_177093_6_, p_177093_7_, p_177093_8_);
      if (flag) {
        g();
      }
    }
  }
  
  protected float b(T entityLivingBaseIn)
  {
    return 90.0F;
  }
  
  protected int a(T entitylivingbaseIn, float lightBrightness, float partialTickTime)
  {
    return 0;
  }
  
  protected void a(T entitylivingbaseIn, float partialTickTime) {}
  
  public void b(T entity, double x, double y, double z)
  {
    GommeHDBed.renderPlayerTag(entity, x, y, z);
    if (ModAPI.enabled()) {
      ModAPI.callEvent(new RenderNametagEvent(entity, x, y, z));
    }
    if (a(entity))
    {
      double d0 = entity.h(this.c.c);
      float f1 = 0.02666667F;
      float f = entity.aK() ? 32.0F : 64.0F;
      if (d0 < f * f)
      {
        String s = entity.i_().d();
        bni.a(516, 0.1F);
        
        boolean nick = false;
        String b = s;
        s = FriendsLoader.getNick(s, Color.removeColor(entity.h_()));
        nick = !b.equals(s);
        if (nick)
        {
          String tag = entity.i_().c().replace(Color.removeColor(entity.h_()), "");
          String t = "";
          if ((tag.startsWith(Color.c)) && (tag.length() >= 2)) {
            t = tag.substring(0, 2);
          }
          s = Color.cl("e") + "✎ " + Color.cl("f") + t + s;
        }
        if (entity.aK())
        {
          bct fontrenderer = c();
          bni.G();
          bni.b((float)x, (float)y + entity.H + 0.5F - (entity.m_() ? entity.H / 2.0F : 0.0F) + 
            LabyMod.getInstance().getCosmeticManager().getNameTagHeight(entity), (float)z);
          GL11.glNormal3f(0.0F, 1.0F, 0.0F);
          bni.b(-this.c.e, 0.0F, 1.0F, 0.0F);
          bni.b(this.c.f, 1.0F, 0.0F, 0.0F);
          bni.b(-0.02666667F, -0.02666667F, 0.02666667F);
          bni.c(0.0F, 9.374999F, 0.0F);
          bni.g();
          bni.a(false);
          bni.m();
          bni.z();
          bni.a(770, 771, 1, 0);
          int i = fontrenderer.a(s) / 2;
          bnu tessellator = bnu.a();
          bmz worldrenderer = tessellator.c();
          worldrenderer.a(7, bvp.f);
          worldrenderer.b(-i - 1, -1.0D, 0.0D).a(0.0F, 0.0F, 0.0F, 0.25F).d();
          worldrenderer.b(-i - 1, 8.0D, 0.0D).a(0.0F, 0.0F, 0.0F, 0.25F).d();
          worldrenderer.b(i + 1, 8.0D, 0.0D).a(0.0F, 0.0F, 0.0F, 0.25F).d();
          worldrenderer.b(i + 1, -1.0D, 0.0D).a(0.0F, 0.0F, 0.0F, 0.25F).d();
          tessellator.b();
          bni.y();
          bni.a(true);
          fontrenderer.a(s, -fontrenderer.a(s) / 2, 0, 553648127);
          bni.f();
          bni.l();
          bni.c(1.0F, 1.0F, 1.0F, 1.0F);
          bni.H();
        }
        else if (nick)
        {
          bct fontrenderer = c();
          bni.G();
          bni.b((float)x, (float)y + entity.H + 0.5F - (entity.m_() ? entity.H / 2.0F : 0.0F) + 
            LabyMod.getInstance().getCosmeticManager().getNameTagHeight(entity), (float)z);
          GL11.glNormal3f(0.0F, 1.0F, 0.0F);
          bni.b(-this.c.e, 0.0F, 1.0F, 0.0F);
          bni.b(this.c.f, 1.0F, 0.0F, 0.0F);
          bni.b(-0.02666667F, -0.02666667F, 0.02666667F);
          bni.g();
          bni.a(false);
          bni.m();
          bni.z();
          bni.a(770, 771, 1, 0);
          int i = fontrenderer.a(s) / 2;
          bnu tessellator = bnu.a();
          bmz worldrenderer = tessellator.c();
          worldrenderer.a(7, bvp.f);
          worldrenderer.b(-i - 1, -1.0D, 0.0D).a(0.0F, 0.0F, 0.0F, 0.25F).d();
          worldrenderer.b(-i - 1, 8.0D, 0.0D).a(0.0F, 0.0F, 0.0F, 0.25F).d();
          worldrenderer.b(i + 1, 8.0D, 0.0D).a(0.0F, 0.0F, 0.0F, 0.25F).d();
          worldrenderer.b(i + 1, -1.0D, 0.0D).a(0.0F, 0.0F, 0.0F, 0.25F).d();
          tessellator.b();
          bni.y();
          bni.a(true);
          bni.k();
          fontrenderer.a(s, -fontrenderer.a(s) / 2, 0, -1);
          bni.f();
          bni.l();
          bni.c(1.0F, 1.0F, 1.0F, 1.0F);
          bni.H();
        }
        else
        {
          a(entity, x, y - (entity.m_() ? entity.H / 2.0F : 0.0D) + 
            LabyMod.getInstance().getCosmeticManager().getNameTagHeight(entity), z, s, d0);
        }
      }
    }
  }
  
  private void renderHolograms()
  {
    try
    {
      for (Hologram hologram : )
      {
        double x = hologram.getX();
        double y = hologram.getY();
        double z = hologram.getZ();
        if ((LabyMod.getInstance().isInGame()) && (this.c != null))
        {
          x -= this.c.h;
          y -= this.c.i;
          z -= this.c.j;
        }
        else
        {
          return;
        }
        bct fontrenderer = c();
        bni.G();
        bni.c((float)x, (float)y, (float)z);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        bni.b(-this.c.e, 0.0F, 1.0F, 0.0F);
        bni.b(this.c.f, 1.0F, 0.0F, 0.0F);
        bni.b(-0.32666668F, -0.32666668F, 0.32666668F);
        bni.g();
        bni.a(false);
        bni.m();
        bni.z();
        bni.a(770, 771, 1, 0);
        int i = fontrenderer.a(hologram.getText()) / 2;
        bnu tessellator = bnu.a();
        bmz worldrenderer = tessellator.c();
        worldrenderer.a(7, bvp.f);
        SetColor color = hologram.getColor();
        worldrenderer.b(-i - 1, -1.0D, 0.0D).a(color.getR(), color.getG(), color.getB(), color.getA()).d();
        worldrenderer.b(-i - 1, 8.0D, 0.0D).a(color.getR(), color.getG(), color.getB(), color.getA()).d();
        worldrenderer.b(i + 1, 8.0D, 0.0D).a(color.getR(), color.getG(), color.getB(), color.getA()).d();
        worldrenderer.b(i + 1, -1.0D, 0.0D).a(color.getR(), color.getG(), color.getB(), color.getA()).d();
        tessellator.b();
        bni.y();
        bni.a(true);
        bni.k();
        fontrenderer.a(hologram.getText(), -fontrenderer.a(hologram.getText()) / 2, 0, -1);
        bni.f();
        bni.l();
        bni.c(1.0F, 1.0F, 1.0F, 1.0F);
        bni.H();
      }
    }
    catch (Exception error)
    {
      error.printStackTrace();
    }
  }
  
  protected boolean a(T entity)
  {
    bmt entityplayersp = bcf.z().h;
    boolean flag = !entity.e(entityplayersp);
    if (entity != entityplayersp)
    {
      bbr team = entity.aO();
      bbr team1 = entityplayersp.aO();
      if (team != null)
      {
        bbr.b team$enumvisible = team.i();
        switch (team$enumvisible)
        {
        case a: 
          return flag;
        case b: 
          return false;
        case c: 
          return (team.a(team1)) && ((team.h()) || (flag)) ? true : team1 == null ? flag : false;
        case d: 
          return (!team.a(team1)) && (flag) ? true : team1 == null ? flag : false;
        }
        return true;
      }
    }
    boolean check = entity != this.c.c;
    if (ConfigManager.settings.showMyName) {
      check = true;
    }
    return (bcf.w()) && (check) && (flag) && (!entity.aJ());
  }
  
  static
  {
    int[] aint = b.e();
    for (int i = 0; i < 256; i++) {
      aint[i] = -1;
    }
    b.d();
  }
}
