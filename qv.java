import com.google.common.collect.Lists;
import java.util.List;

public class qv
  implements qg
{
  private String a;
  private int b;
  private adq[] c;
  private List<qi> d;
  private boolean e;
  
  public qv(String ☃, boolean ☃, int ☃)
  {
    this.a = ☃;
    this.e = ☃;
    this.b = ☃;
    this.c = new adq[☃];
  }
  
  public qv(eu ☃, int ☃)
  {
    this(☃.c(), true, ☃);
  }
  
  public void a(qi ☃)
  {
    if (this.d == null) {
      this.d = Lists.newArrayList();
    }
    this.d.add(☃);
  }
  
  public void b(qi ☃)
  {
    this.d.remove(☃);
  }
  
  public adq a(int ☃)
  {
    if ((☃ < 0) || (☃ >= this.c.length)) {
      return null;
    }
    return this.c[☃];
  }
  
  public adq a(int ☃, int ☃)
  {
    adq ☃ = qh.a(this.c, ☃, ☃);
    if (☃ != null) {
      v_();
    }
    return ☃;
  }
  
  public adq a(adq ☃)
  {
    adq ☃ = ☃.k();
    for (int ☃ = 0; ☃ < this.b; ☃++)
    {
      adq ☃ = a(☃);
      if (☃ == null)
      {
        a(☃, ☃);
        v_();
        return null;
      }
      if (adq.c(☃, ☃))
      {
        int ☃ = Math.min(w_(), ☃.c());
        int ☃ = Math.min(☃.b, ☃ - ☃.b);
        if (☃ > 0)
        {
          ☃.b += ☃;
          ☃.b -= ☃;
          if (☃.b <= 0)
          {
            v_();
            return null;
          }
        }
      }
    }
    if (☃.b != ☃.b) {
      v_();
    }
    return ☃;
  }
  
  public adq b(int ☃)
  {
    if (this.c[☃] != null)
    {
      adq ☃ = this.c[☃];
      this.c[☃] = null;
      return ☃;
    }
    return null;
  }
  
  public void a(int ☃, adq ☃)
  {
    this.c[☃] = ☃;
    if ((☃ != null) && (☃.b > w_())) {
      ☃.b = w_();
    }
    v_();
  }
  
  public int u_()
  {
    return this.b;
  }
  
  public String h_()
  {
    return this.a;
  }
  
  public boolean o_()
  {
    return this.e;
  }
  
  public void a(String ☃)
  {
    this.e = true;
    this.a = ☃;
  }
  
  public eu i_()
  {
    if (o_()) {
      return new fa(h_());
    }
    return new fb(h_(), new Object[0]);
  }
  
  public int w_()
  {
    return 64;
  }
  
  public void v_()
  {
    if (this.d != null) {
      for (int ☃ = 0; ☃ < this.d.size(); ☃++) {
        ((qi)this.d.get(☃)).a(this);
      }
    }
  }
  
  public boolean a(zj ☃)
  {
    return true;
  }
  
  public void b(zj ☃) {}
  
  public void c(zj ☃) {}
  
  public boolean b(int ☃, adq ☃)
  {
    return true;
  }
  
  public int c_(int ☃)
  {
    return 0;
  }
  
  public void b(int ☃, int ☃) {}
  
  public int g()
  {
    return 0;
  }
  
  public void l()
  {
    for (int ☃ = 0; ☃ < this.c.length; ☃++) {
      this.c[☃] = null;
    }
  }
}
