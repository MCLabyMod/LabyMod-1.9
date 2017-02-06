import com.google.common.collect.Lists;
import java.util.List;

public class bve$b
{
  private final int a;
  private final int b;
  private final int c;
  private final int d;
  private List<b> e;
  private bve.a f;
  
  public bve$b(int originXIn, int originYIn, int widthIn, int heightIn)
  {
    this.a = originXIn;
    this.b = originYIn;
    this.c = widthIn;
    this.d = heightIn;
  }
  
  public bve.a a()
  {
    return this.f;
  }
  
  public int b()
  {
    return this.a;
  }
  
  public int c()
  {
    return this.b;
  }
  
  public boolean a(bve.a holderIn)
  {
    if (this.f != null) {
      return false;
    }
    int i = holderIn.b();
    int j = holderIn.c();
    if ((i <= this.c) && (j <= this.d))
    {
      if ((i == this.c) && (j == this.d))
      {
        this.f = holderIn;
        return true;
      }
      if (this.e == null)
      {
        this.e = Lists.newArrayListWithCapacity(1);
        this.e.add(new b(this.a, this.b, i, j));
        int k = this.c - i;
        int l = this.d - j;
        if ((l > 0) && (k > 0))
        {
          int i1 = Math.max(this.d, k);
          int j1 = Math.max(this.c, l);
          if (i1 >= j1)
          {
            this.e.add(new b(this.a, this.b + j, i, l));
            this.e.add(new b(this.a + i, this.b, k, this.d));
          }
          else
          {
            this.e.add(new b(this.a + i, this.b, k, j));
            this.e.add(new b(this.a, this.b + j, this.c, l));
          }
        }
        else if (k == 0)
        {
          this.e.add(new b(this.a, this.b + j, i, l));
        }
        else if (l == 0)
        {
          this.e.add(new b(this.a + i, this.b, k, j));
        }
      }
      for (b stitcher$slot : this.e) {
        if (stitcher$slot.a(holderIn)) {
          return true;
        }
      }
      return false;
    }
    return false;
  }
  
  public void a(List<b> p_94184_1_)
  {
    if (this.f != null) {
      p_94184_1_.add(this);
    } else if (this.e != null) {
      for (b stitcher$slot : this.e) {
        stitcher$slot.a(p_94184_1_);
      }
    }
  }
  
  public String toString()
  {
    return "Slot{originX=" + this.a + ", originY=" + this.b + ", width=" + this.c + ", height=" + this.d + ", texture=" + this.f + ", subSlots=" + this.e + '}';
  }
}
