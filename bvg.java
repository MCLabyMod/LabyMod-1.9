import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bvg
  extends buw
  implements bvm
{
  private static final Logger h = ;
  public static final kk f = new kk("missingno");
  public static final kk g = new kk("textures/atlas/blocks.png");
  private final List<bvh> i;
  private final Map<String, bvh> j;
  private final Map<String, bvh> k;
  private final String l;
  private final buz m;
  private int n;
  private final bvh o;
  private bvh[] iconGrid = null;
  private int iconGridSize = -1;
  private int iconGridCountX = -1;
  private int iconGridCountY = -1;
  private double iconGridSizeU = -1.0D;
  private double iconGridSizeV = -1.0D;
  private static final boolean ENABLE_SKIP = Boolean.parseBoolean(System.getProperty("fml.skipFirstTextureLoad", "true"));
  private boolean skipFirst = false;
  public int atlasWidth = 0;
  public int atlasHeight = 0;
  
  public bvg(String basePathIn)
  {
    this(basePathIn, (buz)null);
  }
  
  public bvg(String basePathIn, boolean skipFirst)
  {
    this(basePathIn, (buz)null, skipFirst);
  }
  
  public bvg(String basePathIn, buz iconCreatorIn)
  {
    this(basePathIn, iconCreatorIn, false);
  }
  
  public bvg(String basePathIn, buz iconCreatorIn, boolean skipFirst)
  {
    this.i = Lists.newArrayList();
    this.j = Maps.newHashMap();
    this.k = Maps.newHashMap();
    this.o = new bvh("missingno");
    this.l = basePathIn;
    this.m = iconCreatorIn;
    
    this.skipFirst = ((skipFirst) && (ENABLE_SKIP));
  }
  
  private void g()
  {
    int size = getMinSpriteSize();
    
    int[] aint = getMissingImageData(size);
    
    this.o.b(size);
    this.o.c(size);
    
    int[][] aint1 = new int[this.n + 1][];
    aint1[0] = aint;
    this.o.a(Lists.newArrayList(new int[][][] { aint1 }));
    
    this.o.setIndexInMap(0);
  }
  
  public void a(bwg resourceManager)
    throws IOException
  {
    shadersmod.client.ShadersTex.resManager = resourceManager;
    if (this.m != null) {
      a(resourceManager, this.m);
    }
  }
  
  public void a(bwg resourceManager, buz p_174943_2_)
  {
    this.j.clear();
    p_174943_2_.a(this);
    if (this.n >= 4)
    {
      this.n = detectMaxMipmapLevel(this.j, resourceManager);
      Config.log("Mipmap levels: " + this.n);
    }
    g();
    c();
    b(resourceManager);
  }
  
  public void b(bwg resourceManager)
  {
    shadersmod.client.ShadersTex.resManager = resourceManager;
    
    Config.dbg("Multitexture: " + Config.isMultiTexture());
    Iterator it;
    if (Config.isMultiTexture()) {
      for (it = this.k.values().iterator(); it.hasNext();)
      {
        bvh ts = (bvh)it.next();
        
        ts.deleteSpriteTexture();
      }
    }
    ConnectedTextures.updateIcons(this);
    
    CustomItems.updateIcons(this);
    
    int i = bcf.B();
    bve stitcher = new bve(i, i, 0, this.n);
    this.k.clear();
    this.i.clear();
    int j = Integer.MAX_VALUE;
    
    Reflector.callVoid(Reflector.ForgeHooksClient_onTextureStitchedPre, new Object[] { this });
    
    int minSpriteSize = getMinSpriteSize();
    
    this.iconGridSize = minSpriteSize;
    
    int k = 1 << this.n;
    for (Map.Entry<String, bvh> entry : this.j.entrySet())
    {
      if (this.skipFirst) {
        break;
      }
      bvh textureatlassprite = (bvh)entry.getValue();
      kk resourcelocation = a(textureatlassprite);
      bwf iresource = null;
      if (textureatlassprite.hasCustomLoader(resourceManager, resourcelocation))
      {
        if (!textureatlassprite.load(resourceManager, resourcelocation))
        {
          j = Math.min(j, Math.min(textureatlassprite.c(), textureatlassprite.d()));
          stitcher.a(textureatlassprite);
        }
      }
      else
      {
        try
        {
          bvc pngsizeinfo = bvc.a(resourceManager.a(resourcelocation));
          iresource = resourceManager.a(resourcelocation);
          boolean flag = iresource.a("animation") != null;
          textureatlassprite.a(pngsizeinfo, flag);
        }
        catch (RuntimeException runtimeexception)
        {
          h.error("Unable to parse metadata from " + resourcelocation, runtimeexception);
          
          IOUtils.closeQuietly(iresource); continue;
        }
        catch (IOException ioexception)
        {
          h.error("Using missing texture, unable to load " + resourcelocation, ioexception);
          
          IOUtils.closeQuietly(iresource); continue;
        }
        finally
        {
          IOUtils.closeQuietly(iresource);
        }
        j = Math.min(j, Math.min(textureatlassprite.c(), textureatlassprite.d()));
        int lvt_11_2_ = Math.min(Integer.lowestOneBit(textureatlassprite.c()), Integer.lowestOneBit(textureatlassprite.d()));
        if (lvt_11_2_ < k)
        {
          h.warn("Texture {} with size {}x{} limits mip level from {} to {}", new Object[] { resourcelocation, Integer.valueOf(textureatlassprite.c()), Integer.valueOf(textureatlassprite.d()), Integer.valueOf(on.e(k)), Integer.valueOf(on.e(lvt_11_2_)) });
          k = lvt_11_2_;
        }
        stitcher.a(textureatlassprite);
      }
    }
    int l = Math.min(j, k);
    int i1 = on.e(l);
    if (i1 < this.n)
    {
      h.warn("{}: dropping miplevel from {} to {}, because of minimum power of two: {}", new Object[] { this.l, Integer.valueOf(this.n), Integer.valueOf(i1), Integer.valueOf(l) });
      this.n = i1;
    }
    this.o.d(this.n);
    stitcher.a(this.o);
    try
    {
      stitcher.c();
    }
    catch (bvf stitcherexception)
    {
      throw stitcherexception;
    }
    h.info("Created: {}x{} {}-atlas", new Object[] { Integer.valueOf(stitcher.a()), Integer.valueOf(stitcher.b()), this.l });
    bvk.a(b(), this.n, stitcher.a(), stitcher.b());
    Map<String, bvh> map = Maps.newHashMap(this.j);
    for (bvh textureatlassprite1 : stitcher.d()) {
      if ((textureatlassprite1 == this.o) || (a(resourceManager, textureatlassprite1)))
      {
        String s = textureatlassprite1.i();
        map.remove(s);
        this.k.put(s, textureatlassprite1);
        try
        {
          bvk.a(textureatlassprite1.a(0), textureatlassprite1.c(), textureatlassprite1.d(), textureatlassprite1.a(), textureatlassprite1.b(), false, false);
        }
        catch (Throwable throwable)
        {
          b crashreport = b.a(throwable, "Stitching texture atlas");
          c crashreportcategory = crashreport.a("Texture being stitched together");
          crashreportcategory.a("Atlas path", this.l);
          crashreportcategory.a("Sprite", textureatlassprite1);
          throw new e(crashreport);
        }
        if (textureatlassprite1.m()) {
          this.i.add(textureatlassprite1);
        }
      }
    }
    for (bvh textureatlassprite2 : map.values()) {
      textureatlassprite2.a(this.o);
    }
  }
  
  /* Error */
  private boolean a(bwg p_184397_1_, final bvh p_184397_2_)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_2
    //   2: invokevirtual 297	bvg:a	(Lbvh;)Lkk;
    //   5: astore_3
    //   6: aconst_null
    //   7: astore 4
    //   9: aload_1
    //   10: aload_3
    //   11: invokeinterface 325 2 0
    //   16: astore 4
    //   18: aload_2
    //   19: aload 4
    //   21: aload_0
    //   22: getfield 140	bvg:n	I
    //   25: iconst_1
    //   26: iadd
    //   27: invokevirtual 495	bvh:a	(Lbwf;I)V
    //   30: aload 4
    //   32: invokestatic 344	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   35: goto +106 -> 141
    //   38: astore 6
    //   40: getstatic 346	bvg:h	Lorg/apache/logging/log4j/Logger;
    //   43: new 182	java/lang/StringBuilder
    //   46: dup
    //   47: invokespecial 183	java/lang/StringBuilder:<init>	()V
    //   50: ldc_w 348
    //   53: invokevirtual 189	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: aload_3
    //   57: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   60: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   63: aload 6
    //   65: invokeinterface 357 3 0
    //   70: iconst_0
    //   71: istore 5
    //   73: aload 4
    //   75: invokestatic 344	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   78: goto +60 -> 138
    //   81: astore 6
    //   83: getstatic 346	bvg:h	Lorg/apache/logging/log4j/Logger;
    //   86: new 182	java/lang/StringBuilder
    //   89: dup
    //   90: invokespecial 183	java/lang/StringBuilder:<init>	()V
    //   93: ldc_w 359
    //   96: invokevirtual 189	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: aload_3
    //   100: invokevirtual 351	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   103: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   106: aload 6
    //   108: invokeinterface 357 3 0
    //   113: iconst_0
    //   114: istore 5
    //   116: iload 5
    //   118: istore 7
    //   120: aload 4
    //   122: invokestatic 344	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   125: iload 7
    //   127: ireturn
    //   128: astore 8
    //   130: aload 4
    //   132: invokestatic 344	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   135: aload 8
    //   137: athrow
    //   138: iload 5
    //   140: ireturn
    //   141: aload_2
    //   142: aload_0
    //   143: getfield 140	bvg:n	I
    //   146: invokevirtual 384	bvh:d	(I)V
    //   149: iconst_1
    //   150: ireturn
    //   151: astore 5
    //   153: aload 5
    //   155: ldc_w 497
    //   158: invokestatic 433	b:a	(Ljava/lang/Throwable;Ljava/lang/String;)Lb;
    //   161: astore 6
    //   163: aload 6
    //   165: ldc_w 499
    //   168: invokevirtual 438	b:a	(Ljava/lang/String;)Lc;
    //   171: astore 7
    //   173: aload 7
    //   175: ldc_w 501
    //   178: new 13	bvg$1
    //   181: dup
    //   182: aload_0
    //   183: aload_2
    //   184: invokespecial 504	bvg$1:<init>	(Lbvg;Lbvh;)V
    //   187: invokevirtual 507	c:a	(Ljava/lang/String;Ljava/util/concurrent/Callable;)V
    //   190: aload 7
    //   192: ldc_w 509
    //   195: new 11	bvg$2
    //   198: dup
    //   199: aload_0
    //   200: aload_2
    //   201: invokespecial 510	bvg$2:<init>	(Lbvg;Lbvh;)V
    //   204: invokevirtual 507	c:a	(Ljava/lang/String;Ljava/util/concurrent/Callable;)V
    //   207: aload 7
    //   209: ldc_w 512
    //   212: new 9	bvg$3
    //   215: dup
    //   216: aload_0
    //   217: aload_2
    //   218: invokespecial 513	bvg$3:<init>	(Lbvg;Lbvh;)V
    //   221: invokevirtual 507	c:a	(Ljava/lang/String;Ljava/util/concurrent/Callable;)V
    //   224: aload 7
    //   226: ldc_w 515
    //   229: aload_0
    //   230: getfield 140	bvg:n	I
    //   233: invokestatic 371	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   236: invokevirtual 444	c:a	(Ljava/lang/String;Ljava/lang/Object;)V
    //   239: new 447	e
    //   242: dup
    //   243: aload 6
    //   245: invokespecial 450	e:<init>	(Lb;)V
    //   248: athrow
    // Line number table:
    //   Java source line #300	-> byte code offset #0
    //   Java source line #301	-> byte code offset #6
    //   Java source line #308	-> byte code offset #9
    //   Java source line #309	-> byte code offset #18
    //   Java source line #325	-> byte code offset #30
    //   Java source line #312	-> byte code offset #38
    //   Java source line #314	-> byte code offset #40
    //   Java source line #315	-> byte code offset #70
    //   Java source line #325	-> byte code offset #73
    //   Java source line #326	-> byte code offset #78
    //   Java source line #317	-> byte code offset #81
    //   Java source line #319	-> byte code offset #83
    //   Java source line #320	-> byte code offset #113
    //   Java source line #321	-> byte code offset #116
    //   Java source line #325	-> byte code offset #120
    //   Java source line #328	-> byte code offset #138
    //   Java source line #333	-> byte code offset #141
    //   Java source line #334	-> byte code offset #149
    //   Java source line #336	-> byte code offset #151
    //   Java source line #338	-> byte code offset #153
    //   Java source line #339	-> byte code offset #163
    //   Java source line #340	-> byte code offset #173
    //   Java source line #347	-> byte code offset #190
    //   Java source line #354	-> byte code offset #207
    //   Java source line #361	-> byte code offset #224
    //   Java source line #362	-> byte code offset #239
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	249	0	this	bvg
    //   0	249	1	p_184397_1_	bwg
    //   0	249	2	p_184397_2_	bvh
    //   5	95	3	resourcelocation	kk
    //   7	124	4	iresource	bwf
    //   71	68	5	flag	boolean
    //   151	3	5	throwable	Throwable
    //   38	26	6	runtimeexception	RuntimeException
    //   81	26	6	ioexception	IOException
    //   161	83	6	crashreport	b
    //   118	8	7	bool1	boolean
    //   171	54	7	crashreportcategory	c
    //   128	8	8	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   9	30	38	java/lang/RuntimeException
    //   9	30	81	java/io/IOException
    //   9	30	128	finally
    //   38	73	128	finally
    //   81	120	128	finally
    //   128	130	128	finally
    //   141	150	151	java/lang/Throwable
  }
  
  public kk a(bvh p_184396_1_)
  {
    kk resourcelocation = new kk(p_184396_1_.i());
    return completeResourceLocation(resourcelocation);
  }
  
  public kk completeResourceLocation(kk resourcelocation)
  {
    if (isAbsoluteLocation(resourcelocation)) {
      return new kk(resourcelocation.b(), resourcelocation.a() + ".png");
    }
    return new kk(resourcelocation.b(), String.format("%s/%s%s", new Object[] { this.l, resourcelocation.a(), ".png" }));
  }
  
  public bvh a(String iconName)
  {
    bvh textureatlassprite = (bvh)this.k.get(iconName);
    if (textureatlassprite == null) {
      textureatlassprite = this.o;
    }
    return textureatlassprite;
  }
  
  public void d()
  {
    if (Config.isShaders()) {
      shadersmod.client.ShadersTex.updatingTex = getMultiTexID();
    }
    bvk.b(b());
    for (bvh textureatlassprite : this.i) {
      if (isTerrainAnimationActive(textureatlassprite)) {
        textureatlassprite.j();
      }
    }
    if (Config.isMultiTexture())
    {
      for (Iterator it = this.i.iterator(); it.hasNext();)
      {
        bvh ts = (bvh)it.next();
        if (isTerrainAnimationActive(ts))
        {
          bvh spriteSingle = ts.spriteSingle;
          if (spriteSingle != null)
          {
            if ((ts == TextureUtils.iconClock) || (ts == TextureUtils.iconCompass)) {
              spriteSingle.h = ts.h;
            }
            ts.bindSpriteTexture();
            spriteSingle.j();
          }
        }
      }
      bvk.b(b());
    }
    if (Config.isShaders()) {
      shadersmod.client.ShadersTex.updatingTex = null;
    }
  }
  
  public bvh a(kk location)
  {
    if (location == null) {
      throw new IllegalArgumentException("Location cannot be null!");
    }
    bvh textureatlassprite = (bvh)this.j.get(location.toString());
    if (textureatlassprite == null)
    {
      textureatlassprite = bvh.a(location);
      this.j.put(location.toString(), textureatlassprite);
      if ((textureatlassprite instanceof bvh))
      {
        bvh tas = textureatlassprite;
        if (tas.getIndexInMap() < 0) {
          tas.setIndexInMap(this.j.size());
        }
      }
    }
    return textureatlassprite;
  }
  
  public void e()
  {
    d();
  }
  
  public void a(int mipmapLevelsIn)
  {
    this.n = mipmapLevelsIn;
  }
  
  public bvh f()
  {
    return this.o;
  }
  
  public bvh getTextureExtry(String name)
  {
    kk loc = new kk(name);
    return (bvh)this.j.get(loc.toString());
  }
  
  public boolean setTextureEntry(String name, bvh entry)
  {
    if (!this.j.containsKey(name))
    {
      this.j.put(name, entry);
      if (entry.getIndexInMap() < 0) {
        entry.setIndexInMap(this.j.size());
      }
      return true;
    }
    return false;
  }
  
  private boolean isAbsoluteLocation(kk loc)
  {
    String path = loc.a();
    return isAbsoluteLocationPath(path);
  }
  
  private boolean isAbsoluteLocationPath(String resPath)
  {
    String path = resPath.toLowerCase();
    if ((path.startsWith("mcpatcher/")) || (path.startsWith("optifine/"))) {
      return true;
    }
    return false;
  }
  
  public bvh getSpriteSafe(String name)
  {
    kk loc = new kk(name);
    return (bvh)this.j.get(loc.toString());
  }
  
  private boolean isTerrainAnimationActive(bvh ts)
  {
    if ((ts == TextureUtils.iconWaterStill) || (ts == TextureUtils.iconWaterFlow)) {
      return Config.isAnimatedWater();
    }
    if ((ts == TextureUtils.iconLavaStill) || (ts == TextureUtils.iconLavaFlow)) {
      return Config.isAnimatedLava();
    }
    if ((ts == TextureUtils.iconFireLayer0) || (ts == TextureUtils.iconFireLayer1)) {
      return Config.isAnimatedFire();
    }
    if (ts == TextureUtils.iconPortal) {
      return Config.isAnimatedPortal();
    }
    if ((ts == TextureUtils.iconClock) || (ts == TextureUtils.iconCompass)) {
      return true;
    }
    return Config.isAnimatedTerrain();
  }
  
  public int getCountRegisteredSprites()
  {
    return this.j.size();
  }
  
  private int detectMaxMipmapLevel(Map mapSprites, bwg rm)
  {
    int minSize = detectMinimumSpriteSize(mapSprites, rm, 20);
    if (minSize < 16) {
      minSize = 16;
    }
    minSize = on.c(minSize);
    if (minSize > 16) {
      Config.log("Sprite size: " + minSize);
    }
    int minLevel = on.e(minSize);
    if (minLevel < 4) {
      minLevel = 4;
    }
    return minLevel;
  }
  
  private int detectMinimumSpriteSize(Map mapSprites, bwg rm, int percentScale)
  {
    Map mapSizeCounts = new HashMap();
    Set entrySetSprites = mapSprites.entrySet();
    for (Iterator it = entrySetSprites.iterator(); it.hasNext();)
    {
      Map.Entry entry = (Map.Entry)it.next();
      bvh sprite = (bvh)entry.getValue();
      kk loc = new kk(sprite.i());
      kk locComplete = completeResourceLocation(loc);
      if (!sprite.hasCustomLoader(rm, loc)) {
        try
        {
          bwf res = rm.a(locComplete);
          if (res == null) {
            continue;
          }
          InputStream in = res.b();
          if (in == null) {
            continue;
          }
          Dimension dim = TextureUtils.getImageSize(in, "png");
          if (dim == null) {
            continue;
          }
          int width = dim.width;
          int width2 = on.c(width);
          if (!mapSizeCounts.containsKey(Integer.valueOf(width2)))
          {
            mapSizeCounts.put(Integer.valueOf(width2), Integer.valueOf(1));
            continue;
          }
          int count = ((Integer)mapSizeCounts.get(Integer.valueOf(width2))).intValue();
          mapSizeCounts.put(Integer.valueOf(width2), Integer.valueOf(count + 1));
        }
        catch (Exception e) {}
      }
    }
    int countSprites = 0;
    Set setSizes = mapSizeCounts.keySet();
    Set setSizesSorted = new TreeSet(setSizes);
    for (Iterator it = setSizesSorted.iterator(); it.hasNext();)
    {
      int size = ((Integer)it.next()).intValue();
      int count = ((Integer)mapSizeCounts.get(Integer.valueOf(size))).intValue();
      countSprites += count;
    }
    int minSize = 16;
    int countScale = 0;
    
    int countScaleMax = countSprites * percentScale / 100;
    for (Iterator it = setSizesSorted.iterator(); it.hasNext();)
    {
      int size = ((Integer)it.next()).intValue();
      int count = ((Integer)mapSizeCounts.get(Integer.valueOf(size))).intValue();
      countScale += count;
      if (size > minSize) {
        minSize = size;
      }
      if (countScale > countScaleMax) {
        return minSize;
      }
    }
    return minSize;
  }
  
  private int getMinSpriteSize()
  {
    int minSize = 1 << this.n;
    if (minSize < 8) {
      minSize = 8;
    }
    return minSize;
  }
  
  private int[] getMissingImageData(int size)
  {
    BufferedImage bi = new BufferedImage(16, 16, 2);
    
    bi.setRGB(0, 0, 16, 16, bvk.b, 0, 16);
    
    BufferedImage bi2 = TextureUtils.scaleToPowerOfTwo(bi, size);
    
    int[] data = new int[size * size];
    bi2.getRGB(0, 0, size, size, data, 0, size);
    
    return data;
  }
  
  public boolean isTextureBound()
  {
    int boundTexId = bni.getBoundTexture();
    int texId = b();
    
    return boundTexId == texId;
  }
  
  private void updateIconGrid(int sheetWidth, int sheetHeight)
  {
    this.iconGridCountX = -1;
    this.iconGridCountY = -1;
    this.iconGrid = null;
    if (this.iconGridSize <= 0) {
      return;
    }
    this.iconGridCountX = (sheetWidth / this.iconGridSize);
    this.iconGridCountY = (sheetHeight / this.iconGridSize);
    
    this.iconGrid = new bvh[this.iconGridCountX * this.iconGridCountY];
    this.iconGridSizeU = (1.0D / this.iconGridCountX);
    this.iconGridSizeV = (1.0D / this.iconGridCountY);
    for (Iterator it = this.k.values().iterator(); it.hasNext();)
    {
      bvh ts = (bvh)it.next();
      double uMin = Math.min(ts.e(), ts.f());
      double vMin = Math.min(ts.g(), ts.h());
      double uMax = Math.max(ts.e(), ts.f());
      double vMax = Math.max(ts.g(), ts.h());
      int iuMin = (int)(uMin / this.iconGridSizeU);
      int ivMin = (int)(vMin / this.iconGridSizeV);
      int iuMax = (int)(uMax / this.iconGridSizeU);
      int ivMax = (int)(vMax / this.iconGridSizeV);
      for (int iu = iuMin; iu <= iuMax; iu++) {
        if ((iu < 0) || (iu >= this.iconGridCountX)) {
          Config.warn("Invalid grid U: " + iu + ", icon: " + ts.i());
        } else {
          for (int iv = ivMin; iv <= ivMax; iv++) {
            if ((iv < 0) || (iv >= this.iconGridCountX))
            {
              Config.warn("Invalid grid V: " + iv + ", icon: " + ts.i());
            }
            else
            {
              int index = iv * this.iconGridCountX + iu;
              this.iconGrid[index] = ts;
            }
          }
        }
      }
    }
  }
  
  public bvh getIconByUV(double u, double v)
  {
    if (this.iconGrid == null) {
      return null;
    }
    int iu = (int)(u / this.iconGridSizeU);
    int iv = (int)(v / this.iconGridSizeV);
    int index = iv * this.iconGridCountX + iu;
    if ((index < 0) || (index > this.iconGrid.length)) {
      return null;
    }
    return this.iconGrid[index];
  }
}
