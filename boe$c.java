 enum boe$c
{
  private final int g;
  private final int h;
  private final int i;
  private final int j;
  private static final c[] k;
  
  private boe$c(int p_i46234_3_, int p_i46234_4_, int p_i46234_5_, int p_i46234_6_)
  {
    this.g = p_i46234_3_;
    this.h = p_i46234_4_;
    this.i = p_i46234_5_;
    this.j = p_i46234_6_;
  }
  
  public static c a(cq p_178184_0_)
  {
    return k[p_178184_0_.a()];
  }
  
  static
  {
    k = new c[6];
    
    k[cq.a.a()] = a;
    k[cq.b.a()] = b;
    k[cq.c.a()] = c;
    k[cq.d.a()] = d;
    k[cq.e.a()] = e;
    k[cq.f.a()] = f;
  }
}
