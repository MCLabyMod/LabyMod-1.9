import java.io.IOException;

public class gr
  implements ff<fi>
{
  private cy a;
  private float b;
  private float c;
  private float d;
  private float e;
  private float f;
  private float g;
  private float h;
  private int i;
  private boolean j;
  private int[] k;
  
  public gr() {}
  
  public gr(cy ☃, boolean ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, int ☃, int... ☃)
  {
    this.a = ☃;
    this.j = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
    this.f = ☃;
    this.g = ☃;
    this.h = ☃;
    this.i = ☃;
    this.k = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = cy.a(☃.readInt());
    if (this.a == null) {
      this.a = cy.J;
    }
    this.j = ☃.readBoolean();
    this.b = ☃.readFloat();
    this.c = ☃.readFloat();
    this.d = ☃.readFloat();
    this.e = ☃.readFloat();
    this.f = ☃.readFloat();
    this.g = ☃.readFloat();
    this.h = ☃.readFloat();
    this.i = ☃.readInt();
    int ☃ = this.a.d();
    this.k = new int[☃];
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      this.k[☃] = ☃.g();
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeInt(this.a.c());
    ☃.writeBoolean(this.j);
    ☃.writeFloat(this.b);
    ☃.writeFloat(this.c);
    ☃.writeFloat(this.d);
    ☃.writeFloat(this.e);
    ☃.writeFloat(this.f);
    ☃.writeFloat(this.g);
    ☃.writeFloat(this.h);
    ☃.writeInt(this.i);
    int ☃ = this.a.d();
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      ☃.b(this.k[☃]);
    }
  }
  
  public cy a()
  {
    return this.a;
  }
  
  public boolean b()
  {
    return this.j;
  }
  
  public double c()
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
  
  public float f()
  {
    return this.e;
  }
  
  public float g()
  {
    return this.f;
  }
  
  public float h()
  {
    return this.g;
  }
  
  public float i()
  {
    return this.h;
  }
  
  public int j()
  {
    return this.i;
  }
  
  public int[] k()
  {
    return this.k;
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
}
