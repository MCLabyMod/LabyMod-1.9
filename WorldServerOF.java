import net.minecraft.server.MinecraftServer;

public class WorldServerOF
  extends lp
{
  private MinecraftServer mcServer;
  
  public WorldServerOF(MinecraftServer par1MinecraftServer, azi par2iSaveHandler, azh worldInfo, int par4, oo par6Profiler)
  {
    super(par1MinecraftServer, par2iSaveHandler, worldInfo, par4, par6Profiler);
    
    this.mcServer = par1MinecraftServer;
  }
  
  public void d()
  {
    super.d();
    if (!Config.isTimeDefault()) {
      fixWorldTime();
    }
    if (Config.waterOpacityChanged)
    {
      Config.waterOpacityChanged = false;
      
      ClearWater.updateWaterOpacity(Config.getGameSettings(), this);
    }
  }
  
  protected void t()
  {
    if (!Config.isWeatherEnabled()) {
      fixWorldWeather();
    }
    super.t();
  }
  
  private void fixWorldWeather()
  {
    if ((this.x.o()) || (this.x.m()))
    {
      this.x.g(0);
      this.x.b(false);
      k(0.0F);
      
      this.x.f(0);
      this.x.a(false);
      i(0.0F);
      
      this.mcServer.al().a(new gn(2, 0.0F));
      
      this.mcServer.al().a(new gn(7, 0.0F));
      
      this.mcServer.al().a(new gn(8, 0.0F));
    }
  }
  
  private void fixWorldTime()
  {
    if (this.x.q().a() != 1) {
      return;
    }
    long time = Q();
    long timeOfDay = time % 24000L;
    if (Config.isTimeDayOnly())
    {
      if (timeOfDay <= 1000L) {
        b(time - timeOfDay + 1001L);
      }
      if (timeOfDay >= 11000L) {
        b(time - timeOfDay + 24001L);
      }
    }
    if (Config.isTimeNightOnly())
    {
      if (timeOfDay <= 14000L) {
        b(time - timeOfDay + 14001L);
      }
      if (timeOfDay >= 22000L) {
        b(time - timeOfDay + 24000L + 14001L);
      }
    }
  }
}
