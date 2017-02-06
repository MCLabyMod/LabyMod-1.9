import java.lang.reflect.Method;

public class ReflectorMethod
{
  private ReflectorClass reflectorClass = null;
  private String targetMethodName = null;
  private Class[] targetMethodParameterTypes = null;
  private boolean checked = false;
  private Method targetMethod = null;
  
  public ReflectorMethod(ReflectorClass reflectorClass, String targetMethodName)
  {
    this(reflectorClass, targetMethodName, null, false);
  }
  
  public ReflectorMethod(ReflectorClass reflectorClass, String targetMethodName, Class[] targetMethodParameterTypes)
  {
    this(reflectorClass, targetMethodName, targetMethodParameterTypes, false);
  }
  
  public ReflectorMethod(ReflectorClass reflectorClass, String targetMethodName, Class[] targetMethodParameterTypes, boolean lazyResolve)
  {
    this.reflectorClass = reflectorClass;
    this.targetMethodName = targetMethodName;
    this.targetMethodParameterTypes = targetMethodParameterTypes;
    Method m;
    if (!lazyResolve) {
      m = getTargetMethod();
    }
  }
  
  public Method getTargetMethod()
  {
    if (this.checked) {
      return this.targetMethod;
    }
    this.checked = true;
    
    Class cls = this.reflectorClass.getTargetClass();
    if (cls == null) {
      return null;
    }
    try
    {
      Method[] ms = cls.getDeclaredMethods();
      for (int i = 0; i < ms.length; i++)
      {
        Method m = ms[i];
        if (m.getName().equals(this.targetMethodName)) {
          if (this.targetMethodParameterTypes != null)
          {
            Class[] types = m.getParameterTypes();
            if (!Reflector.matchesTypes(this.targetMethodParameterTypes, types)) {}
          }
          else
          {
            this.targetMethod = m;
            if (!this.targetMethod.isAccessible()) {
              this.targetMethod.setAccessible(true);
            }
            return this.targetMethod;
          }
        }
      }
      Config.log("(Reflector) Method not present: " + cls.getName() + "." + this.targetMethodName);
      return null;
    }
    catch (Throwable e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public boolean exists()
  {
    if (this.checked) {
      return this.targetMethod != null;
    }
    return getTargetMethod() != null;
  }
  
  public Class getReturnType()
  {
    Method tm = getTargetMethod();
    if (tm == null) {
      return null;
    }
    return tm.getReturnType();
  }
  
  public void deactivate()
  {
    this.checked = true;
    this.targetMethod = null;
  }
}
