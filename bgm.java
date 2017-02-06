import io.netty.buffer.Unpooled;
import java.util.List;
import org.lwjgl.input.Keyboard;

public class bgm
  extends bfb
  implements bfg
{
  private bdd a;
  private bdd f;
  private final ahj g;
  private bcz h;
  private bcz i;
  private bcz r;
  private boolean s;
  private bfh t;
  
  public bgm(ahj ☃)
  {
    this.g = ☃;
  }
  
  public void e()
  {
    this.a.a();
  }
  
  public void b()
  {
    Keyboard.enableRepeatEvents(true);
    this.n.clear();
    this.n.add(this.h = new bcz(0, this.l / 2 - 4 - 150, this.m / 4 + 120 + 12, 150, 20, bwo.a("gui.done", new Object[0])));
    this.n.add(this.i = new bcz(1, this.l / 2 + 4, this.m / 4 + 120 + 12, 150, 20, bwo.a("gui.cancel", new Object[0])));
    this.n.add(this.r = new bcz(4, this.l / 2 + 150 - 20, 150, 20, 20, "O"));
    
    this.a = new bdd(2, this.q, this.l / 2 - 150, 50, 300, 20);
    this.a.f(32500);
    this.a.b(true);
    this.a.a(this.g.m());
    
    this.f = new bdd(3, this.q, this.l / 2 - 150, 150, 276, 20);
    this.f.f(32500);
    this.f.c(false);
    this.f.a("-");
    
    this.s = this.g.n();
    a();
    
    this.h.l = (!this.a.b().trim().isEmpty());
    this.t = new bfh(this.a, true)
    {
      public cj b()
      {
        return bgm.a(bgm.this).c();
      }
    };
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
    if (☃.k == 1)
    {
      this.g.a(this.s);
      this.j.a(null);
    }
    else if (☃.k == 0)
    {
      em ☃ = new em(Unpooled.buffer());
      
      ☃.writeByte(this.g.j());
      this.g.a(☃);
      ☃.a(this.a.b());
      ☃.writeBoolean(this.g.n());
      this.j.v().a(new iq("MC|AdvCmd", ☃));
      if (!this.g.n()) {
        this.g.b(null);
      }
      this.j.a(null);
    }
    else if (☃.k == 4)
    {
      this.g.a(!this.g.n());
      a();
    }
  }
  
  protected void a(char ☃, int ☃)
  {
    this.t.d();
    if (☃ == 15) {
      this.t.a();
    } else {
      this.t.c();
    }
    this.a.a(☃, ☃);
    this.f.a(☃, ☃);
    this.h.l = (!this.a.b().trim().isEmpty());
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
      ☃ += ☃ * this.q.a + 16;
      c(this.q, bwo.a("advMode.previousOutput", new Object[0]), this.l / 2 - 150, ☃, 10526880);
      this.f.g();
    }
    super.a(☃, ☃, ☃);
  }
  
  private void a()
  {
    if (this.g.n())
    {
      this.r.j = "O";
      if (this.g.l() != null) {
        this.f.a(this.g.l().c());
      }
    }
    else
    {
      this.r.j = "X";
      this.f.a("-");
    }
  }
  
  public void a(String... ☃)
  {
    this.t.a(☃);
  }
}
