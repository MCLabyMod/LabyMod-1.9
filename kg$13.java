final class kg$13
  implements kf<dc>
{
  public void a(em ☃, dc ☃)
  {
    ☃.writeFloat(☃.b());
    ☃.writeFloat(☃.c());
    ☃.writeFloat(☃.d());
  }
  
  public dc b(em ☃)
  {
    return new dc(☃.readFloat(), ☃.readFloat(), ☃.readFloat());
  }
  
  public ke<dc> a(int ☃)
  {
    return new ke(☃, this);
  }
}
