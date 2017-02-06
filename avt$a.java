import java.util.List;
import java.util.Random;

public class avt$a
  extends awg
{
  private boolean a;
  private boolean b;
  private boolean c;
  private int d;
  
  public avt$a() {}
  
  protected void a(dn ☃)
  {
    ☃.a("hr", this.a);
    ☃.a("sc", this.b);
    ☃.a("hps", this.c);
    ☃.a("Num", this.d);
  }
  
  protected void b(dn ☃)
  {
    this.a = ☃.p("hr");
    this.b = ☃.p("sc");
    this.c = ☃.p("hps");
    this.d = ☃.h("Num");
  }
  
  public avt$a(int ☃, Random ☃, avp ☃, cq ☃)
  {
    super(☃);
    a(☃);
    this.l = ☃;
    this.a = (☃.nextInt(3) == 0);
    this.b = ((!this.a) && (☃.nextInt(23) == 0));
    if (e().k() == cq.a.c) {
      this.d = (☃.e() / 5);
    } else {
      this.d = (☃.c() / 5);
    }
  }
  
  public static avp a(List<awg> ☃, Random ☃, int ☃, int ☃, int ☃, cq ☃)
  {
    avp ☃ = new avp(☃, ☃, ☃, ☃, ☃ + 2, ☃);
    
    int ☃ = ☃.nextInt(3) + 2;
    while (☃ > 0)
    {
      int ☃ = ☃ * 5;
      switch (avt.1.a[☃.ordinal()])
      {
      case 1: 
        ☃.d = (☃ + 2);
        ☃.c = (☃ - (☃ - 1));
        break;
      case 2: 
        ☃.d = (☃ + 2);
        ☃.f = (☃ + (☃ - 1));
        break;
      case 3: 
        ☃.a = (☃ - (☃ - 1));
        ☃.f = (☃ + 2);
        break;
      case 4: 
        ☃.d = (☃ + (☃ - 1));
        ☃.f = (☃ + 2);
      }
      if (awg.a(☃, ☃) == null) {
        break;
      }
      ☃--;
    }
    if (☃ > 0) {
      return ☃;
    }
    return null;
  }
  
  public void a(awg ☃, List<awg> ☃, Random ☃)
  {
    int ☃ = d();
    int ☃ = ☃.nextInt(4);
    cq ☃ = e();
    if (☃ != null) {
      switch (avt.1.a[☃.ordinal()])
      {
      case 1: 
        if (☃ <= 1) {
          avt.a(☃, ☃, ☃, this.l.a, this.l.b - 1 + ☃.nextInt(3), this.l.c - 1, ☃, ☃);
        } else if (☃ == 2) {
          avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b - 1 + ☃.nextInt(3), this.l.c, cq.e, ☃);
        } else {
          avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b - 1 + ☃.nextInt(3), this.l.c, cq.f, ☃);
        }
        break;
      case 2: 
        if (☃ <= 1) {
          avt.a(☃, ☃, ☃, this.l.a, this.l.b - 1 + ☃.nextInt(3), this.l.f + 1, ☃, ☃);
        } else if (☃ == 2) {
          avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b - 1 + ☃.nextInt(3), this.l.f - 3, cq.e, ☃);
        } else {
          avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b - 1 + ☃.nextInt(3), this.l.f - 3, cq.f, ☃);
        }
        break;
      case 3: 
        if (☃ <= 1) {
          avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b - 1 + ☃.nextInt(3), this.l.c, ☃, ☃);
        } else if (☃ == 2) {
          avt.a(☃, ☃, ☃, this.l.a, this.l.b - 1 + ☃.nextInt(3), this.l.c - 1, cq.c, ☃);
        } else {
          avt.a(☃, ☃, ☃, this.l.a, this.l.b - 1 + ☃.nextInt(3), this.l.f + 1, cq.d, ☃);
        }
        break;
      case 4: 
        if (☃ <= 1) {
          avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b - 1 + ☃.nextInt(3), this.l.c, ☃, ☃);
        } else if (☃ == 2) {
          avt.a(☃, ☃, ☃, this.l.d - 3, this.l.b - 1 + ☃.nextInt(3), this.l.c - 1, cq.c, ☃);
        } else {
          avt.a(☃, ☃, ☃, this.l.d - 3, this.l.b - 1 + ☃.nextInt(3), this.l.f + 1, cq.d, ☃);
        }
        break;
      }
    }
    if (☃ < 8) {
      if ((☃ == cq.c) || (☃ == cq.d)) {
        for (int ☃ = this.l.c + 3; ☃ + 3 <= this.l.f; ☃ += 5)
        {
          int ☃ = ☃.nextInt(5);
          if (☃ == 0) {
            avt.a(☃, ☃, ☃, this.l.a - 1, this.l.b, ☃, cq.e, ☃ + 1);
          } else if (☃ == 1) {
            avt.a(☃, ☃, ☃, this.l.d + 1, this.l.b, ☃, cq.f, ☃ + 1);
          }
        }
      } else {
        for (int ☃ = this.l.a + 3; ☃ + 3 <= this.l.d; ☃ += 5)
        {
          int ☃ = ☃.nextInt(5);
          if (☃ == 0) {
            avt.a(☃, ☃, ☃, ☃, this.l.b, this.l.c - 1, cq.c, ☃ + 1);
          } else if (☃ == 1) {
            avt.a(☃, ☃, ☃, ☃, this.l.b, this.l.f + 1, cq.d, ☃ + 1);
          }
        }
      }
    }
  }
  
  protected boolean a(aht ☃, avp ☃, Random ☃, int ☃, int ☃, int ☃, kk ☃)
  {
    cj ☃ = new cj(a(☃, ☃), d(☃), b(☃, ☃));
    if ((☃.b(☃)) && 
      (☃.o(☃).a() == axe.a))
    {
      arc ☃ = aju.av.u().a(ant.d, ☃.nextBoolean() ? ajp.b.a : ajp.b.b);
      a(☃, ☃, ☃, ☃, ☃, ☃);
      aai ☃ = new aai(☃, ☃.p() + 0.5F, ☃.q() + 0.5F, ☃.r() + 0.5F);
      ☃.a(☃, ☃.nextLong());
      ☃.a(☃);
      return true;
    }
    return false;
  }
  
  public boolean a(aht ☃, Random ☃, avp ☃)
  {
    if (a(☃, ☃)) {
      return false;
    }
    int ☃ = 0;
    int ☃ = 2;
    int ☃ = 0;
    int ☃ = 2;
    int ☃ = this.d * 5 - 1;
    
    a(☃, ☃, 0, 0, 0, 2, 1, ☃, aju.a.u(), aju.a.u(), false);
    a(☃, ☃, ☃, 0.8F, 0, 2, 0, 2, 2, ☃, aju.a.u(), aju.a.u(), false);
    if (this.b) {
      a(☃, ☃, ☃, 0.6F, 0, 0, 0, 2, 1, ☃, aju.G.u(), aju.a.u(), false);
    }
    for (int ☃ = 0; ☃ < this.d; ☃++)
    {
      int ☃ = 2 + ☃ * 5;
      
      a(☃, ☃, 0, 0, ☃, 0, 1, ☃, aju.aO.u(), aju.a.u(), false);
      a(☃, ☃, 2, 0, ☃, 2, 1, ☃, aju.aO.u(), aju.a.u(), false);
      if (☃.nextInt(4) == 0)
      {
        a(☃, ☃, 0, 2, ☃, 0, 2, ☃, aju.f.u(), aju.a.u(), false);
        a(☃, ☃, 2, 2, ☃, 2, 2, ☃, aju.f.u(), aju.a.u(), false);
      }
      else
      {
        a(☃, ☃, 0, 2, ☃, 2, 2, ☃, aju.f.u(), aju.a.u(), false);
      }
      a(☃, ☃, ☃, 0.1F, 0, 2, ☃ - 1, aju.G.u());
      a(☃, ☃, ☃, 0.1F, 2, 2, ☃ - 1, aju.G.u());
      a(☃, ☃, ☃, 0.1F, 0, 2, ☃ + 1, aju.G.u());
      a(☃, ☃, ☃, 0.1F, 2, 2, ☃ + 1, aju.G.u());
      a(☃, ☃, ☃, 0.05F, 0, 2, ☃ - 2, aju.G.u());
      a(☃, ☃, ☃, 0.05F, 2, 2, ☃ - 2, aju.G.u());
      a(☃, ☃, ☃, 0.05F, 0, 2, ☃ + 2, aju.G.u());
      a(☃, ☃, ☃, 0.05F, 2, 2, ☃ + 2, aju.G.u());
      
      a(☃, ☃, ☃, 0.05F, 1, 2, ☃ - 1, aju.aa.u().a(apf.a, cq.d));
      a(☃, ☃, ☃, 0.05F, 1, 2, ☃ + 1, aju.aa.u().a(apf.a, cq.c));
      if (☃.nextInt(100) == 0) {
        a(☃, ☃, ☃, 2, 0, ☃ - 1, azt.f);
      }
      if (☃.nextInt(100) == 0) {
        a(☃, ☃, ☃, 0, 0, ☃ + 1, azt.f);
      }
      if ((this.b) && (!this.c))
      {
        int ☃ = d(0);int ☃ = ☃ - 1 + ☃.nextInt(3);
        int ☃ = a(1, ☃);
        ☃ = b(1, ☃);
        cj ☃ = new cj(☃, ☃, ☃);
        if (☃.b(☃))
        {
          this.c = true;
          ☃.a(☃, aju.ac.u(), 2);
          
          apv ☃ = ☃.r(☃);
          if ((☃ instanceof aqk)) {
            ((aqk)☃).b().a("CaveSpider");
          }
        }
      }
    }
    for (int ☃ = 0; ☃ <= 2; ☃++) {
      for (int ☃ = 0; ☃ <= ☃; ☃++)
      {
        int ☃ = -1;
        arc ☃ = a(☃, ☃, ☃, ☃, ☃);
        if (☃.a() == axe.a)
        {
          int ☃ = -1;
          a(☃, aju.f.u(), ☃, ☃, ☃, ☃);
        }
      }
    }
    if (this.a)
    {
      arc ☃ = aju.av.u().a(ant.d, ajp.b.a);
      for (int ☃ = 0; ☃ <= ☃; ☃++)
      {
        arc ☃ = a(☃, 1, -1, ☃, ☃);
        if ((☃.a() != axe.a) && (☃.b())) {
          a(☃, ☃, ☃, 0.7F, 1, 0, ☃, ☃);
        }
      }
    }
    return true;
  }
}
