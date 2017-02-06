import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.util.Map;
import java.util.UUID;

public class bpu
  extends bpn<aqo>
{
  private static final kk d = new kk("textures/entity/skeleton/skeleton.png");
  private static final kk e = new kk("textures/entity/skeleton/wither_skeleton.png");
  private static final kk f = new kk("textures/entity/zombie/zombie.png");
  private static final kk g = new kk("textures/entity/creeper/creeper.png");
  private static final kk h = new kk("textures/entity/enderdragon/dragon.png");
  private final bkg i = new bkg(0.0F);
  public static bpu a;
  private final bjq j = new bjq(0, 0, 64, 32);
  private final bjq k = new biw();
  
  public void a(aqo ☃, double ☃, double ☃, double ☃, float ☃, int ☃)
  {
    cq ☃ = cq.a(☃.u() & 0x7);
    
    float ☃ = ☃.a(☃);
    a((float)☃, (float)☃, (float)☃, ☃, ☃.e() * 360 / 16.0F, ☃.d(), ☃.b(), ☃, ☃);
  }
  
  public void a(bpm ☃)
  {
    super.a(☃);
    a = this;
  }
  
  public void a(float ☃, float ☃, float ☃, cq ☃, float ☃, int ☃, GameProfile ☃, int ☃, float ☃)
  {
    bjc ☃ = this.j;
    if (☃ >= 0)
    {
      a(b[☃]);
      bni.n(5890);
      bni.G();
      bni.b(4.0F, 2.0F, 1.0F);
      bni.c(0.0625F, 0.0625F, 0.0625F);
      bni.n(5888);
    }
    else
    {
      switch (☃)
      {
      case 0: 
      default: 
        a(d);
        break;
      case 1: 
        a(e);
        break;
      case 2: 
        a(f);
        ☃ = this.k;
        break;
      case 3: 
        ☃ = this.k;
        kk ☃ = bvw.a();
        if (☃ != null)
        {
          bcf ☃ = bcf.z();
          Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> ☃ = ☃.Y().a(☃);
          if (☃.containsKey(MinecraftProfileTexture.Type.SKIN))
          {
            ☃ = ☃.Y().a((MinecraftProfileTexture)☃.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
          }
          else
          {
            UUID ☃ = zj.a(☃);
            ☃ = bvw.a(☃);
          }
        }
        a(☃);
        break;
      case 4: 
        a(g);
        break;
      case 5: 
        a(h);
        ☃ = this.i;
      }
    }
    bni.G();
    bni.r();
    if (☃ != cq.b) {
      switch (bpu.1.a[☃.ordinal()])
      {
      case 1: 
        bni.c(☃ + 0.5F, ☃ + 0.25F, ☃ + 0.74F);
        break;
      case 2: 
        bni.c(☃ + 0.5F, ☃ + 0.25F, ☃ + 0.26F);
        ☃ = 180.0F;
        break;
      case 3: 
        bni.c(☃ + 0.74F, ☃ + 0.25F, ☃ + 0.5F);
        ☃ = 270.0F;
        break;
      case 4: 
      default: 
        bni.c(☃ + 0.26F, ☃ + 0.25F, ☃ + 0.5F);
        ☃ = 90.0F;
        break;
      }
    } else {
      bni.c(☃ + 0.5F, ☃, ☃ + 0.5F);
    }
    float ☃ = 0.0625F;
    bni.D();
    bni.b(-1.0F, -1.0F, 1.0F);
    
    bni.e();
    if (☃ == 3) {
      bni.a(bni.q.b);
    }
    ☃.a(null, ☃, 0.0F, 0.0F, ☃, 0.0F, ☃);
    if (☃ == 3) {
      bni.b(bni.q.b);
    }
    bni.H();
    if (☃ >= 0)
    {
      bni.n(5890);
      bni.H();
      bni.n(5888);
    }
  }
}
