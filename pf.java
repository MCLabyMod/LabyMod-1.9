public class pf
  implements ox
{
  public int a()
  {
    return 100;
  }
  
  public dn a(dn ☃)
  {
    du ☃ = ☃.c("Equipment", 10);
    if ((☃.c() > 0) && (!☃.b("HandItems", 10)))
    {
      du ☃ = new du();
      
      ☃.a(☃.h(0));
      ☃.a(new dn());
      ☃.a("HandItems", ☃);
    }
    if ((☃.c() > 1) && (!☃.b("ArmorItem", 10)))
    {
      du ☃ = new du();
      
      ☃.a(☃.b(1));
      ☃.a(☃.b(2));
      ☃.a(☃.b(3));
      ☃.a(☃.b(4));
      ☃.a("ArmorItems", ☃);
    }
    ☃.q("Equipment");
    if (☃.b("DropChances", 9))
    {
      du ☃ = ☃.c("DropChances", 5);
      if (!☃.b("HandDropChances", 10))
      {
        du ☃ = new du();
        
        ☃.a(new dr(☃.f(0)));
        ☃.a(new dr(0.0F));
        ☃.a("HandDropChances", ☃);
      }
      if (!☃.b("ArmorDropChances", 10))
      {
        du ☃ = new du();
        
        ☃.a(new dr(☃.f(1)));
        ☃.a(new dr(☃.f(2)));
        ☃.a(new dr(☃.f(3)));
        ☃.a(new dr(☃.f(4)));
        ☃.a("ArmorDropChances", ☃);
      }
      ☃.q("DropChances");
    }
    return ☃;
  }
}
