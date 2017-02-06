import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import net.minecraft.client.ClientBrandRetriever;
import org.lwjgl.opengl.Display;

public class bdc
  extends bcv
{
  private final bcf a;
  private final bct f;
  
  public bdc(bcf mc)
  {
    this.a = mc;
    this.f = mc.k;
  }
  
  public void a(bcx scaledResolutionIn)
  {
    this.a.B.a("debug");
    bni.G();
    a();
    b(scaledResolutionIn);
    bni.H();
    
    this.a.B.b();
  }
  
  private boolean d()
  {
    return (this.a.h.cX()) || (this.a.u.v);
  }
  
  protected void a()
  {
    List<String> list = b();
    list.add("");
    list.add("Debug: Pie [shift]: " + (this.a.u.ar ? "visible" : "hidden") + " FPS [alt]: " + (this.a.u.as ? "visible" : "hidden"));
    list.add("For help: press F3 + Q");
    for (int i = 0; i < list.size(); i++)
    {
      String s = (String)list.get(i);
      if (!Strings.isNullOrEmpty(s))
      {
        int j = this.f.a;
        int k = this.f.a(s);
        int l = 2;
        int i1 = 2 + j * i;
        a(1, i1 - 1, 2 + k + 1, i1 + j - 1, -1873784752);
        this.f.a(s, 2, i1, 14737632);
      }
    }
  }
  
  protected void b(bcx scaledRes)
  {
    List<String> list = c();
    for (int i = 0; i < list.size(); i++)
    {
      String s = (String)list.get(i);
      if (!Strings.isNullOrEmpty(s))
      {
        int j = this.f.a;
        int k = this.f.a(s);
        int l = scaledRes.a() - 2 - k;
        int i1 = 2 + j * i;
        a(l - 1, i1 - 1, l + k + 1, i1 + j - 1, -1873784752);
        this.f.a(s, l, i1, 14737632);
      }
    }
  }
  
  protected List<String> b()
  {
    cj blockpos = new cj(this.a.aa().p, this.a.aa().bl().b, this.a.aa().r);
    if (d()) {
      return Lists.newArrayList(new String[] { "Minecraft 1.9 (" + this.a.c() + "/" + ClientBrandRetriever.getClientModName() + ")", this.a.D, this.a.g.f(), this.a.g.h(), "P: " + this.a.j.b() + ". T: " + this.a.f.F(), this.a.f.G(), "", String.format("Chunk-relative: %d %d %d", new Object[] { Integer.valueOf(blockpos.p() & 0xF), Integer.valueOf(blockpos.q() & 0xF), Integer.valueOf(blockpos.r() & 0xF) }) });
    }
    rr entity = this.a.aa();
    cq enumfacing = entity.bi();
    String s = "Invalid";
    switch (enumfacing)
    {
    case c: 
      s = "Towards negative Z";
      break;
    case d: 
      s = "Towards positive Z";
      break;
    case e: 
      s = "Towards negative X";
      break;
    case f: 
      s = "Towards positive X";
    }
    List<String> list = Lists.newArrayList(new String[] { "Minecraft 1.9 (" + this.a.c() + "/" + ClientBrandRetriever.getClientModName() + ("release".equalsIgnoreCase(this.a.d()) ? "" : new StringBuilder().append("/").append(this.a.d()).toString()) + ")", this.a.D, this.a.g.f(), this.a.g.h(), "P: " + this.a.j.b() + ". T: " + this.a.f.F(), this.a.f.G(), "", String.format("XYZ: %.3f / %.5f / %.3f", new Object[] { Double.valueOf(this.a.aa().p), Double.valueOf(this.a.aa().bl().b), Double.valueOf(this.a.aa().r) }), String.format("Block: %d %d %d", new Object[] { Integer.valueOf(blockpos.p()), Integer.valueOf(blockpos.q()), Integer.valueOf(blockpos.r()) }), String.format("Chunk: %d %d %d in %d %d %d", new Object[] { Integer.valueOf(blockpos.p() & 0xF), Integer.valueOf(blockpos.q() & 0xF), Integer.valueOf(blockpos.r() & 0xF), Integer.valueOf(blockpos.p() >> 4), Integer.valueOf(blockpos.q() >> 4), Integer.valueOf(blockpos.r() >> 4) }), String.format("Facing: %s (%s) (%.1f / %.1f)", new Object[] { enumfacing, s, Float.valueOf(on.g(entity.v)), Float.valueOf(on.g(entity.w)) }) });
    if (this.a.f != null)
    {
      ase chunk = this.a.f.f(blockpos);
      if (!this.a.f.e(blockpos))
      {
        list.add("Outside of world...");
      }
      else if (!chunk.f())
      {
        list.add("Biome: " + chunk.a(blockpos, this.a.f.A()).l());
        list.add("Light: " + chunk.a(blockpos, 0) + " (" + chunk.a(ahz.a, blockpos) + " sky, " + chunk.a(ahz.b, blockpos) + " block)");
        ql difficultyinstance = this.a.f.D(blockpos);
        if ((this.a.D()) && (this.a.F() != null))
        {
          lr entityplayermp = this.a.F().al().a(this.a.h.bc());
          if (entityplayermp != null) {
            difficultyinstance = entityplayermp.l.D(new cj(entityplayermp));
          }
        }
        list.add(String.format("Local Difficulty: %.2f // %.2f (Day %d)", new Object[] { Float.valueOf(difficultyinstance.b()), Float.valueOf(difficultyinstance.c()), Long.valueOf(this.a.f.Q() / 24000L) }));
      }
      else
      {
        list.add("Waiting for chunk...");
      }
    }
    if ((this.a.o != null) && (this.a.o.a())) {
      list.add("Shader: " + this.a.o.f().b());
    }
    if ((this.a.t != null) && (this.a.t.a == bbi.a.b) && (this.a.t.a() != null))
    {
      cj blockpos1 = this.a.t.a();
      list.add(String.format("Looking at: %d %d %d", new Object[] { Integer.valueOf(blockpos1.p()), Integer.valueOf(blockpos1.q()), Integer.valueOf(blockpos1.r()) }));
    }
    return list;
  }
  
  protected List<String> c()
  {
    long i = Runtime.getRuntime().maxMemory();
    long j = Runtime.getRuntime().totalMemory();
    long k = Runtime.getRuntime().freeMemory();
    long l = j - k;
    List<String> list = Lists.newArrayList(new String[] { String.format("Java: %s %dbit", new Object[] { System.getProperty("java.version"), Integer.valueOf(this.a.S() ? 64 : 32) }), String.format("Mem: % 2d%% %03d/%03dMB", new Object[] { Long.valueOf(l * 100L / i), Long.valueOf(a(l)), Long.valueOf(a(i)) }), String.format("Allocated: % 2d%% %03dMB", new Object[] { Long.valueOf(j * 100L / i), Long.valueOf(a(j)) }), "", String.format("CPU: %s", new Object[] { bzg.k() }), "", String.format("Display: %dx%d (%s)", new Object[] { Integer.valueOf(Display.getWidth()), Integer.valueOf(Display.getHeight()), bni.u(7936) }), bni.u(7937), bni.u(7938) });
    if (Reflector.FMLCommonHandler_getBrandings.exists())
    {
      Object instance = Reflector.call(Reflector.FMLCommonHandler_instance, new Object[0]);
      list.add("");
      list.addAll((Collection)Reflector.call(instance, Reflector.FMLCommonHandler_getBrandings, new Object[] { Boolean.valueOf(false) }));
    }
    if (d()) {
      return list;
    }
    if ((this.a.t != null) && (this.a.t.a == bbi.a.b) && (this.a.t.a() != null))
    {
      cj blockpos = this.a.t.a();
      arc iblockstate = this.a.f.o(blockpos);
      if (this.a.f.L() != ahy.g) {
        iblockstate = iblockstate.b(this.a.f, blockpos);
      }
      list.add("");
      list.add(String.valueOf(ajt.h.b(iblockstate.t())));
      for (Map.Entry<arr<?>, Comparable<?>> entry : iblockstate.s().entrySet())
      {
        arr iproperty = (arr)entry.getKey();
        Comparable t = (Comparable)entry.getValue();
        String s = iproperty.a(t);
        if (t == Boolean.TRUE) {
          s = a.k + s;
        } else if (t == Boolean.FALSE) {
          s = a.m + s;
        }
        list.add(iproperty.a() + ": " + s);
      }
    }
    return list;
  }
  
  private void e()
  {
    bni.j();
    oc frametimer = this.a.ag();
    int i = frametimer.a();
    int j = frametimer.b();
    long[] along = frametimer.c();
    bcx scaledresolution = new bcx(this.a);
    int k = i;
    int l = 0;
    a(0, scaledresolution.b() - 60, 240, scaledresolution.b(), -1873784752);
    while (k != j)
    {
      int i1 = frametimer.a(along[k], 30);
      int j1 = c(on.a(i1, 0, 60), 0, 30, 60);
      b(l, scaledresolution.b(), scaledresolution.b() - i1, j1);
      l++;
      k = frametimer.b(k + 1);
    }
    a(1, scaledresolution.b() - 30 + 1, 14, scaledresolution.b() - 30 + 10, -1873784752);
    this.f.a("60", 2, scaledresolution.b() - 30 + 2, 14737632);
    a(0, 239, scaledresolution.b() - 30, -1);
    a(1, scaledresolution.b() - 60 + 1, 14, scaledresolution.b() - 60 + 10, -1873784752);
    this.f.a("30", 2, scaledresolution.b() - 60 + 2, 14737632);
    a(0, 239, scaledresolution.b() - 60, -1);
    a(0, 239, scaledresolution.b() - 1, -1);
    b(0, scaledresolution.b() - 60, scaledresolution.b(), -1);
    b(239, scaledresolution.b() - 60, scaledresolution.b(), -1);
    if (this.a.u.g <= 120) {
      a(0, 239, scaledresolution.b() - 60 + this.a.u.g / 2, -16711681);
    }
    bni.k();
  }
  
  private int c(int p_181552_1_, int p_181552_2_, int p_181552_3_, int p_181552_4_)
  {
    return p_181552_1_ < p_181552_3_ ? a(-16711936, 65280, p_181552_1_ / p_181552_3_) : a(65280, -65536, (p_181552_1_ - p_181552_3_) / (p_181552_4_ - p_181552_3_));
  }
  
  private int a(int p_181553_1_, int p_181553_2_, float p_181553_3_)
  {
    int i = p_181553_1_ >> 24 & 0xFF;
    int j = p_181553_1_ >> 16 & 0xFF;
    int k = p_181553_1_ >> 8 & 0xFF;
    int l = p_181553_1_ & 0xFF;
    int i1 = p_181553_2_ >> 24 & 0xFF;
    int j1 = p_181553_2_ >> 16 & 0xFF;
    int k1 = p_181553_2_ >> 8 & 0xFF;
    int l1 = p_181553_2_ & 0xFF;
    int i2 = on.a((int)(i + (i1 - i) * p_181553_3_), 0, 255);
    int j2 = on.a((int)(j + (j1 - j) * p_181553_3_), 0, 255);
    int k2 = on.a((int)(k + (k1 - k) * p_181553_3_), 0, 255);
    int l2 = on.a((int)(l + (l1 - l) * p_181553_3_), 0, 255);
    return i2 << 24 | j2 << 16 | k2 << 8 | l2;
  }
  
  private static long a(long bytes)
  {
    return bytes / 1024L / 1024L;
  }
}
