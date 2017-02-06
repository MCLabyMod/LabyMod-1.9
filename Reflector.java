import com.google.common.base.Optional;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Reflector
{
  public static ReflectorClass MinecraftForge = new ReflectorClass("net.minecraftforge.common.MinecraftForge");
  public static ReflectorField MinecraftForge_EVENT_BUS = new ReflectorField(MinecraftForge, "EVENT_BUS");
  public static ReflectorClass ForgeHooks = new ReflectorClass("net.minecraftforge.common.ForgeHooks");
  public static ReflectorMethod ForgeHooks_onLivingSetAttackTarget = new ReflectorMethod(ForgeHooks, "onLivingSetAttackTarget");
  public static ReflectorMethod ForgeHooks_onLivingUpdate = new ReflectorMethod(ForgeHooks, "onLivingUpdate");
  public static ReflectorMethod ForgeHooks_onLivingAttack = new ReflectorMethod(ForgeHooks, "onLivingAttack");
  public static ReflectorMethod ForgeHooks_onLivingHurt = new ReflectorMethod(ForgeHooks, "onLivingHurt");
  public static ReflectorMethod ForgeHooks_onLivingDeath = new ReflectorMethod(ForgeHooks, "onLivingDeath");
  public static ReflectorMethod ForgeHooks_onLivingDrops = new ReflectorMethod(ForgeHooks, "onLivingDrops");
  public static ReflectorMethod ForgeHooks_onLivingFall = new ReflectorMethod(ForgeHooks, "onLivingFall");
  public static ReflectorMethod ForgeHooks_onLivingJump = new ReflectorMethod(ForgeHooks, "onLivingJump");
  public static ReflectorClass MinecraftForgeClient = new ReflectorClass("net.minecraftforge.client.MinecraftForgeClient");
  public static ReflectorMethod MinecraftForgeClient_getRenderPass = new ReflectorMethod(MinecraftForgeClient, "getRenderPass");
  public static ReflectorMethod MinecraftForgeClient_onRebuildChunk = new ReflectorMethod(MinecraftForgeClient, "onRebuildChunk");
  public static ReflectorClass ForgeHooksClient = new ReflectorClass("net.minecraftforge.client.ForgeHooksClient");
  public static ReflectorMethod ForgeHooksClient_onDrawBlockHighlight = new ReflectorMethod(ForgeHooksClient, "onDrawBlockHighlight");
  public static ReflectorMethod ForgeHooksClient_orientBedCamera = new ReflectorMethod(ForgeHooksClient, "orientBedCamera");
  public static ReflectorMethod ForgeHooksClient_dispatchRenderLast = new ReflectorMethod(ForgeHooksClient, "dispatchRenderLast");
  public static ReflectorMethod ForgeHooksClient_setRenderPass = new ReflectorMethod(ForgeHooksClient, "setRenderPass");
  public static ReflectorMethod ForgeHooksClient_onTextureStitchedPre = new ReflectorMethod(ForgeHooksClient, "onTextureStitchedPre");
  public static ReflectorMethod ForgeHooksClient_onTextureStitchedPost = new ReflectorMethod(ForgeHooksClient, "onTextureStitchedPost");
  public static ReflectorMethod ForgeHooksClient_renderFirstPersonHand = new ReflectorMethod(ForgeHooksClient, "renderFirstPersonHand");
  public static ReflectorMethod ForgeHooksClient_getOffsetFOV = new ReflectorMethod(ForgeHooksClient, "getOffsetFOV");
  public static ReflectorMethod ForgeHooksClient_drawScreen = new ReflectorMethod(ForgeHooksClient, "drawScreen");
  public static ReflectorMethod ForgeHooksClient_onFogRender = new ReflectorMethod(ForgeHooksClient, "onFogRender");
  public static ReflectorMethod ForgeHooksClient_setRenderLayer = new ReflectorMethod(ForgeHooksClient, "setRenderLayer");
  public static ReflectorMethod ForgeHooksClient_transform = new ReflectorMethod(ForgeHooksClient, "transform");
  public static ReflectorMethod ForgeHooksClient_getMatrix = new ReflectorMethod(ForgeHooksClient, "getMatrix", new Class[] { bxp.class });
  public static ReflectorMethod ForgeHooksClient_fillNormal = new ReflectorMethod(ForgeHooksClient, "fillNormal");
  public static ReflectorMethod ForgeHooksClient_handleCameraTransforms = new ReflectorMethod(ForgeHooksClient, "handleCameraTransforms");
  public static ReflectorMethod ForgeHooksClient_getArmorModel = new ReflectorMethod(ForgeHooksClient, "getArmorModel");
  public static ReflectorMethod ForgeHooksClient_getArmorTexture = new ReflectorMethod(ForgeHooksClient, "getArmorTexture");
  public static ReflectorMethod ForgeHooksClient_putQuadColor = new ReflectorMethod(ForgeHooksClient, "putQuadColor");
  public static ReflectorMethod ForgeHooksClient_applyTransform = new ReflectorMethod(ForgeHooksClient, "applyTransform", new Class[] { bzc.class, Optional.class });
  public static ReflectorMethod ForgeHooksClient_loadEntityShader = new ReflectorMethod(ForgeHooksClient, "loadEntityShader");
  public static ReflectorClass FMLCommonHandler = new ReflectorClass("net.minecraftforge.fml.common.FMLCommonHandler");
  public static ReflectorMethod FMLCommonHandler_instance = new ReflectorMethod(FMLCommonHandler, "instance");
  public static ReflectorMethod FMLCommonHandler_handleServerStarting = new ReflectorMethod(FMLCommonHandler, "handleServerStarting");
  public static ReflectorMethod FMLCommonHandler_handleServerAboutToStart = new ReflectorMethod(FMLCommonHandler, "handleServerAboutToStart");
  public static ReflectorMethod FMLCommonHandler_enhanceCrashReport = new ReflectorMethod(FMLCommonHandler, "enhanceCrashReport");
  public static ReflectorMethod FMLCommonHandler_getBrandings = new ReflectorMethod(FMLCommonHandler, "getBrandings");
  public static ReflectorMethod FMLCommonHandler_callFuture = new ReflectorMethod(FMLCommonHandler, "callFuture");
  public static ReflectorClass FMLClientHandler = new ReflectorClass("net.minecraftforge.fml.client.FMLClientHandler");
  public static ReflectorMethod FMLClientHandler_instance = new ReflectorMethod(FMLClientHandler, "instance");
  public static ReflectorMethod FMLClientHandler_isLoading = new ReflectorMethod(FMLClientHandler, "isLoading");
  public static ReflectorMethod FMLClientHandler_trackBrokenTexture = new ReflectorMethod(FMLClientHandler, "trackBrokenTexture");
  public static ReflectorMethod FMLClientHandler_trackMissingTexture = new ReflectorMethod(FMLClientHandler, "trackMissingTexture");
  public static ReflectorClass ForgeWorldProvider = new ReflectorClass(asv.class);
  public static ReflectorMethod ForgeWorldProvider_getSkyRenderer = new ReflectorMethod(ForgeWorldProvider, "getSkyRenderer");
  public static ReflectorMethod ForgeWorldProvider_getCloudRenderer = new ReflectorMethod(ForgeWorldProvider, "getCloudRenderer");
  public static ReflectorMethod ForgeWorldProvider_getWeatherRenderer = new ReflectorMethod(ForgeWorldProvider, "getWeatherRenderer");
  public static ReflectorClass ForgeWorld = new ReflectorClass(aht.class);
  public static ReflectorMethod ForgeWorld_countEntities = new ReflectorMethod(ForgeWorld, "countEntities", new Class[] { sc.class, Boolean.TYPE });
  public static ReflectorMethod ForgeWorld_getPerWorldStorage = new ReflectorMethod(ForgeWorld, "getPerWorldStorage");
  public static ReflectorClass IRenderHandler = new ReflectorClass("net.minecraftforge.client.IRenderHandler");
  public static ReflectorMethod IRenderHandler_render = new ReflectorMethod(IRenderHandler, "render");
  public static ReflectorClass DimensionManager = new ReflectorClass("net.minecraftforge.common.DimensionManager");
  public static ReflectorMethod DimensionManager_getStaticDimensionIDs = new ReflectorMethod(DimensionManager, "getStaticDimensionIDs");
  public static ReflectorClass WorldEvent_Load = new ReflectorClass("net.minecraftforge.event.world.WorldEvent$Load");
  public static ReflectorConstructor WorldEvent_Load_Constructor = new ReflectorConstructor(WorldEvent_Load, new Class[] { aht.class });
  public static ReflectorClass RenderItemInFrameEvent = new ReflectorClass("net.minecraftforge.client.event.RenderItemInFrameEvent");
  public static ReflectorConstructor RenderItemInFrameEvent_Constructor = new ReflectorConstructor(RenderItemInFrameEvent, new Class[] { xs.class, bry.class });
  public static ReflectorClass DrawScreenEvent_Pre = new ReflectorClass("net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Pre");
  public static ReflectorConstructor DrawScreenEvent_Pre_Constructor = new ReflectorConstructor(DrawScreenEvent_Pre, new Class[] { bfb.class, Integer.TYPE, Integer.TYPE, Float.TYPE });
  public static ReflectorClass DrawScreenEvent_Post = new ReflectorClass("net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Post");
  public static ReflectorConstructor DrawScreenEvent_Post_Constructor = new ReflectorConstructor(DrawScreenEvent_Post, new Class[] { bfb.class, Integer.TYPE, Integer.TYPE, Float.TYPE });
  public static ReflectorClass EntityViewRenderEvent_FogColors = new ReflectorClass("net.minecraftforge.client.event.EntityViewRenderEvent$FogColors");
  public static ReflectorConstructor EntityViewRenderEvent_FogColors_Constructor = new ReflectorConstructor(EntityViewRenderEvent_FogColors, new Class[] { bng.class, rr.class, ajt.class, Double.TYPE, Float.TYPE, Float.TYPE, Float.TYPE });
  public static ReflectorField EntityViewRenderEvent_FogColors_red = new ReflectorField(EntityViewRenderEvent_FogColors, "red");
  public static ReflectorField EntityViewRenderEvent_FogColors_green = new ReflectorField(EntityViewRenderEvent_FogColors, "green");
  public static ReflectorField EntityViewRenderEvent_FogColors_blue = new ReflectorField(EntityViewRenderEvent_FogColors, "blue");
  public static ReflectorClass EntityViewRenderEvent_FogDensity = new ReflectorClass("net.minecraftforge.client.event.EntityViewRenderEvent$FogDensity");
  public static ReflectorConstructor EntityViewRenderEvent_FogDensity_Constructor = new ReflectorConstructor(EntityViewRenderEvent_FogDensity, new Class[] { bng.class, rr.class, ajt.class, Double.TYPE, Float.TYPE });
  public static ReflectorField EntityViewRenderEvent_FogDensity_density = new ReflectorField(EntityViewRenderEvent_FogDensity, "density");
  public static ReflectorClass EntityViewRenderEvent_RenderFogEvent = new ReflectorClass("net.minecraftforge.client.event.EntityViewRenderEvent$RenderFogEvent");
  public static ReflectorConstructor EntityViewRenderEvent_RenderFogEvent_Constructor = new ReflectorConstructor(EntityViewRenderEvent_RenderFogEvent, new Class[] { bng.class, rr.class, ajt.class, Double.TYPE, Integer.TYPE, Float.TYPE });
  public static ReflectorClass RenderLivingEvent_Pre = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Pre");
  public static ReflectorConstructor RenderLivingEvent_Pre_Constructor = new ReflectorConstructor(RenderLivingEvent_Pre, new Class[] { sa.class, bsg.class, Double.TYPE, Double.TYPE, Double.TYPE });
  public static ReflectorClass RenderLivingEvent_Post = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Post");
  public static ReflectorConstructor RenderLivingEvent_Post_Constructor = new ReflectorConstructor(RenderLivingEvent_Post, new Class[] { sa.class, bsg.class, Double.TYPE, Double.TYPE, Double.TYPE });
  public static ReflectorClass RenderLivingEvent_Specials_Pre = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Specials$Pre");
  public static ReflectorConstructor RenderLivingEvent_Specials_Pre_Constructor = new ReflectorConstructor(RenderLivingEvent_Specials_Pre, new Class[] { sa.class, bsg.class, Double.TYPE, Double.TYPE, Double.TYPE });
  public static ReflectorClass RenderLivingEvent_Specials_Post = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Specials$Post");
  public static ReflectorConstructor RenderLivingEvent_Specials_Post_Constructor = new ReflectorConstructor(RenderLivingEvent_Specials_Post, new Class[] { sa.class, bsg.class, Double.TYPE, Double.TYPE, Double.TYPE });
  public static ReflectorClass EventBus = new ReflectorClass("net.minecraftforge.fml.common.eventhandler.EventBus");
  public static ReflectorMethod EventBus_post = new ReflectorMethod(EventBus, "post");
  public static ReflectorClass Event_Result = new ReflectorClass("net.minecraftforge.fml.common.eventhandler.Event$Result");
  public static ReflectorField Event_Result_DENY = new ReflectorField(Event_Result, "DENY");
  public static ReflectorField Event_Result_ALLOW = new ReflectorField(Event_Result, "ALLOW");
  public static ReflectorField Event_Result_DEFAULT = new ReflectorField(Event_Result, "DEFAULT");
  public static ReflectorClass ForgeEventFactory = new ReflectorClass("net.minecraftforge.event.ForgeEventFactory");
  public static ReflectorMethod ForgeEventFactory_canEntitySpawn = new ReflectorMethod(ForgeEventFactory, "canEntitySpawn");
  public static ReflectorMethod ForgeEventFactory_canEntityDespawn = new ReflectorMethod(ForgeEventFactory, "canEntityDespawn");
  public static ReflectorMethod ForgeEventFactory_renderBlockOverlay = new ReflectorMethod(ForgeEventFactory, "renderBlockOverlay");
  public static ReflectorMethod ForgeEventFactory_renderWaterOverlay = new ReflectorMethod(ForgeEventFactory, "renderWaterOverlay");
  public static ReflectorMethod ForgeEventFactory_renderFireOverlay = new ReflectorMethod(ForgeEventFactory, "renderFireOverlay");
  public static ReflectorClass RenderBlockOverlayEvent_OverlayType = new ReflectorClass("net.minecraftforge.client.event.RenderBlockOverlayEvent$OverlayType");
  public static ReflectorField RenderBlockOverlayEvent_OverlayType_BLOCK = new ReflectorField(RenderBlockOverlayEvent_OverlayType, "BLOCK");
  public static ReflectorClass ChunkWatchEvent_UnWatch = new ReflectorClass("net.minecraftforge.event.world.ChunkWatchEvent$UnWatch");
  public static ReflectorConstructor ChunkWatchEvent_UnWatch_Constructor = new ReflectorConstructor(ChunkWatchEvent_UnWatch, new Class[] { ahn.class, lr.class });
  public static ReflectorClass ForgeBlock = new ReflectorClass(ajt.class);
  public static ReflectorMethod ForgeBlock_getBedDirection = new ReflectorMethod(ForgeBlock, "getBedDirection");
  public static ReflectorMethod ForgeBlock_isBed = new ReflectorMethod(ForgeBlock, "isBed");
  public static ReflectorMethod ForgeBlock_isBedFoot = new ReflectorMethod(ForgeBlock, "isBedFoot");
  public static ReflectorMethod ForgeBlock_hasTileEntity = new ReflectorMethod(ForgeBlock, "hasTileEntity", new Class[] { arc.class });
  public static ReflectorMethod ForgeBlock_canCreatureSpawn = new ReflectorMethod(ForgeBlock, "canCreatureSpawn");
  public static ReflectorMethod ForgeBlock_addHitEffects = new ReflectorMethod(ForgeBlock, "addHitEffects");
  public static ReflectorMethod ForgeBlock_addDestroyEffects = new ReflectorMethod(ForgeBlock, "addDestroyEffects");
  public static ReflectorMethod ForgeBlock_isAir = new ReflectorMethod(ForgeBlock, "isAir");
  public static ReflectorMethod ForgeBlock_canRenderInLayer = new ReflectorMethod(ForgeBlock, "canRenderInLayer");
  public static ReflectorMethod ForgeBlock_getExtendedState = new ReflectorMethod(ForgeBlock, "getExtendedState");
  public static ReflectorClass ForgeEntity = new ReflectorClass(rr.class);
  public static ReflectorField ForgeEntity_captureDrops = new ReflectorField(ForgeEntity, "captureDrops");
  public static ReflectorField ForgeEntity_capturedDrops = new ReflectorField(ForgeEntity, "capturedDrops");
  public static ReflectorMethod ForgeEntity_shouldRenderInPass = new ReflectorMethod(ForgeEntity, "shouldRenderInPass");
  public static ReflectorMethod ForgeEntity_canRiderInteract = new ReflectorMethod(ForgeEntity, "canRiderInteract");
  public static ReflectorMethod ForgeEntity_shouldRiderSit = new ReflectorMethod(ForgeEntity, "shouldRiderSit");
  public static ReflectorClass ForgeTileEntity = new ReflectorClass(apv.class);
  public static ReflectorMethod ForgeTileEntity_shouldRenderInPass = new ReflectorMethod(ForgeTileEntity, "shouldRenderInPass");
  public static ReflectorMethod ForgeTileEntity_getRenderBoundingBox = new ReflectorMethod(ForgeTileEntity, "getRenderBoundingBox");
  public static ReflectorMethod ForgeTileEntity_canRenderBreaking = new ReflectorMethod(ForgeTileEntity, "canRenderBreaking");
  public static ReflectorClass ForgeItem = new ReflectorClass(ado.class);
  public static ReflectorMethod ForgeItem_onEntitySwing = new ReflectorMethod(ForgeItem, "onEntitySwing");
  public static ReflectorMethod ForgeItem_shouldCauseReequipAnimation = new ReflectorMethod(ForgeItem, "shouldCauseReequipAnimation");
  public static ReflectorMethod ForgeItem_getModel = new ReflectorMethod(ForgeItem, "getModel");
  public static ReflectorMethod ForgeItem_showDurabilityBar = new ReflectorMethod(ForgeItem, "showDurabilityBar");
  public static ReflectorMethod ForgeItem_getDurabilityForDisplay = new ReflectorMethod(ForgeItem, "getDurabilityForDisplay");
  public static ReflectorClass ForgePotionEffect = new ReflectorClass(rl.class);
  public static ReflectorMethod ForgePotionEffect_isCurativeItem = new ReflectorMethod(ForgePotionEffect, "isCurativeItem");
  public static ReflectorClass ForgeItemRecord = new ReflectorClass(aef.class);
  public static ReflectorMethod ForgeItemRecord_getRecordResource = new ReflectorMethod(ForgeItemRecord, "getRecordResource", new Class[] { String.class });
  public static ReflectorClass ForgeVertexFormatElementEnumUseage = new ReflectorClass(bvs.b.class);
  public static ReflectorMethod ForgeVertexFormatElementEnumUseage_preDraw = new ReflectorMethod(ForgeVertexFormatElementEnumUseage, "preDraw");
  public static ReflectorMethod ForgeVertexFormatElementEnumUseage_postDraw = new ReflectorMethod(ForgeVertexFormatElementEnumUseage, "postDraw");
  public static ReflectorClass BlamingTransformer = new ReflectorClass("net.minecraftforge.fml.common.asm.transformers.BlamingTransformer");
  public static ReflectorMethod BlamingTransformer_onCrash = new ReflectorMethod(BlamingTransformer, "onCrash");
  public static ReflectorClass CoreModManager = new ReflectorClass("net.minecraftforge.fml.relauncher.CoreModManager");
  public static ReflectorMethod CoreModManager_onCrash = new ReflectorMethod(CoreModManager, "onCrash");
  public static ReflectorClass ISmartBlockModel = new ReflectorClass("net.minecraftforge.client.model.ISmartBlockModel");
  public static ReflectorMethod ISmartBlockModel_handleBlockState = new ReflectorMethod(ISmartBlockModel, "handleBlockState");
  public static ReflectorClass Launch = new ReflectorClass("net.minecraft.launchwrapper.Launch");
  public static ReflectorField Launch_blackboard = new ReflectorField(Launch, "blackboard");
  public static ReflectorClass SplashScreen = new ReflectorClass("net.minecraftforge.fml.client.SplashProgress");
  public static ReflectorClass LightUtil = new ReflectorClass("net.minecraftforge.client.model.pipeline.LightUtil");
  public static ReflectorMethod LightUtil_putBakedQuad = new ReflectorMethod(LightUtil, "putBakedQuad");
  public static ReflectorMethod LightUtil_renderQuadColor = new ReflectorMethod(LightUtil, "renderQuadColor");
  public static ReflectorField LightUtil_tessellator = new ReflectorField(LightUtil, "tessellator");
  public static ReflectorField LightUtil_itemConsumer = new ReflectorField(LightUtil, "itemConsumer");
  public static ReflectorClass IExtendedBlockState = new ReflectorClass("net.minecraftforge.common.property.IExtendedBlockState");
  public static ReflectorMethod IExtendedBlockState_getClean = new ReflectorMethod(IExtendedBlockState, "getClean");
  public static ReflectorClass ItemModelMesherForge = new ReflectorClass("net.minecraftforge.client.ItemModelMesherForge");
  public static ReflectorConstructor ItemModelMesherForge_Constructor = new ReflectorConstructor(ItemModelMesherForge, new Class[] { bxs.class });
  public static ReflectorClass ModelLoader = new ReflectorClass("net.minecraftforge.client.model.ModelLoader");
  public static ReflectorMethod ModelLoader_onRegisterItems = new ReflectorMethod(ModelLoader, "onRegisterItems");
  public static ReflectorClass Attributes = new ReflectorClass("net.minecraftforge.client.model.Attributes");
  public static ReflectorField Attributes_DEFAULT_BAKED_FORMAT = new ReflectorField(Attributes, "DEFAULT_BAKED_FORMAT");
  public static ReflectorClass BetterFoliageClient = new ReflectorClass("mods.betterfoliage.client.BetterFoliageClient");
  public static ReflectorClass IColoredBakedQuad = new ReflectorClass("net.minecraftforge.client.model.IColoredBakedQuad");
  public static ReflectorClass ForgeBiomeGenBase = new ReflectorClass(aig.class);
  public static ReflectorMethod ForgeBiomeGenBase_getWaterColorMultiplier = new ReflectorMethod(ForgeBiomeGenBase, "getWaterColorMultiplier");
  public static ReflectorClass RenderingRegistry = new ReflectorClass("net.minecraftforge.fml.client.registry.RenderingRegistry");
  public static ReflectorMethod RenderingRegistry_loadEntityRenderers = new ReflectorMethod(RenderingRegistry, "loadEntityRenderers", new Class[] { brm.class, Map.class });
  public static ReflectorClass ForgeTileEntityRendererDispatcher = new ReflectorClass(bpm.class);
  public static ReflectorMethod ForgeTileEntityRendererDispatcher_preDrawBatch = new ReflectorMethod(ForgeTileEntityRendererDispatcher, "preDrawBatch");
  public static ReflectorMethod ForgeTileEntityRendererDispatcher_drawBatch = new ReflectorMethod(ForgeTileEntityRendererDispatcher, "drawBatch");
  
  public static void callVoid(ReflectorMethod refMethod, Object... params)
  {
    try
    {
      Method m = refMethod.getTargetMethod();
      if (m == null) {
        return;
      }
      m.invoke(null, params);
    }
    catch (Throwable e)
    {
      handleException(e, null, refMethod, params);
    }
  }
  
  public static boolean callBoolean(ReflectorMethod refMethod, Object... params)
  {
    try
    {
      Method method = refMethod.getTargetMethod();
      if (method == null) {
        return false;
      }
      Boolean retVal = (Boolean)method.invoke(null, params);
      
      return retVal.booleanValue();
    }
    catch (Throwable e)
    {
      handleException(e, null, refMethod, params);
    }
    return false;
  }
  
  public static int callInt(ReflectorMethod refMethod, Object... params)
  {
    try
    {
      Method method = refMethod.getTargetMethod();
      if (method == null) {
        return 0;
      }
      Integer retVal = (Integer)method.invoke(null, params);
      
      return retVal.intValue();
    }
    catch (Throwable e)
    {
      handleException(e, null, refMethod, params);
    }
    return 0;
  }
  
  public static float callFloat(ReflectorMethod refMethod, Object... params)
  {
    try
    {
      Method method = refMethod.getTargetMethod();
      if (method == null) {
        return 0.0F;
      }
      Float retVal = (Float)method.invoke(null, params);
      
      return retVal.floatValue();
    }
    catch (Throwable e)
    {
      handleException(e, null, refMethod, params);
    }
    return 0.0F;
  }
  
  public static double callDouble(ReflectorMethod refMethod, Object... params)
  {
    try
    {
      Method method = refMethod.getTargetMethod();
      if (method == null) {
        return 0.0D;
      }
      Double retVal = (Double)method.invoke(null, params);
      
      return retVal.doubleValue();
    }
    catch (Throwable e)
    {
      handleException(e, null, refMethod, params);
    }
    return 0.0D;
  }
  
  public static String callString(ReflectorMethod refMethod, Object... params)
  {
    try
    {
      Method method = refMethod.getTargetMethod();
      if (method == null) {
        return null;
      }
      return (String)method.invoke(null, params);
    }
    catch (Throwable e)
    {
      handleException(e, null, refMethod, params);
    }
    return null;
  }
  
  public static Object call(ReflectorMethod refMethod, Object... params)
  {
    try
    {
      Method method = refMethod.getTargetMethod();
      if (method == null) {
        return null;
      }
      return method.invoke(null, params);
    }
    catch (Throwable e)
    {
      handleException(e, null, refMethod, params);
    }
    return null;
  }
  
  public static void callVoid(Object obj, ReflectorMethod refMethod, Object... params)
  {
    try
    {
      if (obj == null) {
        return;
      }
      Method method = refMethod.getTargetMethod();
      if (method == null) {
        return;
      }
      method.invoke(obj, params);
    }
    catch (Throwable e)
    {
      handleException(e, obj, refMethod, params);
    }
  }
  
  public static boolean callBoolean(Object obj, ReflectorMethod refMethod, Object... params)
  {
    try
    {
      Method method = refMethod.getTargetMethod();
      if (method == null) {
        return false;
      }
      Boolean retVal = (Boolean)method.invoke(obj, params);
      
      return retVal.booleanValue();
    }
    catch (Throwable e)
    {
      handleException(e, obj, refMethod, params);
    }
    return false;
  }
  
  public static int callInt(Object obj, ReflectorMethod refMethod, Object... params)
  {
    try
    {
      Method method = refMethod.getTargetMethod();
      if (method == null) {
        return 0;
      }
      Integer retVal = (Integer)method.invoke(obj, params);
      
      return retVal.intValue();
    }
    catch (Throwable e)
    {
      handleException(e, obj, refMethod, params);
    }
    return 0;
  }
  
  public static float callFloat(Object obj, ReflectorMethod refMethod, Object... params)
  {
    try
    {
      Method method = refMethod.getTargetMethod();
      if (method == null) {
        return 0.0F;
      }
      Float retVal = (Float)method.invoke(obj, params);
      
      return retVal.floatValue();
    }
    catch (Throwable e)
    {
      handleException(e, obj, refMethod, params);
    }
    return 0.0F;
  }
  
  public static double callDouble(Object obj, ReflectorMethod refMethod, Object... params)
  {
    try
    {
      Method method = refMethod.getTargetMethod();
      if (method == null) {
        return 0.0D;
      }
      Double retVal = (Double)method.invoke(obj, params);
      
      return retVal.doubleValue();
    }
    catch (Throwable e)
    {
      handleException(e, obj, refMethod, params);
    }
    return 0.0D;
  }
  
  public static String callString(Object obj, ReflectorMethod refMethod, Object... params)
  {
    try
    {
      Method method = refMethod.getTargetMethod();
      if (method == null) {
        return null;
      }
      return (String)method.invoke(obj, params);
    }
    catch (Throwable e)
    {
      handleException(e, obj, refMethod, params);
    }
    return null;
  }
  
  public static Object call(Object obj, ReflectorMethod refMethod, Object... params)
  {
    try
    {
      Method method = refMethod.getTargetMethod();
      if (method == null) {
        return null;
      }
      return method.invoke(obj, params);
    }
    catch (Throwable e)
    {
      handleException(e, obj, refMethod, params);
    }
    return null;
  }
  
  public static Object getFieldValue(ReflectorField refField)
  {
    return getFieldValue(null, refField);
  }
  
  public static Object getFieldValue(Object obj, ReflectorField refField)
  {
    try
    {
      Field field = refField.getTargetField();
      if (field == null) {
        return null;
      }
      return field.get(obj);
    }
    catch (Throwable e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static float getFieldValueFloat(Object obj, ReflectorField refField, float def)
  {
    Object val = getFieldValue(obj, refField);
    if (!(val instanceof Float)) {
      return def;
    }
    Float valFloat = (Float)val;
    return valFloat.floatValue();
  }
  
  public static void setFieldValue(ReflectorField refField, Object value)
  {
    setFieldValue(null, refField, value);
  }
  
  public static void setFieldValue(Object obj, ReflectorField refField, Object value)
  {
    try
    {
      Field field = refField.getTargetField();
      if (field == null) {
        return;
      }
      field.set(obj, value);
    }
    catch (Throwable e)
    {
      e.printStackTrace();
    }
  }
  
  public static boolean postForgeBusEvent(ReflectorConstructor constr, Object... params)
  {
    Object event = newInstance(constr, params);
    if (event == null) {
      return false;
    }
    return postForgeBusEvent(event);
  }
  
  public static boolean postForgeBusEvent(Object event)
  {
    if (event == null) {
      return false;
    }
    Object eventBus = getFieldValue(MinecraftForge_EVENT_BUS);
    if (eventBus == null) {
      return false;
    }
    Object ret = call(eventBus, EventBus_post, new Object[] { event });
    if (!(ret instanceof Boolean)) {
      return false;
    }
    Boolean retBool = (Boolean)ret;
    return retBool.booleanValue();
  }
  
  public static Object newInstance(ReflectorConstructor constr, Object... params)
  {
    Constructor c = constr.getTargetConstructor();
    if (c == null) {
      return null;
    }
    try
    {
      return c.newInstance(params);
    }
    catch (Throwable e)
    {
      handleException(e, constr, params);
    }
    return null;
  }
  
  public static boolean matchesTypes(Class[] pTypes, Class[] cTypes)
  {
    if (pTypes.length != cTypes.length) {
      return false;
    }
    for (int i = 0; i < cTypes.length; i++)
    {
      Class pType = pTypes[i];
      Class cType = cTypes[i];
      if (pType != cType) {
        return false;
      }
    }
    return true;
  }
  
  private static void dbgCall(boolean isStatic, String callType, ReflectorMethod refMethod, Object[] params, Object retVal)
  {
    String className = refMethod.getTargetMethod().getDeclaringClass().getName();
    String methodName = refMethod.getTargetMethod().getName();
    String staticStr = "";
    if (isStatic) {
      staticStr = " static";
    }
    Config.dbg(callType + staticStr + " " + className + "." + methodName + "(" + Config.arrayToString(params) + ") => " + retVal);
  }
  
  private static void dbgCallVoid(boolean isStatic, String callType, ReflectorMethod refMethod, Object[] params)
  {
    String className = refMethod.getTargetMethod().getDeclaringClass().getName();
    String methodName = refMethod.getTargetMethod().getName();
    String staticStr = "";
    if (isStatic) {
      staticStr = " static";
    }
    Config.dbg(callType + staticStr + " " + className + "." + methodName + "(" + Config.arrayToString(params) + ")");
  }
  
  private static void dbgFieldValue(boolean isStatic, String accessType, ReflectorField refField, Object val)
  {
    String className = refField.getTargetField().getDeclaringClass().getName();
    String fieldName = refField.getTargetField().getName();
    String staticStr = "";
    if (isStatic) {
      staticStr = " static";
    }
    Config.dbg(accessType + staticStr + " " + className + "." + fieldName + " => " + val);
  }
  
  private static void handleException(Throwable e, Object obj, ReflectorMethod refMethod, Object[] params)
  {
    if ((e instanceof InvocationTargetException))
    {
      e.printStackTrace();
      return;
    }
    if ((e instanceof IllegalArgumentException))
    {
      Config.warn("*** IllegalArgumentException ***");
      Config.warn("Method: " + refMethod.getTargetMethod());
      
      Config.warn("Object: " + obj);
      
      Config.warn("Parameter classes: " + Config.arrayToString(getClasses(params)));
      Config.warn("Parameters: " + Config.arrayToString(params));
    }
    Config.warn("*** Exception outside of method ***");
    Config.warn("Method deactivated: " + refMethod.getTargetMethod());
    refMethod.deactivate();
    
    e.printStackTrace();
  }
  
  private static void handleException(Throwable e, ReflectorConstructor refConstr, Object[] params)
  {
    if ((e instanceof InvocationTargetException))
    {
      e.printStackTrace();
      return;
    }
    if ((e instanceof IllegalArgumentException))
    {
      Config.warn("*** IllegalArgumentException ***");
      Config.warn("Constructor: " + refConstr.getTargetConstructor());
      
      Config.warn("Parameter classes: " + Config.arrayToString(getClasses(params)));
      Config.warn("Parameters: " + Config.arrayToString(params));
    }
    Config.warn("*** Exception outside of constructor ***");
    Config.warn("Constructor deactivated: " + refConstr.getTargetConstructor());
    refConstr.deactivate();
    
    e.printStackTrace();
  }
  
  private static Object[] getClasses(Object[] objs)
  {
    if (objs == null) {
      return new Class[0];
    }
    Class[] classes = new Class[objs.length];
    for (int i = 0; i < classes.length; i++)
    {
      Object obj = objs[i];
      if (obj != null) {
        classes[i] = obj.getClass();
      }
    }
    return classes;
  }
  
  public static Field getField(Class cls, Class fieldType)
  {
    try
    {
      Field[] fileds = cls.getDeclaredFields();
      for (int i = 0; i < fileds.length; i++)
      {
        Field field = fileds[i];
        if (field.getType() == fieldType)
        {
          field.setAccessible(true);
          
          return field;
        }
      }
      return null;
    }
    catch (Exception e) {}
    return null;
  }
  
  public static Field[] getFields(Class cls, Class fieldType)
  {
    List list = new ArrayList();
    try
    {
      Field[] fileds = cls.getDeclaredFields();
      for (int i = 0; i < fileds.length; i++)
      {
        Field field = fileds[i];
        if (field.getType() == fieldType)
        {
          field.setAccessible(true);
          
          list.add(field);
        }
      }
      return (Field[])list.toArray(new Field[list.size()]);
    }
    catch (Exception e) {}
    return null;
  }
}
