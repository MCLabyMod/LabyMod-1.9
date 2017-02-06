package mods.itemphysic;

import ado;
import adq;
import aht;
import ajt;
import arc;
import axe;
import bcd;
import bcf;
import bch;
import bni;
import bnl;
import brm;
import brz;
import bvg;
import bvi;
import bvj;
import bxo;
import cj;
import java.util.Random;
import kk;
import org.lwjgl.opengl.GL11;
import rr;
import yd;

public class ClientPhysic
{
  public static Random random = new Random();
  public static bcf mc = bcf.z();
  public static brz renderItem = mc.ad();
  public static long tick;
  public static double rotation;
  public static final kk RES_ITEM_GLINT = new kk("textures/misc/enchanted_item_glint.png");
  
  public static void doRender(rr par1Entity, double x, double y, double z, float par8, float par9)
  {
    rotation = (System.nanoTime() - tick) / 3000000.0D * ItemPhysic.rotationSpeed;
    if (!mc.x) {
      rotation = 0.0D;
    }
    yd item = (yd)par1Entity;
    adq itemstack = item.k();
    if (itemstack.b() != null)
    {
      random.setSeed(187L);
      boolean flag = false;
      if (bvg.g != null)
      {
        mc.ac().a.a(bvg.g);
        mc.ac().a.b(bvg.g)
          .b(false, false);
        
        flag = true;
      }
      bni.D();
      bni.a(516, 0.1F);
      bni.m();
      bcd.b();
      bni.a(770, 771, 1, 0);
      bni.G();
      
      bxo ibakedmodel = renderItem.a().a(itemstack);
      int i = func_177077_a(item, x, y, z, par9, ibakedmodel);
      
      cj pos = new cj(item);
      if (item.w > 360.0F) {
        item.w = 0.0F;
      }
      if ((item != null) && (!Double.isNaN(item.p)) && 
        (!Double.isNaN(item.q)) && 
        (!Double.isNaN(item.r)) && (item.l != null)) {
        if (item.z)
        {
          if ((item.w != 0.0F) && (item.w != 90.0F) && (item.w != 180.0F) && (item.w != 270.0F))
          {
            double Abstand0 = formPositiv(item.w);
            double Abstand90 = formPositiv(item.w - 90.0F);
            double Abstand180 = formPositiv(item.w - 180.0F);
            double Abstand270 = formPositiv(item.w - 270.0F);
            if ((Abstand0 <= Abstand90) && (Abstand0 <= Abstand180) && (Abstand0 <= Abstand270)) {
              if (item.w < 0.0F)
              {
                yd tmp389_387 = item;
                tmp389_387.w = ((float)(tmp389_387.w + rotation));
              }
              else
              {
                yd tmp407_405 = item;
                tmp407_405.w = ((float)(tmp407_405.w - rotation));
              }
            }
            if ((Abstand90 < Abstand0) && (Abstand90 <= Abstand180) && (Abstand90 <= Abstand270)) {
              if (item.w - 90.0F < 0.0F)
              {
                yd tmp459_457 = item;
                tmp459_457.w = ((float)(tmp459_457.w + rotation));
              }
              else
              {
                yd tmp477_475 = item;
                tmp477_475.w = ((float)(tmp477_475.w - rotation));
              }
            }
            if ((Abstand180 < Abstand90) && (Abstand180 < Abstand0) && (Abstand180 <= Abstand270)) {
              if (item.w - 180.0F < 0.0F)
              {
                yd tmp529_527 = item;
                tmp529_527.w = ((float)(tmp529_527.w + rotation));
              }
              else
              {
                yd tmp547_545 = item;
                tmp547_545.w = ((float)(tmp547_545.w - rotation));
              }
            }
            if ((Abstand270 < Abstand90) && (Abstand270 < Abstand180) && (Abstand270 < Abstand0)) {
              if (item.w - 270.0F < 0.0F)
              {
                yd tmp599_597 = item;
                tmp599_597.w = ((float)(tmp599_597.w + rotation));
              }
              else
              {
                yd tmp617_615 = item;
                tmp617_615.w = ((float)(tmp617_615.w - rotation));
              }
            }
          }
        }
        else
        {
          cj posUp = new cj(item);
          posUp.a(0, 1, 0);
          arc s1 = item.l.o(posUp);
          arc s2 = item.l.o(pos);
          axe m1 = s1.t().q(s1);
          axe m2 = s2.t().q(s2);
          
          boolean m3 = item.a(axe.h);
          boolean m4 = item.ai();
          if ((m3 | m1 == axe.h | m2 == axe.h | m4))
          {
            yd tmp748_746 = item;
            tmp748_746.w = ((float)(tmp748_746.w + rotation / 4.0D));
          }
          else
          {
            yd tmp770_768 = item;
            tmp770_768.w = ((float)(tmp770_768.w + rotation * 2.0D));
          }
        }
      }
      GL11.glRotatef(item.v, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(item.w + 90.0F, 1.0F, 0.0F, 0.0F);
      for (int j = 0; j < i; j++) {
        if (ibakedmodel.a())
        {
          bni.G();
          
          bni.b(0.5F, 0.5F, 0.5F);
          renderItem.a(itemstack, ibakedmodel);
          bni.H();
        }
        else
        {
          bni.G();
          if ((j > 0) && (shouldSpreadItems())) {
            bni.c(0.0F, 0.0F, 0.046875F * j);
          }
          bni.b(0.6F, 0.6F, 0.6F);
          renderItem.a(itemstack, ibakedmodel);
          if (!shouldSpreadItems()) {
            bni.c(0.0F, 0.0F, 0.046875F);
          }
          bni.H();
        }
      }
      bni.H();
      bni.E();
      bni.l();
      mc.ac().a
        .a(bvg.g);
      if (flag) {
        mc.ac().a.b(bvg.g).a();
      }
    }
  }
  
  public static int func_177077_a(yd item, double x, double y, double z, float p_177077_8_, bxo p_177077_9_)
  {
    adq itemstack = item.k();
    ado item2 = itemstack.b();
    if (item2 == null) {
      return 0;
    }
    boolean flag = p_177077_9_.a();
    int i = func_177078_a(itemstack);
    float f1 = 0.25F;
    float f2 = 0.0F;
    bni.c((float)x, (float)y + f2 + 0.25F, (float)z);
    float f3 = 0.0F;
    if ((flag) || (
      (mc.ac().g != null) && 
      (mc.ac().g.i))) {
      bni.b(f3, 0.0F, 1.0F, 0.0F);
    }
    if (!flag)
    {
      f3 = -0.0F * (i - 1) * 0.5F;
      float f4 = -0.0F * (i - 1) * 0.5F;
      float f5 = -0.046875F * (i - 1) * 0.5F;
      bni.c(f3, f4, f5);
    }
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    return i;
  }
  
  public static boolean shouldSpreadItems()
  {
    return true;
  }
  
  public static double formPositiv(float rotationPitch)
  {
    return Math.abs(rotationPitch);
  }
  
  public static int func_177078_a(adq stack)
  {
    byte b0 = 1;
    if (stack.b <= 1) {
      return b0;
    }
    if (stack.b > 48) {
      return 5;
    }
    if (stack.b > 32) {
      return 4;
    }
    if (stack.b > 16) {
      return 3;
    }
    if (stack.b > 1) {
      return 2;
    }
    return b0;
  }
  
  public static byte getMiniBlockCount(adq stack, byte original)
  {
    return original;
  }
  
  public static byte getMiniItemCount(adq stack, byte original)
  {
    return original;
  }
}
