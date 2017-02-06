package org.h2.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.sql.SQLException;
import java.util.ArrayList;
import org.h2.message.DbException;
import org.h2.security.SHA256;
import org.h2.store.FileLister;
import org.h2.store.FileStore;
import org.h2.store.fs.FileChannelInputStream;
import org.h2.store.fs.FileChannelOutputStream;
import org.h2.store.fs.FilePath;
import org.h2.store.fs.FilePathEncrypt;
import org.h2.store.fs.FilePathEncrypt.FileEncrypt;
import org.h2.store.fs.FileUtils;
import org.h2.util.Tool;

public class ChangeFileEncryption
  extends Tool
{
  private String directory;
  private String cipherType;
  private byte[] decrypt;
  private byte[] encrypt;
  private byte[] decryptKey;
  private byte[] encryptKey;
  
  public static void main(String... args)
    throws SQLException
  {
    new ChangeFileEncryption().runTool(args);
  }
  
  public void runTool(String... args)
    throws SQLException
  {
    String dir = ".";
    String cipher = null;
    char[] decryptPassword = null;
    char[] encryptPassword = null;
    String db = null;
    boolean quiet = false;
    for (int i = 0; (args != null) && (i < args.length); i++)
    {
      String arg = args[i];
      if (arg.equals("-dir"))
      {
        dir = args[(++i)];
      }
      else if (arg.equals("-cipher"))
      {
        cipher = args[(++i)];
      }
      else if (arg.equals("-db"))
      {
        db = args[(++i)];
      }
      else if (arg.equals("-decrypt"))
      {
        decryptPassword = args[(++i)].toCharArray();
      }
      else if (arg.equals("-encrypt"))
      {
        encryptPassword = args[(++i)].toCharArray();
      }
      else if (arg.equals("-quiet"))
      {
        quiet = true;
      }
      else
      {
        if ((arg.equals("-help")) || (arg.equals("-?")))
        {
          showUsage();
          return;
        }
        showUsageAndThrowUnsupportedOption(arg);
      }
    }
    if (((encryptPassword == null) && (decryptPassword == null)) || (cipher == null))
    {
      showUsage();
      throw new SQLException("Encryption or decryption password not set, or cipher not set");
    }
    try
    {
      process(dir, db, cipher, decryptPassword, encryptPassword, quiet);
    }
    catch (Exception e)
    {
      throw DbException.toSQLException(e);
    }
  }
  
  private static byte[] getFileEncryptionKey(char[] password)
  {
    if (password == null) {
      return null;
    }
    return SHA256.getKeyPasswordHash("file", password);
  }
  
  public static void execute(String dir, String db, String cipher, char[] decryptPassword, char[] encryptPassword, boolean quiet)
    throws SQLException
  {
    try
    {
      new ChangeFileEncryption().process(dir, db, cipher, decryptPassword, encryptPassword, quiet);
    }
    catch (Exception e)
    {
      throw DbException.toSQLException(e);
    }
  }
  
  private void process(String dir, String db, String cipher, char[] decryptPassword, char[] encryptPassword, boolean quiet)
    throws SQLException
  {
    dir = FileLister.getDir(dir);
    ChangeFileEncryption change = new ChangeFileEncryption();
    if (encryptPassword != null)
    {
      for (char c : encryptPassword) {
        if (c == ' ') {
          throw new SQLException("The file password may not contain spaces");
        }
      }
      change.encryptKey = FilePathEncrypt.getPasswordBytes(encryptPassword);
      change.encrypt = getFileEncryptionKey(encryptPassword);
    }
    if (decryptPassword != null)
    {
      change.decryptKey = FilePathEncrypt.getPasswordBytes(decryptPassword);
      change.decrypt = getFileEncryptionKey(decryptPassword);
    }
    change.out = this.out;
    change.directory = dir;
    change.cipherType = cipher;
    
    ArrayList<String> files = FileLister.getDatabaseFiles(dir, db, true);
    FileLister.tryUnlockDatabase(files, "encryption");
    files = FileLister.getDatabaseFiles(dir, db, false);
    if ((files.size() == 0) && (!quiet)) {
      printNoDatabaseFilesFound(dir, db);
    }
    for (String fileName : files)
    {
      String temp = dir + "/temp.db";
      FileUtils.delete(temp);
      FileUtils.move(fileName, temp);
      FileUtils.move(temp, fileName);
    }
    for (String fileName : files) {
      if (!FileUtils.isDirectory(fileName)) {
        change.process(fileName);
      }
    }
  }
  
  private void process(String fileName)
  {
    if (fileName.endsWith(".mv.db"))
    {
      try
      {
        copy(fileName);
      }
      catch (IOException e)
      {
        throw DbException.convertIOException(e, "Error encrypting / decrypting file " + fileName);
      }
      return;
    }
    FileStore in;
    FileStore in;
    if (this.decrypt == null) {
      in = FileStore.open(null, fileName, "r");
    } else {
      in = FileStore.open(null, fileName, "r", this.cipherType, this.decrypt);
    }
    try
    {
      in.init();
      copy(fileName, in, this.encrypt);
    }
    finally
    {
      in.closeSilently();
    }
  }
  
  private void copy(String fileName)
    throws IOException
  {
    if (FileUtils.isDirectory(fileName)) {
      return;
    }
    FileChannel fileIn = FilePath.get(fileName).open("r");
    FileChannel fileOut = null;
    String temp = this.directory + "/temp.db";
    try
    {
      if (this.decryptKey != null) {
        fileIn = new FilePathEncrypt.FileEncrypt(fileName, this.decryptKey, fileIn);
      }
      InputStream inStream = new FileChannelInputStream(fileIn, true);
      FileUtils.delete(temp);
      fileOut = FilePath.get(temp).open("rw");
      if (this.encryptKey != null) {
        fileOut = new FilePathEncrypt.FileEncrypt(temp, this.encryptKey, fileOut);
      }
      OutputStream outStream = new FileChannelOutputStream(fileOut, true);
      byte[] buffer = new byte['က'];
      long remaining = fileIn.size();
      long total = remaining;
      long time = System.currentTimeMillis();
      while (remaining > 0L)
      {
        if (System.currentTimeMillis() - time > 1000L)
        {
          this.out.println(fileName + ": " + (100L - 100L * remaining / total) + "%");
          time = System.currentTimeMillis();
        }
        int len = (int)Math.min(buffer.length, remaining);
        len = inStream.read(buffer, 0, len);
        outStream.write(buffer, 0, len);
        remaining -= len;
      }
      inStream.close();
      outStream.close();
    }
    finally
    {
      fileIn.close();
      if (fileOut != null) {
        fileOut.close();
      }
    }
    FileUtils.delete(fileName);
    FileUtils.move(temp, fileName);
  }
  
  private void copy(String fileName, FileStore in, byte[] key)
  {
    if (FileUtils.isDirectory(fileName)) {
      return;
    }
    String temp = this.directory + "/temp.db";
    FileUtils.delete(temp);
    FileStore fileOut;
    FileStore fileOut;
    if (key == null) {
      fileOut = FileStore.open(null, temp, "rw");
    } else {
      fileOut = FileStore.open(null, temp, "rw", this.cipherType, key);
    }
    fileOut.init();
    byte[] buffer = new byte['က'];
    long remaining = in.length() - 48L;
    long total = remaining;
    in.seek(48L);
    fileOut.seek(48L);
    long time = System.currentTimeMillis();
    while (remaining > 0L)
    {
      if (System.currentTimeMillis() - time > 1000L)
      {
        this.out.println(fileName + ": " + (100L - 100L * remaining / total) + "%");
        time = System.currentTimeMillis();
      }
      int len = (int)Math.min(buffer.length, remaining);
      in.readFully(buffer, 0, len);
      fileOut.write(buffer, 0, len);
      remaining -= len;
    }
    in.close();
    fileOut.close();
    FileUtils.delete(fileName);
    FileUtils.move(temp, fileName);
  }
}
