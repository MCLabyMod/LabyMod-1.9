import java.util.Arrays;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;

public class bfq
  extends bdl
{
  private final bfr u;
  private final bcf v;
  private final bdl.a[] w;
  private int x = 0;
  
  public bfq(bfr ☃, bcf ☃)
  {
    super(☃, ☃.l + 45, ☃.m, 63, ☃.m - 32, 20);
    this.u = ☃;
    this.v = ☃;
    
    bcc[] ☃ = (bcc[])ArrayUtils.clone(☃.u.al);
    this.w = new bdl.a[☃.length + bcc.d().size()];
    
    Arrays.sort(☃);
    
    int ☃ = 0;
    String ☃ = null;
    for (bcc ☃ : ☃)
    {
      String ☃ = ☃.f();
      if (!☃.equals(☃))
      {
        ☃ = ☃;
        this.w[(☃++)] = new bfq.a(☃);
      }
      int ☃ = ☃.k.a(bwo.a(☃.h(), new Object[0]));
      if (☃ > this.x) {
        this.x = ☃;
      }
      this.w[(☃++)] = new bfq.b(☃, null);
    }
  }
  
  protected int b()
  {
    return this.w.length;
  }
  
  public bdl.a b(int ☃)
  {
    return this.w[☃];
  }
  
  public class a
    implements bdl.a
  {
    private final String b;
    private final int c;
    
    public a(String ☃)
    {
      this.b = bwo.a(☃, new Object[0]);
      this.c = bfq.a(bfq.this).k.a(this.b);
    }
    
    public void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, boolean ☃)
    {
      bfq.a(bfq.this).k.a(this.b, bfq.a(bfq.this).m.l / 2 - this.c / 2, ☃ + ☃ - bfq.a(bfq.this).k.a - 1, 16777215);
    }
    
    public boolean a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
    {
      return false;
    }
    
    public void b(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃) {}
    
    public void a(int ☃, int ☃, int ☃) {}
  }
  
  public class b
    implements bdl.a
  {
    private final bcc b;
    private final String c;
    private final bcz d;
    private final bcz e;
    
    private b(bcc ☃)
    {
      this.b = ☃;
      this.c = bwo.a(☃.h(), new Object[0]);
      this.d = new bcz(0, 0, 0, 75, 20, bwo.a(☃.h(), new Object[0]));
      this.e = new bcz(0, 0, 0, 50, 20, bwo.a("controls.reset", new Object[0]));
    }
    
    public void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, boolean ☃)
    {
      boolean ☃ = bfq.b(bfq.this).f == this.b;
      bfq.a(bfq.this).k.a(this.c, ☃ + 90 - bfq.c(bfq.this), ☃ + ☃ / 2 - bfq.a(bfq.this).k.a / 2, 16777215);
      
      this.e.h = (☃ + 190);
      this.e.i = ☃;
      this.e.l = (this.b.j() != this.b.i());
      this.e.a(bfq.a(bfq.this), ☃, ☃);
      
      this.d.h = (☃ + 105);
      this.d.i = ☃;
      this.d.j = bch.c(this.b.j());
      
      boolean ☃ = false;
      if (this.b.j() != 0) {
        for (bcc ☃ : bfq.a(bfq.this).u.al) {
          if ((☃ != this.b) && (☃.j() == this.b.j()))
          {
            ☃ = true;
            break;
          }
        }
      }
      if (☃) {
        this.d.j = (a.p + "> " + a.o + this.d.j + a.p + " <");
      } else if (☃) {
        this.d.j = (a.m + this.d.j);
      }
      this.d.a(bfq.a(bfq.this), ☃, ☃);
    }
    
    public boolean a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
    {
      if (this.d.c(bfq.a(bfq.this), ☃, ☃))
      {
        bfq.b(bfq.this).f = this.b;
        return true;
      }
      if (this.e.c(bfq.a(bfq.this), ☃, ☃))
      {
        bfq.a(bfq.this).u.a(this.b, this.b.i());
        bcc.c();
        return true;
      }
      return false;
    }
    
    public void b(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
    {
      this.d.a(☃, ☃);
      this.e.a(☃, ☃);
    }
    
    public void a(int ☃, int ☃, int ☃) {}
  }
  
  protected int d()
  {
    return super.d() + 15;
  }
  
  public int c()
  {
    return super.c() + 32;
  }
}
