import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;

public class but
{
  private static final Logger a = ;
  private int b;
  private final int c;
  private final int d;
  private final IntBuffer e;
  private final FloatBuffer f;
  private final String g;
  private boolean h;
  private final buo i;
  
  public but(String ☃, int ☃, int ☃, buo ☃)
  {
    this.g = ☃;
    this.c = ☃;
    this.d = ☃;
    this.i = ☃;
    if (☃ <= 3)
    {
      this.e = BufferUtils.createIntBuffer(☃);
      this.f = null;
    }
    else
    {
      this.e = null;
      this.f = BufferUtils.createFloatBuffer(☃);
    }
    this.b = -1;
    h();
  }
  
  private void h()
  {
    this.h = true;
    if (this.i != null) {
      this.i.d();
    }
  }
  
  public static int a(String ☃)
  {
    int ☃ = -1;
    if (☃.equals("int")) {
      ☃ = 0;
    } else if (☃.equals("float")) {
      ☃ = 4;
    } else if (☃.startsWith("matrix")) {
      if (☃.endsWith("2x2")) {
        ☃ = 8;
      } else if (☃.endsWith("3x3")) {
        ☃ = 9;
      } else if (☃.endsWith("4x4")) {
        ☃ = 10;
      }
    }
    return ☃;
  }
  
  public void b(int ☃)
  {
    this.b = ☃;
  }
  
  public String a()
  {
    return this.g;
  }
  
  public void a(float ☃)
  {
    this.f.position(0);
    this.f.put(0, ☃);
    h();
  }
  
  public void a(float ☃, float ☃)
  {
    this.f.position(0);
    this.f.put(0, ☃);
    this.f.put(1, ☃);
    h();
  }
  
  public void a(float ☃, float ☃, float ☃)
  {
    this.f.position(0);
    this.f.put(0, ☃);
    this.f.put(1, ☃);
    this.f.put(2, ☃);
    h();
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃)
  {
    this.f.position(0);
    this.f.put(☃);
    this.f.put(☃);
    this.f.put(☃);
    this.f.put(☃);
    this.f.flip();
    h();
  }
  
  public void b(float ☃, float ☃, float ☃, float ☃)
  {
    this.f.position(0);
    if (this.d >= 4) {
      this.f.put(0, ☃);
    }
    if (this.d >= 5) {
      this.f.put(1, ☃);
    }
    if (this.d >= 6) {
      this.f.put(2, ☃);
    }
    if (this.d >= 7) {
      this.f.put(3, ☃);
    }
    h();
  }
  
  public void a(int ☃, int ☃, int ☃, int ☃)
  {
    this.e.position(0);
    if (this.d >= 0) {
      this.e.put(0, ☃);
    }
    if (this.d >= 1) {
      this.e.put(1, ☃);
    }
    if (this.d >= 2) {
      this.e.put(2, ☃);
    }
    if (this.d >= 3) {
      this.e.put(3, ☃);
    }
    h();
  }
  
  public void a(float[] ☃)
  {
    if (☃.length < this.c)
    {
      a.warn("Uniform.set called with a too-small value array (expected " + this.c + ", got " + ☃.length + "). Ignoring.");
      return;
    }
    this.f.position(0);
    this.f.put(☃);
    this.f.position(0);
    h();
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    this.f.position(0);
    this.f.put(0, ☃);
    this.f.put(1, ☃);
    this.f.put(2, ☃);
    this.f.put(3, ☃);
    this.f.put(4, ☃);
    this.f.put(5, ☃);
    this.f.put(6, ☃);
    this.f.put(7, ☃);
    this.f.put(8, ☃);
    this.f.put(9, ☃);
    this.f.put(10, ☃);
    this.f.put(11, ☃);
    this.f.put(12, ☃);
    this.f.put(13, ☃);
    this.f.put(14, ☃);
    this.f.put(15, ☃);
    h();
  }
  
  public void a(Matrix4f ☃)
  {
    a(☃.m00, ☃.m01, ☃.m02, ☃.m03, ☃.m10, ☃.m11, ☃.m12, ☃.m13, ☃.m20, ☃.m21, ☃.m22, ☃.m23, ☃.m30, ☃.m31, ☃.m32, ☃.m33);
  }
  
  public void b()
  {
    if (!this.h) {}
    this.h = false;
    if (this.d <= 3)
    {
      i();
    }
    else if (this.d <= 7)
    {
      j();
    }
    else if (this.d <= 10)
    {
      k();
    }
    else
    {
      a.warn("Uniform.upload called, but type value (" + this.d + ") is not " + "a valid type. Ignoring.");
      return;
    }
  }
  
  private void i()
  {
    switch (this.d)
    {
    case 0: 
      bzg.a(this.b, this.e);
      break;
    case 1: 
      bzg.b(this.b, this.e);
      break;
    case 2: 
      bzg.c(this.b, this.e);
      break;
    case 3: 
      bzg.d(this.b, this.e);
      break;
    default: 
      a.warn("Uniform.upload called, but count value (" + this.c + ") is " + " not in the range of 1 to 4. Ignoring.");
    }
  }
  
  private void j()
  {
    switch (this.d)
    {
    case 4: 
      bzg.a(this.b, this.f);
      break;
    case 5: 
      bzg.b(this.b, this.f);
      break;
    case 6: 
      bzg.c(this.b, this.f);
      break;
    case 7: 
      bzg.d(this.b, this.f);
      break;
    default: 
      a.warn("Uniform.upload called, but count value (" + this.c + ") is " + "not in the range of 1 to 4. Ignoring.");
    }
  }
  
  private void k()
  {
    switch (this.d)
    {
    case 8: 
      bzg.a(this.b, true, this.f);
      break;
    case 9: 
      bzg.b(this.b, true, this.f);
      break;
    case 10: 
      bzg.c(this.b, true, this.f);
    }
  }
}
