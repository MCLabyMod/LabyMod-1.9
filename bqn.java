import java.nio.Buffer;
import java.nio.FloatBuffer;

public class bqn
  extends bqp
{
  private static bqn e = new bqn();
  
  public static bqp a()
  {
    e.b();
    return e;
  }
  
  private void a(float[] ☃)
  {
    float ☃ = on.c(☃[0] * ☃[0] + ☃[1] * ☃[1] + ☃[2] * ☃[2]);
    
    ☃[0] /= ☃;
    ☃[1] /= ☃;
    ☃[2] /= ☃;
    ☃[3] /= ☃;
  }
  
  private FloatBuffer f = bce.h(16);
  private FloatBuffer g = bce.h(16);
  private FloatBuffer h = bce.h(16);
  
  public void b()
  {
    this.f.clear();
    this.g.clear();
    this.h.clear();
    
    bni.c(2983, this.f);
    
    bni.c(2982, this.g);
    
    float[] ☃ = this.b;
    float[] ☃ = this.c;
    
    this.f.flip().limit(16);
    this.f.get(☃);
    this.g.flip().limit(16);
    this.g.get(☃);
    
    this.d[0] = (☃[0] * ☃[0] + ☃[1] * ☃[4] + ☃[2] * ☃[8] + ☃[3] * ☃[12]);
    this.d[1] = (☃[0] * ☃[1] + ☃[1] * ☃[5] + ☃[2] * ☃[9] + ☃[3] * ☃[13]);
    this.d[2] = (☃[0] * ☃[2] + ☃[1] * ☃[6] + ☃[2] * ☃[10] + ☃[3] * ☃[14]);
    this.d[3] = (☃[0] * ☃[3] + ☃[1] * ☃[7] + ☃[2] * ☃[11] + ☃[3] * ☃[15]);
    
    this.d[4] = (☃[4] * ☃[0] + ☃[5] * ☃[4] + ☃[6] * ☃[8] + ☃[7] * ☃[12]);
    this.d[5] = (☃[4] * ☃[1] + ☃[5] * ☃[5] + ☃[6] * ☃[9] + ☃[7] * ☃[13]);
    this.d[6] = (☃[4] * ☃[2] + ☃[5] * ☃[6] + ☃[6] * ☃[10] + ☃[7] * ☃[14]);
    this.d[7] = (☃[4] * ☃[3] + ☃[5] * ☃[7] + ☃[6] * ☃[11] + ☃[7] * ☃[15]);
    
    this.d[8] = (☃[8] * ☃[0] + ☃[9] * ☃[4] + ☃[10] * ☃[8] + ☃[11] * ☃[12]);
    this.d[9] = (☃[8] * ☃[1] + ☃[9] * ☃[5] + ☃[10] * ☃[9] + ☃[11] * ☃[13]);
    this.d[10] = (☃[8] * ☃[2] + ☃[9] * ☃[6] + ☃[10] * ☃[10] + ☃[11] * ☃[14]);
    this.d[11] = (☃[8] * ☃[3] + ☃[9] * ☃[7] + ☃[10] * ☃[11] + ☃[11] * ☃[15]);
    
    this.d[12] = (☃[12] * ☃[0] + ☃[13] * ☃[4] + ☃[14] * ☃[8] + ☃[15] * ☃[12]);
    this.d[13] = (☃[12] * ☃[1] + ☃[13] * ☃[5] + ☃[14] * ☃[9] + ☃[15] * ☃[13]);
    this.d[14] = (☃[12] * ☃[2] + ☃[13] * ☃[6] + ☃[14] * ☃[10] + ☃[15] * ☃[14]);
    this.d[15] = (☃[12] * ☃[3] + ☃[13] * ☃[7] + ☃[14] * ☃[11] + ☃[15] * ☃[15]);
    
    float[] ☃ = this.a[0];
    ☃[0] = (this.d[3] - this.d[0]);
    ☃[1] = (this.d[7] - this.d[4]);
    ☃[2] = (this.d[11] - this.d[8]);
    ☃[3] = (this.d[15] - this.d[12]);
    
    a(☃);
    
    float[] ☃ = this.a[1];
    ☃[0] = (this.d[3] + this.d[0]);
    ☃[1] = (this.d[7] + this.d[4]);
    ☃[2] = (this.d[11] + this.d[8]);
    ☃[3] = (this.d[15] + this.d[12]);
    
    a(☃);
    
    float[] ☃ = this.a[2];
    ☃[0] = (this.d[3] + this.d[1]);
    ☃[1] = (this.d[7] + this.d[5]);
    ☃[2] = (this.d[11] + this.d[9]);
    ☃[3] = (this.d[15] + this.d[13]);
    
    a(☃);
    
    float[] ☃ = this.a[3];
    ☃[0] = (this.d[3] - this.d[1]);
    ☃[1] = (this.d[7] - this.d[5]);
    ☃[2] = (this.d[11] - this.d[9]);
    ☃[3] = (this.d[15] - this.d[13]);
    
    a(☃);
    
    float[] ☃ = this.a[4];
    ☃[0] = (this.d[3] - this.d[2]);
    ☃[1] = (this.d[7] - this.d[6]);
    ☃[2] = (this.d[11] - this.d[10]);
    ☃[3] = (this.d[15] - this.d[14]);
    
    a(☃);
    
    float[] ☃ = this.a[5];
    ☃[0] = (this.d[3] + this.d[2]);
    ☃[1] = (this.d[7] + this.d[6]);
    ☃[2] = (this.d[11] + this.d[10]);
    ☃[3] = (this.d[15] + this.d[14]);
    
    a(☃);
  }
}
