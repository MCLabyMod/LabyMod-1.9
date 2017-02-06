public enum boe$a
{
  protected final cq[] g;
  protected final float h;
  protected final boolean i;
  protected final boe.d[] j;
  protected final boe.d[] k;
  protected final boe.d[] l;
  protected final boe.d[] m;
  private static final a[] n;
  
  private boe$a(cq[] p_i46236_3_, float p_i46236_4_, boolean p_i46236_5_, boe.d[] p_i46236_6_, boe.d[] p_i46236_7_, boe.d[] p_i46236_8_, boe.d[] p_i46236_9_)
  {
    this.g = p_i46236_3_;
    this.h = p_i46236_4_;
    this.i = p_i46236_5_;
    this.j = p_i46236_6_;
    this.k = p_i46236_7_;
    this.l = p_i46236_8_;
    this.m = p_i46236_9_;
  }
  
  public static a a(cq p_178273_0_)
  {
    return n[p_178273_0_.a()];
  }
  
  static
  {
    n = new a[6];
    
    n[cq.a.a()] = a;
    n[cq.b.a()] = b;
    n[cq.c.a()] = c;
    n[cq.d.a()] = d;
    n[cq.e.a()] = e;
    n[cq.f.a()] = f;
  }
}
