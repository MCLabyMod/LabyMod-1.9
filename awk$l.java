import java.util.List;
import java.util.Random;

public class awk$l
  extends awk.o
{
  private int a;
  
  public awk$l() {}
  
  public awk$l(awk.k ☃, int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃, ☃);
    
    a(☃);
    this.l = ☃;
    this.a = Math.max(☃.c(), ☃.e());
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    ☃.a("Length", this.a);
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    this.a = ☃.h("Length");
  }
  
  public void a(awg ☃, List<awg> ☃, Random ☃)
  {
    boolean ☃ = false;
    
    int ☃ = ☃.nextInt(5);
    while (☃ < this.a - 8)
    {
      awg ☃ = a((awk.k)☃, ☃, ☃, 0, ☃);
      if (☃ != null)
      {
        ☃ += Math.max(☃.l.c(), ☃.l.e());
        ☃ = true;
      }
      ☃ += 2 + ☃.nextInt(5);
    }
    ☃ = ☃.nextInt(5);
    while (☃ < this.a - 8)
    {
      awg ☃ = b((awk.k)☃, ☃, ☃, 0, ☃);
      if (☃ != null)
      {
        ☃ += Math.max(☃.l.c(), ☃.l.e());
        ☃ = true;
      }
      ☃ += 2 + ☃.nextInt(5);
    }
    cq ☃ = e();
    if ((☃) && (☃.nextInt(3) > 0) && (☃ != null)) {
      switch (awk.1.a[☃.ordinal()])
      {
      case 1: 
        awk.b((awk.k)☃, ☃, ☃, this.l.a - 1, this.l.b, this.l.c, cq.e, d());
        break;
      case 2: 
        awk.b((awk.k)☃, ☃, ☃, this.l.a - 1, this.l.b, this.l.f - 2, cq.e, d());
        break;
      case 4: 
        awk.b((awk.k)☃, ☃, ☃, this.l.d - 2, this.l.b, this.l.c - 1, cq.c, d());
        break;
      case 3: 
        awk.b((awk.k)☃, ☃, ☃, this.l.a, this.l.b, this.l.c - 1, cq.c, d());
      }
    }
    if ((☃) && (☃.nextInt(3) > 0) && (☃ != null)) {
      switch (awk.1.a[☃.ordinal()])
      {
      case 1: 
        awk.b((awk.k)☃, ☃, ☃, this.l.d + 1, this.l.b, this.l.c, cq.f, d());
        break;
      case 2: 
        awk.b((awk.k)☃, ☃, ☃, this.l.d + 1, this.l.b, this.l.f - 2, cq.f, d());
        break;
      case 4: 
        awk.b((awk.k)☃, ☃, ☃, this.l.d - 2, this.l.b, this.l.f + 1, cq.d, d());
        break;
      case 3: 
        awk.b((awk.k)☃, ☃, ☃, this.l.a, this.l.b, this.l.f + 1, cq.d, d());
      }
    }
  }
  
  public static avp a(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃)
  {
    int ☃ = 7 * on.a(☃, 3, 5);
    while (☃ >= 7)
    {
      avp ☃ = avp.a(☃, ☃, ☃, 0, 0, 0, 3, 3, ☃, ☃);
      if (awg.a(☃, ☃) == null) {
        return ☃;
      }
      ☃ -= 7;
    }
    return null;
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    arc ☃ = a(aju.n.u());
    arc ☃ = a(aju.e.u());
    for (int ☃ = this.l.a; ☃ <= this.l.d; ☃++) {
      for (int ☃ = this.l.c; ☃ <= this.l.f; ☃++)
      {
        cj ☃ = new cj(☃, 64, ☃);
        if (☃.b(☃))
        {
          ☃ = ☃.q(☃).b();
          ☃.a(☃, ☃, 2);
          ☃.a(☃.b(), ☃, 2);
        }
      }
    }
    return true;
  }
}
