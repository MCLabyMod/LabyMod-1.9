import com.mojang.authlib.GameProfile;
import java.net.SocketAddress;

public class bym
  extends mm
{
  private dn f;
  
  public bym(byn ☃)
  {
    super(☃);
    
    a(10);
  }
  
  protected void b(lr ☃)
  {
    if (☃.h_().equals(b().Q()))
    {
      this.f = new dn();
      ☃.e(this.f);
    }
    super.b(☃);
  }
  
  public String a(SocketAddress ☃, GameProfile ☃)
  {
    if ((☃.getName().equalsIgnoreCase(b().Q())) && (a(☃.getName()) != null)) {
      return "That name is already taken.";
    }
    return super.a(☃, ☃);
  }
  
  public byn b()
  {
    return (byn)super.c();
  }
  
  public dn t()
  {
    return this.f;
  }
}
