public enum bnf
{
  private static final bnf[] g;
  private final bnf.b[] h;
  
  public static final class a
  {
    public static final int a = cq.d.a();
    public static final int b = cq.b.a();
    public static final int c = cq.f.a();
    public static final int d = cq.c.a();
    public static final int e = cq.a.a();
    public static final int f = cq.e.a();
  }
  
  static
  {
    g = new bnf[6];
    
    g[bnf.a.e] = a;
    g[bnf.a.b] = b;
    g[bnf.a.d] = c;
    g[bnf.a.a] = d;
    g[bnf.a.f] = e;
    g[bnf.a.c] = f;
  }
  
  public static bnf a(cq ☃)
  {
    return g[☃.a()];
  }
  
  private bnf(bnf.b... ☃)
  {
    this.h = ☃;
  }
  
  public bnf.b a(int ☃)
  {
    return this.h[☃];
  }
  
  public static class b
  {
    public final int a;
    public final int b;
    public final int c;
    
    private b(int ☃, int ☃, int ☃)
    {
      this.a = ☃;
      this.b = ☃;
      this.c = ☃;
    }
  }
}
