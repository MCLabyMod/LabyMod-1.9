import com.google.common.collect.Lists;
import java.util.List;

class bgc$a
  extends aau
{
  public List<adq> a = Lists.newArrayList();
  
  public bgc$a(zj ☃)
  {
    zi ☃ = ☃.br;
    for (int ☃ = 0; ☃ < 5; ☃++) {
      for (int ☃ = 0; ☃ < 9; ☃++) {
        a(new abt(bgc.g(), ☃ * 9 + ☃, 9 + ☃ * 18, 18 + ☃ * 18));
      }
    }
    for (int ☃ = 0; ☃ < 9; ☃++) {
      a(new abt(☃, ☃, 9 + ☃ * 18, 112));
    }
    a(0.0F);
  }
  
  public boolean a(zj ☃)
  {
    return true;
  }
  
  public void a(float ☃)
  {
    int ☃ = (this.a.size() + 9 - 1) / 9 - 5;
    
    int ☃ = (int)(☃ * ☃ + 0.5D);
    if (☃ < 0) {
      ☃ = 0;
    }
    for (int ☃ = 0; ☃ < 5; ☃++) {
      for (int ☃ = 0; ☃ < 9; ☃++)
      {
        int ☃ = ☃ + (☃ + ☃) * 9;
        if ((☃ >= 0) && (☃ < this.a.size())) {
          bgc.g().a(☃ + ☃ * 9, (adq)this.a.get(☃));
        } else {
          bgc.g().a(☃ + ☃ * 9, null);
        }
      }
    }
  }
  
  public boolean e()
  {
    return this.a.size() > 45;
  }
  
  protected void a(int ☃, int ☃, boolean ☃, zj ☃) {}
  
  public adq b(zj ☃, int ☃)
  {
    if ((☃ >= this.c.size() - 9) && (☃ < this.c.size()))
    {
      abt ☃ = (abt)this.c.get(☃);
      if ((☃ != null) && (☃.e())) {
        ☃.d(null);
      }
    }
    return null;
  }
  
  public boolean a(adq ☃, abt ☃)
  {
    return ☃.g > 90;
  }
  
  public boolean b(abt ☃)
  {
    return ((☃.d instanceof zi)) || ((☃.g > 90) && (☃.f <= 162));
  }
}
