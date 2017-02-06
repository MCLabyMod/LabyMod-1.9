import java.util.List;
import java.util.Random;

public class adv
  extends aed
{
  public String a(adq ☃)
  {
    return di.a(afg.c(☃).b("lingering_potion.effect."));
  }
  
  public void a(adq ☃, zj ☃, List<String> ☃, boolean ☃)
  {
    afg.a(☃, ☃, 0.25F);
  }
  
  public qp<adq> a(adq ☃, aht ☃, zj ☃, qm ☃)
  {
    if (!☃.bJ.d) {
      ☃.b -= 1;
    }
    ☃.a(null, ☃.p, ☃.q, ☃.r, ng.di, nh.g, 0.5F, 0.4F / (i.nextFloat() * 0.4F + 0.8F));
    if (!☃.E)
    {
      aac ☃ = new aac(☃, ☃, ☃);
      ☃.a(☃, ☃.w, ☃.v, -20.0F, 0.5F, 1.0F);
      ☃.a(☃);
    }
    ☃.b(nt.b(this));
    return new qp(qo.a, ☃);
  }
}
