import com.mojang.authlib.GameProfile;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.Logger;

class bkz$1
  implements jw
{
  private boolean d = false;
  private boolean e = false;
  private long f = 0L;
  
  bkz$1(bkz this$0, ek paramek, bkx parambkx) {}
  
  public void a(jy packetIn)
  {
    if (this.e)
    {
      this.val$networkmanager.a(new fa("Received unrequested status"));
    }
    else
    {
      this.e = true;
      jz serverstatusresponse = packetIn.a();
      if (serverstatusresponse.a() != null) {
        this.val$server.d = serverstatusresponse.a().d();
      } else {
        this.val$server.d = "";
      }
      if (serverstatusresponse.c() != null)
      {
        this.val$server.g = serverstatusresponse.c().a();
        this.val$server.f = serverstatusresponse.c().b();
      }
      else
      {
        this.val$server.g = "Old";
        this.val$server.f = 0;
      }
      if (serverstatusresponse.b() != null)
      {
        this.val$server.c = (a.h + "" + serverstatusresponse.b().b() + "" + a.i + "/" + a.h + serverstatusresponse.b().a());
        if (ArrayUtils.isNotEmpty(serverstatusresponse.b().c()))
        {
          StringBuilder stringbuilder = new StringBuilder();
          for (GameProfile gameprofile : serverstatusresponse.b().c())
          {
            if (stringbuilder.length() > 0) {
              stringbuilder.append("\n");
            }
            stringbuilder.append(gameprofile.getName());
          }
          if (serverstatusresponse.b().c().length < serverstatusresponse.b().b())
          {
            if (stringbuilder.length() > 0) {
              stringbuilder.append("\n");
            }
            stringbuilder.append("... and ").append(serverstatusresponse.b().b() - serverstatusresponse.b().c().length).append(" more ...");
          }
          this.val$server.i = stringbuilder.toString();
        }
      }
      else
      {
        this.val$server.c = (a.i + "???");
      }
      if (serverstatusresponse.d() != null)
      {
        String s = serverstatusresponse.d();
        if (s.startsWith("data:image/png;base64,")) {
          this.val$server.a(s.substring("data:image/png;base64,".length()));
        } else {
          bkz.c().error("Invalid server icon (unknown format)");
        }
      }
      else
      {
        this.val$server.a((String)null);
      }
      this.f = bcf.I();
      this.val$networkmanager.a(new kb(this.f));
      this.d = true;
    }
  }
  
  public void a(jx packetIn)
  {
    long i = this.f;
    long j = bcf.I();
    this.val$server.e = (j - i);
    this.val$networkmanager.a(new fa("Finished"));
  }
  
  public void a(eu reason)
  {
    if (!this.d)
    {
      bkz.c().error("Can't ping " + this.val$server.b + ": " + reason.c());
      this.val$server.d = (a.e + "Can't connect to server.");
      this.val$server.c = "";
      bkz.a(this.this$0, this.val$server);
    }
  }
}
