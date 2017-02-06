import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.util.glu.GLU;

public class bce
{
  public static synchronized int a(int ☃)
  {
    int ☃ = bni.t(☃);
    if (☃ == 0)
    {
      int ☃ = bni.L();
      String ☃ = "No error code reported";
      if (☃ != 0) {
        ☃ = GLU.gluErrorString(☃);
      }
      throw new IllegalStateException("glGenLists returned an ID of 0 for a count of " + ☃ + ", GL error (" + ☃ + "): " + ☃);
    }
    return ☃;
  }
  
  public static synchronized void a(int ☃, int ☃)
  {
    bni.e(☃, ☃);
  }
  
  public static synchronized void b(int ☃)
  {
    a(☃, 1);
  }
  
  public static synchronized ByteBuffer c(int ☃)
  {
    return ByteBuffer.allocateDirect(☃).order(ByteOrder.nativeOrder());
  }
  
  public static IntBuffer f(int ☃)
  {
    return c(☃ << 2).asIntBuffer();
  }
  
  public static FloatBuffer h(int ☃)
  {
    return c(☃ << 2).asFloatBuffer();
  }
}
