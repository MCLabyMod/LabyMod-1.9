package net.minecraft.realms;

import bvr;
import bvs;
import bvs.a;
import bvs.b;

public class RealmsDefaultVertexFormat
{
  public static final RealmsVertexFormat BLOCK = new RealmsVertexFormat(new bvr());
  public static final RealmsVertexFormat BLOCK_NORMALS = new RealmsVertexFormat(new bvr());
  public static final RealmsVertexFormat ENTITY = new RealmsVertexFormat(new bvr());
  public static final RealmsVertexFormat PARTICLE = new RealmsVertexFormat(new bvr());
  public static final RealmsVertexFormat POSITION = new RealmsVertexFormat(new bvr());
  public static final RealmsVertexFormat POSITION_COLOR = new RealmsVertexFormat(new bvr());
  public static final RealmsVertexFormat POSITION_TEX = new RealmsVertexFormat(new bvr());
  public static final RealmsVertexFormat POSITION_NORMAL = new RealmsVertexFormat(new bvr());
  public static final RealmsVertexFormat POSITION_TEX_COLOR = new RealmsVertexFormat(new bvr());
  public static final RealmsVertexFormat POSITION_TEX_NORMAL = new RealmsVertexFormat(new bvr());
  public static final RealmsVertexFormat POSITION_TEX2_COLOR = new RealmsVertexFormat(new bvr());
  public static final RealmsVertexFormat POSITION_TEX_COLOR_NORMAL = new RealmsVertexFormat(new bvr());
  public static final RealmsVertexFormatElement ELEMENT_POSITION = new RealmsVertexFormatElement(new bvs(0, bvs.a.a, bvs.b.a, 3));
  public static final RealmsVertexFormatElement ELEMENT_COLOR = new RealmsVertexFormatElement(new bvs(0, bvs.a.b, bvs.b.c, 4));
  public static final RealmsVertexFormatElement ELEMENT_UV0 = new RealmsVertexFormatElement(new bvs(0, bvs.a.a, bvs.b.d, 2));
  public static final RealmsVertexFormatElement ELEMENT_UV1 = new RealmsVertexFormatElement(new bvs(1, bvs.a.e, bvs.b.d, 2));
  public static final RealmsVertexFormatElement ELEMENT_NORMAL = new RealmsVertexFormatElement(new bvs(0, bvs.a.c, bvs.b.b, 3));
  public static final RealmsVertexFormatElement ELEMENT_PADDING = new RealmsVertexFormatElement(new bvs(0, bvs.a.c, bvs.b.g, 1));
  
  static
  {
    BLOCK.addElement(ELEMENT_POSITION);
    BLOCK.addElement(ELEMENT_COLOR);
    BLOCK.addElement(ELEMENT_UV0);
    BLOCK.addElement(ELEMENT_UV1);
    
    BLOCK_NORMALS.addElement(ELEMENT_POSITION);
    BLOCK_NORMALS.addElement(ELEMENT_COLOR);
    BLOCK_NORMALS.addElement(ELEMENT_UV0);
    BLOCK_NORMALS.addElement(ELEMENT_NORMAL);
    BLOCK_NORMALS.addElement(ELEMENT_PADDING);
    
    ENTITY.addElement(ELEMENT_POSITION);
    ENTITY.addElement(ELEMENT_UV0);
    ENTITY.addElement(ELEMENT_NORMAL);
    ENTITY.addElement(ELEMENT_PADDING);
    
    PARTICLE.addElement(ELEMENT_POSITION);
    PARTICLE.addElement(ELEMENT_UV0);
    PARTICLE.addElement(ELEMENT_COLOR);
    PARTICLE.addElement(ELEMENT_UV1);
    
    POSITION.addElement(ELEMENT_POSITION);
    POSITION_COLOR.addElement(ELEMENT_POSITION);
    POSITION_COLOR.addElement(ELEMENT_COLOR);
    
    POSITION_TEX.addElement(ELEMENT_POSITION);
    POSITION_TEX.addElement(ELEMENT_UV0);
    
    POSITION_NORMAL.addElement(ELEMENT_POSITION);
    POSITION_NORMAL.addElement(ELEMENT_NORMAL);
    POSITION_NORMAL.addElement(ELEMENT_PADDING);
    
    POSITION_TEX_COLOR.addElement(ELEMENT_POSITION);
    POSITION_TEX_COLOR.addElement(ELEMENT_UV0);
    POSITION_TEX_COLOR.addElement(ELEMENT_COLOR);
    
    POSITION_TEX_NORMAL.addElement(ELEMENT_POSITION);
    POSITION_TEX_NORMAL.addElement(ELEMENT_UV0);
    POSITION_TEX_NORMAL.addElement(ELEMENT_NORMAL);
    POSITION_TEX_NORMAL.addElement(ELEMENT_PADDING);
    
    POSITION_TEX2_COLOR.addElement(ELEMENT_POSITION);
    POSITION_TEX2_COLOR.addElement(ELEMENT_UV0);
    POSITION_TEX2_COLOR.addElement(ELEMENT_UV1);
    POSITION_TEX2_COLOR.addElement(ELEMENT_COLOR);
    
    POSITION_TEX_COLOR_NORMAL.addElement(ELEMENT_POSITION);
    POSITION_TEX_COLOR_NORMAL.addElement(ELEMENT_UV0);
    POSITION_TEX_COLOR_NORMAL.addElement(ELEMENT_COLOR);
    POSITION_TEX_COLOR_NORMAL.addElement(ELEMENT_NORMAL);
    POSITION_TEX_COLOR_NORMAL.addElement(ELEMENT_PADDING);
  }
}
