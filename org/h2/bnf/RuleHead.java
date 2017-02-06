package org.h2.bnf;

public class RuleHead
{
  private final String section;
  private final String topic;
  private Rule rule;
  
  RuleHead(String section, String topic, Rule rule)
  {
    this.section = section;
    this.topic = topic;
    this.rule = rule;
  }
  
  public String getTopic()
  {
    return this.topic;
  }
  
  public Rule getRule()
  {
    return this.rule;
  }
  
  void setRule(Rule rule)
  {
    this.rule = rule;
  }
  
  public String getSection()
  {
    return this.section;
  }
}
