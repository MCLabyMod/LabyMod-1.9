import net.minecraft.server.MinecraftServer;

class aqn$2
  implements m
{
  aqn$2(aqn this$0, zj paramzj) {}
  
  public String h_()
  {
    return this.val$playerIn.h_();
  }
  
  public eu i_()
  {
    return this.val$playerIn.i_();
  }
  
  public void a(eu component) {}
  
  public boolean a(int permLevel, String commandName)
  {
    return permLevel <= 2;
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
    return this.val$playerIn.e();
  }
  
  public rr f()
  {
    return this.val$playerIn;
  }
  
  public boolean z_()
  {
    return false;
  }
  
  public void a(n.a type, int amount)
  {
    if ((this.this$0.b != null) && (!this.this$0.b.E)) {
      aqn.a(this.this$0).a(this.this$0.b.u(), this, type, amount);
    }
  }
  
  public MinecraftServer h()
  {
    return this.val$playerIn.h();
  }
}
