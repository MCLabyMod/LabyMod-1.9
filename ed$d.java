import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ed$d
  extends ed.a
{
  private static final Pattern c = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+[d|D]");
  private static final Pattern d = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+[f|F]");
  private static final Pattern e = Pattern.compile("[-+]?[0-9]+[b|B]");
  private static final Pattern f = Pattern.compile("[-+]?[0-9]+[l|L]");
  private static final Pattern g = Pattern.compile("[-+]?[0-9]+[s|S]");
  private static final Pattern h = Pattern.compile("[-+]?[0-9]+");
  private static final Pattern i = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+");
  private static final Splitter j = Splitter.on(',').omitEmptyStrings();
  protected String b;
  
  public ed$d(String ☃, String ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public eb a()
    throws ec
  {
    try
    {
      if (c.matcher(this.b).matches()) {
        return new dp(Double.parseDouble(this.b.substring(0, this.b.length() - 1)));
      }
      if (d.matcher(this.b).matches()) {
        return new dr(Float.parseFloat(this.b.substring(0, this.b.length() - 1)));
      }
      if (e.matcher(this.b).matches()) {
        return new dm(Byte.parseByte(this.b.substring(0, this.b.length() - 1)));
      }
      if (f.matcher(this.b).matches()) {
        return new dv(Long.parseLong(this.b.substring(0, this.b.length() - 1)));
      }
      if (g.matcher(this.b).matches()) {
        return new dz(Short.parseShort(this.b.substring(0, this.b.length() - 1)));
      }
      if (h.matcher(this.b).matches()) {
        return new dt(Integer.parseInt(this.b));
      }
      if (i.matcher(this.b).matches()) {
        return new dp(Double.parseDouble(this.b));
      }
      if ((this.b.equalsIgnoreCase("true")) || (this.b.equalsIgnoreCase("false"))) {
        return new dm((byte)(Boolean.parseBoolean(this.b) ? 1 : 0));
      }
    }
    catch (NumberFormatException ☃)
    {
      this.b = this.b.replaceAll("\\\\\"", "\"");
      return new ea(this.b);
    }
    if ((this.b.startsWith("[")) && (this.b.endsWith("]")))
    {
      String ☃ = this.b.substring(1, this.b.length() - 1);
      
      String[] ☃ = (String[])Iterables.toArray(j.split(☃), String.class);
      try
      {
        int[] ☃ = new int[☃.length];
        for (int ☃ = 0; ☃ < ☃.length; ☃++) {
          ☃[☃] = Integer.parseInt(☃[☃].trim());
        }
        return new ds(☃);
      }
      catch (NumberFormatException ☃)
      {
        return new ea(this.b);
      }
    }
    if ((this.b.startsWith("\"")) && (this.b.endsWith("\""))) {
      this.b = this.b.substring(1, this.b.length() - 1);
    }
    this.b = this.b.replaceAll("\\\\\"", "\"");
    
    StringBuilder ☃ = new StringBuilder();
    for (int ☃ = 0; ☃ < this.b.length(); ☃++) {
      if ((☃ < this.b.length() - 1) && (this.b.charAt(☃) == '\\') && (this.b.charAt(☃ + 1) == '\\'))
      {
        ☃.append('\\');
        ☃++;
      }
      else
      {
        ☃.append(this.b.charAt(☃));
      }
    }
    return new ea(☃.toString());
  }
}
