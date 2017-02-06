import com.google.common.collect.Sets;
import java.util.Set;

public class aeb
  extends acr
{
  private static final Set<ajt> e = Sets.newHashSet(new ajt[] { aju.cs, aju.q, aju.e, aju.E, aju.ah, aju.ag, aju.T, aju.D, aju.R, aju.o, aju.aI, aju.S, aju.p, aju.y, aju.x, aju.aD, aju.Y, aju.aV, aju.cB, aju.av, aju.aC, aju.A, aju.cM, aju.b, aju.U, aju.aG, aju.az });
  
  protected aeb(ado.a ☃)
  {
    super(1.0F, -2.8F, ☃, e);
  }
  
  public boolean a(arc ☃)
  {
    ajt ☃ = ☃.t();
    if (☃ == aju.Z) {
      return this.d.d() == 3;
    }
    if ((☃ == aju.ah) || (☃ == aju.ag)) {
      return this.d.d() >= 2;
    }
    if ((☃ == aju.bP) || (☃ == aju.bT)) {
      return this.d.d() >= 2;
    }
    if ((☃ == aju.R) || (☃ == aju.o)) {
      return this.d.d() >= 2;
    }
    if ((☃ == aju.S) || (☃ == aju.p)) {
      return this.d.d() >= 1;
    }
    if ((☃ == aju.y) || (☃ == aju.x)) {
      return this.d.d() >= 1;
    }
    if ((☃ == aju.aC) || (☃ == aju.aD)) {
      return this.d.d() >= 2;
    }
    axe ☃ = ☃.a();
    if (☃ == axe.e) {
      return true;
    }
    if (☃ == axe.f) {
      return true;
    }
    if (☃ == axe.g) {
      return true;
    }
    return false;
  }
  
  public float a(adq ☃, arc ☃)
  {
    axe ☃ = ☃.a();
    if ((☃ == axe.f) || (☃ == axe.g) || (☃ == axe.e)) {
      return this.a;
    }
    return super.a(☃, ☃);
  }
}
