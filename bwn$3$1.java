import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.util.Map;

class bwn$3$1
  implements Runnable
{
  bwn$3$1(bwn.3 param3, Map paramMap) {}
  
  public void run()
  {
    if (this.a.containsKey(MinecraftProfileTexture.Type.SKIN)) {
      this.b.d.a((MinecraftProfileTexture)this.a.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN, this.b.c);
    }
    if (this.a.containsKey(MinecraftProfileTexture.Type.CAPE)) {
      this.b.d.a((MinecraftProfileTexture)this.a.get(MinecraftProfileTexture.Type.CAPE), MinecraftProfileTexture.Type.CAPE, this.b.c);
    }
  }
}
