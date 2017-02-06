package org.h2.bnf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import org.h2.bnf.context.DbContextRule;
import org.h2.tools.Csv;
import org.h2.util.New;
import org.h2.util.StringUtils;
import org.h2.util.Utils;

public class Bnf
{
  private final HashMap<String, RuleHead> ruleMap = New.hashMap();
  private String syntax;
  private String currentToken;
  private String[] tokens;
  private char firstChar;
  private int index;
  private Rule lastRepeat;
  private ArrayList<RuleHead> statements;
  private String currentTopic;
  
  public static Bnf getInstance(Reader csv)
    throws SQLException, IOException
  {
    Bnf bnf = new Bnf();
    if (csv == null)
    {
      byte[] data = Utils.getResource("/org/h2/res/help.csv");
      csv = new InputStreamReader(new ByteArrayInputStream(data));
    }
    bnf.parse(csv);
    return bnf;
  }
  
  private void addFixedRule(String name, int fixedType)
  {
    Rule rule = new RuleFixed(fixedType);
    addRule(name, "Fixed", rule);
  }
  
  private RuleHead addRule(String topic, String section, Rule rule)
  {
    RuleHead head = new RuleHead(section, topic, rule);
    String key = StringUtils.toLowerEnglish(topic.trim().replace(' ', '_'));
    if (this.ruleMap.get(key) != null) {
      throw new AssertionError("already exists: " + topic);
    }
    this.ruleMap.put(key, head);
    return head;
  }
  
  private void parse(Reader reader)
    throws SQLException, IOException
  {
    Rule functions = null;
    this.statements = New.arrayList();
    Csv csv = new Csv();
    csv.setLineCommentCharacter('#');
    ResultSet rs = csv.read(reader, null);
    while (rs.next())
    {
      String section = rs.getString("SECTION").trim();
      if (!section.startsWith("System"))
      {
        String topic = rs.getString("TOPIC");
        this.syntax = rs.getString("SYNTAX").trim();
        this.currentTopic = section;
        this.tokens = tokenize();
        this.index = 0;
        Rule rule = parseRule();
        if (section.startsWith("Command")) {
          rule = new RuleList(rule, new RuleElement(";\n\n", this.currentTopic), false);
        }
        RuleHead head = addRule(topic, section, rule);
        if (section.startsWith("Function"))
        {
          if (functions == null) {
            functions = rule;
          } else {
            functions = new RuleList(rule, functions, true);
          }
        }
        else if (section.startsWith("Commands")) {
          this.statements.add(head);
        }
      }
    }
    addRule("@func@", "Function", functions);
    addFixedRule("@ymd@", 0);
    addFixedRule("@hms@", 1);
    addFixedRule("@nanos@", 2);
    addFixedRule("anything_except_single_quote", 3);
    addFixedRule("anything_except_double_quote", 4);
    addFixedRule("anything_until_end_of_line", 5);
    addFixedRule("anything_until_end_comment", 6);
    addFixedRule("anything_except_two_dollar_signs", 8);
    addFixedRule("anything", 7);
    addFixedRule("@hex_start@", 10);
    addFixedRule("@concat@", 11);
    addFixedRule("@az_@", 12);
    addFixedRule("@af@", 13);
    addFixedRule("@digit@", 14);
    addFixedRule("@open_bracket@", 15);
    addFixedRule("@close_bracket@", 16);
  }
  
  public void visit(BnfVisitor visitor, String s)
  {
    this.syntax = s;
    this.tokens = tokenize();
    this.index = 0;
    Rule rule = parseRule();
    rule.setLinks(this.ruleMap);
    rule.accept(visitor);
  }
  
  public static boolean startWithSpace(String s)
  {
    return (s.length() > 0) && (Character.isWhitespace(s.charAt(0)));
  }
  
  public static String getRuleMapKey(String token)
  {
    StringBuilder buff = new StringBuilder();
    for (char ch : token.toCharArray()) {
      if (Character.isUpperCase(ch)) {
        buff.append('_').append(Character.toLowerCase(ch));
      } else {
        buff.append(ch);
      }
    }
    return buff.toString();
  }
  
  public RuleHead getRuleHead(String title)
  {
    return (RuleHead)this.ruleMap.get(title);
  }
  
  private Rule parseRule()
  {
    read();
    return parseOr();
  }
  
  private Rule parseOr()
  {
    Rule r = parseList();
    if (this.firstChar == '|')
    {
      read();
      r = new RuleList(r, parseOr(), true);
    }
    this.lastRepeat = r;
    return r;
  }
  
  private Rule parseList()
  {
    Rule r = parseToken();
    if ((this.firstChar != '|') && (this.firstChar != ']') && (this.firstChar != '}') && (this.firstChar != 0)) {
      r = new RuleList(r, parseList(), false);
    }
    this.lastRepeat = r;
    return r;
  }
  
