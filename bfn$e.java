import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.lwjgl.input.Mouse;

abstract class bfn$e
  extends bdq
{
  protected int v = -1;
  protected List<nn> w;
  protected Comparator<nn> x;
  protected int y = -1;
  protected int z;
  
  protected bfn$e(bfn parambfn, bcf ☃)
  {
    super(☃, parambfn.l, parambfn.m, 32, parambfn.m - 64, 20);
    
    b(false);
    a(true, 20);
  }
  
  protected void a(int ☃, boolean ☃, int ☃, int ☃) {}
  
  protected boolean a(int ☃)
  {
    return false;
  }
  
  public int c()
  {
    return 375;
  }
  
  protected int d()
  {
    return this.b / 2 + 140;
  }
  
  protected void a()
  {
    this.A.c();
  }
  
  protected void a(int ☃, int ☃, bnu ☃)
  {
    if (!Mouse.isButtonDown(0)) {
      this.v = -1;
    }
    if (this.v == 0) {
      bfn.a(this.A, ☃ + 115 - 18, ☃ + 1, 0, 0);
    } else {
      bfn.a(this.A, ☃ + 115 - 18, ☃ + 1, 0, 18);
    }
    if (this.v == 1) {
      bfn.a(this.A, ☃ + 165 - 18, ☃ + 1, 0, 0);
    } else {
      bfn.a(this.A, ☃ + 165 - 18, ☃ + 1, 0, 18);
    }
    if (this.v == 2) {
      bfn.a(this.A, ☃ + 215 - 18, ☃ + 1, 0, 0);
    } else {
      bfn.a(this.A, ☃ + 215 - 18, ☃ + 1, 0, 18);
    }
    if (this.v == 3) {
      bfn.a(this.A, ☃ + 265 - 18, ☃ + 1, 0, 0);
    } else {
      bfn.a(this.A, ☃ + 265 - 18, ☃ + 1, 0, 18);
    }
    if (this.v == 4) {
      bfn.a(this.A, ☃ + 315 - 18, ☃ + 1, 0, 0);
    } else {
      bfn.a(this.A, ☃ + 315 - 18, ☃ + 1, 0, 18);
    }
    if (this.y != -1)
    {
      int ☃ = 79;
      int ☃ = 18;
      if (this.y == 1) {
        ☃ = 129;
      } else if (this.y == 2) {
        ☃ = 179;
      } else if (this.y == 3) {
        ☃ = 229;
      } else if (this.y == 4) {
        ☃ = 279;
      }
      if (this.z == 1) {
        ☃ = 36;
      }
      bfn.a(this.A, ☃ + ☃, ☃ + 1, ☃, 0);
    }
  }
  
  protected void a(int ☃, int ☃)
  {
    this.v = -1;
    if ((☃ >= 79) && (☃ < 115)) {
      this.v = 0;
    } else if ((☃ >= 129) && (☃ < 165)) {
      this.v = 1;
    } else if ((☃ >= 179) && (☃ < 215)) {
      this.v = 2;
    } else if ((☃ >= 229) && (☃ < 265)) {
      this.v = 3;
    } else if ((☃ >= 279) && (☃ < 315)) {
      this.v = 4;
    }
    if (this.v >= 0)
    {
      d(this.v);
      this.a.U().a(bye.a(ng.go, 1.0F));
    }
  }
  
  protected final int b()
  {
    return this.w.size();
  }
  
  protected final nn c(int ☃)
  {
    return (nn)this.w.get(☃);
  }
  
  protected abstract String b(int paramInt);
  
  protected void a(np ☃, int ☃, int ☃, boolean ☃)
  {
    if (☃ != null)
    {
      String ☃ = ☃.a(bfn.b(this.A).a(☃));
      this.A.c(bfn.e(this.A), ☃, ☃ - bfn.f(this.A).a(☃), ☃ + 5, ☃ ? 16777215 : 9474192);
    }
    else
    {
      String ☃ = "-";
      this.A.c(bfn.g(this.A), ☃, ☃ - bfn.h(this.A).a(☃), ☃ + 5, ☃ ? 16777215 : 9474192);
    }
  }
  
  protected void b(int ☃, int ☃)
  {
    if ((☃ < this.d) || (☃ > this.e)) {
      return;
    }
    int ☃ = c(☃, ☃);
    int ☃ = (this.b - c()) / 2;
    if (☃ >= 0)
    {
      if ((☃ < ☃ + 40) || (☃ > ☃ + 40 + 20)) {
        return;
      }
      nn ☃ = c(☃);
      a(☃, ☃, ☃);
    }
    else
    {
      String ☃ = "";
      if ((☃ >= ☃ + 115 - 18) && (☃ <= ☃ + 115)) {
        ☃ = b(0);
      } else if ((☃ >= ☃ + 165 - 18) && (☃ <= ☃ + 165)) {
        ☃ = b(1);
      } else if ((☃ >= ☃ + 215 - 18) && (☃ <= ☃ + 215)) {
        ☃ = b(2);
      } else if ((☃ >= ☃ + 265 - 18) && (☃ <= ☃ + 265)) {
        ☃ = b(3);
      } else if ((☃ >= ☃ + 315 - 18) && (☃ <= ☃ + 315)) {
        ☃ = b(4);
      } else {
        return;
      }
      ☃ = ("" + bwo.a(☃, new Object[0])).trim();
      if (!☃.isEmpty())
      {
        int ☃ = ☃ + 12;
        int ☃ = ☃ - 12;
        int ☃ = bfn.i(this.A).a(☃);
        bfn.a(this.A, ☃ - 3, ☃ - 3, ☃ + ☃ + 3, ☃ + 8 + 3, -1073741824, -1073741824);
        
        bfn.j(this.A).a(☃, ☃, ☃, -1);
      }
    }
  }
  
  protected void a(nn ☃, int ☃, int ☃)
  {
    if (☃ == null) {
      return;
    }
    ado ☃ = ☃.a();
    adq ☃ = new adq(☃);
    String ☃ = ☃.a();
    
    String ☃ = ("" + bwo.a(new StringBuilder().append(☃).append(".name").toString(), new Object[0])).trim();
    if (!☃.isEmpty())
    {
      int ☃ = ☃ + 12;
      int ☃ = ☃ - 12;
      int ☃ = bfn.k(this.A).a(☃);
      bfn.b(this.A, ☃ - 3, ☃ - 3, ☃ + ☃ + 3, ☃ + 8 + 3, -1073741824, -1073741824);
      
      bfn.l(this.A).a(☃, ☃, ☃, -1);
    }
  }
  
  protected void d(int ☃)
  {
    if (☃ != this.y)
    {
      this.y = ☃;
      this.z = -1;
    }
    else if (this.z == -1)
    {
      this.z = 1;
    }
    else
    {
      this.y = -1;
      this.z = 0;
    }
    Collections.sort(this.w, this.x);
  }
}
