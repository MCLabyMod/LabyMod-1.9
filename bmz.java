import com.google.common.primitives.Floats;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import shadersmod.client.SVertexBuilder;

public class bmz
{
  private static final Logger a = ;
  private ByteBuffer b;
  public IntBuffer c;
  private ShortBuffer d;
  public FloatBuffer e;
  public int f;
  private bvs g;
  private int h;
  private boolean i;
  public int j;
  private double k;
  private double l;
  private double m;
  private bvr n;
  private boolean o;
  private ahm blockLayer = null;
  private boolean[] drawnIcons = new boolean['Ā'];
  private bvh[] quadSprites = null;
  private bvh quadSprite = null;
  public SVertexBuilder sVertexBuilder;
  
  public bmz(int bufferSizeIn)
  {
    if (Config.isShaders()) {
      bufferSizeIn *= 2;
    }
    this.b = bce.c(bufferSizeIn * 4);
    this.c = this.b.asIntBuffer();
    this.d = this.b.asShortBuffer();
    this.e = this.b.asFloatBuffer();
    
    SVertexBuilder.initVertexBuilder(this);
  }
  
  private void b(int p_181670_1_)
  {
    if (Config.isShaders()) {
      p_181670_1_ *= 2;
    }
    int i = (this.f + 1) * this.n.g() + this.n.d(this.h);
    if ((p_181670_1_ > this.c.remaining()) || (i >= this.b.capacity()))
    {
      int j = this.b.capacity();
      int k = j % 2097152;
      int l = k + (((this.c.position() + p_181670_1_) * 4 - k) / 2097152 + 1) * 2097152;
      a.debug("Needed to grow BufferBuilder buffer: Old size " + j + " bytes, new size " + l + " bytes.");
      int i1 = this.c.position();
      ByteBuffer bytebuffer = bce.c(l);
      this.b.position(0);
      bytebuffer.put(this.b);
      bytebuffer.rewind();
      this.b = bytebuffer;
      
      this.e = this.b.asFloatBuffer();
      this.c = this.b.asIntBuffer();
      this.c.position(i1);
      this.d = this.b.asShortBuffer();
      this.d.position(i1 << 1);
      if (this.quadSprites != null)
      {
        bvh[] sprites = this.quadSprites;
        int quadSize = getBufferQuadSize();
        this.quadSprites = new bvh[quadSize];
        System.arraycopy(sprites, 0, this.quadSprites, 0, Math.min(sprites.length, this.quadSprites.length));
      }
    }
  }
  
  public void a(float p_181674_1_, float p_181674_2_, float p_181674_3_)
  {
    int i = this.f / 4;
    final float[] afloat = new float[i];
    for (int j = 0; j < i; j++) {
      afloat[j] = a(this.e, (float)(p_181674_1_ + this.k), (float)(p_181674_2_ + this.l), (float)(p_181674_3_ + this.m), this.n.f(), j * this.n.g());
    }
    Integer[] ainteger = new Integer[i];
    for (int k = 0; k < ainteger.length; k++) {
      ainteger[k] = Integer.valueOf(k);
    }
    Arrays.sort(ainteger, new Comparator()
    {
      public int a(Integer p_compare_1_, Integer p_compare_2_)
      {
        return Floats.compare(afloat[p_compare_2_.intValue()], afloat[p_compare_1_.intValue()]);
      }
    });
    BitSet bitset = new BitSet();
    int l = this.n.g();
    int[] aint = new int[l];
    for (int l1 = 0; (l1 = bitset.nextClearBit(l1)) < ainteger.length; l1++)
    {
      int i1 = ainteger[l1].intValue();
      if (i1 != l1)
      {
        this.c.limit(i1 * l + l);
        this.c.position(i1 * l);
        this.c.get(aint);
        int j1 = i1;
        for (int k1 = ainteger[i1].intValue(); j1 != l1; k1 = ainteger[k1].intValue())
        {
          this.c.limit(k1 * l + l);
          this.c.position(k1 * l);
          IntBuffer intbuffer = this.c.slice();
          this.c.limit(j1 * l + l);
          this.c.position(j1 * l);
          this.c.put(intbuffer);
          bitset.set(j1);
          j1 = k1;
        }
        this.c.limit(l1 * l + l);
        this.c.position(l1 * l);
        this.c.put(aint);
      }
      bitset.set(l1);
    }
    if (this.quadSprites != null)
    {
      bvh[] quadSpritesSorted = new bvh[this.f / 4];
      int quadStep = this.n.g() / 4 * 4;
      for (int ix = 0; ix < ainteger.length; ix++)
      {
        int indexQuad = ainteger[ix].intValue();
        int indexQuadSorted = ix;
        quadSpritesSorted[indexQuadSorted] = this.quadSprites[indexQuad];
      }
      System.arraycopy(quadSpritesSorted, 0, this.quadSprites, 0, quadSpritesSorted.length);
    }
  }
  
