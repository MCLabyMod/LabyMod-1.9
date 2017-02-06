import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class os
{
  private static final Pattern a = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");
  
  public static String a(int ticks)
  {
    int i = ticks / 20;
    int j = i / 60;
    i %= 60;
    return j + ":" + i;
  }
  
  public static String a(String text)
  {
    return a.matcher(text).replaceAll("");
  }
  
  public static boolean b(String string)
  {
    return StringUtils.isEmpty(string);
  }
}
