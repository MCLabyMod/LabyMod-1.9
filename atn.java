import java.util.List;
import java.util.Random;

public class atn
  implements ary
{
  protected static final arc a = aju.b.u();
  private final Random i;
  private final awu j;
  private final awu k;
  private final awu l;
  private final awv m;
  public awu b;
  public awu c;
  public awu d;
  private final aht n;
  private final boolean o;
  private final ahy p;
  private final double[] q;
  private final float[] r;
  private atg s;
  private arc t = aju.j.u();
  
  public atn(aht ☃, long ☃, boolean ☃, String ☃)
  {
    this.n = ☃;
    this.o = ☃;
    this.p = ☃.T().t();
    
    this.i = new Random(☃);
    this.j = new awu(this.i, 16);
    this.k = new awu(this.i, 16);
    this.l = new awu(this.i, 8);
    this.m = new awv(this.i, 4);
    
    this.b = new awu(this.i, 10);
    this.c = new awu(this.i, 16);
    
    this.d = new awu(this.i, 8);
    
    this.q = new double['̹'];
    
    this.r = new float[25];
    for (int ☃ = -2; ☃ <= 2; ☃++) {
      for (int ☃ = -2; ☃ <= 2; ☃++)
      {
        float ☃ = 10.0F / on.c(☃ * ☃ + ☃ * ☃ + 0.2F);
        this.r[(☃ + 2 + (☃ + 2) * 5)] = ☃;
      }
    }
    if (☃ != null)
    {
      this.s = atg.a.a(☃).b();
      this.t = (this.s.E ? aju.l.u() : aju.j.u());
      ☃.b(this.s.q);
    }
  }
  
  public void a(int ☃, int ☃, atf ☃)
  {
    this.C = this.n.A().a(this.C, ☃ * 4 - 2, ☃ * 4 - 2, 10, 10);
    a(☃ * 4, 0, ☃ * 4);
    for (int ☃ = 0; ☃ < 4; ☃++)
    {
      int ☃ = ☃ * 5;
      int ☃ = (☃ + 1) * 5;
      for (int ☃ = 0; ☃ < 4; ☃++)
      {
        int ☃ = (☃ + ☃) * 33;
        int ☃ = (☃ + ☃ + 1) * 33;
        int ☃ = (☃ + ☃) * 33;
        int ☃ = (☃ + ☃ + 1) * 33;
        for (int ☃ = 0; ☃ < 32; ☃++)
        {
          double ☃ = 0.125D;
          double ☃ = this.q[(☃ + ☃)];
          double ☃ = this.q[(☃ + ☃)];
          double ☃ = this.q[(☃ + ☃)];
          double ☃ = this.q[(☃ + ☃)];
          
          double ☃ = (this.q[(☃ + ☃ + 1)] - ☃) * ☃;
          double ☃ = (this.q[(☃ + ☃ + 1)] - ☃) * ☃;
          double ☃ = (this.q[(☃ + ☃ + 1)] - ☃) * ☃;
          double ☃ = (this.q[(☃ + ☃ + 1)] - ☃) * ☃;
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
              ☃ -= ☃;
              for (int ☃ = 0; ☃ < 4; ☃++) {
                if (☃ += ☃ > 0.0D) {
                  ☃.a(☃ * 4 + ☃, ☃ * 8 + ☃, ☃ * 4 + ☃, a);
                } else if (☃ * 8 + ☃ < this.s.q) {
                  ☃.a(☃ * 4 + ☃, ☃ * 8 + ☃, ☃ * 4 + ☃, this.t);
                }
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
  
  private double[] u = new double['Ā'];
  
  public void a(int ☃, int ☃, atf ☃, aig[] ☃)
  {
    double ☃ = 0.03125D;
    this.u = this.m.a(this.u, ☃ * 16, ☃ * 16, 16, 16, ☃ * 2.0D, ☃ * 2.0D, 1.0D);
    for (int ☃ = 0; ☃ < 16; ☃++) {
      for (int ☃ = 0; ☃ < 16; ☃++)
      {
        aig ☃ = ☃[(☃ + ☃ * 16)];
        ☃.a(this.n, this.i, ☃, ☃ * 16 + ☃, ☃ * 16 + ☃, this.u[(☃ + ☃ * 16)]);
      }
    }
  }
  
  private final atk v = new atj();
  private final awb w = new awb();
  private final awj x = new awj();
  private final avs y = new avs();
  private final avz z = new avz();
  private final atk A = new ate();
  private final avx B = new avx();
  private aig[] C;
  double[] e;
  double[] f;
  double[] g;
  double[] h;
  
  public ase a(int ☃, int ☃)
  {
    this.i.setSeed(☃ * 341873128712L + ☃ * 132897987541L);
    
    atf ☃ = new atf();
    
    a(☃, ☃, ☃);
    this.C = this.n.A().b(this.C, ☃ * 16, ☃ * 16, 16, 16);
    a(☃, ☃, ☃, this.C);
    if (this.s.r) {
      this.v.a(this.n, ☃, ☃, ☃);
    }
    if (this.s.z) {
      this.A.a(this.n, ☃, ☃, ☃);
    }
    if (this.o)
    {
      if (this.s.w) {
        this.y.a(this.n, ☃, ☃, ☃);
      }
      if (this.s.v) {
        this.x.a(this.n, ☃, ☃, ☃);
      }
      if (this.s.u) {
        this.w.a(this.n, ☃, ☃, ☃);
      }
      if (this.s.x) {
        this.z.a(this.n, ☃, ☃, ☃);
      }
      if (this.s.y) {
        this.B.a(this.n, ☃, ☃, ☃);
      }
    }
    ase ☃ = new ase(this.n, ☃, ☃, ☃);
    byte[] ☃ = ☃.l();
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      ☃[☃] = ((byte)aig.a(this.C[☃]));
    }
    ☃.b();
    
    return ☃;
  }
  
  private void a(int ☃, int ☃, int ☃)
  {
    this.h = this.c.a(this.h, ☃, ☃, 5, 5, this.s.e, this.s.f, this.s.g);
    
    float ☃ = this.s.a;
    float ☃ = this.s.b;
    this.e = this.l.a(this.e, ☃, ☃, ☃, 5, 33, 5, ☃ / this.s.h, ☃ / this.s.i, ☃ / this.s.j);
    this.f = this.j.a(this.f, ☃, ☃, ☃, 5, 33, 5, ☃, ☃, ☃);
    this.g = this.k.a(this.g, ☃, ☃, ☃, 5, 33, 5, ☃, ☃, ☃);
    ☃ = ☃ = 0;
    
    int ☃ = 0;
    int ☃ = 0;
    for (int ☃ = 0; ☃ < 5; ☃++) {
      for (int ☃ = 0; ☃ < 5; ☃++)
      {
        float ☃ = 0.0F;
        float ☃ = 0.0F;
        float ☃ = 0.0F;
        
        int ☃ = 2;
        
        aig ☃ = this.C[(☃ + 2 + (☃ + 2) * 10)];
        for (int ☃ = -☃; ☃ <= ☃; ☃++) {
          for (int ☃ = -☃; ☃ <= ☃; ☃++)
          {
            aig ☃ = this.C[(☃ + ☃ + 2 + (☃ + ☃ + 2) * 10)];
            float ☃ = this.s.n + ☃.j() * this.s.m;
            float ☃ = this.s.p + ☃.m() * this.s.o;
            if ((this.p == ahy.e) && (☃ > 0.0F))
            {
              ☃ = 1.0F + ☃ * 2.0F;
              ☃ = 1.0F + ☃ * 4.0F;
            }
            float ☃ = this.r[(☃ + 2 + (☃ + 2) * 5)] / (☃ + 2.0F);
            if (☃.j() > ☃.j()) {
              ☃ /= 2.0F;
            }
            ☃ += ☃ * ☃;
            ☃ += ☃ * ☃;
            ☃ += ☃;
          }
        }
        ☃ /= ☃;
        ☃ /= ☃;
        
        ☃ = ☃ * 0.9F + 0.1F;
        ☃ = (☃ * 4.0F - 1.0F) / 8.0F;
        
        double ☃ = this.h[☃] / 8000.0D;
        if (☃ < 0.0D) {
          ☃ = -☃ * 0.3D;
        }
        ☃ = ☃ * 3.0D - 2.0D;
        if (☃ < 0.0D)
        {
          ☃ /= 2.0D;
          if (☃ < -1.0D) {
            ☃ = -1.0D;
          }
          ☃ /= 1.4D;
          ☃ /= 2.0D;
        }
        else
        {
          if (☃ > 1.0D) {
            ☃ = 1.0D;
          }
          ☃ /= 8.0D;
        }
        ☃++;
        
        double ☃ = ☃;
        double ☃ = ☃;
        ☃ += ☃ * 0.2D;
        ☃ = ☃ * this.s.k / 8.0D;
        
        double ☃ = this.s.k + ☃ * 4.0D;
        for (int ☃ = 0; ☃ < 33; ☃++)
        {
          double ☃ = (☃ - ☃) * this.s.l * 128.0D / 256.0D / ☃;
          if (☃ < 0.0D) {
            ☃ *= 4.0D;
          }
          double ☃ = this.f[☃] / this.s.d;
          double ☃ = this.g[☃] / this.s.c;
          
          double ☃ = (this.e[☃] / 10.0D + 1.0D) / 2.0D;
          double ☃ = on.b(☃, ☃, ☃) - ☃;
          if (☃ > 29)
          {
            double ☃ = (☃ - 29) / 3.0F;
            ☃ = ☃ * (1.0D - ☃) + -10.0D * ☃;
          }
          this.q[☃] = ☃;
          ☃++;
        }
      }
    }
  }
  
  public void b(int ☃, int ☃)
  {
    alh.f = true;
    int ☃ = ☃ * 16;
    int ☃ = ☃ * 16;
    cj ☃ = new cj(☃, 0, ☃);
    aig ☃ = this.n.b(☃.a(16, 0, 16));
    
    this.i.setSeed(this.n.O());
    long ☃ = this.i.nextLong() / 2L * 2L + 1L;
    long ☃ = this.i.nextLong() / 2L * 2L + 1L;
    this.i.setSeed(☃ * ☃ + ☃ * ☃ ^ this.n.O());
    
    boolean ☃ = false;
    
    ahn ☃ = new ahn(☃, ☃);
    if (this.o)
    {
      if (this.s.w) {
        this.y.a(this.n, this.i, ☃);
      }
      if (this.s.v) {
        ☃ = this.x.a(this.n, this.i, ☃);
      }
      if (this.s.u) {
        this.w.a(this.n, this.i, ☃);
      }
      if (this.s.x) {
        this.z.a(this.n, this.i, ☃);
      }
      if (this.s.y) {
        this.B.a(this.n, this.i, ☃);
      }
    }
    if ((☃ != ail.d) && (☃ != ail.s) && (this.s.A) && 
      (!☃) && (this.i.nextInt(this.s.B) == 0))
    {
      int ☃ = this.i.nextInt(16) + 8;
      int ☃ = this.i.nextInt(256);
      int ☃ = this.i.nextInt(16) + 8;
      new aum(aju.j).b(this.n, this.i, ☃.a(☃, ☃, ☃));
    }
    if ((!☃) && (this.i.nextInt(this.s.D / 10) == 0) && (this.s.C))
    {
      int ☃ = this.i.nextInt(16) + 8;
      int ☃ = this.i.nextInt(this.i.nextInt(248) + 8);
      int ☃ = this.i.nextInt(16) + 8;
      if ((☃ < this.n.K()) || (this.i.nextInt(this.s.D / 8) == 0)) {
        new aum(aju.l).b(this.n, this.i, ☃.a(☃, ☃, ☃));
      }
    }
    if (this.s.s) {
      for (int ☃ = 0; ☃ < this.s.t; ☃++)
      {
        int ☃ = this.i.nextInt(16) + 8;
        int ☃ = this.i.nextInt(256);
        int ☃ = this.i.nextInt(16) + 8;
        new aus().b(this.n, this.i, ☃.a(☃, ☃, ☃));
      }
    }
    ☃.a(this.n, this.i, new cj(☃, 0, ☃));
    
    aia.a(this.n, ☃, ☃ + 8, ☃ + 8, 16, 16, this.i);
    
    ☃ = ☃.a(8, 0, 8);
    for (int ☃ = 0; ☃ < 16; ☃++) {
      for (int ☃ = 0; ☃ < 16; ☃++)
      {
        cj ☃ = this.n.p(☃.a(☃, 0, ☃));
        cj ☃ = ☃.b();
        if (this.n.u(☃)) {
          this.n.a(☃, aju.aI.u(), 2);
        }
        if (this.n.f(☃, true)) {
          this.n.a(☃, aju.aH.u(), 2);
        }
      }
    }
    alh.f = false;
  }
  
  public boolean a(ase ☃, int ☃, int ☃)
  {
    boolean ☃ = false;
    if ((this.s.y) && (this.o)) {
      if (☃.x() < 3600L) {
        ☃ |= this.B.a(this.n, this.i, new ahn(☃, ☃));
      }
    }
    return ☃;
  }
  
  public List<aig.c> a(sc ☃, cj ☃)
  {
    aig ☃ = this.n.b(☃);
    if (this.o)
    {
      if ((☃ == sc.a) && (this.z.a(☃))) {
        return this.z.b();
      }
      if ((☃ == sc.a) && (this.s.y) && (this.B.b(this.n, ☃))) {
        return this.B.b();
      }
    }
    return ☃.a(☃);
  }
  
  public cj a(aht ☃, String ☃, cj ☃)
  {
    if (("Stronghold".equals(☃)) && (this.w != null)) {
      return this.w.a(☃, ☃);
    }
    return null;
  }
  
  public void b(ase ☃, int ☃, int ☃)
  {
    if (this.o)
    {
      if (this.s.w) {
        this.y.a(this.n, ☃, ☃, null);
      }
      if (this.s.v) {
        this.x.a(this.n, ☃, ☃, null);
      }
      if (this.s.u) {
        this.w.a(this.n, ☃, ☃, null);
      }
      if (this.s.x) {
        this.z.a(this.n, ☃, ☃, null);
      }
      if (this.s.y) {
        this.B.a(this.n, ☃, ☃, null);
      }
    }
  }
}
