import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

public class bet
  extends bfb
{
  protected bfb a;
  private bet.a f;
  private final bch g;
  private final bwq h;
  private bdm i;
  private bdm r;
  
  public bet(bfb ☃, bch ☃, bwq ☃)
  {
    this.a = ☃;
    this.g = ☃;
    this.h = ☃;
  }
  
  public void b()
  {
    this.n.add(this.i = new bdm(100, this.l / 2 - 155, this.m - 38, bch.a.E, this.g.c(bch.a.E)));
    this.n.add(this.r = new bdm(6, this.l / 2 - 155 + 160, this.m - 38, bwo.a("gui.done", new Object[0])));
    
    this.f = new bet.a(this.j);
    this.f.d(7, 8);
  }
  
  public void k()
  {
    super.k();
    this.f.p();
  }
  
  protected void a(bcz ☃)
  {
    if (!☃.l) {
      return;
    }
    switch (☃.k)
    {
    case 100: 
      if ((☃ instanceof bdm))
      {
        this.g.a(((bdm)☃).c(), 1);
        ☃.j = this.g.c(bch.a.E);
        
        bcx ☃ = new bcx(this.j);
        int ☃ = ☃.a();
        int ☃ = ☃.b();
        a(this.j, ☃, ☃);
      }
      break;
    case 5: 
      break;
    case 6: 
      this.j.a(this.a);
      break;
    default: 
      this.f.a(☃);
    }
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    this.f.a(☃, ☃, ☃);
    
    a(this.q, bwo.a("options.language", new Object[0]), this.l / 2, 16, 16777215);
    a(this.q, "(" + bwo.a("options.languageWarning", new Object[0]) + ")", this.l / 2, this.m - 56, 8421504);
    
    super.a(☃, ☃, ☃);
  }
  
  class a
    extends bdq
  {
    private final List<String> v = Lists.newArrayList();
    private final Map<String, bwp> w = Maps.newHashMap();
    
    public a(bcf ☃)
    {
      super(bet.this.l, bet.this.m, 32, bet.this.m - 65 + 4, 18);
      for (bwp ☃ : bet.a(bet.this).d())
      {
        this.w.put(☃.a(), ☃);
        this.v.add(☃.a());
      }
    }
    
    protected int b()
    {
      return this.v.size();
    }
    
    protected void a(int ☃, boolean ☃, int ☃, int ☃)
    {
      bwp ☃ = (bwp)this.w.get(this.v.get(☃));
      
      bet.a(bet.this).a(☃);
      bet.b(bet.this).aB = ☃.a();
      
      this.a.f();
      
      bet.this.q.a((bet.a(bet.this).a()) || (bet.b(bet.this).aC));
      bet.this.q.b(bet.a(bet.this).b());
      
      bet.c(bet.this).j = bwo.a("gui.done", new Object[0]);
      bet.d(bet.this).j = bet.b(bet.this).c(bch.a.E);
      bet.b(bet.this).b();
    }
    
    protected boolean a(int ☃)
    {
      return ((String)this.v.get(☃)).equals(bet.a(bet.this).c().a());
    }
    
    protected int k()
    {
      return b() * 18;
    }
    
    protected void a()
    {
      bet.this.c();
    }
    
    protected void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
    {
      bet.this.q.b(true);
      bet.this.a(bet.this.q, ((bwp)this.w.get(this.v.get(☃))).toString(), this.b / 2, ☃ + 1, 16777215);
      bet.this.q.b(bet.a(bet.this).c().b());
    }
  }
}
