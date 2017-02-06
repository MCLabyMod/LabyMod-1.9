import com.google.common.collect.Lists;
import java.util.List;

public class bti
  extends brw<za>
{
  private static final kk k = new kk("textures/entity/zombie_villager/zombie_villager.png");
  private static final kk l = new kk("textures/entity/zombie_villager/zombie_farmer.png");
  private static final kk m = new kk("textures/entity/zombie_villager/zombie_librarian.png");
  private static final kk n = new kk("textures/entity/zombie_villager/zombie_priest.png");
  private static final kk o = new kk("textures/entity/zombie_villager/zombie_smith.png");
  private static final kk p = new kk("textures/entity/zombie_villager/zombie_butcher.png");
  private static final kk q = new kk("textures/entity/zombie/zombie.png");
  private final bix r;
  private bkb s;
  private final List<bty<za>> t;
  private final List<bty<za>> u;
  
  public bti(brm ☃)
  {
    super(☃, new bkf(), 0.5F, 1.0F);
    bty<?> ☃ = (bty)this.i.get(0);
    
    this.r = this.a;
    this.s = new bkb();
    
    a(new btv(this));
    
    btu ☃ = new btu(this)
    {
      protected void I_()
      {
        this.c = new bkf(0.5F, true);
        this.d = new bkf(1.0F, true);
      }
    };
    a(☃);
    this.u = Lists.newArrayList(this.i);
    if ((☃ instanceof bto))
    {
      b(☃);
      a(new bto(this.s.e));
    }
    b(☃);
    a(new bud(this));
    this.t = Lists.newArrayList(this.i);
  }
  
  public void a(za ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    b(☃);
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  protected kk a(za ☃)
  {
    if (☃.dd())
    {
      switch (☃.de())
      {
      case 0: 
        return l;
      case 1: 
        return m;
      case 2: 
        return n;
      case 3: 
        return o;
      case 4: 
        return p;
      }
      return k;
    }
    return q;
  }
  
  private void b(za ☃)
  {
    if (☃.dd())
    {
      this.g = this.s;
      this.i = this.t;
    }
    else
    {
      this.g = this.r;
      this.i = this.u;
    }
    this.a = ((bix)this.g);
  }
  
  protected void a(za ☃, float ☃, float ☃, float ☃)
  {
    if (☃.dg()) {
      ☃ += (float)(Math.cos(☃.T * 3.25D) * 3.141592653589793D * 0.25D);
    }
    super.a(☃, ☃, ☃, ☃);
  }
}
