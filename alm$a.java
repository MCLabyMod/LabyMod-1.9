import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.util.Collection;

public enum alm$a
  implements or
{
  private static final a[][] k;
  private final alm.b l;
  private final int m;
  private final String n;
  private final String o;
  
  static
  {
    k = new a[alm.b.values().length][];
    for (alm.b ☃ : alm.b.values())
    {
      Collection<a> ☃ = Collections2.filter(Lists.newArrayList(values()), new Predicate()
      {
        public boolean a(alm.a ☃)
        {
          return ☃.a() == this.a;
        }
      });
      k[☃.ordinal()] = ((a[])☃.toArray(new a[☃.size()]));
    }
  }
  
  private alm$a(alm.b ☃, int ☃, String ☃)
  {
    this(☃, ☃, ☃, ☃);
  }
  
  private alm$a(alm.b ☃, int ☃, String ☃, String ☃)
  {
    this.l = ☃;
    this.m = ☃;
    this.n = ☃;
    this.o = ☃;
  }
  
  public alm.b a()
  {
    return this.l;
  }
  
  public int b()
  {
    return this.m;
  }
  
  public static a a(alm.b ☃, int ☃)
  {
    a[] ☃ = k[☃.ordinal()];
    if ((☃ < 0) || (☃ >= ☃.length)) {
      ☃ = 0;
    }
    return ☃[☃];
  }
  
  public static a[] a(alm.b ☃)
  {
    return k[☃.ordinal()];
  }
  
  public String toString()
  {
    return this.n;
  }
  
  public String m()
  {
    return this.n;
  }
  
  public String d()
  {
    return this.o;
  }
}
