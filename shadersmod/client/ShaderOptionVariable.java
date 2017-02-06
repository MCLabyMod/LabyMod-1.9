package shadersmod.client;

import Config;
import StrUtils;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShaderOptionVariable
  extends ShaderOption
{
  private static final Pattern PATTERN_VARIABLE = Pattern.compile("^\\s*#define\\s+([A-Za-z0-9_]+)\\s+(-?[0-9\\.]*)F?f?\\s*(//.*)?$");
  
  public ShaderOptionVariable(String name, String description, String value, String[] values, String path)
  {
    super(name, description, value, values, value, path);
    
    setVisible(getValues().length > 1);
  }
  
  public String getSourceLine()
  {
    return "#define " + getName() + " " + getValue() + " // Shader option " + getValue();
  }
  
  public String getValueColor(String val)
  {
    return "§a";
  }
  
  public boolean matchesLine(String line)
  {
    Matcher m = PATTERN_VARIABLE.matcher(line);
    if (!m.matches()) {
      return false;
    }
    String defName = m.group(1);
    if (!defName.matches(getName())) {
      return false;
    }
    return true;
  }
  
  public static ShaderOption parseOption(String line, String path)
  {
    Matcher m = PATTERN_VARIABLE.matcher(line);
    if (!m.matches()) {
      return null;
    }
    String name = m.group(1);
    String value = m.group(2);
    String description = m.group(3);
    
    String vals = StrUtils.getSegment(description, "[", "]");
    if ((vals != null) && (vals.length() > 0)) {
      description = description.replace(vals, "").trim();
    }
    String[] values = parseValues(value, vals);
    if ((name == null) || (name.length() <= 0)) {
      return null;
    }
    path = StrUtils.removePrefix(path, "/shaders/");
    
    ShaderOption so = new ShaderOptionVariable(name, description, value, values, path);
    
    return so;
  }
  
  public static String[] parseValues(String value, String valuesStr)
  {
    String[] values = { value };
    if (valuesStr == null) {
      return values;
    }
    valuesStr = valuesStr.trim();
    valuesStr = StrUtils.removePrefix(valuesStr, "[");
    valuesStr = StrUtils.removeSuffix(valuesStr, "]");
    valuesStr = valuesStr.trim();
    if (valuesStr.length() <= 0) {
      return values;
    }
    String[] parts = Config.tokenize(valuesStr, " ");
    if (parts.length <= 0) {
      return values;
    }
    if (!Arrays.asList(parts).contains(value)) {
      parts = (String[])Config.addObjectToArray(parts, value, 0);
    }
    return parts;
  }
}
