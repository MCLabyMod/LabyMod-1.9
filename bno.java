import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import shadersmod.client.Shaders;
import shadersmod.client.ShadersRender;

public class bno
  implements ahv, bwh
{
  private static final Logger b = ;
  private static final kk c = new kk("textures/environment/moon_phases.png");
  private static final kk d = new kk("textures/environment/sun.png");
  private static final kk e = new kk("textures/environment/clouds.png");
  private static final kk f = new kk("textures/environment/end_sky.png");
  private static final kk g = new kk("textures/misc/forcefield.png");
  public final bcf h;
  private final bvi i;
  private final brm j;
  private bku k;
  private Set<bqf> l = Sets.newLinkedHashSet();
  private List<bno.a> m = Lists.newArrayListWithCapacity(69696);
  private final Set<apv> n = Sets.newHashSet();
  private bnx o;
  private int p = -1;
  private int q = -1;
  private int r = -1;
  private bvr s;
  private bvq t;
  private bvq u;
  private bvq v;
  private int w;
  public final Map<Integer, li> x = Maps.newHashMap();
  private final Map<cj, byi> y = Maps.newHashMap();
  private final bvh[] z = new bvh[10];
  private bnt A;
  private bup B;
  private double C = Double.MIN_VALUE;
  private double D = Double.MIN_VALUE;
  private double E = Double.MIN_VALUE;
  private int F = Integer.MIN_VALUE;
  private int G = Integer.MIN_VALUE;
  private int H = Integer.MIN_VALUE;
  private double I = Double.MIN_VALUE;
  private double J = Double.MIN_VALUE;
  private double K = Double.MIN_VALUE;
  private double L = Double.MIN_VALUE;
  private double M = Double.MIN_VALUE;
  private bqa N = null;
  private bnd O;
  private int P = -1;
  private int Q = 2;
  private int R;
  private int S;
  private int T;
  private boolean U = false;
  private bqp V;
  private final Vector4f[] W = new Vector4f[8];
  private final bzd X = new bzd();
  private boolean Y = false;
  bqg a;
  private double Z;
  private double aa;
  private double ab;
  public boolean ac = true;
  private boolean ad = false;
  private final Set<cj> ae = Sets.newHashSet();
  private CloudRenderer cloudRenderer;
  public rr renderedEntity;
  public Set chunksToResortTransparency = new LinkedHashSet();
  public Set chunksToUpdateForced = new LinkedHashSet();
  private Deque visibilityDeque = new ArrayDeque();
  private List renderInfosEntities = new ArrayList(1024);
  private List renderInfosTileEntities = new ArrayList(1024);
  private List renderInfosNormal = new ArrayList(1024);
  private List renderInfosEntitiesNormal = new ArrayList(1024);
  private List renderInfosTileEntitiesNormal = new ArrayList(1024);
  private List renderInfosShadow = new ArrayList(1024);
  private List renderInfosEntitiesShadow = new ArrayList(1024);
  private List renderInfosTileEntitiesShadow = new ArrayList(1024);
  private int renderDistance = 0;
  private int renderDistanceSq = 0;
  private static final Set SET_ALL_FACINGS = Collections.unmodifiableSet(new HashSet(Arrays.asList(cq.n)));
  private int countTileEntitiesRendered;
  
  public bno(bcf mcIn)
  {
    this.cloudRenderer = new CloudRenderer(mcIn);
    
    this.h = mcIn;
    this.j = mcIn.ac();
    this.i = mcIn.N();
    this.i.a(g);
    bni.b(3553, 10242, 10497);
    bni.b(3553, 10243, 10497);
    bni.i(0);
    p();
    this.Y = bzg.f();
    if (this.Y)
    {
      this.O = new bnv();
      this.a = new bqh();
    }
    else
    {
      this.O = new bnq();
      this.a = new bqd();
    }
    this.s = new bvr();
    this.s.a(new bvs(0, bvs.a.a, bvs.b.a, 3));
    s();
    r();
    q();
  }
  
  public void a(bwg resourceManager)
  {
    p();
  }
  
  private void p()
  {
    bvg texturemap = this.h.R();
    for (int i = 0; i < this.z.length; i++) {
      this.z[i] = texturemap.a("minecraft:blocks/destroy_stage_" + i);
    }
  }
  
  public void b()
  {
    if (bzg.O)
    {
      if (bus.b() == null) {
        bus.a();
      }
      kk resourcelocation = new kk("shaders/post/entity_outline.json");
      try
      {
        this.B = new bup(this.h.N(), this.h.O(), this.h.b(), resourcelocation);
        this.B.a(this.h.d, this.h.e);
        this.A = this.B.a("final");
      }
      catch (IOException ioexception)
      {
        b.warn("Failed to load shader: " + resourcelocation, ioexception);
        this.B = null;
        this.A = null;
      }
      catch (JsonSyntaxException jsonsyntaxexception)
      {
        b.warn("Failed to load shader: " + resourcelocation, jsonsyntaxexception);
        this.B = null;
        this.A = null;
      }
    }
    else
    {
      this.B = null;
      this.A = null;
    }
  }
  
  public void c()
  {
    if (d())
    {
      bni.m();
      bni.a(bni.r.l, bni.l.j, bni.r.o, bni.l.e);
      this.A.a(this.h.d, this.h.e, false);
      bni.l();
    }
  }
  
  protected boolean d()
  {
    return (this.A != null) && (this.B != null) && (this.h.h != null);
  }
  
  private void q()
  {
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    if (this.v != null) {
      this.v.c();
    }
    if (this.r >= 0)
    {
      bce.b(this.r);
      this.r = -1;
    }
    if (this.Y)
    {
      this.v = new bvq(this.s);
      a(vertexbuffer, -16.0F, true);
      vertexbuffer.e();
      vertexbuffer.b();
      this.v.a(vertexbuffer.f());
    }
    else
    {
      this.r = bce.a(1);
      bni.f(this.r, 4864);
      a(vertexbuffer, -16.0F, true);
      tessellator.b();
      bni.K();
    }
  }
  
  private void r()
  {
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    if (this.u != null) {
      this.u.c();
    }
    if (this.q >= 0)
    {
      bce.b(this.q);
      this.q = -1;
    }
    if (this.Y)
    {
      this.u = new bvq(this.s);
      a(vertexbuffer, 16.0F, false);
      vertexbuffer.e();
      vertexbuffer.b();
      this.u.a(vertexbuffer.f());
    }
    else
    {
      this.q = bce.a(1);
      bni.f(this.q, 4864);
      a(vertexbuffer, 16.0F, false);
      tessellator.b();
      bni.K();
    }
  }
  
  private void a(bmz worldRendererIn, float posY, boolean reverseX)
  {
    int i = 64;
    int j = 6;
    worldRendererIn.a(7, bvp.e);
    for (int k = 65152; k <= 384; k += 64) {
      for (int l = 65152; l <= 384; l += 64)
      {
        float f = k;
        float f1 = k + 64;
        if (reverseX)
        {
          f1 = k;
          f = k + 64;
        }
        worldRendererIn.b(f, posY, l).d();
        worldRendererIn.b(f1, posY, l).d();
        worldRendererIn.b(f1, posY, l + 64).d();
        worldRendererIn.b(f, posY, l + 64).d();
      }
    }
  }
  
  private void s()
  {
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    if (this.t != null) {
      this.t.c();
    }
    if (this.p >= 0)
    {
      bce.b(this.p);
      this.p = -1;
    }
    if (this.Y)
    {
      this.t = new bvq(this.s);
      a(vertexbuffer);
      vertexbuffer.e();
      vertexbuffer.b();
      this.t.a(vertexbuffer.f());
    }
    else
    {
      this.p = bce.a(1);
      bni.G();
      bni.f(this.p, 4864);
      a(vertexbuffer);
      tessellator.b();
      bni.K();
      bni.H();
    }
  }
  
  private void a(bmz worldRendererIn)
  {
    Random random = new Random(10842L);
    worldRendererIn.a(7, bvp.e);
    for (int i = 0; i < 1500; i++)
    {
      double d0 = random.nextFloat() * 2.0F - 1.0F;
      double d1 = random.nextFloat() * 2.0F - 1.0F;
      double d2 = random.nextFloat() * 2.0F - 1.0F;
      double d3 = 0.15F + random.nextFloat() * 0.1F;
      double d4 = d0 * d0 + d1 * d1 + d2 * d2;
      if ((d4 < 1.0D) && (d4 > 0.01D))
      {
        d4 = 1.0D / Math.sqrt(d4);
        d0 *= d4;
        d1 *= d4;
        d2 *= d4;
        double d5 = d0 * 100.0D;
        double d6 = d1 * 100.0D;
        double d7 = d2 * 100.0D;
        double d8 = Math.atan2(d0, d2);
        double d9 = Math.sin(d8);
        double d10 = Math.cos(d8);
        double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
        double d12 = Math.sin(d11);
        double d13 = Math.cos(d11);
        double d14 = random.nextDouble() * 3.141592653589793D * 2.0D;
        double d15 = Math.sin(d14);
        double d16 = Math.cos(d14);
        for (int j = 0; j < 4; j++)
        {
          double d17 = 0.0D;
          double d18 = ((j & 0x2) - 1) * d3;
          double d19 = ((j + 1 & 0x2) - 1) * d3;
          double d20 = 0.0D;
          double d21 = d18 * d16 - d19 * d15;
          double d22 = d19 * d16 + d18 * d15;
          double d23 = d21 * d12 + 0.0D * d13;
          double d24 = 0.0D * d12 - d21 * d13;
          double d25 = d24 * d9 - d22 * d10;
          double d26 = d22 * d9 + d24 * d10;
          worldRendererIn.b(d5 + d25, d6 + d23, d7 + d26).d();
        }
      }
    }
  }
  
  public void a(bku worldClientIn)
  {
    if (this.k != null) {
      this.k.b(this);
    }
    this.C = Double.MIN_VALUE;
    this.D = Double.MIN_VALUE;
    this.E = Double.MIN_VALUE;
    this.F = Integer.MIN_VALUE;
    this.G = Integer.MIN_VALUE;
    this.H = Integer.MIN_VALUE;
    this.j.a(worldClientIn);
    this.k = worldClientIn;
    if (worldClientIn != null)
    {
      worldClientIn.a(this);
      a();
    }
    else
    {
      this.l.clear();
      this.m.clear();
      this.o = null;
      if (this.N != null) {
        this.N.g();
      }
      this.N = null;
    }
  }
  
  public void a()
  {
    if (this.k != null)
    {
      if (this.N == null) {
        this.N = new bqa();
      }
      this.ac = true;
      
      aju.t.b(Config.isTreesFancy());
      aju.u.b(Config.isTreesFancy());
      
      boe.updateAoLightValue();
      
      this.P = this.h.u.c;
      
      this.renderDistance = (this.P * 16);
      this.renderDistanceSq = (this.renderDistance * this.renderDistance);
      
      boolean flag = this.Y;
      this.Y = bzg.f();
      if ((flag) && (!this.Y))
      {
        this.O = new bnq();
        this.a = new bqd();
      }
      else if ((!flag) && (this.Y))
      {
        this.O = new bnv();
        this.a = new bqh();
      }
      if (flag != this.Y)
      {
        s();
        r();
        q();
      }
      if (this.o != null) {
        this.o.a();
      }
      e();
      synchronized (this.n)
      {
        this.n.clear();
      }
      this.o = new bnx(this.k, this.h.u.c, this, this.a);
      if (this.k != null)
      {
        rr entity = this.h.aa();
        if (entity != null) {
          this.o.a(entity.p, entity.r);
        }
      }
      this.Q = 2;
    }
  }
  
  protected void e()
  {
    this.l.clear();
    this.N.b();
  }
  
  public void a(int width, int height)
  {
    if (bzg.O) {
      if (this.B != null) {
        this.B.a(width, height);
      }
    }
  }
  
  public void a(rr renderViewEntity, bqm camera, float partialTicks)
  {
    int pass = 0;
    if (Reflector.MinecraftForgeClient_getRenderPass.exists()) {
      pass = Reflector.callInt(Reflector.MinecraftForgeClient_getRenderPass, new Object[0]);
    }
    if (this.Q > 0)
    {
      if (pass > 0) {
        return;
      }
      this.Q -= 1;
    }
    else
    {
      double d0 = renderViewEntity.m + (renderViewEntity.p - renderViewEntity.m) * partialTicks;
      double d1 = renderViewEntity.n + (renderViewEntity.q - renderViewEntity.n) * partialTicks;
      double d2 = renderViewEntity.o + (renderViewEntity.r - renderViewEntity.o) * partialTicks;
      this.k.C.a("prepare");
      bpm.a.a(this.k, this.h.N(), this.h.k, this.h.aa(), partialTicks);
      this.j.a(this.k, this.h.k, this.h.aa(), this.h.i, this.h.u, partialTicks);
      if (pass == 0)
      {
        this.R = 0;
        this.S = 0;
        this.T = 0;
        
        this.countTileEntitiesRendered = 0;
      }
      rr entity = this.h.aa();
      double d3 = entity.M + (entity.p - entity.M) * partialTicks;
      double d4 = entity.N + (entity.q - entity.N) * partialTicks;
      double d5 = entity.O + (entity.r - entity.O) * partialTicks;
      bpm.b = d3;
      bpm.c = d4;
      bpm.d = d5;
      this.j.a(d3, d4, d5);
      this.h.o.i();
      this.k.C.c("global");
      List<rr> list = this.k.J();
      if (pass == 0) {
        this.R = list.size();
      }
      if ((Config.isFogOff()) && (this.h.o.fogStandard)) {
        bni.p();
      }
      boolean forgeEntityPass = Reflector.ForgeEntity_shouldRenderInPass.exists();
      boolean forgeTileEntityPass = Reflector.ForgeTileEntity_shouldRenderInPass.exists();
      for (int i = 0; i < this.k.j.size(); i++)
      {
        rr entity1 = (rr)this.k.j.get(i);
        if (forgeEntityPass)
        {
          if (!Reflector.callBoolean(entity1, Reflector.ForgeEntity_shouldRenderInPass, new Object[] { Integer.valueOf(pass) })) {}
        }
        else
        {
          this.S += 1;
          if (entity1.h(d0, d1, d2)) {
            this.j.a(entity1, partialTicks, false);
          }
        }
      }
      this.k.C.c("entities");
      
      boolean isShaders = Config.isShaders();
      if (isShaders) {
        Shaders.beginEntities();
      }
      boolean oldFancyGraphics = this.h.u.i;
      this.h.u.i = Config.isDroppedItemsFancy();
      
      List<rr> list1 = Lists.newArrayList();
      List<rr> list2 = Lists.newArrayList();
      
      Iterator iterInfosEntities = this.renderInfosEntities.iterator();
      while (iterInfosEntities.hasNext())
      {
        bno.a renderglobal$containerlocalrenderinformation = (bno.a)iterInfosEntities.next();
        
        ase chunk = this.k.f(renderglobal$containerlocalrenderinformation.a.k());
        ny<rr> classinheritancemultimap = chunk.t()[(renderglobal$containerlocalrenderinformation.a.k().q() / 16)];
        if (!classinheritancemultimap.isEmpty()) {
          for (rr entity2 : classinheritancemultimap) {
            if (forgeEntityPass)
            {
              if (!Reflector.callBoolean(entity2, Reflector.ForgeEntity_shouldRenderInPass, new Object[] { Integer.valueOf(pass) })) {}
            }
            else
            {
              boolean flag = (this.j.a(entity2, camera, d0, d1, d2)) || (entity2.y(this.h.h));
              if (flag)
              {
                boolean flag1 = (this.h.aa() instanceof sa) ? ((sa)this.h.aa()).cl() : false;
                if (((entity2 != this.h.aa()) || (this.h.u.ap != 0) || (flag1)) && ((entity2.q < 0.0D) || (entity2.q >= 256.0D) || (this.k.e(new cj(entity2)))))
                {
                  this.S += 1;
                  if (entity2.getClass() == xs.class) {
                    rr.b(0.06D);
                  }
                  this.renderedEntity = entity2;
                  if (isShaders) {
                    Shaders.nextEntity();
                  }
                  this.j.a(entity2, partialTicks, false);
                  
                  this.renderedEntity = null;
                  if (a(entity2, entity, camera)) {
                    list1.add(entity2);
                  }
                  if (this.j.b(entity2)) {
                    list2.add(entity2);
                  }
                }
              }
            }
          }
        }
      }
      if (!list2.isEmpty()) {
        for (rr entity3 : list2) {
          if (forgeEntityPass)
          {
            if (!Reflector.callBoolean(entity3, Reflector.ForgeEntity_shouldRenderInPass, new Object[] { Integer.valueOf(pass) })) {}
          }
          else
          {
            if (isShaders) {
              Shaders.nextEntity();
            }
            this.j.a(entity3, partialTicks);
          }
        }
      }
      if ((d()) && ((!list1.isEmpty()) || (this.ad)))
      {
        this.k.C.c("entityOutlines");
        this.A.f();
        this.ad = (!list1.isEmpty());
        if (!list1.isEmpty())
        {
          bni.c(519);
          bni.p();
          this.A.a(false);
          bcd.a();
          this.j.c(true);
          for (int j = 0; j < list1.size(); j++)
          {
            rr entityOutline = (rr)list1.get(j);
            if (forgeEntityPass)
            {
              if (!Reflector.callBoolean(entityOutline, Reflector.ForgeEntity_shouldRenderInPass, new Object[] { Integer.valueOf(pass) })) {}
            }
            else
            {
              if (isShaders) {
                Shaders.nextEntity();
              }
              this.j.a(entityOutline, partialTicks, false);
            }
          }
          this.j.c(false);
          bcd.b();
          bni.a(false);
          this.B.a(partialTicks);
          bni.f();
          bni.a(true);
          bni.o();
          bni.m();
          bni.h();
          bni.c(515);
          bni.k();
          bni.e();
        }
        this.h.b().a(false);
      }
      this.h.u.i = oldFancyGraphics;
      
      bct fontRenderer = bpm.a.a();
      if (isShaders)
      {
        Shaders.endEntities();
        Shaders.beginBlockEntities();
      }
      this.k.C.c("blockentities");
      bcd.b();
      
      Iterator iterInfoBlockEntities = this.renderInfosTileEntities.iterator();
      while (iterInfoBlockEntities.hasNext())
      {
        bno.a renderglobal$containerlocalrenderinformation1 = (bno.a)iterInfoBlockEntities.next();
        
        List<apv> list3 = renderglobal$containerlocalrenderinformation1.a.h().b();
        if (!list3.isEmpty()) {
          for (apv tileentity2 : list3) {
            if (forgeTileEntityPass)
            {
              if (Reflector.callBoolean(tileentity2, Reflector.ForgeTileEntity_shouldRenderInPass, new Object[] { Integer.valueOf(pass) }))
              {
                bbh aabb = (bbh)Reflector.call(tileentity2, Reflector.ForgeTileEntity_getRenderBoundingBox, new Object[0]);
                if ((aabb != null) && (!camera.a(aabb))) {}
              }
            }
            else
            {
              apv te = tileentity2;
              Class teClass = te.getClass();
              if (teClass == aqn.class) {
                if (!Config.zoomMode)
                {
                  zj pl = this.h.h;
                  double distSq = te.a(pl.p, pl.q, pl.r);
                  if (distSq > 256.0D) {
                    fontRenderer.enabled = false;
                  }
                }
              }
              if (isShaders) {
                Shaders.nextBlockEntity();
              }
              bpm.a.a(tileentity2, partialTicks, -1);
              
              this.countTileEntitiesRendered += 1;
              
              fontRenderer.enabled = true;
            }
          }
        }
      }
      synchronized (this.n)
      {
        for (apv tileentity : this.n) {
          if (forgeTileEntityPass)
          {
            if (!Reflector.callBoolean(tileentity, Reflector.ForgeTileEntity_shouldRenderInPass, new Object[] { Integer.valueOf(pass) })) {}
          }
          else
          {
            if (isShaders) {
              Shaders.nextBlockEntity();
            }
            bpm.a.a(tileentity, partialTicks, -1);
          }
        }
      }
      if (Reflector.ForgeTileEntityRendererDispatcher_drawBatch.exists()) {
        Reflector.call(bpm.a, Reflector.ForgeTileEntityRendererDispatcher_drawBatch, new Object[] { Integer.valueOf(pass) });
      }
      u();
      for (li destroyblockprogress : this.x.values())
      {
        cj blockpos = destroyblockprogress.b();
        apv tileentity1 = this.k.r(blockpos);
        if ((tileentity1 instanceof apx))
        {
          apx tileentitychest = (apx)tileentity1;
          if (tileentitychest.h != null)
          {
            blockpos = blockpos.a(cq.e);
            tileentity1 = this.k.r(blockpos);
          }
          else if (tileentitychest.f != null)
          {
            blockpos = blockpos.a(cq.c);
            tileentity1 = this.k.r(blockpos);
          }
        }
        ajt block = this.k.o(blockpos).t();
        boolean shouldRender;
        if (forgeTileEntityPass)
        {
          apv tileEntity = tileentity1;
          boolean shouldRender = false;
          if (tileEntity != null) {
            if ((Reflector.callBoolean(tileEntity, Reflector.ForgeTileEntity_shouldRenderInPass, new Object[] { Integer.valueOf(pass) })) && (Reflector.callBoolean(tileEntity, Reflector.ForgeTileEntity_canRenderBreaking, new Object[0])))
            {
              bbh aabb = (bbh)Reflector.call(tileEntity, Reflector.ForgeTileEntity_getRenderBoundingBox, new Object[0]);
              if (aabb != null) {
                shouldRender = camera.a(aabb);
              }
            }
          }
        }
        else
        {
          shouldRender = (tileentity1 != null) && (((block instanceof ake)) || ((block instanceof alf)) || ((block instanceof aoj)) || ((block instanceof aok)));
        }
        if (shouldRender)
        {
          if (isShaders) {
            Shaders.nextBlockEntity();
          }
          bpm.a.a(tileentity1, partialTicks, destroyblockprogress.c());
        }
      }
      v();
      this.h.o.h();
      this.h.B.b();
    }
  }
  
  private boolean a(rr p_184383_1_, rr p_184383_2_, bqm p_184383_3_)
  {
    boolean flag = ((p_184383_2_ instanceof sa)) && (((sa)p_184383_2_).cl());
    return (p_184383_1_ != p_184383_2_) || (this.h.u.ap != 0) || (flag);
  }
  
  public String f()
  {
    int i = this.o.f.length;
    int j = g();
    return String.format("C: %d/%d %sD: %d, L: %d, %s", new Object[] { Integer.valueOf(j), Integer.valueOf(i), this.h.H ? "(s) " : "", Integer.valueOf(this.P), Integer.valueOf(this.ae.size()), this.N == null ? "null" : this.N.a() });
  }
  
  protected int g()
  {
    int i = 0;
    for (bno.a renderglobal$containerlocalrenderinformation : this.m)
    {
      bqc compiledchunk = renderglobal$containerlocalrenderinformation.a.b;
      if ((compiledchunk != bqc.a) && (!compiledchunk.a())) {
        i++;
      }
    }
    return i;
  }
  
  public String h()
  {
    return "E: " + this.S + "/" + this.R + ", B: " + this.T + ", " + Config.getVersionShaders();
  }
  
  public void a(rr viewEntity, double partialTicks, bqm camera, int frameCount, boolean playerSpectator)
  {
    if (this.h.u.c != this.P) {
      a();
    }
    this.k.C.a("camera");
    double d0 = viewEntity.p - this.C;
    double d1 = viewEntity.q - this.D;
    double d2 = viewEntity.r - this.E;
    if ((this.F != viewEntity.ab) || (this.G != viewEntity.ac) || (this.H != viewEntity.ad) || (d0 * d0 + d1 * d1 + d2 * d2 > 16.0D))
    {
      this.C = viewEntity.p;
      this.D = viewEntity.q;
      this.E = viewEntity.r;
      this.F = viewEntity.ab;
      this.G = viewEntity.ac;
      this.H = viewEntity.ad;
      this.o.a(viewEntity.p, viewEntity.r);
    }
    this.k.C.c("renderlistcamera");
    double d3 = viewEntity.M + (viewEntity.p - viewEntity.M) * partialTicks;
    double d4 = viewEntity.N + (viewEntity.q - viewEntity.N) * partialTicks;
    double d5 = viewEntity.O + (viewEntity.r - viewEntity.O) * partialTicks;
    this.O.a(d3, d4, d5);
    this.k.C.c("cull");
    if (this.V != null)
    {
      bqo frustum = new bqo(this.V);
      frustum.a(this.X.a, this.X.b, this.X.c);
      camera = frustum;
    }
    this.h.B.c("culling");
    cj blockpos1 = new cj(d3, d4 + viewEntity.bn(), d5);
    bqf renderchunk = this.o.a(blockpos1);
    cj blockpos = new cj(on.c(d3 / 16.0D) * 16, on.c(d4 / 16.0D) * 16, on.c(d5 / 16.0D) * 16);
    this.ac = ((this.ac) || (!this.l.isEmpty()) || (viewEntity.p != this.I) || (viewEntity.q != this.J) || (viewEntity.r != this.K) || (viewEntity.w != this.L) || (viewEntity.v != this.M));
    this.I = viewEntity.p;
    this.J = viewEntity.q;
    this.K = viewEntity.r;
    this.L = viewEntity.w;
    this.M = viewEntity.v;
    boolean flag = this.V != null;
    this.h.B.c("update");
    
    Lagometer.timerVisibility.start();
    
    ase viewEntityChunk = this.k.f().b(viewEntity.ab, viewEntity.ad);
    if (viewEntityChunk == null) {
      this.ac = true;
    }
    if (Shaders.isShadowPass)
    {
      this.m = this.renderInfosShadow;
      this.renderInfosEntities = this.renderInfosEntitiesShadow;
      this.renderInfosTileEntities = this.renderInfosTileEntitiesShadow;
      if ((!flag) && (this.ac))
      {
        this.m.clear();
        this.renderInfosEntities.clear();
        this.renderInfosTileEntities.clear();
        for (bqf chunk : this.o.f)
        {
          bno.a info = new bno.a(chunk, null, 0, null);
          cj pos = chunk.k();
          if ((!chunk.b.a()) || (chunk.n()))
          {
            if (info == null) {
              info = new bno.a(chunk, null, 0, null);
            }
            this.m.add(info);
          }
          if (ChunkUtils.hasEntities(this.k.f(pos)))
          {
            if (info == null) {
              info = new bno.a(chunk, null, 0, null);
            }
            this.renderInfosEntities.add(info);
          }
          if (chunk.h().b().size() > 0)
          {
            if (info == null) {
              info = new bno.a(chunk, null, 0, null);
            }
            this.renderInfosTileEntities.add(info);
          }
        }
      }
    }
    else
    {
      this.m = this.renderInfosNormal;
      this.renderInfosEntities = this.renderInfosEntitiesNormal;
      this.renderInfosTileEntities = this.renderInfosTileEntitiesNormal;
    }
    if ((!flag) && (this.ac) && (!Shaders.isShadowPass))
    {
      this.ac = false;
      
      this.m.clear();
      this.renderInfosEntities.clear();
      this.renderInfosTileEntities.clear();
      
      this.visibilityDeque.clear();
      Deque queue = this.visibilityDeque;
      
      rr.b(on.a(this.h.u.c / 8.0D, 1.0D, 2.5D));
      boolean flag1 = this.h.H;
      if (renderchunk != null)
      {
        boolean flag2 = false;
        bno.a renderglobal$containerlocalrenderinformation3 = new bno.a(renderchunk, (cq)null, 0, null);
        
        Set set1 = SET_ALL_FACINGS;
        if (set1.size() == 1)
        {
          Vector3f vector3f = a(viewEntity, partialTicks);
          cq enumfacing = cq.a(vector3f.x, vector3f.y, vector3f.z).d();
          set1.remove(enumfacing);
        }
        if (set1.isEmpty()) {
          flag2 = true;
        }
        if ((flag2) && (!playerSpectator))
        {
          this.m.add(renderglobal$containerlocalrenderinformation3);
        }
        else
        {
          if ((playerSpectator) && (this.k.o(blockpos1).p())) {
            flag1 = false;
          }
          renderchunk.a(frameCount);
          queue.add(renderglobal$containerlocalrenderinformation3);
        }
      }
      else
      {
        int i = blockpos1.q() > 0 ? 248 : 8;
        for (int j = -this.P; j <= this.P; j++) {
          for (int k = -this.P; k <= this.P; k++)
          {
            bqf renderchunk1 = this.o.a(new cj((j << 4) + 8, i, (k << 4) + 8));
            if ((renderchunk1 != null) && (camera.a(renderchunk1.c)))
            {
              renderchunk1.a(frameCount);
              queue.add(new bno.a(renderchunk1, (cq)null, 0, null));
            }
          }
        }
      }
      this.h.B.a("iteration");
      
      cq[] enumFacingValues = cq.n;
      int countFacingValues = enumFacingValues.length;
      while (!queue.isEmpty())
      {
        bno.a renderglobal$containerlocalrenderinformation1 = (bno.a)queue.poll();
        bqf renderchunk3 = renderglobal$containerlocalrenderinformation1.a;
        cq enumfacing2 = renderglobal$containerlocalrenderinformation1.b;
        cj blockpos3 = renderchunk3.k();
        if ((!renderchunk3.b.a()) || (renderchunk3.n())) {
          this.m.add(renderglobal$containerlocalrenderinformation1);
        }
        if (ChunkUtils.hasEntities(this.k.f(blockpos3))) {
          this.renderInfosEntities.add(renderglobal$containerlocalrenderinformation1);
        }
        if (renderchunk3.h().b().size() > 0) {
          this.renderInfosTileEntities.add(renderglobal$containerlocalrenderinformation1);
        }
        for (int iv = 0; iv < countFacingValues; iv++)
        {
          cq enumfacing1 = enumFacingValues[iv];
          if (((!flag1) || (!renderglobal$containerlocalrenderinformation1.c.contains(enumfacing1.d()))) && ((!flag1) || (enumfacing2 == null) || (renderchunk3.h().a(enumfacing2.d(), enumfacing1))))
          {
            bqf renderchunk2 = a(blockpos1, renderchunk3, enumfacing1);
            if ((renderchunk2 != null) && (renderchunk2.a(frameCount)) && (camera.a(renderchunk2.c)))
            {
              bno.a renderglobal$containerlocalrenderinformation = new bno.a(renderchunk2, enumfacing1, renderglobal$containerlocalrenderinformation1.d + 1, null);
              renderglobal$containerlocalrenderinformation.c.addAll(renderglobal$containerlocalrenderinformation1.c);
              renderglobal$containerlocalrenderinformation.c.add(enumfacing1);
              queue.add(renderglobal$containerlocalrenderinformation);
            }
          }
        }
      }
      this.h.B.b();
    }
    this.h.B.c("captureFrustum");
    if (this.U)
    {
      a(d3, d4, d5);
      this.U = false;
    }
    Lagometer.timerVisibility.end();
    if (Shaders.isShadowPass)
    {
      Shaders.mcProfilerEndSection();
      return;
    }
    this.h.B.c("rebuildNear");
    
    this.N.e();
    
    Set<bqf> set = this.l;
    this.l = Sets.newLinkedHashSet();
    
    Lagometer.timerChunkUpdate.start();
    for (bno.a renderglobal$containerlocalrenderinformation2 : this.m)
    {
      bqf renderchunk4 = renderglobal$containerlocalrenderinformation2.a;
      if ((renderchunk4.n()) || (set.contains(renderchunk4)))
      {
        this.ac = true;
        cj blockpos2 = renderchunk4.k().a(8, 8, 8);
        boolean flag3 = blockpos2.k(blockpos1) < 768.0D;
        if (!flag3)
        {
          this.l.add(renderchunk4);
        }
        else if (!Config.isActing())
        {
          this.chunksToUpdateForced.add(renderchunk4);
        }
        else
        {
          this.h.B.a("build near");
          this.N.b(renderchunk4);
          renderchunk4.m();
          this.h.B.b();
        }
      }
    }
    Lagometer.timerChunkUpdate.end();
    
    this.l.addAll(set);
    this.h.B.b();
  }
  
  private Set<cq> b(cj pos)
  {
    bqi visgraph = new bqi();
    cj blockpos = new cj(pos.p() >> 4 << 4, pos.q() >> 4 << 4, pos.r() >> 4 << 4);
    ase chunk = this.k.f(blockpos);
    for (cj.a blockpos$mutableblockpos : cj.b(blockpos, blockpos.a(15, 15, 15))) {
      if (chunk.a(blockpos$mutableblockpos).p()) {
        visgraph.a(blockpos$mutableblockpos);
      }
    }
    return visgraph.b(pos);
  }
  
  private bqf a(cj playerPos, bqf renderChunkBase, cq facing)
  {
    cj var4 = renderChunkBase.getPositionOffset16(facing);
    if ((var4.q() < 0) || (var4.q() >= 256)) {
      return null;
    }
    int dx = on.a(playerPos.p() - var4.p());
    int dz = on.a(playerPos.r() - var4.r());
    if (Config.isFogOff())
    {
      if ((dx > this.renderDistance) || (dz > this.renderDistance)) {
        return null;
      }
    }
    else
    {
      int distSq = dx * dx + dz * dz;
      if (distSq > this.renderDistanceSq) {
        return null;
      }
    }
    return this.o.a(var4);
  }
  
  private void a(double x, double y, double z)
  {
    this.V = new bqn();
    ((bqn)this.V).b();
    bzc matrix4f = new bzc(this.V.c);
    matrix4f.transpose();
    bzc matrix4f1 = new bzc(this.V.b);
    matrix4f1.transpose();
    bzc matrix4f2 = new bzc();
    bzc.mul(matrix4f1, matrix4f, matrix4f2);
    matrix4f2.invert();
    this.X.a = x;
    this.X.b = y;
    this.X.c = z;
    this.W[0] = new Vector4f(-1.0F, -1.0F, -1.0F, 1.0F);
    this.W[1] = new Vector4f(1.0F, -1.0F, -1.0F, 1.0F);
    this.W[2] = new Vector4f(1.0F, 1.0F, -1.0F, 1.0F);
    this.W[3] = new Vector4f(-1.0F, 1.0F, -1.0F, 1.0F);
    this.W[4] = new Vector4f(-1.0F, -1.0F, 1.0F, 1.0F);
    this.W[5] = new Vector4f(1.0F, -1.0F, 1.0F, 1.0F);
    this.W[6] = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
    this.W[7] = new Vector4f(-1.0F, 1.0F, 1.0F, 1.0F);
    for (int i = 0; i < 8; i++)
    {
      bzc.transform(matrix4f2, this.W[i], this.W[i]);
      this.W[i].x /= this.W[i].w;
      this.W[i].y /= this.W[i].w;
      this.W[i].z /= this.W[i].w;
      this.W[i].w = 1.0F;
    }
  }
  
  protected Vector3f a(rr entityIn, double partialTicks)
  {
    float f = (float)(entityIn.y + (entityIn.w - entityIn.y) * partialTicks);
    float f1 = (float)(entityIn.x + (entityIn.v - entityIn.x) * partialTicks);
    if (bcf.z().u.ap == 2) {
      f += 180.0F;
    }
    float f2 = on.b(-f1 * 0.017453292F - 3.1415927F);
    float f3 = on.a(-f1 * 0.017453292F - 3.1415927F);
    float f4 = -on.b(-f * 0.017453292F);
    float f5 = on.a(-f * 0.017453292F);
    return new Vector3f(f3 * f4, f5, f2 * f4);
  }
  
  public int a(ahm blockLayerIn, double partialTicks, int pass, rr entityIn)
  {
    
    if (blockLayerIn == ahm.d)
    {
      this.h.B.a("translucent_sort");
      double d0 = entityIn.p - this.Z;
      double d1 = entityIn.q - this.aa;
      double d2 = entityIn.r - this.ab;
      int k;
      if (d0 * d0 + d1 * d1 + d2 * d2 > 1.0D)
      {
        this.Z = entityIn.p;
        this.aa = entityIn.q;
        this.ab = entityIn.r;
        k = 0;
        
        this.chunksToResortTransparency.clear();
        for (bno.a renderglobal$containerlocalrenderinformation : this.m) {
          if ((renderglobal$containerlocalrenderinformation.a.b.d(blockLayerIn)) && (k++ < 15)) {
            this.chunksToResortTransparency.add(renderglobal$containerlocalrenderinformation.a);
          }
        }
      }
      this.h.B.b();
    }
    this.h.B.a("filterempty");
    int l = 0;
    boolean flag = blockLayerIn == ahm.d;
    int i1 = flag ? this.m.size() - 1 : 0;
    int i = flag ? -1 : this.m.size();
    int j1 = flag ? -1 : 1;
    for (int j = i1; j != i; j += j1)
    {
      bqf renderchunk = ((bno.a)this.m.get(j)).a;
      if (!renderchunk.h().b(blockLayerIn))
      {
        l++;
        this.O.a(renderchunk, blockLayerIn);
      }
    }
    if (l == 0)
    {
      this.h.B.b();
      return l;
    }
    if ((Config.isFogOff()) && (this.h.o.fogStandard)) {
      bni.p();
    }
    this.h.B.c("render_" + blockLayerIn);
    a(blockLayerIn);
    this.h.B.b();
    return l;
  }
  
  private void a(ahm blockLayerIn)
  {
    this.h.o.i();
    if (bzg.f())
    {
      bni.q(32884);
      bzg.l(bzg.q);
      bni.q(32888);
      bzg.l(bzg.r);
      bni.q(32888);
      bzg.l(bzg.q);
      bni.q(32886);
    }
    if (Config.isShaders()) {
      ShadersRender.preRenderChunkLayer();
    }
    this.O.a(blockLayerIn);
    if (Config.isShaders()) {
      ShadersRender.postRenderChunkLayer();
    }
    if (bzg.f()) {
      for (bvs vertexformatelement : bvp.a.h())
      {
        bvs.b vertexformatelement$enumusage = vertexformatelement.b();
        int i = vertexformatelement.d();
        switch (vertexformatelement$enumusage)
        {
        case a: 
          bni.p(32884);
          break;
        case d: 
          bzg.l(bzg.q + i);
          bni.p(32888);
          bzg.l(bzg.q);
          break;
        case c: 
          bni.p(32886);
          bni.I();
        }
      }
    }
    this.h.o.h();
  }
  
  private void a(Iterator<li> iteratorIn)
  {
    while (iteratorIn.hasNext())
    {
      li destroyblockprogress = (li)iteratorIn.next();
      int i = destroyblockprogress.d();
      if (this.w - i > 400) {
        iteratorIn.remove();
      }
    }
  }
  
  public void k()
  {
    if (Config.isShaders()) {
      if ((Keyboard.isKeyDown(61)) && (Keyboard.isKeyDown(19))) {
        Shaders.uninit();
      }
    }
    this.w += 1;
    if (this.w % 20 == 0) {
      a(this.x.values().iterator());
    }
    if ((!this.ae.isEmpty()) && (!this.N.h()) && (this.l.isEmpty()))
    {
      Iterator<cj> iterator = this.ae.iterator();
      while (iterator.hasNext())
      {
        cj blockpos = (cj)iterator.next();
        iterator.remove();
        int i = blockpos.p();
        int j = blockpos.q();
        int k = blockpos.r();
        a(i - 1, j - 1, k - 1, i + 1, j + 1, k + 1, false);
      }
    }
  }
  
  private void t()
  {
    if (!Config.isSkyEnabled()) {
      return;
    }
    bni.p();
    bni.d();
    bni.m();
    bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
    bcd.a();
    bni.a(false);
    this.i.a(f);
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    for (int i = 0; i < 6; i++)
    {
      bni.G();
      if (i == 1) {
        bni.b(90.0F, 1.0F, 0.0F, 0.0F);
      }
      if (i == 2) {
        bni.b(-90.0F, 1.0F, 0.0F, 0.0F);
      }
      if (i == 3) {
        bni.b(180.0F, 1.0F, 0.0F, 0.0F);
      }
      if (i == 4) {
        bni.b(90.0F, 0.0F, 0.0F, 1.0F);
      }
      if (i == 5) {
        bni.b(-90.0F, 0.0F, 0.0F, 1.0F);
      }
      vertexbuffer.a(7, bvp.i);
      vertexbuffer.b(-100.0D, -100.0D, -100.0D).a(0.0D, 0.0D).b(40, 40, 40, 255).d();
      vertexbuffer.b(-100.0D, -100.0D, 100.0D).a(0.0D, 16.0D).b(40, 40, 40, 255).d();
      vertexbuffer.b(100.0D, -100.0D, 100.0D).a(16.0D, 16.0D).b(40, 40, 40, 255).d();
      vertexbuffer.b(100.0D, -100.0D, -100.0D).a(16.0D, 0.0D).b(40, 40, 40, 255).d();
      tessellator.b();
      bni.H();
    }
    bni.a(true);
    bni.y();
    bni.e();
  }
  
  public void a(float partialTicks, int pass)
  {
    if (Reflector.ForgeWorldProvider_getSkyRenderer.exists())
    {
      asv wp = this.h.f.s;
      Object skyRenderer = Reflector.call(wp, Reflector.ForgeWorldProvider_getSkyRenderer, new Object[0]);
      if (skyRenderer != null)
      {
        Reflector.callVoid(skyRenderer, Reflector.IRenderHandler_render, new Object[] { Float.valueOf(partialTicks), this.k, this.h });
        return;
      }
    }
    if (this.h.f.s.p() == asw.c)
    {
      t();
    }
    else if (this.h.f.s.d())
    {
      bni.z();
      
      boolean isShaders = Config.isShaders();
      if (isShaders) {
        Shaders.disableTexture2D();
      }
      bbj vec3d = this.k.a(this.h.aa(), partialTicks);
      
      vec3d = CustomColors.getSkyColor(vec3d, this.h.f, this.h.aa().p, this.h.aa().q + 1.0D, this.h.aa().r);
      if (isShaders) {
        Shaders.setSkyColor(vec3d);
      }
      float f = (float)vec3d.b;
      float f1 = (float)vec3d.c;
      float f2 = (float)vec3d.d;
      if (pass != 2)
      {
        float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
        float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
        float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
        f = f3;
        f1 = f4;
        f2 = f5;
      }
      bni.d(f, f1, f2);
      bnu tessellator = bnu.a();
      bmz vertexbuffer = tessellator.c();
      bni.a(false);
      bni.o();
      if (isShaders) {
        Shaders.enableFog();
      }
      bni.d(f, f1, f2);
      if (isShaders) {
        Shaders.preSkyList();
      }
      if (Config.isSkyEnabled()) {
        if (this.Y)
        {
          this.u.a();
          bni.q(32884);
          bni.d(3, 5126, 12, 0);
          this.u.a(7);
          this.u.b();
          bni.p(32884);
        }
        else
        {
          bni.s(this.q);
        }
      }
      bni.p();
      if (isShaders) {
        Shaders.disableFog();
      }
      bni.d();
      bni.m();
      bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
      bcd.a();
      float[] afloat = this.k.s.a(this.k.c(partialTicks), partialTicks);
      if ((afloat != null) && (Config.isSunMoonEnabled()))
      {
        bni.z();
        if (isShaders) {
          Shaders.disableTexture2D();
        }
        bni.j(7425);
        bni.G();
        bni.b(90.0F, 1.0F, 0.0F, 0.0F);
        bni.b(on.a(this.k.d(partialTicks)) < 0.0F ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
        bni.b(90.0F, 0.0F, 0.0F, 1.0F);
        float f6 = afloat[0];
        float f7 = afloat[1];
        float f8 = afloat[2];
        if (pass != 2)
        {
          float f9 = (f6 * 30.0F + f7 * 59.0F + f8 * 11.0F) / 100.0F;
          float f10 = (f6 * 30.0F + f7 * 70.0F) / 100.0F;
          float f11 = (f6 * 30.0F + f8 * 70.0F) / 100.0F;
          f6 = f9;
          f7 = f10;
          f8 = f11;
        }
        vertexbuffer.a(6, bvp.f);
        vertexbuffer.b(0.0D, 100.0D, 0.0D).a(f6, f7, f8, afloat[3]).d();
        int j = 16;
        for (int l = 0; l <= 16; l++)
        {
          float f21 = l * 6.2831855F / 16.0F;
          float f12 = on.a(f21);
          float f13 = on.b(f21);
          vertexbuffer.b(f12 * 120.0F, f13 * 120.0F, -f13 * 40.0F * afloat[3]).a(afloat[0], afloat[1], afloat[2], 0.0F).d();
        }
        tessellator.b();
        bni.H();
        bni.j(7424);
      }
      bni.y();
      if (isShaders) {
        Shaders.enableTexture2D();
      }
      bni.a(bni.r.l, bni.l.e, bni.r.e, bni.l.n);
      bni.G();
      float f16 = 1.0F - this.k.j(partialTicks);
      bni.c(1.0F, 1.0F, 1.0F, f16);
      bni.b(-90.0F, 0.0F, 1.0F, 0.0F);
      
      CustomSky.renderSky(this.k, this.i, this.k.c(partialTicks), f16);
      if (isShaders) {
        Shaders.preCelestialRotate();
      }
      bni.b(this.k.c(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
      if (isShaders) {
        Shaders.postCelestialRotate();
      }
      if (Config.isSunMoonEnabled())
      {
        float f17 = 30.0F;
        
        this.i.a(d);
        vertexbuffer.a(7, bvp.g);
        vertexbuffer.b(-f17, 100.0D, -f17).a(0.0D, 0.0D).d();
        vertexbuffer.b(f17, 100.0D, -f17).a(1.0D, 0.0D).d();
        vertexbuffer.b(f17, 100.0D, f17).a(1.0D, 1.0D).d();
        vertexbuffer.b(-f17, 100.0D, f17).a(0.0D, 1.0D).d();
        tessellator.b();
        
        f17 = 20.0F;
        
        this.i.a(c);
        int i = this.k.D();
        int k = i % 4;
        int i1 = i / 4 % 2;
        float f22 = (k + 0) / 4.0F;
        float f23 = (i1 + 0) / 2.0F;
        float f24 = (k + 1) / 4.0F;
        float f14 = (i1 + 1) / 2.0F;
        vertexbuffer.a(7, bvp.g);
        vertexbuffer.b(-f17, -100.0D, f17).a(f24, f14).d();
        vertexbuffer.b(f17, -100.0D, f17).a(f22, f14).d();
        vertexbuffer.b(f17, -100.0D, -f17).a(f22, f23).d();
        vertexbuffer.b(-f17, -100.0D, -f17).a(f24, f23).d();
        tessellator.b();
      }
      bni.z();
      if (isShaders) {
        Shaders.disableTexture2D();
      }
      float f15 = this.k.g(partialTicks) * f16;
      if ((f15 > 0.0F) && (Config.isStarsEnabled()) && (!CustomSky.hasSkyLayers(this.k)))
      {
        bni.c(f15, f15, f15, f15);
        if (this.Y)
        {
          this.t.a();
          bni.q(32884);
          bni.d(3, 5126, 12, 0);
          this.t.a(7);
          this.t.b();
          bni.p(32884);
        }
        else
        {
          bni.s(this.p);
        }
      }
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      bni.l();
      bni.e();
      bni.o();
      if (isShaders) {
        Shaders.enableFog();
      }
      bni.H();
      bni.z();
      if (isShaders) {
        Shaders.disableTexture2D();
      }
      bni.d(0.0F, 0.0F, 0.0F);
      double d0 = this.h.h.g(partialTicks).c - this.k.ab();
      if (d0 < 0.0D)
      {
        bni.G();
        bni.c(0.0F, 12.0F, 0.0F);
        if (this.Y)
        {
          this.v.a();
          bni.q(32884);
          bni.d(3, 5126, 12, 0);
          this.v.a(7);
          this.v.b();
          bni.p(32884);
        }
        else
        {
          bni.s(this.r);
        }
        bni.H();
        float f18 = 1.0F;
        float f19 = -(float)(d0 + 65.0D);
        float f20 = -1.0F;
        vertexbuffer.a(7, bvp.f);
        vertexbuffer.b(-1.0D, f19, 1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(1.0D, f19, 1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(1.0D, -1.0D, 1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(-1.0D, -1.0D, 1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(-1.0D, -1.0D, -1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(1.0D, -1.0D, -1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(1.0D, f19, -1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(-1.0D, f19, -1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(1.0D, -1.0D, -1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(1.0D, -1.0D, 1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(1.0D, f19, 1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(1.0D, f19, -1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(-1.0D, f19, -1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(-1.0D, f19, 1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(-1.0D, -1.0D, 1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(-1.0D, -1.0D, -1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(-1.0D, -1.0D, -1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(-1.0D, -1.0D, 1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(1.0D, -1.0D, 1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(1.0D, -1.0D, -1.0D).b(0, 0, 0, 255).d();
        tessellator.b();
      }
      if (this.k.s.g()) {
        bni.d(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
      } else {
        bni.d(f, f1, f2);
      }
      if (this.h.u.c <= 4) {
        bni.d(this.h.o.R, this.h.o.S, this.h.o.T);
      }
      bni.G();
      bni.c(0.0F, -(float)(d0 - 16.0D), 0.0F);
      if (Config.isSkyEnabled()) {
        bni.s(this.r);
      }
      bni.H();
      bni.y();
      if (isShaders) {
        Shaders.enableTexture2D();
      }
      bni.a(true);
    }
  }
  
  public void b(float partialTicks, int pass)
  {
    if (Config.isCloudsOff()) {
      return;
    }
    if (Reflector.ForgeWorldProvider_getCloudRenderer.exists())
    {
      asv wp = this.h.f.s;
      
      Object cloudRenderer = Reflector.call(wp, Reflector.ForgeWorldProvider_getCloudRenderer, new Object[0]);
      if (cloudRenderer != null)
      {
        Reflector.callVoid(cloudRenderer, Reflector.IRenderHandler_render, new Object[] { Float.valueOf(partialTicks), this.k, this.h });
        return;
      }
    }
    if (this.h.f.s.d()) {
      if (Config.isCloudsFancy())
      {
        c(partialTicks, pass);
      }
      else
      {
        float partialTicksPrev = partialTicks;
        this.cloudRenderer.prepareToRender(false, this.w, partialTicksPrev);
        
        partialTicks = 0.0F;
        
        bni.r();
        rr entity = this.h.aa();
        float f = (float)(entity.N + (entity.q - entity.N) * partialTicks);
        int i = 32;
        int j = 8;
        bnu tessellator = bnu.a();
        bmz vertexbuffer = tessellator.c();
        this.i.a(e);
        bni.m();
        bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
        if (this.cloudRenderer.shouldUpdateGlList())
        {
          this.cloudRenderer.startUpdateGlList();
          
          bbj vec3d = this.k.e(partialTicks);
          float f1 = (float)vec3d.b;
          float f2 = (float)vec3d.c;
          float f3 = (float)vec3d.d;
          if (pass != 2)
          {
            float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
            float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
            float f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
            f1 = f4;
            f2 = f5;
            f3 = f6;
          }
          float f10 = 4.8828125E-4F;
          double d2 = this.w + partialTicks;
          double d0 = entity.m + (entity.p - entity.m) * partialTicks + d2 * 0.029999999329447746D;
          double d1 = entity.o + (entity.r - entity.o) * partialTicks;
          int k = on.c(d0 / 2048.0D);
          int l = on.c(d1 / 2048.0D);
          d0 -= k * 2048;
          d1 -= l * 2048;
          float f7 = this.k.s.f() - f + 0.33F;
          
          f7 += this.h.u.ofCloudsHeight * 128.0F;
          
          float f8 = (float)(d0 * 4.8828125E-4D);
          float f9 = (float)(d1 * 4.8828125E-4D);
          vertexbuffer.a(7, bvp.i);
          for (int i1 = 65280; i1 < 256; i1 += 32) {
            for (int j1 = 65280; j1 < 256; j1 += 32)
            {
              vertexbuffer.b(i1 + 0, f7, j1 + 32).a((i1 + 0) * 4.8828125E-4F + f8, (j1 + 32) * 4.8828125E-4F + f9).a(f1, f2, f3, 0.8F).d();
              vertexbuffer.b(i1 + 32, f7, j1 + 32).a((i1 + 32) * 4.8828125E-4F + f8, (j1 + 32) * 4.8828125E-4F + f9).a(f1, f2, f3, 0.8F).d();
              vertexbuffer.b(i1 + 32, f7, j1 + 0).a((i1 + 32) * 4.8828125E-4F + f8, (j1 + 0) * 4.8828125E-4F + f9).a(f1, f2, f3, 0.8F).d();
              vertexbuffer.b(i1 + 0, f7, j1 + 0).a((i1 + 0) * 4.8828125E-4F + f8, (j1 + 0) * 4.8828125E-4F + f9).a(f1, f2, f3, 0.8F).d();
            }
          }
          tessellator.b();
          
          this.cloudRenderer.endUpdateGlList();
        }
        this.cloudRenderer.renderGlList();
        
        bni.c(1.0F, 1.0F, 1.0F, 1.0F);
        bni.l();
        bni.q();
      }
    }
  }
  
  public boolean a(double x, double y, double z, float partialTicks)
  {
    return false;
  }
  
  private void c(float partialTicks, int pass)
  {
    float partialTicksPrev = partialTicks;
    this.cloudRenderer.prepareToRender(true, this.w, partialTicksPrev);
    
    partialTicks = 0.0F;
    
    bni.r();
    rr entity = this.h.aa();
    float f = (float)(entity.N + (entity.q - entity.N) * partialTicks);
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    float f1 = 12.0F;
    float f2 = 4.0F;
    double d0 = this.w + partialTicks;
    double d1 = (entity.m + (entity.p - entity.m) * partialTicks + d0 * 0.029999999329447746D) / 12.0D;
    double d2 = (entity.o + (entity.r - entity.o) * partialTicks) / 12.0D + 0.33000001311302185D;
    float f3 = this.k.s.f() - f + 0.33F;
    
    f3 += this.h.u.ofCloudsHeight * 128.0F;
    
    int i = on.c(d1 / 2048.0D);
    int j = on.c(d2 / 2048.0D);
    d1 -= i * 2048;
    d2 -= j * 2048;
    this.i.a(e);
    bni.m();
    bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
    bbj vec3d = this.k.e(partialTicks);
    float f4 = (float)vec3d.b;
    float f5 = (float)vec3d.c;
    float f6 = (float)vec3d.d;
    if (pass != 2)
    {
      float f7 = (f4 * 30.0F + f5 * 59.0F + f6 * 11.0F) / 100.0F;
      float f8 = (f4 * 30.0F + f5 * 70.0F) / 100.0F;
      float f9 = (f4 * 30.0F + f6 * 70.0F) / 100.0F;
      f4 = f7;
      f5 = f8;
      f6 = f9;
    }
    float f26 = f4 * 0.9F;
    float f27 = f5 * 0.9F;
    float f28 = f6 * 0.9F;
    float f10 = f4 * 0.7F;
    float f11 = f5 * 0.7F;
    float f12 = f6 * 0.7F;
    float f13 = f4 * 0.8F;
    float f14 = f5 * 0.8F;
    float f15 = f6 * 0.8F;
    float f16 = 0.00390625F;
    float f17 = on.c(d1) * 0.00390625F;
    float f18 = on.c(d2) * 0.00390625F;
    float f19 = (float)(d1 - on.c(d1));
    float f20 = (float)(d2 - on.c(d2));
    int k = 8;
    int l = 4;
    float f21 = 9.765625E-4F;
    bni.b(12.0F, 1.0F, 12.0F);
    for (int i1 = 0; i1 < 2; i1++)
    {
      if (i1 == 0) {
        bni.a(false, false, false, false);
      } else {
        switch (pass)
        {
        case 0: 
          bni.a(false, true, true, true);
          break;
        case 1: 
          bni.a(true, false, false, true);
          break;
        case 2: 
          bni.a(true, true, true, true);
        }
      }
      this.cloudRenderer.renderGlList();
    }
    if (this.cloudRenderer.shouldUpdateGlList())
    {
      this.cloudRenderer.startUpdateGlList();
      for (int j1 = -3; j1 <= 4; j1++) {
        for (int k1 = -3; k1 <= 4; k1++)
        {
          vertexbuffer.a(7, bvp.l);
          float f22 = j1 * 8;
          float f23 = k1 * 8;
          float f24 = f22 - f19;
          float f25 = f23 - f20;
          if (f3 > -5.0F)
          {
            vertexbuffer.b(f24 + 0.0F, f3 + 0.0F, f25 + 8.0F).a((f22 + 0.0F) * 0.00390625F + f17, (f23 + 8.0F) * 0.00390625F + f18).a(f10, f11, f12, 0.8F).c(0.0F, -1.0F, 0.0F).d();
            vertexbuffer.b(f24 + 8.0F, f3 + 0.0F, f25 + 8.0F).a((f22 + 8.0F) * 0.00390625F + f17, (f23 + 8.0F) * 0.00390625F + f18).a(f10, f11, f12, 0.8F).c(0.0F, -1.0F, 0.0F).d();
            vertexbuffer.b(f24 + 8.0F, f3 + 0.0F, f25 + 0.0F).a((f22 + 8.0F) * 0.00390625F + f17, (f23 + 0.0F) * 0.00390625F + f18).a(f10, f11, f12, 0.8F).c(0.0F, -1.0F, 0.0F).d();
            vertexbuffer.b(f24 + 0.0F, f3 + 0.0F, f25 + 0.0F).a((f22 + 0.0F) * 0.00390625F + f17, (f23 + 0.0F) * 0.00390625F + f18).a(f10, f11, f12, 0.8F).c(0.0F, -1.0F, 0.0F).d();
          }
          if (f3 <= 5.0F)
          {
            vertexbuffer.b(f24 + 0.0F, f3 + 4.0F - 9.765625E-4F, f25 + 8.0F).a((f22 + 0.0F) * 0.00390625F + f17, (f23 + 8.0F) * 0.00390625F + f18).a(f4, f5, f6, 0.8F).c(0.0F, 1.0F, 0.0F).d();
            vertexbuffer.b(f24 + 8.0F, f3 + 4.0F - 9.765625E-4F, f25 + 8.0F).a((f22 + 8.0F) * 0.00390625F + f17, (f23 + 8.0F) * 0.00390625F + f18).a(f4, f5, f6, 0.8F).c(0.0F, 1.0F, 0.0F).d();
            vertexbuffer.b(f24 + 8.0F, f3 + 4.0F - 9.765625E-4F, f25 + 0.0F).a((f22 + 8.0F) * 0.00390625F + f17, (f23 + 0.0F) * 0.00390625F + f18).a(f4, f5, f6, 0.8F).c(0.0F, 1.0F, 0.0F).d();
            vertexbuffer.b(f24 + 0.0F, f3 + 4.0F - 9.765625E-4F, f25 + 0.0F).a((f22 + 0.0F) * 0.00390625F + f17, (f23 + 0.0F) * 0.00390625F + f18).a(f4, f5, f6, 0.8F).c(0.0F, 1.0F, 0.0F).d();
          }
          if (j1 > -1) {
            for (int l1 = 0; l1 < 8; l1++)
            {
              vertexbuffer.b(f24 + l1 + 0.0F, f3 + 0.0F, f25 + 8.0F).a((f22 + l1 + 0.5F) * 0.00390625F + f17, (f23 + 8.0F) * 0.00390625F + f18).a(f26, f27, f28, 0.8F).c(-1.0F, 0.0F, 0.0F).d();
              vertexbuffer.b(f24 + l1 + 0.0F, f3 + 4.0F, f25 + 8.0F).a((f22 + l1 + 0.5F) * 0.00390625F + f17, (f23 + 8.0F) * 0.00390625F + f18).a(f26, f27, f28, 0.8F).c(-1.0F, 0.0F, 0.0F).d();
              vertexbuffer.b(f24 + l1 + 0.0F, f3 + 4.0F, f25 + 0.0F).a((f22 + l1 + 0.5F) * 0.00390625F + f17, (f23 + 0.0F) * 0.00390625F + f18).a(f26, f27, f28, 0.8F).c(-1.0F, 0.0F, 0.0F).d();
              vertexbuffer.b(f24 + l1 + 0.0F, f3 + 0.0F, f25 + 0.0F).a((f22 + l1 + 0.5F) * 0.00390625F + f17, (f23 + 0.0F) * 0.00390625F + f18).a(f26, f27, f28, 0.8F).c(-1.0F, 0.0F, 0.0F).d();
            }
          }
          if (j1 <= 1) {
            for (int i2 = 0; i2 < 8; i2++)
            {
              vertexbuffer.b(f24 + i2 + 1.0F - 9.765625E-4F, f3 + 0.0F, f25 + 8.0F).a((f22 + i2 + 0.5F) * 0.00390625F + f17, (f23 + 8.0F) * 0.00390625F + f18).a(f26, f27, f28, 0.8F).c(1.0F, 0.0F, 0.0F).d();
              vertexbuffer.b(f24 + i2 + 1.0F - 9.765625E-4F, f3 + 4.0F, f25 + 8.0F).a((f22 + i2 + 0.5F) * 0.00390625F + f17, (f23 + 8.0F) * 0.00390625F + f18).a(f26, f27, f28, 0.8F).c(1.0F, 0.0F, 0.0F).d();
              vertexbuffer.b(f24 + i2 + 1.0F - 9.765625E-4F, f3 + 4.0F, f25 + 0.0F).a((f22 + i2 + 0.5F) * 0.00390625F + f17, (f23 + 0.0F) * 0.00390625F + f18).a(f26, f27, f28, 0.8F).c(1.0F, 0.0F, 0.0F).d();
              vertexbuffer.b(f24 + i2 + 1.0F - 9.765625E-4F, f3 + 0.0F, f25 + 0.0F).a((f22 + i2 + 0.5F) * 0.00390625F + f17, (f23 + 0.0F) * 0.00390625F + f18).a(f26, f27, f28, 0.8F).c(1.0F, 0.0F, 0.0F).d();
            }
          }
          if (k1 > -1) {
            for (int j2 = 0; j2 < 8; j2++)
            {
              vertexbuffer.b(f24 + 0.0F, f3 + 4.0F, f25 + j2 + 0.0F).a((f22 + 0.0F) * 0.00390625F + f17, (f23 + j2 + 0.5F) * 0.00390625F + f18).a(f13, f14, f15, 0.8F).c(0.0F, 0.0F, -1.0F).d();
              vertexbuffer.b(f24 + 8.0F, f3 + 4.0F, f25 + j2 + 0.0F).a((f22 + 8.0F) * 0.00390625F + f17, (f23 + j2 + 0.5F) * 0.00390625F + f18).a(f13, f14, f15, 0.8F).c(0.0F, 0.0F, -1.0F).d();
              vertexbuffer.b(f24 + 8.0F, f3 + 0.0F, f25 + j2 + 0.0F).a((f22 + 8.0F) * 0.00390625F + f17, (f23 + j2 + 0.5F) * 0.00390625F + f18).a(f13, f14, f15, 0.8F).c(0.0F, 0.0F, -1.0F).d();
              vertexbuffer.b(f24 + 0.0F, f3 + 0.0F, f25 + j2 + 0.0F).a((f22 + 0.0F) * 0.00390625F + f17, (f23 + j2 + 0.5F) * 0.00390625F + f18).a(f13, f14, f15, 0.8F).c(0.0F, 0.0F, -1.0F).d();
            }
          }
          if (k1 <= 1) {
            for (int k2 = 0; k2 < 8; k2++)
            {
              vertexbuffer.b(f24 + 0.0F, f3 + 4.0F, f25 + k2 + 1.0F - 9.765625E-4F).a((f22 + 0.0F) * 0.00390625F + f17, (f23 + k2 + 0.5F) * 0.00390625F + f18).a(f13, f14, f15, 0.8F).c(0.0F, 0.0F, 1.0F).d();
              vertexbuffer.b(f24 + 8.0F, f3 + 4.0F, f25 + k2 + 1.0F - 9.765625E-4F).a((f22 + 8.0F) * 0.00390625F + f17, (f23 + k2 + 0.5F) * 0.00390625F + f18).a(f13, f14, f15, 0.8F).c(0.0F, 0.0F, 1.0F).d();
              vertexbuffer.b(f24 + 8.0F, f3 + 0.0F, f25 + k2 + 1.0F - 9.765625E-4F).a((f22 + 8.0F) * 0.00390625F + f17, (f23 + k2 + 0.5F) * 0.00390625F + f18).a(f13, f14, f15, 0.8F).c(0.0F, 0.0F, 1.0F).d();
              vertexbuffer.b(f24 + 0.0F, f3 + 0.0F, f25 + k2 + 1.0F - 9.765625E-4F).a((f22 + 0.0F) * 0.00390625F + f17, (f23 + k2 + 0.5F) * 0.00390625F + f18).a(f13, f14, f15, 0.8F).c(0.0F, 0.0F, 1.0F).d();
            }
          }
          tessellator.b();
        }
      }
      this.cloudRenderer.endUpdateGlList();
    }
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.l();
    bni.q();
  }
  
  public void a(long finishTimeNano)
  {
    finishTimeNano = (finishTimeNano + 1.0E8D);
    
    this.ac |= this.N.a(finishTimeNano);
    if (this.chunksToUpdateForced.size() > 0)
    {
      Iterator itForced = this.chunksToUpdateForced.iterator();
      while (itForced.hasNext())
      {
        bqf rc = (bqf)itForced.next();
        if (!this.N.a(rc)) {
          break;
        }
        rc.m();
        itForced.remove();
        this.l.remove(rc);
        this.chunksToResortTransparency.remove(rc);
      }
    }
    if (this.chunksToResortTransparency.size() > 0)
    {
      Iterator itTransparency = this.chunksToResortTransparency.iterator();
      if (itTransparency.hasNext())
      {
        bqf renderChunk = (bqf)itTransparency.next();
        if (this.N.c(renderChunk)) {
          itTransparency.remove();
        }
      }
    }
    int countUpdated = 0;
    int updatesPerFrame = Config.getUpdatesPerFrame();
    int maxUpdatesPerFrame = updatesPerFrame * 2;
    if (!this.l.isEmpty())
    {
      Iterator<bqf> iterator = this.l.iterator();
      while (iterator.hasNext())
      {
        bqf renderchunk = (bqf)iterator.next();
        boolean flag;
        boolean flag;
        if (renderchunk.o()) {
          flag = this.N.b(renderchunk);
        } else {
          flag = this.N.a(renderchunk);
        }
        if (!flag) {
          break;
        }
        renderchunk.m();
        iterator.remove();
        if ((renderchunk.h().a()) && (updatesPerFrame < maxUpdatesPerFrame)) {
          updatesPerFrame++;
        }
        countUpdated++;
        if (countUpdated >= updatesPerFrame) {
          break;
        }
      }
    }
  }
  
  public void a(rr entityIn, float partialTicks)
  {
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    arv worldborder = this.k.aj();
    double d0 = this.h.u.c * 16;
    if ((entityIn.p >= worldborder.d() - d0) || (entityIn.p <= worldborder.b() + d0) || (entityIn.r >= worldborder.e() - d0) || (entityIn.r <= worldborder.c() + d0))
    {
      double d1 = 1.0D - worldborder.a(entityIn) / d0;
      d1 = Math.pow(d1, 4.0D);
      double d2 = entityIn.M + (entityIn.p - entityIn.M) * partialTicks;
      double d3 = entityIn.N + (entityIn.q - entityIn.N) * partialTicks;
      double d4 = entityIn.O + (entityIn.r - entityIn.O) * partialTicks;
      bni.m();
      bni.a(bni.r.l, bni.l.e, bni.r.e, bni.l.n);
      this.i.a(g);
      bni.a(false);
      bni.G();
      int i = worldborder.a().a();
      float f = (i >> 16 & 0xFF) / 255.0F;
      float f1 = (i >> 8 & 0xFF) / 255.0F;
      float f2 = (i & 0xFF) / 255.0F;
      bni.c(f, f1, f2, (float)d1);
      bni.a(-3.0F, -3.0F);
      bni.s();
      bni.a(516, 0.1F);
      bni.e();
      bni.r();
      float f3 = (float)(bcf.I() % 3000L) / 3000.0F;
      float f4 = 0.0F;
      float f5 = 0.0F;
      float f6 = 128.0F;
      vertexbuffer.a(7, bvp.g);
      vertexbuffer.c(-d2, -d3, -d4);
      double d5 = Math.max(on.c(d4 - d0), worldborder.c());
      double d6 = Math.min(on.f(d4 + d0), worldborder.e());
      if (d2 > worldborder.d() - d0)
      {
        float f7 = 0.0F;
        for (double d7 = d5; d7 < d6; f7 += 0.5F)
        {
          double d8 = Math.min(1.0D, d6 - d7);
          float f8 = (float)d8 * 0.5F;
          vertexbuffer.b(worldborder.d(), 256.0D, d7).a(f3 + f7, f3 + 0.0F).d();
          vertexbuffer.b(worldborder.d(), 256.0D, d7 + d8).a(f3 + f8 + f7, f3 + 0.0F).d();
          vertexbuffer.b(worldborder.d(), 0.0D, d7 + d8).a(f3 + f8 + f7, f3 + 128.0F).d();
          vertexbuffer.b(worldborder.d(), 0.0D, d7).a(f3 + f7, f3 + 128.0F).d();
          d7 += 1.0D;
        }
      }
      if (d2 < worldborder.b() + d0)
      {
        float f9 = 0.0F;
        for (double d9 = d5; d9 < d6; f9 += 0.5F)
        {
          double d12 = Math.min(1.0D, d6 - d9);
          float f12 = (float)d12 * 0.5F;
          vertexbuffer.b(worldborder.b(), 256.0D, d9).a(f3 + f9, f3 + 0.0F).d();
          vertexbuffer.b(worldborder.b(), 256.0D, d9 + d12).a(f3 + f12 + f9, f3 + 0.0F).d();
          vertexbuffer.b(worldborder.b(), 0.0D, d9 + d12).a(f3 + f12 + f9, f3 + 128.0F).d();
          vertexbuffer.b(worldborder.b(), 0.0D, d9).a(f3 + f9, f3 + 128.0F).d();
          d9 += 1.0D;
        }
      }
      d5 = Math.max(on.c(d2 - d0), worldborder.b());
      d6 = Math.min(on.f(d2 + d0), worldborder.d());
      if (d4 > worldborder.e() - d0)
      {
        float f10 = 0.0F;
        for (double d10 = d5; d10 < d6; f10 += 0.5F)
        {
          double d13 = Math.min(1.0D, d6 - d10);
          float f13 = (float)d13 * 0.5F;
          vertexbuffer.b(d10, 256.0D, worldborder.e()).a(f3 + f10, f3 + 0.0F).d();
          vertexbuffer.b(d10 + d13, 256.0D, worldborder.e()).a(f3 + f13 + f10, f3 + 0.0F).d();
          vertexbuffer.b(d10 + d13, 0.0D, worldborder.e()).a(f3 + f13 + f10, f3 + 128.0F).d();
          vertexbuffer.b(d10, 0.0D, worldborder.e()).a(f3 + f10, f3 + 128.0F).d();
          d10 += 1.0D;
        }
      }
      if (d4 < worldborder.c() + d0)
      {
        float f11 = 0.0F;
        for (double d11 = d5; d11 < d6; f11 += 0.5F)
        {
          double d14 = Math.min(1.0D, d6 - d11);
          float f14 = (float)d14 * 0.5F;
          vertexbuffer.b(d11, 256.0D, worldborder.c()).a(f3 + f11, f3 + 0.0F).d();
          vertexbuffer.b(d11 + d14, 256.0D, worldborder.c()).a(f3 + f14 + f11, f3 + 0.0F).d();
          vertexbuffer.b(d11 + d14, 0.0D, worldborder.c()).a(f3 + f14 + f11, f3 + 128.0F).d();
          vertexbuffer.b(d11, 0.0D, worldborder.c()).a(f3 + f11, f3 + 128.0F).d();
          d11 += 1.0D;
        }
      }
      tessellator.b();
      vertexbuffer.c(0.0D, 0.0D, 0.0D);
      bni.q();
      bni.d();
      bni.a(0.0F, 0.0F);
      bni.t();
      bni.e();
      bni.l();
      bni.H();
      bni.a(true);
    }
  }
  
  private void u()
  {
    bni.a(bni.r.d, bni.l.m, bni.r.e, bni.l.n);
    bni.m();
    bni.c(1.0F, 1.0F, 1.0F, 0.5F);
    bni.a(-3.0F, -3.0F);
    bni.s();
    bni.a(516, 0.1F);
    bni.e();
    bni.G();
    if (Config.isShaders()) {
      ShadersRender.beginBlockDamage();
    }
  }
  
  private void v()
  {
    bni.d();
    bni.a(0.0F, 0.0F);
    bni.t();
    bni.e();
    bni.a(true);
    bni.H();
    if (Config.isShaders()) {
      ShadersRender.endBlockDamage();
    }
  }
  
  public void a(bnu tessellatorIn, bmz worldRendererIn, rr entityIn, float partialTicks)
  {
    double d0 = entityIn.M + (entityIn.p - entityIn.M) * partialTicks;
    double d1 = entityIn.N + (entityIn.q - entityIn.N) * partialTicks;
    double d2 = entityIn.O + (entityIn.r - entityIn.O) * partialTicks;
    if (!this.x.isEmpty())
    {
      this.i.a(bvg.g);
      u();
      worldRendererIn.a(7, bvp.a);
      worldRendererIn.c(-d0, -d1, -d2);
      worldRendererIn.c();
      Iterator<li> iterator = this.x.values().iterator();
      while (iterator.hasNext())
      {
        li destroyblockprogress = (li)iterator.next();
        cj blockpos = destroyblockprogress.b();
        double d3 = blockpos.p() - d0;
        double d4 = blockpos.q() - d1;
        double d5 = blockpos.r() - d2;
        ajt block = this.k.o(blockpos).t();
        boolean renderBreaking;
        boolean renderBreaking;
        if (Reflector.ForgeTileEntity_canRenderBreaking.exists())
        {
          boolean tileEntityRenderBreaking = ((block instanceof ake)) || ((block instanceof alf)) || ((block instanceof aoj)) || ((block instanceof aok));
          if (!tileEntityRenderBreaking)
          {
            apv te = this.k.r(blockpos);
            if (te != null) {
              tileEntityRenderBreaking = Reflector.callBoolean(te, Reflector.ForgeTileEntity_canRenderBreaking, new Object[0]);
            }
          }
          renderBreaking = !tileEntityRenderBreaking;
        }
        else
        {
          renderBreaking = (!(block instanceof ake)) && (!(block instanceof alf)) && (!(block instanceof aoj)) && (!(block instanceof aok));
        }
        if (renderBreaking) {
          if (d3 * d3 + d4 * d4 + d5 * d5 > 1024.0D)
          {
            iterator.remove();
          }
          else
          {
            arc iblockstate = this.k.o(blockpos);
            if (iblockstate.a() != axe.a)
            {
              int i = destroyblockprogress.c();
              bvh textureatlassprite = this.z[i];
              boc blockrendererdispatcher = this.h.ab();
              blockrendererdispatcher.a(iblockstate, blockpos, textureatlassprite, this.k);
            }
          }
        }
      }
      tessellatorIn.b();
      worldRendererIn.c(0.0D, 0.0D, 0.0D);
      v();
    }
  }
  
  public void a(zj player, bbi movingObjectPositionIn, int execute, float partialTicks)
  {
    if ((execute == 0) && (movingObjectPositionIn.a == bbi.a.b))
    {
      bni.m();
      bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
      bni.c(0.0F, 0.0F, 0.0F, 0.4F);
      bni.d(2.0F);
      bni.z();
      if (Config.isShaders()) {
        Shaders.disableTexture2D();
      }
      bni.a(false);
      cj blockpos = movingObjectPositionIn.a();
      arc iblockstate = this.k.o(blockpos);
      if ((iblockstate.a() != axe.a) && (this.k.aj().a(blockpos)))
      {
        double d0 = player.M + (player.p - player.M) * partialTicks;
        double d1 = player.N + (player.q - player.N) * partialTicks;
        double d2 = player.O + (player.r - player.O) * partialTicks;
        a(iblockstate.c(this.k, blockpos).g(0.0020000000949949026D).c(-d0, -d1, -d2));
      }
      bni.a(true);
      bni.y();
      if (Config.isShaders()) {
        Shaders.enableTexture2D();
      }
      bni.l();
    }
  }
  
  public static void a(bbh boundingBox)
  {
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    vertexbuffer.a(3, bvp.e);
    vertexbuffer.b(boundingBox.a, boundingBox.b, boundingBox.c).d();
    vertexbuffer.b(boundingBox.d, boundingBox.b, boundingBox.c).d();
    vertexbuffer.b(boundingBox.d, boundingBox.b, boundingBox.f).d();
    vertexbuffer.b(boundingBox.a, boundingBox.b, boundingBox.f).d();
    vertexbuffer.b(boundingBox.a, boundingBox.b, boundingBox.c).d();
    tessellator.b();
    vertexbuffer.a(3, bvp.e);
    vertexbuffer.b(boundingBox.a, boundingBox.e, boundingBox.c).d();
    vertexbuffer.b(boundingBox.d, boundingBox.e, boundingBox.c).d();
    vertexbuffer.b(boundingBox.d, boundingBox.e, boundingBox.f).d();
    vertexbuffer.b(boundingBox.a, boundingBox.e, boundingBox.f).d();
    vertexbuffer.b(boundingBox.a, boundingBox.e, boundingBox.c).d();
    tessellator.b();
    vertexbuffer.a(1, bvp.e);
    vertexbuffer.b(boundingBox.a, boundingBox.b, boundingBox.c).d();
    vertexbuffer.b(boundingBox.a, boundingBox.e, boundingBox.c).d();
    vertexbuffer.b(boundingBox.d, boundingBox.b, boundingBox.c).d();
    vertexbuffer.b(boundingBox.d, boundingBox.e, boundingBox.c).d();
    vertexbuffer.b(boundingBox.d, boundingBox.b, boundingBox.f).d();
    vertexbuffer.b(boundingBox.d, boundingBox.e, boundingBox.f).d();
    vertexbuffer.b(boundingBox.a, boundingBox.b, boundingBox.f).d();
    vertexbuffer.b(boundingBox.a, boundingBox.e, boundingBox.f).d();
    tessellator.b();
  }
  
  public static void a(bbh boundingBox, int red, int green, int blue, int alpha)
  {
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    vertexbuffer.a(3, bvp.f);
    vertexbuffer.b(boundingBox.a, boundingBox.b, boundingBox.c).b(red, green, blue, alpha).d();
    vertexbuffer.b(boundingBox.d, boundingBox.b, boundingBox.c).b(red, green, blue, alpha).d();
    vertexbuffer.b(boundingBox.d, boundingBox.b, boundingBox.f).b(red, green, blue, alpha).d();
    vertexbuffer.b(boundingBox.a, boundingBox.b, boundingBox.f).b(red, green, blue, alpha).d();
    vertexbuffer.b(boundingBox.a, boundingBox.b, boundingBox.c).b(red, green, blue, alpha).d();
    tessellator.b();
    vertexbuffer.a(3, bvp.f);
    vertexbuffer.b(boundingBox.a, boundingBox.e, boundingBox.c).b(red, green, blue, alpha).d();
    vertexbuffer.b(boundingBox.d, boundingBox.e, boundingBox.c).b(red, green, blue, alpha).d();
    vertexbuffer.b(boundingBox.d, boundingBox.e, boundingBox.f).b(red, green, blue, alpha).d();
    vertexbuffer.b(boundingBox.a, boundingBox.e, boundingBox.f).b(red, green, blue, alpha).d();
    vertexbuffer.b(boundingBox.a, boundingBox.e, boundingBox.c).b(red, green, blue, alpha).d();
    tessellator.b();
    vertexbuffer.a(1, bvp.f);
    vertexbuffer.b(boundingBox.a, boundingBox.b, boundingBox.c).b(red, green, blue, alpha).d();
    vertexbuffer.b(boundingBox.a, boundingBox.e, boundingBox.c).b(red, green, blue, alpha).d();
    vertexbuffer.b(boundingBox.d, boundingBox.b, boundingBox.c).b(red, green, blue, alpha).d();
    vertexbuffer.b(boundingBox.d, boundingBox.e, boundingBox.c).b(red, green, blue, alpha).d();
    vertexbuffer.b(boundingBox.d, boundingBox.b, boundingBox.f).b(red, green, blue, alpha).d();
    vertexbuffer.b(boundingBox.d, boundingBox.e, boundingBox.f).b(red, green, blue, alpha).d();
    vertexbuffer.b(boundingBox.a, boundingBox.b, boundingBox.f).b(red, green, blue, alpha).d();
    vertexbuffer.b(boundingBox.a, boundingBox.e, boundingBox.f).b(red, green, blue, alpha).d();
    tessellator.b();
  }
  
  private void a(int p_184385_1_, int p_184385_2_, int p_184385_3_, int p_184385_4_, int p_184385_5_, int p_184385_6_, boolean p_184385_7_)
  {
    this.o.a(p_184385_1_, p_184385_2_, p_184385_3_, p_184385_4_, p_184385_5_, p_184385_6_, p_184385_7_);
  }
  
  public void a(aht worldIn, cj pos, arc oldState, arc newState, int flags)
  {
    int i = pos.p();
    int j = pos.q();
    int k = pos.r();
    a(i - 1, j - 1, k - 1, i + 1, j + 1, k + 1, (flags & 0x8) != 0);
  }
  
  public void a(cj pos)
  {
    this.ae.add(pos.h());
  }
  
  public void a(int x1, int y1, int z1, int x2, int y2, int z2)
  {
    a(x1 - 1, y1 - 1, z1 - 1, x2 + 1, y2 + 1, z2 + 1, false);
  }
  
  public void a(nf soundIn, cj pos)
  {
    byi isound = (byi)this.y.get(pos);
    if (isound != null)
    {
      this.h.U().b(isound);
      this.y.remove(pos);
    }
    if (soundIn != null)
    {
      aef itemrecord = aef.a(soundIn);
      if (itemrecord != null) {
        this.h.r.a(itemrecord.g());
      }
      bye positionedsoundrecord = bye.a(soundIn, pos.p(), pos.q(), pos.r());
      this.y.put(pos, positionedsoundrecord);
      this.h.U().a(positionedsoundrecord);
    }
  }
  
  public void a(zj player, nf soundIn, nh category, double x, double y, double z, float volume, float pitch) {}
  
  public void a(int particleID, boolean ignoreRange, final double xCoord, double yCoord, final double zCoord, double xOffset, double yOffset, double zOffset, int... parameters)
  {
    try
    {
      b(particleID, ignoreRange, xCoord, yCoord, zCoord, xOffset, yOffset, zOffset, parameters);
    }
    catch (Throwable throwable)
    {
      b crashreport = b.a(throwable, "Exception while adding particle");
      c crashreportcategory = crashreport.a("Particle being added");
      crashreportcategory.a("ID", Integer.valueOf(particleID));
      if (parameters != null) {
        crashreportcategory.a("Parameters", parameters);
      }
      crashreportcategory.a("Position", new Callable()
      {
        public String a()
          throws Exception
        {
          return c.a(xCoord, zCoord, this.val$zCoord);
        }
      });
      throw new e(crashreport);
    }
  }
  
  private void a(cy particleIn, double xCoord, double yCoord, double zCoord, double xOffset, double yOffset, double zOffset, int... parameters)
  {
    a(particleIn.c(), particleIn.e(), xCoord, yCoord, zCoord, xOffset, yOffset, zOffset, parameters);
  }
  
  private blx b(int particleID, boolean ignoreRange, double xCoord, double yCoord, double zCoord, double xOffset, double yOffset, double zOffset, int... parameters)
  {
    rr entity = this.h.aa();
    if ((this.h != null) && (entity != null) && (this.h.j != null))
    {
      int i = this.h.u.aA;
      if ((i == 1) && (this.k.r.nextInt(3) == 0)) {
        i = 2;
      }
      double d0 = entity.p - xCoord;
      double d1 = entity.q - yCoord;
      double d2 = entity.r - zCoord;
      
      int id = particleID;
      if ((id == cy.c.c()) && (!Config.isAnimatedExplosion())) {
        return null;
      }
      if ((id == cy.b.c()) && (!Config.isAnimatedExplosion())) {
        return null;
      }
      if ((id == cy.a.c()) && (!Config.isAnimatedExplosion())) {
        return null;
      }
      if ((id == cy.h.c()) && (!Config.isWaterParticles())) {
        return null;
      }
      if ((id == cy.i.c()) && (!Config.isVoidParticles())) {
        return null;
      }
      if ((id == cy.l.c()) && (!Config.isAnimatedSmoke())) {
        return null;
      }
      if ((id == cy.m.c()) && (!Config.isAnimatedSmoke())) {
        return null;
      }
      if ((id == cy.p.c()) && (!Config.isPotionParticles())) {
        return null;
      }
      if ((id == cy.q.c()) && (!Config.isPotionParticles())) {
        return null;
      }
      if ((id == cy.n.c()) && (!Config.isPotionParticles())) {
        return null;
      }
      if ((id == cy.o.c()) && (!Config.isPotionParticles())) {
        return null;
      }
      if ((id == cy.r.c()) && (!Config.isPotionParticles())) {
        return null;
      }
      if ((id == cy.y.c()) && (!Config.isAnimatedPortal())) {
        return null;
      }
      if ((id == cy.A.c()) && (!Config.isAnimatedFlame())) {
        return null;
      }
      if ((id == cy.E.c()) && (!Config.isAnimatedRedstone())) {
        return null;
      }
      if ((id == cy.s.c()) && (!Config.isDrippingWaterLava())) {
        return null;
      }
      if ((id == cy.t.c()) && (!Config.isDrippingWaterLava())) {
        return null;
      }
      if ((id == cy.d.c()) && (!Config.isFireworkParticles())) {
        return null;
      }
      if (!ignoreRange)
      {
        double maxDistSq = 1024.0D;
        if (id == cy.j.c()) {
          maxDistSq = 38416.0D;
        }
        if (d0 * d0 + d1 * d1 + d2 * d2 > maxDistSq) {
          return null;
        }
        if (i > 1) {
          return null;
        }
      }
      blx entityFx = this.h.j.a(particleID, xCoord, yCoord, zCoord, xOffset, yOffset, zOffset, parameters);
      if (id == cy.e.c()) {
        CustomColors.updateWaterFX(entityFx, this.k, xCoord, yCoord, zCoord);
      }
      if (id == cy.f.c()) {
        CustomColors.updateWaterFX(entityFx, this.k, xCoord, yCoord, zCoord);
      }
      if (id == cy.N.c()) {
        CustomColors.updateWaterFX(entityFx, this.k, xCoord, yCoord, zCoord);
      }
      if (id == cy.w.c()) {
        CustomColors.updateMyceliumFX(entityFx);
      }
      if (id == cy.y.c()) {
        CustomColors.updatePortalFX(entityFx);
      }
      if (id == cy.E.c()) {
        CustomColors.updateReddustFX(entityFx, this.k, xCoord, yCoord, zCoord);
      }
      return entityFx;
    }
    return null;
  }
  
  public void a(rr entityIn)
  {
    RandomMobs.entityLoaded(entityIn, this.k);
  }
  
  public void b(rr entityIn) {}
  
  public void l() {}
  
  public void a(int soundID, cj pos, int data)
  {
    switch (soundID)
    {
    case 1023: 
    case 1028: 
      rr entity = this.h.aa();
      if (entity != null)
      {
        double d0 = pos.p() - entity.p;
        double d1 = pos.q() - entity.q;
        double d2 = pos.r() - entity.r;
        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
        double d4 = entity.p;
        double d5 = entity.q;
        double d6 = entity.r;
        if (d3 > 0.0D)
        {
          d4 += d0 / d3 * 2.0D;
          d5 += d1 / d3 * 2.0D;
          d6 += d2 / d3 * 2.0D;
        }
        if (soundID == 1023) {
          this.k.a(d4, d5, d6, ng.gJ, nh.f, 1.0F, 1.0F, false);
        } else {
          this.k.a(d4, d5, d6, ng.aN, nh.f, 5.0F, 1.0F, false);
        }
      }
      break;
    }
  }
  
  public void a(zj player, int sfxType, cj blockPosIn, int data)
  {
    Random random = this.k.r;
    switch (sfxType)
    {
    case 1000: 
      this.k.a(blockPosIn, ng.au, nh.e, 1.0F, 1.0F, false);
      break;
    case 1001: 
      this.k.a(blockPosIn, ng.av, nh.e, 1.0F, 1.2F, false);
      break;
    case 1002: 
      this.k.a(blockPosIn, ng.aw, nh.e, 1.0F, 1.2F, false);
      break;
    case 1003: 
      this.k.a(blockPosIn, ng.aT, nh.g, 1.0F, 1.2F, false);
      break;
    case 1004: 
      this.k.a(blockPosIn, ng.br, nh.g, 1.0F, 1.2F, false);
      break;
    case 1005: 
      this.k.a(blockPosIn, ng.cL, nh.e, 1.0F, this.k.r.nextFloat() * 0.1F + 0.9F, false);
      break;
    case 1006: 
      this.k.a(blockPosIn, ng.gU, nh.e, 1.0F, this.k.r.nextFloat() * 0.1F + 0.9F, false);
      break;
    case 1007: 
      this.k.a(blockPosIn, ng.gW, nh.e, 1.0F, this.k.r.nextFloat() * 0.1F + 0.9F, false);
      break;
    case 1008: 
      this.k.a(blockPosIn, ng.bk, nh.e, 1.0F, this.k.r.nextFloat() * 0.1F + 0.9F, false);
      break;
    case 1009: 
      this.k.a(blockPosIn, ng.bv, nh.e, 0.5F, 2.6F + (random.nextFloat() - random.nextFloat()) * 0.8F, false);
      break;
    case 1010: 
      if ((ado.c(data) instanceof aef)) {
        this.k.a(blockPosIn, ((aef)ado.c(data)).h());
      } else {
        this.k.a(blockPosIn, (nf)null);
      }
      break;
    case 1011: 
      this.k.a(blockPosIn, ng.cK, nh.e, 1.0F, this.k.r.nextFloat() * 0.1F + 0.9F, false);
      break;
    case 1012: 
      this.k.a(blockPosIn, ng.gT, nh.e, 1.0F, this.k.r.nextFloat() * 0.1F + 0.9F, false);
      break;
    case 1013: 
      this.k.a(blockPosIn, ng.gV, nh.e, 1.0F, this.k.r.nextFloat() * 0.1F + 0.9F, false);
      break;
    case 1014: 
      this.k.a(blockPosIn, ng.bj, nh.e, 1.0F, this.k.r.nextFloat() * 0.1F + 0.9F, false);
      break;
    case 1015: 
      this.k.a(blockPosIn, ng.bO, nh.f, 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
      break;
    case 1016: 
      this.k.a(blockPosIn, ng.bN, nh.f, 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
      break;
    case 1017: 
      this.k.a(blockPosIn, ng.aS, nh.f, 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
      break;
    case 1018: 
      this.k.a(blockPosIn, ng.F, nh.f, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
      break;
    case 1019: 
      this.k.a(blockPosIn, ng.hh, nh.f, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
      break;
    case 1020: 
      this.k.a(blockPosIn, ng.hi, nh.f, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
      break;
    case 1021: 
      this.k.a(blockPosIn, ng.hj, nh.f, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
      break;
    case 1022: 
      this.k.a(blockPosIn, ng.gF, nh.f, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
      break;
    case 1024: 
      this.k.a(blockPosIn, ng.gI, nh.f, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
      break;
    case 1025: 
      this.k.a(blockPosIn, ng.A, nh.g, 0.05F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
      break;
    case 1026: 
      this.k.a(blockPosIn, ng.hp, nh.f, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
      break;
    case 1027: 
      this.k.a(blockPosIn, ng.hw, nh.g, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
      break;
    case 1029: 
      this.k.a(blockPosIn, ng.c, nh.e, 1.0F, this.k.r.nextFloat() * 0.1F + 0.9F, false);
      break;
    case 1030: 
      this.k.a(blockPosIn, ng.i, nh.e, 1.0F, this.k.r.nextFloat() * 0.1F + 0.9F, false);
      break;
    case 1031: 
      this.k.a(blockPosIn, ng.f, nh.e, 0.3F, this.k.r.nextFloat() * 0.1F + 0.9F, false);
      break;
    case 1032: 
      this.h.U().a(bye.a(ng.em, random.nextFloat() * 0.4F + 0.8F));
      break;
    case 1033: 
      this.k.a(blockPosIn, ng.ae, nh.e, 1.0F, 1.0F, false);
      break;
    case 1034: 
      this.k.a(blockPosIn, ng.ad, nh.e, 1.0F, 1.0F, false);
      break;
    case 1035: 
      this.k.a(blockPosIn, ng.K, nh.e, 1.0F, 1.0F, false);
      break;
    case 1036: 
      this.k.a(blockPosIn, ng.cM, nh.e, 1.0F, this.k.r.nextFloat() * 0.1F + 0.9F, false);
      break;
    case 1037: 
      this.k.a(blockPosIn, ng.cN, nh.e, 1.0F, this.k.r.nextFloat() * 0.1F + 0.9F, false);
      break;
    case 2000: 
      int i1 = data % 3 - 1;
      int i = data / 3 % 3 - 1;
      double d8 = blockPosIn.p() + i1 * 0.6D + 0.5D;
      double d10 = blockPosIn.q() + 0.5D;
      double d12 = blockPosIn.r() + i * 0.6D + 0.5D;
      for (int k1 = 0; k1 < 10; k1++)
      {
        double d13 = random.nextDouble() * 0.2D + 0.01D;
        double d14 = d8 + i1 * 0.01D + (random.nextDouble() - 0.5D) * i * 0.5D;
        double d17 = d10 + (random.nextDouble() - 0.5D) * 0.5D;
        double d20 = d12 + i * 0.01D + (random.nextDouble() - 0.5D) * i1 * 0.5D;
        double d23 = i1 * d13 + random.nextGaussian() * 0.01D;
        double d25 = -0.03D + random.nextGaussian() * 0.01D;
        double d27 = i * d13 + random.nextGaussian() * 0.01D;
        a(cy.l, d14, d17, d20, d23, d25, d27, new int[0]);
      }
      return;
    case 2001: 
      ajt block = ajt.b(data & 0xFFF);
      if (block.u().a() != axe.a)
      {
        aop soundtype = block.w();
        this.k.a(blockPosIn, soundtype.c(), nh.e, (soundtype.a() + 1.0F) / 2.0F, soundtype.b() * 0.8F, false);
      }
      this.h.j.a(blockPosIn, block.a(data >> 12 & 0xFF));
      break;
    case 2002: 
      double d6 = blockPosIn.p();
      double d7 = blockPosIn.q();
      double d9 = blockPosIn.r();
      for (int j1 = 0; j1 < 8; j1++) {
        a(cy.K, d6, d7, d9, random.nextGaussian() * 0.15D, random.nextDouble() * 0.2D, random.nextGaussian() * 0.15D, new int[] { ado.a(ads.bH) });
      }
      afe potiontype = afe.a(data);
      int k = afg.a(potiontype);
      float f = (k >> 16 & 0xFF) / 255.0F;
      float f1 = (k >> 8 & 0xFF) / 255.0F;
      float f2 = (k >> 0 & 0xFF) / 255.0F;
      cy enumparticletypes = potiontype.c() ? cy.o : cy.n;
      for (int i2 = 0; i2 < 100; i2++)
      {
        double d16 = random.nextDouble() * 4.0D;
        double d19 = random.nextDouble() * 3.141592653589793D * 2.0D;
        double d22 = Math.cos(d19) * d16;
        double d24 = 0.01D + random.nextDouble() * 0.5D;
        double d26 = Math.sin(d19) * d16;
        blx entityfx1 = b(enumparticletypes.c(), enumparticletypes.e(), d6 + d22 * 0.1D, d7 + 0.3D, d9 + d26 * 0.1D, d22, d24, d26, new int[0]);
        if (entityfx1 != null)
        {
          float f5 = 0.75F + random.nextFloat() * 0.25F;
          entityfx1.a(f * f5, f1 * f5, f2 * f5);
          entityfx1.c((float)d16);
        }
      }
      this.k.a(blockPosIn, ng.fU, nh.g, 1.0F, this.k.r.nextFloat() * 0.1F + 0.9F, false);
      break;
    case 2003: 
      double d0 = blockPosIn.p() + 0.5D;
      double d1 = blockPosIn.q();
      double d2 = blockPosIn.r() + 0.5D;
      for (int j = 0; j < 8; j++) {
        a(cy.K, d0, d1, d2, random.nextGaussian() * 0.15D, random.nextDouble() * 0.2D, random.nextGaussian() * 0.15D, new int[] { ado.a(ads.bR) });
      }
      for (double d11 = 0.0D; d11 < 6.283185307179586D; d11 += 0.15707963267948966D)
      {
        a(cy.y, d0 + Math.cos(d11) * 5.0D, d1 - 0.4D, d2 + Math.sin(d11) * 5.0D, Math.cos(d11) * -5.0D, 0.0D, Math.sin(d11) * -5.0D, new int[0]);
        a(cy.y, d0 + Math.cos(d11) * 5.0D, d1 - 0.4D, d2 + Math.sin(d11) * 5.0D, Math.cos(d11) * -7.0D, 0.0D, Math.sin(d11) * -7.0D, new int[0]);
      }
      return;
    case 2004: 
      for (int l1 = 0; l1 < 20; l1++)
      {
        double d15 = blockPosIn.p() + 0.5D + (this.k.r.nextFloat() - 0.5D) * 2.0D;
        double d18 = blockPosIn.q() + 0.5D + (this.k.r.nextFloat() - 0.5D) * 2.0D;
        double d21 = blockPosIn.r() + 0.5D + (this.k.r.nextFloat() - 0.5D) * 2.0D;
        this.k.a(cy.l, d15, d18, d21, 0.0D, 0.0D, 0.0D, new int[0]);
        this.k.a(cy.A, d15, d18, d21, 0.0D, 0.0D, 0.0D, new int[0]);
      }
      return;
    case 2005: 
      acu.a(this.k, blockPosIn, data);
      break;
    case 2006: 
      for (int l = 0; l < 200; l++)
      {
        float f3 = random.nextFloat() * 4.0F;
        float f4 = random.nextFloat() * 6.2831855F;
        double d3 = on.b(f4) * f3;
        double d4 = 0.01D + random.nextDouble() * 0.5D;
        double d5 = on.a(f4) * f3;
        blx entityfx = b(cy.Q.c(), false, blockPosIn.p() + d3 * 0.1D, blockPosIn.q() + 0.3D, blockPosIn.r() + d5 * 0.1D, d3, d4, d5, new int[0]);
        if (entityfx != null) {
          entityfx.c(f3);
        }
      }
      this.k.a(blockPosIn, ng.aO, nh.f, 1.0F, this.k.r.nextFloat() * 0.1F + 0.9F, false);
      break;
    case 3000: 
      this.k.a(cy.c, true, blockPosIn.p() + 0.5D, blockPosIn.q() + 0.5D, blockPosIn.r() + 0.5D, 0.0D, 0.0D, 0.0D, new int[0]);
      this.k.a(blockPosIn, ng.bf, nh.e, 10.0F, (1.0F + (this.k.r.nextFloat() - this.k.r.nextFloat()) * 0.2F) * 0.7F, false);
      break;
    case 3001: 
      this.k.a(blockPosIn, ng.aQ, nh.f, 64.0F, 0.8F + this.k.r.nextFloat() * 0.3F, false);
    }
  }
  
  public void b(int breakerId, cj pos, int progress)
  {
    if ((progress >= 0) && (progress < 10))
    {
      li destroyblockprogress = (li)this.x.get(Integer.valueOf(breakerId));
      if ((destroyblockprogress == null) || (destroyblockprogress.b().p() != pos.p()) || (destroyblockprogress.b().q() != pos.q()) || (destroyblockprogress.b().r() != pos.r()))
      {
        destroyblockprogress = new li(breakerId, pos);
        this.x.put(Integer.valueOf(breakerId), destroyblockprogress);
      }
      destroyblockprogress.a(progress);
      destroyblockprogress.b(this.w);
    }
    else
    {
      this.x.remove(Integer.valueOf(breakerId));
    }
  }
  
  public boolean n()
  {
    return (this.l.isEmpty()) && (this.N.f());
  }
  
  public void o()
  {
    this.ac = true;
  }
  
  public void resetClouds()
  {
    this.cloudRenderer.reset();
  }
  
  public int getCountRenderers()
  {
    return this.o.f.length;
  }
  
  public int getCountActiveRenderers()
  {
    return this.m.size();
  }
  
  public int getCountEntitiesRendered()
  {
    return this.S;
  }
  
  public int getCountTileEntitiesRendered()
  {
    return this.countTileEntitiesRendered;
  }
  
  public void a(Collection<apv> tileEntitiesToRemove, Collection<apv> tileEntitiesToAdd)
  {
    synchronized (this.n)
    {
      this.n.removeAll(tileEntitiesToRemove);
      this.n.addAll(tileEntitiesToAdd);
    }
  }
  
  class a
  {
    final bqf a;
    final cq b;
    final Set<cq> c;
    final int d;
    
    private a(bqf renderChunkIn, cq facingIn, int counterIn)
    {
      this.c = EnumSet.noneOf(cq.class);
      this.a = renderChunkIn;
      this.b = facingIn;
      this.d = counterIn;
    }
  }
}
