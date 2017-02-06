public class arx
  implements asj
{
  private static final asi d = new asc();
  protected static final arc a = aju.a.u();
  protected nx b;
  protected asi c;
  private int e = 0;
  
  public arx()
  {
    b(4);
  }
  
  private static int b(int ☃, int ☃, int ☃)
  {
    return ☃ << 8 | ☃ << 4 | ☃;
  }
  
  private void b(int ☃)
  {
    if (☃ == this.e) {
      return;
    }
    this.e = ☃;
    if (this.e <= 4)
    {
      this.e = 4;
      this.c = new asg(this.e, this);
    }
    else if (this.e <= 8)
    {
      this.c = new asd(this.e, this);
    }
    else
    {
      this.c = d;
      this.e = on.d(ajt.i.a());
    }
    this.c.a(a);
    
    this.b = new nx(this.e, 4096);
  }
  
  public int a(int ☃, arc ☃)
  {
    nx ☃ = this.b;
    asi ☃ = this.c;
    
    b(☃);
    for (int ☃ = 0; ☃ < ☃.b(); ☃++)
    {
      arc ☃ = ☃.a(☃.a(☃));
      if (☃ != null) {
        b(☃, ☃);
      }
    }
    return this.c.a(☃);
  }
  
  public void a(int ☃, int ☃, int ☃, arc ☃)
  {
    b(b(☃, ☃, ☃), ☃);
  }
  
  protected void b(int ☃, arc ☃)
  {
    int ☃ = this.c.a(☃);
    this.b.a(☃, ☃);
  }
  
  public arc a(int ☃, int ☃, int ☃)
  {
    return a(b(☃, ☃, ☃));
  }
  
  protected arc a(int ☃)
  {
    arc ☃ = this.c.a(this.b.a(☃));
    return ☃ == null ? a : ☃;
  }
  
  public void a(em ☃)
  {
    int ☃ = ☃.readByte();
    if (this.e != ☃) {
      b(☃);
    }
    this.c.a(☃);
    ☃.b(this.b.a());
  }
  
  public void b(em ☃)
  {
    ☃.writeByte(this.e);
    this.c.b(☃);
    ☃.a(this.b.a());
  }
  
  public asa a(byte[] ☃, asa ☃)
  {
    asa ☃ = null;
    for (int ☃ = 0; ☃ < 4096; ☃++)
    {
      int ☃ = ajt.i.a(a(☃));
      int ☃ = ☃ & 0xF;
      int ☃ = ☃ >> 8 & 0xF;
      int ☃ = ☃ >> 4 & 0xF;
      if ((☃ >> 12 & 0xF) != 0)
      {
        if (☃ == null) {
          ☃ = new asa();
        }
        ☃.a(☃, ☃, ☃, ☃ >> 12 & 0xF);
      }
      ☃[☃] = ((byte)(☃ >> 4 & 0xFF));
      ☃.a(☃, ☃, ☃, ☃ & 0xF);
    }
    return ☃;
  }
  
  public void a(byte[] ☃, asa ☃, asa ☃)
  {
    for (int ☃ = 0; ☃ < 4096; ☃++)
    {
      int ☃ = ☃ & 0xF;
      int ☃ = ☃ >> 8 & 0xF;
      int ☃ = ☃ >> 4 & 0xF;
      int ☃ = ☃ == null ? 0 : ☃.a(☃, ☃, ☃);
      int ☃ = ☃ << 12 | (☃[☃] & 0xFF) << 4 | ☃.a(☃, ☃, ☃);
      b(☃, (arc)ajt.i.a(☃));
    }
  }
  
  public int a()
  {
    int ☃ = this.b.b();
    return 1 + this.c.a() + em.a(☃) + ☃ * 8;
  }
}
