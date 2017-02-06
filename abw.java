import com.google.common.base.Predicates;
import com.google.common.collect.Multimap;
import java.util.List;
import java.util.UUID;

public class abw
  extends ado
{
  private static final int[] m = { 13, 15, 16, 11 };
  private static final UUID[] n = { UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150") };
  public static final String[] a = { "minecraft:items/empty_armor_slot_boots", "minecraft:items/empty_armor_slot_leggings", "minecraft:items/empty_armor_slot_chestplate", "minecraft:items/empty_armor_slot_helmet" };
  public static final cr b = new cn()
  {
    protected adq b(ck ☃, adq ☃)
    {
      adq ☃ = abw.a(☃, ☃);
      return ☃ != null ? ☃ : super.b(☃, ☃);
    }
  };
  public final rw c;
  public final int d;
  public final int e;
  private final abw.a o;
  
  public static adq a(ck ☃, adq ☃)
  {
    cj ☃ = ☃.d().a(aku.e(☃.f()));
    
    int ☃ = ☃.p();
    int ☃ = ☃.q();
    int ☃ = ☃.r();
    bbh ☃ = new bbh(☃, ☃, ☃, ☃ + 1, ☃ + 1, ☃ + 1);
    List<sa> ☃ = ☃.i().a(sa.class, ☃, Predicates.and(rv.e, new rv.a(☃)));
    if (☃.isEmpty()) {
      return null;
    }
    sa ☃ = (sa)☃.get(0);
    rw ☃ = sb.d(☃);
    adq ☃ = ☃.k();
    ☃.b = 1;
    ☃.a(☃, ☃);
    if ((☃ instanceof sb)) {
      ((sb)☃).a(☃, 2.0F);
    }
    ☃.b -= 1;
    return ☃;
  }
  
  public static enum a
  {
    private final String f;
    private final int g;
    private final int[] h;
    private final int i;
    private final nf j;
    
    private a(String ☃, int ☃, int[] ☃, int ☃, nf ☃)
    {
      this.f = ☃;
      this.g = ☃;
      this.h = ☃;
      this.i = ☃;
      this.j = ☃;
    }
    
    public int a(rw ☃)
    {
      return abw.e()[☃.b()] * this.g;
    }
    
    public int b(rw ☃)
    {
      return this.h[☃.b()];
    }
    
    public int a()
    {
      return this.i;
    }
    
    public nf b()
    {
      return this.j;
    }
    
    public ado c()
    {
      if (this == a) {
        return ads.aM;
      }
      if (this == b) {
        return ads.l;
      }
      if (this == d) {
        return ads.m;
      }
      if (this == c) {
        return ads.l;
      }
      if (this == e) {
        return ads.k;
      }
      return null;
    }
    
    public String d()
    {
      return this.f;
    }
  }
  
  public abw(abw.a ☃, int ☃, rw ☃)
  {
    this.o = ☃;
    this.c = ☃;
    this.e = ☃;
    this.d = ☃.b(☃);
    e(☃.a(☃));
    this.j = 1;
    a(acq.j);
    aku.c.a(this, b);
  }
  
  public rw B_()
  {
    return this.c;
  }
  
  public int c()
  {
    return this.o.a();
  }
  
  public abw.a d()
  {
    return this.o;
  }
  
  public boolean e_(adq ☃)
  {
    if (this.o != abw.a.a) {
      return false;
    }
    dn ☃ = ☃.o();
    if ((☃ != null) && (☃.b("display", 10))) {
      return ☃.o("display").b("color", 3);
    }
    return false;
  }
  
  public int b(adq ☃)
  {
    if (this.o != abw.a.a) {
      return 16777215;
    }
    dn ☃ = ☃.o();
    if (☃ != null)
    {
      dn ☃ = ☃.o("display");
      if ((☃ != null) && 
        (☃.b("color", 3))) {
        return ☃.h("color");
      }
    }
    return 10511680;
  }
  
  public void c(adq ☃)
  {
    if (this.o != abw.a.a) {
      return;
    }
    dn ☃ = ☃.o();
    if (☃ == null) {
      return;
    }
    dn ☃ = ☃.o("display");
    if (☃.e("color")) {
      ☃.q("color");
    }
  }
  
  public void a(adq ☃, int ☃)
  {
    if (this.o != abw.a.a) {
      throw new UnsupportedOperationException("Can't dye non-leather!");
    }
    dn ☃ = ☃.o();
    if (☃ == null)
    {
      ☃ = new dn();
      ☃.d(☃);
    }
    dn ☃ = ☃.o("display");
    if (!☃.b("display", 10)) {
      ☃.a("display", ☃);
    }
    ☃.a("color", ☃);
  }
  
  public boolean a(adq ☃, adq ☃)
  {
    if (this.o.c() == ☃.b()) {
      return true;
    }
    return super.a(☃, ☃);
  }
  
  public qp<adq> a(adq ☃, aht ☃, zj ☃, qm ☃)
  {
    rw ☃ = sb.d(☃);
    adq ☃ = ☃.a(☃);
    if (☃ == null)
    {
      ☃.a(☃, ☃.k());
      ☃.b = 0;
      return new qp(qo.a, ☃);
    }
    return new qp(qo.c, ☃);
  }
  
  public Multimap<String, sn> a(rw ☃)
  {
    Multimap<String, sn> ☃ = super.a(☃);
    if (☃ == this.c) {
      ☃.put(yt.g.a(), new sn(n[☃.b()], "Armor modifier", this.d, 0));
    }
    return ☃;
  }
}
