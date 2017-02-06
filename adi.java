import java.util.Random;

public class adi
  extends ado
{
  public adi()
  {
    e(64);
    d(1);
    a(acq.i);
    
    a(new kk("cast"), new adr()
    {
      public float a(adq ☃, aht ☃, sa ☃)
      {
        if (☃ == null) {
          return 0.0F;
        }
        return (☃.cb() == ☃) && ((☃ instanceof zj)) && (((zj)☃).bP != null) ? 1.0F : 0.0F;
      }
    });
  }
  
  public boolean A_()
  {
    return true;
  }
  
  public boolean C_()
  {
    return true;
  }
  
  public qp<adq> a(adq ☃, aht ☃, zj ☃, qm ☃)
  {
    if (☃.bP != null)
    {
      int ☃ = ☃.bP.j();
      ☃.a(☃, ☃);
      ☃.a(☃);
    }
    else
    {
      ☃.a(null, ☃.p, ☃.q, ☃.r, ng.H, nh.g, 0.5F, 0.4F / (i.nextFloat() * 0.4F + 0.8F));
      if (!☃.E) {
        ☃.a(new xw(☃, ☃));
      }
      ☃.a(☃);
      ☃.b(nt.b(this));
    }
    return new qp(qo.a, ☃);
  }
  
  public boolean g_(adq ☃)
  {
    return super.g_(☃);
  }
  
  public int c()
  {
    return 1;
  }
}
