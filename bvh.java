import com.google.common.collect.Lists;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import shadersmod.client.ShadersTex;

public class bvh
{
  private final String j;
  protected List<int[][]> a = Lists.newArrayList();
  protected int[][] b;
  private bwy k;
  protected boolean c;
  protected int d;
  protected int e;
  protected int f;
  protected int g;
  private float l;
  private float m;
  private float n;
  private float o;
  protected int h;
  protected int i;
  private int indexInMap = -1;
  public float baseU;
  public float baseV;
  public int sheetWidth;
  public int sheetHeight;
  public int glSpriteTextureId = -1;
  public bvh spriteSingle = null;
  public boolean isSpriteSingle = false;
  public int mipmapLevels = 0;
  
  private bvh(bvh parent)
  {
    this.j = parent.j;
    this.isSpriteSingle = true;
  }
  
  protected bvh(String spriteName)
  {
    this.j = spriteName;
    if (Config.isMultiTexture()) {
      this.spriteSingle = new bvh(this);
    }
  }
  
  protected static bvh a(kk spriteResourceLocation)
  {
    return new bvh(spriteResourceLocation.toString());
  }
  
  public void a(int inX, int inY, int originInX, int originInY, boolean rotatedIn)
  {
    this.d = originInX;
    this.e = originInY;
    this.c = rotatedIn;
    float f = (float)(0.009999999776482582D / inX);
    float f1 = (float)(0.009999999776482582D / inY);
    this.l = (originInX / (float)inX + f);
    this.m = ((originInX + this.f) / (float)inX - f);
    this.n = (originInY / inY + f1);
    this.o = ((originInY + this.g) / inY - f1);
    
    this.baseU = Math.min(this.l, this.m);
    this.baseV = Math.min(this.n, this.o);
    if (this.spriteSingle != null) {
      this.spriteSingle.a(this.f, this.g, 0, 0, false);
    }
  }
  
  public void a(bvh atlasSpirit)
  {
    this.d = atlasSpirit.d;
    this.e = atlasSpirit.e;
    this.f = atlasSpirit.f;
    this.g = atlasSpirit.g;
    this.c = atlasSpirit.c;
    this.l = atlasSpirit.l;
    this.m = atlasSpirit.m;
    this.n = atlasSpirit.n;
    this.o = atlasSpirit.o;
    if (this.spriteSingle != null) {
      this.spriteSingle.a(this.f, this.g, 0, 0, false);
    }
  }
  
  public int a()
  {
    return this.d;
  }
  
  public int b()
  {
    return this.e;
  }
  
  public int c()
  {
    return this.f;
  }
  
  public int d()
  {
    return this.g;
  }
  
  public float e()
  {
    return this.l;
  }
  
  public float f()
  {
    return this.m;
  }
  
  public float a(double u)
  {
    float f = this.m - this.l;
    return this.l + f * (float)u / 16.0F;
  }
  
  public float a(float p_188537_1_)
  {
    float f = this.m - this.l;
    return (p_188537_1_ - this.l) / f * 16.0F;
  }
  
  public float g()
  {
    return this.n;
  }
  
  public float h()
  {
    return this.o;
  }
  
  public float b(double v)
  {
    float f = this.o - this.n;
    return this.n + f * (float)v / 16.0F;
  }
  
  public float b(float p_188536_1_)
  {
    float f = this.o - this.n;
    return (p_188536_1_ - this.n) / f * 16.0F;
  }
  
  public String i()
  {
    return this.j;
  }
  
  public void j()
  {
    this.i += 1;
    if (this.i >= this.k.a(this.h))
    {
      int i = this.k.c(this.h);
      int j = this.k.c() == 0 ? this.a.size() : this.k.c();
      this.h = ((this.h + 1) % j);
      this.i = 0;
      int k = this.k.c(this.h);
      
      boolean texBlur = false;
      boolean texClamp = this.isSpriteSingle;
      if ((i != k) && (k >= 0) && (k < this.a.size())) {
        if (Config.isShaders()) {
          ShadersTex.uploadTexSub((int[][])this.a.get(k), this.f, this.g, this.d, this.e, texBlur, texClamp);
        } else {
          bvk.a((int[][])this.a.get(k), this.f, this.g, this.d, this.e, texBlur, texClamp);
        }
      }
    }
    else if (this.k.e())
    {
      n();
    }
  }
  
