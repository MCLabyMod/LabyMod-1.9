public class bkh
  extends bjc
{
  private bkm a;
  private bkm b;
  private bkm c;
  private bkm d;
  private bkm e;
  private bkm f;
  private bkm g;
  private bkm h;
  private bkm i;
  private bkm j;
  private bkm k;
  private bkm l;
  private float m;
  
  public bkh(float ☃)
  {
    this.s = 256;
    this.t = 256;
    
    a("body.body", 0, 0);
    a("wing.skin", -56, 88);
    a("wingtip.skin", -56, 144);
    a("rearleg.main", 0, 0);
    a("rearfoot.main", 112, 0);
    a("rearlegtip.main", 196, 0);
    a("head.upperhead", 112, 30);
    a("wing.bone", 112, 88);
    a("head.upperlip", 176, 44);
    a("jaw.jaw", 176, 65);
    a("frontleg.main", 112, 104);
    a("wingtip.bone", 112, 136);
    a("frontfoot.main", 144, 104);
    a("neck.box", 192, 104);
    a("frontlegtip.main", 226, 138);
    a("body.scale", 220, 53);
    a("head.scale", 0, 0);
    a("neck.scale", 48, 0);
    a("head.nostril", 112, 0);
    
    float ☃ = -16.0F;
    this.a = new bkm(this, "head");
    this.a.a("upperlip", -6.0F, -1.0F, -8.0F + ☃, 12, 5, 16);
    this.a.a("upperhead", -8.0F, -8.0F, 6.0F + ☃, 16, 16, 16);
    this.a.i = true;
    this.a.a("scale", -5.0F, -12.0F, 12.0F + ☃, 2, 4, 6);
    this.a.a("nostril", -5.0F, -3.0F, -6.0F + ☃, 2, 2, 4);
    this.a.i = false;
    this.a.a("scale", 3.0F, -12.0F, 12.0F + ☃, 2, 4, 6);
    this.a.a("nostril", 3.0F, -3.0F, -6.0F + ☃, 2, 2, 4);
    
    this.c = new bkm(this, "jaw");
    this.c.a(0.0F, 4.0F, 8.0F + ☃);
    this.c.a("jaw", -6.0F, 0.0F, -16.0F, 12, 4, 16);
    this.a.a(this.c);
    
    this.b = new bkm(this, "neck");
    this.b.a("box", -5.0F, -5.0F, -5.0F, 10, 10, 10);
    this.b.a("scale", -1.0F, -9.0F, -3.0F, 2, 4, 6);
    
    this.d = new bkm(this, "body");
    this.d.a(0.0F, 4.0F, 8.0F);
    this.d.a("body", -12.0F, 0.0F, -16.0F, 24, 24, 64);
    this.d.a("scale", -1.0F, -6.0F, -10.0F, 2, 6, 12);
    this.d.a("scale", -1.0F, -6.0F, 10.0F, 2, 6, 12);
    this.d.a("scale", -1.0F, -6.0F, 30.0F, 2, 6, 12);
    
    this.k = new bkm(this, "wing");
    this.k.a(-12.0F, 5.0F, 2.0F);
    this.k.a("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8);
    this.k.a("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
    this.l = new bkm(this, "wingtip");
    this.l.a(-56.0F, 0.0F, 0.0F);
    this.l.a("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4);
    this.l.a("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
    this.k.a(this.l);
    
    this.f = new bkm(this, "frontleg");
    this.f.a(-12.0F, 20.0F, 2.0F);
    this.f.a("main", -4.0F, -4.0F, -4.0F, 8, 24, 8);
    this.h = new bkm(this, "frontlegtip");
    this.h.a(0.0F, 20.0F, -1.0F);
    this.h.a("main", -3.0F, -1.0F, -3.0F, 6, 24, 6);
    this.f.a(this.h);
    this.j = new bkm(this, "frontfoot");
    this.j.a(0.0F, 23.0F, 0.0F);
    this.j.a("main", -4.0F, 0.0F, -12.0F, 8, 4, 16);
    this.h.a(this.j);
    
    this.e = new bkm(this, "rearleg");
    this.e.a(-16.0F, 16.0F, 42.0F);
    this.e.a("main", -8.0F, -4.0F, -8.0F, 16, 32, 16);
    this.g = new bkm(this, "rearlegtip");
    this.g.a(0.0F, 32.0F, -4.0F);
    this.g.a("main", -6.0F, -2.0F, 0.0F, 12, 32, 12);
    this.e.a(this.g);
    this.i = new bkm(this, "rearfoot");
    this.i.a(0.0F, 31.0F, 4.0F);
    this.i.a("main", -9.0F, 0.0F, -20.0F, 18, 6, 24);
    this.g.a(this.i);
  }
  
  public void a(sa ☃, float ☃, float ☃, float ☃)
  {
    this.m = ☃;
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    bni.G();
    wu ☃ = (wu)☃;
    float ☃ = ☃.bC + (☃.bD - ☃.bC) * this.m;
    this.c.f = ((float)(Math.sin(☃ * 6.2831855F) + 1.0D) * 0.2F);
    
    float ☃ = (float)(Math.sin(☃ * 6.2831855F - 1.0F) + 1.0D);
    ☃ = (☃ * ☃ + ☃ * 2.0F) * 0.05F;
    
    bni.c(0.0F, ☃ - 2.0F, -3.0F);
    bni.b(☃ * 2.0F, 1.0F, 0.0F, 0.0F);
    
    float ☃ = -30.0F;
    
    float ☃ = 0.0F;
    
    float ☃ = 1.5F;
    
    double[] ☃ = ☃.a(6, this.m);
    
    float ☃ = a(☃.a(5, this.m)[0] - ☃.a(10, this.m)[0]);
    float ☃ = a(☃.a(5, this.m)[0] + ☃ / 2.0F);
    
    ☃ += 2.0F;
    
    float ☃ = ☃ * 6.2831855F;
    ☃ = 20.0F;
    float ☃ = -12.0F;
    for (int ☃ = 0; ☃ < 5; ☃++)
    {
      double[] ☃ = ☃.a(5 - ☃, this.m);
      float ☃ = (float)Math.cos(☃ * 0.45F + ☃) * 0.15F;
      this.b.g = (a(☃[0] - ☃[0]) * 0.017453292F * ☃);
      this.b.f = (☃ + ☃.a(☃, ☃, ☃) * 0.017453292F * ☃ * 5.0F);
      this.b.h = (-a(☃[0] - ☃) * 0.017453292F * ☃);
      
      this.b.d = ☃;
      this.b.e = ☃;
      this.b.c = ☃;
      ☃ = (float)(☃ + Math.sin(this.b.f) * 10.0D);
      ☃ = (float)(☃ - Math.cos(this.b.g) * Math.cos(this.b.f) * 10.0D);
      ☃ = (float)(☃ - Math.sin(this.b.g) * Math.cos(this.b.f) * 10.0D);
      this.b.a(☃);
    }
    this.a.d = ☃;
    this.a.e = ☃;
    this.a.c = ☃;
    double[] ☃ = ☃.a(0, this.m);
    this.a.g = (a(☃[0] - ☃[0]) * 0.017453292F);
    this.a.f = (a(☃.a(6, ☃, ☃)) * 0.017453292F * ☃ * 5.0F);
    this.a.h = (-a(☃[0] - ☃) * 0.017453292F);
    this.a.a(☃);
    bni.G();
    bni.c(0.0F, 1.0F, 0.0F);
    bni.b(-☃ * ☃, 0.0F, 0.0F, 1.0F);
    bni.c(0.0F, -1.0F, 0.0F);
    this.d.h = 0.0F;
    this.d.a(☃);
    for (int ☃ = 0; ☃ < 2; ☃++)
    {
      bni.q();
      float ☃ = ☃ * 6.2831855F;
      this.k.f = (0.125F - (float)Math.cos(☃) * 0.2F);
      this.k.g = 0.25F;
      this.k.h = ((float)(Math.sin(☃) + 0.125D) * 0.8F);
      this.l.h = (-(float)(Math.sin(☃ + 2.0F) + 0.5D) * 0.75F);
      
      this.e.f = (1.0F + ☃ * 0.1F);
      this.g.f = (0.5F + ☃ * 0.1F);
      this.i.f = (0.75F + ☃ * 0.1F);
      
      this.f.f = (1.3F + ☃ * 0.1F);
      this.h.f = (-0.5F - ☃ * 0.1F);
      this.j.f = (0.75F + ☃ * 0.1F);
      this.k.a(☃);
      this.f.a(☃);
      this.e.a(☃);
      bni.b(-1.0F, 1.0F, 1.0F);
      if (☃ == 0) {
        bni.a(bni.i.a);
      }
    }
    bni.H();
    bni.a(bni.i.b);
    bni.r();
    
    float ☃ = -(float)Math.sin(☃ * 6.2831855F) * 0.0F;
    ☃ = ☃ * 6.2831855F;
    ☃ = 10.0F;
    ☃ = 60.0F;
    ☃ = 0.0F;
    ☃ = ☃.a(11, this.m);
    for (int ☃ = 0; ☃ < 12; ☃++)
    {
      ☃ = ☃.a(12 + ☃, this.m);
      ☃ = (float)(☃ + Math.sin(☃ * 0.45F + ☃) * 0.05000000074505806D);
      this.b.g = ((a(☃[0] - ☃[0]) * ☃ + 180.0F) * 0.017453292F);
      this.b.f = (☃ + (float)(☃[1] - ☃[1]) * 0.017453292F * ☃ * 5.0F);
      this.b.h = (a(☃[0] - ☃) * 0.017453292F * ☃);
      this.b.d = ☃;
      this.b.e = ☃;
      this.b.c = ☃;
      ☃ = (float)(☃ + Math.sin(this.b.f) * 10.0D);
      ☃ = (float)(☃ - Math.cos(this.b.g) * Math.cos(this.b.f) * 10.0D);
      ☃ = (float)(☃ - Math.sin(this.b.g) * Math.cos(this.b.f) * 10.0D);
      this.b.a(☃);
    }
    bni.H();
  }
  
  private float a(double ☃)
  {
    while (☃ >= 180.0D) {
      ☃ -= 360.0D;
    }
    while (☃ < -180.0D) {
      ☃ += 360.0D;
    }
    return (float)☃;
  }
}
