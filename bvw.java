import java.util.UUID;

public class bvw
{
  private static final kk a = new kk("textures/entity/steve.png");
  private static final kk b = new kk("textures/entity/alex.png");
  
  public static kk a()
  {
    return a;
  }
  
  public static kk a(UUID ☃)
  {
    if (c(☃)) {
      return b;
    }
    return a;
  }
  
  public static String b(UUID ☃)
  {
    if (c(☃)) {
      return "slim";
    }
    return "default";
  }
  
  private static boolean c(UUID ☃)
  {
    return (☃.hashCode() & 0x1) == 1;
  }
}
