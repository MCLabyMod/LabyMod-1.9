import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum asw
{
  private final int d;
  private final String e;
  private final String f;
  private final Class<? extends asv> g;
  
  private asw(int ☃, String ☃, String ☃, Class<? extends asv> ☃)
  {
    this.d = ☃;
    this.e = ☃;
    this.f = ☃;
    this.g = ☃;
  }
  
  public int a()
  {
    return this.d;
  }
  
  public String b()
  {
    return this.e;
  }
  
  public String c()
  {
    return this.f;
  }
  
  public asv d()
  {
    try
    {
      Constructor<? extends asv> ☃ = this.g.getConstructor(new Class[0]);
      return (asv)☃.newInstance(new Object[0]);
    }
    catch (NoSuchMethodException ☃)
    {
      throw new Error("Could not create new dimension", ☃);
    }
    catch (InvocationTargetException ☃)
    {
      throw new Error("Could not create new dimension", ☃);
    }
    catch (InstantiationException ☃)
    {
      throw new Error("Could not create new dimension", ☃);
    }
    catch (IllegalAccessException ☃)
    {
      throw new Error("Could not create new dimension", ☃);
    }
  }
  
  public static asw a(int ☃)
  {
    for (asw ☃ : ) {
      if (☃.a() == ☃) {
        return ☃;
      }
    }
    throw new IllegalArgumentException("Invalid dimension id " + ☃);
  }
}
