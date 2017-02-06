import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;

public class aqo
  extends apv
  implements ky
{
  private int a;
  private int f;
  private GameProfile g = null;
  private int h;
  private boolean i;
  private static mi j;
  private static MinecraftSessionService k;
  
  public static void a(mi ☃)
  {
    j = ☃;
  }
  
  public static void a(MinecraftSessionService ☃)
  {
    k = ☃;
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("SkullType", (byte)(this.a & 0xFF));
    ☃.a("Rot", (byte)(this.f & 0xFF));
    if (this.g != null)
    {
      dn ☃ = new dn();
      dy.a(☃, this.g);
      ☃.a("Owner", ☃);
    }
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    this.a = ☃.f("SkullType");
    this.f = ☃.f("Rot");
    if (this.a == 3) {
      if (☃.b("Owner", 10))
      {
        this.g = dy.a(☃.o("Owner"));
      }
      else if (☃.b("ExtraType", 8))
      {
        String ☃ = ☃.l("ExtraType");
        if (!os.b(☃))
        {
          this.g = new GameProfile(null, ☃);
          g();
        }
      }
    }
  }
  
  public void c()
  {
    if (this.a == 5) {
      if (this.b.y(this.c))
      {
        this.i = true;
        this.h += 1;
      }
      else
      {
        this.i = false;
      }
    }
  }
  
  public float a(float ☃)
  {
    if (this.i) {
      return this.h + ☃;
    }
    return this.h;
  }
  
  public GameProfile b()
  {
    return this.g;
  }
  
  public ff<?> D_()
  {
    dn ☃ = new dn();
    b(☃);
    return new fs(this.c, 4, ☃);
  }
  
  public void a(int ☃)
  {
    this.a = ☃;
    this.g = null;
  }
  
  public void a(GameProfile ☃)
  {
    this.a = 3;
    this.g = ☃;
    g();
  }
  
  private void g()
  {
    this.g = b(this.g);
    v_();
  }
  
  public static GameProfile b(GameProfile ☃)
  {
    if ((☃ == null) || (os.b(☃.getName()))) {
      return ☃;
    }
    if ((☃.isComplete()) && (☃.getProperties().containsKey("textures"))) {
      return ☃;
    }
    if ((j == null) || (k == null)) {
      return ☃;
    }
    GameProfile ☃ = j.a(☃.getName());
    if (☃ == null) {
      return ☃;
    }
    Property ☃ = (Property)Iterables.getFirst(☃.getProperties().get("textures"), null);
    if (☃ == null) {
      ☃ = k.fillProfileProperties(☃, true);
    }
    return ☃;
  }
  
  public int d()
  {
    return this.a;
  }
  
  public int e()
  {
    return this.f;
  }
  
  public void b(int ☃)
  {
    this.f = ☃;
  }
}
