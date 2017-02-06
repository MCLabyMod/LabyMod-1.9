package org.h2.bnf;

import java.util.ArrayList;

public abstract interface BnfVisitor
{
  public abstract void visitRuleElement(boolean paramBoolean, String paramString, Rule paramRule);
  
  public abstract void visitRuleRepeat(boolean paramBoolean, Rule paramRule);
  
  public abstract void visitRuleFixed(int paramInt);
  
  public abstract void visitRuleList(boolean paramBoolean, ArrayList<Rule> paramArrayList);
  
  public abstract void visitRuleOptional(Rule paramRule);
}
