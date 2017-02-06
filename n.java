import net.minecraft.server.MinecraftServer;

public class n
{
  private static final int a = n.a.values().length;
  private static final String[] b = new String[a];
  private String[] c = b;
  private String[] d = b;
  
  public void a(MinecraftServer ☃, final m ☃, n.a ☃, int ☃)
  {
    String ☃ = this.c[☃.a()];
    if (☃ == null) {
      return;
    }
    m ☃ = new m()
    {
      public String h_()
      {
        return ☃.h_();
      }
      
      public eu i_()
      {
        return ☃.i_();
      }
      
      public void a(eu ☃)
      {
        ☃.a(☃);
      }
      
      public boolean a(int ☃, String ☃)
      {
        return true;
      }
      
      public cj c()
      {
        return ☃.c();
      }
      
      public bbj d()
      {
        return ☃.d();
      }
      
      public aht e()
      {
        return ☃.e();
      }
      
      public rr f()
      {
        return ☃.f();
      }
      
      public boolean z_()
      {
        return ☃.z_();
      }
      
      public void a(n.a ☃, int ☃)
      {
        ☃.a(☃, ☃);
      }
      
      public MinecraftServer h()
      {
        return ☃.h();
      }
    };
    String ☃;
    try
    {
      ☃ = i.e(☃, ☃, ☃);
    }
    catch (ca ☃)
    {
      return;
    }
    String ☃ = this.d[☃.a()];
    if (☃ == null) {
      return;
    }
    bbp ☃ = ☃.e().ad();
    bbl ☃ = ☃.b(☃);
    if (☃ == null) {
      return;
    }
    if (!☃.b(☃, ☃)) {
      return;
    }
    bbn ☃ = ☃.c(☃, ☃);
    ☃.c(☃);
  }
  
  public void a(dn ☃)
  {
    if (!☃.b("CommandStats", 10)) {
      return;
    }
    dn ☃ = ☃.o("CommandStats");
    for (n.a ☃ : n.a.values())
    {
      String ☃ = ☃.b() + "Name";
      String ☃ = ☃.b() + "Objective";
      if ((☃.b(☃, 8)) && (☃.b(☃, 8)))
      {
        String ☃ = ☃.l(☃);
        String ☃ = ☃.l(☃);
        a(this, ☃, ☃, ☃);
      }
    }
  }
  
  public void b(dn ☃)
  {
    dn ☃ = new dn();
    for (n.a ☃ : n.a.values())
    {
      String ☃ = this.c[☃.a()];
      String ☃ = this.d[☃.a()];
      if ((☃ != null) && (☃ != null))
      {
        ☃.a(☃.b() + "Name", ☃);
        ☃.a(☃.b() + "Objective", ☃);
      }
    }
    if (!☃.c_()) {
      ☃.a("CommandStats", ☃);
    }
  }
  
  public static void a(n ☃, n.a ☃, String ☃, String ☃)
  {
    if ((☃ == null) || (☃.isEmpty()) || (☃ == null) || (☃.isEmpty()))
    {
      a(☃, ☃);
      return;
    }
    if ((☃.c == b) || (☃.d == b))
    {
      ☃.c = new String[a];
      ☃.d = new String[a];
    }
    ☃.c[☃.a()] = ☃;
    ☃.d[☃.a()] = ☃;
  }
  
  private static void a(n ☃, n.a ☃)
  {
    if ((☃.c == b) || (☃.d == b)) {
      return;
    }
    ☃.c[☃.a()] = null;
    ☃.d[☃.a()] = null;
    
    boolean ☃ = true;
    for (n.a ☃ : n.a.values()) {
      if ((☃.c[☃.a()] != null) && (☃.d[☃.a()] != null))
      {
        ☃ = false;
        break;
      }
    }
    if (☃)
    {
      ☃.c = b;
      ☃.d = b;
    }
  }
  
  public void a(n ☃)
  {
    for (n.a ☃ : ) {
      a(this, ☃, ☃.c[☃.a()], ☃.d[☃.a()]);
    }
  }
  
  public static enum a
  {
    final int f;
    final String g;
    
    private a(int ☃, String ☃)
    {
      this.f = ☃;
      this.g = ☃;
    }
    
    public int a()
    {
      return this.f;
    }
    
    public String b()
    {
      return this.g;
    }
    
    public static String[] c()
    {
      String[] ☃ = new String[values().length];
      int ☃ = 0;
      for (a ☃ : values()) {
        ☃[(☃++)] = ☃.b();
      }
      return ☃;
    }
    
    public static a a(String ☃)
    {
      for (a ☃ : ) {
        if (☃.b().equals(☃)) {
          return ☃;
        }
      }
      return null;
    }
  }
}
