import java.util.List;

public class bfr
  extends bfb
{
  private static final bch.a[] h = { bch.a.a, bch.a.b, bch.a.y };
  private bfb i;
  protected String a = "Controls";
  private bch r;
  public bcc f = null;
  public long g;
  private bfq s;
  private bcz t;
  
  public bfr(bfb ☃, bch ☃)
  {
    this.i = ☃;
    this.r = ☃;
  }
  
  public void b()
  {
    this.s = new bfq(this, this.j);
    this.n.add(new bcz(200, this.l / 2 - 155, this.m - 29, 150, 20, bwo.a("gui.done", new Object[0])));
    this.n.add(this.t = new bcz(201, this.l / 2 - 155 + 160, this.m - 29, 150, 20, bwo.a("controls.resetAll", new Object[0])));
    this.a = bwo.a("controls.title", new Object[0]);
    
    int ☃ = 0;
    for (bch.a ☃ : h)
    {
      if (☃.a()) {
        this.n.add(new bdr(☃.c(), this.l / 2 - 155 + ☃ % 2 * 160, 18 + 24 * (☃ >> 1), ☃));
      } else {
        this.n.add(new bdm(☃.c(), this.l / 2 - 155 + ☃ % 2 * 160, 18 + 24 * (☃ >> 1), ☃, this.r.c(☃)));
      }
      ☃++;
    }
  }
  
  public void k()
  {
    super.k();
    this.s.p();
  }
  
  protected void a(bcz ☃)
  {
    if (☃.k == 200)
    {
      this.j.a(this.i);
    }
    else if (☃.k == 201)
    {
      for (bcc ☃ : this.j.u.al) {
        ☃.b(☃.i());
      }
      bcc.c();
    }
    else if ((☃.k < 100) && ((☃ instanceof bdm)))
    {
      this.r.a(((bdm)☃).c(), 1);
      ☃.j = this.r.c(bch.a.a(☃.k));
    }
  }
  
  protected void a(int ☃, int ☃, int ☃)
  {
    if (this.f != null)
    {
      this.r.a(this.f, -100 + ☃);
      this.f = null;
      bcc.c();
    }
    else if ((☃ != 0) || (!this.s.b(☃, ☃, ☃)))
    {
      super.a(☃, ☃, ☃);
    }
  }
  
  protected void b(int ☃, int ☃, int ☃)
  {
    if ((☃ != 0) || (!this.s.c(☃, ☃, ☃))) {
      super.b(☃, ☃, ☃);
    }
  }
  
  protected void a(char ☃, int ☃)
  {
    if (this.f != null)
    {
      if (☃ == 1) {
        this.r.a(this.f, 0);
      } else if (☃ != 0) {
        this.r.a(this.f, ☃);
      } else if (☃ > 0) {
        this.r.a(this.f, ☃ + 'Ā');
      }
      this.f = null;
      this.g = bcf.I();
      bcc.c();
    }
    else
    {
      super.a(☃, ☃);
    }
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    c();
    
    this.s.a(☃, ☃, ☃);
    a(this.q, this.a, this.l / 2, 8, 16777215);
    
    boolean ☃ = true;
    for (bcc ☃ : this.r.al) {
      if (☃.j() != ☃.i())
      {
        ☃ = false;
        break;
      }
    }
    this.t.l = (!☃);
    
    super.a(☃, ☃, ☃);
  }
}
