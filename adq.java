import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public final class adq
{
  public static final DecimalFormat a = new DecimalFormat("#.##");
  public int b;
  public int c;
  private ado d;
  private dn e;
  private int f;
  private xs g;
  
  public adq(ajt ☃)
  {
    this(☃, 1);
  }
  
  public adq(ajt ☃, int ☃)
  {
    this(☃, ☃, 0);
  }
  
  public adq(ajt ☃, int ☃, int ☃)
  {
    this(ado.a(☃), ☃, ☃);
  }
  
  public adq(ado ☃)
  {
    this(☃, 1);
  }
  
  public adq(ado ☃, int ☃)
  {
    this(☃, ☃, 0);
  }
  
  public adq(ado ☃, int ☃, int ☃)
  {
    this.d = ☃;
    this.b = ☃;
    this.f = ☃;
    if (this.f < 0) {
      this.f = 0;
    }
  }
  
  public static adq a(dn ☃)
  {
    adq ☃ = new adq();
    ☃.c(☃);
    return ☃.b() != null ? ☃ : null;
  }
  
  private adq() {}
  
  public adq a(int ☃)
  {
    ☃ = Math.min(☃, this.b);
    adq ☃ = new adq(this.d, ☃, this.f);
    if (this.e != null) {
      ☃.e = ((dn)this.e.b());
    }
    this.b -= ☃;
    return ☃;
  }
  
  public ado b()
  {
    return this.d;
  }
  
  public qo a(zj ☃, aht ☃, cj ☃, qm ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    qo ☃ = b().a(this, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    if (☃ == qo.a) {
      ☃.b(nt.b(this.d));
    }
    return ☃;
  }
  
  public float a(arc ☃)
  {
    return b().a(this, ☃);
  }
  
  public qp<adq> a(aht ☃, zj ☃, qm ☃)
  {
    return b().a(this, ☃, ☃, ☃);
  }
  
  public adq a(aht ☃, sa ☃)
  {
    return b().a(this, ☃, ☃);
  }
  
  public dn b(dn ☃)
  {
    kk ☃ = (kk)ado.f.b(this.d);
    ☃.a("id", ☃ == null ? "minecraft:air" : ☃.toString());
    ☃.a("Count", (byte)this.b);
    ☃.a("Damage", (short)this.f);
    if (this.e != null) {
      ☃.a("tag", this.e);
    }
    return ☃;
  }
  
  public void c(dn ☃)
  {
    this.d = ado.d(☃.l("id"));
    this.b = ☃.f("Count");
    this.f = ☃.g("Damage");
    if (this.f < 0) {
      this.f = 0;
    }
    if (☃.b("tag", 10))
    {
      this.e = ☃.o("tag");
      if (this.d != null) {
        this.d.a(this.e);
      }
    }
  }
  
  public int c()
  {
    return b().j();
  }
  
  public boolean d()
  {
    return (c() > 1) && ((!e()) || (!g()));
  }
  
  public boolean e()
  {
    if (this.d == null) {
      return false;
    }
    if (this.d.l() <= 0) {
      return false;
    }
    if ((n()) && (o().p("Unbreakable"))) {
      return false;
    }
    return true;
  }
  
  public boolean f()
  {
    return this.d.k();
  }
  
  public boolean g()
  {
    return (e()) && (this.f > 0);
  }
  
  public int h()
  {
    return this.f;
  }
  
  public int i()
  {
    return this.f;
  }
  
  public void b(int ☃)
  {
    this.f = ☃;
    if (this.f < 0) {
      this.f = 0;
    }
  }
  
  public int j()
  {
    return this.d == null ? 0 : this.d.l();
  }
  
  public boolean a(int ☃, Random ☃)
  {
    if (!e()) {
      return false;
    }
    if (☃ > 0)
    {
      int ☃ = ago.a(agq.s, this);
      
      int ☃ = 0;
      for (int ☃ = 0; (☃ > 0) && (☃ < ☃); ☃++) {
        if (agk.a(this, ☃, ☃)) {
          ☃++;
        }
      }
      ☃ -= ☃;
      if (☃ <= 0) {
        return false;
      }
    }
    this.f += ☃;
    
    return this.f > j();
  }
  
  public void a(int ☃, sa ☃)
  {
    if (((☃ instanceof zj)) && (((zj)☃).bJ.d)) {
      return;
    }
    if (!e()) {
      return;
    }
    if (a(☃, ☃.bF()))
    {
      ☃.b(this);
      
      this.b -= 1;
      if ((☃ instanceof zj))
      {
        zj ☃ = (zj)☃;
        ☃.b(nt.c(this.d));
      }
      if (this.b < 0) {
        this.b = 0;
      }
      this.f = 0;
    }
  }
  
  public void a(sa ☃, zj ☃)
  {
    boolean ☃ = this.d.a(this, ☃, ☃);
    if (☃) {
      ☃.b(nt.b(this.d));
    }
  }
  
  public void a(aht ☃, arc ☃, cj ☃, zj ☃)
  {
    boolean ☃ = this.d.a(this, ☃, ☃, ☃, ☃);
    if (☃) {
      ☃.b(nt.b(this.d));
    }
  }
  
  public boolean b(arc ☃)
  {
    return this.d.a(☃);
  }
  
  public boolean a(zj ☃, sa ☃, qm ☃)
  {
    return this.d.a(this, ☃, ☃, ☃);
  }
  
  public adq k()
  {
    adq ☃ = new adq(this.d, this.b, this.f);
    if (this.e != null) {
      ☃.e = ((dn)this.e.b());
    }
    return ☃;
  }
  
  public static boolean a(adq ☃, adq ☃)
  {
    if ((☃ == null) && (☃ == null)) {
      return true;
    }
    if ((☃ == null) || (☃ == null)) {
      return false;
    }
    if ((☃.e == null) && (☃.e != null)) {
      return false;
    }
    if ((☃.e != null) && (!☃.e.equals(☃.e))) {
      return false;
    }
    return true;
  }
  
  public static boolean b(adq ☃, adq ☃)
  {
    if ((☃ == null) && (☃ == null)) {
      return true;
    }
    if ((☃ == null) || (☃ == null)) {
      return false;
    }
    return ☃.e(☃);
  }
  
  private boolean e(adq ☃)
  {
    if (this.b != ☃.b) {
      return false;
    }
    if (this.d != ☃.d) {
      return false;
    }
    if (this.f != ☃.f) {
      return false;
    }
    if ((this.e == null) && (☃.e != null)) {
      return false;
    }
    if ((this.e != null) && (!this.e.equals(☃.e))) {
      return false;
    }
    return true;
  }
  
  public static boolean c(adq ☃, adq ☃)
  {
    if (☃ == ☃) {
      return true;
    }
    if ((☃ != null) && (☃ != null)) {
      return ☃.a(☃);
    }
    return false;
  }
  
  public static boolean d(adq ☃, adq ☃)
  {
    if (☃ == ☃) {
      return true;
    }
    if ((☃ != null) && (☃ != null)) {
      return ☃.b(☃);
    }
    return false;
  }
  
  public boolean a(adq ☃)
  {
    return (☃ != null) && (this.d == ☃.d) && (this.f == ☃.f);
  }
  
  public boolean b(adq ☃)
  {
    if (e()) {
      return (☃ != null) && (this.d == ☃.d);
    }
    return a(☃);
  }
  
  public String a()
  {
    return this.d.f_(this);
  }
  
  public static adq c(adq ☃)
  {
    return ☃ == null ? null : ☃.k();
  }
  
  public String toString()
  {
    return this.b + "x" + this.d.a() + "@" + this.f;
  }
  
  public void a(aht ☃, rr ☃, int ☃, boolean ☃)
  {
    if (this.c > 0) {
      this.c -= 1;
    }
    if (this.d != null) {
      this.d.a(this, ☃, ☃, ☃, ☃);
    }
  }
  
  public void a(aht ☃, zj ☃, int ☃)
  {
    ☃.a(nt.a(this.d), ☃);
    this.d.b(this, ☃, ☃);
  }
  
  public int l()
  {
    return b().e(this);
  }
  
  public afa m()
  {
    return b().f(this);
  }
  
  public void a(aht ☃, sa ☃, int ☃)
  {
    b().a(this, ☃, ☃, ☃);
  }
  
  public boolean n()
  {
    return this.e != null;
  }
  
  public dn o()
  {
    return this.e;
  }
  
  public dn a(String ☃, boolean ☃)
  {
    if ((this.e == null) || (!this.e.b(☃, 10)))
    {
      if (☃)
      {
        dn ☃ = new dn();
        a(☃, ☃);
        return ☃;
      }
      return null;
    }
    return this.e.o(☃);
  }
  
  public du p()
  {
    if (this.e == null) {
      return null;
    }
    return this.e.c("ench", 10);
  }
  
  public void d(dn ☃)
  {
    this.e = ☃;
  }
  
  public String q()
  {
    String ☃ = b().a(this);
    if ((this.e != null) && (this.e.b("display", 10)))
    {
      dn ☃ = this.e.o("display");
      if (☃.b("Name", 8)) {
        ☃ = ☃.l("Name");
      }
    }
    return ☃;
  }
  
  public adq c(String ☃)
  {
    if (this.e == null) {
      this.e = new dn();
    }
    if (!this.e.b("display", 10)) {
      this.e.a("display", new dn());
    }
    this.e.o("display").a("Name", ☃);
    return this;
  }
  
  public void r()
  {
    if (this.e == null) {
      return;
    }
    if (!this.e.b("display", 10)) {
      return;
    }
    dn ☃ = this.e.o("display");
    ☃.q("Name");
    if (☃.c_())
    {
      this.e.q("display");
      if (this.e.c_()) {
        d(null);
      }
    }
  }
  
  public boolean s()
  {
    if (this.e == null) {
      return false;
    }
    if (!this.e.b("display", 10)) {
      return false;
    }
    return this.e.o("display").b("Name", 8);
  }
  
  public List<String> a(zj ☃, boolean ☃)
  {
    List<String> ☃ = Lists.newArrayList();
    
    String ☃ = q();
    if (s()) {
      ☃ = a.u + ☃;
    }
    ☃ = ☃ + a.v;
    if (☃)
    {
      String ☃ = "";
      if (!☃.isEmpty())
      {
        ☃ = ☃ + " (";
        ☃ = ")";
      }
      int ☃ = ado.a(this.d);
      if (f()) {
        ☃ = ☃ + String.format("#%04d/%d%s", new Object[] { Integer.valueOf(☃), Integer.valueOf(this.f), ☃ });
      } else {
        ☃ = ☃ + String.format("#%04d%s", new Object[] { Integer.valueOf(☃), ☃ });
      }
    }
    else if ((!s()) && 
      (this.d == ads.bk))
    {
      ☃ = ☃ + " #" + this.f;
    }
    ☃.add(☃);
    
    int ☃ = 0;
    if ((n()) && 
      (this.e.b("HideFlags", 99))) {
      ☃ = this.e.h("HideFlags");
    }
    if ((☃ & 0x20) == 0) {
      this.d.a(this, ☃, ☃, ☃);
    }
    if (n())
    {
      if ((☃ & 0x1) == 0)
      {
        du ☃ = p();
        if (☃ != null) {
          for (int ☃ = 0; ☃ < ☃.c(); ☃++)
          {
            int ☃ = ☃.b(☃).g("id");
            int ☃ = ☃.b(☃).g("lvl");
            if (agm.c(☃) != null) {
              ☃.add(agm.c(☃).d(☃));
            }
          }
        }
      }
      if (this.e.b("display", 10))
      {
        dn ☃ = this.e.o("display");
        if (☃.b("color", 3)) {
          if (☃) {
            ☃.add("Color: #" + String.format("%06X", new Object[] { Integer.valueOf(☃.h("color")) }));
          } else {
            ☃.add(a.u + di.a("item.dyed"));
          }
        }
        if (☃.d("Lore") == 9)
        {
          du ☃ = ☃.c("Lore", 8);
          if (!☃.c_()) {
            for (int ☃ = 0; ☃ < ☃.c(); ☃++) {
              ☃.add(a.f + "" + a.u + ☃.g(☃));
            }
          }
        }
      }
    }
    for (rw ☃ : rw.values())
    {
      Multimap<String, sn> ☃ = a(☃);
      if ((!☃.isEmpty()) && ((☃ & 0x2) == 0))
      {
        ☃.add("");
        ☃.add(di.a("item.modifiers." + ☃.d()));
        for (Map.Entry<String, sn> ☃ : ☃.entries())
        {
          sn ☃ = (sn)☃.getValue();
          double ☃ = ☃.d();
          
          boolean ☃ = false;
          if (☃.a() == ado.g)
          {
            ☃ += ☃.a(yt.e).b();
            ☃ += ago.a(this, sf.a);
            ☃ = true;
          }
          else if (☃.a() == ado.h)
          {
            ☃ += ☃.a(yt.f).b();
            ☃ = true;
          }
          double ☃;
          double ☃;
          if ((☃.c() == 1) || (☃.c() == 2)) {
            ☃ = ☃ * 100.0D;
          } else {
            ☃ = ☃;
          }
          if (☃)
          {
            ☃.add(" " + di.a(new StringBuilder().append("attribute.modifier.equals.").append(☃.c()).toString(), new Object[] { a.format(☃), di.a("attribute.name." + (String)☃.getKey()) }));
          }
          else if (☃ > 0.0D)
          {
            ☃.add(a.j + " " + di.a(new StringBuilder().append("attribute.modifier.plus.").append(☃.c()).toString(), new Object[] { a.format(☃), di.a("attribute.name." + (String)☃.getKey()) }));
          }
          else if (☃ < 0.0D)
          {
            ☃ *= -1.0D;
            ☃.add(a.m + " " + di.a(new StringBuilder().append("attribute.modifier.take.").append(☃.c()).toString(), new Object[] { a.format(☃), di.a("attribute.name." + (String)☃.getKey()) }));
          }
        }
      }
    }
    if ((n()) && (o().p("Unbreakable")) && ((☃ & 0x4) == 0)) {
      ☃.add(a.j + di.a("item.unbreakable"));
    }
    if ((n()) && (this.e.b("CanDestroy", 9)) && ((☃ & 0x8) == 0))
    {
      du ☃ = this.e.c("CanDestroy", 8);
      if (!☃.c_())
      {
        ☃.add("");
        ☃.add(a.h + di.a("item.canBreak"));
        for (int ☃ = 0; ☃ < ☃.c(); ☃++)
        {
          ajt ☃ = ajt.b(☃.g(☃));
          if (☃ != null) {
            ☃.add(a.i + ☃.c());
          } else {
            ☃.add(a.i + "missingno");
          }
        }
      }
    }
    if ((n()) && (this.e.b("CanPlaceOn", 9)) && ((☃ & 0x10) == 0))
    {
      du ☃ = this.e.c("CanPlaceOn", 8);
      if (!☃.c_())
      {
        ☃.add("");
        ☃.add(a.h + di.a("item.canPlace"));
        for (int ☃ = 0; ☃ < ☃.c(); ☃++)
        {
          ajt ☃ = ajt.b(☃.g(☃));
          if (☃ != null) {
            ☃.add(a.i + ☃.c());
          } else {
            ☃.add(a.i + "missingno");
          }
        }
      }
    }
    if (☃)
    {
      if (g()) {
        ☃.add("Durability: " + (j() - h()) + " / " + j());
      }
      ☃.add(a.i + ((kk)ado.f.b(this.d)).toString());
      if (n()) {
        ☃.add(a.i + "NBT: " + o().c().size() + " tag(s)");
      }
    }
    return ☃;
  }
  
  public boolean t()
  {
    return b().i_(this);
  }
  
  public aee u()
  {
    return b().g(this);
  }
  
  public boolean v()
  {
    if (!b().g_(this)) {
      return false;
    }
    if (w()) {
      return false;
    }
    return true;
  }
  
  public void a(agm ☃, int ☃)
  {
    if (this.e == null) {
      d(new dn());
    }
    if (!this.e.b("ench", 9)) {
      this.e.a("ench", new du());
    }
    du ☃ = this.e.c("ench", 10);
    dn ☃ = new dn();
    ☃.a("id", (short)agm.b(☃));
    ☃.a("lvl", (short)(byte)☃);
    ☃.a(☃);
  }
  
  public boolean w()
  {
    if ((this.e != null) && (this.e.b("ench", 9))) {
      return true;
    }
    return false;
  }
  
  public void a(String ☃, eb ☃)
  {
    if (this.e == null) {
      d(new dn());
    }
    this.e.a(☃, ☃);
  }
  
  public boolean x()
  {
    return b().s();
  }
  
  public boolean y()
  {
    return this.g != null;
  }
  
  public void a(xs ☃)
  {
    this.g = ☃;
  }
  
  public xs z()
  {
    return this.g;
  }
  
  public int A()
  {
    if ((n()) && (this.e.b("RepairCost", 3))) {
      return this.e.h("RepairCost");
    }
    return 0;
  }
  
  public void c(int ☃)
  {
    if (!n()) {
      this.e = new dn();
    }
    this.e.a("RepairCost", ☃);
  }
  
  public Multimap<String, sn> a(rw ☃)
  {
    Multimap<String, sn> ☃;
    if ((n()) && (this.e.b("AttributeModifiers", 9)))
    {
      Multimap<String, sn> ☃ = HashMultimap.create();
      du ☃ = this.e.c("AttributeModifiers", 10);
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        dn ☃ = ☃.b(☃);
        sn ☃ = yt.a(☃);
        if (☃ != null) {
          if ((!☃.b("Slot", 8)) || 
            (☃.l("Slot").equals(☃.d()))) {
            if ((☃.a().getLeastSignificantBits() != 0L) && (☃.a().getMostSignificantBits() != 0L)) {
              ☃.put(☃.l("AttributeName"), ☃);
            }
          }
        }
      }
    }
    else
    {
      ☃ = b().a(☃);
    }
    return ☃;
  }
  
  public void a(String ☃, sn ☃, rw ☃)
  {
    if (this.e == null) {
      this.e = new dn();
    }
    if (!this.e.b("AttributeModifiers", 9)) {
      this.e.a("AttributeModifiers", new du());
    }
    du ☃ = this.e.c("AttributeModifiers", 10);
    dn ☃ = yt.a(☃);
    ☃.a("AttributeName", ☃);
    if (☃ != null) {
      ☃.a("Slot", ☃.d());
    }
    ☃.a(☃);
  }
  
  public void a(ado ☃)
  {
    this.d = ☃;
  }
  
  public eu B()
  {
    fa ☃ = new fa(q());
    if (s()) {
      ☃.b().b(Boolean.valueOf(true));
    }
    eu ☃ = new fa("[").a(☃).a("]");
    if (this.d != null)
    {
      dn ☃ = new dn();
      b(☃);
      
      ☃.b().a(new ew(ew.a.c, new fa(☃.toString())));
      ☃.b().a(u().e);
    }
    return ☃;
  }
  
  private ajt h = null;
  private boolean i = false;
  
  public boolean a(ajt ☃)
  {
    if (☃ == this.h) {
      return this.i;
    }
    this.h = ☃;
    if ((n()) && (this.e.b("CanDestroy", 9)))
    {
      du ☃ = this.e.c("CanDestroy", 8);
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        ajt ☃ = ajt.b(☃.g(☃));
        if (☃ == ☃)
        {
          this.i = true;
          return true;
        }
      }
    }
    this.i = false;
    return false;
  }
  
  private ajt j = null;
  private boolean k = false;
  
  public boolean b(ajt ☃)
  {
    if (☃ == this.j) {
      return this.k;
    }
    this.j = ☃;
    if ((n()) && (this.e.b("CanPlaceOn", 9)))
    {
      du ☃ = this.e.c("CanPlaceOn", 8);
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        ajt ☃ = ajt.b(☃.g(☃));
        if (☃ == ☃)
        {
          this.k = true;
          return true;
        }
      }
    }
    this.k = false;
    return false;
  }
}
