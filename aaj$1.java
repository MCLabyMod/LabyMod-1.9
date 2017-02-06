import io.netty.buffer.ByteBuf;
import net.minecraft.server.MinecraftServer;

class aaj$1
  extends ahj
{
  aaj$1(aaj paramaaj) {}
  
  public void i()
  {
    this.a.R().b(aaj.k(), m());
    this.a.R().b(aaj.l(), l());
  }
  
  public int j()
  {
    return 1;
  }
  
  public void a(ByteBuf ☃)
  {
    ☃.writeInt(this.a.O());
  }
  
  public cj c()
  {
    return new cj(this.a.p, this.a.q + 0.5D, this.a.r);
  }
  
  public bbj d()
  {
    return new bbj(this.a.p, this.a.q, this.a.r);
  }
  
  public aht e()
  {
    return this.a.l;
  }
  
  public rr f()
  {
    return this.a;
  }
  
  public MinecraftServer h()
  {
    return this.a.l.u();
  }
}
