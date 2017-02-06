import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bkq
  implements arz
{
  private static final Logger a = ;
  private final ase b;
  private final ol<ase> c = new ol();
  private final List<ase> d = Lists.newArrayList();
  private final aht e;
  
  public bkq(aht ☃)
  {
    this.b = new asb(☃, 0, 0);
    
    this.e = ☃;
  }
  
  public void a(int ☃, int ☃)
  {
    ase ☃ = d(☃, ☃);
    if (!☃.f()) {
      ☃.d();
    }
    this.c.d(ahn.a(☃, ☃));
    this.d.remove(☃);
  }
  
  public ase b(int ☃, int ☃)
  {
    return (ase)this.c.a(ahn.a(☃, ☃));
  }
  
  public ase c(int ☃, int ☃)
  {
    ase ☃ = new ase(this.e, ☃, ☃);
    this.c.a(ahn.a(☃, ☃), ☃);
    this.d.add(☃);
    ☃.c(true);
    
    return ☃;
  }
  
  public ase d(int ☃, int ☃)
  {
    return (ase)Objects.firstNonNull(b(☃, ☃), this.b);
  }
  
  public boolean d()
  {
    long ☃ = System.currentTimeMillis();
    for (ase ☃ : this.d) {
      ☃.b(System.currentTimeMillis() - ☃ > 5L);
    }
    if (System.currentTimeMillis() - ☃ > 100L) {
      a.info("Warning: Clientside chunk ticking took {} ms", new Object[] { Long.valueOf(System.currentTimeMillis() - ☃) });
    }
    return false;
  }
  
  public String f()
  {
    return "MultiplayerChunkCache: " + this.c.a() + ", " + this.d.size();
  }
}
