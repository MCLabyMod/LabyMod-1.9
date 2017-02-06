package shadersmod.client;

import Reflector;
import ReflectorMethod;
import acc;
import ado;
import adq;
import ahm;
import ajt;
import bca;
import bcd;
import bcf;
import bch;
import bmt;
import bng;
import bni;
import bnk;
import bno;
import bqm;
import bqo;
import bqp;
import bvg;
import bvi;
import bvj;
import bzg;
import java.nio.IntBuffer;
import oo;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import rr;

public class ShadersRender
{
  public static void setFrustrumPosition(bqm frustum, double x, double y, double z)
  {
    frustum.a(x, y, z);
  }
  
  public static void setupTerrain(bno renderGlobal, rr viewEntity, double partialTicks, bqm camera, int frameCount, boolean playerSpectator)
  {
    renderGlobal.a(viewEntity, partialTicks, camera, frameCount, playerSpectator);
  }
  
  public static void beginTerrainSolid()
  {
    if (Shaders.isRenderingWorld)
    {
      Shaders.fogEnabled = true;
      Shaders.useProgram(7);
    }
  }
  
  public static void beginTerrainCutoutMipped()
  {
    if (Shaders.isRenderingWorld) {
      Shaders.useProgram(7);
    }
  }
  
  public static void beginTerrainCutout()
  {
    if (Shaders.isRenderingWorld) {
      Shaders.useProgram(7);
    }
  }
  
  public static void endTerrain()
  {
    if (Shaders.isRenderingWorld) {
      Shaders.useProgram(3);
    }
  }
  
