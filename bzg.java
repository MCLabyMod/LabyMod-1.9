import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ARBFramebufferObject;
import org.lwjgl.opengl.ARBMultitexture;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexBufferObject;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.EXTBlendFuncSeparate;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Processor;

public class bzg
{
  private static final Logger T = ;
  public static boolean a;
  public static boolean b;
  public static int c;
  public static int d;
  public static int e;
  public static int f;
  public static int g;
  public static int h;
  public static int i;
  public static int j;
  public static int k;
  private static bzg.a U;
  public static boolean l;
  private static boolean V;
  private static boolean W;
  public static int m;
  public static int n;
  public static int o;
  public static int p;
  private static boolean X;
  public static int q;
  public static int r;
  public static int s;
  private static boolean Y;
  public static int t;
  public static int u;
  public static int v;
  public static int w;
  public static int x;
  public static int y;
  public static int z;
  public static int A;
  public static int B;
  public static int C;
  public static int D;
  public static int E;
  public static int F;
  public static int G;
  public static int H;
  public static int I;
  public static int J;
  public static int K;
  public static int L;
  private static boolean Z;
  public static boolean M;
  public static boolean N;
  public static boolean O;
  private static String aa = "";
  private static String ab;
  public static boolean P;
  public static boolean Q;
  private static boolean ac;
  public static int R;
  public static int S;
  public static float lastBrightnessX = 0.0F;
  public static float lastBrightnessY = 0.0F;
  
