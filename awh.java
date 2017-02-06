import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class awh
{
  protected List<awg> a = Lists.newLinkedList();
  protected avp b;
  private int c;
  private int d;
  
  public awh() {}
  
  public awh(int ☃, int ☃)
  {
    this.c = ☃;
    this.d = ☃;
  }
  
  public avp b()
  {
    return this.b;
  }
  
  public List<awg> c()
  {
    return this.a;
  }
  
  public void a(aht ☃, Random ☃, avp ☃)
  {
    Iterator<awg> ☃ = this.a.iterator();
    while (☃.hasNext())
    {
      awg ☃ = (awg)☃.next();
      if ((☃.c().a(☃)) && 
        (!☃.a(☃, ☃, ☃))) {
        ☃.remove();
      }
    }
  }
  
  protected void d()
  {
    this.b = avp.a();
    for (awg ☃ : this.a) {
      this.b.b(☃.c());
    }
  }
  
  public dn a(int ☃, int ☃)
  {
    dn ☃ = new dn();
    
    ☃.a("id", awe.a(this));
    ☃.a("ChunkX", ☃);
    ☃.a("ChunkZ", ☃);
    ☃.a("BB", this.b.g());
    
    du ☃ = new du();
    for (awg ☃ : this.a) {
      ☃.a(☃.b());
    }
    ☃.a("Children", ☃);
    
    a(☃);
    
    return ☃;
  }
  
  public void a(dn ☃) {}
  
  public void a(aht ☃, dn ☃)
  {
    this.c = ☃.h("ChunkX");
    this.d = ☃.h("ChunkZ");
    if (☃.e("BB")) {
      this.b = new avp(☃.n("BB"));
    }
    du ☃ = ☃.c("Children", 10);
    for (int ☃ = 0; ☃ < ☃.c(); ☃++) {
      this.a.add(awe.b(☃.b(☃), ☃));
    }
    b(☃);
  }
  
  public void b(dn ☃) {}
  
  protected void a(aht ☃, Random ☃, int ☃)
  {
    int ☃ = ☃.K() - ☃;
    
    int ☃ = this.b.d() + 1;
    if (☃ < ☃) {
      ☃ += ☃.nextInt(☃ - ☃);
    }
    int ☃ = ☃ - this.b.e;
    this.b.a(0, ☃, 0);
    for (awg ☃ : this.a) {
      ☃.a(0, ☃, 0);
    }
  }
  
  protected void a(aht ☃, Random ☃, int ☃, int ☃)
  {
    int ☃ = ☃ - ☃ + 1 - this.b.d();
    int ☃ = 1;
    if (☃ > 1) {
      ☃ = ☃ + ☃.nextInt(☃);
    } else {
      ☃ = ☃;
    }
    int ☃ = ☃ - this.b.b;
    this.b.a(0, ☃, 0);
    for (awg ☃ : this.a) {
      ☃.a(0, ☃, 0);
    }
  }
  
  public boolean a()
  {
    return true;
  }
  
  public boolean a(ahn ☃)
  {
    return true;
  }
  
  public void b(ahn ☃) {}
  
  public int e()
  {
    return this.c;
  }
  
  public int f()
  {
    return this.d;
  }
}
