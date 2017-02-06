import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public enum cq
  implements or
{
  private final int g;
  private final int h;
  private final int i;
  private final String j;
  private final cq.a k;
  private final cq.b l;
  private final df m;
  public static final cq[] n;
  private static final cq[] o;
  private static final Map<String, cq> p;
  
  private cq(int indexIn, int oppositeIn, int horizontalIndexIn, String nameIn, cq.b axisDirectionIn, cq.a axisIn, df directionVecIn)
  {
    this.g = indexIn;
    this.i = horizontalIndexIn;
    this.h = oppositeIn;
    this.j = nameIn;
    this.k = axisIn;
    this.l = axisDirectionIn;
    this.m = directionVecIn;
  }
  
  public int a()
  {
    return this.g;
  }
  
  public int b()
  {
    return this.i;
  }
  
  public cq.b c()
  {
    return this.l;
  }
  
  public cq d()
  {
    return n[this.h];
  }
  
  public cq a(cq.a axis)
  {
    switch (axis)
    {
    case a: 
      if ((this != e) && (this != f)) {
        return o();
      }
      return this;
    case b: 
      if ((this != b) && (this != a)) {
        return e();
      }
      return this;
    case c: 
      if ((this != c) && (this != d)) {
        return q();
      }
      return this;
    }
    throw new IllegalStateException("Unable to get CW facing for axis " + axis);
  }
  
  public cq e()
  {
    switch (this)
    {
    case c: 
      return f;
    case f: 
      return d;
    case d: 
      return e;
    case e: 
      return c;
    }
    throw new IllegalStateException("Unable to get Y-rotated facing of " + this);
  }
  
  private cq o()
  {
    switch (this)
    {
    case c: 
      return a;
    case f: 
    case e: 
    default: 
      throw new IllegalStateException("Unable to get X-rotated facing of " + this);
    case d: 
      return b;
    case b: 
      return c;
    }
    return d;
  }
  
  private cq q()
  {
    switch (this)
    {
    case f: 
      return a;
    case d: 
    default: 
      throw new IllegalStateException("Unable to get Z-rotated facing of " + this);
    case e: 
      return b;
    case b: 
      return f;
    }
    return e;
  }
  
  public cq f()
  {
    switch (this)
    {
    case c: 
      return e;
    case f: 
      return c;
    case d: 
      return f;
    case e: 
      return d;
    }
    throw new IllegalStateException("Unable to get CCW facing of " + this);
  }
  
  public int g()
  {
    return this.k == cq.a.a ? this.l.a() : 0;
  }
  
  public int h()
  {
    return this.k == cq.a.b ? this.l.a() : 0;
  }
  
  public int i()
  {
    return this.k == cq.a.c ? this.l.a() : 0;
  }
  
  public String j()
  {
    return this.j;
  }
  
  public cq.a k()
  {
    return this.k;
  }
  
  public static cq a(String name)
  {
    return name == null ? null : (cq)p.get(name.toLowerCase());
  }
  
  public static cq a(int index)
  {
    return n[on.a(index % n.length)];
  }
  
  public static cq b(int p_176731_0_)
  {
    return o[on.a(p_176731_0_ % o.length)];
  }
  
  public static cq a(double angle)
  {
    return b(on.c(angle / 90.0D + 0.5D) & 0x3);
  }
  
  public float l()
  {
    return (this.i & 0x3) * 90;
  }
  
  public static cq a(Random rand)
  {
    return values()[rand.nextInt(values().length)];
  }
  
  public static cq a(float p_176737_0_, float p_176737_1_, float p_176737_2_)
  {
    cq enumfacing = c;
    float f = Float.MIN_VALUE;
    for (cq enumfacing1 : values())
    {
      float f1 = p_176737_0_ * enumfacing1.m.p() + p_176737_1_ * enumfacing1.m.q() + p_176737_2_ * enumfacing1.m.r();
      if (f1 > f)
      {
        f = f1;
        enumfacing = enumfacing1;
      }
    }
    return enumfacing;
  }
  
  public String toString()
  {
    return this.j;
  }
  
  public String m()
  {
    return this.j;
  }
  
  public static cq a(cq.b p_181076_0_, cq.a p_181076_1_)
  {
    for (cq enumfacing : ) {
      if ((enumfacing.c() == p_181076_0_) && (enumfacing.k() == p_181076_1_)) {
        return enumfacing;
      }
    }
    throw new IllegalArgumentException("No such direction: " + p_181076_0_ + " " + p_181076_1_);
  }
  
  public df n()
  {
    return this.m;
  }
  
  static
  {
    n = new cq[6];
    
    o = new cq[4];
    p = Maps.newHashMap();
    for (cq enumfacing : values())
    {
      n[enumfacing.g] = enumfacing;
      if (enumfacing.k().c()) {
        o[enumfacing.i] = enumfacing;
      }
      p.put(enumfacing.j().toLowerCase(), enumfacing);
    }
  }
  
  public static enum a
    implements Predicate<cq>, or
  {
    private static final Map<String, a> d;
    private final String e;
    private final cq.c f;
    
    private a(String name, cq.c plane)
    {
      this.e = name;
      this.f = plane;
    }
    
    public static a a(String name)
    {
      return name == null ? null : (a)d.get(name.toLowerCase());
    }
    
    public String a()
    {
      return this.e;
    }
    
    public boolean b()
    {
      return this.f == cq.c.b;
    }
    
    public boolean c()
    {
      return this.f == cq.c.a;
    }
    
    public String toString()
    {
      return this.e;
    }
    
    public boolean a(cq p_apply_1_)
    {
      return (p_apply_1_ != null) && (p_apply_1_.k() == this);
    }
    
    public cq.c d()
    {
      return this.f;
    }
    
    public String m()
    {
      return this.e;
    }
    
    static
    {
      d = Maps.newHashMap();
      for (a enumfacing$axis : values()) {
        d.put(enumfacing$axis.a().toLowerCase(), enumfacing$axis);
      }
    }
  }
  
  public static enum b
  {
    private final int c;
    private final String d;
    
    private b(int offset, String description)
    {
      this.c = offset;
      this.d = description;
    }
    
    public int a()
    {
      return this.c;
    }
    
    public String toString()
    {
      return this.d;
    }
  }
  
  public static enum c
    implements Predicate<cq>, Iterable<cq>
  {
    private c() {}
    
    public cq[] a()
    {
      switch (cq.1.$SwitchMap$net$minecraft$util$EnumFacing$Plane[ordinal()])
      {
      case 1: 
        return new cq[] { cq.c, cq.f, cq.d, cq.e };
      case 2: 
        return new cq[] { cq.b, cq.a };
      }
      throw new Error("Someone's been tampering with the universe!");
    }
    
    public cq a(Random rand)
    {
      cq[] aenumfacing = a();
      return aenumfacing[rand.nextInt(aenumfacing.length)];
    }
    
    public boolean a(cq p_apply_1_)
    {
      return (p_apply_1_ != null) && (p_apply_1_.k().d() == this);
    }
    
    public Iterator<cq> iterator()
    {
      return Iterators.forArray(a());
    }
  }
}
