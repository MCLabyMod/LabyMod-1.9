import org.lwjgl.util.vector.Matrix4f;

public class bzc
  extends Matrix4f
{
  public bzc(float[] ☃)
  {
    this.m00 = ☃[0];
    this.m01 = ☃[1];
    this.m02 = ☃[2];
    this.m03 = ☃[3];
    this.m10 = ☃[4];
    this.m11 = ☃[5];
    this.m12 = ☃[6];
    this.m13 = ☃[7];
    this.m20 = ☃[8];
    this.m21 = ☃[9];
    this.m22 = ☃[10];
    this.m23 = ☃[11];
    this.m30 = ☃[12];
    this.m31 = ☃[13];
    this.m32 = ☃[14];
    this.m33 = ☃[15];
  }
  
  public bzc()
  {
    this.m00 = (this.m01 = this.m02 = this.m03 = this.m10 = this.m11 = this.m12 = this.m13 = this.m20 = this.m21 = this.m22 = this.m23 = this.m30 = this.m31 = this.m32 = this.m33 = 0.0F);
  }
}
