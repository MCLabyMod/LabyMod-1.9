import com.mojang.authlib.GameProfile;

public class bhq
  implements bhu
{
  private final GameProfile a;
  private final kk b;
  
  public bhq(GameProfile ☃)
  {
    this.a = ☃;
    this.b = bmq.e(☃.getName());
    
    bmq.a(this.b, ☃.getName());
  }
  
  public void a(bhs ☃)
  {
    bcf.z().v().a(new jf(this.a.getId()));
  }
  
  public eu F_()
  {
    return new fa(this.a.getName());
  }
  
  public void a(float ☃, int ☃)
  {
    bcf.z().N().a(this.b);
    bni.c(1.0F, 1.0F, 1.0F, ☃ / 255.0F);
    bcv.a(2, 2, 8.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
    bcv.a(2, 2, 40.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
  }
  
  public boolean G_()
  {
    return true;
  }
}
