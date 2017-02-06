package installer;

import installer.json.JSONObject;
import installer.json.JSONParser;
import installer.json.JSONWriter;
import installer.json.ParseException;
import installer.labystudio.frame.FrameInstall;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Installer
  extends Thread
{
  FrameInstall frame;
  
  public Installer(FrameInstall frame)
  {
    this.frame = frame;
    new ProgressBarUpdater(frame.getProgressBar(), frame.getInstallTitle(), frame.getInstallSubTitle());
    start();
  }
  
  public void run()
  {
    File dirMinecraft = Utils.getWorkingDirectory();
    Utils.dbg("Dir minecraft: " + dirMinecraft);
    File dirVersions = new File(dirMinecraft, "versions");
    Utils.dbg("Dir versions: " + dirVersions);
    File jarInstaller;
    try
    {
      jarInstaller = getLabyModVersion();
    }
    catch (Exception e)
    {
      File jarInstaller;
      jarInstaller = fileChooser("Can't find currently running Installer jar! Please select the LabyMod Installer", "jar", "LabyMod Installer (jar file)", "Can't find currently running Installer jar!", "Invalid file!");
    }
    if (jarInstaller == null)
    {
      close("Can't find the LabyMod Installer!");
      return;
    }
    Utils.dbg("LabyMod Version: " + jarInstaller.getName());
    
    String labyModProfileName = "LabyMod-" + Main.mcVersion;
    File dirVersionLabyMod = new File(dirVersions, labyModProfileName);
    try
    {
      File fileJar = new File(dirVersionLabyMod, labyModProfileName + ".jar");
      Files.deleteIfExists(fileJar.toPath());
    }
    catch (IOException e)
    {
      e.printStackTrace();
      close("Please close Minecraft!");
      return;
    }
    copyMinecraftJson(Main.mcVersion, labyModProfileName, dirVersions);
    
    ArrayList<File> mods = new ArrayList();
    mods.add(jarInstaller);
    
    File dirTemp = new File(dirVersions, labyModProfileName + "/temp");
    dirTemp.mkdir();
    for (ModTemplate mod : Main.modTempates) {
      if (mod.isEnabled()) {
        try
        {
          File dest = new File(dirTemp, mod.getModName().replace("/", "&") + ".zip");
          Utils.downloadFile(mod.getDownload(), dest);
          mods.add(dest);
        }
        catch (IOException e)
        {
          e.printStackTrace();
          Utils.showMessage("Error while installing " + mod.getModName() + ": " + e.getMessage() + "! Skipping..");
        }
      }
    }
    if ((Main.mods != null) && (Main.mods.length != 0))
    {
      File[] arrayOfFile;
      e = (arrayOfFile = Main.mods).length;
      for (IOException localIOException1 = 0; localIOException1 < e; localIOException1++)
      {
        File mod = arrayOfFile[localIOException1];
        mods.add(mod);
      }
    }
    try
    {
      updateLauncherJson(dirMinecraft, labyModProfileName);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      Utils.showMessage("Oops! Failed while create a LabyMod profile for you! You have to create a profile manually. (" + e.getMessage() + ")");
    }
    File fileJsonMcLabyMod = new File(dirVersionLabyMod, labyModProfileName + ".json");
    try
    {
      updateVersionJson(fileJsonMcLabyMod, labyModProfileName);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      close("Can't edit " + fileJsonMcLabyMod.getAbsolutePath());
      return;
    }
    backupMods(mods, jarInstaller);
    createAutoUpdater(jarInstaller);
    File mcVanilla = new File(dirVersions, Main.mcVersion);
    File fileJarMcVanilla = new File(mcVanilla, Main.mcVersion + ".jar");
    File fileJarMcLabyMod = new File(dirVersionLabyMod, labyModProfileName + ".jar");
    if (!fileJarMcVanilla.exists())
    {
      System.out.println("Can't find " + fileJarMcVanilla.getAbsolutePath());
      close("Minecraft version not found: " + fileJarMcVanilla.getName() + "\nYou need to start the version " + fileJarMcVanilla.getName() + " manually once.");
      return;
    }
    mods.add(fileJarMcVanilla);
    
    ProgressBarUpdater.setMax(Utils.count(mods));
    if (!Utils.copyJars(mods, fileJarMcLabyMod, false))
    {
      close("Error while installing LabyMod!");
      return;
    }
    if (dirTemp.exists())
    {
      Utils.deleteDirSilent(dirTemp);
      dirTemp.delete();
    }
    setValue(100);
    Utils.showMessage("LabyMod is successfully installed.");
    this.frame.dispose();
    System.exit(0);
  }
  
  private void setValue(int id)
  {
    ProgressBarUpdater.value = id;
  }
  
  private void createAutoUpdater(File labyModFile)
  {
    try
    {
      File outDir = new File(Utils.getWorkingDirectory().getAbsolutePath() + "/LabyMod/");
      File oldUpdater = new File(outDir.getAbsolutePath(), "Updater-" + Main.mcVersion + ".jar");
      if (oldUpdater.exists()) {
        oldUpdater.delete();
      }
      ZipFile zipFile = new ZipFile(labyModFile);
      Enumeration<? extends ZipEntry> entries = zipFile.entries();
      while (entries.hasMoreElements())
      {
        ZipEntry entry = (ZipEntry)entries.nextElement();
        if (entry.getName().contains("Updater-" + Main.mcVersion + ".jar"))
        {
          File entryDestination = new File(outDir, entry.getName());
          if (entry.isDirectory())
          {
            entryDestination.mkdirs();
          }
          else
          {
            entryDestination.getParentFile().mkdirs();
            InputStream in = zipFile.getInputStream(entry);
            OutputStream out = new FileOutputStream(entryDestination);
            Utils.copyAll(in, out);
            in.close();
            out.close();
          }
        }
      }
      zipFile.close();
    }
    catch (Exception error)
    {
      error.printStackTrace();
    }
  }
  
  private void backupMods(ArrayList<File> mods, File ignore)
  {
    if ((mods != null) && (!mods.isEmpty()))
    {
      File out = new File(Utils.getWorkingDirectory().getAbsolutePath() + "/LabyMod/mods-" + Main.mcVersion + "/");
      if (out.exists()) {
        Utils.deleteDir(out);
      }
      if ((out.getParentFile().getParentFile().exists()) && (!out.exists())) {
        out.mkdirs();
      }
      for (File mod : mods) {
        if (!mod.getName().equals(ignore.getName())) {
          Utils.copyFile(mod, new File(out, mod.getName()));
        }
      }
    }
  }
  
  private void status(String string)
  {
    this.frame.getInstallTitle().setText(string);
  }
  
  private static File getLabyModVersion()
    throws URISyntaxException, IOException
  {
    URL url = Installer.class.getProtectionDomain().getCodeSource().getLocation();
    Utils.dbg("URL: " + url);
    URI uri = url.toURI();
    return new File(uri);
  }
  
  public File fileChooser(String title, String fileType, String fileDesc, String errorNotFound, String errorInvalid)
  {
    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(fileDesc, new String[] { fileType });
    chooser.setFileFilter(filter);
    chooser.setCurrentDirectory(new File(Utils.getDesktop()));
    chooser.setDialogTitle(title);
    status("FileChooser: Selecting " + fileType);
    int returnVal = chooser.showOpenDialog(this.frame.getParent());
    if (returnVal != 0)
    {
      close(errorNotFound);
    }
    else
    {
      if ((chooser.getSelectedFile() != null) && (chooser.getSelectedFile().exists())) {
        return chooser.getSelectedFile();
      }
      close(errorInvalid);
    }
    return null;
  }
  
  private boolean copyMinecraftJson(String mcVer, String mcVerLabyMod, File dirMcVer)
  {
    File dirVerMc = new File(dirMcVer, mcVer);
    System.out.println("Dir version MC: " + dirVerMc.getAbsolutePath() + " (" + dirVerMc.getName() + ")");
    if (!dirVerMc.exists())
    {
      System.out.println("Can't find " + dirMcVer.getAbsolutePath() + " (copy json)");
      close("Minecraft version not found: " + mcVer + "\nYou need to start the version " + mcVer + " manually once.");
      return false;
    }
    File dirVerMcLabyMod = new File(dirMcVer, mcVerLabyMod);
    if (dirVerMcLabyMod.exists()) {
      Utils.deleteDir(dirVerMcLabyMod);
    }
    dirVerMcLabyMod.mkdirs();
    
    Utils.dbg("Dir version MC-LabyMod: " + dirVerMcLabyMod);
    
    File fileJsonMc = new File(dirVerMc, mcVer + ".json");
    File fileJsonMcLabyMod = new File(dirVerMcLabyMod, mcVerLabyMod + ".json");
    Utils.copyFile(fileJsonMc, fileJsonMcLabyMod);
    return true;
  }
  
  private static void updateLauncherJson(File dirMc, String mcVerLabyMod)
    throws IOException, ParseException
  {
    File fileJson = new File(dirMc, "launcher_profiles.json");
    String json = Utils.readFile(fileJson);
    JSONParser jp = new JSONParser();
    
    JSONObject root = (JSONObject)jp.parse(json);
    
    JSONObject profiles = (JSONObject)root.get("profiles");
    
    JSONObject prof = (JSONObject)profiles.get(mcVerLabyMod);
    if (prof == null)
    {
      prof = new JSONObject();
      prof.put("name", mcVerLabyMod);
      
      profiles.put(mcVerLabyMod, prof);
    }
    prof.put("lastVersionId", mcVerLabyMod);
    
    root.put("selectedProfile", mcVerLabyMod);
    
    FileWriter fwJson = new FileWriter(fileJson);
    JSONWriter jw = new JSONWriter(fwJson);
    jw.writeObject(root);
    
    fwJson.flush();
    fwJson.close();
  }
  
  private static void updateVersionJson(File fileJson, String verLabyMod)
    throws IOException, ParseException
  {
    String json = Utils.readFile(fileJson);
    JSONParser jp = new JSONParser();
    
    JSONObject root = (JSONObject)jp.parse(json);
    
    root.put("id", verLabyMod);
    root.remove("downloads");
    
    FileWriter fwJson = new FileWriter(fileJson);
    JSONWriter jw = new JSONWriter(fwJson);
    jw.writeObject(root);
    
    fwJson.flush();
    fwJson.close();
  }
  
  private void close(String message)
  {
    if (message != null)
    {
      this.frame.hide();
      Utils.showErrorMessage(message);
      Utils.showMessage("If you have any problems you can contact us via TeamSpeak: ts.labymod.net");
      this.frame.dispose();
      System.exit(0);
    }
  }
}
