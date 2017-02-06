package org.h2.bnf;

import java.util.HashMap;

public class RuleFixed
  implements Rule
{
  public static final int YMD = 0;
  public static final int HMS = 1;
  public static final int NANOS = 2;
  public static final int ANY_EXCEPT_SINGLE_QUOTE = 3;
  public static final int ANY_EXCEPT_DOUBLE_QUOTE = 4;
  public static final int ANY_UNTIL_EOL = 5;
  public static final int ANY_UNTIL_END = 6;
  public static final int ANY_WORD = 7;
  public static final int ANY_EXCEPT_2_DOLLAR = 8;
  public static final int HEX_START = 10;
  public static final int CONCAT = 11;
  public static final int AZ_UNDERSCORE = 12;
  public static final int AF = 13;
  public static final int DIGIT = 14;
  public static final int OPEN_BRACKET = 15;
  public static final int CLOSE_BRACKET = 16;
  private final int type;
  
  RuleFixed(int type)
  {
    this.type = type;
  }
  
  public void accept(BnfVisitor visitor)
  {
    visitor.visitRuleFixed(this.type);
  }
  
  public void setLinks(HashMap<String, RuleHead> ruleMap) {}
  
  public boolean autoComplete(Sentence sentence)
  {
    sentence.stopIfRequired();
    String query = sentence.getQuery();
    String s = query;
    switch (this.type)
    {
    case 0: 
      while ((s.length() > 0) && ("0123456789-".indexOf(s.charAt(0)) >= 0)) {
        s = s.substring(1);
      }
      if (s.length() == 0) {
        sentence.add("2006-01-01", "1", 1);
      }
      break;
    case 1: 
      while ((s.length() > 0) && ("0123456789:".indexOf(s.charAt(0)) >= 0)) {
        s = s.substring(1);
      }
      if (s.length() == 0) {
        sentence.add("12:00:00", "1", 1);
      }
      break;
    case 2: 
      while ((s.length() > 0) && (Character.isDigit(s.charAt(0)))) {
        s = s.substring(1);
      }
      if (s.length() == 0) {
        sentence.add("nanoseconds", "0", 1);
      }
      break;
    case 3: 
      for (;;)
      {
        if ((s.length() > 0) && (s.charAt(0) != '\''))
        {
          s = s.substring(1);
        }
        else
        {
          if (!s.startsWith("''")) {
            break;
          }
          s = s.substring(2);
        }
      }
      if (s.length() == 0)
      {
        sentence.add("anything", "Hello World", 1);
        sentence.add("'", "'", 1);
      }
      break;
    case 8: 
      while ((s.length() > 0) && (!s.startsWith("$$"))) {
        s = s.substring(1);
      }
      if (s.length() == 0)
      {
        sentence.add("anything", "Hello World", 1);
        sentence.add("$$", "$$", 1);
      }
      break;
    case 4: 
      for (;;)
      {
        if ((s.length() > 0) && (s.charAt(0) != '"'))
        {
          s = s.substring(1);
        }
        else
        {
          if (!s.startsWith("\"\"")) {
            break;
          }
          s = s.substring(2);
        }
      }
      if (s.length() == 0)
      {
        sentence.add("anything", "identifier", 1);
        sentence.add("\"", "\"", 1);
      }
      break;
    case 7: 
      while ((s.length() > 0) && (!Bnf.startWithSpace(s))) {
        s = s.substring(1);
      }
      if (s.length() == 0) {
        sentence.add("anything", "anything", 1);
      }
      break;
    case 10: 
      if ((s.startsWith("0X")) || (s.startsWith("0x"))) {
        s = s.substring(2);
      } else if ("0".equals(s)) {
        sentence.add("0x", "x", 1);
      } else if (s.length() == 0) {
        sentence.add("0x", "0x", 1);
      }
      break;
    case 11: 
      if (s.equals("|")) {
        sentence.add("||", "|", 1);
      } else if (s.startsWith("||")) {
        s = s.substring(2);
      } else if (s.length() == 0) {
        sentence.add("||", "||", 1);
      }
      break;
    case 12: 
      if ((s.length() > 0) && ((Character.isLetter(s.charAt(0))) || (s.charAt(0) == '_'))) {
        s = s.substring(1);
      }
      if (s.length() == 0) {
        sentence.add("character", "A", 1);
      }
      break;
    case 13: 
      if (s.length() > 0)
      {
        char ch = Character.toUpperCase(s.charAt(0));
        if ((ch >= 'A') && (ch <= 'F')) {
          s = s.substring(1);
        }
      }
      if (s.length() == 0) {
        sentence.add("hex character", "0A", 1);
      }
      break;
    case 14: 
      if ((s.length() > 0) && (Character.isDigit(s.charAt(0)))) {
        s = s.substring(1);
      }
      if (s.length() == 0) {
        sentence.add("digit", "1", 1);
      }
      break;
    case 15: 
      if (s.length() == 0) {
        sentence.add("[", "[", 1);
      } else if (s.charAt(0) == '[') {
        s = s.substring(1);
      }
      break;
    case 16: 
      if (s.length() == 0) {
        sentence.add("]", "]", 1);
      } else if (s.charAt(0) == ']') {
        s = s.substring(1);
      }
      break;
    case 5: 
    case 6: 
    case 9: 
    default: 
      throw new AssertionError("type=" + this.type);
    }
    if (!s.equals(query))
    {
      while (Bnf.startWithSpace(s)) {
        s = s.substring(1);
      }
      sentence.setQuery(s);
      return true;
    }
    return false;
  }
}
