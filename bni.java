import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.vector.Quaternion;

public class bni
{
  private static final FloatBuffer a = BufferUtils.createFloatBuffer(16);
  private static final FloatBuffer b = BufferUtils.createFloatBuffer(4);
  private static bni.a c = new bni.a(null);
  private static bni.c d = new bni.c(2896);
  private static bni.c[] e = new bni.c[8];
  public static boolean clearEnabled = true;
  
  public static void a()
  {
    GL11.glPushAttrib(8256);
  }
  
  public static void c() {}
  
  public static void d()
  {
    c.a.a();
  }
  
  public static void e()
  {
    c.a.b();
  }
  
  public static void a(int func, float ref)
  {
    if ((func != c.b) || (ref != c.c))
    {
      c.b = func;
      c.c = ref;
      GL11.glAlphaFunc(func, ref);
    }
  }
  
  public static void f()
  {
    d.b();
  }
  
  public static void g()
  {
    d.a();
  }
  
  public static void a(int light)
  {
    e[light].b();
  }
  
  public static void b(int light)
  {
    e[light].a();
  }
  
  public static void h()
  {
    f.a.b();
  }
  
  public static void i()
  {
    f.a.a();
  }
  
  public static void a(int face, int mode)
  {
    if ((face != f.b) || (mode != f.c))
    {
      f.b = face;
      f.c = mode;
      GL11.glColorMaterial(face, mode);
    }
  }
  
  public static void a(int p_187438_0_, int p_187438_1_, FloatBuffer p_187438_2_)
  {
    GL11.glLight(p_187438_0_, p_187438_1_, p_187438_2_);
  }
  
  public static void a(int p_187424_0_, FloatBuffer p_187424_1_)
  {
    GL11.glLightModel(p_187424_0_, p_187424_1_);
  }
  
  public static void a(float p_187432_0_, float p_187432_1_, float p_187432_2_)
  {
    GL11.glNormal3f(p_187432_0_, p_187432_1_, p_187432_2_);
  }
  
  public static void j()
  {
    h.a.a();
  }
  
  public static void k()
  {
    h.a.b();
  }
  
  public static void c(int depthFunc)
  {
    if (depthFunc != h.c)
    {
      h.c = depthFunc;
      GL11.glDepthFunc(depthFunc);
    }
  }
  
  public static void a(boolean flagIn)
  {
    if (flagIn != h.b)
    {
      h.b = flagIn;
      GL11.glDepthMask(flagIn);
    }
  }
  
  public static void l()
  {
    g.a.a();
  }
  
  public static void m()
  {
    g.a.b();
  }
  
  public static void a(bni.r p_187401_0_, bni.l p_187401_1_)
  {
    b(p_187401_0_.p, p_187401_1_.o);
  }
  
  public static void b(int srcFactor, int dstFactor)
  {
    if ((srcFactor != g.b) || (dstFactor != g.c))
    {
      g.b = srcFactor;
      g.c = dstFactor;
      GL11.glBlendFunc(srcFactor, dstFactor);
    }
  }
  
  public static void a(bni.r p_187428_0_, bni.l p_187428_1_, bni.r p_187428_2_, bni.l p_187428_3_)
  {
    a(p_187428_0_.p, p_187428_1_.o, p_187428_2_.p, p_187428_3_.o);
  }
  
