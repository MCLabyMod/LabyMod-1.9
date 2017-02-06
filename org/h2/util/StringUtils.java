package org.h2.util;

import java.lang.ref.SoftReference;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Locale;
import org.h2.engine.Constants;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;

public class StringUtils
{
  private static SoftReference<String[]> softCache = new SoftReference(null);
  private static long softCacheCreated;
  private static final char[] HEX = "0123456789abcdef".toCharArray();
  private static final int[] HEX_DECODE = new int[103];
  private static final int TO_UPPER_CACHE_LENGTH = 2048;
  private static final int TO_UPPER_CACHE_MAX_ENTRY_LENGTH = 64;
  private static final String[][] TO_UPPER_CACHE = new String['ࠀ'][];
  
  static
  {
    for (int i = 0; i < HEX_DECODE.length; i++) {
      HEX_DECODE[i] = -1;
    }
    for (int i = 0; i <= 9; i++) {
      HEX_DECODE[(i + 48)] = i;
    }
    for (int i = 0; i <= 5; i++) {
      HEX_DECODE[(i + 97)] = (HEX_DECODE[(i + 65)] = i + 10);
    }
  }
  
  private static String[] getCache()
  {
    if (softCache != null)
    {
      String[] cache = (String[])softCache.get();
      if (cache != null) {
        return cache;
      }
    }
    long time = System.currentTimeMillis();
    if ((softCacheCreated != 0L) && (time - softCacheCreated < 5000L)) {
      return null;
    }
    try
    {
      String[] cache = new String[SysProperties.OBJECT_CACHE_SIZE];
      softCache = new SoftReference(cache);
      return cache;
    }
    finally
    {
      softCacheCreated = System.currentTimeMillis();
    }
  }
  
  public static boolean equals(String a, String b)
  {
    if (a == null) {
      return b == null;
    }
    return a.equals(b);
  }
  
  public static String toUpperEnglish(String s)
  {
    if (s.length() > 64) {
      return s.toUpperCase(Locale.ENGLISH);
    }
    int index = s.hashCode() & 0x7FF;
    String[] e = TO_UPPER_CACHE[index];
    if ((e != null) && 
      (e[0].equals(s))) {
      return e[1];
    }
    String s2 = s.toUpperCase(Locale.ENGLISH);
    e = new String[] { s, s2 };
    TO_UPPER_CACHE[index] = e;
    return s2;
  }
  
  public static String toLowerEnglish(String s)
  {
    return s.toLowerCase(Locale.ENGLISH);
  }
  
  public static boolean startsWithIgnoreCase(String s, String start)
  {
    if (s.length() < start.length()) {
      return false;
    }
    return s.substring(0, start.length()).equalsIgnoreCase(start);
  }
  
  public static String quoteStringSQL(String s)
  {
    if (s == null) {
      return "NULL";
    }
    int length = s.length();
    StringBuilder buff = new StringBuilder(length + 2);
    buff.append('\'');
    for (int i = 0; i < length; i++)
    {
      char c = s.charAt(i);
      if (c == '\'') {
        buff.append(c);
      } else if ((c < ' ') || (c > '')) {
        return "STRINGDECODE(" + quoteStringSQL(javaEncode(s)) + ")";
      }
      buff.append(c);
    }
    buff.append('\'');
    return buff.toString();
  }
  
  public static String javaEncode(String s)
  {
    int length = s.length();
    StringBuilder buff = new StringBuilder(length);
    for (int i = 0; i < length; i++)
    {
      char c = s.charAt(i);
      switch (c)
      {
      case '\t': 
        buff.append("\\t");
        break;
      case '\n': 
        buff.append("\\n");
        break;
      case '\f': 
        buff.append("\\f");
        break;
      case '\r': 
        buff.append("\\r");
        break;
      case '"': 
        buff.append("\\\"");
        break;
      case '\\': 
        buff.append("\\\\");
        break;
      default: 
        int ch = c & 0xFFFF;
        if ((ch >= 32) && (ch < 128))
        {
          buff.append(c);
        }
        else
        {
          buff.append("\\u");
          String hex = Integer.toHexString(ch);
          for (int len = hex.length(); len < 4; len++) {
            buff.append('0');
          }
          buff.append(hex);
        }
        break;
      }
    }
    return buff.toString();
  }
  
  public static String addAsterisk(String s, int index)
  {
    if (s != null)
    {
      index = Math.min(index, s.length());
      s = s.substring(0, index) + "[*]" + s.substring(index);
    }
    return s;
  }
  
  private static DbException getFormatException(String s, int i)
  {
    return DbException.get(90095, addAsterisk(s, i));
  }
  
