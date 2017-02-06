import com.google.common.collect.Maps;
import java.util.Map;
import java.util.TimerTask;
import net.minecraft.server.MinecraftServer;

class qw$1
  extends TimerTask
{
  qw$1(qw paramqw) {}
  
  public void run()
  {
    if (!qw.a(this.a).Z()) {
      return;
    }
    Map<String, Object> ☃;
    synchronized (qw.b(this.a))
    {
      ☃ = Maps.newHashMap(qw.c(this.a));
      if (qw.d(this.a) == 0) {
        ☃.putAll(qw.e(this.a));
      }
      ☃.put("snooper_count", Integer.valueOf(qw.f(this.a)));
      ☃.put("snooper_token", qw.g(this.a));
    }
    MinecraftServer ☃ = (qw.a(this.a) instanceof MinecraftServer) ? (MinecraftServer)qw.a(this.a) : null;
    oe.a(qw.h(this.a), ☃, true, ☃ == null ? null : ☃.au());
  }
}
