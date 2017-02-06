import shadersmod.client.MultiTexID;
import shadersmod.client.ShadersTex;

public abstract class buw
  implements bvj
{
  protected int a = -1;
  protected boolean b;
  protected boolean c;
  protected boolean d;
  protected boolean e;
  public MultiTexID multiTex;
  
  public void a(boolean blurIn, boolean mipmapIn)
  {
    this.b = blurIn;
    this.c = mipmapIn;
    int i = -1;
    int j = -1;
    if (blurIn)
    {
      i = mipmapIn ? 9987 : 9729;
      j = 9729;
    }
    else
    {
      i = mipmapIn ? 9986 : 9728;
      j = 9728;
    }
    bni.i(b());
    
    bni.b(3553, 10241, i);
    bni.b(3553, 10240, j);
  }
  
  public void b(boolean blurIn, boolean mipmapIn)
  {
    this.d = this.b;
    this.e = this.c;
    a(blurIn, mipmapIn);
  }
  
  public void a()
  {
    a(this.d, this.e);
  }
  
  public int b()
  {
    if (this.a == -1) {
      this.a = bvk.a();
    }
    return this.a;
  }
  
  public void c()
  {
    ShadersTex.deleteTextures(this, this.a);
    if (this.a != -1)
    {
      bvk.a(this.a);
      this.a = -1;
    }
  }
  
  public MultiTexID getMultiTexID()
  {
    return ShadersTex.getMultiTexID(this);
  }
}
