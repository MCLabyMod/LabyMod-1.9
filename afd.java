import java.util.List;

public class afd
  extends ado
{
  public afd()
  {
    d(1);
  }
  
  public static boolean b(dn ☃)
  {
    if (!afc.b(☃)) {
      return false;
    }
    if (!☃.b("title", 8)) {
      return false;
    }
    String ☃ = ☃.l("title");
    if ((☃ == null) || (☃.length() > 32)) {
      return false;
    }
    return ☃.b("author", 8);
  }
  
  public static int h(adq ☃)
  {
    return ☃.o().h("generation");
  }
  
  public String a(adq ☃)
  {
    if (☃.n())
    {
      dn ☃ = ☃.o();
      
      String ☃ = ☃.l("title");
      if (!os.b(☃)) {
        return ☃;
      }
    }
    return super.a(☃);
  }
  
  public void a(adq ☃, zj ☃, List<String> ☃, boolean ☃)
  {
    if (☃.n())
    {
      dn ☃ = ☃.o();
      
      String ☃ = ☃.l("author");
      if (!os.b(☃)) {
        ☃.add(a.h + di.a("book.byAuthor", new Object[] { ☃ }));
      }
      ☃.add(a.h + di.a(new StringBuilder().append("book.generation.").append(☃.h("generation")).toString()));
    }
  }
  
  public qp<adq> a(adq ☃, aht ☃, zj ☃, qm ☃)
  {
    if (!☃.E) {
      a(☃, ☃);
    }
    ☃.a(☃, ☃);
    ☃.b(nt.b(this));
    return new qp(qo.a, ☃);
  }
  
  private void a(adq ☃, zj ☃)
  {
    if ((☃ == null) || (☃.o() == null)) {
      return;
    }
    dn ☃ = ☃.o();
    if (☃.p("resolved")) {
      return;
    }
    ☃.a("resolved", true);
    if (!b(☃)) {
      return;
    }
    du ☃ = ☃.c("pages", 8);
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      String ☃ = ☃.g(☃);
      eu ☃;
      try
      {
        ☃ = eu.a.b(☃);
        ☃ = ev.a(☃, ☃, ☃);
      }
      catch (Exception ☃)
      {
        ☃ = new fa(☃);
      }
      ☃.a(☃, new ea(eu.a.a(☃)));
    }
    ☃.a("pages", ☃);
    if (((☃ instanceof lr)) && (☃.cb() == ☃))
    {
      abt ☃ = ☃.bt.a(☃.br, ☃.br.d);
      ((lr)☃).a.a(new gf(0, ☃.e, ☃));
    }
  }
  
  public boolean i_(adq ☃)
  {
    return true;
  }
}
