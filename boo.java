import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.lwjgl.util.vector.Vector3f;

public class boo
{
  public static final List<String> a = Lists.newArrayList(new String[] { "layer0", "layer1", "layer2", "layer3", "layer4" });
  
  public bok a(bvg ☃, bok ☃)
  {
    Map<String, String> ☃ = Maps.newHashMap();
    
    List<bog> ☃ = Lists.newArrayList();
    for (int ☃ = 0; ☃ < a.size(); ☃++)
    {
      String ☃ = (String)a.get(☃);
      if (!☃.b(☃)) {
        break;
      }
      String ☃ = ☃.c(☃);
      ☃.put(☃, ☃);
      
      bvh ☃ = ☃.a(new kk(☃).toString());
      ☃.addAll(a(☃, ☃, ☃));
    }
    if (☃.isEmpty()) {
      return null;
    }
    ☃.put("particle", ☃.b("particle") ? ☃.c("particle") : (String)☃.get("layer0"));
    
    return new bok(null, ☃, ☃, false, false, ☃.j(), ☃.f());
  }
  
  private List<bog> a(int ☃, String ☃, bvh ☃)
  {
    Map<cq, boh> ☃ = Maps.newHashMap();
    ☃.put(cq.d, new boh(null, ☃, ☃, new boj(new float[] { 0.0F, 0.0F, 16.0F, 16.0F }, 0)));
    ☃.put(cq.c, new boh(null, ☃, ☃, new boj(new float[] { 16.0F, 0.0F, 0.0F, 16.0F }, 0)));
    
    List<bog> ☃ = Lists.newArrayList();
    ☃.add(new bog(new Vector3f(0.0F, 0.0F, 7.5F), new Vector3f(16.0F, 16.0F, 8.5F), ☃, null, true));
    
    ☃.addAll(a(☃, ☃, ☃));
    
    return ☃;
  }
  
  private List<bog> a(bvh ☃, String ☃, int ☃)
  {
    float ☃ = ☃.c();
    float ☃ = ☃.d();
    List<bog> ☃ = Lists.newArrayList();
    for (boo.a ☃ : a(☃))
    {
      float ☃ = 0.0F;
      float ☃ = 0.0F;
      float ☃ = 0.0F;
      float ☃ = 0.0F;
      float ☃ = 0.0F;
      float ☃ = 0.0F;
      float ☃ = 0.0F;
      float ☃ = 0.0F;
      float ☃ = 0.0F;
      float ☃ = 0.0F;
      
      float ☃ = ☃.b();
      float ☃ = ☃.c();
      float ☃ = ☃.d();
      
      boo.b ☃ = ☃.a();
      switch (boo.1.a[☃.ordinal()])
      {
      case 1: 
        ☃ = ☃ = ☃;
        ☃ = ☃ = ☃ + 1.0F;
        ☃ = ☃ = ☃;
        ☃ = ☃ = ☃;
        
        ☃ = 16.0F / ☃;
        ☃ = 16.0F / (☃ - 1.0F);
        break;
      case 2: 
        ☃ = ☃ = ☃;
        
        ☃ = ☃ = ☃;
        ☃ = ☃ = ☃ + 1.0F;
        ☃ = ☃ + 1.0F;
        ☃ = ☃ + 1.0F;
        
        ☃ = 16.0F / ☃;
        ☃ = 16.0F / (☃ - 1.0F);
        break;
      case 3: 
        ☃ = ☃ = ☃;
        ☃ = ☃ = ☃;
        ☃ = ☃ = ☃;
        ☃ = ☃ = ☃ + 1.0F;
        
        ☃ = 16.0F / (☃ - 1.0F);
        ☃ = 16.0F / ☃;
        break;
      case 4: 
        ☃ = ☃ = ☃;
        
        ☃ = ☃ + 1.0F;
        ☃ = ☃ + 1.0F;
        ☃ = ☃ = ☃;
        ☃ = ☃ = ☃ + 1.0F;
        
        ☃ = 16.0F / (☃ - 1.0F);
        ☃ = 16.0F / ☃;
      }
      float ☃ = 16.0F / ☃;
      float ☃ = 16.0F / ☃;
      
      ☃ *= ☃;
      ☃ *= ☃;
      ☃ *= ☃;
      ☃ *= ☃;
      
      ☃ = 16.0F - ☃;
      ☃ = 16.0F - ☃;
      
      ☃ *= ☃;
      ☃ *= ☃;
      ☃ *= ☃;
      ☃ *= ☃;
      
      Map<cq, boh> ☃ = Maps.newHashMap();
      ☃.put(☃.a(), new boh(null, ☃, ☃, new boj(new float[] { ☃, ☃, ☃, ☃ }, 0)));
      switch (boo.1.a[☃.ordinal()])
      {
      case 1: 
        ☃.add(new bog(new Vector3f(☃, ☃, 7.5F), new Vector3f(☃, ☃, 8.5F), ☃, null, true));
        break;
      case 2: 
        ☃.add(new bog(new Vector3f(☃, ☃, 7.5F), new Vector3f(☃, ☃, 8.5F), ☃, null, true));
        break;
      case 3: 
        ☃.add(new bog(new Vector3f(☃, ☃, 7.5F), new Vector3f(☃, ☃, 8.5F), ☃, null, true));
        break;
      case 4: 
        ☃.add(new bog(new Vector3f(☃, ☃, 7.5F), new Vector3f(☃, ☃, 8.5F), ☃, null, true));
      }
    }
    return ☃;
  }
  
