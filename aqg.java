public class aqg
  extends aqj
  implements ky, qy
{
  private static final int[] a = { 0 };
  private static final int[] f = { 2, 1 };
  private static final int[] g = { 1 };
  private adq[] h = new adq[3];
  private int i;
  private int j;
  private int k;
  private int l;
  private String m;
  
  public int u_()
  {
    return this.h.length;
  }
  
  public adq a(int ☃)
  {
    return this.h[☃];
  }
  
  public adq a(int ☃, int ☃)
  {
    return qh.a(this.h, ☃, ☃);
  }
  
  public adq b(int ☃)
  {
    return qh.a(this.h, ☃);
  }
  
  public void a(int ☃, adq ☃)
  {
    boolean ☃ = (☃ != null) && (☃.a(this.h[☃])) && (adq.a(☃, this.h[☃]));
    this.h[☃] = ☃;
    if ((☃ != null) && (☃.b > w_())) {
      ☃.b = w_();
    }
    if ((☃ == 0) && (!☃))
    {
      this.l = a(☃);
      this.k = 0;
      v_();
    }
  }
  
  public String h_()
  {
    return o_() ? this.m : "container.furnace";
  }
  
  public boolean o_()
  {
    return (this.m != null) && (!this.m.isEmpty());
  }
  
  public void a(String ☃)
  {
    this.m = ☃;
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    du ☃ = ☃.c("Items", 10);
    this.h = new adq[u_()];
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      dn ☃ = ☃.b(☃);
      int ☃ = ☃.f("Slot");
      if ((☃ >= 0) && (☃ < this.h.length)) {
        this.h[☃] = adq.a(☃);
      }
    }
    this.i = ☃.g("BurnTime");
    this.k = ☃.g("CookTime");
    this.l = ☃.g("CookTimeTotal");
    this.j = b(this.h[1]);
    if (☃.b("CustomName", 8)) {
      this.m = ☃.l("CustomName");
    }
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("BurnTime", (short)this.i);
    ☃.a("CookTime", (short)this.k);
    ☃.a("CookTimeTotal", (short)this.l);
    du ☃ = new du();
    for (int ☃ = 0; ☃ < this.h.length; ☃++) {
      if (this.h[☃] != null)
      {
        dn ☃ = new dn();
        ☃.a("Slot", (byte)☃);
        this.h[☃].b(☃);
        ☃.a(☃);
      }
    }
    ☃.a("Items", ☃);
    if (o_()) {
      ☃.a("CustomName", this.m);
    }
  }
  
  public int w_()
  {
    return 64;
  }
  
  public boolean m()
  {
    return this.i > 0;
  }
  
  public static boolean a(qg ☃)
  {
    return ☃.c_(0) > 0;
  }
  
  public void c()
  {
    boolean ☃ = m();
    boolean ☃ = false;
    if (m()) {
      this.i -= 1;
    }
    if (!this.b.E)
    {
      if ((m()) || ((this.h[1] != null) && (this.h[0] != null)))
      {
        if ((!m()) && (o()))
        {
          this.j = (this.i = b(this.h[1]));
          if (m())
          {
            ☃ = true;
            if (this.h[1] != null)
            {
              this.h[1].b -= 1;
              if (this.h[1].b == 0)
              {
                ado ☃ = this.h[1].b().q();
                this.h[1] = (☃ != null ? new adq(☃) : null);
              }
            }
          }
        }
        if ((m()) && (o()))
        {
          this.k += 1;
          if (this.k == this.l)
          {
            this.k = 0;
            this.l = a(this.h[0]);
            n();
            ☃ = true;
          }
        }
        else
        {
          this.k = 0;
        }
      }
      else if ((!m()) && 
        (this.k > 0))
      {
        this.k = on.a(this.k - 2, 0, this.l);
      }
      if (☃ != m())
      {
        ☃ = true;
        als.a(m(), this.b, this.c);
      }
    }
    if (☃) {
      v_();
    }
  }
  
  public int a(adq ☃)
  {
    return 200;
  }
  
  private boolean o()
  {
    if (this.h[0] == null) {
      return false;
    }
    adq ☃ = afq.a().a(this.h[0]);
    if (☃ == null) {
      return false;
    }
    if (this.h[2] == null) {
      return true;
    }
    if (!this.h[2].a(☃)) {
      return false;
    }
    if ((this.h[2].b < w_()) && (this.h[2].b < this.h[2].c())) {
      return true;
    }
    if (this.h[2].b < ☃.c()) {
      return true;
    }
    return false;
  }
  
  public void n()
  {
    if (!o()) {
      return;
    }
    adq ☃ = afq.a().a(this.h[0]);
    if (this.h[2] == null) {
      this.h[2] = ☃.k();
    } else if (this.h[2].b() == ☃.b()) {
      this.h[2].b += 1;
    }
    if ((this.h[0].b() == ado.a(aju.v)) && (this.h[0].i() == 1) && (this.h[1] != null) && (this.h[1].b() == ads.ay)) {
      this.h[1] = new adq(ads.az);
    }
    this.h[0].b -= 1;
    if (this.h[0].b <= 0) {
      this.h[0] = null;
    }
  }
  
  public static int b(adq ☃)
  {
    if (☃ == null) {
      return 0;
    }
    ado ☃ = ☃.b();
    if (((☃ instanceof acc)) && (ajt.a(☃) != aju.a))
    {
      ajt ☃ = ajt.a(☃);
      if (☃ == aju.bM) {
        return 150;
      }
      if (☃.u().a() == axe.d) {
        return 300;
      }
      if (☃ == aju.cA) {
        return 16000;
      }
    }
    if (((☃ instanceof acr)) && (((acr)☃).h().equals("WOOD"))) {
      return 200;
    }
    if (((☃ instanceof aex)) && (((aex)☃).h().equals("WOOD"))) {
      return 200;
    }
    if (((☃ instanceof adn)) && (((adn)☃).g().equals("WOOD"))) {
      return 200;
    }
    if (☃ == ads.A) {
      return 100;
    }
    if (☃ == ads.j) {
      return 1600;
    }
    if (☃ == ads.aA) {
      return 20000;
    }
    if (☃ == ado.a(aju.g)) {
      return 100;
    }
    if (☃ == ads.bC) {
      return 2400;
    }
    return 0;
  }
  
  public static boolean c(adq ☃)
  {
    return b(☃) > 0;
  }
  
  public boolean a(zj ☃)
  {
    if (this.b.r(this.c) != this) {
      return false;
    }
    if (☃.e(this.c.p() + 0.5D, this.c.q() + 0.5D, this.c.r() + 0.5D) > 64.0D) {
      return false;
    }
    return true;
  }
  
  public void b(zj ☃) {}
  
  public void c(zj ☃) {}
  
  public boolean b(int ☃, adq ☃)
  {
    if (☃ == 2) {
      return false;
    }
    if (☃ == 1)
    {
      adq ☃ = this.h[1];
      return (c(☃)) || ((abg.d_(☃)) && ((☃ == null) || (☃.b() != ads.ay)));
    }
    return true;
  }
  
  public int[] a(cq ☃)
  {
    if (☃ == cq.a) {
      return f;
    }
    if (☃ == cq.b) {
      return a;
    }
    return g;
  }
  
  public boolean a(int ☃, adq ☃, cq ☃)
  {
    return b(☃, ☃);
  }
  
  public boolean b(int ☃, adq ☃, cq ☃)
  {
    if ((☃ == cq.a) && (☃ == 1))
    {
      ado ☃ = ☃.b();
      if ((☃ != ads.az) && (☃ != ads.ay)) {
        return false;
      }
    }
    return true;
  }
  
  public String k()
  {
    return "minecraft:furnace";
  }
  
  public aau a(zi ☃, zj ☃)
  {
    return new abh(☃, this);
  }
  
  public int c_(int ☃)
  {
    switch (☃)
    {
    case 0: 
      return this.i;
    case 1: 
      return this.j;
    case 2: 
      return this.k;
    case 3: 
      return this.l;
    }
    return 0;
  }
  
  public void b(int ☃, int ☃)
  {
    switch (☃)
    {
    case 0: 
      this.i = ☃;
      break;
    case 1: 
      this.j = ☃;
      break;
    case 2: 
      this.k = ☃;
      break;
    case 3: 
      this.l = ☃;
    }
  }
  
  public int g()
  {
    return 4;
  }
  
  public void l()
  {
    for (int ☃ = 0; ☃ < this.h.length; ☃++) {
      this.h[☃] = null;
    }
  }
}