  public static void beginTranslucent()
  {
    if (Shaders.isRenderingWorld)
    {
      if (Shaders.usedDepthBuffers >= 2)
      {
        bni.g(33995);
        Shaders.checkGLError("pre copy depth");
        GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, Shaders.renderWidth, Shaders.renderHeight);
        Shaders.checkGLError("copy depth");
        bni.g(33984);
      }
      Shaders.useProgram(12);
    }
  }
  
  public static void endTranslucent()
  {
    if (Shaders.isRenderingWorld) {
      Shaders.useProgram(3);
    }
  }
  
  public static void renderHand0(bng er, float par1, int par2)
  {
    if (!Shaders.isShadowPass)
    {
      ado item = Shaders.itemToRender != null ? Shaders.itemToRender.b() : null;
      ajt block = (item instanceof acc) ? ((acc)item).d() : null;
      if ((!(item instanceof acc)) || (!(block instanceof ajt)) || (block.f() == ahm.a))
      {
        Shaders.readCenterDepth();
        Shaders.beginHand();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        er.b(par1, par2);
        Shaders.endHand();
        Shaders.isHandRendered = true;
      }
    }
  }
  
  public static void renderHand1(bng er, float par1, int par2)
  {
    if (!Shaders.isShadowPass) {
      if (!Shaders.isHandRendered)
      {
        Shaders.readCenterDepth();
        bni.m();
        Shaders.beginHand();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        er.b(par1, par2);
        Shaders.endHand();
        Shaders.isHandRendered = true;
      }
    }
  }
  
  public static void renderItemFP(bnk itemRenderer, float par1)
  {
    bni.a(true);
    
    bni.c(515);
    
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    itemRenderer.a(par1);
  }
  
  public static void renderFPOverlay(bng er, float par1, int par2)
  {
    if (!Shaders.isShadowPass)
    {
      Shaders.beginFPOverlay();
      er.b(par1, par2);
      Shaders.endFPOverlay();
    }
  }
  
  public static void beginBlockDamage()
  {
    if (Shaders.isRenderingWorld)
    {
      Shaders.useProgram(11);
      if (Shaders.programsID[11] == Shaders.programsID[7])
      {
        Shaders.setDrawBuffers(Shaders.drawBuffersColorAtt0);
        bni.a(false);
      }
    }
  }
  
  public static void endBlockDamage()
  {
    if (Shaders.isRenderingWorld)
    {
      bni.a(true);
      Shaders.useProgram(3);
    }
  }
  
  public static void renderShadowMap(bng entityRenderer, int pass, float partialTicks, long finishTimeNano)
  {
    if ((Shaders.usedShadowDepthBuffers > 0) && (--Shaders.shadowPassCounter <= 0))
    {
      bcf mc = bcf.z();
      mc.B.c("shadow pass");
      
      bno renderGlobal = mc.g;
      Shaders.isShadowPass = true;
      Shaders.shadowPassCounter = Shaders.shadowPassInterval;
      Shaders.preShadowPassThirdPersonView = mc.u.ap;
      mc.u.ap = 1;
      
      Shaders.checkGLError("pre shadow");
      GL11.glMatrixMode(5889);
      GL11.glPushMatrix();
      GL11.glMatrixMode(5888);
      GL11.glPushMatrix();
      
      mc.B.c("shadow clear");
      EXTFramebufferObject.glBindFramebufferEXT(36160, Shaders.sfb);
      Shaders.checkGLError("shadow bind sfb");
      Shaders.useProgram(30);
      
      mc.B.c("shadow camera");
      entityRenderer.a(partialTicks, 2);
      Shaders.setCameraShadow(partialTicks);
      bca.a(mc.h, mc.u.ap == 2);
      Shaders.checkGLError("shadow camera");
      
      GL20.glDrawBuffers(Shaders.sfbDrawBuffers);
      Shaders.checkGLError("shadow drawbuffers");
      GL11.glReadBuffer(0);
      Shaders.checkGLError("shadow readbuffer");
      
      EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, Shaders.sfbDepthTextures.get(0), 0);
      if (Shaders.usedShadowColorBuffers != 0) {
        EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064, 3553, Shaders.sfbColorTextures.get(0), 0);
      }
      Shaders.checkFramebufferStatus("shadow fb");
      GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glClear(Shaders.usedShadowColorBuffers != 0 ? 16640 : 256);
      Shaders.checkGLError("shadow clear");
      
      mc.B.c("shadow frustum");
      bqp clippingHelper = ClippingHelperShadow.getInstance();
      mc.B.c("shadow culling");
      bqo frustum = new bqo(clippingHelper);
      
      rr viewEntity = mc.aa();
      double viewPosX = viewEntity.M + (viewEntity.p - viewEntity.M) * partialTicks;
      double viewPosY = viewEntity.N + (viewEntity.q - viewEntity.N) * partialTicks;
      double viewPosZ = viewEntity.O + (viewEntity.r - viewEntity.O) * partialTicks;
      frustum.a(viewPosX, viewPosY, viewPosZ);
      
      bni.j(7425);
      bni.k();
      bni.c(515);
      bni.a(true);
      bni.a(true, true, true, true);
      
      bni.r();
      
      mc.B.c("shadow prepareterrain");
      mc.N().a(bvg.g);
      
      mc.B.c("shadow setupterrain");
      int frameCount = 0;
      frameCount = entityRenderer.af;
      entityRenderer.af = (frameCount + 1);
      renderGlobal.a(viewEntity, partialTicks, frustum, frameCount, mc.h.y());
      mc.B.c("shadow updatechunks");
      
      mc.B.c("shadow terrain");
      
      bni.n(5888);
      bni.G();
      bni.d();
      renderGlobal.a(ahm.a, partialTicks, 2, viewEntity);
      Shaders.checkGLError("shadow terrain solid");
      bni.e();
      renderGlobal.a(ahm.b, partialTicks, 2, viewEntity);
      Shaders.checkGLError("shadow terrain cutoutmipped");
      mc.N().b(bvg.g).b(false, false);
      renderGlobal.a(ahm.c, partialTicks, 2, viewEntity);
      Shaders.checkGLError("shadow terrain cutout");
      mc.N().b(bvg.g).a();
      bni.j(7424);
      bni.a(516, 0.1F);
      
      bni.n(5888);
      bni.H();
      bni.G();
      
      mc.B.c("shadow entities");
      if (Reflector.ForgeHooksClient_setRenderPass.exists()) {
        Reflector.callVoid(Reflector.ForgeHooksClient_setRenderPass, new Object[] { Integer.valueOf(0) });
      }
      renderGlobal.a(viewEntity, frustum, partialTicks);
      Shaders.checkGLError("shadow entities");
      
      bni.n(5888);
      bni.H();
      
      bni.a(true);
      
      bni.l();
      bni.q();
      bni.a(770, 771, 1, 0);
      bni.a(516, 0.1F);
      if (Shaders.usedShadowDepthBuffers >= 2)
      {
        bni.g(33989);
        Shaders.checkGLError("pre copy shadow depth");
        GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, Shaders.shadowMapWidth, Shaders.shadowMapHeight);
        Shaders.checkGLError("copy shadow depth");
        bni.g(33984);
      }
      bni.l();
      bni.a(true);
      mc.N().a(bvg.g);
      bni.j(7425);
      Shaders.checkGLError("shadow pre-translucent");
      GL20.glDrawBuffers(Shaders.sfbDrawBuffers);
      Shaders.checkGLError("shadow drawbuffers pre-translucent");
      Shaders.checkFramebufferStatus("shadow pre-translucent");
      
      mc.B.c("shadow translucent");
      renderGlobal.a(ahm.d, partialTicks, 2, viewEntity);
      Shaders.checkGLError("shadow translucent");
      if (Reflector.ForgeHooksClient_setRenderPass.exists())
      {
        bcd.b();
        Reflector.call(Reflector.ForgeHooksClient_setRenderPass, new Object[] { Integer.valueOf(1) });
        renderGlobal.a(viewEntity, frustum, partialTicks);
        Reflector.call(Reflector.ForgeHooksClient_setRenderPass, new Object[] { Integer.valueOf(-1) });
        bcd.a();
        Shaders.checkGLError("shadow entities 1");
      }
      bni.j(7424);
      bni.a(true);
      bni.q();
      bni.l();
      
      GL11.glFlush();
      Shaders.checkGLError("shadow flush");
      
      Shaders.isShadowPass = false;
      mc.u.ap = Shaders.preShadowPassThirdPersonView;
      
      mc.B.c("shadow postprocess");
      if (Shaders.hasGlGenMipmap)
      {
        if (Shaders.usedShadowDepthBuffers >= 1)
        {
          if (Shaders.shadowMipmapEnabled[0] != 0)
          {
            bni.g(33988);
            bni.i(Shaders.sfbDepthTextures.get(0));
            GL30.glGenerateMipmap(3553);
            GL11.glTexParameteri(3553, 10241, Shaders.shadowFilterNearest[0] != 0 ? 9984 : 9987);
          }
          if (Shaders.usedShadowDepthBuffers >= 2) {
            if (Shaders.shadowMipmapEnabled[1] != 0)
            {
              bni.g(33989);
              bni.i(Shaders.sfbDepthTextures.get(1));
              GL30.glGenerateMipmap(3553);
              GL11.glTexParameteri(3553, 10241, Shaders.shadowFilterNearest[1] != 0 ? 9984 : 9987);
            }
          }
          bni.g(33984);
        }
        if (Shaders.usedShadowColorBuffers >= 1)
        {
          if (Shaders.shadowColorMipmapEnabled[0] != 0)
          {
            bni.g(33997);
            bni.i(Shaders.sfbColorTextures.get(0));
            GL30.glGenerateMipmap(3553);
            GL11.glTexParameteri(3553, 10241, Shaders.shadowColorFilterNearest[0] != 0 ? 9984 : 9987);
          }
          if (Shaders.usedShadowColorBuffers >= 2) {
            if (Shaders.shadowColorMipmapEnabled[1] != 0)
            {
              bni.g(33998);
              bni.i(Shaders.sfbColorTextures.get(1));
              GL30.glGenerateMipmap(3553);
              GL11.glTexParameteri(3553, 10241, Shaders.shadowColorFilterNearest[1] != 0 ? 9984 : 9987);
            }
          }
          bni.g(33984);
        }
      }
      Shaders.checkGLError("shadow postprocess");
      EXTFramebufferObject.glBindFramebufferEXT(36160, Shaders.dfb);
      GL11.glViewport(0, 0, Shaders.renderWidth, Shaders.renderHeight);
      Shaders.activeDrawBuffers = null;
      mc.N().a(bvg.g);
      Shaders.useProgram(7);
      GL11.glMatrixMode(5888);
      GL11.glPopMatrix();
      GL11.glMatrixMode(5889);
      GL11.glPopMatrix();
      GL11.glMatrixMode(5888);
      Shaders.checkGLError("shadow end");
    }
  }
  
  public static void preRenderChunkLayer()
  {
    if (bzg.f())
    {
      GL11.glEnableClientState(32885);
      GL20.glEnableVertexAttribArray(Shaders.midTexCoordAttrib);
      GL20.glEnableVertexAttribArray(Shaders.tangentAttrib);
      GL20.glEnableVertexAttribArray(Shaders.entityAttrib);
    }
  }
  
  public static void postRenderChunkLayer()
  {
    if (bzg.f())
    {
      GL11.glDisableClientState(32885);
      GL20.glDisableVertexAttribArray(Shaders.midTexCoordAttrib);
      GL20.glDisableVertexAttribArray(Shaders.tangentAttrib);
      GL20.glDisableVertexAttribArray(Shaders.entityAttrib);
    }
  }
  
  public static void setupArrayPointersVbo()
  {
    int vertexSizeI = 14;
    GL11.glVertexPointer(3, 5126, 56, 0L);
    GL11.glColorPointer(4, 5121, 56, 12L);
    GL11.glTexCoordPointer(2, 5126, 56, 16L);
    bzg.l(bzg.r);
    GL11.glTexCoordPointer(2, 5122, 56, 24L);
    bzg.l(bzg.q);
    GL11.glNormalPointer(5120, 56, 28L);
    GL20.glVertexAttribPointer(Shaders.midTexCoordAttrib, 2, 5126, false, 56, 32L);
    GL20.glVertexAttribPointer(Shaders.tangentAttrib, 4, 5122, false, 56, 40L);
    GL20.glVertexAttribPointer(Shaders.entityAttrib, 3, 5122, false, 56, 48L);
  }
  
  public static void beaconBeamBegin()
  {
    Shaders.useProgram(14);
  }
  
  public static void beaconBeamStartQuad1() {}
  
  public static void beaconBeamStartQuad2() {}
  
  public static void beaconBeamDraw1() {}
  
  public static void beaconBeamDraw2() {}
  
  public static void layerArmorBaseDrawEnchantedGlintBegin()
  {
    Shaders.useProgram(17);
  }
  
  public static void layerArmorBaseDrawEnchantedGlintEnd()
  {
    if (Shaders.isRenderingWorld) {
      Shaders.useProgram(16);
    } else {
      Shaders.useProgram(0);
    }
  }
}
