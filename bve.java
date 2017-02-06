import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class bve
{
  private final int a;
  private final Set<bve.a> b = Sets.newHashSetWithExpectedSize(256);
  private final List<bve.b> c = Lists.newArrayListWithCapacity(256);
  private int d;
  private int e;
  private final int f;
  private final int g;
  private final int h;
  
  public bve(int maxWidthIn, int maxHeightIn, int maxTileDimensionIn, int mipmapLevelStitcherIn)
  {
    this.a = mipmapLevelStitcherIn;
    this.f = maxWidthIn;
    this.g = maxHeightIn;
    this.h = maxTileDimensionIn;
  }
  
  public int a()
  {
    return this.d;
  }
  
  public int b()
  {
    return this.e;
  }
  
  public void a(bvh textureAtlas)
  {
    bve.a stitcher$holder = new bve.a(textureAtlas, this.a);
    if (this.h > 0) {
      stitcher$holder.a(this.h);
    }
    this.b.add(stitcher$holder);
  }
  
  public void c()
  {
    bve.a[] astitcher$holder = (bve.a[])this.b.toArray(new bve.a[this.b.size()]);
    Arrays.sort((Object[])astitcher$holder);
    for (bve.a stitcher$holder : astitcher$holder) {
      if (!a(stitcher$holder))
      {
        String s = String.format("Unable to fit: %s, size: %dx%d, atlas: %dx%d, atlasMax: %dx%d - Maybe try a lower resolution resourcepack?", new Object[] { stitcher$holder.a().i(), Integer.valueOf(stitcher$holder.a().c()), Integer.valueOf(stitcher$holder.a().d()), Integer.valueOf(this.d), Integer.valueOf(this.e), Integer.valueOf(this.f), Integer.valueOf(this.g) });
        
        throw new bvf(stitcher$holder, s);
      }
    }
    this.d = on.c(this.d);
    this.e = on.c(this.e);
  }
  
  public List<bvh> d()
  {
    List<bve.b> list = Lists.newArrayList();
    for (bve.b stitcher$slot : this.c) {
      stitcher$slot.a(list);
    }
    List<bvh> list1 = Lists.newArrayList();
    for (bve.b stitcher$slot1 : list)
    {
      bve.a stitcher$holder = stitcher$slot1.a();
      bvh textureatlassprite = stitcher$holder.a();
      textureatlassprite.a(this.d, this.e, stitcher$slot1.b(), stitcher$slot1.c(), stitcher$holder.e());
      list1.add(textureatlassprite);
    }
    return list1;
  }
  
  private static int b(int p_147969_0_, int p_147969_1_)
  {
    return (p_147969_0_ >> p_147969_1_) + ((p_147969_0_ & (1 << p_147969_1_) - 1) == 0 ? 0 : 1) << p_147969_1_;
  }
  
  private boolean a(bve.a p_94310_1_)
  {
    bvh textureatlassprite = p_94310_1_.a();
    boolean flag = textureatlassprite.c() != textureatlassprite.d();
    for (int i = 0; i < this.c.size(); i++)
    {
      if (((bve.b)this.c.get(i)).a(p_94310_1_)) {
        return true;
      }
      if (flag)
      {
        p_94310_1_.d();
        if (((bve.b)this.c.get(i)).a(p_94310_1_)) {
          return true;
        }
        p_94310_1_.d();
      }
    }
    return b(p_94310_1_);
  }
  
  private boolean b(bve.a p_94311_1_)
  {
    int i = Math.min(p_94311_1_.b(), p_94311_1_.c());
    int j = Math.max(p_94311_1_.b(), p_94311_1_.c());
    int k = on.c(this.d);
    int l = on.c(this.e);
    int i1 = on.c(this.d + i);
    int j1 = on.c(this.e + i);
    boolean flag1 = i1 <= this.f;
    boolean flag2 = j1 <= this.g;
    if ((!flag1) && (!flag2)) {
      return false;
    }
    boolean flag3 = (flag1) && (k != i1);
    boolean flag4 = (flag2) && (l != j1);
    boolean flag;
    boolean flag;
    if ((flag3 ^ flag4)) {
      flag = flag3;
    } else {
      flag = (flag1) && (k <= l);
    }
    bve.b stitcher$slot;
    if (flag)
    {
      if (p_94311_1_.b() > p_94311_1_.c()) {
        p_94311_1_.d();
      }
      if (this.e == 0) {
        this.e = p_94311_1_.c();
      }
      bve.b stitcher$slot = new bve.b(this.d, 0, p_94311_1_.b(), this.e);
      this.d += p_94311_1_.b();
    }
    else
    {
      stitcher$slot = new bve.b(0, this.e, this.d, p_94311_1_.c());
      this.e += p_94311_1_.c();
    }
    stitcher$slot.a(p_94311_1_);
    this.c.add(stitcher$slot);
    return true;
  }
  
  public static class a
    implements Comparable<a>
  {
    private final bvh a;
    private final int b;
    private final int c;
    private final int d;
    private boolean e;
    private float f = 1.0F;
    
    public a(bvh theTextureIn, int mipmapLevelHolderIn)
    {
      this.a = theTextureIn;
      this.b = theTextureIn.c();
      this.c = theTextureIn.d();
      this.d = mipmapLevelHolderIn;
      this.e = (bve.a(this.c, mipmapLevelHolderIn) > bve.a(this.b, mipmapLevelHolderIn));
    }
    
    public bvh a()
    {
      return this.a;
    }
    
    public int b()
    {
      return this.e ? bve.a((int)(this.c * this.f), this.d) : bve.a((int)(this.b * this.f), this.d);
    }
    
    public int c()
    {
      return this.e ? bve.a((int)(this.b * this.f), this.d) : bve.a((int)(this.c * this.f), this.d);
    }
    
    public void d()
    {
      this.e = (!this.e);
    }
    
    public boolean e()
    {
      return this.e;
    }
    
    public void a(int p_94196_1_)
    {
      if ((this.b > p_94196_1_) && (this.c > p_94196_1_)) {
        this.f = (p_94196_1_ / Math.min(this.b, this.c));
      }
    }
    
    public String toString()
    {
      return "Holder{width=" + this.b + ", height=" + this.c + ", name=" + this.a.i() + '}';
    }
    
    public int a(a p_compareTo_1_)
    {
      int i;
      int i;
      if (c() == p_compareTo_1_.c())
      {
        if (b() == p_compareTo_1_.b())
        {
          if (this.a.i() == null) {
            return p_compareTo_1_.a.i() == null ? 0 : -1;
          }
          return this.a.i().compareTo(p_compareTo_1_.a.i());
        }
        i = b() < p_compareTo_1_.b() ? 1 : -1;
      }
      else
      {
        i = c() < p_compareTo_1_.c() ? 1 : -1;
      }
      return i;
    }
  }
  
  public static class b
  {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private List<b> e;
    private bve.a f;
    
    public b(int originXIn, int originYIn, int widthIn, int heightIn)
    {
      this.a = originXIn;
      this.b = originYIn;
      this.c = widthIn;
      this.d = heightIn;
    }
    
    public bve.a a()
    {
      return this.f;
    }
    
    public int b()
    {
      return this.a;
    }
    
    public int c()
    {
      return this.b;
    }
    
    public boolean a(bve.a holderIn)
    {
      if (this.f != null) {
        return false;
      }
      int i = holderIn.b();
      int j = holderIn.c();
      if ((i <= this.c) && (j <= this.d))
      {
        if ((i == this.c) && (j == this.d))
        {
          this.f = holderIn;
          return true;
        }
        if (this.e == null)
        {
          this.e = Lists.newArrayListWithCapacity(1);
          this.e.add(new b(this.a, this.b, i, j));
          int k = this.c - i;
          int l = this.d - j;
          if ((l > 0) && (k > 0))
          {
            int i1 = Math.max(this.d, k);
            int j1 = Math.max(this.c, l);
            if (i1 >= j1)
            {
              this.e.add(new b(this.a, this.b + j, i, l));
              this.e.add(new b(this.a + i, this.b, k, this.d));
            }
            else
            {
              this.e.add(new b(this.a + i, this.b, k, j));
              this.e.add(new b(this.a, this.b + j, this.c, l));
            }
          }
          else if (k == 0)
          {
            this.e.add(new b(this.a, this.b + j, i, l));
          }
          else if (l == 0)
          {
            this.e.add(new b(this.a + i, this.b, k, j));
          }
        }
        for (b stitcher$slot : this.e) {
          if (stitcher$slot.a(holderIn)) {
            return true;
          }
        }
        return false;
      }
      return false;
    }
    
    public void a(List<b> p_94184_1_)
    {
      if (this.f != null) {
        p_94184_1_.add(this);
      } else if (this.e != null) {
        for (b stitcher$slot : this.e) {
          stitcher$slot.a(p_94184_1_);
        }
      }
    }
    
    public String toString()
    {
      return "Slot{originX=" + this.a + ", originY=" + this.b + ", width=" + this.c + ", height=" + this.d + ", texture=" + this.f + ", subSlots=" + this.e + '}';
    }
  }
}
