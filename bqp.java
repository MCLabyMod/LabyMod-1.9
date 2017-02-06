public class bqp
{
  public float[][] a = new float[6][4];
  public float[] b = new float[16];
  public float[] c = new float[16];
  public float[] d = new float[16];
  
  private float dot(float[] p_178624_1_, float p_178624_2_, float p_178624_4_, float p_178624_6_)
  {
    return p_178624_1_[0] * p_178624_2_ + p_178624_1_[1] * p_178624_4_ + p_178624_1_[2] * p_178624_6_ + p_178624_1_[3];
  }
  
  public boolean b(double p_78553_1_, double p_78553_3_, double p_78553_5_, double p_78553_7_, double p_78553_9_, double p_78553_11_)
  {
    float minXf = (float)p_78553_1_;
    float minYf = (float)p_78553_3_;
    float minZf = (float)p_78553_5_;
    float maxXf = (float)p_78553_7_;
    float maxYf = (float)p_78553_9_;
    float maxZf = (float)p_78553_11_;
    for (int var13 = 0; var13 < 6; var13++)
    {
      float[] var14 = this.a[var13];
      if ((dot(var14, minXf, minYf, minZf) <= 0.0F) && (dot(var14, maxXf, minYf, minZf) <= 0.0F) && (dot(var14, minXf, maxYf, minZf) <= 0.0F) && (dot(var14, maxXf, maxYf, minZf) <= 0.0F) && (dot(var14, minXf, minYf, maxZf) <= 0.0F) && (dot(var14, maxXf, minYf, maxZf) <= 0.0F) && (dot(var14, minXf, maxYf, maxZf) <= 0.0F) && (dot(var14, maxXf, maxYf, maxZf) <= 0.0F)) {
        return false;
      }
    }
    return true;
  }
}
