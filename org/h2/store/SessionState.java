package org.h2.store;

class SessionState
{
  public int sessionId;
  public int lastCommitLog;
  public int lastCommitPos;
  public PageStoreInDoubtTransaction inDoubtTransaction;
  
  public boolean isCommitted(int logId, int pos)
  {
    if (logId != this.lastCommitLog) {
      return this.lastCommitLog > logId;
    }
    return this.lastCommitPos >= pos;
  }
  
  public String toString()
  {
    return "sessionId:" + this.sessionId + " log:" + this.lastCommitLog + " pos:" + this.lastCommitPos + " inDoubt:" + this.inDoubtTransaction;
  }
}
