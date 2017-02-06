package de.labystudio.spotify;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;
import de.labystudio.labymod.Timings;

public class SpotifyManager
{
  private String spotify;
  private String title;
  private boolean set;
  SpotifyUser32 spotifyUser32;
  private WinUser.WNDENUMPROC wndeNumProc;
  private String trackName = null;
  private String artistName = null;
  private long displayTime;
  
  public SpotifyManager()
  {
    try
    {
      this.spotifyUser32 = ((SpotifyUser32)Native.loadLibrary("User32", SpotifyUser32.class));
      this.spotify = null;
      this.title = "?";
      this.set = false;
      
      this.wndeNumProc = new WinUser.WNDENUMPROC()
      {
        public boolean callback(WinDef.HWND hWnd, Pointer arg1)
        {
          byte[] windowText = new byte['Ȁ'];
          SpotifyManager.this.spotifyUser32.GetWindowTextA(hWnd, windowText, 512);
          String wText = Native.toString(windowText);
          if (wText.isEmpty()) {
            return true;
          }
          boolean notPlaying = wText.equals("Spotify");
          if (((SpotifyManager.this.spotify == null) && (notPlaying)) || (notPlaying) || ((SpotifyManager.this.title.equals(wText)) && (!hWnd.toString().equals(SpotifyManager.this.spotify)))) {
            SpotifyManager.this.spotify = hWnd.toString();
          }
          if ((SpotifyManager.this.spotify != null) && (SpotifyManager.this.spotify.equals(hWnd.toString())))
          {
            boolean refresh = !SpotifyManager.this.title.equals(wText);
            if (notPlaying)
            {
              SpotifyManager.this.title = "No song playing";
            }
            else
            {
              SpotifyManager.this.title = wText;
              SpotifyManager.this.setDisplayTime(System.currentTimeMillis());
            }
            if (refresh) {
              SpotifyManager.this.newTitleIsPlaying();
            }
            SpotifyManager.this.set = true;
          }
          return true;
        }
      };
    }
    catch (Exception error)
    {
      error.printStackTrace();
    }
    catch (Error error)
    {
      error.printStackTrace();
    }
  }
  
  public String getArtistName()
  {
    return this.artistName;
  }
  
  public String getTrackName()
  {
    return this.trackName;
  }
  
  private void newTitleIsPlaying()
  {
    if ((getSpotifyTitle() != null) && (getSpotifyTitle().contains(" - ")))
    {
      String[] split = getSpotifyTitle().replaceFirst(" - ", "@@@").split("@@@");
      this.artistName = split[0];
      this.trackName = split[1];
    }
    else
    {
      this.artistName = null;
      this.trackName = null;
    }
  }
  
  public SpotifyUser32 getSpotifyUser32()
  {
    return this.spotifyUser32;
  }
  
  public String getSpotifyTitle()
  {
    return this.title;
  }
  
  public long getDisplayTime()
  {
    return this.displayTime;
  }
  
  public void setDisplayTime(long displayTime)
  {
    this.displayTime = displayTime;
  }
  
  public WinUser.WNDENUMPROC getWndeNumProc()
  {
    return this.wndeNumProc;
  }
  
  public void updateTitle()
  {
    if (getSpotifyUser32() == null) {
      return;
    }
    if (getWndeNumProc() == null) {
      return;
    }
    Timings.start("Spotify update Title");
    this.set = false;
    new SpotifyThread(getSpotifyUser32(), getWndeNumProc(), new SpotifyCallback()
    {
      public void done()
      {
        if ((SpotifyManager.this.spotify != null) && (!SpotifyManager.this.set)) {
          SpotifyManager.this.spotify = null;
        }
      }
    }).start();
    Timings.stop("Spotify update Title");
  }
}
