import java.io.IOException;

public class jx
  implements ff<jw>
{
  private long a;
  
  public jx() {}
  
  public jx(long ☃)
  {
    this.a = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readLong();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeLong(this.a);
  }
  
  public void a(jw ☃)
  {
    ☃.a(this);
  }
}
