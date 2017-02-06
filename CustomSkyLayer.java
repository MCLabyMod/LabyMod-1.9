import java.util.Properties;

public class CustomSkyLayer
{
  public String source = null;
  private int startFadeIn = -1;
  private int endFadeIn = -1;
  private int startFadeOut = -1;
  private int endFadeOut = -1;
  private int blend = 1;
  private boolean rotate = false;
  private float speed = 1.0F;
  private float[] axis = DEFAULT_AXIS;
  private RangeListInt days = null;
  private int daysLoop = 8;
  public int textureId = -1;
  public static final float[] DEFAULT_AXIS = { 1.0F, 0.0F, 0.0F };
  
  public CustomSkyLayer(Properties props, String defSource)
  {
    ConnectedParser cp = new ConnectedParser("CustomSky");
    
    this.source = props.getProperty("source", defSource);
    this.startFadeIn = parseTime(props.getProperty("startFadeIn"));
    this.endFadeIn = parseTime(props.getProperty("endFadeIn"));
    this.startFadeOut = parseTime(props.getProperty("startFadeOut"));
    this.endFadeOut = parseTime(props.getProperty("endFadeOut"));
    this.blend = Blender.parseBlend(props.getProperty("blend"));
    this.rotate = parseBoolean(props.getProperty("rotate"), true);
    this.speed = parseFloat(props.getProperty("speed"), 1.0F);
    this.axis = parseAxis(props.getProperty("axis"), DEFAULT_AXIS);
    this.days = cp.parseRangeListInt(props.getProperty("days"));
    this.daysLoop = cp.parseInt(props.getProperty("daysLoop"), 8);
  }
  
  private int parseTime(String str)
  {
    if (str == null) {
      return -1;
    }
    String[] strs = Config.tokenize(str, ":");
    if (strs.length != 2)
    {
      Config.warn("Invalid time: " + str);
      return -1;
    }
    String hourStr = strs[0];
    String minStr = strs[1];
    
    int hour = Config.parseInt(hourStr, -1);
    int min = Config.parseInt(minStr, -1);
    if ((hour < 0) || (hour > 23) || (min < 0) || (min > 59))
    {
      Config.warn("Invalid time: " + str);
      return -1;
    }
    hour -= 6;
    if (hour < 0) {
      hour += 24;
    }
    int time = hour * 1000 + (int)(min / 60.0D * 1000.0D);
    
    return time;
  }
  
  private boolean parseBoolean(String str, boolean defVal)
  {
    if (str == null) {
      return defVal;
    }
    if (str.toLowerCase().equals("true")) {
      return true;
    }
    if (str.toLowerCase().equals("false")) {
      return false;
    }
    Config.warn("Unknown boolean: " + str);
    
    return defVal;
  }
  
  private float parseFloat(String str, float defVal)
  {
    if (str == null) {
      return defVal;
    }
    float val = Config.parseFloat(str, Float.MIN_VALUE);
    if (val == Float.MIN_VALUE)
    {
      Config.warn("Invalid value: " + str);
      return defVal;
    }
    return val;
  }
  
  private float[] parseAxis(String str, float[] defVal)
  {
    if (str == null) {
      return defVal;
    }
    String[] strs = Config.tokenize(str, " ");
    if (strs.length != 3)
    {
      Config.warn("Invalid axis: " + str);
      return defVal;
    }
    float[] fs = new float[3];
    for (int i = 0; i < strs.length; i++)
    {
      fs[i] = Config.parseFloat(strs[i], Float.MIN_VALUE);
      if (fs[i] == Float.MIN_VALUE)
      {
        Config.warn("Invalid axis: " + str);
        return defVal;
      }
      if ((fs[i] < -1.0F) || (fs[i] > 1.0F))
      {
        Config.warn("Invalid axis values: " + str);
        return defVal;
      }
    }
    float ax = fs[0];
    float ay = fs[1];
    float az = fs[2];
    if (ax * ax + ay * ay + az * az < 1.0E-5F)
    {
      Config.warn("Invalid axis values: " + str);
      return defVal;
    }
    float[] as = { az, ay, -ax };
    
    return as;
  }
  
  public boolean isValid(String path)
  {
    if (this.source == null)
    {
      Config.warn("No source texture: " + path);
      return false;
    }
    this.source = TextureUtils.fixResourcePath(this.source, TextureUtils.getBasePath(path));
    if ((this.startFadeIn < 0) || (this.endFadeIn < 0) || (this.endFadeOut < 0))
    {
      Config.warn("Invalid times, required are: startFadeIn, endFadeIn and endFadeOut.");
      return false;
    }
    int timeFadeIn = normalizeTime(this.endFadeIn - this.startFadeIn);
    if (this.startFadeOut < 0)
    {
      this.startFadeOut = normalizeTime(this.endFadeOut - timeFadeIn);
      if (timeBetween(this.startFadeOut, this.startFadeIn, this.endFadeIn)) {
        this.startFadeOut = this.endFadeIn;
      }
    }
    int timeOn = normalizeTime(this.startFadeOut - this.endFadeIn);
    
    int timeFadeOut = normalizeTime(this.endFadeOut - this.startFadeOut);
    
    int timeOff = normalizeTime(this.startFadeIn - this.endFadeOut);
    
    int timeSum = timeFadeIn + timeOn + timeFadeOut + timeOff;
    if (timeSum != 24000)
    {
      Config.warn("Invalid fadeIn/fadeOut times, sum is not 24h: " + timeSum);
      return false;
    }
    if (this.speed < 0.0F)
    {
      Config.warn("Invalid speed: " + this.speed);
      return false;
    }
    if (this.daysLoop <= 0)
    {
      Config.warn("Invalid daysLoop: " + this.daysLoop);
      return false;
    }
    return true;
  }
  
