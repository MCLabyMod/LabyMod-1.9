import com.google.common.collect.Maps;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.ModSettings;
import java.util.Map;
import java.util.UUID;

public class bcy
  extends bcv
{
  private static final kk a = new kk("textures/gui/bars.png");
  private final bcf f;
  private final Map<UUID, bdh> g = Maps.newLinkedHashMap();
  
  public Map<UUID, bdh> getField_184060_g()
  {
    return this.g;
  }
  
  public bcy(bcf p_i46606_1_)
  {
    this.f = p_i46606_1_;
  }
  
  public void a()
  {
    bcx scaledresolution;
    int i;
    int j;
    if (!this.g.isEmpty())
    {
      scaledresolution = new bcx(this.f);
      i = scaledresolution.a();
      j = 12;
      for (bdh bossinfolerping : this.g.values())
      {
        int k = i / 2 - 91;
        bni.c(1.0F, 1.0F, 1.0F, 1.0F);
        if (ConfigManager.settings.showBossBar)
        {
          this.f.N().a(a);
          a(k, j, bossinfolerping);
        }
        String s = bossinfolerping.e().d();
        this.f.k.a(s, i / 2 - this.f.k.a(s) / 2, j - 9, 16777215);
        j += 10 + this.f.k.a;
        if (j >= scaledresolution.b() / 3) {
          break;
        }
      }
    }
  }
  
  private void a(int p_184052_1_, int p_184052_2_, qe p_184052_3_)
  {
    b(p_184052_1_, p_184052_2_, 0, p_184052_3_.g().ordinal() * 5 * 2, 182, 5);
    if (p_184052_3_.h() != qe.b.a) {
      b(p_184052_1_, p_184052_2_, 0, 80 + (p_184052_3_.h().ordinal() - 1) * 5 * 2, 182, 5);
    }
    int i = (int)(p_184052_3_.f() * 183.0F);
    if (i > 0)
    {
      b(p_184052_1_, p_184052_2_, 0, p_184052_3_.g().ordinal() * 5 * 2 + 5, i, 5);
      if (p_184052_3_.h() != qe.b.a) {
        b(p_184052_1_, p_184052_2_, 0, 80 + (p_184052_3_.h().ordinal() - 1) * 5 * 2 + 5, i, 5);
      }
    }
  }
  
  public void a(fv p_184055_1_)
  {
    if (p_184055_1_.b() == fv.a.a) {
      this.g.put(p_184055_1_.a(), new bdh(p_184055_1_));
    } else if (p_184055_1_.b() == fv.a.b) {
      this.g.remove(p_184055_1_.a());
    } else {
      ((bdh)this.g.get(p_184055_1_.a())).a(p_184055_1_);
    }
  }
  
  public void b()
  {
    this.g.clear();
  }
  
  public boolean d()
  {
    if (!this.g.isEmpty()) {
      for (qe bossinfo : this.g.values()) {
        if (bossinfo.j()) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean e()
  {
    if (!this.g.isEmpty()) {
      for (qe bossinfo : this.g.values()) {
        if (bossinfo.i()) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean f()
  {
    if (!this.g.isEmpty()) {
      for (qe bossinfo : this.g.values()) {
        if (bossinfo.k()) {
          return true;
        }
      }
    }
    return false;
  }
}
