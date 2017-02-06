package org.h2.bnf;

import java.util.ArrayList;
import java.util.HashMap;
import org.h2.util.New;

public class RuleList
  implements Rule
{
  private final boolean or;
  private final ArrayList<Rule> list;
  private boolean mapSet;
  
  public RuleList(Rule first, Rule next, boolean or)
  {
    this.list = New.arrayList();
    if (((first instanceof RuleList)) && (((RuleList)first).or == or)) {
      this.list.addAll(((RuleList)first).list);
    } else {
      this.list.add(first);
    }
    if (((next instanceof RuleList)) && (((RuleList)next).or == or)) {
      this.list.addAll(((RuleList)next).list);
    } else {
      this.list.add(next);
    }
    this.or = or;
  }
  
  public void accept(BnfVisitor visitor)
  {
    visitor.visitRuleList(this.or, this.list);
  }
  
  public void setLinks(HashMap<String, RuleHead> ruleMap)
  {
    if (!this.mapSet)
    {
      for (Rule r : this.list) {
        r.setLinks(ruleMap);
      }
      this.mapSet = true;
    }
  }
  
  public boolean autoComplete(Sentence sentence)
  {
    sentence.stopIfRequired();
    String old = sentence.getQuery();
    if (this.or)
    {
      for (Rule r : this.list)
      {
        sentence.setQuery(old);
        if (r.autoComplete(sentence)) {
          return true;
        }
      }
      return false;
    }
    for (Rule r : this.list) {
      if (!r.autoComplete(sentence))
      {
        sentence.setQuery(old);
        return false;
      }
    }
    return true;
  }
}
