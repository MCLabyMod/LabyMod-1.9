import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public class bvc
{
  public final int a;
  public final int b;
  
  public bvc(InputStream ☃)
    throws IOException
  {
    DataInputStream ☃ = new DataInputStream(☃);
    if (☃.readLong() != -8552249625308161526L) {
      throw new IOException("Bad PNG Signature");
    }
    if (☃.readInt() != 13) {
      throw new IOException("Bad length for IHDR chunk!");
    }
    if (☃.readInt() != 1229472850) {
      throw new IOException("Bad type for IHDR chunk!");
    }
    this.a = ☃.readInt();
    this.b = ☃.readInt();
    
    IOUtils.closeQuietly(☃);
  }
  
  public static bvc a(bwf ☃)
    throws IOException
  {
    try
    {
      return new bvc(☃.b());
    }
    finally
    {
      IOUtils.closeQuietly(☃);
    }
  }
}
