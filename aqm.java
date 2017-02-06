import java.util.Random;

public abstract class aqm
  extends aqj
  implements qu
{
  protected kk m;
  protected long n;
  
  protected boolean c(dn ☃)
  {
    if (☃.b("LootTable", 8))
    {
      this.m = new kk(☃.l("LootTable"));
      this.n = ☃.i("LootTableSeed");
      return true;
    }
    return false;
  }
  
  protected boolean d(dn ☃)
  {
    if (this.m != null)
    {
      ☃.a("LootTable", this.m.toString());
      if (this.n != 0L) {
        ☃.a("LootTableSeed", this.n);
      }
      return true;
    }
    return false;
  }
  
  protected void d(zj ☃)
  {
    if (this.m != null)
    {
      azy ☃ = this.b.ak().a(this.m);
      this.m = null;
      Random ☃;
      Random ☃;
      if (this.n == 0L) {
        ☃ = new Random();
      } else {
        ☃ = new Random(this.n);
      }
      azz.a ☃ = new azz.a((lp)this.b);
      if (☃ != null) {
        ☃.a(☃.db());
      }
      ☃.a(this, ☃, ☃.a());
    }
  }
  
  public kk b()
  {
    return this.m;
  }
}
