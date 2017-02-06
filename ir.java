import java.io.IOException;

public class ir
  implements ff<ig>
{
  private int a;
  private ir.a b;
  private bbj c;
  private qm d;
  
  public ir() {}
  
  public ir(rr ☃)
  {
    this.a = ☃.O();
    this.b = ir.a.b;
  }
  
  public ir(rr ☃, qm ☃)
  {
    this.a = ☃.O();
    this.b = ir.a.a;
    this.d = ☃;
  }
  
  public ir(rr ☃, qm ☃, bbj ☃)
  {
    this.a = ☃.O();
    this.b = ir.a.c;
    this.d = ☃;
    this.c = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.b = ((ir.a)☃.a(ir.a.class));
    if (this.b == ir.a.c) {
      this.c = new bbj(☃.readFloat(), ☃.readFloat(), ☃.readFloat());
    }
    if ((this.b == ir.a.a) || (this.b == ir.a.c)) {
      this.d = ((qm)☃.a(qm.class));
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
    ☃.a(this.b);
    if (this.b == ir.a.c)
    {
      ☃.writeFloat((float)this.c.b);
      ☃.writeFloat((float)this.c.c);
      ☃.writeFloat((float)this.c.d);
    }
    if ((this.b == ir.a.a) || (this.b == ir.a.c)) {
      ☃.a(this.d);
    }
  }
  
  public void a(ig ☃)
  {
    ☃.a(this);
  }
  
  public rr a(aht ☃)
  {
    return ☃.a(this.a);
  }
  
  public ir.a a()
  {
    return this.b;
  }
  
  public qm b()
  {
    return this.d;
  }
  
  public bbj c()
  {
    return this.c;
  }
  
  public static enum a
  {
    private a() {}
  }
}
