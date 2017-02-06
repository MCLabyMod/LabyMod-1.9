import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;

final class mi$1
  implements ProfileLookupCallback
{
  mi$1(GameProfile[] paramArrayOfGameProfile) {}
  
  public void onProfileLookupSucceeded(GameProfile ☃)
  {
    this.a[0] = ☃;
  }
  
  public void onProfileLookupFailed(GameProfile ☃, Exception ☃)
  {
    this.a[0] = null;
  }
}
