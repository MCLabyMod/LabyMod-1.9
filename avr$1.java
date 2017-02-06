import java.util.List;
import java.util.Random;

final class avr$1
  implements avr.b
{
  public void a() {}
  
  public boolean a(int ☃, avr.a ☃, cj ☃, List<awg> ☃, Random ☃)
  {
    if (☃ > 8) {
      return false;
    }
    aoe ☃ = ☃.b.c();
    avr.a ☃;
    ☃.add(☃ = avr.a(☃, ☃, "base_floor", ☃, true));
    
    int ☃ = ☃.nextInt(3);
    if (☃ == 0)
    {
      ☃.add(avr.a(☃, new cj(-1, 4, -1), "base_roof", ☃, true));
    }
    else if (☃ == 1)
    {
      ☃.add(☃ = avr.a(☃, new cj(-1, 0, -1), "second_floor_2", ☃, false));
      ☃.add(☃ = avr.a(☃, new cj(-1, 8, -1), "second_roof", ☃, false));
      
      avr.a(avr.d(), ☃ + 1, ☃, null, ☃, ☃);
    }
    else if (☃ == 2)
    {
      ☃.add(☃ = avr.a(☃, new cj(-1, 0, -1), "second_floor_2", ☃, false));
      ☃.add(☃ = avr.a(☃, new cj(-1, 4, -1), "third_floor_c", ☃, false));
      ☃.add(☃ = avr.a(☃, new cj(-1, 8, -1), "third_roof", ☃, true));
      
      avr.a(avr.d(), ☃ + 1, ☃, null, ☃, ☃);
    }
    return true;
  }
}