  public static String javaDecode(String s)
  {
    int length = s.length();
    StringBuilder buff = new StringBuilder(length);
    for (int i = 0; i < length; i++)
    {
      char c = s.charAt(i);
      if (c == '\\')
      {
        if (i + 1 >= s.length()) {
          throw getFormatException(s, i);
        }
        c = s.charAt(++i);
        switch (c)
        {
        case 't': 
          buff.append('\t');
          break;
        case 'r': 
          buff.append('\r');
          break;
        case 'n': 
          buff.append('\n');
          break;
        case 'b': 
          buff.append('\b');
          break;
        case 'f': 
          buff.append('\f');
          break;
        case '#': 
          buff.append('#');
          break;
        case '=': 
          buff.append('=');
          break;
        case ':': 
          buff.append(':');
          break;
        case '"': 
          buff.append('"');
          break;
        case '\\': 
          buff.append('\\');
          break;
        case 'u': 
          try
          {
            c = (char)Integer.parseInt(s.substring(i + 1, i + 5), 16);
          }
          catch (NumberFormatException e)
          {
            throw getFormatException(s, i);
          }
          i += 4;
          buff.append(c);
          break;
        default: 
          if ((c >= '0') && (c <= '9'))
          {
            try
            {
              c = (char)Integer.parseInt(s.substring(i, i + 3), 8);
            }
            catch (NumberFormatException e)
            {
              throw getFormatException(s, i);
            }
            i += 2;
            buff.append(c); continue;
          }
          throw getFormatException(s, i);
        }
      }
      else
      {
        buff.append(c);
      }
    }
    return buff.toString();
  }
  
  public static String quoteJavaString(String s)
  {
    if (s == null) {
      return "null";
    }
    return "\"" + javaEncode(s) + "\"";
  }
  
  public static String quoteJavaStringArray(String[] array)
  {
    if (array == null) {
      return "null";
    }
    StatementBuilder buff = new StatementBuilder("new String[]{");
    for (String a : array)
    {
      buff.appendExceptFirst(", ");
      buff.append(quoteJavaString(a));
    }
    return buff.append('}').toString();
  }
  
  public static String quoteJavaIntArray(int[] array)
  {
    if (array == null) {
      return "null";
    }
    StatementBuilder buff = new StatementBuilder("new int[]{");
    for (int a : array)
    {
      buff.appendExceptFirst(", ");
      buff.append(a);
    }
    return buff.append('}').toString();
  }
  
  public static String enclose(String s)
  {
    if (s.startsWith("(")) {
      return s;
    }
    return "(" + s + ")";
  }
  
  public static String unEnclose(String s)
  {
    if ((s.startsWith("(")) && (s.endsWith(")"))) {
      return s.substring(1, s.length() - 1);
    }
    return s;
  }
  
  public static String urlEncode(String s)
  {
    try
    {
      return URLEncoder.encode(s, "UTF-8");
    }
    catch (Exception e)
    {
      throw DbException.convert(e);
    }
  }
  
  public static String urlDecode(String encoded)
  {
    int length = encoded.length();
    byte[] buff = new byte[length];
    int j = 0;
    for (int i = 0; i < length; i++)
    {
      char ch = encoded.charAt(i);
      if (ch == '+')
      {
        buff[(j++)] = 32;
      }
      else if (ch == '%')
      {
        buff[(j++)] = ((byte)Integer.parseInt(encoded.substring(i + 1, i + 3), 16));
        i += 2;
      }
      else
      {
        if ((SysProperties.CHECK) && (
          (ch > '') || (ch < ' '))) {
          throw new IllegalArgumentException("Unexpected char " + ch + " decoding " + encoded);
        }
        buff[(j++)] = ((byte)ch);
      }
    }
    String s = new String(buff, 0, j, Constants.UTF8);
    return s;
  }
  
  public static String[] arraySplit(String s, char separatorChar, boolean trim)
  {
    if (s == null) {
      return null;
    }
    int length = s.length();
    if (length == 0) {
      return new String[0];
    }
    ArrayList<String> list = New.arrayList();
    StringBuilder buff = new StringBuilder(length);
    for (int i = 0; i < length; i++)
    {
      char c = s.charAt(i);
      if (c == separatorChar)
      {
        String e = buff.toString();
        list.add(trim ? e.trim() : e);
        buff.setLength(0);
      }
      else if ((c == '\\') && (i < length - 1))
      {
        buff.append(s.charAt(++i));
      }
      else
      {
        buff.append(c);
      }
    }
    String e = buff.toString();
    list.add(trim ? e.trim() : e);
    String[] array = new String[list.size()];
    list.toArray(array);
    return array;
  }
  
