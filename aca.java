import java.util.List;

public class aca
  extends acc
{
  public aca()
  {
    super(aju.cK);
    this.j = 16;
    a(acq.c);
    a(true);
    e(0);
  }
  
  public qo a(adq ☃, zj ☃, aht ☃, cj ☃, qm ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    arc ☃ = ☃.o(☃);
    boolean ☃ = ☃.t().a(☃, ☃);
    if ((☃ == cq.a) || ((!☃.a().a()) && (!☃)) || ((☃) && (☃ != cq.b))) {
      return qo.c;
    }
    ☃ = ☃.a(☃);
    if ((!☃.a(☃, ☃, ☃)) || (!aju.cK.a(☃, ☃))) {
      return qo.c;
    }
    if (☃.E) {
      return qo.a;
    }
    ☃ = ☃ ? ☃.b() : ☃;
    if (☃ == cq.b)
    {
      int ☃ = on.c((☃.v + 180.0F) * 16.0F / 360.0F + 0.5D) & 0xF;
      ☃.a(☃, aju.cK.u().a(aou.b, Integer.valueOf(☃)), 3);
    }
    else
    {
      ☃.a(☃, aju.cL.u().a(apl.b, ☃), 3);
    }
    ☃.b -= 1;
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof apt)) {
      ((apt)☃).a(☃);
    }
    return qo.a;
  }
  
  public String a(adq ☃)
  {
    String ☃ = "item.banner.";
    
    act ☃ = b(☃);
    ☃ = ☃ + ☃.d() + ".name";
    return di.a(☃);
  }
  
  public static void a(adq ☃, List<String> ☃)
  {
    dn ☃ = ☃.a("BlockEntityTag", false);
    if ((☃ == null) || (!☃.e("Patterns"))) {
      return;
    }
    du ☃ = ☃.c("Patterns", 10);
    for (int ☃ = 0; (☃ < ☃.c()) && (☃ < 6); ☃++)
    {
      dn ☃ = ☃.b(☃);
      act ☃ = act.a(☃.h("Color"));
      apt.a ☃ = apt.a.a(☃.l("Pattern"));
      if (☃ != null) {
        ☃.add(di.a("item.banner." + ☃.a() + "." + ☃.d()));
      }
    }
  }
  
  public void a(adq ☃, zj ☃, List<String> ☃, boolean ☃)
  {
    a(☃, ☃);
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    for (act ☃ : )
    {
      dn ☃ = new dn();
      apt.a(☃, ☃.b(), null);
      
      dn ☃ = new dn();
      ☃.a("BlockEntityTag", ☃);
      
      adq ☃ = new adq(☃, 1, ☃.b());
      ☃.d(☃);
      
      ☃.add(☃);
    }
  }
  
  public acq b()
  {
    return acq.c;
  }
  
  public static act b(adq ☃)
  {
    dn ☃ = ☃.a("BlockEntityTag", false);
    act ☃ = null;
    if ((☃ != null) && (☃.e("Base"))) {
      ☃ = act.a(☃.h("Base"));
    } else {
      ☃ = act.a(☃.i());
    }
    return ☃;
  }
}
