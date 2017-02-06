package net.minecraft.realms;

import bmz;
import bmz.a;
import bvr;
import java.nio.ByteBuffer;

public class RealmsBufferBuilder
{
  private bmz b;
  
  public RealmsBufferBuilder(bmz ☃)
  {
    this.b = ☃;
  }
  
  public RealmsBufferBuilder from(bmz ☃)
  {
    this.b = ☃;
    return this;
  }
  
  public void sortQuads(float ☃, float ☃, float ☃)
  {
    this.b.a(☃, ☃, ☃);
  }
  
  public void fixupQuadColor(int ☃)
  {
    this.b.a(☃);
  }
  
  public ByteBuffer getBuffer()
  {
    return this.b.f();
  }
  
  public void postNormal(float ☃, float ☃, float ☃)
  {
    this.b.b(☃, ☃, ☃);
  }
  
  public int getDrawMode()
  {
    return this.b.i();
  }
  
  public void offset(double ☃, double ☃, double ☃)
  {
    this.b.c(☃, ☃, ☃);
  }
  
  public void restoreState(bmz.a ☃)
  {
    this.b.a(☃);
  }
  
  public void endVertex()
  {
    this.b.d();
  }
  
  public RealmsBufferBuilder normal(float ☃, float ☃, float ☃)
  {
    return from(this.b.c(☃, ☃, ☃));
  }
  
  public void end()
  {
    this.b.e();
  }
  
  public void begin(int ☃, bvr ☃)
  {
    this.b.a(☃, ☃);
  }
  
  public RealmsBufferBuilder color(int ☃, int ☃, int ☃, int ☃)
  {
    return from(this.b.b(☃, ☃, ☃, ☃));
  }
  
  public void faceTex2(int ☃, int ☃, int ☃, int ☃)
  {
    this.b.a(☃, ☃, ☃, ☃);
  }
  
  public void postProcessFacePosition(double ☃, double ☃, double ☃)
  {
    this.b.a(☃, ☃, ☃);
  }
  
  public void fixupVertexColor(float ☃, float ☃, float ☃, int ☃)
  {
    this.b.b(☃, ☃, ☃, ☃);
  }
  
  public RealmsBufferBuilder color(float ☃, float ☃, float ☃, float ☃)
  {
    return from(this.b.a(☃, ☃, ☃, ☃));
  }
  
  public RealmsVertexFormat getVertexFormat()
  {
    return new RealmsVertexFormat(this.b.g());
  }
  
  public void faceTint(float ☃, float ☃, float ☃, int ☃)
  {
    this.b.a(☃, ☃, ☃, ☃);
  }
  
  public RealmsBufferBuilder tex2(int ☃, int ☃)
  {
    return from(this.b.a(☃, ☃));
  }
  
  public void putBulkData(int[] ☃)
  {
    this.b.a(☃);
  }
  
  public RealmsBufferBuilder tex(double ☃, double ☃)
  {
    return from(this.b.a(☃, ☃));
  }
  
  public int getVertexCount()
  {
    return this.b.h();
  }
  
  public void clear()
  {
    this.b.b();
  }
  
  public RealmsBufferBuilder vertex(double ☃, double ☃, double ☃)
  {
    return from(this.b.b(☃, ☃, ☃));
  }
  
  public void fixupQuadColor(float ☃, float ☃, float ☃)
  {
    this.b.d(☃, ☃, ☃);
  }
  
  public void noColor()
  {
    this.b.c();
  }
}
