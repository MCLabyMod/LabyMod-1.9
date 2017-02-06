import java.util.List;
import java.util.Random;

public class awb$a
  extends awh
{
  public awb$a() {}
  
  public awb$a(aht ☃, Random ☃, int ☃, int ☃)
  {
    super(☃, ☃);
    
    awc.b();
    
    awc.m ☃ = new awc.m(0, ☃, (☃ << 4) + 2, (☃ << 4) + 2);
    this.a.add(☃);
    ☃.a(☃, this.a, ☃);
    
    List<awg> ☃ = ☃.c;
    while (!☃.isEmpty())
    {
      int ☃ = ☃.nextInt(☃.size());
      awg ☃ = (awg)☃.remove(☃);
      ☃.a(☃, this.a, ☃);
    }
    d();
    a(☃, ☃, 10);
  }
}
