import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bxr
{
  private static final Set<kk> b = Sets.newHashSet(new kk[] { new kk("blocks/water_flow"), new kk("blocks/water_still"), new kk("blocks/lava_flow"), new kk("blocks/lava_still"), new kk("blocks/water_overlay"), new kk("blocks/destroy_stage_0"), new kk("blocks/destroy_stage_1"), new kk("blocks/destroy_stage_2"), new kk("blocks/destroy_stage_3"), new kk("blocks/destroy_stage_4"), new kk("blocks/destroy_stage_5"), new kk("blocks/destroy_stage_6"), new kk("blocks/destroy_stage_7"), new kk("blocks/destroy_stage_8"), new kk("blocks/destroy_stage_9"), new kk("items/empty_armor_slot_helmet"), new kk("items/empty_armor_slot_chestplate"), new kk("items/empty_armor_slot_leggings"), new kk("items/empty_armor_slot_boots"), new kk("items/empty_armor_slot_shield") });
  private static final Logger c = LogManager.getLogger();
  protected static final bxt a = new bxt("builtin/missing", "missing");
  private static final String d = "{    'textures': {       'particle': 'missingno',       'missingno': 'missingno'    },    'elements': [         {  'from': [ 0, 0, 0 ],            'to': [ 16, 16, 16 ],            'faces': {                'down':  { 'uv': [ 0, 0, 16, 16 ], 'cullface': 'down',  'texture': '#missingno' },                'up':    { 'uv': [ 0, 0, 16, 16 ], 'cullface': 'up',    'texture': '#missingno' },                'north': { 'uv': [ 0, 0, 16, 16 ], 'cullface': 'north', 'texture': '#missingno' },                'south': { 'uv': [ 0, 0, 16, 16 ], 'cullface': 'south', 'texture': '#missingno' },                'west':  { 'uv': [ 0, 0, 16, 16 ], 'cullface': 'west',  'texture': '#missingno' },                'east':  { 'uv': [ 0, 0, 16, 16 ], 'cullface': 'east',  'texture': '#missingno' }            }        }    ]}".replaceAll("'", "\"");
  private static final Map<String, String> e = Maps.newHashMap();
  private static final Joiner f;
  private final bwg g;
  private final Map<kk, bvh> h = Maps.newHashMap();
  private final Map<kk, bok> i = Maps.newLinkedHashMap();
  private final Map<bxt, bot> j = Maps.newLinkedHashMap();
  private final Map<bol, Collection<bxt>> k = Maps.newLinkedHashMap();
  private final bvg l;
  private final bob m;
  private final bon n = new bon();
  private final boo o = new boo();
  private dd<bxt, bxo> p = new dd();
  private static final String q;
  private static final bok r;
  private static final bok s;
  
  static
  {
    e.put("missing", d);
    
    f = Joiner.on(" -> ");
    
    q = "{    'elements': [        {   'from': [0, 0, 0],            'to': [16, 16, 16],            'faces': {                'down': {'uv': [0, 0, 16, 16], 'texture': '' }            }        }    ]}".replaceAll("'", "\"");
    
    r = bok.a(q);
    s = bok.a(q);
    
    r.b = "generation marker";
    s.b = "block entity marker";
  }
  
  private Map<String, kk> t = Maps.newLinkedHashMap();
  private final Map<kk, bol> u = Maps.newHashMap();
  private Map<ado, List<String>> v = Maps.newIdentityHashMap();
  
  public bxr(bwg ☃, bvg ☃, bob ☃)
  {
    this.g = ☃;
    this.l = ☃;
    this.m = ☃;
  }
  
  public db<bxt, bxo> a()
  {
    b();
    
    c();
    
    k();
    
    m();
    
    o();
    
    h();
    
    i();
    
    return this.p;
  }
  
  private void b()
  {
    bpf ☃ = this.m.a();
    for (Iterator ☃ = ajt.h.iterator(); ☃.hasNext();)
    {
      ☃ = (ajt)☃.next();
      for (final kk ☃ : ☃.a(☃)) {
        try
        {
          ☃ = a(☃);
          
          Map<arc, bxt> ☃ = ☃.b(☃);
          if (☃.b())
          {
            Collection<bxt> ☃ = Sets.newHashSet(☃.values());
            ☃.c().a(☃.t());
            
            this.k.put(☃, Lists.newArrayList(Iterables.filter(☃, new Predicate()
            {
              public boolean a(bxt ☃)
              {
                return ☃.equals(☃);
              }
            })));
          }
          for (Map.Entry<arc, bxt> ☃ : ☃.entrySet())
          {
            bxt ☃ = (bxt)☃.getValue();
            if (☃.equals(☃)) {
              try
              {
                this.j.put(☃, ☃.c(☃.c()));
              }
              catch (RuntimeException ☃)
              {
                if (!☃.b()) {
                  c.warn("Unable to load variant: " + ☃.c() + " from " + ☃);
                }
              }
            }
          }
        }
        catch (Exception ☃)
        {
          bol ☃;
          c.warn("Unable to load definition " + ☃, ☃);
        }
      }
    }
    ajt ☃;
  }
  
  private void c()
  {
    this.j.put(a, new bot(Lists.newArrayList(new bou[] { new bou(new kk(a.a()), bxp.a, false, 1) })));
    
    kk ☃ = new kk("item_frame");
    bol ☃ = a(☃);
    a(☃, new bxt(☃, "normal"));
    a(☃, new bxt(☃, "map"));
    
    d();
    e();
    f();
  }
  
  private void a(bol ☃, bxt ☃)
  {
    this.j.put(☃, ☃.c(☃.c()));
  }
  
  private bol a(kk ☃)
  {
    kk ☃ = b(☃);
    
    bol ☃ = (bol)this.u.get(☃);
    if (☃ == null)
    {
      ☃ = a(☃, ☃);
      this.u.put(☃, ☃);
    }
    return ☃;
  }
  
  private bol a(kk ☃, kk ☃)
  {
    List<bol> ☃ = Lists.newArrayList();
    try
    {
      for (bwf ☃ : this.g.b(☃)) {
        ☃.add(a(☃, ☃));
      }
    }
    catch (IOException ☃)
    {
      throw new RuntimeException("Encountered an exception when loading model definition of model " + ☃.toString(), ☃);
    }
    return new bol(☃);
  }
  
  private bol a(kk ☃, bwf ☃)
  {
    InputStream ☃ = null;
    try
    {
      ☃ = ☃.b();
      return bol.a(new InputStreamReader(☃, Charsets.UTF_8));
    }
    catch (Exception ☃)
    {
      throw new RuntimeException("Encountered an exception when loading model definition of '" + ☃ + "' from: '" + ☃.a() + "' in resourcepack: '" + ☃.d() + "'", ☃);
    }
    finally
    {
      IOUtils.closeQuietly(☃);
    }
  }
  
  private kk b(kk ☃)
  {
    return new kk(☃.b(), "blockstates/" + ☃.a() + ".json");
  }
  
  private void d()
  {
    for (Map.Entry<bxt, bot> ☃ : this.j.entrySet()) {
      a((bxt)☃.getKey(), (bot)☃.getValue());
    }
  }
  
  private void e()
  {
    for (Map.Entry<bol, Collection<bxt>> ☃ : this.k.entrySet())
    {
      ☃ = (bxt)((Collection)☃.getValue()).iterator().next();
      for (bot ☃ : ((bol)☃.getKey()).a()) {
        a(☃, ☃);
      }
    }
    bxt ☃;
  }
  
  private void a(bxt ☃, bot ☃)
  {
    for (bou ☃ : ☃.a())
    {
      kk ☃ = ☃.a();
      if (this.i.get(☃) == null) {
        try
        {
          this.i.put(☃, c(☃));
        }
        catch (Exception ☃)
        {
          c.warn("Unable to load block model: '{}' for variant: '{}': {} ", new Object[] { ☃, ☃, ☃ });
        }
      }
    }
  }
  
  private bok c(kk ☃)
    throws IOException
  {
    Reader ☃ = null;
    bwf ☃ = null;
    try
    {
      String ☃ = ☃.a();
      bok localbok1;
      if ("builtin/generated".equals(☃)) {
        return r;
      }
      if ("builtin/entity".equals(☃)) {
        return s;
      }
      String ☃;
      if (☃.startsWith("builtin/"))
      {
        String ☃ = ☃.substring("builtin/".length());
        ☃ = (String)e.get(☃);
        if (☃ == null) {
          throw new FileNotFoundException(☃.toString());
        }
        ☃ = new StringReader(☃);
      }
      else
      {
        ☃ = this.g.a(d(☃));
        ☃ = new InputStreamReader(☃.b(), Charsets.UTF_8);
      }
      bok ☃ = bok.a(☃);
      ☃.b = ☃.toString();
      return ☃;
    }
    finally
    {
      IOUtils.closeQuietly(☃);
      IOUtils.closeQuietly(☃);
    }
  }
  
  private kk d(kk ☃)
  {
    return new kk(☃.b(), "models/" + ☃.a() + ".json");
  }
  
  private void f()
  {
    g();
    for (Iterator ☃ = ado.f.iterator(); ☃.hasNext();)
    {
      ☃ = (ado)☃.next();
      List<String> ☃ = a(☃);
      for (String ☃ : ☃)
      {
        kk ☃ = a(☃);
        
        ☃ = (kk)ado.f.b(☃);
        
        a(☃, ☃, ☃);
        if (☃.i())
        {
          bok ☃ = (bok)this.i.get(☃);
          if (☃ != null) {
            for (kk ☃ : ☃.e()) {
              a(☃.toString(), ☃, ☃);
            }
          }
        }
      }
    }
    ado ☃;
    kk ☃;
  }
  
  private void a(String ☃, kk ☃, kk ☃)
  {
    this.t.put(☃, ☃);
    if (this.i.get(☃) != null) {
      return;
    }
    try
    {
      bok ☃ = c(☃);
      this.i.put(☃, ☃);
    }
    catch (Exception ☃)
    {
      c.warn("Unable to load item model: '" + ☃ + "' for item: '" + ☃ + "'", ☃);
    }
  }
  
  private void g()
  {
    this.v.put(ado.a(aju.b), Lists.newArrayList(new String[] { "stone", "granite", "granite_smooth", "diorite", "diorite_smooth", "andesite", "andesite_smooth" }));
    this.v.put(ado.a(aju.d), Lists.newArrayList(new String[] { "dirt", "coarse_dirt", "podzol" }));
    this.v.put(ado.a(aju.f), Lists.newArrayList(new String[] { "oak_planks", "spruce_planks", "birch_planks", "jungle_planks", "acacia_planks", "dark_oak_planks" }));
    this.v.put(ado.a(aju.g), Lists.newArrayList(new String[] { "oak_sapling", "spruce_sapling", "birch_sapling", "jungle_sapling", "acacia_sapling", "dark_oak_sapling" }));
    this.v.put(ado.a(aju.m), Lists.newArrayList(new String[] { "sand", "red_sand" }));
    this.v.put(ado.a(aju.r), Lists.newArrayList(new String[] { "oak_log", "spruce_log", "birch_log", "jungle_log" }));
    this.v.put(ado.a(aju.t), Lists.newArrayList(new String[] { "oak_leaves", "spruce_leaves", "birch_leaves", "jungle_leaves" }));
    this.v.put(ado.a(aju.v), Lists.newArrayList(new String[] { "sponge", "sponge_wet" }));
    this.v.put(ado.a(aju.A), Lists.newArrayList(new String[] { "sandstone", "chiseled_sandstone", "smooth_sandstone" }));
    this.v.put(ado.a(aju.cM), Lists.newArrayList(new String[] { "red_sandstone", "chiseled_red_sandstone", "smooth_red_sandstone" }));
    this.v.put(ado.a(aju.H), Lists.newArrayList(new String[] { "dead_bush", "tall_grass", "fern" }));
    this.v.put(ado.a(aju.I), Lists.newArrayList(new String[] { "dead_bush" }));
    this.v.put(ado.a(aju.L), Lists.newArrayList(new String[] { "black_wool", "red_wool", "green_wool", "brown_wool", "blue_wool", "purple_wool", "cyan_wool", "silver_wool", "gray_wool", "pink_wool", "lime_wool", "yellow_wool", "light_blue_wool", "magenta_wool", "orange_wool", "white_wool" }));
    this.v.put(ado.a(aju.N), Lists.newArrayList(new String[] { "dandelion" }));
    this.v.put(ado.a(aju.O), Lists.newArrayList(new String[] { "poppy", "blue_orchid", "allium", "houstonia", "red_tulip", "orange_tulip", "white_tulip", "pink_tulip", "oxeye_daisy" }));
    this.v.put(ado.a(aju.U), Lists.newArrayList(new String[] { "stone_slab", "sandstone_slab", "cobblestone_slab", "brick_slab", "stone_brick_slab", "nether_brick_slab", "quartz_slab" }));
    this.v.put(ado.a(aju.cP), Lists.newArrayList(new String[] { "red_sandstone_slab" }));
    this.v.put(ado.a(aju.cG), Lists.newArrayList(new String[] { "black_stained_glass", "red_stained_glass", "green_stained_glass", "brown_stained_glass", "blue_stained_glass", "purple_stained_glass", "cyan_stained_glass", "silver_stained_glass", "gray_stained_glass", "pink_stained_glass", "lime_stained_glass", "yellow_stained_glass", "light_blue_stained_glass", "magenta_stained_glass", "orange_stained_glass", "white_stained_glass" }));
    this.v.put(ado.a(aju.be), Lists.newArrayList(new String[] { "stone_monster_egg", "cobblestone_monster_egg", "stone_brick_monster_egg", "mossy_brick_monster_egg", "cracked_brick_monster_egg", "chiseled_brick_monster_egg" }));
    this.v.put(ado.a(aju.bf), Lists.newArrayList(new String[] { "stonebrick", "mossy_stonebrick", "cracked_stonebrick", "chiseled_stonebrick" }));
    this.v.put(ado.a(aju.bM), Lists.newArrayList(new String[] { "oak_slab", "spruce_slab", "birch_slab", "jungle_slab", "acacia_slab", "dark_oak_slab" }));
    this.v.put(ado.a(aju.bZ), Lists.newArrayList(new String[] { "cobblestone_wall", "mossy_cobblestone_wall" }));
    this.v.put(ado.a(aju.cf), Lists.newArrayList(new String[] { "anvil_intact", "anvil_slightly_damaged", "anvil_very_damaged" }));
    this.v.put(ado.a(aju.cq), Lists.newArrayList(new String[] { "quartz_block", "chiseled_quartz_block", "quartz_column" }));
    this.v.put(ado.a(aju.cu), Lists.newArrayList(new String[] { "black_stained_hardened_clay", "red_stained_hardened_clay", "green_stained_hardened_clay", "brown_stained_hardened_clay", "blue_stained_hardened_clay", "purple_stained_hardened_clay", "cyan_stained_hardened_clay", "silver_stained_hardened_clay", "gray_stained_hardened_clay", "pink_stained_hardened_clay", "lime_stained_hardened_clay", "yellow_stained_hardened_clay", "light_blue_stained_hardened_clay", "magenta_stained_hardened_clay", "orange_stained_hardened_clay", "white_stained_hardened_clay" }));
    this.v.put(ado.a(aju.cH), Lists.newArrayList(new String[] { "black_stained_glass_pane", "red_stained_glass_pane", "green_stained_glass_pane", "brown_stained_glass_pane", "blue_stained_glass_pane", "purple_stained_glass_pane", "cyan_stained_glass_pane", "silver_stained_glass_pane", "gray_stained_glass_pane", "pink_stained_glass_pane", "lime_stained_glass_pane", "yellow_stained_glass_pane", "light_blue_stained_glass_pane", "magenta_stained_glass_pane", "orange_stained_glass_pane", "white_stained_glass_pane" }));
    this.v.put(ado.a(aju.u), Lists.newArrayList(new String[] { "acacia_leaves", "dark_oak_leaves" }));
    this.v.put(ado.a(aju.s), Lists.newArrayList(new String[] { "acacia_log", "dark_oak_log" }));
    this.v.put(ado.a(aju.cI), Lists.newArrayList(new String[] { "prismarine", "prismarine_bricks", "dark_prismarine" }));
    this.v.put(ado.a(aju.cy), Lists.newArrayList(new String[] { "black_carpet", "red_carpet", "green_carpet", "brown_carpet", "blue_carpet", "purple_carpet", "cyan_carpet", "silver_carpet", "gray_carpet", "pink_carpet", "lime_carpet", "yellow_carpet", "light_blue_carpet", "magenta_carpet", "orange_carpet", "white_carpet" }));
    this.v.put(ado.a(aju.cF), Lists.newArrayList(new String[] { "sunflower", "syringa", "double_grass", "double_fern", "double_rose", "paeonia" }));
    this.v.put(ads.j, Lists.newArrayList(new String[] { "coal", "charcoal" }));
    this.v.put(ads.bb, Lists.newArrayList(new String[] { "cod", "salmon", "clownfish", "pufferfish" }));
    this.v.put(ads.bc, Lists.newArrayList(new String[] { "cooked_cod", "cooked_salmon" }));
    this.v.put(ads.bd, Lists.newArrayList(new String[] { "dye_black", "dye_red", "dye_green", "dye_brown", "dye_blue", "dye_purple", "dye_cyan", "dye_silver", "dye_gray", "dye_pink", "dye_lime", "dye_yellow", "dye_light_blue", "dye_magenta", "dye_orange", "dye_white" }));
    this.v.put(ads.bG, Lists.newArrayList(new String[] { "bottle_drinkable" }));
    this.v.put(ads.ch, Lists.newArrayList(new String[] { "skull_skeleton", "skull_wither", "skull_zombie", "skull_char", "skull_creeper", "skull_dragon" }));
    this.v.put(ads.bH, Lists.newArrayList(new String[] { "bottle_splash" }));
    this.v.put(ads.bI, Lists.newArrayList(new String[] { "bottle_lingering" }));
    
    this.v.put(ado.a(aju.bo), Lists.newArrayList(new String[] { "oak_fence_gate" }));
    this.v.put(ado.a(aju.aO), Lists.newArrayList(new String[] { "oak_fence" }));
    this.v.put(ads.as, Lists.newArrayList(new String[] { "oak_door" }));
    this.v.put(ads.aG, Lists.newArrayList(new String[] { "oak_boat" }));
  }
  
  private List<String> a(ado ☃)
  {
    List<String> ☃ = (List)this.v.get(☃);
    if (☃ == null) {
      ☃ = Collections.singletonList(((kk)ado.f.b(☃)).toString());
    }
    return ☃;
  }
  
  private kk a(String ☃)
  {
    kk ☃ = new kk(☃);
    return new kk(☃.b(), "item/" + ☃.a());
  }
  
  private void h()
  {
    for (bxt ☃ : this.j.keySet())
    {
      bxo ☃ = a((bot)this.j.get(☃), ☃.toString());
      if (☃ != null) {
        this.p.a(☃, ☃);
      }
    }
    for (Map.Entry<bol, Collection<bxt>> ☃ : this.k.entrySet())
    {
      ☃ = (bol)☃.getKey();
      boy ☃ = ☃.c();
      
      String ☃ = ((kk)ajt.h.b(☃.c().c())).toString();
      
      bxu.a ☃ = new bxu.a();
      for (bpa ☃ : ☃.a())
      {
        bxo ☃ = a(☃.a(), "selector of " + ☃);
        if (☃ != null) {
          ☃.a(☃.a(☃.c()), ☃);
        }
      }
      ☃ = ☃.a();
      for (bxt ☃ : (Collection)☃.getValue()) {
        if (!☃.b(☃.c())) {
          this.p.a(☃, ☃);
        }
      }
    }
    bol ☃;
    bxo ☃;
  }
  
  private bxo a(bot ☃, String ☃)
  {
    if (☃.a().isEmpty()) {
      return null;
    }
    bxw.a ☃ = new bxw.a();
    
    int ☃ = 0;
    for (bou ☃ : ☃.a())
    {
      bok ☃ = (bok)this.i.get(☃.a());
      if ((☃ == null) || (!☃.d()))
      {
        c.warn("Missing model for: " + ☃);
      }
      else if (☃.a().isEmpty())
      {
        c.warn("Missing elements for: " + ☃);
      }
      else
      {
        bxo ☃ = a(☃, ☃.b(), ☃.c());
        if (☃ != null)
        {
          ☃++;
          ☃.a(☃, ☃.d());
        }
      }
    }
    bxo ☃ = null;
    if (☃ == 0) {
      c.warn("No weighted models for: " + ☃);
    } else if (☃ == 1) {
      ☃ = ☃.b();
    } else {
      ☃ = ☃.a();
    }
    return ☃;
  }
  
  private void i()
  {
    for (Map.Entry<String, kk> ☃ : this.t.entrySet())
    {
      kk ☃ = (kk)☃.getValue();
      bxt ☃ = new bxt((String)☃.getKey(), "inventory");
      bok ☃ = (bok)this.i.get(☃);
      if ((☃ == null) || (!☃.d()))
      {
        c.warn("Missing model for: " + ☃);
      }
      else if (☃.a().isEmpty())
      {
        c.warn("Missing elements for: " + ☃);
      }
      else if (c(☃))
      {
        this.p.a(☃, new bxq(☃.j(), ☃.g()));
      }
      else
      {
        bxo ☃ = a(☃, bxp.a, false);
        if (☃ != null) {
          this.p.a(☃, ☃);
        }
      }
    }
  }
  
  private Set<kk> j()
  {
    Set<kk> ☃ = Sets.newHashSet();
    
    List<bxt> ☃ = Lists.newArrayList(this.j.keySet());
    Collections.sort(☃, new Comparator()
    {
      public int a(bxt ☃, bxt ☃)
      {
        return ☃.toString().compareTo(☃.toString());
      }
    });
    for (Iterator ☃ = ☃.iterator(); ☃.hasNext();)
    {
      ☃ = (bxt)☃.next();
      bot ☃ = (bot)this.j.get(☃);
      for (bou ☃ : ☃.a())
      {
        bok ☃ = (bok)this.i.get(☃.a());
        if (☃ == null) {
          c.warn("Missing model for: " + ☃);
        } else {
          ☃.addAll(a(☃));
        }
      }
    }
    bxt ☃;
    for (Iterator ☃ = this.k.keySet().iterator(); ☃.hasNext();)
    {
      ☃ = (bol)☃.next();
      for (bot ☃ : ☃.c().b()) {
        for (bou ☃ : ☃.a())
        {
          bok ☃ = (bok)this.i.get(☃.a());
          if (☃ == null) {
            c.warn("Missing model for: " + ajt.h.b(☃.c().c().c()));
          } else {
            ☃.addAll(a(☃));
          }
        }
      }
    }
    bol ☃;
    ☃.addAll(b);
    
    return ☃;
  }
  
  private bxo a(bok ☃, bxp ☃, boolean ☃)
  {
    bvh ☃ = (bvh)this.h.get(new kk(☃.c("particle")));
    
    bxv.a ☃ = new bxv.a(☃, ☃.g()).a(☃);
    if (☃.a().isEmpty()) {
      return null;
    }
    for (Iterator ☃ = ☃.a().iterator(); ☃.hasNext();)
    {
      ☃ = (bog)☃.next();
      for (cq ☃ : ☃.c.keySet())
      {
        boh ☃ = (boh)☃.c.get(☃);
        bvh ☃ = (bvh)this.h.get(new kk(☃.c(☃.d)));
        if (☃.b == null) {
          ☃.a(a(☃, ☃, ☃, ☃, ☃, ☃));
        } else {
          ☃.a(☃.a(☃.b), a(☃, ☃, ☃, ☃, ☃, ☃));
        }
      }
    }
    bog ☃;
    return ☃.b();
  }
  
  private bof a(bog ☃, boh ☃, bvh ☃, cq ☃, bxp ☃, boolean ☃)
  {
    return this.n.a(☃.a, ☃.b, ☃, ☃, ☃, ☃, ☃.d, ☃, ☃.e);
  }
  
  private void k()
  {
    l();
    for (bok ☃ : this.i.values()) {
      ☃.a(this.i);
    }
    bok.b(this.i);
  }
  
  private void l()
  {
    Deque<kk> ☃ = Queues.newArrayDeque();
    Set<kk> ☃ = Sets.newHashSet();
    for (kk ☃ : this.i.keySet())
    {
      ☃.add(☃);
      
      a(☃, ☃, (bok)this.i.get(☃));
    }
    while (!☃.isEmpty())
    {
      kk ☃ = (kk)☃.pop();
      try
      {
        if (this.i.get(☃) != null) {
          continue;
        }
        bok ☃ = c(☃);
        this.i.put(☃, ☃);
        
        a(☃, ☃, ☃);
      }
      catch (Exception ☃)
      {
        c.warn("In parent chain: " + f.join(e(☃)) + "; unable to load model: '" + ☃ + "'", ☃);
      }
      ☃.add(☃);
    }
  }
  
  private void a(Deque<kk> ☃, Set<kk> ☃, bok ☃)
  {
    kk ☃ = ☃.h();
    if ((☃ != null) && (!☃.contains(☃))) {
      ☃.add(☃);
    }
  }
  
  private List<kk> e(kk ☃)
  {
    List<kk> ☃ = Lists.newArrayList(new kk[] { ☃ });
    kk ☃ = ☃;
    while ((☃ = f(☃)) != null) {
      ☃.add(0, ☃);
    }
    return ☃;
  }
  
  private kk f(kk ☃)
  {
    for (Map.Entry<kk, bok> ☃ : this.i.entrySet())
    {
      bok ☃ = (bok)☃.getValue();
      if ((☃ != null) && (☃.equals(☃.h()))) {
        return (kk)☃.getKey();
      }
    }
    return null;
  }
  
  private Set<kk> a(bok ☃)
  {
    Set<kk> ☃ = Sets.newHashSet();
    for (bog ☃ : ☃.a()) {
      for (boh ☃ : ☃.c.values())
      {
        kk ☃ = new kk(☃.c(☃.d));
        ☃.add(☃);
      }
    }
    ☃.add(new kk(☃.c("particle")));
    
    return ☃;
  }
  
  private void m()
  {
    final Set<kk> ☃ = j();
    ☃.addAll(n());
    
    ☃.remove(bvg.f);
    
    buz ☃ = new buz()
    {
      public void a(bvg ☃)
      {
        for (kk ☃ : ☃)
        {
          bvh ☃ = ☃.a(☃);
          bxr.a(bxr.this).put(☃, ☃);
        }
      }
    };
    this.l.a(this.g, ☃);
    
    this.h.put(new kk("missingno"), this.l.f());
  }
  
  private Set<kk> n()
  {
    Set<kk> ☃ = Sets.newHashSet();
    for (kk ☃ : this.t.values())
    {
      ☃ = (bok)this.i.get(☃);
      if (☃ != null)
      {
        ☃.add(new kk(☃.c("particle")));
        if (b(☃)) {
          for (String ☃ : boo.a) {
            ☃.add(new kk(☃.c(☃)));
          }
        } else if (!c(☃)) {
          for (bog ☃ : ☃.a()) {
            for (boh ☃ : ☃.c.values())
            {
              kk ☃ = new kk(☃.c(☃.d));
              ☃.add(☃);
            }
          }
        }
      }
    }
    bok ☃;
    return ☃;
  }
  
  private boolean b(bok ☃)
  {
    if (☃ == null) {
      return false;
    }
    return ☃.i() == r;
  }
  
  private boolean c(bok ☃)
  {
    if (☃ == null) {
      return false;
    }
    bok ☃ = ☃.i();
    return ☃ == s;
  }
  
  private void o()
  {
    for (kk ☃ : this.t.values())
    {
      bok ☃ = (bok)this.i.get(☃);
      if (b(☃))
      {
        bok ☃ = d(☃);
        if (☃ != null) {
          ☃.b = ☃.toString();
        }
        this.i.put(☃, ☃);
      }
      else if (c(☃))
      {
        this.i.put(☃, ☃);
      }
    }
    for (bvh ☃ : this.h.values()) {
      if (!☃.m()) {
        ☃.l();
      }
    }
  }
  
  private bok d(bok ☃)
  {
    return this.o.a(this.l, ☃);
  }
}
