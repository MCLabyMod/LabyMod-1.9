import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class bph
  extends bpe
{
  private final arr<?> a;
  private final String c;
  private final List<arr<?>> d;
  
  private bph(arr<?> ☃, String ☃, List<arr<?>> ☃)
  {
    this.a = ☃;
    this.c = ☃;
    this.d = ☃;
  }
  
  protected bxt a(arc ☃)
  {
    Map<arr<?>, Comparable<?>> ☃ = Maps.newLinkedHashMap(☃.s());
    String ☃;
    String ☃;
    if (this.a == null) {
      ☃ = ((kk)ajt.h.b(☃.t())).toString();
    } else {
      ☃ = a(this.a, ☃);
    }
    if (this.c != null) {
      ☃ = ☃ + this.c;
    }
    for (arr<?> ☃ : this.d) {
      ☃.remove(☃);
    }
    return new bxt(☃, a(☃));
  }
  
  private <T extends Comparable<T>> String a(arr<T> ☃, Map<arr<?>, Comparable<?>> ☃)
  {
    return ☃.a((Comparable)☃.remove(this.a));
  }
  
  public static class a
  {
    private arr<?> a;
    private String b;
    private final List<arr<?>> c = Lists.newArrayList();
    
    public a a(arr<?> ☃)
    {
      this.a = ☃;
      return this;
    }
    
    public a a(String ☃)
    {
      this.b = ☃;
      return this;
    }
    
    public a a(arr<?>... ☃)
    {
      Collections.addAll(this.c, ☃);
      return this;
    }
    
    public bph a()
    {
      return new bph(this.a, this.b, this.c, null);
    }
  }
}
