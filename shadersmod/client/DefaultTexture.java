package shadersmod.client;

import buw;
import bwg;

public class DefaultTexture
  extends buw
{
  public DefaultTexture()
  {
    a(null);
  }
  
  public void a(bwg resourcemanager)
  {
    int[] aint = ShadersTex.createAIntImage(1, -1);
    ShadersTex.setupTexture(getMultiTexID(), aint, 1, 1, false, false);
  }
}
