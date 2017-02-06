import java.io.IOException;

public class it
  implements ff<ig>
{
  protected double a;
  protected double b;
  protected double c;
  protected float d;
  protected float e;
  protected boolean f;
  protected boolean g;
  protected boolean h;
  public it() {}
  
  public static class b
    extends it
  {
    public b()
    {
      this.g = true;
      this.h = true;
    }
    
    public b(double ☃, double ☃, double ☃, float ☃, float ☃, boolean ☃)
    {
      this.a = ☃;
      this.b = ☃;
      this.c = ☃;
      this.d = ☃;
      this.e = ☃;
      this.f = ☃;
      this.h = true;
      this.g = true;
    }
    
    public void a(em ☃)
      throws IOException
    {
      this.a = ☃.readDouble();
      this.b = ☃.readDouble();
      this.c = ☃.readDouble();
      this.d = ☃.readFloat();
      this.e = ☃.readFloat();
      super.a(☃);
    }
    
    public void b(em ☃)
      throws IOException
    {
      ☃.writeDouble(this.a);
      ☃.writeDouble(this.b);
      ☃.writeDouble(this.c);
      ☃.writeFloat(this.d);
      ☃.writeFloat(this.e);
      super.b(☃);
    }
  }
  
  public static class a
    extends it
  {
    public a()
    {
      this.g = true;
    }
    
    public a(double ☃, double ☃, double ☃, boolean ☃)
    {
      this.a = ☃;
      this.b = ☃;
      this.c = ☃;
      this.f = ☃;
      this.g = true;
    }
    
    public void a(em ☃)
      throws IOException
    {
      this.a = ☃.readDouble();
      this.b = ☃.readDouble();
      this.c = ☃.readDouble();
      super.a(☃);
    }
    
    public void b(em ☃)
      throws IOException
    {
      ☃.writeDouble(this.a);
      ☃.writeDouble(this.b);
      ☃.writeDouble(this.c);
      super.b(☃);
    }
  }
  
  public static class c
    extends it
  {
    public c()
    {
      this.h = true;
    }
    
    public c(float ☃, float ☃, boolean ☃)
    {
      this.d = ☃;
      this.e = ☃;
      this.f = ☃;
      this.h = true;
    }
    
    public void a(em ☃)
      throws IOException
    {
      this.d = ☃.readFloat();
      this.e = ☃.readFloat();
      super.a(☃);
    }
    
    public void b(em ☃)
      throws IOException
    {
      ☃.writeFloat(this.d);
      ☃.writeFloat(this.e);
      super.b(☃);
    }
  }
  
  public it(boolean ☃)
  {
    this.f = ☃;
  }
  
  public void a(ig ☃)
  {
    ☃.a(this);
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.f = (☃.readUnsignedByte() != 0);
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeByte(this.f ? 1 : 0);
  }
  
  public double a(double ☃)
  {
    return this.g ? this.a : ☃;
  }
  
  public double b(double ☃)
  {
    return this.g ? this.b : ☃;
  }
  
  public double c(double ☃)
  {
    return this.g ? this.c : ☃;
  }
  
  public float a(float ☃)
  {
    return this.h ? this.d : ☃;
  }
  
  public float b(float ☃)
  {
    return this.h ? this.e : ☃;
  }
  
  public boolean a()
  {
    return this.f;
  }
}
