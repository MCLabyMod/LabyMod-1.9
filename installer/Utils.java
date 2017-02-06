package installer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;
import java.util.jar.JarEntry;
import java.util.jar.JarException;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipException;
import javax.swing.JOptionPane;

public class Utils
{
  static MessageDigest mdSha;
  static boolean shader = false;
  public static final String MAC_OS_HOME_PREFIX = "Library/Application Support";
  
  static
  {
    try
    {
      mdSha = MessageDigest.getInstance("SHA");
    }
    catch (NoSuchAlgorithmException e)
    {
      System.err.println("No SHA algorithm");
      mdSha = null;
    }
  }
  
  public static enum OS
  {
    LINUX,  SOLARIS,  WINDOWS,  MACOS,  UNKNOWN;
  }
  
  public static File getWorkingDirectory()
  {
    return getWorkingDirectory("minecraft");
  }
  
  public static File getWorkingDirectory(String applicationName)
  {
    String userHome = System.getProperty("user.home", ".");
    File workingDirectory = null;
    switch (getPlatform())
    {
    case LINUX: 
      workingDirectory = new File(userHome, '.' + applicationName + '/');
      break;
    case UNKNOWN: 
      workingDirectory = new File(userHome, "Library/Application Support/" + applicationName);
      break;
    case MACOS: 
      String applicationData = System.getenv("APPDATA");
      if (applicationData != null) {
        workingDirectory = new File(applicationData, "." + applicationName + '/');
      } else {
        workingDirectory = new File(userHome, '.' + applicationName + '/');
      }
      break;
    case WINDOWS: 
      workingDirectory = new File(userHome, "Library/Application Support/" + applicationName);
      break;
    case SOLARIS: 
      String applicationDataW = System.getenv("APPDATA");
      if (applicationDataW != null) {
        workingDirectory = new File(applicationDataW, "." + applicationName + '/');
      } else {
        workingDirectory = new File(userHome, '.' + applicationName + '/');
      }
      break;
    default: 
      workingDirectory = new File(userHome, applicationName + '/');
    }
    if ((!workingDirectory.exists()) && (!workingDirectory.mkdirs())) {
      throw new RuntimeException("The working directory could not be created: " + workingDirectory);
    }
    return workingDirectory;
  }
  
  public static boolean copyFile(File from, File to)
  {
    if (!from.exists())
    {
      error(from.getName() + " doesn't exists");
      return false;
    }
    if (!to.getParentFile().exists()) {
      to.getParentFile().mkdirs();
    }
    try
    {
      System.out.println(Main.debug + "copy " + from.getName());
      Files.copy(from.toPath(), to.toPath(), new CopyOption[0]);
      return true;
    }
    catch (IOException e)
    {
      e.printStackTrace();
      error(e.getMessage());
    }
    return false;
  }
  
  public static void addToFile(File from, File to)
    throws IOException
  {
    if (!from.exists())
    {
      error(from.getName() + " doesn't exists");
      return;
    }
    if (!to.getParentFile().exists()) {
      to.getParentFile().mkdirs();
    }
    if (from.getCanonicalPath().equals(to.getCanonicalPath())) {
      return;
    }
    FileInputStream fin = new FileInputStream(from);
    FileOutputStream fout = new FileOutputStream(to, true);
    copyAll(fin, fout);
    
    fout.flush();
    
    fin.close();
    fout.close();
  }
  
  public static void copyAll(InputStream is, OutputStream os)
    throws IOException
  {
    byte[] buf = new byte['Ѐ'];
    for (;;)
    {
      int len = is.read(buf);
      if (len < 0) {
        break;
      }
      os.write(buf, 0, len);
    }
  }
  
