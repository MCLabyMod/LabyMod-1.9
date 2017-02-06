import java.util.Random;

public class blm
{
  public static class c
    extends blx
  {
    private int G;
    private final bly H;
    private du I;
    boolean a;
    
    public c(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, bly ☃, dn ☃)
    {
      super(☃, ☃, ☃, 0.0D, 0.0D, 0.0D);
      this.i = ☃;
      this.j = ☃;
      this.k = ☃;
      this.H = ☃;
      this.v = 8;
      if (☃ != null)
      {
        this.I = ☃.c("Explosions", 10);
        if (this.I.c_())
        {
          this.I = null;
        }
        else
        {
          this.v = (this.I.c() * 2 - 1);
          for (int ☃ = 0; ☃ < this.I.c(); ☃++)
          {
            dn ☃ = this.I.b(☃);
            if (☃.p("Flicker"))
            {
              this.a = true;
              this.v += 15;
              break;
            }
          }
        }
      }
    }
    
    public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃) {}
    
    public void a()
    {
      if ((this.G == 0) && (this.I != null))
      {
        boolean ☃ = m();
        
        boolean ☃ = false;
        if (this.I.c() >= 3) {
          ☃ = true;
        } else {
          for (int ☃ = 0; ☃ < this.I.c(); ☃++)
          {
            dn ☃ = this.I.b(☃);
            if (☃.f("Type") == 1)
            {
              ☃ = true;
              break;
            }
          }
        }
        nf ☃;
        nf ☃;
        if (☃) {
          ☃ = ☃ ? ng.bp : ng.bo;
        } else {
          ☃ = ☃ ? ng.bn : ng.bm;
        }
        this.b.a(this.f, this.g, this.h, ☃, nh.i, 20.0F, 0.95F + this.p.nextFloat() * 0.1F, true);
      }
      if ((this.G % 2 == 0) && (this.I != null) && (this.G / 2 < this.I.c()))
      {
        int ☃ = this.G / 2;
        dn ☃ = this.I.b(☃);
        
        int ☃ = ☃.f("Type");
        boolean ☃ = ☃.p("Trail");
        boolean ☃ = ☃.p("Flicker");
        int[] ☃ = ☃.n("Colors");
        int[] ☃ = ☃.n("FadeColors");
        if (☃.length == 0) {
          ☃ = new int[] { acu.a[0] };
        }
        if (☃ == 1) {
          a(0.5D, 4, ☃, ☃, ☃, ☃);
        } else if (☃ == 2) {
          a(0.5D, new double[][] { { 0.0D, 1.0D }, { 0.3455D, 0.309D }, { 0.9511D, 0.309D }, { 0.3795918367346939D, -0.12653061224489795D }, { 0.6122448979591837D, -0.8040816326530612D }, { 0.0D, -0.35918367346938773D } }, ☃, ☃, ☃, ☃, false);
        } else if (☃ == 3) {
          a(0.5D, new double[][] { { 0.0D, 0.2D }, { 0.2D, 0.2D }, { 0.2D, 0.6D }, { 0.6D, 0.6D }, { 0.6D, 0.2D }, { 0.2D, 0.2D }, { 0.2D, 0.0D }, { 0.4D, 0.0D }, { 0.4D, -0.6D }, { 0.2D, -0.6D }, { 0.2D, -0.4D }, { 0.0D, -0.4D } }, ☃, ☃, ☃, ☃, true);
        } else if (☃ == 4) {
          a(☃, ☃, ☃, ☃);
        } else {
          a(0.25D, 2, ☃, ☃, ☃, ☃);
        }
        int ☃ = ☃[0];
        float ☃ = ((☃ & 0xFF0000) >> 16) / 255.0F;
        float ☃ = ((☃ & 0xFF00) >> 8) / 255.0F;
        float ☃ = ((☃ & 0xFF) >> 0) / 255.0F;
        blm.a ☃ = new blm.a(this.b, this.f, this.g, this.h);
        ☃.a(☃, ☃, ☃);
        this.H.a(☃);
      }
      this.G += 1;
      if (this.G > this.v)
      {
        if (this.a)
        {
          boolean ☃ = m();
          nf ☃ = ☃ ? ng.bt : ng.bs;
          this.b.a(this.f, this.g, this.h, ☃, nh.i, 20.0F, 0.9F + this.p.nextFloat() * 0.15F, true);
        }
        i();
      }
    }
    
