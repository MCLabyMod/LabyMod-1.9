import de.labystudio.labymod.LabyMod;
import java.io.IOException;

public class jj
  implements ff<jk>
{
  private int a;
  private String b;
  private int c;
  private el d;
  
  public jj() {}
  
  public jj(int version, String ip, int port, el requestedState)
  {
    version = LabyMod.getClientVersion();
    this.a = version;
    this.b = ip;
    this.c = port;
    this.d = requestedState;
  }
  
  public void a(em buf)
    throws IOException
  {
    this.a = buf.g();
    this.b = buf.c(255);
    this.c = buf.readUnsignedShort();
    this.d = el.a(buf.g());
  }
  
  public void b(em buf)
    throws IOException
  {
    buf.b(this.a);
    buf.a(this.b);
    buf.writeShort(this.c);
    buf.b(this.d.a());
  }
  
  public void a(jk handler)
  {
    handler.a(this);
  }
  
  public el a()
  {
    return this.d;
  }
  
  public int b()
  {
    return this.a;
  }
}
