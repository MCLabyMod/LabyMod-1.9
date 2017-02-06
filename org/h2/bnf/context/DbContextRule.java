package org.h2.bnf.context;

import java.util.HashMap;
import java.util.HashSet;
import org.h2.bnf.Bnf;
import org.h2.bnf.BnfVisitor;
import org.h2.bnf.Rule;
import org.h2.bnf.RuleElement;
import org.h2.bnf.RuleHead;
import org.h2.bnf.RuleList;
import org.h2.bnf.Sentence;
import org.h2.command.Parser;
import org.h2.message.DbException;
import org.h2.util.StringUtils;

public class DbContextRule
  implements Rule
{
  public static final int COLUMN = 0;
  public static final int TABLE = 1;
  public static final int TABLE_ALIAS = 2;
  public static final int NEW_TABLE_ALIAS = 3;
  public static final int COLUMN_ALIAS = 4;
  public static final int SCHEMA = 5;
  public static final int PROCEDURE = 6;
  private final DbContents contents;
  private final int type;
  private String columnType;
  
  public DbContextRule(DbContents contents, int type)
  {
    this.contents = contents;
    this.type = type;
  }
  
  public void setColumnType(String columnType)
  {
    this.columnType = columnType;
  }
  
  public void setLinks(HashMap<String, RuleHead> ruleMap) {}
  
  public void accept(BnfVisitor visitor) {}
  
  public boolean autoComplete(Sentence sentence)
  {
    String query = sentence.getQuery();String s = query;
    String up = sentence.getQueryUpper();
    switch (this.type)
    {
    case 5: 
      DbSchema[] schemas = this.contents.getSchemas();
      String best = null;
      DbSchema bestSchema = null;
      for (DbSchema schema : schemas)
      {
        String name = StringUtils.toUpperEnglish(schema.name);
        if (up.startsWith(name))
        {
          if ((best == null) || (name.length() > best.length()))
          {
            best = name;
            bestSchema = schema;
          }
        }
        else if (((s.length() == 0) || (name.startsWith(up))) && 
          (s.length() < name.length()))
        {
          sentence.add(name, name.substring(s.length()), this.type);
          sentence.add(schema.quotedName + ".", schema.quotedName.substring(s.length()) + ".", 0);
        }
      }
      if (best != null)
      {
        sentence.setLastMatchedSchema(bestSchema);
        s = s.substring(best.length());
      }
      break;
    case 1: 
      DbSchema schema = sentence.getLastMatchedSchema();
      if (schema == null) {
        schema = this.contents.getDefaultSchema();
      }
      DbTableOrView[] tables = schema.getTables();
      String best = null;
      DbTableOrView bestTable = null;
      for (DbTableOrView table : tables)
      {
        String compare = up;
        String name = StringUtils.toUpperEnglish(table.getName());
        if (table.getQuotedName().length() > name.length())
        {
          name = table.getQuotedName();
          compare = query;
        }
        if (compare.startsWith(name))
        {
          if ((best == null) || (name.length() > best.length()))
          {
            best = name;
            bestTable = table;
          }
        }
        else if (((s.length() == 0) || (name.startsWith(compare))) && 
          (s.length() < name.length())) {
          sentence.add(table.getQuotedName(), table.getQuotedName().substring(s.length()), 0);
        }
      }
      if (best != null)
      {
        sentence.setLastMatchedTable(bestTable);
        sentence.addTable(bestTable);
        s = s.substring(best.length());
      }
      break;
    case 3: 
      s = autoCompleteTableAlias(sentence, true);
      break;
    case 2: 
      s = autoCompleteTableAlias(sentence, false);
      break;
    case 4: 
      int i = 0;
      if (query.indexOf(' ') >= 0)
      {
        for (; i < up.length(); i++)
        {
          char ch = up.charAt(i);
          if ((ch != '_') && (!Character.isLetterOrDigit(ch))) {
            break;
          }
        }
        if (i != 0)
        {
          String alias = up.substring(0, i);
          if (!Parser.isKeyword(alias, true)) {
            s = s.substring(alias.length());
          }
        }
      }
      break;
    case 0: 
      HashSet<DbTableOrView> set = sentence.getTables();
      String best = null;
      DbTableOrView last = sentence.getLastMatchedTable();
      if ((last != null) && (last.getColumns() != null)) {
        for (DbColumn column : last.getColumns())
        {
          String compare = up;
          String name = StringUtils.toUpperEnglish(column.getName());
          if (column.getQuotedName().length() > name.length())
          {
            name = column.getQuotedName();
            compare = query;
          }
          if ((compare.startsWith(name)) && ((this.columnType == null) || (column.getDataType().contains(this.columnType))))
          {
            String b = s.substring(name.length());
            if ((best == null) || (b.length() < best.length())) {
              best = b;
            } else if (((s.length() == 0) || (name.startsWith(compare))) && 
              (s.length() < name.length())) {
              sentence.add(column.getName(), column.getName().substring(s.length()), 0);
            }
          }
        }
      }
      for (DbSchema schema : this.contents.getSchemas()) {
        for (DbTableOrView table : schema.getTables()) {
          if ((table == last) || (set == null) || (set.contains(table))) {
            if ((table != null) && (table.getColumns() != null)) {
              for (DbColumn column : table.getColumns())
              {
                String name = StringUtils.toUpperEnglish(column.getName());
                if ((this.columnType == null) || (column.getDataType().contains(this.columnType))) {
                  if (up.startsWith(name))
                  {
                    String b = s.substring(name.length());
                    if ((best == null) || (b.length() < best.length())) {
                      best = b;
                    }
                  }
                  else if (((s.length() == 0) || (name.startsWith(up))) && 
                    (s.length() < name.length()))
                  {
                    sentence.add(column.getName(), column.getName().substring(s.length()), 0);
                  }
                }
              }
            }
          }
        }
      }
      if (best != null) {
        s = best;
      }
      break;
    case 6: 
      autoCompleteProcedure(sentence);
      break;
    default: 
      throw DbException.throwInternalError("type=" + this.type);
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
  
  private void autoCompleteProcedure(Sentence sentence)
  {
    DbSchema schema = sentence.getLastMatchedSchema();
    if (schema == null) {
      schema = this.contents.getDefaultSchema();
    }
    String incompleteSentence = sentence.getQueryUpper();
    String incompleteFunctionName = incompleteSentence;
    if (incompleteSentence.contains("(")) {
      incompleteFunctionName = incompleteSentence.substring(0, incompleteSentence.indexOf('(')).trim();
    }
    RuleElement openBracket = new RuleElement("(", "Function");
    RuleElement closeBracket = new RuleElement(")", "Function");
    RuleElement comma = new RuleElement(",", "Function");
    for (DbProcedure procedure : schema.getProcedures())
    {
      String procName = procedure.getName();
      if (procName.startsWith(incompleteFunctionName))
      {
        RuleElement procedureElement = new RuleElement(procName, "Function");
        
        RuleList rl = new RuleList(procedureElement, openBracket, false);
        if (incompleteSentence.contains("("))
        {
          for (DbColumn parameter : procedure.getParameters())
          {
            if (parameter.getPosition() > 1) {
              rl = new RuleList(rl, comma, false);
            }
            DbContextRule columnRule = new DbContextRule(this.contents, 0);
            
            String parameterType = parameter.getDataType();
            if (parameterType.contains("(")) {
              parameterType = parameterType.substring(0, parameterType.indexOf('('));
            }
            columnRule.setColumnType(parameterType);
            rl = new RuleList(rl, columnRule, false);
          }
          rl = new RuleList(rl, closeBracket, false);
        }
        rl.autoComplete(sentence);
      }
    }
  }
  
  private static String autoCompleteTableAlias(Sentence sentence, boolean newAlias)
  {
    String s = sentence.getQuery();
    String up = sentence.getQueryUpper();
    for (int i = 0; i < up.length(); i++)
    {
      char ch = up.charAt(i);
      if ((ch != '_') && (!Character.isLetterOrDigit(ch))) {
        break;
      }
    }
    if (i == 0) {
      return s;
    }
    String alias = up.substring(0, i);
    if (("SET".equals(alias)) || (Parser.isKeyword(alias, true))) {
      return s;
    }
    if (newAlias) {
      sentence.addAlias(alias, sentence.getLastTable());
    }
    HashMap<String, DbTableOrView> map = sentence.getAliases();
    if (((map != null) && (map.containsKey(alias))) || (sentence.getLastTable() == null))
    {
      if ((newAlias) && (s.length() == alias.length())) {
        return s;
      }
      s = s.substring(alias.length());
      if (s.length() == 0) {
        sentence.add(alias + ".", ".", 0);
      }
      return s;
    }
    HashSet<DbTableOrView> tables = sentence.getTables();
    if (tables != null)
    {
      String best = null;
      for (DbTableOrView table : tables)
      {
        String tableName = StringUtils.toUpperEnglish(table.getName());
        if ((alias.startsWith(tableName)) && ((best == null) || (tableName.length() > best.length())))
        {
          sentence.setLastMatchedTable(table);
          best = tableName;
        }
        else if ((s.length() == 0) || (tableName.startsWith(alias)))
        {
          sentence.add(tableName + ".", tableName.substring(s.length()) + ".", 0);
        }
      }
      if (best != null)
      {
        s = s.substring(best.length());
        if (s.length() == 0) {
          sentence.add(alias + ".", ".", 0);
        }
        return s;
      }
    }
    return s;
  }
}
