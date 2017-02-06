public class bdj
  extends bcz
{
  private boolean o;
  private String p;
  private final bdo.b q;
  
  public bdj(bdo.b ☃, int ☃, int ☃, int ☃, String ☃, boolean ☃)
  {
    super(☃, ☃, ☃, 150, 20, "");
    this.p = ☃;
    this.o = ☃;
    this.j = c();
    this.q = ☃;
  }
  
  private String c()
  {
    return bwo.a(this.p, new Object[0]) + ": " + (this.o ? bwo.a("gui.yes", new Object[0]) : bwo.a("gui.no", new Object[0]));
  }
  
  public void b(boolean ☃)
  {
    this.o = ☃;
    this.j = c();
    this.q.a(this.k, ☃);
  }
  
  public boolean c(bcf ☃, int ☃, int ☃)
  {
    if (super.c(☃, ☃, ☃))
    {
      this.o = (!this.o);
      this.j = c();
      this.q.a(this.k, this.o);
      return true;
    }
    return false;
  }
}
