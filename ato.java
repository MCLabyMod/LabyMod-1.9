import java.util.List;
import java.util.Random;

public class ato
  implements ary
{
  private Random h;
  protected static final arc a = aju.bH.u();
  protected static final arc b = aju.a.u();
  private final awu i;
  private final awu j;
  private final awu k;
  public awu c;
  public awu d;
  private final aht l;
  private final boolean m;
  private final avq n = new avq(this);
  private final awy o;
  private double[] p;
  private aig[] q;
  double[] e;
  double[] f;
  double[] g;
  
  public ato(aht ☃, boolean ☃, long ☃)
  {
    this.l = ☃;
    this.m = ☃;
    
    this.h = new Random(☃);
    this.i = new awu(this.h, 16);
    this.j = new awu(this.h, 16);
    this.k = new awu(this.h, 8);
    
    this.c = new awu(this.h, 10);
    this.d = new awu(this.h, 16);
    
    this.o = new awy(this.h);
  }
  
  public void a(int ☃, int ☃, atf ☃)
  {
    int ☃ = 2;
    
    int ☃ = ☃ + 1;
    int ☃ = 33;
    int ☃ = ☃ + 1;
    this.p = a(this.p, ☃ * ☃, 0, ☃ * ☃, ☃, ☃, ☃);
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      for (int ☃ = 0; ☃ < ☃; ☃++) {
        for (int ☃ = 0; ☃ < 32; ☃++)
        {
          double ☃ = 0.25D;
          double ☃ = this.p[(((☃ + 0) * ☃ + ☃ + 0) * ☃ + ☃ + 0)];
          double ☃ = this.p[(((☃ + 0) * ☃ + ☃ + 1) * ☃ + ☃ + 0)];
          double ☃ = this.p[(((☃ + 1) * ☃ + ☃ + 0) * ☃ + ☃ + 0)];
          double ☃ = this.p[(((☃ + 1) * ☃ + ☃ + 1) * ☃ + ☃ + 0)];
          
          double ☃ = (this.p[(((☃ + 0) * ☃ + ☃ + 0) * ☃ + ☃ + 1)] - ☃) * ☃;
          double ☃ = (this.p[(((☃ + 0) * ☃ + ☃ + 1) * ☃ + ☃ + 1)] - ☃) * ☃;
          double ☃ = (this.p[(((☃ + 1) * ☃ + ☃ + 0) * ☃ + ☃ + 1)] - ☃) * ☃;
          double ☃ = (this.p[(((☃ + 1) * ☃ + ☃ + 1) * ☃ + ☃ + 1)] - ☃) * ☃;
          for (int ☃ = 0; ☃ < 4; ☃++)
          {
            double ☃ = 0.125D;
            
            double ☃ = ☃;
            double ☃ = ☃;
            double ☃ = (☃ - ☃) * ☃;
            double ☃ = (☃ - ☃) * ☃;
            for (int ☃ = 0; ☃ < 8; ☃++)
            {
              double ☃ = 0.125D;
              
              double ☃ = ☃;
              double ☃ = (☃ - ☃) * ☃;
              for (int ☃ = 0; ☃ < 8; ☃++)
              {
                arc ☃ = b;
                if (☃ > 0.0D) {
                  ☃ = a;
                }
                int ☃ = ☃ + ☃ * 8;
                int ☃ = ☃ + ☃ * 4;
                int ☃ = ☃ + ☃ * 8;
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
  
  public void a(atf ☃)
  {
    for (int ☃ = 0; ☃ < 16; ☃++) {
      for (int ☃ = 0; ☃ < 16; ☃++)
      {
        int ☃ = 1;
        int ☃ = -1;
        
        arc ☃ = a;
        arc ☃ = a;
        for (int ☃ = 127; ☃ >= 0; ☃--)
        {
          arc ☃ = ☃.a(☃, ☃, ☃);
          if (☃.a() == axe.a) {
            ☃ = -1;
          } else if (☃.t() == aju.b) {
            if (☃ == -1)
            {
              if (☃ <= 0)
              {
                ☃ = b;
                ☃ = a;
              }
              ☃ = ☃;
              if (☃ >= 0) {
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
  
  public ase a(int ☃, int ☃)
  {
    this.h.setSeed(☃ * 341873128712L + ☃ * 132897987541L);
    
    atf ☃ = new atf();
    this.q = this.l.A().b(this.q, ☃ * 16, ☃ * 16, 16, 16);
    
    a(☃, ☃, ☃);
    a(☃);
    if (this.m) {
      this.n.a(this.l, ☃, ☃, ☃);
    }
    ase ☃ = new ase(this.l, ☃, ☃, ☃);
    byte[] ☃ = ☃.l();
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      ☃[☃] = ((byte)aig.a(this.q[☃]));
    }
    ☃.b();
    
    return ☃;
  }
  
  private float a(int ☃, int ☃, int ☃, int ☃)
  {
    float ☃ = ☃ * 2 + ☃;
    float ☃ = ☃ * 2 + ☃;
    float ☃ = 100.0F - on.c(☃ * ☃ + ☃ * ☃) * 8.0F;
    if (☃ > 80.0F) {
      ☃ = 80.0F;
    }
    if (☃ < -100.0F) {
      ☃ = -100.0F;
    }
    for (int ☃ = -12; ☃ <= 12; ☃++) {
      for (int ☃ = -12; ☃ <= 12; ☃++)
      {
        long ☃ = ☃ + ☃;
        long ☃ = ☃ + ☃;
        if ((☃ * ☃ + ☃ * ☃ > 4096L) && (this.o.a(☃, ☃) < -0.8999999761581421D))
        {
          float ☃ = (on.e((float)☃) * 3439.0F + on.e((float)☃) * 147.0F) % 13.0F + 9.0F;
          ☃ = ☃ - ☃ * 2;
          ☃ = ☃ - ☃ * 2;
          float ☃ = 100.0F - on.c(☃ * ☃ + ☃ * ☃) * ☃;
          if (☃ > 80.0F) {
            ☃ = 80.0F;
          }
          if (☃ < -100.0F) {
            ☃ = -100.0F;
          }
          if (☃ > ☃) {
            ☃ = ☃;
          }
        }
      }
    }
    return ☃;
  }
  
  public boolean c(int ☃, int ☃)
  {
    return (☃ * ☃ + ☃ * ☃ > 4096L) && (a(☃, ☃, 1, 1) >= 0.0F);
  }
  
  private double[] a(double[] ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    if (☃ == null) {
      ☃ = new double[☃ * ☃ * ☃];
    }
    double ☃ = 684.412D;
    double ☃ = 684.412D;
    ☃ *= 2.0D;
    
    this.e = this.k.a(this.e, ☃, ☃, ☃, ☃, ☃, ☃, ☃ / 80.0D, ☃ / 160.0D, ☃ / 80.0D);
    this.f = this.i.a(this.f, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    this.g = this.j.a(this.g, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    int ☃ = ☃ / 2;
    int ☃ = ☃ / 2;
    
    int ☃ = 0;
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        float ☃ = a(☃, ☃, ☃, ☃);
        for (int ☃ = 0; ☃ < ☃; ☃++)
        {
          double ☃ = 0.0D;
          double ☃ = this.f[☃] / 512.0D;
          double ☃ = this.g[☃] / 512.0D;
          
          double ☃ = (this.e[☃] / 10.0D + 1.0D) / 2.0D;
          if (☃ < 0.0D) {
            ☃ = ☃;
          } else if (☃ > 1.0D) {
            ☃ = ☃;
          } else {
            ☃ = ☃ + (☃ - ☃) * ☃;
          }
          ☃ -= 8.0D;
          
          ☃ += ☃;
          
          int ☃ = 2;
          if (☃ > ☃ / 2 - ☃)
          {
            double ☃ = (☃ - (☃ / 2 - ☃)) / 64.0F;
            ☃ = on.a(☃, 0.0D, 1.0D);
            ☃ = ☃ * (1.0D - ☃) + -3000.0D * ☃;
          }
          ☃ = 8;
          if (☃ < ☃)
          {
            double ☃ = (☃ - ☃) / (☃ - 1.0F);
            ☃ = ☃ * (1.0D - ☃) + -30.0D * ☃;
          }
          ☃[☃] = ☃;
          ☃++;
        }
      }
    }
    return ☃;
  }
  
  private final aub r = new aub();
  
  public void b(int ☃, int ☃)
  {
    alh.f = true;
    
    cj ☃ = new cj(☃ * 16, 0, ☃ * 16);
    if (this.m) {
      this.n.a(this.l, this.h, new ahn(☃, ☃));
    }
    this.l.b(☃.a(16, 0, 16)).a(this.l, this.l.r, ☃);
    
    long ☃ = ☃ * ☃ + ☃ * ☃;
    if (☃ > 4096L)
    {
      float ☃ = a(☃, ☃, 1, 1);
      if ((☃ < -20.0F) && (this.h.nextInt(14) == 0))
      {
        this.r.b(this.l, this.h, ☃.a(this.h.nextInt(16) + 8, 55 + this.h.nextInt(16), this.h.nextInt(16) + 8));
        if (this.h.nextInt(4) == 0) {
          this.r.b(this.l, this.h, ☃.a(this.h.nextInt(16) + 8, 55 + this.h.nextInt(16), this.h.nextInt(16) + 8));
        }
      }
      if (a(☃, ☃, 1, 1) > 40.0F)
      {
        int ☃ = this.h.nextInt(5);
        for (int ☃ = 0; ☃ < ☃; ☃++)
        {
          int ☃ = this.h.nextInt(16) + 8;
          int ☃ = this.h.nextInt(16) + 8;
          int ☃ = this.l.l(☃.a(☃, 0, ☃)).q();
          if (☃ > 0)
          {
            int ☃ = ☃ - 1;
            if ((this.l.d(☃.a(☃, ☃ + 1, ☃))) && (this.l.o(☃.a(☃, ☃, ☃)).t() == aju.bH)) {
              akf.a(this.l, ☃.a(☃, ☃ + 1, ☃), this.h, 8);
            }
          }
        }
      }
    }
    alh.f = false;
  }
  
  public boolean a(ase ☃, int ☃, int ☃)
  {
    return false;
  }
  
  public List<aig.c> a(sc ☃, cj ☃)
  {
    return this.l.b(☃).a(☃);
  }
  
  public cj a(aht ☃, String ☃, cj ☃)
  {
    return null;
  }
  
  public void b(ase ☃, int ☃, int ☃) {}
}
