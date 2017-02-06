import com.google.common.collect.Sets;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Set;

public class azz
{
  private final float a;
  private final lp b;
  private final bab c;
  private final rr d;
  private final zj e;
  private final rc f;
  private final Set<azy> g = Sets.newLinkedHashSet();
  
  public azz(float ☃, lp ☃, bab ☃, rr ☃, zj ☃, rc ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
    this.f = ☃;
  }
  
  public rr a()
  {
    return this.d;
  }
  
  public rr b()
  {
    return this.e;
  }
  
  public rr c()
  {
    return this.f == null ? null : this.f.j();
  }
  
  public boolean a(azy ☃)
  {
    return this.g.add(☃);
  }
  
  public void b(azy ☃)
  {
    this.g.remove(☃);
  }
  
  public bab e()
  {
    return this.c;
  }
  
  public float f()
  {
    return this.a;
  }
  
  public rr a(azz.b ☃)
  {
    switch (azz.1.a[☃.ordinal()])
    {
    case 1: 
      return a();
    case 2: 
      return c();
    case 3: 
      return b();
    }
    return null;
  }
  
  public static class a
  {
    private final lp a;
    private float b;
    private rr c;
    private zj d;
    private rc e;
    
    public a(lp ☃)
    {
      this.a = ☃;
    }
    
    public a a(float ☃)
    {
      this.b = ☃;
      return this;
    }
    
    public a a(rr ☃)
    {
      this.c = ☃;
      return this;
    }
    
    public a a(zj ☃)
    {
      this.d = ☃;
      return this;
    }
    
    public a a(rc ☃)
    {
      this.e = ☃;
      return this;
    }
    
    public azz a()
    {
      return new azz(this.b, this.a, this.a.ak(), this.c, this.d, this.e);
    }
  }
  
  public static enum b
  {
    private final String d;
    
    private b(String ☃)
    {
      this.d = ☃;
    }
    
    public static b a(String ☃)
    {
      for (b ☃ : ) {
        if (☃.d.equals(☃)) {
          return ☃;
        }
      }
      throw new IllegalArgumentException("Invalid entity target " + ☃);
    }
    
    public static class a
      extends TypeAdapter<azz.b>
    {
      public void a(JsonWriter ☃, azz.b ☃)
        throws IOException
      {
        ☃.value(azz.b.a(☃));
      }
      
      public azz.b a(JsonReader ☃)
        throws IOException
      {
        return azz.b.a(☃.nextString());
      }
    }
  }
}
