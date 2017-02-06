import com.mojang.authlib.GameProfile;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class fo
  implements ff<fi>
{
  private int a;
  private UUID b;
  private double c;
  private double d;
  private double e;
  private byte f;
  private byte g;
  private kh h;
  private List<kh.a<?>> i;
  
  public fo() {}
  
  public fo(zj ☃)
  {
    this.a = ☃.O();
    this.b = ☃.cK().getId();
    this.c = ☃.p;
    this.d = ☃.q;
    this.e = ☃.r;
    this.f = ((byte)(int)(☃.v * 256.0F / 360.0F));
    this.g = ((byte)(int)(☃.w * 256.0F / 360.0F));
    
    this.h = ☃.R();
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.b = ☃.i();
    this.c = ☃.readDouble();
    this.d = ☃.readDouble();
    this.e = ☃.readDouble();
    this.f = ☃.readByte();
    this.g = ☃.readByte();
    this.i = kh.b(☃);
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
    ☃.a(this.b);
    ☃.writeDouble(this.c);
    ☃.writeDouble(this.d);
    ☃.writeDouble(this.e);
    ☃.writeByte(this.f);
    ☃.writeByte(this.g);
    this.h.a(☃);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public List<kh.a<?>> a()
  {
    if (this.i == null) {
      this.i = this.h.c();
    }
    return this.i;
  }
  
  public int b()
  {
    return this.a;
  }
  
  public UUID c()
  {
    return this.b;
  }
  
  public double d()
  {
    return this.c;
  }
  
  public double e()
  {
    return this.d;
  }
  
  public double f()
  {
    return this.e;
  }
  
  public byte g()
  {
    return this.f;
  }
  
  public byte h()
  {
    return this.g;
  }
}
