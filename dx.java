import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class dx
{
  public static dn a(InputStream ☃)
    throws IOException
  {
    DataInputStream ☃ = new DataInputStream(new BufferedInputStream(new GZIPInputStream(☃)));
    try
    {
      return a(☃, dw.a);
    }
    finally
    {
      ☃.close();
    }
  }
  
  public static void a(dn ☃, OutputStream ☃)
    throws IOException
  {
    DataOutputStream ☃ = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(☃)));
    try
    {
      a(☃, ☃);
    }
    finally
    {
      ☃.close();
    }
  }
  
  public static void a(dn ☃, File ☃)
    throws IOException
  {
    File ☃ = new File(☃.getAbsolutePath() + "_tmp");
    if (☃.exists()) {
      ☃.delete();
    }
    b(☃, ☃);
    if (☃.exists()) {
      ☃.delete();
    }
    if (☃.exists()) {
      throw new IOException("Failed to delete " + ☃);
    }
    ☃.renameTo(☃);
  }
  
  public static void b(dn ☃, File ☃)
    throws IOException
  {
    DataOutputStream ☃ = new DataOutputStream(new FileOutputStream(☃));
    try
    {
      a(☃, ☃);
    }
    finally
    {
      ☃.close();
    }
  }
  
  public static dn a(File ☃)
    throws IOException
  {
    if (!☃.exists()) {
      return null;
    }
    DataInputStream ☃ = new DataInputStream(new FileInputStream(☃));
    try
    {
      return a(☃, dw.a);
    }
    finally
    {
      ☃.close();
    }
  }
  
  public static dn a(DataInputStream ☃)
    throws IOException
  {
    return a(☃, dw.a);
  }
  
  public static dn a(DataInput ☃, dw ☃)
    throws IOException
  {
    eb ☃ = a(☃, 0, ☃);
    if ((☃ instanceof dn)) {
      return (dn)☃;
    }
    throw new IOException("Root tag must be a named compound tag");
  }
  
  public static void a(dn ☃, DataOutput ☃)
    throws IOException
  {
    a(☃, ☃);
  }
  
  private static void a(eb ☃, DataOutput ☃)
    throws IOException
  {
    ☃.writeByte(☃.a());
    if (☃.a() == 0) {
      return;
    }
    ☃.writeUTF("");
    
    ☃.a(☃);
  }
  
  private static eb a(DataInput ☃, int ☃, dw ☃)
    throws IOException
  {
    byte ☃ = ☃.readByte();
    if (☃ == 0) {
      return new dq();
    }
    ☃.readUTF();
    
    eb ☃ = eb.a(☃);
    try
    {
      ☃.a(☃, ☃, ☃);
    }
    catch (IOException ☃)
    {
      b ☃ = b.a(☃, "Loading NBT data");
      c ☃ = ☃.a("NBT Tag");
      ☃.a("Tag name", "[UNNAMED TAG]");
      ☃.a("Tag type", Byte.valueOf(☃));
      throw new e(☃);
    }
    return ☃;
  }
}
