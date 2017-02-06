import java.io.IOException;

public class fw
  implements ff<fi>
{
  private qk a;
  private boolean b;
  
  public fw() {}
  
  public fw(qk ☃, boolean ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = qk.a(☃.readUnsignedByte());
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeByte(this.a.a());
  }
  
  public boolean a()
  {
    return this.b;
  }
  
  public qk b()
  {
    return this.a;
  }
}
