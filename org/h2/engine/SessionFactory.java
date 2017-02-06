package org.h2.engine;

import java.sql.SQLException;

abstract interface SessionFactory
{
  public abstract SessionInterface createSession(ConnectionInfo paramConnectionInfo)
    throws SQLException;
}
