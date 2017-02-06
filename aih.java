import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class aih
{
  private final aik a;
  private long b;
  
  public class a
  {
    public aig[] a = new aig['Ā'];
    public int b;
    public int c;
    public long d;
    
    public a(int ☃, int ☃)
    {
      this.b = ☃;
      this.c = ☃;
      aih.a(aih.this).a(this.a, ☃ << 4, ☃ << 4, 16, 16, false);
    }
    
    public aig a(int ☃, int ☃)
    {
      return this.a[(☃ & 0xF | (☃ & 0xF) << 4)];
    }
  }
  
  private final ol<aih.a> c = new ol();
  private final List<aih.a> d = Lists.newArrayList();
  
  public aih(aik ☃)
  {
    this.a = ☃;
  }
  
  public aih.a a(int ☃, int ☃)
  {
    ☃ >>= 4;
    ☃ >>= 4;
    long ☃ = ☃ & 0xFFFFFFFF | (☃ & 0xFFFFFFFF) << 32;
    aih.a ☃ = (aih.a)this.c.a(☃);
    if (☃ == null)
    {
      ☃ = new aih.a(☃, ☃);
      this.c.a(☃, ☃);
      this.d.add(☃);
    }
    ☃.d = MinecraftServer.av();
    return ☃;
  }
  
  public aig a(int ☃, int ☃, aig ☃)
  {
    aig ☃ = a(☃, ☃).a(☃, ☃);
    return ☃ == null ? ☃ : ☃;
  }
  
  public void a()
  {
    long ☃ = MinecraftServer.av();
    long ☃ = ☃ - this.b;
    if ((☃ > 7500L) || (☃ < 0L))
    {
      this.b = ☃;
      for (int ☃ = 0; ☃ < this.d.size(); ☃++)
      {
        aih.a ☃ = (aih.a)this.d.get(☃);
        long ☃ = ☃ - ☃.d;
        if ((☃ > 30000L) || (☃ < 0L))
        {
          this.d.remove(☃--);
          long ☃ = ☃.b & 0xFFFFFFFF | (☃.c & 0xFFFFFFFF) << 32;
          this.c.d(☃);
        }
      }
    }
  }
  
  public aig[] b(int ☃, int ☃)
  {
    return a(☃, ☃).a;
  }
}
