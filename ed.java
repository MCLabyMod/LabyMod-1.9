import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ed
{
  private static final Logger a = ;
  private static final Pattern b = Pattern.compile("\\[[-+\\d|,\\s]+\\]");
  
  public static dn a(String ☃)
    throws ec
  {
    ☃ = ☃.trim();
    if (!☃.startsWith("{")) {
      throw new ec("Invalid tag encountered, expected '{' as first char.");
    }
    if (b(☃) != 1) {
      throw new ec("Encountered multiple top tags, only one expected");
    }
    return (dn)a("tag", ☃).a();
  }
  
  static int b(String ☃)
    throws ec
  {
    int ☃ = 0;
    boolean ☃ = false;
    Stack<Character> ☃ = new Stack();
    
    int ☃ = 0;
    while (☃ < ☃.length())
    {
      char ☃ = ☃.charAt(☃);
      if (☃ == '"')
      {
        if (b(☃, ☃))
        {
          if (!☃) {
            throw new ec("Illegal use of \\\": " + ☃);
          }
        }
        else {
          ☃ = !☃;
        }
      }
      else if (!☃) {
        if ((☃ == '{') || (☃ == '['))
        {
          if (☃.isEmpty()) {
            ☃++;
          }
          ☃.push(Character.valueOf(☃));
        }
        else
        {
          if ((☃ == '}') && ((☃.isEmpty()) || (((Character)☃.pop()).charValue() != '{'))) {
            throw new ec("Unbalanced curly brackets {}: " + ☃);
          }
          if ((☃ == ']') && ((☃.isEmpty()) || (((Character)☃.pop()).charValue() != '['))) {
            throw new ec("Unbalanced square brackets []: " + ☃);
          }
        }
      }
      ☃++;
    }
    if (☃) {
      throw new ec("Unbalanced quotation: " + ☃);
    }
    if (!☃.isEmpty()) {
      throw new ec("Unbalanced brackets: " + ☃);
    }
    if ((☃ == 0) && (!☃.isEmpty())) {
      ☃ = 1;
    }
    return ☃;
  }
  
  static ed.a a(String... ☃)
    throws ec
  {
    return a(☃[0], ☃[1]);
  }
  
  static ed.a a(String ☃, String ☃)
    throws ec
  {
    ☃ = ☃.trim();
    if (☃.startsWith("{"))
    {
      ☃ = ☃.substring(1, ☃.length() - 1);
      
      ed.b ☃ = new ed.b(☃);
      while (☃.length() > 0)
      {
        String ☃ = b(☃, true);
        if (☃.length() > 0)
        {
          boolean ☃ = false;
          ☃.b.add(a(☃, ☃));
        }
        if (☃.length() < ☃.length() + 1) {
          break;
        }
        char ☃ = ☃.charAt(☃.length());
        if ((☃ != ',') && (☃ != '{') && (☃ != '}') && (☃ != '[') && (☃ != ']')) {
          throw new ec("Unexpected token '" + ☃ + "' at: " + ☃.substring(☃.length()));
        }
        ☃ = ☃.substring(☃.length() + 1);
      }
      return ☃;
    }
    if ((☃.startsWith("[")) && (!b.matcher(☃).matches()))
    {
      ☃ = ☃.substring(1, ☃.length() - 1);
      
      ed.c ☃ = new ed.c(☃);
      while (☃.length() > 0)
      {
        String ☃ = b(☃, false);
        if (☃.length() > 0)
        {
          boolean ☃ = true;
          ☃.b.add(a(☃, ☃));
        }
        if (☃.length() < ☃.length() + 1) {
          break;
        }
        char ☃ = ☃.charAt(☃.length());
        if ((☃ != ',') && (☃ != '{') && (☃ != '}') && (☃ != '[') && (☃ != ']')) {
          throw new ec("Unexpected token '" + ☃ + "' at: " + ☃.substring(☃.length()));
        }
        ☃ = ☃.substring(☃.length() + 1);
      }
      return ☃;
    }
    return new ed.d(☃, ☃);
  }
  
  private static ed.a a(String ☃, boolean ☃)
    throws ec
  {
    String ☃ = c(☃, ☃);
    String ☃ = d(☃, ☃);
    return a(new String[] { ☃, ☃ });
  }
  
  private static String b(String ☃, boolean ☃)
    throws ec
  {
    int ☃ = a(☃, ':');
    int ☃ = a(☃, ',');
    if (☃)
    {
      if (☃ == -1) {
        throw new ec("Unable to locate name/value separator for string: " + ☃);
      }
      if ((☃ != -1) && (☃ < ☃)) {
        throw new ec("Name error at: " + ☃);
      }
    }
    else if ((☃ == -1) || (☃ > ☃))
    {
      ☃ = -1;
    }
    return a(☃, ☃);
  }
  
  private static String a(String ☃, int ☃)
    throws ec
  {
    Stack<Character> ☃ = new Stack();
    int ☃ = ☃ + 1;
    boolean ☃ = false;
    boolean ☃ = false;
    boolean ☃ = false;
    int ☃ = 0;
    while (☃ < ☃.length())
    {
      char ☃ = ☃.charAt(☃);
      if (☃ == '"')
      {
        if (b(☃, ☃))
        {
          if (!☃) {
            throw new ec("Illegal use of \\\": " + ☃);
          }
        }
        else
        {
          ☃ = !☃;
          if ((☃) && (!☃)) {
            ☃ = true;
          }
          if (!☃) {
            ☃ = ☃;
          }
        }
      }
      else if (!☃) {
        if ((☃ == '{') || (☃ == '['))
        {
          ☃.push(Character.valueOf(☃));
        }
        else
        {
          if ((☃ == '}') && ((☃.isEmpty()) || (((Character)☃.pop()).charValue() != '{'))) {
            throw new ec("Unbalanced curly brackets {}: " + ☃);
          }
          if ((☃ == ']') && ((☃.isEmpty()) || (((Character)☃.pop()).charValue() != '['))) {
            throw new ec("Unbalanced square brackets []: " + ☃);
          }
          if ((☃ == ',') && 
            (☃.isEmpty())) {
            return ☃.substring(0, ☃);
          }
        }
      }
      if (!Character.isWhitespace(☃))
      {
        if ((!☃) && (☃) && (☃ != ☃)) {
          return ☃.substring(0, ☃ + 1);
        }
        ☃ = true;
      }
      ☃++;
    }
    return ☃.substring(0, ☃);
  }
  
  private static String c(String ☃, boolean ☃)
    throws ec
  {
    if (☃)
    {
      ☃ = ☃.trim();
      if ((☃.startsWith("{")) || (☃.startsWith("["))) {
        return "";
      }
    }
    int ☃ = a(☃, ':');
    if (☃ == -1)
    {
      if (☃) {
        return "";
      }
      throw new ec("Unable to locate name/value separator for string: " + ☃);
    }
    return ☃.substring(0, ☃).trim();
  }
  
  private static String d(String ☃, boolean ☃)
    throws ec
  {
    if (☃)
    {
      ☃ = ☃.trim();
      if ((☃.startsWith("{")) || (☃.startsWith("["))) {
        return ☃;
      }
    }
    int ☃ = a(☃, ':');
    if (☃ == -1)
    {
      if (☃) {
        return ☃;
      }
      throw new ec("Unable to locate name/value separator for string: " + ☃);
    }
    return ☃.substring(☃ + 1).trim();
  }
  
  private static int a(String ☃, char ☃)
  {
    int ☃ = 0;
    boolean ☃ = true;
    while (☃ < ☃.length())
    {
      char ☃ = ☃.charAt(☃);
      if (☃ == '"')
      {
        if (!b(☃, ☃)) {
          ☃ = !☃;
        }
      }
      else if (☃)
      {
        if (☃ == ☃) {
          return ☃;
        }
        if ((☃ == '{') || (☃ == '[')) {
          return -1;
        }
      }
      ☃++;
    }
    return -1;
  }
  
  private static boolean b(String ☃, int ☃)
  {
    return (☃ > 0) && (☃.charAt(☃ - 1) == '\\') && (!b(☃, ☃ - 1));
  }
  
  static abstract class a
  {
    protected String a;
    
    public abstract eb a()
      throws ec;
  }
  
  static class b
    extends ed.a
  {
    protected List<ed.a> b = Lists.newArrayList();
    
    public b(String ☃)
    {
      this.a = ☃;
    }
    
    public eb a()
      throws ec
    {
      dn ☃ = new dn();
      for (ed.a ☃ : this.b) {
        ☃.a(☃.a, ☃.a());
      }
      return ☃;
    }
  }
  
  static class c
    extends ed.a
  {
    protected List<ed.a> b = Lists.newArrayList();
    
    public c(String ☃)
    {
      this.a = ☃;
    }
    
    public eb a()
      throws ec
    {
      du ☃ = new du();
      for (ed.a ☃ : this.b) {
        ☃.a(☃.a());
      }
      return ☃;
    }
  }
  
  static class d
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
    
    public d(String ☃, String ☃)
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
}
