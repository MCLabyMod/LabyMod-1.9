import net.minecraft.realms.RealmsButton;

public class bdx
  extends bcz
{
  private RealmsButton o;
  
  public bdx(RealmsButton ☃, int ☃, int ☃, int ☃, String ☃)
  {
    super(☃, ☃, ☃, ☃);
    this.o = ☃;
  }
  
  public bdx(RealmsButton ☃, int ☃, int ☃, int ☃, String ☃, int ☃, int ☃)
  {
    super(☃, ☃, ☃, ☃, ☃, ☃);
    this.o = ☃;
  }
  
  public int c()
  {
    return this.k;
  }
  
  public boolean d()
  {
    return this.l;
  }
  
  public void b(boolean ☃)
  {
    this.l = ☃;
  }
  
  public void a(String ☃)
  {
    this.j = ☃;
  }
  
  public int b()
  {
    return super.b();
  }
  
  public int e()
  {
    return this.i;
  }
  
  public boolean c(bcf ☃, int ☃, int ☃)
  {
    if (super.c(☃, ☃, ☃)) {
      this.o.clicked(☃, ☃);
    }
    return super.c(☃, ☃, ☃);
  }
  
  public void a(int ☃, int ☃)
  {
    this.o.released(☃, ☃);
  }
  
  public void b(bcf ☃, int ☃, int ☃)
  {
    this.o.renderBg(☃, ☃);
  }
  
  public RealmsButton f()
  {
    return this.o;
  }
  
  public int a(boolean ☃)
  {
    return this.o.getYImage(☃);
  }
  
  public int c(boolean ☃)
  {
    return super.a(☃);
  }
  
  public int g()
  {
    return this.g;
  }
}