    private boolean m()
    {
      bcf ☃ = bcf.z();
      if ((☃ != null) && (☃.aa() != null) && 
        (☃.aa().e(this.f, this.g, this.h) < 256.0D)) {
        return false;
      }
      return true;
    }
    
    private void a(double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int[] ☃, int[] ☃, boolean ☃, boolean ☃)
    {
      blm.b ☃ = new blm.b(this.b, ☃, ☃, ☃, ☃, ☃, ☃, this.H);
      ☃.e(0.99F);
      ☃.a(☃);
      ☃.b(☃);
      
      int ☃ = this.p.nextInt(☃.length);
      ☃.c(☃[☃]);
      if ((☃ != null) && (☃.length > 0)) {
        ☃.d(☃[this.p.nextInt(☃.length)]);
      }
      this.H.a(☃);
    }
    
    private void a(double ☃, int ☃, int[] ☃, int[] ☃, boolean ☃, boolean ☃)
    {
      double ☃ = this.f;
      double ☃ = this.g;
      double ☃ = this.h;
      for (int ☃ = -☃; ☃ <= ☃; ☃++) {
        for (int ☃ = -☃; ☃ <= ☃; ☃++) {
          for (int ☃ = -☃; ☃ <= ☃; ☃++)
          {
            double ☃ = ☃ + (this.p.nextDouble() - this.p.nextDouble()) * 0.5D;
            double ☃ = ☃ + (this.p.nextDouble() - this.p.nextDouble()) * 0.5D;
            double ☃ = ☃ + (this.p.nextDouble() - this.p.nextDouble()) * 0.5D;
            double ☃ = on.a(☃ * ☃ + ☃ * ☃ + ☃ * ☃) / ☃ + this.p.nextGaussian() * 0.05D;
            
            a(☃, ☃, ☃, ☃ / ☃, ☃ / ☃, ☃ / ☃, ☃, ☃, ☃, ☃);
            if ((☃ != -☃) && (☃ != ☃) && (☃ != -☃) && (☃ != ☃)) {
              ☃ += ☃ * 2 - 1;
            }
          }
        }
      }
    }
    
    private void a(double ☃, double[][] ☃, int[] ☃, int[] ☃, boolean ☃, boolean ☃, boolean ☃)
    {
      double ☃ = ☃[0][0];
      double ☃ = ☃[0][1];
      
      a(this.f, this.g, this.h, ☃ * ☃, ☃ * ☃, 0.0D, ☃, ☃, ☃, ☃);
      
      float ☃ = this.p.nextFloat() * 3.1415927F;
      double ☃ = ☃ ? 0.034D : 0.34D;
      for (int ☃ = 0; ☃ < 3; ☃++)
      {
        double ☃ = ☃ + ☃ * 3.1415927F * ☃;
        
        double ☃ = ☃;
        double ☃ = ☃;
        for (int ☃ = 1; ☃ < ☃.length; ☃++)
        {
          double ☃ = ☃[☃][0];
          double ☃ = ☃[☃][1];
          for (double ☃ = 0.25D; ☃ <= 1.0D; ☃ += 0.25D)
          {
            double ☃ = (☃ + (☃ - ☃) * ☃) * ☃;
            double ☃ = (☃ + (☃ - ☃) * ☃) * ☃;
            
            double ☃ = ☃ * Math.sin(☃);
            ☃ *= Math.cos(☃);
            for (double ☃ = -1.0D; ☃ <= 1.0D; ☃ += 2.0D) {
              a(this.f, this.g, this.h, ☃ * ☃, ☃, ☃ * ☃, ☃, ☃, ☃, ☃);
            }
          }
          ☃ = ☃;
          ☃ = ☃;
        }
      }
    }
    