  private void n()
  {
    double d0 = 1.0D - this.i / this.k.a(this.h);
    int i = this.k.c(this.h);
    int j = this.k.c() == 0 ? this.a.size() : this.k.c();
    int k = this.k.c((this.h + 1) % j);
    if ((i != k) && (k >= 0) && (k < this.a.size()))
    {
      int[][] aint = (int[][])this.a.get(i);
      int[][] aint1 = (int[][])this.a.get(k);
      if ((this.b == null) || (this.b.length != aint.length)) {
        this.b = new int[aint.length][];
      }
      for (int l = 0; l < aint.length; l++)
      {
        if (this.b[l] == null) {
          this.b[l] = new int[aint[l].length];
        }
        if ((l < aint1.length) && (aint1[l].length == aint[l].length)) {
          for (int i1 = 0; i1 < aint[l].length; i1++)
          {
            int j1 = aint[l][i1];
            int k1 = aint1[l][i1];
            int l1 = a(d0, j1 >> 16 & 0xFF, k1 >> 16 & 0xFF);
            int i2 = a(d0, j1 >> 8 & 0xFF, k1 >> 8 & 0xFF);
            int j2 = a(d0, j1 & 0xFF, k1 & 0xFF);
            this.b[l][i1] = (j1 & 0xFF000000 | l1 << 16 | i2 << 8 | j2);
          }
        }
      }
      bvk.a(this.b, this.f, this.g, this.d, this.e, false, false);
    }
  }
  
  private int a(double p_188535_1_, int p_188535_3_, int p_188535_4_)
  {
    return (int)(p_188535_1_ * p_188535_3_ + (1.0D - p_188535_1_) * p_188535_4_);
  }
  
  public int[][] a(int index)
  {
    return (int[][])this.a.get(index);
  }
  
  public int k()
  {
    return this.a.size();
  }
  
  public void b(int newWidth)
  {
    this.f = newWidth;
    if (this.spriteSingle != null) {
      this.spriteSingle.b(this.f);
    }
  }
  
  public void c(int newHeight)
  {
    this.g = newHeight;
    if (this.spriteSingle != null) {
      this.spriteSingle.c(this.g);
    }
  }
  
  public void a(bvc sizeInfo, boolean p_188538_2_)
    throws IOException
  {
    o();
    this.f = sizeInfo.a;
    this.g = sizeInfo.b;
    if (p_188538_2_) {
      this.g = this.f;
    } else if (sizeInfo.b != sizeInfo.a) {
      throw new RuntimeException("broken aspect ratio and not an animation");
    }
    if (this.spriteSingle != null)
    {
      this.spriteSingle.f = this.f;
      this.spriteSingle.g = this.g;
    }
  }
  
