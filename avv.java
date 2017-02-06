import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class avv
  extends awd
{
  private final List<aig.c> a = Lists.newArrayList();
  
  public avv()
  {
    this.a.add(new aig.c(yg.class, 10, 2, 3));
    this.a.add(new aig.c(yr.class, 5, 4, 4));
    this.a.add(new aig.c(yw.class, 10, 4, 4));
    this.a.add(new aig.c(yp.class, 3, 4, 4));
  }
  
  public String a()
  {
    return "Fortress";
  }
  
  public List<aig.c> b()
  {
    return this.a;
  }
  
  protected boolean a(int ☃, int ☃)
  {
    int ☃ = ☃ >> 4;
    int ☃ = ☃ >> 4;
    
    this.f.setSeed(☃ ^ ☃ << 4 ^ this.g.O());
    this.f.nextInt();
    if (this.f.nextInt(3) != 0) {
      return false;
    }
    if (☃ != (☃ << 4) + 4 + this.f.nextInt(8)) {
      return false;
    }
    if (☃ != (☃ << 4) + 4 + this.f.nextInt(8)) {
      return false;
    }
    return true;
  }
  
  protected awh b(int ☃, int ☃)
  {
    return new avv.a(this.g, this.f, ☃, ☃);
  }
  
  public static class a
    extends awh
  {
    public a() {}
    
    public a(aht ☃, Random ☃, int ☃, int ☃)
    {
      super(☃);
      
      avw.q ☃ = new avw.q(☃, (☃ << 4) + 2, (☃ << 4) + 2);
      this.a.add(☃);
      ☃.a(☃, this.a, ☃);
      
      List<awg> ☃ = ☃.d;
      while (!☃.isEmpty())
      {
        int ☃ = ☃.nextInt(☃.size());
        awg ☃ = (awg)☃.remove(☃);
        ☃.a(☃, this.a, ☃);
      }
      d();
      a(☃, ☃, 48, 70);
    }
  }
}
