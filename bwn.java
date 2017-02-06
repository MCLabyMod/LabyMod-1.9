import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.InsecureTextureException;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.PropertyMap;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class bwn
{
  private static final ExecutorService a = new ThreadPoolExecutor(0, 2, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue());
  private final bvi b;
  private final File c;
  private final MinecraftSessionService d;
  private final LoadingCache<GameProfile, Map<MinecraftProfileTexture.Type, MinecraftProfileTexture>> e;
  
  public bwn(bvi ☃, File ☃, MinecraftSessionService ☃)
  {
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
    
    this.e = CacheBuilder.newBuilder().expireAfterAccess(15L, TimeUnit.SECONDS).build(new CacheLoader()
    {
      public Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> a(GameProfile ☃)
        throws Exception
      {
        return bcf.z().X().getTextures(☃, false);
      }
    });
  }
  
  public kk a(MinecraftProfileTexture ☃, MinecraftProfileTexture.Type ☃)
  {
    return a(☃, ☃, null);
  }
  
  public kk a(final MinecraftProfileTexture ☃, final MinecraftProfileTexture.Type ☃, final bwn.a ☃)
  {
    final kk ☃ = new kk("skins/" + ☃.getHash());
    bvj ☃ = this.b.b(☃);
    if (☃ != null)
    {
      if (☃ != null) {
        ☃.a(☃, ☃, ☃);
      }
    }
    else
    {
      File ☃ = new File(this.c, ☃.getHash().length() > 2 ? ☃.getHash().substring(0, 2) : "xx");
      File ☃ = new File(☃, ☃.getHash());
      final bnj ☃ = ☃ == MinecraftProfileTexture.Type.SKIN ? new bnp() : null;
      buy ☃ = new buy(☃, ☃.getUrl(), bvw.a(), new bnj()
      {
        public BufferedImage a(BufferedImage ☃)
        {
          if (☃ != null) {
            ☃ = ☃.a(☃);
          }
          return ☃;
        }
        
        public void a()
        {
          if (☃ != null) {
            ☃.a();
          }
          if (☃ != null) {
            ☃.a(☃, ☃, ☃);
          }
        }
      });
      this.b.a(☃, ☃);
    }
    return ☃;
  }
  
  public void a(final GameProfile ☃, final bwn.a ☃, final boolean ☃)
  {
    a.submit(new Runnable()
    {
      public void run()
      {
        final Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> ☃ = Maps.newHashMap();
        try
        {
          ☃.putAll(bwn.a(bwn.this).getTextures(☃, ☃));
        }
        catch (InsecureTextureException localInsecureTextureException) {}
        if ((☃.isEmpty()) && (☃.getId().equals(bcf.z().K().e().getId())))
        {
          ☃.getProperties().clear();
          ☃.getProperties().putAll(bcf.z().L());
          ☃.putAll(bwn.a(bwn.this).getTextures(☃, false));
        }
        bcf.z().a(new Runnable()
        {
          public void run()
          {
            if (☃.containsKey(MinecraftProfileTexture.Type.SKIN)) {
              bwn.this.a((MinecraftProfileTexture)☃.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN, bwn.3.this.c);
            }
            if (☃.containsKey(MinecraftProfileTexture.Type.CAPE)) {
              bwn.this.a((MinecraftProfileTexture)☃.get(MinecraftProfileTexture.Type.CAPE), MinecraftProfileTexture.Type.CAPE, bwn.3.this.c);
            }
          }
        });
      }
    });
  }
  
  public Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> a(GameProfile ☃)
  {
    return (Map)this.e.getUnchecked(☃);
  }
  
  public static abstract interface a
  {
    public abstract void a(MinecraftProfileTexture.Type paramType, kk paramkk, MinecraftProfileTexture paramMinecraftProfileTexture);
  }
}
