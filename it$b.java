import java.io.IOException;

public class it$b
  extends it
{
  public it$b()
  {
    this.g = true;
    this.h = true;
  }
  
  public it$b(double ☃, double ☃, double ☃, float ☃, float ☃, boolean ☃)
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
