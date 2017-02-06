import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.util.glu.GLU;

public class bca
{
  private static final IntBuffer a = bce.f(16);
  private static final FloatBuffer b = bce.h(16);
  private static final FloatBuffer c = bce.h(16);
  private static final FloatBuffer d = bce.h(3);
  private static bbj e = new bbj(0.0D, 0.0D, 0.0D);
  private static float f;
  private static float g;
  private static float h;
  private static float i;
  private static float j;
  
  public static void a(zj ☃, boolean ☃)
  {
    bni.c(2982, b);
    bni.c(2983, c);
    bni.a(2978, a);
    
    float ☃ = (a.get(0) + a.get(2)) / 2;
    float ☃ = (a.get(1) + a.get(3)) / 2;
    GLU.gluUnProject(☃, ☃, 0.0F, b, c, a, d);
    
    e = new bbj(d.get(0), d.get(1), d.get(2));
    
    int ☃ = ☃ ? 1 : 0;
    
    float ☃ = ☃.w;
    float ☃ = ☃.v;
    
    f = on.b(☃ * 0.017453292F) * (1 - ☃ * 2);
    h = on.a(☃ * 0.017453292F) * (1 - ☃ * 2);
    
    i = -h * on.a(☃ * 0.017453292F) * (1 - ☃ * 2);
    j = f * on.a(☃ * 0.017453292F) * (1 - ☃ * 2);
    g = on.b(☃ * 0.017453292F);
  }
  
  public static bbj a(rr ☃, double ☃)
  {
    double ☃ = ☃.m + (☃.p - ☃.m) * ☃;
    double ☃ = ☃.n + (☃.q - ☃.n) * ☃;
    double ☃ = ☃.o + (☃.r - ☃.o) * ☃;
    
    double ☃ = ☃ + e.b;
    double ☃ = ☃ + e.c;
    double ☃ = ☃ + e.d;
    
    return new bbj(☃, ☃, ☃);
  }
  
  public static arc a(aht ☃, rr ☃, float ☃)
  {
    bbj ☃ = a(☃, ☃);
    cj ☃ = new cj(☃);
    arc ☃ = ☃.o(☃);
    if (☃.a().d())
    {
      float ☃ = 0.0F;
      if ((☃.t() instanceof amo)) {
        ☃ = amo.e(((Integer)☃.c(amo.b)).intValue()) - 0.11111111F;
      }
      float ☃ = ☃.q() + 1 - ☃;
      if (☃.c >= ☃) {
        ☃ = ☃.o(☃.a());
      }
    }
    return ☃;
  }
  
  public static bbj a()
  {
    return e;
  }
  
  public static float b()
  {
    return f;
  }
  
  public static float c()
  {
    return g;
  }
  
  public static float d()
  {
    return h;
  }
  
  public static float e()
  {
    return i;
  }
  
  public static float f()
  {
    return j;
  }
}
