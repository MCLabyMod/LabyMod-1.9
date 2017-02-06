package org.h2.bnf;

import java.util.HashMap;

public abstract interface Rule
{
  public abstract void setLinks(HashMap<String, RuleHead> paramHashMap);
  
  public abstract boolean autoComplete(Sentence paramSentence);
  
  public abstract void accept(BnfVisitor paramBnfVisitor);
}
