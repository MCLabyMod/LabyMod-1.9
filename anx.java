import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class anx
  extends ajt
{
  public static final arp<anx.a> a = arp.a("north", anx.a.class);
  public static final arp<anx.a> b = arp.a("east", anx.a.class);
  public static final arp<anx.a> c = arp.a("south", anx.a.class);
  public static final arp<anx.a> d = arp.a("west", anx.a.class);
  public static final arq e = arq.a("power", 0, 15);
  protected static final bbh[] f = { new bbh(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 0.8125D), new bbh(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 1.0D), new bbh(0.0D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 0.8125D), new bbh(0.0D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 1.0D), new bbh(0.1875D, 0.0D, 0.0D, 0.8125D, 0.0625D, 0.8125D), new bbh(0.1875D, 0.0D, 0.0D, 0.8125D, 0.0625D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 0.8125D, 0.0625D, 0.8125D), new bbh(0.0D, 0.0D, 0.0D, 0.8125D, 0.0625D, 1.0D), new bbh(0.1875D, 0.0D, 0.1875D, 1.0D, 0.0625D, 0.8125D), new bbh(0.1875D, 0.0D, 0.1875D, 1.0D, 0.0625D, 1.0D), new bbh(0.0D, 0.0D, 0.1875D, 1.0D, 0.0625D, 0.8125D), new bbh(0.0D, 0.0D, 0.1875D, 1.0D, 0.0625D, 1.0D), new bbh(0.1875D, 0.0D, 0.0D, 1.0D, 0.0625D, 0.8125D), new bbh(0.1875D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 0.8125D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D) };
  private boolean g = true;
  private final Set<cj> B = Sets.newHashSet();
  
  public anx()
  {
    super(axe.q);
    w(this.A.b().a(a, anx.a.c).a(b, anx.a.c).a(c, anx.a.c).a(d, anx.a.c).a(e, Integer.valueOf(0)));
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return f[x(☃.b(☃, ☃))];
  }
  
  private static int x(arc ☃)
  {
    int ☃ = 0;
    boolean ☃ = ☃.c(a) != anx.a.c;
    boolean ☃ = ☃.c(b) != anx.a.c;
    boolean ☃ = ☃.c(c) != anx.a.c;
    boolean ☃ = ☃.c(d) != anx.a.c;
    if ((☃) || ((☃) && (!☃) && (!☃) && (!☃))) {
      ☃ |= 1 << cq.c.b();
    }
    if ((☃) || ((☃) && (!☃) && (!☃) && (!☃))) {
      ☃ |= 1 << cq.f.b();
    }
    if ((☃) || ((☃) && (!☃) && (!☃) && (!☃))) {
      ☃ |= 1 << cq.d.b();
    }
    if ((☃) || ((☃) && (!☃) && (!☃) && (!☃))) {
      ☃ |= 1 << cq.e.b();
    }
    return ☃;
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    ☃ = ☃.a(d, b(☃, ☃, cq.e));
    ☃ = ☃.a(b, b(☃, ☃, cq.f));
    ☃ = ☃.a(a, b(☃, ☃, cq.c));
    ☃ = ☃.a(c, b(☃, ☃, cq.d));
    
    return ☃;
  }
  
  private anx.a b(ahx ☃, cj ☃, cq ☃)
  {
    cj ☃ = ☃.a(☃);
    arc ☃ = ☃.o(☃.a(☃));
    if ((a(☃.o(☃), ☃)) || ((!☃.l()) && (i(☃.o(☃.b()))))) {
      return anx.a.b;
    }
    arc ☃ = ☃.o(☃.a());
    if (!☃.l())
    {
      boolean ☃ = (☃.o(☃).q()) || (☃.o(☃).t() == aju.aX);
      if ((☃) && (i(☃.o(☃.a()))))
      {
        if (☃.k()) {
          return anx.a.a;
        }
        return anx.a.b;
      }
    }
    return anx.a.c;
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    return k;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    return (☃.o(☃.b()).q()) || (☃.o(☃.b()).t() == aju.aX);
  }
  
  private arc e(aht ☃, cj ☃, arc ☃)
  {
    ☃ = a(☃, ☃, ☃, ☃);
    
    List<cj> ☃ = Lists.newArrayList(this.B);
    this.B.clear();
    for (cj ☃ : ☃) {
      ☃.d(☃, this);
    }
    return ☃;
  }
  
  private arc a(aht ☃, cj ☃, cj ☃, arc ☃)
  {
    arc ☃ = ☃;
    int ☃ = ((Integer)☃.c(e)).intValue();
    int ☃ = 0;
    
    ☃ = a(☃, ☃, ☃);
    
    this.g = false;
    int ☃ = ☃.z(☃);
    this.g = true;
    if ((☃ > 0) && (☃ > ☃ - 1)) {
      ☃ = ☃;
    }
    int ☃ = 0;
    for (cq ☃ : cq.c.a)
    {
      cj ☃ = ☃.a(☃);
      boolean ☃ = (☃.p() != ☃.p()) || (☃.r() != ☃.r());
      if (☃) {
        ☃ = a(☃, ☃, ☃);
      }
      if ((☃.o(☃).l()) && (!☃.o(☃.a()).l()))
      {
        if ((☃) && (☃.q() >= ☃.q())) {
          ☃ = a(☃, ☃.a(), ☃);
        }
      }
      else if ((!☃.o(☃).l()) && 
        (☃) && (☃.q() <= ☃.q())) {
        ☃ = a(☃, ☃.b(), ☃);
      }
    }
    if (☃ > ☃) {
      ☃ = ☃ - 1;
    } else if (☃ > 0) {
      ☃--;
    } else {
      ☃ = 0;
    }
    if (☃ > ☃ - 1) {
      ☃ = ☃;
    }
    if (☃ != ☃)
    {
      ☃ = ☃.a(e, Integer.valueOf(☃));
      if (☃.o(☃) == ☃) {
        ☃.a(☃, ☃, 2);
      }
      this.B.add(☃);
      for (cq ☃ : cq.values()) {
        this.B.add(☃.a(☃));
      }
    }
    return ☃;
  }
  
  private void b(aht ☃, cj ☃)
  {
    if (☃.o(☃).t() != this) {
      return;
    }
    ☃.d(☃, this);
    for (cq ☃ : cq.values()) {
      ☃.d(☃.a(☃), this);
    }
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    if (☃.E) {
      return;
    }
    e(☃, ☃, ☃);
    for (cq ☃ : cq.c.b) {
      ☃.d(☃.a(☃), this);
    }
    for (cq ☃ : cq.c.a) {
      b(☃, ☃.a(☃));
    }
    for (cq ☃ : cq.c.a)
    {
      cj ☃ = ☃.a(☃);
      if (☃.o(☃).l()) {
        b(☃, ☃.a());
      } else {
        b(☃, ☃.b());
      }
    }
  }
  
  public void b(aht ☃, cj ☃, arc ☃)
  {
    super.b(☃, ☃, ☃);
    if (☃.E) {
      return;
    }
    for (cq ☃ : cq.values()) {
      ☃.d(☃.a(☃), this);
    }
    e(☃, ☃, ☃);
    for (cq ☃ : cq.c.a) {
      b(☃, ☃.a(☃));
    }
    for (cq ☃ : cq.c.a)
    {
      cj ☃ = ☃.a(☃);
      if (☃.o(☃).l()) {
        b(☃, ☃.a());
      } else {
        b(☃, ☃.b());
      }
    }
  }
  
  private int a(aht ☃, cj ☃, int ☃)
  {
    if (☃.o(☃).t() != this) {
      return ☃;
    }
    int ☃ = ((Integer)☃.o(☃).c(e)).intValue();
    if (☃ > ☃) {
      return ☃;
    }
    return ☃;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (☃.E) {
      return;
    }
    if (a(☃, ☃))
    {
      e(☃, ☃, ☃);
    }
    else
    {
      b(☃, ☃, ☃, 0);
      ☃.g(☃);
    }
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ads.aE;
  }
  
  public int c(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    if (!this.g) {
      return 0;
    }
    return ☃.a(☃, ☃, ☃);
  }
  
  public int b(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    if (!this.g) {
      return 0;
    }
    int ☃ = ((Integer)☃.c(e)).intValue();
    if (☃ == 0) {
      return 0;
    }
    if (☃ == cq.b) {
      return ☃;
    }
    EnumSet<cq> ☃ = EnumSet.noneOf(cq.class);
    for (cq ☃ : cq.c.a) {
      if (c(☃, ☃, ☃)) {
        ☃.add(☃);
      }
    }
    if ((☃.k().c()) && (☃.isEmpty())) {
      return ☃;
    }
    if ((☃.contains(☃)) && (!☃.contains(☃.f())) && (!☃.contains(☃.e()))) {
      return ☃;
    }
    return 0;
  }
  
  private boolean c(ahx ☃, cj ☃, cq ☃)
  {
    cj ☃ = ☃.a(☃);
    arc ☃ = ☃.o(☃);
    boolean ☃ = ☃.l();
    
    boolean ☃ = ☃.o(☃.a()).l();
    if ((!☃) && (☃) && (c(☃, ☃.a()))) {
      return true;
    }
    if (a(☃, ☃)) {
      return true;
    }
    if ((☃.t() == aju.bc) && (☃.c(akr.D) == ☃)) {
      return true;
    }
    if ((!☃) && (c(☃, ☃.b()))) {
      return true;
    }
    return false;
  }
  
  protected static boolean c(ahx ☃, cj ☃)
  {
    return i(☃.o(☃));
  }
  
  protected static boolean i(arc ☃)
  {
    return a(☃, null);
  }
  
  protected static boolean a(arc ☃, cq ☃)
  {
    ajt ☃ = ☃.t();
    if (☃ == aju.af) {
      return true;
    }
    if (aju.bb.C(☃))
    {
      cq ☃ = (cq)☃.c(aoc.D);
      return (☃ == ☃) || (☃.d() == ☃);
    }
    return (☃.m()) && (☃ != null);
  }
  
  public boolean g(arc ☃)
  {
    return this.g;
  }
  
  public static int e(int ☃)
  {
    float ☃ = ☃ / 15.0F;
    float ☃ = ☃ * 0.6F + 0.4F;
    if (☃ == 0) {
      ☃ = 0.3F;
    }
    float ☃ = ☃ * ☃ * 0.7F - 0.5F;
    float ☃ = ☃ * ☃ * 0.6F - 0.7F;
    if (☃ < 0.0F) {
      ☃ = 0.0F;
    }
    if (☃ < 0.0F) {
      ☃ = 0.0F;
    }
    int ☃ = on.a((int)(☃ * 255.0F), 0, 255);
    int ☃ = on.a((int)(☃ * 255.0F), 0, 255);
    int ☃ = on.a((int)(☃ * 255.0F), 0, 255);
    return 0xFF000000 | ☃ << 16 | ☃ << 8 | ☃;
  }
  
  public void a(arc ☃, aht ☃, cj ☃, Random ☃)
  {
    int ☃ = ((Integer)☃.c(e)).intValue();
    if (☃ == 0) {
      return;
    }
    double ☃ = ☃.p() + 0.5D + (☃.nextFloat() - 0.5D) * 0.2D;
    double ☃ = ☃.q() + 0.0625F;
    double ☃ = ☃.r() + 0.5D + (☃.nextFloat() - 0.5D) * 0.2D;
    
    float ☃ = ☃ / 15.0F;
    float ☃ = ☃ * 0.6F + 0.4F;
    float ☃ = Math.max(0.0F, ☃ * ☃ * 0.7F - 0.5F);
    float ☃ = Math.max(0.0F, ☃ * ☃ * 0.6F - 0.7F);
    
    ☃.a(cy.E, ☃, ☃, ☃, ☃, ☃, ☃, new int[0]);
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(ads.aE);
  }
  
  public ahm f()
  {
    return ahm.c;
  }
  
  public arc a(int ☃)
  {
    return u().a(e, Integer.valueOf(☃));
  }
  
  public int e(arc ☃)
  {
    return ((Integer)☃.c(e)).intValue();
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    switch (anx.1.a[☃.ordinal()])
    {
    case 1: 
      return ☃.a(a, ☃.c(c)).a(b, ☃.c(d)).a(c, ☃.c(a)).a(d, ☃.c(b));
    case 2: 
      return ☃.a(a, ☃.c(b)).a(b, ☃.c(c)).a(c, ☃.c(d)).a(d, ☃.c(a));
    case 3: 
      return ☃.a(a, ☃.c(d)).a(b, ☃.c(a)).a(c, ☃.c(b)).a(d, ☃.c(c));
    }
    return ☃;
  }
  
  public arc a(arc ☃, amr ☃)
  {
    switch (anx.1.b[☃.ordinal()])
    {
    case 1: 
      return ☃.a(a, ☃.c(c)).a(c, ☃.c(a));
    case 2: 
      return ☃.a(b, ☃.c(d)).a(d, ☃.c(b));
    }
    return super.a(☃, ☃);
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a, b, c, d, e });
  }
  
  static enum a
    implements or
  {
    private final String d;
    
    private a(String ☃)
    {
      this.d = ☃;
    }
    
    public String toString()
    {
      return m();
    }
    
    public String m()
    {
      return this.d;
    }
  }
}
