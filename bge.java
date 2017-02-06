import com.google.common.collect.Ordering;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.ModSettings;
import java.util.Collection;

public abstract class bge
  extends bft
{
  private boolean u;
  
  public bge(aau inventorySlotsIn)
  {
    super(inventorySlotsIn);
  }
  
  public void b()
  {
    super.b();
    a();
  }
  
  protected void a()
  {
    if (!this.j.h.bO().isEmpty())
    {
      if (!ConfigManager.settings.oldInventory) {
        this.i = (160 + (this.l - this.f - 200) / 2);
      }
      this.u = true;
    }
    else
    {
      this.i = ((this.l - this.f) / 2);
      this.u = false;
    }
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    super.a(mouseX, mouseY, partialTicks);
    if (this.u) {
      f();
    }
  }
  
  private void f()
  {
    int i = this.i - 124;
    int j = this.r;
    int k = 166;
    Collection<rl> collection = this.j.h.bO();
    int l;
    if (!collection.isEmpty())
    {
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      bni.g();
      l = 33;
      if (collection.size() > 5) {
        l = 132 / (collection.size() - 1);
      }
      for (rl potioneffect : Ordering.natural().sortedCopy(collection))
      {
        rk potion = potioneffect.a();
        bni.c(1.0F, 1.0F, 1.0F, 1.0F);
        this.j.N().a(a);
        b(i, j, 0, 166, 140, 32);
        if (potion.c())
        {
          int i1 = potion.d();
          b(i + 6, j + 7, 0 + i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18);
        }
        String s1 = bwo.a(potion.a(), new Object[0]);
        if (potioneffect.c() == 1) {
          s1 = s1 + " " + bwo.a("enchantment.level.2", new Object[0]);
        } else if (potioneffect.c() == 2) {
          s1 = s1 + " " + bwo.a("enchantment.level.3", new Object[0]);
        } else if (potioneffect.c() == 3) {
          s1 = s1 + " " + bwo.a("enchantment.level.4", new Object[0]);
        }
        this.q.a(s1, i + 10 + 18, j + 6, 16777215);
        String s = rk.a(potioneffect, 1.0F);
        this.q.a(s, i + 10 + 18, j + 6 + 10, 8355711);
        j += l;
      }
    }
  }
}
