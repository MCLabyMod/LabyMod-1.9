import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

public class ayz
  extends ayx
{
  public int b;
  public int c;
  public byte d;
  public boolean e;
  public byte f;
  
  public class a
  {
    public final zj a;
    private boolean d = true;
    private int e = 0;
    private int f = 0;
    private int g = 127;
    private int h = 127;
    private int i;
    public int b;
    
    public a(zj ☃)
    {
      this.a = ☃;
    }
    
    public ff<?> a(adq ☃)
    {
      if (this.d)
      {
        this.d = false;
        return new gt(☃.i(), ayz.this.f, ayz.this.e, ayz.this.i.values(), ayz.this.g, this.e, this.f, this.g + 1 - this.e, this.h + 1 - this.f);
      }
      if (this.i++ % 5 == 0) {
        return new gt(☃.i(), ayz.this.f, ayz.this.e, ayz.this.i.values(), ayz.this.g, 0, 0, 0, 0);
      }
      return null;
    }
    
    public void a(int ☃, int ☃)
    {
      if (this.d)
      {
        this.e = Math.min(this.e, ☃);
        this.f = Math.min(this.f, ☃);
        this.g = Math.max(this.g, ☃);
        this.h = Math.max(this.h, ☃);
      }
      else
      {
        this.d = true;
        this.e = ☃;
        this.f = ☃;
        this.g = ☃;
        this.h = ☃;
      }
    }
  }
  
  public byte[] g = new byte['䀀'];
  public List<ayz.a> h = Lists.newArrayList();
  private Map<zj, ayz.a> j = Maps.newHashMap();
  public Map<String, ayy> i = Maps.newLinkedHashMap();
  
  public ayz(String ☃)
  {
    super(☃);
  }
  
  public void a(double ☃, double ☃, int ☃)
  {
    int ☃ = 128 * (1 << ☃);
    
    int ☃ = on.c((☃ + 64.0D) / ☃);
    int ☃ = on.c((☃ + 64.0D) / ☃);
    
    this.b = (☃ * ☃ + ☃ / 2 - 64);
    this.c = (☃ * ☃ + ☃ / 2 - 64);
  }
  
  public void a(dn ☃)
  {
    this.d = ☃.f("dimension");
    this.b = ☃.h("xCenter");
    this.c = ☃.h("zCenter");
    this.f = ☃.f("scale");
    this.f = ((byte)on.a(this.f, 0, 4));
    if (☃.b("trackingPosition", 1)) {
      this.e = ☃.p("trackingPosition");
    } else {
      this.e = true;
    }
    int ☃ = ☃.g("width");
    int ☃ = ☃.g("height");
    if ((☃ == 128) && (☃ == 128))
    {
      this.g = ☃.m("colors");
    }
    else
    {
      byte[] ☃ = ☃.m("colors");
      this.g = new byte['䀀'];
      int ☃ = (128 - ☃) / 2;
      int ☃ = (128 - ☃) / 2;
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        int ☃ = ☃ + ☃;
        if ((☃ >= 0) || (☃ < 128)) {
          for (int ☃ = 0; ☃ < ☃; ☃++)
          {
            int ☃ = ☃ + ☃;
            if ((☃ >= 0) || (☃ < 128)) {
              this.g[(☃ + ☃ * 128)] = ☃[(☃ + ☃ * ☃)];
            }
          }
        }
      }
    }
  }
  
  public void b(dn ☃)
  {
    ☃.a("dimension", this.d);
    ☃.a("xCenter", this.b);
    ☃.a("zCenter", this.c);
    ☃.a("scale", this.f);
    ☃.a("width", (short)128);
    ☃.a("height", (short)128);
    ☃.a("colors", this.g);
    ☃.a("trackingPosition", this.e);
  }
  
  public void a(zj ☃, adq ☃)
  {
    if (!this.j.containsKey(☃))
    {
      ayz.a ☃ = new ayz.a(☃);
      this.j.put(☃, ☃);
      this.h.add(☃);
    }
    if (!☃.br.f(☃)) {
      this.i.remove(☃.h_());
    }
    for (int ☃ = 0; ☃ < this.h.size(); ☃++)
    {
      ayz.a ☃ = (ayz.a)this.h.get(☃);
      if ((☃.a.F) || ((!☃.a.br.f(☃)) && (!☃.y())))
      {
        this.j.remove(☃.a);
        this.h.remove(☃);
      }
      else if ((!☃.y()) && (☃.a.am == this.d) && (this.e))
      {
        a(0, ☃.a.l, ☃.a.h_(), ☃.a.p, ☃.a.r, ☃.a.v);
      }
    }
    if ((☃.y()) && (this.e))
    {
      xs ☃ = ☃.z();
      cj ☃ = ☃.q();
      a(1, ☃.l, "frame-" + ☃.O(), ☃.p(), ☃.r(), ☃.b.b() * 90);
    }
    if ((☃.n()) && (☃.o().b("Decorations", 9)))
    {
      du ☃ = ☃.o().c("Decorations", 10);
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        dn ☃ = ☃.b(☃);
        if (!this.i.containsKey(☃.l("id"))) {
          a(☃.f("type"), ☃.l, ☃.l("id"), ☃.k("x"), ☃.k("z"), ☃.k("rot"));
        }
      }
    }
  }
  
  private void a(int ☃, aht ☃, String ☃, double ☃, double ☃, double ☃)
  {
    int ☃ = 1 << this.f;
    float ☃ = (float)(☃ - this.b) / ☃;
    float ☃ = (float)(☃ - this.c) / ☃;
    byte ☃ = (byte)(int)(☃ * 2.0F + 0.5D);
    byte ☃ = (byte)(int)(☃ * 2.0F + 0.5D);
    
    int ☃ = 63;
    if ((☃ >= -☃) && (☃ >= -☃) && (☃ <= ☃) && (☃ <= ☃))
    {
      ☃ += (☃ < 0.0D ? -8.0D : 8.0D);
      byte ☃ = (byte)(int)(☃ * 16.0D / 360.0D);
      if (this.d < 0)
      {
        int ☃ = (int)(☃.T().f() / 10L);
        ☃ = (byte)(☃ * ☃ * 34187121 + ☃ * 121 >> 15 & 0xF);
      }
    }
    else if ((Math.abs(☃) < 320.0F) && (Math.abs(☃) < 320.0F))
    {
      ☃ = 6;
      byte ☃ = 0;
      if (☃ <= -☃) {
        ☃ = (byte)(int)(☃ * 2 + 2.5D);
      }
      if (☃ <= -☃) {
        ☃ = (byte)(int)(☃ * 2 + 2.5D);
      }
      if (☃ >= ☃) {
        ☃ = (byte)(☃ * 2 + 1);
      }
      if (☃ >= ☃) {
        ☃ = (byte)(☃ * 2 + 1);
      }
    }
    else
    {
      this.i.remove(☃); return;
    }
    byte ☃;
    this.i.put(☃, new ayy((byte)☃, ☃, ☃, ☃));
  }
  
  public ff<?> a(adq ☃, aht ☃, zj ☃)
  {
    ayz.a ☃ = (ayz.a)this.j.get(☃);
    if (☃ == null) {
      return null;
    }
    return ☃.a(☃);
  }
  
  public void a(int ☃, int ☃)
  {
    super.c();
    for (ayz.a ☃ : this.h) {
      ☃.a(☃, ☃);
    }
  }
  
  public ayz.a a(zj ☃)
  {
    ayz.a ☃ = (ayz.a)this.j.get(☃);
    if (☃ == null)
    {
      ☃ = new ayz.a(☃);
      this.j.put(☃, ☃);
      this.h.add(☃);
    }
    return ☃;
  }
}
