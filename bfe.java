import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class bfe
  extends bfb
{
  private final bfb a;
  private final bch f;
  private final List<String> g = Lists.newArrayList();
  private final List<String> h = Lists.newArrayList();
  private String i;
  private String[] r;
  private bfe.a s;
  private bcz t;
  
  public bfe(bfb ☃, bch ☃)
  {
    this.a = ☃;
    this.f = ☃;
  }
  
  public void b()
  {
    this.i = bwo.a("options.snooper.title", new Object[0]);
    String ☃ = bwo.a("options.snooper.desc", new Object[0]);
    List<String> ☃ = Lists.newArrayList();
    for (String ☃ : this.q.c(☃, this.l - 30)) {
      ☃.add(☃);
    }
    this.r = ((String[])☃.toArray(new String[☃.size()]));
    
    this.g.clear();
    this.h.clear();
    
    this.n.add(this.t = new bcz(1, this.l / 2 - 152, this.m - 30, 150, 20, this.f.c(bch.a.u)));
    this.n.add(new bcz(2, this.l / 2 + 2, this.m - 30, 150, 20, bwo.a("gui.done", new Object[0])));
    
    boolean ☃ = (this.j.F() != null) && (this.j.F().ar() != null);
    for (Map.Entry<String, String> ☃ : new TreeMap(this.j.H().c()).entrySet())
    {
      this.g.add((☃ ? "C " : "") + (String)☃.getKey());
      this.h.add(this.q.a((String)☃.getValue(), this.l - 220));
    }
    if (☃) {
      for (Map.Entry<String, String> ☃ : new TreeMap(this.j.F().ar().c()).entrySet())
      {
        this.g.add("S " + (String)☃.getKey());
        this.h.add(this.q.a((String)☃.getValue(), this.l - 220));
      }
    }
    this.s = new bfe.a();
  }
  
  public void k()
  {
    super.k();
    this.s.p();
  }
  
  protected void a(bcz ☃)
  {
    if (!☃.l) {
      return;
    }
    if (☃.k == 2)
    {
      this.f.b();
      this.f.b();
      this.j.a(this.a);
    }
    if (☃.k == 1)
    {
      this.f.a(bch.a.u, 1);
      this.t.j = this.f.c(bch.a.u);
    }
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    c();
    
    this.s.a(☃, ☃, ☃);
    a(this.q, this.i, this.l / 2, 8, 16777215);
    
    int ☃ = 22;
    for (String ☃ : this.r)
    {
      a(this.q, ☃, this.l / 2, ☃, 8421504);
      ☃ += this.q.a;
    }
    super.a(☃, ☃, ☃);
  }
  
  class a
    extends bdq
  {
    public a()
    {
      super(bfe.this.l, bfe.this.m, 80, bfe.this.m - 40, bfe.this.q.a + 1);
    }
    
    protected int b()
    {
      return bfe.a(bfe.this).size();
    }
    
    protected void a(int ☃, boolean ☃, int ☃, int ☃) {}
    
    protected boolean a(int ☃)
    {
      return false;
    }
    
    protected void a() {}
    
    protected void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
    {
      bfe.this.q.a((String)bfe.a(bfe.this).get(☃), 10, ☃, 16777215);
      
      bfe.this.q.a((String)bfe.b(bfe.this).get(☃), 230, ☃, 16777215);
    }
    
    protected int d()
    {
      return this.b - 10;
    }
  }
}
