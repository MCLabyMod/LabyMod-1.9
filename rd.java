public class rd
  extends rc
{
  protected rr s;
  private boolean t = false;
  
  public rd(String ☃, rr ☃)
  {
    super(☃);
    this.s = ☃;
  }
  
  public rd w()
  {
    this.t = true;
    return this;
  }
  
  public boolean x()
  {
    return this.t;
  }
  
  public rr j()
  {
    return this.s;
  }
  
  public eu c(sa ☃)
  {
    adq ☃ = (this.s instanceof sa) ? ((sa)this.s).cb() : null;
    String ☃ = "death.attack." + this.r;
    String ☃ = ☃ + ".item";
    if ((☃ != null) && (☃.s()) && (di.c(☃))) {
      return new fb(☃, new Object[] { ☃.i_(), this.s.i_(), ☃.B() });
    }
    return new fb(☃, new Object[] { ☃.i_(), this.s.i_() });
  }
  
  public boolean r()
  {
    return (this.s != null) && ((this.s instanceof sa)) && (!(this.s instanceof zj));
  }
  
  public bbj v()
  {
    return new bbj(this.s.p, this.s.q, this.s.r);
  }
}
