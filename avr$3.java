import java.util.List;
import java.util.Random;

final class avr$3
  implements avr.b
{
  public boolean a;
  
  public void a()
  {
    this.a = false;
  }
  
  public boolean a(int ☃, avr.a ☃, cj ☃, List<awg> ☃, Random ☃)
  {
    aoe ☃ = ☃.b.c();
    int ☃ = ☃.nextInt(4) + 1;
    avr.a ☃;
    ☃.add(☃ = avr.a(☃, new cj(0, 0, -4), "bridge_piece", ☃, true));
    ☃.m = -1;
    int ☃ = 0;
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      if (☃.nextBoolean())
      {
        ☃.add(☃ = avr.a(☃, new cj(0, ☃, -4), "bridge_piece", ☃, true));
        ☃ = 0;
      }
      else
      {
        if (☃.nextBoolean()) {
          ☃.add(☃ = avr.a(☃, new cj(0, ☃, -4), "bridge_steep_stairs", ☃, true));
        } else {
          ☃.add(☃ = avr.a(☃, new cj(0, ☃, -8), "bridge_gentle_stairs", ☃, true));
        }
        ☃ = 4;
      }
    }
    if ((this.a) || (☃.nextInt(10 - ☃) != 0))
    {
      if (!avr.a(avr.h(), ☃ + 1, ☃, new cj(-3, ☃ + 1, -11), ☃, ☃)) {
        return false;
      }
    }
    else
    {
      ☃.add(avr.a(☃, new cj(-8 + ☃.nextInt(8), ☃, -70 + ☃.nextInt(10)), "ship", ☃, true));
      this.a = true;
    }
    ☃.add(☃ = avr.a(☃, new cj(4, ☃, 0), "bridge_end", ☃.a(aoe.c), true));
    ☃.m = -1;
    
    return true;
  }
}
