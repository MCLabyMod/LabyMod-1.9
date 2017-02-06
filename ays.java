import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class ays
  extends ayo
{
  private float j;
  
  public void a(ahx ☃, sb ☃)
  {
    super.a(☃, ☃);
    this.j = ☃.a(aym.g);
  }
  
  public void a()
  {
    super.a();
    this.b.a(aym.g, this.j);
  }
  
  public ayn b()
  {
    int ☃;
    if ((e()) && (this.b.ai()))
    {
      int ☃ = (int)this.b.bl().b;
      cj.a ☃ = new cj.a(on.c(this.b.p), ☃, on.c(this.b.r));
      ajt ☃ = this.a.o(☃).t();
      while ((☃ == aju.i) || (☃ == aju.j))
      {
        ☃++;
        ☃.c(on.c(this.b.p), ☃, on.c(this.b.r));
        ☃ = this.a.o(☃).t();
      }
    }
    else
    {
      int ☃;
      if (!this.b.z)
      {
        cj ☃ = new cj(this.b);
        while (((this.a.o(☃).a() == axe.a) || (this.a.o(☃).t().b(this.a, ☃))) && (☃.q() > 0)) {
          ☃ = ☃.b();
        }
        ☃ = ☃.a().q();
      }
      else
      {
        ☃ = on.c(this.b.bl().b + 0.5D);
      }
    }
    cj ☃ = new cj(this.b);
    aym ☃ = a(this.b, ☃.p(), ☃, ☃.r());
    if (this.b.a(☃) < 0.0F)
    {
      Set<cj> ☃ = new HashSet();
      ☃.add(new cj(this.b.bl().a, ☃, this.b.bl().c));
      ☃.add(new cj(this.b.bl().a, ☃, this.b.bl().f));
      ☃.add(new cj(this.b.bl().d, ☃, this.b.bl().c));
      ☃.add(new cj(this.b.bl().d, ☃, this.b.bl().f));
      for (cj ☃ : ☃)
      {
        aym ☃ = a(this.b, ☃);
        if (this.b.a(☃) >= 0.0F) {
          return a(☃.p(), ☃.q(), ☃.r());
        }
      }
    }
    return a(☃.p(), ☃, ☃.r());
  }
  
  public ayn a(double ☃, double ☃, double ☃)
  {
    return a(on.c(☃ - this.b.G / 2.0F), on.c(☃), on.c(☃ - this.b.G / 2.0F));
  }
  
  public int a(ayn[] ☃, ayn ☃, ayn ☃, float ☃)
  {
    int ☃ = 0;
    
    int ☃ = 0;
    aym ☃ = a(this.b, ☃.a, ☃.b + 1, ☃.c);
    if (this.b.a(☃) >= 0.0F) {
      ☃ = 1;
    }
    cj ☃ = new cj(☃.a, ☃.b, ☃.c).b();
    double ☃ = ☃.b - (1.0D - this.a.o(☃).c(this.a, ☃).e);
    
    ayn ☃ = a(☃.a, ☃.b, ☃.c + 1, ☃, ☃, cq.d);
    ayn ☃ = a(☃.a - 1, ☃.b, ☃.c, ☃, ☃, cq.e);
    ayn ☃ = a(☃.a + 1, ☃.b, ☃.c, ☃, ☃, cq.f);
    ayn ☃ = a(☃.a, ☃.b, ☃.c - 1, ☃, ☃, cq.c);
    if ((☃ != null) && (!☃.i) && (☃.a(☃) < ☃)) {
      ☃[(☃++)] = ☃;
    }
    if ((☃ != null) && (!☃.i) && (☃.a(☃) < ☃)) {
      ☃[(☃++)] = ☃;
    }
    if ((☃ != null) && (!☃.i) && (☃.a(☃) < ☃)) {
      ☃[(☃++)] = ☃;
    }
    if ((☃ != null) && (!☃.i) && (☃.a(☃) < ☃)) {
      ☃[(☃++)] = ☃;
    }
    boolean ☃ = (☃ == null) || (☃.m == aym.b) || (☃.l != 0.0F);
    boolean ☃ = (☃ == null) || (☃.m == aym.b) || (☃.l != 0.0F);
    boolean ☃ = (☃ == null) || (☃.m == aym.b) || (☃.l != 0.0F);
    boolean ☃ = (☃ == null) || (☃.m == aym.b) || (☃.l != 0.0F);
    if ((☃) && (☃))
    {
      ayn ☃ = a(☃.a - 1, ☃.b, ☃.c - 1, ☃, ☃, cq.c);
      if ((☃ != null) && (!☃.i) && (☃.a(☃) < ☃)) {
        ☃[(☃++)] = ☃;
      }
    }
    if ((☃) && (☃))
    {
      ayn ☃ = a(☃.a + 1, ☃.b, ☃.c - 1, ☃, ☃, cq.c);
      if ((☃ != null) && (!☃.i) && (☃.a(☃) < ☃)) {
        ☃[(☃++)] = ☃;
      }
    }
    if ((☃) && (☃))
    {
      ayn ☃ = a(☃.a - 1, ☃.b, ☃.c + 1, ☃, ☃, cq.d);
      if ((☃ != null) && (!☃.i) && (☃.a(☃) < ☃)) {
        ☃[(☃++)] = ☃;
      }
    }
    if ((☃) && (☃))
    {
      ayn ☃ = a(☃.a + 1, ☃.b, ☃.c + 1, ☃, ☃, cq.d);
      if ((☃ != null) && (!☃.i) && (☃.a(☃) < ☃)) {
        ☃[(☃++)] = ☃;
      }
    }
    return ☃;
  }
  
  private ayn a(int ☃, int ☃, int ☃, int ☃, double ☃, cq ☃)
  {
    ayn ☃ = null;
    
    cj ☃ = new cj(☃, ☃, ☃);
    cj ☃ = ☃.b();
    double ☃ = ☃ - (1.0D - this.a.o(☃).c(this.a, ☃).e);
    if (☃ - ☃ > 1.0D) {
      return null;
    }
    aym ☃ = a(this.b, ☃, ☃, ☃);
    float ☃ = this.b.a(☃);
    double ☃ = this.b.G / 2.0D;
    if (☃ >= 0.0F)
    {
      ☃ = a(☃, ☃, ☃);
      ☃.m = ☃;
      ☃.l = Math.max(☃.l, ☃);
    }
    if (☃ == aym.c) {
      return ☃;
    }
    if ((☃ == null) && (☃ > 0) && (☃ != aym.e) && (☃ != aym.d))
    {
      ☃ = a(☃, ☃ + 1, ☃, ☃ - 1, ☃, ☃);
      if ((☃ != null) && ((☃.m == aym.b) || (☃.m == aym.c)))
      {
        double ☃ = ☃ - ☃.g() + 0.5D;
        double ☃ = ☃ - ☃.i() + 0.5D;
        
        bbh ☃ = new bbh(☃ - ☃, ☃ + 0.001D, ☃ - ☃, ☃ + ☃, ☃ + this.b.H, ☃ + ☃);
        bbh ☃ = this.a.o(☃).c(this.a, ☃);
        
        bbh ☃ = ☃.a(0.0D, ☃.e - 0.002D, 0.0D);
        if (this.b.l.b(☃)) {
          ☃ = null;
        }
      }
    }
    if (☃ == aym.b)
    {
      bbh ☃ = new bbh(☃ - ☃ + 0.5D, ☃ + 0.001D, ☃ - ☃ + 0.5D, ☃ + ☃ + 0.5D, ☃ + this.b.H, ☃ + ☃ + 0.5D);
      if (this.b.l.b(☃)) {
        return null;
      }
      int ☃ = 0;
      while ((☃ > 0) && (☃ == aym.b))
      {
        ☃--;
        if (☃++ >= this.b.aW()) {
          return null;
        }
        ☃ = a(this.b, ☃, ☃, ☃);
        ☃ = this.b.a(☃);
        if ((☃ != aym.b) && (☃ >= 0.0F))
        {
          ☃ = a(☃, ☃, ☃);
          ☃.m = ☃;
          ☃.l = Math.max(☃.l, ☃);
        }
        else if (☃ < 0.0F)
        {
          return null;
        }
      }
    }
    return ☃;
  }
  
  public aym a(ahx ☃, int ☃, int ☃, int ☃, sb ☃, int ☃, int ☃, int ☃, boolean ☃, boolean ☃)
  {
    EnumSet<aym> ☃ = EnumSet.noneOf(aym.class);
    aym ☃ = aym.a;
    
    double ☃ = ☃.G / 2.0D;
    cj ☃ = new cj(☃);
    for (int ☃ = ☃; ☃ < ☃ + ☃; ☃++) {
      for (int ☃ = ☃; ☃ < ☃ + ☃; ☃++) {
        for (int ☃ = ☃; ☃ < ☃ + ☃; ☃++)
        {
          aym ☃ = a(☃, ☃, ☃, ☃);
          if ((☃ == aym.p) && (☃) && (☃)) {
            ☃ = aym.c;
          }
          if ((☃ == aym.o) && (!☃)) {
            ☃ = aym.a;
          }
          if ((☃ == aym.h) && (!(☃.o(☃).t() instanceof ajp)) && (!(☃.o(☃.b()).t() instanceof ajp))) {
            ☃ = aym.e;
          }
          if ((☃ == ☃) && (☃ == ☃) && (☃ == ☃)) {
            ☃ = ☃;
          }
          if ((☃ > ☃) && (☃ != aym.b))
          {
            bbh ☃ = new bbh(☃ - ☃ + 0.5D, ☃ + 0.001D, ☃ - ☃ + 0.5D, ☃ + ☃ + 0.5D, ☃ + ☃.H, ☃ + ☃ + 0.5D);
            if (!☃.l.b(☃)) {
              ☃ = aym.b;
            }
          }
          ☃.add(☃);
        }
      }
    }
    if (☃.contains(aym.e)) {
      return aym.e;
    }
    aym ☃ = aym.a;
    for (aym ☃ : ☃)
    {
      if (☃.a(☃) < 0.0F) {
        return ☃;
      }
      if (☃.a(☃) >= ☃.a(☃)) {
        ☃ = ☃;
      }
    }
    if ((☃ == aym.b) && (☃.a(☃) == 0.0F)) {
      return aym.b;
    }
    return ☃;
  }
  
  private aym a(sb ☃, cj ☃)
  {
    return a(this.a, ☃.p(), ☃.q(), ☃.r(), ☃, this.d, this.e, this.f, d(), c());
  }
  
  private aym a(sb ☃, int ☃, int ☃, int ☃)
  {
    return a(this.a, ☃, ☃, ☃, ☃, this.d, this.e, this.f, d(), c());
  }
  
  public static aym a(ahx ☃, int ☃, int ☃, int ☃)
  {
    cj ☃ = new cj(☃, ☃, ☃);
    arc ☃ = ☃.o(☃);
    ajt ☃ = ☃.t();
    axe ☃ = ☃.a();
    
    aym ☃ = aym.a;
    if ((☃ == aju.bd) || (☃ == aju.cw) || (☃ == aju.bx)) {
      return aym.d;
    }
    if (☃ == aju.ab) {
      return aym.j;
    }
    if (☃ == aju.aK) {
      return aym.l;
    }
    if (((☃ instanceof akv)) && (☃ == axe.d) && (!((Boolean)☃.c(akv.b)).booleanValue())) {
      return aym.p;
    }
    if (((☃ instanceof akv)) && (☃ == axe.f) && (!((Boolean)☃.c(akv.b)).booleanValue())) {
      return aym.q;
    }
    if (((☃ instanceof akv)) && (((Boolean)☃.c(akv.b)).booleanValue())) {
      return aym.o;
    }
    if ((☃ instanceof ajp)) {
      return aym.h;
    }
    if (((☃ instanceof alj)) || ((☃ instanceof apk)) || (((☃ instanceof alk)) && (!((Boolean)☃.c(alk.a)).booleanValue()))) {
      return aym.e;
    }
    if (☃ == axe.a)
    {
      ☃ = aym.b;
    }
    else
    {
      if (☃ == axe.h) {
        return aym.g;
      }
      if (☃ == axe.i) {
        return aym.f;
      }
    }
    if ((☃.b(☃, ☃)) && (☃ == aym.a)) {
      ☃ = aym.b;
    }
    if ((☃ == aym.b) && (☃ >= 1))
    {
      aym ☃ = a(☃, ☃, ☃ - 1, ☃);
      ☃ = (☃ == aym.c) || (☃ == aym.b) || (☃ == aym.g) || (☃ == aym.f) ? aym.b : aym.c;
    }
    if (☃ == aym.c) {
      for (int ☃ = ☃ - 1; ☃ <= ☃ + 1; ☃++) {
        for (int ☃ = ☃ - 1; ☃ <= ☃ + 1; ☃++) {
          if ((☃ != ☃) || (☃ != ☃))
          {
            ajt ☃ = ☃.o(new cj(☃, ☃, ☃)).t();
            if (☃ == aju.aK) {
              ☃ = aym.k;
            } else if (☃ == aju.ab) {
              ☃ = aym.i;
            }
          }
        }
      }
    }
    return ☃;
  }
}
