package optifine;

import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import optifine.json.JSONArray;
import optifine.json.JSONObject;
import optifine.json.JSONParser;
import optifine.json.JSONWriter;
import optifine.json.ParseException;

public class Installer
{
  public static void main(String[] args)
  {
    try
    {
      
    }
    catch (Exception e)
    {
      String msg = e.getMessage();
      if ((msg != null) && (msg.equals("QUIET"))) {
        return;
      }
      e.printStackTrace();
      String str = Utils.getExceptionStackTrace(e);
      str = str.replace("\t", "  ");
      JTextArea textArea = new JTextArea(str);
      textArea.setEditable(false);
      Font f = textArea.getFont();
      Font f2 = new Font("Monospaced", f.getStyle(), f.getSize());
      textArea.setFont(f2);
      JScrollPane scrollPane = new JScrollPane(textArea);
      scrollPane.setPreferredSize(new Dimension(600, 400));
      JOptionPane.showMessageDialog(null, scrollPane, "Error", 0);
    }
  }
  
  public static void doInstall()
    throws Exception
  {
    File dirMc = Utils.getWorkingDirectory();
    Utils.dbg("Dir minecraft: " + dirMc);
    File dirMcLib = new File(dirMc, "libraries");
    Utils.dbg("Dir libraries: " + dirMcLib);
    File dirMcVers = new File(dirMc, "versions");
    Utils.dbg("Dir versions: " + dirMcVers);
    
    String ofVer = getOptiFineVersion();
    Utils.dbg("OptiFine Version: " + ofVer);
    String[] ofVers = Utils.tokenize(ofVer, "_");
    
    String mcVer = ofVers[1];
    Utils.dbg("Minecraft Version: " + mcVer);
    
    String ofEd = getOptiFineEdition(ofVers);
    Utils.dbg("OptiFine Edition: " + ofEd);
    
    installOptiFineLibrary(mcVer, ofEd, dirMcLib);
    
    String mcVerOf = mcVer + "-OptiFine_" + ofEd;
    Utils.dbg("Minecraft_OptiFine Version: " + mcVerOf);
    copyMinecraftVersion(mcVer, mcVerOf, dirMcVers);
    
    updateJson(dirMcVers, mcVerOf, dirMcLib, mcVer, ofEd);
    
    updateLauncherJson(dirMc, mcVerOf);
  }
  
  private static void updateLauncherJson(File dirMc, String mcVerOf)
    throws IOException, ParseException
  {
    File fileJson = new File(dirMc, "launcher_profiles.json");
    String json = Utils.readFile(fileJson);
    JSONParser jp = new JSONParser();
    
    JSONObject root = (JSONObject)jp.parse(json);
    
    JSONObject profiles = (JSONObject)root.get("profiles");
    
    JSONObject prof = (JSONObject)profiles.get("OptiFine");
    if (prof == null)
    {
      prof = new JSONObject();
      prof.put("name", "OptiFine");
      
      profiles.put("OptiFine", prof);
    }
    prof.put("lastVersionId", mcVerOf);
    
    root.put("selectedProfile", "OptiFine");
    
    FileWriter fwJson = new FileWriter(fileJson);
    JSONWriter jw = new JSONWriter(fwJson);
    jw.writeObject(root);
    
    fwJson.flush();
    fwJson.close();
  }
  
