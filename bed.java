import java.util.List;

public class bed
  extends bfb
{
  private static final bch.a[] a = { bch.a.p, bch.a.q, bch.a.r, bch.a.s, bch.a.t, bch.a.z, bch.a.B, bch.a.C, bch.a.A, bch.a.F };
  private final bfb f;
  private final bch g;
  private String h;
  
  public bed(bfb ☃, bch ☃)
  {
    this.f = ☃;
    this.g = ☃;
  }
  
  public void b()
  {
    int ☃ = 0;
    this.h = bwo.a("options.chat.title", new Object[0]);
    for (bch.a ☃ : a)
    {
      if (☃.a()) {
        this.n.add(new bdr(☃.c(), this.l / 2 - 155 + ☃ % 2 * 160, this.m / 6 + 24 * (☃ >> 1), ☃));
      } else {
        this.n.add(new bdm(☃.c(), this.l / 2 - 155 + ☃ % 2 * 160, this.m / 6 + 24 * (☃ >> 1), ☃, this.g.c(☃)));
      }
      ☃++;
    }
    this.n.add(new bcz(200, this.l / 2 - 100, this.m / 6 + 120, bwo.a("gui.done", new Object[0])));
  }
  
  protected void a(bcz ☃)
  {
    if (!☃.l) {
      return;
    }
    if ((☃.k < 100) && ((☃ instanceof bdm)))
    {
      this.g.a(((bdm)☃).c(), 1);
      ☃.j = this.g.c(bch.a.a(☃.k));
    }
    if (☃.k == 200)
    {
      this.j.u.b();
      this.j.a(this.f);
    }
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    c();
    a(this.q, this.h, this.l / 2, 20, 16777215);
    
    super.a(☃, ☃, ☃);
  }
}
