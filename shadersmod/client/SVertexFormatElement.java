package shadersmod.client;

import bvs;
import bvs.a;
import bvs.b;

public class SVertexFormatElement
  extends bvs
{
  int sUsage;
  
  public SVertexFormatElement(int sUsage, bvs.a type, int count)
  {
    super(0, type, bvs.b.g, count);
    this.sUsage = sUsage;
  }
}
