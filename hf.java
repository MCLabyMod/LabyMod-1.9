import java.io.IOException;

public class hf
  implements ff<fi>
{
  private int a;
  private qk b;
  private ahw.a c;
  private ahy d;
  
  public hf() {}
  
  public hf(int ☃, qk ☃, ahy ☃, ahw.a ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readInt();
    this.b = qk.a(☃.readUnsignedByte());
    this.c = ahw.a.a(☃.readUnsignedByte());
    this.d = ahy.a(☃.c(16));
    if (this.d == null) {
      this.d = ahy.b;
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeInt(this.a);
    ☃.writeByte(this.b.a());
    ☃.writeByte(this.c.a());
    ☃.a(this.d.a());
  }
  
  public int a()
  {
    return this.a;
  }
  
  public qk b()
  {
    return this.b;
  }
  
  public ahw.a c()
  {
    return this.c;
  }
  
  public ahy d()
  {
    return this.d;
  }
}
