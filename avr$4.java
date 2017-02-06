import java.util.List;
import java.util.Random;

final class avr$4
  implements avr.b
{
  public void a() {}
  
  public boolean a(int ☃, avr.a ☃, cj ☃, List<awg> ☃, Random ☃)
  {
    aoe ☃ = ☃.b.c();
    avr.a ☃;
    ☃.add(☃ = avr.a(☃, new cj(-3, 4, -3), "fat_tower_base", ☃, true));
    ☃.add(☃ = avr.a(☃, new cj(0, 4, 0), "fat_tower_middle", ☃, true));
    for (int ☃ = 0; ☃ < 2; ☃++)
    {
      if (☃.nextInt(3) == 0) {
        break;
      }
      ☃.add(☃ = avr.a(☃, new cj(0, 8, 0), "fat_tower_middle", ☃, true));
      for (ou<aoe, cj> ☃ : avr.i()) {
        if (☃.nextBoolean())
        {
          avr.a ☃;
          ☃.add(☃ = avr.a(☃, (cj)☃.b(), "bridge_end", ☃.a((aoe)☃.a()), true));
          avr.a(avr.f(), ☃ + 1, ☃, null, ☃, ☃);
        }
      }
    }
    ☃.add(☃ = avr.a(☃, new cj(-2, 8, -2), "fat_tower_top", ☃, true));
    return true;
  }
}
