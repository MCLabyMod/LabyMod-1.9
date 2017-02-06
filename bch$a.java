public enum bch$a
{
  private final boolean L;
  private final boolean M;
  private final String N;
  private final float O;
  private float P;
  private float Q;
  
  public static a a(int ordinal)
  {
    for (a gamesettings$options : ) {
      if (gamesettings$options.c() == ordinal) {
        return gamesettings$options;
      }
    }
    return null;
  }
  
  private bch$a(String str, boolean isFloat, boolean isBoolean)
  {
    this(str, isFloat, isBoolean, 0.0F, 1.0F, 0.0F);
  }
  
  private bch$a(String str, boolean isFloat, boolean isBoolean, float valMin, float valMax, float valStep)
  {
    this.N = str;
    this.L = isFloat;
    this.M = isBoolean;
    this.P = valMin;
    this.Q = valMax;
    this.O = valStep;
  }
  
  public boolean a()
  {
    return this.L;
  }
  
  public boolean b()
  {
    return this.M;
  }
  
  public int c()
  {
    return ordinal();
  }
  
  public String d()
  {
    return this.N;
  }
  
  public float e()
  {
    return this.P;
  }
  
  public float f()
  {
    return this.Q;
  }
  
  public void a(float value)
  {
    this.Q = value;
  }
  
  public float c(float value)
  {
    return on.a((e(value) - this.P) / (this.Q - this.P), 0.0F, 1.0F);
  }
  
  public float d(float value)
  {
    return e(this.P + (this.Q - this.P) * on.a(value, 0.0F, 1.0F));
  }
  
  public float e(float value)
  {
    value = f(value);
    return on.a(value, this.P, this.Q);
  }
  
  protected float f(float value)
  {
    if (this.O > 0.0F) {
      value = this.O * Math.round(value / this.O);
    }
    return value;
  }
}
