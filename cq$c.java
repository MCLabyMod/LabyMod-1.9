import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import java.util.Iterator;
import java.util.Random;

public enum cq$c
  implements Predicate<cq>, Iterable<cq>
{
  private cq$c() {}
  
  public cq[] a()
  {
    switch (cq.1.$SwitchMap$net$minecraft$util$EnumFacing$Plane[ordinal()])
    {
    case 1: 
      return new cq[] { cq.c, cq.f, cq.d, cq.e };
    case 2: 
      return new cq[] { cq.b, cq.a };
    }
    throw new Error("Someone's been tampering with the universe!");
  }
  
  public cq a(Random rand)
  {
    cq[] aenumfacing = a();
    return aenumfacing[rand.nextInt(aenumfacing.length)];
  }
  
  public boolean a(cq p_apply_1_)
  {
    return (p_apply_1_ != null) && (p_apply_1_.k().d() == this);
  }
  
  public Iterator<cq> iterator()
  {
    return Iterators.forArray(a());
  }
}
