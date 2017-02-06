import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import org.lwjgl.util.vector.Matrix4f;

public class buq
{
  private final buo c;
  public final bnt a;
  public final bnt b;
  private final List<Object> d = Lists.newArrayList();
  private final List<String> e = Lists.newArrayList();
  private final List<Integer> f = Lists.newArrayList();
  private final List<Integer> g = Lists.newArrayList();
  private Matrix4f h;
  
  public buq(bwg ☃, String ☃, bnt ☃, bnt ☃)
    throws IOException
  {
    this.c = new buo(☃, ☃);
    this.a = ☃;
    this.b = ☃;
  }
  
  public void b()
  {
    this.c.a();
  }
  
  public void a(String ☃, Object ☃, int ☃, int ☃)
  {
    this.e.add(this.e.size(), ☃);
    this.d.add(this.d.size(), ☃);
    this.f.add(this.f.size(), Integer.valueOf(☃));
    this.g.add(this.g.size(), Integer.valueOf(☃));
  }
  
  private void d()
  {
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.l();
    bni.j();
    bni.d();
    bni.p();
    bni.g();
    bni.i();
    bni.y();
    bni.i(0);
  }
  
  public void a(Matrix4f ☃)
  {
    this.h = ☃;
  }
  
  public void a(float ☃)
  {
    d();
    this.a.e();
    
    float ☃ = this.b.a;
    float ☃ = this.b.b;
    bni.b(0, 0, (int)☃, (int)☃);
    
    this.c.a("DiffuseSampler", this.a);
    for (int ☃ = 0; ☃ < this.d.size(); ☃++)
    {
      this.c.a((String)this.e.get(☃), this.d.get(☃));
      this.c.b("AuxSize" + ☃).a(((Integer)this.f.get(☃)).intValue(), ((Integer)this.g.get(☃)).intValue());
    }
    this.c.b("ProjMat").a(this.h);
    this.c.b("InSize").a(this.a.a, this.a.b);
    this.c.b("OutSize").a(☃, ☃);
    this.c.b("Time").a(☃);
    
    bcf ☃ = bcf.z();
    this.c.b("ScreenSize").a(☃.d, ☃.e);
    this.c.c();
    this.b.f();
    this.b.a(false);
    
    bni.a(false);
    bni.a(true, true, true, true);
    
    bnu ☃ = bnu.a();
    bmz ☃ = ☃.c();
    ☃.a(7, bvp.f);
    ☃.b(0.0D, ☃, 500.0D).b(255, 255, 255, 255).d();
    ☃.b(☃, ☃, 500.0D).b(255, 255, 255, 255).d();
    ☃.b(☃, 0.0D, 500.0D).b(255, 255, 255, 255).d();
    ☃.b(0.0D, 0.0D, 500.0D).b(255, 255, 255, 255).d();
    ☃.b();
    
    bni.a(true);
    bni.a(true, true, true, true);
    
    this.c.b();
    this.b.e();
    this.a.d();
    for (Object ☃ : this.d) {
      if ((☃ instanceof bnt)) {
        ((bnt)☃).d();
      }
    }
  }
  
  public buo c()
  {
    return this.c;
  }
}
