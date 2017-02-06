import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class apu
  extends aqj
  implements ky, qy
{
  public static final rk[][] a = { { rm.a, rm.c }, { rm.k, rm.h }, { rm.e }, { rm.j } };
  private static final Set<rk> f = Sets.newHashSet();
  private final List<apu.a> g;
  private long h;
  private float i;
  private boolean j;
  private int k;
  private rk l;
  private rk m;
  private adq n;
  private String o;
  
  static
  {
    for (rk[] ☃ : a) {
      Collections.addAll(f, ☃);
    }
  }
  
  public apu()
  {
    this.g = Lists.newArrayList();
    
    this.k = -1;
  }
  
  public void c()
  {
    if (this.b.P() % 80L == 0L) {
      m();
    }
  }
  
  public void m()
  {
    if (this.b != null)
    {
      F();
      E();
    }
  }
  
  private void E()
  {
    int ☃;
    if ((this.j) && (this.k > 0) && (!this.b.E) && (this.l != null))
    {
      double ☃ = this.k * 10 + 10;
      int ☃ = 0;
      if ((this.k >= 4) && (this.l == this.m)) {
        ☃ = 1;
      }
      ☃ = (9 + this.k * 2) * 20;
      
      int ☃ = this.c.p();
      int ☃ = this.c.q();
      int ☃ = this.c.r();
      bbh ☃ = new bbh(☃, ☃, ☃, ☃ + 1, ☃ + 1, ☃ + 1).g(☃).a(0.0D, this.b.Y(), 0.0D);
      List<zj> ☃ = this.b.a(zj.class, ☃);
      for (zj ☃ : ☃) {
        ☃.c(new rl(this.l, ☃, ☃, true, true));
      }
      if ((this.k >= 4) && (this.l != this.m) && (this.m != null)) {
        for (zj ☃ : ☃) {
          ☃.c(new rl(this.m, ☃, 0, true, true));
        }
      }
    }
  }
  
  private void F()
  {
    int ☃ = this.k;
    
    int ☃ = this.c.p();
    int ☃ = this.c.q();
    int ☃ = this.c.r();
    
    this.k = 0;
    this.g.clear();
    this.j = true;
    
    apu.a ☃ = new apu.a(we.a(act.a));
    this.g.add(☃);
    boolean ☃ = true;
    
    cj.a ☃ = new cj.a();
    for (int ☃ = ☃ + 1; ☃ < 256; ☃++)
    {
      arc ☃ = this.b.o(☃.c(☃, ☃, ☃));
      float[] ☃;
      if (☃.t() == aju.cG)
      {
        ☃ = we.a((act)☃.c(aor.a));
      }
      else
      {
        float[] ☃;
        if (☃.t() == aju.cH)
        {
          ☃ = we.a((act)☃.c(aos.a));
        }
        else
        {
          if ((☃.c() < 15) || (☃.t() == aju.h))
          {
            ☃.a();
            continue;
          }
          this.j = false;
          this.g.clear();
          break;
        }
      }
      float[] ☃;
      if (!☃) {
        ☃ = new float[] { (☃.b()[0] + ☃[0]) / 2.0F, (☃.b()[1] + ☃[1]) / 2.0F, (☃.b()[2] + ☃[2]) / 2.0F };
      }
      if (Arrays.equals(☃, ☃.b()))
      {
        ☃.a();
      }
      else
      {
        ☃ = new apu.a(☃);
        this.g.add(☃);
      }
      ☃ = false;
    }
    if (this.j)
    {
      for (int ☃ = 1; ☃ <= 4; ☃++)
      {
        int ☃ = ☃ - ☃;
        if (☃ < 0) {
          break;
        }
        boolean ☃ = true;
        for (int ☃ = ☃ - ☃; (☃ <= ☃ + ☃) && (☃); ☃++) {
          for (int ☃ = ☃ - ☃; ☃ <= ☃ + ☃; ☃++)
          {
            ajt ☃ = this.b.o(new cj(☃, ☃, ☃)).t();
            if ((☃ != aju.bT) && (☃ != aju.R) && (☃ != aju.ah) && (☃ != aju.S))
            {
              ☃ = false;
              break;
            }
          }
        }
        if (!☃) {
          break;
        }
        this.k = ☃;
      }
      if (this.k == 0) {
        this.j = false;
      }
    }
    if ((!this.b.E) && 
      (this.k == 4) && (☃ < this.k)) {
      for (zj ☃ : this.b.a(zj.class, new bbh(☃, ☃, ☃, ☃, ☃ - 4, ☃).b(10.0D, 5.0D, 10.0D))) {
        ☃.b(nk.K);
      }
    }
  }
  
  public List<apu.a> n()
  {
    return this.g;
  }
  
  public float o()
  {
    if (!this.j) {
      return 0.0F;
    }
    int ☃ = (int)(this.b.P() - this.h);
    this.h = this.b.P();
    if (☃ > 1)
    {
      this.i -= ☃ / 40.0F;
      if (this.i < 0.0F) {
        this.i = 0.0F;
      }
    }
    this.i += 0.025F;
    if (this.i > 1.0F) {
      this.i = 1.0F;
    }
    return this.i;
  }
  
  public ff<?> D_()
  {
    dn ☃ = new dn();
    b(☃);
    return new fs(this.c, 3, ☃);
  }
  
  public double s()
  {
    return 65536.0D;
  }
  
  private static rk f(int ☃)
  {
    rk ☃ = rk.a(☃);
    
    return f.contains(☃) ? ☃ : null;
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    
    this.l = f(☃.h("Primary"));
    this.m = f(☃.h("Secondary"));
    this.k = ☃.h("Levels");
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    
    ☃.a("Primary", rk.a(this.l));
    ☃.a("Secondary", rk.a(this.m));
    
    ☃.a("Levels", this.k);
  }
  
  public int u_()
  {
    return 1;
  }
  
  public adq a(int ☃)
  {
    if (☃ == 0) {
      return this.n;
    }
    return null;
  }
  
  public adq a(int ☃, int ☃)
  {
    if ((☃ == 0) && (this.n != null))
    {
      if (☃ >= this.n.b)
      {
        adq ☃ = this.n;
        this.n = null;
        return ☃;
      }
      this.n.b -= ☃;
      return new adq(this.n.b(), ☃, this.n.i());
    }
    return null;
  }
  
  public adq b(int ☃)
  {
    if (☃ == 0)
    {
      adq ☃ = this.n;
      this.n = null;
      return ☃;
    }
    return null;
  }
  
  public void a(int ☃, adq ☃)
  {
    if (☃ == 0) {
      this.n = ☃;
    }
  }
  
  public String h_()
  {
    return o_() ? this.o : "container.beacon";
  }
  
  public boolean o_()
  {
    return (this.o != null) && (!this.o.isEmpty());
  }
  
  public void a(String ☃)
  {
    this.o = ☃;
  }
  
  public int w_()
  {
    return 1;
  }
  
  public boolean a(zj ☃)
  {
    if (this.b.r(this.c) != this) {
      return false;
    }
    if (☃.e(this.c.p() + 0.5D, this.c.q() + 0.5D, this.c.r() + 0.5D) > 64.0D) {
      return false;
    }
    return true;
  }
  
  public boolean b(int ☃, adq ☃)
  {
    return (☃.b() == ads.bY) || (☃.b() == ads.k) || (☃.b() == ads.m) || (☃.b() == ads.l);
  }
  
  public String k()
  {
    return "minecraft:beacon";
  }
  
  public aau a(zi ☃, zj ☃)
  {
    return new aax(☃, this);
  }
  
  public int c_(int ☃)
  {
    switch (☃)
    {
    case 0: 
      return this.k;
    case 1: 
      return rk.a(this.l);
    case 2: 
      return rk.a(this.m);
    }
    return 0;
  }
  
  public void b(int ☃, int ☃)
  {
    switch (☃)
    {
    case 0: 
      this.k = ☃;
      break;
    case 1: 
      this.l = f(☃);
      break;
    case 2: 
      this.m = f(☃);
    }
  }
  
  public int g()
  {
    return 3;
  }
  
  public void l()
  {
    this.n = null;
  }
  
  public boolean c(int ☃, int ☃)
  {
    if (☃ == 1)
    {
      m();
      return true;
    }
    return super.c(☃, ☃);
  }
  
  public int[] a(cq ☃)
  {
    return new int[0];
  }
  
  public boolean a(int ☃, adq ☃, cq ☃)
  {
    return false;
  }
  
  public boolean b(int ☃, adq ☃, cq ☃)
  {
    return false;
  }
  
  public void b(zj ☃) {}
  
  public void c(zj ☃) {}
  
  public static class a
  {
    private final float[] a;
    private int b;
    
    public a(float[] ☃)
    {
      this.a = ☃;
      this.b = 1;
    }
    
    protected void a()
    {
      this.b += 1;
    }
    
    public float[] b()
    {
      return this.a;
    }
    
    public int c()
    {
      return this.b;
    }
  }
}
