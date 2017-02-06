public class bfq$b
  implements bdl.a
{
  private final bcc b;
  private final String c;
  private final bcz d;
  private final bcz e;
  
  private bfq$b(bfq parambfq, bcc ☃)
  {
    this.b = ☃;
    this.c = bwo.a(☃.h(), new Object[0]);
    this.d = new bcz(0, 0, 0, 75, 20, bwo.a(☃.h(), new Object[0]));
    this.e = new bcz(0, 0, 0, 50, 20, bwo.a("controls.reset", new Object[0]));
  }
  
  public void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    boolean ☃ = bfq.b(this.a).f == this.b;
    bfq.a(this.a).k.a(this.c, ☃ + 90 - bfq.c(this.a), ☃ + ☃ / 2 - bfq.a(this.a).k.a / 2, 16777215);
    
    this.e.h = (☃ + 190);
    this.e.i = ☃;
    this.e.l = (this.b.j() != this.b.i());
    this.e.a(bfq.a(this.a), ☃, ☃);
    
    this.d.h = (☃ + 105);
    this.d.i = ☃;
    this.d.j = bch.c(this.b.j());
    
    boolean ☃ = false;
    if (this.b.j() != 0) {
      for (bcc ☃ : bfq.a(this.a).u.al) {
        if ((☃ != this.b) && (☃.j() == this.b.j()))
        {
          ☃ = true;
          break;
        }
      }
    }
    if (☃) {
      this.d.j = (a.p + "> " + a.o + this.d.j + a.p + " <");
    } else if (☃) {
      this.d.j = (a.m + this.d.j);
    }
    this.d.a(bfq.a(this.a), ☃, ☃);
  }
  
  public boolean a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    if (this.d.c(bfq.a(this.a), ☃, ☃))
    {
      bfq.b(this.a).f = this.b;
      return true;
    }
    if (this.e.c(bfq.a(this.a), ☃, ☃))
    {
      bfq.a(this.a).u.a(this.b, this.b.i());
      bcc.c();
      return true;
    }
    return false;
  }
  
  public void b(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    this.d.a(☃, ☃);
    this.e.a(☃, ☃);
  }
  
  public void a(int ☃, int ☃, int ☃) {}
}
