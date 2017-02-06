import java.util.List;

public class bff
  extends bfb
{
  private final bfb f;
  private final bch g;
  protected String a = "Options";
  private String h;
  
  public bff(bfb ☃, bch ☃)
  {
    this.f = ☃;
    this.g = ☃;
  }
  
  public void b()
  {
    int ☃ = 0;
    this.a = bwo.a("options.sounds.title", new Object[0]);
    this.h = bwo.a("options.off", new Object[0]);
    
    this.n.add(new bff.a(nh.a.ordinal(), this.l / 2 - 155 + ☃ % 2 * 160, this.m / 6 - 12 + 24 * (☃ >> 1), nh.a, true));
    ☃ += 2;
    for (nh ☃ : nh.values()) {
      if (☃ != nh.a)
      {
        this.n.add(new bff.a(☃.ordinal(), this.l / 2 - 155 + ☃ % 2 * 160, this.m / 6 - 12 + 24 * (☃ >> 1), ☃, false));
        
        ☃++;
      }
    }
    this.n.add(new bdm(201, this.l / 2 - 75, this.m / 6 - 12 + 24 * (++☃ >> 1), bch.a.J, this.g.c(bch.a.J)));
    this.n.add(new bcz(200, this.l / 2 - 100, this.m / 6 + 168, bwo.a("gui.done", new Object[0])));
  }
  
  protected void a(bcz ☃)
  {
    if (!☃.l) {
      return;
    }
    if (☃.k == 200)
    {
      this.j.u.b();
      this.j.a(this.f);
    }
    else if (☃.k == 201)
    {
      this.j.u.a(bch.a.J, 1);
      ☃.j = this.j.u.c(bch.a.J);
      this.j.u.b();
    }
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    c();
    a(this.q, this.a, this.l / 2, 15, 16777215);
    super.a(☃, ☃, ☃);
  }
  
  protected String a(nh ☃)
  {
    float ☃ = this.g.a(☃);
    if (☃ == 0.0F) {
      return this.h;
    }
    return (int)(☃ * 100.0F) + "%";
  }
  
  class a
    extends bcz
  {
    private final nh r;
    private final String s;
    public float o = 1.0F;
    public boolean p;
    
    public a(int ☃, int ☃, int ☃, nh ☃, boolean ☃)
    {
      super(☃, ☃, ☃ ? 310 : 150, 20, "");
      this.r = ☃;
      this.s = bwo.a("soundCategory." + ☃.a(), new Object[0]);
      this.j = (this.s + ": " + bff.this.a(☃));
      this.o = bff.a(bff.this).a(☃);
    }
    
    protected int a(boolean ☃)
    {
      return 0;
    }
    
    protected void b(bcf ☃, int ☃, int ☃)
    {
      if (!this.m) {
        return;
      }
      if (this.p)
      {
        this.o = ((☃ - (this.h + 4)) / (this.f - 8));
        this.o = on.a(this.o, 0.0F, 1.0F);
        ☃.u.a(this.r, this.o);
        ☃.u.b();
        this.j = (this.s + ": " + bff.this.a(this.r));
      }
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      b(this.h + (int)(this.o * (this.f - 8)), this.i, 0, 66, 4, 20);
      b(this.h + (int)(this.o * (this.f - 8)) + 4, this.i, 196, 66, 4, 20);
    }
    
    public boolean c(bcf ☃, int ☃, int ☃)
    {
      if (super.c(☃, ☃, ☃))
      {
        this.o = ((☃ - (this.h + 4)) / (this.f - 8));
        this.o = on.a(this.o, 0.0F, 1.0F);
        ☃.u.a(this.r, this.o);
        ☃.u.b();
        this.j = (this.s + ": " + bff.this.a(this.r));
        this.p = true;
        return true;
      }
      return false;
    }
    
    public void a(byx ☃) {}
    
    public void a(int ☃, int ☃)
    {
      if (this.p) {
        bff.this.j.U().a(bye.a(ng.go, 1.0F));
      }
      this.p = false;
    }
  }
}
