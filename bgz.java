import java.util.List;

public abstract class bgz
  implements bdl.a
{
  private static final kk c = new kk("textures/gui/resource_packs.png");
  private static final eu d = new fb("resourcePack.incompatible", new Object[0]);
  private static final eu e = new fb("resourcePack.incompatible.old", new Object[0]);
  private static final eu f = new fb("resourcePack.incompatible.new", new Object[0]);
  protected final bcf a;
  protected final bgy b;
  
  public bgz(bgy ☃)
  {
    this.b = ☃;
    this.a = bcf.z();
  }
  
  public void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    int ☃ = a();
    if (☃ != 2)
    {
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      bcv.a(☃ - 1, ☃ - 1, ☃ + ☃ - 9, ☃ + ☃ + 1, -8978432);
    }
    d();
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bcv.a(☃, ☃, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
    String ☃ = c();
    String ☃ = b();
    if ((e()) && ((this.a.u.z) || (☃)))
    {
      this.a.N().a(c);
      bcv.a(☃, ☃, ☃ + 32, ☃ + 32, -1601138544);
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      int ☃ = ☃ - ☃;
      int ☃ = ☃ - ☃;
      if (☃ < 2)
      {
        ☃ = d.d();
        ☃ = e.d();
      }
      else if (☃ > 2)
      {
        ☃ = d.d();
        ☃ = f.d();
      }
      if (f())
      {
        if (☃ < 32) {
          bcv.a(☃, ☃, 0.0F, 32.0F, 32, 32, 256.0F, 256.0F);
        } else {
          bcv.a(☃, ☃, 0.0F, 0.0F, 32, 32, 256.0F, 256.0F);
        }
      }
      else
      {
        if (g()) {
          if (☃ < 16) {
            bcv.a(☃, ☃, 32.0F, 32.0F, 32, 32, 256.0F, 256.0F);
          } else {
            bcv.a(☃, ☃, 32.0F, 0.0F, 32, 32, 256.0F, 256.0F);
          }
        }
        if (h()) {
          if ((☃ < 32) && (☃ > 16) && (☃ < 16)) {
            bcv.a(☃, ☃, 96.0F, 32.0F, 32, 32, 256.0F, 256.0F);
          } else {
            bcv.a(☃, ☃, 96.0F, 0.0F, 32, 32, 256.0F, 256.0F);
          }
        }
        if (i()) {
          if ((☃ < 32) && (☃ > 16) && (☃ > 16)) {
            bcv.a(☃, ☃, 64.0F, 32.0F, 32, 32, 256.0F, 256.0F);
          } else {
            bcv.a(☃, ☃, 64.0F, 0.0F, 32, 32, 256.0F, 256.0F);
          }
        }
      }
    }
    int ☃ = this.a.k.a(☃);
    if (☃ > 157) {
      ☃ = this.a.k.a(☃, 157 - this.a.k.a("...")) + "...";
    }
    this.a.k.a(☃, ☃ + 32 + 2, ☃ + 1, 16777215);
    List<String> ☃ = this.a.k.c(☃, 157);
    for (int ☃ = 0; (☃ < 2) && (☃ < ☃.size()); ☃++) {
      this.a.k.a((String)☃.get(☃), ☃ + 32 + 2, ☃ + 12 + 10 * ☃, 8421504);
    }
  }
  
  protected abstract int a();
  
  protected abstract String b();
  
  protected abstract String c();
  
  protected abstract void d();
  
  protected boolean e()
  {
    return true;
  }
  
  protected boolean f()
  {
    return !this.b.a(this);
  }
  
  protected boolean g()
  {
    return this.b.a(this);
  }
  
  protected boolean h()
  {
    List<bgz> ☃ = this.b.b(this);
    int ☃ = ☃.indexOf(this);
    return (☃ > 0) && (((bgz)☃.get(☃ - 1)).e());
  }
  
  protected boolean i()
  {
    List<bgz> ☃ = this.b.b(this);
    int ☃ = ☃.indexOf(this);
    return (☃ >= 0) && (☃ < ☃.size() - 1) && (((bgz)☃.get(☃ + 1)).e());
  }
  
  public boolean a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    if ((e()) && (☃ <= 32))
    {
      if (f())
      {
        this.b.g();
        
        final int ☃ = ((bgz)this.b.f().get(0)).j() ? 1 : 0;
        
        int ☃ = a();
        if (☃ != 2)
        {
          String ☃ = bwo.a("resourcePack.incompatible.confirm.title", new Object[0]);
          String ☃ = bwo.a("resourcePack.incompatible.confirm." + (☃ > 2 ? "new" : "old"), new Object[0]);
          
          this.a.a(new beh(new beg()
          {
            public void a(boolean ☃, int ☃)
            {
              List<bgz> ☃ = bgz.this.b.b(bgz.this);
              bgz.this.a.a(bgz.this.b);
              if (☃)
              {
                ☃.remove(bgz.this);
                bgz.this.b.f().add(☃, bgz.this);
              }
            }
          }, ☃, ☃, 0));
        }
        else
        {
          this.b.b(this).remove(this);
          this.b.f().add(☃, this);
        }
        return true;
      }
      if ((☃ < 16) && (g()))
      {
        this.b.b(this).remove(this);
        this.b.a().add(0, this);
        this.b.g();
        return true;
      }
      if ((☃ > 16) && (☃ < 16) && (h()))
      {
        List<bgz> ☃ = this.b.b(this);
        int ☃ = ☃.indexOf(this);
        ☃.remove(this);
        ☃.add(☃ - 1, this);
        this.b.g();
        return true;
      }
      if ((☃ > 16) && (☃ > 16) && (i()))
      {
        List<bgz> ☃ = this.b.b(this);
        int ☃ = ☃.indexOf(this);
        ☃.remove(this);
        ☃.add(☃ + 1, this);
        this.b.g();
        return true;
      }
    }
    return false;
  }
  
  public void a(int ☃, int ☃, int ☃) {}
  
  public void b(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃) {}
  
  public boolean j()
  {
    return false;
  }
}
