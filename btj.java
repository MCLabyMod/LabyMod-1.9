import com.google.common.collect.Maps;
import java.util.Map;

public abstract class btj<T extends bjc>
  implements bty<sa>
{
  protected static final kk b = new kk("textures/misc/enchanted_item_glint.png");
  protected T c;
  protected T d;
  private final bsd<?> a;
  private float e = 1.0F;
  private float f = 1.0F;
  private float g = 1.0F;
  private float h = 1.0F;
  private boolean i;
  
  public btj(bsd<?> ☃)
  {
    this.a = ☃;
    
    I_();
  }
  
  public void a(sa ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, rw.e);
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, rw.d);
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, rw.c);
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, rw.f);
  }
  
  public boolean a()
  {
    return false;
  }
  
  private void a(sa ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rw ☃)
  {
    adq ☃ = a(☃, ☃);
    if ((☃ == null) || (!(☃.b() instanceof abw))) {
      return;
    }
    abw ☃ = (abw)☃.b();
    if (☃.B_() != ☃) {
      return;
    }
    T ☃ = a(☃);
    
    ☃.a(this.a.b());
    ☃.a(☃, ☃, ☃, ☃);
    
    a(☃, ☃);
    
    boolean ☃ = b(☃);
    this.a.a(a(☃, ☃));
    switch (btj.1.a[☃.d().ordinal()])
    {
    case 1: 
      int ☃ = ☃.b(☃);
      float ☃ = (☃ >> 16 & 0xFF) / 255.0F;
      float ☃ = (☃ >> 8 & 0xFF) / 255.0F;
      float ☃ = (☃ & 0xFF) / 255.0F;
      
      bni.c(this.f * ☃, this.g * ☃, this.h * ☃, this.e);
      ☃.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
      
      this.a.a(a(☃, ☃, "overlay"));
    case 2: 
    case 3: 
    case 4: 
    case 5: 
      bni.c(this.f, this.g, this.h, this.e);
      ☃.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
    if ((!this.i) && (☃.w())) {
      a(this.a, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
  
  public adq a(sa ☃, rw ☃)
  {
    return ☃.a(☃);
  }
  
  public T a(rw ☃)
  {
    return b(☃) ? this.c : this.d;
  }
  
  private boolean b(rw ☃)
  {
    return ☃ == rw.d;
  }
  
  public static void a(bsd<?> ☃, sa ☃, bjc ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    float ☃ = ☃.T + ☃;
    ☃.a(b);
    
    bni.m();
    bni.c(514);
    bni.a(false);
    
    float ☃ = 0.5F;
    bni.c(☃, ☃, ☃, 1.0F);
    for (int ☃ = 0; ☃ < 2; ☃++)
    {
      bni.g();
      bni.a(bni.r.n, bni.l.e);
      
      float ☃ = 0.76F;
      bni.c(0.5F * ☃, 0.25F * ☃, 0.8F * ☃, 1.0F);
      
      bni.n(5890);
      bni.F();
      
      float ☃ = 0.33333334F;
      bni.b(☃, ☃, ☃);
      
      bni.b(30.0F - ☃ * 60.0F, 0.0F, 0.0F, 1.0F);
      bni.c(0.0F, ☃ * (0.001F + ☃ * 0.003F) * 20.0F, 0.0F);
      bni.n(5888);
      
      ☃.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
    bni.n(5890);
    bni.F();
    bni.n(5888);
    
    bni.f();
    
    bni.a(true);
    bni.c(515);
    bni.l();
  }
  
  private static final Map<String, kk> j = Maps.newHashMap();
  
  private kk a(abw ☃, boolean ☃)
  {
    return a(☃, ☃, null);
  }
  
  private kk a(abw ☃, boolean ☃, String ☃)
  {
    String ☃ = String.format("textures/models/armor/%s_layer_%d%s.png", new Object[] { ☃.d().d(), Integer.valueOf(☃ ? 2 : 1), ☃ == null ? "" : String.format("_%s", new Object[] { ☃ }) });
    
    kk ☃ = (kk)j.get(☃);
    if (☃ == null)
    {
      ☃ = new kk(☃);
      j.put(☃, ☃);
    }
    return ☃;
  }
  
  protected abstract void I_();
  
  protected abstract void a(T paramT, rw paramrw);
}
