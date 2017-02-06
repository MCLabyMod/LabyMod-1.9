import com.mojang.authlib.GameProfile;

public class bne
{
  public static bne a = new bne();
  private apx b = new apx(ake.a.a);
  private apx c = new apx(ake.a.b);
  private aqe d = new aqe();
  private apt e = new apt();
  private aqo f = new aqo();
  private bjl g = new bjl();
  
  public void a(adq ☃)
  {
    if (☃.b() == ads.cO)
    {
      this.e.a(☃);
      bpm.a.a(this.e, 0.0D, 0.0D, 0.0D, 0.0F);
    }
    else if (☃.b() == ads.cQ)
    {
      if (☃.a("BlockEntityTag", false) != null)
      {
        this.e.a(☃);
        bcf.z().N().a(bny.b.a(this.e.g(), this.e.c(), this.e.e()));
      }
      else
      {
        bcf.z().N().a(bny.c);
      }
      bni.G();
      bni.b(1.0F, -1.0F, -1.0F);
      this.g.a();
      bni.H();
    }
    else if (☃.b() == ads.ch)
    {
      GameProfile ☃ = null;
      if (☃.n())
      {
        dn ☃ = ☃.o();
        if (☃.b("SkullOwner", 10))
        {
          ☃ = dy.a(☃.o("SkullOwner"));
        }
        else if ((☃.b("SkullOwner", 8)) && (!☃.l("SkullOwner").isEmpty()))
        {
          ☃ = new GameProfile(null, ☃.l("SkullOwner"));
          ☃ = aqo.b(☃);
          ☃.q("SkullOwner");
          ☃.a("SkullOwner", dy.a(new dn(), ☃));
        }
      }
      if (bpu.a != null)
      {
        bni.G();
        bni.r();
        bpu.a.a(0.0F, 0.0F, 0.0F, cq.b, 0.0F, ☃.i(), ☃, -1, 0.0F);
        bni.q();
        bni.H();
      }
    }
    else
    {
      ajt ☃ = ajt.a(☃.b());
      if (☃ == aju.bQ) {
        bpm.a.a(this.d, 0.0D, 0.0D, 0.0D, 0.0F);
      } else if (☃ == aju.cg) {
        bpm.a.a(this.c, 0.0D, 0.0D, 0.0D, 0.0F);
      } else {
        bpm.a.a(this.b, 0.0D, 0.0D, 0.0D, 0.0F);
      }
    }
  }
}