  public static String arrayCombine(String[] list, char separatorChar)
  {
    StatementBuilder buff = new StatementBuilder();
    for (String s : list)
    {
      buff.appendExceptFirst(String.valueOf(separatorChar));
      if (s == null) {
        s = "";
      }
      int j = 0;
      for (int length = s.length(); j < length; j++)
      {
        char c = s.charAt(j);
        if ((c == '\\') || (c == separatorChar)) {
          buff.append('\\');
        }
        buff.append(c);
      }
    }
    return buff.toString();
  }
  
  public static String xmlAttr(String name, String value)
  {
    return " " + name + "=\"" + xmlText(value) + "\"";
  }
  
  public static String xmlNode(String name, String attributes, String content)
  {
    return xmlNode(name, attributes, content, true);
  }
  
  public static String xmlNode(String name, String attributes, String content, boolean indent)
  {
    String start = name + attributes;
    if (content == null) {
      return "<" + start + "/>\n";
    }
    if ((indent) && (content.indexOf('\n') >= 0)) {
      content = "\n" + indent(content);
    }
    return "<" + start + ">" + content + "</" + name + ">\n";
  }
  
  public static String indent(String s)
  {
    return indent(s, 4, true);
  }
  
  public static String indent(String s, int spaces, boolean newline)
  {
    StringBuilder buff = new StringBuilder(s.length() + spaces);
    for (int i = 0; i < s.length();)
    {
      for (int j = 0; j < spaces; j++) {
        buff.append(' ');
      }
      int n = s.indexOf('\n', i);
      n = n < 0 ? s.length() : n + 1;
      buff.append(s.substring(i, n));
      i = n;
    }
    if ((newline) && (!s.endsWith("\n"))) {
      buff.append('\n');
    }
    return buff.toString();
  }
  
  public static String xmlComment(String data)
  {
    int idx = 0;
    for (;;)
    {
      idx = data.indexOf("--", idx);
      if (idx < 0) {
        break;
      }
      data = data.substring(0, idx + 1) + " " + data.substring(idx + 1);
    }
    if (data.indexOf('\n') >= 0) {
      return "<!--\n" + indent(data) + "-->\n";
    }
    return "<!-- " + data + " -->\n";
  }
  
  public static String xmlCData(String data)
  {
    if (data.contains("]]>")) {
      return xmlText(data);
    }
    boolean newline = data.endsWith("\n");
    data = "<![CDATA[" + data + "]]>";
    return newline ? data + "\n" : data;
  }
  
  public static String xmlStartDoc()
  {
    return "<?xml version=\"1.0\"?>\n";
  }
  
  public static String xmlText(String text)
  {
    return xmlText(text, false);
  }
  
  public static String xmlText(String text, boolean escapeNewline)
  {
    int length = text.length();
    StringBuilder buff = new StringBuilder(length);
    for (int i = 0; i < length; i++)
    {
      char ch = text.charAt(i);
      switch (ch)
      {
      case '<': 
        buff.append("&lt;");
        break;
      case '>': 
        buff.append("&gt;");
        break;
      case '&': 
        buff.append("&amp;");
        break;
      case '\'': 
        buff.append("&apos;");
        break;
      case '"': 
        buff.append("&quot;");
        break;
      case '\n': 
      case '\r': 
        if (escapeNewline) {
          buff.append("&#x").append(Integer.toHexString(ch)).append(';');
        } else {
          buff.append(ch);
        }
        break;
      case '\t': 
        buff.append(ch);
        break;
      default: 
        if ((ch < ' ') || (ch > '')) {
          buff.append("&#x").append(Integer.toHexString(ch)).append(';');
        } else {
          buff.append(ch);
        }
        break;
      }
    }
    return buff.toString();
  }
  
  public static String replaceAll(String s, String before, String after)
  {
    int next = s.indexOf(before);
    if (next < 0) {
      return s;
    }
    StringBuilder buff = new StringBuilder(s.length() - before.length() + after.length());
    
    int index = 0;
    do
    {
      buff.append(s.substring(index, next)).append(after);
      index = next + before.length();
      next = s.indexOf(before, index);
    } while (next >= 0);
    buff.append(s.substring(index));
    
    return buff.toString();
  }
  
  public static String quoteIdentifier(String s)
  {
    int length = s.length();
    StringBuilder buff = new StringBuilder(length + 2);
    buff.append('"');
    for (int i = 0; i < length; i++)
    {
      char c = s.charAt(i);
      if (c == '"') {
        buff.append(c);
      }
      buff.append(c);
    }
    return '"';
  }
  
  public static boolean isNullOrEmpty(String s)
  {
    return (s == null) || (s.length() == 0);
  }
  
  public static String quoteRemarkSQL(String sql)
  {
    sql = replaceAll(sql, "*/", "++/");
    return replaceAll(sql, "/*", "/++");
  }
  
