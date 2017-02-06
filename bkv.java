import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.util.Map;

public class bkv
{
  private final GameProfile b;
  Map<MinecraftProfileTexture.Type, kk> a = Maps.newEnumMap(MinecraftProfileTexture.Type.class);
  private ahw.a c;
  private int d;
  private boolean e;
  private String f;
  private eu g;
  private int h;
  private int i;
  private long j;
  private long k;
  private long l;
  
  public bkv(GameProfile ☃)
  {
    this.b = ☃;
  }
  
  public bkv(gz.b ☃)
  {
    this.b = ☃.a();
    this.c = ☃.c();
    this.d = ☃.b();
    this.g = ☃.d();
  }
  
  public GameProfile a()
  {
    return this.b;
  }
  
  public ahw.a b()
  {
    return this.c;
  }
  
  protected void a(ahw.a ☃)
  {
    this.c = ☃;
  }
  
  public int c()
  {
    return this.d;
  }
  
  protected void a(int ☃)
  {
    this.d = ☃;
  }
  
  public boolean e()
  {
    return g() != null;
  }
  
  public String f()
  {
    if (this.f == null) {
      return bvw.b(this.b.getId());
    }
    return this.f;
  }
  
  public kk g()
  {
    k();
    
    return (kk)Objects.firstNonNull(this.a.get(MinecraftProfileTexture.Type.SKIN), bvw.a(this.b.getId()));
  }
  
  public kk h()
  {
    k();
    
    return (kk)this.a.get(MinecraftProfileTexture.Type.CAPE);
  }
  
  public kk i()
  {
    k();
    
    return (kk)this.a.get(MinecraftProfileTexture.Type.ELYTRA);
  }
  
  public bbm j()
  {
    return bcf.z().f.ad().g(a().getName());
  }
  
  protected void k()
  {
    synchronized (this)
    {
      if (!this.e)
      {
        this.e = true;
        bcf.z().Y().a(this.b, new bwn.a()
        {
          public void a(MinecraftProfileTexture.Type ☃, kk ☃, MinecraftProfileTexture ☃)
          {
            switch (bkv.2.a[☃.ordinal()])
            {
            case 1: 
              bkv.this.a.put(MinecraftProfileTexture.Type.SKIN, ☃);
              bkv.a(bkv.this, ☃.getMetadata("model"));
              if (bkv.a(bkv.this) == null) {
                bkv.a(bkv.this, "default");
              }
              break;
            case 2: 
              bkv.this.a.put(MinecraftProfileTexture.Type.CAPE, ☃);
              break;
            case 3: 
              bkv.this.a.put(MinecraftProfileTexture.Type.ELYTRA, ☃);
            }
          }
        }, true);
      }
    }
  }
  
  public void a(eu ☃)
  {
    this.g = ☃;
  }
  
  public eu l()
  {
    return this.g;
  }
  
  public int m()
  {
    return this.h;
  }
  
  public void b(int ☃)
  {
    this.h = ☃;
  }
  
  public int n()
  {
    return this.i;
  }
  
  public void c(int ☃)
  {
    this.i = ☃;
  }
  
  public long o()
  {
    return this.j;
  }
  
  public void a(long ☃)
  {
    this.j = ☃;
  }
  
  public long p()
  {
    return this.k;
  }
  
  public void b(long ☃)
  {
    this.k = ☃;
  }
  
  public long q()
  {
    return this.l;
  }
  
  public void c(long ☃)
  {
    this.l = ☃;
  }
}
