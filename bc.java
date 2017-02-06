import net.minecraft.server.MinecraftServer;

public class bc
  extends j
  implements h
{
  private final MinecraftServer a;
  
  public bc(MinecraftServer ☃)
  {
    this.a = ☃;
    a(new br());
    a(new ah());
    a(new ag());
    a(new y());
    a(new am());
    a(new bt());
    a(new bv());
    a(new ae());
    a(new bm());
    a(new aj());
    a(new aw());
    a(new bj());
    a(new z());
    a(new ab());
    a(new at());
    a(new aa());
    a(new bh());
    a(new ak());
    a(new x());
    a(new ap());
    a(new ba());
    a(new bg());
    a(new be());
    a(new ai());
    a(new t());
    a(new bq());
    a(new bi());
    a(new au());
    a(new bb());
    a(new ad());
    a(new bu());
    a(new p());
    a(new bl());
    a(new bd());
    a(new af());
    a(new u());
    a(new v());
    a(new s());
    a(new bp());
    a(new bn());
    a(new bx());
    a(new bs());
    a(new ac());
    if (☃.aa())
    {
      a(new aq());
      a(new w());
      a(new bk());
      a(new ax());
      a(new ay());
      a(new az());
      a(new q());
      a(new ar());
      a(new r());
      a(new an());
      a(new as());
      a(new al());
      a(new ao());
      a(new bw());
      a(new bf());
    }
    else
    {
      a(new av());
    }
    i.a(this);
  }
  
  public void a(m ☃, k ☃, int ☃, String ☃, Object... ☃)
  {
    boolean ☃ = true;
    
    MinecraftServer ☃ = this.a;
    if (!☃.z_()) {
      ☃ = false;
    }
    eu ☃ = new fb("chat.type.admin", new Object[] { ☃.h_(), new fb(☃, ☃) });
    ☃.b().a(a.h);
    ☃.b().b(Boolean.valueOf(true));
    if (☃) {
      for (zj ☃ : ☃.al().v()) {
        if ((☃ != ☃) && (☃.al().h(☃.cK())) && (☃.a(this.a, ☃)))
        {
          boolean ☃ = ((☃ instanceof MinecraftServer)) && (this.a.s());
          boolean ☃ = ((☃ instanceof my)) && (this.a.r());
          if ((☃) || (☃) || ((!(☃ instanceof my)) && (!(☃ instanceof MinecraftServer)))) {
            ☃.a(☃);
          }
        }
      }
    }
    if ((☃ != ☃) && (☃.d[0].U().b("logAdminCommands"))) {
      ☃.a(☃);
    }
    boolean ☃ = ☃.d[0].U().b("sendCommandFeedback");
    if ((☃ instanceof ahj)) {
      ☃ = ((ahj)☃).n();
    }
    if ((((☃ & 0x1) != 1) && (☃)) || ((☃ instanceof MinecraftServer))) {
      ☃.a(new fb(☃, ☃));
    }
  }
  
  protected MinecraftServer a()
  {
    return this.a;
  }
}
