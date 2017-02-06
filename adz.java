import com.google.common.base.Function;

public class adz
  extends acc
{
  protected final ajt b;
  protected final Function<adq, String> c;
  
  public adz(ajt ☃, ajt ☃, Function<adq, String> ☃)
  {
    super(☃);
    
    this.b = ☃;
    this.c = ☃;
    
    e(0);
    a(true);
  }
  
  public adz(ajt ☃, ajt ☃, String[] ☃)
  {
    this(☃, ☃, new Function()
    {
      public String a(adq ☃)
      {
        int ☃ = ☃.i();
        if ((☃ < 0) || (☃ >= adz.this.length)) {
          ☃ = 0;
        }
        return adz.this[☃];
      }
    });
  }
  
  public int a(int ☃)
  {
    return ☃;
  }
  
  public String f_(adq ☃)
  {
    return super.a() + "." + (String)this.c.apply(☃);
  }
}
