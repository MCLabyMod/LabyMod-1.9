import org.lwjgl.opengl.GL11;

public class Lagometer
{
  private static bcf mc;
  private static bch gameSettings;
  private static oo profiler;
  public static boolean active = false;
  public static Lagometer.TimerNano timerTick = new Lagometer.TimerNano();
  public static Lagometer.TimerNano timerScheduledExecutables = new Lagometer.TimerNano();
  public static Lagometer.TimerNano timerChunkUpload = new Lagometer.TimerNano();
  public static Lagometer.TimerNano timerChunkUpdate = new Lagometer.TimerNano();
  public static Lagometer.TimerNano timerVisibility = new Lagometer.TimerNano();
  public static Lagometer.TimerNano timerTerrain = new Lagometer.TimerNano();
  public static Lagometer.TimerNano timerServer = new Lagometer.TimerNano();
  private static long[] timesFrame = new long['Ȁ'];
  private static long[] timesTick = new long['Ȁ'];
  private static long[] timesScheduledExecutables = new long['Ȁ'];
  private static long[] timesChunkUpload = new long['Ȁ'];
  private static long[] timesChunkUpdate = new long['Ȁ'];
  private static long[] timesVisibility = new long['Ȁ'];
  private static long[] timesTerrain = new long['Ȁ'];
  private static long[] timesServer = new long['Ȁ'];
  private static boolean[] gcs = new boolean['Ȁ'];
  private static int numRecordedFrameTimes = 0;
  private static long prevFrameTimeNano = -1L;
  private static long renderTimeNano = 0L;
  private static long memTimeStartMs = System.currentTimeMillis();
  private static long memStart = getMemoryUsed();
  private static long memTimeLast = memTimeStartMs;
  private static long memLast = memStart;
  private static long memTimeDiffMs = 1L;
  private static long memDiff = 0L;
  private static int memMbSec = 0;
  
  public static boolean updateMemoryAllocation()
  {
    long timeNowMs = System.currentTimeMillis();
    long memNow = getMemoryUsed();
    boolean gc = false;
    if (memNow < memLast)
    {
      double memDiffMb = memDiff / 1000000.0D;
      double timeDiffSec = memTimeDiffMs / 1000.0D;
      int mbSec = (int)(memDiffMb / timeDiffSec);
      if (mbSec > 0) {
        memMbSec = mbSec;
      }
      memTimeStartMs = timeNowMs;
      memStart = memNow;
      memTimeDiffMs = 0L;
      memDiff = 0L;
      gc = true;
    }
    else
    {
      memTimeDiffMs = timeNowMs - memTimeStartMs;
      memDiff = memNow - memStart;
    }
    memTimeLast = timeNowMs;
    memLast = memNow;
    return gc;
  }
  
  private static long getMemoryUsed()
  {
    Runtime r = Runtime.getRuntime();
    return r.totalMemory() - r.freeMemory();
  }
  
  public static void updateLagometer()
  {
    if (mc == null)
    {
      mc = bcf.z();
      gameSettings = mc.u;
      profiler = mc.B;
    }
    if ((!gameSettings.aq) || (!gameSettings.ofLagometer))
    {
      active = false;
      prevFrameTimeNano = -1L;
      return;
    }
    active = true;
    
    long timeNowNano = System.nanoTime();
    if (prevFrameTimeNano == -1L)
    {
      prevFrameTimeNano = timeNowNano;
      return;
    }
    int frameIndex = numRecordedFrameTimes & timesFrame.length - 1;
    numRecordedFrameTimes += 1;
    
    boolean gc = updateMemoryAllocation();
    
    timesFrame[frameIndex] = (timeNowNano - prevFrameTimeNano - renderTimeNano);
    timesTick[frameIndex] = timerTick.timeNano;
    timesScheduledExecutables[frameIndex] = timerScheduledExecutables.timeNano;
    timesChunkUpload[frameIndex] = timerChunkUpload.timeNano;
    timesChunkUpdate[frameIndex] = timerChunkUpdate.timeNano;
    timesVisibility[frameIndex] = timerVisibility.timeNano;
    timesTerrain[frameIndex] = timerTerrain.timeNano;
    timesServer[frameIndex] = timerServer.timeNano;
    gcs[frameIndex] = gc;
    
    timerTick.reset();
    timerScheduledExecutables.reset();
    timerVisibility.reset();
    timerChunkUpdate.reset();
    timerChunkUpload.reset();
    timerTerrain.reset();
    timerServer.reset();
    
    prevFrameTimeNano = System.nanoTime();
  }
  
