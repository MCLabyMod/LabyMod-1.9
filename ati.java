import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ati
  implements ary
{
  private final aht a;
  private final Random b;
  private final arc[] c = new arc['Ā'];
  private final avk d;
  private final List<awd> e = Lists.newArrayList();
  private final boolean f;
  private final boolean g;
  private aum h;
  private aum i;
  
  public ati(aht ☃, long ☃, boolean ☃, String ☃)
  {
    this.a = ☃;
    this.b = new Random(☃);
    this.d = avk.a(☃);
    if (☃)
    {
      Map<String, Map<String, String>> ☃ = this.d.b();
      if (☃.containsKey("village"))
      {
        Map<String, String> ☃ = (Map)☃.get("village");
        if (!☃.containsKey("size")) {
          ☃.put("size", "1");
        }
        this.e.add(new awj(☃));
      }
      if (☃.containsKey("biome_1")) {
        this.e.add(new avz((Map)☃.get("biome_1")));
      }
      if (☃.containsKey("mineshaft")) {
        this.e.add(new avs((Map)☃.get("mineshaft")));
      }
      if (☃.containsKey("stronghold")) {
        this.e.add(new awb((Map)☃.get("stronghold")));
      }
      if (☃.containsKey("oceanmonument")) {
        this.e.add(new avx((Map)☃.get("oceanmonument")));
      }
    }
    if (this.d.b().containsKey("lake")) {
      this.h = new aum(aju.j);
    }
    if (this.d.b().containsKey("lava_lake")) {
      this.i = new aum(aju.l);
    }
    this.g = this.d.b().containsKey("dungeon");
    
    int ☃ = 0;
    int ☃ = 0;
    boolean ☃ = true;
    for (avl ☃ : this.d.c())
    {
      for (int ☃ = ☃.d(); ☃ < ☃.d() + ☃.b(); ☃++)
      {
        arc ☃ = ☃.c();
        if (☃.t() != aju.a)
        {
          ☃ = false;
          this.c[☃] = ☃;
        }
      }
      if (☃.c().t() == aju.a)
      {
        ☃ += ☃.b();
      }
      else
      {
        ☃ += ☃.b() + ☃;
        ☃ = 0;
      }
    }
    ☃.b(☃);
    
    this.f = ((☃) && (this.d.a() != aig.a(ail.P)) ? false : this.d.b().containsKey("decoration"));
  }
  
  public ase a(int ☃, int ☃)
  {
    atf ☃ = new atf();
    for (int ☃ = 0; ☃ < this.c.length; ☃++)
    {
      arc ☃ = this.c[☃];
      if (☃ != null) {
        for (int ☃ = 0; ☃ < 16; ☃++) {
          for (int ☃ = 0; ☃ < 16; ☃++) {
            ☃.a(☃, ☃, ☃, ☃);
          }
        }
      }
    }
    for (atk ☃ : this.e) {
      ☃.a(this.a, ☃, ☃, ☃);
    }
    ase ☃ = new ase(this.a, ☃, ☃, ☃);
    aig[] ☃ = this.a.A().b(null, ☃ * 16, ☃ * 16, 16, 16);
    byte[] ☃ = ☃.l();
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      ☃[☃] = ((byte)aig.a(☃[☃]));
    }
    ☃.b();
    
    return ☃;
  }
  
  public void b(int ☃, int ☃)
  {
    int ☃ = ☃ * 16;
    int ☃ = ☃ * 16;
    cj ☃ = new cj(☃, 0, ☃);
    aig ☃ = this.a.b(new cj(☃ + 16, 0, ☃ + 16));
    boolean ☃ = false;
    
    this.b.setSeed(this.a.O());
    long ☃ = this.b.nextLong() / 2L * 2L + 1L;
    long ☃ = this.b.nextLong() / 2L * 2L + 1L;
    this.b.setSeed(☃ * ☃ + ☃ * ☃ ^ this.a.O());
    
    ahn ☃ = new ahn(☃, ☃);
    for (awd ☃ : this.e)
    {
      boolean ☃ = ☃.a(this.a, this.b, ☃);
      if ((☃ instanceof awj)) {
        ☃ |= ☃;
      }
    }
    if ((this.h != null) && (!☃) && (this.b.nextInt(4) == 0)) {
      this.h.b(this.a, this.b, ☃.a(this.b.nextInt(16) + 8, this.b.nextInt(256), this.b.nextInt(16) + 8));
    }
    if ((this.i != null) && (!☃) && (this.b.nextInt(8) == 0))
    {
      cj ☃ = ☃.a(this.b.nextInt(16) + 8, this.b.nextInt(this.b.nextInt(248) + 8), this.b.nextInt(16) + 8);
      if ((☃.q() < this.a.K()) || (this.b.nextInt(10) == 0)) {
        this.i.b(this.a, this.b, ☃);
      }
    }
    if (this.g) {
      for (int ☃ = 0; ☃ < 8; ☃++) {
        new aus().b(this.a, this.b, ☃.a(this.b.nextInt(16) + 8, this.b.nextInt(256), this.b.nextInt(16) + 8));
      }
    }
    if (this.f) {
      ☃.a(this.a, this.b, ☃);
    }
  }
  
  public boolean a(ase ☃, int ☃, int ☃)
  {
    return false;
  }
  
  public List<aig.c> a(sc ☃, cj ☃)
  {
    aig ☃ = this.a.b(☃);
    return ☃.a(☃);
  }
  
  public cj a(aht ☃, String ☃, cj ☃)
  {
    if ("Stronghold".equals(☃)) {
      for (awd ☃ : this.e) {
        if ((☃ instanceof awb)) {
          return ☃.a(☃, ☃);
        }
      }
    }
    return null;
  }
  
  public void b(ase ☃, int ☃, int ☃)
  {
    for (awd ☃ : this.e) {
      ☃.a(this.a, ☃, ☃, null);
    }
  }
}
