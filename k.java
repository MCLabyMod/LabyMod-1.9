import java.util.List;
import net.minecraft.server.MinecraftServer;

public abstract interface k
  extends Comparable<k>
{
  public abstract String c();
  
  public abstract String b(m paramm);
  
  public abstract List<String> b();
  
  public abstract void a(MinecraftServer paramMinecraftServer, m paramm, String[] paramArrayOfString)
    throws bz;
  
  public abstract boolean a(MinecraftServer paramMinecraftServer, m paramm);
  
  public abstract List<String> a(MinecraftServer paramMinecraftServer, m paramm, String[] paramArrayOfString, cj paramcj);
  
  public abstract boolean b(String[] paramArrayOfString, int paramInt);
}
