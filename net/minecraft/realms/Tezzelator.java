package net.minecraft.realms;

import bmz;
import bnu;

public class Tezzelator
{
  public static bnu t = ;
  public static final Tezzelator instance = new Tezzelator();
  
  public void end()
  {
    t.b();
  }
  
  public Tezzelator vertex(double ☃, double ☃, double ☃)
  {
    t.c().b(☃, ☃, ☃);
    return this;
  }
  
  public void color(float ☃, float ☃, float ☃, float ☃)
  {
    t.c().a(☃, ☃, ☃, ☃);
  }
  
  public void tex2(short ☃, short ☃)
  {
    t.c().a(☃, ☃);
  }
  
  public void normal(float ☃, float ☃, float ☃)
  {
    t.c().c(☃, ☃, ☃);
  }
  
  public void begin(int ☃, RealmsVertexFormat ☃)
  {
    t.c().a(☃, ☃.getVertexFormat());
  }
  
  public void endVertex()
  {
    t.c().d();
  }
  
  public void offset(double ☃, double ☃, double ☃)
  {
    t.c().c(☃, ☃, ☃);
  }
  
  public RealmsBufferBuilder color(int ☃, int ☃, int ☃, int ☃)
  {
    return new RealmsBufferBuilder(t.c().b(☃, ☃, ☃, ☃));
  }
  
  public Tezzelator tex(double ☃, double ☃)
  {
    t.c().a(☃, ☃);
    return this;
  }
}