  private Rule parseToken()
  {
    Rule r;
    Rule r;
    if (((this.firstChar >= 'A') && (this.firstChar <= 'Z')) || ((this.firstChar >= 'a') && (this.firstChar <= 'z')))
    {
      r = new RuleElement(this.currentToken, this.currentTopic);
    }
    else if (this.firstChar == '[')
    {
      read();
      Rule r2 = parseOr();
      Rule r = new RuleOptional(r2);
      if (this.firstChar != ']') {
        throw new AssertionError("expected ], got " + this.currentToken + " syntax:" + this.syntax);
      }
    }
    else if (this.firstChar == '{')
    {
      read();
      Rule r = parseOr();
      if (this.firstChar != '}') {
        throw new AssertionError("expected }, got " + this.currentToken + " syntax:" + this.syntax);
      }
    }
    else if ("@commaDots@".equals(this.currentToken))
    {
      Rule r = new RuleList(new RuleElement(",", this.currentTopic), this.lastRepeat, false);
      r = new RuleRepeat(r, true);
    }
    else
    {
      Rule r;
      if ("@dots@".equals(this.currentToken)) {
        r = new RuleRepeat(this.lastRepeat, false);
      } else {
        r = new RuleElement(this.currentToken, this.currentTopic);
      }
    }
    this.lastRepeat = r;
    read();
    return r;
  }
  
  private void read()
  {
    if (this.index < this.tokens.length)
    {
      this.currentToken = this.tokens[(this.index++)];
      this.firstChar = this.currentToken.charAt(0);
    }
    else
    {
      this.currentToken = "";
      this.firstChar = '\000';
    }
  }
  
  private String[] tokenize()
  {
    ArrayList<String> list = New.arrayList();
    this.syntax = StringUtils.replaceAll(this.syntax, "yyyy-MM-dd", "@ymd@");
    this.syntax = StringUtils.replaceAll(this.syntax, "hh:mm:ss", "@hms@");
    this.syntax = StringUtils.replaceAll(this.syntax, "nnnnnnnnn", "@nanos@");
    this.syntax = StringUtils.replaceAll(this.syntax, "function", "@func@");
    this.syntax = StringUtils.replaceAll(this.syntax, "0x", "@hexStart@");
    this.syntax = StringUtils.replaceAll(this.syntax, ",...", "@commaDots@");
    this.syntax = StringUtils.replaceAll(this.syntax, "...", "@dots@");
    this.syntax = StringUtils.replaceAll(this.syntax, "||", "@concat@");
    this.syntax = StringUtils.replaceAll(this.syntax, "a-z|_", "@az_@");
    this.syntax = StringUtils.replaceAll(this.syntax, "A-Z|_", "@az_@");
    this.syntax = StringUtils.replaceAll(this.syntax, "A-F", "@af@");
    this.syntax = StringUtils.replaceAll(this.syntax, "0-9", "@digit@");
    this.syntax = StringUtils.replaceAll(this.syntax, "'['", "@openBracket@");
    this.syntax = StringUtils.replaceAll(this.syntax, "']'", "@closeBracket@");
    StringTokenizer tokenizer = getTokenizer(this.syntax);
    while (tokenizer.hasMoreTokens())
    {
      String s = tokenizer.nextToken();
      
      s = StringUtils.cache(s);
      if ((s.length() != 1) || 
        (" \r\n".indexOf(s.charAt(0)) < 0)) {
        list.add(s);
      }
    }
    return (String[])list.toArray(new String[list.size()]);
  }
  
  public HashMap<String, String> getNextTokenList(String query)
  {
    Sentence sentence = new Sentence();
    sentence.setQuery(query);
    try
    {
      for (RuleHead head : this.statements) {
        if (head.getSection().startsWith("Commands"))
        {
          sentence.start();
          if (head.getRule().autoComplete(sentence)) {
            break;
          }
        }
      }
    }
    catch (IllegalStateException e) {}
    return sentence.getNext();
  }
  
  public void linkStatements()
  {
    for (RuleHead r : this.ruleMap.values()) {
      r.getRule().setLinks(this.ruleMap);
    }
  }
  
  public void updateTopic(String topic, DbContextRule rule)
  {
    topic = StringUtils.toLowerEnglish(topic);
    RuleHead head = (RuleHead)this.ruleMap.get(topic);
    if (head == null)
    {
      head = new RuleHead("db", topic, rule);
      this.ruleMap.put(topic, head);
      this.statements.add(head);
    }
    else
    {
      head.setRule(rule);
    }
  }
  
  public ArrayList<RuleHead> getStatements()
  {
    return this.statements;
  }
  
  public static StringTokenizer getTokenizer(String s)
  {
    return new StringTokenizer(s, " [](){}|.,\r\n<>:-+*/=<\">!'$", true);
  }
}
