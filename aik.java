import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class aik
{
  private axv a;
  private axv b;
  private final aih c = new aih(this);
  private final List<aig> d = Lists.newArrayList(new aig[] { ail.f, ail.c, ail.g, ail.u, ail.t, ail.w, ail.x });
  
  protected aik() {}
  
  private aik(long ☃, ahy ☃, String ☃)
  {
    this();
    
    axv[] ☃ = axv.a(☃, ☃, ☃);
    this.a = ☃[0];
    this.b = ☃[1];
  }
  
  public aik(azh ☃)
  {
    this(☃.a(), ☃.t(), ☃.A());
  }
  
  public List<aig> a()
  {
    return this.d;
  }
  
  public aig a(cj ☃)
  {
    return a(☃, null);
  }
  
  public aig a(cj ☃, aig ☃)
  {
    return this.c.a(☃.p(), ☃.r(), ☃);
  }
  
  public float a(float ☃, int ☃)
  {
    return ☃;
  }
  
  public aig[] a(aig[] ☃, int ☃, int ☃, int ☃, int ☃)
  {
    
    if ((☃ == null) || (☃.length < ☃ * ☃)) {
      ☃ = new aig[☃ * ☃];
    }
    int[] ☃ = this.a.a(☃, ☃, ☃, ☃);
    try
    {
      for (int ☃ = 0; ☃ < ☃ * ☃; ☃++) {
        ☃[☃] = aig.a(☃[☃], ail.b);
      }
    }
    catch (Throwable ☃)
    {
      b ☃ = b.a(☃, "Invalid Biome id");
      c ☃ = ☃.a("RawBiomeBlock");
      ☃.a("biomes[] size", Integer.valueOf(☃.length));
      ☃.a("x", Integer.valueOf(☃));
      ☃.a("z", Integer.valueOf(☃));
      ☃.a("w", Integer.valueOf(☃));
      ☃.a("h", Integer.valueOf(☃));
      
      throw new e(☃);
    }
    return ☃;
  }
  
  public aig[] b(aig[] ☃, int ☃, int ☃, int ☃, int ☃)
  {
    return a(☃, ☃, ☃, ☃, ☃, true);
  }
  
  public aig[] a(aig[] ☃, int ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    
    if ((☃ == null) || (☃.length < ☃ * ☃)) {
      ☃ = new aig[☃ * ☃];
    }
    if ((☃) && (☃ == 16) && (☃ == 16) && ((☃ & 0xF) == 0) && ((☃ & 0xF) == 0))
    {
      aig[] ☃ = this.c.b(☃, ☃);
      System.arraycopy(☃, 0, ☃, 0, ☃ * ☃);
      return ☃;
    }
    int[] ☃ = this.b.a(☃, ☃, ☃, ☃);
    for (int ☃ = 0; ☃ < ☃ * ☃; ☃++) {
      ☃[☃] = aig.a(☃[☃], ail.b);
    }
    return ☃;
  }
  
  public boolean a(int ☃, int ☃, int ☃, List<aig> ☃)
  {
    axt.a();
    int ☃ = ☃ - ☃ >> 2;
    int ☃ = ☃ - ☃ >> 2;
    int ☃ = ☃ + ☃ >> 2;
    int ☃ = ☃ + ☃ >> 2;
    
    int ☃ = ☃ - ☃ + 1;
    int ☃ = ☃ - ☃ + 1;
    
    int[] ☃ = this.a.a(☃, ☃, ☃, ☃);
    try
    {
      for (int ☃ = 0; ☃ < ☃ * ☃; ☃++)
      {
        aig ☃ = aig.b(☃[☃]);
        if (!☃.contains(☃)) {
          return false;
        }
      }
    }
    catch (Throwable ☃)
    {
      b ☃ = b.a(☃, "Invalid Biome id");
      c ☃ = ☃.a("Layer");
      ☃.a("Layer", this.a.toString());
      ☃.a("x", Integer.valueOf(☃));
      ☃.a("z", Integer.valueOf(☃));
      ☃.a("radius", Integer.valueOf(☃));
      ☃.a("allowed", ☃);
      
      throw new e(☃);
    }
    return true;
  }
  
  public cj a(int ☃, int ☃, int ☃, List<aig> ☃, Random ☃)
  {
    axt.a();
    int ☃ = ☃ - ☃ >> 2;
    int ☃ = ☃ - ☃ >> 2;
    int ☃ = ☃ + ☃ >> 2;
    int ☃ = ☃ + ☃ >> 2;
    
    int ☃ = ☃ - ☃ + 1;
    int ☃ = ☃ - ☃ + 1;
    int[] ☃ = this.a.a(☃, ☃, ☃, ☃);
    cj ☃ = null;
    int ☃ = 0;
    for (int ☃ = 0; ☃ < ☃ * ☃; ☃++)
    {
      int ☃ = ☃ + ☃ % ☃ << 2;
      int ☃ = ☃ + ☃ / ☃ << 2;
      aig ☃ = aig.b(☃[☃]);
      if ((☃.contains(☃)) && (
        (☃ == null) || (☃.nextInt(☃ + 1) == 0)))
      {
        ☃ = new cj(☃, 0, ☃);
        ☃++;
      }
    }
    return ☃;
  }
  
  public void b()
  {
    this.c.a();
  }
}
