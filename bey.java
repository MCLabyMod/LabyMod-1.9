import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.lwjgl.input.Keyboard;

public class bey
  extends bfb
{
  private static final List<bey.a> a = ;
  private final bej f;
  private String g;
  private String h;
  private String i;
  private bey.b r;
  private bcz s;
  private bdd t;
  
  static
  {
    a("Classic Flat", ado.a(aju.c), ail.c, Arrays.asList(new String[] { "village" }), new avl[] { new avl(1, aju.c), new avl(2, aju.d), new avl(1, aju.h) });
    
    a("Tunnelers' Dream", ado.a(aju.b), ail.e, Arrays.asList(new String[] { "biome_1", "dungeon", "decoration", "stronghold", "mineshaft" }), new avl[] { new avl(1, aju.c), new avl(5, aju.d), new avl(230, aju.b), new avl(1, aju.h) });
    
    a("Water World", ads.az, ail.z, Arrays.asList(new String[] { "biome_1", "oceanmonument" }), new avl[] { new avl(90, aju.j), new avl(5, aju.m), new avl(5, aju.d), new avl(5, aju.b), new avl(1, aju.h) });
    
    a("Overworld", ado.a(aju.H), apc.a.b.a(), ail.c, Arrays.asList(new String[] { "village", "biome_1", "decoration", "stronghold", "mineshaft", "dungeon", "lake", "lava_lake" }), new avl[] { new avl(1, aju.c), new avl(3, aju.d), new avl(59, aju.b), new avl(1, aju.h) });
    
    a("Snowy Kingdom", ado.a(aju.aH), ail.n, Arrays.asList(new String[] { "village", "biome_1" }), new avl[] { new avl(1, aju.aH), new avl(1, aju.c), new avl(3, aju.d), new avl(59, aju.b), new avl(1, aju.h) });
    
    a("Bottomless Pit", ads.I, ail.c, Arrays.asList(new String[] { "village", "biome_1" }), new avl[] { new avl(1, aju.c), new avl(3, aju.d), new avl(2, aju.e) });
    
    a("Desert", ado.a(aju.m), ail.d, Arrays.asList(new String[] { "village", "biome_1", "decoration", "stronghold", "mineshaft", "dungeon" }), new avl[] { new avl(8, aju.m), new avl(52, aju.A), new avl(3, aju.b), new avl(1, aju.h) });
    
    a("Redstone Ready", ads.aE, ail.d, new avl[] { new avl(52, aju.A), new avl(3, aju.b), new avl(1, aju.h) });
    
    a("The Void", ado.a(aju.cv), ail.P, Arrays.asList(new String[] { "decoration" }), new avl[] { new avl(1, aju.a) });
  }
  
  public bey(bej ☃)
  {
    this.f = ☃;
  }
  
  public void b()
  {
    this.n.clear();
    Keyboard.enableRepeatEvents(true);
    
    this.g = bwo.a("createWorld.customize.presets.title", new Object[0]);
    this.h = bwo.a("createWorld.customize.presets.share", new Object[0]);
    this.i = bwo.a("createWorld.customize.presets.list", new Object[0]);
    this.t = new bdd(2, this.q, 50, 40, this.l - 100, 20);
    this.r = new bey.b();
    
    this.t.f(1230);
    this.t.a(this.f.a());
    
    this.n.add(this.s = new bcz(0, this.l / 2 - 155, this.m - 28, 150, 20, bwo.a("createWorld.customize.presets.select", new Object[0])));
    this.n.add(new bcz(1, this.l / 2 + 5, this.m - 28, 150, 20, bwo.a("gui.cancel", new Object[0])));
    
    a();
  }
  
  public void k()
  {
    super.k();
    this.r.p();
  }
  
  public void m()
  {
    Keyboard.enableRepeatEvents(false);
  }
  
  protected void a(int ☃, int ☃, int ☃)
  {
    this.t.a(☃, ☃, ☃);
    super.a(☃, ☃, ☃);
  }
  
  protected void a(char ☃, int ☃)
  {
    if (!this.t.a(☃, ☃)) {
      super.a(☃, ☃);
    }
  }
  
  protected void a(bcz ☃)
  {
    if ((☃.k == 0) && (g()))
    {
      this.f.a(this.t.b());
      this.j.a(this.f);
    }
    else if (☃.k == 1)
    {
      this.j.a(this.f);
    }
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    c();
    
    this.r.a(☃, ☃, ☃);
    a(this.q, this.g, this.l / 2, 8, 16777215);
    c(this.q, this.h, 50, 30, 10526880);
    c(this.q, this.i, 50, 70, 10526880);
    
    this.t.g();
    super.a(☃, ☃, ☃);
  }
  
  public void e()
  {
    this.t.a();
    super.e();
  }
  
  public void a()
  {
    boolean ☃ = g();
    this.s.l = ☃;
  }
  
  private boolean g()
  {
    return ((this.r.u > -1) && (this.r.u < a.size())) || (this.t.b().length() > 1);
  }
  
  class b
    extends bdq
  {
    public int u = -1;
    
    public b()
    {
      super(bey.this.l, bey.this.m, 80, bey.this.m - 37, 24);
    }
    
    private void a(int ☃, int ☃, ado ☃, int ☃)
    {
      e(☃ + 1, ☃ + 1);
      
      bni.D();
      
      bcd.c();
      
      bey.this.k.a(new adq(☃, 1, ☃), ☃ + 2, ☃ + 2);
      bcd.a();
      
      bni.E();
    }
    
    private void e(int ☃, int ☃)
    {
      d(☃, ☃, 0, 0);
    }
    
    private void d(int ☃, int ☃, int ☃, int ☃)
    {
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      this.a.N().a(bcv.c);
      
      float ☃ = 0.0078125F;
      float ☃ = 0.0078125F;
      int ☃ = 18;
      int ☃ = 18;
      bnu ☃ = bnu.a();
      bmz ☃ = ☃.c();
      ☃.a(7, bvp.g);
      ☃.b(☃ + 0, ☃ + 18, bey.a(bey.this)).a((☃ + 0) * 0.0078125F, (☃ + 18) * 0.0078125F).d();
      ☃.b(☃ + 18, ☃ + 18, bey.b(bey.this)).a((☃ + 18) * 0.0078125F, (☃ + 18) * 0.0078125F).d();
      ☃.b(☃ + 18, ☃ + 0, bey.c(bey.this)).a((☃ + 18) * 0.0078125F, (☃ + 0) * 0.0078125F).d();
      ☃.b(☃ + 0, ☃ + 0, bey.d(bey.this)).a((☃ + 0) * 0.0078125F, (☃ + 0) * 0.0078125F).d();
      ☃.b();
    }
    
    protected int b()
    {
      return bey.f().size();
    }
    
    protected void a(int ☃, boolean ☃, int ☃, int ☃)
    {
      this.u = ☃;
      bey.this.a();
      bey.f(bey.this).a(((bey.a)bey.f().get(bey.e(bey.this).u)).d);
    }
    
    protected boolean a(int ☃)
    {
      return ☃ == this.u;
    }
    
    protected void a() {}
    
    protected void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
    {
      bey.a ☃ = (bey.a)bey.f().get(☃);
      a(☃, ☃, ☃.a, ☃.b);
      bey.this.q.a(☃.c, ☃ + 18 + 5, ☃ + 6, 16777215);
    }
  }
  
  private static void a(String ☃, ado ☃, aig ☃, avl... ☃)
  {
    a(☃, ☃, 0, ☃, null, ☃);
  }
  
  private static void a(String ☃, ado ☃, aig ☃, List<String> ☃, avl... ☃)
  {
    a(☃, ☃, 0, ☃, ☃, ☃);
  }
  
  private static void a(String ☃, ado ☃, int ☃, aig ☃, List<String> ☃, avl... ☃)
  {
    avk ☃ = new avk();
    for (int ☃ = ☃.length - 1; ☃ >= 0; ☃--) {
      ☃.c().add(☃[☃]);
    }
    ☃.a(aig.a(☃));
    ☃.d();
    if (☃ != null) {
      for (String ☃ : ☃) {
        ☃.b().put(☃, Maps.newHashMap());
      }
    }
    a.add(new bey.a(☃, ☃, ☃, ☃.toString()));
  }
  
  static class a
  {
    public ado a;
    public int b;
    public String c;
    public String d;
    
    public a(ado ☃, int ☃, String ☃, String ☃)
    {
      this.a = ☃;
      this.b = ☃;
      this.c = ☃;
      this.d = ☃;
    }
  }
}
