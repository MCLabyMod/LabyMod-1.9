import java.util.Map;

public class bwo
{
  private static bwr a;
  
  static void a(bwr i18nLocaleIn)
  {
    a = i18nLocaleIn;
  }
  
  public static String a(String translateKey, Object... parameters)
  {
    return a.a(translateKey, parameters);
  }
  
  public static boolean a(String p_188566_0_)
  {
    return a.a(p_188566_0_);
  }
  
  public static Map getLocaleProperties()
  {
    return a.a;
  }
}