  public static void a()
  {
    Config.initDisplay();
    
    ContextCapabilities contextcapabilities = GLContext.getCapabilities();
    X = (contextcapabilities.GL_ARB_multitexture) && (!contextcapabilities.OpenGL13);
    Y = (contextcapabilities.GL_ARB_texture_env_combine) && (!contextcapabilities.OpenGL13);
    if (X)
    {
      aa += "Using ARB_multitexture.\n";
      q = 33984;
      r = 33985;
      s = 33986;
    }
    else
    {
      aa += "Using GL 1.3 multitexturing.\n";
      q = 33984;
      r = 33985;
      s = 33986;
    }
    if (Y)
    {
      aa += "Using ARB_texture_env_combine.\n";
      t = 34160;
      u = 34165;
      v = 34167;
      w = 34166;
      x = 34168;
      y = 34161;
      z = 34176;
      A = 34177;
      B = 34178;
      C = 34192;
      D = 34193;
      E = 34194;
      F = 34162;
      G = 34184;
      H = 34185;
      I = 34186;
      J = 34200;
      K = 34201;
      L = 34202;
    }
    else
    {
      aa += "Using GL 1.3 texture combiners.\n";
      t = 34160;
      u = 34165;
      v = 34167;
      w = 34166;
      x = 34168;
      y = 34161;
      z = 34176;
      A = 34177;
      B = 34178;
      C = 34192;
      D = 34193;
      E = 34194;
      F = 34162;
      G = 34184;
      H = 34185;
      I = 34186;
      J = 34200;
      K = 34201;
      L = 34202;
    }
    M = (contextcapabilities.GL_EXT_blend_func_separate) && (!contextcapabilities.OpenGL14);
    Z = (contextcapabilities.OpenGL14) || (contextcapabilities.GL_EXT_blend_func_separate);
    l = (Z) && ((contextcapabilities.GL_ARB_framebuffer_object) || (contextcapabilities.GL_EXT_framebuffer_object) || (contextcapabilities.OpenGL30));
    if (l)
    {
      aa += "Using framebuffer objects because ";
      if (contextcapabilities.OpenGL30)
      {
        aa += "OpenGL 3.0 is supported and separate blending is supported.\n";
        U = bzg.a.a;
        c = 36160;
        d = 36161;
        e = 36064;
        f = 36096;
        g = 36053;
        h = 36054;
        i = 36055;
        j = 36059;
        k = 36060;
      }
      else if (contextcapabilities.GL_ARB_framebuffer_object)
      {
        aa += "ARB_framebuffer_object is supported and separate blending is supported.\n";
        U = bzg.a.b;
        c = 36160;
        d = 36161;
        e = 36064;
        f = 36096;
        g = 36053;
        i = 36055;
        h = 36054;
        j = 36059;
        k = 36060;
      }
      else if (contextcapabilities.GL_EXT_framebuffer_object)
      {
        aa += "EXT_framebuffer_object is supported.\n";
        U = bzg.a.c;
        c = 36160;
        d = 36161;
        e = 36064;
        f = 36096;
        g = 36053;
        i = 36055;
        h = 36054;
        j = 36059;
        k = 36060;
      }
    }
    else
    {
      aa += "Not using framebuffer objects because ";
      aa = aa + "OpenGL 1.4 is " + (contextcapabilities.OpenGL14 ? "" : "not ") + "supported, ";
      aa = aa + "EXT_blend_func_separate is " + (contextcapabilities.GL_EXT_blend_func_separate ? "" : "not ") + "supported, ";
      aa = aa + "OpenGL 3.0 is " + (contextcapabilities.OpenGL30 ? "" : "not ") + "supported, ";
      aa = aa + "ARB_framebuffer_object is " + (contextcapabilities.GL_ARB_framebuffer_object ? "" : "not ") + "supported, and ";
      aa = aa + "EXT_framebuffer_object is " + (contextcapabilities.GL_EXT_framebuffer_object ? "" : "not ") + "supported.\n";
    }
    N = contextcapabilities.OpenGL21;
    V = (N) || ((contextcapabilities.GL_ARB_vertex_shader) && (contextcapabilities.GL_ARB_fragment_shader) && (contextcapabilities.GL_ARB_shader_objects));
    aa = aa + "Shaders are " + (V ? "" : "not ") + "available because ";
    if (V)
    {
      if (contextcapabilities.OpenGL21)
      {
        aa += "OpenGL 2.1 is supported.\n";
        W = false;
        m = 35714;
        n = 35713;
        o = 35633;
        p = 35632;
      }
      else
      {
        aa += "ARB_shader_objects, ARB_vertex_shader, and ARB_fragment_shader are supported.\n";
        W = true;
        m = 35714;
        n = 35713;
        o = 35633;
        p = 35632;
      }
    }
    else
    {
      aa = aa + "OpenGL 2.1 is " + (contextcapabilities.OpenGL21 ? "" : "not ") + "supported, ";
      aa = aa + "ARB_shader_objects is " + (contextcapabilities.GL_ARB_shader_objects ? "" : "not ") + "supported, ";
      aa = aa + "ARB_vertex_shader is " + (contextcapabilities.GL_ARB_vertex_shader ? "" : "not ") + "supported, and ";
      aa = aa + "ARB_fragment_shader is " + (contextcapabilities.GL_ARB_fragment_shader ? "" : "not ") + "supported.\n";
    }
    O = (l) && (V);
    String s = GL11.glGetString(7936).toLowerCase();
    a = s.contains("nvidia");
    ac = (!contextcapabilities.OpenGL15) && (contextcapabilities.GL_ARB_vertex_buffer_object);
    P = (contextcapabilities.OpenGL15) || (ac);
    aa = aa + "VBOs are " + (P ? "" : "not ") + "available because ";
    if (P) {
      if (ac)
      {
        aa += "ARB_vertex_buffer_object is supported.\n";
        S = 35044;
        R = 34962;
      }
      else
      {
        aa += "OpenGL 1.5 is supported.\n";
        S = 35044;
        R = 34962;
      }
    }
    b = s.contains("ati");
    if (b) {
      if (P) {
        Q = true;
      } else {
        bch.a.f.a(16.0F);
      }
    }
    try
    {
      Processor[] aprocessor = new SystemInfo().getHardware().getProcessors();
      ab = String.format("%dx %s", new Object[] { Integer.valueOf(aprocessor.length), aprocessor[0] }).replaceAll("\\s+", " ");
    }
    catch (Throwable var3) {}
  }
  
