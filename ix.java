import java.io.IOException;

public class ix
  implements ff<ig>
{
  private cj a;
  private cq b;
  private ix.a c;
  
  public ix() {}
  
  public ix(ix.a ☃, cj ☃, cq ☃)
  {
    this.c = ☃;
    this.a = ☃;
    this.b = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.c = ((ix.a)☃.a(ix.a.class));
    this.a = ☃.e();
    this.b = cq.a(☃.readUnsignedByte());
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.c);
    ☃.a(this.a);
    ☃.writeByte(this.b.a());
  }
  
  public void a(ig ☃)
  {
    ☃.a(this);
  }
  
  public cj a()
  {
    return this.a;
  }
  
  public cq b()
  {
    return this.b;
  }
  
  public ix.a c()
  {
    return this.c;
  }
  
  public static enum a
  {
    private a() {}
  }
}
