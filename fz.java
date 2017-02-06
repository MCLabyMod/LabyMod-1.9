import java.io.IOException;

public class fz
  implements ff<fi>
{
  private ahn a;
  private fz.a[] b;
  
  public fz() {}
  
  public fz(int ☃, short[] ☃, ase ☃)
  {
    this.a = new ahn(☃.b, ☃.c);
    
    this.b = new fz.a[☃];
    for (int ☃ = 0; ☃ < this.b.length; ☃++) {
      this.b[☃] = new fz.a(☃[☃], ☃);
    }
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = new ahn(☃.readInt(), ☃.readInt());
    this.b = new fz.a[☃.g()];
    for (int ☃ = 0; ☃ < this.b.length; ☃++) {
      this.b[☃] = new fz.a(☃.readShort(), (arc)ajt.i.a(☃.g()));
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeInt(this.a.a);
    ☃.writeInt(this.a.b);
    ☃.b(this.b.length);
    for (fz.a ☃ : this.b)
    {
      ☃.writeShort(☃.b());
      ☃.b(ajt.i.a(☃.c()));
    }
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public fz.a[] a()
  {
    return this.b;
  }
  
  public class a
  {
    private final short b;
    private final arc c;
    
    public a(short ☃, arc ☃)
    {
      this.b = ☃;
      this.c = ☃;
    }
    
    public a(short ☃, ase ☃)
    {
      this.b = ☃;
      this.c = ☃.a(a());
    }
    
    public cj a()
    {
      return new cj(fz.a(fz.this).a(this.b >> 12 & 0xF, this.b & 0xFF, this.b >> 8 & 0xF));
    }
    
    public short b()
    {
      return this.b;
    }
    
    public arc c()
    {
      return this.c;
    }
  }
}