  public bmz.a a()
  {
    this.c.rewind();
    int i = j();
    this.c.limit(i);
    int[] aint = new int[i];
    this.c.get(aint);
    this.c.limit(this.c.capacity());
    this.c.position(i);
    
    bvh[] quadSpritesCopy = null;
    if (this.quadSprites != null)
    {
      int countQuads = this.f / 4;
      quadSpritesCopy = new bvh[countQuads];
      System.arraycopy(this.quadSprites, 0, quadSpritesCopy, 0, countQuads);
    }
    return new bmz.a(aint, new bvr(this.n), quadSpritesCopy);
  }
  
  public int j()
  {
    return this.f * this.n.f();
  }
  
  private static float a(FloatBuffer p_181665_0_, float p_181665_1_, float p_181665_2_, float p_181665_3_, int p_181665_4_, int p_181665_5_)
  {
    float f = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 0 + 0);
    float f1 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 0 + 1);
    float f2 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 0 + 2);
    float f3 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 1 + 0);
    float f4 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 1 + 1);
    float f5 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 1 + 2);
    float f6 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 2 + 0);
    float f7 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 2 + 1);
    float f8 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 2 + 2);
    float f9 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 3 + 0);
    float f10 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 3 + 1);
    float f11 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 3 + 2);
    float f12 = (f + f3 + f6 + f9) * 0.25F - p_181665_1_;
    float f13 = (f1 + f4 + f7 + f10) * 0.25F - p_181665_2_;
    float f14 = (f2 + f5 + f8 + f11) * 0.25F - p_181665_3_;
    return f12 * f12 + f13 * f13 + f14 * f14;
  }
  
  public void a(bmz.a state)
  {
    this.c.clear();
    b(state.a().length);
    this.c.put(state.a());
    this.f = state.b();
    this.n = new bvr(state.c());
    if (state.stateQuadSprites != null)
    {
      if (this.quadSprites == null) {
        this.quadSprites = new bvh[getBufferQuadSize()];
      }
      bvh[] src = state.stateQuadSprites;
      System.arraycopy(src, 0, this.quadSprites, 0, src.length);
    }
    else
    {
      this.quadSprites = null;
    }
  }
  
  public void b()
  {
    this.f = 0;
    this.g = null;
    this.h = 0;
    
    this.quadSprite = null;
  }
  
  public void a(int glMode, bvr format)
  {
    if (this.o) {
      throw new IllegalStateException("Already building!");
    }
    this.o = true;
    b();
    this.j = glMode;
    this.n = format;
    this.g = format.c(this.h);
    this.i = false;
    this.b.limit(this.b.capacity());
    if (Config.isShaders()) {
      SVertexBuilder.endSetVertexFormat(this);
    }
    if (Config.isMultiTexture())
    {
      if (this.blockLayer != null) {
        if (this.quadSprites == null) {
          this.quadSprites = new bvh[getBufferQuadSize()];
        }
      }
    }
    else {
      this.quadSprites = null;
    }
  }
  
  public bmz a(double p_187315_1_, double p_187315_3_)
  {
    if ((this.quadSprite != null) && (this.quadSprites != null))
    {
      p_187315_1_ = this.quadSprite.toSingleU((float)p_187315_1_);
      p_187315_3_ = this.quadSprite.toSingleV((float)p_187315_3_);
      
      this.quadSprites[(this.f / 4)] = this.quadSprite;
    }
    int i = this.f * this.n.g() + this.n.d(this.h);
    switch (this.g.a())
    {
    case a: 
      this.b.putFloat(i, (float)p_187315_1_);
      this.b.putFloat(i + 4, (float)p_187315_3_);
      break;
    case f: 
    case g: 
      this.b.putInt(i, (int)p_187315_1_);
      this.b.putInt(i + 4, (int)p_187315_3_);
      break;
    case d: 
    case e: 
      this.b.putShort(i, (short)(int)p_187315_3_);
      this.b.putShort(i + 2, (short)(int)p_187315_1_);
      break;
    case b: 
    case c: 
      this.b.put(i, (byte)(int)p_187315_3_);
      this.b.put(i + 1, (byte)(int)p_187315_1_);
    }
    k();
    return this;
  }
  
  public bmz a(int p_187314_1_, int p_187314_2_)
  {
    int i = this.f * this.n.g() + this.n.d(this.h);
    switch (this.g.a())
    {
    case a: 
      this.b.putFloat(i, p_187314_1_);
      this.b.putFloat(i + 4, p_187314_2_);
      break;
    case f: 
    case g: 
      this.b.putInt(i, p_187314_1_);
      this.b.putInt(i + 4, p_187314_2_);
      break;
    case d: 
    case e: 
      this.b.putShort(i, (short)p_187314_2_);
      this.b.putShort(i + 2, (short)p_187314_1_);
      break;
    case b: 
    case c: 
      this.b.put(i, (byte)p_187314_2_);
      this.b.put(i + 1, (byte)p_187314_1_);
    }
    k();
    return this;
  }
  
  public void a(int p_178962_1_, int p_178962_2_, int p_178962_3_, int p_178962_4_)
  {
    int i = (this.f - 4) * this.n.f() + this.n.b(1) / 4;
    int j = this.n.g() >> 2;
    this.c.put(i, p_178962_1_);
    this.c.put(i + j, p_178962_2_);
    this.c.put(i + j * 2, p_178962_3_);
    this.c.put(i + j * 3, p_178962_4_);
  }
  
  public void a(double x, double y, double z)
  {
    int i = this.n.f();
    int j = (this.f - 4) * i;
    for (int k = 0; k < 4; k++)
    {
      int l = j + k * i;
      int i1 = l + 1;
      int j1 = i1 + 1;
      this.c.put(l, Float.floatToRawIntBits((float)(x + this.k) + Float.intBitsToFloat(this.c.get(l))));
      this.c.put(i1, Float.floatToRawIntBits((float)(y + this.l) + Float.intBitsToFloat(this.c.get(i1))));
      this.c.put(j1, Float.floatToRawIntBits((float)(z + this.m) + Float.intBitsToFloat(this.c.get(j1))));
    }
  }
  
  public int c(int p_78909_1_)
  {
    return ((this.f - p_78909_1_) * this.n.g() + this.n.e()) / 4;
  }
  
  public void a(float red, float green, float blue, int p_178978_4_)
  {
    int i = c(p_178978_4_);
    int j = -1;
    if (!this.i)
    {
      j = this.c.get(i);
      if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN)
      {
        int k = (int)((j & 0xFF) * red);
        int l = (int)((j >> 8 & 0xFF) * green);
        int i1 = (int)((j >> 16 & 0xFF) * blue);
        j &= 0xFF000000;
        j = j | i1 << 16 | l << 8 | k;
      }
      else
      {
        int j1 = (int)((j >> 24 & 0xFF) * red);
        int k1 = (int)((j >> 16 & 0xFF) * green);
        int l1 = (int)((j >> 8 & 0xFF) * blue);
        j &= 0xFF;
        j = j | j1 << 24 | k1 << 16 | l1 << 8;
      }
    }
    this.c.put(i, j);
  }
  
  private void b(int argb, int p_178988_2_)
  {
    int i = c(p_178988_2_);
    int j = argb >> 16 & 0xFF;
    int k = argb >> 8 & 0xFF;
    int l = argb & 0xFF;
    int i1 = argb >> 24 & 0xFF;
    a(i, j, k, l, i1);
  }
  
  public void b(float red, float green, float blue, int p_178994_4_)
  {
    int i = c(p_178994_4_);
    int j = on.a((int)(red * 255.0F), 0, 255);
    int k = on.a((int)(green * 255.0F), 0, 255);
    int l = on.a((int)(blue * 255.0F), 0, 255);
    a(i, j, k, l, 255);
  }
  
  public void a(int index, int red, int p_178972_3_, int p_178972_4_, int p_178972_5_)
  {
    if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
      this.c.put(index, p_178972_5_ << 24 | p_178972_4_ << 16 | p_178972_3_ << 8 | red);
    } else {
      this.c.put(index, red << 24 | p_178972_3_ << 16 | p_178972_4_ << 8 | p_178972_5_);
    }
  }
  
  public void c()
  {
    this.i = true;
  }
  
  public bmz a(float red, float green, float blue, float alpha)
  {
    return b((int)(red * 255.0F), (int)(green * 255.0F), (int)(blue * 255.0F), (int)(alpha * 255.0F));
  }
  
  public bmz b(int red, int green, int blue, int alpha)
  {
    if (this.i) {
      return this;
    }
    int i = this.f * this.n.g() + this.n.d(this.h);
    switch (this.g.a())
    {
    case a: 
      this.b.putFloat(i, red / 255.0F);
      this.b.putFloat(i + 4, green / 255.0F);
      this.b.putFloat(i + 8, blue / 255.0F);
      this.b.putFloat(i + 12, alpha / 255.0F);
      break;
    case f: 
    case g: 
      this.b.putFloat(i, red);
      this.b.putFloat(i + 4, green);
      this.b.putFloat(i + 8, blue);
      this.b.putFloat(i + 12, alpha);
      break;
    case d: 
    case e: 
      this.b.putShort(i, (short)red);
      this.b.putShort(i + 2, (short)green);
      this.b.putShort(i + 4, (short)blue);
      this.b.putShort(i + 6, (short)alpha);
      break;
    case b: 
    case c: 
      if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN)
      {
        this.b.put(i, (byte)red);
        this.b.put(i + 1, (byte)green);
        this.b.put(i + 2, (byte)blue);
        this.b.put(i + 3, (byte)alpha);
      }
      else
      {
        this.b.put(i, (byte)alpha);
        this.b.put(i + 1, (byte)blue);
        this.b.put(i + 2, (byte)green);
        this.b.put(i + 3, (byte)red);
      }
      break;
    }
    k();
    return this;
  }
  
  public void a(int[] vertexData)
  {
    if (Config.isShaders()) {
      SVertexBuilder.beginAddVertexData(this, vertexData);
    }
    b(vertexData.length);
    this.c.position(j());
    this.c.put(vertexData);
    this.f += vertexData.length / this.n.f();
    if (Config.isShaders()) {
      SVertexBuilder.endAddVertexData(this);
    }
  }
  
  public void d()
  {
    this.f += 1;
    b(this.n.f());
    
    this.h = 0;
    this.g = this.n.c(this.h);
    if (Config.isShaders()) {
      SVertexBuilder.endAddVertex(this);
    }
  }
  
  public bmz b(double x, double y, double z)
  {
    if (Config.isShaders()) {
      SVertexBuilder.beginAddVertex(this);
    }
    int i = this.f * this.n.g() + this.n.d(this.h);
    switch (this.g.a())
    {
    case a: 
      this.b.putFloat(i, (float)(x + this.k));
      this.b.putFloat(i + 4, (float)(y + this.l));
      this.b.putFloat(i + 8, (float)(z + this.m));
      break;
    case f: 
    case g: 
      this.b.putInt(i, Float.floatToRawIntBits((float)(x + this.k)));
      this.b.putInt(i + 4, Float.floatToRawIntBits((float)(y + this.l)));
      this.b.putInt(i + 8, Float.floatToRawIntBits((float)(z + this.m)));
      break;
    case d: 
    case e: 
      this.b.putShort(i, (short)(int)(x + this.k));
      this.b.putShort(i + 2, (short)(int)(y + this.l));
      this.b.putShort(i + 4, (short)(int)(z + this.m));
      break;
    case b: 
    case c: 
      this.b.put(i, (byte)(int)(x + this.k));
      this.b.put(i + 1, (byte)(int)(y + this.l));
      this.b.put(i + 2, (byte)(int)(z + this.m));
    }
    k();
    return this;
  }
  
  public void b(float x, float y, float z)
  {
    int i = (byte)(int)(x * 127.0F) & 0xFF;
    int j = (byte)(int)(y * 127.0F) & 0xFF;
    int k = (byte)(int)(z * 127.0F) & 0xFF;
    int l = i | j << 8 | k << 16;
    int i1 = this.n.g() >> 2;
    int j1 = (this.f - 4) * i1 + this.n.c() / 4;
    this.c.put(j1, l);
    this.c.put(j1 + i1, l);
    this.c.put(j1 + i1 * 2, l);
    this.c.put(j1 + i1 * 3, l);
  }
  
  private void k()
  {
    this.h += 1;
    this.h %= this.n.i();
    this.g = this.n.c(this.h);
    if (this.g.b() == bvs.b.g) {
      k();
    }
  }
  
  public bmz c(float p_181663_1_, float p_181663_2_, float p_181663_3_)
  {
    int i = this.f * this.n.g() + this.n.d(this.h);
    switch (this.g.a())
    {
    case a: 
      this.b.putFloat(i, p_181663_1_);
      this.b.putFloat(i + 4, p_181663_2_);
      this.b.putFloat(i + 8, p_181663_3_);
      break;
    case f: 
    case g: 
      this.b.putInt(i, (int)p_181663_1_);
      this.b.putInt(i + 4, (int)p_181663_2_);
      this.b.putInt(i + 8, (int)p_181663_3_);
      break;
    case d: 
    case e: 
      this.b.putShort(i, (short)((int)(p_181663_1_ * 32767.0F) & 0xFFFF));
      this.b.putShort(i + 2, (short)((int)(p_181663_2_ * 32767.0F) & 0xFFFF));
      this.b.putShort(i + 4, (short)((int)(p_181663_3_ * 32767.0F) & 0xFFFF));
      break;
    case b: 
    case c: 
      this.b.put(i, (byte)((int)(p_181663_1_ * 127.0F) & 0xFF));
      this.b.put(i + 1, (byte)((int)(p_181663_2_ * 127.0F) & 0xFF));
      this.b.put(i + 2, (byte)((int)(p_181663_3_ * 127.0F) & 0xFF));
    }
    k();
    return this;
  }
  
  public void c(double x, double y, double z)
  {
    this.k = x;
    this.l = y;
    this.m = z;
  }
  
  public void e()
  {
    if (!this.o) {
      throw new IllegalStateException("Not building!");
    }
    this.o = false;
    this.b.position(0);
    this.b.limit(j() * 4);
  }
  
  public ByteBuffer f()
  {
    return this.b;
  }
  
  public bvr g()
  {
    return this.n;
  }
  
  public int h()
  {
    return this.f;
  }
  
  public int i()
  {
    return this.j;
  }
  
  public void a(int argb)
  {
    for (int i = 0; i < 4; i++) {
      b(argb, i + 1);
    }
  }
  
  public void d(float red, float green, float blue)
  {
    for (int i = 0; i < 4; i++) {
      b(red, green, blue, i + 1);
    }
  }
  
  public void putSprite(bvh sprite)
  {
    if (this.quadSprites == null) {
      return;
    }
    int countQuads = this.f / 4;
    
    this.quadSprites[(countQuads - 1)] = sprite;
  }
  
  public void setSprite(bvh sprite)
  {
    if (this.quadSprites == null) {
      return;
    }
    this.quadSprite = sprite;
  }
  
  public boolean isMultiTexture()
  {
    return this.quadSprites != null;
  }
  
  public void drawMultiTexture()
  {
    if (this.quadSprites == null) {
      return;
    }
    int maxTextureIndex = Config.getMinecraft().R().getCountRegisteredSprites();
    if (this.drawnIcons.length <= maxTextureIndex) {
      this.drawnIcons = new boolean[maxTextureIndex + 1];
    }
    Arrays.fill(this.drawnIcons, false);
    
    int texSwitch = 0;
    int grassOverlayIndex = -1;
    int countQuads = this.f / 4;
    for (int i = 0; i < countQuads; i++)
    {
      bvh icon = this.quadSprites[i];
      if (icon != null)
      {
        int iconIndex = icon.getIndexInMap();
        if (this.drawnIcons[iconIndex] == 0) {
          if (icon == TextureUtils.iconGrassSideOverlay)
          {
            if (grassOverlayIndex < 0) {
              grassOverlayIndex = i;
            }
          }
          else
          {
            i = drawForIcon(icon, i) - 1;
            texSwitch++;
            if (this.blockLayer != ahm.d) {
              this.drawnIcons[iconIndex] = true;
            }
          }
        }
      }
    }
    if (grassOverlayIndex >= 0)
    {
      drawForIcon(TextureUtils.iconGrassSideOverlay, grassOverlayIndex);
      texSwitch++;
    }
    if (texSwitch > 0) {}
  }
  
  private int drawForIcon(bvh sprite, int startQuadPos)
  {
    GL11.glBindTexture(3553, sprite.glSpriteTextureId);
    
    int firstRegionEnd = -1;
    int lastPos = -1;
    int countQuads = this.f / 4;
    for (int i = startQuadPos; i < countQuads; i++)
    {
      bvh ts = this.quadSprites[i];
      if (ts == sprite)
      {
        if (lastPos < 0) {
          lastPos = i;
        }
      }
      else if (lastPos >= 0)
      {
        draw(lastPos, i);
        if (this.blockLayer == ahm.d) {
          return i;
        }
        lastPos = -1;
        if (firstRegionEnd < 0) {
          firstRegionEnd = i;
        }
      }
    }
    if (lastPos >= 0) {
      draw(lastPos, countQuads);
    }
    if (firstRegionEnd < 0) {
      firstRegionEnd = countQuads;
    }
    return firstRegionEnd;
  }
  
  private void draw(int startQuadVertex, int endQuadVertex)
  {
    int vxQuadCount = endQuadVertex - startQuadVertex;
    if (vxQuadCount <= 0) {
      return;
    }
    int startVertex = startQuadVertex * 4;
    int vxCount = vxQuadCount * 4;
    
    GL11.glDrawArrays(this.j, startVertex, vxCount);
  }
  
  public void setBlockLayer(ahm blockLayer)
  {
    this.blockLayer = blockLayer;
    if (blockLayer == null)
    {
      this.quadSprites = null;
      this.quadSprite = null;
    }
  }
  
  private int getBufferQuadSize()
  {
    int quadSize = this.c.capacity() * 4 / (this.n.g() * 4);
    return quadSize;
  }
  
  public void checkAndGrow()
  {
    b(this.n.g());
  }
  
  public boolean isColorDisabled()
  {
    return this.i;
  }
  
  public class a
  {
    private final int[] b;
    private final bvr c;
    private bvh[] stateQuadSprites;
    
    public a(int[] buffer, bvr format, bvh[] quadSprites)
    {
      this.b = buffer;
      this.c = format;
      this.stateQuadSprites = quadSprites;
    }
    
    public int[] a()
    {
      return this.b;
    }
    
    public int b()
    {
      return this.b.length / this.c.f();
    }
    
    public bvr c()
    {
      return this.c;
    }
  }
}
