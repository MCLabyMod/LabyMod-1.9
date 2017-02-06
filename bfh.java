import com.google.common.collect.Lists;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public abstract class bfh
{
  protected final bdd a;
  protected final boolean b;
  protected boolean c;
  protected boolean d;
  protected int e;
  protected List<String> f = Lists.newArrayList();
  
  public bfh(bdd ☃, boolean ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public void a()
  {
    if (this.c)
    {
      this.a.b(this.a.a(-1, this.a.i(), false) - this.a.i());
      if (this.e >= this.f.size()) {
        this.e = 0;
      }
    }
    else
    {
      int ☃ = this.a.a(-1, this.a.i(), false);
      
      this.f.clear();
      this.e = 0;
      String ☃ = this.a.b().substring(0, this.a.i());
      
      a(☃);
      if (this.f.isEmpty()) {
        return;
      }
      this.c = true;
      
      this.a.b(☃ - this.a.i());
    }
    this.a.b((String)this.f.get(this.e++));
  }
  
  private void a(String ☃)
  {
    if (☃.length() < 1) {
      return;
    }
    bcf.z().h.d.a(new ii(☃, b(), this.b));
    this.d = true;
  }
  
  public abstract cj b();
  
  public void a(String[] ☃)
  {
    if (!this.d) {
      return;
    }
    this.c = false;
    this.f.clear();
    for (String ☃ : ☃) {
      if (!☃.isEmpty()) {
        this.f.add(☃);
      }
    }
    String ☃ = this.a.b().substring(this.a.a(-1, this.a.i(), false));
    String ☃ = StringUtils.getCommonPrefix(☃);
    if ((!☃.isEmpty()) && (!☃.equalsIgnoreCase(☃)))
    {
      this.a.b(this.a.a(-1, this.a.i(), false) - this.a.i());
      this.a.b(☃);
    }
    else if (!this.f.isEmpty())
    {
      this.c = true;
      a();
    }
  }
  
  public void c()
  {
    this.c = false;
  }
  
  public void d()
  {
    this.d = false;
  }
}
