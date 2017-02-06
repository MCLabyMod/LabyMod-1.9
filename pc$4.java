final class pc$4
  implements pd
{
  public dn a(pa ☃, dn ☃, int ☃)
  {
    if (("Villager".equals(☃.l("id"))) && (☃.b("Offers", 10)))
    {
      dn ☃ = ☃.o("Offers");
      if (☃.b("Recipes", 9))
      {
        du ☃ = ☃.c("Recipes", 10);
        for (int ☃ = 0; ☃ < ☃.c(); ☃++)
        {
          dn ☃ = ☃.b(☃);
          
          pc.a(☃, ☃, ☃, "buy");
          pc.a(☃, ☃, ☃, "buyB");
          pc.a(☃, ☃, ☃, "sell");
          
          ☃.a(☃, ☃);
        }
      }
    }
    return ☃;
  }
}
