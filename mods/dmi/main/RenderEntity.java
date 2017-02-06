package mods.dmi.main;

import bbl;
import bbp;
import bcf;
import bct;
import bmq;
import bmz;
import bni;
import bnu;
import brm;
import bvp;
import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import org.lwjgl.opengl.GL11;
import sa;
import xq;

public class RenderEntity
{
  public static void renderNameTag(sa entity, double x, double y, double z)
  {
    if ((!Settings.settings.enabled) || (!DamageIndicator.allowed(LabyMod.getInstance().ip))) {
      return;
    }
    int scale = Settings.settings.scale;
    if ((entity instanceof bmq))
    {
      bmq c = (bmq)entity;
      if (c.aN()) {
        return;
      }
    }
    if (((entity instanceof xq)) || (entity.aK())) {
      return;
    }
    brm renderManager = bcf.z().ac();
    bct var13 = renderManager.c();
    bni.G();
    bni.c((float)x, (float)y + entity.H + 0.5F - (entity.m_() ? entity.H / 2.0F : 0.0F), (float)z);
    GL11.glNormal3f(0.0F, 1.0F, 0.0F);
    bni.b(-renderManager.e, 0.0F, 1.0F, 0.0F);
    bni.b(renderManager.f, 1.0F, 0.0F, 0.0F);
    bni.b(-0.02666667F, -0.02666667F, 0.02666667F);
    bni.g();
    bni.a(false);
    bni.j();
    bni.m();
    bni.a(770, 771, 1, 0);
    int hj = -10;
    if ((entity.H < 100.0D) && ((entity instanceof bmq)))
    {
      bbp var128 = ((bmq)entity).cW();
      bbl var138 = var128.a(2);
      if (var138 != null) {
        hj = (int)(hj - (25.0D - LabyMod.getInstance().draw.getScale(scale) * 10.0D));
      }
    }
    bni.z();
    bnu tessellator = bnu.a();
    bmz worldrenderer = tessellator.c();
    worldrenderer.a(7, bvp.f);
    String var144 = "";
    int g = 0;
    double health = entity.bQ();
    if (!Settings.settings.DMILayout)
    {
      var144 = Math.ceil(health) / 2.0D + Color.cl("c") + " ❤";
      g = 16;
    }
    else
    {
      String r = Color.cl("4");
      String d = Color.cl("7");
      String h = Color.cl("c");
      int max = (int)(entity.bW() / 2.0F);
      if (health == 0.0D)
      {
        for (int i = 0; i < max; i++) {
          var144 = var144 + d + "❤";
        }
      }
      else
      {
        for (int i = 1; i < (int)Math.ceil(health / 2.0D); i++) {
          var144 = var144 + r + "❤";
        }
        if (health % 2.0D != 0.0D) {
          var144 = var144 + h + "❤";
        } else {
          var144 = var144 + r + "❤";
        }
        for (int i = (int)Math.ceil(health / 2.0D); i < max; i++) {
          var144 = var144 + d + "❤";
        }
        g = 40;
      }
    }
    double k = LabyMod.getInstance().draw.getScale(scale);
    GL11.glScaled(k, k, k);
    int i = LabyMod.getInstance().draw.getStringWidth(var144) / 2;
    worldrenderer.b(-i - 1, hj, 0.0D).a(0.0F, 0.0F, 0.0F, 0.25F).d();
    worldrenderer.b(-i - 1, hj + 8, 0.0D).a(0.0F, 0.0F, 0.0F, 0.25F).d();
    worldrenderer.b(i + 1, hj + 8, 0.0D).a(0.0F, 0.0F, 0.0F, 0.25F).d();
    worldrenderer.b(i + 1, hj, 0.0D).a(0.0F, 0.0F, 0.0F, 0.25F).d();
    tessellator.b();
    bni.y();
    bni.a(true);
    bni.k();
    var13.a(var144, -LabyMod.getInstance().draw.getStringWidth(var144) / 2 + 2, hj, -1);
    bni.f();
    bni.l();
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.H();
  }
}
