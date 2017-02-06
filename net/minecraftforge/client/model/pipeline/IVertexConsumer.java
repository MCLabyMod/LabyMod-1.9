package net.minecraftforge.client.model.pipeline;

import bvr;
import cq;

public abstract interface IVertexConsumer
{
  public abstract bvr getVertexFormat();
  
  public abstract void setQuadTint(int paramInt);
  
  public abstract void setQuadOrientation(cq paramcq);
  
  public abstract void setQuadColored();
  
  public abstract void put(int paramInt, float... paramVarArgs);
}
