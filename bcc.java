import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import org.lwjgl.input.Keyboard;

public class bcc
  implements Comparable<bcc>
{
  private static final List<bcc> a = ;
  private static final oh<bcc> b = new oh();
  private static final Set<String> c = Sets.newHashSet();
  private final String d;
  private final int e;
  private final String f;
  private int g;
  private boolean h;
  private int i;
  
  public static void a(int ☃)
  {
    if (☃ == 0) {
      return;
    }
    bcc ☃ = (bcc)b.a(☃);
    if (☃ != null) {
      ☃.i += 1;
    }
  }
  
  public static void a(int ☃, boolean ☃)
  {
    if (☃ == 0) {
      return;
    }
    bcc ☃ = (bcc)b.a(☃);
    if (☃ != null) {
      ☃.h = ☃;
    }
  }
  
  public static void a()
  {
    for (bcc ☃ : a) {
      try
      {
        a(☃.g, Keyboard.isKeyDown(☃.g));
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {}
    }
  }
  
  public static void b()
  {
    for (bcc ☃ : a) {
      ☃.k();
    }
  }
  
  public static void c()
  {
    b.c();
    for (bcc ☃ : a) {
      b.a(☃.g, ☃);
    }
  }
  
  public static Set<String> d()
  {
    return c;
  }
  
  public bcc(String ☃, int ☃, String ☃)
  {
    this.d = ☃;
    this.g = ☃;
    this.e = ☃;
    this.f = ☃;
    
    a.add(this);
    b.a(☃, this);
    c.add(☃);
  }
  
  public boolean e()
  {
    return this.h;
  }
  
  public String f()
  {
    return this.f;
  }
  
  public boolean g()
  {
    if (this.i == 0) {
      return false;
    }
    this.i -= 1;
    return true;
  }
  
  private void k()
  {
    this.i = 0;
    this.h = false;
  }
  
  public String h()
  {
    return this.d;
  }
  
  public int i()
  {
    return this.e;
  }
  
  public int j()
  {
    return this.g;
  }
  
  public void b(int ☃)
  {
    this.g = ☃;
  }
  
  public int a(bcc ☃)
  {
    int ☃ = bwo.a(this.f, new Object[0]).compareTo(bwo.a(☃.f, new Object[0]));
    if (☃ == 0) {
      ☃ = bwo.a(this.d, new Object[0]).compareTo(bwo.a(☃.d, new Object[0]));
    }
    return ☃;
  }
}
