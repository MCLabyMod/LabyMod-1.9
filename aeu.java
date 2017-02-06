import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;

public class aeu
  extends ado
{
  public aeu()
  {
    a(acq.f);
  }
  
  public String a(adq ☃)
  {
    String ☃ = ("" + di.a(new StringBuilder().append(a()).append(".name").toString())).trim();
    
    String ☃ = h(☃);
    if (☃ != null) {
      ☃ = ☃ + " " + di.a(new StringBuilder().append("entity.").append(☃).append(".name").toString());
    }
    return ☃;
  }
  
  public qo a(adq ☃, zj ☃, aht ☃, cj ☃, qm ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (☃.E) {
      return qo.a;
    }
    if (!☃.a(☃.a(☃), ☃, ☃)) {
      return qo.c;
    }
    arc ☃ = ☃.o(☃);
    if (☃.t() == aju.ac)
    {
      apv ☃ = ☃.r(☃);
      if ((☃ instanceof aqk))
      {
        ahk ☃ = ((aqk)☃).b();
        ☃.a(h(☃));
        ☃.v_();
        ☃.a(☃, ☃, ☃, 3);
        if (!☃.bJ.d) {
          ☃.b -= 1;
        }
        return qo.a;
      }
    }
    ☃ = ☃.a(☃);
    
    double ☃ = 0.0D;
    if ((☃ == cq.b) && ((☃ instanceof alj))) {
      ☃ = 0.5D;
    }
    rr ☃ = a(☃, h(☃), ☃.p() + 0.5D, ☃.q() + ☃, ☃.r() + 0.5D);
    if (☃ != null)
    {
      if (((☃ instanceof sa)) && (☃.s())) {
        ☃.c(☃.q());
      }
      a(☃, ☃, ☃, ☃);
      if (!☃.bJ.d) {
        ☃.b -= 1;
      }
    }
    return qo.a;
  }
  
  public static void a(aht ☃, zj ☃, adq ☃, rr ☃)
  {
    MinecraftServer ☃ = ☃.u();
    if ((☃ == null) || (☃ == null)) {
      return;
    }
    dn ☃ = ☃.o();
    if ((☃ != null) && (☃.b("EntityTag", 10)))
    {
      if ((!☃.E) && (☃.br()) && ((☃ == null) || (!☃.al().h(☃.cK())))) {
        return;
      }
      dn ☃ = new dn();
      ☃.e(☃);
      UUID ☃ = ☃.bc();
      ☃.a(☃.o("EntityTag"));
      ☃.a(☃);
      ☃.f(☃);
    }
  }
  
  public qp<adq> a(adq ☃, aht ☃, zj ☃, qm ☃)
  {
    if (☃.E) {
      return new qp(qo.b, ☃);
    }
    bbi ☃ = a(☃, ☃, true);
    if ((☃ == null) || (☃.a != bbi.a.b)) {
      return new qp(qo.b, ☃);
    }
    cj ☃ = ☃.a();
    if (!(☃.o(☃).t() instanceof amo)) {
      return new qp(qo.b, ☃);
    }
    if ((!☃.a(☃, ☃)) || (!☃.a(☃, ☃.b, ☃))) {
      return new qp(qo.c, ☃);
    }
    rr ☃ = a(☃, h(☃), ☃.p() + 0.5D, ☃.q() + 0.5D, ☃.r() + 0.5D);
    if (☃ == null) {
      return new qp(qo.b, ☃);
    }
    if (((☃ instanceof sa)) && (☃.s())) {
      ☃.c(☃.q());
    }
    a(☃, ☃, ☃, ☃);
    if (!☃.bJ.d) {
      ☃.b -= 1;
    }
    ☃.b(nt.b(this));
    return new qp(qo.a, ☃);
  }
  
  public static rr a(aht ☃, String ☃, double ☃, double ☃, double ☃)
  {
    if ((☃ == null) || (!rt.a.containsKey(☃))) {
      return null;
    }
    rr ☃ = null;
    for (int ☃ = 0; ☃ < 1; ☃++)
    {
      ☃ = rt.b(☃, ☃);
      if ((☃ instanceof sa))
      {
        sb ☃ = (sb)☃;
        ☃.b(☃, ☃, ☃, on.g(☃.r.nextFloat() * 360.0F), 0.0F);
        ☃.aO = ☃.v;
        ☃.aM = ☃.v;
        
        ☃.a(☃.D(new cj(☃)), null);
        ☃.a(☃);
        ☃.D();
      }
    }
    return ☃;
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    for (rt.a ☃ : rt.a.values())
    {
      adq ☃ = new adq(☃, 1);
      a(☃, ☃.a);
      ☃.add(☃);
    }
  }
  
  public static void a(adq ☃, String ☃)
  {
    dn ☃ = ☃.n() ? ☃.o() : new dn();
    dn ☃ = new dn();
    ☃.a("id", ☃);
    ☃.a("EntityTag", ☃);
    ☃.d(☃);
  }
  
  public static String h(adq ☃)
  {
    dn ☃ = ☃.o();
    if (☃ == null) {
      return null;
    }
    if (!☃.b("EntityTag", 10)) {
      return null;
    }
    dn ☃ = ☃.o("EntityTag");
    if (!☃.b("id", 8)) {
      return null;
    }
    return ☃.l("id");
  }
}
