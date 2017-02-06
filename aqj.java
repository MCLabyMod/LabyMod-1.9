public abstract class aqj
  extends apv
  implements qn, qs
{
  private qr a = qr.a;
  
  public void a(dn ☃)
  {
    super.a(☃);
    
    this.a = qr.b(☃);
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    if (this.a != null) {
      this.a.a(☃);
    }
  }
  
  public boolean x_()
  {
    return (this.a != null) && (!this.a.a());
  }
  
  public qr y_()
  {
    return this.a;
  }
  
  public void a(qr ☃)
  {
    this.a = ☃;
  }
  
  public eu i_()
  {
    if (o_()) {
      return new fa(h_());
    }
    return new fb(h_(), new Object[0]);
  }
}
