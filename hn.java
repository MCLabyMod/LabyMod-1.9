import java.io.IOException;

public class hn
  implements ff<fi>
{
  private int a;
  private int b;
  private int c;
  private int d;
  
  public hn() {}
  
  public hn(rr ☃)
  {
    this(☃.O(), ☃.s, ☃.t, ☃.u);
  }
  
  public hn(int ☃, double ☃, double ☃, double ☃)
  {
    this.a = ☃;
    double ☃ = 3.9D;
    if (☃ < -☃) {
      ☃ = -☃;
    }
    if (☃ < -☃) {
      ☃ = -☃;
    }
    if (☃ < -☃) {
      ☃ = -☃;
    }
    if (☃ > ☃) {
      ☃ = ☃;
    }
    if (☃ > ☃) {
      ☃ = ☃;
    }
    if (☃ > ☃) {
      ☃ = ☃;
    }
    this.b = ((int)(☃ * 8000.0D));
    this.c = ((int)(☃ * 8000.0D));
    this.d = ((int)(☃ * 8000.0D));
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.b = ☃.readShort();
    this.c = ☃.readShort();
    this.d = ☃.readShort();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
    ☃.writeShort(this.b);
    ☃.writeShort(this.c);
    ☃.writeShort(this.d);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public int b()
  {
    return this.b;
  }
  
  public int c()
  {
    return this.c;
  }
  
  public int d()
  {
    return this.d;
  }
}
