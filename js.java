import com.mojang.authlib.GameProfile;
import java.io.IOException;

public class js
  implements ff<jr>
{
  private GameProfile a;
  
  public js() {}
  
  public js(GameProfile ☃)
  {
    this.a = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = new GameProfile(null, ☃.c(16));
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a.getName());
  }
  
  public void a(jr ☃)
  {
    ☃.a(this);
  }
  
  public GameProfile a()
  {
    return this.a;
  }
}
