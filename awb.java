import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class awb
  extends awd
{
  private List<aig> a;
  private boolean b;
  private ahn[] d = new ahn[''];
  private double h = 32.0D;
  private int i = 3;
  
  public awb()
  {
    this.a = Lists.newArrayList();
    for (aig ☃ : aig.q) {
      if ((☃ != null) && (☃.j() > 0.0F)) {
        this.a.add(☃);
      }
    }
  }
  
  public awb(Map<String, String> ☃)
  {
    this();
    for (Map.Entry<String, String> ☃ : ☃.entrySet()) {
      if (((String)☃.getKey()).equals("distance")) {
        this.h = on.a((String)☃.getValue(), this.h, 1.0D);
      } else if (((String)☃.getKey()).equals("count")) {
        this.d = new ahn[on.a((String)☃.getValue(), this.d.length, 1)];
      } else if (((String)☃.getKey()).equals("spread")) {
        this.i = on.a((String)☃.getValue(), this.i, 1);
      }
    }
  }
  
  public String a()
  {
    return "Stronghold";
  }
  
  public cj a(aht ☃, cj ☃)
  {
    if (!this.b)
    {
      c();
      this.b = true;
    }
    cj ☃ = null;
    cj.a ☃ = new cj.a(0, 0, 0);
    double ☃ = Double.MAX_VALUE;
    for (ahn ☃ : this.d)
    {
      ☃.c((☃.a << 4) + 8, 32, (☃.b << 4) + 8);
      double ☃ = ☃.k(☃);
      if (☃ == null)
      {
        ☃ = new cj(☃);
        ☃ = ☃;
      }
      else if (☃ < ☃)
      {
        ☃ = new cj(☃);
        ☃ = ☃;
      }
    }
    return ☃;
  }
  
  protected boolean a(int ☃, int ☃)
  {
    if (!this.b)
    {
      c();
      this.b = true;
    }
    for (ahn ☃ : this.d) {
      if ((☃ == ☃.a) && (☃ == ☃.b)) {
        return true;
      }
    }
    return false;
  }
  
  private void c()
  {
    a(this.g);
    
    int ☃ = 0;
    for (awh ☃ : this.c.values()) {
      if (☃ < this.d.length) {
        this.d[(☃++)] = new ahn(☃.e(), ☃.f());
      }
    }
    Random ☃ = new Random();
    ☃.setSeed(this.g.O());
    
    double ☃ = ☃.nextDouble() * 3.141592653589793D * 2.0D;
    int ☃ = 0;
    int ☃ = 0;
    
    int ☃ = this.c.size();
    if (☃ < this.d.length) {
      for (int ☃ = 0; ☃ < this.d.length; ☃++)
      {
        double ☃ = 4.0D * this.h + this.h * ☃ * 6.0D + (☃.nextDouble() - 0.5D) * (this.h * 2.5D);
        int ☃ = (int)Math.round(Math.cos(☃) * ☃);
        int ☃ = (int)Math.round(Math.sin(☃) * ☃);
        
        cj ☃ = this.g.A().a((☃ << 4) + 8, (☃ << 4) + 8, 112, this.a, ☃);
        if (☃ != null)
        {
          ☃ = ☃.p() >> 4;
          ☃ = ☃.r() >> 4;
        }
        if (☃ >= ☃) {
          this.d[☃] = new ahn(☃, ☃);
        }
        ☃ += 6.283185307179586D / this.i;
        
        ☃++;
        if (☃ == this.i)
        {
          ☃++;
          ☃ = 0;
          this.i += 2 * this.i / (☃ + 1);
          this.i = Math.min(this.i, this.d.length - ☃);
          ☃ += ☃.nextDouble() * 3.141592653589793D * 2.0D;
        }
      }
    }
  }
  
  protected List<cj> E_()
  {
    List<cj> ☃ = Lists.newArrayList();
    for (ahn ☃ : this.d) {
      if (☃ != null) {
        ☃.add(☃.a(64));
      }
    }
    return ☃;
  }
  
  protected awh b(int ☃, int ☃)
  {
    awb.a ☃ = new awb.a(this.g, this.f, ☃, ☃);
    while ((☃.c().isEmpty()) || (((awc.m)☃.c().get(0)).b == null)) {
      ☃ = new awb.a(this.g, this.f, ☃, ☃);
    }
    return ☃;
  }
  
  public static class a
    extends awh
  {
    public a() {}
    
    public a(aht ☃, Random ☃, int ☃, int ☃)
    {
      super(☃);
      
      awc.b();
      
      awc.m ☃ = new awc.m(0, ☃, (☃ << 4) + 2, (☃ << 4) + 2);
      this.a.add(☃);
      ☃.a(☃, this.a, ☃);
      
      List<awg> ☃ = ☃.c;
      while (!☃.isEmpty())
      {
        int ☃ = ☃.nextInt(☃.size());
        awg ☃ = (awg)☃.remove(☃);
        ☃.a(☃, this.a, ☃);
      }
      d();
      a(☃, ☃, 10);
    }
  }
}
