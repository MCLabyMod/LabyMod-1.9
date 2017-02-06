import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

public class ii
  implements ff<ig>
{
  private String a;
  private boolean b;
  private cj c;
  
  public ii() {}
  
  public ii(String ☃, cj ☃, boolean ☃)
  {
    this.a = ☃;
    this.c = ☃;
    this.b = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.c(32767);
    this.b = ☃.readBoolean();
    boolean ☃ = ☃.readBoolean();
    if (☃) {
      this.c = ☃.e();
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.a(StringUtils.substring(this.a, 0, 32767));
    ☃.writeBoolean(this.b);
    boolean ☃ = this.c != null;
    ☃.writeBoolean(☃);
    if (☃) {
      ☃.a(this.c);
    }
  }
  
  public void a(ig ☃)
  {
    ☃.a(this);
  }
  
  public String a()
  {
    return this.a;
  }
  
  public cj b()
  {
    return this.c;
  }
  
  public boolean c()
  {
    return this.b;
  }
}
