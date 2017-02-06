import com.google.common.collect.Maps;
import java.util.Map;

public class bmw
  extends qv
  implements qs
{
  private String a;
  private Map<Integer, Integer> b = Maps.newHashMap();
  
  public bmw(String ☃, eu ☃, int ☃)
  {
    super(☃, ☃);
    this.a = ☃;
  }
  
  public int c_(int ☃)
  {
    if (this.b.containsKey(Integer.valueOf(☃))) {
      return ((Integer)this.b.get(Integer.valueOf(☃))).intValue();
    }
    return 0;
  }
  
  public void b(int ☃, int ☃)
  {
    this.b.put(Integer.valueOf(☃), Integer.valueOf(☃));
  }
  
  public int g()
  {
    return this.b.size();
  }
  
  public boolean x_()
  {
    return false;
  }
  
  public void a(qr ☃) {}
  
  public qr y_()
  {
    return qr.a;
  }
  
  public String k()
  {
    return this.a;
  }
  
  public aau a(zi ☃, zj ☃)
  {
    throw new UnsupportedOperationException();
  }
}
