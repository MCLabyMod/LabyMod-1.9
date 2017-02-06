package de.labystudio.capes;

import de.labystudio.chat.Client;
import de.labystudio.chat.ClientConnection;
import de.labystudio.chat.EnumAlertType;
import de.labystudio.gui.GuiAchievementMod;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.Source;
import de.labystudio.utils.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CapeUploader
  extends Thread
{
  public static boolean upload;
  public static boolean openUpload = false;
  CapeCallback callBack;
  
  public CapeUploader(CapeCallback callBack)
  {
    this.callBack = callBack;
  }
  
  private final String CrLf = "\r\n";
  
  public void run()
  {
    upload = false;
    openUpload = true;
    HttpURLConnection conn = null;
    OutputStream os = null;
    InputStream is = null;
    File file = selectCape();
    if ((file == null) || (!file.exists()))
    {
      openUpload = false;
      return;
    }
    try
    {
      openUpload = false;
      upload = true;
      System.out.println("[LabyMod] Uploading cape " + file.getName());
      LabyMod.getInstance().getClient().getClientConnection();URL url = new URL(Source.url_changeCape + "?username=" + LabyMod.getInstance().getPlayerName() + "&capeKey=" + ClientConnection.getCapeKey());
      conn = (HttpURLConnection)url.openConnection();
      conn.setDoOutput(true);
      String postData = "";
      InputStream imgIs = new FileInputStream(file);
      byte[] imgData = new byte[imgIs.available()];
      imgIs.read(imgData);
      imgIs.close();
      String message1 = "";
      message1 = message1 + "-----------------------------4664151417711\r\n";
      message1 = message1 + "Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"" + "\r\n";
      message1 = message1 + "Content-Type: image/png\r\n";
      message1 = message1 + "\r\n";
      String message2 = "";
      message2 = message2 + "\r\n-----------------------------4664151417711--\r\n";
      conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=---------------------------4664151417711");
      conn.setRequestProperty("Content-Length", String.valueOf(message1.length() + message2.length() + imgData.length));
      os = conn.getOutputStream();
      os.write(message1.getBytes());
      int index = 0;
      int size = 1024;
      do
      {
        if (index + size > imgData.length) {
          size = imgData.length - index;
        }
        os.write(imgData, index, size);
        index += size;
      } while (index < imgData.length);
      os.write(message2.getBytes());
      os.flush();
      is = conn.getInputStream();
      
      byte[] data = new byte['Ѐ'];
      int len = 0;
      String output = "";
      while ((len = is.read(data)) > 0) {
        if (len > 0)
        {
          output = output + new String(data, 0, len);
          System.out.println("[LabyMod] Output: " + output);
        }
      }
      System.out.println(output);
      if (output.equalsIgnoreCase("OK"))
      {
        LabyMod.getInstance().getCapeManager().refresh();
        this.callBack.done();
      }
      else
      {
        this.callBack.failed(output);
      }
      try
      {
        os.close();
      }
      catch (Exception localException1) {}
      try
      {
        is.close();
      }
      catch (Exception localException2) {}
      upload = false;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      LabyMod.getInstance().achievementGui.displayBroadcast("CapeManager", Color.cl("c") + "Error: " + e.getMessage(), EnumAlertType.LABYMOD);
      this.callBack.failed(e.getMessage());
    }
    finally
    {
      try
      {
        os.close();
      }
      catch (Exception localException5) {}
      try
      {
        is.close();
      }
      catch (Exception localException6) {}
    }
    openUpload = false;
  }
  
  private File selectCape()
  {
    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG image files", new String[] { "png" });
    chooser.setFileFilter(filter);
    chooser.setMultiSelectionEnabled(false);
    chooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
    chooser.setDialogTitle("Select your cape");
    
    JFrame frame = new JFrame();
    
    chooser.showOpenDialog(frame.getParent());
    return chooser.getSelectedFile();
  }
}
