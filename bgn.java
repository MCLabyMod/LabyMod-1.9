import java.util.List;
import org.lwjgl.input.Keyboard;

public class bgn
  extends bfb
{
  private aqn a;
  private int f;
  private int g;
  private bcz h;
  
  public bgn(aqn ☃)
  {
    this.a = ☃;
  }
  
  public void b()
  {
    this.n.clear();
    Keyboard.enableRepeatEvents(true);
    this.n.add(this.h = new bcz(0, this.l / 2 - 100, this.m / 4 + 120, bwo.a("gui.done", new Object[0])));
    this.a.a(false);
  }
  
  public void m()
  {
    Keyboard.enableRepeatEvents(false);
    bks ☃ = this.j.v();
    if (☃ != null) {
      ☃.a(new jd(this.a.v(), this.a.a));
    }
    this.a.a(true);
  }
  
  public void e()
  {
    this.f += 1;
  }
  
  protected void a(bcz ☃)
  {
    if (!☃.l) {
      return;
    }
    if (☃.k == 0)
    {
      this.a.v_();
      this.j.a(null);
    }
  }
  
  protected void a(char ☃, int ☃)
  {
    if (☃ == 200) {
      this.g = (this.g - 1 & 0x3);
    }
    if ((☃ == 208) || (☃ == 28) || (☃ == 156)) {
      this.g = (this.g + 1 & 0x3);
    }
    String ☃ = this.a.a[this.g].c();
    if ((☃ == 14) && (!☃.isEmpty())) {
      ☃ = ☃.substring(0, ☃.length() - 1);
    }
    if ((f.a(☃)) && (this.q.a(☃ + ☃) <= 90)) {
      ☃ = ☃ + ☃;
    }
    this.a.a[this.g] = new fa(☃);
    if (☃ == 1) {
      a(this.h);
    }
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    c();
    
    a(this.q, bwo.a("sign.edit", new Object[0]), this.l / 2, 40, 16777215);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    
    bni.G();
    bni.c(this.l / 2, 0.0F, 50.0F);
    float ☃ = 93.75F;
    bni.b(-☃, -☃, -☃);
    bni.b(180.0F, 0.0F, 1.0F, 0.0F);
    
    ajt ☃ = this.a.w();
    if (☃ == aju.an)
    {
      float ☃ = this.a.u() * 360 / 16.0F;
      bni.b(☃, 0.0F, 1.0F, 0.0F);
      
      bni.c(0.0F, -1.0625F, 0.0F);
    }
    else
    {
      int ☃ = this.a.u();
      float ☃ = 0.0F;
      if (☃ == 2) {
        ☃ = 180.0F;
      }
      if (☃ == 4) {
        ☃ = 90.0F;
      }
      if (☃ == 5) {
        ☃ = -90.0F;
      }
      bni.b(☃, 0.0F, 1.0F, 0.0F);
      bni.c(0.0F, -1.0625F, 0.0F);
    }
    if (this.f / 6 % 2 == 0) {
      this.a.f = this.g;
    }
    bpm.a.a(this.a, -0.5D, -0.75D, -0.5D, 0.0F);
    this.a.f = -1;
    
    bni.H();
    
    super.a(☃, ☃, ☃);
  }
}
