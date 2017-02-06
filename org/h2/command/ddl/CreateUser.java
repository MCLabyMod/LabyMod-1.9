package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.expression.Expression;
import org.h2.message.DbException;
import org.h2.security.SHA256;
import org.h2.util.StringUtils;
import org.h2.value.Value;

public class CreateUser
  extends DefineCommand
{
  private String userName;
  private boolean admin;
  private Expression password;
  private Expression salt;
  private Expression hash;
  private boolean ifNotExists;
  private String comment;
  
  public CreateUser(Session session)
  {
    super(session);
  }
  
  public void setIfNotExists(boolean ifNotExists)
  {
    this.ifNotExists = ifNotExists;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  public void setPassword(Expression password)
  {
    this.password = password;
  }
  
  private char[] getCharArray(Expression e)
  {
    return e.optimize(this.session).getValue(this.session).getString().toCharArray();
  }
  
  private byte[] getByteArray(Expression e)
  {
    return StringUtils.convertHexToBytes(e.optimize(this.session).getValue(this.session).getString());
  }
  
  public int update()
  {
    this.session.getUser().checkAdmin();
    this.session.commit(true);
    Database db = this.session.getDatabase();
    if (db.findRole(this.userName) != null) {
      throw DbException.get(90069, this.userName);
    }
    if (db.findUser(this.userName) != null)
    {
      if (this.ifNotExists) {
        return 0;
      }
      throw DbException.get(90033, this.userName);
    }
    int id = getObjectId();
    User user = new User(db, id, this.userName, false);
    user.setAdmin(this.admin);
    user.setComment(this.comment);
    if ((this.hash != null) && (this.salt != null))
    {
      user.setSaltAndHash(getByteArray(this.salt), getByteArray(this.hash));
    }
    else if (this.password != null)
    {
      char[] passwordChars = getCharArray(this.password);
      byte[] userPasswordHash;
      byte[] userPasswordHash;
      if ((this.userName.length() == 0) && (passwordChars.length == 0)) {
        userPasswordHash = new byte[0];
      } else {
        userPasswordHash = SHA256.getKeyPasswordHash(this.userName, passwordChars);
      }
      user.setUserPasswordHash(userPasswordHash);
    }
    else
    {
      throw DbException.throwInternalError();
    }
    db.addDatabaseObject(this.session, user);
    return 0;
  }
  
  public void setSalt(Expression e)
  {
    this.salt = e;
  }
  
  public void setHash(Expression e)
  {
    this.hash = e;
  }
  
  public void setAdmin(boolean b)
  {
    this.admin = b;
  }
  
  public void setComment(String comment)
  {
    this.comment = comment;
  }
  
  public int getType()
  {
    return 32;
  }
}
