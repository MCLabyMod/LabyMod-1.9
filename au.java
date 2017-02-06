import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class au
  extends i
{
  public String c()
  {
    return "playsound";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.playsound.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 2) {
      throw new cf(b(☃), new Object[0]);
    }
    int ☃ = 0;
    String ☃ = ☃[(☃++)];
    nh ☃ = nh.a(☃[(☃++)]);
    lr ☃ = a(☃, ☃, ☃[(☃++)]);
    
    bbj ☃ = ☃.d();
    
    double ☃ = ☃.b;
    if (☃.length > ☃) {
      ☃ = b(☃, ☃[(☃++)], true);
    }
    double ☃ = ☃.c;
    if (☃.length > ☃) {
      ☃ = b(☃, ☃[(☃++)], 0, 0, false);
    }
    double ☃ = ☃.d;
    if (☃.length > ☃) {
      ☃ = b(☃, ☃[(☃++)], true);
    }
    double ☃ = 1.0D;
    if (☃.length > ☃) {
      ☃ = a(☃[(☃++)], 0.0D, 3.4028234663852886E38D);
    }
    double ☃ = 1.0D;
    if (☃.length > ☃) {
      ☃ = a(☃[(☃++)], 0.0D, 2.0D);
    }
    double ☃ = 0.0D;
    if (☃.length > ☃) {
      ☃ = a(☃[☃], 0.0D, 1.0D);
    }
    double ☃ = ☃ > 1.0D ? ☃ * 16.0D : 16.0D;
    double ☃ = ☃.f(☃, ☃, ☃);
    if (☃ > ☃)
    {
      if (☃ <= 0.0D) {
        throw new bz("commands.playsound.playerTooFar", new Object[] { ☃.h_() });
      }
      double ☃ = ☃ - ☃.p;
      double ☃ = ☃ - ☃.q;
      double ☃ = ☃ - ☃.r;
      double ☃ = Math.sqrt(☃ * ☃ + ☃ * ☃ + ☃ * ☃);
      if (☃ > 0.0D)
      {
        ☃ = ☃.p + ☃ / ☃ * 2.0D;
        ☃ = ☃.q + ☃ / ☃ * 2.0D;
        ☃ = ☃.r + ☃ / ☃ * 2.0D;
      }
      ☃ = ☃;
    }
    ☃.a.a(new gi(☃, ☃, ☃, ☃, ☃, (float)☃, (float)☃));
    a(☃, this, "commands.playsound.success", new Object[] { ☃, ☃.h_() });
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, nf.a.c());
    }
    if (☃.length == 2) {
      return a(☃, nh.b());
    }
    if (☃.length == 3) {
      return a(☃, ☃.J());
    }
    if ((☃.length > 3) && (☃.length <= 6)) {
      return a(☃, 2, ☃);
    }
    return Collections.emptyList();
  }
  
  public boolean b(String[] ☃, int ☃)
  {
    return ☃ == 2;
  }
}
