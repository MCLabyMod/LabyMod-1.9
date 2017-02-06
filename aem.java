import java.util.List;

public class aem
  extends ado
{
  public aem()
  {
    this.j = 1;
    a(acq.j);
    e(336);
    
    a(new kk("blocking"), new adr()
    {
      public float a(adq ☃, aht ☃, sa ☃)
      {
        return (☃ != null) && (☃.cs()) && (☃.cv() == ☃) ? 1.0F : 0.0F;
      }
    });
  }
  
  public qo a(adq ☃, zj ☃, aht ☃, cj ☃, qm ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    return super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public String a(adq ☃)
  {
    if (☃.a("BlockEntityTag", false) != null)
    {
      String ☃ = "item.shield.";
      
      act ☃ = aca.b(☃);
      ☃ = ☃ + ☃.d() + ".name";
      return di.a(☃);
    }
    return di.a("item.shield.name");
  }
  
  public void a(adq ☃, zj ☃, List<String> ☃, boolean ☃)
  {
    aca.a(☃, ☃);
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    adq ☃ = new adq(☃, 1, 0);
    ☃.add(☃);
  }
  
  public acq b()
  {
    return acq.j;
  }
  
  public afa f(adq ☃)
  {
    return afa.d;
  }
  
  public int e(adq ☃)
  {
    return 72000;
  }
  
  public qp<adq> a(adq ☃, aht ☃, zj ☃, qm ☃)
  {
    ☃.c(☃);
    return new qp(qo.a, ☃);
  }
  
  public boolean a(adq ☃, adq ☃)
  {
    if (☃.b() == ado.a(aju.f)) {
      return true;
    }
    return super.a(☃, ☃);
  }
}
