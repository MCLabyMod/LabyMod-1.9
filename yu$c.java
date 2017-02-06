import com.google.common.base.Predicate;

class yu$c
  extends uy<sa>
{
  public yu$c(yu ☃)
  {
    super(☃, sa.class, 10, true, false, new Predicate()
    {
      public boolean a(sa ☃)
      {
        return ☃ instanceof yl;
      }
    });
  }
  
  public boolean a()
  {
    if (this.e.aO() == null) {
      return false;
    }
    return super.a();
  }
  
  protected bbh a(double ☃)
  {
    cq ☃ = ((yu)this.e).cZ();
    if (☃.k() == cq.a.a) {
      return this.e.bl().b(4.0D, ☃, ☃);
    }
    if (☃.k() == cq.a.c) {
      return this.e.bl().b(☃, ☃, 4.0D);
    }
    return this.e.bl().b(☃, 4.0D, ☃);
  }
}
