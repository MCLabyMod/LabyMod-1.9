import com.google.common.collect.Lists;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class ko$a
{
  private String a = null;
  private final List<String> b = Lists.newArrayList();
  
  private void a(String ☃)
  {
    this.b.add(0, ☃);
  }
  
  public String b()
  {
    return StringUtils.join(this.b, "->");
  }
  
  public String toString()
  {
    if (this.a != null)
    {
      if (!this.b.isEmpty()) {
        return this.a + " " + b();
      }
      return this.a;
    }
    if (!this.b.isEmpty()) {
      return "(Unknown file) " + b();
    }
    return "(Unknown file)";
  }
}
