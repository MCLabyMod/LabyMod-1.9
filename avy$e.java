import java.util.Random;

class avy$e
  implements avy.i
{
  public boolean a(avy.v ☃)
  {
    if ((☃.c[cq.c.a()] != 0) && (!☃.b[cq.c.a()].d)) {
      return true;
    }
    return false;
  }
  
  public avy.r a(cq ☃, avy.v ☃, Random ☃)
  {
    avy.v ☃ = ☃;
    if ((☃.c[cq.c.a()] == 0) || (☃.b[cq.c.a()].d)) {
      ☃ = ☃.b[cq.d.a()];
    }
    ☃.d = true;
    ☃.b[cq.c.a()].d = true;
    return new avy.o(☃, ☃, ☃);
  }
}
