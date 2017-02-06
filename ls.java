import net.minecraft.server.MinecraftServer;

public class ls
{
  public aht a;
  public lr b;
  private ahw.a c = ahw.a.a;
  private boolean d;
  private int e;
  private cj f = cj.a;
  private int g;
  private boolean h;
  private cj i = cj.a;
  private int j;
  private int k = -1;
  
  public ls(aht ☃)
  {
    this.a = ☃;
  }
  
  public void a(ahw.a ☃)
  {
    this.c = ☃;
    
    ☃.a(this.b.bJ);
    
    this.b.w();
    this.b.b.al().a(new gz(gz.a.b, new lr[] { this.b }));
    this.a.e();
  }
  
  public ahw.a b()
  {
    return this.c;
  }
  
  public boolean c()
  {
    return this.c.e();
  }
  
  public boolean d()
  {
    return this.c.d();
  }
  
  public void b(ahw.a ☃)
  {
    if (this.c == ahw.a.a) {
      this.c = ☃;
    }
    a(this.c);
  }
  
  public void a()
  {
    this.g += 1;
    if (this.h)
    {
      int ☃ = this.g - this.j;
      arc ☃ = this.a.o(this.i);
      ajt ☃ = ☃.t();
      if (☃.a() == axe.a)
      {
        this.h = false;
      }
      else
      {
        float ☃ = ☃.a(this.b, this.b.l, this.i) * (☃ + 1);
        int ☃ = (int)(☃ * 10.0F);
        if (☃ != this.k)
        {
          this.a.c(this.b.O(), this.i, ☃);
          this.k = ☃;
        }
        if (☃ >= 1.0F)
        {
          this.h = false;
          b(this.i);
        }
      }
    }
    else if (this.d)
    {
      arc ☃ = this.a.o(this.f);
      ajt ☃ = ☃.t();
      if (☃.a() == axe.a)
      {
        this.a.c(this.b.O(), this.f, -1);
        this.k = -1;
        this.d = false;
      }
      else
      {
        int ☃ = this.g - this.e;
        float ☃ = ☃.a(this.b, this.b.l, this.i) * (☃ + 1);
        int ☃ = (int)(☃ * 10.0F);
        if (☃ != this.k)
        {
          this.a.c(this.b.O(), this.f, ☃);
          this.k = ☃;
        }
      }
    }
  }
  
  public void a(cj ☃, cq ☃)
  {
    if (d())
    {
      if (!this.a.a(null, ☃, ☃)) {
        b(☃);
      }
      return;
    }
    arc ☃ = this.a.o(☃);
    ajt ☃ = ☃.t();
    if (this.c.c())
    {
      if (this.c == ahw.a.e) {
        return;
      }
      if (!this.b.cU())
      {
        adq ☃ = this.b.cb();
        if (☃ == null) {
          return;
        }
        if (!☃.a(☃)) {
          return;
        }
      }
    }
    this.a.a(null, ☃, ☃);
    this.e = this.g;
    float ☃ = 1.0F;
    if (☃.a() != axe.a)
    {
      ☃.a(this.a, ☃, this.b);
      ☃ = ☃.a(this.b, this.b.l, ☃);
    }
    if ((☃.a() != axe.a) && (☃ >= 1.0F))
    {
      b(☃);
    }
    else
    {
      this.d = true;
      this.f = ☃;
      
      int ☃ = (int)(☃ * 10.0F);
      this.a.c(this.b.O(), ☃, ☃);
      this.k = ☃;
    }
  }
  
  public void a(cj ☃)
  {
    if (☃.equals(this.f))
    {
      int ☃ = this.g - this.e;
      
      arc ☃ = this.a.o(☃);
      if (☃.a() != axe.a)
      {
        float ☃ = ☃.a(this.b, this.b.l, ☃) * (☃ + 1);
        if (☃ >= 0.7F)
        {
          this.d = false;
          this.a.c(this.b.O(), ☃, -1);
          b(☃);
        }
        else if (!this.h)
        {
          this.d = false;
          this.h = true;
          this.i = ☃;
          this.j = this.e;
        }
      }
    }
  }
  
  public void e()
  {
    this.d = false;
    
    this.a.c(this.b.O(), this.f, -1);
  }
  
