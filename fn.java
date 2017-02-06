import java.io.IOException;
import java.util.UUID;

public class fn
  implements ff<fi>
{
  private int a;
  private UUID b;
  private cj c;
  private cq d;
  private String e;
  
  public fn() {}
  
  public fn(xu ☃)
  {
    this.a = ☃.O();
    this.b = ☃.bc();
    this.c = ☃.q();
    this.d = ☃.b;
    this.e = ☃.c.B;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.b = ☃.i();
    this.e = ☃.c(xu.a.A);
    this.c = ☃.e();
    this.d = cq.b(☃.readUnsignedByte());
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
    ☃.a(this.b);
    ☃.a(this.e);
    ☃.a(this.c);
    ☃.writeByte(this.d.b());
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public UUID b()
  {
    return this.b;
  }
  
  public cj c()
  {
    return this.c;
  }
  
  public cq d()
  {
    return this.d;
  }
  
  public String e()
  {
    return this.e;
  }
}
