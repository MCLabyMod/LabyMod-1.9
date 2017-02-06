import java.io.IOException;

public class gu
  implements ff<fi>
{
  protected int a;
  protected int b;
  protected int c;
  protected int d;
  protected byte e;
  protected byte f;
  protected boolean g;
  protected boolean h;
  public gu() {}
  
  public static class b
    extends gu
  {
    public b()
    {
      this.h = true;
    }
    
    public b(int ☃, long ☃, long ☃, long ☃, byte ☃, byte ☃, boolean ☃)
    {
      super();
      
      this.b = ((int)☃);
      this.c = ((int)☃);
      this.d = ((int)☃);
      this.e = ☃;
      this.f = ☃;
      this.g = ☃;
      this.h = true;
    }
    
    public void a(em ☃)
      throws IOException
    {
      super.a(☃);
      this.b = ☃.readShort();
      this.c = ☃.readShort();
      this.d = ☃.readShort();
      this.e = ☃.readByte();
      this.f = ☃.readByte();
      this.g = ☃.readBoolean();
    }
    
    public void b(em ☃)
      throws IOException
    {
      super.b(☃);
      ☃.writeShort(this.b);
      ☃.writeShort(this.c);
      ☃.writeShort(this.d);
      ☃.writeByte(this.e);
      ☃.writeByte(this.f);
      ☃.writeBoolean(this.g);
    }
  }
  
  public static class a
    extends gu
  {
    public a() {}
    
    public a(int ☃, long ☃, long ☃, long ☃, boolean ☃)
    {
      super();
      
      this.b = ((int)☃);
      this.c = ((int)☃);
      this.d = ((int)☃);
      this.g = ☃;
    }
    
    public void a(em ☃)
      throws IOException
    {
      super.a(☃);
      this.b = ☃.readShort();
      this.c = ☃.readShort();
      this.d = ☃.readShort();
      this.g = ☃.readBoolean();
    }
    
    public void b(em ☃)
      throws IOException
    {
      super.b(☃);
      ☃.writeShort(this.b);
      ☃.writeShort(this.c);
      ☃.writeShort(this.d);
      ☃.writeBoolean(this.g);
    }
  }
  
  public static class c
    extends gu
  {
    public c()
    {
      this.h = true;
    }
    
    public c(int ☃, byte ☃, byte ☃, boolean ☃)
    {
      super();
      this.e = ☃;
      this.f = ☃;
      this.h = true;
      this.g = ☃;
    }
    
    public void a(em ☃)
      throws IOException
    {
      super.a(☃);
      this.e = ☃.readByte();
      this.f = ☃.readByte();
      this.g = ☃.readBoolean();
    }
    
    public void b(em ☃)
      throws IOException
    {
      super.b(☃);
      ☃.writeByte(this.e);
      ☃.writeByte(this.f);
      ☃.writeBoolean(this.g);
    }
  }
  
  public gu(int ☃)
  {
    this.a = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public String toString()
  {
    return "Entity_" + super.toString();
  }
  
  public rr a(aht ☃)
  {
    return ☃.a(this.a);
  }
  
  public int a()
  {
    return this.b;
  }
  
  public int b()
  {
    return this.c;
  }
  
  public int c()
  {
    return this.d;
  }
  
  public byte d()
  {
    return this.e;
  }
  
  public byte e()
  {
    return this.f;
  }
  
  public boolean f()
  {
    return this.h;
  }
  
  public boolean g()
  {
    return this.g;
  }
}
