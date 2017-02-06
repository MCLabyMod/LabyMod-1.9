import io.netty.buffer.ByteBuf;
import net.minecraft.server.MinecraftServer;

class apy$1
  extends ahj
{
  apy$1(apy paramapy) {}
  
  public cj c()
  {
    return this.a.c;
  }
  
  public bbj d()
  {
    return new bbj(this.a.c.p() + 0.5D, this.a.c.q() + 0.5D, this.a.c.r() + 0.5D);
  }
  
  public aht e()
  {
    return this.a.D();
  }
  
  public void a(String ☃)
  {
    super.a(☃);
    this.a.v_();
  }
  
  public void i()
  {
    arc ☃ = this.a.b.o(this.a.c);
    this.a.D().a(this.a.c, ☃, ☃, 3);
  }
  
  public int j()
  {
    return 0;
  }
  
  public void a(ByteBuf ☃)
  {
    ☃.writeInt(this.a.c.p());
    ☃.writeInt(this.a.c.q());
    ☃.writeInt(this.a.c.r());
  }
  
  public rr f()
  {
    return null;
  }
  
  public MinecraftServer h()
  {
    return this.a.b.u();
  }
}
