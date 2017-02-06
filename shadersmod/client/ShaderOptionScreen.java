package shadersmod.client;

public class ShaderOptionScreen
  extends ShaderOption
{
  public ShaderOptionScreen(String name)
  {
    super(name, null, null, new String[] { null }, null, null);
  }
  
  public String getNameText()
  {
    return Shaders.translate("screen." + getName(), getName());
  }
  
  public String getDescriptionText()
  {
    return Shaders.translate("screen." + getName() + ".comment", null);
  }
}
