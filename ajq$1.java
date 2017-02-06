final class ajq$1
  implements Runnable
{
  ajq$1(aht paramaht, cj paramcj) {}
  
  public void run()
  {
    ase ☃ = this.a.f(this.b);
    for (int ☃ = this.b.q() - 1; ☃ >= 0; ☃--)
    {
      final cj ☃ = new cj(this.b.p(), ☃, this.b.r());
      if (!☃.c(☃)) {
        break;
      }
      arc ☃ = this.a.o(☃);
      if (☃.t() == aju.bY) {
        ((lp)this.a).a(new Runnable()
        {
          public void run()
          {
            apv ☃ = ajq.1.this.a.r(☃);
            if ((☃ instanceof apu))
            {
              ((apu)☃).m();
              ajq.1.this.a.c(☃, aju.bY, 1, 0);
            }
          }
        });
      }
    }
  }
}
