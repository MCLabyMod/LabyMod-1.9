import java.util.List;
import java.util.Random;

public class awj$a
  extends awh
{
  private boolean c;
  
  public awj$a() {}
  
  public awj$a(aht ☃, Random ☃, int ☃, int ☃, int ☃)
  {
    super(☃, ☃);
    
    List<awk.e> ☃ = awk.a(☃, ☃);
    
    awk.k ☃ = new awk.k(☃.A(), 0, ☃, (☃ << 4) + 2, (☃ << 4) + 2, ☃, ☃);
    this.a.add(☃);
    ☃.a(☃, this.a, ☃);
    
    List<awg> ☃ = ☃.g;
    List<awg> ☃ = ☃.f;
    while ((!☃.isEmpty()) || (!☃.isEmpty())) {
      if (☃.isEmpty())
      {
        int ☃ = ☃.nextInt(☃.size());
        awg ☃ = (awg)☃.remove(☃);
        ☃.a(☃, this.a, ☃);
      }
      else
      {
        int ☃ = ☃.nextInt(☃.size());
        awg ☃ = (awg)☃.remove(☃);
        ☃.a(☃, this.a, ☃);
      }
    }
    d();
    
    int ☃ = 0;
    for (awg ☃ : this.a) {
      if (!(☃ instanceof awk.o)) {
        ☃++;
      }
    }
    this.c = (☃ > 2);
  }
  
  public boolean a()
  {
    return this.c;
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    
    ☃.a("Valid", this.c);
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    this.c = ☃.p("Valid");
  }
}
