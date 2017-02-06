import java.io.IOException;

public class it$a
  extends it
{
  public it$a()
  {
    this.g = true;
  }
  
  public it$a(double ☃, double ☃, double ☃, boolean ☃)
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
