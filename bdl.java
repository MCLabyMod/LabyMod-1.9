public abstract class bdl
  extends bdq
{
  public bdl(bcf ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    super(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  protected void a(int ☃, boolean ☃, int ☃, int ☃) {}
  
  protected boolean a(int ☃)
  {
    return false;
  }
  
  protected void a() {}
  
  protected void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    b(☃).a(☃, ☃, ☃, c(), ☃, ☃, ☃, (g(☃)) && (c(☃, ☃) == ☃));
  }
  
  protected void a(int ☃, int ☃, int ☃)
  {
    b(☃).a(☃, ☃, ☃);
  }
  
  public boolean b(int ☃, int ☃, int ☃)
  {
    if (g(☃))
    {
      int ☃ = c(☃, ☃);
      if (☃ >= 0)
      {
        int ☃ = this.g + this.b / 2 - c() / 2 + 2;
        int ☃ = this.d + 4 - n() + ☃ * this.h + this.t;
        int ☃ = ☃ - ☃;
        int ☃ = ☃ - ☃;
        if (b(☃).a(☃, ☃, ☃, ☃, ☃, ☃))
        {
          d(false);
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean c(int ☃, int ☃, int ☃)
  {
    for (int ☃ = 0; ☃ < b(); ☃++)
    {
      int ☃ = this.g + this.b / 2 - c() / 2 + 2;
      int ☃ = this.d + 4 - n() + ☃ * this.h + this.t;
      int ☃ = ☃ - ☃;
      int ☃ = ☃ - ☃;
      b(☃).b(☃, ☃, ☃, ☃, ☃, ☃);
    }
    d(true);
    return false;
  }
  
  public abstract bdl.a b(int paramInt);
  
  public static abstract interface a
  {
    public abstract void a(int paramInt1, int paramInt2, int paramInt3);
    
    public abstract void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean);
    
    public abstract boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
    
    public abstract void b(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
  }
}
