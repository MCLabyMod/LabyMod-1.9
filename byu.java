import java.util.Random;

public class byu
  implements ky
{
  private final Random a = new Random();
  private final bcf b;
  private byi c;
  private int d = 100;
  
  public byu(bcf ☃)
  {
    this.b = ☃;
  }
  
  public void c()
  {
    byu.a ☃ = this.b.V();
    if (this.c != null)
    {
      if (!☃.a().a().equals(this.c.a()))
      {
        this.b.U().b(this.c);
        this.d = on.a(this.a, 0, ☃.b() / 2);
      }
      if (!this.b.U().c(this.c))
      {
        this.c = null;
        this.d = Math.min(on.a(this.a, ☃.b(), ☃.c()), this.d);
      }
    }
    this.d = Math.min(this.d, ☃.c());
    if ((this.c == null) && (this.d-- <= 0)) {
      a(☃);
    }
  }
  
  public void a(byu.a ☃)
  {
    this.c = bye.a(☃.a());
    this.b.U().a(this.c);
    this.d = Integer.MAX_VALUE;
  }
  
  public void a()
  {
    if (this.c != null)
    {
      this.b.U().b(this.c);
      this.c = null;
      this.d = 0;
    }
  }
  
  public static enum a
  {
    private final nf h;
    private final int i;
    private final int j;
    
    private a(nf ☃, int ☃, int ☃)
    {
      this.h = ☃;
      this.i = ☃;
      this.j = ☃;
    }
    
    public nf a()
    {
      return this.h;
    }
    
    public int b()
    {
      return this.i;
    }
    
    public int c()
    {
      return this.j;
    }
  }
}
