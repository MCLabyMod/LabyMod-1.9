import java.io.IOException;

public class kb
  implements ff<ka>
{
  private long a;
  
  public kb() {}
  
  public kb(long ☃)
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
  
  public void a(ka ☃)
  {
    ☃.a(this);
  }
  
  public long a()
  {
    return this.a;
  }
}
