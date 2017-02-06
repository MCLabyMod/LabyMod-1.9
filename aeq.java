import com.mojang.authlib.GameProfile;
import java.util.List;

public class aeq
  extends ado
{
  private static final String[] a = { "skeleton", "wither", "zombie", "char", "creeper", "dragon" };
  
  public aeq()
  {
    a(acq.c);
    e(0);
    a(true);
  }
  
  public qo a(adq ☃, zj ☃, aht ☃, cj ☃, qm ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (☃ == cq.a) {
      return qo.c;
    }
    arc ☃ = ☃.o(☃);
    ajt ☃ = ☃.t();
    boolean ☃ = ☃.a(☃, ☃);
    if (!☃)
    {
      if (!☃.o(☃).a().a()) {
        return qo.c;
      }
      ☃ = ☃.a(☃);
    }
    if ((!☃.a(☃, ☃, ☃)) || (!aju.ce.a(☃, ☃))) {
      return qo.c;
    }
    if (☃.E) {
      return qo.a;
    }
    ☃.a(☃, aju.ce.u().a(aok.a, ☃), 11);
    
    int ☃ = 0;
    if (☃ == cq.b) {
      ☃ = on.c(☃.v * 16.0F / 360.0F + 0.5D) & 0xF;
    }
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof aqo))
    {
      aqo ☃ = (aqo)☃;
      if (☃.i() == 3)
      {
        GameProfile ☃ = null;
        if (☃.n())
        {
          dn ☃ = ☃.o();
          if (☃.b("SkullOwner", 10)) {
            ☃ = dy.a(☃.o("SkullOwner"));
          } else if ((☃.b("SkullOwner", 8)) && (!☃.l("SkullOwner").isEmpty())) {
            ☃ = new GameProfile(null, ☃.l("SkullOwner"));
          }
        }
        ☃.a(☃);
      }
      else
      {
        ☃.a(☃.i());
      }
      ☃.b(☃);
      aju.ce.a(☃, ☃, ☃);
    }
    ☃.b -= 1;
    return qo.a;
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    for (int ☃ = 0; ☃ < a.length; ☃++) {
      ☃.add(new adq(☃, 1, ☃));
    }
  }
  
  public int a(int ☃)
  {
    return ☃;
  }
  
  public String f_(adq ☃)
  {
    int ☃ = ☃.i();
    if ((☃ < 0) || (☃ >= a.length)) {
      ☃ = 0;
    }
    return super.a() + "." + a[☃];
  }
  
  public String a(adq ☃)
  {
    if ((☃.i() == 3) && (☃.n()))
    {
      if (☃.o().b("SkullOwner", 8)) {
        return di.a("item.skull.player.name", new Object[] { ☃.o().l("SkullOwner") });
      }
      if (☃.o().b("SkullOwner", 10))
      {
        dn ☃ = ☃.o().o("SkullOwner");
        if (☃.b("Name", 8)) {
          return di.a("item.skull.player.name", new Object[] { ☃.l("Name") });
        }
      }
    }
    return super.a(☃);
  }
  
  public boolean a(dn ☃)
  {
    super.a(☃);
    if ((☃.b("SkullOwner", 8)) && (!☃.l("SkullOwner").isEmpty()))
    {
      GameProfile ☃ = new GameProfile(null, ☃.l("SkullOwner"));
      ☃ = aqo.b(☃);
      ☃.a("SkullOwner", dy.a(new dn(), ☃));
      return true;
    }
    return false;
  }
}
