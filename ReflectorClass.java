public class ReflectorClass
{
  private String targetClassName = null;
  private boolean checked = false;
  private Class targetClass = null;
  
  public ReflectorClass(String targetClassName)
  {
    this(targetClassName, false);
  }
  
  public ReflectorClass(String targetClassName, boolean lazyResolve)
  {
    this.targetClassName = targetClassName;
    Class cls;
    if (!lazyResolve) {
      cls = getTargetClass();
    }
  }
  
  public ReflectorClass(Class targetClass)
  {
    this.targetClass = targetClass;
    this.targetClassName = targetClass.getName();
    this.checked = true;
  }
  
  public Class getTargetClass()
  {
    if (this.checked) {
      return this.targetClass;
    }
    this.checked = true;
    try
    {
      this.targetClass = Class.forName(this.targetClassName);
    }
    catch (ClassNotFoundException e)
    {
      Config.log("(Reflector) Class not present: " + this.targetClassName);
    }
    catch (Throwable e)
    {
      e.printStackTrace();
    }
    return this.targetClass;
  }
  
  public boolean exists()
  {
    return getTargetClass() != null;
  }
  
  public String getTargetClassName()
  {
    return this.targetClassName;
  }
  
  public boolean isInstance(Object obj)
  {
    if (getTargetClass() == null) {
      return false;
    }
    return getTargetClass().isInstance(obj);
  }
}
