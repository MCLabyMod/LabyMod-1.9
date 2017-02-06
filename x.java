import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class x
  extends i
{
  private static final Logger a = ;
  private long b;
  private int c;
  
  public String c()
  {
    return "debug";
  }
  
  public int a()
  {
    return 3;
  }
  
  public String b(m ☃)
  {
    return "commands.debug.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 1) {
      throw new cf("commands.debug.usage", new Object[0]);
    }
    if (☃[0].equals("start"))
    {
      if (☃.length != 1) {
        throw new cf("commands.debug.usage", new Object[0]);
      }
      a(☃, this, "commands.debug.start", new Object[0]);
      
      ☃.aq();
      this.b = MinecraftServer.av();
      this.c = ☃.ap();
    }
    else if (☃[0].equals("stop"))
    {
      if (☃.length != 1) {
        throw new cf("commands.debug.usage", new Object[0]);
      }
      if (!☃.c.a) {
        throw new bz("commands.debug.notStarted", new Object[0]);
      }
      long ☃ = MinecraftServer.av();
      int ☃ = ☃.ap();
      
      long ☃ = ☃ - this.b;
      int ☃ = ☃ - this.c;
      a(☃, ☃, ☃);
      
      ☃.c.a = false;
      a(☃, this, "commands.debug.stop", new Object[] { Float.valueOf((float)☃ / 1000.0F), Integer.valueOf(☃) });
    }
    else
    {
      throw new cf("commands.debug.usage", new Object[0]);
    }
  }
  
  private void a(long ☃, int ☃, MinecraftServer ☃)
  {
    File ☃ = new File(☃.d("debug"), "profile-results-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + ".txt");
    
    ☃.getParentFile().mkdirs();
    try
    {
      FileWriter ☃ = new FileWriter(☃);
      ☃.write(b(☃, ☃, ☃));
      ☃.close();
    }
    catch (Throwable ☃)
    {
      a.error("Could not save profiler results to " + ☃, ☃);
    }
  }
  
  private String b(long ☃, int ☃, MinecraftServer ☃)
  {
    StringBuilder ☃ = new StringBuilder();
    
    ☃.append("---- Minecraft Profiler Results ----\n");
    ☃.append("// ");
    ☃.append(d());
    ☃.append("\n\n");
    
    ☃.append("Time span: ").append(☃).append(" ms\n");
    ☃.append("Tick span: ").append(☃).append(" ticks\n");
    ☃.append("// This is approximately ").append(String.format("%.2f", new Object[] { Float.valueOf(☃ / ((float)☃ / 1000.0F)) })).append(" ticks per second. It should be ").append(20).append(" ticks per second\n\n");
    
    ☃.append("--- BEGIN PROFILE DUMP ---\n\n");
    
    a(0, "root", ☃, ☃);
    
    ☃.append("--- END PROFILE DUMP ---\n\n");
    
    return ☃.toString();
  }
  
  private void a(int ☃, String ☃, StringBuilder ☃, MinecraftServer ☃)
  {
    List<oo.a> ☃ = ☃.c.b(☃);
    if ((☃ == null) || (☃.size() < 3)) {
      return;
    }
    for (int ☃ = 1; ☃ < ☃.size(); ☃++)
    {
      oo.a ☃ = (oo.a)☃.get(☃);
      
      ☃.append(String.format("[%02d] ", new Object[] { Integer.valueOf(☃) }));
      for (int ☃ = 0; ☃ < ☃; ☃++) {
        ☃.append("|   ");
      }
      ☃.append(☃.c).append(" - ").append(String.format("%.2f", new Object[] { Double.valueOf(☃.a) })).append("%/").append(String.format("%.2f", new Object[] { Double.valueOf(☃.b) })).append("%\n");
      if (!☃.c.equals("unspecified")) {
        try
        {
          a(☃ + 1, ☃ + "." + ☃.c, ☃, ☃);
        }
        catch (Exception ☃)
        {
          ☃.append("[[ EXCEPTION ").append(☃).append(" ]]");
        }
      }
    }
  }
  
  private static String d()
  {
    String[] ☃ = { "Shiny numbers!", "Am I not running fast enough? :(", "I'm working as hard as I can!", "Will I ever be good enough for you? :(", "Speedy. Zoooooom!", "Hello world", "40% better than a crash report.", "Now with extra numbers", "Now with less numbers", "Now with the same numbers", "You should add flames to things, it makes them go faster!", "Do you feel the need for... optimization?", "*cracks redstone whip*", "Maybe if you treated it better then it'll have more motivation to work faster! Poor server." };
    try
    {
      return ☃[((int)(System.nanoTime() % ☃.length))];
    }
    catch (Throwable ☃) {}
    return "Witty comment unavailable :(";
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, new String[] { "start", "stop" });
    }
    return Collections.emptyList();
  }
}
