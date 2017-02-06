package shadersmod.client;

import Config;
import StrUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShaderPackParser
{
  private static final Pattern PATTERN_INCLUDE = Pattern.compile("^\\s*#include\\s+\"([A-Za-z0-9_/\\.]+)\".*$");
  private static final Set<String> setConstNames = makeSetConstNames();
  
  public static ShaderOption[] parseShaderPackOptions(IShaderPack shaderPack, String[] programNames, List<Integer> listDimensions)
  {
    if (shaderPack == null) {
      return new ShaderOption[0];
    }
    Map<String, ShaderOption> mapOptions = new HashMap();
    
    collectShaderOptions(shaderPack, "/shaders", programNames, mapOptions);
    for (Iterator<Integer> it = listDimensions.iterator(); it.hasNext();)
    {
      int dimId = ((Integer)it.next()).intValue();
      String dirWorld = "/shaders/world" + dimId;
      collectShaderOptions(shaderPack, dirWorld, programNames, mapOptions);
    }
    Collection<ShaderOption> options = mapOptions.values();
    ShaderOption[] sos = (ShaderOption[])options.toArray(new ShaderOption[options.size()]);
    
    Comparator<ShaderOption> comp = new Comparator()
    {
      public int compare(ShaderOption o1, ShaderOption o2)
      {
        return o1.getName().compareToIgnoreCase(o2.getName());
      }
    };
    Arrays.sort(sos, comp);
    
    return sos;
  }
  
  private static void collectShaderOptions(IShaderPack shaderPack, String dir, String[] programNames, Map<String, ShaderOption> mapOptions)
  {
    for (int i = 0; i < programNames.length; i++)
    {
      String programName = programNames[i];
      if (!programName.equals(""))
      {
        String vsh = dir + "/" + programName + ".vsh";
        String fsh = dir + "/" + programName + ".fsh";
        
        collectShaderOptions(shaderPack, vsh, mapOptions);
        collectShaderOptions(shaderPack, fsh, mapOptions);
      }
    }
  }
  
  private static void collectShaderOptions(IShaderPack sp, String path, Map<String, ShaderOption> mapOptions)
  {
    String[] lines = getLines(sp, path);
    for (int i = 0; i < lines.length; i++)
    {
      String line = lines[i];
      ShaderOption so = getShaderOption(line, path);
      if (so != null) {
        if ((!so.checkUsed()) || (isOptionUsed(so, lines)))
        {
          String key = so.getName();
          ShaderOption so2 = (ShaderOption)mapOptions.get(key);
          if (so2 != null)
          {
            if (!Config.equals(so2.getValueDefault(), so.getValueDefault()))
            {
              Config.warn("Ambiguous shader option: " + so.getName());
              Config.warn(" - in " + Config.arrayToString(so2.getPaths()) + ": " + so2.getValueDefault());
              Config.warn(" - in " + Config.arrayToString(so.getPaths()) + ": " + so.getValueDefault());
              
              so2.setEnabled(false);
            }
            if ((so2.getDescription() == null) || (so2.getDescription().length() <= 0)) {
              so2.setDescription(so.getDescription());
            }
            so2.addPaths(so.getPaths());
          }
          else
          {
            mapOptions.put(key, so);
          }
        }
      }
    }
  }
  
  private static boolean isOptionUsed(ShaderOption so, String[] lines)
  {
    for (int i = 0; i < lines.length; i++)
    {
      String line = lines[i];
      if (so.isUsedInLine(line)) {
        return true;
      }
    }
    return false;
  }
  
  private static String[] getLines(IShaderPack sp, String path)
  {
    try
    {
      String str = loadFile(path, sp, 0);
      if (str == null) {
        return new String[0];
      }
      ByteArrayInputStream is = new ByteArrayInputStream(str.getBytes());
      
      return Config.readLines(is);
    }
    catch (IOException e)
    {
      Config.dbg(e.getClass().getName() + ": " + e.getMessage());
    }
    return new String[0];
  }
  
  private static ShaderOption getShaderOption(String line, String path)
  {
    ShaderOption so = null;
    if (so == null) {
      so = ShaderOptionSwitch.parseOption(line, path);
    }
    if (so == null) {
      so = ShaderOptionVariable.parseOption(line, path);
    }
    if (so != null) {
      return so;
    }
    if (so == null) {
      so = ShaderOptionSwitchConst.parseOption(line, path);
    }
    if (so == null) {
      so = ShaderOptionVariableConst.parseOption(line, path);
    }
    if (so != null) {
      if (setConstNames.contains(so.getName())) {
        return so;
      }
    }
    return null;
  }
  
  private static Set<String> makeSetConstNames()
  {
    Set<String> set = new HashSet();
    
    set.add("shadowMapResolution");
    set.add("shadowDistance");
    set.add("shadowIntervalSize");
    set.add("generateShadowMipmap");
    set.add("generateShadowColorMipmap");
    set.add("shadowHardwareFiltering");
    set.add("shadowHardwareFiltering0");
    set.add("shadowHardwareFiltering1");
    set.add("shadowtex0Mipmap");
    set.add("shadowtexMipmap");
    set.add("shadowtex1Mipmap");
    set.add("shadowcolor0Mipmap");
    set.add("shadowColor0Mipmap");
    set.add("shadowcolor1Mipmap");
    set.add("shadowColor1Mipmap");
    set.add("shadowtex0Nearest");
    set.add("shadowtexNearest");
    set.add("shadow0MinMagNearest");
    set.add("shadowtex1Nearest");
    set.add("shadow1MinMagNearest");
    set.add("shadowcolor0Nearest");
    set.add("shadowColor0Nearest");
    set.add("shadowColor0MinMagNearest");
    set.add("shadowcolor1Nearest");
    set.add("shadowColor1Nearest");
    set.add("shadowColor1MinMagNearest");
    set.add("wetnessHalflife");
    set.add("drynessHalflife");
    set.add("eyeBrightnessHalflife");
    set.add("centerDepthHalflife");
    set.add("sunPathRotation");
    set.add("ambientOcclusionLevel");
    set.add("superSamplingLevel");
    set.add("noiseTextureResolution");
    
    return set;
  }
  
  public static ShaderProfile[] parseProfiles(Properties props, ShaderOption[] shaderOptions)
  {
    String PREFIX_PROFILE = "profile.";
    
    List<ShaderProfile> list = new ArrayList();
    Set keys = props.keySet();
    for (Iterator it = keys.iterator(); it.hasNext();)
    {
      String key = (String)it.next();
      if (key.startsWith(PREFIX_PROFILE))
      {
        String name = key.substring(PREFIX_PROFILE.length());
        String val = props.getProperty(key);
        
        Set<String> parsedProfiles = new HashSet();
        ShaderProfile p = parseProfile(name, props, parsedProfiles, shaderOptions);
        if (p != null) {
          list.add(p);
        }
      }
    }
    if (list.size() <= 0) {
      return null;
    }
    ShaderProfile[] profs = (ShaderProfile[])list.toArray(new ShaderProfile[list.size()]);
    
    return profs;
  }
  
  private static ShaderProfile parseProfile(String name, Properties props, Set<String> parsedProfiles, ShaderOption[] shaderOptions)
  {
    String PREFIX_PROFILE = "profile.";
    String key = PREFIX_PROFILE + name;
    if (parsedProfiles.contains(key))
    {
      Config.warn("[Shaders] Profile already parsed: " + name);
      return null;
    }
    parsedProfiles.add(name);
    
    ShaderProfile prof = new ShaderProfile(name);
    
    String val = props.getProperty(key);
    String[] parts = Config.tokenize(val, " ");
    for (int i = 0; i < parts.length; i++)
    {
      String part = parts[i];
      if (part.startsWith(PREFIX_PROFILE))
      {
        String nameParent = part.substring(PREFIX_PROFILE.length());
        ShaderProfile profParent = parseProfile(nameParent, props, parsedProfiles, shaderOptions);
        if (prof != null)
        {
          prof.addOptionValues(profParent);
          prof.addDisabledPrograms(profParent.getDisabledPrograms());
        }
      }
      else
      {
        String[] tokens = Config.tokenize(part, ":=");
        if (tokens.length == 1)
        {
          String option = tokens[0];
          boolean on = true;
          if (option.startsWith("!"))
          {
            on = false;
            option = option.substring(1);
          }
          String PREFIX_PROGRAM = "program.";
          if ((!on) && (option.startsWith("program.")))
          {
            String program = option.substring(PREFIX_PROGRAM.length());
            if (!Shaders.isProgramPath(program)) {
              Config.warn("Invalid program: " + program + " in profile: " + prof.getName());
            } else {
              prof.addDisabledProgram(program);
            }
          }
          else
          {
            ShaderOption so = ShaderUtils.getShaderOption(option, shaderOptions);
            if (!(so instanceof ShaderOptionSwitch))
            {
              Config.warn("[Shaders] Invalid option: " + option);
            }
            else
            {
              prof.addOptionValue(option, String.valueOf(on));
              
              so.setVisible(true);
            }
          }
        }
        else if (tokens.length != 2)
        {
          Config.warn("[Shaders] Invalid option value: " + part);
        }
        else
        {
          String option = tokens[0];
          String value = tokens[1];
          
          ShaderOption so = ShaderUtils.getShaderOption(option, shaderOptions);
          if (so == null)
          {
            Config.warn("[Shaders] Invalid option: " + part);
          }
          else if (!so.isValidValue(value))
          {
            Config.warn("[Shaders] Invalid value: " + part);
          }
          else
          {
            so.setVisible(true);
            
            prof.addOptionValue(option, value);
          }
        }
      }
    }
    return prof;
  }
  
  public static Map<String, ShaderOption[]> parseGuiScreens(Properties props, ShaderProfile[] shaderProfiles, ShaderOption[] shaderOptions)
  {
    Map<String, ShaderOption[]> map = new HashMap();
    
    parseGuiScreen("screen", props, map, shaderProfiles, shaderOptions);
    if (map.isEmpty()) {
      return null;
    }
    return map;
  }
  
  private static boolean parseGuiScreen(String key, Properties props, Map<String, ShaderOption[]> map, ShaderProfile[] shaderProfiles, ShaderOption[] shaderOptions)
  {
    String val = props.getProperty(key);
    if (val == null) {
      return false;
    }
    List<ShaderOption> list = new ArrayList();
    Set<String> setNames = new HashSet();
    
    String[] opNames = Config.tokenize(val, " ");
    for (int i = 0; i < opNames.length; i++)
    {
      String opName = opNames[i];
      if (opName.equals("<empty>"))
      {
        list.add(null);
      }
      else if (setNames.contains(opName))
      {
        Config.warn("[Shaders] Duplicate option: " + opName + ", key: " + key);
      }
      else
      {
        setNames.add(opName);
        if (opName.equals("<profile>"))
        {
          if (shaderProfiles == null)
          {
            Config.warn("[Shaders] Option profile can not be used, no profiles defined: " + opName + ", key: " + key);
          }
          else
          {
            ShaderOptionProfile optionProfile = new ShaderOptionProfile(shaderProfiles, shaderOptions);
            list.add(optionProfile);
          }
        }
        else if (opName.equals("*"))
        {
          ShaderOption soRest = new ShaderOptionRest("<rest>");
          list.add(soRest);
        }
        else if ((opName.startsWith("[")) && (opName.endsWith("]")))
        {
          String screen = StrUtils.removePrefixSuffix(opName, "[", "]");
          if (!screen.matches("^[a-zA-Z0-9_]+$"))
          {
            Config.warn("[Shaders] Invalid screen: " + opName + ", key: " + key);
          }
          else if (!parseGuiScreen("screen." + screen, props, map, shaderProfiles, shaderOptions))
          {
            Config.warn("[Shaders] Invalid screen: " + opName + ", key: " + key);
          }
          else
          {
            ShaderOptionScreen optionScreen = new ShaderOptionScreen(screen);
            list.add(optionScreen);
          }
        }
        else
        {
          ShaderOption so = ShaderUtils.getShaderOption(opName, shaderOptions);
          if (so == null)
          {
            Config.warn("[Shaders] Invalid option: " + opName + ", key: " + key);
            
            list.add(null);
          }
          else
          {
            so.setVisible(true);
            
            list.add(so);
          }
        }
      }
    }
    ShaderOption[] scrOps = (ShaderOption[])list.toArray(new ShaderOption[list.size()]);
    
    map.put(key, scrOps);
    
    return true;
  }
  
  public static BufferedReader resolveIncludes(BufferedReader reader, String filePath, IShaderPack shaderPack, int includeLevel)
    throws IOException
  {
    String fileDir = "/";
    int pos = filePath.lastIndexOf("/");
    if (pos >= 0) {
      fileDir = filePath.substring(0, pos);
    }
    CharArrayWriter caw = new CharArrayWriter();
    for (;;)
    {
      String line = reader.readLine();
      if (line == null) {
        break;
      }
      Matcher m = PATTERN_INCLUDE.matcher(line);
      if (m.matches())
      {
        String fileInc = m.group(1);
        
        boolean absolute = fileInc.startsWith("/");
        String filePathInc = fileDir + "/" + fileInc;
        line = loadFile(filePathInc, shaderPack, includeLevel);
        if (line == null) {
          throw new IOException("Included file not found: " + filePath);
        }
      }
      caw.write(line);
      caw.write("\n");
    }
    CharArrayReader car = new CharArrayReader(caw.toCharArray());
    return new BufferedReader(car);
  }
  
  private static String loadFile(String filePath, IShaderPack shaderPack, int includeLevel)
    throws IOException
  {
    if (includeLevel >= 10) {
      throw new IOException("#include depth exceeded: " + includeLevel + ", file: " + filePath);
    }
    includeLevel++;
    
    InputStream in = shaderPack.getResourceAsStream(filePath);
    if (in == null) {
      return null;
    }
    InputStreamReader isr = new InputStreamReader(in, "ASCII");
    BufferedReader br = new BufferedReader(isr);
    
    br = resolveIncludes(br, filePath, shaderPack, includeLevel);
    
    CharArrayWriter caw = new CharArrayWriter();
    for (;;)
    {
      String line = br.readLine();
      if (line == null) {
        break;
      }
      caw.write(line);
      caw.write("\n");
    }
    return caw.toString();
  }
}