  public static boolean b()
  {
    return O;
  }
  
  public static String c()
  {
    return aa;
  }
  
  public static int a(int program, int pname)
  {
    return W ? ARBShaderObjects.glGetObjectParameteriARB(program, pname) : GL20.glGetProgrami(program, pname);
  }
  
  public static void b(int program, int shaderIn)
  {
    if (W) {
      ARBShaderObjects.glAttachObjectARB(program, shaderIn);
    } else {
      GL20.glAttachShader(program, shaderIn);
    }
  }
  
  public static void a(int p_153180_0_)
  {
    if (W) {
      ARBShaderObjects.glDeleteObjectARB(p_153180_0_);
    } else {
      GL20.glDeleteShader(p_153180_0_);
    }
  }
  
  public static int b(int type)
  {
    return W ? ARBShaderObjects.glCreateShaderObjectARB(type) : GL20.glCreateShader(type);
  }
  
  public static void a(int shaderIn, ByteBuffer string)
  {
    if (W) {
      ARBShaderObjects.glShaderSourceARB(shaderIn, string);
    } else {
      GL20.glShaderSource(shaderIn, string);
    }
  }
  
  public static void c(int shaderIn)
  {
    if (W) {
      ARBShaderObjects.glCompileShaderARB(shaderIn);
    } else {
      GL20.glCompileShader(shaderIn);
    }
  }
  
  public static int c(int shaderIn, int pname)
  {
    return W ? ARBShaderObjects.glGetObjectParameteriARB(shaderIn, pname) : GL20.glGetShaderi(shaderIn, pname);
  }
  
  public static String d(int shaderIn, int maxLength)
  {
    return W ? ARBShaderObjects.glGetInfoLogARB(shaderIn, maxLength) : GL20.glGetShaderInfoLog(shaderIn, maxLength);
  }
  
  public static String e(int program, int maxLength)
  {
    return W ? ARBShaderObjects.glGetInfoLogARB(program, maxLength) : GL20.glGetProgramInfoLog(program, maxLength);
  }
  
  public static void d(int program)
  {
    if (W) {
      ARBShaderObjects.glUseProgramObjectARB(program);
    } else {
      GL20.glUseProgram(program);
    }
  }
  
  public static int d()
  {
    return W ? ARBShaderObjects.glCreateProgramObjectARB() : GL20.glCreateProgram();
  }
  
  public static void e(int program)
  {
    if (W) {
      ARBShaderObjects.glDeleteObjectARB(program);
    } else {
      GL20.glDeleteProgram(program);
    }
  }
  
  public static void f(int program)
  {
    if (W) {
      ARBShaderObjects.glLinkProgramARB(program);
    } else {
      GL20.glLinkProgram(program);
    }
  }
  
  public static int a(int programObj, CharSequence name)
  {
    return W ? ARBShaderObjects.glGetUniformLocationARB(programObj, name) : GL20.glGetUniformLocation(programObj, name);
  }
  
  public static void a(int location, IntBuffer values)
  {
    if (W) {
      ARBShaderObjects.glUniform1ARB(location, values);
    } else {
      GL20.glUniform1(location, values);
    }
  }
  
  public static void f(int location, int v0)
  {
    if (W) {
      ARBShaderObjects.glUniform1iARB(location, v0);
    } else {
      GL20.glUniform1i(location, v0);
    }
  }
  
  public static void a(int location, FloatBuffer values)
  {
    if (W) {
      ARBShaderObjects.glUniform1ARB(location, values);
    } else {
      GL20.glUniform1(location, values);
    }
  }
  
