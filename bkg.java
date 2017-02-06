public class bkg
  extends bjc
{
  private bkm a;
  private bkm b;
  
  public bkg(float ☃)
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
    
    this.b = new bkm(this, "jaw");
    this.b.a(0.0F, 4.0F, 8.0F + ☃);
    this.b.a("jaw", -6.0F, 0.0F, -16.0F, 12, 4, 16);
    this.a.a(this.b);
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    this.b.f = ((float)(Math.sin(☃ * 3.1415927F * 0.2F) + 1.0D) * 0.2F);
    
    this.a.g = (☃ * 0.017453292F);
    this.a.f = (☃ * 0.017453292F);
    
    bni.c(0.0F, -0.374375F, 0.0F);
    bni.b(0.75F, 0.75F, 0.75F);
    
    this.a.a(☃);
  }
}
