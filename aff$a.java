import com.google.common.base.Predicate;

class aff$a
  implements Predicate<adq>
{
  private final ado a;
  private final int b;
  
  public aff$a(ado ☃)
  {
    this(☃, -1);
  }
  
  public aff$a(ado ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public boolean a(adq ☃)
  {
    return (☃ != null) && (☃.b() == this.a) && ((this.b == -1) || (this.b == ☃.i()));
  }
}
