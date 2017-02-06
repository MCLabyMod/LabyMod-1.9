import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ResUtils
{
  public static String[] collectFiles(String prefix, String suffix)
  {
    return collectFiles(new String[] { prefix }, new String[] { suffix });
  }
  
  public static String[] collectFiles(String[] prefixes, String[] suffixes)
  {
    Set<String> setPaths = new LinkedHashSet();
    bwi[] rps = Config.getResourcePacks();
    for (int i = 0; i < rps.length; i++)
    {
      bwi rp = rps[i];
      String[] ps = collectFiles(rp, prefixes, suffixes, null);
      setPaths.addAll(Arrays.asList(ps));
    }
    String[] paths = (String[])setPaths.toArray(new String[setPaths.size()]);
    return paths;
  }
  
  public static String[] collectFiles(bwi rp, String prefix, String suffix, String[] defaultPaths)
  {
    return collectFiles(rp, new String[] { prefix }, new String[] { suffix }, defaultPaths);
  }
  
  public static String[] collectFiles(bwi rp, String[] prefixes, String[] suffixes)
  {
    return collectFiles(rp, prefixes, suffixes, null);
  }
  
  public static String[] collectFiles(bwi rp, String[] prefixes, String[] suffixes, String[] defaultPaths)
  {
    if ((rp instanceof bvx)) {
      return collectFilesFixed(rp, defaultPaths);
    }
    if (!(rp instanceof bvu)) {
      return new String[0];
    }
    bvu arp = (bvu)rp;
    
    File tpFile = arp.a;
    if (tpFile == null) {
      return new String[0];
    }
    if (tpFile.isDirectory()) {
      return collectFilesFolder(tpFile, "", prefixes, suffixes);
    }
    if (tpFile.isFile()) {
      return collectFilesZIP(tpFile, prefixes, suffixes);
    }
    return new String[0];
  }
  
  private static String[] collectFilesFixed(bwi rp, String[] paths)
  {
    if (paths == null) {
      return new String[0];
    }
    List list = new ArrayList();
    for (int i = 0; i < paths.length; i++)
    {
      String path = paths[i];
      kk loc = new kk(path);
      if (rp.b(loc)) {
        list.add(path);
      }
    }
    String[] pathArr = (String[])list.toArray(new String[list.size()]);
    return pathArr;
  }
  
  private static String[] collectFilesFolder(File tpFile, String basePath, String[] prefixes, String[] suffixes)
  {
    List list = new ArrayList();
    String prefixAssets = "assets/minecraft/";
    File[] files = tpFile.listFiles();
    if (files == null) {
      return new String[0];
    }
    for (int i = 0; i < files.length; i++)
    {
      File file = files[i];
      if (file.isFile())
      {
        String name = basePath + file.getName();
        if (name.startsWith(prefixAssets))
        {
          name = name.substring(prefixAssets.length());
          if (StrUtils.startsWith(name, prefixes)) {
            if (StrUtils.endsWith(name, suffixes)) {
              list.add(name);
            }
          }
        }
      }
      else if (file.isDirectory())
      {
        String dirPath = basePath + file.getName() + "/";
        String[] names = collectFilesFolder(file, dirPath, prefixes, suffixes);
        for (int n = 0; n < names.length; n++)
        {
          String name = names[n];
          list.add(name);
        }
      }
    }
    String[] names = (String[])list.toArray(new String[list.size()]);
    return names;
  }
  
  private static String[] collectFilesZIP(File tpFile, String[] prefixes, String[] suffixes)
  {
    List list = new ArrayList();
    String prefixAssets = "assets/minecraft/";
    try
    {
      ZipFile zf = new ZipFile(tpFile);
      Enumeration en = zf.entries();
      while (en.hasMoreElements())
      {
        ZipEntry ze = (ZipEntry)en.nextElement();
        String name = ze.getName();
        if (name.startsWith(prefixAssets))
        {
          name = name.substring(prefixAssets.length());
          if ((StrUtils.startsWith(name, prefixes)) && 
          
            (StrUtils.endsWith(name, suffixes))) {
            list.add(name);
          }
        }
      }
      zf.close();
      
      return (String[])list.toArray(new String[list.size()]);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return new String[0];
  }
}
