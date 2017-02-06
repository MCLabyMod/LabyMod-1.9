import shadersmod.client.SVertexFormat;

public class bjg
{
  public bjy[] a;
  public int b;
  private boolean c;
  
  public bjg(bjy[] vertices)
  {
    this.a = vertices;
    this.b = vertices.length;
  }
  
  public bjg(bjy[] vertices, int texcoordU1, int texcoordV1, int texcoordU2, int texcoordV2, float textureWidth, float textureHeight)
  {
    this(vertices);
    float f = 0.0F / textureWidth;
    float f1 = 0.0F / textureHeight;
    vertices[0] = vertices[0].a(texcoordU2 / textureWidth - f, texcoordV1 / textureHeight + f1);
    vertices[1] = vertices[1].a(texcoordU1 / textureWidth + f, texcoordV1 / textureHeight + f1);
    vertices[2] = vertices[2].a(texcoordU1 / textureWidth + f, texcoordV2 / textureHeight - f1);
    vertices[3] = vertices[3].a(texcoordU2 / textureWidth - f, texcoordV2 / textureHeight - f1);
  }
  
  public void a()
  {
    bjy[] apositiontexturevertex = new bjy[this.a.length];
    for (int i = 0; i < this.a.length; i++) {
      apositiontexturevertex[i] = this.a[(this.a.length - i - 1)];
    }
    this.a = apositiontexturevertex;
  }
  
  public void a(bmz renderer, float scale)
  {
    bbj vec3d = this.a[1].a.a(this.a[0].a);
    bbj vec3d1 = this.a[1].a.a(this.a[2].a);
    bbj vec3d2 = vec3d1.c(vec3d).a();
    float f = (float)vec3d2.b;
    float f1 = (float)vec3d2.c;
    float f2 = (float)vec3d2.d;
    if (this.c)
    {
      f = -f;
      f1 = -f1;
      f2 = -f2;
    }
    if (Config.isShaders()) {
      renderer.a(7, SVertexFormat.defVertexFormatTextured);
    } else {
      renderer.a(7, bvp.c);
    }
    for (int i = 0; i < 4; i++)
    {
      bjy positiontexturevertex = this.a[i];
      renderer.b(positiontexturevertex.a.b * scale, positiontexturevertex.a.c * scale, positiontexturevertex.a.d * scale).a(positiontexturevertex.b, positiontexturevertex.c).c(f, f1, f2).d();
    }
    bnu.a().b();
  }
}
