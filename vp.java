import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;

public class vp
{
  private aht a;
  private final List<vo> b = Lists.newArrayList();
  private cj c = cj.a;
  private cj d = cj.a;
  private int e;
  private int f;
  private int g;
  private int h;
  private int i;
  private TreeMap<String, Integer> j = new TreeMap();
  public vp() {}
  
  class a
  {
    public sa a;
    public int b;
    
    a(sa ☃, int ☃)
    {
      this.a = ☃;
      this.b = ☃;
    }
  }
  
  private List<vp.a> k = Lists.newArrayList();
  private int l;
  
  public vp(aht ☃)
  {
    this.a = ☃;
  }
  
  public void a(aht ☃)
  {
    this.a = ☃;
  }
  
  public void a(int ☃)
  {
    this.g = ☃;
    m();
    l();
    if (☃ % 20 == 0) {
      k();
    }
    if (☃ % 30 == 0) {
      j();
    }
    int ☃ = this.h / 10;
    if ((this.l < ☃) && (this.b.size() > 20) && (this.a.r.nextInt(7000) == 0))
    {
      bbj ☃ = a(this.d, 2, 4, 2);
      if (☃ != null)
      {
        wh ☃ = new wh(this.a);
        ☃.b(☃.b, ☃.c, ☃.d);
        this.a.a(☃);
        this.l += 1;
      }
    }
  }
  
  private bbj a(cj ☃, int ☃, int ☃, int ☃)
  {
    for (int ☃ = 0; ☃ < 10; ☃++)
    {
      cj ☃ = ☃.a(this.a.r.nextInt(16) - 8, this.a.r.nextInt(6) - 3, this.a.r.nextInt(16) - 8);
      if (a(☃)) {
        if (a(new cj(☃, ☃, ☃), ☃)) {
          return new bbj(☃.p(), ☃.q(), ☃.r());
        }
      }
    }
    return null;
  }
  
  private boolean a(cj ☃, cj ☃)
  {
    if (!this.a.o(☃.b()).q()) {
      return false;
    }
    int ☃ = ☃.p() - ☃.p() / 2;
    int ☃ = ☃.r() - ☃.r() / 2;
    for (int ☃ = ☃; ☃ < ☃ + ☃.p(); ☃++) {
      for (int ☃ = ☃.q(); ☃ < ☃.q() + ☃.q(); ☃++) {
        for (int ☃ = ☃; ☃ < ☃ + ☃.r(); ☃++) {
          if (this.a.o(new cj(☃, ☃, ☃)).l()) {
            return false;
          }
        }
      }
    }
    return true;
  }
  
  private void j()
  {
    List<wh> ☃ = this.a.a(wh.class, new bbh(this.d.p() - this.e, this.d.q() - 4, this.d.r() - this.e, this.d.p() + this.e, this.d.q() + 4, this.d.r() + this.e));
    this.l = ☃.size();
  }
  
  private void k()
  {
    List<ze> ☃ = this.a.a(ze.class, new bbh(this.d.p() - this.e, this.d.q() - 4, this.d.r() - this.e, this.d.p() + this.e, this.d.q() + 4, this.d.r() + this.e));
    this.h = ☃.size();
    if (this.h == 0) {
      this.j.clear();
    }
  }
  
  public cj a()
  {
    return this.d;
  }
  
  public int b()
  {
    return this.e;
  }
  
  public int c()
  {
    return this.b.size();
  }
  
  public int d()
  {
    return this.g - this.f;
  }
  
  public int e()
  {
    return this.h;
  }
  
  public boolean a(cj ☃)
  {
    return this.d.k(☃) < this.e * this.e;
  }
  
  public List<vo> f()
  {
    return this.b;
  }
  
  public vo b(cj ☃)
  {
    vo ☃ = null;
    int ☃ = Integer.MAX_VALUE;
    for (vo ☃ : this.b)
    {
      int ☃ = ☃.a(☃);
      if (☃ < ☃)
      {
        ☃ = ☃;
        ☃ = ☃;
      }
    }
    return ☃;
  }
  
