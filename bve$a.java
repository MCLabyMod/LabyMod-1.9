public class bve$a
  implements Comparable<a>
{
  private final bvh a;
  private final int b;
  private final int c;
  private final int d;
  private boolean e;
  private float f = 1.0F;
  
  public bve$a(bvh theTextureIn, int mipmapLevelHolderIn)
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
