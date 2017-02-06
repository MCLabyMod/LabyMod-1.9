import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.gson.JsonSyntaxException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.FloatBuffer;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Project;
import shadersmod.client.Shaders;
import shadersmod.client.ShadersRender;

public class bng
  implements bwh
{
  private static final Logger e = ;
  private static final kk f = new kk("textures/environment/rain.png");
  private static final kk g = new kk("textures/environment/snow.png");
  public static boolean a;
  public static int b;
  private bcf h;
  private final bwg i;
  private Random j = new Random();
  private float k;
  public bnk c;
  private final bcw l;
  private int m;
  private rr n;
  private oq o = new oq();
  private oq p = new oq();
  private float q = 4.0F;
  private float r = 4.0F;
  private float s;
  private float t;
  private float u;
  private float v;
  private float w;
  private float x;
  private float y;
  private float z;
  private float A;
  private boolean B;
  private boolean C = true;
  private boolean D = true;
  private long E;
  private long F = bcf.I();
  private long G;
  private final bux H;
  private final int[] I;
  private final kk J;
  private boolean K;
  private float L;
  private float M;
  private int N;
  private float[] O = new float['Ѐ'];
  private float[] P = new float['Ѐ'];
  private FloatBuffer Q = bce.h(16);
  public float R;
  public float S;
  public float T;
  private float U;
  private float V;
  private int W = 0;
  private boolean X = false;
  private double Y = 1.0D;
  private double Z;
  private double aa;
  private bup ab;
  private static final kk[] ac = { new kk("shaders/post/notch.json"), new kk("shaders/post/fxaa.json"), new kk("shaders/post/art.json"), new kk("shaders/post/bumpy.json"), new kk("shaders/post/blobs2.json"), new kk("shaders/post/pencil.json"), new kk("shaders/post/color_convolve.json"), new kk("shaders/post/deconverge.json"), new kk("shaders/post/flip.json"), new kk("shaders/post/invert.json"), new kk("shaders/post/ntsc.json"), new kk("shaders/post/outline.json"), new kk("shaders/post/phosphor.json"), new kk("shaders/post/scan_pincushion.json"), new kk("shaders/post/sobel.json"), new kk("shaders/post/bits.json"), new kk("shaders/post/desaturate.json"), new kk("shaders/post/green.json"), new kk("shaders/post/blur.json"), new kk("shaders/post/wobble.json"), new kk("shaders/post/blobs.json"), new kk("shaders/post/antialias.json"), new kk("shaders/post/creeper.json"), new kk("shaders/post/spider.json") };
  public static final int d = ac.length;
  private int ad;
  private boolean ae;
  public int af;
  private boolean initialized = false;
  private aht updatedWorld = null;
  private boolean showDebugInfo = false;
  public boolean fogStandard = false;
  private float clipDistance = 128.0F;
  private long lastServerTime = 0L;
  private int lastServerTicks = 0;
  private int serverWaitTime = 0;
  private int serverWaitTimeCurrent = 0;
  private float avgServerTimeDiff = 0.0F;
  private float avgServerTickDiff = 0.0F;
  private long lastErrorCheckTimeMs = 0L;
  private bup[] fxaaShaders = new bup[10];
  
  public bng(bcf mcIn, bwg resourceManagerIn)
  {
    this.ad = d;
    this.ae = false;
    this.af = 0;
    this.h = mcIn;
    this.i = resourceManagerIn;
    this.c = mcIn.ae();
    this.l = new bcw(mcIn.N());
    this.H = new bux(16, 16);
    this.J = mcIn.N().a("lightMap", this.H);
    this.I = this.H.e();
    this.ab = null;
    for (int i = 0; i < 32; i++) {
      for (int j = 0; j < 32; j++)
      {
        float f = j - 16;
        float f1 = i - 16;
        float f2 = on.c(f * f + f1 * f1);
        this.O[(i << 5 | j)] = (-f1 / f2);
        this.P[(i << 5 | j)] = (f / f2);
      }
    }
  }
  
  public boolean a()
  {
    return (bzg.O) && (this.ab != null);
  }
  
  public void b()
  {
    if (this.ab != null) {
      this.ab.a();
    }
    this.ab = null;
    this.ad = d;
  }
  
  public void c()
  {
    this.ae = (!this.ae);
  }
  
  public void a(rr entityIn)
  {
    if (bzg.O)
    {
      if (this.ab != null) {
        this.ab.a();
      }
      this.ab = null;
      if ((entityIn instanceof yi)) {
        a(new kk("shaders/post/creeper.json"));
      } else if ((entityIn instanceof yy)) {
        a(new kk("shaders/post/spider.json"));
      } else if ((entityIn instanceof yj)) {
        a(new kk("shaders/post/invert.json"));
      } else if (Reflector.ForgeHooksClient_loadEntityShader.exists()) {
        Reflector.call(Reflector.ForgeHooksClient_loadEntityShader, new Object[] { entityIn, this });
      }
    }
  }
  
  private void a(kk resourceLocationIn)
  {
    if (!bzg.j()) {
      return;
    }
    try
    {
      this.ab = new bup(this.h.N(), this.i, this.h.b(), resourceLocationIn);
      this.ab.a(this.h.d, this.h.e);
      this.ae = true;
    }
    catch (IOException ioexception)
    {
      e.warn("Failed to load shader: " + resourceLocationIn, ioexception);
      this.ad = d;
      this.ae = false;
    }
    catch (JsonSyntaxException jsonsyntaxexception)
    {
      e.warn("Failed to load shader: " + resourceLocationIn, jsonsyntaxexception);
      this.ad = d;
      this.ae = false;
    }
  }
  
  public void a(bwg resourceManager)
  {
    if (this.ab != null) {
      this.ab.a();
    }
    this.ab = null;
    if (this.ad != d) {
      a(ac[this.ad]);
    } else {
      a(this.h.aa());
    }
  }
  
  public void e()
  {
    if ((bzg.O) && (bus.b() == null)) {
      bus.a();
    }
    l();
    m();
    this.U = this.V;
    this.r = this.q;
    if (this.h.u.au)
    {
      float f = this.h.u.a * 0.6F + 0.2F;
      float f1 = f * f * f * 8.0F;
      this.u = this.o.a(this.s, 0.05F * f1);
      this.v = this.p.a(this.t, 0.05F * f1);
      this.w = 0.0F;
      this.s = 0.0F;
      this.t = 0.0F;
    }
    else
    {
      this.u = 0.0F;
      this.v = 0.0F;
      this.o.a();
      this.p.a();
    }
    if (this.h.aa() == null) {
      this.h.a(this.h.h);
    }
    rr viewEntity = this.h.aa();
    double vx = viewEntity.p;
    double vy = viewEntity.q + viewEntity.bn();
    double vz = viewEntity.r;
    float f3 = this.h.f.n(new cj(vx, vy, vz));
    
    float f4 = this.h.u.c / 16.0F;
    f4 = on.a(f4, 0.0F, 1.0F);
    
    float f2 = f3 * (1.0F - f4) + f4;
    this.V += (f2 - this.V) * 0.1F;
    this.m += 1;
    this.c.a();
    p();
    this.A = this.z;
    if (this.h.r.j().e())
    {
      this.z += 0.05F;
      if (this.z > 1.0F) {
        this.z = 1.0F;
      }
    }
    else if (this.z > 0.0F)
    {
      this.z -= 0.0125F;
    }
  }
  
  public bup f()
  {
    return this.ab;
  }
  
  public void a(int width, int height)
  {
    if (bzg.O)
    {
      if (this.ab != null) {
        this.ab.a(width, height);
      }
      this.h.g.a(width, height);
    }
  }
  
  public void a(float partialTicks)
  {
    rr entity = this.h.aa();
    if (entity != null) {
      if (this.h.f != null)
      {
        this.h.B.a("pick");
        this.h.i = null;
        double d0 = this.h.c.d();
        this.h.t = entity.a(d0, partialTicks);
        double d1 = d0;
        bbj vec3d = entity.g(partialTicks);
        boolean flag = false;
        int i = 3;
        if (this.h.c.i())
        {
          d0 = 6.0D;
          d1 = 6.0D;
        }
        else if (d0 > 3.0D)
        {
          flag = true;
        }
        if (this.h.t != null) {
          d1 = this.h.t.c.f(vec3d);
        }
        bbj vec3d1 = entity.f(partialTicks);
        bbj vec3d2 = vec3d.b(vec3d1.b * d0, vec3d1.c * d0, vec3d1.d * d0);
        this.n = null;
        bbj vec3d3 = null;
        float f = 1.0F;
        List<rr> list = this.h.f.a(entity, entity.bl().a(vec3d1.b * d0, vec3d1.c * d0, vec3d1.d * d0).b(f, f, f), Predicates.and(rv.e, new Predicate()
        {
          public boolean a(rr p_apply_1_)
          {
            return (p_apply_1_ != null) && (p_apply_1_.ap());
          }
        }));
        double d2 = d1;
        for (int j = 0; j < list.size(); j++)
        {
          rr entity1 = (rr)list.get(j);
          bbh axisalignedbb = entity1.bl().g(entity1.aA());
          bbi raytraceresult = axisalignedbb.a(vec3d, vec3d2);
          if (axisalignedbb.a(vec3d))
          {
            if (d2 >= 0.0D)
            {
              this.n = entity1;
              vec3d3 = raytraceresult == null ? vec3d : raytraceresult.c;
              d2 = 0.0D;
            }
          }
          else if (raytraceresult != null)
          {
            double d3 = vec3d.f(raytraceresult.c);
            if ((d3 < d2) || (d2 == 0.0D))
            {
              boolean canRiderInteract = false;
              if (Reflector.ForgeEntity_canRiderInteract.exists()) {
                canRiderInteract = Reflector.callBoolean(entity1, Reflector.ForgeEntity_canRiderInteract, new Object[0]);
              }
              if ((!canRiderInteract) && (entity1.bw() == entity.bw()))
              {
                if (d2 == 0.0D)
                {
                  this.n = entity1;
                  vec3d3 = raytraceresult.c;
                }
              }
              else
              {
                this.n = entity1;
                vec3d3 = raytraceresult.c;
                d2 = d3;
              }
            }
          }
        }
        if ((this.n != null) && (flag) && (vec3d.f(vec3d3) > 3.0D))
        {
          this.n = null;
          this.h.t = new bbi(bbi.a.a, vec3d3, (cq)null, new cj(vec3d3));
        }
        if ((this.n != null) && ((d2 < d1) || (this.h.t == null)))
        {
          this.h.t = new bbi(this.n, vec3d3);
          if (((this.n instanceof sa)) || ((this.n instanceof xs))) {
            this.h.i = this.n;
          }
        }
        this.h.B.b();
      }
    }
  }
  
  private void l()
  {
    float f = 1.0F;
    if ((this.h.aa() instanceof bmq))
    {
      bmq abstractclientplayer = (bmq)this.h.aa();
      f = abstractclientplayer.x();
    }
    this.y = this.x;
    this.x += (f - this.x) * 0.5F;
    if (this.x > 1.5F) {
      this.x = 1.5F;
    }
    if (this.x < 0.1F) {
      this.x = 0.1F;
    }
  }
  
  private float a(float partialTicks, boolean useFOVSetting)
  {
    if (this.X) {
      return 90.0F;
    }
    rr entity = this.h.aa();
    float f = 70.0F;
    if (useFOVSetting)
    {
      f = this.h.u.aw;
      f *= (this.y + (this.x - this.y) * partialTicks);
    }
    boolean zoomActive = false;
    if (this.h.m == null) {
      zoomActive = bch.a(this.h.u.ofKeyBindZoom);
    }
    if (zoomActive)
    {
      if (!Config.zoomMode)
      {
        Config.zoomMode = true;
        this.h.u.au = true;
      }
      if (Config.zoomMode) {
        f /= 4.0F;
      }
    }
    else if (Config.zoomMode)
    {
      Config.zoomMode = false;
      this.h.u.au = false;
      
      this.o = new oq();
      this.p = new oq();
      
      this.h.g.ac = true;
    }
    if (((entity instanceof sa)) && (((sa)entity).bQ() <= 0.0F))
    {
      float f1 = ((sa)entity).aA + partialTicks;
      f /= ((1.0F - 500.0F / (f1 + 500.0F)) * 2.0F + 1.0F);
    }
    arc iblockstate = bca.a(this.h.f, entity, partialTicks);
    if (iblockstate.a() == axe.h) {
      f = f * 60.0F / 70.0F;
    }
    return f;
  }
  
  private void d(float partialTicks)
  {
    if ((this.h.aa() instanceof sa))
    {
      sa entitylivingbase = (sa)this.h.aa();
      float f = entitylivingbase.ax - partialTicks;
      if (entitylivingbase.bQ() <= 0.0F)
      {
        float f1 = entitylivingbase.aA + partialTicks;
        bni.b(40.0F - 8000.0F / (f1 + 200.0F), 0.0F, 0.0F, 1.0F);
      }
      if (f < 0.0F) {
        return;
      }
      f /= entitylivingbase.ay;
      f = on.a(f * f * f * f * 3.1415927F);
      float f2 = entitylivingbase.az;
      bni.b(-f2, 0.0F, 1.0F, 0.0F);
      bni.b(-f * 14.0F, 0.0F, 0.0F, 1.0F);
      bni.b(f2, 0.0F, 1.0F, 0.0F);
    }
  }
  
  private void e(float partialTicks)
  {
    if ((this.h.aa() instanceof zj))
    {
      zj entityplayer = (zj)this.h.aa();
      float f = entityplayer.J - entityplayer.I;
      float f1 = -(entityplayer.J + f * partialTicks);
      float f2 = entityplayer.bw + (entityplayer.bx - entityplayer.bw) * partialTicks;
      float f3 = entityplayer.aI + (entityplayer.aJ - entityplayer.aI) * partialTicks;
      bni.c(on.a(f1 * 3.1415927F) * f2 * 0.5F, -Math.abs(on.b(f1 * 3.1415927F) * f2), 0.0F);
      bni.b(on.a(f1 * 3.1415927F) * f2 * 3.0F, 0.0F, 0.0F, 1.0F);
      bni.b(Math.abs(on.b(f1 * 3.1415927F - 0.2F) * f2) * 5.0F, 1.0F, 0.0F, 0.0F);
      bni.b(f3, 1.0F, 0.0F, 0.0F);
    }
  }
  
  private void f(float partialTicks)
  {
    rr entity = this.h.aa();
    float f = entity.bn();
    double d0 = entity.m + (entity.p - entity.m) * partialTicks;
    double d1 = entity.n + (entity.q - entity.n) * partialTicks + f;
    double d2 = entity.o + (entity.r - entity.o) * partialTicks;
    if (((entity instanceof sa)) && (((sa)entity).cl()))
    {
      f = (float)(f + 1.0D);
      bni.c(0.0F, 0.3F, 0.0F);
      if (!this.h.u.av)
      {
        cj blockpos = new cj(entity);
        arc iblockstate = this.h.f.o(blockpos);
        ajt block = iblockstate.t();
        if (Reflector.ForgeHooksClient_orientBedCamera.exists())
        {
          Reflector.callVoid(Reflector.ForgeHooksClient_orientBedCamera, new Object[] { this.h.f, blockpos, iblockstate, entity });
        }
        else if (block == aju.C)
        {
          int j = ((cq)iblockstate.c(ajr.D)).b();
          bni.b(j * 90, 0.0F, 1.0F, 0.0F);
        }
        bni.b(entity.x + (entity.v - entity.x) * partialTicks + 180.0F, 0.0F, -1.0F, 0.0F);
        bni.b(entity.y + (entity.w - entity.y) * partialTicks, -1.0F, 0.0F, 0.0F);
      }
    }
    else if (this.h.u.ap > 0)
    {
      double d3 = this.r + (this.q - this.r) * partialTicks;
      if (this.h.u.av)
      {
        bni.c(0.0F, 0.0F, (float)-d3);
      }
      else
      {
        float f1 = entity.v;
        float f2 = entity.w;
        if (this.h.u.ap == 2) {
          f2 += 180.0F;
        }
        double d4 = -on.a(f1 * 0.017453292F) * on.b(f2 * 0.017453292F) * d3;
        double d5 = on.b(f1 * 0.017453292F) * on.b(f2 * 0.017453292F) * d3;
        double d6 = -on.a(f2 * 0.017453292F) * d3;
        for (int i = 0; i < 8; i++)
        {
          float f3 = (i & 0x1) * 2 - 1;
          float f4 = (i >> 1 & 0x1) * 2 - 1;
          float f5 = (i >> 2 & 0x1) * 2 - 1;
          f3 *= 0.1F;
          f4 *= 0.1F;
          f5 *= 0.1F;
          bbi raytraceresult = this.h.f.a(new bbj(d0 + f3, d1 + f4, d2 + f5), new bbj(d0 - d4 + f3 + f5, d1 - d6 + f4, d2 - d5 + f5));
          if (raytraceresult != null)
          {
            double d7 = raytraceresult.c.f(new bbj(d0, d1, d2));
            if (d7 < d3) {
              d3 = d7;
            }
          }
        }
        if (this.h.u.ap == 2) {
          bni.b(180.0F, 0.0F, 1.0F, 0.0F);
        }
        bni.b(entity.w - f2, 1.0F, 0.0F, 0.0F);
        bni.b(entity.v - f1, 0.0F, 1.0F, 0.0F);
        bni.c(0.0F, 0.0F, (float)-d3);
        bni.b(f1 - entity.v, 0.0F, 1.0F, 0.0F);
        bni.b(f2 - entity.w, 1.0F, 0.0F, 0.0F);
      }
    }
    else
    {
      bni.c(0.0F, 0.0F, 0.05F);
    }
    if (!this.h.u.av)
    {
      bni.b(entity.y + (entity.w - entity.y) * partialTicks, 1.0F, 0.0F, 0.0F);
      if ((entity instanceof vw))
      {
        vw entityanimal = (vw)entity;
        bni.b(entityanimal.aP + (entityanimal.aO - entityanimal.aP) * partialTicks + 180.0F, 0.0F, 1.0F, 0.0F);
      }
      else
      {
        bni.b(entity.x + (entity.v - entity.x) * partialTicks + 180.0F, 0.0F, 1.0F, 0.0F);
      }
    }
    bni.c(0.0F, -f, 0.0F);
    d0 = entity.m + (entity.p - entity.m) * partialTicks;
    d1 = entity.n + (entity.q - entity.n) * partialTicks + f;
    d2 = entity.o + (entity.r - entity.o) * partialTicks;
    this.B = this.h.g.a(d0, d1, d2, partialTicks);
  }
  
  public void a(float partialTicks, int pass)
  {
    this.k = (this.h.u.c * 16);
    if (Config.isFogFancy()) {
      this.k *= 0.95F;
    }
    if (Config.isFogFast()) {
      this.k *= 0.83F;
    }
    bni.n(5889);
    bni.F();
    float f = 0.07F;
    if (this.h.u.e) {
      bni.c(-(pass * 2 - 1) * f, 0.0F, 0.0F);
    }
    this.clipDistance = (this.k * 2.0F);
    if (this.clipDistance < 173.0F) {
      this.clipDistance = 173.0F;
    }
    if (this.h.f.s.p() == asw.c) {
      this.clipDistance = 256.0F;
    }
    if (this.Y != 1.0D)
    {
      bni.c((float)this.Z, (float)-this.aa, 0.0F);
      bni.a(this.Y, this.Y, 1.0D);
    }
    Project.gluPerspective(a(partialTicks, true), this.h.d / this.h.e, 0.05F, this.clipDistance);
    bni.n(5888);
    bni.F();
    if (this.h.u.e) {
      bni.c((pass * 2 - 1) * 0.1F, 0.0F, 0.0F);
    }
    d(partialTicks);
    if (this.h.u.d) {
      e(partialTicks);
    }
    float f1 = this.h.h.bV + (this.h.h.bU - this.h.h.bV) * partialTicks;
    if (f1 > 0.0F)
    {
      int i = 20;
      if (this.h.h.a(rm.i)) {
        i = 7;
      }
      float f2 = 5.0F / (f1 * f1 + 5.0F) - f1 * 0.04F;
      f2 *= f2;
      bni.b((this.m + partialTicks) * i, 0.0F, 1.0F, 1.0F);
      bni.b(1.0F / f2, 1.0F, 1.0F);
      bni.b(-(this.m + partialTicks) * i, 0.0F, 1.0F, 1.0F);
    }
    f(partialTicks);
    if (this.X) {
      switch (this.W)
      {
      case 0: 
        bni.b(90.0F, 0.0F, 1.0F, 0.0F);
        break;
      case 1: 
        bni.b(180.0F, 0.0F, 1.0F, 0.0F);
        break;
      case 2: 
        bni.b(-90.0F, 0.0F, 1.0F, 0.0F);
        break;
      case 3: 
        bni.b(90.0F, 1.0F, 0.0F, 0.0F);
        break;
      case 4: 
        bni.b(-90.0F, 1.0F, 0.0F, 0.0F);
      }
    }
  }
  
  public void b(float partialTicks, int pass)
  {
    if (!this.X)
    {
      bni.n(5889);
      bni.F();
      float f = 0.07F;
      if (this.h.u.e) {
        bni.c(-(pass * 2 - 1) * f, 0.0F, 0.0F);
      }
      if (Config.isShaders()) {
        Shaders.applyHandDepth();
      }
      Project.gluPerspective(a(partialTicks, false), this.h.d / this.h.e, 0.05F, this.k * 2.0F);
      bni.n(5888);
      bni.F();
      if (this.h.u.e) {
        bni.c((pass * 2 - 1) * 0.1F, 0.0F, 0.0F);
      }
      boolean flag = false;
      if ((!Config.isShaders()) || (!Shaders.isHandRendered))
      {
        bni.G();
        d(partialTicks);
        if (this.h.u.d) {
          e(partialTicks);
        }
        flag = ((this.h.aa() instanceof sa)) && (((sa)this.h.aa()).cl());
        if ((this.h.u.ap == 0) && (!flag) && (!this.h.u.ao) && (!this.h.c.a()))
        {
          i();
          if (Config.isShaders()) {
            ShadersRender.renderItemFP(this.c, partialTicks);
          } else {
            this.c.a(partialTicks);
          }
          h();
        }
        bni.H();
      }
      if ((Config.isShaders()) && (!Shaders.isCompositeRendered)) {
        return;
      }
      h();
      if ((this.h.u.ap == 0) && (!flag))
      {
        this.c.b(partialTicks);
        d(partialTicks);
      }
      if (this.h.u.d) {
        e(partialTicks);
      }
    }
  }
  
  public void h()
  {
    bni.g(bzg.r);
    bni.z();
    bni.g(bzg.q);
    if (Config.isShaders()) {
      Shaders.disableLightmap();
    }
  }
  
  public void i()
  {
    bni.g(bzg.r);
    bni.n(5890);
    bni.F();
    float f = 0.00390625F;
    bni.b(f, f, f);
    bni.c(8.0F, 8.0F, 8.0F);
    bni.n(5888);
    this.h.N().a(this.J);
    bni.b(3553, 10241, 9729);
    bni.b(3553, 10240, 9729);
    bni.b(3553, 10242, 10496);
    bni.b(3553, 10243, 10496);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.y();
    bni.g(bzg.q);
    if (Config.isShaders()) {
      Shaders.enableLightmap();
    }
  }
  
  private void m()
  {
    this.M = ((float)(this.M + (Math.random() - Math.random()) * Math.random() * Math.random()));
    this.M = ((float)(this.M * 0.9D));
    this.L += this.M - this.L;
    this.K = true;
  }
  
  private void g(float partialTicks)
  {
    if (this.K)
    {
      this.h.B.a("lightTex");
      aht world = this.h.f;
      if (world != null)
      {
        if ((Config.isCustomColors()) && (CustomColors.updateLightmap(world, this.L, this.I, this.h.h.a(rm.p))))
        {
          this.H.d();
          this.K = false;
          this.h.B.b();
          
          return;
        }
        float f = world.b(1.0F);
        float f1 = f * 0.95F + 0.05F;
        for (int i = 0; i < 256; i++)
        {
          float f2 = world.s.n()[(i / 16)] * f1;
          float f3 = world.s.n()[(i % 16)] * (this.L * 0.1F + 1.5F);
          if (world.ag() > 0) {
            f2 = world.s.n()[(i / 16)];
          }
          float f4 = f2 * (f * 0.65F + 0.35F);
          float f5 = f2 * (f * 0.65F + 0.35F);
          float f6 = f3 * ((f3 * 0.6F + 0.4F) * 0.6F + 0.4F);
          float f7 = f3 * (f3 * f3 * 0.6F + 0.4F);
          float f8 = f4 + f3;
          float f9 = f5 + f6;
          float f10 = f2 + f7;
          f8 = f8 * 0.96F + 0.03F;
          f9 = f9 * 0.96F + 0.03F;
          f10 = f10 * 0.96F + 0.03F;
          if (this.z > 0.0F)
          {
            float f11 = this.A + (this.z - this.A) * partialTicks;
            f8 = f8 * (1.0F - f11) + f8 * 0.7F * f11;
            f9 = f9 * (1.0F - f11) + f9 * 0.6F * f11;
            f10 = f10 * (1.0F - f11) + f10 * 0.6F * f11;
          }
          if (world.s.p().a() == 1)
          {
            f8 = 0.22F + f3 * 0.75F;
            f9 = 0.28F + f6 * 0.75F;
            f10 = 0.25F + f7 * 0.75F;
          }
          if (this.h.h.a(rm.p))
          {
            float f15 = a(this.h.h, partialTicks);
            float f12 = 1.0F / f8;
            if (f12 > 1.0F / f9) {
              f12 = 1.0F / f9;
            }
            if (f12 > 1.0F / f10) {
              f12 = 1.0F / f10;
            }
            f8 = f8 * (1.0F - f15) + f8 * f12 * f15;
            f9 = f9 * (1.0F - f15) + f9 * f12 * f15;
            f10 = f10 * (1.0F - f15) + f10 * f12 * f15;
          }
          if (f8 > 1.0F) {
            f8 = 1.0F;
          }
          if (f9 > 1.0F) {
            f9 = 1.0F;
          }
          if (f10 > 1.0F) {
            f10 = 1.0F;
          }
          float f16 = this.h.u.ax;
          float f17 = 1.0F - f8;
          float f13 = 1.0F - f9;
          float f14 = 1.0F - f10;
          f17 = 1.0F - f17 * f17 * f17 * f17;
          f13 = 1.0F - f13 * f13 * f13 * f13;
          f14 = 1.0F - f14 * f14 * f14 * f14;
          f8 = f8 * (1.0F - f16) + f17 * f16;
          f9 = f9 * (1.0F - f16) + f13 * f16;
          f10 = f10 * (1.0F - f16) + f14 * f16;
          f8 = f8 * 0.96F + 0.03F;
          f9 = f9 * 0.96F + 0.03F;
          f10 = f10 * 0.96F + 0.03F;
          if (f8 > 1.0F) {
            f8 = 1.0F;
          }
          if (f9 > 1.0F) {
            f9 = 1.0F;
          }
          if (f10 > 1.0F) {
            f10 = 1.0F;
          }
          if (f8 < 0.0F) {
            f8 = 0.0F;
          }
          if (f9 < 0.0F) {
            f9 = 0.0F;
          }
          if (f10 < 0.0F) {
            f10 = 0.0F;
          }
          int j = 255;
          int k = (int)(f8 * 255.0F);
          int l = (int)(f9 * 255.0F);
          int i1 = (int)(f10 * 255.0F);
          this.I[i] = (j << 24 | k << 16 | l << 8 | i1);
        }
        this.H.d();
        this.K = false;
        this.h.B.b();
      }
    }
  }
  
  private float a(sa entitylivingbaseIn, float partialTicks)
  {
    int i = entitylivingbaseIn.b(rm.p).b();
    return i > 200 ? 1.0F : 0.7F + on.a((i - partialTicks) * 3.1415927F * 0.2F) * 0.3F;
  }
  
  public void a(float partialTicks, long nanoTime)
  {
    frameInit();
    
    boolean flag = Display.isActive();
    if ((!flag) && (this.h.u.y) && ((!this.h.u.z) || (!Mouse.isButtonDown(1))))
    {
      if (bcf.I() - this.F > 500L) {
        this.h.q();
      }
    }
    else {
      this.F = bcf.I();
    }
    this.h.B.a("mouse");
    if ((flag) && (bcf.a) && (this.h.x) && (!Mouse.isInsideWindow()))
    {
      Mouse.setGrabbed(false);
      Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2 - 20);
      Mouse.setGrabbed(true);
    }
    if ((this.h.x) && (flag))
    {
      this.h.v.c();
      float f = this.h.u.a * 0.6F + 0.2F;
      float f1 = f * f * f * 8.0F;
      float f2 = this.h.v.a * f1;
      float f3 = this.h.v.b * f1;
      int i = 1;
      if (this.h.u.b) {
        i = -1;
      }
      if (this.h.u.au)
      {
        this.s += f2;
        this.t += f3;
        float f4 = partialTicks - this.w;
        this.w = partialTicks;
        f2 = this.u * f4;
        f3 = this.v * f4;
        this.h.h.c(f2, f3 * i);
      }
      else
      {
        this.s = 0.0F;
        this.t = 0.0F;
        this.h.h.c(f2, f3 * i);
      }
    }
    this.h.B.b();
    if (!this.h.s)
    {
      a = this.h.u.e;
      final bcx scaledresolution = new bcx(this.h);
      int i1 = scaledresolution.a();
      int j1 = scaledresolution.b();
      final int k1 = Mouse.getX() * i1 / this.h.d;
      final int l1 = j1 - Mouse.getY() * j1 / this.h.e - 1;
      int i2 = this.h.u.g;
      if (this.h.f != null)
      {
        this.h.B.a("level");
        int j = Math.min(bcf.af(), i2);
        j = Math.max(j, 60);
        long k = System.nanoTime() - nanoTime;
        long l = Math.max(1000000000 / j / 4 - k, 0L);
        b(partialTicks, System.nanoTime() + l);
        if ((this.h.E()) && (this.E < bcf.I() - 1000L))
        {
          this.E = bcf.I();
          if (!this.h.F().y()) {
            n();
          }
        }
        if (bzg.O)
        {
          this.h.g.c();
          if ((this.ab != null) && (this.ae))
          {
            bni.n(5890);
            bni.G();
            bni.F();
            this.ab.a(partialTicks);
            bni.H();
          }
          this.h.b().a(true);
        }
        this.G = System.nanoTime();
        this.h.B.c("gui");
        if ((!this.h.u.ao) || (this.h.m != null))
        {
          bni.a(516, 0.1F);
          this.h.r.a(partialTicks);
          if ((this.h.u.ofShowFps) && (!this.h.u.aq)) {
            Config.drawFps();
          }
          if (this.h.u.aq) {
            Lagometer.showLagometer(scaledresolution);
          }
        }
        this.h.B.b();
      }
      else
      {
        bni.b(0, 0, this.h.d, this.h.e);
        bni.n(5889);
        bni.F();
        bni.n(5888);
        bni.F();
        j();
        this.G = System.nanoTime();
      }
      if (this.h.m != null)
      {
        bni.m(256);
        try
        {
          if (Reflector.ForgeHooksClient_drawScreen.exists()) {
            Reflector.callVoid(Reflector.ForgeHooksClient_drawScreen, new Object[] { this.h.m, Integer.valueOf(k1), Integer.valueOf(l1), Float.valueOf(partialTicks) });
          } else {
            this.h.m.a(k1, l1, partialTicks);
          }
        }
        catch (Throwable throwable)
        {
          b crashreport = b.a(throwable, "Rendering screen");
          c crashreportcategory = crashreport.a("Screen render details");
          crashreportcategory.a("Screen name", new Callable()
          {
            public String a()
              throws Exception
            {
              return bng.a(bng.this).m.getClass().getCanonicalName();
            }
          });
          crashreportcategory.a("Mouse location", new Callable()
          {
            public String a()
              throws Exception
            {
              return String.format("Scaled: (%d, %d). Absolute: (%d, %d)", new Object[] { Integer.valueOf(k1), Integer.valueOf(l1), Integer.valueOf(Mouse.getX()), Integer.valueOf(Mouse.getY()) });
            }
          });
          crashreportcategory.a("Screen size", new Callable()
          {
            public String a()
              throws Exception
            {
              return String.format("Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %d", new Object[] { Integer.valueOf(scaledresolution.a()), Integer.valueOf(scaledresolution.b()), Integer.valueOf(bng.a(bng.this).d), Integer.valueOf(bng.a(bng.this).e), Integer.valueOf(scaledresolution.e()) });
            }
          });
          throw new e(crashreport);
        }
      }
    }
    frameFinish();
    
    waitForServerThread();
    
    Lagometer.updateLagometer();
    if (this.h.u.ofProfiler) {
      this.h.u.ar = true;
    }
  }
  
  private void n()
  {
    if ((this.h.g.g() > 10) && (this.h.g.n()) && (!this.h.F().y()))
    {
      BufferedImage bufferedimage = bcj.a(this.h.d, this.h.e, this.h.b());
      int i = bufferedimage.getWidth();
      int j = bufferedimage.getHeight();
      int k = 0;
      int l = 0;
      if (i > j)
      {
        k = (i - j) / 2;
        i = j;
      }
      else
      {
        l = (j - i) / 2;
      }
      try
      {
        BufferedImage bufferedimage1 = new BufferedImage(64, 64, 1);
        Graphics graphics = bufferedimage1.createGraphics();
        graphics.drawImage(bufferedimage, 0, 0, 64, 64, k, l, k + i, l + i, (ImageObserver)null);
        graphics.dispose();
        ImageIO.write(bufferedimage1, "png", this.h.F().z());
      }
      catch (IOException ioexception)
      {
        e.warn("Couldn't save auto screenshot", ioexception);
      }
    }
  }
  
  public void b(float partialTicks)
  {
    j();
  }
  
  private boolean o()
  {
    if (!this.D) {
      return false;
    }
    rr entity = this.h.aa();
    boolean flag = ((entity instanceof zj)) && (!this.h.u.ao);
    if ((flag) && (!((zj)entity).bJ.e))
    {
      adq itemstack = ((zj)entity).cb();
      if ((this.h.t != null) && (this.h.t.a == bbi.a.b))
      {
        cj blockpos = this.h.t.a();
        ajt block = this.h.f.o(blockpos).t();
        if (this.h.c.l() == ahw.a.e)
        {
          boolean hasTileEntity;
          boolean hasTileEntity;
          if (Reflector.ForgeBlock_hasTileEntity.exists())
          {
            arc bs = this.h.f.o(blockpos);
            hasTileEntity = Reflector.callBoolean(block, Reflector.ForgeBlock_hasTileEntity, new Object[] { bs });
          }
          else
          {
            hasTileEntity = block.m();
          }
          flag = (hasTileEntity) && ((this.h.f.r(blockpos) instanceof qg));
        }
        else
        {
          flag = (itemstack != null) && ((itemstack.a(block)) || (itemstack.b(block)));
        }
      }
    }
    return flag;
  }
  
  public void b(float partialTicks, long finishTimeNano)
  {
    g(partialTicks);
    if (this.h.aa() == null) {
      this.h.a(this.h.h);
    }
    a(partialTicks);
    if (Config.isShaders()) {
      Shaders.beginRender(this.h, partialTicks, finishTimeNano);
    }
    bni.k();
    bni.e();
    
    bni.a(516, 0.1F);
    this.h.B.a("center");
    if (this.h.u.e)
    {
      b = 0;
      bni.a(false, true, true, false);
      a(0, partialTicks, finishTimeNano);
      b = 1;
      bni.a(true, false, false, false);
      a(1, partialTicks, finishTimeNano);
      bni.a(true, true, true, false);
    }
    else
    {
      a(2, partialTicks, finishTimeNano);
    }
    this.h.B.b();
  }
  
  private void a(int pass, float partialTicks, long finishTimeNano)
  {
    boolean isShaders = Config.isShaders();
    if (isShaders) {
      Shaders.beginRenderPass(pass, partialTicks, finishTimeNano);
    }
    bno renderglobal = this.h.g;
    bly effectrenderer = this.h.j;
    boolean flag = o();
    bni.q();
    this.h.B.c("clear");
    if (isShaders) {
      Shaders.setViewport(0, 0, this.h.d, this.h.e);
    } else {
      bni.b(0, 0, this.h.d, this.h.e);
    }
    h(partialTicks);
    bni.m(16640);
    if (isShaders) {
      Shaders.clearRenderBuffer();
    }
    this.h.B.c("camera");
    a(partialTicks, pass);
    if (isShaders) {
      Shaders.setCamera(partialTicks);
    }
    bca.a(this.h.h, this.h.u.ap == 2);
    this.h.B.c("frustum");
    bqn.a();
    this.h.B.c("culling");
    bqm icamera = new bqo();
    rr entity = this.h.aa();
    double d0 = entity.M + (entity.p - entity.M) * partialTicks;
    double d1 = entity.N + (entity.q - entity.N) * partialTicks;
    double d2 = entity.O + (entity.r - entity.O) * partialTicks;
    if (isShaders) {
      ShadersRender.setFrustrumPosition(icamera, d0, d1, d2);
    } else {
      icamera.a(d0, d1, d2);
    }
    if (((Config.isSkyEnabled()) || (Config.isSunMoonEnabled()) || (Config.isStarsEnabled())) && (!Shaders.isShadowPass))
    {
      a(-1, partialTicks);
      this.h.B.c("sky");
      bni.n(5889);
      bni.F();
      
      Project.gluPerspective(a(partialTicks, true), this.h.d / this.h.e, 0.05F, this.clipDistance);
      bni.n(5888);
      if (isShaders) {
        Shaders.beginSky();
      }
      renderglobal.a(partialTicks, pass);
      if (isShaders) {
        Shaders.endSky();
      }
      bni.n(5889);
      bni.F();
      
      Project.gluPerspective(a(partialTicks, true), this.h.d / this.h.e, 0.05F, this.clipDistance);
      bni.n(5888);
    }
    else
    {
      bni.l();
    }
    a(0, partialTicks);
    bni.j(7425);
    if (entity.q + entity.bn() < 128.0D + this.h.u.ofCloudsHeight * 128.0F) {
      a(renderglobal, partialTicks, pass);
    }
    this.h.B.c("prepareterrain");
    a(0, partialTicks);
    this.h.N().a(bvg.g);
    bcd.a();
    this.h.B.c("terrain_setup");
    if (isShaders) {
      ShadersRender.setupTerrain(renderglobal, entity, partialTicks, icamera, this.af++, this.h.h.y());
    } else {
      renderglobal.a(entity, partialTicks, icamera, this.af++, this.h.h.y());
    }
    if ((pass == 0) || (pass == 2))
    {
      this.h.B.c("updatechunks");
      
      Lagometer.timerChunkUpload.start();
      
      this.h.g.a(finishTimeNano);
      
      Lagometer.timerChunkUpload.end();
    }
    this.h.B.c("terrain");
    
    Lagometer.timerTerrain.start();
    if ((this.h.u.ofSmoothFps) && (pass > 0))
    {
      this.h.B.c("finish");
      GL11.glFinish();
      this.h.B.c("terrain");
    }
    bni.n(5888);
    bni.G();
    bni.d();
    if (isShaders) {
      ShadersRender.beginTerrainSolid();
    }
    renderglobal.a(ahm.a, partialTicks, pass, entity);
    bni.e();
    if (isShaders) {
      ShadersRender.beginTerrainCutoutMipped();
    }
    renderglobal.a(ahm.b, partialTicks, pass, entity);
    this.h.N().b(bvg.g).b(false, false);
    if (isShaders) {
      ShadersRender.beginTerrainCutout();
    }
    renderglobal.a(ahm.c, partialTicks, pass, entity);
    this.h.N().b(bvg.g).a();
    if (isShaders) {
      ShadersRender.endTerrain();
    }
    Lagometer.timerTerrain.end();
    bni.j(7424);
    bni.a(516, 0.1F);
    if (!this.X)
    {
      bni.n(5888);
      bni.H();
      bni.G();
      bcd.b();
      this.h.B.c("entities");
      if (Reflector.ForgeHooksClient_setRenderPass.exists()) {
        Reflector.callVoid(Reflector.ForgeHooksClient_setRenderPass, new Object[] { Integer.valueOf(0) });
      }
      renderglobal.a(entity, icamera, partialTicks);
      if (Reflector.ForgeHooksClient_setRenderPass.exists()) {
        Reflector.callVoid(Reflector.ForgeHooksClient_setRenderPass, new Object[] { Integer.valueOf(-1) });
      }
      bcd.a();
      h();
    }
    bni.n(5888);
    bni.H();
    if ((flag) && (this.h.t != null) && (!entity.a(axe.h)))
    {
      zj entityplayer = (zj)entity;
      bni.d();
      this.h.B.c("outline");
      
      boolean hasForgeMethod = Reflector.ForgeHooksClient_onDrawBlockHighlight.exists();
      if (hasForgeMethod)
      {
        if (Reflector.callBoolean(Reflector.ForgeHooksClient_onDrawBlockHighlight, new Object[] { renderglobal, entityplayer, this.h.t, Integer.valueOf(0), entityplayer.cb(), Float.valueOf(partialTicks) })) {}
      }
      else if (!this.h.u.ao) {
        renderglobal.a(entityplayer, this.h.t, 0, partialTicks);
      }
      bni.e();
    }
    if (!renderglobal.x.isEmpty())
    {
      this.h.B.c("destroyProgress");
      bni.m();
      bni.a(bni.r.l, bni.l.e, bni.r.e, bni.l.n);
      this.h.N().b(bvg.g).b(false, false);
      renderglobal.a(bnu.a(), bnu.a().c(), entity, partialTicks);
      this.h.N().b(bvg.g).a();
      bni.l();
    }
    bni.a(770, 771, 1, 0);
    bni.l();
    if (!this.X)
    {
      i();
      this.h.B.c("litParticles");
      if (isShaders) {
        Shaders.beginLitParticles();
      }
      effectrenderer.b(entity, partialTicks);
      bcd.a();
      a(0, partialTicks);
      this.h.B.c("particles");
      if (isShaders) {
        Shaders.beginParticles();
      }
      effectrenderer.a(entity, partialTicks);
      if (isShaders) {
        Shaders.endParticles();
      }
      h();
    }
    bni.a(false);
    bni.q();
    this.h.B.c("weather");
    if (isShaders) {
      Shaders.beginWeather();
    }
    c(partialTicks);
    if (isShaders) {
      Shaders.endWeather();
    }
    bni.a(true);
    if (isShaders)
    {
      ShadersRender.renderHand0(this, partialTicks, pass);
      Shaders.preWater();
    }
    renderglobal.a(entity, partialTicks);
    bni.l();
    bni.q();
    bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
    bni.a(516, 0.1F);
    a(0, partialTicks);
    bni.m();
    bni.a(false);
    this.h.N().a(bvg.g);
    bni.j(7425);
    this.h.B.c("translucent");
    if (isShaders) {
      Shaders.beginWater();
    }
    renderglobal.a(ahm.d, partialTicks, pass, entity);
    if (isShaders) {
      Shaders.endWater();
    }
    if ((Reflector.ForgeHooksClient_setRenderPass.exists()) && (!this.X))
    {
      bcd.b();
      this.h.B.c("entities");
      Reflector.callVoid(Reflector.ForgeHooksClient_setRenderPass, new Object[] { Integer.valueOf(1) });
      this.h.g.a(entity, icamera, partialTicks);
      Reflector.callVoid(Reflector.ForgeHooksClient_setRenderPass, new Object[] { Integer.valueOf(-1) });
      bcd.a();
    }
    bni.j(7424);
    bni.a(true);
    bni.q();
    bni.l();
    bni.p();
    if (entity.q + entity.bn() >= 128.0D + this.h.u.ofCloudsHeight * 128.0F)
    {
      this.h.B.c("aboveClouds");
      a(renderglobal, partialTicks, pass);
    }
    if (Reflector.ForgeHooksClient_dispatchRenderLast.exists())
    {
      this.h.B.c("forge_render_last");
      Reflector.callVoid(Reflector.ForgeHooksClient_dispatchRenderLast, new Object[] { renderglobal, Float.valueOf(partialTicks) });
    }
    this.h.B.c("hand");
    
    boolean handRendered = ReflectorForge.renderFirstPersonHand(this.h.g, partialTicks, pass);
    if ((!handRendered) && (this.C) && (!Shaders.isShadowPass))
    {
      if (isShaders)
      {
        ShadersRender.renderHand1(this, partialTicks, pass);
        Shaders.renderCompositeFinal();
      }
      bni.m(256);
      if (isShaders) {
        ShadersRender.renderFPOverlay(this, partialTicks, pass);
      } else {
        b(partialTicks, pass);
      }
    }
    if (isShaders) {
      Shaders.endRender();
    }
  }
  
  private void a(bno renderGlobalIn, float partialTicks, int pass)
  {
    if ((this.h.u.c >= 4) && (!Config.isCloudsOff()) && (Shaders.shouldRenderClouds(this.h.u)))
    {
      this.h.B.c("clouds");
      bni.n(5889);
      bni.F();
      
      Project.gluPerspective(a(partialTicks, true), this.h.d / this.h.e, 0.05F, this.clipDistance * 4.0F);
      bni.n(5888);
      bni.G();
      a(0, partialTicks);
      renderGlobalIn.b(partialTicks, pass);
      bni.p();
      bni.H();
      bni.n(5889);
      bni.F();
      
      Project.gluPerspective(a(partialTicks, true), this.h.d / this.h.e, 0.05F, this.clipDistance);
      bni.n(5888);
    }
  }
  
  private void p()
  {
    float f = this.h.f.j(1.0F);
    if (!Config.isRainFancy()) {
      f /= 2.0F;
    }
    if ((f != 0.0F) && (Config.isRainSplash()))
    {
      this.j.setSeed(this.m * 312987231L);
      rr entity = this.h.aa();
      aht world = this.h.f;
      cj blockpos = new cj(entity);
      int i = 10;
      double d0 = 0.0D;
      double d1 = 0.0D;
      double d2 = 0.0D;
      int j = 0;
      int k = (int)(100.0F * f * f);
      if (this.h.u.aA == 1) {
        k >>= 1;
      } else if (this.h.u.aA == 2) {
        k = 0;
      }
      for (int l = 0; l < k; l++)
      {
        cj blockpos1 = world.p(blockpos.a(this.j.nextInt(i) - this.j.nextInt(i), 0, this.j.nextInt(i) - this.j.nextInt(i)));
        aig biomegenbase = world.b(blockpos1);
        cj blockpos2 = blockpos1.b();
        arc iblockstate = world.o(blockpos2);
        if ((blockpos1.q() <= blockpos.q() + i) && (blockpos1.q() >= blockpos.q() - i) && (biomegenbase.d()) && (biomegenbase.a(blockpos1) >= 0.15F))
        {
          double d3 = this.j.nextDouble();
          double d4 = this.j.nextDouble();
          bbh axisalignedbb = iblockstate.c(world, blockpos2);
          if (iblockstate.a() == axe.i)
          {
            this.h.f.a(cy.l, blockpos1.p() + d3, blockpos1.q() + 0.1F - axisalignedbb.b, blockpos1.r() + d4, 0.0D, 0.0D, 0.0D, new int[0]);
          }
          else if (iblockstate.a() != axe.a)
          {
            j++;
            if (this.j.nextInt(j) == 0)
            {
              d0 = blockpos2.p() + d3;
              d1 = blockpos2.q() + 0.1F + axisalignedbb.e - 1.0D;
              d2 = blockpos2.r() + d4;
            }
            this.h.f.a(cy.N, blockpos2.p() + d3, blockpos2.q() + 0.1F + axisalignedbb.e, blockpos2.r() + d4, 0.0D, 0.0D, 0.0D, new int[0]);
          }
        }
      }
      if ((j > 0) && (this.j.nextInt(3) < this.N++))
      {
        this.N = 0;
        if ((d1 > blockpos.q() + 1) && (world.p(blockpos).q() > on.d(blockpos.q()))) {
          this.h.f.a(d0, d1, d2, ng.gy, nh.d, 0.1F, 0.5F, false);
        } else {
          this.h.f.a(d0, d1, d2, ng.gx, nh.d, 0.2F, 1.0F, false);
        }
      }
    }
  }
  
  protected void c(float partialTicks)
  {
    if (Reflector.ForgeWorldProvider_getWeatherRenderer.exists())
    {
      asv worldProvider = this.h.f.s;
      Object weatherRenderer = Reflector.call(worldProvider, Reflector.ForgeWorldProvider_getWeatherRenderer, new Object[0]);
      if (weatherRenderer != null)
      {
        Reflector.callVoid(weatherRenderer, Reflector.IRenderHandler_render, new Object[] { Float.valueOf(partialTicks), this.h.f, this.h });
        return;
      }
    }
    float f = this.h.f.j(partialTicks);
    if (f > 0.0F)
    {
      if (Config.isRainOff()) {
        return;
      }
      i();
      rr entity = this.h.aa();
      aht world = this.h.f;
      int i = on.c(entity.p);
      int j = on.c(entity.q);
      int k = on.c(entity.r);
      bnu tessellator = bnu.a();
      bmz vertexbuffer = tessellator.c();
      bni.r();
      bni.a(0.0F, 1.0F, 0.0F);
      bni.m();
      bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
      bni.a(516, 0.1F);
      double d0 = entity.M + (entity.p - entity.M) * partialTicks;
      double d1 = entity.N + (entity.q - entity.N) * partialTicks;
      double d2 = entity.O + (entity.r - entity.O) * partialTicks;
      int l = on.c(d1);
      int i1 = 5;
      if (Config.isRainFancy()) {
        i1 = 10;
      }
      int j1 = -1;
      float f1 = this.m + partialTicks;
      vertexbuffer.c(-d0, -d1, -d2);
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      cj.a blockpos$mutableblockpos = new cj.a();
      for (int k1 = k - i1; k1 <= k + i1; k1++) {
        for (int l1 = i - i1; l1 <= i + i1; l1++)
        {
          int i2 = (k1 - k + 16) * 32 + l1 - i + 16;
          double d3 = this.O[i2] * 0.5D;
          double d4 = this.P[i2] * 0.5D;
          blockpos$mutableblockpos.c(l1, 0, k1);
          aig biomegenbase = world.b(blockpos$mutableblockpos);
          if ((biomegenbase.d()) || (biomegenbase.c()))
          {
            int j2 = world.p(blockpos$mutableblockpos).q();
            int k2 = j - i1;
            int l2 = j + i1;
            if (k2 < j2) {
              k2 = j2;
            }
            if (l2 < j2) {
              l2 = j2;
            }
            int i3 = j2;
            if (j2 < l) {
              i3 = l;
            }
            if (k2 != l2)
            {
              this.j.setSeed(l1 * l1 * 3121 + l1 * 45238971 ^ k1 * k1 * 418711 + k1 * 13761);
              blockpos$mutableblockpos.c(l1, k2, k1);
              float f2 = biomegenbase.a(blockpos$mutableblockpos);
              if (world.A().a(f2, j2) >= 0.15F)
              {
                if (j1 != 0)
                {
                  if (j1 >= 0) {
                    tessellator.b();
                  }
                  j1 = 0;
                  this.h.N().a(f);
                  vertexbuffer.a(7, bvp.d);
                }
                double d5 = -((this.m + l1 * l1 * 3121 + l1 * 45238971 + k1 * k1 * 418711 + k1 * 13761 & 0x1F) + partialTicks) / 32.0D * (3.0D + this.j.nextDouble());
                double d6 = l1 + 0.5F - entity.p;
                double d7 = k1 + 0.5F - entity.r;
                float f3 = on.a(d6 * d6 + d7 * d7) / i1;
                float f4 = ((1.0F - f3 * f3) * 0.5F + 0.5F) * f;
                blockpos$mutableblockpos.c(l1, i3, k1);
                int j3 = world.b(blockpos$mutableblockpos, 0);
                int k3 = j3 >> 16 & 0xFFFF;
                int l3 = j3 & 0xFFFF;
                vertexbuffer.b(l1 - d3 + 0.5D, l2, k1 - d4 + 0.5D).a(0.0D, k2 * 0.25D + d5).a(1.0F, 1.0F, 1.0F, f4).a(k3, l3).d();
                vertexbuffer.b(l1 + d3 + 0.5D, l2, k1 + d4 + 0.5D).a(1.0D, k2 * 0.25D + d5).a(1.0F, 1.0F, 1.0F, f4).a(k3, l3).d();
                vertexbuffer.b(l1 + d3 + 0.5D, k2, k1 + d4 + 0.5D).a(1.0D, l2 * 0.25D + d5).a(1.0F, 1.0F, 1.0F, f4).a(k3, l3).d();
                vertexbuffer.b(l1 - d3 + 0.5D, k2, k1 - d4 + 0.5D).a(0.0D, l2 * 0.25D + d5).a(1.0F, 1.0F, 1.0F, f4).a(k3, l3).d();
              }
              else
              {
                if (j1 != 1)
                {
                  if (j1 >= 0) {
                    tessellator.b();
                  }
                  j1 = 1;
                  this.h.N().a(g);
                  vertexbuffer.a(7, bvp.d);
                }
                double d8 = -((this.m & 0x1FF) + partialTicks) / 512.0F;
                double d9 = this.j.nextDouble() + f1 * 0.01D * (float)this.j.nextGaussian();
                double d10 = this.j.nextDouble() + f1 * (float)this.j.nextGaussian() * 0.001D;
                double d11 = l1 + 0.5F - entity.p;
                double d12 = k1 + 0.5F - entity.r;
                float f6 = on.a(d11 * d11 + d12 * d12) / i1;
                float f5 = ((1.0F - f6 * f6) * 0.3F + 0.5F) * f;
                blockpos$mutableblockpos.c(l1, i3, k1);
                int i4 = (world.b(blockpos$mutableblockpos, 0) * 3 + 15728880) / 4;
                int j4 = i4 >> 16 & 0xFFFF;
                int k4 = i4 & 0xFFFF;
                vertexbuffer.b(l1 - d3 + 0.5D, l2, k1 - d4 + 0.5D).a(0.0D + d9, k2 * 0.25D + d8 + d10).a(1.0F, 1.0F, 1.0F, f5).a(j4, k4).d();
                vertexbuffer.b(l1 + d3 + 0.5D, l2, k1 + d4 + 0.5D).a(1.0D + d9, k2 * 0.25D + d8 + d10).a(1.0F, 1.0F, 1.0F, f5).a(j4, k4).d();
                vertexbuffer.b(l1 + d3 + 0.5D, k2, k1 + d4 + 0.5D).a(1.0D + d9, l2 * 0.25D + d8 + d10).a(1.0F, 1.0F, 1.0F, f5).a(j4, k4).d();
                vertexbuffer.b(l1 - d3 + 0.5D, k2, k1 - d4 + 0.5D).a(0.0D + d9, l2 * 0.25D + d8 + d10).a(1.0F, 1.0F, 1.0F, f5).a(j4, k4).d();
              }
            }
          }
        }
      }
      if (j1 >= 0) {
        tessellator.b();
      }
      vertexbuffer.c(0.0D, 0.0D, 0.0D);
      bni.q();
      bni.l();
      bni.a(516, 0.1F);
      h();
    }
  }
  
  public void j()
  {
    bcx scaledresolution = new bcx(this.h);
    bni.m(256);
    bni.n(5889);
    bni.F();
    bni.a(0.0D, scaledresolution.c(), scaledresolution.d(), 0.0D, 1000.0D, 3000.0D);
    bni.n(5888);
    bni.F();
    bni.c(0.0F, 0.0F, -2000.0F);
  }
  
  private void h(float partialTicks)
  {
    aht world = this.h.f;
    rr entity = this.h.aa();
    float f = 0.25F + 0.75F * this.h.u.c / 32.0F;
    f = 1.0F - (float)Math.pow(f, 0.25D);
    bbj vec3d = world.a(this.h.aa(), partialTicks);
    
    vec3d = CustomColors.getWorldSkyColor(vec3d, world, this.h.aa(), partialTicks);
    
    float f1 = (float)vec3d.b;
    float f2 = (float)vec3d.c;
    float f3 = (float)vec3d.d;
    bbj vec3d1 = world.f(partialTicks);
    
    vec3d1 = CustomColors.getWorldFogColor(vec3d1, world, this.h.aa(), partialTicks);
    
    this.R = ((float)vec3d1.b);
    this.S = ((float)vec3d1.c);
    this.T = ((float)vec3d1.d);
    if (this.h.u.c >= 4)
    {
      double d0 = -1.0D;
      bbj vec3d2 = on.a(world.d(partialTicks)) > 0.0F ? new bbj(d0, 0.0D, 0.0D) : new bbj(1.0D, 0.0D, 0.0D);
      float f5 = (float)entity.f(partialTicks).b(vec3d2);
      if (f5 < 0.0F) {
        f5 = 0.0F;
      }
      if (f5 > 0.0F)
      {
        float[] afloat = world.s.a(world.c(partialTicks), partialTicks);
        if (afloat != null)
        {
          f5 *= afloat[3];
          this.R = (this.R * (1.0F - f5) + afloat[0] * f5);
          this.S = (this.S * (1.0F - f5) + afloat[1] * f5);
          this.T = (this.T * (1.0F - f5) + afloat[2] * f5);
        }
      }
    }
    this.R += (f1 - this.R) * f;
    this.S += (f2 - this.S) * f;
    this.T += (f3 - this.T) * f;
    float f8 = world.j(partialTicks);
    if (f8 > 0.0F)
    {
      float f4 = 1.0F - f8 * 0.5F;
      float f10 = 1.0F - f8 * 0.4F;
      this.R *= f4;
      this.S *= f4;
      this.T *= f10;
    }
    float f9 = world.h(partialTicks);
    if (f9 > 0.0F)
    {
      float f11 = 1.0F - f9 * 0.5F;
      this.R *= f11;
      this.S *= f11;
      this.T *= f11;
    }
    arc iblockstate = bca.a(this.h.f, entity, partialTicks);
    if (this.B)
    {
      bbj vec3d3 = world.e(partialTicks);
      this.R = ((float)vec3d3.b);
      this.S = ((float)vec3d3.c);
      this.T = ((float)vec3d3.d);
    }
    else if (iblockstate.a() == axe.h)
    {
      float f12 = 0.0F;
      if ((entity instanceof sa))
      {
        f12 = ago.c((sa)entity) * 0.2F;
        if (((sa)entity).a(rm.m)) {
          f12 = f12 * 0.3F + 0.6F;
        }
      }
      this.R = (0.02F + f12);
      this.S = (0.02F + f12);
      this.T = (0.2F + f12);
      
      bbj colUnderwater = CustomColors.getUnderwaterColor(this.h.f, this.h.aa().p, this.h.aa().q + 1.0D, this.h.aa().r);
      if (colUnderwater != null)
      {
        this.R = ((float)colUnderwater.b);
        this.S = ((float)colUnderwater.c);
        this.T = ((float)colUnderwater.d);
      }
    }
    else if (iblockstate.a() == axe.i)
    {
      this.R = 0.6F;
      this.S = 0.1F;
      this.T = 0.0F;
    }
    float f13 = this.U + (this.V - this.U) * partialTicks;
    this.R *= f13;
    this.S *= f13;
    this.T *= f13;
    double d1 = (entity.N + (entity.q - entity.N) * partialTicks) * world.s.j();
    if (((entity instanceof sa)) && (((sa)entity).a(rm.o)))
    {
      int i = ((sa)entity).b(rm.o).b();
      if (i < 20) {
        d1 *= (1.0F - i / 20.0F);
      } else {
        d1 = 0.0D;
      }
    }
    if (d1 < 1.0D)
    {
      if (d1 < 0.0D) {
        d1 = 0.0D;
      }
      d1 *= d1;
      this.R = ((float)(this.R * d1));
      this.S = ((float)(this.S * d1));
      this.T = ((float)(this.T * d1));
    }
    if (this.z > 0.0F)
    {
      float f14 = this.A + (this.z - this.A) * partialTicks;
      this.R = (this.R * (1.0F - f14) + this.R * 0.7F * f14);
      this.S = (this.S * (1.0F - f14) + this.S * 0.6F * f14);
      this.T = (this.T * (1.0F - f14) + this.T * 0.6F * f14);
    }
    if (((entity instanceof sa)) && (((sa)entity).a(rm.p)))
    {
      float f15 = a((sa)entity, partialTicks);
      float f6 = 1.0F / this.R;
      if (f6 > 1.0F / this.S) {
        f6 = 1.0F / this.S;
      }
      if (f6 > 1.0F / this.T) {
        f6 = 1.0F / this.T;
      }
      this.R = (this.R * (1.0F - f15) + this.R * f6 * f15);
      this.S = (this.S * (1.0F - f15) + this.S * f6 * f15);
      this.T = (this.T * (1.0F - f15) + this.T * f6 * f15);
    }
    if (this.h.u.e)
    {
      float f16 = (this.R * 30.0F + this.S * 59.0F + this.T * 11.0F) / 100.0F;
      float f17 = (this.R * 30.0F + this.S * 70.0F) / 100.0F;
      float f7 = (this.R * 30.0F + this.T * 70.0F) / 100.0F;
      this.R = f16;
      this.S = f17;
      this.T = f7;
    }
    if (Reflector.EntityViewRenderEvent_FogColors_Constructor.exists())
    {
      Object event = Reflector.newInstance(Reflector.EntityViewRenderEvent_FogColors_Constructor, new Object[] { this, entity, iblockstate, Float.valueOf(partialTicks), Float.valueOf(this.R), Float.valueOf(this.S), Float.valueOf(this.T) });
      Reflector.postForgeBusEvent(event);
      
      this.R = Reflector.getFieldValueFloat(event, Reflector.EntityViewRenderEvent_FogColors_red, this.R);
      this.S = Reflector.getFieldValueFloat(event, Reflector.EntityViewRenderEvent_FogColors_green, this.S);
      this.T = Reflector.getFieldValueFloat(event, Reflector.EntityViewRenderEvent_FogColors_blue, this.T);
    }
    Shaders.setClearColor(this.R, this.S, this.T, 0.0F);
  }
  
  private void a(int startCoords, float partialTicks)
  {
    this.fogStandard = false;
    
    rr entity = this.h.aa();
    bni.b(2918, a(this.R, this.S, this.T, 1.0F));
    bni.a(0.0F, -1.0F, 0.0F);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    arc iblockstate = bca.a(this.h.f, entity, partialTicks);
    
    Object event = Reflector.newInstance(Reflector.EntityViewRenderEvent_FogDensity_Constructor, new Object[] { this, entity, iblockstate, Float.valueOf(partialTicks), Float.valueOf(0.1F) });
    if (Reflector.postForgeBusEvent(event))
    {
      float density = Reflector.getFieldValueFloat(event, Reflector.EntityViewRenderEvent_FogDensity_density, 0.0F);
      bni.a(density);
    }
    else if (((entity instanceof sa)) && (((sa)entity).a(rm.o)))
    {
      float f1 = 5.0F;
      int i = ((sa)entity).b(rm.o).b();
      if (i < 20) {
        f1 = 5.0F + (this.k - 5.0F) * (1.0F - i / 20.0F);
      }
      if (Config.isShaders()) {
        Shaders.setFog(bni.m.a);
      } else {
        bni.a(bni.m.a);
      }
      if (startCoords == -1)
      {
        bni.b(0.0F);
        bni.c(f1 * 0.8F);
      }
      else
      {
        bni.b(f1 * 0.25F);
        bni.c(f1);
      }
      if (GLContext.getCapabilities().GL_NV_fog_distance) {
        if (Config.isFogFancy()) {
          bni.c(34138, 34139);
        }
      }
    }
    else if (this.B)
    {
      if (Config.isShaders()) {
        Shaders.setFog(bni.m.b);
      } else {
        bni.a(bni.m.b);
      }
      bni.a(0.1F);
    }
    else if (iblockstate.a() == axe.h)
    {
      if (Config.isShaders()) {
        Shaders.setFog(bni.m.b);
      } else {
        bni.a(bni.m.b);
      }
      if ((entity instanceof sa))
      {
        if (((sa)entity).a(rm.m)) {
          bni.a(0.01F);
        } else {
          bni.a(0.1F - ago.c((sa)entity) * 0.03F);
        }
      }
      else {
        bni.a(0.1F);
      }
      if (Config.isClearWater()) {
        bni.a(0.02F);
      }
    }
    else if (iblockstate.a() == axe.i)
    {
      if (Config.isShaders()) {
        Shaders.setFog(bni.m.b);
      } else {
        bni.a(bni.m.b);
      }
      bni.a(2.0F);
    }
    else
    {
      float f = this.k;
      
      this.fogStandard = true;
      if (Config.isShaders()) {
        Shaders.setFog(bni.m.a);
      } else {
        bni.a(bni.m.a);
      }
      if (startCoords == -1)
      {
        bni.b(0.0F);
        bni.c(f);
      }
      else
      {
        bni.b(f * Config.getFogStart());
        bni.c(f);
      }
      if (GLContext.getCapabilities().GL_NV_fog_distance)
      {
        if (Config.isFogFancy()) {
          bni.c(34138, 34139);
        }
        if (Config.isFogFast()) {
          bni.c(34138, 34140);
        }
      }
      if ((this.h.f.s.b((int)entity.p, (int)entity.r)) || (this.h.r.j().f()))
      {
        bni.b(f * 0.05F);
        
        bni.c(f);
      }
      if (Reflector.ForgeHooksClient_onFogRender.exists()) {
        Reflector.callVoid(Reflector.ForgeHooksClient_onFogRender, new Object[] { this, entity, iblockstate, Float.valueOf(partialTicks), Integer.valueOf(startCoords), Float.valueOf(f) });
      }
    }
    bni.h();
    bni.o();
    bni.a(1028, 4608);
  }
  
  private FloatBuffer a(float red, float green, float blue, float alpha)
  {
    if (Config.isShaders()) {
      Shaders.setFogColor(red, green, blue);
    }
    this.Q.clear();
    this.Q.put(red).put(green).put(blue).put(alpha);
    this.Q.flip();
    return this.Q;
  }
  
  public bcw k()
  {
    return this.l;
  }
  
  private void waitForServerThread()
  {
    this.serverWaitTimeCurrent = 0;
    if ((!Config.isSmoothWorld()) || (!Config.isSingleProcessor()))
    {
      this.lastServerTime = 0L;
      this.lastServerTicks = 0;
      return;
    }
    if (!this.h.D()) {
      return;
    }
    byn srv = this.h.F();
    if (srv == null) {
      return;
    }
    boolean paused = this.h.T();
    if ((paused) || ((this.h.m instanceof bfa)))
    {
      if ((this.h.m instanceof bfa)) {
        Config.sleep(20L);
      }
      this.lastServerTime = 0L;
      this.lastServerTicks = 0;
      return;
    }
    if (this.serverWaitTime > 0)
    {
      Lagometer.timerServer.start();
      
      Config.sleep(this.serverWaitTime);
      
      Lagometer.timerServer.end();
      
      this.serverWaitTimeCurrent = this.serverWaitTime;
    }
    long timeNow = System.nanoTime() / 1000000L;
    if ((this.lastServerTime == 0L) || (this.lastServerTicks == 0))
    {
      this.lastServerTime = timeNow;
      this.lastServerTicks = srv.ap();
      this.avgServerTickDiff = 1.0F;
      this.avgServerTimeDiff = 50.0F;
      return;
    }
    long timeDiff = timeNow - this.lastServerTime;
    if (timeDiff < 0L)
    {
      this.lastServerTime = timeNow;
      timeDiff = 0L;
    }
    if (timeDiff < 50L) {
      return;
    }
    this.lastServerTime = timeNow;
    
    int ticks = srv.ap();
    
    int tickDiff = ticks - this.lastServerTicks;
    if (tickDiff < 0)
    {
      this.lastServerTicks = ticks;
      tickDiff = 0;
    }
    if (tickDiff < 1) {
      if (this.serverWaitTime < 100) {
        this.serverWaitTime += 2;
      }
    }
    if (tickDiff > 1) {
      if (this.serverWaitTime > 0) {
        this.serverWaitTime -= 1;
      }
    }
    this.lastServerTicks = ticks;
  }
  
  private void frameInit()
  {
    if (!this.initialized)
    {
      TextureUtils.registerResourceListener();
      if ((Config.getBitsOs() == 64) && (Config.getBitsJre() == 32)) {
        Config.setNotify64BitJava(true);
      }
      this.initialized = true;
    }
    Config.isActing();
    
    Config.checkDisplayMode();
    
    aht world = this.h.f;
    if (world != null)
    {
      if (Config.getNewRelease() != null)
      {
        String userEdition = "HD_U".replace("HD_U", "HD Ultra").replace("L", "Light");
        String fullNewVer = userEdition + " " + Config.getNewRelease();
        fa msg = new fa(bwo.a("of.message.newVersion", new Object[] { fullNewVer }));
        this.h.r.d().a(msg);
        Config.setNewRelease(null);
      }
      if (Config.isNotify64BitJava())
      {
        Config.setNotify64BitJava(false);
        fa msg = new fa(bwo.a("of.message.java64Bit", new Object[0]));
        this.h.r.d().a(msg);
      }
    }
    if ((this.h.m instanceof bfi)) {
      updateMainMenu((bfi)this.h.m);
    }
    if (this.updatedWorld != world)
    {
      RandomMobs.worldChanged(this.updatedWorld, world);
      
      Config.updateThreadPriorities();
      
      this.lastServerTime = 0L;
      this.lastServerTicks = 0;
      
      this.updatedWorld = world;
    }
    if (!setFxaaShader(Shaders.configAntialiasingLevel)) {
      Shaders.configAntialiasingLevel = 0;
    }
  }
  
  private void frameFinish()
  {
    if (this.h.f != null)
    {
      long now = System.currentTimeMillis();
      if (now > this.lastErrorCheckTimeMs + 10000L)
      {
        this.lastErrorCheckTimeMs = now;
        
        int err = bni.L();
        if (err != 0)
        {
          String text = GLU.gluErrorString(err);
          fa msg = new fa(bwo.a("of.message.openglError", new Object[] { Integer.valueOf(err), text }));
          this.h.r.d().a(msg);
        }
      }
    }
  }
  
  private void updateMainMenu(bfi mainGui)
  {
    try
    {
      String str = null;
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(new Date());
      int day = calendar.get(5);
      int month = calendar.get(2) + 1;
      if ((day == 8) && (month == 4)) {
        str = "Happy birthday, OptiFine!";
      }
      if ((day == 14) && (month == 8)) {
        str = "Happy birthday, sp614x!";
      }
      if (str == null) {
        return;
      }
      Field[] fs = bfi.class.getDeclaredFields();
      for (int i = 0; i < fs.length; i++) {
        if (fs[i].getType() == String.class)
        {
          fs[i].setAccessible(true);
          fs[i].set(mainGui, str);
          break;
        }
      }
    }
    catch (Throwable e) {}
  }
  
  public boolean setFxaaShader(int fxaaLevel)
  {
    if (!bzg.j()) {
      return false;
    }
    if ((this.ab != null) && (this.ab != this.fxaaShaders[2]) && (this.ab != this.fxaaShaders[4])) {
      return true;
    }
    if ((fxaaLevel == 2) || (fxaaLevel == 4))
    {
      if ((this.ab != null) && (this.ab == this.fxaaShaders[fxaaLevel])) {
        return true;
      }
      if (this.h.f == null) {
        return true;
      }
      a(new kk("shaders/post/fxaa_of_" + fxaaLevel + "x.json"));
      
      this.fxaaShaders[fxaaLevel] = this.ab;
      
      return this.ae;
    }
    if (this.ab == null) {
      return true;
    }
    this.ab.a();
    this.ab = null;
    
    return true;
  }
}
