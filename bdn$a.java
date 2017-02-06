public class bdn$a
  implements bdl.a
{
  private final bcf a;
  private final bcz b;
  private final bcz c;
  
  public bdn$a(bcz ☃, bcz ☃)
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
