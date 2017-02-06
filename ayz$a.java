import java.util.Map;

public class ayz$a
{
  public final zj a;
  private boolean d = true;
  private int e = 0;
  private int f = 0;
  private int g = 127;
  private int h = 127;
  private int i;
  public int b;
  
  public ayz$a(ayz paramayz, zj ☃)
  {
    this.a = ☃;
  }
  
  public ff<?> a(adq ☃)
  {
    if (this.d)
    {
      this.d = false;
      return new gt(☃.i(), this.c.f, this.c.e, this.c.i.values(), this.c.g, this.e, this.f, this.g + 1 - this.e, this.h + 1 - this.f);
    }
    if (this.i++ % 5 == 0) {
      return new gt(☃.i(), this.c.f, this.c.e, this.c.i.values(), this.c.g, 0, 0, 0, 0);
    }
    return null;
  }
  
  public void a(int ☃, int ☃)
  {
    if (this.d)
    {
      this.e = Math.min(this.e, ☃);
      this.f = Math.min(this.f, ☃);
      this.g = Math.max(this.g, ☃);
      this.h = Math.max(this.h, ☃);
    }
    else
    {
      this.d = true;
      this.e = ☃;
      this.f = ☃;
      this.g = ☃;
      this.h = ☃;
    }
  }
}
