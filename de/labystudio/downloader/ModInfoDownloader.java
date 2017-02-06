package de.labystudio.downloader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.labystudio.cosmetic.Cosmetic;
import de.labystudio.cosmetic.CosmeticManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.Source;
import de.labystudio.utils.Debug;
import de.labystudio.utils.ServerBroadcast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class ModInfoDownloader
{
  public ModInfoDownloader()
  {
    Thread checkThread = new Thread()
    {
      public void run()
      {
        if (!ModInfoDownloader.this.downloadModInfo(Source.url_mod_info)) {
          System.out.println("Can't download the mod info.");
        }
      }
    };
    checkThread.setPriority(1);
    checkThread.start();
  }
  
  private boolean downloadModInfo(String page)
  {
    try
    {
      HttpURLConnection connection = (HttpURLConnection)new URL(page).openConnection();
      connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
      connection.setRequestProperty("Cookie", "foo=bar");
      connection.connect();
      BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
      
      String json = "";
      String line;
      while ((line = r.readLine()) != null) {
        json = json + line;
      }
      LabyMod.getInstance().autoUpdaterCurrentVersionId = Integer.parseInt(Source.mod_VersionName.replace(".", ""));
      JsonParser parser = new JsonParser();
      JsonElement element = parser.parse(json);
      String latestVersion = element.getAsJsonObject().get("latest_version").getAsString();
      LabyMod.getInstance().latestVersionName = latestVersion;
      LabyMod.getInstance().autoUpdaterLatestVersionId = Integer.parseInt(latestVersion.replace(".", ""));
      System.out.println("[LabyMod] The latest LabyMod version is v" + LabyMod.getInstance().latestVersionName + ", you are currently using LabyMod version v" + Source.mod_VersionName);
      if (LabyMod.getInstance().autoUpdaterLatestVersionId > LabyMod.getInstance().autoUpdaterCurrentVersionId) {
        System.out.println("[LabyMod] You are outdated!");
      } else {
        System.out.println("[LabyMod] You are using the latest version.");
      }
      try
      {
        JsonArray array = element.getAsJsonObject().get("cosmetics").getAsJsonArray();
        for (JsonElement a : array)
        {
          UUID uuid = UUID.fromString(a.getAsJsonObject().get("user_id").getAsString());
          if (a.getAsJsonObject().get("enabled").getAsInt() == 1)
          {
            int type = a.getAsJsonObject().get("cosmetic_id").getAsInt();
            JsonElement dataElement = a.getAsJsonObject().get("data");
            ArrayList<Cosmetic> list = new ArrayList();
            if (LabyMod.getInstance().getCosmeticManager().getCosmetics().containsKey(uuid)) {
              list.addAll((Collection)LabyMod.getInstance().getCosmeticManager().getCosmetics().get(uuid));
            }
            if ((dataElement instanceof JsonNull))
            {
              list.add(new Cosmetic(type));
              LabyMod.getInstance().getCosmeticManager().getCosmetics().put(uuid, list);
            }
            else
            {
              String data = dataElement.getAsString();
              list.add(new Cosmetic(type, data));
              LabyMod.getInstance().getCosmeticManager().getCosmetics().put(uuid, list);
            }
          }
        }
        if (Debug.capes()) {
          System.out.println("[LabyMod] Loaded " + LabyMod.getInstance().getCosmeticManager().getCosmetics().size() + " cosmetics");
        }
      }
      catch (Exception error)
      {
        error.printStackTrace();
        System.out.println("[LabyMod] Failed to load cosmetics");
      }
      try
      {
        JsonArray array = element.getAsJsonObject().get("broadcast").getAsJsonArray();
        for (JsonElement a : array)
        {
          String line1 = a.getAsJsonObject().get("line1").getAsString();
          String line2 = a.getAsJsonObject().get("line2").getAsString();
          String url = a.getAsJsonObject().get("url").getAsString();
          LabyMod.getInstance().setServerBroadcast(new ServerBroadcast(line1, line2, url));
          System.out.println("[LabyMod] Loaded LabyMod server broadcast");
        }
      }
      catch (Exception error)
      {
        error.printStackTrace();
        System.out.println("[LabyMod] Failed to load broadcast");
      }
      return true;
    }
    catch (Exception error)
    {
      LabyMod.getInstance().autoUpdaterLatestVersionId = LabyMod.getInstance().autoUpdaterCurrentVersionId;
      error.printStackTrace();
    }
    return false;
  }
}
