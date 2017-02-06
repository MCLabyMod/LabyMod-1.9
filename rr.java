import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class rr
  implements m
{
  private static final Logger a = ;
  private static final bbh b = new bbh(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
  private static double c = 1.0D;
  private static int f;
  private int g = f++;
  public boolean i;
  private final List<rr> h = Lists.newArrayList();
  protected int j;
  private rr as;
  public boolean k;
  public aht l;
  public double m;
  public double n;
  public double o;
  public double p;
  public double q;
  public double r;
  public double s;
  public double t;
  public double u;
  public float v;
  public float w;
  public float x;
  public float y;
  private bbh at = b;
  public boolean z;
  public boolean A;
  public boolean B;
  public boolean C;
  public boolean D;
  protected boolean E;
  private boolean au;
  public boolean F;
  public float G = 0.6F;
  public float H = 1.8F;
  public float I;
  public float J;
  public float K;
  public float L;
  private int av = 1;
  public double M;
  public double N;
  public double O;
  public float P;
  public boolean Q;
  public float R;
  protected Random S = new Random();
  public int T;
  public int U = 1;
  private int aw;
  protected boolean V;
  public int W;
  protected boolean X = true;
  protected boolean Y;
  protected kh Z;
  private static final ke<Byte> ax = kh.a(rr.class, kg.a);
  private static final ke<Integer> ay = kh.a(rr.class, kg.b);
  private static final ke<String> az = kh.a(rr.class, kg.d);
  private static final ke<Boolean> aA = kh.a(rr.class, kg.h);
  private static final ke<Boolean> aB = kh.a(rr.class, kg.h);
  public boolean aa;
  public int ab;
  public int ac;
  public int ad;
  public long ae;
  public long af;
  public long ag;
  public boolean ah;
  public boolean ai;
  public int aj;
  protected boolean ak;
  protected int al;
  public int am;
  protected cj an;
  protected bbj ao;
  protected cq ap;
  private boolean aC;
  protected UUID aq = on.a(this.S);
  private final n aD = new n();
  private final List<adq> aE = Lists.newArrayList();
  protected boolean ar;
  private final Set<String> aF = Sets.newHashSet();
  private boolean aG;
  
  public rr(aht ☃)
  {
    this.l = ☃;
    b(0.0D, 0.0D, 0.0D);
    if (☃ != null) {
      this.am = ☃.s.p().a();
    }
    this.Z = new kh(this);
    this.Z.a(ax, Byte.valueOf((byte)0));
    this.Z.a(ay, Integer.valueOf(300));
    this.Z.a(aA, Boolean.valueOf(false));
    this.Z.a(az, "");
    this.Z.a(aB, Boolean.valueOf(false));
    i();
  }
  
  public int O()
  {
    return this.g;
  }
  
  public void f(int ☃)
  {
    this.g = ☃;
  }
  
  public Set<String> P()
  {
    return this.aF;
  }
  
  public boolean a(String ☃)
  {
    if (this.aF.size() >= 1024) {
      return false;
    }
    this.aF.add(☃);
    return true;
  }
  
  public boolean b(String ☃)
  {
    return this.aF.remove(☃);
  }
  
  public void Q()
  {
    T();
  }
  
  protected abstract void i();
  
  public kh R()
  {
    return this.Z;
  }
  
  public boolean equals(Object ☃)
  {
    if ((☃ instanceof rr)) {
      return ((rr)☃).g == this.g;
    }
    return false;
  }
  
  public int hashCode()
  {
    return this.g;
  }
  
  protected void S()
  {
    if (this.l == null) {
      return;
    }
    while ((this.q > 0.0D) && (this.q < 256.0D))
    {
      b(this.p, this.q, this.r);
      if (this.l.a(this, bl()).isEmpty()) {
        break;
      }
      this.q += 1.0D;
    }
    this.s = (this.t = this.u = 0.0D);
    this.w = 0.0F;
  }
  
  public void T()
  {
    this.F = true;
  }
  
  public void b(boolean ☃) {}
  
  protected void a(float ☃, float ☃)
  {
    if ((☃ != this.G) || (☃ != this.H))
    {
      float ☃ = this.G;
      
      this.G = ☃;
      this.H = ☃;
      bbh ☃ = bl();
      a(new bbh(☃.a, ☃.b, ☃.c, ☃.a + this.G, ☃.b + this.H, ☃.c + this.G));
      if ((this.G > ☃) && (!this.X) && (!this.l.E)) {
        d(☃ - this.G, 0.0D, ☃ - this.G);
      }
    }
  }
  
  protected void b(float ☃, float ☃)
  {
    this.v = (☃ % 360.0F);
    this.w = (☃ % 360.0F);
  }
  
  public void b(double ☃, double ☃, double ☃)
  {
    this.p = ☃;
    this.q = ☃;
    this.r = ☃;
    float ☃ = this.G / 2.0F;
    float ☃ = this.H;
    a(new bbh(☃ - ☃, ☃, ☃ - ☃, ☃ + ☃, ☃ + ☃, ☃ + ☃));
  }
  
  public void c(float ☃, float ☃)
  {
    float ☃ = this.w;
    float ☃ = this.v;
    
    this.v = ((float)(this.v + ☃ * 0.15D));
    this.w = ((float)(this.w - ☃ * 0.15D));
    this.w = on.a(this.w, -90.0F, 90.0F);
    
    this.y += this.w - ☃;
    this.x += this.v - ☃;
    if (this.as != null) {
      this.as.l(this);
    }
  }
  
  public void m()
  {
    if (!this.l.E) {
      b(6, aM());
    }
    U();
  }
  
  public void U()
  {
    this.l.C.a("entityBaseTick");
    if ((aI()) && (by().F)) {
      p();
    }
    if (this.j > 0) {
      this.j -= 1;
    }
    this.I = this.J;
    this.m = this.p;
    this.n = this.q;
    this.o = this.r;
    this.y = this.w;
    this.x = this.v;
    if ((!this.l.E) && ((this.l instanceof lp)))
    {
      this.l.C.a("portal");
      if (this.ak)
      {
        MinecraftServer ☃ = this.l.u();
        if (☃.E())
        {
          if (!aI())
          {
            int ☃ = V();
            if (this.al++ >= ☃)
            {
              this.al = ☃;
              this.aj = aC();
              int ☃;
              int ☃;
              if (this.l.s.p().a() == -1) {
                ☃ = 0;
              } else {
                ☃ = -1;
              }
              c(☃);
            }
          }
          this.ak = false;
        }
      }
      else
      {
        if (this.al > 0) {
          this.al -= 4;
        }
        if (this.al < 0) {
          this.al = 0;
        }
      }
      H();
      this.l.C.b();
    }
    al();
    
    aj();
    if (this.l.E) {
      this.aw = 0;
    } else if (this.aw > 0) {
      if (this.Y)
      {
        this.aw -= 4;
        if (this.aw < 0) {
          this.aw = 0;
        }
      }
      else
      {
        if (this.aw % 20 == 0) {
          a(rc.c, 1.0F);
        }
        this.aw -= 1;
      }
    }
    if (an())
    {
      W();
      this.L *= 0.5F;
    }
    if (this.q < -64.0D) {
      Y();
    }
    if (!this.l.E) {
      b(0, this.aw > 0);
    }
    this.X = false;
    
    this.l.C.b();
  }
  
  protected void H()
  {
    if (this.aj > 0) {
      this.aj -= 1;
    }
  }
  
  public int V()
  {
    return 1;
  }
  
  protected void W()
  {
    if (this.Y) {
      return;
    }
    a(rc.d, 4.0F);
    g(15);
  }
  
  public void g(int ☃)
  {
    int ☃ = ☃ * 20;
    if ((this instanceof sa)) {
      ☃ = agy.a((sa)this, ☃);
    }
    if (this.aw < ☃) {
      this.aw = ☃;
    }
  }
  
  public void X()
  {
    this.aw = 0;
  }
  
  protected void Y()
  {
    T();
  }
  
  public boolean c(double ☃, double ☃, double ☃)
  {
    bbh ☃ = bl().c(☃, ☃, ☃);
    return b(☃);
  }
  
  private boolean b(bbh ☃)
  {
    return (this.l.a(this, ☃).isEmpty()) && (!this.l.e(☃));
  }
  
  public void d(double ☃, double ☃, double ☃)
  {
    if (this.Q)
    {
      a(bl().c(☃, ☃, ☃));
      Z();
      return;
    }
    this.l.C.a("move");
    
    double ☃ = this.p;
    double ☃ = this.q;
    double ☃ = this.r;
    if (this.E)
    {
      this.E = false;
      
      ☃ *= 0.25D;
      ☃ *= 0.05000000074505806D;
      ☃ *= 0.25D;
      this.s = 0.0D;
      this.t = 0.0D;
      this.u = 0.0D;
    }
    double ☃ = ☃;
    double ☃ = ☃;
    double ☃ = ☃;
    
    boolean ☃ = (this.z) && (aK()) && ((this instanceof zj));
    if (☃)
    {
      double ☃ = 0.05D;
      while ((☃ != 0.0D) && (this.l.a(this, bl().c(☃, -1.0D, 0.0D)).isEmpty()))
      {
        if ((☃ < ☃) && (☃ >= -☃)) {
          ☃ = 0.0D;
        } else if (☃ > 0.0D) {
          ☃ -= ☃;
        } else {
          ☃ += ☃;
        }
        ☃ = ☃;
      }
      while ((☃ != 0.0D) && (this.l.a(this, bl().c(0.0D, -1.0D, ☃)).isEmpty()))
      {
        if ((☃ < ☃) && (☃ >= -☃)) {
          ☃ = 0.0D;
        } else if (☃ > 0.0D) {
          ☃ -= ☃;
        } else {
          ☃ += ☃;
        }
        ☃ = ☃;
      }
      while ((☃ != 0.0D) && (☃ != 0.0D) && (this.l.a(this, bl().c(☃, -1.0D, ☃)).isEmpty()))
      {
        if ((☃ < ☃) && (☃ >= -☃)) {
          ☃ = 0.0D;
        } else if (☃ > 0.0D) {
          ☃ -= ☃;
        } else {
          ☃ += ☃;
        }
        ☃ = ☃;
        if ((☃ < ☃) && (☃ >= -☃)) {
          ☃ = 0.0D;
        } else if (☃ > 0.0D) {
          ☃ -= ☃;
        } else {
          ☃ += ☃;
        }
        ☃ = ☃;
      }
    }
    List<bbh> ☃ = this.l.a(this, bl().a(☃, ☃, ☃));
    bbh ☃ = bl();
    
    int ☃ = 0;
    for (int ☃ = ☃.size(); ☃ < ☃; ☃++) {
      ☃ = ((bbh)☃.get(☃)).b(bl(), ☃);
    }
    a(bl().c(0.0D, ☃, 0.0D));
    boolean ☃ = (this.z) || ((☃ != ☃) && (☃ < 0.0D));
    
    int ☃ = 0;
    for (int ☃ = ☃.size(); ☃ < ☃; ☃++) {
      ☃ = ((bbh)☃.get(☃)).a(bl(), ☃);
    }
    a(bl().c(☃, 0.0D, 0.0D));
    
    int ☃ = 0;
    for (int ☃ = ☃.size(); ☃ < ☃; ☃++) {
      ☃ = ((bbh)☃.get(☃)).c(bl(), ☃);
    }
    a(bl().c(0.0D, 0.0D, ☃));
    if ((this.P > 0.0F) && (☃) && ((☃ != ☃) || (☃ != ☃)))
    {
      double ☃ = ☃;
      double ☃ = ☃;
      double ☃ = ☃;
      bbh ☃ = bl();
      
      a(☃);
      ☃ = ☃;
      ☃ = this.P;
      ☃ = ☃;
      
      List<bbh> ☃ = this.l.a(this, bl().a(☃, ☃, ☃));
      
      bbh ☃ = bl();
      bbh ☃ = ☃.a(☃, 0.0D, ☃);
      double ☃ = ☃;
      int ☃ = 0;
      for (int ☃ = ☃.size(); ☃ < ☃; ☃++) {
        ☃ = ((bbh)☃.get(☃)).b(☃, ☃);
      }
      ☃ = ☃.c(0.0D, ☃, 0.0D);
      
      double ☃ = ☃;
      
      int ☃ = 0;
      for (int ☃ = ☃.size(); ☃ < ☃; ☃++) {
        ☃ = ((bbh)☃.get(☃)).a(☃, ☃);
      }
      ☃ = ☃.c(☃, 0.0D, 0.0D);
      
      double ☃ = ☃;
      
      int ☃ = 0;
      for (int ☃ = ☃.size(); ☃ < ☃; ☃++) {
        ☃ = ((bbh)☃.get(☃)).c(☃, ☃);
      }
      ☃ = ☃.c(0.0D, 0.0D, ☃);
      
      bbh ☃ = bl();
      double ☃ = ☃;
      int ☃ = 0;
      for (int ☃ = ☃.size(); ☃ < ☃; ☃++) {
        ☃ = ((bbh)☃.get(☃)).b(☃, ☃);
      }
      ☃ = ☃.c(0.0D, ☃, 0.0D);
      
      double ☃ = ☃;
      
      int ☃ = 0;
      for (int ☃ = ☃.size(); ☃ < ☃; ☃++) {
        ☃ = ((bbh)☃.get(☃)).a(☃, ☃);
      }
      ☃ = ☃.c(☃, 0.0D, 0.0D);
      
      double ☃ = ☃;
      
      int ☃ = 0;
      for (int ☃ = ☃.size(); ☃ < ☃; ☃++) {
        ☃ = ((bbh)☃.get(☃)).c(☃, ☃);
      }
      ☃ = ☃.c(0.0D, 0.0D, ☃);
      
      double ☃ = ☃ * ☃ + ☃ * ☃;
      double ☃ = ☃ * ☃ + ☃ * ☃;
      if (☃ > ☃)
      {
        ☃ = ☃;
        ☃ = ☃;
        ☃ = -☃;
        a(☃);
      }
      else
      {
        ☃ = ☃;
        ☃ = ☃;
        ☃ = -☃;
        a(☃);
      }
      int ☃ = 0;
      for (int ☃ = ☃.size(); ☃ < ☃; ☃++) {
        ☃ = ((bbh)☃.get(☃)).b(bl(), ☃);
      }
      a(bl().c(0.0D, ☃, 0.0D));
      if (☃ * ☃ + ☃ * ☃ >= ☃ * ☃ + ☃ * ☃)
      {
        ☃ = ☃;
        ☃ = ☃;
        ☃ = ☃;
        a(☃);
      }
    }
    this.l.C.b();
    this.l.C.a("rest");
    
    Z();
    
    this.A = ((☃ != ☃) || (☃ != ☃));
    this.B = (☃ != ☃);
    
    this.z = ((this.B) && (☃ < 0.0D));
    this.C = ((this.A) || (this.B));
    
    int ☃ = on.c(this.p);
    int ☃ = on.c(this.q - 0.20000000298023224D);
    int ☃ = on.c(this.r);
    
    cj ☃ = new cj(☃, ☃, ☃);
    arc ☃ = this.l.o(☃);
    if (☃.a() == axe.a)
    {
      cj ☃ = ☃.b();
      arc ☃ = this.l.o(☃);
      ajt ☃ = ☃.t();
      if (((☃ instanceof alj)) || ((☃ instanceof apk)) || ((☃ instanceof alk)))
      {
        ☃ = ☃;
        ☃ = ☃;
      }
    }
    a(☃, this.z, ☃, ☃);
    if (☃ != ☃) {
      this.s = 0.0D;
    }
    if (☃ != ☃) {
      this.u = 0.0D;
    }
    ajt ☃ = ☃.t();
    if (☃ != ☃) {
      ☃.a(this.l, this);
    }
    if ((ae()) && (!☃) && (!aI()))
    {
      double ☃ = this.p - ☃;
      double ☃ = this.q - ☃;
      double ☃ = this.r - ☃;
      if (☃ != aju.au) {
        ☃ = 0.0D;
      }
      if ((☃ != null) && (this.z)) {
        ☃.a(this.l, ☃, this);
      }
      this.J = ((float)(this.J + on.a(☃ * ☃ + ☃ * ☃) * 0.6D));
      this.K = ((float)(this.K + on.a(☃ * ☃ + ☃ * ☃ + ☃ * ☃) * 0.6D));
      if ((this.K > this.av) && (☃.a() != axe.a))
      {
        this.av = ((int)this.K + 1);
        if (ai())
        {
          float ☃ = on.a(this.s * this.s * 0.20000000298023224D + this.t * this.t + this.u * this.u * 0.20000000298023224D) * 0.35F;
          if (☃ > 1.0F) {
            ☃ = 1.0F;
          }
          a(aa(), ☃, 1.0F + (this.S.nextFloat() - this.S.nextFloat()) * 0.4F);
        }
        a(☃, ☃);
      }
    }
    try
    {
      ac();
    }
    catch (Throwable ☃)
    {
      b ☃ = b.a(☃, "Checking entity block collision");
      c ☃ = ☃.a("Entity being checked for collision");
      
      a(☃);
      
      throw new e(☃);
    }
    boolean ☃ = ah();
    if (this.l.f(bl().h(0.001D)))
    {
      h(1);
      if (!☃)
      {
        this.aw += 1;
        if (this.aw == 0) {
          g(8);
        }
      }
    }
    else if (this.aw <= 0)
    {
      this.aw = (-this.U);
    }
    if ((☃) && (this.aw > 0))
    {
      a(ng.bE, 0.7F, 1.6F + (this.S.nextFloat() - this.S.nextFloat()) * 0.4F);
      this.aw = (-this.U);
    }
    this.l.C.b();
  }
  
  public void Z()
  {
    bbh ☃ = bl();
    this.p = ((☃.a + ☃.d) / 2.0D);
    this.q = ☃.b;
    this.r = ((☃.c + ☃.f) / 2.0D);
  }
  
  protected nf aa()
  {
    return ng.bI;
  }
  
  protected nf ab()
  {
    return ng.bH;
  }
  
  protected void ac()
  {
    bbh ☃ = bl();
    cj.b ☃ = cj.b.c(☃.a + 0.001D, ☃.b + 0.001D, ☃.c + 0.001D);
    cj.b ☃ = cj.b.c(☃.d - 0.001D, ☃.e - 0.001D, ☃.f - 0.001D);
    cj.b ☃ = cj.b.s();
    if (this.l.a(☃, ☃)) {
      for (int ☃ = ☃.p(); ☃ <= ☃.p(); ☃++) {
        for (int ☃ = ☃.q(); ☃ <= ☃.q(); ☃++) {
          for (int ☃ = ☃.r(); ☃ <= ☃.r(); ☃++)
          {
            ☃.d(☃, ☃, ☃);
            arc ☃ = this.l.o(☃);
            try
            {
              ☃.t().a(this.l, ☃, ☃, this);
            }
            catch (Throwable ☃)
            {
              b ☃ = b.a(☃, "Colliding entity with block");
              c ☃ = ☃.a("Block being collided with");
              
              c.a(☃, ☃, ☃);
              
              throw new e(☃);
            }
          }
        }
      }
    }
    ☃.t();
    ☃.t();
    ☃.t();
  }
  
  protected void a(cj ☃, ajt ☃)
  {
    aop ☃ = ☃.w();
    if (this.l.o(☃.a()).t() == aju.aH)
    {
      ☃ = aju.aH.w();
      a(☃.d(), ☃.a() * 0.15F, ☃.b());
    }
    else if (!☃.u().a().d())
    {
      a(☃.d(), ☃.a() * 0.15F, ☃.b());
    }
  }
  
  public void a(nf ☃, float ☃, float ☃)
  {
    if (!ad()) {
      this.l.a(null, this.p, this.q, this.r, ☃, bz(), ☃, ☃);
    }
  }
  
  public boolean ad()
  {
    return ((Boolean)this.Z.a(aB)).booleanValue();
  }
  
  public void c(boolean ☃)
  {
    this.Z.b(aB, Boolean.valueOf(☃));
  }
  
  protected boolean ae()
  {
    return true;
  }
  
  protected void a(double ☃, boolean ☃, arc ☃, cj ☃)
  {
    if (☃)
    {
      if (this.L > 0.0F) {
        ☃.t().a(this.l, ☃, this, this.L);
      }
      this.L = 0.0F;
    }
    else if (☃ < 0.0D)
    {
      this.L = ((float)(this.L - ☃));
    }
  }
  
  public bbh af()
  {
    return null;
  }
  
  protected void h(int ☃)
  {
    if (!this.Y) {
      a(rc.a, ☃);
    }
  }
  
  public final boolean ag()
  {
    return this.Y;
  }
  
  public void e(float ☃, float ☃)
  {
    if (aJ()) {
      for (rr ☃ : bu()) {
        ☃.e(☃, ☃);
      }
    }
  }
  
  public boolean ah()
  {
    if (this.V) {
      return true;
    }
    cj.b ☃ = cj.b.c(this.p, this.q, this.r);
    if ((!this.l.B(☃)) && (!this.l.B(☃.d(this.p, this.q + this.H, this.r))))
    {
      ☃.t();
      return false;
    }
    ☃.t();
    return true;
  }
  
  public boolean ai()
  {
    return this.V;
  }
  
  public boolean aj()
  {
    if ((by() instanceof aag))
    {
      this.V = false;
    }
    else if (this.l.a(bl().b(0.0D, -0.4000000059604645D, 0.0D).h(0.001D), axe.h, this))
    {
      if ((!this.V) && (!this.X)) {
        ak();
      }
      this.L = 0.0F;
      this.V = true;
      this.aw = 0;
    }
    else
    {
      this.V = false;
    }
    return this.V;
  }
  
  protected void ak()
  {
    float ☃ = on.a(this.s * this.s * 0.20000000298023224D + this.t * this.t + this.u * this.u * 0.20000000298023224D) * 0.2F;
    if (☃ > 1.0F) {
      ☃ = 1.0F;
    }
    a(ab(), ☃, 1.0F + (this.S.nextFloat() - this.S.nextFloat()) * 0.4F);
    float ☃ = on.c(bl().b);
    for (int ☃ = 0; ☃ < 1.0F + this.G * 20.0F; ☃++)
    {
      float ☃ = (this.S.nextFloat() * 2.0F - 1.0F) * this.G;
      float ☃ = (this.S.nextFloat() * 2.0F - 1.0F) * this.G;
      this.l.a(cy.e, this.p + ☃, ☃ + 1.0F, this.r + ☃, this.s, this.t - this.S.nextFloat() * 0.2F, this.u, new int[0]);
    }
    for (int ☃ = 0; ☃ < 1.0F + this.G * 20.0F; ☃++)
    {
      float ☃ = (this.S.nextFloat() * 2.0F - 1.0F) * this.G;
      float ☃ = (this.S.nextFloat() * 2.0F - 1.0F) * this.G;
      this.l.a(cy.f, this.p + ☃, ☃ + 1.0F, this.r + ☃, this.s, this.t, this.u, new int[0]);
    }
  }
  
  public void al()
  {
    if ((aL()) && (!ai())) {
      am();
    }
  }
  
  protected void am()
  {
    int ☃ = on.c(this.p);
    int ☃ = on.c(this.q - 0.20000000298023224D);
    int ☃ = on.c(this.r);
    cj ☃ = new cj(☃, ☃, ☃);
    arc ☃ = this.l.o(☃);
    if (☃.i() != aob.a) {
      this.l.a(cy.L, this.p + (this.S.nextFloat() - 0.5D) * this.G, bl().b + 0.1D, this.r + (this.S.nextFloat() - 0.5D) * this.G, -this.s * 4.0D, 1.5D, -this.u * 4.0D, new int[] { ajt.j(☃) });
    }
  }
  
  public boolean a(axe ☃)
  {
    if ((by() instanceof aag)) {
      return false;
    }
    double ☃ = this.q + bn();
    cj ☃ = new cj(this.p, ☃, this.r);
    arc ☃ = this.l.o(☃);
    if (☃.a() == ☃)
    {
      float ☃ = amo.e(☃.t().e(☃)) - 0.11111111F;
      float ☃ = ☃.q() + 1 - ☃;
      boolean ☃ = ☃ < ☃;
      if ((!☃) && ((this instanceof zj))) {
        return false;
      }
      return ☃;
    }
    return false;
  }
  
  public boolean an()
  {
    return this.l.a(bl().b(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), axe.i);
  }
  
  public void a(float ☃, float ☃, float ☃)
  {
    float ☃ = ☃ * ☃ + ☃ * ☃;
    if (☃ < 1.0E-4F) {
      return;
    }
    ☃ = on.c(☃);
    if (☃ < 1.0F) {
      ☃ = 1.0F;
    }
    ☃ = ☃ / ☃;
    ☃ *= ☃;
    ☃ *= ☃;
    
    float ☃ = on.a(this.v * 0.017453292F);
    float ☃ = on.b(this.v * 0.017453292F);
    
    this.s += ☃ * ☃ - ☃ * ☃;
    this.u += ☃ * ☃ + ☃ * ☃;
  }
  
  public int d(float ☃)
  {
    cj.a ☃ = new cj.a(on.c(this.p), 0, on.c(this.r));
    if (this.l.e(☃))
    {
      ☃.p(on.c(this.q + bn()));
      return this.l.b(☃, 0);
    }
    return 0;
  }
  
  public float e(float ☃)
  {
    cj.a ☃ = new cj.a(on.c(this.p), 0, on.c(this.r));
    if (this.l.e(☃))
    {
      ☃.p(on.c(this.q + bn()));
      return this.l.n(☃);
    }
    return 0.0F;
  }
  
  public void a(aht ☃)
  {
    this.l = ☃;
  }
  
  public void a(double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    this.m = (this.p = on.a(☃, -3.0E7D, 3.0E7D));
    this.n = (this.q = ☃);
    this.o = (this.r = on.a(☃, -3.0E7D, 3.0E7D));
    
    ☃ = on.a(☃, -90.0F, 90.0F);
    this.x = (this.v = ☃);
    this.y = (this.w = ☃);
    
    double ☃ = this.x - ☃;
    if (☃ < -180.0D) {
      this.x += 360.0F;
    }
    if (☃ >= 180.0D) {
      this.x -= 360.0F;
    }
    b(this.p, this.q, this.r);
    b(☃, ☃);
  }
  
  public void a(cj ☃, float ☃, float ☃)
  {
    b(☃.p() + 0.5D, ☃.q(), ☃.r() + 0.5D, ☃, ☃);
  }
  
  public void b(double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    this.M = (this.m = this.p = ☃);
    this.N = (this.n = this.q = ☃);
    this.O = (this.o = this.r = ☃);
    this.v = ☃;
    this.w = ☃;
    b(this.p, this.q, this.r);
  }
  
  public float g(rr ☃)
  {
    float ☃ = (float)(this.p - ☃.p);
    float ☃ = (float)(this.q - ☃.q);
    float ☃ = (float)(this.r - ☃.r);
    return on.c(☃ * ☃ + ☃ * ☃ + ☃ * ☃);
  }
  
  public double e(double ☃, double ☃, double ☃)
  {
    double ☃ = this.p - ☃;
    double ☃ = this.q - ☃;
    double ☃ = this.r - ☃;
    return ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
  }
  
  public double c(cj ☃)
  {
    return ☃.e(this.p, this.q, this.r);
  }
  
  public double d(cj ☃)
  {
    return ☃.f(this.p, this.q, this.r);
  }
  
  public double f(double ☃, double ☃, double ☃)
  {
    double ☃ = this.p - ☃;
    double ☃ = this.q - ☃;
    double ☃ = this.r - ☃;
    return on.a(☃ * ☃ + ☃ * ☃ + ☃ * ☃);
  }
  
  public double h(rr ☃)
  {
    double ☃ = this.p - ☃.p;
    double ☃ = this.q - ☃.q;
    double ☃ = this.r - ☃.r;
    return ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
  }
  
  public void d(zj ☃) {}
  
  public void i(rr ☃)
  {
    if (x(☃)) {
      return;
    }
    if ((☃.Q) || (this.Q)) {
      return;
    }
    double ☃ = ☃.p - this.p;
    double ☃ = ☃.r - this.r;
    
    double ☃ = on.a(☃, ☃);
    if (☃ >= 0.009999999776482582D)
    {
      ☃ = on.a(☃);
      ☃ /= ☃;
      ☃ /= ☃;
      
      double ☃ = 1.0D / ☃;
      if (☃ > 1.0D) {
        ☃ = 1.0D;
      }
      ☃ *= ☃;
      ☃ *= ☃;
      
      ☃ *= 0.05000000074505806D;
      ☃ *= 0.05000000074505806D;
      
      ☃ *= (1.0F - this.R);
      ☃ *= (1.0F - this.R);
      if (!aJ()) {
        g(-☃, 0.0D, -☃);
      }
      if (!☃.aJ()) {
        ☃.g(☃, 0.0D, ☃);
      }
    }
  }
  
  public void g(double ☃, double ☃, double ☃)
  {
    this.s += ☃;
    this.t += ☃;
    this.u += ☃;
    this.ai = true;
  }
  
  protected void ao()
  {
    this.D = true;
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    ao();
    return false;
  }
  
  public bbj f(float ☃)
  {
    if (☃ == 1.0F) {
      return f(this.w, this.v);
    }
    float ☃ = this.y + (this.w - this.y) * ☃;
    float ☃ = this.x + (this.v - this.x) * ☃;
    return f(☃, ☃);
  }
  
  protected final bbj f(float ☃, float ☃)
  {
    float ☃ = on.b(-☃ * 0.017453292F - 3.1415927F);
    float ☃ = on.a(-☃ * 0.017453292F - 3.1415927F);
    float ☃ = -on.b(-☃ * 0.017453292F);
    float ☃ = on.a(-☃ * 0.017453292F);
    
    return new bbj(☃ * ☃, ☃, ☃ * ☃);
  }
  
  public bbj g(float ☃)
  {
    if (☃ == 1.0F) {
      return new bbj(this.p, this.q + bn(), this.r);
    }
    double ☃ = this.m + (this.p - this.m) * ☃;
    double ☃ = this.n + (this.q - this.n) * ☃ + bn();
    double ☃ = this.o + (this.r - this.o) * ☃;
    
    return new bbj(☃, ☃, ☃);
  }
  
  public bbi a(double ☃, float ☃)
  {
    bbj ☃ = g(☃);
    bbj ☃ = f(☃);
    bbj ☃ = ☃.b(☃.b * ☃, ☃.c * ☃, ☃.d * ☃);
    return this.l.a(☃, ☃, false, false, true);
  }
  
  public boolean ap()
  {
    return false;
  }
  
  public boolean aq()
  {
    return false;
  }
  
  public void b(rr ☃, int ☃) {}
  
  public boolean h(double ☃, double ☃, double ☃)
  {
    double ☃ = this.p - ☃;
    double ☃ = this.q - ☃;
    double ☃ = this.r - ☃;
    double ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
    return a(☃);
  }
  
  public boolean a(double ☃)
  {
    double ☃ = bl().a();
    if (Double.isNaN(☃)) {
      ☃ = 1.0D;
    }
    ☃ *= 64.0D * c;
    return ☃ < ☃ * ☃;
  }
  
  public boolean c(dn ☃)
  {
    String ☃ = as();
    if ((this.F) || (☃ == null)) {
      return false;
    }
    ☃.a("id", ☃);
    e(☃);
    return true;
  }
  
  public boolean d(dn ☃)
  {
    String ☃ = as();
    if ((this.F) || (☃ == null) || (aI())) {
      return false;
    }
    ☃.a("id", ☃);
    e(☃);
    return true;
  }
  
  public void e(dn ☃)
  {
    try
    {
      ☃.a("Pos", a(new double[] { this.p, this.q, this.r }));
      ☃.a("Motion", a(new double[] { this.s, this.t, this.u }));
      ☃.a("Rotation", a(new float[] { this.v, this.w }));
      
      ☃.a("FallDistance", this.L);
      ☃.a("Fire", (short)this.aw);
      ☃.a("Air", (short)aP());
      ☃.a("OnGround", this.z);
      ☃.a("Dimension", this.am);
      ☃.a("Invulnerable", this.aC);
      ☃.a("PortalCooldown", this.aj);
      
      ☃.a("UUID", bc());
      if ((bf() != null) && (!bf().isEmpty())) {
        ☃.a("CustomName", bf());
      }
      if (bg()) {
        ☃.a("CustomNameVisible", bg());
      }
      this.aD.b(☃);
      if (ad()) {
        ☃.a("Silent", ad());
      }
      if (this.ar) {
        ☃.a("Glowing", this.ar);
      }
      if (this.aF.size() > 0)
      {
        du ☃ = new du();
        for (String ☃ : this.aF) {
          ☃.a(new ea(☃));
        }
        ☃.a("Tags", ☃);
      }
      b(☃);
      if (aJ())
      {
        du ☃ = new du();
        for (rr ☃ : bu())
        {
          dn ☃ = new dn();
          if (☃.c(☃)) {
            ☃.a(☃);
          }
        }
        if (!☃.c_()) {
          ☃.a("Passengers", ☃);
        }
      }
    }
    catch (Throwable ☃)
    {
      b ☃ = b.a(☃, "Saving entity NBT");
      c ☃ = ☃.a("Entity being saved");
      a(☃);
      throw new e(☃);
    }
  }
  
  public void f(dn ☃)
  {
    try
    {
      du ☃ = ☃.c("Pos", 6);
      du ☃ = ☃.c("Motion", 6);
      du ☃ = ☃.c("Rotation", 5);
      
      this.s = ☃.e(0);
      this.t = ☃.e(1);
      this.u = ☃.e(2);
      if (Math.abs(this.s) > 10.0D) {
        this.s = 0.0D;
      }
      if (Math.abs(this.t) > 10.0D) {
        this.t = 0.0D;
      }
      if (Math.abs(this.u) > 10.0D) {
        this.u = 0.0D;
      }
      this.m = (this.M = this.p = ☃.e(0));
      this.n = (this.N = this.q = ☃.e(1));
      this.o = (this.O = this.r = ☃.e(2));
      
      this.x = (this.v = ☃.f(0));
      this.y = (this.w = ☃.f(1));
      
      h(this.v);
      i(this.v);
      
      this.L = ☃.j("FallDistance");
      this.aw = ☃.g("Fire");
      j(☃.g("Air"));
      this.z = ☃.p("OnGround");
      if (☃.e("Dimension")) {
        this.am = ☃.h("Dimension");
      }
      this.aC = ☃.p("Invulnerable");
      this.aj = ☃.h("PortalCooldown");
      if (☃.b("UUID")) {
        this.aq = ☃.a("UUID");
      }
      b(this.p, this.q, this.r);
      b(this.v, this.w);
      if (☃.b("CustomName", 8)) {
        c(☃.l("CustomName"));
      }
      i(☃.p("CustomNameVisible"));
      this.aD.a(☃);
      c(☃.p("Silent"));
      f(☃.p("Glowing"));
      if (☃.b("Tags", 9))
      {
        this.aF.clear();
        du ☃ = ☃.c("Tags", 8);
        int ☃ = Math.min(☃.c(), 1024);
        for (int ☃ = 0; ☃ < ☃; ☃++) {
          this.aF.add(☃.g(☃));
        }
      }
      a(☃);
      if (ar()) {
        b(this.p, this.q, this.r);
      }
    }
    catch (Throwable ☃)
    {
      b ☃ = b.a(☃, "Loading entity NBT");
      c ☃ = ☃.a("Entity being loaded");
      a(☃);
      throw new e(☃);
    }
  }
  
  protected boolean ar()
  {
    return true;
  }
  
  protected final String as()
  {
    return rt.b(this);
  }
  
  protected abstract void a(dn paramdn);
  
  protected abstract void b(dn paramdn);
  
  public void at() {}
  
  protected du a(double... ☃)
  {
    du ☃ = new du();
    for (double ☃ : ☃) {
      ☃.a(new dp(☃));
    }
    return ☃;
  }
  
  protected du a(float... ☃)
  {
    du ☃ = new du();
    for (float ☃ : ☃) {
      ☃.a(new dr(☃));
    }
    return ☃;
  }
  
  public yd a(ado ☃, int ☃)
  {
    return a(☃, ☃, 0.0F);
  }
  
  public yd a(ado ☃, int ☃, float ☃)
  {
    return a(new adq(☃, ☃, 0), ☃);
  }
  
  public yd a(adq ☃, float ☃)
  {
    if ((☃.b == 0) || (☃.b() == null)) {
      return null;
    }
    yd ☃ = new yd(this.l, this.p, this.q + ☃, this.r, ☃);
    ☃.q();
    this.l.a(☃);
    return ☃;
  }
  
  public boolean au()
  {
    return !this.F;
  }
  
  public boolean av()
  {
    if (this.Q) {
      return false;
    }
    cj.b ☃ = cj.b.s();
    for (int ☃ = 0; ☃ < 8; ☃++)
    {
      int ☃ = on.c(this.q + ((☃ >> 0) % 2 - 0.5F) * 0.1F + bn());
      int ☃ = on.c(this.p + ((☃ >> 1) % 2 - 0.5F) * this.G * 0.8F);
      int ☃ = on.c(this.r + ((☃ >> 2) % 2 - 0.5F) * this.G * 0.8F);
      if ((☃.p() != ☃) || (☃.q() != ☃) || (☃.r() != ☃))
      {
        ☃.d(☃, ☃, ☃);
        if (this.l.o(☃).t().j())
        {
          ☃.t();
          return true;
        }
      }
    }
    ☃.t();
    return false;
  }
  
  public boolean a(zj ☃, adq ☃, qm ☃)
  {
    return false;
  }
  
  public bbh j(rr ☃)
  {
    return null;
  }
  
  public void aw()
  {
    rr ☃ = by();
    if ((aI()) && (☃.F))
    {
      p();
      return;
    }
    this.s = 0.0D;
    this.t = 0.0D;
    this.u = 0.0D;
    m();
    if (!aI()) {
      return;
    }
    ☃.k(this);
  }
  
  public void k(rr ☃)
  {
    if (!w(☃)) {
      return;
    }
    ☃.b(this.p, this.q + ay() + ☃.ax(), this.r);
  }
  
  public void l(rr ☃) {}
  
  public double ax()
  {
    return 0.0D;
  }
  
  public double ay()
  {
    return this.H * 0.75D;
  }
  
  public boolean m(rr ☃)
  {
    return a(☃, false);
  }
  
  public boolean a(rr ☃, boolean ☃)
  {
    if ((!☃) && ((!n(☃)) || (!☃.q(this)))) {
      return false;
    }
    if (aI()) {
      p();
    }
    this.as = ☃;
    this.as.o(this);
    
    return true;
  }
  
  protected boolean n(rr ☃)
  {
    return this.j <= 0;
  }
  
  public void az()
  {
    for (int ☃ = this.h.size() - 1; ☃ >= 0; ☃--) {
      ((rr)this.h.get(☃)).p();
    }
  }
  
  public void p()
  {
    if (this.as != null)
    {
      rr ☃ = this.as;
      this.as = null;
      ☃.p(this);
    }
  }
  
  protected void o(rr ☃)
  {
    if (☃.by() != this) {
      throw new IllegalStateException("Use x.startRiding(y), not y.addPassenger(x)");
    }
    if ((!this.l.E) && ((☃ instanceof zj)) && (!(bt() instanceof zj))) {
      this.h.add(0, ☃);
    } else {
      this.h.add(☃);
    }
  }
  
  protected void p(rr ☃)
  {
    if (☃.by() == this) {
      throw new IllegalStateException("Use x.stopRiding(y), not y.removePassenger(x)");
    }
    this.h.remove(☃);
    ☃.j = 60;
  }
  
  protected boolean q(rr ☃)
  {
    return bu().size() < 1;
  }
  
  public void a(double ☃, double ☃, double ☃, float ☃, float ☃, int ☃, boolean ☃)
  {
    b(☃, ☃, ☃);
    b(☃, ☃);
  }
  
  public float aA()
  {
    return 0.0F;
  }
  
  public bbj aB()
  {
    return null;
  }
  
  public void e(cj ☃)
  {
    if (this.aj > 0)
    {
      this.aj = aC();
      return;
    }
    if ((!this.l.E) && (!☃.equals(this.an)))
    {
      this.an = new cj(☃);
      arg.b ☃ = aju.aY.c(this.l, this.an);
      
      double ☃ = ☃.b().k() == cq.a.a ? ☃.a().r() : ☃.a().p();
      double ☃ = ☃.b().k() == cq.a.a ? this.r : this.p;
      ☃ = Math.abs(on.c(☃ - (☃.b().e().c() == cq.b.b ? 1 : 0), ☃, ☃ - ☃.d()));
      double ☃ = on.c(this.q - 1.0D, ☃.a().q(), ☃.a().q() - ☃.e());
      this.ao = new bbj(☃, ☃, 0.0D);
      this.ap = ☃.b();
    }
    this.ak = true;
  }
  
  public int aC()
  {
    return 300;
  }
  
  public void i(double ☃, double ☃, double ☃)
  {
    this.s = ☃;
    this.t = ☃;
    this.u = ☃;
  }
  
  public void a(byte ☃) {}
  
  public void aD() {}
  
  public Iterable<adq> aE()
  {
    return this.aE;
  }
  
  public Iterable<adq> aF()
  {
    return this.aE;
  }
  
  public Iterable<adq> aG()
  {
    return Iterables.concat(aE(), aF());
  }
  
  public void a(rw ☃, adq ☃) {}
  
  public boolean aH()
  {
    boolean ☃ = (this.l != null) && (this.l.E);
    
    return (!this.Y) && ((this.aw > 0) || ((☃) && (i(0))));
  }
  
  public boolean aI()
  {
    return by() != null;
  }
  
  public boolean aJ()
  {
    return !bu().isEmpty();
  }
  
  public boolean aK()
  {
    return i(1);
  }
  
  public void d(boolean ☃)
  {
    b(1, ☃);
  }
  
  public boolean aL()
  {
    return i(3);
  }
  
  public void e(boolean ☃)
  {
    b(3, ☃);
  }
  
  public boolean aM()
  {
    return (this.ar) || ((this.l.E) && (i(6)));
  }
  
  public void f(boolean ☃)
  {
    this.ar = ☃;
    if (!this.l.E) {
      b(6, this.ar);
    }
  }
  
  public boolean aN()
  {
    return i(5);
  }
  
  public boolean e(zj ☃)
  {
    if (☃.y()) {
      return false;
    }
    bbr ☃ = aO();
    if ((☃ != null) && (☃ != null) && (☃.aO() == ☃) && (☃.h())) {
      return false;
    }
    return aN();
  }
  
  public bbr aO()
  {
    return this.l.ad().g(bc().toString());
  }
  
  public boolean r(rr ☃)
  {
    return a(☃.aO());
  }
  
  public boolean a(bbr ☃)
  {
    if (aO() != null) {
      return aO().a(☃);
    }
    return false;
  }
  
  public void g(boolean ☃)
  {
    b(5, ☃);
  }
  
  protected boolean i(int ☃)
  {
    return (((Byte)this.Z.a(ax)).byteValue() & 1 << ☃) != 0;
  }
  
  protected void b(int ☃, boolean ☃)
  {
    byte ☃ = ((Byte)this.Z.a(ax)).byteValue();
    if (☃) {
      this.Z.b(ax, Byte.valueOf((byte)(☃ | 1 << ☃)));
    } else {
      this.Z.b(ax, Byte.valueOf((byte)(☃ & (1 << ☃ ^ 0xFFFFFFFF))));
    }
  }
  
  public int aP()
  {
    return ((Integer)this.Z.a(ay)).intValue();
  }
  
  public void j(int ☃)
  {
    this.Z.b(ay, Integer.valueOf(☃));
  }
  
  public void a(ya ☃)
  {
    a(rc.b, 5.0F);
    this.aw += 1;
    if (this.aw == 0) {
      g(8);
    }
  }
  
  public void b(sa ☃) {}
  
  protected boolean j(double ☃, double ☃, double ☃)
  {
    cj ☃ = new cj(☃, ☃, ☃);
    
    double ☃ = ☃ - ☃.p();
    double ☃ = ☃ - ☃.q();
    double ☃ = ☃ - ☃.r();
    
    List<bbh> ☃ = this.l.a(bl());
    if (☃.isEmpty()) {
      return false;
    }
    cq ☃ = cq.b;
    double ☃ = Double.MAX_VALUE;
    if ((!this.l.t(☃.e())) && (☃ < ☃))
    {
      ☃ = ☃;
      ☃ = cq.e;
    }
    if ((!this.l.t(☃.f())) && (1.0D - ☃ < ☃))
    {
      ☃ = 1.0D - ☃;
      ☃ = cq.f;
    }
    if ((!this.l.t(☃.c())) && (☃ < ☃))
    {
      ☃ = ☃;
      ☃ = cq.c;
    }
    if ((!this.l.t(☃.d())) && (1.0D - ☃ < ☃))
    {
      ☃ = 1.0D - ☃;
      ☃ = cq.d;
    }
    if ((!this.l.t(☃.a())) && (1.0D - ☃ < ☃))
    {
      ☃ = 1.0D - ☃;
      ☃ = cq.b;
    }
    float ☃ = this.S.nextFloat() * 0.2F + 0.1F;
    float ☃ = ☃.c().a();
    if (☃.k() == cq.a.a) {
      this.s += ☃ * ☃;
    } else if (☃.k() == cq.a.b) {
      this.t += ☃ * ☃;
    } else if (☃.k() == cq.a.c) {
      this.u += ☃ * ☃;
    }
    return true;
  }
  
  public void aQ()
  {
    this.E = true;
    this.L = 0.0F;
  }
  
  public String h_()
  {
    if (o_()) {
      return bf();
    }
    String ☃ = rt.b(this);
    if (☃ == null) {
      ☃ = "generic";
    }
    return di.a("entity." + ☃ + ".name");
  }
  
  public rr[] aR()
  {
    return null;
  }
  
  public boolean s(rr ☃)
  {
    return this == ☃;
  }
  
  public float aS()
  {
    return 0.0F;
  }
  
  public void h(float ☃) {}
  
  public void i(float ☃) {}
  
  public boolean aT()
  {
    return true;
  }
  
  public boolean t(rr ☃)
  {
    return false;
  }
  
  public String toString()
  {
    return String.format("%s['%s'/%d, l='%s', x=%.2f, y=%.2f, z=%.2f]", new Object[] { getClass().getSimpleName(), h_(), Integer.valueOf(this.g), this.l == null ? "~NULL~" : this.l.T().j(), Double.valueOf(this.p), Double.valueOf(this.q), Double.valueOf(this.r) });
  }
  
  public boolean b(rc ☃)
  {
    return (this.aC) && (☃ != rc.k) && (!☃.u());
  }
  
  public void h(boolean ☃)
  {
    this.aC = ☃;
  }
  
  public void u(rr ☃)
  {
    b(☃.p, ☃.q, ☃.r, ☃.v, ☃.w);
  }
  
  private void a(rr ☃)
  {
    dn ☃ = new dn();
    ☃.e(☃);
    ☃.q("Dimension");
    f(☃);
    this.aj = ☃.aj;
    this.an = ☃.an;
    this.ao = ☃.ao;
    this.ap = ☃.ap;
  }
  
  public rr c(int ☃)
  {
    if ((this.l.E) || (this.F)) {
      return null;
    }
    this.l.C.a("changeDimension");
    
    MinecraftServer ☃ = h();
    int ☃ = this.am;
    lp ☃ = ☃.a(☃);
    lp ☃ = ☃.a(☃);
    this.am = ☃;
    if ((☃ == 1) && (☃ == 1))
    {
      ☃ = ☃.a(0);
      this.am = 0;
    }
    this.l.e(this);
    this.F = false;
    this.l.C.a("reposition");
    cj ☃;
    cj ☃;
    if (☃ == 1)
    {
      ☃ = ☃.p();
    }
    else
    {
      double ☃ = this.p;
      double ☃ = this.r;
      double ☃ = 8.0D;
      if (☃ == -1)
      {
        ☃ = on.a(☃ / ☃, ☃.aj().b() + 16.0D, ☃.aj().d() - 16.0D);
        ☃ = on.a(☃ / ☃, ☃.aj().c() + 16.0D, ☃.aj().e() - 16.0D);
      }
      else if (☃ == 0)
      {
        ☃ = on.a(☃ * ☃, ☃.aj().b() + 16.0D, ☃.aj().d() - 16.0D);
        ☃ = on.a(☃ * ☃, ☃.aj().c() + 16.0D, ☃.aj().e() - 16.0D);
      }
      ☃ = on.a((int)☃, -29999872, 29999872);
      ☃ = on.a((int)☃, -29999872, 29999872);
      
      float ☃ = this.v;
      b(☃, this.q, ☃, 90.0F, 0.0F);
      aib ☃ = ☃.x();
      ☃.b(this, ☃);
      ☃ = new cj(this);
    }
    ☃.a(this, false);
    
    this.l.C.c("reloading");
    rr ☃ = rt.a(rt.b(this), ☃);
    if (☃ != null)
    {
      ☃.a(this);
      if ((☃ == 1) && (☃ == 1))
      {
        cj ☃ = ☃.q(☃.R());
        ☃.a(☃, ☃.v, ☃.w);
      }
      else
      {
        ☃.a(☃, ☃.v, ☃.w);
      }
      boolean ☃ = ☃.k;
      ☃.k = true;
      ☃.a(☃);
      ☃.k = ☃;
      ☃.a(☃, false);
    }
    this.F = true;
    this.l.C.b();
    
    ☃.m();
    ☃.m();
    this.l.C.b();
    return ☃;
  }
  
  public boolean aV()
  {
    return true;
  }
  
  public float a(ahp ☃, aht ☃, cj ☃, arc ☃)
  {
    return ☃.t().a(this);
  }
  
  public boolean a(ahp ☃, aht ☃, cj ☃, arc ☃, float ☃)
  {
    return true;
  }
  
  public int aW()
  {
    return 3;
  }
  
  public bbj aY()
  {
    return this.ao;
  }
  
  public cq aZ()
  {
    return this.ap;
  }
  
  public boolean ba()
  {
    return false;
  }
  
  public void a(c ☃)
  {
    ☃.a("Entity Type", new Callable()
    {
      public String a()
        throws Exception
      {
        return rt.b(rr.this) + " (" + rr.this.getClass().getCanonicalName() + ")";
      }
    });
    ☃.a("Entity ID", Integer.valueOf(this.g));
    ☃.a("Entity Name", new Callable()
    {
      public String a()
        throws Exception
      {
        return rr.this.h_();
      }
    });
    ☃.a("Entity's Exact location", String.format("%.2f, %.2f, %.2f", new Object[] { Double.valueOf(this.p), Double.valueOf(this.q), Double.valueOf(this.r) }));
    ☃.a("Entity's Block location", c.a(on.c(this.p), on.c(this.q), on.c(this.r)));
    ☃.a("Entity's Momentum", String.format("%.2f, %.2f, %.2f", new Object[] { Double.valueOf(this.s), Double.valueOf(this.t), Double.valueOf(this.u) }));
    ☃.a("Entity's Passengers", new Callable()
    {
      public String a()
        throws Exception
      {
        return rr.this.bu().toString();
      }
    });
    ☃.a("Entity's Vehicle", new Callable()
    {
      public String a()
        throws Exception
      {
        return rr.this.by().toString();
      }
    });
  }
  
  public boolean bb()
  {
    return aH();
  }
  
  public void a(UUID ☃)
  {
    this.aq = ☃;
  }
  
  public UUID bc()
  {
    return this.aq;
  }
  
  public boolean bd()
  {
    return true;
  }
  
  public static double be()
  {
    return c;
  }
  
  public static void b(double ☃)
  {
    c = ☃;
  }
  
  public eu i_()
  {
    fa ☃ = new fa(bbm.a(aO(), h_()));
    ☃.b().a(bk());
    ☃.b().a(bc().toString());
    return ☃;
  }
  
  public void c(String ☃)
  {
    this.Z.b(az, ☃);
  }
  
  public String bf()
  {
    return (String)this.Z.a(az);
  }
  
  public boolean o_()
  {
    return !((String)this.Z.a(az)).isEmpty();
  }
  
  public void i(boolean ☃)
  {
    this.Z.b(aA, Boolean.valueOf(☃));
  }
  
  public boolean bg()
  {
    return ((Boolean)this.Z.a(aA)).booleanValue();
  }
  
  public void a(double ☃, double ☃, double ☃)
  {
    this.aG = true;
    b(☃, ☃, ☃, this.v, this.w);
    this.l.a(this, false);
  }
  
  public boolean bh()
  {
    return bg();
  }
  
  public void a(ke<?> ☃) {}
  
  public cq bi()
  {
    return cq.b(on.c(this.v * 4.0F / 360.0F + 0.5D) & 0x3);
  }
  
  public cq bj()
  {
    return bi();
  }
  
  protected ew bk()
  {
    dn ☃ = new dn();
    String ☃ = rt.b(this);
    
    ☃.a("id", bc().toString());
    if (☃ != null) {
      ☃.a("type", ☃);
    }
    ☃.a("name", h_());
    
    return new ew(ew.a.d, new fa(☃.toString()));
  }
  
  public boolean a(lr ☃)
  {
    return true;
  }
  
  public bbh bl()
  {
    return this.at;
  }
  
  public bbh bm()
  {
    return bl();
  }
  
  public void a(bbh ☃)
  {
    this.at = ☃;
  }
  
  public float bn()
  {
    return this.H * 0.85F;
  }
  
  public boolean bo()
  {
    return this.au;
  }
  
  public void j(boolean ☃)
  {
    this.au = ☃;
  }
  
  public boolean c(int ☃, adq ☃)
  {
    return false;
  }
  
  public void a(eu ☃) {}
  
  public boolean a(int ☃, String ☃)
  {
    return true;
  }
  
  public cj c()
  {
    return new cj(this.p, this.q + 0.5D, this.r);
  }
  
  public bbj d()
  {
    return new bbj(this.p, this.q, this.r);
  }
  
  public aht e()
  {
    return this.l;
  }
  
  public rr f()
  {
    return this;
  }
  
  public boolean z_()
  {
    return false;
  }
  
  public void a(n.a ☃, int ☃)
  {
    if ((this.l != null) && (!this.l.E)) {
      this.aD.a(this.l.u(), this, ☃, ☃);
    }
  }
  
  public MinecraftServer h()
  {
    return this.l.u();
  }
  
  public n bp()
  {
    return this.aD;
  }
  
  public void v(rr ☃)
  {
    this.aD.a(☃.bp());
  }
  
  public qo a(zj ☃, bbj ☃, adq ☃, qm ☃)
  {
    return qo.b;
  }
  
  public boolean bq()
  {
    return false;
  }
  
  protected void a(sa ☃, rr ☃)
  {
    if ((☃ instanceof sa)) {
      ago.a((sa)☃, ☃);
    }
    ago.b(☃, ☃);
  }
  
  public void b(lr ☃) {}
  
  public void c(lr ☃) {}
  
  public float a(aoe ☃)
  {
    float ☃ = on.g(this.v);
    switch (rr.5.a[☃.ordinal()])
    {
    case 1: 
      return ☃ + 180.0F;
    case 2: 
      return ☃ + 270.0F;
    case 3: 
      return ☃ + 90.0F;
    }
    return ☃;
  }
  
  public float a(amr ☃)
  {
    float ☃ = on.g(this.v);
    switch (rr.5.b[☃.ordinal()])
    {
    case 1: 
      return -☃;
    case 2: 
      return 180.0F - ☃;
    }
    return ☃;
  }
  
  public boolean br()
  {
    return false;
  }
  
  public boolean bs()
  {
    boolean ☃ = this.aG;
    this.aG = false;
    return ☃;
  }
  
  public rr bt()
  {
    return null;
  }
  
  public List<rr> bu()
  {
    if (this.h.isEmpty()) {
      return Collections.emptyList();
    }
    return Lists.newArrayList(this.h);
  }
  
  public boolean w(rr ☃)
  {
    for (rr ☃ : bu()) {
      if (☃.equals(☃)) {
        return true;
      }
    }
    return false;
  }
  
  public Collection<rr> bv()
  {
    Set<rr> ☃ = Sets.newHashSet();
    a(rr.class, ☃);
    return ☃;
  }
  
  public <T extends rr> Collection<T> b(Class<T> ☃)
  {
    Set<T> ☃ = Sets.newHashSet();
    a(☃, ☃);
    return ☃;
  }
  
  private <T extends rr> void a(Class<T> ☃, Set<T> ☃)
  {
    for (rr ☃ : bu())
    {
      if (☃.isAssignableFrom(☃.getClass())) {
        ☃.add(☃);
      }
      ☃.a(☃, ☃);
    }
  }
  
  public rr bw()
  {
    rr ☃ = this;
    while (☃.aI()) {
      ☃ = ☃.by();
    }
    return ☃;
  }
  
  public boolean x(rr ☃)
  {
    return bw() == ☃.bw();
  }
  
  public boolean y(rr ☃)
  {
    for (rr ☃ : bu())
    {
      if (☃.equals(☃)) {
        return true;
      }
      if (☃.y(☃)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean bx()
  {
    rr ☃ = bt();
    if ((☃ instanceof zj)) {
      return ((zj)☃).cJ();
    }
    return !this.l.E;
  }
  
  public rr by()
  {
    return this.as;
  }
  
  public axh z()
  {
    return axh.a;
  }
  
  public nh bz()
  {
    return nh.g;
  }
}
