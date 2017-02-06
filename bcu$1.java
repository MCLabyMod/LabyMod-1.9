import com.google.common.base.Predicate;

class bcu$1
  implements Predicate<bbn>
{
  bcu$1(bcu this$0) {}
  
  public boolean a(bbn p_apply_1_)
  {
    return (p_apply_1_.e() != null) && (!p_apply_1_.e().startsWith("#"));
  }
}