  private int normalizeTime(int timeMc)
  {
    while (timeMc >= 24000) {
      timeMc -= 24000;
    }
    while (timeMc < 0) {
      timeMc += 24000;
    }
    return timeMc;
  }
  
  public void render(int timeOfDay, float celestialAngle, float rainBrightness)
  {
    float brightness = rainBrightness * getFadeBrightness(timeOfDay);
    brightness = Config.limit(brightness, 0.0F, 1.0F);
    if (brightness < 1.0E-4F) {
      return;
    }
    bni.i(this.textureId);
    
    Blender.setupBlend(this.blend, brightness);
    
    bni.G();
    if (this.rotate) {
      bni.b(celestialAngle * 360.0F * this.speed, this.axis[0], this.axis[1], this.axis[2]);
    }
    bnu tess = bnu.a();
    
    bni.b(90.0F, 1.0F, 0.0F, 0.0F);
    bni.b(-90.0F, 0.0F, 0.0F, 1.0F);
    renderSide(tess, 4);
    
    bni.G();
    bni.b(90.0F, 1.0F, 0.0F, 0.0F);
    renderSide(tess, 1);
    bni.H();
    
    bni.G();
    bni.b(-90.0F, 1.0F, 0.0F, 0.0F);
    renderSide(tess, 0);
    bni.H();
    
    bni.b(90.0F, 0.0F, 0.0F, 1.0F);
    renderSide(tess, 5);
    
    bni.b(90.0F, 0.0F, 0.0F, 1.0F);
    renderSide(tess, 2);
    
    bni.b(90.0F, 0.0F, 0.0F, 1.0F);
    renderSide(tess, 3);
    
    bni.H();
  }
  
  private float getFadeBrightness(int timeOfDay)
  {
    if (timeBetween(timeOfDay, this.startFadeIn, this.endFadeIn))
    {
      int timeFadeIn = normalizeTime(this.endFadeIn - this.startFadeIn);
      int timeDiff = normalizeTime(timeOfDay - this.startFadeIn);
      return timeDiff / timeFadeIn;
    }
    if (timeBetween(timeOfDay, this.endFadeIn, this.startFadeOut)) {
      return 1.0F;
    }
    if (timeBetween(timeOfDay, this.startFadeOut, this.endFadeOut))
    {
      int timeFadeOut = normalizeTime(this.endFadeOut - this.startFadeOut);
      int timeDiff = normalizeTime(timeOfDay - this.startFadeOut);
      return 1.0F - timeDiff / timeFadeOut;
    }
    return 0.0F;
  }
  
  private void renderSide(bnu tess, int side)
  {
    bmz wr = tess.c();
    double tx = side % 3 / 3.0D;
    double ty = side / 3 / 2.0D;
    wr.a(7, bvp.g);
    wr.b(-100.0D, -100.0D, -100.0D).a(tx, ty).d();
    wr.b(-100.0D, -100.0D, 100.0D).a(tx, ty + 0.5D).d();
    wr.b(100.0D, -100.0D, 100.0D).a(tx + 0.3333333333333333D, ty + 0.5D).d();
    wr.b(100.0D, -100.0D, -100.0D).a(tx + 0.3333333333333333D, ty).d();
    tess.b();
  }
  
  public boolean isActive(aht world, int timeOfDay)
  {
    if (timeBetween(timeOfDay, this.endFadeOut, this.startFadeIn)) {
      return false;
    }
    if (this.days != null)
    {
      long time = world.Q();
      long timeShift = time - this.startFadeIn;
      while (timeShift < 0L) {
        timeShift += 24000 * this.daysLoop;
      }
      int day = (int)(timeShift / 24000L);
      int dayOfLoop = day % this.daysLoop;
      if (!this.days.isInRange(dayOfLoop)) {
        return false;
      }
    }
    return true;
  }
  
  private boolean timeBetween(int timeOfDay, int timeStart, int timeEnd)
  {
    if (timeStart <= timeEnd) {
      return (timeOfDay >= timeStart) && (timeOfDay <= timeEnd);
    }
    return (timeOfDay >= timeStart) || (timeOfDay <= timeEnd);
  }
}
