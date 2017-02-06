import java.util.Random;

public class awa$a
  extends awa.d
{
  private boolean[] e = new boolean[4];
  
  public awa$a() {}
  
  public awa$a(Random ☃, int ☃, int ☃)
  {
    super(☃, ☃, 64, ☃, 21, 15, 21);
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    ☃.a("hasPlacedChest0", this.e[0]);
    ☃.a("hasPlacedChest1", this.e[1]);
    ☃.a("hasPlacedChest2", this.e[2]);
    ☃.a("hasPlacedChest3", this.e[3]);
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    this.e[0] = ☃.p("hasPlacedChest0");
    this.e[1] = ☃.p("hasPlacedChest1");
    this.e[2] = ☃.p("hasPlacedChest2");
    this.e[3] = ☃.p("hasPlacedChest3");
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    a(☃, ☃, 0, -4, 0, this.a - 1, 0, this.c - 1, aju.A.u(), aju.A.u(), false);
    for (int ☃ = 1; ☃ <= 9; ☃++)
    {
      a(☃, ☃, ☃, ☃, ☃, this.a - 1 - ☃, ☃, this.c - 1 - ☃, aju.A.u(), aju.A.u(), false);
      a(☃, ☃, ☃ + 1, ☃, ☃ + 1, this.a - 2 - ☃, ☃, this.c - 2 - ☃, aju.a.u(), aju.a.u(), false);
    }
    for (int ☃ = 0; ☃ < this.a; ☃++) {
      for (int ☃ = 0; ☃ < this.c; ☃++)
      {
        int ☃ = -5;
        b(☃, aju.A.u(), ☃, ☃, ☃, ☃);
      }
    }
    arc ☃ = aju.bO.u().a(aot.a, cq.c);
    arc ☃ = aju.bO.u().a(aot.a, cq.d);
    arc ☃ = aju.bO.u().a(aot.a, cq.f);
    arc ☃ = aju.bO.u().a(aot.a, cq.e);
    int ☃ = (act.b.b() ^ 0xFFFFFFFF) & 0xF;
    int ☃ = (act.l.b() ^ 0xFFFFFFFF) & 0xF;
    
    a(☃, ☃, 0, 0, 0, 4, 9, 4, aju.A.u(), aju.a.u(), false);
    a(☃, ☃, 1, 10, 1, 3, 10, 3, aju.A.u(), aju.A.u(), false);
    a(☃, ☃, 2, 10, 0, ☃);
    a(☃, ☃, 2, 10, 4, ☃);
    a(☃, ☃, 0, 10, 2, ☃);
    a(☃, ☃, 4, 10, 2, ☃);
    a(☃, ☃, this.a - 5, 0, 0, this.a - 1, 9, 4, aju.A.u(), aju.a.u(), false);
    a(☃, ☃, this.a - 4, 10, 1, this.a - 2, 10, 3, aju.A.u(), aju.A.u(), false);
    a(☃, ☃, this.a - 3, 10, 0, ☃);
    a(☃, ☃, this.a - 3, 10, 4, ☃);
    a(☃, ☃, this.a - 5, 10, 2, ☃);
    a(☃, ☃, this.a - 1, 10, 2, ☃);
    
    a(☃, ☃, 8, 0, 0, 12, 4, 4, aju.A.u(), aju.a.u(), false);
    a(☃, ☃, 9, 1, 0, 11, 3, 4, aju.a.u(), aju.a.u(), false);
    a(☃, aju.A.a(aog.a.c.a()), 9, 1, 1, ☃);
    a(☃, aju.A.a(aog.a.c.a()), 9, 2, 1, ☃);
    a(☃, aju.A.a(aog.a.c.a()), 9, 3, 1, ☃);
    a(☃, aju.A.a(aog.a.c.a()), 10, 3, 1, ☃);
    a(☃, aju.A.a(aog.a.c.a()), 11, 3, 1, ☃);
    a(☃, aju.A.a(aog.a.c.a()), 11, 2, 1, ☃);
    a(☃, aju.A.a(aog.a.c.a()), 11, 1, 1, ☃);
    
    a(☃, ☃, 4, 1, 1, 8, 3, 3, aju.A.u(), aju.a.u(), false);
    a(☃, ☃, 4, 1, 2, 8, 2, 2, aju.a.u(), aju.a.u(), false);
    a(☃, ☃, 12, 1, 1, 16, 3, 3, aju.A.u(), aju.a.u(), false);
    a(☃, ☃, 12, 1, 2, 16, 2, 2, aju.a.u(), aju.a.u(), false);
    
    a(☃, ☃, 5, 4, 5, this.a - 6, 4, this.c - 6, aju.A.u(), aju.A.u(), false);
    a(☃, ☃, 9, 4, 9, 11, 4, 11, aju.a.u(), aju.a.u(), false);
    a(☃, ☃, 8, 1, 8, 8, 3, 8, aju.A.a(aog.a.c.a()), aju.A.a(aog.a.c.a()), false);
    a(☃, ☃, 12, 1, 8, 12, 3, 8, aju.A.a(aog.a.c.a()), aju.A.a(aog.a.c.a()), false);
    a(☃, ☃, 8, 1, 12, 8, 3, 12, aju.A.a(aog.a.c.a()), aju.A.a(aog.a.c.a()), false);
    a(☃, ☃, 12, 1, 12, 12, 3, 12, aju.A.a(aog.a.c.a()), aju.A.a(aog.a.c.a()), false);
    
    a(☃, ☃, 1, 1, 5, 4, 4, 11, aju.A.u(), aju.A.u(), false);
    a(☃, ☃, this.a - 5, 1, 5, this.a - 2, 4, 11, aju.A.u(), aju.A.u(), false);
    a(☃, ☃, 6, 7, 9, 6, 7, 11, aju.A.u(), aju.A.u(), false);
    a(☃, ☃, this.a - 7, 7, 9, this.a - 7, 7, 11, aju.A.u(), aju.A.u(), false);
    a(☃, ☃, 5, 5, 9, 5, 7, 11, aju.A.a(aog.a.c.a()), aju.A.a(aog.a.c.a()), false);
    a(☃, ☃, this.a - 6, 5, 9, this.a - 6, 7, 11, aju.A.a(aog.a.c.a()), aju.A.a(aog.a.c.a()), false);
    a(☃, aju.a.u(), 5, 5, 10, ☃);
    a(☃, aju.a.u(), 5, 6, 10, ☃);
    a(☃, aju.a.u(), 6, 6, 10, ☃);
    a(☃, aju.a.u(), this.a - 6, 5, 10, ☃);
    a(☃, aju.a.u(), this.a - 6, 6, 10, ☃);
    a(☃, aju.a.u(), this.a - 7, 6, 10, ☃);
    
    a(☃, ☃, 2, 4, 4, 2, 6, 4, aju.a.u(), aju.a.u(), false);
    a(☃, ☃, this.a - 3, 4, 4, this.a - 3, 6, 4, aju.a.u(), aju.a.u(), false);
    a(☃, ☃, 2, 4, 5, ☃);
    a(☃, ☃, 2, 3, 4, ☃);
    a(☃, ☃, this.a - 3, 4, 5, ☃);
    a(☃, ☃, this.a - 3, 3, 4, ☃);
    a(☃, ☃, 1, 1, 3, 2, 2, 3, aju.A.u(), aju.A.u(), false);
    a(☃, ☃, this.a - 3, 1, 3, this.a - 2, 2, 3, aju.A.u(), aju.A.u(), false);
    a(☃, aju.A.u(), 1, 1, 2, ☃);
    a(☃, aju.A.u(), this.a - 2, 1, 2, ☃);
    a(☃, aju.U.a(apa.a.b.a()), 1, 2, 2, ☃);
    a(☃, aju.U.a(apa.a.b.a()), this.a - 2, 2, 2, ☃);
    a(☃, ☃, 2, 1, 2, ☃);
    a(☃, ☃, this.a - 3, 1, 2, ☃);
    
    a(☃, ☃, 4, 3, 5, 4, 3, 18, aju.A.u(), aju.A.u(), false);
    a(☃, ☃, this.a - 5, 3, 5, this.a - 5, 3, 17, aju.A.u(), aju.A.u(), false);
    a(☃, ☃, 3, 1, 5, 4, 2, 16, aju.a.u(), aju.a.u(), false);
    a(☃, ☃, this.a - 6, 1, 5, this.a - 5, 2, 16, aju.a.u(), aju.a.u(), false);
    for (int ☃ = 5; ☃ <= 17; ☃ += 2)
    {
      a(☃, aju.A.a(aog.a.c.a()), 4, 1, ☃, ☃);
      a(☃, aju.A.a(aog.a.b.a()), 4, 2, ☃, ☃);
      a(☃, aju.A.a(aog.a.c.a()), this.a - 5, 1, ☃, ☃);
      a(☃, aju.A.a(aog.a.b.a()), this.a - 5, 2, ☃, ☃);
    }
    a(☃, aju.cu.a(☃), 10, 0, 7, ☃);
    a(☃, aju.cu.a(☃), 10, 0, 8, ☃);
    a(☃, aju.cu.a(☃), 9, 0, 9, ☃);
    a(☃, aju.cu.a(☃), 11, 0, 9, ☃);
    a(☃, aju.cu.a(☃), 8, 0, 10, ☃);
    a(☃, aju.cu.a(☃), 12, 0, 10, ☃);
    a(☃, aju.cu.a(☃), 7, 0, 10, ☃);
    a(☃, aju.cu.a(☃), 13, 0, 10, ☃);
    a(☃, aju.cu.a(☃), 9, 0, 11, ☃);
    a(☃, aju.cu.a(☃), 11, 0, 11, ☃);
    a(☃, aju.cu.a(☃), 10, 0, 12, ☃);
    a(☃, aju.cu.a(☃), 10, 0, 13, ☃);
    a(☃, aju.cu.a(☃), 10, 0, 10, ☃);
    for (int ☃ = 0; ☃ <= this.a - 1; ☃ += this.a - 1)
    {
      a(☃, aju.A.a(aog.a.c.a()), ☃, 2, 1, ☃);
      a(☃, aju.cu.a(☃), ☃, 2, 2, ☃);
      a(☃, aju.A.a(aog.a.c.a()), ☃, 2, 3, ☃);
      a(☃, aju.A.a(aog.a.c.a()), ☃, 3, 1, ☃);
      a(☃, aju.cu.a(☃), ☃, 3, 2, ☃);
      a(☃, aju.A.a(aog.a.c.a()), ☃, 3, 3, ☃);
      a(☃, aju.cu.a(☃), ☃, 4, 1, ☃);
      a(☃, aju.A.a(aog.a.b.a()), ☃, 4, 2, ☃);
      a(☃, aju.cu.a(☃), ☃, 4, 3, ☃);
      a(☃, aju.A.a(aog.a.c.a()), ☃, 5, 1, ☃);
      a(☃, aju.cu.a(☃), ☃, 5, 2, ☃);
      a(☃, aju.A.a(aog.a.c.a()), ☃, 5, 3, ☃);
      a(☃, aju.cu.a(☃), ☃, 6, 1, ☃);
      a(☃, aju.A.a(aog.a.b.a()), ☃, 6, 2, ☃);
      a(☃, aju.cu.a(☃), ☃, 6, 3, ☃);
      a(☃, aju.cu.a(☃), ☃, 7, 1, ☃);
      a(☃, aju.cu.a(☃), ☃, 7, 2, ☃);
      a(☃, aju.cu.a(☃), ☃, 7, 3, ☃);
      a(☃, aju.A.a(aog.a.c.a()), ☃, 8, 1, ☃);
      a(☃, aju.A.a(aog.a.c.a()), ☃, 8, 2, ☃);
      a(☃, aju.A.a(aog.a.c.a()), ☃, 8, 3, ☃);
    }
    for (int ☃ = 2; ☃ <= this.a - 3; ☃ += this.a - 3 - 2)
    {
      a(☃, aju.A.a(aog.a.c.a()), ☃ - 1, 2, 0, ☃);
      a(☃, aju.cu.a(☃), ☃, 2, 0, ☃);
      a(☃, aju.A.a(aog.a.c.a()), ☃ + 1, 2, 0, ☃);
      a(☃, aju.A.a(aog.a.c.a()), ☃ - 1, 3, 0, ☃);
      a(☃, aju.cu.a(☃), ☃, 3, 0, ☃);
      a(☃, aju.A.a(aog.a.c.a()), ☃ + 1, 3, 0, ☃);
      a(☃, aju.cu.a(☃), ☃ - 1, 4, 0, ☃);
      a(☃, aju.A.a(aog.a.b.a()), ☃, 4, 0, ☃);
      a(☃, aju.cu.a(☃), ☃ + 1, 4, 0, ☃);
      a(☃, aju.A.a(aog.a.c.a()), ☃ - 1, 5, 0, ☃);
      a(☃, aju.cu.a(☃), ☃, 5, 0, ☃);
      a(☃, aju.A.a(aog.a.c.a()), ☃ + 1, 5, 0, ☃);
      a(☃, aju.cu.a(☃), ☃ - 1, 6, 0, ☃);
      a(☃, aju.A.a(aog.a.b.a()), ☃, 6, 0, ☃);
      a(☃, aju.cu.a(☃), ☃ + 1, 6, 0, ☃);
      a(☃, aju.cu.a(☃), ☃ - 1, 7, 0, ☃);
      a(☃, aju.cu.a(☃), ☃, 7, 0, ☃);
      a(☃, aju.cu.a(☃), ☃ + 1, 7, 0, ☃);
      a(☃, aju.A.a(aog.a.c.a()), ☃ - 1, 8, 0, ☃);
      a(☃, aju.A.a(aog.a.c.a()), ☃, 8, 0, ☃);
      a(☃, aju.A.a(aog.a.c.a()), ☃ + 1, 8, 0, ☃);
    }
    a(☃, ☃, 8, 4, 0, 12, 6, 0, aju.A.a(aog.a.c.a()), aju.A.a(aog.a.c.a()), false);
    a(☃, aju.a.u(), 8, 6, 0, ☃);
    a(☃, aju.a.u(), 12, 6, 0, ☃);
    a(☃, aju.cu.a(☃), 9, 5, 0, ☃);
    a(☃, aju.A.a(aog.a.b.a()), 10, 5, 0, ☃);
    a(☃, aju.cu.a(☃), 11, 5, 0, ☃);
    
    a(☃, ☃, 8, -14, 8, 12, -11, 12, aju.A.a(aog.a.c.a()), aju.A.a(aog.a.c.a()), false);
    a(☃, ☃, 8, -10, 8, 12, -10, 12, aju.A.a(aog.a.b.a()), aju.A.a(aog.a.b.a()), false);
    a(☃, ☃, 8, -9, 8, 12, -9, 12, aju.A.a(aog.a.c.a()), aju.A.a(aog.a.c.a()), false);
    a(☃, ☃, 8, -8, 8, 12, -1, 12, aju.A.u(), aju.A.u(), false);
    a(☃, ☃, 9, -11, 9, 11, -1, 11, aju.a.u(), aju.a.u(), false);
    a(☃, aju.az.u(), 10, -11, 10, ☃);
    a(☃, ☃, 9, -13, 9, 11, -13, 11, aju.W.u(), aju.a.u(), false);
    a(☃, aju.a.u(), 8, -11, 10, ☃);
    a(☃, aju.a.u(), 8, -10, 10, ☃);
    a(☃, aju.A.a(aog.a.b.a()), 7, -10, 10, ☃);
    a(☃, aju.A.a(aog.a.c.a()), 7, -11, 10, ☃);
    a(☃, aju.a.u(), 12, -11, 10, ☃);
    a(☃, aju.a.u(), 12, -10, 10, ☃);
    a(☃, aju.A.a(aog.a.b.a()), 13, -10, 10, ☃);
    a(☃, aju.A.a(aog.a.c.a()), 13, -11, 10, ☃);
    a(☃, aju.a.u(), 10, -11, 8, ☃);
    a(☃, aju.a.u(), 10, -10, 8, ☃);
    a(☃, aju.A.a(aog.a.b.a()), 10, -10, 7, ☃);
    a(☃, aju.A.a(aog.a.c.a()), 10, -11, 7, ☃);
    a(☃, aju.a.u(), 10, -11, 12, ☃);
    a(☃, aju.a.u(), 10, -10, 12, ☃);
    a(☃, aju.A.a(aog.a.b.a()), 10, -10, 13, ☃);
    a(☃, aju.A.a(aog.a.c.a()), 10, -11, 13, ☃);
    for (cq ☃ : cq.c.a) {
      if (this.e[☃.b()] == 0)
      {
        int ☃ = ☃.g() * 2;
        int ☃ = ☃.i() * 2;
        this.e[☃.b()] = a(☃, ☃, ☃, 10 + ☃, -11, 10 + ☃, azt.k);
      }
    }
    return true;
  }
}
