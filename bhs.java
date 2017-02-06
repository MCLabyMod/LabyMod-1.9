import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import java.util.List;

public class bhs
{
  private static final bhu b = new bhs.a(null);
  private static final bhu c = new bhs.b(-1, true);
  private static final bhu d = new bhs.b(1, true);
  private static final bhu e = new bhs.b(1, false);
  public static final bhu a = new bhu()
  {
    public void a(bhs ☃) {}
    
    public eu F_()
    {
      return new fa("");
    }
    
    public void a(float ☃, int ☃) {}
    
    public boolean G_()
    {
      return false;
    }
  };
  private final bhv f;
  private final List<bhw> g = Lists.newArrayList();
  private bht h;
  private int i = -1;
  private int j;
  
  public bhs(bhv ☃)
  {
    this.h = new bhr();
    this.f = ☃;
  }
  
  public bhu a(int ☃)
  {
    int ☃ = ☃ + this.j * 6;
    if ((this.j > 0) && (☃ == 0)) {
      return c;
    }
    if (☃ == 7)
    {
      if (☃ < this.h.a().size()) {
        return d;
      }
      return e;
    }
    if (☃ == 8) {
      return b;
    }
    if ((☃ < 0) || (☃ >= this.h.a().size())) {
      return a;
    }
    return (bhu)Objects.firstNonNull(this.h.a().get(☃), a);
  }
  
  public List<bhu> a()
  {
    List<bhu> ☃ = Lists.newArrayList();
    for (int ☃ = 0; ☃ <= 8; ☃++) {
      ☃.add(a(☃));
    }
    return ☃;
  }
  
  public bhu b()
  {
    return a(this.i);
  }
  
  public bht c()
  {
    return this.h;
  }
  
  public void b(int ☃)
  {
    bhu ☃ = a(☃);
    if (☃ != a) {
      if ((this.i == ☃) && (☃.G_())) {
        ☃.a(this);
      } else {
        this.i = ☃;
      }
    }
  }
  
  public void d()
  {
    this.f.a(this);
  }
  
  public int e()
  {
    return this.i;
  }
  
  public void a(bht ☃)
  {
    this.g.add(f());
    
    this.h = ☃;
    this.i = -1;
    this.j = 0;
  }
  
  public bhw f()
  {
    return new bhw(this.h, a(), this.i);
  }
  
  static class a
    implements bhu
  {
    public void a(bhs ☃)
    {
      ☃.d();
    }
    
    public eu F_()
    {
      return new fa("Close menu");
    }
    
    public void a(float ☃, int ☃)
    {
      bcf.z().N().a(bdu.a);
      bcv.a(0, 0, 128.0F, 0.0F, 16, 16, 256.0F, 256.0F);
    }
    
    public boolean G_()
    {
      return true;
    }
  }
  
  static class b
    implements bhu
  {
    private final int a;
    private final boolean b;
    
    public b(int ☃, boolean ☃)
    {
      this.a = ☃;
      this.b = ☃;
    }
    
    public void a(bhs ☃)
    {
      bhs.a(☃, this.a);
    }
    
    public eu F_()
    {
      if (this.a < 0) {
        return new fa("Previous Page");
      }
      return new fa("Next Page");
    }
    
    public void a(float ☃, int ☃)
    {
      bcf.z().N().a(bdu.a);
      if (this.a < 0) {
        bcv.a(0, 0, 144.0F, 0.0F, 16, 16, 256.0F, 256.0F);
      } else {
        bcv.a(0, 0, 160.0F, 0.0F, 16, 16, 256.0F, 256.0F);
      }
    }
    
    public boolean G_()
    {
      return this.b;
    }
  }
}
