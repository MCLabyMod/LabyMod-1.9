import com.google.common.base.Predicate;

public abstract interface yl
  extends rq
{
  public static final Predicate<rr> d = new Predicate()
  {
    public boolean a(rr ☃)
    {
      return ☃ instanceof yl;
    }
  };
  public static final Predicate<rr> e = new Predicate()
  {
    public boolean a(rr ☃)
    {
      return ((☃ instanceof yl)) && (!☃.aN());
    }
  };
}
