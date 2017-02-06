package org.h2.command.dml;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.h2.api.JavaObjectSerializer;
import org.h2.command.Prepared;
import org.h2.engine.Database;
import org.h2.engine.Session;
import org.h2.engine.SysProperties;
import org.h2.expression.Expression;
import org.h2.message.DbException;
import org.h2.security.SHA256;
import org.h2.store.DataHandler;
import org.h2.store.FileStore;
import org.h2.store.FileStoreInputStream;
import org.h2.store.FileStoreOutputStream;
import org.h2.store.LobStorageBackend;
import org.h2.store.fs.FileUtils;
import org.h2.tools.CompressTool;
import org.h2.util.IOUtils;
import org.h2.util.SmallLRUCache;
import org.h2.util.TempFileDeleter;
import org.h2.value.Value;

abstract class ScriptBase
  extends Prepared
  implements DataHandler
{
  private static final String SCRIPT_SQL = "script.sql";
  protected OutputStream out;
  protected InputStream in;
  private Expression fileNameExpr;
  private Expression password;
  private String fileName;
  private String cipher;
  private FileStore store;
  private String compressionAlgorithm;
  
  ScriptBase(Session session)
  {
    super(session);
  }
  
  public void setCipher(String c)
  {
    this.cipher = c;
  }
  
  private boolean isEncrypted()
  {
    return this.cipher != null;
  }
  
  public void setPassword(Expression password)
  {
    this.password = password;
  }
  
  public void setFileNameExpr(Expression file)
  {
    this.fileNameExpr = file;
  }
  
  protected String getFileName()
  {
    if ((this.fileNameExpr != null) && (this.fileName == null))
    {
      this.fileName = this.fileNameExpr.optimize(this.session).getValue(this.session).getString();
      if ((this.fileName == null) || (this.fileName.trim().length() == 0)) {
        this.fileName = "script.sql";
      }
      this.fileName = (SysProperties.getScriptDirectory() + this.fileName);
    }
    return this.fileName;
  }
  
  public boolean isTransactional()
  {
    return false;
  }
  
  void deleteStore()
  {
    String file = getFileName();
    if (file != null) {
      FileUtils.delete(file);
    }
  }
  
  private void initStore()
  {
    Database db = this.session.getDatabase();
    byte[] key = null;
    if ((this.cipher != null) && (this.password != null))
    {
      char[] pass = this.password.optimize(this.session).getValue(this.session).getString().toCharArray();
      
      key = SHA256.getKeyPasswordHash("script", pass);
    }
    String file = getFileName();
    this.store = FileStore.open(db, file, "rw", this.cipher, key);
    this.store.setCheckedWriting(false);
    this.store.init();
  }
  
  void openOutput()
  {
    String file = getFileName();
    if (file == null) {
      return;
    }
    if (isEncrypted())
    {
      initStore();
      this.out = new FileStoreOutputStream(this.store, this, this.compressionAlgorithm);
      
      this.out = new BufferedOutputStream(this.out, 131072);
    }
    else
    {
      OutputStream o;
      try
      {
        o = FileUtils.newOutputStream(file, false);
      }
      catch (IOException e)
      {
        throw DbException.convertIOException(e, null);
      }
      this.out = new BufferedOutputStream(o, 4096);
      this.out = CompressTool.wrapOutputStream(this.out, this.compressionAlgorithm, "script.sql");
    }
  }
  
  void openInput()
  {
    String file = getFileName();
    if (file == null) {
      return;
    }
    if (isEncrypted())
    {
      initStore();
      this.in = new FileStoreInputStream(this.store, this, this.compressionAlgorithm != null, false);
    }
    else
    {
      InputStream inStream;
      try
      {
        inStream = FileUtils.newInputStream(file);
      }
      catch (IOException e)
      {
        throw DbException.convertIOException(e, file);
      }
      this.in = new BufferedInputStream(inStream, 4096);
      this.in = CompressTool.wrapInputStream(this.in, this.compressionAlgorithm, "script.sql");
      if (this.in == null) {
        throw DbException.get(90124, "script.sql in " + file);
      }
    }
  }
  
  void closeIO()
  {
    IOUtils.closeSilently(this.out);
    this.out = null;
    IOUtils.closeSilently(this.in);
    this.in = null;
    if (this.store != null)
    {
      this.store.closeSilently();
      this.store = null;
    }
  }
  
  public boolean needRecompile()
  {
    return false;
  }
  
  public String getDatabasePath()
  {
    return null;
  }
  
  public FileStore openFile(String name, String mode, boolean mustExist)
  {
    return null;
  }
  
  public void checkPowerOff()
  {
    this.session.getDatabase().checkPowerOff();
  }
  
  public void checkWritingAllowed()
  {
    this.session.getDatabase().checkWritingAllowed();
  }
  
  public int getMaxLengthInplaceLob()
  {
    return this.session.getDatabase().getMaxLengthInplaceLob();
  }
  
  public TempFileDeleter getTempFileDeleter()
  {
    return this.session.getDatabase().getTempFileDeleter();
  }
  
  public String getLobCompressionAlgorithm(int type)
  {
    return this.session.getDatabase().getLobCompressionAlgorithm(type);
  }
  
  public void setCompressionAlgorithm(String algorithm)
  {
    this.compressionAlgorithm = algorithm;
  }
  
  public Object getLobSyncObject()
  {
    return this;
  }
  
  public SmallLRUCache<String, String[]> getLobFileListCache()
  {
    return null;
  }
  
  public LobStorageBackend getLobStorage()
  {
    return null;
  }
  
  public int readLob(long lobId, byte[] hmac, long offset, byte[] buff, int off, int length)
  {
    throw DbException.throwInternalError();
  }
  
  public JavaObjectSerializer getJavaObjectSerializer()
  {
    return this.session.getDataHandler().getJavaObjectSerializer();
  }
}