  public static String pad(String string, int n, String padding, boolean right)
  {
    if (n < 0) {
      n = 0;
    }
    if (n < string.length()) {
      return string.substring(0, n);
    }
    if (n == string.length()) {
      return string;
    }
    char paddingChar;
    char paddingChar;
    if ((padding == null) || (padding.length() == 0)) {
      paddingChar = ' ';
    } else {
      paddingChar = padding.charAt(0);
    }
    StringBuilder buff = new StringBuilder(n);
    n -= string.length();
    if (right) {
      buff.append(string);
    }
    for (int i = 0; i < n; i++) {
      buff.append(paddingChar);
    }
    if (!right) {
      buff.append(string);
    }
    return buff.toString();
  }
  
  public static char[] cloneCharArray(char[] chars)
  {
    if (chars == null) {
      return null;
    }
    int len = chars.length;
    if (len == 0) {
      return chars;
    }
    char[] copy = new char[len];
    System.arraycopy(chars, 0, copy, 0, len);
    return copy;
  }
  
  public static String trim(String s, boolean leading, boolean trailing, String sp)
  {
    char space = (sp == null) || (sp.length() < 1) ? ' ' : sp.charAt(0);
    if (leading)
    {
      int len = s.length();int i = 0;
      while ((i < len) && (s.charAt(i) == space)) {
        i++;
      }
      s = i == 0 ? s : s.substring(i);
    }
    if (trailing)
    {
      int endIndex = s.length() - 1;
      int i = endIndex;
      while ((i >= 0) && (s.charAt(i) == space)) {
        i--;
      }
      s = i == endIndex ? s : s.substring(0, i + 1);
    }
    return s;
  }
  
  public static String cache(String s)
  {
    if (!SysProperties.OBJECT_CACHE) {
      return s;
    }
    if (s == null) {
      return s;
    }
    if (s.length() == 0) {
      return "";
    }
    int hash = s.hashCode();
    String[] cache = getCache();
    if (cache != null)
    {
      int index = hash & SysProperties.OBJECT_CACHE_SIZE - 1;
      String cached = cache[index];
      if ((cached != null) && 
        (s.equals(cached))) {
        return cached;
      }
      cache[index] = s;
    }
    return s;
  }
  
  public static String fromCacheOrNew(String s)
  {
    if (!SysProperties.OBJECT_CACHE) {
      return s;
    }
    if (s == null) {
      return s;
    }
    if (s.length() == 0) {
      return "";
    }
    int hash = s.hashCode();
    String[] cache = getCache();
    int index = hash & SysProperties.OBJECT_CACHE_SIZE - 1;
    if (cache == null) {
      return s;
    }
    String cached = cache[index];
    if ((cached != null) && 
      (s.equals(cached))) {
      return cached;
    }
    s = new String(s);
    cache[index] = s;
    return s;
  }
  
  public static void clearCache()
  {
    softCache = new SoftReference(null);
  }
  
  public static byte[] convertHexToBytes(String s)
  {
    int len = s.length();
    if (len % 2 != 0) {
      throw DbException.get(90003, s);
    }
    len /= 2;
    byte[] buff = new byte[len];
    int mask = 0;
    int[] hex = HEX_DECODE;
    try
    {
      for (int i = 0; i < len; i++)
      {
        int d = hex[s.charAt(i + i)] << 4 | hex[s.charAt(i + i + 1)];
        mask |= d;
        buff[i] = ((byte)d);
      }
    }
    catch (ArrayIndexOutOfBoundsException e)
    {
      throw DbException.get(90004, s);
    }
    if ((mask & 0xFF00) != 0) {
      throw DbException.get(90004, s);
    }
    return buff;
  }
  
  public static String convertBytesToHex(byte[] value)
  {
    return convertBytesToHex(value, value.length);
  }
  
  public static String convertBytesToHex(byte[] value, int len)
  {
    char[] buff = new char[len + len];
    char[] hex = HEX;
    for (int i = 0; i < len; i++)
    {
      int c = value[i] & 0xFF;
      buff[(i + i)] = hex[(c >> 4)];
      buff[(i + i + 1)] = hex[(c & 0xF)];
    }
    return new String(buff);
  }
  
  public static boolean isNumber(String s)
  {
    if (s.length() == 0) {
      return false;
    }
    for (char c : s.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }
  
  public static void appendZeroPadded(StringBuilder buff, int length, long positiveValue)
  {
    if (length == 2)
    {
      if (positiveValue < 10L) {
        buff.append('0');
      }
      buff.append(positiveValue);
    }
    else
    {
      String s = Long.toString(positiveValue);
      length -= s.length();
      while (length > 0)
      {
        buff.append('0');
        length--;
      }
      buff.append(s);
    }
  }
  
  public static String escapeMetaDataPattern(String pattern)
  {
    if ((pattern == null) || (pattern.length() == 0)) {
      return pattern;
    }
    return replaceAll(pattern, "\\", "\\\\");
  }
}