  public vo c(cj ☃)
  {
    vo ☃ = null;
    int ☃ = Integer.MAX_VALUE;
    for (vo ☃ : this.b)
    {
      int ☃ = ☃.a(☃);
      if (☃ > 256) {
        ☃ *= 1000;
      } else {
        ☃ = ☃.c();
      }
      if (☃ < ☃)
      {
        cj ☃ = ☃.d();
        cq ☃ = ☃.j();
        if ((this.a.o(☃.a(☃, 1)).t().b(this.a, ☃.a(☃, 1))) && (this.a.o(☃.a(☃, -1)).t().b(this.a, ☃.a(☃, -1))) && (this.a.o(☃.a().a(☃, 1)).t().b(this.a, ☃.a().a(☃, 1))) && (this.a.o(☃.a().a(☃, -1)).t().b(this.a, ☃.a().a(☃, -1))))
        {
          ☃ = ☃;
          ☃ = ☃;
        }
      }
    }
    return ☃;
  }
  
  public vo e(cj ☃)
  {
    if (this.d.k(☃) > this.e * this.e) {
      return null;
    }
    for (vo ☃ : this.b) {
      if ((☃.d().p() == ☃.p()) && (☃.d().r() == ☃.r()) && (Math.abs(☃.d().q() - ☃.q()) <= 1)) {
        return ☃;
      }
    }
    return null;
  }
  
  public void a(vo ☃)
  {
    this.b.add(☃);
    this.c = this.c.a(☃.d());
    n();
    this.f = ☃.h();
  }
  
  public boolean g()
  {
    return this.b.isEmpty();
  }
  
  public void a(sa ☃)
  {
    for (vp.a ☃ : this.k) {
      if (☃.a == ☃)
      {
        ☃.b = this.g;
        return;
      }
    }
    this.k.add(new vp.a(☃, this.g));
  }
  
  public sa b(sa ☃)
  {
    double ☃ = Double.MAX_VALUE;
    vp.a ☃ = null;
    for (int ☃ = 0; ☃ < this.k.size(); ☃++)
    {
      vp.a ☃ = (vp.a)this.k.get(☃);
      double ☃ = ☃.a.h(☃);
      if (☃ <= ☃)
      {
        ☃ = ☃;
        ☃ = ☃;
      }
    }
    return ☃ != null ? ☃.a : null;
  }
  
  public zj c(sa ☃)
  {
    double ☃ = Double.MAX_VALUE;
    zj ☃ = null;
    for (String ☃ : this.j.keySet()) {
      if (d(☃))
      {
        zj ☃ = this.a.a(☃);
        if (☃ != null)
        {
          double ☃ = ☃.h(☃);
          if (☃ <= ☃)
          {
            ☃ = ☃;
            ☃ = ☃;
          }
        }
      }
    }
    return ☃;
  }
  
  private void l()
  {
    Iterator<vp.a> ☃ = this.k.iterator();
    while (☃.hasNext())
    {
      vp.a ☃ = (vp.a)☃.next();
      if ((!☃.a.au()) || (Math.abs(this.g - ☃.b) > 300)) {
        ☃.remove();
      }
    }
  }
  
  private void m()
  {
    boolean ☃ = false;
    boolean ☃ = this.a.r.nextInt(50) == 0;
    for (Iterator<vo> ☃ = this.b.iterator(); ☃.hasNext();)
    {
      vo ☃ = (vo)☃.next();
      if (☃) {
        ☃.a();
      }
      if ((!f(☃.d())) || (Math.abs(this.g - ☃.h()) > 1200))
      {
        this.c = this.c.b(☃.d());
        ☃ = true;
        ☃.a(true);
        ☃.remove();
      }
    }
    if (☃) {
      n();
    }
  }
  
  private boolean f(cj ☃)
  {
    arc ☃ = this.a.o(☃);
    ajt ☃ = ☃.t();
    if ((☃ instanceof akv)) {
      return ☃.a() == axe.d;
    }
    return false;
  }
  
