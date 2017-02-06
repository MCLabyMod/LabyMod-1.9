import com.google.common.collect.Lists;
import java.util.List;

public class bdn
  extends bdl
{
  private final List<bdn.a> u = Lists.newArrayList();
  
  public bdn(bcf ☃, int ☃, int ☃, int ☃, int ☃, int ☃, bch.a... ☃)
  {
    super(☃, ☃, ☃, ☃, ☃, ☃);
    this.k = false;
    for (int ☃ = 0; ☃ < ☃.length; ☃ += 2)
    {
      bch.a ☃ = ☃[☃];
      bch.a ☃ = ☃ < ☃.length - 1 ? ☃[(☃ + 1)] : null;
      bcz ☃ = a(☃, ☃ / 2 - 155, 0, ☃);
      bcz ☃ = a(☃, ☃ / 2 - 155 + 160, 0, ☃);
      
      this.u.add(new bdn.a(☃, ☃));
    }
  }
  
  private bcz a(bcf ☃, int ☃, int ☃, bch.a ☃)
  {
    if (☃ == null) {
      return null;
    }
    int ☃ = ☃.c();
    return ☃.a() ? new bdr(☃, ☃, ☃, ☃) : new bdm(☃, ☃, ☃, ☃, ☃.u.c(☃));
  }
  
  public bdn.a c(int ☃)
  {
    return (bdn.a)this.u.get(☃);
  }
  
  protected int b()
  {
    return this.u.size();
  }
  
  public int c()
  {
    return 400;
  }
  
  protected int d()
  {
    return super.d() + 32;
  }
  
  public static class a
    implements bdl.a
  {
    private final bcf a;
    private final bcz b;
    private final bcz c;
    
    public a(bcz ☃, bcz ☃)
    {
      this.a = bcf.z();
      this.b = ☃;
      this.c = ☃;
    }
    
    public void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, boolean ☃)
    {
      if (this.b != null)
      {
        this.b.i = ☃;
        this.b.a(this.a, ☃, ☃);
      }
      if (this.c != null)
      {
        this.c.i = ☃;
        this.c.a(this.a, ☃, ☃);
      }
    }
    
    public boolean a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
    {
      if (this.b.c(this.a, ☃, ☃))
      {
        if ((this.b instanceof bdm))
        {
          this.a.u.a(((bdm)this.b).c(), 1);
          this.b.j = this.a.u.c(bch.a.a(this.b.k));
        }
        return true;
      }
      if ((this.c != null) && (this.c.c(this.a, ☃, ☃)))
      {
        if ((this.c instanceof bdm))
        {
          this.a.u.a(((bdm)this.c).c(), 1);
          this.c.j = this.a.u.c(bch.a.a(this.c.k));
        }
        return true;
      }
      return false;
    }
    
    public void b(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
    {
      if (this.b != null) {
        this.b.a(☃, ☃);
      }
      if (this.c != null) {
        this.c.a(☃, ☃);
      }
    }
    
    public void a(int ☃, int ☃, int ☃) {}
  }
}
