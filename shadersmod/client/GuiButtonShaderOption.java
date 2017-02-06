package shadersmod.client;

import bcz;

public class GuiButtonShaderOption
  extends bcz
{
  private ShaderOption shaderOption = null;
  
  public GuiButtonShaderOption(int buttonId, int x, int y, int widthIn, int heightIn, ShaderOption shaderOption, String text)
  {
    super(buttonId, x, y, widthIn, heightIn, text);
    this.shaderOption = shaderOption;
  }
  
  public ShaderOption getShaderOption()
  {
    return this.shaderOption;
  }
}
