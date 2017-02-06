import com.google.common.util.concurrent.ListeningExecutorService;

public class ajq
  extends ajn
{
  public ajq()
  {
    super(axe.s, axf.G);
    c(3.0F);
    a(acq.f);
  }
  
  public apv a(aht ☃, int ☃)
  {
    return new apu();
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (☃.E) {
      return true;
    }
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof apu))
    {
      ☃.a((apu)☃);
      ☃.b(nt.P);
    }
    return true;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public aob a(arc ☃)
  {
    return aob.d;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, sa ☃, adq ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃);
    if (☃.s())
    {
      apv ☃ = ☃.r(☃);
      if ((☃ instanceof apu)) {
        ((apu)☃).a(☃.q());
      }
    }
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof apu))
    {
      ((apu)☃).m();
      ☃.c(☃, this, 1, 0);
    }
  }
  
  public ahm f()
  {
    return ahm.c;
  }
  
  public static void c(aht ☃, final cj ☃)
  {
    oe.a.submit(new Runnable()
    {
      public void run()
      {
        ase ☃ = this.a.f(☃);
        for (int ☃ = ☃.q() - 1; ☃ >= 0; ☃--)
        {
          final cj ☃ = new cj(☃.p(), ☃, ☃.r());
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
    });
  }
}
