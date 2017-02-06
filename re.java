public class re
  extends rd
{
  private final rr t;
  
  public re(String ☃, rr ☃, rr ☃)
  {
    super(☃, ☃);
    this.t = ☃;
  }
  
  public rr i()
  {
    return this.s;
  }
  
  public rr j()
  {
    return this.t;
  }
  
  public eu c(sa ☃)
  {
    eu ☃ = this.t == null ? this.s.i_() : this.t.i_();
    adq ☃ = (this.t instanceof sa) ? ((sa)this.t).cb() : null;
    String ☃ = "death.attack." + this.r;
    String ☃ = ☃ + ".item";
    if ((☃ != null) && (☃.s()) && (di.c(☃))) {
      return new fb(☃, new Object[] { ☃.i_(), ☃, ☃.B() });
    }
    return new fb(☃, new Object[] { ☃.i_(), ☃ });
  }
}
