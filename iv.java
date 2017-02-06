import java.io.IOException;

public class iv
  implements ff<ig>
{
  private boolean a;
  private boolean b;
  
  public iv() {}
  
  public iv(boolean ☃, boolean ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readBoolean();
    this.b = ☃.readBoolean();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeBoolean(this.a);
    ☃.writeBoolean(this.b);
  }
  
  public void a(ig ☃)
  {
    ☃.a(this);
  }
  
  public boolean a()
  {
    return this.a;
  }
  
  public boolean b()
  {
    return this.b;
  }
}
