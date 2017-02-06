import java.nio.ByteBuffer;

public class bvq
{
  private int a;
  private final bvr b;
  private int c;
  
  public bvq(bvr vertexFormatIn)
  {
    this.b = vertexFormatIn;
    this.a = bzg.e();
  }
  
  public void a()
  {
    bzg.g(bzg.R, this.a);
  }
  
  public void a(ByteBuffer data)
  {
    a();
    bzg.a(bzg.R, data, 35044);
    b();
    this.c = (data.limit() / this.b.g());
  }
  
  public void a(int mode)
  {
    bni.f(mode, 0, this.c);
  }
  
  public void b()
  {
    bzg.g(bzg.R, 0);
  }
  
  public void c()
  {
    if (this.a >= 0)
    {
      bzg.g(this.a);
      this.a = -1;
    }
  }
}
