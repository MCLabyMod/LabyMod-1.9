import java.util.List;
import java.util.Random;

public class avv$a
  extends awh
{
  public avv$a() {}
  
  public avv$a(aht ☃, Random ☃, int ☃, int ☃)
  {
    super(☃, ☃);
    
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
