import com.google.common.collect.Lists;
import java.util.List;

public class bdf
  extends bcv
{
  protected int a = 200;
  protected int f = 20;
  public int g;
  public int h;
  private List<String> k;
  public int i;
  private boolean l;
  public boolean j = true;
  private boolean m;
  private int n;
  private int o;
  private int p;
  private int q;
  private bct r;
  private int s;
  
  public bdf(bct ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    this.r = ☃;
    this.i = ☃;
    this.g = ☃;
    this.h = ☃;
    this.a = ☃;
    this.f = ☃;
    this.k = Lists.newArrayList();
    this.l = false;
    this.m = false;
    this.n = ☃;
    this.o = -1;
    this.p = -1;
    this.q = -1;
    this.s = 0;
  }
  
  public void a(String ☃)
  {
    this.k.add(bwo.a(☃, new Object[0]));
  }
  
  public bdf a()
  {
    this.l = true;
    return this;
  }
  
  public void a(bcf ☃, int ☃, int ☃)
  {
    if (!this.j) {
      return;
    }
    bni.m();
    bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
    
    b(☃, ☃, ☃);
    int ☃ = this.h + this.f / 2 + this.s / 2;
    int ☃ = ☃ - this.k.size() * 10 / 2;
    for (int ☃ = 0; ☃ < this.k.size(); ☃++) {
      if (this.l) {
        a(this.r, (String)this.k.get(☃), this.g + this.a / 2, ☃ + ☃ * 10, this.n);
      } else {
        c(this.r, (String)this.k.get(☃), this.g, ☃ + ☃ * 10, this.n);
      }
    }
  }
  
  protected void b(bcf ☃, int ☃, int ☃)
  {
    if (this.m)
    {
      int ☃ = this.a + this.s * 2;
      int ☃ = this.f + this.s * 2;
      int ☃ = this.g - this.s;
      int ☃ = this.h - this.s;
      a(☃, ☃, ☃ + ☃, ☃ + ☃, this.o);
      a(☃, ☃ + ☃, ☃, this.p);
      a(☃, ☃ + ☃, ☃ + ☃, this.q);
      b(☃, ☃, ☃ + ☃, this.p);
      b(☃ + ☃, ☃, ☃ + ☃, this.q);
    }
  }
}
