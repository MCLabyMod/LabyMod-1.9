import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import java.io.IOException;
import java.util.List;

public class gz
  implements ff<fi>
{
  private gz.a a;
  private final List<gz.b> b = Lists.newArrayList();
  
  public gz() {}
  
  public gz(gz.a ☃, lr... ☃)
  {
    this.a = ☃;
    for (lr ☃ : ☃) {
      this.b.add(new gz.b(☃.cK(), ☃.g, ☃.c.b(), ☃.J()));
    }
  }
  
  public gz(gz.a ☃, Iterable<lr> ☃)
  {
    this.a = ☃;
    for (lr ☃ : ☃) {
      this.b.add(new gz.b(☃.cK(), ☃.g, ☃.c.b(), ☃.J()));
    }
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ((gz.a)☃.a(gz.a.class));
    
    int ☃ = ☃.g();
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      GameProfile ☃ = null;
      int ☃ = 0;
      ahw.a ☃ = null;
      eu ☃ = null;
      switch (gz.1.a[this.a.ordinal()])
      {
      case 1: 
        ☃ = new GameProfile(☃.i(), ☃.c(16));
        int ☃ = ☃.g();
        for (int ☃ = 0; ☃ < ☃; ☃++)
        {
          String ☃ = ☃.c(32767);
          String ☃ = ☃.c(32767);
          if (☃.readBoolean()) {
            ☃.getProperties().put(☃, new Property(☃, ☃, ☃.c(32767)));
          } else {
            ☃.getProperties().put(☃, new Property(☃, ☃));
          }
        }
        ☃ = ahw.a.a(☃.g());
        ☃ = ☃.g();
        if (☃.readBoolean()) {
          ☃ = ☃.f();
        }
        break;
      case 2: 
        ☃ = new GameProfile(☃.i(), null);
        ☃ = ahw.a.a(☃.g());
        break;
      case 3: 
        ☃ = new GameProfile(☃.i(), null);
        ☃ = ☃.g();
        break;
      case 4: 
        ☃ = new GameProfile(☃.i(), null);
        if (☃.readBoolean()) {
          ☃ = ☃.f();
        }
        break;
      case 5: 
        ☃ = new GameProfile(☃.i(), null);
      }
      this.b.add(new gz.b(☃, ☃, ☃, ☃));
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(this.a);
    
    ☃.b(this.b.size());
    for (gz.b ☃ : this.b) {
      switch (gz.1.a[this.a.ordinal()])
      {
      case 1: 
        ☃.a(☃.a().getId());
        ☃.a(☃.a().getName());
        ☃.b(☃.a().getProperties().size());
        for (Property ☃ : ☃.a().getProperties().values())
        {
          ☃.a(☃.getName());
          ☃.a(☃.getValue());
          if (☃.hasSignature())
          {
            ☃.writeBoolean(true);
            ☃.a(☃.getSignature());
          }
          else
          {
            ☃.writeBoolean(false);
          }
        }
        ☃.b(☃.c().a());
        ☃.b(☃.b());
        if (☃.d() == null)
        {
          ☃.writeBoolean(false);
        }
        else
        {
          ☃.writeBoolean(true);
          ☃.a(☃.d());
        }
        break;
      case 2: 
        ☃.a(☃.a().getId());
        ☃.b(☃.c().a());
        break;
      case 3: 
        ☃.a(☃.a().getId());
        ☃.b(☃.b());
        break;
      case 4: 
        ☃.a(☃.a().getId());
        if (☃.d() == null)
        {
          ☃.writeBoolean(false);
        }
        else
        {
          ☃.writeBoolean(true);
          ☃.a(☃.d());
        }
        break;
      case 5: 
        ☃.a(☃.a().getId());
      }
    }
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public List<gz.b> a()
  {
    return this.b;
  }
  
  public gz.a b()
  {
    return this.a;
  }
  
  public String toString()
  {
    return Objects.toStringHelper(this).add("action", this.a).add("entries", this.b).toString();
  }
  
  public class b
  {
    private final int b;
    private final ahw.a c;
    private final GameProfile d;
    private final eu e;
    
    public b(GameProfile ☃, int ☃, ahw.a ☃, eu ☃)
    {
      this.d = ☃;
      this.b = ☃;
      this.c = ☃;
      this.e = ☃;
    }
    
    public GameProfile a()
    {
      return this.d;
    }
    
    public int b()
    {
      return this.b;
    }
    
    public ahw.a c()
    {
      return this.c;
    }
    
    public eu d()
    {
      return this.e;
    }
    
    public String toString()
    {
      return Objects.toStringHelper(this).add("latency", this.b).add("gameMode", this.c).add("profile", this.d).add("displayName", this.e == null ? null : eu.a.a(this.e)).toString();
    }
  }
  
  public static enum a
  {
    private a() {}
  }
}