  public static void b(int location, IntBuffer values)
  {
    if (W) {
      ARBShaderObjects.glUniform2ARB(location, values);
    } else {
      GL20.glUniform2(location, values);
    }
  }
  
  public static void b(int location, FloatBuffer values)
  {
    if (W) {
      ARBShaderObjects.glUniform2ARB(location, values);
    } else {
      GL20.glUniform2(location, values);
    }
  }
  
  public static void c(int location, IntBuffer values)
  {
    if (W) {
      ARBShaderObjects.glUniform3ARB(location, values);
    } else {
      GL20.glUniform3(location, values);
    }
  }
  
  public static void c(int location, FloatBuffer values)
  {
    if (W) {
      ARBShaderObjects.glUniform3ARB(location, values);
    } else {
      GL20.glUniform3(location, values);
    }
  }
  
  public static void d(int location, IntBuffer values)
  {
    if (W) {
      ARBShaderObjects.glUniform4ARB(location, values);
    } else {
      GL20.glUniform4(location, values);
    }
  }
  
  public static void d(int location, FloatBuffer values)
  {
    if (W) {
      ARBShaderObjects.glUniform4ARB(location, values);
    } else {
      GL20.glUniform4(location, values);
    }
  }
  
  public static void a(int location, boolean transpose, FloatBuffer matrices)
  {
    if (W) {
      ARBShaderObjects.glUniformMatrix2ARB(location, transpose, matrices);
    } else {
      GL20.glUniformMatrix2(location, transpose, matrices);
    }
  }
  
  public static void b(int location, boolean transpose, FloatBuffer matrices)
  {
    if (W) {
      ARBShaderObjects.glUniformMatrix3ARB(location, transpose, matrices);
    } else {
      GL20.glUniformMatrix3(location, transpose, matrices);
    }
  }
  
  public static void c(int location, boolean transpose, FloatBuffer matrices)
  {
    if (W) {
      ARBShaderObjects.glUniformMatrix4ARB(location, transpose, matrices);
    } else {
      GL20.glUniformMatrix4(location, transpose, matrices);
    }
  }
  
  public static int b(int p_153164_0_, CharSequence p_153164_1_)
  {
    return W ? ARBVertexShader.glGetAttribLocationARB(p_153164_0_, p_153164_1_) : GL20.glGetAttribLocation(p_153164_0_, p_153164_1_);
  }
  
  public static int e()
  {
    return ac ? ARBVertexBufferObject.glGenBuffersARB() : GL15.glGenBuffers();
  }
  
  public static void g(int target, int buffer)
  {
    if (ac) {
      ARBVertexBufferObject.glBindBufferARB(target, buffer);
    } else {
      GL15.glBindBuffer(target, buffer);
    }
  }
  
  public static void a(int target, ByteBuffer data, int usage)
  {
    if (ac) {
      ARBVertexBufferObject.glBufferDataARB(target, data, usage);
    } else {
      GL15.glBufferData(target, data, usage);
    }
  }
  
  public static void g(int buffer)
  {
    if (ac) {
      ARBVertexBufferObject.glDeleteBuffersARB(buffer);
    } else {
      GL15.glDeleteBuffers(buffer);
    }
  }
  
  public static boolean f()
  {
    if (Config.isMultiTexture()) {
      return false;
    }
    return (P) && (bcf.z().u.u);
  }
  
  public static void h(int target, int framebufferIn)
  {
    if (l) {
      switch (U)
      {
      case a: 
        GL30.glBindFramebuffer(target, framebufferIn);
        break;
      case b: 
        ARBFramebufferObject.glBindFramebuffer(target, framebufferIn);
        break;
      case c: 
        EXTFramebufferObject.glBindFramebufferEXT(target, framebufferIn);
      }
    }
  }
  
