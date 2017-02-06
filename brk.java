import java.util.Random;

public class brk
  extends bsg<yj>
{
  private static final kk a = new kk("textures/entity/enderman/enderman.png");
  private bir b;
  private Random k = new Random();
  
  public brk(brm ☃)
  {
    super(☃, new bir(0.0F), 0.5F);
    this.b = ((bir)this.g);
    
    a(new btt(this));
    a(new btm(this));
  }
  
  public void a(yj ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    arc ☃ = ☃.db();
    this.b.a = (☃ != null);
    this.b.b = ☃.dc();
    if (☃.dc())
    {
      double ☃ = 0.02D;
      ☃ += this.k.nextGaussian() * ☃;
      ☃ += this.k.nextGaussian() * ☃;
    }
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  protected kk a(yj ☃)
  {
    return a;
  }
}
