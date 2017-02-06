import java.util.List;

public class bef
  extends beh
{
  private final String r;
  private final String s;
  private final String t;
  private boolean u = true;
  
  public bef(beg ☃, String ☃, int ☃, boolean ☃)
  {
    super(☃, bwo.a(☃ ? "chat.link.confirmTrusted" : "chat.link.confirm", new Object[0]), ☃, ☃);
    
    this.g = bwo.a(☃ ? "chat.link.open" : "gui.yes", new Object[0]);
    this.h = bwo.a(☃ ? "gui.cancel" : "gui.no", new Object[0]);
    this.s = bwo.a("chat.copy", new Object[0]);
    this.r = bwo.a("chat.link.warning", new Object[0]);
    this.t = ☃;
  }
  
  public void b()
  {
    super.b();
    this.n.clear();
    this.n.add(new bcz(0, this.l / 2 - 50 - 105, this.m / 6 + 96, 100, 20, this.g));
    this.n.add(new bcz(2, this.l / 2 - 50, this.m / 6 + 96, 100, 20, this.s));
    this.n.add(new bcz(1, this.l / 2 - 50 + 105, this.m / 6 + 96, 100, 20, this.h));
  }
  
  protected void a(bcz ☃)
  {
    if (☃.k == 2) {
      a();
    }
    this.a.a(☃.k == 0, this.i);
  }
  
  public void a()
  {
    e(this.t);
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    super.a(☃, ☃, ☃);
    if (this.u) {
      a(this.q, this.r, this.l / 2, 110, 16764108);
    }
  }
  
  public void f()
  {
    this.u = false;
  }
}
