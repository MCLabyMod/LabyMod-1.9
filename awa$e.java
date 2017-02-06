import java.util.Random;

public class awa$e
  extends awa.d
{
  private boolean e;
  
  public awa$e() {}
  
  public awa$e(Random ☃, int ☃, int ☃)
  {
    super(☃, ☃, 64, ☃, 7, 7, 9);
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    ☃.a("Witch", this.e);
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    this.e = ☃.p("Witch");
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (!a(☃, ☃, 0)) {
      return false;
    }
    a(☃, ☃, 1, 1, 1, 5, 1, 7, aju.f.a(anj.a.b.a()), aju.f.a(anj.a.b.a()), false);
    a(☃, ☃, 1, 4, 2, 5, 4, 7, aju.f.a(anj.a.b.a()), aju.f.a(anj.a.b.a()), false);
    a(☃, ☃, 2, 1, 0, 4, 1, 0, aju.f.a(anj.a.b.a()), aju.f.a(anj.a.b.a()), false);
    
    a(☃, ☃, 2, 2, 2, 3, 3, 2, aju.f.a(anj.a.b.a()), aju.f.a(anj.a.b.a()), false);
    a(☃, ☃, 1, 2, 3, 1, 3, 6, aju.f.a(anj.a.b.a()), aju.f.a(anj.a.b.a()), false);
    a(☃, ☃, 5, 2, 3, 5, 3, 6, aju.f.a(anj.a.b.a()), aju.f.a(anj.a.b.a()), false);
    a(☃, ☃, 2, 2, 7, 4, 3, 7, aju.f.a(anj.a.b.a()), aju.f.a(anj.a.b.a()), false);
    
    a(☃, ☃, 1, 0, 2, 1, 3, 2, aju.r.u(), aju.r.u(), false);
    a(☃, ☃, 5, 0, 2, 5, 3, 2, aju.r.u(), aju.r.u(), false);
    a(☃, ☃, 1, 0, 7, 1, 3, 7, aju.r.u(), aju.r.u(), false);
    a(☃, ☃, 5, 0, 7, 5, 3, 7, aju.r.u(), aju.r.u(), false);
    
    a(☃, aju.aO.u(), 2, 3, 2, ☃);
    a(☃, aju.aO.u(), 3, 3, 7, ☃);
    a(☃, aju.a.u(), 1, 3, 4, ☃);
    a(☃, aju.a.u(), 5, 3, 4, ☃);
    a(☃, aju.a.u(), 5, 3, 5, ☃);
    a(☃, aju.ca.u().a(aln.b, aln.a.r), 1, 3, 5, ☃);
    
    a(☃, aju.ai.u(), 3, 2, 6, ☃);
    a(☃, aju.bE.u(), 4, 2, 6, ☃);
    
    a(☃, aju.aO.u(), 1, 2, 1, ☃);
    a(☃, aju.aO.u(), 5, 2, 1, ☃);
    
    arc ☃ = aju.bU.u().a(aot.a, cq.c);
    arc ☃ = aju.bU.u().a(aot.a, cq.f);
    arc ☃ = aju.bU.u().a(aot.a, cq.e);
    arc ☃ = aju.bU.u().a(aot.a, cq.d);
    
    a(☃, ☃, 0, 4, 1, 6, 4, 1, ☃, ☃, false);
    a(☃, ☃, 0, 4, 2, 0, 4, 7, ☃, ☃, false);
    a(☃, ☃, 6, 4, 2, 6, 4, 7, ☃, ☃, false);
    a(☃, ☃, 0, 4, 8, 6, 4, 8, ☃, ☃, false);
    for (int ☃ = 2; ☃ <= 7; ☃ += 5) {
      for (int ☃ = 1; ☃ <= 5; ☃ += 4) {
        b(☃, aju.r.u(), ☃, -1, ☃, ☃);
      }
    }
    if (!this.e)
    {
      int ☃ = a(2, 5);
      int ☃ = d(2);
      int ☃ = b(2, 5);
      if (☃.b(new cj(☃, ☃, ☃)))
      {
        this.e = true;
        
        yz ☃ = new yz(☃);
        ☃.b(☃ + 0.5D, ☃, ☃ + 0.5D, 0.0F, 0.0F);
        ☃.a(☃.D(new cj(☃, ☃, ☃)), null);
        ☃.a(☃);
      }
    }
    return true;
  }
}
