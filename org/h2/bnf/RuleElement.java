package org.h2.bnf;

import java.util.HashMap;
import org.h2.util.StringUtils;

public class RuleElement
  implements Rule
{
  private final boolean keyword;
  private final String name;
  private Rule link;
  private final int type;
  
  public RuleElement(String name, String topic)
  {
    this.name = name;
    this.keyword = ((name.length() == 1) || (name.equals(StringUtils.toUpperEnglish(name))));
    
    topic = StringUtils.toLowerEnglish(topic);
    this.type = (topic.startsWith("function") ? 2 : 1);
  }
  
  public void accept(BnfVisitor visitor)
  {
    visitor.visitRuleElement(this.keyword, this.name, this.link);
  }
  
  public void setLinks(HashMap<String, RuleHead> ruleMap)
  {
    if (this.link != null) {
      this.link.setLinks(ruleMap);
    }
    if (this.keyword) {
      return;
    }
    String test = Bnf.getRuleMapKey(this.name);
    for (int i = 0; i < test.length(); i++)
    {
      String t = test.substring(i);
      RuleHead r = (RuleHead)ruleMap.get(t);
      if (r != null)
      {
        this.link = r.getRule();
        return;
      }
    }
    throw new AssertionError("Unknown " + this.name + "/" + test);
  }
  
  public boolean autoComplete(Sentence sentence)
  {
    sentence.stopIfRequired();
    if (this.keyword)
    {
      String query = sentence.getQuery();
      String q = query.trim();
      String up = sentence.getQueryUpper().trim();
      if (up.startsWith(this.name))
      {
        query = query.substring(this.name.length());
        while ((!"_".equals(this.name)) && (Bnf.startWithSpace(query))) {
          query = query.substring(1);
        }
        sentence.setQuery(query);
        return true;
      }
      if (((q.length() == 0) || (this.name.startsWith(up))) && 
        (q.length() < this.name.length())) {
        sentence.add(this.name, this.name.substring(q.length()), this.type);
      }
      return false;
    }
    return this.link.autoComplete(sentence);
  }
}
