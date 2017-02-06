class avy$v
{
  int a;
  v[] b = new v[6];
  boolean[] c = new boolean[6];
  boolean d;
  boolean e;
  int f;
  
  public avy$v(int ☃)
  {
    this.a = ☃;
  }
  
  public void a(cq ☃, v ☃)
  {
    this.b[☃.a()] = ☃;
    ☃.b[☃.d().a()] = this;
  }
  
  public void a()
  {
    for (int ☃ = 0; ☃ < 6; ☃++) {
      this.c[☃] = (this.b[☃] != null ? 1 : false);
    }
  }
  
  public boolean a(int ☃)
  {
    if (this.e) {
      return true;
    }
    this.f = ☃;
    for (int ☃ = 0; ☃ < 6; ☃++) {
      if ((this.b[☃] != null) && (this.c[☃] != 0) && 
        (this.b[☃].f != ☃) && (this.b[☃].a(☃))) {
        return true;
      }
    }
    return false;
  }
  
  public boolean b()
  {
    return this.a >= 75;
  }
  
  public int c()
  {
    int ☃ = 0;
    for (int ☃ = 0; ☃ < 6; ☃++) {
      if (this.c[☃] != 0) {
        ☃++;
      }
    }
    return ☃;
  }
}
