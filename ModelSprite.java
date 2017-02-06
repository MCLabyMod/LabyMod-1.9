import org.lwjgl.opengl.GL11;

public class ModelSprite
{
  private bkm modelRenderer = null;
  private int textureOffsetX = 0;
  private int textureOffsetY = 0;
  private float posX = 0.0F;
  private float posY = 0.0F;
  private float posZ = 0.0F;
  private int sizeX = 0;
  private int sizeY = 0;
  private int sizeZ = 0;
  private float sizeAdd = 0.0F;
  private float minU = 0.0F;
  private float minV = 0.0F;
  private float maxU = 0.0F;
  private float maxV = 0.0F;
  
  public ModelSprite(bkm modelRenderer, int textureOffsetX, int textureOffsetY, float posX, float posY, float posZ, int sizeX, int sizeY, int sizeZ, float sizeAdd)
  {
    this.modelRenderer = modelRenderer;
    this.textureOffsetX = textureOffsetX;
    this.textureOffsetY = textureOffsetY;
    this.posX = posX;
    this.posY = posY;
    this.posZ = posZ;
    this.sizeX = sizeX;
    this.sizeY = sizeY;
    this.sizeZ = sizeZ;
    this.sizeAdd = sizeAdd;
    
    this.minU = (textureOffsetX / modelRenderer.a);
    this.minV = (textureOffsetY / modelRenderer.b);
    this.maxU = ((textureOffsetX + sizeX) / modelRenderer.a);
    this.maxV = ((textureOffsetY + sizeY) / modelRenderer.b);
  }
  
  public void render(bnu tessellator, float scale)
  {
    bni.c(this.posX * scale, this.posY * scale, this.posZ * scale);
    
    float rMinU = this.minU;
    float rMaxU = this.maxU;
    float rMinV = this.minV;
    float rMaxV = this.maxV;
    if (this.modelRenderer.i)
    {
      rMinU = this.maxU;
      rMaxU = this.minU;
    }
    if (this.modelRenderer.mirrorV)
    {
      rMinV = this.maxV;
      rMaxV = this.minV;
    }
    renderItemIn2D(tessellator, rMinU, rMinV, rMaxU, rMaxV, this.sizeX, this.sizeY, scale * this.sizeZ, this.modelRenderer.a, this.modelRenderer.b);
    
    bni.c(-this.posX * scale, -this.posY * scale, -this.posZ * scale);
  }
  
  public static void renderItemIn2D(bnu tess, float minU, float minV, float maxU, float maxV, int sizeX, int sizeY, float width, float texWidth, float texHeight)
  {
    if (width < 6.25E-4F) {
      width = 6.25E-4F;
    }
    float dU = maxU - minU;
    float dV = maxV - minV;
    
    double dimX = on.e(dU) * (texWidth / 16.0F);
    double dimY = on.e(dV) * (texHeight / 16.0F);
    
    bmz tessellator = tess.c();
    
    GL11.glNormal3f(0.0F, 0.0F, -1.0F);
    tessellator.a(7, bvp.f);
    tessellator.b(0.0D, 0.0D, 0.0D).a(minU, minV).d();
    tessellator.b(dimX, 0.0D, 0.0D).a(maxU, minV).d();
    tessellator.b(dimX, dimY, 0.0D).a(maxU, maxV).d();
    tessellator.b(0.0D, dimY, 0.0D).a(minU, maxV).d();
    tess.b();
    
    GL11.glNormal3f(0.0F, 0.0F, 1.0F);
    tessellator.a(7, bvp.f);
    tessellator.b(0.0D, dimY, width).a(minU, maxV).d();
    tessellator.b(dimX, dimY, width).a(maxU, maxV).d();
    tessellator.b(dimX, 0.0D, width).a(maxU, minV).d();
    tessellator.b(0.0D, 0.0D, width).a(minU, minV).d();
    tess.b();
    
    float var8 = 0.5F * dU / sizeX;
    float var9 = 0.5F * dV / sizeY;
    GL11.glNormal3f(-1.0F, 0.0F, 0.0F);
    tessellator.a(7, bvp.f);
    for (int var10 = 0; var10 < sizeX; var10++)
    {
      float var11 = var10 / sizeX;
      float var12 = minU + dU * var11 + var8;
      tessellator.b(var11 * dimX, 0.0D, width).a(var12, minV).d();
      tessellator.b(var11 * dimX, 0.0D, 0.0D).a(var12, minV).d();
      tessellator.b(var11 * dimX, dimY, 0.0D).a(var12, maxV).d();
      tessellator.b(var11 * dimX, dimY, width).a(var12, maxV).d();
    }
    tess.b();
    
    GL11.glNormal3f(1.0F, 0.0F, 0.0F);
    tessellator.a(7, bvp.f);
    for (var10 = 0; var10 < sizeX; var10++)
    {
      float var11 = var10 / sizeX;
      float var12 = minU + dU * var11 + var8;
      float var13 = var11 + 1.0F / sizeX;
      tessellator.b(var13 * dimX, dimY, width).a(var12, maxV).d();
      tessellator.b(var13 * dimX, dimY, 0.0D).a(var12, maxV).d();
      tessellator.b(var13 * dimX, 0.0D, 0.0D).a(var12, minV).d();
      tessellator.b(var13 * dimX, 0.0D, width).a(var12, minV).d();
    }
    tess.b();
    
    GL11.glNormal3f(0.0F, 1.0F, 0.0F);
    tessellator.a(7, bvp.f);
    for (var10 = 0; var10 < sizeY; var10++)
    {
      float var11 = var10 / sizeY;
      float var12 = minV + dV * var11 + var9;
      float var13 = var11 + 1.0F / sizeY;
      tessellator.b(0.0D, var13 * dimY, 0.0D).a(minU, var12).d();
      tessellator.b(dimX, var13 * dimY, 0.0D).a(maxU, var12).d();
      tessellator.b(dimX, var13 * dimY, width).a(maxU, var12).d();
      tessellator.b(0.0D, var13 * dimY, width).a(minU, var12).d();
    }
    tess.b();
    
    GL11.glNormal3f(0.0F, -1.0F, 0.0F);
    tessellator.a(7, bvp.f);
    for (var10 = 0; var10 < sizeY; var10++)
    {
      float var11 = var10 / sizeY;
      float var12 = minV + dV * var11 + var9;
      tessellator.b(dimX, var11 * dimY, 0.0D).a(maxU, var12).d();
      tessellator.b(0.0D, var11 * dimY, 0.0D).a(minU, var12).d();
      tessellator.b(0.0D, var11 * dimY, width).a(minU, var12).d();
      tessellator.b(dimX, var11 * dimY, width).a(maxU, var12).d();
    }
    tess.b();
  }
}
