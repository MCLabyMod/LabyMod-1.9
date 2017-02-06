public class bkx
{
  public String a;
  public String b;
  public String c;
  public String d;
  public long e;
  public int f = 107;
  public String g = "1.9";
  public boolean h;
  public String i;
  private bkx.a j = bkx.a.c;
  private String k;
  public String serverCommand = "";
  private boolean l;
  
  public bkx(String name, String ip, boolean isLan, String command)
  {
    this.a = name;
    this.b = ip;
    this.l = isLan;
    this.serverCommand = command;
  }
  
  public bkx(String name, String ip, boolean isLan)
  {
    this.a = name;
    this.b = ip;
    this.l = isLan;
  }
  
  public dn a()
  {
    dn nbttagcompound = new dn();
    nbttagcompound.a("name", this.a);
    nbttagcompound.a("ip", this.b);
    nbttagcompound.a("command", this.serverCommand);
    if (this.k != null) {
      nbttagcompound.a("icon", this.k);
    }
    if (this.j == bkx.a.a) {
      nbttagcompound.a("acceptTextures", true);
    } else if (this.j == bkx.a.b) {
      nbttagcompound.a("acceptTextures", false);
    }
    return nbttagcompound;
  }
  
  public bkx.a b()
  {
    return this.j;
  }
  
  public void a(bkx.a mode)
  {
    this.j = mode;
  }
  
  public static bkx a(dn nbtCompound)
  {
    bkx serverdata = new bkx(nbtCompound.l("name"), nbtCompound.l("ip"), false, nbtCompound.l("command"));
    if (nbtCompound.b("icon", 8)) {
      serverdata.a(nbtCompound.l("icon"));
    }
    if (nbtCompound.b("acceptTextures", 1))
    {
      if (nbtCompound.p("acceptTextures")) {
        serverdata.a(bkx.a.a);
      } else {
        serverdata.a(bkx.a.b);
      }
    }
    else {
      serverdata.a(bkx.a.c);
    }
    return serverdata;
  }
  
  public String c()
  {
    return this.k;
  }
  
  public void a(String icon)
  {
    this.k = icon;
  }
  
  public boolean d()
  {
    return this.l;
  }
  
  public void a(bkx serverDataIn)
  {
    this.b = serverDataIn.b;
    this.a = serverDataIn.a;
    a(serverDataIn.b());
    this.k = serverDataIn.k;
    this.l = serverDataIn.l;
  }
  
  public static enum a
  {
    private final eu d;
    
    private a(String name)
    {
      this.d = new fb("addServer.resourcePack." + name, new Object[0]);
    }
    
    public eu a()
    {
      return this.d;
    }
  }
}
