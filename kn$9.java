import com.mojang.authlib.GameProfile;

final class kn$9
  extends cn
{
  private boolean b = true;
  
  protected adq b(ck ☃, adq ☃)
  {
    aht ☃ = ☃.i();
    cq ☃ = aku.e(☃.f());
    cj ☃ = ☃.d().a(☃);
    aok ☃ = aju.ce;
    if ((☃.d(☃)) && (☃.b(☃, ☃, ☃)))
    {
      if (!☃.E)
      {
        ☃.a(☃, ☃.u().a(aok.a, cq.b), 3);
        apv ☃ = ☃.r(☃);
        if ((☃ instanceof aqo))
        {
          if (☃.i() == 3)
          {
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
                if (!os.b(☃)) {
                  ☃ = new GameProfile(null, ☃);
                }
              }
            }
            ((aqo)☃).a(☃);
          }
          else
          {
            ((aqo)☃).a(☃.i());
          }
          ((aqo)☃).b(☃.d().b() * 4);
          aju.ce.a(☃, ☃, (aqo)☃);
        }
        ☃.b -= 1;
      }
    }
    else if (abw.a(☃, ☃) == null) {
      this.b = false;
    }
    return ☃;
  }
  
  protected void a(ck ☃)
  {
    if (this.b) {
      ☃.i().b(1000, ☃.d(), 0);
    } else {
      ☃.i().b(1001, ☃.d(), 0);
    }
  }
}
