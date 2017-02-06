public class lk
  extends ls
{
  private boolean c;
  private boolean d;
  private int e;
  private int f;
  
  public lk(aht ☃)
  {
    super(☃);
  }
  
  public void a()
  {
    super.a();
    this.f += 1;
    
    long ☃ = this.a.P();
    long ☃ = ☃ / 24000L + 1L;
    if ((!this.c) && (this.f > 20))
    {
      this.c = true;
      this.b.a.a(new gn(5, 0.0F));
    }
    this.d = (☃ > 120500L);
    if (this.d) {
      this.e += 1;
    }
    if (☃ % 24000L == 500L)
    {
      if (☃ <= 6L) {
        this.b.a(new fb("demo.day." + ☃, new Object[0]));
      }
    }
    else if (☃ == 1L)
    {
      if (☃ == 100L) {
        this.b.a.a(new gn(5, 101.0F));
      } else if (☃ == 175L) {
        this.b.a.a(new gn(5, 102.0F));
      } else if (☃ == 250L) {
        this.b.a.a(new gn(5, 103.0F));
      }
    }
    else if ((☃ == 5L) && 
      (☃ % 24000L == 22000L)) {
      this.b.a(new fb("demo.day.warning", new Object[0]));
    }
  }
  
  private void f()
  {
    if (this.e > 100)
    {
      this.b.a(new fb("demo.reminder", new Object[0]));
      this.e = 0;
    }
  }
  
  public void a(cj ☃, cq ☃)
  {
    if (this.d)
    {
      f();
      return;
    }
    super.a(☃, ☃);
  }
  
  public void a(cj ☃)
  {
    if (this.d) {
      return;
    }
    super.a(☃);
  }
  
  public boolean b(cj ☃)
  {
    if (this.d) {
      return false;
    }
    return super.b(☃);
  }
  
  public qo a(zj ☃, aht ☃, adq ☃, qm ☃)
  {
    if (this.d)
    {
      f();
      return qo.b;
    }
    return super.a(☃, ☃, ☃, ☃);
  }
  
  public qo a(zj ☃, aht ☃, adq ☃, qm ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (this.d)
    {
      f();
      return qo.b;
    }
    return super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
}