  public static void showLagometer(bcx scaledResolution)
  {
    if ((gameSettings == null) || (!gameSettings.ofLagometer)) {
      return;
    }
    long timeRenderStartNano = System.nanoTime();
    
    bni.m(256);
    bni.n(5889);
    
    bni.G();
    
    bni.h();
    bni.F();
    bni.a(0.0D, mc.d, mc.e, 0.0D, 1000.0D, 3000.0D);
    bni.n(5888);
    
    bni.G();
    
    bni.F();
    bni.c(0.0F, 0.0F, -2000.0F);
    GL11.glLineWidth(1.0F);
    bni.z();
    
    bnu tess = bnu.a();
    bmz tessellator = tess.c();
    tessellator.a(1, bvp.f);
    for (int frameNum = 0; frameNum < timesFrame.length; frameNum++)
    {
      int lum = (frameNum - numRecordedFrameTimes & timesFrame.length - 1) * 100 / timesFrame.length;
      lum += 155;
      
      float baseHeight = mc.e;
      
      long heightFrame = 0L;
      if (gcs[frameNum] != 0)
      {
        heightFrame = renderTime(frameNum, timesFrame[frameNum], lum, lum / 2, 0, baseHeight, tessellator);
      }
      else
      {
        heightFrame = renderTime(frameNum, timesFrame[frameNum], lum, lum, lum, baseHeight, tessellator);
        
        baseHeight -= (float)renderTime(frameNum, timesServer[frameNum], lum / 2, lum / 2, lum / 2, baseHeight, tessellator);
        
        baseHeight -= (float)renderTime(frameNum, timesTerrain[frameNum], 0, lum, 0, baseHeight, tessellator);
        
        baseHeight -= (float)renderTime(frameNum, timesVisibility[frameNum], lum, lum, 0, baseHeight, tessellator);
        
        baseHeight -= (float)renderTime(frameNum, timesChunkUpdate[frameNum], lum, 0, 0, baseHeight, tessellator);
        
        baseHeight -= (float)renderTime(frameNum, timesChunkUpload[frameNum], lum, 0, lum, baseHeight, tessellator);
        
        baseHeight -= (float)renderTime(frameNum, timesScheduledExecutables[frameNum], 0, 0, lum, baseHeight, tessellator);
        
        baseHeight -= (float)renderTime(frameNum, timesTick[frameNum], 0, lum, lum, baseHeight, tessellator);
      }
    }
    tess.b();
    
    bni.n(5889);
    
    bni.H();
    
    bni.n(5888);
    
    bni.H();
    
    bni.y();
    
    float lumMem = 1.0F - (float)((System.currentTimeMillis() - memTimeStartMs) / 1000.0D);
    lumMem = Config.limit(lumMem, 0.0F, 1.0F);
    
    int memColR = (int)(170.0F + lumMem * 85.0F);
    int memColG = (int)(100.0F + lumMem * 55.0F);
    int memColB = (int)(10.0F + lumMem * 10.0F);
    int colMem = memColR << 16 | memColG << 8 | memColB;
    int posX = 512 / scaledResolution.e() + 2;
    int posY = mc.e / scaledResolution.e() - 8;
    bcu.a(posX - 1, posY - 1, posX + 50, posY + 10, -1605349296);
    mc.k.a(" " + memMbSec + " MB/s", posX, posY, colMem);
    
    renderTimeNano = System.nanoTime() - timeRenderStartNano;
  }
  
  private static long renderTime(int frameNum, long time, int r, int g, int b, float baseHeight, bmz tessellator)
  {
    long heightTime = time / 200000L;
    if (heightTime < 3L) {
      return 0L;
    }
    tessellator.b(frameNum + 0.5F, baseHeight - (float)heightTime + 0.5F, 0.0D).b(r, g, b, 255).d();
    tessellator.b(frameNum + 0.5F, baseHeight + 0.5F, 0.0D).b(r, g, b, 255).d();
    return heightTime;
  }
  
  public static boolean isActive()
  {
    return active;
  }
  
  public static class TimerNano
  {
    public long timeStartNano = 0L;
    public long timeNano = 0L;
    
    public void start()
    {
      if (!Lagometer.active) {
        return;
      }
      if (this.timeStartNano == 0L) {
        this.timeStartNano = System.nanoTime();
      }
    }
    
    public void end()
    {
      if (!Lagometer.active) {
        return;
      }
      if (this.timeStartNano != 0L)
      {
        this.timeNano += System.nanoTime() - this.timeStartNano;
        this.timeStartNano = 0L;
      }
    }
    
    private void reset()
    {
      this.timeNano = 0L;
      this.timeStartNano = 0L;
    }
  }
}
