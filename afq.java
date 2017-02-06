import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;

public class afq
{
  private static final afq a = new afq();
  private Map<adq, adq> b = Maps.newHashMap();
  private Map<adq, Float> c = Maps.newHashMap();
  
  public static afq a()
  {
    return a;
  }
  
  private afq()
  {
    a(aju.p, new adq(ads.l), 0.7F);
    a(aju.o, new adq(ads.m), 1.0F);
    a(aju.ag, new adq(ads.k), 1.0F);
    a(aju.m, new adq(aju.w), 0.1F);
    a(ads.an, new adq(ads.ao), 0.35F);
    a(ads.bp, new adq(ads.bq), 0.35F);
    a(ads.br, new adq(ads.bs), 0.35F);
    a(ads.bv, new adq(ads.bw), 0.35F);
    a(ads.bt, new adq(ads.bu), 0.35F);
    a(aju.e, new adq(aju.b), 0.1F);
    a(new adq(aju.bf, 1, aoy.b), new adq(aju.bf, 1, aoy.d), 0.1F);
    a(ads.aP, new adq(ads.aO), 0.3F);
    a(aju.aL, new adq(aju.cz), 0.35F);
    a(aju.aK, new adq(ads.bd, 1, act.n.b()), 0.2F);
    a(aju.r, new adq(ads.j, 1, 1), 0.15F);
    a(aju.s, new adq(ads.j, 1, 1), 0.15F);
    a(aju.bP, new adq(ads.bY), 1.0F);
    a(ads.cc, new adq(ads.cd), 0.35F);
    a(aju.aV, new adq(ads.cp), 0.1F);
    a(new adq(aju.v, 1, 1), new adq(aju.v, 1, 0), 0.15F);
    a(ads.cS, new adq(ads.cT), 0.1F);
    for (adh.a ☃ : adh.a.values()) {
      if (☃.g()) {
        a(new adq(ads.bb, 1, ☃.a()), new adq(ads.bc, 1, ☃.a()), 0.35F);
      }
    }
    a(aju.q, new adq(ads.j), 0.1F);
    a(aju.aC, new adq(ads.aE), 0.7F);
    
    a(aju.x, new adq(ads.bd, 1, act.l.b()), 0.2F);
    a(aju.co, new adq(ads.cq), 0.2F);
  }
  
  public void a(ajt ☃, adq ☃, float ☃)
  {
    a(ado.a(☃), ☃, ☃);
  }
  
  public void a(ado ☃, adq ☃, float ☃)
  {
    a(new adq(☃, 1, 32767), ☃, ☃);
  }
  
  public void a(adq ☃, adq ☃, float ☃)
  {
    this.b.put(☃, ☃);
    this.c.put(☃, Float.valueOf(☃));
  }
  
  public adq a(adq ☃)
  {
    for (Map.Entry<adq, adq> ☃ : this.b.entrySet()) {
      if (a(☃, (adq)☃.getKey())) {
        return (adq)☃.getValue();
      }
    }
    return null;
  }
  
  private boolean a(adq ☃, adq ☃)
  {
    return (☃.b() == ☃.b()) && ((☃.i() == 32767) || (☃.i() == ☃.i()));
  }
  
  public Map<adq, adq> b()
  {
    return this.b;
  }
  
  public float b(adq ☃)
  {
    for (Map.Entry<adq, Float> ☃ : this.c.entrySet()) {
      if (a(☃, (adq)☃.getKey())) {
        return ((Float)☃.getValue()).floatValue();
      }
    }
    return 0.0F;
  }
}