  public static void downloadFile(String s, File dest)
    throws IOException
  {
    URL website = new URL(s);
    URLConnection web = website.openConnection();
    web.setRequestProperty("User-Agent", 
      "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
    ReadableByteChannel rbc = Channels.newChannel(web.getInputStream());
    FileOutputStream fos = new FileOutputStream(dest);
    fos.getChannel().transferFrom(rbc, 0L, Long.MAX_VALUE);
    fos.close();
  }
  
  public static boolean copyJars(ArrayList<File> mods, File destinationJar, boolean reinstalling)
  {
    ProgressBarUpdater.title = "Install LabyMod in " + destinationJar.getName();
    try
    {
      ArrayList<String> entryList = new ArrayList();
      JarOutputStream tempJar = new JarOutputStream(new FileOutputStream(destinationJar));
      byte[] buffer = new byte['Є'];
      for (File jarFile : mods)
      {
        ProgressBarUpdater.title = "Install " + jarFile.getName();
        System.out.println(Main.debug + "Install " + jarFile.getName());
        try
        {
          JarFile jar = new JarFile(jarFile);
          for (Enumeration<JarEntry> entries = jar.entries(); entries.hasMoreElements();)
          {
            JarEntry entry = new JarEntry(((JarEntry)entries.nextElement()).getName());
            if ((!entry.getName().startsWith("META-INF/")) && (!entry.getName().startsWith("Updater.jar")) && 
              (!entryList.contains(entry.getName())))
            {
              ProgressBarUpdater.subTitle = "Copy " + entry.getName();
              entryList.add(entry.getName());
              InputStream entryStream = jar.getInputStream(entry);
              tempJar.putNextEntry(entry);
              int bytesRead;
              while ((bytesRead = entryStream.read(buffer)) != -1)
              {
                int bytesRead;
                tempJar.write(buffer, 0, bytesRead);
              }
              entryStream.close();
              tempJar.flush();
              tempJar.closeEntry();
              ProgressBarUpdater.next();
            }
          }
          jar.close();
        }
        catch (Exception error)
        {
          error.printStackTrace();
          if ((((error instanceof ZipException)) || ((error instanceof JarException))) && (jarFile.getName().equals(Main.mcVersion + ".jar")) && (reinstallVersion())) {
            return copyJars(mods, destinationJar, true);
          }
          error("Error while installing " + jarFile.getName() + " (" + error.getMessage() + ")");
          if (jarFile.getName().equals(Main.mcVersion + ".jar")) {
            showMessage("Failed to read vanilla 1.8.8 jar file! You need to run Minecraft 1.8.8 vanilla manually once.");
          }
          return false;
        }
      }
      tempJar.close();
    }
    catch (Exception error)
    {
      error.printStackTrace();
      error("Error while installing " + mods.size() + " Mods (" + error.getMessage() + ")");
      return false;
    }
    ProgressBarUpdater.subTitle = "";
    return true;
  }
  
  public static boolean reinstallVersion()
  {
    File directory = new File(getWorkingDirectory(), "versions/" + Main.mcVersion);
    File jarFile = new File(directory, "1.8.8.jar");
    if ((jarFile.exists()) && (!jarFile.delete()))
    {
      error("[REINSTALLING] Error while trying to delete version " + Main.mcVersion);
      return false;
    }
    try
    {
      downloadFile("https://launcher.mojang.com/mc/game/1.9/client/2f67dfe8953299440d1902f9124f0f2c3a2c940f/client.jar", jarFile);
    }
    catch (IOException e)
    {
      error(
        "[REINSTALLING] Error while trying to download version " + Main.mcVersion + " (" + e.getMessage() + ")");
      return false;
    }
    return true;
  }
  
  public static int count(ArrayList<File> mods)
  {
    System.out.println("Reading all files..");
    ProgressBarUpdater.title = "Reading all files..";
    int amount = 0;
    try
    {
      ArrayList<String> entryList = new ArrayList();
      for (File jarFile : mods) {
        try
        {
          JarFile jar = new JarFile(jarFile);
          amount += jar.size();
          jar.close();
        }
        catch (Exception error)
        {
          error.printStackTrace();
        }
      }
    }
    catch (Exception error)
    {
      error.printStackTrace();
    }
    return amount;
  }
  
  private static void installShaderMod(File jarFile)
  {
    File mcDir = getWorkingDirectory("minecraft");
    String subDir = "shadersmodcore";
    String name = "ShadersModCore";
    String version = "2.4.12mc1.8";
    
    File outDir = new File(mcDir, "libraries/" + subDir + "/" + name + "/" + version);
    File outJar = new File(outDir, name + "-" + version + ".jar");
    
    InputStream stream = null;
    try
    {
      stream = new FileInputStream(jarFile);
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    outDir.mkdirs();
    byte[] abyte = readByteArray(stream);
    if ((abyte != null) && (writeFileWithSha(outJar, abyte))) {
      shader = true;
    }
  }
  
  private static boolean isShaderMod(File jarFile)
  {
    try
    {
      JarFile file = new JarFile(jarFile.getAbsolutePath());
      JarEntry entry = file.getJarEntry("shadersmod/installer/Installer.class");
      file.close();
      if (entry == null) {
        return false;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
  public static String toShaString(byte[] abyte)
  {
    mdSha.reset();
    byte[] sha = mdSha.digest(abyte);
    StringBuilder sb = new StringBuilder();
    byte[] arrayOfByte1;
    int j = (arrayOfByte1 = sha).length;
    for (int i = 0; i < j; i++)
    {
      byte b = arrayOfByte1[i];
      sb.append(String.format("%02x", new Object[] { Byte.valueOf(b) }));
    }
    return sb.toString();
  }
  
  public static boolean writeFileWithSha(File dst, byte[] abyte)
  {
    boolean result = false;
    OutputStream out = null;
    Writer wr = null;
    try
    {
      out = new FileOutputStream(dst);
      out.write(abyte);
      out.close();
      out = null;
      result = true;
      wr = new FileWriter(dst.getPath() + ".sha");
      wr.write(toShaString(abyte));
      wr.close();
      wr = null;
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return result;
  }
  
  static void close(Closeable closeable)
  {
    try
    {
      closeable.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  static byte[] readByteArray(InputStream is)
  {
    try
    {
      int size = 1024;
      int len;
      ByteArrayOutputStream bos;
      byte[] buf;
      if ((is instanceof ByteArrayInputStream))
      {
        size = is.available();
        byte[] buf = new byte[size];
        len = is.read(buf, 0, size);
      }
      else
      {
        bos = new ByteArrayOutputStream();
        buf = new byte[size];
        int len;
        while ((len = is.read(buf, 0, size)) != -1)
        {
          int len;
          bos.write(buf, 0, len);
        }
      }
      return bos.toByteArray();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static File[] concat(File[] a, File[] b)
  {
    int aLen = a.length;
    int bLen = b.length;
    File[] c = new File[aLen + bLen];
    System.arraycopy(a, 0, c, 0, aLen);
    System.arraycopy(b, 0, c, aLen, bLen);
    return c;
  }
  
  public static boolean copyFiles(File from, File to)
  {
    if (!from.exists())
    {
      error(from.getName() + " doesn't exists");
      return false;
    }
    if (!to.getParentFile().exists()) {
      to.getParentFile().mkdirs();
    }
    try
    {
      if (from.listFiles() != null)
      {
        File[] arrayOfFile;
        int j = (arrayOfFile = from.listFiles()).length;
        for (int i = 0; i < j; i++)
        {
          File f = arrayOfFile[i];
          File fileTo = new File(to.toPath() + "/" + f.getName());
          if ((fileTo.exists()) && (!fileTo.isDirectory())) {
            deleteDir(fileTo);
          }
          copyFiles(f, fileTo);
        }
      }
      else
      {
        System.out.println(Main.debug + "copy " + from.getName());
        Files.copy(from.toPath(), to.toPath(), new CopyOption[0]);
      }
      return true;
    }
    catch (IOException e)
    {
      e.printStackTrace();
      error(e.getMessage());
    }
    return false;
  }
  
  public static boolean deleteDir(File dir)
  {
    if (dir.exists())
    {
      if (dir.isDirectory())
      {
        String[] children = dir.list();
        for (int i = 0; i < children.length; i++)
        {
          boolean success = deleteDir(new File(dir, children[i]));
          if (!success) {
            return false;
          }
        }
      }
      System.out.println(Main.debug + "delete " + dir.getName());
      if (!dir.delete())
      {
        error("Delete " + dir.getName() + " failed");
        return false;
      }
    }
    return true;
  }
  
  public static boolean deleteDirSilent(File dir)
  {
    if (dir.exists())
    {
      if (dir.isDirectory())
      {
        String[] children = dir.list();
        for (int i = 0; i < children.length; i++)
        {
          boolean success = deleteDir(new File(dir, children[i]));
          if (!success) {
            return false;
          }
        }
      }
      System.out.println(Main.debug + "delete " + dir.getName());
      if (!dir.delete()) {
        return false;
      }
    }
    return true;
  }
  
  public static void error(String message)
  {
    if (message != null) {
      showErrorMessage(message);
    }
  }
  
  public static String getDesktop()
  {
    return System.getProperty("user.home") + "/Desktop/";
  }
  
  List<String> lines = new ArrayList();
  String line = null;
  
  public static void editFile(File file, String replaceFrom, String replaceTo)
  {
    try
    {
      List<String> lines = new ArrayList();
      String line = null;
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      System.out.println(Main.debug + "edit " + file.getName());
      while ((line = br.readLine()) != null)
      {
        if (line.contains(replaceFrom)) {
          line = line.replace(replaceFrom, replaceTo);
        }
        lines.add(line);
      }
      fr.close();
      br.close();
      
      FileWriter fw = new FileWriter(file);
      BufferedWriter out = new BufferedWriter(fw);
      for (String s : lines) {
        out.write(s);
      }
      out.flush();
      out.close();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
  
  public static void editFileFirst(File file, String replaceFrom, String replaceTo)
  {
    try
    {
      List<String> lines = new ArrayList();
      String line = null;
      boolean found = false;
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      System.out.println(Main.debug + "edit " + file.getName());
      while ((line = br.readLine()) != null)
      {
        if ((line.contains(replaceFrom)) && (!found))
        {
          found = true;
          line = line.replaceFirst(replaceFrom, replaceTo);
        }
        lines.add(line);
      }
      fr.close();
      br.close();
      
      FileWriter fw = new FileWriter(file);
      BufferedWriter out = new BufferedWriter(fw);
      for (String s : lines) {
        out.write(s);
      }
      out.flush();
      out.close();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
  
  public static BufferedImage resize(BufferedImage img, int newW, int newH)
  {
    Image tmp = img.getScaledInstance(newW, newH, 4);
    BufferedImage dimg = new BufferedImage(newW, newH, 2);
    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();
    return dimg;
  }
  
  public static OS getPlatform()
  {
    String osName = System.getProperty("os.name").toLowerCase();
    if (osName.contains("win")) {
      return OS.WINDOWS;
    }
    if (osName.contains("mac")) {
      return OS.MACOS;
    }
    if (osName.contains("solaris")) {
      return OS.SOLARIS;
    }
    if (osName.contains("sunos")) {
      return OS.SOLARIS;
    }
    if (osName.contains("linux")) {
      return OS.LINUX;
    }
    if (osName.contains("unix")) {
      return OS.LINUX;
    }
    return OS.UNKNOWN;
  }
  
  public static int find(byte[] buf, byte[] pattern)
  {
    return find(buf, 0, pattern);
  }
  
  public static int find(byte[] buf, int index, byte[] pattern)
  {
    for (int i = index; i < buf.length - pattern.length; i++)
    {
      boolean found = true;
      for (int pos = 0; pos < pattern.length; pos++) {
        if (pattern[pos] != buf[(i + pos)])
        {
          found = false;
          break;
        }
      }
      if (found) {
        return i;
      }
    }
    return -1;
  }
  
  public static byte[] readAll(InputStream is)
    throws IOException
  {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    byte[] buf = new byte['Ѐ'];
    for (;;)
    {
      int len = is.read(buf);
      if (len < 0) {
        break;
      }
      baos.write(buf, 0, len);
    }
    is.close();
    
    byte[] bytes = baos.toByteArray();
    
    return bytes;
  }
  
  public static void dbg(String str)
  {
    System.out.println(str);
  }
  
  public static String[] tokenize(String str, String delim)
  {
    List<String> list = new ArrayList();
    StringTokenizer tok = new StringTokenizer(str, delim);
    while (tok.hasMoreTokens())
    {
      String token = tok.nextToken();
      list.add(token);
    }
    String[] tokens = (String[])list.toArray(new String[list.size()]);
    return tokens;
  }
  
  public static String getExceptionStackTrace(Throwable e)
  {
    StringWriter swr = new StringWriter();
    PrintWriter pwr = new PrintWriter(swr);
    
    e.printStackTrace(pwr);
    
    pwr.close();
    try
    {
      swr.close();
    }
    catch (IOException localIOException) {}
    return swr.getBuffer().toString();
  }
  
  public static void showMessage(String msg)
  {
    JOptionPane.showMessageDialog(null, msg, "LabyMod", 1);
  }
  
  public static void showErrorMessage(String msg)
  {
    JOptionPane.showMessageDialog(null, msg, "Error", 0);
  }
  
  public static String readFile(File file)
    throws IOException
  {
    return readFile(file, "ASCII");
  }
  
  public static String readFile(File file, String encoding)
    throws IOException
  {
    FileInputStream fin = new FileInputStream(file);
    InputStreamReader inr = new InputStreamReader(fin, encoding);
    BufferedReader br = new BufferedReader(inr);
    StringBuffer sb = new StringBuffer();
    for (;;)
    {
      String line = br.readLine();
      if (line == null) {
        break;
      }
      sb.append(line);
      sb.append("\n");
    }
    br.close();
    inr.close();
    fin.close();
    
    return sb.toString();
  }
  
  public static void centerWindow(Component c, Component par)
  {
    if (c == null) {
      return;
    }
    Rectangle rect = c.getBounds();
    Rectangle parRect;
    Rectangle parRect;
    if ((par != null) && (par.isVisible()))
    {
      parRect = par.getBounds();
    }
    else
    {
      Dimension scrDim = Toolkit.getDefaultToolkit().getScreenSize();
      parRect = new Rectangle(0, 0, scrDim.width, scrDim.height);
    }
    int newX = parRect.x + (parRect.width - rect.width) / 2;
    int newY = parRect.y + (parRect.height - rect.height) / 2;
    if (newX < 0) {
      newX = 0;
    }
    if (newY < 0) {
      newY = 0;
    }
    c.setBounds(newX, newY, rect.width, rect.height);
  }
}
