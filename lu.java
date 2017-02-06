import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class lu
{
  private static final Logger a = ;
  private final lv b;
  private final List<lr> c = Lists.newArrayList();
  private final ahn d;
  private final short[] e = new short[64];
  private ase f;
  private int g;
  private int h;
  private long i;
  private boolean j;
  
  public lu(lv ☃, int ☃, int ☃)
  {
    this.b = ☃;
    this.d = new ahn(☃, ☃);
    this.f = ☃.a().r().c(☃, ☃);
  }
  
  public ahn a()
  {
    return this.d;
  }
  
  public void a(lr ☃)
  {
    if (this.c.contains(☃))
    {
      a.debug("Failed to add player. {} already is in chunk {}, {}", new Object[] { ☃, Integer.valueOf(this.d.a), Integer.valueOf(this.d.b) });
      return;
    }
    if (this.c.isEmpty()) {
      this.i = this.b.a().P();
    }
    this.c.add(☃);
    if (this.j) {
      c(☃);
    }
  }
  
  public void b(lr ☃)
  {
    if (!this.c.contains(☃)) {
      return;
    }
    if (this.j) {
      ☃.a.a(new gm(this.d.a, this.d.b));
    }
    this.c.remove(☃);
    if (this.c.isEmpty()) {
      this.b.b(this);
    }
  }
  
  public boolean a(boolean ☃)
  {
    if (this.f != null) {
      return true;
    }
    if (☃) {
      this.f = this.b.a().r().d(this.d.a, this.d.b);
    } else {
      this.f = this.b.a().r().c(this.d.a, this.d.b);
    }
    return this.f != null;
  }
  
  public boolean b()
  {
    if (this.j) {
      return true;
    }
    if (this.f == null) {
      return false;
    }
    if (!this.f.i()) {
      return false;
    }
    this.g = 0;
    this.h = 0;
    this.j = true;
    List<apv> ☃ = Lists.newArrayList(this.b.a().a(this.d.a * 16, 0, this.d.b * 16, this.d.a * 16 + 16, 256, this.d.b * 16 + 16));
    gp ☃ = new gp(this.f, true, 65535);
    for (lr ☃ : this.c)
    {
      ☃.a.a(☃);
      for (apv ☃ : ☃)
      {
        ff<?> ☃ = ☃.D_();
        if (☃ != null) {
          ☃.a.a(☃);
        }
      }
      this.b.a().v().a(☃, this.f);
    }
    return true;
  }
  
  public void c(lr ☃)
  {
    if (!this.j) {
      return;
    }
    ☃.a.a(new gp(this.f, true, 65535));
    for (apv ☃ : this.b.a().a(this.d.a * 16, 0, this.d.b * 16, this.d.a * 16 + 16, 256, this.d.b * 16 + 16))
    {
      ff<?> ☃ = ☃.D_();
      if (☃ != null) {
        ☃.a.a(☃);
      }
    }
    this.b.a().v().a(☃, this.f);
  }
  
  public void c()
  {
    if (this.f != null) {
      this.f.c(this.f.x() + this.b.a().P() - this.i);
    }
    this.i = this.b.a().P();
  }
  
  public void a(int ☃, int ☃, int ☃)
  {
    if (!this.j) {
      return;
    }
    if (this.g == 0) {
      this.b.a(this);
    }
    this.h |= 1 << (☃ >> 4);
    if (this.g < 64)
    {
      short ☃ = (short)(☃ << 12 | ☃ << 8 | ☃);
      for (int ☃ = 0; ☃ < this.g; ☃++) {
        if (this.e[☃] == ☃) {
          return;
        }
      }
      this.e[(this.g++)] = ☃;
    }
  }
  
  public void a(ff<?> ☃)
  {
    if (!this.j) {
      return;
    }
    for (int ☃ = 0; ☃ < this.c.size(); ☃++) {
      ((lr)this.c.get(☃)).a.a(☃);
    }
  }
  
  public void d()
  {
    if ((!this.j) || (this.f == null)) {
      return;
    }
    if (this.g == 0) {
      return;
    }
    if (this.g == 1)
    {
      int ☃ = (this.e[0] >> 12 & 0xF) + this.d.a * 16;
      int ☃ = this.e[0] & 0xFF;
      int ☃ = (this.e[0] >> 8 & 0xF) + this.d.b * 16;
      
      cj ☃ = new cj(☃, ☃, ☃);
      a(new fu(this.b.a(), ☃));
      if (this.b.a().o(☃).t().m()) {
        a(this.b.a().r(☃));
      }
    }
    else if (this.g == 64)
    {
      int ☃ = this.d.a * 16;
      int ☃ = this.d.b * 16;
      
      a(new gp(this.f, false, this.h));
      for (int ☃ = 0; ☃ < 16; ☃++) {
        if ((this.h & 1 << ☃) != 0)
        {
          int ☃ = ☃ << 4;
          List<apv> ☃ = this.b.a().a(☃, ☃, ☃, ☃ + 16, ☃ + 16, ☃ + 16);
          for (int ☃ = 0; ☃ < ☃.size(); ☃++) {
            a((apv)☃.get(☃));
          }
        }
      }
    }
    else
    {
      a(new fz(this.g, this.e, this.f));
      for (int ☃ = 0; ☃ < this.g; ☃++)
      {
        int ☃ = (this.e[☃] >> 12 & 0xF) + this.d.a * 16;
        int ☃ = this.e[☃] & 0xFF;
        int ☃ = (this.e[☃] >> 8 & 0xF) + this.d.b * 16;
        
        cj ☃ = new cj(☃, ☃, ☃);
        if (this.b.a().o(☃).t().m()) {
          a(this.b.a().r(☃));
        }
      }
    }
    this.g = 0;
    this.h = 0;
  }
  
  private void a(apv ☃)
  {
    if (☃ != null)
    {
      ff<?> ☃ = ☃.D_();
      if (☃ != null) {
        a(☃);
      }
    }
  }
  
  public boolean d(lr ☃)
  {
    return this.c.contains(☃);
  }
  
  public boolean a(Predicate<lr> ☃)
  {
    return Iterables.tryFind(this.c, ☃).isPresent();
  }
  
  public boolean a(double ☃, Predicate<lr> ☃)
  {
    int ☃ = 0;
    for (int ☃ = this.c.size(); ☃ < ☃; ☃++)
    {
      lr ☃ = (lr)this.c.get(☃);
      if ((☃.apply(☃)) && (this.d.a(☃) < ☃ * ☃)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean e()
  {
    return this.j;
  }
  
  public ase f()
  {
    return this.f;
  }
  
  public double g()
  {
    double ☃ = Double.MAX_VALUE;
    for (lr ☃ : this.c)
    {
      double ☃ = this.d.a(☃);
      if (☃ < ☃) {
        ☃ = ☃;
      }
    }
    return ☃;
  }
}
