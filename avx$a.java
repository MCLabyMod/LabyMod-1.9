import com.google.common.collect.Sets;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class avx$a
  extends awh
{
  private Set<ahn> c = Sets.newHashSet();
  private boolean d;
  
  public avx$a() {}
  
  public avx$a(aht ☃, Random ☃, int ☃, int ☃)
  {
    super(☃, ☃);
    b(☃, ☃, ☃, ☃);
  }
  
  private void b(aht ☃, Random ☃, int ☃, int ☃)
  {
    ☃.setSeed(☃.O());
    long ☃ = ☃.nextLong();
    long ☃ = ☃.nextLong();
    long ☃ = ☃ * ☃;
    long ☃ = ☃ * ☃;
    ☃.setSeed(☃ ^ ☃ ^ ☃.O());
    
    int ☃ = ☃ * 16 + 8 - 29;
    int ☃ = ☃ * 16 + 8 - 29;
    cq ☃ = cq.c.a.a(☃);
    
    this.a.add(new avy.h(☃, ☃, ☃, ☃));
    d();
    
    this.d = true;
  }
  
  public void a(aht ☃, Random ☃, avp ☃)
  {
    if (!this.d)
    {
      this.a.clear();
      b(☃, ☃, e(), f());
    }
    super.a(☃, ☃, ☃);
  }
  
  public boolean a(ahn ☃)
  {
    if (this.c.contains(☃)) {
      return false;
    }
    return super.a(☃);
  }
  
  public void b(ahn ☃)
  {
    super.b(☃);
    
    this.c.add(☃);
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    
    du ☃ = new du();
    for (ahn ☃ : this.c)
    {
      dn ☃ = new dn();
      ☃.a("X", ☃.a);
      ☃.a("Z", ☃.b);
      ☃.a(☃);
    }
    ☃.a("Processed", ☃);
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    if (☃.b("Processed", 9))
    {
      du ☃ = ☃.c("Processed", 10);
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        dn ☃ = ☃.b(☃);
        this.c.add(new ahn(☃.h("X"), ☃.h("Z")));
      }
    }
  }
}
