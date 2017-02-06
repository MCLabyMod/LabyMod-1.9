import com.mojang.authlib.GameProfile;
import de.labystudio.capes.CapeManager;
import de.labystudio.capes.EnumCapePriority;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.utils.Allowed;
import java.io.File;
import java.io.PrintStream;

public abstract class bmq
  extends zj
{
  private bkv d;
  public float a;
  public float b;
  public float c;
  private String nameClear = null;
  private kk locationCape;
  public EnumCapePriority capeType = null;
  
  public bmq(aht worldIn, GameProfile playerProfile)
  {
    super(worldIn, playerProfile);
    
    this.locationCape = null;
    this.nameClear = playerProfile.getName();
    if ((this.nameClear != null) && (!this.nameClear.isEmpty())) {
      this.nameClear = os.a(this.nameClear);
    }
    LabyMod.getInstance().getCapeManager().downloadCape(this, false, false);
  }
  
  public boolean y()
  {
    bkv networkplayerinfo = bcf.z().v().a(cK().getId());
    return (networkplayerinfo != null) && (networkplayerinfo.b() == ahw.a.e);
  }
  
  public boolean l_()
  {
    bkv networkplayerinfo = bcf.z().v().a(cK().getId());
    return (networkplayerinfo != null) && (networkplayerinfo.b() == ahw.a.c);
  }
  
  public boolean a()
  {
    return b() != null;
  }
  
  protected bkv b()
  {
    if (this.d == null) {
      this.d = bcf.z().v().a(bc());
    }
    return this.d;
  }
  
  public boolean g()
  {
    bkv networkplayerinfo = b();
    return (networkplayerinfo != null) && (networkplayerinfo.e());
  }
  
  public kk o()
  {
    bkv networkplayerinfo = b();
    return networkplayerinfo == null ? bvw.a(bc()) : networkplayerinfo.g();
  }
  
  public kk r()
  {
    bkv networkplayerinfo = b();
    kk original = networkplayerinfo == null ? null : networkplayerinfo.h();
    boolean originalAvailable = (LabyMod.getInstance().getCapeManager().getCapePriority() == EnumCapePriority.ORIGINAL) && (original != null);
    if ((!originalAvailable) && (ConfigManager.settings.capes.booleanValue()) && (this.locationCape != null)) {
      return this.locationCape;
    }
    return original;
  }
  
  public boolean s()
  {
    return b() != null;
  }
  
  public kk t()
  {
    bkv networkplayerinfo = b();
    return networkplayerinfo == null ? null : networkplayerinfo.i();
  }
  
  public static buy a(kk resourceLocationIn, String username)
  {
    bvi texturemanager = bcf.z().N();
    bvj itextureobject = texturemanager.b(resourceLocationIn);
    if (itextureobject == null)
    {
      itextureobject = new buy((File)null, String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", new Object[] { os.a(username) }), bvw.a(d(username)), new bnp());
      texturemanager.a(resourceLocationIn, itextureobject);
    }
    return (buy)itextureobject;
  }
  
  public static kk e(String username)
  {
    return new kk("skins/" + os.a(username));
  }
  
  public String u()
  {
    bkv networkplayerinfo = b();
    return networkplayerinfo == null ? bvw.b(bc()) : networkplayerinfo.f();
  }
  
  public float x()
  {
    float f = 1.0F;
    if (this.bJ.b) {
      f *= 1.1F;
    }
    sm iattributeinstance = a(yt.d);
    if ((ConfigManager.settings.speedFOV) && (Allowed.unfairExtra()))
    {
      f = (float)(f * ((iattributeinstance.e() / this.bJ.b() + 1.0D) / 2.0D));
    }
    else
    {
      double speed = 0.10000000149011612D;
      if (aL()) {
        speed = 0.13000000312924387D;
      }
      f = (float)(f * ((speed / this.bJ.b() + 1.0D) / 2.0D));
    }
    if ((this.bJ.b() == 0.0F) || (Float.isNaN(f)) || (Float.isInfinite(f))) {
      f = 1.0F;
    }
    if ((cs()) && (cv() != null) && (cv().b() == ads.f))
    {
      int i = cx();
      float f1 = i / 20.0F;
      if (f1 > 1.0F) {
        f1 = 1.0F;
      } else {
        f1 *= f1;
      }
      f *= (1.0F - f1 * 0.15F);
    }
    return f;
  }
  
  public String getNameClear()
  {
    return this.nameClear;
  }
  
  public void setLocationOfCape(kk locationOfCape, EnumCapePriority capeType)
  {
    this.locationCape = locationOfCape;
    this.capeType = capeType;
    System.out.println("[LabyMod] Loaded " + capeType.name() + " cape of " + getNameClear());
  }
}
