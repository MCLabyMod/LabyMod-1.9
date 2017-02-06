import java.nio.FloatBuffer;

public class bcd
{
  private static final FloatBuffer a = bce.h(4);
  private static final bbj b = new bbj(0.20000000298023224D, 1.0D, -0.699999988079071D).a();
  private static final bbj c = new bbj(-0.20000000298023224D, 1.0D, 0.699999988079071D).a();
  
  public static void a()
  {
    bni.g();
    bni.b(0);
    bni.b(1);
    bni.i();
  }
  
  public static void b()
  {
    bni.f();
    bni.a(0);
    bni.a(1);
    bni.h();
    bni.a(1032, 5634);
    
    bni.a(16384, 4611, a(b.b, b.c, b.d, 0.0D));
    float ☃ = 0.6F;
    bni.a(16384, 4609, a(☃, ☃, ☃, 1.0F));
    bni.a(16384, 4608, a(0.0F, 0.0F, 0.0F, 1.0F));
    bni.a(16384, 4610, a(0.0F, 0.0F, 0.0F, 1.0F));
    
    bni.a(16385, 4611, a(c.b, c.c, c.d, 0.0D));
    bni.a(16385, 4609, a(☃, ☃, ☃, 1.0F));
    bni.a(16385, 4608, a(0.0F, 0.0F, 0.0F, 1.0F));
    bni.a(16385, 4610, a(0.0F, 0.0F, 0.0F, 1.0F));
    
    bni.j(7424);
    float ☃ = 0.4F;
    bni.a(2899, a(☃, ☃, ☃, 1.0F));
  }
  
  private static FloatBuffer a(double ☃, double ☃, double ☃, double ☃)
  {
    return a((float)☃, (float)☃, (float)☃, (float)☃);
  }
  
  public static FloatBuffer a(float ☃, float ☃, float ☃, float ☃)
  {
    a.clear();
    a.put(☃).put(☃).put(☃).put(☃);
    a.flip();
    return a;
  }
  
  public static void c()
  {
    bni.G();
    bni.b(-30.0F, 0.0F, 1.0F, 0.0F);
    bni.b(165.0F, 1.0F, 0.0F, 0.0F);
    b();
    bni.H();
  }
}
