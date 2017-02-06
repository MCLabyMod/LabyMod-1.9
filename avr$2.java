import java.util.List;
import java.util.Random;

final class avr$2
  implements avr.b
{
  public void a() {}
  
  public boolean a(int ☃, avr.a ☃, cj ☃, List<awg> ☃, Random ☃)
  {
    aoe ☃ = ☃.b.c();
    avr.a ☃ = ☃;
    
    ☃.add(☃ = avr.a(☃, new cj(3 + ☃.nextInt(2), -3, 3 + ☃.nextInt(2)), "tower_base", ☃, true));
    ☃.add(☃ = avr.a(☃, new cj(0, 7, 0), "tower_piece", ☃, true));
    
    avr.a ☃ = ☃.nextInt(3) == 0 ? ☃ : null;
    
    int ☃ = 1 + ☃.nextInt(3);
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      ☃.add(☃ = avr.a(☃, new cj(0, 4, 0), "tower_piece", ☃, true));
      if ((☃ < ☃ - 1) && (☃.nextBoolean())) {
        ☃ = ☃;
      }
    }
    if (☃ != null)
    {
      for (ou<aoe, cj> ☃ : avr.e()) {
        if (☃.nextBoolean())
        {
          avr.a ☃;
          ☃.add(☃ = avr.a(☃, (cj)☃.b(), "bridge_end", ☃.a((aoe)☃.a()), true));
          avr.a(avr.f(), ☃ + 1, ☃, null, ☃, ☃);
        }
      }
      ☃.add(☃ = avr.a(☃, new cj(-1, 4, -1), "tower_top", ☃, true));
    }
    else if (☃ == 7)
    {
      ☃.add(☃ = avr.a(☃, new cj(-1, 4, -1), "tower_top", ☃, true));
    }
    else
    {
      return avr.a(avr.g(), ☃ + 1, ☃, null, ☃, ☃);
    }
    return true;
  }
}
