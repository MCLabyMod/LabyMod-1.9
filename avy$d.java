import java.util.Random;

class avy$d
  implements avy.i
{
  public boolean a(avy.v ☃)
  {
    if ((☃.c[cq.c.a()] != 0) && (!☃.b[cq.c.a()].d) && 
      (☃.c[cq.b.a()] != 0) && (!☃.b[cq.b.a()].d))
    {
      avy.v ☃ = ☃.b[cq.c.a()];
      
      return (☃.c[cq.b.a()] != 0) && (!☃.b[cq.b.a()].d);
    }
    return false;
  }
  
  public avy.r a(cq ☃, avy.v ☃, Random ☃)
  {
    ☃.d = true;
    ☃.b[cq.c.a()].d = true;
    ☃.b[cq.b.a()].d = true;
    ☃.b[cq.c.a()].b[cq.b.a()].d = true;
    return new avy.n(☃, ☃, ☃);
  }
}
