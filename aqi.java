import java.util.List;
import java.util.Random;

public class aqi
  extends aqm
  implements aqh, ky
{
  private adq[] a = new adq[5];
  private String f;
  private int g = -1;
  
  public void a(dn ☃)
  {
    super.a(☃);
    
    this.a = new adq[u_()];
    if (☃.b("CustomName", 8)) {
      this.f = ☃.l("CustomName");
    }
    this.g = ☃.h("TransferCooldown");
    if (!c(☃))
    {
      du ☃ = ☃.c("Items", 10);
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        dn ☃ = ☃.b(☃);
        int ☃ = ☃.f("Slot");
        if ((☃ >= 0) && (☃ < this.a.length)) {
          this.a[☃] = adq.a(☃);
        }
      }
    }
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    if (!d(☃))
    {
      du ☃ = new du();
      for (int ☃ = 0; ☃ < this.a.length; ☃++) {
        if (this.a[☃] != null)
        {
          dn ☃ = new dn();
          ☃.a("Slot", (byte)☃);
          this.a[☃].b(☃);
          ☃.a(☃);
        }
      }
      ☃.a("Items", ☃);
    }
    ☃.a("TransferCooldown", this.g);
    if (o_()) {
      ☃.a("CustomName", this.f);
    }
  }
  
  public int u_()
  {
    return this.a.length;
  }
  
  public adq a(int ☃)
  {
    d(null);
    return this.a[☃];
  }
  
  public adq a(int ☃, int ☃)
  {
    d(null);
    return qh.a(this.a, ☃, ☃);
  }
  
  public adq b(int ☃)
  {
    d(null);
    return qh.a(this.a, ☃);
  }
  
  public void a(int ☃, adq ☃)
  {
    d(null);
    this.a[☃] = ☃;
    if ((☃ != null) && (☃.b > w_())) {
      ☃.b = w_();
    }
  }
  
  public String h_()
  {
    return o_() ? this.f : "container.hopper";
  }
  
  public boolean o_()
  {
    return (this.f != null) && (!this.f.isEmpty());
  }
  
  public void a(String ☃)
  {
    this.f = ☃;
  }
  
  public int w_()
  {
    return 64;
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
    return true;
  }
  
  public void c()
  {
    if ((this.b == null) || (this.b.E)) {
      return;
    }
    this.g -= 1;
    if (!o())
    {
      d(0);
      m();
    }
  }
  
  public boolean m()
  {
    if ((this.b == null) || (this.b.E)) {
      return false;
    }
    if ((!o()) && (amf.f(u())))
    {
      boolean ☃ = false;
      if (!q()) {
        ☃ = H();
      }
      if (!r()) {
        ☃ = (a(this)) || (☃);
      }
      if (☃)
      {
        d(8);
        v_();
        return true;
      }
    }
    return false;
  }
  
  private boolean q()
  {
    for (adq ☃ : this.a) {
      if (☃ != null) {
        return false;
      }
    }
    return true;
  }
  
  private boolean r()
  {
    for (adq ☃ : this.a) {
      if ((☃ == null) || (☃.b != ☃.c())) {
        return false;
      }
    }
    return true;
  }
  
  private boolean H()
  {
    qg ☃ = I();
    if (☃ == null) {
      return false;
    }
    cq ☃ = amf.e(u()).d();
    if (a(☃, ☃)) {
      return false;
    }
    for (int ☃ = 0; ☃ < u_(); ☃++) {
      if (a(☃) != null)
      {
        adq ☃ = a(☃).k();
        adq ☃ = a(☃, a(☃, 1), ☃);
        if ((☃ == null) || (☃.b == 0))
        {
          ☃.v_();
          return true;
        }
        a(☃, ☃);
      }
    }
    return false;
  }
  
  private boolean a(qg ☃, cq ☃)
  {
    if ((☃ instanceof qy))
    {
      qy ☃ = (qy)☃;
      int[] ☃ = ☃.a(☃);
      for (int ☃ = 0; ☃ < ☃.length; ☃++)
      {
        adq ☃ = ☃.a(☃[☃]);
        if ((☃ == null) || (☃.b != ☃.c())) {
          return false;
        }
      }
    }
    else
    {
      int ☃ = ☃.u_();
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        adq ☃ = ☃.a(☃);
        if ((☃ == null) || (☃.b != ☃.c())) {
          return false;
        }
      }
    }
    return true;
  }
  
  private static boolean b(qg ☃, cq ☃)
  {
    if ((☃ instanceof qy))
    {
      qy ☃ = (qy)☃;
      int[] ☃ = ☃.a(☃);
      for (int ☃ = 0; ☃ < ☃.length; ☃++) {
        if (☃.a(☃[☃]) != null) {
          return false;
        }
      }
    }
    else
    {
      int ☃ = ☃.u_();
      for (int ☃ = 0; ☃ < ☃; ☃++) {
        if (☃.a(☃) != null) {
          return false;
        }
      }
    }
    return true;
  }
  
  public static boolean a(aqh ☃)
  {
    qg ☃ = b(☃);
    if (☃ != null)
    {
      cq ☃ = cq.a;
      if (b(☃, ☃)) {
        return false;
      }
      if ((☃ instanceof qy))
      {
        qy ☃ = (qy)☃;
        int[] ☃ = ☃.a(☃);
        for (int ☃ = 0; ☃ < ☃.length; ☃++) {
          if (a(☃, ☃, ☃[☃], ☃)) {
            return true;
          }
        }
      }
      else
      {
        int ☃ = ☃.u_();
        for (int ☃ = 0; ☃ < ☃; ☃++) {
          if (a(☃, ☃, ☃, ☃)) {
            return true;
          }
        }
      }
    }
    else
    {
      for (yd ☃ : a(☃.D(), ☃.E(), ☃.F(), ☃.G())) {
        if (a(☃, ☃)) {
          return true;
        }
      }
    }
    return false;
  }
  
  private static boolean a(aqh ☃, qg ☃, int ☃, cq ☃)
  {
    adq ☃ = ☃.a(☃);
    if ((☃ != null) && (b(☃, ☃, ☃, ☃)))
    {
      adq ☃ = ☃.k();
      adq ☃ = a(☃, ☃.a(☃, 1), null);
      if ((☃ == null) || (☃.b == 0))
      {
        ☃.v_();
        return true;
      }
      ☃.a(☃, ☃);
    }
    return false;
  }
  
  public static boolean a(qg ☃, yd ☃)
  {
    boolean ☃ = false;
    if (☃ == null) {
      return false;
    }
    adq ☃ = ☃.k().k();
    adq ☃ = a(☃, ☃, null);
    if ((☃ == null) || (☃.b == 0))
    {
      ☃ = true;
      
      ☃.T();
    }
    else
    {
      ☃.a(☃);
    }
    return ☃;
  }
  
  public static adq a(qg ☃, adq ☃, cq ☃)
  {
    if (((☃ instanceof qy)) && (☃ != null))
    {
      qy ☃ = (qy)☃;
      int[] ☃ = ☃.a(☃);
      for (int ☃ = 0; (☃ < ☃.length) && (☃ != null) && (☃.b > 0); ☃++) {
        ☃ = c(☃, ☃, ☃[☃], ☃);
      }
    }
    else
    {
      int ☃ = ☃.u_();
      for (int ☃ = 0; (☃ < ☃) && (☃ != null) && (☃.b > 0); ☃++) {
        ☃ = c(☃, ☃, ☃, ☃);
      }
    }
    if ((☃ != null) && (☃.b == 0)) {
      ☃ = null;
    }
    return ☃;
  }
  
  private static boolean a(qg ☃, adq ☃, int ☃, cq ☃)
  {
    if (!☃.b(☃, ☃)) {
      return false;
    }
    if (((☃ instanceof qy)) && (!((qy)☃).a(☃, ☃, ☃))) {
      return false;
    }
    return true;
  }
  
  private static boolean b(qg ☃, adq ☃, int ☃, cq ☃)
  {
    if (((☃ instanceof qy)) && (!((qy)☃).b(☃, ☃, ☃))) {
      return false;
    }
    return true;
  }
  
  private static adq c(qg ☃, adq ☃, int ☃, cq ☃)
  {
    adq ☃ = ☃.a(☃);
    if (a(☃, ☃, ☃, ☃))
    {
      boolean ☃ = false;
      if (☃ == null)
      {
        ☃.a(☃, ☃);
        ☃ = null;
        ☃ = true;
      }
      else if (a(☃, ☃))
      {
        int ☃ = ☃.c() - ☃.b;
        int ☃ = Math.min(☃.b, ☃);
        
        ☃.b -= ☃;
        ☃.b += ☃;
        ☃ = ☃ > 0;
      }
      if (☃)
      {
        if ((☃ instanceof aqi))
        {
          aqi ☃ = (aqi)☃;
          if (☃.p()) {
            ☃.d(8);
          }
          ☃.v_();
        }
        ☃.v_();
      }
    }
    return ☃;
  }
  
  private qg I()
  {
    cq ☃ = amf.e(u());
    return b(D(), E() + ☃.g(), F() + ☃.h(), G() + ☃.i());
  }
  
  public static qg b(aqh ☃)
  {
    return b(☃.D(), ☃.E(), ☃.F() + 1.0D, ☃.G());
  }
  
  public static List<yd> a(aht ☃, double ☃, double ☃, double ☃)
  {
    return ☃.a(yd.class, new bbh(☃ - 0.5D, ☃, ☃ - 0.5D, ☃ + 0.5D, ☃ + 1.5D, ☃ + 0.5D), rv.a);
  }
  
  public static qg b(aht ☃, double ☃, double ☃, double ☃)
  {
    qg ☃ = null;
    int ☃ = on.c(☃);
    int ☃ = on.c(☃);
    int ☃ = on.c(☃);
    cj ☃ = new cj(☃, ☃, ☃);
    
    ajt ☃ = ☃.o(☃).t();
    if (☃.m())
    {
      apv ☃ = ☃.r(☃);
      if ((☃ instanceof qg))
      {
        ☃ = (qg)☃;
        if (((☃ instanceof apx)) && 
          ((☃ instanceof ake))) {
          ☃ = ((ake)☃).c(☃, ☃);
        }
      }
    }
    if (☃ == null)
    {
      List<rr> ☃ = ☃.a(null, new bbh(☃ - 0.5D, ☃ - 0.5D, ☃ - 0.5D, ☃ + 0.5D, ☃ + 0.5D, ☃ + 0.5D), rv.c);
      if (!☃.isEmpty()) {
        ☃ = (qg)☃.get(☃.r.nextInt(☃.size()));
      }
    }
    return ☃;
  }
  
  private static boolean a(adq ☃, adq ☃)
  {
    if (☃.b() != ☃.b()) {
      return false;
    }
    if (☃.i() != ☃.i()) {
      return false;
    }
    if (☃.b > ☃.c()) {
      return false;
    }
    if (!adq.a(☃, ☃)) {
      return false;
    }
    return true;
  }
  
  public double E()
  {
    return this.c.p() + 0.5D;
  }
  
  public double F()
  {
    return this.c.q() + 0.5D;
  }
  
  public double G()
  {
    return this.c.r() + 0.5D;
  }
  
  public void d(int ☃)
  {
    this.g = ☃;
  }
  
  public boolean o()
  {
    return this.g > 0;
  }
  
  public boolean p()
  {
    return this.g <= 1;
  }
  
  public String k()
  {
    return "minecraft:hopper";
  }
  
  public aau a(zi ☃, zj ☃)
  {
    d(☃);
    return new abj(☃, this, ☃);
  }
  
  public int c_(int ☃)
  {
    return 0;
  }
  
  public void b(int ☃, int ☃) {}
  
  public int g()
  {
    return 0;
  }
  
  public void l()
  {
    d(null);
    for (int ☃ = 0; ☃ < this.a.length; ☃++) {
      this.a[☃] = null;
    }
  }
}
