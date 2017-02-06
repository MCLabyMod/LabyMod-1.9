import com.google.common.collect.Multimap;

public class adn
  extends ado
{
  private final float b;
  protected ado.a a;
  
  public adn(ado.a ☃)
  {
    this.a = ☃;
    this.j = 1;
    e(☃.a());
    a(acq.i);
    this.b = (☃.c() + 1.0F);
  }
  
  public qo a(adq ☃, zj ☃, aht ☃, cj ☃, qm ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (!☃.a(☃.a(☃), ☃, ☃)) {
      return qo.c;
    }
    arc ☃ = ☃.o(☃);
    ajt ☃ = ☃.t();
    if ((☃ != cq.a) && (☃.o(☃.a()).a() == axe.a))
    {
      if ((☃ == aju.c) || (☃ == aju.da))
      {
        a(☃, ☃, ☃, ☃, aju.ak.u());
        return qo.a;
      }
      if (☃ == aju.d) {
        switch (adn.1.a[((akt.a)☃.c(akt.a)).ordinal()])
        {
        case 1: 
          a(☃, ☃, ☃, ☃, aju.ak.u());
          return qo.a;
        case 2: 
          a(☃, ☃, ☃, ☃, aju.d.u().a(akt.a, akt.a.a));
          return qo.a;
        }
      }
    }
    return qo.b;
  }
  
  public boolean a(adq ☃, sa ☃, sa ☃)
  {
    ☃.a(1, ☃);
    return true;
  }
  
  protected void a(adq ☃, zj ☃, aht ☃, cj ☃, arc ☃)
  {
    ☃.a(☃, ☃, ng.cm, nh.e, 1.0F, 1.0F);
    if (!☃.E)
    {
      ☃.a(☃, ☃, 11);
      ☃.a(1, ☃);
    }
  }
  
  public boolean A_()
  {
    return true;
  }
  
  public String g()
  {
    return this.a.toString();
  }
  
  public Multimap<String, sn> a(rw ☃)
  {
    Multimap<String, sn> ☃ = super.a(☃);
    if (☃ == rw.a)
    {
      ☃.put(yt.e.a(), new sn(g, "Weapon modifier", 0.0D, 0));
      ☃.put(yt.f.a(), new sn(h, "Weapon modifier", this.b - 4.0F, 0));
    }
    return ☃;
  }
}
