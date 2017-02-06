import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bhm
  extends bfb
  implements beg
{
  private static final Logger g = ;
  protected bfb a;
  protected String f = "Select world";
  private String h;
  private bcz i;
  private bcz r;
  private bcz s;
  private bcz t;
  private bho u;
  
  public bhm(bfb ☃)
  {
    this.a = ☃;
  }
  
  public void b()
  {
    this.f = bwo.a("selectWorld.title", new Object[0]);
    
    this.u = new bho(this, this.j, this.l, this.m, 32, this.m - 64, 36);
    
    a();
  }
  
  public void k()
  {
    super.k();
    this.u.p();
  }
  
  public void a()
  {
    this.n.add(this.r = new bcz(1, this.l / 2 - 154, this.m - 52, 150, 20, bwo.a("selectWorld.select", new Object[0])));
    this.n.add(new bcz(3, this.l / 2 + 4, this.m - 52, 150, 20, bwo.a("selectWorld.create", new Object[0])));
    
    this.n.add(this.s = new bcz(4, this.l / 2 - 154, this.m - 28, 72, 20, bwo.a("selectWorld.edit", new Object[0])));
    this.n.add(this.i = new bcz(2, this.l / 2 - 76, this.m - 28, 72, 20, bwo.a("selectWorld.delete", new Object[0])));
    this.n.add(this.t = new bcz(5, this.l / 2 + 4, this.m - 28, 72, 20, bwo.a("selectWorld.recreate", new Object[0])));
    this.n.add(new bcz(0, this.l / 2 + 82, this.m - 28, 72, 20, bwo.a("gui.cancel", new Object[0])));
    
    this.r.l = false;
    this.i.l = false;
    this.s.l = false;
    this.t.l = false;
  }
  
  protected void a(bcz ☃)
  {
    if (!☃.l) {
      return;
    }
    bhn ☃ = this.u.f();
    if (☃.k == 2)
    {
      if (☃ != null) {
        ☃.b();
      }
    }
    else if (☃.k == 1)
    {
      if (☃ != null) {
        ☃.a();
      }
    }
    else if (☃.k == 3) {
      this.j.a(new bhk(this));
    } else if (☃.k == 4)
    {
      if (☃ != null) {
        ☃.c();
      }
    }
    else if (☃.k == 0) {
      this.j.a(this.a);
    } else if ((☃.k == 5) && 
      (☃ != null)) {
      ☃.d();
    }
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    this.h = null;
    
    this.u.a(☃, ☃, ☃);
    
    a(this.q, this.f, this.l / 2, 20, 16777215);
    
    super.a(☃, ☃, ☃);
    if (this.h != null) {
      a(Lists.newArrayList(Splitter.on("\n").split(this.h)), ☃, ☃);
    }
  }
  
  protected void a(int ☃, int ☃, int ☃)
  {
    super.a(☃, ☃, ☃);
    this.u.b(☃, ☃, ☃);
  }
  
  protected void b(int ☃, int ☃, int ☃)
  {
    super.b(☃, ☃, ☃);
    this.u.c(☃, ☃, ☃);
  }
  
  public void a(String ☃)
  {
    this.h = ☃;
  }
  
  public void a(bhn ☃)
  {
    boolean ☃ = ☃ != null;
    this.r.l = ☃;
    this.i.l = ☃;
    this.s.l = ☃;
    this.t.l = ☃;
  }
}