  public static void i(int target, int renderbuffer)
  {
    if (l) {
      switch (U)
      {
      case a: 
        GL30.glBindRenderbuffer(target, renderbuffer);
        break;
      case b: 
        ARBFramebufferObject.glBindRenderbuffer(target, renderbuffer);
        break;
      case c: 
        EXTFramebufferObject.glBindRenderbufferEXT(target, renderbuffer);
      }
    }
  }
  
  public static void h(int renderbuffer)
  {
    if (l) {
      switch (U)
      {
      case a: 
        GL30.glDeleteRenderbuffers(renderbuffer);
        break;
      case b: 
        ARBFramebufferObject.glDeleteRenderbuffers(renderbuffer);
        break;
      case c: 
        EXTFramebufferObject.glDeleteRenderbuffersEXT(renderbuffer);
      }
    }
  }
  
  public static void i(int framebufferIn)
  {
    if (l) {
      switch (U)
      {
      case a: 
        GL30.glDeleteFramebuffers(framebufferIn);
        break;
      case b: 
        ARBFramebufferObject.glDeleteFramebuffers(framebufferIn);
        break;
      case c: 
        EXTFramebufferObject.glDeleteFramebuffersEXT(framebufferIn);
      }
    }
  }
  
  public static int g()
  {
    if (!l) {
      return -1;
    }
    switch (U)
    {
    case a: 
      return GL30.glGenFramebuffers();
    case b: 
      return ARBFramebufferObject.glGenFramebuffers();
    case c: 
      return EXTFramebufferObject.glGenFramebuffersEXT();
    }
    return -1;
  }
  
  public static int h()
  {
    if (!l) {
      return -1;
    }
    switch (U)
    {
    case a: 
      return GL30.glGenRenderbuffers();
    case b: 
      return ARBFramebufferObject.glGenRenderbuffers();
    case c: 
      return EXTFramebufferObject.glGenRenderbuffersEXT();
    }
    return -1;
  }
  
  public static void a(int target, int internalFormat, int width, int height)
  {
    if (l) {
      switch (U)
      {
      case a: 
        GL30.glRenderbufferStorage(target, internalFormat, width, height);
        break;
      case b: 
        ARBFramebufferObject.glRenderbufferStorage(target, internalFormat, width, height);
        break;
      case c: 
        EXTFramebufferObject.glRenderbufferStorageEXT(target, internalFormat, width, height);
      }
    }
  }
  
  public static void b(int target, int attachment, int renderBufferTarget, int renderBuffer)
  {
    if (l) {
      switch (U)
      {
      case a: 
        GL30.glFramebufferRenderbuffer(target, attachment, renderBufferTarget, renderBuffer);
        break;
      case b: 
        ARBFramebufferObject.glFramebufferRenderbuffer(target, attachment, renderBufferTarget, renderBuffer);
        break;
      case c: 
        EXTFramebufferObject.glFramebufferRenderbufferEXT(target, attachment, renderBufferTarget, renderBuffer);
      }
    }
  }
  
  public static int j(int target)
  {
    if (!l) {
      return -1;
    }
    switch (U)
    {
    case a: 
      return GL30.glCheckFramebufferStatus(target);
    case b: 
      return ARBFramebufferObject.glCheckFramebufferStatus(target);
    case c: 
      return EXTFramebufferObject.glCheckFramebufferStatusEXT(target);
    }
    return -1;
  }
  
  public static void a(int target, int attachment, int textarget, int texture, int level)
  {
    if (l) {
      switch (U)
      {
      case a: 
        GL30.glFramebufferTexture2D(target, attachment, textarget, texture, level);
        break;
      case b: 
        ARBFramebufferObject.glFramebufferTexture2D(target, attachment, textarget, texture, level);
        break;
      case c: 
        EXTFramebufferObject.glFramebufferTexture2DEXT(target, attachment, textarget, texture, level);
      }
    }
  }
  
  public static void k(int texture)
  {
    if (X) {
      ARBMultitexture.glActiveTextureARB(texture);
    } else {
      GL13.glActiveTexture(texture);
    }
  }
  
