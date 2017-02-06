package de.labystudio.downloader;

import de.labystudio.capes.CapeManager;
import de.labystudio.labymod.LabyMod;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class UserCapesDownloader
{
  public UserCapesDownloader()
  {
    Thread checkThread = new Thread()
    {
      public void run()
      {
        if (!UserCapesDownloader.this.downloadUserCapes("http://info.labymod.net/php/userCapes.json")) {
          System.out.println("Can't download usercapes.");
        }
      }
    };
    checkThread.setPriority(1);
    checkThread.start();
  }
  
  private boolean downloadUserCapes(String page)
  {
    try
    {
      System.out.println("[UserCapes] Download all usercapes..");
      HttpURLConnection connection = (HttpURLConnection)new URL(page).openConnection();
      connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
      connection.setRequestProperty("Cookie", "foo=bar");
      connection.connect();
      BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
      
      String content = "";
      ArrayList<String> userCapes = new ArrayList();
      String line;
      while ((line = r.readLine()) != null) {
        content = content + line;
      }
      String[] split = content.split(";");
      for (String user : split) {
        userCapes.add(user);
      }
      System.out.println("[UserCapes] Total usercapes: " + userCapes.size());
      LabyMod.getInstance().getCapeManager().setUserCapes(userCapes);
      return true;
    }
    catch (Exception error)
    {
      error.printStackTrace();
    }
    return false;
  }
}
