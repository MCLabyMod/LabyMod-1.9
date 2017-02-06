import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class avs
  extends awd
{
  private double a = 0.004D;
  
  public avs() {}
  
  public String a()
  {
    return "Mineshaft";
  }
  
  public avs(Map<String, String> ☃)
  {
    for (Map.Entry<String, String> ☃ : ☃.entrySet()) {
      if (((String)☃.getKey()).equals("chance")) {
        this.a = on.a((String)☃.getValue(), this.a);
      }
    }
  }
  
  protected boolean a(int ☃, int ☃)
  {
    return (this.f.nextDouble() < this.a) && (this.f.nextInt(80) < Math.max(Math.abs(☃), Math.abs(☃)));
  }
  
  protected awh b(int ☃, int ☃)
  {
    return new avu(this.g, this.f, ☃, ☃);
  }
}
