import net.minecraft.server.MinecraftServer;

public class mc
  implements jk
{
  private final MinecraftServer a;
  private final ek b;
  
  public mc(MinecraftServer ☃, ek ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public void a(jj ☃)
  {
    switch (mc.1.a[☃.a().ordinal()])
    {
    case 1: 
      this.b.a(el.d);
      if (☃.b() > 107)
      {
        fa ☃ = new fa("Outdated server! I'm still on 1.9");
        this.b.a(new jq(☃));
        this.b.a(☃);
      }
      else if (☃.b() < 107)
      {
        fa ☃ = new fa("Outdated client! Please use 1.9");
        this.b.a(new jq(☃));
        this.b.a(☃);
      }
      else
      {
        this.b.a(new md(this.a, this.b));
      }
      break;
    case 2: 
      this.b.a(el.c);
      this.b.a(new me(this.a, this.b));
      break;
    default: 
      throw new UnsupportedOperationException("Invalid intention " + ☃.a());
    }
  }
  
  public void a(eu ☃) {}
}
