import java.util.Random;

public class blk
  extends bmd
{
  public blk(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, 176, 8, -5.0E-4F);
    
    this.i = ☃;
    this.j = ☃;
    this.k = ☃;
    
    this.w *= 0.75F;
    
    this.v = (60 + this.p.nextInt(12));
    
    d(15916745);
  }
  
  public void a(double ☃, double ☃, double ☃)
  {
    a(l().c(☃, ☃, ☃));
    j();
  }
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new blk(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
}
