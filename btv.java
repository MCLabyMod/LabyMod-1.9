import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.ModSettings;
import de.labystudio.utils.Allowed;
import de.labystudio.utils.DualHand;

public class btv
  implements bty<sa>
{
  protected final bsd<?> a;
  
  public btv(bsd<?> livingEntityRendererIn)
  {
    this.a = livingEntityRendererIn;
  }
  
  public void a(sa entitylivingbaseIn, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale)
  {
    boolean flag = entitylivingbaseIn.cr() == rz.b;
    adq itemstack = flag ? entitylivingbaseIn.cc() : entitylivingbaseIn.cb();
    adq itemstack1 = flag ? entitylivingbaseIn.cb() : entitylivingbaseIn.cc();
    if ((itemstack != null) || (itemstack1 != null))
    {
      bni.G();
      if (this.a.b().q)
      {
        float f = 0.5F;
        bni.c(0.0F, 0.625F, 0.0F);
        bni.b(-20.0F, -1.0F, 0.0F, 0.0F);
        bni.b(f, f, f);
      }
      a(entitylivingbaseIn, itemstack1, bos.b.c, rz.b);
      a(entitylivingbaseIn, itemstack, bos.b.b, rz.a);
      bni.H();
    }
  }
  
  private void a(sa p_188358_1_, adq p_188358_2_, bos.b p_188358_3_, rz p_188358_4_)
  {
    if (p_188358_2_ != null)
    {
      bni.G();
      if ((ConfigManager.settings.animationSword == 0) && (Allowed.animations()) && (Allowed.blocking()))
      {
        boolean ok = false;
        ado a;
        ado a;
        if (p_188358_4_ == rz.b) {
          a = DualHand.getItemInLeftHand();
        } else {
          a = DualHand.getItemInRightHand();
        }
        if ((a == null) || (a.e(p_188358_2_) == 0)) {
          ok = true;
        }
        if ((ok) && ((p_188358_2_.b() instanceof aex)) && (((zj)p_188358_1_).isBlocking(p_188358_4_)))
        {
          if (p_188358_1_.aK())
          {
            ((bix)this.a.b()).a(0.0325F, p_188358_4_);
            if (p_188358_4_ == rz.b)
            {
              bni.c(-0.58F, 0.3F, -0.2F);
              bni.b(-24390.0F, 137290.0F, -2009900.0F, -2054900.0F);
            }
            else
            {
              bni.c(0.58F, 0.3F, -0.2F);
              bni.b(24390.0F, 137290.0F, -2009900.0F, -2054900.0F);
            }
          }
          else
          {
            ((bix)this.a.b()).a(0.0325F, p_188358_4_);
            if (p_188358_4_ == rz.b)
            {
              bni.c(-0.48F, 0.2F, -0.2F);
              bni.b(-24390.0F, 137290.0F, -2009900.0F, -2054900.0F);
            }
            else
            {
              bni.c(0.48F, 0.2F, -0.2F);
              bni.b(24390.0F, 137290.0F, -2009900.0F, -2054900.0F);
            }
          }
        }
        else {
          ((bix)this.a.b()).a(0.0625F, p_188358_4_);
        }
      }
      else
      {
        ((bix)this.a.b()).a(0.0625F, p_188358_4_);
      }
      if (p_188358_1_.aK()) {
        bni.c(0.0F, 0.2F, 0.0F);
      }
      bni.b(-90.0F, 1.0F, 0.0F, 0.0F);
      bni.b(180.0F, 0.0F, 1.0F, 0.0F);
      boolean flag = p_188358_4_ == rz.a;
      bni.c(flag ? -0.0625F : 0.0625F, 0.125F, -0.625F);
      bcf.z().ae().a(p_188358_1_, p_188358_2_, p_188358_3_, flag);
      bni.H();
    }
  }
  
  public boolean a()
  {
    return false;
  }
}