  public static void l(int texture)
  {
    if (X) {
      ARBMultitexture.glClientActiveTextureARB(texture);
    } else {
      GL13.glClientActiveTexture(texture);
    }
  }
  
  public static void a(int target, float p_77475_1_, float p_77475_2_)
  {
    if (X) {
      ARBMultitexture.glMultiTexCoord2fARB(target, p_77475_1_, p_77475_2_);
    } else {
      GL13.glMultiTexCoord2f(target, p_77475_1_, p_77475_2_);
    }
    if (target == r)
    {
      lastBrightnessX = p_77475_1_;
      lastBrightnessY = p_77475_2_;
    }
  }
  
  public static void c(int sFactorRGB, int dFactorRGB, int sfactorAlpha, int dfactorAlpha)
  {
    if (Z)
    {
      if (M) {
        EXTBlendFuncSeparate.glBlendFuncSeparateEXT(sFactorRGB, dFactorRGB, sfactorAlpha, dfactorAlpha);
      } else {
        GL14.glBlendFuncSeparate(sFactorRGB, dFactorRGB, sfactorAlpha, dfactorAlpha);
      }
    }
    else {
      GL11.glBlendFunc(sFactorRGB, dFactorRGB);
    }
  }
  
  public static boolean j()
  {
    if (Config.isFastRender()) {
      return false;
    }
    if (Config.isAntialiasing()) {
      return false;
    }
    return (l) && (bcf.z().u.f);
  }
  
  public static String k()
  {
    return ab == null ? "<unknown>" : ab;
  }
  
  public static void m(int p_188785_0_)
  {
    bni.z();
    bni.a(false);
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    vertexbuffer.a(1, bvp.f);
    GL11.glLineWidth(2.0F);
    vertexbuffer.b(0.0D, 0.0D, 0.0D).b(255, 0, 0, 255).d();
    vertexbuffer.b(p_188785_0_, 0.0D, 0.0D).b(255, 0, 0, 255).d();
    vertexbuffer.b(0.0D, 0.0D, 0.0D).b(0, 255, 0, 255).d();
    vertexbuffer.b(0.0D, p_188785_0_, 0.0D).b(0, 255, 0, 255).d();
    vertexbuffer.b(0.0D, 0.0D, 0.0D).b(0, 0, 255, 255).d();
    vertexbuffer.b(0.0D, 0.0D, p_188785_0_).b(0, 0, 255, 255).d();
    tessellator.b();
    GL11.glLineWidth(1.0F);
    bni.a(true);
    bni.y();
  }
  
  public static void a(File p_188786_0_)
  {
    String s = p_188786_0_.getAbsolutePath();
    if (g.a() == g.a.d)
    {
      try
      {
        T.info(s);
        Runtime.getRuntime().exec(new String[] { "/usr/bin/open", s });
        return;
      }
      catch (IOException ioexception1)
      {
        T.error("Couldn't open file", ioexception1);
      }
    }
    else if (g.a() == g.a.c)
    {
      String s1 = String.format("cmd.exe /C start \"Open file\" \"%s\"", new Object[] { s });
      try
      {
        Runtime.getRuntime().exec(s1);
        return;
      }
      catch (IOException ioexception)
      {
        T.error("Couldn't open file", ioexception);
      }
    }
    boolean flag = false;
    try
    {
      Class<?> oclass = Class.forName("java.awt.Desktop");
      Object object = oclass.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
      oclass.getMethod("browse", new Class[] { URI.class }).invoke(object, new Object[] { p_188786_0_.toURI() });
    }
    catch (Throwable throwable)
    {
      T.error("Couldn't open link", throwable);
      flag = true;
    }
    if (flag)
    {
      T.info("Opening via system class!");
      Sys.openURL("file://" + s);
    }
  }
  
  static enum a
  {
    private a() {}
  }
}
