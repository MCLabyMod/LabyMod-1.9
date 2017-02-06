import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;

public class gl
  implements ff<fi>
{
  private double a;
  private double b;
  private double c;
  private float d;
  private List<cj> e;
  private float f;
  private float g;
  private float h;
  
  public gl() {}
  
  public gl(double ☃, double ☃, double ☃, float ☃, List<cj> ☃, bbj ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
    this.e = Lists.newArrayList(☃);
    if (☃ != null)
    {
      this.f = ((float)☃.b);
      this.g = ((float)☃.c);
      this.h = ((float)☃.d);
    }
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readFloat();
    this.b = ☃.readFloat();
    this.c = ☃.readFloat();
    this.d = ☃.readFloat();
    int ☃ = ☃.readInt();
    
    this.e = Lists.newArrayListWithCapacity(☃);
    
    int ☃ = (int)this.a;
    int ☃ = (int)this.b;
    int ☃ = (int)this.c;
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      int ☃ = ☃.readByte() + ☃;
      int ☃ = ☃.readByte() + ☃;
      int ☃ = ☃.readByte() + ☃;
      this.e.add(new cj(☃, ☃, ☃));
    }
    this.f = ☃.readFloat();
    this.g = ☃.readFloat();
    this.h = ☃.readFloat();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeFloat((float)this.a);
    ☃.writeFloat((float)this.b);
    ☃.writeFloat((float)this.c);
    ☃.writeFloat(this.d);
    ☃.writeInt(this.e.size());
    
    int ☃ = (int)this.a;
    int ☃ = (int)this.b;
    int ☃ = (int)this.c;
    for (cj ☃ : this.e)
    {
      int ☃ = ☃.p() - ☃;
      int ☃ = ☃.q() - ☃;
      int ☃ = ☃.r() - ☃;
      ☃.writeByte(☃);
      ☃.writeByte(☃);
      ☃.writeByte(☃);
    }
    ☃.writeFloat(this.f);
    ☃.writeFloat(this.g);
    ☃.writeFloat(this.h);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public float a()
  {
    return this.f;
  }
  
  public float b()
  {
    return this.g;
  }
  
  public float c()
  {
    return this.h;
  }
  
  public double d()
  {
    return this.a;
  }
  
  public double e()
  {
    return this.b;
  }
  
  public double f()
  {
    return this.c;
  }
  
  public float g()
  {
    return this.d;
  }
  
  public List<cj> h()
  {
    return this.e;
  }
}
