package org.h2.engine;

import java.util.ArrayList;
import org.h2.command.CommandInterface;
import org.h2.result.ResultInterface;
import org.h2.util.New;
import org.h2.value.Value;

abstract class SessionWithState
  implements SessionInterface
{
  protected ArrayList<String> sessionState;
  protected boolean sessionStateChanged;
  private boolean sessionStateUpdating;
  
  protected void recreateSessionState()
  {
    if ((this.sessionState != null) && (this.sessionState.size() > 0))
    {
      this.sessionStateUpdating = true;
      try
      {
        for (String sql : this.sessionState)
        {
          CommandInterface ci = prepareCommand(sql, Integer.MAX_VALUE);
          ci.executeUpdate();
        }
      }
      finally
      {
        this.sessionStateUpdating = false;
        this.sessionStateChanged = false;
      }
    }
  }
  
  public void readSessionState()
  {
    if ((!this.sessionStateChanged) || (this.sessionStateUpdating)) {
      return;
    }
    this.sessionStateChanged = false;
    this.sessionState = New.arrayList();
    CommandInterface ci = prepareCommand("SELECT * FROM INFORMATION_SCHEMA.SESSION_STATE", Integer.MAX_VALUE);
    
    ResultInterface result = ci.executeQuery(0, false);
    while (result.next())
    {
      Value[] row = result.currentRow();
      this.sessionState.add(row[1].getString());
    }
  }
}
