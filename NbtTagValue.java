import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.lang3.StringEscapeUtils;

public class NbtTagValue
{
  private String[] parents = null;
  private String name = null;
  private int type = 0;
  private String value = null;
  private static final int TYPE_TEXT = 0;
  private static final int TYPE_PATTERN = 1;
  private static final int TYPE_IPATTERN = 2;
  private static final int TYPE_REGEX = 3;
  private static final int TYPE_IREGEX = 4;
  private static final String PREFIX_PATTERN = "pattern:";
  private static final String PREFIX_IPATTERN = "ipattern:";
  private static final String PREFIX_REGEX = "regex:";
  private static final String PREFIX_IREGEX = "iregex:";
  
  public NbtTagValue(String tag, String value)
  {
    String[] names = Config.tokenize(tag, ".");
    this.parents = ((String[])Arrays.copyOfRange(names, 0, names.length - 1));
    this.name = names[(names.length - 1)];
    if (value.startsWith("pattern:"))
    {
      this.type = 1;
      value = value.substring("pattern:".length());
    }
    else if (value.startsWith("ipattern:"))
    {
      this.type = 2;
      value = value.substring("ipattern:".length()).toLowerCase();
    }
    else if (value.startsWith("regex:"))
    {
      this.type = 3;
      value = value.substring("regex:".length());
    }
    else if (value.startsWith("iregex:"))
    {
      this.type = 4;
      value = value.substring("iregex:".length()).toLowerCase();
    }
    else
    {
      this.type = 0;
    }
    value = StringEscapeUtils.unescapeJava(value);
    
    this.value = value;
  }
  
  public boolean matches(dn nbt)
  {
    if (nbt == null) {
      return false;
    }
    eb tagBase = nbt;
    for (int i = 0; i < this.parents.length; i++)
    {
      String tag = this.parents[i];
      
      tagBase = getChildTag(tagBase, tag);
      if (tagBase == null) {
        return false;
      }
    }
    if (this.name.equals("*")) {
      return matchesAnyChild(tagBase);
    }
    tagBase = getChildTag(tagBase, this.name);
    if (tagBase == null) {
      return false;
    }
    if (matches(tagBase)) {
      return true;
    }
    return false;
  }
  
  private boolean matchesAnyChild(eb tagBase)
  {
    dn tagCompound;
    Iterator it;
    if ((tagBase instanceof dn))
    {
      tagCompound = (dn)tagBase;
      Set nbtKeySet = tagCompound.c();
      for (it = nbtKeySet.iterator(); it.hasNext();)
      {
        String key = (String)it.next();
        eb nbtBase = tagCompound.c(key);
        if (matches(nbtBase)) {
          return true;
        }
      }
    }
    if ((tagBase instanceof du))
    {
      du tagList = (du)tagBase;
      int count = tagList.c();
      for (int i = 0; i < count; i++)
      {
        eb nbtBase = tagList.h(i);
        if (matches(nbtBase)) {
          return true;
        }
      }
    }
    return false;
  }
  
  private static eb getChildTag(eb tagBase, String tag)
  {
    if ((tagBase instanceof dn))
    {
      dn tagCompound = (dn)tagBase;
      return tagCompound.c(tag);
    }
    if ((tagBase instanceof du))
    {
      du tagList = (du)tagBase;
      int index = Config.parseInt(tag, -1);
      if (index < 0) {
        return null;
      }
      return tagList.h(index);
    }
    return null;
  }
  
  private boolean matches(eb nbtBase)
  {
    if (nbtBase == null) {
      return false;
    }
    String nbtValue = getValue(nbtBase);
    if (nbtValue == null) {
      return false;
    }
    switch (this.type)
    {
    case 0: 
      return nbtValue.equals(this.value);
    case 1: 
      return matchesPattern(nbtValue, this.value);
    case 2: 
      return matchesPattern(nbtValue.toLowerCase(), this.value);
    case 3: 
      return matchesRegex(nbtValue, this.value);
    case 4: 
      return matchesRegex(nbtValue.toLowerCase(), this.value);
    }
    throw new IllegalArgumentException("Unknown NbtTagValue type: " + this.type);
  }
  
  private boolean matchesPattern(String str, String pattern)
  {
    return StrUtils.equalsMask(str, pattern, '*', '?');
  }
  
  private boolean matchesRegex(String str, String regex)
  {
    return str.matches(regex);
  }
  
  private static String getValue(eb nbtBase)
  {
    if (nbtBase == null) {
      return null;
    }
    if ((nbtBase instanceof ea))
    {
      ea nbtString = (ea)nbtBase;
      return nbtString.a_();
    }
    if ((nbtBase instanceof dt))
    {
      dt i = (dt)nbtBase;
      return Integer.toString(i.d());
    }
    if ((nbtBase instanceof dm))
    {
      dm b = (dm)nbtBase;
      return Byte.toString(b.f());
    }
    if ((nbtBase instanceof dz))
    {
      dz s = (dz)nbtBase;
      return Short.toString(s.e());
    }
    if ((nbtBase instanceof dv))
    {
      dv l = (dv)nbtBase;
      return Long.toString(l.c());
    }
    if ((nbtBase instanceof dr))
    {
      dr f = (dr)nbtBase;
      return Float.toString(f.h());
    }
    if ((nbtBase instanceof dp))
    {
      dp d = (dp)nbtBase;
      return Double.toString(d.g());
    }
    return nbtBase.toString();
  }
  
  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < this.parents.length; i++)
    {
      String parent = this.parents[i];
      if (i > 0) {
        sb.append(".");
      }
      sb.append(parent);
    }
    if (sb.length() > 0) {
      sb.append(".");
    }
    sb.append(this.name);
    sb.append(" = ");
    sb.append(this.value);
    
    return sb.toString();
  }
}
