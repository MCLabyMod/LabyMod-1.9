package shadersmod.client;

import bqp;
import on;

public class ClippingHelperShadow
  extends bqp
{
  private static ClippingHelperShadow instance = new ClippingHelperShadow();
  float[] frustumTest = new float[6];
  float[][] shadowClipPlanes = new float[10][4];
  int shadowClipPlaneCount;
  float[] matInvMP = new float[16];
  float[] vecIntersection = new float[4];
  
  public boolean b(double x1, double y1, double z1, double x2, double y2, double z2)
  {
    for (int index = 0; index < this.shadowClipPlaneCount; index++)
    {
      float[] plane = this.shadowClipPlanes[index];
      if ((dot4(plane, x1, y1, z1) <= 0.0D) && (dot4(plane, x2, y1, z1) <= 0.0D) && (dot4(plane, x1, y2, z1) <= 0.0D) && (dot4(plane, x2, y2, z1) <= 0.0D) && (dot4(plane, x1, y1, z2) <= 0.0D) && (dot4(plane, x2, y1, z2) <= 0.0D) && (dot4(plane, x1, y2, z2) <= 0.0D) && (dot4(plane, x2, y2, z2) <= 0.0D)) {
        return false;
      }
    }
    return true;
  }
  
  private double dot4(float[] plane, double x, double y, double z)
  {
    return plane[0] * x + plane[1] * y + plane[2] * z + plane[3];
  }
  
  private double dot3(float[] vecA, float[] vecB)
  {
    return vecA[0] * vecB[0] + vecA[1] * vecB[1] + vecA[2] * vecB[2];
  }
  
  public static bqp getInstance()
  {
    instance.init();
    return instance;
  }
  
  private void normalizePlane(float[] plane)
  {
    float length = on.c(plane[0] * plane[0] + plane[1] * plane[1] + plane[2] * plane[2]);
    plane[0] /= length;
    plane[1] /= length;
    plane[2] /= length;
    plane[3] /= length;
  }
  
  private void normalize3(float[] plane)
  {
    float length = on.c(plane[0] * plane[0] + plane[1] * plane[1] + plane[2] * plane[2]);
    if (length == 0.0F) {
      length = 1.0F;
    }
    plane[0] /= length;
    plane[1] /= length;
    plane[2] /= length;
  }
  
  private void assignPlane(float[] plane, float a, float b, float c, float d)
  {
    float length = (float)Math.sqrt(a * a + b * b + c * c);
    plane[0] = (a / length);
    plane[1] = (b / length);
    plane[2] = (c / length);
    plane[3] = (d / length);
  }
  
  private void copyPlane(float[] dst, float[] src)
  {
    dst[0] = src[0];
    dst[1] = src[1];
    dst[2] = src[2];
    dst[3] = src[3];
  }
  
  private void cross3(float[] out, float[] a, float[] b)
  {
    out[0] = (a[1] * b[2] - a[2] * b[1]);
    out[1] = (a[2] * b[0] - a[0] * b[2]);
    out[2] = (a[0] * b[1] - a[1] * b[0]);
  }
  
  private void addShadowClipPlane(float[] plane)
  {
    copyPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], plane);
  }
  
  private float length(float x, float y, float z)
  {
    return (float)Math.sqrt(x * x + y * y + z * z);
  }
  
  private float distance(float x1, float y1, float z1, float x2, float y2, float z2)
  {
    return length(x1 - x2, y1 - y2, z1 - z2);
  }
  
  private void makeShadowPlane(float[] shadowPlane, float[] positivePlane, float[] negativePlane, float[] vecSun)
  {
    cross3(this.vecIntersection, positivePlane, negativePlane);
    cross3(shadowPlane, this.vecIntersection, vecSun);
    normalize3(shadowPlane);
    
    float dotPN = (float)dot3(positivePlane, negativePlane);
    float dotNP = dotPN;
    float dotSN = (float)dot3(shadowPlane, negativePlane);
    float disSN = distance(shadowPlane[0], shadowPlane[1], shadowPlane[2], negativePlane[0] * dotSN, negativePlane[1] * dotSN, negativePlane[2] * dotSN);
    float disPN = distance(positivePlane[0], positivePlane[1], positivePlane[2], negativePlane[0] * dotPN, negativePlane[1] * dotPN, negativePlane[2] * dotPN);
    float k1 = disSN / disPN;
    float dotSP = (float)dot3(shadowPlane, positivePlane);
    float disSP = distance(shadowPlane[0], shadowPlane[1], shadowPlane[2], positivePlane[0] * dotSP, positivePlane[1] * dotSP, positivePlane[2] * dotSP);
    float disNP = distance(negativePlane[0], negativePlane[1], negativePlane[2], positivePlane[0] * dotNP, positivePlane[1] * dotNP, positivePlane[2] * dotNP);
    float k2 = disSP / disNP;
    
    shadowPlane[3] = (positivePlane[3] * k1 + negativePlane[3] * k2);
  }
  
  public void init()
  {
    float[] matPrj = this.b;
    float[] matMdv = this.c;
    float[] matMP = this.d;
    System.arraycopy(Shaders.faProjection, 0, matPrj, 0, 16);
    System.arraycopy(Shaders.faModelView, 0, matMdv, 0, 16);
    
    SMath.multiplyMat4xMat4(matMP, matMdv, matPrj);
    
    assignPlane(this.a[0], matMP[3] - matMP[0], matMP[7] - matMP[4], matMP[11] - matMP[8], matMP[15] - matMP[12]);
    
    assignPlane(this.a[1], matMP[3] + matMP[0], matMP[7] + matMP[4], matMP[11] + matMP[8], matMP[15] + matMP[12]);
    
    assignPlane(this.a[2], matMP[3] + matMP[1], matMP[7] + matMP[5], matMP[11] + matMP[9], matMP[15] + matMP[13]);
    
    assignPlane(this.a[3], matMP[3] - matMP[1], matMP[7] - matMP[5], matMP[11] - matMP[9], matMP[15] - matMP[13]);
    
    assignPlane(this.a[4], matMP[3] - matMP[2], matMP[7] - matMP[6], matMP[11] - matMP[10], matMP[15] - matMP[14]);
    
    assignPlane(this.a[5], matMP[3] + matMP[2], matMP[7] + matMP[6], matMP[11] + matMP[10], matMP[15] + matMP[14]);
    
    float[] vecSun = Shaders.shadowLightPositionVector;
    float test0 = (float)dot3(this.a[0], vecSun);
    float test1 = (float)dot3(this.a[1], vecSun);
    float test2 = (float)dot3(this.a[2], vecSun);
    float test3 = (float)dot3(this.a[3], vecSun);
    float test4 = (float)dot3(this.a[4], vecSun);
    float test5 = (float)dot3(this.a[5], vecSun);
    
    this.shadowClipPlaneCount = 0;
    if (test0 >= 0.0F)
    {
      copyPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[0]);
      if (test0 > 0.0F)
      {
        if (test2 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[0], this.a[2], vecSun);
        }
        if (test3 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[0], this.a[3], vecSun);
        }
        if (test4 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[0], this.a[4], vecSun);
        }
        if (test5 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[0], this.a[5], vecSun);
        }
      }
    }
    if (test1 >= 0.0F)
    {
      copyPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[1]);
      if (test1 > 0.0F)
      {
        if (test2 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[1], this.a[2], vecSun);
        }
        if (test3 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[1], this.a[3], vecSun);
        }
        if (test4 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[1], this.a[4], vecSun);
        }
        if (test5 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[1], this.a[5], vecSun);
        }
      }
    }
    if (test2 >= 0.0F)
    {
      copyPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[2]);
      if (test2 > 0.0F)
      {
        if (test0 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[2], this.a[0], vecSun);
        }
        if (test1 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[2], this.a[1], vecSun);
        }
        if (test4 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[2], this.a[4], vecSun);
        }
        if (test5 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[2], this.a[5], vecSun);
        }
      }
    }
    if (test3 >= 0.0F)
    {
      copyPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[3]);
      if (test3 > 0.0F)
      {
        if (test0 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[3], this.a[0], vecSun);
        }
        if (test1 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[3], this.a[1], vecSun);
        }
        if (test4 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[3], this.a[4], vecSun);
        }
        if (test5 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[3], this.a[5], vecSun);
        }
      }
    }
    if (test4 >= 0.0F)
    {
      copyPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[4]);
      if (test4 > 0.0F)
      {
        if (test0 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[4], this.a[0], vecSun);
        }
        if (test1 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[4], this.a[1], vecSun);
        }
        if (test2 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[4], this.a[2], vecSun);
        }
        if (test3 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[4], this.a[3], vecSun);
        }
      }
    }
    if (test5 >= 0.0F)
    {
      copyPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[5]);
      if (test5 > 0.0F)
      {
        if (test0 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[5], this.a[0], vecSun);
        }
        if (test1 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[5], this.a[1], vecSun);
        }
        if (test2 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[5], this.a[2], vecSun);
        }
        if (test3 < 0.0F) {
          makeShadowPlane(this.shadowClipPlanes[(this.shadowClipPlaneCount++)], this.a[5], this.a[3], vecSun);
        }
      }
    }
  }
}
