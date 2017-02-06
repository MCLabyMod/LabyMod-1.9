public class bnt
{
  public int a;
  public int b;
  public int c;
  public int d;
  public boolean e;
  public int f;
  public int g;
  public int h;
  public float[] i;
  public int j;
  
  public bnt(int ☃, int ☃, boolean ☃)
  {
    this.e = ☃;
    
    this.f = -1;
    this.g = -1;
    this.h = -1;
    
    this.i = new float[4];
    this.i[0] = 1.0F;
    this.i[1] = 1.0F;
    this.i[2] = 1.0F;
    this.i[3] = 0.0F;
    
    a(☃, ☃);
  }
  
  public void a(int ☃, int ☃)
  {
    if (!bzg.j())
    {
      this.c = ☃;
      this.d = ☃;
      return;
    }
    bni.k();
    if (this.f >= 0) {
      a();
    }
    b(☃, ☃);
    b();
    
    bzg.h(bzg.c, 0);
  }
  
  public void a()
  {
    if (!bzg.j()) {
      return;
    }
    d();
    e();
    if (this.h > -1)
    {
      bzg.h(this.h);
      this.h = -1;
    }
    if (this.g > -1)
    {
      bvk.a(this.g);
      this.g = -1;
    }
    if (this.f > -1)
    {
      bzg.h(bzg.c, 0);
      bzg.i(this.f);
      this.f = -1;
    }
  }
  
  public void b(int ☃, int ☃)
  {
    this.c = ☃;
    this.d = ☃;
    
    this.a = ☃;
    this.b = ☃;
    if (!bzg.j())
    {
      f();
      return;
    }
    this.f = bzg.g();
    this.g = bvk.a();
    if (this.e) {
      this.h = bzg.h();
    }
    a(9728);
    bni.i(this.g);
    bni.a(3553, 0, 32856, this.a, this.b, 0, 6408, 5121, null);
    
    bzg.h(bzg.c, this.f);
    bzg.a(bzg.c, bzg.e, 3553, this.g, 0);
    if (this.e)
    {
      bzg.i(bzg.d, this.h);
      
      bzg.a(bzg.d, 33190, this.a, this.b);
      
      bzg.b(bzg.c, bzg.f, bzg.d, this.h);
    }
    f();
    d();
  }
  
  public void a(int ☃)
  {
    if (bzg.j())
    {
      this.j = ☃;
      bni.i(this.g);
      bni.b(3553, 10241, ☃);
      bni.b(3553, 10240, ☃);
      bni.b(3553, 10242, 10496);
      bni.b(3553, 10243, 10496);
      bni.i(0);
    }
  }
  
  public void b()
  {
    int ☃ = bzg.j(bzg.c);
    if (☃ == bzg.g) {
      return;
    }
    if (☃ == bzg.h) {
      throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT");
    }
    if (☃ == bzg.i) {
      throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT");
    }
    if (☃ == bzg.j) {
      throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER");
    }
    if (☃ == bzg.k) {
      throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER");
    }
    throw new RuntimeException("glCheckFramebufferStatus returned unknown status:" + ☃);
  }
  
  public void c()
  {
    if (bzg.j()) {
      bni.i(this.g);
    }
  }
  
  public void d()
  {
    if (bzg.j()) {
      bni.i(0);
    }
  }
  
  public void a(boolean ☃)
  {
    if (bzg.j())
    {
      bzg.h(bzg.c, this.f);
      if (☃) {
        bni.b(0, 0, this.c, this.d);
      }
    }
  }
  
  public void e()
  {
    if (bzg.j()) {
      bzg.h(bzg.c, 0);
    }
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃)
  {
    this.i[0] = ☃;
    this.i[1] = ☃;
    this.i[2] = ☃;
    this.i[3] = ☃;
  }
  
  public void c(int ☃, int ☃)
  {
    a(☃, ☃, true);
  }
  
  public void a(int ☃, int ☃, boolean ☃)
  {
    if (!bzg.j()) {
      return;
    }
    bni.a(true, true, true, false);
    bni.j();
    bni.a(false);
    
    bni.n(5889);
    bni.F();
    bni.a(0.0D, ☃, ☃, 0.0D, 1000.0D, 3000.0D);
    bni.n(5888);
    bni.F();
    bni.c(0.0F, 0.0F, -2000.0F);
    
    bni.b(0, 0, ☃, ☃);
    
    bni.y();
    bni.g();
    bni.d();
    if (☃)
    {
      bni.l();
      bni.h();
    }
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    
    c();
    
    float ☃ = ☃;
    float ☃ = ☃;
    float ☃ = this.c / this.a;
    float ☃ = this.d / this.b;
    
    bnu ☃ = bnu.a();
    bmz ☃ = ☃.c();
    ☃.a(7, bvp.i);
    ☃.b(0.0D, ☃, 0.0D).a(0.0D, 0.0D).b(255, 255, 255, 255).d();
    ☃.b(☃, ☃, 0.0D).a(☃, 0.0D).b(255, 255, 255, 255).d();
    ☃.b(☃, 0.0D, 0.0D).a(☃, ☃).b(255, 255, 255, 255).d();
    ☃.b(0.0D, 0.0D, 0.0D).a(0.0D, ☃).b(255, 255, 255, 255).d();
    ☃.b();
    
    d();
    
    bni.a(true);
    bni.a(true, true, true, true);
  }
  
  public void f()
  {
    a(true);
    bni.a(this.i[0], this.i[1], this.i[2], this.i[3]);
    int ☃ = 16384;
    if (this.e)
    {
      bni.a(1.0D);
      ☃ |= 0x100;
    }
    bni.m(☃);
    e();
  }
}
