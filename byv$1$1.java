import org.apache.logging.log4j.Logger;
import paulscode.sound.SoundSystemLogger;

class byv$1$1
  extends SoundSystemLogger
{
  byv$1$1(byv.1 param1) {}
  
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
}
