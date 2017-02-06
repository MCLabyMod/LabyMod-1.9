import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GLContext;

 enum bni$q$1
{
  bni$q$1()
  {
    super(paramString, paramInt, null);
  }
  
  public void a()
  {
    bni.d();
    bni.a(519, 0.0F);
    bni.g();
    GL11.glLightModel(2899, bcd.a(0.2F, 0.2F, 0.2F, 1.0F));
    for (int i = 0; i < 8; i++)
    {
      bni.b(i);
      GL11.glLight(16384 + i, 4608, bcd.a(0.0F, 0.0F, 0.0F, 1.0F));
      GL11.glLight(16384 + i, 4611, bcd.a(0.0F, 0.0F, 1.0F, 0.0F));
      if (i == 0)
      {
        GL11.glLight(16384 + i, 4609, bcd.a(1.0F, 1.0F, 1.0F, 1.0F));
        GL11.glLight(16384 + i, 4610, bcd.a(1.0F, 1.0F, 1.0F, 1.0F));
      }
      else
      {
        GL11.glLight(16384 + i, 4609, bcd.a(0.0F, 0.0F, 0.0F, 1.0F));
        GL11.glLight(16384 + i, 4610, bcd.a(0.0F, 0.0F, 0.0F, 1.0F));
      }
    }
    bni.i();
    bni.a(1032, 5634);
    bni.j();
    bni.c(513);
    bni.a(true);
    bni.l();
    bni.a(bni.r.e, bni.l.n);
    bni.a(bni.r.e, bni.l.n, bni.r.e, bni.l.n);
    GL14.glBlendEquation(32774);
    bni.p();
    GL11.glFogi(2917, 2048);
    bni.a(1.0F);
    bni.b(0.0F);
    bni.c(1.0F);
    GL11.glFog(2918, bcd.a(0.0F, 0.0F, 0.0F, 0.0F));
    if (GLContext.getCapabilities().GL_NV_fog_distance) {
      GL11.glFogi(2917, 34140);
    }
    bni.a(0.0F, 0.0F);
    bni.x();
    bni.f(5379);
    bni.b(bni.u.a);
    bni.a(bni.u.a, 9216);
    bni.a(bni.u.a, 9474, bcd.a(1.0F, 0.0F, 0.0F, 0.0F));
    bni.a(bni.u.a, 9217, bcd.a(1.0F, 0.0F, 0.0F, 0.0F));
    bni.b(bni.u.b);
    bni.a(bni.u.b, 9216);
    bni.a(bni.u.b, 9474, bcd.a(0.0F, 1.0F, 0.0F, 0.0F));
    bni.a(bni.u.b, 9217, bcd.a(0.0F, 1.0F, 0.0F, 0.0F));
    bni.b(bni.u.c);
    bni.a(bni.u.c, 9216);
    bni.a(bni.u.c, 9474, bcd.a(0.0F, 0.0F, 0.0F, 0.0F));
    bni.a(bni.u.c, 9217, bcd.a(0.0F, 0.0F, 0.0F, 0.0F));
    bni.b(bni.u.d);
    bni.a(bni.u.d, 9216);
    bni.a(bni.u.d, 9474, bcd.a(0.0F, 0.0F, 0.0F, 0.0F));
    bni.a(bni.u.d, 9217, bcd.a(0.0F, 0.0F, 0.0F, 0.0F));
    bni.g(0);
    GL11.glTexParameteri(3553, 10240, 9729);
    GL11.glTexParameteri(3553, 10241, 9986);
    GL11.glTexParameteri(3553, 10242, 10497);
    GL11.glTexParameteri(3553, 10243, 10497);
    GL11.glTexParameteri(3553, 33085, 1000);
    GL11.glTexParameteri(3553, 33083, 1000);
    GL11.glTexParameteri(3553, 33082, 64536);
    GL11.glTexParameterf(3553, 34049, 0.0F);
    GL11.glTexEnvi(8960, 8704, 8448);
    GL11.glTexEnv(8960, 8705, bcd.a(0.0F, 0.0F, 0.0F, 0.0F));
    GL11.glTexEnvi(8960, 34161, 8448);
    GL11.glTexEnvi(8960, 34162, 8448);
    GL11.glTexEnvi(8960, 34176, 5890);
    GL11.glTexEnvi(8960, 34177, 34168);
    GL11.glTexEnvi(8960, 34178, 34166);
    GL11.glTexEnvi(8960, 34184, 5890);
    GL11.glTexEnvi(8960, 34185, 34168);
    GL11.glTexEnvi(8960, 34186, 34166);
    GL11.glTexEnvi(8960, 34192, 768);
    GL11.glTexEnvi(8960, 34193, 768);
    GL11.glTexEnvi(8960, 34194, 770);
    GL11.glTexEnvi(8960, 34200, 770);
    GL11.glTexEnvi(8960, 34201, 770);
    GL11.glTexEnvi(8960, 34202, 770);
    GL11.glTexEnvf(8960, 34163, 1.0F);
    GL11.glTexEnvf(8960, 3356, 1.0F);
    bni.C();
    bni.j(7425);
    bni.E();
    bni.a(true, true, true, true);
    bni.a(1.0D);
    GL11.glLineWidth(1.0F);
    GL11.glNormal3f(0.0F, 0.0F, 1.0F);
    GL11.glPolygonMode(1028, 6914);
    GL11.glPolygonMode(1029, 6914);
  }
  
  public void b() {}
}
