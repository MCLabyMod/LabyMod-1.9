import org.apache.logging.log4j.Logger;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemLogger;

class byv$1
  implements Runnable
{
  byv$1(byv parambyv) {}
  
  public void run()
  {
    SoundSystemConfig.setLogger(new SoundSystemLogger()
    {
      public void message(String ☃, int ☃)
      {
        if (!☃.isEmpty()) {
          byv.g().info(☃);
        }
      }
      
      public void importantMessage(String ☃, int ☃)
      {
        if (!☃.isEmpty()) {
          byv.g().warn(☃);
        }
      }
      
      public void errorMessage(String ☃, String ☃, int ☃)
      {
        if (!☃.isEmpty())
        {
          byv.g().error("Error in class '" + ☃ + "'");
          byv.g().error(☃);
        }
      }
    });
    byv.a(this.a, new byv.a(this.a, null));
    byv.a(this.a, true);
    byv.b(this.a).setMasterVolume(byv.a(this.a).a(nh.a));
    byv.g().info(byv.h(), "Sound engine started");
  }
}
