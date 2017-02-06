import java.util.List;
import net.minecraft.server.MinecraftServer;

public class acc
  extends ado
{
  protected final ajt a;
  
  public acc(ajt ☃)
  {
    this.a = ☃;
  }
  
  public acc b(String ☃)
  {
    super.c(☃);
    return this;
  }
  
  public qo a(adq ☃, zj ☃, aht ☃, cj ☃, qm ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    arc ☃ = ☃.o(☃);
    ajt ☃ = ☃.t();
    if (!☃.a(☃, ☃)) {
      ☃ = ☃.a(☃);
    }
    if ((☃.b == 0) || (!☃.a(☃, ☃, ☃)) || (!☃.a(this.a, ☃, false, ☃, null, ☃))) {
      return qo.c;
    }
    int ☃ = a(☃.i());
    arc ☃ = this.a.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    if (☃.a(☃, ☃, 11))
    {
      ☃ = ☃.o(☃);
      if (☃.t() == this.a)
      {
        a(☃, ☃, ☃, ☃);
        this.a.a(☃, ☃, ☃, ☃, ☃);
      }
      aop ☃ = this.a.w();
      ☃.a(☃, ☃, ☃.e(), nh.e, (☃.a() + 1.0F) / 2.0F, ☃.b() * 0.8F);
      ☃.b -= 1;
    }
    return qo.a;
  }
  
  public static boolean a(aht ☃, zj ☃, cj ☃, adq ☃)
  {
    MinecraftServer ☃ = ☃.u();
    if (☃ == null) {
      return false;
    }
    if ((☃.n()) && (☃.o().b("BlockEntityTag", 10)))
    {
      apv ☃ = ☃.r(☃);
      if (☃ != null)
      {
        if ((!☃.E) && (☃.B()) && ((☃ == null) || (!☃.al().h(☃.cK())))) {
          return false;
        }
        dn ☃ = new dn();
        dn ☃ = (dn)☃.b();
        ☃.b(☃);
        
        dn ☃ = (dn)☃.o().c("BlockEntityTag");
        ☃.a(☃);
        ☃.a("x", ☃.p());
        ☃.a("y", ☃.q());
        ☃.a("z", ☃.r());
        if (!☃.equals(☃))
        {
          ☃.a(☃);
          ☃.v_();
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean a(aht ☃, cj ☃, cq ☃, zj ☃, adq ☃)
  {
    ajt ☃ = ☃.o(☃).t();
    if (☃ == aju.aH) {
      ☃ = cq.b;
    } else if (!☃.a(☃, ☃)) {
      ☃ = ☃.a(☃);
    }
    return ☃.a(this.a, ☃, false, ☃, null, ☃);
  }
  
  public String f_(adq ☃)
  {
    return this.a.a();
  }
  
  public String a()
  {
    return this.a.a();
  }
  
  public acq b()
  {
    return this.a.r();
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    this.a.a(☃, ☃, ☃);
  }
  
  public ajt d()
  {
    return this.a;
  }
}
