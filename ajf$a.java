import com.google.common.cache.CacheLoader;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class ajf$a
  extends CacheLoader<Long, avb.a[]>
{
  public avb.a[] a(Long ☃)
    throws Exception
  {
    List<Integer> ☃ = Lists.newArrayList(ContiguousSet.create(Range.closedOpen(Integer.valueOf(0), Integer.valueOf(10)), DiscreteDomain.integers()));
    Collections.shuffle(☃, new Random(☃.longValue()));
    
    avb.a[] ☃ = new avb.a[10];
    for (int ☃ = 0; ☃ < 10; ☃++)
    {
      int ☃ = (int)(42.0D * Math.cos(2.0D * (-3.141592653589793D + 0.3141592653589793D * ☃)));
      int ☃ = (int)(42.0D * Math.sin(2.0D * (-3.141592653589793D + 0.3141592653589793D * ☃)));
      int ☃ = ((Integer)☃.get(☃)).intValue();
      int ☃ = 2 + ☃ / 3;
      int ☃ = 76 + ☃ * 3;
      boolean ☃ = (☃ == 1) || (☃ == 2);
      ☃[☃] = new avb.a(☃, ☃, ☃, ☃, ☃);
    }
    return ☃;
  }
}