  private void n()
  {
    int ☃ = this.b.size();
    if (☃ == 0)
    {
      this.d = cj.a;
      this.e = 0;
      return;
    }
    this.d = new cj(this.c.p() / ☃, this.c.q() / ☃, this.c.r() / ☃);
    int ☃ = 0;
    for (vo ☃ : this.b) {
      ☃ = Math.max(☃.a(this.d), ☃);
    }
    this.e = Math.max(32, (int)Math.sqrt(☃) + 1);
  }
  
  public int a(String ☃)
  {
    Integer ☃ = (Integer)this.j.get(☃);
    if (☃ != null) {
      return ☃.intValue();
    }
    return 0;
  }
  
  public int a(String ☃, int ☃)
  {
    int ☃ = a(☃);
    int ☃ = on.a(☃ + ☃, -30, 10);
    this.j.put(☃, Integer.valueOf(☃));
    return ☃;
  }
  
  public boolean d(String ☃)
  {
    return a(☃) <= -15;
  }
  
  public void a(dn ☃)
  {
    this.h = ☃.h("PopSize");
    this.e = ☃.h("Radius");
    this.l = ☃.h("Golems");
    this.f = ☃.h("Stable");
    this.g = ☃.h("Tick");
    this.i = ☃.h("MTick");
    this.d = new cj(☃.h("CX"), ☃.h("CY"), ☃.h("CZ"));
    this.c = new cj(☃.h("ACX"), ☃.h("ACY"), ☃.h("ACZ"));
    
    du ☃ = ☃.c("Doors", 10);
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      dn ☃ = ☃.b(☃);
      
      vo ☃ = new vo(new cj(☃.h("X"), ☃.h("Y"), ☃.h("Z")), ☃.h("IDX"), ☃.h("IDZ"), ☃.h("TS"));
      this.b.add(☃);
    }
    du ☃ = ☃.c("Players", 10);
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      dn ☃ = ☃.b(☃);
      if (☃.e("UUID"))
      {
        mi ☃ = this.a.u().aA();
        GameProfile ☃ = ☃.a(UUID.fromString(☃.l("UUID")));
        if (☃ != null) {
          this.j.put(☃.getName(), Integer.valueOf(☃.h("S")));
        }
      }
      else
      {
        this.j.put(☃.l("Name"), Integer.valueOf(☃.h("S")));
      }
    }
  }
  
  public void b(dn ☃)
  {
    ☃.a("PopSize", this.h);
    ☃.a("Radius", this.e);
    ☃.a("Golems", this.l);
    ☃.a("Stable", this.f);
    ☃.a("Tick", this.g);
    ☃.a("MTick", this.i);
    ☃.a("CX", this.d.p());
    ☃.a("CY", this.d.q());
    ☃.a("CZ", this.d.r());
    ☃.a("ACX", this.c.p());
    ☃.a("ACY", this.c.q());
    ☃.a("ACZ", this.c.r());
    
    du ☃ = new du();
    for (vo ☃ : this.b)
    {
      dn ☃ = new dn();
      ☃.a("X", ☃.d().p());
      ☃.a("Y", ☃.d().q());
      ☃.a("Z", ☃.d().r());
      ☃.a("IDX", ☃.f());
      ☃.a("IDZ", ☃.g());
      ☃.a("TS", ☃.h());
      ☃.a(☃);
    }
    ☃.a("Doors", ☃);
    
    du ☃ = new du();
    for (String ☃ : this.j.keySet())
    {
      dn ☃ = new dn();
      
      mi ☃ = this.a.u().aA();
      GameProfile ☃ = ☃.a(☃);
      if (☃ != null)
      {
        ☃.a("UUID", ☃.getId().toString());
        ☃.a("S", ((Integer)this.j.get(☃)).intValue());
        ☃.a(☃);
      }
    }
    ☃.a("Players", ☃);
  }
  
  public void h()
  {
    this.i = this.g;
  }
  
  public boolean i()
  {
    return (this.i == 0) || (this.g - this.i >= 3600);
  }
  
  public void b(int ☃)
  {
    for (String ☃ : this.j.keySet()) {
      a(☃, ☃);
    }
  }
}
