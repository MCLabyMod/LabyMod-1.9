import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.lwjgl.input.Mouse;

public class bfn
  extends bfb
  implements bfo
{
  protected bfb a;
  protected String f = "Select world";
  private bfn.b g;
  private bfn.c h;
  private bfn.a i;
  private bfn.d r;
  private nu s;
  private bdq t;
  private boolean u = true;
  
  public bfn(bfb ☃, nu ☃)
  {
    this.a = ☃;
    this.s = ☃;
  }
  
  public void b()
  {
    this.f = bwo.a("gui.stats", new Object[0]);
    
    this.u = true;
    this.j.v().a(new ik(ik.a.b));
  }
  
  public void k()
  {
    super.k();
    if (this.t != null) {
      this.t.p();
    }
  }
  
  public void f()
  {
    this.g = new bfn.b(this.j);
    this.g.d(1, 1);
    
    this.h = new bfn.c(this.j);
    this.h.d(1, 1);
    
    this.i = new bfn.a(this.j);
    this.i.d(1, 1);
    
    this.r = new bfn.d(this.j);
    this.r.d(1, 1);
  }
  
  public void g()
  {
    this.n.add(new bcz(0, this.l / 2 + 4, this.m - 28, 150, 20, bwo.a("gui.done", new Object[0])));
    
    this.n.add(new bcz(1, this.l / 2 - 160, this.m - 52, 80, 20, bwo.a("stat.generalButton", new Object[0])));
    bcz ☃;
    this.n.add(☃ = new bcz(2, this.l / 2 - 80, this.m - 52, 80, 20, bwo.a("stat.blocksButton", new Object[0])));
    bcz ☃;
    this.n.add(☃ = new bcz(3, this.l / 2, this.m - 52, 80, 20, bwo.a("stat.itemsButton", new Object[0])));
    bcz ☃;
    this.n.add(☃ = new bcz(4, this.l / 2 + 80, this.m - 52, 80, 20, bwo.a("stat.mobsButton", new Object[0])));
    if (this.i.b() == 0) {
      ☃.l = false;
    }
    if (this.h.b() == 0) {
      ☃.l = false;
    }
    if (this.r.b() == 0) {
      ☃.l = false;
    }
  }
  
  protected void a(bcz ☃)
  {
    if (!☃.l) {
      return;
    }
    if (☃.k == 0) {
      this.j.a(this.a);
    } else if (☃.k == 1) {
      this.t = this.g;
    } else if (☃.k == 3) {
      this.t = this.h;
    } else if (☃.k == 2) {
      this.t = this.i;
    } else if (☃.k == 4) {
      this.t = this.r;
    } else {
      this.t.a(☃);
    }
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    if (this.u)
    {
      c();
      a(this.q, bwo.a("multiplayer.downloadingStats", new Object[0]), this.l / 2, this.m / 2, 16777215);
      a(this.q, c_[((int)(bcf.I() / 150L % c_.length))], this.l / 2, this.m / 2 + this.q.a * 2, 16777215);
    }
    else
    {
      this.t.a(☃, ☃, ☃);
      a(this.q, this.f, this.l / 2, 20, 16777215);
      super.a(☃, ☃, ☃);
    }
  }
  
  public void a()
  {
    if (this.u)
    {
      f();
      g();
      this.t = this.g;
      this.u = false;
    }
  }
  
  public boolean d()
  {
    return !this.u;
  }
  
  class b
    extends bdq
  {
    public b(bcf ☃)
    {
      super(bfn.this.l, bfn.this.m, 32, bfn.this.m - 64, 10);
      
      b(false);
    }
    
    protected int b()
    {
      return nt.c.size();
    }
    
    protected void a(int ☃, boolean ☃, int ☃, int ☃) {}
    
    protected boolean a(int ☃)
    {
      return false;
    }
    
    protected int k()
    {
      return b() * 10;
    }
    
    protected void a()
    {
      bfn.this.c();
    }
    
    protected void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
    {
      np ☃ = (np)nt.c.get(☃);
      bfn.this.c(bfn.a(bfn.this), ☃.e().c(), ☃ + 2, ☃ + 1, ☃ % 2 == 0 ? 16777215 : 9474192);
      String ☃ = ☃.a(bfn.b(bfn.this).a(☃));
      bfn.this.c(bfn.c(bfn.this), ☃, ☃ + 2 + 213 - bfn.d(bfn.this).a(☃), ☃ + 1, ☃ % 2 == 0 ? 16777215 : 9474192);
    }
  }
  
  private void a(int ☃, int ☃, ado ☃)
  {
    b(☃ + 1, ☃ + 1);
    
    bni.D();
    
    bcd.c();
    
    this.k.a(new adq(☃), ☃ + 2, ☃ + 2);
    bcd.a();
    
    bni.E();
  }
  
  private void b(int ☃, int ☃)
  {
    c(☃, ☃, 0, 0);
  }
  
  private void c(int ☃, int ☃, int ☃, int ☃)
  {
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    this.j.N().a(c);
    
    float ☃ = 0.0078125F;
    float ☃ = 0.0078125F;
    int ☃ = 18;
    int ☃ = 18;
    bnu ☃ = bnu.a();
    bmz ☃ = ☃.c();
    ☃.a(7, bvp.g);
    ☃.b(☃ + 0, ☃ + 18, this.e).a((☃ + 0) * 0.0078125F, (☃ + 18) * 0.0078125F).d();
    ☃.b(☃ + 18, ☃ + 18, this.e).a((☃ + 18) * 0.0078125F, (☃ + 18) * 0.0078125F).d();
    ☃.b(☃ + 18, ☃ + 0, this.e).a((☃ + 18) * 0.0078125F, (☃ + 0) * 0.0078125F).d();
    ☃.b(☃ + 0, ☃ + 0, this.e).a((☃ + 0) * 0.0078125F, (☃ + 0) * 0.0078125F).d();
    ☃.b();
  }
  
  abstract class e
    extends bdq
  {
    protected int v = -1;
    protected List<nn> w;
    protected Comparator<nn> x;
    protected int y = -1;
    protected int z;
    
    protected e(bcf ☃)
    {
      super(bfn.this.l, bfn.this.m, 32, bfn.this.m - 64, 20);
      
      b(false);
      a(true, 20);
    }
    
    protected void a(int ☃, boolean ☃, int ☃, int ☃) {}
    
    protected boolean a(int ☃)
    {
      return false;
    }
    
    public int c()
    {
      return 375;
    }
    
    protected int d()
    {
      return this.b / 2 + 140;
    }
    
    protected void a()
    {
      bfn.this.c();
    }
    
    protected void a(int ☃, int ☃, bnu ☃)
    {
      if (!Mouse.isButtonDown(0)) {
        this.v = -1;
      }
      if (this.v == 0) {
        bfn.a(bfn.this, ☃ + 115 - 18, ☃ + 1, 0, 0);
      } else {
        bfn.a(bfn.this, ☃ + 115 - 18, ☃ + 1, 0, 18);
      }
      if (this.v == 1) {
        bfn.a(bfn.this, ☃ + 165 - 18, ☃ + 1, 0, 0);
      } else {
        bfn.a(bfn.this, ☃ + 165 - 18, ☃ + 1, 0, 18);
      }
      if (this.v == 2) {
        bfn.a(bfn.this, ☃ + 215 - 18, ☃ + 1, 0, 0);
      } else {
        bfn.a(bfn.this, ☃ + 215 - 18, ☃ + 1, 0, 18);
      }
      if (this.v == 3) {
        bfn.a(bfn.this, ☃ + 265 - 18, ☃ + 1, 0, 0);
      } else {
        bfn.a(bfn.this, ☃ + 265 - 18, ☃ + 1, 0, 18);
      }
      if (this.v == 4) {
        bfn.a(bfn.this, ☃ + 315 - 18, ☃ + 1, 0, 0);
      } else {
        bfn.a(bfn.this, ☃ + 315 - 18, ☃ + 1, 0, 18);
      }
      if (this.y != -1)
      {
        int ☃ = 79;
        int ☃ = 18;
        if (this.y == 1) {
          ☃ = 129;
        } else if (this.y == 2) {
          ☃ = 179;
        } else if (this.y == 3) {
          ☃ = 229;
        } else if (this.y == 4) {
          ☃ = 279;
        }
        if (this.z == 1) {
          ☃ = 36;
        }
        bfn.a(bfn.this, ☃ + ☃, ☃ + 1, ☃, 0);
      }
    }
    
    protected void a(int ☃, int ☃)
    {
      this.v = -1;
      if ((☃ >= 79) && (☃ < 115)) {
        this.v = 0;
      } else if ((☃ >= 129) && (☃ < 165)) {
        this.v = 1;
      } else if ((☃ >= 179) && (☃ < 215)) {
        this.v = 2;
      } else if ((☃ >= 229) && (☃ < 265)) {
        this.v = 3;
      } else if ((☃ >= 279) && (☃ < 315)) {
        this.v = 4;
      }
      if (this.v >= 0)
      {
        d(this.v);
        this.a.U().a(bye.a(ng.go, 1.0F));
      }
    }
    
    protected final int b()
    {
      return this.w.size();
    }
    
    protected final nn c(int ☃)
    {
      return (nn)this.w.get(☃);
    }
    
    protected abstract String b(int paramInt);
    
    protected void a(np ☃, int ☃, int ☃, boolean ☃)
    {
      if (☃ != null)
      {
        String ☃ = ☃.a(bfn.b(bfn.this).a(☃));
        bfn.this.c(bfn.e(bfn.this), ☃, ☃ - bfn.f(bfn.this).a(☃), ☃ + 5, ☃ ? 16777215 : 9474192);
      }
      else
      {
        String ☃ = "-";
        bfn.this.c(bfn.g(bfn.this), ☃, ☃ - bfn.h(bfn.this).a(☃), ☃ + 5, ☃ ? 16777215 : 9474192);
      }
    }
    
    protected void b(int ☃, int ☃)
    {
      if ((☃ < this.d) || (☃ > this.e)) {
        return;
      }
      int ☃ = c(☃, ☃);
      int ☃ = (this.b - c()) / 2;
      if (☃ >= 0)
      {
        if ((☃ < ☃ + 40) || (☃ > ☃ + 40 + 20)) {
          return;
        }
        nn ☃ = c(☃);
        a(☃, ☃, ☃);
      }
      else
      {
        String ☃ = "";
        if ((☃ >= ☃ + 115 - 18) && (☃ <= ☃ + 115)) {
          ☃ = b(0);
        } else if ((☃ >= ☃ + 165 - 18) && (☃ <= ☃ + 165)) {
          ☃ = b(1);
        } else if ((☃ >= ☃ + 215 - 18) && (☃ <= ☃ + 215)) {
          ☃ = b(2);
        } else if ((☃ >= ☃ + 265 - 18) && (☃ <= ☃ + 265)) {
          ☃ = b(3);
        } else if ((☃ >= ☃ + 315 - 18) && (☃ <= ☃ + 315)) {
          ☃ = b(4);
        } else {
          return;
        }
        ☃ = ("" + bwo.a(☃, new Object[0])).trim();
        if (!☃.isEmpty())
        {
          int ☃ = ☃ + 12;
          int ☃ = ☃ - 12;
          int ☃ = bfn.i(bfn.this).a(☃);
          bfn.a(bfn.this, ☃ - 3, ☃ - 3, ☃ + ☃ + 3, ☃ + 8 + 3, -1073741824, -1073741824);
          
          bfn.j(bfn.this).a(☃, ☃, ☃, -1);
        }
      }
    }
    
    protected void a(nn ☃, int ☃, int ☃)
    {
      if (☃ == null) {
        return;
      }
      ado ☃ = ☃.a();
      adq ☃ = new adq(☃);
      String ☃ = ☃.a();
      
      String ☃ = ("" + bwo.a(new StringBuilder().append(☃).append(".name").toString(), new Object[0])).trim();
      if (!☃.isEmpty())
      {
        int ☃ = ☃ + 12;
        int ☃ = ☃ - 12;
        int ☃ = bfn.k(bfn.this).a(☃);
        bfn.b(bfn.this, ☃ - 3, ☃ - 3, ☃ + ☃ + 3, ☃ + 8 + 3, -1073741824, -1073741824);
        
        bfn.l(bfn.this).a(☃, ☃, ☃, -1);
      }
    }
    
    protected void d(int ☃)
    {
      if (☃ != this.y)
      {
        this.y = ☃;
        this.z = -1;
      }
      else if (this.z == -1)
      {
        this.z = 1;
      }
      else
      {
        this.y = -1;
        this.z = 0;
      }
      Collections.sort(this.w, this.x);
    }
  }
  
  class c
    extends bfn.e
  {
    public c(bcf ☃)
    {
      super(☃);
      
      this.w = Lists.newArrayList();
      for (nn ☃ : nt.d)
      {
        boolean ☃ = false;
        ado ☃ = ☃.a();
        if (bfn.b(bfn.this).a(☃) > 0) {
          ☃ = true;
        } else if ((nt.c(☃) != null) && (bfn.b(bfn.this).a(nt.c(☃)) > 0)) {
          ☃ = true;
        } else if ((nt.a(☃) != null) && (bfn.b(bfn.this).a(nt.a(☃)) > 0)) {
          ☃ = true;
        } else if ((nt.d(☃) != null) && (bfn.b(bfn.this).a(nt.d(☃)) > 0)) {
          ☃ = true;
        } else if ((nt.e(☃) != null) && (bfn.b(bfn.this).a(nt.e(☃)) > 0)) {
          ☃ = true;
        }
        if (☃) {
          this.w.add(☃);
        }
      }
      this.x = new Comparator()
      {
        public int a(nn ☃, nn ☃)
        {
          ado ☃ = ☃.a();
          ado ☃ = ☃.a();
          
          int ☃ = ado.a(☃);
          int ☃ = ado.a(☃);
          
          np ☃ = null;
          np ☃ = null;
          if (bfn.c.this.y == 0)
          {
            ☃ = nt.c(☃);
            ☃ = nt.c(☃);
          }
          else if (bfn.c.this.y == 1)
          {
            ☃ = nt.a(☃);
            ☃ = nt.a(☃);
          }
          else if (bfn.c.this.y == 2)
          {
            ☃ = nt.b(☃);
            ☃ = nt.b(☃);
          }
          else if (bfn.c.this.y == 3)
          {
            ☃ = nt.d(☃);
            ☃ = nt.d(☃);
          }
          else if (bfn.c.this.y == 4)
          {
            ☃ = nt.e(☃);
            ☃ = nt.e(☃);
          }
          if ((☃ != null) || (☃ != null))
          {
            if (☃ == null) {
              return 1;
            }
            if (☃ == null) {
              return -1;
            }
            int ☃ = bfn.b(bfn.this).a(☃);
            int ☃ = bfn.b(bfn.this).a(☃);
            if (☃ != ☃) {
              return (☃ - ☃) * bfn.c.this.z;
            }
          }
          return ☃ - ☃;
        }
      };
    }
    
    protected void a(int ☃, int ☃, bnu ☃)
    {
      super.a(☃, ☃, ☃);
      if (this.v == 0) {
        bfn.a(bfn.this, ☃ + 115 - 18 + 1, ☃ + 1 + 1, 72, 18);
      } else {
        bfn.a(bfn.this, ☃ + 115 - 18, ☃ + 1, 72, 18);
      }
      if (this.v == 1) {
        bfn.a(bfn.this, ☃ + 165 - 18 + 1, ☃ + 1 + 1, 18, 18);
      } else {
        bfn.a(bfn.this, ☃ + 165 - 18, ☃ + 1, 18, 18);
      }
      if (this.v == 2) {
        bfn.a(bfn.this, ☃ + 215 - 18 + 1, ☃ + 1 + 1, 36, 18);
      } else {
        bfn.a(bfn.this, ☃ + 215 - 18, ☃ + 1, 36, 18);
      }
      if (this.v == 3) {
        bfn.a(bfn.this, ☃ + 265 - 18 + 1, ☃ + 1 + 1, 90, 18);
      } else {
        bfn.a(bfn.this, ☃ + 265 - 18, ☃ + 1, 90, 18);
      }
      if (this.v == 4) {
        bfn.a(bfn.this, ☃ + 315 - 18 + 1, ☃ + 1 + 1, 108, 18);
      } else {
        bfn.a(bfn.this, ☃ + 315 - 18, ☃ + 1, 108, 18);
      }
    }
    
    protected void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
    {
      nn ☃ = c(☃);
      
      ado ☃ = ☃.a();
      bfn.a(bfn.this, ☃ + 40, ☃, ☃);
      
      a(nt.c(☃), ☃ + 115, ☃, ☃ % 2 == 0);
      a(nt.a(☃), ☃ + 165, ☃, ☃ % 2 == 0);
      a(☃, ☃ + 215, ☃, ☃ % 2 == 0);
      a(nt.d(☃), ☃ + 265, ☃, ☃ % 2 == 0);
      a(nt.e(☃), ☃ + 315, ☃, ☃ % 2 == 0);
    }
    
    protected String b(int ☃)
    {
      if (☃ == 1) {
        return "stat.crafted";
      }
      if (☃ == 2) {
        return "stat.used";
      }
      if (☃ == 3) {
        return "stat.pickup";
      }
      if (☃ == 4) {
        return "stat.dropped";
      }
      return "stat.depleted";
    }
  }
  
  class a
    extends bfn.e
  {
    public a(bcf ☃)
    {
      super(☃);
      
      this.w = Lists.newArrayList();
      for (nn ☃ : nt.e)
      {
        boolean ☃ = false;
        ado ☃ = ☃.a();
        if (bfn.b(bfn.this).a(☃) > 0) {
          ☃ = true;
        } else if ((nt.b(☃) != null) && (bfn.b(bfn.this).a(nt.b(☃)) > 0)) {
          ☃ = true;
        } else if ((nt.a(☃) != null) && (bfn.b(bfn.this).a(nt.a(☃)) > 0)) {
          ☃ = true;
        } else if ((nt.d(☃) != null) && (bfn.b(bfn.this).a(nt.d(☃)) > 0)) {
          ☃ = true;
        } else if ((nt.e(☃) != null) && (bfn.b(bfn.this).a(nt.e(☃)) > 0)) {
          ☃ = true;
        }
        if (☃) {
          this.w.add(☃);
        }
      }
      this.x = new Comparator()
      {
        public int a(nn ☃, nn ☃)
        {
          ado ☃ = ☃.a();
          ado ☃ = ☃.a();
          
          np ☃ = null;
          np ☃ = null;
          if (bfn.a.this.y == 2)
          {
            ☃ = nt.a(ajt.a(☃));
            ☃ = nt.a(ajt.a(☃));
          }
          else if (bfn.a.this.y == 0)
          {
            ☃ = nt.a(☃);
            ☃ = nt.a(☃);
          }
          else if (bfn.a.this.y == 1)
          {
            ☃ = nt.b(☃);
            ☃ = nt.b(☃);
          }
          else if (bfn.a.this.y == 3)
          {
            ☃ = nt.d(☃);
            ☃ = nt.d(☃);
          }
          else if (bfn.a.this.y == 4)
          {
            ☃ = nt.e(☃);
            ☃ = nt.e(☃);
          }
          if ((☃ != null) || (☃ != null))
          {
            if (☃ == null) {
              return 1;
            }
            if (☃ == null) {
              return -1;
            }
            int ☃ = bfn.b(bfn.this).a(☃);
            int ☃ = bfn.b(bfn.this).a(☃);
            if (☃ != ☃) {
              return (☃ - ☃) * bfn.a.this.z;
            }
          }
          return ado.a(☃) - ado.a(☃);
        }
      };
    }
    
    protected void a(int ☃, int ☃, bnu ☃)
    {
      super.a(☃, ☃, ☃);
      if (this.v == 0) {
        bfn.a(bfn.this, ☃ + 115 - 18 + 1, ☃ + 1 + 1, 18, 18);
      } else {
        bfn.a(bfn.this, ☃ + 115 - 18, ☃ + 1, 18, 18);
      }
      if (this.v == 1) {
        bfn.a(bfn.this, ☃ + 165 - 18 + 1, ☃ + 1 + 1, 36, 18);
      } else {
        bfn.a(bfn.this, ☃ + 165 - 18, ☃ + 1, 36, 18);
      }
      if (this.v == 2) {
        bfn.a(bfn.this, ☃ + 215 - 18 + 1, ☃ + 1 + 1, 54, 18);
      } else {
        bfn.a(bfn.this, ☃ + 215 - 18, ☃ + 1, 54, 18);
      }
      if (this.v == 3) {
        bfn.a(bfn.this, ☃ + 265 - 18 + 1, ☃ + 1 + 1, 90, 18);
      } else {
        bfn.a(bfn.this, ☃ + 265 - 18, ☃ + 1, 90, 18);
      }
      if (this.v == 4) {
        bfn.a(bfn.this, ☃ + 315 - 18 + 1, ☃ + 1 + 1, 108, 18);
      } else {
        bfn.a(bfn.this, ☃ + 315 - 18, ☃ + 1, 108, 18);
      }
    }
    
    protected void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
    {
      nn ☃ = c(☃);
      ado ☃ = ☃.a();
      
      bfn.a(bfn.this, ☃ + 40, ☃, ☃);
      
      a(nt.a(☃), ☃ + 115, ☃, ☃ % 2 == 0);
      a(nt.b(☃), ☃ + 165, ☃, ☃ % 2 == 0);
      a(☃, ☃ + 215, ☃, ☃ % 2 == 0);
      a(nt.d(☃), ☃ + 265, ☃, ☃ % 2 == 0);
      a(nt.e(☃), ☃ + 315, ☃, ☃ % 2 == 0);
    }
    
    protected String b(int ☃)
    {
      if (☃ == 0) {
        return "stat.crafted";
      }
      if (☃ == 1) {
        return "stat.used";
      }
      if (☃ == 3) {
        return "stat.pickup";
      }
      if (☃ == 4) {
        return "stat.dropped";
      }
      return "stat.mined";
    }
  }
  
  class d
    extends bdq
  {
    private final List<rt.a> v = Lists.newArrayList();
    
    public d(bcf ☃)
    {
      super(bfn.this.l, bfn.this.m, 32, bfn.this.m - 64, bfn.m(bfn.this).a * 4);
      
      b(false);
      for (rt.a ☃ : rt.a.values()) {
        if ((bfn.b(bfn.this).a(☃.d) > 0) || (bfn.b(bfn.this).a(☃.e) > 0)) {
          this.v.add(☃);
        }
      }
    }
    
    protected int b()
    {
      return this.v.size();
    }
    
    protected void a(int ☃, boolean ☃, int ☃, int ☃) {}
    
    protected boolean a(int ☃)
    {
      return false;
    }
    
    protected int k()
    {
      return b() * bfn.n(bfn.this).a * 4;
    }
    
    protected void a()
    {
      bfn.this.c();
    }
    
    protected void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
    {
      rt.a ☃ = (rt.a)this.v.get(☃);
      String ☃ = bwo.a("entity." + ☃.a + ".name", new Object[0]);
      int ☃ = bfn.b(bfn.this).a(☃.d);
      int ☃ = bfn.b(bfn.this).a(☃.e);
      String ☃ = bwo.a("stat.entityKills", new Object[] { Integer.valueOf(☃), ☃ });
      String ☃ = bwo.a("stat.entityKilledBy", new Object[] { ☃, Integer.valueOf(☃) });
      if (☃ == 0) {
        ☃ = bwo.a("stat.entityKills.none", new Object[] { ☃ });
      }
      if (☃ == 0) {
        ☃ = bwo.a("stat.entityKilledBy.none", new Object[] { ☃ });
      }
      bfn.this.c(bfn.o(bfn.this), ☃, ☃ + 2 - 10, ☃ + 1, 16777215);
      
      bfn.this.c(bfn.p(bfn.this), ☃, ☃ + 2, ☃ + 1 + bfn.q(bfn.this).a, ☃ == 0 ? 6316128 : 9474192);
      bfn.this.c(bfn.r(bfn.this), ☃, ☃ + 2, ☃ + 1 + bfn.s(bfn.this).a * 2, ☃ == 0 ? 6316128 : 9474192);
    }
  }
}
