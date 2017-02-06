public class axe
{
  public static final axe a = new axc(axf.b);
  public static final axe b = new axe(axf.c);
  public static final axe c = new axe(axf.l);
  public static final axe d = new axe(axf.o).g();
  public static final axe e = new axe(axf.m).f();
  public static final axe f = new axe(axf.h).f();
  public static final axe g = new axe(axf.h).f().o();
  public static final axe h = new axd(axf.n).n();
  public static final axe i = new axd(axf.f).n();
  public static final axe j = new axe(axf.i).g().s().n();
  public static final axe k = new axb(axf.i).n();
  public static final axe l = new axb(axf.i).g().n().i();
  public static final axe m = new axe(axf.t);
  public static final axe n = new axe(axf.e).g();
  public static final axe o = new axc(axf.b).n();
  public static final axe p = new axe(axf.d);
  public static final axe q = new axb(axf.b).n();
  public static final axe r = new axb(axf.e).g();
  public static final axe s = new axe(axf.b).s().p();
  public static final axe t = new axe(axf.b).p();
  public static final axe u = new axe(axf.f).g().s();
  public static final axe v = new axe(axf.i).n();
  public static final axe w = new axe(axf.g).s().p();
  public static final axe x = new axe(axf.g).p();
  public static final axe y = new axb(axf.j).i().s().f().n();
  public static final axe z = new axe(axf.j).f();
  public static final axe A = new axe(axf.i).s().n();
  public static final axe B = new axe(axf.k);
  public static final axe C = new axe(axf.i).n();
  public static final axe D = new axe(axf.i).n();
  public static final axe E = new axg(axf.b).o();
  public static final axe F = new axe(axf.b).n();
  public static final axe G = new axe(axf.e)
  {
    public boolean c()
    {
      return false;
    }
  }.f().n();
  public static final axe H = new axe(axf.m).o();
  public static final axe I = new axe(axf.b).f().o();
  private boolean J;
  private boolean K;
  private boolean L;
  private final axf M;
  private boolean N = true;
  private axh O = axh.a;
  private boolean P;
  
  public axe(axf ☃)
  {
    this.M = ☃;
  }
  
  public boolean d()
  {
    return false;
  }
  
  public boolean a()
  {
    return true;
  }
  
  public boolean b()
  {
    return true;
  }
  
  public boolean c()
  {
    return true;
  }
  
  private axe s()
  {
    this.L = true;
    return this;
  }
  
  protected axe f()
  {
    this.N = false;
    return this;
  }
  
  protected axe g()
  {
    this.J = true;
    return this;
  }
  
  public boolean h()
  {
    return this.J;
  }
  
  public axe i()
  {
    this.K = true;
    return this;
  }
  
  public boolean j()
  {
    return this.K;
  }
  
  public boolean k()
  {
    if (this.L) {
      return false;
    }
    return c();
  }
  
  public boolean l()
  {
    return this.N;
  }
  
  public axh m()
  {
    return this.O;
  }
  
  protected axe n()
  {
    this.O = axh.b;
    return this;
  }
  
  protected axe o()
  {
    this.O = axh.c;
    return this;
  }
  
  protected axe p()
  {
    this.P = true;
    return this;
  }
  
  public axf r()
  {
    return this.M;
  }
}
