import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

class bet$a
  extends bdq
{
  private final List<String> v = Lists.newArrayList();
  private final Map<String, bwp> w = Maps.newHashMap();
  
  public bet$a(bet arg1, bcf ☃)
  {
    super(☃, ???.l, ???.m, 32, ???.m - 65 + 4, 18);
    for (bwp ☃ : bet.a(???).d())
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
    
    bet.a(this.u).a(☃);
    bet.b(this.u).aB = ☃.a();
    
    this.a.f();
    
    this.u.q.a((bet.a(this.u).a()) || (bet.b(this.u).aC));
    this.u.q.b(bet.a(this.u).b());
    
    bet.c(this.u).j = bwo.a("gui.done", new Object[0]);
    bet.d(this.u).j = bet.b(this.u).c(bch.a.E);
    bet.b(this.u).b();
  }
  
  protected boolean a(int ☃)
  {
    return ((String)this.v.get(☃)).equals(bet.a(this.u).c().a());
  }
  
  protected int k()
  {
    return b() * 18;
  }
  
  protected void a()
  {
    this.u.c();
  }
  
  protected void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    this.u.q.b(true);
    this.u.a(this.u.q, ((bwp)this.w.get(this.v.get(☃))).toString(), this.b / 2, ☃ + 1, 16777215);
    this.u.q.b(bet.a(this.u).c().b());
  }
}
