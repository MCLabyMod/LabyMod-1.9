import io.netty.buffer.Unpooled;
import java.util.List;
import org.lwjgl.input.Keyboard;

public class bfy
  extends bfb
  implements bfg
{
  private bdd a;
  private bdd f;
  private final apy g;
  private bcz h;
  private bcz i;
  private bcz r;
  private bcz s;
  private bcz t;
  private bcz u;
  private boolean v;
  private apy.a w = apy.a.c;
  private bfh x;
  private boolean y;
  private boolean z;
  
  public bfy(apy ☃)
  {
    this.g = ☃;
  }
  
  public void e()
  {
    this.a.a();
  }
  
  public void b()
  {
    final ahj ☃ = this.g.b();
    
    Keyboard.enableRepeatEvents(true);
    this.n.clear();
    this.n.add(this.h = new bcz(0, this.l / 2 - 4 - 150, this.m / 4 + 120 + 12, 150, 20, bwo.a("gui.done", new Object[0])));
    this.n.add(this.i = new bcz(1, this.l / 2 + 4, this.m / 4 + 120 + 12, 150, 20, bwo.a("gui.cancel", new Object[0])));
    this.n.add(this.r = new bcz(4, this.l / 2 + 150 - 20, 135, 20, 20, "O"));
    this.n.add(this.s = new bcz(5, this.l / 2 - 50 - 100 - 4, 165, 100, 20, bwo.a("advMode.mode.sequence", new Object[0])));
    this.n.add(this.t = new bcz(6, this.l / 2 - 50, 165, 100, 20, bwo.a("advMode.mode.unconditional", new Object[0])));
    this.n.add(this.u = new bcz(7, this.l / 2 + 50 + 4, 165, 100, 20, bwo.a("advMode.mode.redstoneTriggered", new Object[0])));
    
    this.a = new bdd(2, this.q, this.l / 2 - 150, 50, 300, 20);
    this.a.f(32500);
    this.a.b(true);
    
    this.f = new bdd(3, this.q, this.l / 2 - 150, 135, 276, 20);
    this.f.f(32500);
    this.f.c(false);
    this.f.a("-");
    
    this.h.l = false;
    this.r.l = false;
    this.s.l = false;
    this.t.l = false;
    this.u.l = false;
    
    this.x = new bfh(this.a, true)
    {
      public cj b()
      {
        return ☃.c();
      }
    };
  }
  
  public void a()
  {
    ahj ☃ = this.g.b();
    this.a.a(☃.m());
    this.v = ☃.n();
    this.w = this.g.i();
    this.y = this.g.j();
    this.z = this.g.e();
    
    f();
    g();
    i();
    j();
    
    this.h.l = true;
    this.r.l = true;
    this.s.l = true;
    this.t.l = true;
    this.u.l = true;
  }
  
  public void m()
  {
    Keyboard.enableRepeatEvents(false);
  }
  
  protected void a(bcz ☃)
  {
    if (!☃.l) {
      return;
    }
    ahj ☃ = this.g.b();
    if (☃.k == 1)
    {
      ☃.a(this.v);
      this.j.a(null);
    }
    else if (☃.k == 0)
    {
      em ☃ = new em(Unpooled.buffer());
      
      ☃.a(☃);
      ☃.a(this.a.b());
      ☃.writeBoolean(☃.n());
      ☃.a(this.w.name());
      ☃.writeBoolean(this.y);
      ☃.writeBoolean(this.z);
      this.j.v().a(new iq("MC|AutoCmd", ☃));
      if (!☃.n()) {
        ☃.b(null);
      }
      this.j.a(null);
    }
    else if (☃.k == 4)
    {
      ☃.a(!☃.n());
      f();
    }
    else if (☃.k == 5)
    {
      h();
      g();
    }
    else if (☃.k == 6)
    {
      this.y = (!this.y);
      i();
    }
    else if (☃.k == 7)
    {
      this.z = (!this.z);
      j();
    }
  }
  
  protected void a(char ☃, int ☃)
  {
    this.x.d();
    if (☃ == 15) {
      this.x.a();
    } else {
      this.x.c();
    }
    this.a.a(☃, ☃);
    this.f.a(☃, ☃);
    if ((☃ == 28) || (☃ == 156)) {
      a(this.h);
    } else if (☃ == 1) {
      a(this.i);
    }
  }
  
  protected void a(int ☃, int ☃, int ☃)
  {
    super.a(☃, ☃, ☃);
    
    this.a.a(☃, ☃, ☃);
    this.f.a(☃, ☃, ☃);
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    c();
    
    a(this.q, bwo.a("advMode.setCommand", new Object[0]), this.l / 2, 20, 16777215);
    c(this.q, bwo.a("advMode.command", new Object[0]), this.l / 2 - 150, 37, 10526880);
    this.a.g();
    
    int ☃ = 75;
    int ☃ = 0;
    
    c(this.q, bwo.a("advMode.nearestPlayer", new Object[0]), this.l / 2 - 150, ☃ + ☃++ * this.q.a, 10526880);
    c(this.q, bwo.a("advMode.randomPlayer", new Object[0]), this.l / 2 - 150, ☃ + ☃++ * this.q.a, 10526880);
    c(this.q, bwo.a("advMode.allPlayers", new Object[0]), this.l / 2 - 150, ☃ + ☃++ * this.q.a, 10526880);
    c(this.q, bwo.a("advMode.allEntities", new Object[0]), this.l / 2 - 150, ☃ + ☃++ * this.q.a, 10526880);
    c(this.q, "", this.l / 2 - 150, ☃ + ☃++ * this.q.a, 10526880);
    if (!this.f.b().isEmpty())
    {
      ☃ += ☃ * this.q.a + 1;
      c(this.q, bwo.a("advMode.previousOutput", new Object[0]), this.l / 2 - 150, ☃, 10526880);
      this.f.g();
    }
    super.a(☃, ☃, ☃);
  }
  
  private void f()
  {
    ahj ☃ = this.g.b();
    if (☃.n())
    {
      this.r.j = "O";
      if (☃.l() != null) {
        this.f.a(☃.l().c());
      }
    }
    else
    {
      this.r.j = "X";
      this.f.a("-");
    }
  }
  
  private void g()
  {
    switch (bfy.2.a[this.w.ordinal()])
    {
    case 1: 
      this.s.j = bwo.a("advMode.mode.sequence", new Object[0]);
      break;
    case 2: 
      this.s.j = bwo.a("advMode.mode.auto", new Object[0]);
      break;
    case 3: 
      this.s.j = bwo.a("advMode.mode.redstone", new Object[0]);
    }
  }
  
  private void h()
  {
    switch (bfy.2.a[this.w.ordinal()])
    {
    case 1: 
      this.w = apy.a.b;
      break;
    case 2: 
      this.w = apy.a.c;
      break;
    case 3: 
      this.w = apy.a.a;
    }
  }
  
  private void i()
  {
    if (this.y) {
      this.t.j = bwo.a("advMode.mode.conditional", new Object[0]);
    } else {
      this.t.j = bwo.a("advMode.mode.unconditional", new Object[0]);
    }
  }
  
  private void j()
  {
    if (this.z) {
      this.u.j = bwo.a("advMode.mode.autoexec.bat", new Object[0]);
    } else {
      this.u.j = bwo.a("advMode.mode.redstoneTriggered", new Object[0]);
    }
  }
  
  public void a(String... ☃)
  {
    this.x.a(☃);
  }
}
