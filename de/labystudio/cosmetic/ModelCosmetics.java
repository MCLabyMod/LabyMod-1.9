package de.labystudio.cosmetic;

import ado;
import adq;
import bcf;
import bix;
import bkm;
import bmq;
import bni;
import bni.i;
import bnk;
import bos.b;
import bvi;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.labymod.Timings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import kk;
import on;
import org.lwjgl.opengl.GL11;
import qm;
import rk;
import rr;
import sa;

public class ModelCosmetics
  extends bix
{
  private bkm bipedDeadmau5Head;
  bkm wolfTail;
  private bkm wing;
  private bkm wingTip;
  bkm ocelotTail;
  bkm ocelotTail2;
  HashMap<UUID, Long> wingState = new HashMap();
  private bkm[] witherBody;
  private bkm[] witherHeads;
  private bkm[] blazeSticks = new bkm[12];
  private bkm witchHat;
  private bkm halo;
  private static final kk enderDragonTextures = new kk("textures/entity/enderdragon/dragon.png");
  private static final kk blazeTexture = new kk("textures/entity/blaze.png");
  private static final kk witherTexture = new kk("textures/entity/wither/wither.png");
  
  public ModelCosmetics(float p_i46304_1_, boolean p_i46304_2_)
  {
    super(p_i46304_1_, 0.0F, 64, 64);
    
    this.bipedDeadmau5Head = new bkm(this, 24, 0);
    this.bipedDeadmau5Head.a(-3.0F, -6.0F, -1.0F, 6, 6, 1, p_i46304_1_);
    this.wolfTail = new bkm(this, 19, 18);
    this.wolfTail.a(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
    this.wolfTail.a(-0.2F, 10.0F, 3.0F);
    this.wolfTail.k = true;
    a("body.scale", 220, 53);
    a("body.body", 0, 0);
    a("wingtip.bone", 112, 136);
    a("wing.skin", -56, 88);
    a("wing.bone", 112, 88);
    a("wingtip.skin", -56, 144);
    int bw = this.s;
    int bh = this.t;
    this.s = 256;
    this.t = 256;
    this.wing = new bkm(this, "wing");
    this.wing.a(-12.0F, 5.0F, 2.0F);
    this.wing.a("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8);
    this.wing.a("skin", -56.0F, 0.0F, 2.0F, 56, 1, 56);
    this.wing.k = true;
    this.wingTip = new bkm(this, "wingtip");
    this.wingTip.a(-56.0F, 0.0F, 0.0F);
    this.wingTip.k = true;
    this.wingTip.a("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4);
    this.wingTip.a("skin", -56.0F, 0.0F, 2.0F, 56, 1, 56);
    this.wing.a(this.wingTip);
    this.s = bw;
    this.t = bh;
    this.ocelotTail = new bkm(this, 58, 13);
    this.ocelotTail.a(-0.5F, 0.0F, 0.0F, 1, 8, 1);
    this.ocelotTail.f = 0.9F;
    this.ocelotTail.a(0.0F, 15.0F, 8.0F);
    this.ocelotTail2 = new bkm(this, 58, 15);
    this.ocelotTail2.a(-0.5F, 0.0F, 0.0F, 1, 6, 1);
    this.ocelotTail2.a(0.0F, 20.0F, 14.0F);
    this.ocelotTail.k = true;
    this.ocelotTail2.k = true;
    for (int i = 0; i < this.blazeSticks.length; i++)
    {
      this.blazeSticks[i] = new bkm(this, 0, 16);
      this.blazeSticks[i].a(0.0F, 0.0F, 0.0F, 2, 8, 2);
      this.blazeSticks[i].k = true;
    }
    this.s = 64;
    this.t = 64;
    this.witherBody = new bkm[2];
    this.witherBody[0] = new bkm(this, 0, 16);
    this.witherBody[0].a(-10.0F, -1.9F, -0.5F, 20, 3, 3, p_i46304_1_);
    this.witherBody[0].k = true;
    this.witherBody[1] = new bkm(this).b(this.s, this.t);
    this.witherBody[1].a(-2.0F, 6.9F, -0.5F);
    this.witherBody[1].k = true;
    
    this.witherHeads = new bkm[2];
    this.witherHeads[0] = new bkm(this, 3, 3);
    this.witherHeads[0].a(-4.0F, -11.0F, -4.0F, 6, 6, 6, p_i46304_1_);
    this.witherHeads[0].c = -8.0F;
    this.witherHeads[0].d = 4.0F;
    this.witherHeads[0].k = true;
    this.witherHeads[1] = new bkm(this, 3, 3);
    this.witherHeads[1].a(-4.0F, -11.0F, -4.0F, 6, 6, 6, p_i46304_1_);
    this.witherHeads[1].c = 10.0F;
    this.witherHeads[1].d = 4.0F;
    this.witherHeads[1].k = true;
    this.s = bw;
    this.t = bh;
    
    this.witchHat = new bkm(this).b(64, 128);
    this.witchHat.a(-5.0F, -10.03125F, -5.0F);
    this.witchHat.a(0, 64).a(0.0F, 0.0F, 0.0F, 10, 2, 10);
    
    this.halo = new bkm(this, 0, 0);
    this.halo.a(-3.0F, -6.0F, -1.0F, 6, 1, 1, p_i46304_1_);
    this.halo.k = true;
    
    bkm modelrenderer = new bkm(this).b(64, 128);
    modelrenderer.a(1.75F, -4.0F, 2.0F);
    modelrenderer.a(0, 76).a(0.0F, 0.0F, 0.0F, 7, 4, 7);
    modelrenderer.f = -0.05235988F;
    modelrenderer.h = 0.02617994F;
    this.witchHat.a(modelrenderer);
    bkm modelrenderer1 = new bkm(this).b(64, 128);
    modelrenderer1.a(1.75F, -4.0F, 2.0F);
    modelrenderer1.a(0, 87).a(0.0F, 0.0F, 0.0F, 4, 4, 4);
    modelrenderer1.f = -0.10471976F;
    modelrenderer1.h = 0.05235988F;
    modelrenderer.a(modelrenderer1);
    bkm modelrenderer2 = new bkm(this).b(64, 128);
    modelrenderer2.a(1.75F, -2.0F, 2.0F);
    modelrenderer2.a(0, 95).a(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
    modelrenderer2.f = -0.20943952F;
    modelrenderer2.h = 0.10471976F;
    modelrenderer1.a(modelrenderer2);
    this.witchHat.k = true;
  }
  
  public ModelCosmetics(float p_i46304_1_, float f, int i, int j)
  {
    this(p_i46304_1_, false);
  }
  
  public void a(rr entityIn, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale)
  {
    super.a(entityIn, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale);
    bni.G();
    if (entityIn.aK()) {
      bni.c(0.0F, 0.2F, 0.0F);
    }
    if (ConfigManager.settings.cosmetics)
    {
      Timings.start("Cosmetic Renderer");
      ArrayList<Cosmetic> cosmetics = LabyMod.getInstance().getCosmeticManager().getCosmetic(entityIn);
      if ((cosmetics != null) && 
        ((entityIn instanceof bmq)))
      {
        bmq clientPlayer = (bmq)entityIn;
        if ((clientPlayer != null) && (clientPlayer.o() != null) && (!clientPlayer.y()) && (!clientPlayer.a(rk.a(14))))
        {
          float partialTicks = bcf.z().m == null ? LabyMod.getInstance().getPartialTicks() : 1.0F;
          kk tex = clientPlayer.o();
          for (Cosmetic cosmetic : cosmetics) {
            if (cosmetic != null)
            {
              bcf.z().N().a(tex);
              bni.d(1.0F, 1.0F, 1.0F);
              GL11.glColor3d(1.0D, 1.0D, 1.0D);
              bni.I();
              float f1;
              if (cosmetic.getType() == EnumCosmetic.HALO) {
                for (int i = 0; i < 4; i++)
                {
                  float f = clientPlayer.aP + (clientPlayer.aO - clientPlayer.aP) * partialTicks - (clientPlayer.aN + (clientPlayer.aM - clientPlayer.aN) * partialTicks);
                  
                  f1 = clientPlayer.y + (clientPlayer.w - clientPlayer.y) * partialTicks;
                  
                  bni.G();
                  
                  bni.d(3.9F, 3.0F, 0.0F);
                  if ((LabyMod.getInstance().getCosmeticManager().colorPicker) && (entityIn == bcf.z().h))
                  {
                    GL11.glColor3d(LabyMod.getInstance().getCosmeticManager().colorR * 0.01D, 
                      LabyMod.getInstance().getCosmeticManager().colorG * 0.01D, 
                      LabyMod.getInstance().getCosmeticManager().colorB * 0.01D);
                  }
                  else if (cosmetic.getData() != null)
                  {
                    bni.d(1.0F, 1.0F, 1.0F);
                    GL11.glColor3d(cosmetic.a * 0.01D, cosmetic.b * 0.01D, cosmetic.c * 0.01D);
                  }
                  bni.b(f, 0.0F, 1.0F, 0.0F);
                  
                  bni.b(90 * i, 0.0F, 1.0F, 0.0F);
                  
                  float move = 0.2F;
                  switch (i)
                  {
                  case 0: 
                    bni.c(0.0F, 0.0F, 0.01F - move);
                    break;
                  case 1: 
                    bni.c(-0.19F + move, 0.0F, -0.19F);
                    break;
                  case 2: 
                    bni.c(0.0F, 0.0F, -0.38F + move);
                    break;
                  case 3: 
                    bni.c(0.19F - move, 0.0F, -0.19F);
                  }
                  bcf.z().N().a(new kk("textures/misc/forcefield.png"));
                  
                  float anim = p_78088_4_ / 10.0F;
                  anim = on.b(anim);
                  anim *= 0.03F;
                  
                  bni.c(0.0F, -0.4F - p_78088_3_ * 0.1F - anim, 0.0F);
                  this.halo.k = false;
                  this.halo.a(scale);
                  this.halo.k = true;
                  bni.H();
                }
              }
              if (cosmetic.getType() == EnumCosmetic.TOOL)
              {
                bni.G();
                if (entityIn.aK()) {
                  bni.b(30.0F, 1.0F, 0.0F, 0.0F);
                }
                bni.a(0.8D, 0.8D, 0.8D);
                bni.b(180.0F, 0.0F, 1.0F, 0.0F);
                bni.b(0.0D, 0.3D, -0.22D);
                adq item;
                adq item;
                if (cosmetic.getData() == null) {
                  item = ((bmq)entityIn).b(qm.a);
                } else {
                  item = new adq(ado.c((int)cosmetic.b));
                }
                bcf.z().ae().a((sa)entityIn, item, bos.b.i);
                bni.H();
              }
              float f;
              float f1;
              if (cosmetic.getType() == EnumCosmetic.HAT)
              {
                bcf.z().N().a(new kk("textures/entity/witch.png"));
                f = clientPlayer.aP + (clientPlayer.aO - clientPlayer.aP) * partialTicks - (clientPlayer.aN + (clientPlayer.aM - clientPlayer.aN) * partialTicks);
                
                f1 = clientPlayer.y + (clientPlayer.w - clientPlayer.y) * partialTicks;
                
                bni.G();
                if (entityIn.aK()) {
                  bni.b(0.0D, 0.1D, 0.0D);
                }
                if ((LabyMod.getInstance().getCosmeticManager().colorPicker) && (entityIn == bcf.z().h))
                {
                  GL11.glColor3d(LabyMod.getInstance().getCosmeticManager().colorR * 0.01D, 
                    LabyMod.getInstance().getCosmeticManager().colorG * 0.01D, 
                    LabyMod.getInstance().getCosmeticManager().colorB * 0.01D);
                }
                else if (cosmetic.getData() != null)
                {
                  bni.d(1.0F, 1.0F, 1.0F);
                  GL11.glColor3d(cosmetic.a * 0.01D, cosmetic.b * 0.01D, cosmetic.c * 0.01D);
                }
                bni.b(f, 0.0F, 1.0F, 0.0F);
                bni.b(f1, 1.0F, 0.0F, 0.0F);
                this.witchHat.k = false;
                this.witchHat.a(scale);
                this.witchHat.k = true;
                bni.H();
              }
              if (cosmetic.getType() == EnumCosmetic.WITHER)
              {
                f = this.witherHeads;f1 = f.length;
                for (f1 = 0; f1 < f1; f1++)
                {
                  bkm modelrenderer = f[f1];
                  
                  modelrenderer.k = false;
                  modelrenderer.a(scale);
                  modelrenderer.k = true;
                }
                bcf.z().N().a(witherTexture);
                f = this.witherBody;f1 = f.length;
                for (f1 = 0; f1 < f1; f1++)
                {
                  bkm modelrenderer1 = f[f1];
                  
                  modelrenderer1.k = false;
                  modelrenderer1.a(scale);
                  modelrenderer1.k = true;
                }
              }
              if (cosmetic.getType() == EnumCosmetic.BLAZE)
              {
                float p_78087_3_ = p_78088_4_;
                float f = p_78087_3_ * 3.1415927F * -0.01F;
                for (int i = 0; i < 4; i++)
                {
                  this.blazeSticks[i].d = (-2.0F + on.b(((float)(i * 1.5D) + p_78087_3_) * 0.2F));
                  this.blazeSticks[i].c = (on.b(f) * 10.0F);
                  this.blazeSticks[i].e = (on.a(f) * 10.0F);
                  f += 1.0F;
                }
                f = 0.7853982F + p_78087_3_ * 3.1415927F * 0.01F;
                for (int j = 4; j < 8; j++)
                {
                  this.blazeSticks[j].d = (2.0F + on.b((j * 2 + p_78087_3_) * 0.2F));
                  this.blazeSticks[j].c = (on.b(f) * 9.0F);
                  this.blazeSticks[j].e = (on.a(f) * 9.0F);
                  f += 1.0F;
                }
                f = 0.47123894F + p_78087_3_ * 3.1415927F * -0.01F;
                for (int k = 8; k < 12; k++)
                {
                  this.blazeSticks[k].d = (11.0F + on.b((k * 1.5F + p_78087_3_) * 0.5F));
                  this.blazeSticks[k].c = (on.b(f) * 5.0F);
                  this.blazeSticks[k].e = (on.a(f) * 5.0F);
                  f += 1.0F;
                }
                bcf.z().N().a(blazeTexture);
                if ((LabyMod.getInstance().getCosmeticManager().colorPicker) && (entityIn == bcf.z().h))
                {
                  GL11.glColor3d(LabyMod.getInstance().getCosmeticManager().colorR * 0.01D, 
                    LabyMod.getInstance().getCosmeticManager().colorG * 0.01D, 
                    LabyMod.getInstance().getCosmeticManager().colorB * 0.01D);
                }
                else if (cosmetic.getData() != null)
                {
                  bni.d(1.0F, 1.0F, 1.0F);
                  GL11.glColor3d(cosmetic.a * 0.01D, cosmetic.b * 0.01D, cosmetic.c * 0.01D);
                }
                for (int i = 0; i < this.blazeSticks.length; i++)
                {
                  this.blazeSticks[i].k = false;
                  this.blazeSticks[i].a(scale);
                  this.blazeSticks[i].k = true;
                }
              }
              if (cosmetic.getType() == EnumCosmetic.DEADMAU5) {
                for (int i = 0; i < 2; i++)
                {
                  float f = clientPlayer.aP + (clientPlayer.aO - clientPlayer.aP) * partialTicks - (clientPlayer.aN + (clientPlayer.aM - clientPlayer.aN) * partialTicks);
                  
                  float f1 = clientPlayer.y + (clientPlayer.w - clientPlayer.y) * partialTicks;
                  bni.G();
                  bni.b(f, 0.0F, 1.0F, 0.0F);
                  bni.b(f1, 1.0F, 0.0F, 0.0F);
                  bni.c(0.375F * (i * 2 - 1), 0.0F, 0.0F);
                  bni.c(0.0F, -0.375F, 0.0F);
                  bni.b(-f1, 1.0F, 0.0F, 0.0F);
                  bni.b(-f, 0.0F, 1.0F, 0.0F);
                  float f2 = 1.3333334F;
                  bni.b(f2, f2, f2);
                  renderDeadmau5Head(0.0625F);
                  bni.H();
                }
              }
              if (cosmetic.getType() == EnumCosmetic.OCELOTTAIL)
              {
                this.ocelotTail.d = 15.0F;
                this.ocelotTail.e = 8.0F;
                this.ocelotTail2.d = 20.0F;
                this.ocelotTail2.e = 14.0F;
                this.ocelotTail.f = 0.9F;
                bni.G();
                if (entityIn.aK())
                {
                  bni.c(0.0F, -0.35F, -0.33F);
                  this.ocelotTail.d += 1.0F;
                  this.ocelotTail2.d += -4.0F;
                  this.ocelotTail2.e += 2.0F;
                  this.ocelotTail.f = 1.5707964F;
                  this.ocelotTail2.f = 1.5707964F;
                }
                else if (entityIn.aL())
                {
                  bni.c(0.0F, -0.2F, -0.61F);
                  this.ocelotTail2.d = this.ocelotTail.d;
                  this.ocelotTail2.e += 2.0F;
                  this.ocelotTail.f = 1.5707964F;
                  this.ocelotTail2.f = 1.5707964F;
                  this.ocelotTail2.f = (1.7278761F + 0.31415927F * on.b(p_78088_2_) * p_78088_3_);
                }
                if ((entityIn.aL()) || (entityIn.aK()))
                {
                  this.ocelotTail2.f = (1.7278761F + 0.47123894F * on.b(p_78088_2_) * p_78088_3_);
                }
                else
                {
                  bni.c(0.0F, -0.35F, -0.61F);
                  this.ocelotTail2.f = (1.7278761F + 0.7853982F * on.b(p_78088_2_) * p_78088_3_);
                }
                this.ocelotTail.k = false;
                this.ocelotTail2.k = false;
                this.ocelotTail.a(scale);
                this.ocelotTail2.a(scale);
                this.ocelotTail.k = true;
                this.ocelotTail2.k = true;
                bni.H();
              }
              if (cosmetic.getType() == EnumCosmetic.WOLFTAIL)
              {
                bni.G();
                if (entityIn.aK())
                {
                  bni.c(0.0F, 0.2F, -0.25F);
                  bni.b(45.0F, 45.0F, 0.0F, 0.0F);
                }
                else
                {
                  bni.c(0.0F, 0.1F, -0.25F);
                  bni.b(15.0F, 15.0F, 0.0F, 0.0F);
                }
                if ((cosmetic.getData() != null) && (cosmetic.getData().equalsIgnoreCase("emotions")))
                {
                  float health = clientPlayer.bQ();
                  if (health > 20.0F) {
                    health = 20.0F;
                  }
                  if (health < 0.0F) {
                    health = 0.0F;
                  }
                  bni.c(0.0F, health / 80.0F, health / 50.0F * -1.0F);
                  bni.b(health * 2.0F, health * 2.0F, 0.0F, 0.0F);
                }
                this.wolfTail.k = false;
                this.wolfTail.b(scale);
                this.wolfTail.k = true;
                bni.H();
              }
              if (cosmetic.getType() == EnumCosmetic.WINGS)
              {
                bni.G();
                float var6 = 100.0F;
                boolean flying = this.wingState.containsKey(entityIn.bc());
                boolean onGround = entityIn.z;
                if ((!onGround) || (flying)) {
                  var6 = 10.0F;
                }
                if ((!flying) && (!onGround)) {
                  this.wingState.put(entityIn.bc(), Long.valueOf(0L));
                }
                float f = p_78088_3_ + p_78088_4_ / var6;
                float f2 = p_78088_3_ + p_78088_4_ / 100.0F;
                
                float fs11 = f * 3.141593F * 2.0F;
                float temp1 = 0.125F - (float)Math.cos(fs11) * 0.2F;
                
                float fs112 = f2 * 3.141593F * 2.0F;
                float temp2 = 0.125F - (float)Math.cos(fs112) * 0.2F;
                if ((this.wingState.containsKey(entityIn.bc())) && ((int)(temp1 * 100.0F) == (int)(temp2 * 100.0F)) && (onGround))
                {
                  this.wingState.remove(entityIn.bc());
                  var6 = 100.0F;
                }
                bcf.z().N().a(enderDragonTextures);
                bni.a(0.15D, 0.15D, 0.15D);
                bni.b(0.0D, -0.3D, 1.1D);
                bni.b(50.0F, -50.0F, 0.0F, 0.0F);
                
                boolean t = false;
                boolean w = false;
                if ((LabyMod.getInstance().getCosmeticManager().colorPicker) && (entityIn == bcf.z().h))
                {
                  GL11.glColor3d(LabyMod.getInstance().getCosmeticManager().colorR * 0.01D, 
                    LabyMod.getInstance().getCosmeticManager().colorG * 0.01D, 
                    LabyMod.getInstance().getCosmeticManager().colorB * 0.01D);
                }
                else if (cosmetic.getData() != null)
                {
                  bni.d(1.0F, 1.0F, 1.0F);
                  GL11.glColor3d(cosmetic.a * 0.01D, cosmetic.b * 0.01D, cosmetic.c * 0.01D);
                  if ((cosmetic.a == -1.0D) && (cosmetic.b == -1.0D) && (cosmetic.c == -1.0D))
                  {
                    int wr = 600;
                    w = bcf.I() % wr / (wr / 2) == 0L;
                    t = true;
                    if (w) {
                      GL11.glColor3d(18.0D, 0.0D, 0.0D);
                    } else {
                      GL11.glColor3d(0.0D, 0.0D, 18.0D);
                    }
                  }
                }
                bni.b(0);
                bni.b(1);
                for (int j = 0; j < 2; j++)
                {
                  bni.q();
                  float f11 = f * 3.1415927F * 2.0F;
                  this.wing.f = (0.125F - (float)Math.cos(f11) * 0.2F);
                  this.wing.g = 0.25F;
                  this.wing.h = ((float)(Math.sin(f11) + 1.225D) * 0.3F);
                  this.wingTip.h = (-(float)(Math.sin(f11 + 2.0F) + 0.5D) * 0.75F);
                  this.wing.k = false;
                  this.wingTip.k = false;
                  this.wing.a(scale);
                  this.wing.k = true;
                  this.wingTip.k = true;
                  bni.b(-1.0F, 1.0F, 1.0F);
                  if (j == 0)
                  {
                    bni.a(bni.i.a);
                    if (t) {
                      if (w) {
                        GL11.glColor3d(0.0D, 0.0D, 18.0D);
                      } else {
                        GL11.glColor3d(18.0D, 0.0D, 0.0D);
                      }
                    }
                  }
                }
                bni.a(0);
                bni.a(1);
                
                bni.a(bni.i.b);
                bni.r();
                bni.k();
                bni.H();
              }
            }
          }
          bcf.z().N().a(tex);
          GL11.glColor3d(1.0D, 1.0D, 1.0D);
        }
      }
      Timings.stop("Cosmetic Renderer");
    }
    bni.H();
  }
  
  public void renderDeadmau5Head(float p_178727_1_)
  {
    a(this.e, this.bipedDeadmau5Head);
    this.bipedDeadmau5Head.c = 0.0F;
    this.bipedDeadmau5Head.d = 0.0F;
    this.bipedDeadmau5Head.a(p_178727_1_);
  }
  
  public void a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, rr entityIn)
  {
    super.a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, entityIn);
    
    this.wolfTail.g = (on.b(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_);
    this.wolfTail.f = p_78087_2_;
  }
  
  public void a(boolean invisible)
  {
    super.a(invisible);
    this.bipedDeadmau5Head.j = invisible;
    this.wolfTail.j = invisible;
    this.wing.j = invisible;
    this.wingTip.j = invisible;
    this.ocelotTail.j = invisible;
    this.ocelotTail2.j = invisible;
  }
}
