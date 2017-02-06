import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ase
{
  private static final Logger d = ;
  public static final asf a = null;
  private final asf[] e;
  private final byte[] f;
  private final int[] g;
  private final boolean[] h;
  private boolean i;
  private final aht j;
  private final int[] k;
  public final int b;
  public final int c;
  private boolean l;
  private final Map<cj, apv> m;
  private final ny<rr>[] n;
  private boolean o;
  private boolean p;
  private boolean q;
  private boolean r;
  private boolean s;
  private long t;
  private int u;
  private long v;
  private int w;
  private ConcurrentLinkedQueue<cj> x;
  
  public ase(aht worldIn, int x, int z)
  {
    this.e = new asf[16];
    this.f = new byte['Ā'];
    this.g = new int['Ā'];
    this.h = new boolean['Ā'];
    this.m = Maps.newHashMap();
    this.w = 4096;
    this.x = Queues.newConcurrentLinkedQueue();
    this.n = ((ny[])new ny[16]);
    this.j = worldIn;
    this.b = x;
    this.c = z;
    this.k = new int['Ā'];
    for (int i = 0; i < this.n.length; i++) {
      this.n[i] = new ny(rr.class);
    }
    Arrays.fill((int[])this.g, 64537);
    Arrays.fill(this.f, (byte)-1);
  }
  
  public ase(aht worldIn, atf primer, int x, int z)
  {
    this(worldIn, x, z);
    int i = 256;
    boolean flag = !worldIn.s.m();
    for (int j = 0; j < 16; j++) {
      for (int k = 0; k < 16; k++) {
        for (int l = 0; l < i; l++)
        {
          arc iblockstate = primer.a(j, l, k);
          if (iblockstate.a() != axe.a)
          {
            int i1 = l >> 4;
            if (this.e[i1] == a) {
              this.e[i1] = new asf(i1 << 4, flag);
            }
            this.e[i1].a(j, l & 0xF, k, iblockstate);
          }
        }
      }
    }
  }
  
  public boolean a(int x, int z)
  {
    return (x == this.b) && (z == this.c);
  }
  
  public int e(cj pos)
  {
    return b(pos.p() & 0xF, pos.r() & 0xF);
  }
  
  public int b(int x, int z)
  {
    return this.k[(z << 4 | x)];
  }
  
  private asf y()
  {
    for (int i = this.e.length - 1; i >= 0; i--) {
      if (this.e[i] != a) {
        return this.e[i];
      }
    }
    return null;
  }
  
  public int g()
  {
    asf extendedblockstorage = y();
    return extendedblockstorage == null ? 0 : extendedblockstorage.d();
  }
  
  public asf[] h()
  {
    return this.e;
  }
  
  protected void a()
  {
    int i = g();
    this.u = Integer.MAX_VALUE;
    for (int j = 0; j < 16; j++) {
      for (int k = 0; k < 16; k++)
      {
        this.g[(j + (k << 4))] = 64537;
        for (int l = i + 16; l > 0; l--)
        {
          arc iblockstate = a(j, l - 1, k);
          if (iblockstate.c() != 0)
          {
            this.k[(k << 4 | j)] = l;
            if (l >= this.u) {
              break;
            }
            this.u = l; break;
          }
        }
      }
    }
    this.r = true;
  }
  
  public void b()
  {
    int i = g();
    this.u = Integer.MAX_VALUE;
    for (int j = 0; j < 16; j++) {
      for (int k = 0; k < 16; k++)
      {
        this.g[(j + (k << 4))] = 64537;
        for (int l = i + 16; l > 0; l--) {
          if (d(j, l - 1, k) != 0)
          {
            this.k[(k << 4 | j)] = l;
            if (l >= this.u) {
              break;
            }
            this.u = l; break;
          }
        }
        if (!this.j.s.m())
        {
          int k1 = 15;
          int i1 = i + 16 - 1;
          for (;;)
          {
            int j1 = d(j, i1, k);
            if ((j1 == 0) && (k1 != 15)) {
              j1 = 1;
            }
            k1 -= j1;
            if (k1 > 0)
            {
              asf extendedblockstorage = this.e[(i1 >> 4)];
              if (extendedblockstorage != a)
              {
                extendedblockstorage.a(j, i1 & 0xF, k, k1);
                this.j.m(new cj((this.b << 4) + j, i1, (this.c << 4) + k));
              }
            }
            i1--;
            if ((i1 <= 0) || (k1 <= 0)) {
              break;
            }
          }
        }
      }
    }
    this.r = true;
  }
  
  private void d(int x, int z)
  {
    this.h[(x + z * 16)] = true;
    this.l = true;
  }
  
  private void h(boolean p_150803_1_)
  {
    this.j.C.a("recheckGaps");
    if (this.j.a(new cj(this.b * 16 + 8, 0, this.c * 16 + 8), 16))
    {
      for (int i = 0; i < 16; i++) {
        for (int j = 0; j < 16; j++) {
          if (this.h[(i + j * 16)] != 0)
          {
            this.h[(i + j * 16)] = false;
            int k = b(i, j);
            int l = this.b * 16 + i;
            int i1 = this.c * 16 + j;
            int j1 = Integer.MAX_VALUE;
            for (cq enumfacing : cq.c.a) {
              j1 = Math.min(j1, this.j.b(l + enumfacing.g(), i1 + enumfacing.i()));
            }
            b(l, i1, j1);
            for (cq enumfacing1 : cq.c.a) {
              b(l + enumfacing1.g(), i1 + enumfacing1.i(), k);
            }
            if (p_150803_1_)
            {
              this.j.C.b();
              return;
            }
          }
        }
      }
      this.l = false;
    }
    this.j.C.b();
  }
  
  private void b(int x, int z, int maxValue)
  {
    int i = this.j.l(new cj(x, 0, z)).q();
    if (i > maxValue) {
      a(x, z, maxValue, i + 1);
    } else if (i < maxValue) {
      a(x, z, i, maxValue + 1);
    }
  }
  
  private void a(int x, int z, int startY, int endY)
  {
    if ((endY > startY) && (this.j.a(new cj(x, 0, z), 16)))
    {
      for (int i = startY; i < endY; i++) {
        this.j.c(ahz.a, new cj(x, i, z));
      }
      this.r = true;
    }
  }
  
  private void c(int x, int y, int z)
  {
    int i = this.k[(z << 4 | x)] & 0xFF;
    int j = i;
    if (y > i) {
      j = y;
    }
    while ((j > 0) && (d(x, j - 1, z) == 0)) {
      j--;
    }
    if (j != i)
    {
      this.j.a(x + this.b * 16, z + this.c * 16, j, i);
      this.k[(z << 4 | x)] = j;
      int k = this.b * 16 + x;
      int l = this.c * 16 + z;
      if (!this.j.s.m())
      {
        if (j < i) {
          for (int j1 = j; j1 < i; j1++)
          {
            asf extendedblockstorage2 = this.e[(j1 >> 4)];
            if (extendedblockstorage2 != a)
            {
              extendedblockstorage2.a(x, j1 & 0xF, z, 15);
              this.j.m(new cj((this.b << 4) + x, j1, (this.c << 4) + z));
            }
          }
        } else {
          for (int i1 = i; i1 < j; i1++)
          {
            asf extendedblockstorage = this.e[(i1 >> 4)];
            if (extendedblockstorage != a)
            {
              extendedblockstorage.a(x, i1 & 0xF, z, 0);
              this.j.m(new cj((this.b << 4) + x, i1, (this.c << 4) + z));
            }
          }
        }
        int k1 = 15;
        while ((j > 0) && (k1 > 0))
        {
          j--;
          int i2 = d(x, j, z);
          if (i2 == 0) {
            i2 = 1;
          }
          k1 -= i2;
          if (k1 < 0) {
            k1 = 0;
          }
          asf extendedblockstorage1 = this.e[(j >> 4)];
          if (extendedblockstorage1 != a) {
            extendedblockstorage1.a(x, j & 0xF, z, k1);
          }
        }
      }
      int l1 = this.k[(z << 4 | x)];
      int j2 = i;
      int k2 = l1;
      if (l1 < i)
      {
        j2 = l1;
        k2 = i;
      }
      if (l1 < this.u) {
        this.u = l1;
      }
      if (!this.j.s.m())
      {
        for (cq enumfacing : cq.c.a) {
          a(k + enumfacing.g(), l + enumfacing.i(), j2, k2);
        }
        a(k, l, j2, k2);
      }
      this.r = true;
    }
  }
  
  public int b(cj pos)
  {
    return a(pos).c();
  }
  
  private int d(int x, int y, int z)
  {
    return a(x, y, z).c();
  }
  
  public arc a(cj pos)
  {
    return a(pos.p(), pos.q(), pos.r());
  }
  
  public arc a(final int p_186032_1_, final int p_186032_2_, final int p_186032_3_)
  {
    if (this.j.L() == ahy.g)
    {
      arc iblockstate = null;
      if (p_186032_2_ == 60) {
        iblockstate = aju.cv.u();
      }
      if (p_186032_2_ == 70) {
        iblockstate = ath.c(p_186032_1_, p_186032_3_);
      }
      return iblockstate == null ? aju.a.u() : iblockstate;
    }
    try
    {
      if ((p_186032_2_ >= 0) && (p_186032_2_ >> 4 < this.e.length))
      {
        asf extendedblockstorage = this.e[(p_186032_2_ >> 4)];
        if (extendedblockstorage != a) {
          return extendedblockstorage.a(p_186032_1_ & 0xF, p_186032_2_ & 0xF, p_186032_3_ & 0xF);
        }
      }
      return aju.a.u();
    }
    catch (Throwable throwable)
    {
      b crashreport = b.a(throwable, "Getting block state");
      c crashreportcategory = crashreport.a("Block being got");
      crashreportcategory.a("Location", new Callable()
      {
        public String a()
          throws Exception
        {
          return c.a(p_186032_1_, p_186032_2_, p_186032_3_);
        }
      });
      throw new e(crashreport);
    }
  }
  
  public arc a(cj pos, arc state)
  {
    int i = pos.p() & 0xF;
    int j = pos.q();
    int k = pos.r() & 0xF;
    int l = k << 4 | i;
    if (j >= this.g[l] - 1) {
      this.g[l] = 64537;
    }
    int i1 = this.k[l];
    arc iblockstate = a(pos);
    if (iblockstate == state) {
      return null;
    }
    ajt block = state.t();
    ajt block1 = iblockstate.t();
    asf extendedblockstorage = this.e[(j >> 4)];
    boolean flag = false;
    if (extendedblockstorage == a)
    {
      if (block == aju.a) {
        return null;
      }
      extendedblockstorage = this.e[(j >> 4)] = new asf(j >> 4 << 4, !this.j.s.m());
      flag = j >= i1;
    }
    extendedblockstorage.a(i, j & 0xF, k, state);
    if (block1 != block) {
      if (!this.j.E) {
        block1.b(this.j, pos, iblockstate);
      } else if ((block1 instanceof alg)) {
        this.j.s(pos);
      }
    }
    if (extendedblockstorage.a(i, j & 0xF, k).t() != block) {
      return null;
    }
    if (flag)
    {
      b();
    }
    else
    {
      int j1 = state.c();
      int k1 = iblockstate.c();
      if (j1 > 0)
      {
        if (j >= i1) {
          c(i, j + 1, k);
        }
      }
      else if (j == i1 - 1) {
        c(i, j, k);
      }
      if ((j1 != k1) && ((j1 < k1) || (a(ahz.a, pos) > 0) || (a(ahz.b, pos) > 0))) {
        d(i, k);
      }
    }
    if ((block1 instanceof alg))
    {
      apv tileentity = a(pos, ase.a.c);
      if (tileentity != null) {
        tileentity.A();
      }
    }
    if ((!this.j.E) && (block1 != block)) {
      block.c(this.j, pos, state);
    }
    if ((block instanceof alg))
    {
      apv tileentity1 = a(pos, ase.a.c);
      if (tileentity1 == null)
      {
        tileentity1 = ((alg)block).a(this.j, block.e(state));
        this.j.a(pos, tileentity1);
      }
      if (tileentity1 != null) {
        tileentity1.A();
      }
    }
    this.r = true;
    return iblockstate;
  }
  
  public int a(ahz p_177413_1_, cj pos)
  {
    int i = pos.p() & 0xF;
    int j = pos.q();
    int k = pos.r() & 0xF;
    asf extendedblockstorage = this.e[(j >> 4)];
    return p_177413_1_ == ahz.b ? extendedblockstorage.c(i, j & 0xF, k) : p_177413_1_ == ahz.a ? extendedblockstorage.b(i, j & 0xF, k) : this.j.s.m() ? 0 : extendedblockstorage == a ? 0 : c(pos) ? p_177413_1_.c : p_177413_1_.c;
  }
  
  public void a(ahz p_177431_1_, cj pos, int value)
  {
    int i = pos.p() & 0xF;
    int j = pos.q();
    int k = pos.r() & 0xF;
    asf extendedblockstorage = this.e[(j >> 4)];
    if (extendedblockstorage == a)
    {
      extendedblockstorage = this.e[(j >> 4)] = new asf(j >> 4 << 4, !this.j.s.m());
      b();
    }
    this.r = true;
    if (p_177431_1_ == ahz.a)
    {
      if (!this.j.s.m()) {
        extendedblockstorage.a(i, j & 0xF, k, value);
      }
    }
    else if (p_177431_1_ == ahz.b) {
      extendedblockstorage.b(i, j & 0xF, k, value);
    }
  }
  
  public int a(cj pos, int amount)
  {
    int i = pos.p() & 0xF;
    int j = pos.q();
    int k = pos.r() & 0xF;
    asf extendedblockstorage = this.e[(j >> 4)];
    if (extendedblockstorage == a) {
      return (!this.j.s.m()) && (amount < ahz.a.c) ? ahz.a.c - amount : 0;
    }
    int l = this.j.s.m() ? 0 : extendedblockstorage.b(i, j & 0xF, k);
    l -= amount;
    int i1 = extendedblockstorage.c(i, j & 0xF, k);
    if (i1 > l) {
      l = i1;
    }
    return l;
  }
  
  public void a(rr entityIn)
  {
    this.s = true;
    int i = on.c(entityIn.p / 16.0D);
    int j = on.c(entityIn.r / 16.0D);
    if ((i != this.b) || (j != this.c))
    {
      d.warn("Wrong location! (" + i + ", " + j + ") should be (" + this.b + ", " + this.c + "), " + entityIn, new Object[] { entityIn });
      entityIn.T();
    }
    int k = on.c(entityIn.q / 16.0D);
    if (k < 0) {
      k = 0;
    }
    if (k >= this.n.length) {
      k = this.n.length - 1;
    }
    entityIn.aa = true;
    entityIn.ab = this.b;
    entityIn.ac = k;
    entityIn.ad = this.c;
    this.n[k].add(entityIn);
  }
  
  public void b(rr entityIn)
  {
    a(entityIn, entityIn.ac);
  }
  
  public void a(rr entityIn, int p_76608_2_)
  {
    if (p_76608_2_ < 0) {
      p_76608_2_ = 0;
    }
    if (p_76608_2_ >= this.n.length) {
      p_76608_2_ = this.n.length - 1;
    }
    this.n[p_76608_2_].remove(entityIn);
  }
  
  public boolean c(cj pos)
  {
    int i = pos.p() & 0xF;
    int j = pos.q();
    int k = pos.r() & 0xF;
    return j >= this.k[(k << 4 | i)];
  }
  
  private apv g(cj pos)
  {
    arc iblockstate = a(pos);
    ajt block = iblockstate.t();
    return !block.m() ? null : ((alg)block).a(this.j, iblockstate.t().e(iblockstate));
  }
  
  public apv a(cj pos, ase.a p_177424_2_)
  {
    apv tileentity = (apv)this.m.get(pos);
    if (tileentity == null)
    {
      if (p_177424_2_ == ase.a.a)
      {
        tileentity = g(pos);
        this.j.a(pos, tileentity);
      }
      else if (p_177424_2_ == ase.a.b)
      {
        this.x.add(pos);
      }
    }
    else if (tileentity.x())
    {
      this.m.remove(pos);
      return null;
    }
    return tileentity;
  }
  
  public void a(apv tileEntityIn)
  {
    a(tileEntityIn.v(), tileEntityIn);
    if (this.i) {
      this.j.a(tileEntityIn);
    }
  }
  
  public void a(cj pos, apv tileEntityIn)
  {
    tileEntityIn.a(this.j);
    tileEntityIn.a(pos);
    if ((a(pos).t() instanceof alg))
    {
      if (this.m.containsKey(pos)) {
        ((apv)this.m.get(pos)).y();
      }
      tileEntityIn.z();
      this.m.put(pos, tileEntityIn);
    }
  }
  
  public void d(cj pos)
  {
    if (this.i)
    {
      apv tileentity = (apv)this.m.remove(pos);
      if (tileentity != null) {
        tileentity.y();
      }
    }
  }
  
  public void c()
  {
    this.i = true;
    this.j.b(this.m.values());
    for (int i = 0; i < this.n.length; i++)
    {
      for (rr entity : this.n[i]) {
        entity.at();
      }
      this.j.a(this.n[i]);
    }
  }
  
  public void d()
  {
    this.i = false;
    for (apv tileentity : this.m.values()) {
      this.j.b(tileentity);
    }
    for (int i = 0; i < this.n.length; i++) {
      this.j.c(this.n[i]);
    }
  }
  
  public void e()
  {
    this.r = true;
  }
  
  public void a(rr entityIn, bbh aabb, List<rr> listToFill, Predicate<? super rr> p_177414_4_)
  {
    int i = on.c((aabb.b - 2.0D) / 16.0D);
    int j = on.c((aabb.e + 2.0D) / 16.0D);
    i = on.a(i, 0, this.n.length - 1);
    j = on.a(j, 0, this.n.length - 1);
    for (int k = i; k <= j; k++) {
      if (!this.n[k].isEmpty()) {
        for (rr entity : this.n[k]) {
          if ((entity.bl().b(aabb)) && (entity != entityIn))
          {
            if ((p_177414_4_ == null) || (p_177414_4_.apply(entity))) {
              listToFill.add(entity);
            }
            rr[] aentity = entity.aR();
            if (aentity != null) {
              for (int l = 0; l < aentity.length; l++)
              {
                entity = aentity[l];
                if ((entity != entityIn) && (entity.bl().b(aabb)) && ((p_177414_4_ == null) || (p_177414_4_.apply(entity)))) {
                  listToFill.add(entity);
                }
              }
            }
          }
        }
      }
    }
  }
  
  public <T extends rr> void a(Class<? extends T> entityClass, bbh aabb, List<T> listToFill, Predicate<? super T> p_177430_4_)
  {
    int i = on.c((aabb.b - 2.0D) / 16.0D);
    int j = on.c((aabb.e + 2.0D) / 16.0D);
    i = on.a(i, 0, this.n.length - 1);
    j = on.a(j, 0, this.n.length - 1);
    for (int k = i; k <= j; k++) {
      for (T t : this.n[k].c(entityClass)) {
        if ((t.bl().b(aabb)) && ((p_177430_4_ == null) || (p_177430_4_.apply(t)))) {
          listToFill.add(t);
        }
      }
    }
  }
  
  public boolean a(boolean p_76601_1_)
  {
    if (p_76601_1_)
    {
      if (((this.s) && (this.j.P() != this.t)) || (this.r)) {
        return true;
      }
    }
    else if ((this.s) && (this.j.P() >= this.t + 600L)) {
      return true;
    }
    return this.r;
  }
  
  public Random a(long seed)
  {
    return new Random(this.j.O() + this.b * this.b * 4987142 + this.b * 5947611 + this.c * this.c * 4392871L + this.c * 389711 ^ seed);
  }
  
  public boolean f()
  {
    return false;
  }
  
  public void a(arz p_186030_1_, ary p_186030_2_)
  {
    ase chunk = p_186030_1_.b(this.b, this.c - 1);
    ase chunk1 = p_186030_1_.b(this.b + 1, this.c);
    ase chunk2 = p_186030_1_.b(this.b, this.c + 1);
    ase chunk3 = p_186030_1_.b(this.b - 1, this.c);
    if ((chunk1 != null) && (chunk2 != null) && (p_186030_1_.b(this.b + 1, this.c + 1) != null)) {
      a(p_186030_2_);
    }
    if ((chunk3 != null) && (chunk2 != null) && (p_186030_1_.b(this.b - 1, this.c + 1) != null)) {
      chunk3.a(p_186030_2_);
    }
    if ((chunk != null) && (chunk1 != null) && (p_186030_1_.b(this.b + 1, this.c - 1) != null)) {
      chunk.a(p_186030_2_);
    }
    if ((chunk != null) && (chunk3 != null))
    {
      ase chunk4 = p_186030_1_.b(this.b - 1, this.c - 1);
      if (chunk4 != null) {
        chunk4.a(p_186030_2_);
      }
    }
  }
  
  protected void a(ary p_186034_1_)
  {
    if (u())
    {
      if (p_186034_1_.a(this, this.b, this.c)) {
        e();
      }
    }
    else
    {
      o();
      p_186034_1_.b(this.b, this.c);
      e();
    }
  }
  
  public cj f(cj pos)
  {
    int i = pos.p() & 0xF;
    int j = pos.r() & 0xF;
    int k = i | j << 4;
    cj blockpos = new cj(pos.p(), this.g[k], pos.r());
    if (blockpos.q() == 64537)
    {
      int l = g() + 15;
      blockpos = new cj(pos.p(), l, pos.r());
      int i1 = -1;
      while ((blockpos.q() > 0) && (i1 == -1))
      {
        arc iblockstate = a(blockpos);
        axe material = iblockstate.a();
        if ((!material.c()) && (!material.d())) {
          blockpos = blockpos.b();
        } else {
          i1 = blockpos.q() + 1;
        }
      }
      this.g[k] = i1;
    }
    return new cj(pos.p(), this.g[k], pos.r());
  }
  
  public void b(boolean p_150804_1_)
  {
    if ((this.l) && (!this.j.s.m()) && (!p_150804_1_)) {
      h(this.j.E);
    }
    this.q = true;
    if ((!this.p) && (this.o)) {
      o();
    }
    while (!this.x.isEmpty())
    {
      cj blockpos = (cj)this.x.poll();
      if ((a(blockpos, ase.a.c) == null) && (a(blockpos).t().m()))
      {
        apv tileentity = g(blockpos);
        this.j.a(blockpos, tileentity);
        this.j.b(blockpos, blockpos);
      }
    }
  }
  
  public boolean i()
  {
    return (this.q) && (this.o) && (this.p);
  }
  
  public boolean j()
  {
    return this.q;
  }
  
  public ahn k()
  {
    return new ahn(this.b, this.c);
  }
  
  public boolean c(int startY, int endY)
  {
    if (startY < 0) {
      startY = 0;
    }
    if (endY >= 256) {
      endY = 255;
    }
    for (int i = startY; i <= endY; i += 16)
    {
      asf extendedblockstorage = this.e[(i >> 4)];
      if ((extendedblockstorage != a) && (!extendedblockstorage.a())) {
        return false;
      }
    }
    return true;
  }
  
  public void a(asf[] newStorageArrays)
  {
    if (this.e.length != newStorageArrays.length) {
      d.warn("Could not set level chunk sections, array length is " + newStorageArrays.length + " instead of " + this.e.length);
    } else {
      System.arraycopy(newStorageArrays, 0, this.e, 0, this.e.length);
    }
  }
  
  public void a(em p_186033_1_, int p_186033_2_, boolean p_186033_3_)
  {
    boolean flag = !this.j.s.m();
    for (int i = 0; i < this.e.length; i++)
    {
      asf extendedblockstorage = this.e[i];
      if ((p_186033_2_ & 1 << i) == 0)
      {
        if ((p_186033_3_) && (extendedblockstorage != a)) {
          this.e[i] = a;
        }
      }
      else
      {
        if (extendedblockstorage == a)
        {
          extendedblockstorage = new asf(i << 4, flag);
          this.e[i] = extendedblockstorage;
        }
        extendedblockstorage.g().a(p_186033_1_);
        p_186033_1_.readBytes(extendedblockstorage.h().a());
        if (flag) {
          p_186033_1_.readBytes(extendedblockstorage.i().a());
        }
      }
    }
    if (p_186033_3_) {
      p_186033_1_.readBytes(this.f);
    }
    for (int j = 0; j < this.e.length; j++) {
      if ((this.e[j] != a) && ((p_186033_2_ & 1 << j) != 0)) {
        this.e[j].e();
      }
    }
    this.p = true;
    this.o = true;
    a();
    for (apv tileentity : this.m.values()) {
      tileentity.A();
    }
  }
  
  public aig a(cj pos, aik chunkManager)
  {
    int i = pos.p() & 0xF;
    int j = pos.r() & 0xF;
    int k = this.f[(j << 4 | i)] & 0xFF;
    if (k == 255)
    {
      aig biomegenbase = chunkManager.a(pos, ail.c);
      k = aig.a(biomegenbase);
      this.f[(j << 4 | i)] = ((byte)(k & 0xFF));
    }
    aig biomegenbase1 = aig.b(k);
    return biomegenbase1 == null ? ail.c : biomegenbase1;
  }
  
  public byte[] l()
  {
    return this.f;
  }
  
  public void a(byte[] biomeArray)
  {
    if (this.f.length != biomeArray.length) {
      d.warn("Could not set level chunk biomes, array length is " + biomeArray.length + " instead of " + this.f.length);
    } else {
      for (int i = 0; i < this.f.length; i++) {
        this.f[i] = biomeArray[i];
      }
    }
  }
  
  public void m()
  {
    this.w = 0;
  }
  
  public void n()
  {
    if (this.w < 4096)
    {
      cj blockpos = new cj(this.b << 4, 0, this.c << 4);
      for (int i = 0; i < 8; i++)
      {
        if (this.w >= 4096) {
          return;
        }
        int j = this.w % 16;
        int k = this.w / 16 % 16;
        int l = this.w / 256;
        this.w += 1;
        for (int i1 = 0; i1 < 16; i1++)
        {
          cj blockpos1 = blockpos.a(k, (j << 4) + i1, l);
          boolean flag = (i1 == 0) || (i1 == 15) || (k == 0) || (k == 15) || (l == 0) || (l == 15);
          if (((this.e[j] == a) && (flag)) || ((this.e[j] != a) && (this.e[j].a(k, i1, l).a() == axe.a)))
          {
            for (cq enumfacing : cq.values())
            {
              cj blockpos2 = blockpos1.a(enumfacing);
              if (this.j.o(blockpos2).d() > 0) {
                this.j.w(blockpos2);
              }
            }
            this.j.w(blockpos1);
          }
        }
      }
    }
  }
  
  public void o()
  {
    this.o = true;
    this.p = true;
    cj blockpos = new cj(this.b << 4, 0, this.c << 4);
    if (!this.j.s.m()) {
      if (this.j.a(blockpos.a(-1, 0, -1), blockpos.a(16, this.j.K(), 16)))
      {
        for (int i = 0; i < 16; i++) {
          for (int j = 0; j < 16; j++) {
            if (!e(i, j))
            {
              this.p = false;
              break label121;
            }
          }
        }
        label121:
        if (this.p)
        {
          for (cq enumfacing : cq.c.a)
          {
            int k = enumfacing.c() == cq.b.a ? 16 : 1;
            this.j.f(blockpos.a(enumfacing, k)).a(enumfacing.d());
          }
          z();
        }
      }
      else
      {
        this.p = false;
      }
    }
  }
  
  private void z()
  {
    for (int i = 0; i < this.h.length; i++) {
      this.h[i] = true;
    }
    h(false);
  }
  
  private void a(cq facing)
  {
    if (this.o) {
      if (facing == cq.f) {
        for (int i = 0; i < 16; i++) {
          e(15, i);
        }
      } else if (facing == cq.e) {
        for (int j = 0; j < 16; j++) {
          e(0, j);
        }
      } else if (facing == cq.d) {
        for (int k = 0; k < 16; k++) {
          e(k, 15);
        }
      } else if (facing == cq.c) {
        for (int l = 0; l < 16; l++) {
          e(l, 0);
        }
      }
    }
  }
  
  private boolean e(int x, int z)
  {
    int i = g();
    boolean flag = false;
    boolean flag1 = false;
    cj.a blockpos$mutableblockpos = new cj.a((this.b << 4) + x, 0, (this.c << 4) + z);
    for (int j = i + 16 - 1; (j > this.j.K()) || ((j > 0) && (!flag1)); j--)
    {
      blockpos$mutableblockpos.c(blockpos$mutableblockpos.p(), j, blockpos$mutableblockpos.r());
      int k = b(blockpos$mutableblockpos);
      if ((k == 255) && (blockpos$mutableblockpos.q() < this.j.K())) {
        flag1 = true;
      }
      if ((!flag) && (k > 0)) {
        flag = true;
      } else if ((flag) && (k == 0) && (!this.j.w(blockpos$mutableblockpos))) {
        return false;
      }
    }
    for (int l = blockpos$mutableblockpos.q(); l > 0; l--)
    {
      blockpos$mutableblockpos.c(blockpos$mutableblockpos.p(), l, blockpos$mutableblockpos.r());
      if (a(blockpos$mutableblockpos).d() > 0) {
        this.j.w(blockpos$mutableblockpos);
      }
    }
    return true;
  }
  
  public boolean p()
  {
    return this.i;
  }
  
  public void c(boolean loaded)
  {
    this.i = loaded;
  }
  
  public aht q()
  {
    return this.j;
  }
  
  public int[] r()
  {
    return this.k;
  }
  
  public void a(int[] newHeightMap)
  {
    if (this.k.length != newHeightMap.length) {
      d.warn("Could not set level chunk heightmap, array length is " + newHeightMap.length + " instead of " + this.k.length);
    } else {
      for (int i = 0; i < this.k.length; i++) {
        this.k[i] = newHeightMap[i];
      }
    }
  }
  
  public Map<cj, apv> s()
  {
    return this.m;
  }
  
  public ny<rr>[] t()
  {
    return this.n;
  }
  
  public boolean u()
  {
    return this.o;
  }
  
  public void d(boolean terrainPopulated)
  {
    this.o = terrainPopulated;
  }
  
  public boolean v()
  {
    return this.p;
  }
  
  public void e(boolean lightPopulated)
  {
    this.p = lightPopulated;
  }
  
  public void f(boolean modified)
  {
    this.r = modified;
  }
  
  public void g(boolean hasEntitiesIn)
  {
    this.s = hasEntitiesIn;
  }
  
  public void b(long saveTime)
  {
    this.t = saveTime;
  }
  
  public int w()
  {
    return this.u;
  }
  
  public long x()
  {
    return this.v;
  }
  
  public void c(long newInhabitedTime)
  {
    this.v = newInhabitedTime;
  }
  
  public static enum a
  {
    private a() {}
  }
}
