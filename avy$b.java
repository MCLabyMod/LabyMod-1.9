import java.util.Random;

class avy$b
  implements avy.i
{
  public boolean a(avy.v ☃)
  {
    if ((☃.c[cq.f.a()] != 0) && (!☃.b[cq.f.a()].d) && 
      (☃.c[cq.b.a()] != 0) && (!☃.b[cq.b.a()].d))
    {
      avy.v ☃ = ☃.b[cq.f.a()];
      
      return (☃.c[cq.b.a()] != 0) && (!☃.b[cq.b.a()].d);
    }
    return false;
  }
  
  public avy.r a(cq ☃, avy.v ☃, Random ☃)
  {
    ☃.d = true;
    ☃.b[cq.f.a()].d = true;
    ☃.b[cq.b.a()].d = true;
    ☃.b[cq.f.a()].b[cq.b.a()].d = true;
    return new avy.l(☃, ☃, ☃);
  }
}
