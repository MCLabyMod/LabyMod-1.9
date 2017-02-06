import com.google.common.cache.CacheLoader;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import java.util.Map;

class bwn$1
  extends CacheLoader<GameProfile, Map<MinecraftProfileTexture.Type, MinecraftProfileTexture>>
{
  bwn$1(bwn parambwn) {}
  
  public Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> a(GameProfile ☃)
    throws Exception
  {
    return bcf.z().X().getTextures(☃, false);
  }
}
