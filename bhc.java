import com.google.gson.JsonParseException;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bhc
  extends bgz
{
  private static final Logger c = ;
  private final bwi d;
  private final kk e;
  
  public bhc(bgy ☃, bwi ☃)
  {
    super(☃);
    this.d = ☃;
    bux ☃;
    try
    {
      ☃ = new bux(☃.a());
    }
    catch (IOException ☃)
    {
      ☃ = bvk.a;
    }
    this.e = this.a.N().a("texturepackicon", ☃);
  }
  
  protected int a()
  {
    return 2;
  }
  
  protected String b()
  {
    try
    {
      bxh ☃ = (bxh)this.d.a(this.a.P().b, "pack");
      if (☃ != null) {
        return ☃.a().d();
      }
    }
    catch (JsonParseException ☃)
    {
      c.error("Couldn't load metadata info", ☃);
    }
    catch (IOException ☃)
    {
      c.error("Couldn't load metadata info", ☃);
    }
    return a.m + "Missing " + "pack.mcmeta" + " :(";
  }
  
  protected boolean f()
  {
    return false;
  }
  
  protected boolean g()
  {
    return false;
  }
  
  protected boolean h()
  {
    return false;
  }
  
  protected boolean i()
  {
    return false;
  }
  
  protected String c()
  {
    return "Server";
  }
  
  protected void d()
  {
    this.a.N().a(this.e);
  }
  
  protected boolean e()
  {
    return false;
  }
  
  public boolean j()
  {
    return true;
  }
}
