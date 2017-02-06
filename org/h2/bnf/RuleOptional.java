package org.h2.bnf;

import java.util.HashMap;

public class RuleOptional
  implements Rule
{
  private final Rule rule;
  private boolean mapSet;
  
  public RuleOptional(Rule rule)
  {
    this.rule = rule;
  }
  
  public void accept(BnfVisitor visitor)
  {
    visitor.visitRuleOptional(this.rule);
  }
  
  public void setLinks(HashMap<String, RuleHead> ruleMap)
  {
    if (!this.mapSet)
    {
      this.rule.setLinks(ruleMap);
      this.mapSet = true;
    }
  }
  
  public boolean autoComplete(Sentence sentence)
  {
    sentence.stopIfRequired();
    this.rule.autoComplete(sentence);
    return true;
  }
}
