package org.h2.command.ddl;

import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.expression.Expression;
import org.h2.message.DbException;
import org.h2.security.SHA256;
import org.h2.util.StringUtils;
import org.h2.value.Value;

public class AlterUser
  extends DefineCommand
{
  private int type;
  private User user;
  private String newName;
  private Expression password;
  private Expression salt;
  private Expression hash;
  private boolean admin;
  
  public AlterUser(Session session)
  {
    super(session);
  }
  
  public void setType(int type)
  {
    this.type = type;
  }
  
  public void setNewName(String newName)
  {
    this.newName = newName;
  }
  
  public void setUser(User user)
  {
    this.user = user;
  }
  
  public void setAdmin(boolean admin)
  {
    this.admin = admin;
  }
  
  public void setSalt(Expression e)
  {
    this.salt = e;
  }
  
  public void setHash(Expression e)
  {
    this.hash = e;
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
    this.session.commit(true);
    Database db = this.session.getDatabase();
    switch (this.type)
    {
    case 19: 
      if (this.user != this.session.getUser()) {
        this.session.getUser().checkAdmin();
      }
      if ((this.hash != null) && (this.salt != null))
      {
        this.user.setSaltAndHash(getByteArray(this.salt), getByteArray(this.hash));
      }
      else
      {
        String name = this.newName == null ? this.user.getName() : this.newName;
        char[] passwordChars = getCharArray(this.password);
        byte[] userPasswordHash = SHA256.getKeyPasswordHash(name, passwordChars);
        this.user.setUserPasswordHash(userPasswordHash);
      }
      break;
    case 18: 
      this.session.getUser().checkAdmin();
      if ((db.findUser(this.newName) != null) || (this.newName.equals(this.user.getName()))) {
        throw DbException.get(90033, this.newName);
      }
      db.renameDatabaseObject(this.session, this.user, this.newName);
      break;
    case 17: 
      this.session.getUser().checkAdmin();
      if (!this.admin) {
        this.user.checkOwnsNoSchemas();
      }
      this.user.setAdmin(this.admin);
      break;
    default: 
      DbException.throwInternalError("type=" + this.type);
    }
    db.updateMeta(this.session, this.user);
    return 0;
  }
  
  public int getType()
  {
    return this.type;
  }
}
