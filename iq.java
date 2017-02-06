import java.io.IOException;

public class iq
  implements ff<ig>
{
  private String a;
  private em b;
  
  public iq() {}
  
  public iq(String ☃, em ☃)
  {
    this.a = ☃;
    this.b = ☃;
    if (☃.writerIndex() > 32767) {
      throw new IllegalArgumentException("Payload may not be larger than 32767 bytes");
    }
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.c(20);
    int ☃ = ☃.readableBytes();
    if ((☃ < 0) || (☃ > 32767)) {
      throw new IOException("Payload may not be larger than 32767 bytes");
    }
    this.b = new em(☃.readBytes(☃));
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a);
    ☃.writeBytes(this.b);
  }
  
  public void a(ig ☃)
  {
    ☃.a(this);
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
