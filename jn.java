import com.mojang.authlib.GameProfile;
import java.io.IOException;
import java.util.UUID;

public class jn
  implements ff<jm>
{
  private GameProfile a;
  
  public jn() {}
  
  public jn(GameProfile ☃)
  {
    this.a = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    String ☃ = ☃.c(36);
    String ☃ = ☃.c(16);
    UUID ☃ = UUID.fromString(☃);
    this.a = new GameProfile(☃, ☃);
  }
  
  public void b(em ☃)
    throws IOException
  {
    UUID ☃ = this.a.getId();
    ☃.a(☃ == null ? "" : ☃.toString());
    ☃.a(this.a.getName());
  }
  
  public void a(jm ☃)
  {
    ☃.a(this);
  }
  
  public GameProfile a()
  {
    return this.a;
  }
}
