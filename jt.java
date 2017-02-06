import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.SecretKey;

public class jt
  implements ff<jr>
{
  private byte[] a = new byte[0];
  private byte[] b = new byte[0];
  
  public jt() {}
  
  public jt(SecretKey ☃, PublicKey ☃, byte[] ☃)
  {
    this.a = ob.a(☃, ☃.getEncoded());
    this.b = ob.a(☃, ☃);
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.a();
    this.b = ☃.a();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a);
    ☃.a(this.b);
  }
  
  public void a(jr ☃)
  {
    ☃.a(this);
  }
  
  public SecretKey a(PrivateKey ☃)
  {
    return ob.a(☃, this.a);
  }
  
  public byte[] b(PrivateKey ☃)
  {
    if (☃ == null) {
      return this.b;
    }
    return ob.b(☃, this.b);
  }
}