  private static void updateJson(File dirMcVers, String mcVerOf, File dirMcLib, String mcVer, String ofEd)
    throws IOException, ParseException
  {
    File dirMcVersOf = new File(dirMcVers, mcVerOf);
    
    File fileJson = new File(dirMcVersOf, mcVerOf + ".json");
    String json = Utils.readFile(fileJson);
    JSONParser jp = new JSONParser();
    
    JSONObject root = (JSONObject)jp.parse(json);
    root.put("id", mcVerOf);
    
    JSONArray libs = (JSONArray)root.get("libraries");
    
    root.put("inheritsFrom", mcVer);
    libs = new JSONArray();
    root.put("libraries", libs);
    
    String mainClass = (String)root.get("mainClass");
    if (!mainClass.startsWith("net.minecraft.launchwrapper."))
    {
      mainClass = "net.minecraft.launchwrapper.Launch";
      root.put("mainClass", mainClass);
      
      String args = (String)root.get("minecraftArguments");
      args = args + "  --tweakClass optifine.OptiFineTweaker";
      root.put("minecraftArguments", args);
      
      JSONObject libLw = new JSONObject();
      libLw.put("name", "net.minecraft:launchwrapper:1.7");
      libs.add(0, libLw);
    }
    JSONObject libOf = new JSONObject();
    libOf.put("name", "optifine:OptiFine:" + mcVer + "_" + ofEd);
    libs.add(0, libOf);
    
    FileWriter fwJson = new FileWriter(fileJson);
    JSONWriter jw = new JSONWriter(fwJson);
    jw.writeObject(root);
    
    fwJson.flush();
    fwJson.close();
  }
  
  public static String getOptiFineEdition(String[] ofVers)
  {
    if (ofVers.length <= 2) {
      return "";
    }
    String ofEd = "";
    for (int i = 2; i < ofVers.length; i++)
    {
      if (i > 2) {
        ofEd = ofEd + "_";
      }
      ofEd = ofEd + ofVers[i];
    }
    return ofEd;
  }
  
  private static void installOptiFineLibrary(String mcVer, String ofEd, File dirMcLib)
    throws URISyntaxException, IOException
  {
    URL url = Installer.class.getProtectionDomain().getCodeSource().getLocation();
    Utils.dbg("URL: " + url);
    URI uri = url.toURI();
    File fileSrc = new File(uri);
    
    File dirDest = new File(dirMcLib, "optifine/OptiFine/" + mcVer + "_" + ofEd);
    dirDest.mkdirs();
    File fileDest = new File(dirDest, "OptiFine-" + mcVer + "_" + ofEd + ".jar");
    
    Utils.dbg("Source: " + fileSrc);
    Utils.dbg("Dest: " + fileDest);
    Utils.copyFile(fileSrc, fileDest);
  }
  
  private static void copyMinecraftVersion(String mcVer, String mcVerOf, File dirMcVer)
    throws IOException
  {
    File dirVerMc = new File(dirMcVer, mcVer);
    if (!dirVerMc.exists())
    {
      Utils.showErrorMessage("Minecraft version not found: " + mcVer + "\nYou need to start the version " + mcVer + " manually once.");
      throw new RuntimeException("QUIET");
    }
    File dirVerMcOf = new File(dirMcVer, mcVerOf);
    dirVerMcOf.mkdirs();
    
    Utils.dbg("Dir version MC: " + dirVerMc);
    Utils.dbg("Dir version MC-OF: " + dirVerMcOf);
    
    File fileJarMc = new File(dirVerMc, mcVer + ".jar");
    File fileJarMcOf = new File(dirVerMcOf, mcVerOf + ".jar");
    Utils.copyFile(fileJarMc, fileJarMcOf);
    
    File fileJsonMc = new File(dirVerMc, mcVer + ".json");
    File fileJsonMcOf = new File(dirVerMcOf, mcVerOf + ".json");
    Utils.copyFile(fileJsonMc, fileJsonMcOf);
  }
  
  public static String getOptiFineVersion()
    throws IOException
  {
    InputStream in = Installer.class.getResourceAsStream("/Config.class");
    if (in == null) {
      in = Installer.class.getResourceAsStream("/VersionThread.class");
    }
    byte[] bytes = Utils.readAll(in);
    byte[] pattern = "OptiFine_".getBytes("ASCII");
    int pos = Utils.find(bytes, pattern);
    if (pos < 0) {
      return null;
    }
    int startPos = pos;
    for (pos = startPos; pos < bytes.length; pos++)
    {
      byte b = bytes[pos];
      if ((b < 32) || (b > 122)) {
        break;
      }
    }
    int endPos = pos;
    
    String ver = new String(bytes, startPos, endPos - startPos, "ASCII");
    
    return ver;
  }
}
