package org.h2.bnf;

import java.util.HashMap;

public class RuleRepeat
  implements Rule
{
  private final Rule rule;
  private final boolean comma;
  
  public RuleRepeat(Rule rule, boolean comma)
  {
    this.rule = rule;
    this.comma = comma;
  }
  
  public void accept(BnfVisitor visitor)
  {
    visitor.visitRuleRepeat(this.comma, this.rule);
  }
  
  public void setLinks(HashMap<String, RuleHead> ruleMap) {}
  
  public boolean autoComplete(Sentence sentence)
  {
    sentence.stopIfRequired();
    while (this.rule.autoComplete(sentence)) {}
    return true;
  }
}
