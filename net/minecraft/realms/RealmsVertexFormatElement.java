package net.minecraft.realms;

import bvs;

public class RealmsVertexFormatElement
{
  private bvs v;
  
  public RealmsVertexFormatElement(bvs ☃)
  {
    this.v = ☃;
  }
  
  public bvs getVertexFormatElement()
  {
    return this.v;
  }
  
  public boolean isPosition()
  {
    return this.v.f();
  }
  
  public int getIndex()
  {
    return this.v.d();
  }
  
  public int getByteSize()
  {
    return this.v.e();
  }
  
  public int getCount()
  {
    return this.v.c();
  }
  
  public int hashCode()
  {
    return this.v.hashCode();
  }
  
  public boolean equals(Object ☃)
  {
    return this.v.equals(☃);
  }
  
  public String toString()
  {
    return this.v.toString();
  }
}
