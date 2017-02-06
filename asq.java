import com.google.common.collect.Lists;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import net.minecraft.server.MinecraftServer;

public class asq
{
  private static final byte[] a = new byte['က'];
  private final File b;
  private RandomAccessFile c;
  private final int[] d;
  private final int[] e;
  private List<Boolean> f;
  private int g;
  private long h;
  
  public asq(File ☃)
  {
    this.d = new int['Ѐ'];
    this.e = new int['Ѐ'];
    
    this.b = ☃;
    
    this.g = 0;
    try
    {
      if (☃.exists()) {
        this.h = ☃.lastModified();
      }
      this.c = new RandomAccessFile(☃, "rw");
      if (this.c.length() < 4096L)
      {
        this.c.write(a);
        
        this.c.write(a);
        
        this.g += 8192;
      }
      if ((this.c.length() & 0xFFF) != 0L) {
        for (int ☃ = 0; ☃ < (this.c.length() & 0xFFF); ☃++) {
          this.c.write(0);
        }
      }
      int ☃ = (int)this.c.length() / 4096;
      this.f = Lists.newArrayListWithCapacity(☃);
      for (int ☃ = 0; ☃ < ☃; ☃++) {
        this.f.add(Boolean.valueOf(true));
      }
      this.f.set(0, Boolean.valueOf(false));
      this.f.set(1, Boolean.valueOf(false));
      
      this.c.seek(0L);
      for (int ☃ = 0; ☃ < 1024; ☃++)
      {
        int ☃ = this.c.readInt();
        this.d[☃] = ☃;
        if ((☃ != 0) && ((☃ >> 8) + (☃ & 0xFF) <= this.f.size())) {
          for (int ☃ = 0; ☃ < (☃ & 0xFF); ☃++) {
            this.f.set((☃ >> 8) + ☃, Boolean.valueOf(false));
          }
        }
      }
      for (int ☃ = 0; ☃ < 1024; ☃++)
      {
        int ☃ = this.c.readInt();
        this.e[☃] = ☃;
      }
    }
    catch (IOException ☃)
    {
      ☃.printStackTrace();
    }
  }
  
  public synchronized DataInputStream a(int ☃, int ☃)
  {
    if (d(☃, ☃)) {
      return null;
    }
    try
    {
      int ☃ = e(☃, ☃);
      if (☃ == 0) {
        return null;
      }
      int ☃ = ☃ >> 8;
      int ☃ = ☃ & 0xFF;
      if (☃ + ☃ > this.f.size()) {
        return null;
      }
      this.c.seek(☃ * 4096);
      int ☃ = this.c.readInt();
      if (☃ > 4096 * ☃) {
        return null;
      }
      if (☃ <= 0) {
        return null;
      }
      byte ☃ = this.c.readByte();
      if (☃ == 1)
      {
        byte[] ☃ = new byte[☃ - 1];
        this.c.read(☃);
        return new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(☃))));
      }
      if (☃ == 2)
      {
        byte[] ☃ = new byte[☃ - 1];
        this.c.read(☃);
        return new DataInputStream(new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(☃))));
      }
      return null;
    }
    catch (IOException ☃) {}
    return null;
  }
  
  public DataOutputStream b(int ☃, int ☃)
  {
    if (d(☃, ☃)) {
      return null;
    }
    return new DataOutputStream(new BufferedOutputStream(new DeflaterOutputStream(new asq.a(☃, ☃))));
  }
  
  class a
    extends ByteArrayOutputStream
  {
    private int b;
    private int c;
    
    public a(int ☃, int ☃)
    {
      super();
      this.b = ☃;
      this.c = ☃;
    }
    
    public void close()
    {
      asq.this.a(this.b, this.c, this.buf, this.count);
    }
  }
  
  protected synchronized void a(int ☃, int ☃, byte[] ☃, int ☃)
  {
    try
    {
      int ☃ = e(☃, ☃);
      int ☃ = ☃ >> 8;
      int ☃ = ☃ & 0xFF;
      int ☃ = (☃ + 5) / 4096 + 1;
      if (☃ >= 256) {
        return;
      }
      if ((☃ != 0) && (☃ == ☃))
      {
        a(☃, ☃, ☃);
      }
      else
      {
        for (int ☃ = 0; ☃ < ☃; ☃++) {
          this.f.set(☃ + ☃, Boolean.valueOf(true));
        }
        int ☃ = this.f.indexOf(Boolean.valueOf(true));
        int ☃ = 0;
        if (☃ != -1) {
          for (int ☃ = ☃; ☃ < this.f.size(); ☃++)
          {
            if (☃ != 0)
            {
              if (((Boolean)this.f.get(☃)).booleanValue()) {
                ☃++;
              } else {
                ☃ = 0;
              }
            }
            else if (((Boolean)this.f.get(☃)).booleanValue())
            {
              ☃ = ☃;
              ☃ = 1;
            }
            if (☃ >= ☃) {
              break;
            }
          }
        }
        if (☃ >= ☃)
        {
          ☃ = ☃;
          a(☃, ☃, ☃ << 8 | ☃);
          for (int ☃ = 0; ☃ < ☃; ☃++) {
            this.f.set(☃ + ☃, Boolean.valueOf(false));
          }
          a(☃, ☃, ☃);
        }
        else
        {
          this.c.seek(this.c.length());
          ☃ = this.f.size();
          for (int ☃ = 0; ☃ < ☃; ☃++)
          {
            this.c.write(a);
            this.f.add(Boolean.valueOf(false));
          }
          this.g += 4096 * ☃;
          
          a(☃, ☃, ☃);
          a(☃, ☃, ☃ << 8 | ☃);
        }
      }
      b(☃, ☃, (int)(MinecraftServer.av() / 1000L));
    }
    catch (IOException ☃)
    {
      ☃.printStackTrace();
    }
  }
  
  private void a(int ☃, byte[] ☃, int ☃)
    throws IOException
  {
    this.c.seek(☃ * 4096);
    this.c.writeInt(☃ + 1);
    this.c.writeByte(2);
    this.c.write(☃, 0, ☃);
  }
  
  private boolean d(int ☃, int ☃)
  {
    return (☃ < 0) || (☃ >= 32) || (☃ < 0) || (☃ >= 32);
  }
  
  private int e(int ☃, int ☃)
  {
    return this.d[(☃ + ☃ * 32)];
  }
  
  public boolean c(int ☃, int ☃)
  {
    return e(☃, ☃) != 0;
  }
  
  private void a(int ☃, int ☃, int ☃)
    throws IOException
  {
    this.d[(☃ + ☃ * 32)] = ☃;
    this.c.seek((☃ + ☃ * 32) * 4);
    this.c.writeInt(☃);
  }
  
  private void b(int ☃, int ☃, int ☃)
    throws IOException
  {
    this.e[(☃ + ☃ * 32)] = ☃;
    this.c.seek(4096 + (☃ + ☃ * 32) * 4);
    this.c.writeInt(☃);
  }
  
  public void c()
    throws IOException
  {
    if (this.c != null) {
      this.c.close();
    }
  }
}
