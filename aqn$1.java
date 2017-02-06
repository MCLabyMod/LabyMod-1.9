import net.minecraft.server.MinecraftServer;

class aqn$1
  implements m
{
  aqn$1(aqn this$0) {}
  
  public String h_()
  {
    return "Sign";
  }
  
  public eu i_()
  {
    return new fa(h_());
  }
  
  public void a(eu component) {}
  
  public boolean a(int permLevel, String commandName)
  {
    return true;
  }
  
  public cj c()
  {
    return this.this$0.c;
  }
  
  public bbj d()
  {
    return new bbj(this.this$0.c.p() + 0.5D, this.this$0.c.q() + 0.5D, this.this$0.c.r() + 0.5D);
  }
  
  public aht e()
  {
    return this.this$0.b;
  }
  
  public rr f()
  {
    return null;
  }
  
  public boolean z_()
  {
    return false;
  }
  
  public void a(n.a type, int amount) {}
  
  public MinecraftServer h()
  {
    return this.this$0.b.u();
  }
}
