import java.util.List;

public class bep
  extends bfb
{
  private String a;
  private eu f;
  private List<String> g;
  private final bfb h;
  private int i;
  
  public bep(bfb ☃, String ☃, eu ☃)
  {
    this.h = ☃;
    this.a = bwo.a(☃, new Object[0]);
    this.f = ☃;
  }
  
  protected void a(char ☃, int ☃) {}
  
  public void b()
  {
    this.n.clear();
    this.g = this.q.c(this.f.d(), this.l - 50);
    
    this.i = (this.g.size() * this.q.a);
    this.n.add(new bcz(0, this.l / 2 - 100, this.m / 2 + this.i / 2 + this.q.a, bwo.a("gui.toMenu", new Object[0])));
  }
  
  protected void a(bcz ☃)
  {
    if (☃.k == 0) {
      this.j.a(this.h);
    }
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    c();
    
    a(this.q, this.a, this.l / 2, this.m / 2 - this.i / 2 - this.q.a * 2, 11184810);
    
    int ☃ = this.m / 2 - this.i / 2;
    if (this.g != null) {
      for (String ☃ : this.g)
      {
        a(this.q, ☃, this.l / 2, ☃, 16777215);
        ☃ += this.q.a;
      }
    }
    super.a(☃, ☃, ☃);
  }
}
