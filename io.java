import java.io.IOException;

public class io
  implements ff<ig>
{
  private int a;
  private int b;
  private int c;
  private short d;
  private adq e;
  private aaz f;
  
  public io() {}
  
  public io(int ☃, int ☃, int ☃, aaz ☃, adq ☃, short ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.e = (☃ != null ? ☃.k() : null);
    this.d = ☃;
    this.f = ☃;
  }
  
  public void a(ig ☃)
  {
    ☃.a(this);
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readByte();
    this.b = ☃.readShort();
    this.c = ☃.readByte();
    this.d = ☃.readShort();
    this.f = ((aaz)☃.a(aaz.class));
    
    this.e = ☃.k();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeByte(this.a);
    ☃.writeShort(this.b);
    ☃.writeByte(this.c);
    ☃.writeShort(this.d);
    ☃.a(this.f);
    
    ☃.a(this.e);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public int b()
  {
    return this.b;
  }
  
  public int c()
  {
    return this.c;
  }
  
  public short d()
  {
    return this.d;
  }
  
  public adq e()
  {
    return this.e;
  }
  
  public aaz f()
  {
    return this.f;
  }
}
