import java.util.List;

public class acz
  extends ado
{
  public boolean i_(adq ☃)
  {
    return true;
  }
  
  public boolean g_(adq ☃)
  {
    return false;
  }
  
  public aee g(adq ☃)
  {
    if (!h(☃).c_()) {
      return aee.b;
    }
    return super.g(☃);
  }
  
  public du h(adq ☃)
  {
    dn ☃ = ☃.o();
    if ((☃ == null) || (!☃.b("StoredEnchantments", 9))) {
      return new du();
    }
    return (du)☃.c("StoredEnchantments");
  }
  
  public void a(adq ☃, zj ☃, List<String> ☃, boolean ☃)
  {
    super.a(☃, ☃, ☃, ☃);
    
    du ☃ = h(☃);
    if (☃ != null) {
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        int ☃ = ☃.b(☃).g("id");
        int ☃ = ☃.b(☃).g("lvl");
        if (agm.c(☃) != null) {
          ☃.add(agm.c(☃).d(☃));
        }
      }
    }
  }
  
  public void a(adq ☃, agp ☃)
  {
    du ☃ = h(☃);
    boolean ☃ = true;
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      dn ☃ = ☃.b(☃);
      if (agm.c(☃.g("id")) == ☃.b)
      {
        if (☃.g("lvl") < ☃.c) {
          ☃.a("lvl", (short)☃.c);
        }
        ☃ = false;
        break;
      }
    }
    if (☃)
    {
      dn ☃ = new dn();
      
      ☃.a("id", (short)agm.b(☃.b));
      ☃.a("lvl", (short)☃.c);
      
      ☃.a(☃);
    }
    if (!☃.n()) {
      ☃.d(new dn());
    }
    ☃.o().a("StoredEnchantments", ☃);
  }
  
  public adq a(agp ☃)
  {
    adq ☃ = new adq(this);
    a(☃, ☃);
    return ☃;
  }
  
  public void a(agm ☃, List<adq> ☃)
  {
    for (int ☃ = ☃.d(); ☃ <= ☃.b(); ☃++) {
      ☃.add(a(new agp(☃, ☃)));
    }
  }
}
