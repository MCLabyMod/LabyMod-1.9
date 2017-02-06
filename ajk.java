import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ajk
  extends alh
{
  public static final aro a = amg.D;
  public static final arq b = arq.a("damage", 0, 2);
  protected static final bbh c = new bbh(0.0D, 0.0D, 0.125D, 1.0D, 1.0D, 0.875D);
  protected static final bbh d = new bbh(0.125D, 0.0D, 0.0D, 0.875D, 1.0D, 1.0D);
  protected static final Logger e = LogManager.getLogger();
  
  protected ajk()
  {
    super(axe.g);
    w(this.A.b().a(a, cq.c).a(b, Integer.valueOf(0)));
    d(0);
    a(acq.c);
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    cq ☃ = ☃.bi().e();
    try
    {
      return super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃).a(a, ☃).a(b, Integer.valueOf(☃ >> 2));
    }
    catch (IllegalArgumentException ☃)
    {
      if (!☃.E)
      {
        e.warn(String.format("Invalid damage property for anvil at %s. Found %d, must be in [0, 1, 2]", new Object[] { ☃, Integer.valueOf(☃ >> 2) }));
        if ((☃ instanceof zj)) {
          ((zj)☃).a(new fb("Invalid damage property. Please pick in [0, 1, 2]", new Object[0]));
        }
      }
    }
    return super.a(☃, ☃, ☃, ☃, ☃, ☃, 0, ☃).a(a, ☃).a(b, Integer.valueOf(0));
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (!☃.E) {
      ☃.a(new ajk.a(☃, ☃));
    }
    return true;
  }
  
  public int d(arc ☃)
  {
    return ((Integer)☃.c(b)).intValue();
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    cq ☃ = (cq)☃.c(a);
    if (☃.k() == cq.a.a) {
      return c;
    }
    return d;
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    ☃.add(new adq(☃));
    ☃.add(new adq(☃, 1, 1));
    ☃.add(new adq(☃, 1, 2));
  }
  
  protected void a(yc ☃)
  {
    ☃.a(true);
  }
  
  public void a_(aht ☃, cj ☃)
  {
    ☃.b(1031, ☃, 0);
  }
  
  public boolean a(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    return true;
  }
  
  public static class a
    implements qn
  {
    private final aht a;
    private final cj b;
    
    public a(aht ☃, cj ☃)
    {
      this.a = ☃;
      this.b = ☃;
    }
    
    public String h_()
    {
      return "anvil";
    }
    
    public boolean o_()
    {
      return false;
    }
    
    public eu i_()
    {
      return new fb(aju.cf.a() + ".name", new Object[0]);
    }
    
    public aau a(zi ☃, zj ☃)
    {
      return new aaw(☃, this.a, this.b, ☃);
    }
    
    public String k()
    {
      return "minecraft:anvil";
    }
  }
  
  public arc a(int ☃)
  {
    return u().a(a, cq.b(☃ & 0x3)).a(b, Integer.valueOf((☃ & 0xF) >> 2));
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((cq)☃.c(a)).b();
    ☃ |= ((Integer)☃.c(b)).intValue() << 2;
    
    return ☃;
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    if (☃.t() != this) {
      return ☃;
    }
    return ☃.a(a, ☃.a((cq)☃.c(a)));
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a, b });
  }
}
