import io.netty.buffer.ByteBuf;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import net.minecraft.server.MinecraftServer;

public abstract class ahj
  implements m
{
  private static final SimpleDateFormat a = new SimpleDateFormat("HH:mm:ss");
  private int b;
  private boolean c = true;
  private eu d = null;
  private String e = "";
  private String f = "@";
  private final n g = new n();
  
  public int k()
  {
    return this.b;
  }
  
  public void a(int ☃)
  {
    this.b = ☃;
  }
  
  public eu l()
  {
    return this.d == null ? new fa("") : this.d;
  }
  
  public void a(dn ☃)
  {
    ☃.a("Command", this.e);
    ☃.a("SuccessCount", this.b);
    ☃.a("CustomName", this.f);
    ☃.a("TrackOutput", this.c);
    if ((this.d != null) && (this.c)) {
      ☃.a("LastOutput", eu.a.a(this.d));
    }
    this.g.b(☃);
  }
  
  public void b(dn ☃)
  {
    this.e = ☃.l("Command");
    this.b = ☃.h("SuccessCount");
    if (☃.b("CustomName", 8)) {
      this.f = ☃.l("CustomName");
    }
    if (☃.b("TrackOutput", 1)) {
      this.c = ☃.p("TrackOutput");
    }
    if ((☃.b("LastOutput", 8)) && (this.c)) {
      try
      {
        this.d = eu.a.a(☃.l("LastOutput"));
      }
      catch (Throwable ☃)
      {
        this.d = new fa(☃.getMessage());
      }
    } else {
      this.d = null;
    }
    this.g.a(☃);
  }
  
  public boolean a(int ☃, String ☃)
  {
    return ☃ <= 2;
  }
  
  public void a(String ☃)
  {
    this.e = ☃;
    this.b = 0;
  }
  
  public String m()
  {
    return this.e;
  }
  
  public void a(aht ☃)
  {
    if (☃.E)
    {
      this.b = 0;
      return;
    }
    if ("Searge".equalsIgnoreCase(this.e))
    {
      this.d = new fa("#itzlipofutzli");
      this.b = 1;
      return;
    }
    MinecraftServer ☃ = h();
    if ((☃ != null) && (☃.M()) && (☃.ah()))
    {
      l ☃ = ☃.N();
      try
      {
        this.d = null;
        this.b = ☃.a(this, this.e);
      }
      catch (Throwable ☃)
      {
        b ☃ = b.a(☃, "Executing command block");
        c ☃ = ☃.a("Command to be executed");
        
        ☃.a("Command", new Callable()
        {
          public String a()
            throws Exception
          {
            return ahj.this.m();
          }
        });
        ☃.a("Name", new Callable()
        {
          public String a()
            throws Exception
          {
            return ahj.this.h_();
          }
        });
        throw new e(☃);
      }
    }
    else
    {
      this.b = 0;
    }
  }
  
  public String h_()
  {
    return this.f;
  }
  
  public eu i_()
  {
    return new fa(h_());
  }
  
  public void b(String ☃)
  {
    this.f = ☃;
  }
  
  public void a(eu ☃)
  {
    if ((this.c) && (e() != null) && (!e().E))
    {
      this.d = new fa("[" + a.format(new Date()) + "] ").a(☃);
      i();
    }
  }
  
  public boolean z_()
  {
    MinecraftServer ☃ = h();
    return (☃ == null) || (!☃.M()) || (☃.d[0].U().b("commandBlockOutput"));
  }
  
  public void a(n.a ☃, int ☃)
  {
    this.g.a(h(), this, ☃, ☃);
  }
  
  public abstract void i();
  
  public abstract int j();
  
  public abstract void a(ByteBuf paramByteBuf);
  
  public void b(eu ☃)
  {
    this.d = ☃;
  }
  
  public void a(boolean ☃)
  {
    this.c = ☃;
  }
  
  public boolean n()
  {
    return this.c;
  }
  
  public boolean a(zj ☃)
  {
    if (!☃.bJ.d) {
      return false;
    }
    if (☃.e().E) {
      ☃.a(this);
    }
    return true;
  }
  
  public n o()
  {
    return this.g;
  }
}
