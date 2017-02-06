import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.InsecureTextureException;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.PropertyMap;
import java.util.Map;
import java.util.UUID;

class bwn$3
  implements Runnable
{
  bwn$3(bwn parambwn, GameProfile paramGameProfile, boolean paramBoolean, bwn.a parama) {}
  
  public void run()
  {
    final Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> ☃ = Maps.newHashMap();
    try
    {
      ☃.putAll(bwn.a(this.d).getTextures(this.a, this.b));
    }
    catch (InsecureTextureException localInsecureTextureException) {}
    if ((☃.isEmpty()) && (this.a.getId().equals(bcf.z().K().e().getId())))
    {
      this.a.getProperties().clear();
      this.a.getProperties().putAll(bcf.z().L());
      ☃.putAll(bwn.a(this.d).getTextures(this.a, false));
    }
    bcf.z().a(new Runnable()
    {
      public void run()
      {
        if (☃.containsKey(MinecraftProfileTexture.Type.SKIN)) {
          bwn.3.this.d.a((MinecraftProfileTexture)☃.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN, bwn.3.this.c);
        }
        if (☃.containsKey(MinecraftProfileTexture.Type.CAPE)) {
          bwn.3.this.d.a((MinecraftProfileTexture)☃.get(MinecraftProfileTexture.Type.CAPE), MinecraftProfileTexture.Type.CAPE, bwn.3.this.c);
        }
      }
    });
  }
}
