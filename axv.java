public abstract class axv
{
  private long c;
  protected axv a;
  private long d;
  protected long b;
  
  public static axv[] a(long ☃, ahy ☃, String ☃)
  {
    axv ☃ = new axu(1L);
    ☃ = new axr(2000L, ☃);
    ☃ = new axl(1L, ☃);
    ☃ = new ayi(2001L, ☃);
    ☃ = new axl(2L, ☃);
    ☃ = new axl(50L, ☃);
    ☃ = new axl(70L, ☃);
    ☃ = new axy(2L, ☃);
    ☃ = new axn(2L, ☃);
    ☃ = new axl(3L, ☃);
    ☃ = new axk(2L, ☃, axk.a.a);
    ☃ = new axk(2L, ☃, axk.a.b);
    ☃ = new axk(3L, ☃, axk.a.c);
    ☃ = new ayi(2002L, ☃);
    ☃ = new ayi(2003L, ☃);
    ☃ = new axl(4L, ☃);
    ☃ = new axm(5L, ☃);
    ☃ = new axj(4L, ☃);
    ☃ = ayi.b(1000L, ☃, 0);
    
    atg ☃ = null;
    int ☃ = 4;
    int ☃ = ☃;
    if ((☃ == ahy.f) && (!☃.isEmpty()))
    {
      ☃ = atg.a.a(☃).b();
      ☃ = ☃.G;
      ☃ = ☃.H;
    }
    if (☃ == ahy.d) {
      ☃ = 6;
    }
    axv ☃ = ☃;
    ☃ = ayi.b(1000L, ☃, 0);
    ☃ = new axz(100L, ☃);
    
    axv ☃ = ☃;
    ☃ = new axp(200L, ☃, ☃, ☃);
    ☃ = ayi.b(1000L, ☃, 2);
    ☃ = new axo(1000L, ☃);
    axv ☃ = ☃;
    ☃ = ayi.b(1000L, ☃, 2);
    ☃ = new axx(1000L, ☃, ☃);
    
    ☃ = ayi.b(1000L, ☃, 2);
    ☃ = ayi.b(1000L, ☃, ☃);
    ☃ = new aya(1L, ☃);
    ☃ = new ayd(1000L, ☃);
    
    ☃ = new axw(1001L, ☃);
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      ☃ = new ayi(1000 + ☃, ☃);
      if (☃ == 0) {
        ☃ = new axl(3L, ☃);
      }
      if ((☃ == 1) || (☃ == 1)) {
        ☃ = new ayc(1000L, ☃);
      }
    }
    ☃ = new ayd(1000L, ☃);
    
    ☃ = new ayb(100L, ☃, ☃);
    
    axv ☃ = ☃;
    axv ☃ = new ayh(10L, ☃);
    
    ☃.a(☃);
    ☃.a(☃);
    
    return new axv[] { ☃, ☃, ☃ };
  }
  
  public axv(long ☃)
  {
    this.b = ☃;
    this.b *= (this.b * 6364136223846793005L + 1442695040888963407L);
    this.b += ☃;
    this.b *= (this.b * 6364136223846793005L + 1442695040888963407L);
    this.b += ☃;
    this.b *= (this.b * 6364136223846793005L + 1442695040888963407L);
    this.b += ☃;
  }
  
  public void a(long ☃)
  {
    this.c = ☃;
    if (this.a != null) {
      this.a.a(☃);
    }
    this.c *= (this.c * 6364136223846793005L + 1442695040888963407L);
    this.c += this.b;
    this.c *= (this.c * 6364136223846793005L + 1442695040888963407L);
    this.c += this.b;
    this.c *= (this.c * 6364136223846793005L + 1442695040888963407L);
    this.c += this.b;
  }
  
  public void a(long ☃, long ☃)
  {
    this.d = this.c;
    this.d *= (this.d * 6364136223846793005L + 1442695040888963407L);
    this.d += ☃;
    this.d *= (this.d * 6364136223846793005L + 1442695040888963407L);
    this.d += ☃;
    this.d *= (this.d * 6364136223846793005L + 1442695040888963407L);
    this.d += ☃;
    this.d *= (this.d * 6364136223846793005L + 1442695040888963407L);
    this.d += ☃;
  }
  
  protected int a(int ☃)
  {
    int ☃ = (int)((this.d >> 24) % ☃);
    if (☃ < 0) {
      ☃ += ☃;
    }
    this.d *= (this.d * 6364136223846793005L + 1442695040888963407L);
    this.d += this.c;
    return ☃;
  }
  
  public abstract int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  protected static boolean a(int ☃, int ☃)
  {
    if (☃ == ☃) {
      return true;
    }
    aig ☃ = aig.b(☃);
    aig ☃ = aig.b(☃);
    if ((☃ == null) || (☃ == null)) {
      return false;
    }
    if ((☃ == ail.N) || (☃ == ail.O)) {
      return (☃ == ail.N) || (☃ == ail.O);
    }
    return (☃ == ☃) || (☃.g() == ☃.g());
  }
  
  protected static boolean b(int ☃)
  {
    aig ☃ = aig.b(☃);
    return (☃ == ail.a) || (☃ == ail.z) || (☃ == ail.l);
  }
  
  protected int a(int... ☃)
  {
    return ☃[a(☃.length)];
  }
  
  protected int b(int ☃, int ☃, int ☃, int ☃)
  {
    if ((☃ == ☃) && (☃ == ☃)) {
      return ☃;
    }
    if ((☃ == ☃) && (☃ == ☃)) {
      return ☃;
    }
    if ((☃ == ☃) && (☃ == ☃)) {
      return ☃;
    }
    if ((☃ == ☃) && (☃ == ☃)) {
      return ☃;
    }
    if ((☃ == ☃) && (☃ != ☃)) {
      return ☃;
    }
    if ((☃ == ☃) && (☃ != ☃)) {
      return ☃;
    }
    if ((☃ == ☃) && (☃ != ☃)) {
      return ☃;
    }
    if ((☃ == ☃) && (☃ != ☃)) {
      return ☃;
    }
    if ((☃ == ☃) && (☃ != ☃)) {
      return ☃;
    }
    if ((☃ == ☃) && (☃ != ☃)) {
      return ☃;
    }
    return a(new int[] { ☃, ☃, ☃, ☃ });
  }
}
