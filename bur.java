import com.google.common.collect.Maps;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.BufferUtils;

public class bur
{
  private final bur.a a;
  private final String b;
  private int c;
  private int d = 0;
  
  private bur(bur.a ☃, int ☃, String ☃)
  {
    this.a = ☃;
    this.c = ☃;
    this.b = ☃;
  }
  
  public void a(buo ☃)
  {
    this.d += 1;
    bzg.b(☃.h(), this.c);
  }
  
  public void b(buo ☃)
  {
    this.d -= 1;
    if (this.d <= 0)
    {
      bzg.a(this.c);
      this.a.d().remove(this.b);
    }
  }
  
  public String a()
  {
    return this.b;
  }
  
  public static bur a(bwg ☃, bur.a ☃, String ☃)
    throws IOException
  {
    bur ☃ = (bur)☃.d().get(☃);
    if (☃ == null)
    {
      kk ☃ = new kk("shaders/program/" + ☃ + ☃.b());
      bwf ☃ = ☃.a(☃);
      try
      {
        byte[] ☃ = IOUtils.toByteArray(new BufferedInputStream(☃.b()));
        ByteBuffer ☃ = BufferUtils.createByteBuffer(☃.length);
        ☃.put(☃);
        ☃.position(0);
        
        int ☃ = bzg.b(☃.c());
        bzg.a(☃, ☃);
        bzg.c(☃);
        if (bzg.c(☃, bzg.n) == 0)
        {
          String ☃ = StringUtils.trim(bzg.d(☃, 32768));
          ko ☃ = new ko("Couldn't compile " + ☃.a() + " program: " + ☃);
          ☃.b(☃.a());
          throw ☃;
        }
        ☃ = new bur(☃, ☃, ☃);
        ☃.d().put(☃, ☃);
      }
      finally
      {
        IOUtils.closeQuietly(☃);
      }
    }
    return ☃;
  }
  
  public static enum a
  {
    private final String c;
    private final String d;
    private final int e;
    private final Map<String, bur> f = Maps.newHashMap();
    
    private a(String ☃, String ☃, int ☃)
    {
      this.c = ☃;
      this.d = ☃;
      this.e = ☃;
    }
    
    public String a()
    {
      return this.c;
    }
    
    protected String b()
    {
      return this.d;
    }
    
    protected int c()
    {
      return this.e;
    }
    
    protected Map<String, bur> d()
    {
      return this.f;
    }
  }
}
