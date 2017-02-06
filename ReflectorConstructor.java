import java.lang.reflect.Constructor;

public class ReflectorConstructor
{
  private ReflectorClass reflectorClass = null;
  private Class[] parameterTypes = null;
  private boolean checked = false;
  private Constructor targetConstructor = null;
  
  public ReflectorConstructor(ReflectorClass reflectorClass, Class[] parameterTypes)
  {
    this.reflectorClass = reflectorClass;
    this.parameterTypes = parameterTypes;
    
    Constructor c = getTargetConstructor();
  }
  
  public Constructor getTargetConstructor()
  {
    if (this.checked) {
      return this.targetConstructor;
    }
    this.checked = true;
    
    Class cls = this.reflectorClass.getTargetClass();
    if (cls == null) {
      return null;
    }
    try
    {
      this.targetConstructor = findConstructor(cls, this.parameterTypes);
      if (this.targetConstructor == null) {
        Config.dbg("(Reflector) Constructor not present: " + cls.getName() + ", params: " + Config.arrayToString(this.parameterTypes));
      }
      if (this.targetConstructor != null) {
        if (!this.targetConstructor.isAccessible()) {
          this.targetConstructor.setAccessible(true);
        }
      }
    }
    catch (Throwable e)
    {
      e.printStackTrace();
    }
    return this.targetConstructor;
  }
  
  private static Constructor findConstructor(Class cls, Class[] paramTypes)
  {
    Constructor[] cs = cls.getDeclaredConstructors();
    for (int i = 0; i < cs.length; i++)
    {
      Constructor c = cs[i];
      Class[] types = c.getParameterTypes();
      if (Reflector.matchesTypes(paramTypes, types)) {
        return c;
      }
    }
    return null;
  }
  
  public boolean exists()
  {
    if (this.checked) {
      return this.targetConstructor != null;
    }
    return getTargetConstructor() != null;
  }
  
  public void deactivate()
  {
    this.checked = true;
    this.targetConstructor = null;
  }
}
