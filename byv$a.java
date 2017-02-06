import java.util.HashMap;
import paulscode.sound.Library;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.Source;

class byv$a
  extends SoundSystem
{
  private byv$a(byv parambyv) {}
  
  public boolean playing(String ☃)
  {
    synchronized (SoundSystemConfig.THREAD_SYNC)
    {
      if (this.soundLibrary == null) {
        return false;
      }
      Source ☃ = (Source)this.soundLibrary.getSources().get(☃);
      if (☃ == null) {
        return false;
      }
      return (☃.playing()) || (☃.paused()) || (☃.preLoad);
    }
  }
}
