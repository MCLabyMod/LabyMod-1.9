import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import org.lwjgl.input.Keyboard;

public abstract class bft
  extends bfb
{
  public static final kk a = new kk("textures/gui/container/inventory.png");
  protected int f = 176;
  protected int g = 166;
  public aau h;
  protected int i;
  protected int r;
  private abt u;
  private abt v;
  private boolean w;
  private adq x;
  private int y;
  private int z;
  private abt A;
  private long B;
  private adq C;
  private abt D;
  private long E;
  protected final Set<abt> s = Sets.newHashSet();
  protected boolean t;
  private int F;
  private int G;
  private boolean H;
  private int I;
  private long J;
  private abt K;
  private int L;
  private boolean M;
  private adq N;
  
  public bft(aau ☃)
  {
    this.h = ☃;
    this.H = true;
  }
  
  public void b()
  {
    super.b();
    this.j.h.bt = this.h;
    
    this.i = ((this.l - this.f) / 2);
    this.r = ((this.m - this.g) / 2);
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    c();
    int ☃ = this.i;
    int ☃ = this.r;
    
    a(☃, ☃, ☃);
    
    bni.E();
    bcd.a();
    bni.g();
    bni.j();
    
    super.a(☃, ☃, ☃);
    
    bcd.c();
    
    bni.G();
    bni.c(☃, ☃, 0.0F);
    
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.D();
    
    this.u = null;
    
    int ☃ = 240;
    int ☃ = 240;
    bzg.a(bzg.r, ☃, ☃);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    for (int ☃ = 0; ☃ < this.h.c.size(); ☃++)
    {
      abt ☃ = (abt)this.h.c.get(☃);
      
      a(☃);
      if ((a(☃, ☃, ☃)) && (☃.b()))
      {
        this.u = ☃;
        
        bni.g();
        bni.j();
        
        int ☃ = ☃.f;
        int ☃ = ☃.g;
        bni.a(true, true, true, false);
        a(☃, ☃, ☃ + 16, ☃ + 16, -2130706433, -2130706433);
        bni.a(true, true, true, true);
        bni.f();
        bni.k();
      }
    }
    bcd.a();
    b(☃, ☃);
    bcd.c();
    
    zi ☃ = this.j.h.br;
    adq ☃ = this.x == null ? ☃.o() : this.x;
    if (☃ != null)
    {
      int ☃ = 8;
      int ☃ = this.x == null ? 8 : 16;
      String ☃ = null;
      if ((this.x != null) && (this.w))
      {
        ☃ = ☃.k();
        ☃.b = on.f(☃.b / 2.0F);
      }
      else if ((this.t) && (this.s.size() > 1))
      {
        ☃ = ☃.k();
        ☃.b = this.I;
        if (☃.b == 0) {
          ☃ = "" + a.o + "0";
        }
      }
      a(☃, ☃ - ☃ - ☃, ☃ - ☃ - ☃, ☃);
    }
    if (this.C != null)
    {
      float ☃ = (float)(bcf.I() - this.B) / 100.0F;
      if (☃ >= 1.0F)
      {
        ☃ = 1.0F;
        this.C = null;
      }
      int ☃ = this.A.f - this.y;
      int ☃ = this.A.g - this.z;
      int ☃ = this.y + (int)(☃ * ☃);
      int ☃ = this.z + (int)(☃ * ☃);
      
      a(this.C, ☃, ☃, null);
    }
    bni.H();
    if ((☃.o() == null) && (this.u != null) && (this.u.e()))
    {
      adq ☃ = this.u.d();
      a(☃, ☃, ☃);
    }
    bni.f();
    bni.k();
    bcd.b();
  }
  
  private void a(adq ☃, int ☃, int ☃, String ☃)
  {
    bni.c(0.0F, 0.0F, 32.0F);
    this.e = 200.0F;
    this.k.a = 200.0F;
    
    this.k.b(☃, ☃, ☃);
    this.k.a(this.q, ☃, ☃, ☃ - (this.x == null ? 0 : 8), ☃);
    this.e = 0.0F;
    this.k.a = 0.0F;
  }
  
  protected void b(int ☃, int ☃) {}
  
  protected abstract void a(float paramFloat, int paramInt1, int paramInt2);
  
  private void a(abt ☃)
  {
    int ☃ = ☃.f;
    int ☃ = ☃.g;
    adq ☃ = ☃.d();
    boolean ☃ = false;
    boolean ☃ = (☃ == this.v) && (this.x != null) && (!this.w);
    adq ☃ = this.j.h.br.o();
    String ☃ = null;
    if ((☃ == this.v) && (this.x != null) && (this.w) && (☃ != null))
    {
      ☃ = ☃.k();
      ☃.b /= 2;
    }
    else if ((this.t) && (this.s.contains(☃)) && (☃ != null))
    {
      if (this.s.size() == 1) {
        return;
      }
      if ((aau.a(☃, ☃, true)) && (this.h.b(☃)))
      {
        ☃ = ☃.k();
        ☃ = true;
        
        aau.a(this.s, this.F, ☃, ☃.d() == null ? 0 : ☃.d().b);
        if (☃.b > ☃.c())
        {
          ☃ = a.o + "" + ☃.c();
          ☃.b = ☃.c();
        }
        if (☃.b > ☃.b(☃))
        {
          ☃ = a.o + "" + ☃.b(☃);
          ☃.b = ☃.b(☃);
        }
      }
      else
      {
        this.s.remove(☃);
        a();
      }
    }
    this.e = 100.0F;
    this.k.a = 100.0F;
    if ((☃ == null) && (☃.b()))
    {
      String ☃ = ☃.c();
      if (☃ != null)
      {
        bvh ☃ = this.j.R().a(☃);
        bni.g();
        this.j.N().a(bvg.g);
        a(☃, ☃, ☃, 16, 16);
        bni.f();
        ☃ = true;
      }
    }
    if (!☃)
    {
      if (☃) {
        a(☃, ☃, ☃ + 16, ☃ + 16, -2130706433);
      }
      bni.k();
      this.k.a(this.j.h, ☃, ☃, ☃);
      this.k.a(this.q, ☃, ☃, ☃, ☃);
    }
    this.k.a = 0.0F;
    this.e = 0.0F;
  }
  
  private void a()
  {
    adq ☃ = this.j.h.br.o();
    if ((☃ == null) || (!this.t)) {
      return;
    }
    this.I = ☃.b;
    for (abt ☃ : this.s)
    {
      adq ☃ = ☃.k();
      int ☃ = ☃.d() == null ? 0 : ☃.d().b;
      aau.a(this.s, this.F, ☃, ☃);
      if (☃.b > ☃.c()) {
        ☃.b = ☃.c();
      }
      if (☃.b > ☃.b(☃)) {
        ☃.b = ☃.b(☃);
      }
      this.I -= ☃.b - ☃;
    }
  }
  
  private abt c(int ☃, int ☃)
  {
    for (int ☃ = 0; ☃ < this.h.c.size(); ☃++)
    {
      abt ☃ = (abt)this.h.c.get(☃);
      if (a(☃, ☃, ☃)) {
        return ☃;
      }
    }
    return null;
  }
  
  protected void a(int ☃, int ☃, int ☃)
  {
    super.a(☃, ☃, ☃);
    boolean ☃ = ☃ == this.j.u.ab.j() + 100;
    abt ☃ = c(☃, ☃);
    long ☃ = bcf.I();
    this.M = ((this.K == ☃) && (☃ - this.J < 250L) && (this.L == ☃));
    this.H = false;
    if ((☃ == 0) || (☃ == 1) || (☃))
    {
      int ☃ = this.i;
      int ☃ = this.r;
      boolean ☃ = (☃ < ☃) || (☃ < ☃) || (☃ >= ☃ + this.f) || (☃ >= ☃ + this.g);
      
      int ☃ = -1;
      if (☃ != null) {
        ☃ = ☃.e;
      }
      if (☃) {
        ☃ = 64537;
      }
      if ((this.j.u.z) && (☃) && (this.j.h.br.o() == null))
      {
        this.j.a(null);
        return;
      }
      if (☃ != -1) {
        if (this.j.u.z)
        {
          if ((☃ != null) && (☃.e()))
          {
            this.v = ☃;
            this.x = null;
            this.w = (☃ == 1);
          }
          else
          {
            this.v = null;
          }
        }
        else if (!this.t) {
          if (this.j.h.br.o() == null)
          {
            if (☃ == this.j.u.ab.j() + 100)
            {
              a(☃, ☃, ☃, aaz.d);
            }
            else
            {
              boolean ☃ = (☃ != 64537) && ((Keyboard.isKeyDown(42)) || (Keyboard.isKeyDown(54)));
              aaz ☃ = aaz.a;
              if (☃)
              {
                this.N = ((☃ != null) && (☃.e()) ? ☃.d() : null);
                ☃ = aaz.b;
              }
              else if (☃ == 64537)
              {
                ☃ = aaz.e;
              }
              a(☃, ☃, ☃, ☃);
            }
            this.H = true;
          }
          else
          {
            this.t = true;
            this.G = ☃;
            this.s.clear();
            if (☃ == 0) {
              this.F = 0;
            } else if (☃ == 1) {
              this.F = 1;
            } else if (☃ == this.j.u.ab.j() + 100) {
              this.F = 2;
            }
          }
        }
      }
    }
    this.K = ☃;
    this.J = ☃;
    this.L = ☃;
  }
  
  protected void a(int ☃, int ☃, int ☃, long ☃)
  {
    abt ☃ = c(☃, ☃);
    adq ☃ = this.j.h.br.o();
    if ((this.v != null) && (this.j.u.z))
    {
      if ((☃ == 0) || (☃ == 1)) {
        if (this.x == null)
        {
          if ((☃ != this.v) && (this.v.d() != null)) {
            this.x = this.v.d().k();
          }
        }
        else if ((this.x.b > 1) && (☃ != null) && (aau.a(☃, this.x, false)))
        {
          long ☃ = bcf.I();
          if (this.D == ☃)
          {
            if (☃ - this.E > 500L)
            {
              a(this.v, this.v.e, 0, aaz.a);
              a(☃, ☃.e, 1, aaz.a);
              a(this.v, this.v.e, 0, aaz.a);
              this.E = (☃ + 750L);
              this.x.b -= 1;
            }
          }
          else
          {
            this.D = ☃;
            this.E = ☃;
          }
        }
      }
    }
    else if ((this.t) && (☃ != null) && (☃ != null) && (☃.b > this.s.size()) && (aau.a(☃, ☃, true)) && (☃.a(☃)) && (this.h.b(☃)))
    {
      this.s.add(☃);
      a();
    }
  }
  
  protected void b(int ☃, int ☃, int ☃)
  {
    abt ☃ = c(☃, ☃);
    int ☃ = this.i;
    int ☃ = this.r;
    boolean ☃ = (☃ < ☃) || (☃ < ☃) || (☃ >= ☃ + this.f) || (☃ >= ☃ + this.g);
    
    int ☃ = -1;
    if (☃ != null) {
      ☃ = ☃.e;
    }
    if (☃) {
      ☃ = 64537;
    }
    if ((this.M) && (☃ != null) && (☃ == 0) && (this.h.a(null, ☃)))
    {
      if (r())
      {
        if ((☃ != null) && (☃.d != null) && (this.N != null)) {
          for (abt ☃ : this.h.c) {
            if ((☃ != null) && (☃.a(this.j.h)) && (☃.e()) && (☃.d == ☃.d) && (aau.a(☃, this.N, true))) {
              a(☃, ☃.e, ☃, aaz.b);
            }
          }
        }
      }
      else {
        a(☃, ☃, ☃, aaz.g);
      }
      this.M = false;
      this.J = 0L;
    }
    else
    {
      if ((this.t) && (this.G != ☃))
      {
        this.t = false;
        this.s.clear();
        this.H = true;
        return;
      }
      if (this.H)
      {
        this.H = false;
        return;
      }
      if ((this.v != null) && (this.j.u.z))
      {
        if ((☃ == 0) || (☃ == 1))
        {
          if ((this.x == null) && (☃ != this.v)) {
            this.x = this.v.d();
          }
          boolean ☃ = aau.a(☃, this.x, false);
          if ((☃ != -1) && (this.x != null) && (☃))
          {
            a(this.v, this.v.e, ☃, aaz.a);
            a(☃, ☃, 0, aaz.a);
            if (this.j.h.br.o() != null)
            {
              a(this.v, this.v.e, ☃, aaz.a);
              this.y = (☃ - ☃);
              this.z = (☃ - ☃);
              this.A = this.v;
              this.C = this.x;
              this.B = bcf.I();
            }
            else
            {
              this.C = null;
            }
          }
          else if (this.x != null)
          {
            this.y = (☃ - ☃);
            this.z = (☃ - ☃);
            this.A = this.v;
            this.C = this.x;
            this.B = bcf.I();
          }
          this.x = null;
          this.v = null;
        }
      }
      else if ((this.t) && (!this.s.isEmpty()))
      {
        a(null, 64537, aau.d(0, this.F), aaz.f);
        for (abt ☃ : this.s) {
          a(☃, ☃.e, aau.d(1, this.F), aaz.f);
        }
        a(null, 64537, aau.d(2, this.F), aaz.f);
      }
      else if (this.j.h.br.o() != null)
      {
        if (☃ == this.j.u.ab.j() + 100)
        {
          a(☃, ☃, ☃, aaz.d);
        }
        else
        {
          boolean ☃ = (☃ != 64537) && ((Keyboard.isKeyDown(42)) || (Keyboard.isKeyDown(54)));
          if (☃) {
            this.N = ((☃ != null) && (☃.e()) ? ☃.d() : null);
          }
          a(☃, ☃, ☃, ☃ ? aaz.b : aaz.a);
        }
      }
    }
    if (this.j.h.br.o() == null) {
      this.J = 0L;
    }
    this.t = false;
  }
  
  private boolean a(abt ☃, int ☃, int ☃)
  {
    return c(☃.f, ☃.g, 16, 16, ☃, ☃);
  }
  
  protected boolean c(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    int ☃ = this.i;
    int ☃ = this.r;
    ☃ -= ☃;
    ☃ -= ☃;
    
    return (☃ >= ☃ - 1) && (☃ < ☃ + ☃ + 1) && (☃ >= ☃ - 1) && (☃ < ☃ + ☃ + 1);
  }
  
  protected void a(abt ☃, int ☃, int ☃, aaz ☃)
  {
    if (☃ != null) {
      ☃ = ☃.e;
    }
    this.j.c.a(this.h.d, ☃, ☃, ☃, this.j.h);
  }
  
  protected void a(char ☃, int ☃)
  {
    if ((☃ == 1) || (☃ == this.j.u.W.j())) {
      this.j.h.q();
    }
    b(☃);
    if ((this.u != null) && (this.u.e())) {
      if (☃ == this.j.u.ab.j()) {
        a(this.u, this.u.e, 0, aaz.d);
      } else if (☃ == this.j.u.Y.j()) {
        a(this.u, this.u.e, q() ? 1 : 0, aaz.e);
      }
    }
  }
  
  protected boolean b(int ☃)
  {
    if ((this.j.h.br.o() == null) && (this.u != null)) {
      for (int ☃ = 0; ☃ < 9; ☃++) {
        if (☃ == this.j.u.ak[☃].j())
        {
          a(this.u, this.u.e, ☃, aaz.c);
          return true;
        }
      }
    }
    return false;
  }
  
  public void m()
  {
    if (this.j.h == null) {
      return;
    }
    this.h.b(this.j.h);
  }
  
  public boolean d()
  {
    return false;
  }
  
  public void e()
  {
    super.e();
    if ((!this.j.h.au()) || (this.j.h.F)) {
      this.j.h.q();
    }
  }
}
