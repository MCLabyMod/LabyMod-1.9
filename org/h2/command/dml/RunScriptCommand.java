package org.h2.command.dml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import org.h2.command.Prepared;
import org.h2.engine.Constants;
import org.h2.engine.Session;
import org.h2.engine.User;
import org.h2.message.DbException;
import org.h2.result.ResultInterface;
import org.h2.util.ScriptReader;

public class RunScriptCommand
  extends ScriptBase
{
  private static final char UTF8_BOM = '﻿';
  private Charset charset = Constants.UTF8;
  
  public RunScriptCommand(Session session)
  {
    super(session);
  }
  
  public int update()
  {
    this.session.getUser().checkAdmin();
    int count = 0;
    try
    {
      openInput();
      BufferedReader reader = new BufferedReader(new InputStreamReader(this.in, this.charset));
      
      reader.mark(1);
      if (reader.read() != 65279) {
        reader.reset();
      }
      ScriptReader r = new ScriptReader(reader);
      for (;;)
      {
        String sql = r.readStatement();
        if (sql == null) {
          break;
        }
        execute(sql);
        count++;
        if ((count & 0x7F) == 0) {
          checkCanceled();
        }
      }
      reader.close();
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, null);
    }
    finally
    {
      closeIO();
    }
    return count;
  }
  
  private void execute(String sql)
  {
    try
    {
      Prepared command = this.session.prepare(sql);
      if (command.isQuery()) {
        command.query(0);
      } else {
        command.update();
      }
      if (this.session.getAutoCommit()) {
        this.session.commit(false);
      }
    }
    catch (DbException e)
    {
      throw e.addSQL(sql);
    }
  }
  
  public void setCharset(Charset charset)
  {
    this.charset = charset;
  }
  
  public ResultInterface queryMeta()
  {
    return null;
  }
  
  public int getType()
  {
    return 64;
  }
}
