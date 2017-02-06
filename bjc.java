import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class bjc
{
  public float o;
  public boolean p;
  public boolean q = true;
  public List<bkm> r = Lists.newArrayList();
  private Map<String, bkn> a = Maps.newHashMap();
  public int s = 64;
  public int t = 32;
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃) {}
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃) {}
  
  public void a(sa ☃, float ☃, float ☃, float ☃) {}
  
  public bkm a(Random ☃)
  {
    return (bkm)this.r.get(☃.nextInt(this.r.size()));
  }
  
  protected void a(String ☃, int ☃, int ☃)
  {
    this.a.put(☃, new bkn(☃, ☃));
  }
  
  public bkn a(String ☃)
  {
    return (bkn)this.a.get(☃);
  }
  
  public static void a(bkm ☃, bkm ☃)
  {
    ☃.f = ☃.f;
    ☃.g = ☃.g;
    ☃.h = ☃.h;
    ☃.c = ☃.c;
    ☃.d = ☃.d;
    ☃.e = ☃.e;
  }
  
  public void a(bjc ☃)
  {
    this.o = ☃.o;
    this.p = ☃.p;
    this.q = ☃.q;
  }
}