    private void a(int[] ☃, int[] ☃, boolean ☃, boolean ☃)
    {
      double ☃ = this.p.nextGaussian() * 0.05D;
      double ☃ = this.p.nextGaussian() * 0.05D;
      for (int ☃ = 0; ☃ < 70; ☃++)
      {
        double ☃ = this.i * 0.5D + this.p.nextGaussian() * 0.15D + ☃;
        double ☃ = this.k * 0.5D + this.p.nextGaussian() * 0.15D + ☃;
        double ☃ = this.j * 0.5D + this.p.nextDouble() * 0.5D;
        
        a(this.f, this.g, this.h, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
      }
    }
    
    public int b()
    {
      return 0;
    }
  }
  
  public static class b
    extends bmd
  {
    private boolean a;
    private boolean G;
    private final bly H;
    private float I;
    private float J;
    private float K;
    private boolean L;
    
    public b(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, bly ☃)
    {
      super(☃, ☃, ☃, 160, 8, -0.004F);
      this.i = ☃;
      this.j = ☃;
      this.k = ☃;
      this.H = ☃;
      
      this.w *= 0.75F;
      
      this.v = (48 + this.p.nextInt(12));
    }
    
    public void a(boolean ☃)
    {
      this.a = ☃;
    }
    
    public void b(boolean ☃)
    {
      this.G = ☃;
    }
    
    public boolean c()
    {
      return true;
    }
    
    public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
    {
      if ((!this.G) || (this.u < this.v / 3) || ((this.u + this.v) / 3 % 2 == 0)) {
        super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
      }
    }
    
    public void a()
    {
      super.a();
      if ((this.a) && (this.u < this.v / 2) && ((this.u + this.v) % 2 == 0))
      {
        b ☃ = new b(this.b, this.f, this.g, this.h, 0.0D, 0.0D, 0.0D, this.H);
        ☃.e(0.99F);
        ☃.a(this.y, this.z, this.A);
        ☃.u = (☃.v / 2);
        if (this.L)
        {
          ☃.L = true;
          ☃.I = this.I;
          ☃.J = this.J;
          ☃.K = this.K;
        }
        ☃.G = this.G;
        this.H.a(☃);
      }
    }
  }
  
  public static class a
    extends blx
  {
    protected a(aht ☃, double ☃, double ☃, double ☃)
    {
      super(☃, ☃, ☃);
      
      this.v = 4;
    }
    
    public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
    {
      float ☃ = 0.25F;
      float ☃ = 0.5F;
      float ☃ = 0.125F;
      float ☃ = 0.375F;
      float ☃ = 7.1F * on.a((this.u + ☃ - 1.0F) * 0.25F * 3.1415927F);
      e(0.6F - (this.u + ☃ - 1.0F) * 0.25F * 0.5F);
      
      float ☃ = (float)(this.c + (this.f - this.c) * ☃ - D);
      float ☃ = (float)(this.d + (this.g - this.d) * ☃ - E);
      float ☃ = (float)(this.e + (this.h - this.e) * ☃ - F);
      
      int ☃ = a(☃);
      int ☃ = ☃ >> 16 & 0xFFFF;
      int ☃ = ☃ & 0xFFFF;
      
      ☃.b(☃ - ☃ * ☃ - ☃ * ☃, ☃ - ☃ * ☃, ☃ - ☃ * ☃ - ☃ * ☃).a(0.5D, 0.375D).a(this.y, this.z, this.A, this.B).a(☃, ☃).d();
      ☃.b(☃ - ☃ * ☃ + ☃ * ☃, ☃ + ☃ * ☃, ☃ - ☃ * ☃ + ☃ * ☃).a(0.5D, 0.125D).a(this.y, this.z, this.A, this.B).a(☃, ☃).d();
      ☃.b(☃ + ☃ * ☃ + ☃ * ☃, ☃ + ☃ * ☃, ☃ + ☃ * ☃ + ☃ * ☃).a(0.25D, 0.125D).a(this.y, this.z, this.A, this.B).a(☃, ☃).d();
      ☃.b(☃ + ☃ * ☃ - ☃ * ☃, ☃ - ☃ * ☃, ☃ + ☃ * ☃ - ☃ * ☃).a(0.25D, 0.375D).a(this.y, this.z, this.A, this.B).a(☃, ☃).d();
    }
  }
  
  public static class d
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      blm.b ☃ = new blm.b(☃, ☃, ☃, ☃, ☃, ☃, ☃, bcf.z().j);
      ☃.e(0.99F);
      return ☃;
    }
  }
}
