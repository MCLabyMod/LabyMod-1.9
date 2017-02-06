import java.io.IOException;
import org.apache.commons.lang3.Validate;

public class hz
  implements ff<fi>
{
  private nf a;
  private nh b;
  private int c;
  private int d;
  private int e;
  private float f;
  private int g;
  
  public hz() {}
  
  public hz(nf ☃, nh ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    Validate.notNull(☃, "sound", new Object[0]);
    this.a = ☃;
    this.b = ☃;
    this.c = ((int)(☃ * 8.0D));
    this.d = ((int)(☃ * 8.0D));
    this.e = ((int)(☃ * 8.0D));
    this.f = ☃;
    this.g = ((int)(☃ * 63.5F));
    
    ☃ = on.a(☃, 0.0F, 255.0F);
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ((nf)nf.a.a(☃.g()));
    this.b = ((nh)☃.a(nh.class));
    this.c = ☃.readInt();
    this.d = ☃.readInt();
    this.e = ☃.readInt();
    this.f = ☃.readFloat();
    this.g = ☃.readUnsignedByte();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(nf.a.a(this.a));
    ☃.a(this.b);
    ☃.writeInt(this.c);
    ☃.writeInt(this.d);
    ☃.writeInt(this.e);
    ☃.writeFloat(this.f);
    ☃.writeByte(this.g);
  }
  
  public nf a()
  {
    return this.a;
  }
  
  public nh b()
  {
    return this.b;
  }
  
  public double c()
  {
    return this.c / 8.0F;
  }
  
  public double d()
  {
    return this.d / 8.0F;
  }
  
  public double e()
  {
    return this.e / 8.0F;
  }
  
  public float f()
  {
    return this.f;
  }
  
  public float g()
  {
    return this.g / 63.5F;
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
}
