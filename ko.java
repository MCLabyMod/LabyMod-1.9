import com.google.common.collect.Lists;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class ko
  extends IOException
{
  private final List<ko.a> a = Lists.newArrayList();
  private final String b;
  
  public ko(String ☃)
  {
    this.a.add(new ko.a(null));
    this.b = ☃;
  }
  
  public ko(String ☃, Throwable ☃)
  {
    super(☃);
    this.a.add(new ko.a(null));
    this.b = ☃;
  }
  
  public void a(String ☃)
  {
    ko.a.a((ko.a)this.a.get(0), ☃);
  }
  
  public void b(String ☃)
  {
    ko.a.b((ko.a)this.a.get(0), ☃);
    this.a.add(0, new ko.a(null));
  }
  
  public String getMessage()
  {
    return "Invalid " + ((ko.a)this.a.get(this.a.size() - 1)).toString() + ": " + this.b;
  }
  
  public static ko a(Exception ☃)
  {
    if ((☃ instanceof ko)) {
      return (ko)☃;
    }
    String ☃ = ☃.getMessage();
    if ((☃ instanceof FileNotFoundException)) {
      ☃ = "File not found";
    }
    return new ko(☃, ☃);
  }
  
  public static class a
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
}
