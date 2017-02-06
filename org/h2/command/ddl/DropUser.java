package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;

public class DropUser
  extends DefineCommand
{
  private boolean ifExists;
  private String userName;
  
  public DropUser(Session session)
  {
    super(session);
  }
  
  public void setIfExists(boolean b)
  {
    this.ifExists = b;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public int update()
  {
    this.session.getUser().checkAdmin();
    this.session.commit(true);
    Database db = this.session.getDatabase();
    User user = db.findUser(this.userName);
    if (user == null)
    {
      if (!this.ifExists) {
        throw DbException.get(90032, this.userName);
      }
    }
    else
    {
      if (user == this.session.getUser())
      {
        int adminUserCount = 0;
        for (User u : db.getAllUsers()) {
          if (u.isAdmin()) {
            adminUserCount++;
          }
        }
        if (adminUserCount == 1) {
          throw DbException.get(90019);
        }
      }
      user.checkOwnsNoSchemas();
      db.removeDatabaseObject(this.session, user);
    }
    return 0;
  }
  
  public boolean isTransactional()
  {
    return false;
  }
  
  public int getType()
  {
    return 46;
  }
}