  private List<boo.a> a(bvh ☃)
  {
    int ☃ = ☃.c();
    int ☃ = ☃.d();
    
    List<boo.a> ☃ = Lists.newArrayList();
    for (int ☃ = 0; ☃ < ☃.k(); ☃++)
    {
      int[] ☃ = ☃.a(☃)[0];
      for (int ☃ = 0; ☃ < ☃; ☃++) {
        for (int ☃ = 0; ☃ < ☃; ☃++)
        {
          boolean ☃ = !a(☃, ☃, ☃, ☃, ☃);
          
          a(boo.b.a, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
          a(boo.b.b, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
          a(boo.b.c, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
          a(boo.b.d, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
        }
      }
    }
    return ☃;
  }
  
  private void a(boo.b ☃, List<boo.a> ☃, int[] ☃, int ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    boolean ☃ = (a(☃, ☃ + ☃.b(), ☃ + ☃.c(), ☃, ☃)) && (☃);
    if (☃) {
      a(☃, ☃, ☃, ☃);
    }
  }
  
  private void a(List<boo.a> ☃, boo.b ☃, int ☃, int ☃)
  {
    boo.a ☃ = null;
    for (boo.a ☃ : ☃) {
      if (☃.a() == ☃)
      {
        int ☃ = boo.b.a(☃) ? ☃ : ☃;
        if (☃.d() == ☃)
        {
          ☃ = ☃;
          break;
        }
      }
    }
    int ☃ = boo.b.a(☃) ? ☃ : ☃;
    int ☃ = boo.b.a(☃) ? ☃ : ☃;
    if (☃ == null) {
      ☃.add(new boo.a(☃, ☃, ☃));
    } else {
      ☃.a(☃);
    }
  }
  
  private boolean a(int[] ☃, int ☃, int ☃, int ☃, int ☃)
  {
    if ((☃ < 0) || (☃ < 0) || (☃ >= ☃) || (☃ >= ☃)) {
      return true;
    }
    return (☃[(☃ * ☃ + ☃)] >> 24 & 0xFF) == 0;
  }
  
  static enum b
  {
    private final cq e;
    private final int f;
    private final int g;
    
    private b(cq ☃, int ☃, int ☃)
    {
      this.e = ☃;
      this.f = ☃;
      this.g = ☃;
    }
    
    public cq a()
    {
      return this.e;
    }
    
    public int b()
    {
      return this.f;
    }
    
    public int c()
    {
      return this.g;
    }
    
    private boolean d()
    {
      return (this == b) || (this == a);
    }
  }
  
  static class a
  {
    private final boo.b a;
    private int b;
    private int c;
    private final int d;
    
    public a(boo.b ☃, int ☃, int ☃)
    {
      this.a = ☃;
      this.b = ☃;
      this.c = ☃;
      this.d = ☃;
    }
    
    public void a(int ☃)
    {
      if (☃ < this.b) {
        this.b = ☃;
      } else if (☃ > this.c) {
        this.c = ☃;
      }
    }
    
    public boo.b a()
    {
      return this.a;
    }
    
    public int b()
    {
      return this.b;
    }
    
    public int c()
    {
      return this.c;
    }
    
    public int d()
    {
      return this.d;
    }
  }
}
