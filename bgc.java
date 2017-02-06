import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class bgc
  extends bge
{
  private static final kk u = new kk("textures/gui/container/creative_inventory/tabs.png");
  private static qv v = new qv("tmp", true, 45);
  private static int w = acq.b.a();
  private float x;
  private boolean y;
  private boolean z;
  private bdd A;
  private List<abt> B;
  private abt C;
  private boolean D;
  private bgb E;
  
  static class a
    extends aau
  {
    public List<adq> a = Lists.newArrayList();
    
    public a(zj ☃)
    {
      zi ☃ = ☃.br;
      for (int ☃ = 0; ☃ < 5; ☃++) {
        for (int ☃ = 0; ☃ < 9; ☃++) {
          a(new abt(bgc.g(), ☃ * 9 + ☃, 9 + ☃ * 18, 18 + ☃ * 18));
        }
      }
      for (int ☃ = 0; ☃ < 9; ☃++) {
        a(new abt(☃, ☃, 9 + ☃ * 18, 112));
      }
      a(0.0F);
    }
    
    public boolean a(zj ☃)
    {
      return true;
    }
    
    public void a(float ☃)
    {
      int ☃ = (this.a.size() + 9 - 1) / 9 - 5;
      
      int ☃ = (int)(☃ * ☃ + 0.5D);
      if (☃ < 0) {
        ☃ = 0;
      }
      for (int ☃ = 0; ☃ < 5; ☃++) {
        for (int ☃ = 0; ☃ < 9; ☃++)
        {
          int ☃ = ☃ + (☃ + ☃) * 9;
          if ((☃ >= 0) && (☃ < this.a.size())) {
            bgc.g().a(☃ + ☃ * 9, (adq)this.a.get(☃));
          } else {
            bgc.g().a(☃ + ☃ * 9, null);
          }
        }
      }
    }
    
    public boolean e()
    {
      return this.a.size() > 45;
    }
    
    protected void a(int ☃, int ☃, boolean ☃, zj ☃) {}
    
    public adq b(zj ☃, int ☃)
    {
      if ((☃ >= this.c.size() - 9) && (☃ < this.c.size()))
      {
        abt ☃ = (abt)this.c.get(☃);
        if ((☃ != null) && (☃.e())) {
          ☃.d(null);
        }
      }
      return null;
    }
    
    public boolean a(adq ☃, abt ☃)
    {
      return ☃.g > 90;
    }
    
    public boolean b(abt ☃)
    {
      return ((☃.d instanceof zi)) || ((☃.g > 90) && (☃.f <= 162));
    }
  }
  
  public bgc(zj ☃)
  {
    super(new bgc.a(☃));
    ☃.bt = this.h;
    this.p = true;
    this.g = 136;
    this.f = 195;
  }
  
  public void e()
  {
    if (!this.j.c.h()) {
      this.j.a(new bgk(this.j.h));
    }
  }
  
  protected void a(abt ☃, int ☃, int ☃, aaz ☃)
  {
    this.D = true;
    boolean ☃ = ☃ == aaz.b;
    ☃ = (☃ == 64537) && (☃ == aaz.a) ? aaz.e : ☃;
    if ((☃ != null) || (w == acq.m.a()) || (☃ == aaz.f))
    {
      if ((☃ == this.C) && (☃))
      {
        for (int ☃ = 0; ☃ < this.j.h.bs.a().size(); ☃++) {
          this.j.c.a(null, ☃);
        }
      }
      else if (w == acq.m.a())
      {
        if (☃ == this.C)
        {
          this.j.h.br.e(null);
        }
        else if ((☃ == aaz.e) && (☃ != null) && (☃.e()))
        {
          adq ☃ = ☃.a(☃ == 0 ? 1 : ☃.d().c());
          this.j.h.a(☃, true);
          this.j.c.a(☃);
        }
        else if ((☃ == aaz.e) && (this.j.h.br.o() != null))
        {
          this.j.h.a(this.j.h.br.o(), true);
          this.j.c.a(this.j.h.br.o());
          this.j.h.br.e(null);
        }
        else
        {
          this.j.h.bs.a(☃ == null ? ☃ : bgc.b.a((bgc.b)☃).e, ☃, ☃, this.j.h);
          this.j.h.bs.b();
        }
      }
      else if ((☃ != aaz.f) && (☃.d == v))
      {
        zi ☃ = this.j.h.br;
        adq ☃ = ☃.o();
        adq ☃ = ☃.d();
        if (☃ == aaz.c)
        {
          if ((☃ != null) && (☃ >= 0) && (☃ < 9))
          {
            adq ☃ = ☃.k();
            ☃.b = ☃.c();
            this.j.h.br.a(☃, ☃);
            this.j.h.bs.b();
          }
          return;
        }
        if (☃ == aaz.d)
        {
          if ((☃.o() == null) && (☃.e()))
          {
            adq ☃ = ☃.d().k();
            ☃.b = ☃.c();
            ☃.e(☃);
          }
          return;
        }
        if (☃ == aaz.e)
        {
          if (☃ != null)
          {
            adq ☃ = ☃.k();
            ☃.b = (☃ == 0 ? 1 : ☃.c());
            this.j.h.a(☃, true);
            this.j.c.a(☃);
          }
          return;
        }
        if ((☃ != null) && (☃ != null) && (☃.a(☃)) && (adq.a(☃, ☃)))
        {
          if (☃ == 0)
          {
            if (☃) {
              ☃.b = ☃.c();
            } else if (☃.b < ☃.c()) {
              ☃.b += 1;
            }
          }
          else if (☃.b <= 1) {
            ☃.e(null);
          } else {
            ☃.b -= 1;
          }
        }
        else if ((☃ == null) || (☃ != null))
        {
          ☃.e(null);
        }
        else
        {
          ☃.e(adq.c(☃));
          ☃ = ☃.o();
          if (☃) {
            ☃.b = ☃.c();
          }
        }
      }
      else
      {
        this.h.a(☃ == null ? ☃ : ☃.e, ☃, ☃, this.j.h);
        if (aau.c(☃) == 2)
        {
          for (int ☃ = 0; ☃ < 9; ☃++) {
            this.j.c.a(this.h.a(45 + ☃).d(), 36 + ☃);
          }
        }
        else if (☃ != null)
        {
          adq ☃ = this.h.a(☃.e).d();
          this.j.c.a(☃, ☃.e - this.h.c.size() + 9 + 36);
        }
      }
    }
    else
    {
      zi ☃ = this.j.h.br;
      if (☃.o() != null)
      {
        if (☃ == 0)
        {
          this.j.h.a(☃.o(), true);
          this.j.c.a(☃.o());
          ☃.e(null);
        }
        if (☃ == 1)
        {
          adq ☃ = ☃.o().a(1);
          this.j.h.a(☃, true);
          this.j.c.a(☃);
          if (☃.o().b == 0) {
            ☃.e(null);
          }
        }
      }
    }
  }
  
  protected void a()
  {
    int ☃ = this.i;
    super.a();
    if ((this.A != null) && (this.i != ☃)) {
      this.A.a = (this.i + 82);
    }
  }
  
  public void b()
  {
    if (this.j.c.h())
    {
      super.b();
      this.n.clear();
      
      Keyboard.enableRepeatEvents(true);
      
      this.A = new bdd(0, this.q, this.i + 82, this.r + 6, 89, this.q.a);
      this.A.f(15);
      this.A.a(false);
      this.A.e(false);
      this.A.g(16777215);
      
      int ☃ = w;
      w = -1;
      b(acq.a[☃]);
      
      this.E = new bgb(this.j);
      this.j.h.bs.a(this.E);
    }
    else
    {
      this.j.a(new bgk(this.j.h));
    }
  }
  
  public void m()
  {
    super.m();
    if ((this.j.h != null) && (this.j.h.br != null)) {
      this.j.h.bs.b(this.E);
    }
    Keyboard.enableRepeatEvents(false);
  }
  
  protected void a(char ☃, int ☃)
  {
    if (w != acq.g.a())
    {
      if (bch.a(this.j.u.ac)) {
        b(acq.g);
      } else {
        super.a(☃, ☃);
      }
      return;
    }
    if (this.D)
    {
      this.D = false;
      this.A.a("");
    }
    if (b(☃)) {
      return;
    }
    if (this.A.a(☃, ☃)) {
      h();
    } else {
      super.a(☃, ☃);
    }
  }
  
  private void h()
  {
    bgc.a ☃ = (bgc.a)this.h;
    ☃.a.clear();
    for (ado ☃ : ado.f) {
      if (☃ != null) {
        if (☃.b() != null) {
          ☃.a(☃, null, ☃.a);
        }
      }
    }
    for (agm ☃ : agm.b) {
      if ((☃ != null) && (☃.c != null)) {
        ads.cn.a(☃, ☃.a);
      }
    }
    Iterator<adq> ☃ = ☃.a.iterator();
    String ☃ = this.A.b().toLowerCase();
    while (☃.hasNext())
    {
      adq ☃ = (adq)☃.next();
      boolean ☃ = false;
      for (String ☃ : ☃.a(this.j.h, this.j.u.x)) {
        if (a.a(☃).toLowerCase().contains(☃))
        {
          ☃ = true;
          break;
        }
      }
      if (!☃) {
        ☃.remove();
      }
    }
    this.x = 0.0F;
    ☃.a(0.0F);
  }
  
  protected void b(int ☃, int ☃)
  {
    acq ☃ = acq.a[w];
    if (☃.h())
    {
      bni.l();
      this.q.a(bwo.a(☃.c(), new Object[0]), 8, 6, 4210752);
    }
  }
  
  protected void a(int ☃, int ☃, int ☃)
  {
    if (☃ == 0)
    {
      int ☃ = ☃ - this.i;
      int ☃ = ☃ - this.r;
      for (acq ☃ : acq.a) {
        if (a(☃, ☃, ☃)) {
          return;
        }
      }
    }
    super.a(☃, ☃, ☃);
  }
  
  protected void b(int ☃, int ☃, int ☃)
  {
    if (☃ == 0)
    {
      int ☃ = ☃ - this.i;
      int ☃ = ☃ - this.r;
      for (acq ☃ : acq.a) {
        if (a(☃, ☃, ☃))
        {
          b(☃);
          return;
        }
      }
    }
    super.b(☃, ☃, ☃);
  }
  
  private boolean i()
  {
    return (w != acq.m.a()) && (acq.a[w].j()) && (((bgc.a)this.h).e());
  }
  
  private void b(acq ☃)
  {
    int ☃ = w;
    w = ☃.a();
    bgc.a ☃ = (bgc.a)this.h;
    
    this.s.clear();
    ☃.a.clear();
    ☃.a(☃.a);
    if (☃ == acq.m)
    {
      aau ☃ = this.j.h.bs;
      if (this.B == null) {
        this.B = ☃.c;
      }
      ☃.c = Lists.newArrayList();
      for (int ☃ = 0; ☃ < ☃.c.size(); ☃++)
      {
        abt ☃ = new bgc.b((abt)☃.c.get(☃), ☃);
        ☃.c.add(☃);
        if ((☃ >= 5) && (☃ < 9))
        {
          int ☃ = ☃ - 5;
          int ☃ = ☃ / 2;
          int ☃ = ☃ % 2;
          
          ☃.f = (54 + ☃ * 54);
          ☃.g = (6 + ☃ * 27);
        }
        else if ((☃ >= 0) && (☃ < 5))
        {
          ☃.f = 63536;
          ☃.g = 63536;
        }
        else if (☃ == 45)
        {
          ☃.f = 35;
          ☃.g = 20;
        }
        else if (☃ < ☃.c.size())
        {
          int ☃ = ☃ - 9;
          int ☃ = ☃ % 9;
          int ☃ = ☃ / 9;
          
          ☃.f = (9 + ☃ * 18);
          if (☃ >= 36) {
            ☃.g = 112;
          } else {
            ☃.g = (54 + ☃ * 18);
          }
        }
      }
      this.C = new abt(v, 0, 173, 112);
      ☃.c.add(this.C);
    }
    else if (☃ == acq.m.a())
    {
      ☃.c = this.B;
      this.B = null;
    }
    if (this.A != null) {
      if (☃ == acq.g)
      {
        this.A.e(true);
        this.A.d(false);
        this.A.b(true);
        this.A.a("");
        h();
      }
      else
      {
        this.A.e(false);
        this.A.d(true);
        this.A.b(false);
      }
    }
    this.x = 0.0F;
    ☃.a(0.0F);
  }
  
  public void k()
  {
    super.k();
    
    int ☃ = Mouse.getEventDWheel();
    if ((☃ != 0) && (i()))
    {
      int ☃ = (((bgc.a)this.h).a.size() + 9 - 1) / 9 - 5;
      if (☃ > 0) {
        ☃ = 1;
      }
      if (☃ < 0) {
        ☃ = -1;
      }
      this.x = ((float)(this.x - ☃ / ☃));
      this.x = on.a(this.x, 0.0F, 1.0F);
      ((bgc.a)this.h).a(this.x);
    }
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    boolean ☃ = Mouse.isButtonDown(0);
    int ☃ = this.i;
    int ☃ = this.r;
    
    int ☃ = ☃ + 175;
    int ☃ = ☃ + 18;
    int ☃ = ☃ + 14;
    int ☃ = ☃ + 112;
    if ((!this.z) && (☃) && 
      (☃ >= ☃) && (☃ >= ☃) && (☃ < ☃) && (☃ < ☃)) {
      this.y = i();
    }
    if (!☃) {
      this.y = false;
    }
    this.z = ☃;
    if (this.y)
    {
      this.x = ((☃ - ☃ - 7.5F) / (☃ - ☃ - 15.0F));
      this.x = on.a(this.x, 0.0F, 1.0F);
      ((bgc.a)this.h).a(this.x);
    }
    super.a(☃, ☃, ☃);
    for (acq ☃ : acq.a) {
      if (b(☃, ☃, ☃)) {
        break;
      }
    }
    if ((this.C != null) && (w == acq.m.a()) && (c(this.C.f, this.C.g, 16, 16, ☃, ☃))) {
      a(bwo.a("inventory.binSlot", new Object[0]), ☃, ☃);
    }
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.g();
  }
  
  protected void a(adq ☃, int ☃, int ☃)
  {
    if (w == acq.g.a())
    {
      List<String> ☃ = ☃.a(this.j.h, this.j.u.x);
      
      acq ☃ = ☃.b().b();
      if ((☃ == null) && (☃.b() == ads.cn))
      {
        Map<agm, Integer> ☃ = ago.a(☃);
        if (☃.size() == 1)
        {
          agm ☃ = (agm)☃.keySet().iterator().next();
          for (acq ☃ : acq.a) {
            if (☃.a(☃.c))
            {
              ☃ = ☃;
              break;
            }
          }
        }
      }
      if (☃ != null) {
        ☃.add(1, "" + a.r + a.j + bwo.a(☃.c(), new Object[0]));
      }
      for (int ☃ = 0; ☃ < ☃.size(); ☃++) {
        if (☃ == 0) {
          ☃.set(☃, ☃.u().e + (String)☃.get(☃));
        } else {
          ☃.set(☃, a.h + (String)☃.get(☃));
        }
      }
      a(☃, ☃, ☃);
    }
    else
    {
      super.a(☃, ☃, ☃);
    }
  }
  
  protected void a(float ☃, int ☃, int ☃)
  {
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bcd.c();
    acq ☃ = acq.a[w];
    for (acq ☃ : acq.a)
    {
      this.j.N().a(u);
      if (☃.a() != w) {
        a(☃);
      }
    }
    this.j.N().a(new kk("textures/gui/container/creative_inventory/tab_" + ☃.g()));
    b(this.i, this.r, 0, 0, this.f, this.g);
    
    this.A.g();
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    
    int ☃ = this.i + 175;
    int ☃ = this.r + 18;
    int ☃ = ☃ + 112;
    this.j.N().a(u);
    if (☃.j()) {
      b(☃, ☃ + (int)((☃ - ☃ - 17) * this.x), 'è' + (i() ? 0 : 12), 0, 12, 15);
    }
    a(☃);
    if (☃ == acq.m) {
      bgk.a(this.i + 88, this.r + 45, 20, this.i + 88 - ☃, this.r + 45 - 30 - ☃, this.j.h);
    }
  }
  
  protected boolean a(acq ☃, int ☃, int ☃)
  {
    int ☃ = ☃.l();
    int ☃ = 28 * ☃;
    int ☃ = 0;
    if (☃ == 5) {
      ☃ = this.f - 28 + 2;
    } else if (☃ > 0) {
      ☃ += ☃;
    }
    if (☃.m()) {
      ☃ -= 32;
    } else {
      ☃ += this.g;
    }
    return (☃ >= ☃) && (☃ <= ☃ + 28) && (☃ >= ☃) && (☃ <= ☃ + 32);
  }
  
  protected boolean b(acq ☃, int ☃, int ☃)
  {
    int ☃ = ☃.l();
    int ☃ = 28 * ☃;
    int ☃ = 0;
    if (☃ == 5) {
      ☃ = this.f - 28 + 2;
    } else if (☃ > 0) {
      ☃ += ☃;
    }
    if (☃.m()) {
      ☃ -= 32;
    } else {
      ☃ += this.g;
    }
    if (c(☃ + 3, ☃ + 3, 23, 27, ☃, ☃))
    {
      a(bwo.a(☃.c(), new Object[0]), ☃, ☃);
      return true;
    }
    return false;
  }
  
  protected void a(acq ☃)
  {
    boolean ☃ = ☃.a() == w;
    boolean ☃ = ☃.m();
    int ☃ = ☃.l();
    int ☃ = ☃ * 28;
    int ☃ = 0;
    int ☃ = this.i + 28 * ☃;
    int ☃ = this.r;
    int ☃ = 32;
    if (☃) {
      ☃ += 32;
    }
    if (☃ == 5) {
      ☃ = this.i + this.f - 28;
    } else if (☃ > 0) {
      ☃ += ☃;
    }
    if (☃)
    {
      ☃ -= 28;
    }
    else
    {
      ☃ += 64;
      ☃ += this.g - 4;
    }
    bni.g();
    b(☃, ☃, ☃, ☃, 28, ☃);
    
    this.e = 100.0F;
    this.k.a = 100.0F;
    ☃ += 6;
    ☃ += 8 + (☃ ? 1 : -1);
    
    bni.f();
    bni.D();
    adq ☃ = ☃.d();
    this.k.b(☃, ☃, ☃);
    this.k.a(this.q, ☃, ☃, ☃);
    bni.g();
    
    this.k.a = 0.0F;
    this.e = 0.0F;
  }
  
  protected void a(bcz ☃)
  {
    if (☃.k == 0) {
      this.j.a(new bfm(this, this.j.h.G()));
    }
    if (☃.k == 1) {
      this.j.a(new bfn(this, this.j.h.G()));
    }
  }
  
  public int f()
  {
    return w;
  }
  
  class b
    extends abt
  {
    private final abt b;
    
    public b(abt ☃, int ☃)
    {
      super(☃, 0, 0);
      this.b = ☃;
    }
    
    public void a(zj ☃, adq ☃)
    {
      this.b.a(☃, ☃);
    }
    
    public boolean a(adq ☃)
    {
      return this.b.a(☃);
    }
    
    public adq d()
    {
      return this.b.d();
    }
    
    public boolean e()
    {
      return this.b.e();
    }
    
    public void d(adq ☃)
    {
      this.b.d(☃);
    }
    
    public void f()
    {
      this.b.f();
    }
    
    public int a()
    {
      return this.b.a();
    }
    
    public int b(adq ☃)
    {
      return this.b.b(☃);
    }
    
    public String c()
    {
      return this.b.c();
    }
    
    public adq a(int ☃)
    {
      return this.b.a(☃);
    }
    
    public boolean a(qg ☃, int ☃)
    {
      return this.b.a(☃, ☃);
    }
    
    public boolean b()
    {
      return this.b.b();
    }
    
    public boolean a(zj ☃)
    {
      return this.b.a(☃);
    }
    
    public void a(adq ☃, adq ☃)
    {
      super.a(☃, ☃);
    }
  }
}
