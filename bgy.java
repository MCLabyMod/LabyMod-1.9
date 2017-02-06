import com.google.common.collect.Lists;
import java.io.File;
import java.util.Collections;
import java.util.List;

public class bgy
  extends bfb
{
  private final bfb a;
  private List<bgz> f;
  private List<bgz> g;
  private bhe h;
  private bhg i;
  private boolean r = false;
  
  public bgy(bfb ☃)
  {
    this.a = ☃;
  }
  
  public void b()
  {
    this.n.add(new bdm(2, this.l / 2 - 154, this.m - 48, bwo.a("resourcePack.openFolder", new Object[0])));
    this.n.add(new bdm(1, this.l / 2 + 4, this.m - 48, bwo.a("gui.done", new Object[0])));
    if (!this.r)
    {
      this.f = Lists.newArrayList();
      this.g = Lists.newArrayList();
      
      bwk ☃ = this.j.P();
      ☃.a();
      List<bwk.a> ☃ = Lists.newArrayList(☃.c());
      ☃.removeAll(☃.d());
      for (bwk.a ☃ : ☃) {
        this.f.add(new bhb(this, ☃));
      }
      bwk.a ☃ = ☃.b();
      if (☃ != null) {
        this.g.add(new bhc(this, ☃.f()));
      }
      for (bwk.a ☃ : Lists.reverse(☃.d())) {
        this.g.add(new bhb(this, ☃));
      }
      this.g.add(new bha(this));
    }
    this.h = new bhe(this.j, 200, this.m, this.f);
    this.h.i(this.l / 2 - 4 - 200);
    this.h.d(7, 8);
    this.i = new bhg(this.j, 200, this.m, this.g);
    this.i.i(this.l / 2 + 4);
    this.i.d(7, 8);
  }
  
  public void k()
  {
    super.k();
    this.i.p();
    this.h.p();
  }
  
  public boolean a(bgz ☃)
  {
    return this.g.contains(☃);
  }
  
  public List<bgz> b(bgz ☃)
  {
    if (a(☃)) {
      return this.g;
    }
    return this.f;
  }
  
  public List<bgz> a()
  {
    return this.f;
  }
  
  public List<bgz> f()
  {
    return this.g;
  }
  
  protected void a(bcz ☃)
  {
    if (!☃.l) {
      return;
    }
    if (☃.k == 2)
    {
      File ☃ = this.j.P().e();
      
      bzg.a(☃);
    }
    else if (☃.k == 1)
    {
      if (this.r)
      {
        List<bwk.a> ☃ = Lists.newArrayList();
        for (bgz ☃ : this.g) {
          if ((☃ instanceof bhb)) {
            ☃.add(((bhb)☃).l());
          }
        }
        Collections.reverse(☃);
        this.j.P().a(☃);
        this.j.u.k.clear();
        this.j.u.l.clear();
        for (bwk.a ☃ : ☃)
        {
          this.j.u.k.add(☃.d());
          if (☃.f() != 2) {
            this.j.u.l.add(☃.d());
          }
        }
        this.j.u.b();
        this.j.f();
      }
      this.j.a(this.a);
    }
  }
  
  protected void a(int ☃, int ☃, int ☃)
  {
    super.a(☃, ☃, ☃);
    this.h.b(☃, ☃, ☃);
    this.i.b(☃, ☃, ☃);
  }
  
  protected void b(int ☃, int ☃, int ☃)
  {
    super.b(☃, ☃, ☃);
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    c(0);
    
    this.h.a(☃, ☃, ☃);
    this.i.a(☃, ☃, ☃);
    
    a(this.q, bwo.a("resourcePack.title", new Object[0]), this.l / 2, 16, 16777215);
    a(this.q, bwo.a("resourcePack.folderInfo", new Object[0]), this.l / 2 - 77, this.m - 26, 8421504);
    
    super.a(☃, ☃, ☃);
  }
  
  public void g()
  {
    this.r = true;
  }
}
