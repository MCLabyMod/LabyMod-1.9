import com.google.common.base.Optional;
import java.util.UUID;

public class kg
{
  private static final oa<kf<?>> n = new oa(16);
  public static final kf<Byte> a = new kf()
  {
    public void a(em ☃, Byte ☃)
    {
      ☃.writeByte(☃.byteValue());
    }
    
    public Byte b(em ☃)
    {
      return Byte.valueOf(☃.readByte());
    }
    
    public ke<Byte> a(int ☃)
    {
      return new ke(☃, this);
    }
  };
  public static final kf<Integer> b = new kf()
  {
    public void a(em ☃, Integer ☃)
    {
      ☃.b(☃.intValue());
    }
    
    public Integer b(em ☃)
    {
      return Integer.valueOf(☃.g());
    }
    
    public ke<Integer> a(int ☃)
    {
      return new ke(☃, this);
    }
  };
  public static final kf<Float> c = new kf()
  {
    public void a(em ☃, Float ☃)
    {
      ☃.writeFloat(☃.floatValue());
    }
    
    public Float b(em ☃)
    {
      return Float.valueOf(☃.readFloat());
    }
    
    public ke<Float> a(int ☃)
    {
      return new ke(☃, this);
    }
  };
  public static final kf<String> d = new kf()
  {
    public void a(em ☃, String ☃)
    {
      ☃.a(☃);
    }
    
    public String b(em ☃)
    {
      return ☃.c(32767);
    }
    
    public ke<String> a(int ☃)
    {
      return new ke(☃, this);
    }
  };
  public static final kf<eu> e = new kf()
  {
    public void a(em ☃, eu ☃)
    {
      ☃.a(☃);
    }
    
    public eu b(em ☃)
    {
      return ☃.f();
    }
    
    public ke<eu> a(int ☃)
    {
      return new ke(☃, this);
    }
  };
  public static final kf<Optional<adq>> f = new kf()
  {
    public void a(em ☃, Optional<adq> ☃)
    {
      ☃.a((adq)☃.orNull());
    }
    
    public Optional<adq> b(em ☃)
    {
      return Optional.fromNullable(☃.k());
    }
    
    public ke<Optional<adq>> a(int ☃)
    {
      return new ke(☃, this);
    }
  };
  public static final kf<Optional<arc>> g = new kf()
  {
    public void a(em ☃, Optional<arc> ☃)
    {
      if (☃.isPresent()) {
        ☃.b(ajt.j((arc)☃.get()));
      } else {
        ☃.b(0);
      }
    }
    
    public Optional<arc> b(em ☃)
    {
      int ☃ = ☃.g();
      if (☃ == 0) {
        return Optional.absent();
      }
      return Optional.of(ajt.c(☃));
    }
    
    public ke<Optional<arc>> a(int ☃)
    {
      return new ke(☃, this);
    }
  };
  public static final kf<Boolean> h = new kf()
  {
    public void a(em ☃, Boolean ☃)
    {
      ☃.writeBoolean(☃.booleanValue());
    }
    
    public Boolean b(em ☃)
    {
      return Boolean.valueOf(☃.readBoolean());
    }
    
    public ke<Boolean> a(int ☃)
    {
      return new ke(☃, this);
    }
  };
  public static final kf<dc> i = new kf()
  {
    public void a(em ☃, dc ☃)
    {
      ☃.writeFloat(☃.b());
      ☃.writeFloat(☃.c());
      ☃.writeFloat(☃.d());
    }
    
    public dc b(em ☃)
    {
      return new dc(☃.readFloat(), ☃.readFloat(), ☃.readFloat());
    }
    
    public ke<dc> a(int ☃)
    {
      return new ke(☃, this);
    }
  };
  public static final kf<cj> j = new kf()
  {
    public void a(em ☃, cj ☃)
    {
      ☃.a(☃);
    }
    
    public cj b(em ☃)
    {
      return ☃.e();
    }
    
    public ke<cj> a(int ☃)
    {
      return new ke(☃, this);
    }
  };
  public static final kf<Optional<cj>> k = new kf()
  {
    public void a(em ☃, Optional<cj> ☃)
    {
      ☃.writeBoolean(☃.isPresent());
      if (☃.isPresent()) {
        ☃.a((cj)☃.get());
      }
    }
    
    public Optional<cj> b(em ☃)
    {
      if (!☃.readBoolean()) {
        return Optional.absent();
      }
      return Optional.of(☃.e());
    }
    
    public ke<Optional<cj>> a(int ☃)
    {
      return new ke(☃, this);
    }
  };
  public static final kf<cq> l = new kf()
  {
    public void a(em ☃, cq ☃)
    {
      ☃.a(☃);
    }
    
    public cq b(em ☃)
    {
      return (cq)☃.a(cq.class);
    }
    
    public ke<cq> a(int ☃)
    {
      return new ke(☃, this);
    }
  };
  public static final kf<Optional<UUID>> m = new kf()
  {
    public void a(em ☃, Optional<UUID> ☃)
    {
      ☃.writeBoolean(☃.isPresent());
      if (☃.isPresent()) {
        ☃.a((UUID)☃.get());
      }
    }
    
    public Optional<UUID> b(em ☃)
    {
      if (!☃.readBoolean()) {
        return Optional.absent();
      }
      return Optional.of(☃.i());
    }
    
    public ke<Optional<UUID>> a(int ☃)
    {
      return new ke(☃, this);
    }
  };
  
  static
  {
    a(a);
    a(b);
    a(c);
    a(d);
    a(e);
    a(f);
    a(h);
    a(i);
    a(j);
    a(k);
    a(l);
    a(m);
    a(g);
  }
  
  public static void a(kf<?> ☃)
  {
    n.c(☃);
  }
  
  public static kf<?> a(int ☃)
  {
    return (kf)n.a(☃);
  }
  
  public static int b(kf<?> ☃)
  {
    return n.a(☃);
  }
}
