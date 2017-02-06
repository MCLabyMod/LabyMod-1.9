import java.util.Map;

public class ReflectorForge
{
  public static void FMLClientHandler_trackBrokenTexture(kk loc, String message)
  {
    if (Reflector.FMLClientHandler_trackBrokenTexture.exists()) {
      return;
    }
    Object instance = Reflector.call(Reflector.FMLClientHandler_instance, new Object[0]);
    
    Reflector.call(instance, Reflector.FMLClientHandler_trackBrokenTexture, new Object[] { loc, message });
  }
  
  public static void FMLClientHandler_trackMissingTexture(kk loc)
  {
    if (Reflector.FMLClientHandler_trackMissingTexture.exists()) {
      return;
    }
    Object instance = Reflector.call(Reflector.FMLClientHandler_instance, new Object[0]);
    
    Reflector.call(instance, Reflector.FMLClientHandler_trackMissingTexture, new Object[] { loc });
  }
  
  public static void putLaunchBlackboard(String key, Object value)
  {
    Map blackboard = (Map)Reflector.getFieldValue(Reflector.Launch_blackboard);
    if (blackboard == null) {
      return;
    }
    blackboard.put(key, value);
  }
  
  public static boolean renderFirstPersonHand(bno renderGlobal, float partialTicks, int pass)
  {
    if (!Reflector.ForgeHooksClient_renderFirstPersonHand.exists()) {
      return false;
    }
    if (Config.isShaders()) {
      return false;
    }
    return Reflector.callBoolean(Reflector.ForgeHooksClient_renderFirstPersonHand, new Object[] { renderGlobal, Float.valueOf(partialTicks), Integer.valueOf(pass) });
  }
}
