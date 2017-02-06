import java.io.IOException;

public class jg
  implements ff<ig>
{
  private cj a;
  private cq b;
  private qm c;
  private float d;
  private float e;
  private float f;
  
  public jg() {}
  
  public jg(cj ☃, cq ☃, qm ☃, float ☃, float ☃, float ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
    this.f = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.e();
    this.b = ((cq)☃.a(cq.class));
    this.c = ((qm)☃.a(qm.class));
    this.d = (☃.readUnsignedByte() / 16.0F);
    this.e = (☃.readUnsignedByte() / 16.0F);
    this.f = (☃.readUnsignedByte() / 16.0F);
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a);
    ☃.a(this.b);
    ☃.a(this.c);
    ☃.writeByte((int)(this.d * 16.0F));
    ☃.writeByte((int)(this.e * 16.0F));
    ☃.writeByte((int)(this.f * 16.0F));
  }
  
  public void a(ig ☃)
  {
    ☃.a(this);
  }
  
  public cj a()
  {
    return this.a;
  }
  
  public cq b()
  {
    return this.b;
  }
  
  public qm c()
  {
    return this.c;
  }
  
  public float d()
  {
    return this.d;
  }
  
  public float e()
  {
    return this.e;
  }
  
  public float f()
  {
    return this.f;
  }
}