  public static void a(int srcFactor, int dstFactor, int srcFactorAlpha, int dstFactorAlpha)
  {
    if ((srcFactor != g.b) || (dstFactor != g.c) || (srcFactorAlpha != g.d) || (dstFactorAlpha != g.e))
    {
      g.b = srcFactor;
      g.c = dstFactor;
      g.d = srcFactorAlpha;
      g.e = dstFactorAlpha;
      bzg.c(srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
    }
  }
  
  public static void d(int blendEquation)
  {
    GL14.glBlendEquation(blendEquation);
  }
  
  public static void e(int p_187431_0_)
  {
    b.put(0, (p_187431_0_ >> 16 & 0xFF) / 255.0F);
    b.put(1, (p_187431_0_ >> 8 & 0xFF) / 255.0F);
    b.put(2, (p_187431_0_ >> 0 & 0xFF) / 255.0F);
    b.put(3, (p_187431_0_ >> 24 & 0xFF) / 255.0F);
    b(8960, 8705, b);
    a(8960, 8704, 34160);
    a(8960, 34161, 7681);
    a(8960, 34176, 34166);
    a(8960, 34192, 768);
    a(8960, 34162, 7681);
    a(8960, 34184, 5890);
    a(8960, 34200, 770);
  }
  
  public static void n()
  {
    a(8960, 8704, 8448);
    a(8960, 34161, 8448);
    a(8960, 34162, 8448);
    a(8960, 34176, 5890);
    a(8960, 34184, 5890);
    a(8960, 34192, 768);
    a(8960, 34200, 770);
  }
  
  public static void o()
  {
    i.a.b();
  }
  
  public static void p()
  {
    i.a.a();
  }
  
  public static void a(bni.m fogMode)
  {
    w(fogMode.d);
  }
  
  private static void w(int param)
  {
    if (param != i.b)
    {
      i.b = param;
      GL11.glFogi(2917, param);
    }
  }
  
  public static void a(float param)
  {
    if (param != i.c)
    {
      i.c = param;
      GL11.glFogf(2914, param);
    }
  }
  
  public static void b(float param)
  {
    if (param != i.d)
    {
      i.d = param;
      GL11.glFogf(2915, param);
    }
  }
  
  public static void c(float param)
  {
    if (param != i.e)
    {
      i.e = param;
      GL11.glFogf(2916, param);
    }
  }
  
  public static void b(int pname, FloatBuffer param)
  {
    GL11.glFog(pname, param);
  }
  
  public static void c(int pname, int param)
  {
    GL11.glFogi(pname, param);
  }
  
  public static void q()
  {
    j.a.b();
  }
  
  public static void r()
  {
    j.a.a();
  }
  
  public static void a(bni.i cullFace)
  {
    x(cullFace.d);
  }
  
  private static void x(int mode)
  {
    if (mode != j.b)
    {
      j.b = mode;
      GL11.glCullFace(mode);
    }
  }
  
  public static void d(int face, int mode)
  {
    GL11.glPolygonMode(face, mode);
  }
  
  public static void s()
  {
    k.a.b();
  }
  
  public static void t()
  {
    k.a.a();
  }
  
  public static void a(float factor, float units)
  {
    if ((factor != k.c) || (units != k.d))
    {
      k.c = factor;
      k.d = units;
      GL11.glPolygonOffset(factor, units);
    }
  }
  
  public static void w()
  {
    l.a.b();
  }
  
  public static void x()
  {
    l.a.a();
  }
  
  public static void a(bni.o logicOperation)
  {
    f(logicOperation.q);
  }
  
  public static void f(int opcode)
  {
    if (opcode != l.b)
    {
      l.b = opcode;
      GL11.glLogicOp(opcode);
    }
  }
  
  public static void a(bni.u p_179087_0_)
  {
    c(p_179087_0_).a.b();
  }
  
  public static void b(bni.u p_179100_0_)
  {
    c(p_179100_0_).a.a();
  }
  
  public static void a(bni.u texGen, int param)
  {
    bni.v glstatemanager$texgencoord = c(texGen);
    if (param != glstatemanager$texgencoord.c)
    {
      glstatemanager$texgencoord.c = param;
      GL11.glTexGeni(glstatemanager$texgencoord.b, 9472, param);
    }
  }
  
  public static void a(bni.u p_179105_0_, int pname, FloatBuffer params)
  {
    GL11.glTexGen(c(p_179105_0_).b, pname, params);
  }
  
  private static bni.v c(bni.u p_179125_0_)
  {
    switch (p_179125_0_)
    {
    case a: 
      return m.a;
    case b: 
      return m.b;
    case c: 
      return m.c;
    case d: 
      return m.d;
    }
    return m.a;
  }
  
  public static void g(int texture)
  {
    if (q != texture - bzg.q)
    {
      q = texture - bzg.q;
      bzg.k(texture);
    }
  }
  
  public static void y()
  {
    r[q].a.b();
  }
  
  public static void z()
  {
    r[q].a.a();
  }
  
  public static void b(int p_187448_0_, int p_187448_1_, FloatBuffer p_187448_2_)
  {
    GL11.glTexEnv(p_187448_0_, p_187448_1_, p_187448_2_);
  }
  
  public static void a(int p_187399_0_, int p_187399_1_, int p_187399_2_)
  {
    GL11.glTexEnvi(p_187399_0_, p_187399_1_, p_187399_2_);
  }
  
  public static void a(int p_187436_0_, int p_187436_1_, float p_187436_2_)
  {
    GL11.glTexEnvf(p_187436_0_, p_187436_1_, p_187436_2_);
  }
  
  public static void b(int p_187403_0_, int p_187403_1_, float p_187403_2_)
  {
    GL11.glTexParameterf(p_187403_0_, p_187403_1_, p_187403_2_);
  }
  
  public static void b(int p_187421_0_, int p_187421_1_, int p_187421_2_)
  {
    GL11.glTexParameteri(p_187421_0_, p_187421_1_, p_187421_2_);
  }
  
  public static int c(int p_187411_0_, int p_187411_1_, int p_187411_2_)
  {
    return GL11.glGetTexLevelParameteri(p_187411_0_, p_187411_1_, p_187411_2_);
  }
  
  public static int A()
  {
    return GL11.glGenTextures();
  }
  
  public static void h(int texture)
  {
    if (texture == 0) {
      return;
    }
    GL11.glDeleteTextures(texture);
    for (bni.x glstatemanager$texturestate : r) {
      if (glstatemanager$texturestate.b == texture) {
        glstatemanager$texturestate.b = 0;
      }
    }
  }
  
  public static void i(int texture)
  {
    if (texture != r[q].b)
    {
      r[q].b = texture;
      GL11.glBindTexture(3553, texture);
    }
  }
  
  public static void a(int p_187419_0_, int p_187419_1_, int p_187419_2_, int p_187419_3_, int p_187419_4_, int p_187419_5_, int p_187419_6_, int p_187419_7_, IntBuffer p_187419_8_)
  {
    GL11.glTexImage2D(p_187419_0_, p_187419_1_, p_187419_2_, p_187419_3_, p_187419_4_, p_187419_5_, p_187419_6_, p_187419_7_, p_187419_8_);
  }
  
  public static void b(int p_187414_0_, int p_187414_1_, int p_187414_2_, int p_187414_3_, int p_187414_4_, int p_187414_5_, int p_187414_6_, int p_187414_7_, IntBuffer p_187414_8_)
  {
    GL11.glTexSubImage2D(p_187414_0_, p_187414_1_, p_187414_2_, p_187414_3_, p_187414_4_, p_187414_5_, p_187414_6_, p_187414_7_, p_187414_8_);
  }
  
  public static void a(int p_187443_0_, int p_187443_1_, int p_187443_2_, int p_187443_3_, int p_187443_4_, int p_187443_5_, int p_187443_6_, int p_187443_7_)
  {
    GL11.glCopyTexSubImage2D(p_187443_0_, p_187443_1_, p_187443_2_, p_187443_3_, p_187443_4_, p_187443_5_, p_187443_6_, p_187443_7_);
  }
  
  public static void a(int p_187433_0_, int p_187433_1_, int p_187433_2_, int p_187433_3_, IntBuffer p_187433_4_)
  {
    GL11.glGetTexImage(p_187433_0_, p_187433_1_, p_187433_2_, p_187433_3_, p_187433_4_);
  }
  
  public static void B()
  {
    p.b();
  }
  
  public static void C()
  {
    p.a();
  }
  
  public static void j(int mode)
  {
    if (mode != s)
    {
      s = mode;
      GL11.glShadeModel(mode);
    }
  }
  
  public static void D()
  {
    t.b();
  }
  
  public static void E()
  {
    t.a();
  }
  
  public static void b(int x, int y, int width, int height)
  {
    GL11.glViewport(x, y, width, height);
  }
  
  public static void a(boolean red, boolean green, boolean blue, boolean alpha)
  {
    if ((red != u.a) || (green != u.b) || (blue != u.c) || (alpha != u.d))
    {
      u.a = red;
      u.b = green;
      u.c = blue;
      u.d = alpha;
      GL11.glColorMask(red, green, blue, alpha);
    }
  }
  
  public static void a(double depth)
  {
    if (depth != n.a)
    {
      n.a = depth;
      GL11.glClearDepth(depth);
    }
  }
  
  public static void a(float red, float green, float blue, float alpha)
  {
    if ((red != n.b.a) || (green != n.b.b) || (blue != n.b.c) || (alpha != n.b.d))
    {
      n.b.a = red;
      n.b.b = green;
      n.b.c = blue;
      n.b.d = alpha;
      GL11.glClearColor(red, green, blue, alpha);
    }
  }
  
  public static void m(int mask)
  {
    if (!clearEnabled) {
      return;
    }
    GL11.glClear(mask);
  }
  
  public static void n(int mode)
  {
    GL11.glMatrixMode(mode);
  }
  
  public static void F() {}
  
  public static void G() {}
  
  public static void H() {}
  
  public static void c(int pname, FloatBuffer params)
  {
    GL11.glGetFloat(pname, params);
  }
  
  public static void a(double left, double right, double bottom, double top, double zNear, double zFar)
  {
    GL11.glOrtho(left, right, bottom, top, zNear, zFar);
  }
  
  public static void b(float angle, float x, float y, float z)
  {
    GL11.glRotatef(angle, x, y, z);
  }
  
  public static void b(float x, float y, float z)
  {
    GL11.glScalef(x, y, z);
  }
  
  public static void a(double x, double y, double z)
  {
    GL11.glScaled(x, y, z);
  }
  
  public static void c(float x, float y, float z)
  {
    GL11.glTranslatef(x, y, z);
  }
  
  public static void b(double x, double y, double z)
  {
    GL11.glTranslated(x, y, z);
  }
  
  public static void a(FloatBuffer matrix)
  {
    GL11.glMultMatrix(matrix);
  }
  
  public static void a(Quaternion p_187444_0_)
  {
    a(a(a, p_187444_0_));
  }
  
  public static FloatBuffer a(FloatBuffer p_187418_0_, Quaternion p_187418_1_)
  {
    p_187418_0_.clear();
    float f = p_187418_1_.x * p_187418_1_.x;
    float f1 = p_187418_1_.x * p_187418_1_.y;
    float f2 = p_187418_1_.x * p_187418_1_.z;
    float f3 = p_187418_1_.x * p_187418_1_.w;
    float f4 = p_187418_1_.y * p_187418_1_.y;
    float f5 = p_187418_1_.y * p_187418_1_.z;
    float f6 = p_187418_1_.y * p_187418_1_.w;
    float f7 = p_187418_1_.z * p_187418_1_.z;
    float f8 = p_187418_1_.z * p_187418_1_.w;
    p_187418_0_.put(1.0F - 2.0F * (f4 + f7));
    p_187418_0_.put(2.0F * (f1 + f8));
    p_187418_0_.put(2.0F * (f2 - f6));
    p_187418_0_.put(0.0F);
    p_187418_0_.put(2.0F * (f1 - f8));
    p_187418_0_.put(1.0F - 2.0F * (f + f7));
    p_187418_0_.put(2.0F * (f5 + f3));
    p_187418_0_.put(0.0F);
    p_187418_0_.put(2.0F * (f2 + f6));
    p_187418_0_.put(2.0F * (f5 - f3));
    p_187418_0_.put(1.0F - 2.0F * (f + f4));
    p_187418_0_.put(0.0F);
    p_187418_0_.put(0.0F);
    p_187418_0_.put(0.0F);
    p_187418_0_.put(0.0F);
    p_187418_0_.put(1.0F);
    p_187418_0_.rewind();
    return p_187418_0_;
  }
  
  public static void c(float colorRed, float colorGreen, float colorBlue, float colorAlpha)
  {
    if ((colorRed != v.a) || (colorGreen != v.b) || (colorBlue != v.c) || (colorAlpha != v.d))
    {
      v.a = colorRed;
      v.b = colorGreen;
      v.c = colorBlue;
      v.d = colorAlpha;
      GL11.glColor4f(colorRed, colorGreen, colorBlue, colorAlpha);
    }
  }
  
  public static void d(float colorRed, float colorGreen, float colorBlue)
  {
    c(colorRed, colorGreen, colorBlue, 1.0F);
  }
  
  public static void b(float p_187426_0_, float p_187426_1_)
  {
    GL11.glTexCoord2f(p_187426_0_, p_187426_1_);
  }
  
  public static void e(float p_187435_0_, float p_187435_1_, float p_187435_2_)
  {
    GL11.glVertex3f(p_187435_0_, p_187435_1_, p_187435_2_);
  }
  
  public static void I()
  {
    v.a = (v.b = v.c = v.d = -1.0F);
  }
  
  public static void a(int p_187446_0_, int p_187446_1_, ByteBuffer p_187446_2_)
  {
    GL11.glNormalPointer(p_187446_0_, p_187446_1_, p_187446_2_);
  }
  
  public static void c(int p_187405_0_, int p_187405_1_, int p_187405_2_, int p_187405_3_)
  {
    GL11.glTexCoordPointer(p_187405_0_, p_187405_1_, p_187405_2_, p_187405_3_);
  }
  
  public static void a(int p_187404_0_, int p_187404_1_, int p_187404_2_, ByteBuffer p_187404_3_)
  {
    GL11.glTexCoordPointer(p_187404_0_, p_187404_1_, p_187404_2_, p_187404_3_);
  }
  
  public static void d(int p_187420_0_, int p_187420_1_, int p_187420_2_, int p_187420_3_)
  {
    GL11.glVertexPointer(p_187420_0_, p_187420_1_, p_187420_2_, p_187420_3_);
  }
  
  public static void b(int p_187427_0_, int p_187427_1_, int p_187427_2_, ByteBuffer p_187427_3_)
  {
    GL11.glVertexPointer(p_187427_0_, p_187427_1_, p_187427_2_, p_187427_3_);
  }
  
  public static void e(int p_187406_0_, int p_187406_1_, int p_187406_2_, int p_187406_3_)
  {
    GL11.glColorPointer(p_187406_0_, p_187406_1_, p_187406_2_, p_187406_3_);
  }
  
  public static void c(int p_187400_0_, int p_187400_1_, int p_187400_2_, ByteBuffer p_187400_3_)
  {
    GL11.glColorPointer(p_187400_0_, p_187400_1_, p_187400_2_, p_187400_3_);
  }
  
  public static void p(int p_187429_0_)
  {
    GL11.glDisableClientState(p_187429_0_);
  }
  
  public static void q(int p_187410_0_)
  {
    GL11.glEnableClientState(p_187410_0_);
  }
  
  public static void r(int p_187447_0_)
  {
    GL11.glBegin(p_187447_0_);
  }
  
  public static void J() {}
  
  public static void f(int p_187439_0_, int p_187439_1_, int p_187439_2_)
  {
    GL11.glDrawArrays(p_187439_0_, p_187439_1_, p_187439_2_);
  }
  
  public static void d(float p_187441_0_)
  {
    GL11.glLineWidth(p_187441_0_);
  }
  
  public static void s(int list)
  {
    GL11.glCallList(list);
  }
  
  public static void e(int p_187449_0_, int p_187449_1_)
  {
    GL11.glDeleteLists(p_187449_0_, p_187449_1_);
  }
  
  public static void f(int p_187423_0_, int p_187423_1_)
  {
    GL11.glNewList(p_187423_0_, p_187423_1_);
  }
  
  public static void K() {}
  
  public static int t(int p_187442_0_)
  {
    return GL11.glGenLists(p_187442_0_);
  }
  
  public static void g(int p_187425_0_, int p_187425_1_)
  {
    GL11.glPixelStorei(p_187425_0_, p_187425_1_);
  }
  
  public static void a(int p_187413_0_, int p_187413_1_, int p_187413_2_, int p_187413_3_, int p_187413_4_, int p_187413_5_, IntBuffer p_187413_6_)
  {
    GL11.glReadPixels(p_187413_0_, p_187413_1_, p_187413_2_, p_187413_3_, p_187413_4_, p_187413_5_, p_187413_6_);
  }
  
  public static int L()
  {
    return GL11.glGetError();
  }
  
  public static String u(int p_187416_0_)
  {
    return GL11.glGetString(p_187416_0_);
  }
  
  public static void a(int p_187445_0_, IntBuffer p_187445_1_)
  {
    GL11.glGetInteger(p_187445_0_, p_187445_1_);
  }
  
  public static int v(int p_187397_0_)
  {
    return GL11.glGetInteger(p_187397_0_);
  }
  
  public static void a(bni.q p_187408_0_)
  {
    p_187408_0_.a();
  }
  
  public static void b(bni.q p_187440_0_)
  {
    p_187440_0_.b();
  }
  
  public static int getActiveTextureUnit()
  {
    return bzg.q + q;
  }
  
  public static void bindCurrentTexture()
  {
    GL11.glBindTexture(3553, r[q].b);
  }
  
  public static int getBoundTexture()
  {
    return r[q].b;
  }
  
  public static void checkBoundTexture()
  {
    if (!Config.isMinecraftThread()) {
      return;
    }
    int glAct = GL11.glGetInteger(34016);
    int glTex = GL11.glGetInteger(32873);
    int act = getActiveTextureUnit();
    int tex = getBoundTexture();
    if (tex <= 0) {
      return;
    }
    if ((glAct != act) || (glTex != tex)) {
      Config.dbg("checkTexture: act: " + act + ", glAct: " + glAct + ", tex: " + tex + ", glTex: " + glTex);
    }
  }
  
  public static void deleteTextures(IntBuffer buf)
  {
    buf.rewind();
    while (buf.position() < buf.limit())
    {
      int texId = buf.get();
      h(texId);
    }
    buf.rewind();
  }
  
  static
  {
    for (int i = 0; i < 8; i++) {
      e[i] = new bni.c(16384 + i);
    }
    f = new bni.h(null);
    g = new bni.b(null);
    h = new bni.k(null);
    i = new bni.n(null);
    j = new bni.j(null);
    k = new bni.p(null);
    l = new bni.f(null);
    m = new bni.w(null);
    n = new bni.d(null);
    o = new bni.t(null);
    p = new bni.c(2977);
    q = 0;
    
    r = new bni.x[32];
    for (int j = 0; j < r.length; j++) {
      r[j] = new bni.x(null);
    }
  }
  
  private static int s = 7425;
  private static bni.c t = new bni.c(32826);
  private static bni.g u = new bni.g(null);
  private static bni.e v = new bni.e();
  private static bni.h f;
  private static bni.b g;
  private static bni.k h;
  private static bni.n i;
  private static bni.j j;
  private static bni.p k;
  private static bni.f l;
  private static bni.w m;
  private static bni.d n;
  private static bni.t o;
  private static bni.c p;
  private static int q;
  private static bni.x[] r;
  
  static class a
  {
    public bni.c a;
    public int b;
    public float c;
    
    private a()
    {
      this.a = new bni.c(3008);
      this.b = 519;
      this.c = -1.0F;
    }
  }
  
  static class b
  {
    public bni.c a;
    public int b;
    public int c;
    public int d;
    public int e;
    
    private b()
    {
      this.a = new bni.c(3042);
      this.b = 1;
      this.c = 0;
      this.d = 1;
      this.e = 0;
    }
  }
  
  static class c
  {
    private final int a;
    private boolean b = false;
    
    public c(int capabilityIn)
    {
      this.a = capabilityIn;
    }
    
    public void a()
    {
      a(false);
    }
    
    public void b()
    {
      a(true);
    }
    
    public void a(boolean state)
    {
      if (state != this.b)
      {
        this.b = state;
        if (state) {
          GL11.glEnable(this.a);
        } else {
          GL11.glDisable(this.a);
        }
      }
    }
  }
  
  static class d
  {
    public double a;
    public bni.e b;
    public int c;
    
    private d()
    {
      this.a = 1.0D;
      this.b = new bni.e(0.0F, 0.0F, 0.0F, 0.0F);
      this.c = 0;
    }
  }
  
  static class e
  {
    public float a = 1.0F;
    public float b = 1.0F;
    public float c = 1.0F;
    public float d = 1.0F;
    
    public e() {}
    
    public e(float redIn, float greenIn, float blueIn, float alphaIn)
    {
      this.a = redIn;
      this.b = greenIn;
      this.c = blueIn;
      this.d = alphaIn;
    }
  }
  
  static class f
  {
    public bni.c a;
    public int b;
    
    private f()
    {
      this.a = new bni.c(3058);
      this.b = 5379;
    }
  }
  
  static class g
  {
    public boolean a;
    public boolean b;
    public boolean c;
    public boolean d;
    
    private g()
    {
      this.a = true;
      this.b = true;
      this.c = true;
      this.d = true;
    }
  }
  
  static class h
  {
    public bni.c a;
    public int b;
    public int c;
    
    private h()
    {
      this.a = new bni.c(2903);
      this.b = 1032;
      this.c = 5634;
    }
  }
  
  public static enum i
  {
    public final int d;
    
    private i(int modeIn)
    {
      this.d = modeIn;
    }
  }
  
  static class j
  {
    public bni.c a;
    public int b;
    
    private j()
    {
      this.a = new bni.c(2884);
      this.b = 1029;
    }
  }
  
  static class k
  {
    public bni.c a;
    public boolean b;
    public int c;
    
    private k()
    {
      this.a = new bni.c(2929);
      this.b = true;
      this.c = 513;
    }
  }
  
  public static enum l
  {
    public final int o;
    
    private l(int p_i46519_3_)
    {
      this.o = p_i46519_3_;
    }
  }
  
  public static enum m
  {
    public final int d;
    
    private m(int capabilityIn)
    {
      this.d = capabilityIn;
    }
  }
  
  static class n
  {
    public bni.c a;
    public int b;
    public float c;
    public float d;
    public float e;
    
    private n()
    {
      this.a = new bni.c(2912);
      this.b = 2048;
      this.c = 1.0F;
      this.d = 0.0F;
      this.e = 1.0F;
    }
  }
  
  public static enum o
  {
    public final int q;
    
    private o(int opcodeIn)
    {
      this.q = opcodeIn;
    }
  }
  
  static class p
  {
    public bni.c a;
    public bni.c b;
    public float c;
    public float d;
    
    private p()
    {
      this.a = new bni.c(32823);
      this.b = new bni.c(10754);
      this.c = 0.0F;
      this.d = 0.0F;
    }
  }
  
  public static abstract enum q
  {
    private q() {}
    
    public abstract void a();
    
    public abstract void b();
  }
  
  public static enum r
  {
    public final int p;
    
    private r(int factorIn)
    {
      this.p = factorIn;
    }
  }
  
  static class s
  {
    public int a;
    public int b;
    public int c;
    
    private s()
    {
      this.a = 519;
      this.b = 0;
      this.c = -1;
    }
  }
  
  static class t
  {
    public bni.s a;
    public int b;
    public int c;
    public int d;
    public int e;
    
    private t()
    {
      this.a = new bni.s(null);
      this.b = -1;
      this.c = 7680;
      this.d = 7680;
      this.e = 7680;
    }
  }
  
  public static enum u
  {
    private u() {}
  }
  
  static class v
  {
    public bni.c a;
    public int b;
    public int c = -1;
    
    public v(int coordIn, int capabilityIn)
    {
      this.b = coordIn;
      this.a = new bni.c(capabilityIn);
    }
  }
  
  static class w
  {
    public bni.v a;
    public bni.v b;
    public bni.v c;
    public bni.v d;
    
    private w()
    {
      this.a = new bni.v(8192, 3168);
      this.b = new bni.v(8193, 3169);
      this.c = new bni.v(8194, 3170);
      this.d = new bni.v(8195, 3171);
    }
  }
  
  static class x
  {
    public bni.c a;
    public int b;
    
    private x()
    {
      this.a = new bni.c(3553);
      this.b = 0;
    }
  }
}
