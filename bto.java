import com.mojang.authlib.GameProfile;

public class bto
  implements bty<sa>
{
  private final bkm a;
  
  public bto(bkm ☃)
  {
    this.a = ☃;
  }
  
  public void a(sa ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    adq ☃ = ☃.a(rw.f);
    if ((☃ == null) || (☃.b() == null)) {
      return;
    }
    ado ☃ = ☃.b();
    bcf ☃ = bcf.z();
    
    bni.G();
    if (☃.aK()) {
      bni.c(0.0F, 0.2F, 0.0F);
    }
    boolean ☃ = ((☃ instanceof ze)) || (((☃ instanceof za)) && (((za)☃).dd()));
    if ((☃.m_()) && (!(☃ instanceof ze)))
    {
      float ☃ = 2.0F;
      float ☃ = 1.4F;
      bni.c(0.0F, 0.5F * ☃, 0.0F);
      bni.b(☃ / ☃, ☃ / ☃, ☃ / ☃);
      bni.c(0.0F, 16.0F * ☃, 0.0F);
    }
    this.a.c(0.0625F);
    
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    if (☃ == ads.ch)
    {
      float ☃ = 1.1875F;
      bni.b(☃, -☃, -☃);
      if (☃) {
        bni.c(0.0F, 0.0625F, 0.0F);
      }
      GameProfile ☃ = null;
      if (☃.n())
      {
        dn ☃ = ☃.o();
        if (☃.b("SkullOwner", 10))
        {
          ☃ = dy.a(☃.o("SkullOwner"));
        }
        else if (☃.b("SkullOwner", 8))
        {
          String ☃ = ☃.l("SkullOwner");
          if (!os.b(☃))
          {
            ☃ = aqo.b(new GameProfile(null, ☃));
            ☃.a("SkullOwner", dy.a(new dn(), ☃));
          }
        }
      }
      bpu.a.a(-0.5F, 0.0F, -0.5F, cq.b, 180.0F, ☃.i(), ☃, -1, ☃);
    }
    else if ((!(☃ instanceof abw)) || (((abw)☃).B_() != rw.f))
    {
      float ☃ = 0.625F;
      bni.c(0.0F, -0.25F, 0.0F);
      bni.b(180.0F, 0.0F, 1.0F, 0.0F);
      bni.b(☃, -☃, -☃);
      if (☃) {
        bni.c(0.0F, 0.1875F, 0.0F);
      }
      ☃.ae().a(☃, ☃, bos.b.f);
    }
    bni.H();
  }
  
  public boolean a()
  {
    return false;
  }
}
