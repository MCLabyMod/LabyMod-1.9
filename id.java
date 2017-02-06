import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class id
  implements ff<fi>
{
  private int a;
  private final List<id.a> b = Lists.newArrayList();
  
  public id() {}
  
  public id(int ☃, Collection<sm> ☃)
  {
    this.a = ☃;
    for (sm ☃ : ☃) {
      this.b.add(new id.a(☃.a().a(), ☃.b(), ☃.c()));
    }
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    
    int ☃ = ☃.readInt();
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      String ☃ = ☃.c(64);
      double ☃ = ☃.readDouble();
      List<sn> ☃ = Lists.newArrayList();
      int ☃ = ☃.g();
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        UUID ☃ = ☃.i();
        ☃.add(new sn(☃, "Unknown synced attribute modifier", ☃.readDouble(), ☃.readByte()));
      }
      this.b.add(new id.a(☃, ☃, ☃));
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
    ☃.writeInt(this.b.size());
    for (id.a ☃ : this.b)
    {
      ☃.a(☃.a());
      ☃.writeDouble(☃.b());
      ☃.b(☃.c().size());
      for (sn ☃ : ☃.c())
      {
        ☃.a(☃.a());
        ☃.writeDouble(☃.d());
        ☃.writeByte(☃.c());
      }
    }
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public List<id.a> b()
  {
    return this.b;
  }
  
  public class a
  {
    private final String b;
    private final double c;
    private final Collection<sn> d;
    
    public a(double ☃, Collection<sn> arg4)
    {
      this.b = ☃;
      this.c = ☃;
      this.d = ☃;
    }
    
    public String a()
    {
      return this.b;
    }
    
    public double b()
    {
      return this.c;
    }
    
    public Collection<sn> c()
    {
      return this.d;
    }
  }
}
