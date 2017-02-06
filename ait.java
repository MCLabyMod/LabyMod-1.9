import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ait
  extends aig
{
  protected static final arc y = aju.d.u().a(akt.a, akt.a.b);
  protected static final arc z = aju.c.u();
  protected static final arc A = aju.cz.u();
  protected static final arc B = aju.cu.u();
  protected static final arc C = B.a(akj.a, act.b);
  protected static final arc D = aju.m.u().a(aof.a, aof.a.b);
  private arc[] E;
  private long F;
  private awv G;
  private awv H;
  private awv I;
  private boolean J;
  private boolean K;
  
  public ait(boolean ☃, boolean ☃, aig.a ☃)
  {
    super(☃);
    this.J = ☃;
    this.K = ☃;
    
    this.v.clear();
    this.r = D;
    this.s = B;
    
    this.t.z = 64537;
    this.t.C = 20;
    this.t.E = 3;
    this.t.F = 5;
    this.t.A = 0;
    
    this.v.clear();
    if (☃) {
      this.t.z = 5;
    }
  }
  
  public atp a(Random ☃)
  {
    return n;
  }
  
  public int c(cj ☃)
  {
    return 10387789;
  }
  
  public int b(cj ☃)
  {
    return 9470285;
  }
  
  public void a(aht ☃, Random ☃, cj ☃)
  {
    super.a(☃, ☃, ☃);
  }
  
  public void a(aht ☃, Random ☃, atf ☃, int ☃, int ☃, double ☃)
  {
    if ((this.E == null) || (this.F != ☃.O())) {
      a(☃.O());
    }
    if ((this.G == null) || (this.H == null) || (this.F != ☃.O()))
    {
      Random ☃ = new Random(this.F);
      this.G = new awv(☃, 4);
      this.H = new awv(☃, 1);
    }
    this.F = ☃.O();
    
    double ☃ = 0.0D;
    if (this.J)
    {
      int ☃ = (☃ & 0xFFFFFFF0) + (☃ & 0xF);
      int ☃ = (☃ & 0xFFFFFFF0) + (☃ & 0xF);
      
      double ☃ = Math.min(Math.abs(☃), this.G.a(☃ * 0.25D, ☃ * 0.25D));
      if (☃ > 0.0D)
      {
        double ☃ = 0.001953125D;
        double ☃ = Math.abs(this.H.a(☃ * ☃, ☃ * ☃));
        ☃ = ☃ * ☃ * 2.5D;
        double ☃ = Math.ceil(☃ * 50.0D) + 14.0D;
        if (☃ > ☃) {
          ☃ = ☃;
        }
        ☃ += 64.0D;
      }
    }
    int ☃ = ☃ & 0xF;
    int ☃ = ☃ & 0xF;
    
    int ☃ = ☃.K();
    
    arc ☃ = B;
    arc ☃ = this.s;
    
    int ☃ = (int)(☃ / 3.0D + 3.0D + ☃.nextDouble() * 0.25D);
    boolean ☃ = Math.cos(☃ / 3.0D * 3.141592653589793D) > 0.0D;
    int ☃ = -1;
    boolean ☃ = false;
    for (int ☃ = 255; ☃ >= 0; ☃--)
    {
      if ((☃.a(☃, ☃, ☃).a() == axe.a) && (☃ < (int)☃)) {
        ☃.a(☃, ☃, ☃, a);
      }
      if (☃ <= ☃.nextInt(5))
      {
        ☃.a(☃, ☃, ☃, c);
      }
      else
      {
        arc ☃ = ☃.a(☃, ☃, ☃);
        if (☃.a() == axe.a) {
          ☃ = -1;
        } else if (☃.t() == aju.b) {
          if (☃ == -1)
          {
            ☃ = false;
            if (☃ <= 0)
            {
              ☃ = b;
              ☃ = a;
            }
            else if ((☃ >= ☃ - 4) && (☃ <= ☃ + 1))
            {
              ☃ = B;
              ☃ = this.s;
            }
            if ((☃ < ☃) && ((☃ == null) || (☃.a() == axe.a))) {
              ☃ = h;
            }
            ☃ = ☃ + Math.max(0, ☃ - ☃);
            if (☃ >= ☃ - 1)
            {
              if ((this.K) && (☃ > 86 + ☃ * 2))
              {
                if (☃) {
                  ☃.a(☃, ☃, ☃, y);
                } else {
                  ☃.a(☃, ☃, ☃, z);
                }
              }
              else if (☃ > ☃ + 3 + ☃)
              {
                arc ☃;
                arc ☃;
                if ((☃ < 64) || (☃ > 127))
                {
                  ☃ = C;
                }
                else
                {
                  arc ☃;
                  if (☃) {
                    ☃ = A;
                  } else {
                    ☃ = a(☃, ☃, ☃);
                  }
                }
                ☃.a(☃, ☃, ☃, ☃);
              }
              else
              {
                ☃.a(☃, ☃, ☃, this.r);
                ☃ = true;
              }
            }
            else
            {
              ☃.a(☃, ☃, ☃, ☃);
              if (☃.t() == aju.cu) {
                ☃.a(☃, ☃, ☃, C);
              }
            }
          }
          else if (☃ > 0)
          {
            ☃--;
            if (☃) {
              ☃.a(☃, ☃, ☃, C);
            } else {
              ☃.a(☃, ☃, ☃, a(☃, ☃, ☃));
            }
          }
        }
      }
    }
  }
  
  private void a(long ☃)
  {
    this.E = new arc[64];
    Arrays.fill(this.E, A);
    
    Random ☃ = new Random(☃);
    this.I = new awv(☃, 1);
    for (int ☃ = 0; ☃ < 64; ☃++)
    {
      ☃ += ☃.nextInt(5) + 1;
      if (☃ < 64) {
        this.E[☃] = C;
      }
    }
    int ☃ = ☃.nextInt(4) + 2;
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      int ☃ = ☃.nextInt(3) + 1;
      int ☃ = ☃.nextInt(64);
      for (int ☃ = 0; (☃ + ☃ < 64) && (☃ < ☃); ☃++) {
        this.E[(☃ + ☃)] = B.a(akj.a, act.e);
      }
    }
    int ☃ = ☃.nextInt(4) + 2;
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      int ☃ = ☃.nextInt(3) + 2;
      int ☃ = ☃.nextInt(64);
      for (int ☃ = 0; (☃ + ☃ < 64) && (☃ < ☃); ☃++) {
        this.E[(☃ + ☃)] = B.a(akj.a, act.m);
      }
    }
    int ☃ = ☃.nextInt(4) + 2;
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      int ☃ = ☃.nextInt(3) + 1;
      int ☃ = ☃.nextInt(64);
      for (int ☃ = 0; (☃ + ☃ < 64) && (☃ < ☃); ☃++) {
        this.E[(☃ + ☃)] = B.a(akj.a, act.o);
      }
    }
    int ☃ = ☃.nextInt(3) + 3;
    int ☃ = 0;
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      int ☃ = 1;
      ☃ += ☃.nextInt(16) + 4;
      for (int ☃ = 0; (☃ + ☃ < 64) && (☃ < ☃); ☃++)
      {
        this.E[(☃ + ☃)] = B.a(akj.a, act.a);
        if ((☃ + ☃ > 1) && (☃.nextBoolean())) {
          this.E[(☃ + ☃ - 1)] = B.a(akj.a, act.i);
        }
        if ((☃ + ☃ < 63) && (☃.nextBoolean())) {
          this.E[(☃ + ☃ + 1)] = B.a(akj.a, act.i);
        }
      }
    }
  }
  
  private arc a(int ☃, int ☃, int ☃)
  {
    int ☃ = (int)Math.round(this.I.a(☃ / 512.0D, ☃ / 512.0D) * 2.0D);
    return this.E[((☃ + ☃ + 64) % 64)];
  }
}
