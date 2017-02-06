import java.util.List;

public class bem
  extends bfb
  implements beg
{
  private int a;
  private final eu f;
  
  public bem(eu ☃)
  {
    this.f = ☃;
  }
  
  public void b()
  {
    this.n.clear();
    this.a = 0;
    if (this.j.f.T().s())
    {
      this.n.add(new bcz(0, this.l / 2 - 100, this.m / 4 + 72, bwo.a("deathScreen.spectate", new Object[0])));
      this.n.add(new bcz(1, this.l / 2 - 100, this.m / 4 + 96, bwo.a("deathScreen." + (this.j.D() ? "deleteWorld" : "leaveServer"), new Object[0])));
    }
    else
    {
      this.n.add(new bcz(0, this.l / 2 - 100, this.m / 4 + 72, bwo.a("deathScreen.respawn", new Object[0])));
      this.n.add(new bcz(1, this.l / 2 - 100, this.m / 4 + 96, bwo.a("deathScreen.titleScreen", new Object[0])));
      if (this.j.K() == null) {
        ((bcz)this.n.get(1)).l = false;
      }
    }
    for (bcz ☃ : this.n) {
      ☃.l = false;
    }
  }
  
  protected void a(char ☃, int ☃) {}
  
  protected void a(bcz ☃)
  {
    switch (☃.k)
    {
    case 0: 
      this.j.h.cI();
      this.j.a(null);
      break;
    case 1: 
      if (this.j.f.T().s())
      {
        this.j.a(new bfi());
      }
      else
      {
        beh ☃ = new beh(this, bwo.a("deathScreen.quit.confirm", new Object[0]), "", bwo.a("deathScreen.titleScreen", new Object[0]), bwo.a("deathScreen.respawn", new Object[0]), 0);
        this.j.a(☃);
        ☃.b(20);
      }
      break;
    }
  }
  
  public void a(boolean ☃, int ☃)
  {
    if (☃)
    {
      if (this.j.f != null) {
        this.j.f.M();
      }
      this.j.a(null);
      this.j.a(new bfi());
    }
    else
    {
      this.j.h.cI();
      this.j.a(null);
    }
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    boolean ☃ = this.j.f.T().s();
    
    a(0, 0, this.l, this.m, 1615855616, -1602211792);
    
    bni.G();
    bni.b(2.0F, 2.0F, 2.0F);
    a(this.q, ☃ ? bwo.a("deathScreen.title.hardcore", new Object[0]) : bwo.a("deathScreen.title", new Object[0]), this.l / 2 / 2, 30, 16777215);
    bni.H();
    if (this.f != null) {
      a(this.q, this.f.d(), this.l / 2, 85, 16777215);
    }
    a(this.q, bwo.a("deathScreen.score", new Object[0]) + ": " + a.o + this.j.h.cF(), this.l / 2, 100, 16777215);
    if ((this.f != null) && (☃ > 85) && (☃ < 85 + this.q.a))
    {
      eu ☃ = b(☃);
      if ((☃ != null) && (☃.b().i() != null)) {
        a(☃, ☃, ☃);
      }
    }
    super.a(☃, ☃, ☃);
  }
  
  public eu b(int ☃)
  {
    if (this.f == null) {
      return null;
    }
    int ☃ = this.j.k.a(this.f.d());
    int ☃ = this.l / 2 - ☃ / 2;
    int ☃ = this.l / 2 + ☃ / 2;
    int ☃ = ☃;
    if ((☃ < ☃) || (☃ > ☃)) {
      return null;
    }
    for (eu ☃ : this.f)
    {
      ☃ += this.j.k.a(bdb.a(☃.e(), false));
      if (☃ > ☃) {
        return ☃;
      }
    }
    return null;
  }
  
  public boolean d()
  {
    return false;
  }
  
  public void e()
  {
    super.e();
    
    this.a += 1;
    if (this.a == 20) {
      for (bcz ☃ : this.n) {
        ☃.l = true;
      }
    }
  }
}
