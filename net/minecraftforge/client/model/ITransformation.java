package net.minecraftforge.client.model;

import bzc;
import cq;

public abstract interface ITransformation
{
  public abstract bzc getMatrix();
  
  public abstract cq rotate(cq paramcq);
  
  public abstract int rotate(cq paramcq, int paramInt);
}
