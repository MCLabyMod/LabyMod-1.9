import java.util.Calendar;

public class bpo
  extends bpn<apx>
{
  private static final kk a = new kk("textures/entity/chest/trapped_double.png");
  private static final kk d = new kk("textures/entity/chest/christmas_double.png");
  private static final kk e = new kk("textures/entity/chest/normal_double.png");
  private static final kk f = new kk("textures/entity/chest/trapped.png");
  private static final kk g = new kk("textures/entity/chest/christmas.png");
  private static final kk h = new kk("textures/entity/chest/normal.png");
  private bim i = new bim();
  private bim j = new biy();
  private boolean k;
  
  public bpo()
  {
    Calendar ☃ = Calendar.getInstance();
    if ((☃.get(2) + 1 == 12) && (☃.get(5) >= 24) && (☃.get(5) <= 26)) {
      this.k = true;
    }
  }
  
  public void a(apx ☃, double ☃, double ☃, double ☃, float ☃, int ☃)
  {
    bni.k();
    bni.c(515);
    bni.a(true);
    int ☃;
    int ☃;
    if (!☃.t())
    {
      ☃ = 0;
    }
    else
    {
      ajt ☃ = ☃.w();
      ☃ = ☃.u();
      if (((☃ instanceof ake)) && (☃ == 0))
      {
        ((ake)☃).e(☃.D(), ☃.v(), ☃.D().o(☃.v()));
        ☃ = ☃.u();
      }
      ☃.m();
    }
    if ((☃.f != null) || (☃.h != null)) {
      return;
    }
    bim ☃;
    if ((☃.g != null) || (☃.i != null))
    {
      bim ☃ = this.j;
      if (☃ >= 0)
      {
        a(b[☃]);
        bni.n(5890);
        bni.G();
        bni.b(8.0F, 4.0F, 1.0F);
        bni.c(0.0625F, 0.0625F, 0.0625F);
        bni.n(5888);
      }
      else if (this.k)
      {
        a(d);
      }
      else if (☃.o() == ake.a.b)
      {
        a(a);
      }
      else
      {
        a(e);
      }
    }
    else
    {
      ☃ = this.i;
      if (☃ >= 0)
      {
        a(b[☃]);
        bni.n(5890);
        bni.G();
        bni.b(4.0F, 4.0F, 1.0F);
        bni.c(0.0625F, 0.0625F, 0.0625F);
        bni.n(5888);
      }
      else if (this.k)
      {
        a(g);
      }
      else if (☃.o() == ake.a.b)
      {
        a(f);
      }
      else
      {
        a(h);
      }
    }
    bni.G();
    bni.D();
    if (☃ < 0) {
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    }
    bni.c((float)☃, (float)☃ + 1.0F, (float)☃ + 1.0F);
    bni.b(1.0F, -1.0F, -1.0F);
    
    bni.c(0.5F, 0.5F, 0.5F);
    int ☃ = 0;
    if (☃ == 2) {
      ☃ = 180;
    }
    if (☃ == 3) {
      ☃ = 0;
    }
    if (☃ == 4) {
      ☃ = 90;
    }
    if (☃ == 5) {
      ☃ = -90;
    }
    if ((☃ == 2) && (☃.g != null)) {
      bni.c(1.0F, 0.0F, 0.0F);
    }
    if ((☃ == 5) && (☃.i != null)) {
      bni.c(0.0F, 0.0F, -1.0F);
    }
    bni.b(☃, 0.0F, 1.0F, 0.0F);
    bni.c(-0.5F, -0.5F, -0.5F);
    
    float ☃ = ☃.k + (☃.j - ☃.k) * ☃;
    if (☃.f != null)
    {
      float ☃ = ☃.f.k + (☃.f.j - ☃.f.k) * ☃;
      if (☃ > ☃) {
        ☃ = ☃;
      }
    }
    if (☃.h != null)
    {
      float ☃ = ☃.h.k + (☃.h.j - ☃.h.k) * ☃;
      if (☃ > ☃) {
        ☃ = ☃;
      }
    }
    ☃ = 1.0F - ☃;
    ☃ = 1.0F - ☃ * ☃ * ☃;
    
    ☃.a.f = (-(☃ * 1.5707964F));
    ☃.a();
    bni.E();
    bni.H();
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    if (☃ >= 0)
    {
      bni.n(5890);
      bni.H();
      bni.n(5888);
    }
  }
}
