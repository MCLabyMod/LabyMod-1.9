package optifine;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class Utils
{
  public static final String MAC_OS_HOME_PREFIX = "Library/Application Support";
  
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
    case MACOS: 
      workingDirectory = new File(userHome, '.' + applicationName + '/');
      break;
    case SOLARIS: 
      String applicationData = System.getenv("APPDATA");
      if (applicationData != null) {
        workingDirectory = new File(applicationData, "." + applicationName + '/');
      } else {
        workingDirectory = new File(userHome, '.' + applicationName + '/');
      }
      break;
    case UNKNOWN: 
      workingDirectory = new File(userHome, "Library/Application Support/" + applicationName);
      break;
    default: 
      workingDirectory = new File(userHome, applicationName + '/');
    }
    if ((!workingDirectory.exists()) && (!workingDirectory.mkdirs())) {
      throw new RuntimeException("The working directory could not be created: " + workingDirectory);
    }
    return workingDirectory;
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
    List list = new ArrayList();
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
  
  public static void copyFile(File fileSrc, File fileDest)
    throws IOException
  {
    if (fileSrc.getCanonicalPath().equals(fileDest.getCanonicalPath())) {
      return;
    }
    FileInputStream fin = new FileInputStream(fileSrc);
    FileOutputStream fout = new FileOutputStream(fileDest);
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
  
  public static void showMessage(String msg)
  {
    JOptionPane.showMessageDialog(null, msg, "OptiFine", 1);
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
