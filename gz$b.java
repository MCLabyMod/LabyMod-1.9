import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.mojang.authlib.GameProfile;

public class gz$b
{
  private final int b;
  private final ahw.a c;
  private final GameProfile d;
  private final eu e;
  
  public gz$b(gz paramgz, GameProfile ☃, int ☃, ahw.a ☃, eu ☃)
  {
    this.d = ☃;
    this.b = ☃;
    this.c = ☃;
    this.e = ☃;
  }
  
  public GameProfile a()
  {
    return this.d;
  }
  
  public int b()
  {
    return this.b;
  }
  
  public ahw.a c()
  {
    return this.c;
  }
  
  public eu d()
  {
    return this.e;
  }
  
  public String toString()
  {
    return Objects.toStringHelper(this).add("latency", this.b).add("gameMode", this.c).add("profile", this.d).add("displayName", this.e == null ? null : eu.a.a(this.e)).toString();
  }
}
