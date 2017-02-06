import net.minecraft.server.MinecraftServer;

public class me
  implements ka
{
  private static final eu a = new fa("Status request has been handled.");
  private final MinecraftServer b;
  private final ek c;
  private boolean d;
  
  public me(MinecraftServer ☃, ek ☃)
  {
    this.b = ☃;
    this.c = ☃;
  }
  
  public void a(eu ☃) {}
  
  public void a(kc ☃)
  {
    if (this.d)
    {
      this.c.a(a);
      return;
    }
    this.d = true;
    this.c.a(new jy(this.b.aB()));
  }
  
  public void a(kb ☃)
  {
    this.c.a(new jx(☃.a()));
    this.c.a(a);
  }
}
