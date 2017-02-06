import java.util.HashMap;
import java.util.Map;
import shadersmod.client.Shaders;

public class CrashReporter
{
  public static void onCrashReport(b crashReport, c category)
  {
    try
    {
      bch settings = Config.getGameSettings();
      if (settings == null) {
        return;
      }
      if (!settings.r) {
        return;
      }
      Throwable cause = crashReport.b();
      if (cause == null) {
        return;
      }
      if (cause.getClass() == Throwable.class) {
        return;
      }
      if (cause.getClass().getName().contains(".fml.client.SplashProgress")) {
        return;
      }
      extendCrashReport(category);
      
      String url = "http://optifine.net/crashReport";
      String reportStr = makeReport(crashReport);
      byte[] content = reportStr.getBytes("ASCII");
      IFileUploadListener listener = new IFileUploadListener()
      {
        public void fileUploadFinished(String url, byte[] content, Throwable exception) {}
      };
      Map headers = new HashMap();
      headers.put("OF-Version", Config.getVersion());
      headers.put("OF-Summary", makeSummary(crashReport));
      
      FileUploadThread fut = new FileUploadThread(url, headers, content, listener);
      fut.setPriority(10);
      fut.start();
      
      Thread.sleep(1000L);
    }
    catch (Exception e)
    {
      Config.dbg(e.getClass().getName() + ": " + e.getMessage());
    }
  }
  
  private static String makeReport(b crashReport)
  {
    StringBuffer sb = new StringBuffer();
    sb.append("OptiFineVersion: " + Config.getVersion() + "\n");
    sb.append("Summary: " + makeSummary(crashReport) + "\n");
    sb.append("\n");
    sb.append(crashReport.e());
    sb.append("\n");
    
    return sb.toString();
  }
  
  private static String makeSummary(b crashReport)
  {
    Throwable t = crashReport.b();
    if (t == null) {
      return "Unknown";
    }
    StackTraceElement[] traces = t.getStackTrace();
    String firstTrace = "unknown";
    if (traces.length > 0) {
      firstTrace = traces[0].toString().trim();
    }
    String sum = t.getClass().getName() + ": " + t.getMessage() + " (" + crashReport.a() + ")" + " [" + firstTrace + "]";
    
    return sum;
  }
  
  public static void extendCrashReport(c cat)
  {
    cat.a("OptiFine Version", Config.getVersion());
    if (Config.getGameSettings() != null)
    {
      cat.a("Render Distance Chunks", "" + Config.getChunkViewDistance());
      cat.a("Mipmaps", "" + Config.getMipmapLevels());
      cat.a("Anisotropic Filtering", "" + Config.getAnisotropicFilterLevel());
      cat.a("Antialiasing", "" + Config.getAntialiasingLevel());
      cat.a("Multitexture", "" + Config.isMultiTexture());
    }
    cat.a("Shaders", "" + Shaders.getShaderPackName());
    cat.a("OpenGlVersion", "" + Config.openGlVersion);
    cat.a("OpenGlRenderer", "" + Config.openGlRenderer);
    cat.a("OpenGlVendor", "" + Config.openGlVendor);
    cat.a("CpuCount", "" + Config.getAvailableProcessors());
  }
}
