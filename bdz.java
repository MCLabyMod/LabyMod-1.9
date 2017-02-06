import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import net.minecraft.realms.RealmsButton;
import net.minecraft.realms.RealmsScreen;

public class bdz
  extends bfb
{
  private RealmsScreen a;
  
  public bdz(RealmsScreen ☃)
  {
    this.a = ☃;
    this.n = Collections.synchronizedList(Lists.newArrayList());
  }
  
  public RealmsScreen a()
  {
    return this.a;
  }
  
  public void b()
  {
    this.a.init();
    super.b();
  }
  
  public void a(String ☃, int ☃, int ☃, int ☃)
  {
    super.a(this.q, ☃, ☃, ☃, ☃);
  }
  
  public void a(String ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    if (☃) {
      super.c(this.q, ☃, ☃, ☃, ☃);
    } else {
      this.q.a(☃, ☃, ☃, ☃);
    }
  }
  
  public void b(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    this.a.blit(☃, ☃, ☃, ☃, ☃, ☃);
    super.b(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public void c()
  {
    super.c();
  }
  
  public boolean d()
  {
    return super.d();
  }
  
  public void d_(int ☃)
  {
    super.d_(☃);
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    this.a.render(☃, ☃, ☃);
  }
  
  public void a(adq ☃, int ☃, int ☃)
  {
    super.a(☃, ☃, ☃);
  }
  
  public void a(String ☃, int ☃, int ☃)
  {
    super.a(☃, ☃, ☃);
  }
  
  public void a(List<String> ☃, int ☃, int ☃)
  {
    super.a(☃, ☃, ☃);
  }
  
  public void e()
  {
    this.a.tick();
    super.e();
  }
  
  public int h()
  {
    return this.q.a;
  }
  
  public int c(String ☃)
  {
    return this.q.a(☃);
  }
  
  public void b(String ☃, int ☃, int ☃, int ☃)
  {
    this.q.a(☃, ☃, ☃, ☃);
  }
  
  public List<String> a(String ☃, int ☃)
  {
    return this.q.c(☃, ☃);
  }
  
  public final void a(bcz ☃)
  {
    this.a.buttonClicked(((bdx)☃).f());
  }
  
  public void i()
  {
    this.n.clear();
  }
  
  public void a(RealmsButton ☃)
  {
    this.n.add(☃.getProxy());
  }
  
  public List<RealmsButton> j()
  {
    List<RealmsButton> ☃ = Lists.newArrayListWithExpectedSize(this.n.size());
    for (bcz ☃ : this.n) {
      ☃.add(((bdx)☃).f());
    }
    return ☃;
  }
  
  public void b(RealmsButton ☃)
  {
    this.n.remove(☃.getProxy());
  }
  
  public void a(int ☃, int ☃, int ☃)
  {
    this.a.mouseClicked(☃, ☃, ☃);
    super.a(☃, ☃, ☃);
  }
  
  public void k()
  {
    this.a.mouseEvent();
    super.k();
  }
  
  public void l()
  {
    this.a.keyboardEvent();
    super.l();
  }
  
  public void b(int ☃, int ☃, int ☃)
  {
    this.a.mouseReleased(☃, ☃, ☃);
  }
  
  public void a(int ☃, int ☃, int ☃, long ☃)
  {
    this.a.mouseDragged(☃, ☃, ☃, ☃);
  }
  
  public void a(char ☃, int ☃)
  {
    this.a.keyPressed(☃, ☃);
  }
  
  public void a(boolean ☃, int ☃)
  {
    this.a.confirmResult(☃, ☃);
  }
  
  public void m()
  {
    this.a.removed();
    super.m();
  }
}
