import java.util.Random;
import mods.itemphysic.ClientPhysic;
import org.lwjgl.util.vector.Vector3f;

public class brx
  extends brn<yd>
{
  private final brz a;
  private Random b = new Random();
  
  public brx(brm renderManagerIn, brz p_i46167_2_)
  {
    super(renderManagerIn);
    this.a = p_i46167_2_;
    this.d = 0.15F;
    this.e = 0.75F;
  }
  
  private int a(yd itemIn, double p_177077_2_, double p_177077_4_, double p_177077_6_, float p_177077_8_, bxo p_177077_9_)
  {
    adq itemstack = itemIn.k();
    ado item = itemstack.b();
    if (item == null) {
      return 0;
    }
    boolean flag = p_177077_9_.b();
    int i = a(itemstack);
    float f = 0.25F;
    float f1 = on.a((itemIn.o() + p_177077_8_) / 10.0F + itemIn.a) * 0.1F + 0.1F;
    float f2 = p_177077_9_.e().b(bos.b.h).d.y;
    bni.c((float)p_177077_2_, (float)p_177077_4_ + f1 + 0.25F * f2, (float)p_177077_6_);
    if ((flag) || (this.c.g != null))
    {
      float f3 = ((itemIn.o() + p_177077_8_) / 20.0F + itemIn.a) * 57.295776F;
      bni.b(f3, 0.0F, 1.0F, 0.0F);
    }
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    return i;
  }
  
  private int a(adq stack)
  {
    int i = 1;
    if (stack.b > 48) {
      i = 5;
    } else if (stack.b > 32) {
      i = 4;
    } else if (stack.b > 16) {
      i = 3;
    } else if (stack.b > 1) {
      i = 2;
    }
    return i;
  }
  
  public void a(yd entity, double x, double y, double z, float entityYaw, float partialTicks)
  {
    ClientPhysic.doRender(entity, x, y, z, entityYaw, partialTicks);
    
    super.a(entity, x, y, z, entityYaw, partialTicks);
  }
  
  protected kk a(yd entity)
  {
    return bvg.g;
  }
}
