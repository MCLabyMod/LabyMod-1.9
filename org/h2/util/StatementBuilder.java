package org.h2.util;

public class StatementBuilder
{
  private final StringBuilder builder = new StringBuilder();
  private int index;
  
  public StatementBuilder() {}
  
  public StatementBuilder(String string)
  {
    this.builder.append(string);
  }
  
  public StatementBuilder append(String s)
  {
    this.builder.append(s);
    return this;
  }
  
  public StatementBuilder append(char c)
  {
    this.builder.append(c);
    return this;
  }
  
  public StatementBuilder append(long x)
  {
    this.builder.append(x);
    return this;
  }
  
  public StatementBuilder resetCount()
  {
    this.index = 0;
    return this;
  }
  
  public void appendOnlyFirst(String s)
  {
    if (this.index == 0) {
      this.builder.append(s);
    }
  }
  
  public void appendExceptFirst(String s)
  {
    if (this.index++ > 0) {
      this.builder.append(s);
    }
  }
  
  public String toString()
  {
    return this.builder.toString();
  }
  
  public int length()
  {
    return this.builder.length();
  }
}
