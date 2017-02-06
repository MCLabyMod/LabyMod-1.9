package shadersmod.client;

import bvs.a;

public class SVertexAttrib
{
  public int index;
  public int count;
  public bvs.a type;
  public int offset;
  
  public SVertexAttrib(int index, int count, bvs.a type)
  {
    this.index = index;
    this.count = count;
    this.type = type;
  }
}
