package org.h2.engine;

import java.util.ArrayList;
import org.h2.table.Table;

public abstract interface DbObject
{
  public static final int TABLE_OR_VIEW = 0;
  public static final int INDEX = 1;
  public static final int USER = 2;
  public static final int SEQUENCE = 3;
  public static final int TRIGGER = 4;
  public static final int CONSTRAINT = 5;
  public static final int SETTING = 6;
  public static final int ROLE = 7;
  public static final int RIGHT = 8;
  public static final int FUNCTION_ALIAS = 9;
  public static final int SCHEMA = 10;
  public static final int CONSTANT = 11;
  public static final int USER_DATATYPE = 12;
  public static final int COMMENT = 13;
  public static final int AGGREGATE = 14;
  
  public abstract String getSQL();
  
  public abstract ArrayList<DbObject> getChildren();
  
  public abstract Database getDatabase();
  
  public abstract int getId();
  
  public abstract String getName();
  
  public abstract String getCreateSQLForCopy(Table paramTable, String paramString);
  
  public abstract String getCreateSQL();
  
  public abstract String getDropSQL();
  
  public abstract int getType();
  
  public abstract void removeChildrenAndResources(Session paramSession);
  
  public abstract void checkRename();
  
  public abstract void rename(String paramString);
  
  public abstract boolean isTemporary();
  
  public abstract void setTemporary(boolean paramBoolean);
  
  public abstract void setComment(String paramString);
  
  public abstract String getComment();
}
