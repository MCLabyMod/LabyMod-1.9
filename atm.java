import java.util.List;
import java.util.Random;

public class atm
  implements ary
{
  protected static final arc a = aju.a.u();
  protected static final arc b = aju.aV.u();
  protected static final arc c = aju.h.u();
  protected static final arc d = aju.l.u();
  protected static final arc e = aju.n.u();
  protected static final arc f = aju.aW.u();
  private final aht n;
  private final boolean o;
  private final Random p;
  private double[] q = new double['Ā'];
  private double[] r = new double['Ā'];
  private double[] s = new double['Ā'];
  private double[] t;
  private final awu u;
  private final awu v;
  private final awu w;
  private final awu x;
  private final awu y;
  public final awu g;
  public final awu h;
  private final aug z = new aug();
  private final aun A = new aun();
  private final auh B = new auh();
  private final aud C = new aut(aju.co.u(), 14, arj.a(aju.aV));
  private final aui D = new aui(aju.k, true);
  private final aui E = new aui(aju.k, false);
  private final atu F = new atu(aju.P);
  private final atu G = new atu(aju.Q);
  private final avv H = new avv();
  private final atk I = new atl();
  double[] i;
  double[] j;
  double[] k;
  double[] l;
  double[] m;
  
  public atm(aht ☃, boolean ☃, long ☃)
  {
    this.n = ☃;
    this.o = ☃;
    
    this.p = new Random(☃);
    this.u = new awu(this.p, 16);
    this.v = new awu(this.p, 16);
    this.w = new awu(this.p, 8);
    this.x = new awu(this.p, 4);
    this.y = new awu(this.p, 4);
    
    this.g = new awu(this.p, 10);
    this.h = new awu(this.p, 16);
    
    ☃.b(63);
  }
  
  public void a(int ☃, int ☃, atf ☃)
  {
    int ☃ = 4;
    int ☃ = this.n.K() / 2 + 1;
    
    int ☃ = ☃ + 1;
    int ☃ = 17;
    int ☃ = ☃ + 1;
    this.t = a(this.t, ☃ * ☃, 0, ☃ * ☃, ☃, ☃, ☃);
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      for (int ☃ = 0; ☃ < ☃; ☃++) {
        for (int ☃ = 0; ☃ < 16; ☃++)
        {
          double ☃ = 0.125D;
          double ☃ = this.t[(((☃ + 0) * ☃ + ☃ + 0) * ☃ + ☃ + 0)];
          double ☃ = this.t[(((☃ + 0) * ☃ + ☃ + 1) * ☃ + ☃ + 0)];
          double ☃ = this.t[(((☃ + 1) * ☃ + ☃ + 0) * ☃ + ☃ + 0)];
          double ☃ = this.t[(((☃ + 1) * ☃ + ☃ + 1) * ☃ + ☃ + 0)];
          
          double ☃ = (this.t[(((☃ + 0) * ☃ + ☃ + 0) * ☃ + ☃ + 1)] - ☃) * ☃;
          double ☃ = (this.t[(((☃ + 0) * ☃ + ☃ + 1) * ☃ + ☃ + 1)] - ☃) * ☃;
          double ☃ = (this.t[(((☃ + 1) * ☃ + ☃ + 0) * ☃ + ☃ + 1)] - ☃) * ☃;
          double ☃ = (this.t[(((☃ + 1) * ☃ + ☃ + 1) * ☃ + ☃ + 1)] - ☃) * ☃;
          for (int ☃ = 0; ☃ < 8; ☃++)
          {
            double ☃ = 0.25D;
            
            double ☃ = ☃;
            double ☃ = ☃;
            double ☃ = (☃ - ☃) * ☃;
            double ☃ = (☃ - ☃) * ☃;
            for (int ☃ = 0; ☃ < 4; ☃++)
            {
              double ☃ = 0.25D;
              
              double ☃ = ☃;
              double ☃ = (☃ - ☃) * ☃;
              for (int ☃ = 0; ☃ < 4; ☃++)
              {
                arc ☃ = null;
                if (☃ * 8 + ☃ < ☃) {
                  ☃ = d;
                }
                if (☃ > 0.0D) {
                  ☃ = b;
                }
                int ☃ = ☃ + ☃ * 4;
                int ☃ = ☃ + ☃ * 8;
                int ☃ = ☃ + ☃ * 4;
                ☃.a(☃, ☃, ☃, ☃);
                ☃ += ☃;
              }
              ☃ += ☃;
              ☃ += ☃;
            }
            ☃ += ☃;
            ☃ += ☃;
            ☃ += ☃;
            ☃ += ☃;
          }
        }
      }
    }
  }
  
  public void b(int ☃, int ☃, atf ☃)
  {
    int ☃ = this.n.K() + 1;
    
    double ☃ = 0.03125D;
    this.q = this.x.a(this.q, ☃ * 16, ☃ * 16, 0, 16, 16, 1, ☃, ☃, 1.0D);
    this.r = this.x.a(this.r, ☃ * 16, 109, ☃ * 16, 16, 1, 16, ☃, 1.0D, ☃);
    this.s = this.y.a(this.s, ☃ * 16, ☃ * 16, 0, 16, 16, 1, ☃ * 2.0D, ☃ * 2.0D, ☃ * 2.0D);
    for (int ☃ = 0; ☃ < 16; ☃++) {
      for (int ☃ = 0; ☃ < 16; ☃++)
      {
        boolean ☃ = this.q[(☃ + ☃ * 16)] + this.p.nextDouble() * 0.2D > 0.0D;
        boolean ☃ = this.r[(☃ + ☃ * 16)] + this.p.nextDouble() * 0.2D > 0.0D;
        int ☃ = (int)(this.s[(☃ + ☃ * 16)] / 3.0D + 3.0D + this.p.nextDouble() * 0.25D);
        
        int ☃ = -1;
        
        arc ☃ = b;
        arc ☃ = b;
        for (int ☃ = 127; ☃ >= 0; ☃--) {
          if ((☃ >= 127 - this.p.nextInt(5)) || (☃ <= this.p.nextInt(5)))
          {
            ☃.a(☃, ☃, ☃, c);
          }
          else
          {
            arc ☃ = ☃.a(☃, ☃, ☃);
            if ((☃.t() == null) || (☃.a() == axe.a)) {
              ☃ = -1;
            } else if (☃.t() == aju.aV) {
              if (☃ == -1)
              {
                if (☃ <= 0)
                {
                  ☃ = a;
                  ☃ = b;
                }
                else if ((☃ >= ☃ - 4) && (☃ <= ☃ + 1))
                {
                  ☃ = b;
                  ☃ = b;
                  if (☃)
                  {
                    ☃ = e;
                    ☃ = b;
                  }
                  if (☃)
                  {
                    ☃ = f;
                    ☃ = f;
                  }
                }
                if ((☃ < ☃) && ((☃ == null) || (☃.a() == axe.a))) {
                  ☃ = d;
                }
                ☃ = ☃;
                if (☃ >= ☃ - 1) {
                  ☃.a(☃, ☃, ☃, ☃);
                } else {
                  ☃.a(☃, ☃, ☃, ☃);
                }
              }
              else if (☃ > 0)
              {
                ☃--;
                ☃.a(☃, ☃, ☃, ☃);
              }
            }
          }
        }
      }
    }
  }
  
  public ase a(int ☃, int ☃)
  {
    this.p.setSeed(☃ * 341873128712L + ☃ * 132897987541L);
    
    atf ☃ = new atf();
    
    a(☃, ☃, ☃);
    b(☃, ☃, ☃);
    
    this.I.a(this.n, ☃, ☃, ☃);
    if (this.o) {
      this.H.a(this.n, ☃, ☃, ☃);
    }
    ase ☃ = new ase(this.n, ☃, ☃, ☃);
    aig[] ☃ = this.n.A().b(null, ☃ * 16, ☃ * 16, 16, 16);
    byte[] ☃ = ☃.l();
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      ☃[☃] = ((byte)aig.a(☃[☃]));
    }
    ☃.m();
    
    return ☃;
  }
  
  private double[] a(double[] ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    if (☃ == null) {
      ☃ = new double[☃ * ☃ * ☃];
    }
    double ☃ = 684.412D;
    double ☃ = 2053.236D;
    
    this.l = this.g.a(this.l, ☃, ☃, ☃, ☃, 1, ☃, 1.0D, 0.0D, 1.0D);
    this.m = this.h.a(this.m, ☃, ☃, ☃, ☃, 1, ☃, 100.0D, 0.0D, 100.0D);
    
    this.i = this.w.a(this.i, ☃, ☃, ☃, ☃, ☃, ☃, ☃ / 80.0D, ☃ / 60.0D, ☃ / 80.0D);
    this.j = this.u.a(this.j, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    this.k = this.v.a(this.k, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    int ☃ = 0;
    double[] ☃ = new double[☃];
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      ☃[☃] = (Math.cos(☃ * 3.141592653589793D * 6.0D / ☃) * 2.0D);
      
      double ☃ = ☃;
      if (☃ > ☃ / 2) {
        ☃ = ☃ - 1 - ☃;
      }
      if (☃ < 4.0D)
      {
        ☃ = 4.0D - ☃;
        ☃[☃] -= ☃ * ☃ * ☃ * 10.0D;
      }
    }
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        double ☃ = 0.0D;
        for (int ☃ = 0; ☃ < ☃; ☃++)
        {
          double ☃ = 0.0D;
          double ☃ = ☃[☃];
          double ☃ = this.j[☃] / 512.0D;
          double ☃ = this.k[☃] / 512.0D;
          double ☃ = (this.i[☃] / 10.0D + 1.0D) / 2.0D;
          if (☃ < 0.0D) {
            ☃ = ☃;
          } else if (☃ > 1.0D) {
            ☃ = ☃;
          } else {
            ☃ = ☃ + (☃ - ☃) * ☃;
          }
          ☃ -= ☃;
          if (☃ > ☃ - 4)
          {
            double ☃ = (☃ - (☃ - 4)) / 3.0F;
            ☃ = ☃ * (1.0D - ☃) + -10.0D * ☃;
          }
          if (☃ < ☃)
          {
            double ☃ = (☃ - ☃) / 4.0D;
            ☃ = on.a(☃, 0.0D, 1.0D);
            ☃ = ☃ * (1.0D - ☃) + -10.0D * ☃;
          }
          ☃[☃] = ☃;
          ☃++;
        }
      }
    }
    return ☃;
  }
  
  public void b(int ☃, int ☃)
  {
    alh.f = true;
    cj ☃ = new cj(☃ * 16, 0, ☃ * 16);
    
    ahn ☃ = new ahn(☃, ☃);
    
    this.H.a(this.n, this.p, ☃);
    for (int ☃ = 0; ☃ < 8; ☃++) {
      this.E.b(this.n, this.p, ☃.a(this.p.nextInt(16) + 8, this.p.nextInt(120) + 4, this.p.nextInt(16) + 8));
    }
    for (int ☃ = 0; ☃ < this.p.nextInt(this.p.nextInt(10) + 1) + 1; ☃++) {
      this.z.b(this.n, this.p, ☃.a(this.p.nextInt(16) + 8, this.p.nextInt(120) + 4, this.p.nextInt(16) + 8));
    }
    for (int ☃ = 0; ☃ < this.p.nextInt(this.p.nextInt(10) + 1); ☃++) {
      this.A.b(this.n, this.p, ☃.a(this.p.nextInt(16) + 8, this.p.nextInt(120) + 4, this.p.nextInt(16) + 8));
    }
    for (int ☃ = 0; ☃ < 10; ☃++) {
      this.B.b(this.n, this.p, ☃.a(this.p.nextInt(16) + 8, this.p.nextInt(128), this.p.nextInt(16) + 8));
    }
    if (this.p.nextBoolean()) {
      this.F.b(this.n, this.p, ☃.a(this.p.nextInt(16) + 8, this.p.nextInt(128), this.p.nextInt(16) + 8));
    }
    if (this.p.nextBoolean()) {
      this.G.b(this.n, this.p, ☃.a(this.p.nextInt(16) + 8, this.p.nextInt(128), this.p.nextInt(16) + 8));
    }
    for (int ☃ = 0; ☃ < 16; ☃++) {
      this.C.b(this.n, this.p, ☃.a(this.p.nextInt(16), this.p.nextInt(108) + 10, this.p.nextInt(16)));
    }
    for (int ☃ = 0; ☃ < 16; ☃++) {
      this.D.b(this.n, this.p, ☃.a(this.p.nextInt(16), this.p.nextInt(108) + 10, this.p.nextInt(16)));
    }
    alh.f = false;
  }
  
  public boolean a(ase ☃, int ☃, int ☃)
  {
    return false;
  }
  
  public List<aig.c> a(sc ☃, cj ☃)
  {
    if (☃ == sc.a)
    {
      if (this.H.b(☃)) {
        return this.H.b();
      }
      if ((this.H.b(this.n, ☃)) && (this.n.o(☃.b()).t() == aju.by)) {
        return this.H.b();
      }
    }
    aig ☃ = this.n.b(☃);
    return ☃.a(☃);
  }
  
  public cj a(aht ☃, String ☃, cj ☃)
  {
    return null;
  }
  
  public void b(ase ☃, int ☃, int ☃)
  {
    this.H.a(this.n, ☃, ☃, null);
  }
}
