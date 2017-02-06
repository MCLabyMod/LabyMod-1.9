import java.io.IOException;

public class il
  implements ff<ig>
{
  private String a;
  private int b;
  private zj.b c;
  private boolean d;
  private int e;
  private rz f;
  
  public il() {}
  
  public il(String ☃, int ☃, zj.b ☃, boolean ☃, int ☃, rz ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
    this.f = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.c(7);
    this.b = ☃.readByte();
    
    this.c = ((zj.b)☃.a(zj.b.class));
    this.d = ☃.readBoolean();
    
    this.e = ☃.readUnsignedByte();
    this.f = ((rz)☃.a(rz.class));
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a);
    ☃.writeByte(this.b);
    ☃.a(this.c);
    ☃.writeBoolean(this.d);
    ☃.writeByte(this.e);
    ☃.a(this.f);
  }
  
  public void a(ig ☃)
  {
    ☃.a(this);
  }
  
  public String a()
  {
    return this.a;
  }
  
  public zj.b c()
  {
    return this.c;
  }
  
  public boolean d()
  {
    return this.d;
  }
  
  public int e()
  {
    return this.e;
  }
  
  public rz f()
  {
    return this.f;
  }
}
