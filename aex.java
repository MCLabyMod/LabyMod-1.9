import com.google.common.collect.Multimap;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.ModSettings;
import de.labystudio.utils.Allowed;
import de.labystudio.utils.DualHand;

public class aex
  extends ado
{
  private final float a;
  private final ado.a b;
  
  public aex(ado.a material)
  {
    this.b = material;
    this.j = 1;
    e(material.a());
    a(acq.j);
    this.a = (3.0F + material.c());
  }
  
  public float g()
  {
    return this.b.c();
  }
  
  public qp<adq> a(adq itemStackIn, aht worldIn, zj playerIn, qm hand)
  {
    if ((ConfigManager.settings.animationSword != 2) && (Allowed.blocking()) && (Allowed.animations()))
    {
      if ((itemStackIn == null) || ((itemStackIn.b() instanceof aex)) || (itemStackIn.b().e(itemStackIn) == 0))
      {
        ado a;
        ado a;
        if (hand == qm.a) {
          a = DualHand.getItemInOffHand();
        } else {
          a = DualHand.getItemInMainHand();
        }
        if ((a == null) || (a.e(itemStackIn) == 0))
        {
          playerIn.c(hand);
          return new qp(qo.a, itemStackIn);
        }
        return new qp(qo.b, itemStackIn);
      }
      return new qp(qo.b, itemStackIn);
    }
    return new qp(qo.b, itemStackIn);
  }
  
  public afa f(adq stack)
  {
    if ((ConfigManager.settings.animationSword != 2) && (Allowed.blocking()) && (Allowed.animations())) {
      return afa.d;
    }
    return afa.a;
  }
  
  public int e(adq stack)
  {
    if ((ConfigManager.settings.animationSword != 2) && (Allowed.blocking()) && (Allowed.animations())) {
      return 72000;
    }
    return 0;
  }
  
  public float a(adq stack, arc state)
  {
    ajt block = state.t();
    if (block == aju.G) {
      return 15.0F;
    }
    axe material = state.a();
    return (material != axe.k) && (material != axe.l) && (material != axe.v) && (material != axe.j) && (material != axe.C) ? 1.0F : 1.5F;
  }
  
  public boolean a(adq stack, sa target, sa attacker)
  {
    stack.a(1, attacker);
    return true;
  }
  
  public boolean a(adq stack, aht worldIn, arc blockIn, cj pos, sa playerIn)
  {
    if (blockIn.b(worldIn, pos) != 0.0D) {
      stack.a(2, playerIn);
    }
    return true;
  }
  
  public boolean A_()
  {
    return true;
  }
  
  public boolean a(arc blockIn)
  {
    return blockIn.t() == aju.G;
  }
  
  public int c()
  {
    return this.b.e();
  }
  
  public String h()
  {
    return this.b.toString();
  }
  
  public boolean a(adq toRepair, adq repair)
  {
    return this.b.f() == repair.b() ? true : super.a(toRepair, repair);
  }
  
  public Multimap<String, sn> a(rw p_111205_1_)
  {
    Multimap<String, sn> multimap = super.a(p_111205_1_);
    if (p_111205_1_ == rw.a)
    {
      multimap.put(yt.e.a(), new sn(g, "Weapon modifier", this.a, 0));
      multimap.put(yt.f.a(), new sn(h, "Weapon modifier", -2.4000000953674316D, 0));
    }
    return multimap;
  }
}
