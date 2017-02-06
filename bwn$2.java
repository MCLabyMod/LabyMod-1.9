import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.awt.image.BufferedImage;

class bwn$2
  implements bnj
{
  bwn$2(bwn parambwn, bnj parambnj, bwn.a parama, MinecraftProfileTexture.Type paramType, kk paramkk, MinecraftProfileTexture paramMinecraftProfileTexture) {}
  
  public BufferedImage a(BufferedImage ☃)
  {
    if (this.a != null) {
      ☃ = this.a.a(☃);
    }
    return ☃;
  }
  
  public void a()
  {
    if (this.a != null) {
      this.a.a();
    }
    if (this.b != null) {
      this.b.a(this.c, this.d, this.e);
    }
  }
}
