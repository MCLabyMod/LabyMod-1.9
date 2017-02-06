package org.h2.engine;

import java.util.HashMap;
import org.h2.util.New;
import org.h2.util.StringUtils;

public class Mode
{
  static final String REGULAR = "REGULAR";
  private static final HashMap<String, Mode> MODES = ;
  public boolean aliasColumnName;
  public boolean convertInsertNullToZero;
  public boolean convertOnlyToSmallerScale;
  public boolean indexDefinitionInCreateTable;
  public boolean lowerCaseIdentifiers;
  public boolean nullConcatIsNull;
  public boolean squareBracketQuotedNames;
  public boolean supportOffsetFetch = true;
  public boolean systemColumns;
  public boolean uniqueIndexSingleNull;
  public boolean uniqueIndexSingleNullExceptAllColumnsAreNull;
  public boolean treatEmptyStringsAsNull;
  public boolean sysDummy1;
  public boolean allowPlusForStringConcat;
  public boolean logIsLogBase10;
  public boolean serialColumnIsNotPK;
  public boolean swapConvertFunctionParameters;
  public boolean isolationLevelInSelectOrInsertStatement;
  public boolean onDuplicateKeyUpdate;
  private final String name;
  
  static
  {
    Mode mode = new Mode("REGULAR");
    mode.nullConcatIsNull = true;
    add(mode);
    
    mode = new Mode("DB2");
    mode.aliasColumnName = true;
    mode.supportOffsetFetch = true;
    mode.sysDummy1 = true;
    mode.isolationLevelInSelectOrInsertStatement = true;
    add(mode);
    
    mode = new Mode("Derby");
    mode.aliasColumnName = true;
    mode.uniqueIndexSingleNull = true;
    mode.supportOffsetFetch = true;
    mode.sysDummy1 = true;
    mode.isolationLevelInSelectOrInsertStatement = true;
    add(mode);
    
    mode = new Mode("HSQLDB");
    mode.aliasColumnName = true;
    mode.convertOnlyToSmallerScale = true;
    mode.nullConcatIsNull = true;
    mode.uniqueIndexSingleNull = true;
    mode.allowPlusForStringConcat = true;
    add(mode);
    
    mode = new Mode("MSSQLServer");
    mode.aliasColumnName = true;
    mode.squareBracketQuotedNames = true;
    mode.uniqueIndexSingleNull = true;
    mode.allowPlusForStringConcat = true;
    mode.swapConvertFunctionParameters = true;
    add(mode);
    
    mode = new Mode("MySQL");
    mode.convertInsertNullToZero = true;
    mode.indexDefinitionInCreateTable = true;
    mode.lowerCaseIdentifiers = true;
    mode.onDuplicateKeyUpdate = true;
    add(mode);
    
    mode = new Mode("Oracle");
    mode.aliasColumnName = true;
    mode.convertOnlyToSmallerScale = true;
    mode.uniqueIndexSingleNullExceptAllColumnsAreNull = true;
    mode.treatEmptyStringsAsNull = true;
    add(mode);
    
    mode = new Mode("PostgreSQL");
    mode.aliasColumnName = true;
    mode.nullConcatIsNull = true;
    mode.supportOffsetFetch = true;
    mode.systemColumns = true;
    mode.logIsLogBase10 = true;
    mode.serialColumnIsNotPK = true;
    add(mode);
  }
  
  private Mode(String name)
  {
    this.name = name;
  }
  
  private static void add(Mode mode)
  {
    MODES.put(StringUtils.toUpperEnglish(mode.name), mode);
  }
  
  public static Mode getInstance(String name)
  {
    return (Mode)MODES.get(StringUtils.toUpperEnglish(name));
  }
  
  public String getName()
  {
    return this.name;
  }
}
