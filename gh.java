import de.labystudio.labymod.LabyMod;
import java.io.IOException;

public class gh
  implements ff<fi>
{
  private String a;
  private em b;
  
  public gh() {}
  
  public gh(String channelIn, em bufIn)
  {
    this.a = channelIn;
    this.b = bufIn;
    if (bufIn.writerIndex() > 1048576) {
      throw new IllegalArgumentException("Payload may not be larger than 1048576 bytes");
    }
  }
  
  public void a(em buf)
    throws IOException
  {
    this.a = buf.c(20);
    int i = buf.readableBytes();
    if ((i >= 0) && (i <= 1048576)) {
      this.b = new em(buf.readBytes(i));
    } else {
      throw new IOException("Payload may not be larger than 1048576 bytes");
    }
    LabyMod.getInstance().pluginMessage(this.a, this.b);
  }
  
  public void b(em buf)
    throws IOException
  {
    buf.a(this.a);
    buf.writeBytes(this.b);
  }
  
  public void a(fi handler)
  {
    handler.a(this);
  }
  
  public String a()
  {
    return this.a;
  }
  
  public em b()
  {
    return this.b;
  }
}
