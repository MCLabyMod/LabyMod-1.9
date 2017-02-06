import java.io.IOException;
import java.security.PublicKey;

public class jo
  implements ff<jm>
{
  private String a;
  private PublicKey b;
  private byte[] c;
  
  public jo() {}
  
  public jo(String ☃, PublicKey ☃, byte[] ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.c(20);
    this.b = ob.a(☃.a());
    this.c = ☃.a();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a);
    ☃.a(this.b.getEncoded());
    ☃.a(this.c);
  }
  
  public void a(jm ☃)
  {
    ☃.a(this);
  }
  
  public String a()
  {
    return this.a;
  }
  
  public PublicKey b()
  {
    return this.b;
  }
  
  public byte[] c()
  {
    return this.c;
  }
}
