import java.util.List;

public class bfc
  extends bfb
{
  private final bfb a;
  private bcz f;
  private bcz g;
  private String h = "survival";
  private boolean i;
  
  public bfc(bfb ☃)
  {
    this.a = ☃;
  }
  
  public void b()
  {
    this.n.clear();
    this.n.add(new bcz(101, this.l / 2 - 155, this.m - 28, 150, 20, bwo.a("lanServer.start", new Object[0])));
    this.n.add(new bcz(102, this.l / 2 + 5, this.m - 28, 150, 20, bwo.a("gui.cancel", new Object[0])));
    
    this.n.add(this.g = new bcz(104, this.l / 2 - 155, 100, 150, 20, bwo.a("selectWorld.gameMode", new Object[0])));
    this.n.add(this.f = new bcz(103, this.l / 2 + 5, 100, 150, 20, bwo.a("selectWorld.allowCommands", new Object[0])));
    
    a();
  }
  
  private void a()
  {
    this.g.j = (bwo.a("selectWorld.gameMode", new Object[0]) + ": " + bwo.a(new StringBuilder().append("selectWorld.gameMode.").append(this.h).toString(), new Object[0]));
    
    this.f.j = (bwo.a("selectWorld.allowCommands", new Object[0]) + " ");
    if (this.i) {
      this.f.j += bwo.a("options.on", new Object[0]);
    } else {
      this.f.j += bwo.a("options.off", new Object[0]);
    }
  }
  
  protected void a(bcz ☃)
  {
    if (☃.k == 102)
    {
      this.j.a(this.a);
    }
    else if (☃.k == 104)
    {
      if (this.h.equals("spectator")) {
        this.h = "creative";
      } else if (this.h.equals("creative")) {
        this.h = "adventure";
      } else if (this.h.equals("adventure")) {
        this.h = "survival";
      } else {
        this.h = "spectator";
      }
      a();
    }
    else if (☃.k == 103)
    {
      this.i = (!this.i);
      a();
    }
    else if (☃.k == 101)
    {
      this.j.a(null);
      String ☃ = this.j.F().a(ahw.a.a(this.h), this.i);
      eu ☃;
      eu ☃;
      if (☃ != null) {
        ☃ = new fb("commands.publish.started", new Object[] { ☃ });
      } else {
        ☃ = new fa("commands.publish.failed");
      }
      this.j.r.d().a(☃);
    }
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    c();
    
    a(this.q, bwo.a("lanServer.title", new Object[0]), this.l / 2, 50, 16777215);
    a(this.q, bwo.a("lanServer.otherPlayers", new Object[0]), this.l / 2, 82, 16777215);
    
    super.a(☃, ☃, ☃);
  }
}
