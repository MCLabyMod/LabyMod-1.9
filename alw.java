import java.util.Random;

public class alw
  extends ajt
{
  protected static final bbh a = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
  
  protected alw()
  {
    super(axe.c);
    d(255);
  }
  
  public boolean a(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    switch (alw.1.a[☃.ordinal()])
    {
    case 1: 
      return true;
    case 2: 
    case 3: 
    case 4: 
    case 5: 
      arc ☃ = ☃.o(☃.a(☃));
      ajt ☃ = ☃.t();
      return (!☃.p()) && (☃ != aju.ak) && (☃ != aju.da);
    }
    return super.a(☃, ☃, ☃, ☃);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return a;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return aju.d.a(aju.d.u().a(akt.a, akt.a.a), ☃, ☃);
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(this);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    super.a(☃, ☃, ☃, ☃);
    if (☃.o(☃.a()).a().a()) {
      ☃.a(☃, aju.d.u());
    }
  }
}