  private boolean c(cj ☃)
  {
    arc ☃ = this.a.o(☃);
    
    ☃.t().a(this.a, ☃, ☃, this.b);
    
    boolean ☃ = this.a.g(☃);
    if (☃) {
      ☃.t().d(this.a, ☃, ☃);
    }
    return ☃;
  }
  
  public boolean b(cj ☃)
  {
    if ((this.c.d()) && 
      (this.b.cb() != null) && ((this.b.cb().b() instanceof aex))) {
      return false;
    }
    arc ☃ = this.a.o(☃);
    apv ☃ = this.a.r(☃);
    if (((☃.t() instanceof akk)) && (!this.b.a(2, "")))
    {
      this.a.a(☃, ☃, ☃, 3);
      return false;
    }
    if (this.c.c())
    {
      if (this.c == ahw.a.e) {
        return false;
      }
      if (!this.b.cU())
      {
        adq ☃ = this.b.cb();
        if (☃ == null) {
          return false;
        }
        if (!☃.a(☃.t())) {
          return false;
        }
      }
    }
    this.a.a(this.b, 2001, ☃, ajt.j(☃));
    
    boolean ☃ = c(☃);
    if (d())
    {
      this.b.a.a(new fu(this.a, ☃));
    }
    else
    {
      adq ☃ = this.b.cb();
      adq ☃ = ☃ == null ? null : ☃.k();
      boolean ☃ = this.b.b(☃);
      if (☃ != null)
      {
        ☃.a(this.a, ☃, ☃, this.b);
        if (☃.b == 0) {
          this.b.a(qm.a, null);
        }
      }
      if ((☃) && (☃)) {
        ☃.t().a(this.a, this.b, ☃, ☃, ☃, ☃);
      }
    }
    return ☃;
  }
  
  public qo a(zj ☃, aht ☃, adq ☃, qm ☃)
  {
    if (this.c == ahw.a.e) {
      return qo.b;
    }
    if (☃.da().a(☃.b())) {
      return qo.b;
    }
    int ☃ = ☃.b;
    int ☃ = ☃.i();
    qp<adq> ☃ = ☃.a(☃, ☃, ☃);
    
    adq ☃ = (adq)☃.b();
    if ((☃ == ☃) && (☃.b == ☃) && (☃.l() <= 0) && (☃.i() == ☃)) {
      return ☃.a();
    }
    ☃.a(☃, ☃);
    if (d())
    {
      ☃.b = ☃;
      if (☃.e()) {
        ☃.b(☃);
      }
    }
    if (☃.b == 0) {
      ☃.a(☃, null);
    }
    if (!☃.cs()) {
      ((lr)☃).a(☃.bs);
    }
    return ☃.a();
  }
  
  public qo a(zj ☃, aht ☃, adq ☃, qm ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (this.c == ahw.a.e)
    {
      apv ☃ = ☃.r(☃);
      if ((☃ instanceof qs))
      {
        ajt ☃ = ☃.o(☃).t();
        qs ☃ = (qs)☃;
        if (((☃ instanceof apx)) && ((☃ instanceof ake))) {
          ☃ = ((ake)☃).c(☃, ☃);
        }
        if (☃ != null)
        {
          ☃.a(☃);
          return qo.a;
        }
      }
      else if ((☃ instanceof qg))
      {
        ☃.a((qg)☃);
        return qo.a;
      }
      return qo.b;
    }
    if ((!☃.aK()) || ((☃.cb() == null) && (☃.cc() == null)))
    {
      arc ☃ = ☃.o(☃);
      if (☃.t().a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃)) {
        return qo.a;
      }
    }
    if (☃ == null) {
      return qo.b;
    }
    if (☃.da().a(☃.b())) {
      return qo.b;
    }
    if (((☃.b() instanceof acc)) && 
      ((((acc)☃.b()).d() instanceof akk)) && (!☃.a(2, ""))) {
      return qo.c;
    }
    if (d())
    {
      int ☃ = ☃.i();
      int ☃ = ☃.b;
      qo ☃ = ☃.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
      ☃.b(☃);
      ☃.b = ☃;
      return ☃;
    }
    return ☃.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public void a(lp ☃)
  {
    this.a = ☃;
  }
}
