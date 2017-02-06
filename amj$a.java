public class amj$a
  extends apv
{
  private adq a;
  
  public void a(dn ☃)
  {
    super.a(☃);
    if (☃.b("RecordItem", 10)) {
      a(adq.a(☃.o("RecordItem")));
    } else if (☃.h("Record") > 0) {
      a(new adq(ado.c(☃.h("Record"))));
    }
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    if (a() != null) {
      ☃.a("RecordItem", a().b(new dn()));
    }
  }
  
  public adq a()
  {
    return this.a;
  }
  
  public void a(adq ☃)
  {
    this.a = ☃;
    v_();
  }
}
