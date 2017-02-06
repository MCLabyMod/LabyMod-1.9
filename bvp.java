import shadersmod.client.SVertexFormat;

public class bvp
{
  public static bvr a = new bvr();
  public static bvr b = new bvr();
  private static final bvr BLOCK_VANILLA = a;
  private static final bvr ITEM_VANILLA = b;
  public static final bvr c = new bvr();
  public static final bvr d = new bvr();
  public static final bvr e = new bvr();
  public static final bvr f = new bvr();
  public static final bvr g = new bvr();
  public static final bvr h = new bvr();
  public static final bvr i = new bvr();
  public static final bvr j = new bvr();
  public static final bvr k = new bvr();
  public static final bvr l = new bvr();
  public static final bvs m = new bvs(0, bvs.a.a, bvs.b.a, 3);
  public static final bvs n = new bvs(0, bvs.a.b, bvs.b.c, 4);
  public static final bvs o = new bvs(0, bvs.a.a, bvs.b.d, 2);
  public static final bvs p = new bvs(1, bvs.a.e, bvs.b.d, 2);
  public static final bvs q = new bvs(0, bvs.a.c, bvs.b.b, 3);
  public static final bvs r = new bvs(0, bvs.a.c, bvs.b.g, 1);
  
  public static void updateVertexFormats()
  {
    if (Config.isShaders())
    {
      a = SVertexFormat.makeDefVertexFormatBlock();
      b = SVertexFormat.makeDefVertexFormatItem();
    }
    else
    {
      a = BLOCK_VANILLA;
      b = ITEM_VANILLA;
    }
    if (Reflector.Attributes_DEFAULT_BAKED_FORMAT.exists())
    {
      bvr vfSrc = b;
      bvr vfDst = (bvr)Reflector.getFieldValue(Reflector.Attributes_DEFAULT_BAKED_FORMAT);
      
      vfDst.a();
      for (int i = 0; i < vfSrc.i(); i++) {
        vfDst.a(vfSrc.c(i));
      }
    }
  }
  
  static
  {
    a.a(m);
    a.a(n);
    a.a(o);
    a.a(p);
    b.a(m);
    b.a(n);
    b.a(o);
    b.a(q);
    b.a(r);
    c.a(m);
    c.a(o);
    c.a(q);
    c.a(r);
    d.a(m);
    d.a(o);
    d.a(n);
    d.a(p);
    e.a(m);
    f.a(m);
    f.a(n);
    g.a(m);
    g.a(o);
    h.a(m);
    h.a(q);
    h.a(r);
    i.a(m);
    i.a(o);
    i.a(n);
    j.a(m);
    j.a(o);
    j.a(q);
    j.a(r);
    k.a(m);
    k.a(o);
    k.a(p);
    k.a(n);
    l.a(m);
    l.a(o);
    l.a(n);
    l.a(q);
    l.a(r);
  }
}
