public enum wm
{
  private final fb f;
  private final kk g;
  private final nf h;
  private final nf i;
  private final nf j;
  private kk k;
  
  private wm(String ☃, String ☃, nf ☃, nf ☃, nf ☃, kk ☃)
  {
    this.k = ☃;
    this.f = new fb("entity." + ☃ + ".name", new Object[0]);
    this.g = new kk("textures/entity/horse/" + ☃ + ".png");
    this.h = ☃;
    this.i = ☃;
    this.j = ☃;
  }
  
  public nf a()
  {
    return this.i;
  }
  
  public nf b()
  {
    return this.h;
  }
  
  public nf c()
  {
    return this.j;
  }
  
  public fb d()
  {
    return this.f;
  }
  
  public kk e()
  {
    return this.g;
  }
  
  public boolean f()
  {
    return (this == b) || (this == c);
  }
  
  public boolean g()
  {
    return (this == b) || (this == c);
  }
  
  public boolean h()
  {
    return (this == d) || (this == e);
  }
  
  public boolean i()
  {
    return (!h()) && (this != c);
  }
  
  public boolean j()
  {
    return this == a;
  }
  
  public int k()
  {
    return ordinal();
  }
  
  public static wm a(int ☃)
  {
    return values()[☃];
  }
  
  public kk l()
  {
    return this.k;
  }
}
