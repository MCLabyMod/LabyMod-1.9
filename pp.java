public class pp
  implements ox
{
  public int a()
  {
    return 107;
  }
  
  public dn a(dn ☃)
  {
    if (!"MobSpawner".equals(☃.l("id"))) {
      return ☃;
    }
    if (☃.b("EntityId", 8))
    {
      String ☃ = ☃.l("EntityId");
      
      dn ☃ = ☃.o("SpawnData");
      ☃.a("id", ☃.isEmpty() ? "Pig" : ☃);
      ☃.a("SpawnData", ☃);
      
      ☃.q("EntityId");
    }
    if (☃.b("SpawnPotentials", 9))
    {
      du ☃ = ☃.c("SpawnPotentials", 10);
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        dn ☃ = ☃.b(☃);
        if (☃.b("Type", 8))
        {
          dn ☃ = ☃.o("Properties");
          ☃.a("id", ☃.l("Type"));
          ☃.a("Entity", ☃);
          
          ☃.q("Type");
          ☃.q("Properties");
        }
      }
    }
    return ☃;
  }
}