  public void a(bwf resource, int p_188539_2_)
    throws IOException
  {
    BufferedImage bufferedimage = bvk.a(resource.b());
    bwy animationmetadatasection = (bwy)resource.a("animation");
    int[][] aint = new int[p_188539_2_][];
    aint[0] = new int[bufferedimage.getWidth() * bufferedimage.getHeight()];
    bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), aint[0], 0, bufferedimage.getWidth());
    if (animationmetadatasection == null)
    {
      this.a.add(aint);
    }
    else
    {
      int i = bufferedimage.getHeight() / this.f;
      if (animationmetadatasection.c() > 0)
      {
        Iterator iterator = animationmetadatasection.f().iterator();
        while (iterator.hasNext())
        {
          int j = ((Integer)iterator.next()).intValue();
          if (j >= i) {
            throw new RuntimeException("invalid frameindex " + j);
          }
          e(j);
          this.a.set(j, a(aint, this.f, this.f, j));
        }
        this.k = animationmetadatasection;
      }
      else
      {
        List<bwx> list = Lists.newArrayList();
        for (int k = 0; k < i; k++)
        {
          this.a.add(a(aint, this.f, this.f, k));
          list.add(new bwx(k, -1));
        }
        this.k = new bwy(list, this.f, this.g, animationmetadatasection.d(), animationmetadatasection.e());
      }
    }
    for (int i = 0; i < this.a.size(); i++)
    {
      int[][] datas = (int[][])this.a.get(i);
      if (datas != null) {
        if (!this.j.startsWith("minecraft:blocks/leaves_")) {
          for (int di = 0; di < datas.length; di++)
          {
            int[] data = datas[di];
            fixTransparentColor(data);
          }
        }
      }
    }
    if (this.spriteSingle != null)
    {
      bwf resourceSingle = Config.getResourceManager().a(resource.a());
      this.spriteSingle.a(resourceSingle, p_188539_2_);
    }
  }
  
  public void d(int level)
  {
    List<int[][]> list = Lists.newArrayList();
    for (int i = 0; i < this.a.size(); i++)
    {
      final int[][] aint = (int[][])this.a.get(i);
      if (aint != null) {
        try
        {
          list.add(bvk.a(level, this.f, aint));
        }
        catch (Throwable throwable)
        {
          b crashreport = b.a(throwable, "Generating mipmaps for frame");
          c crashreportcategory = crashreport.a("Frame being iterated");
          crashreportcategory.a("Frame index", Integer.valueOf(i));
          crashreportcategory.a("Frame sizes", new Callable()
          {
            public String a()
              throws Exception
            {
              StringBuilder stringbuilder = new StringBuilder();
              for (int[] aint1 : aint)
              {
                if (stringbuilder.length() > 0) {
                  stringbuilder.append(", ");
                }
                stringbuilder.append(aint1 == null ? "null" : Integer.valueOf(aint1.length));
              }
              return stringbuilder.toString();
            }
          });
          throw new e(crashreport);
        }
      }
    }
    a(list);
    if (this.spriteSingle != null) {
      this.spriteSingle.d(level);
    }
  }
  
  private void e(int index)
  {
    if (this.a.size() <= index) {
      for (int i = this.a.size(); i <= index; i++) {
        this.a.add((int[][])null);
      }
    }
    if (this.spriteSingle != null) {
      this.spriteSingle.e(index);
    }
  }
  
  private static int[][] a(int[][] data, int rows, int columns, int p_147962_3_)
  {
    int[][] aint = new int[data.length][];
    for (int i = 0; i < data.length; i++)
    {
      int[] aint1 = data[i];
      if (aint1 != null)
      {
        aint[i] = new int[(rows >> i) * (columns >> i)];
        System.arraycopy(aint1, p_147962_3_ * aint[i].length, aint[i], 0, aint[i].length);
      }
    }
    return aint;
  }
  
  public void l()
  {
    this.a.clear();
    if (this.spriteSingle != null) {
      this.spriteSingle.l();
    }
  }
  
  public boolean m()
  {
    return this.k != null;
  }
  
  public void a(List<int[][]> newFramesTextureData)
  {
    this.a = newFramesTextureData;
    if (this.spriteSingle != null) {
      this.spriteSingle.a(newFramesTextureData);
    }
  }
  
  private void o()
  {
    this.k = null;
    a(Lists.newArrayList());
    this.h = 0;
    this.i = 0;
    if (this.spriteSingle != null) {
      this.spriteSingle.o();
    }
  }
  
  public String toString()
  {
    return "TextureAtlasSprite{name='" + this.j + '\'' + ", frameCount=" + this.a.size() + ", rotated=" + this.c + ", x=" + this.d + ", y=" + this.e + ", height=" + this.g + ", width=" + this.f + ", u0=" + this.l + ", u1=" + this.m + ", v0=" + this.n + ", v1=" + this.o + '}';
  }
  
  public boolean hasCustomLoader(bwg manager, kk location)
  {
    return false;
  }
  
  public boolean load(bwg manager, kk location)
  {
    return true;
  }
  
  public int getIndexInMap()
  {
    return this.indexInMap;
  }
  
  public void setIndexInMap(int indexInMap)
  {
    this.indexInMap = indexInMap;
  }
  
  private void fixTransparentColor(int[] data)
  {
    if (data == null) {
      return;
    }
    long redSum = 0L;
    long greenSum = 0L;
    long blueSum = 0L;
    long count = 0L;
    for (int i = 0; i < data.length; i++)
    {
      int col = data[i];
      int alpha = col >> 24 & 0xFF;
      if (alpha >= 16)
      {
        int red = col >> 16 & 0xFF;
        int green = col >> 8 & 0xFF;
        int blue = col & 0xFF;
        
        redSum += red;
        greenSum += green;
        blueSum += blue;
        
        count += 1L;
      }
    }
    if (count <= 0L) {
      return;
    }
    int redAvg = (int)(redSum / count);
    int greenAvg = (int)(greenSum / count);
    int blueAvg = (int)(blueSum / count);
    int colAvg = redAvg << 16 | greenAvg << 8 | blueAvg;
    for (int i = 0; i < data.length; i++)
    {
      int col = data[i];
      int alpha = col >> 24 & 0xFF;
      if (alpha <= 16) {
        data[i] = colAvg;
      }
    }
  }
  
  public double getSpriteU16(float atlasU)
  {
    float dU = this.m - this.l;
    return (atlasU - this.l) / dU * 16.0F;
  }
  
  public double getSpriteV16(float atlasV)
  {
    float dV = this.o - this.n;
    return (atlasV - this.n) / dV * 16.0F;
  }
  
  public void bindSpriteTexture()
  {
    if (this.glSpriteTextureId < 0)
    {
      this.glSpriteTextureId = bvk.a();
      
      bvk.a(this.glSpriteTextureId, this.mipmapLevels, this.f, this.g);
      
      TextureUtils.applyAnisotropicLevel();
    }
    TextureUtils.bindTexture(this.glSpriteTextureId);
  }
  
  public void deleteSpriteTexture()
  {
    if (this.glSpriteTextureId < 0) {
      return;
    }
    bvk.a(this.glSpriteTextureId);
    
    this.glSpriteTextureId = -1;
  }
  
  public float toSingleU(float u)
  {
    u -= this.baseU;
    
    float ku = this.sheetWidth / this.f;
    
    u *= ku;
    
    return u;
  }
  
  public float toSingleV(float v)
  {
    v -= this.baseV;
    
    float kv = this.sheetHeight / this.g;
    
    v *= kv;
    
    return v;
  }
}
