import com.google.common.base.Predicate;

class uy$1
  implements Predicate<T>
{
  uy$1(uy paramuy, Predicate paramPredicate) {}
  
  public boolean a(T ☃)
  {
    if (☃ == null) {
      return false;
    }
    if ((this.a != null) && (!this.a.apply(☃))) {
      return false;
    }
    if (!rv.e.apply(☃)) {
      return false;
    }
    return this.b.a(☃, false);
  }
}
