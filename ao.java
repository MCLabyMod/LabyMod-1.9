import net.minecraft.server.MinecraftServer;

public class ao
  extends i
{
  public String c()
  {
    return "list";
  }
  
  public int a()
  {
    return 0;
  }
  
  public String b(m ☃)
  {
    return "commands.players.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    int ☃ = ☃.H();
    ☃.a(new fb("commands.players.list", new Object[] { Integer.valueOf(☃), Integer.valueOf(☃.I()) }));
    ☃.a(new fa(☃.al().b((☃.length > 0) && ("uuids".equalsIgnoreCase(☃[0])))));
    ☃.a(n.a.e, ☃);
  }
}
