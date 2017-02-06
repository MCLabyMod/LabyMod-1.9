package org.h2.server.web;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.h2.util.New;

public class PageParser
{
  private static final int TAB_WIDTH = 4;
  private final String page;
  private int pos;
  private final Map<String, Object> settings;
  private final int len;
  private StringBuilder result;
  
  private PageParser(String page, Map<String, Object> settings, int pos)
  {
    this.page = page;
    this.pos = pos;
    this.len = page.length();
    this.settings = settings;
    this.result = new StringBuilder(this.len);
  }
  
  public static String parse(String page, Map<String, Object> settings)
  {
    PageParser block = new PageParser(page, settings, 0);
    return block.replaceTags();
  }
  
  private void setError(int i)
  {
    String s = this.page.substring(0, i) + "####BUG####" + this.page.substring(i);
    s = escapeHtml(s);
    this.result = new StringBuilder();
    this.result.append(s);
  }
  
  private String parseBlockUntil(String end)
    throws ParseException
  {
    PageParser block = new PageParser(this.page, this.settings, this.pos);
    block.parseAll();
    if (!block.readIf(end)) {
      throw new ParseException(this.page, block.pos);
    }
    this.pos = block.pos;
    return block.result.toString();
  }
  
  private String replaceTags()
  {
    try
    {
      parseAll();
      if (this.pos != this.len) {
        setError(this.pos);
      }
    }
    catch (ParseException e)
    {
      setError(this.pos);
    }
    return this.result.toString();
  }
  
  private void parseAll()
    throws ParseException
  {
    StringBuilder buff = this.result;
    String p = this.page;
    for (int i = this.pos; i < this.len; i++)
    {
      char c = p.charAt(i);
      switch (c)
      {
      case '<': 
        if ((p.charAt(i + 3) == ':') && (p.charAt(i + 1) == '/'))
        {
          this.pos = i;
          return;
        }
        if (p.charAt(i + 2) == ':')
        {
          this.pos = i;
          String var;
          int start;
          if (readIf("<c:forEach"))
          {
            var = readParam("var");
            String items = readParam("items");
            read(">");
            start = this.pos;
            List<Object> list = (List)get(items);
            if (list == null)
            {
              this.result.append("?items?");
              list = New.arrayList();
            }
            if (list.size() == 0) {
              parseBlockUntil("</c:forEach>");
            }
            for (Object o : list)
            {
              this.settings.put(var, o);
              this.pos = start;
              String block = parseBlockUntil("</c:forEach>");
              this.result.append(block);
            }
          }
          else if (readIf("<c:if"))
          {
            String test = readParam("test");
            int eq = test.indexOf("=='");
            if (eq < 0)
            {
              setError(i);
              return;
            }
            String val = test.substring(eq + 3, test.length() - 1);
            test = test.substring(0, eq);
            String value = (String)get(test);
            read(">");
            String block = parseBlockUntil("</c:if>");
            this.pos -= 1;
            if (value.equals(val)) {
              this.result.append(block);
            }
          }
          else
          {
            setError(i);
            return;
          }
          i = this.pos;
        }
        else
        {
          buff.append(c);
        }
        break;
      case '$': 
        if ((p.length() > i + 1) && (p.charAt(i + 1) == '{'))
        {
          i += 2;
          int j = p.indexOf('}', i);
          if (j < 0)
          {
            setError(i);
            return;
          }
          String item = p.substring(i, j).trim();
          i = j;
          String s = (String)get(item);
          replaceTags(s);
        }
        else
        {
          buff.append(c);
        }
        break;
      default: 
        buff.append(c);
      }
    }
    this.pos = i;
  }
  
  private Object get(String item)
  {
    int dot = item.indexOf('.');
    if (dot >= 0)
    {
      String sub = item.substring(dot + 1);
      item = item.substring(0, dot);
      HashMap<String, Object> map = (HashMap)this.settings.get(item);
      if (map == null) {
        return "?" + item + "?";
      }
      return map.get(sub);
    }
    return this.settings.get(item);
  }
  
  private void replaceTags(String s)
  {
    if (s != null) {
      this.result.append(parse(s, this.settings));
    }
  }
  
  private String readParam(String name)
    throws ParseException
  {
    read(name);
    read("=");
    read("\"");
    int start = this.pos;
    while (this.page.charAt(this.pos) != '"') {
      this.pos += 1;
    }
    int end = this.pos;
    read("\"");
    String s = this.page.substring(start, end);
    return parse(s, this.settings);
  }
  
  private void skipSpaces()
  {
    while (this.page.charAt(this.pos) == ' ') {
      this.pos += 1;
    }
  }
  
  private void read(String s)
    throws ParseException
  {
    if (!readIf(s)) {
      throw new ParseException(s, this.pos);
    }
  }
  
  private boolean readIf(String s)
  {
    skipSpaces();
    if (this.page.regionMatches(this.pos, s, 0, s.length()))
    {
      this.pos += s.length();
      skipSpaces();
      return true;
    }
    return false;
  }
  
  static String escapeHtmlData(String s)
  {
    return escapeHtml(s, false);
  }
  
  public static String escapeHtml(String s)
  {
    return escapeHtml(s, true);
  }
  
  private static String escapeHtml(String s, boolean convertBreakAndSpace)
  {
    if (s == null) {
      return null;
    }
    if ((convertBreakAndSpace) && 
      (s.length() == 0)) {
      return "&nbsp;";
    }
    StringBuilder buff = new StringBuilder(s.length());
    boolean convertSpace = true;
    for (int i = 0; i < s.length(); i++)
    {
      char c = s.charAt(i);
      if ((c == ' ') || (c == '\t'))
      {
        for (int j = 0; j < (c == ' ' ? 1 : 4); j++) {
          if ((convertSpace) && (convertBreakAndSpace))
          {
            buff.append("&nbsp;");
          }
          else
          {
            buff.append(' ');
            convertSpace = true;
          }
        }
      }
      else
      {
        convertSpace = false;
        switch (c)
        {
        case '$': 
          buff.append("&#36;");
          break;
        case '<': 
          buff.append("&lt;");
          break;
        case '>': 
          buff.append("&gt;");
          break;
        case '&': 
          buff.append("&amp;");
          break;
        case '"': 
          buff.append("&quot;");
          break;
        case '\'': 
          buff.append("&#39;");
          break;
        case '\n': 
          if (convertBreakAndSpace)
          {
            buff.append("<br />");
            convertSpace = true;
          }
          else
          {
            buff.append(c);
          }
          break;
        default: 
          if (c >= '') {
            buff.append("&#").append(c).append(';');
          } else {
            buff.append(c);
          }
          break;
        }
      }
    }
    return buff.toString();
  }
  
  static String escapeJavaScript(String s)
  {
    if (s == null) {
      return null;
    }
    if (s.length() == 0) {
      return "";
    }
    StringBuilder buff = new StringBuilder(s.length());
    for (int i = 0; i < s.length(); i++)
    {
      char c = s.charAt(i);
      switch (c)
      {
      case '"': 
        buff.append("\\\"");
        break;
      case '\'': 
        buff.append("\\'");
        break;
      case '\\': 
        buff.append("\\\\");
        break;
      case '\n': 
        buff.append("\\n");
        break;
      case '\r': 
        buff.append("\\r");
        break;
      case '\t': 
        buff.append("\\t");
        break;
      default: 
        buff.append(c);
      }
    }
    return buff.toString();
  }
}
