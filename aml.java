import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Random;

public abstract class aml
  extends ajt
{
  public static final arn a = arn.a("decayable");
  public static final arn b = arn.a("check_decay");
  protected boolean c;
  int[] d;
  
  public aml()
  {
    super(axe.j);
    a(true);
    a(acq.c);
    c(0.2F);
    d(1);
    a(aop.c);
  }
  
  public void b(aht worldIn, cj pos, arc state)
  {
    int i = 1;
    int j = i + 1;
    int k = pos.p();
    int l = pos.q();
    int i1 = pos.r();
    if (worldIn.a(new cj(k - j, l - j, i1 - j), new cj(k + j, l + j, i1 + j))) {
      for (int j1 = -i; j1 <= i; j1++) {
        for (int k1 = -i; k1 <= i; k1++) {
          for (int l1 = -i; l1 <= i; l1++)
          {
            cj blockpos = pos.a(j1, k1, l1);
            arc iblockstate = worldIn.o(blockpos);
            if ((iblockstate.a() == axe.j) && (!((Boolean)iblockstate.c(b)).booleanValue())) {
              worldIn.a(blockpos, iblockstate.a(b, Boolean.valueOf(true)), 4);
            }
          }
        }
      }
    }
  }
  
  public void b(aht worldIn, cj pos, arc state, Random rand)
  {
    if (!worldIn.E) {
      if ((((Boolean)state.c(b)).booleanValue()) && (((Boolean)state.c(a)).booleanValue()))
      {
        int i = 4;
        int j = i + 1;
        int k = pos.p();
        int l = pos.q();
        int i1 = pos.r();
        int j1 = 32;
        int k1 = j1 * j1;
        int l1 = j1 / 2;
        if (this.d == null) {
          this.d = new int[j1 * j1 * j1];
        }
        if (worldIn.a(new cj(k - j, l - j, i1 - j), new cj(k + j, l + j, i1 + j)))
        {
          cj.a blockpos$mutableblockpos = new cj.a();
          for (int i2 = -i; i2 <= i; i2++) {
            for (int j2 = -i; j2 <= i; j2++) {
              for (int k2 = -i; k2 <= i; k2++)
              {
                arc iblockstate = worldIn.o(blockpos$mutableblockpos.c(k + i2, l + j2, i1 + k2));
                ajt block = iblockstate.t();
                if ((block != aju.r) && (block != aju.s))
                {
                  if (iblockstate.a() == axe.j) {
                    this.d[((i2 + l1) * k1 + (j2 + l1) * j1 + k2 + l1)] = -2;
                  } else {
                    this.d[((i2 + l1) * k1 + (j2 + l1) * j1 + k2 + l1)] = -1;
                  }
                }
                else {
                  this.d[((i2 + l1) * k1 + (j2 + l1) * j1 + k2 + l1)] = 0;
                }
              }
            }
          }
          for (int i3 = 1; i3 <= 4; i3++) {
            for (int j3 = -i; j3 <= i; j3++) {
              for (int k3 = -i; k3 <= i; k3++) {
                for (int l3 = -i; l3 <= i; l3++) {
                  if (this.d[((j3 + l1) * k1 + (k3 + l1) * j1 + l3 + l1)] == i3 - 1)
                  {
                    if (this.d[((j3 + l1 - 1) * k1 + (k3 + l1) * j1 + l3 + l1)] == -2) {
                      this.d[((j3 + l1 - 1) * k1 + (k3 + l1) * j1 + l3 + l1)] = i3;
                    }
                    if (this.d[((j3 + l1 + 1) * k1 + (k3 + l1) * j1 + l3 + l1)] == -2) {
                      this.d[((j3 + l1 + 1) * k1 + (k3 + l1) * j1 + l3 + l1)] = i3;
                    }
                    if (this.d[((j3 + l1) * k1 + (k3 + l1 - 1) * j1 + l3 + l1)] == -2) {
                      this.d[((j3 + l1) * k1 + (k3 + l1 - 1) * j1 + l3 + l1)] = i3;
                    }
                    if (this.d[((j3 + l1) * k1 + (k3 + l1 + 1) * j1 + l3 + l1)] == -2) {
                      this.d[((j3 + l1) * k1 + (k3 + l1 + 1) * j1 + l3 + l1)] = i3;
                    }
                    if (this.d[((j3 + l1) * k1 + (k3 + l1) * j1 + (l3 + l1 - 1))] == -2) {
                      this.d[((j3 + l1) * k1 + (k3 + l1) * j1 + (l3 + l1 - 1))] = i3;
                    }
                    if (this.d[((j3 + l1) * k1 + (k3 + l1) * j1 + l3 + l1 + 1)] == -2) {
                      this.d[((j3 + l1) * k1 + (k3 + l1) * j1 + l3 + l1 + 1)] = i3;
                    }
                  }
                }
              }
            }
          }
        }
        int l2 = this.d[(l1 * k1 + l1 * j1 + l1)];
        if (l2 >= 0) {
          worldIn.a(pos, state.a(b, Boolean.valueOf(false)), 4);
        } else {
          b(worldIn, pos);
        }
      }
    }
  }
  
  public void a(arc worldIn, aht pos, cj state, Random rand)
  {
    if ((pos.B(state.a())) && (!pos.o(state.b()).q()) && (rand.nextInt(15) == 1))
    {
      double d0 = state.p() + rand.nextFloat();
      double d1 = state.q() - 0.05D;
      double d2 = state.r() + rand.nextFloat();
      pos.a(cy.s, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
    }
  }
  
  private void b(aht worldIn, cj pos)
  {
    b(worldIn, pos, worldIn.o(pos), 0);
    worldIn.g(pos);
  }
  
  public int a(Random random)
  {
    return random.nextInt(20) == 0 ? 1 : 0;
  }
  
  public ado a(arc state, Random rand, int fortune)
  {
    return ado.a(aju.g);
  }
  
  public void a(aht worldIn, cj pos, arc state, float chance, int fortune)
  {
    if (!worldIn.E)
    {
      int i = i(state);
      if (fortune > 0)
      {
        i -= (2 << fortune);
        if (i < 10) {
          i = 10;
        }
      }
      if (worldIn.r.nextInt(i) == 0)
      {
        ado item = a(state, worldIn.r, fortune);
        a(worldIn, pos, new adq(item, 1, d(state)));
      }
      i = 200;
      if (fortune > 0)
      {
        i -= (10 << fortune);
        if (i < 40) {
          i = 40;
        }
      }
      a(worldIn, pos, state, i);
    }
  }
  
  protected void a(aht worldIn, cj pos, arc state, int chance) {}
  
  protected int i(arc state)
  {
    return 20;
  }
  
  public boolean b(arc state)
  {
    return !this.c;
  }
  
  public void b(boolean fancy)
  {
    this.c = fancy;
  }
  
  public ahm f()
  {
    return this.c ? ahm.b : ahm.a;
  }
  
  public boolean j()
  {
    return false;
  }
  
  public abstract anj.a e(int paramInt);
  
  public boolean a(arc blockState, ahx blockAccess, cj pos, cq side)
  {
    return (Config.isCullFacesLeaves()) && (blockAccess.o(pos.a(side)).t() == this) ? false : super.a(blockState, blockAccess, pos, side);
  }
  
  private static Map mapOriginalOpacity = new IdentityHashMap();
  
  public static void setLightOpacity(ajt block, int opacity)
  {
    if (!mapOriginalOpacity.containsKey(block)) {
      mapOriginalOpacity.put(block, Integer.valueOf(block.m));
    }
    block.m = opacity;
  }
  
  public static void restoreLightOpacity(ajt block)
  {
    if (!mapOriginalOpacity.containsKey(block)) {
      return;
    }
    int opacity = ((Integer)mapOriginalOpacity.get(block)).intValue();
    
    setLightOpacity(block, opacity);
  }
}
