import com.google.common.collect.Lists;
import java.net.InetAddress;
import java.util.Collections;
import java.util.List;

public class byp$c
{
  private List<byp.a> b = Lists.newArrayList();
  boolean a;
  
  public synchronized boolean a()
  {
    return this.a;
  }
  
  public synchronized void b()
  {
    this.a = false;
  }
  
  public synchronized List<byp.a> c()
  {
    return Collections.unmodifiableList(this.b);
  }
  
  public synchronized void a(String ☃, InetAddress ☃)
  {
    String ☃ = byq.a(☃);
    String ☃ = byq.b(☃);
    if (☃ == null) {
      return;
    }
    ☃ = ☃.getHostAddress() + ":" + ☃;
    
    boolean ☃ = false;
    for (byp.a ☃ : this.b) {
      if (☃.b().equals(☃))
      {
        ☃.c();
        ☃ = true;
        break;
      }
    }
    if (!☃)
    {
      this.b.add(new byp.a(☃, ☃));
      this.a = true;
    }
  }
}
