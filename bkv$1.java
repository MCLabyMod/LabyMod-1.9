import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.util.Map;

class bkv$1
  implements bwn.a
{
  bkv$1(bkv parambkv) {}
  
  public void a(MinecraftProfileTexture.Type ☃, kk ☃, MinecraftProfileTexture ☃)
  {
    switch (bkv.2.a[☃.ordinal()])
    {
    case 1: 
      this.a.a.put(MinecraftProfileTexture.Type.SKIN, ☃);
      bkv.a(this.a, ☃.getMetadata("model"));
      if (bkv.a(this.a) == null) {
        bkv.a(this.a, "default");
      }
      break;
    case 2: 
      this.a.a.put(MinecraftProfileTexture.Type.CAPE, ☃);
      break;
    case 3: 
      this.a.a.put(MinecraftProfileTexture.Type.ELYTRA, ☃);
    }
  }
}
