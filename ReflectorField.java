import java.lang.reflect.Field;

public class ReflectorField
{
  private ReflectorClass reflectorClass = null;
  private String targetFieldName = null;
  private boolean checked = false;
  private Field targetField = null;
  
  public ReflectorField(ReflectorClass reflectorClass, String targetFieldName)
  {
    this.reflectorClass = reflectorClass;
    this.targetFieldName = targetFieldName;
    
    Field f = getTargetField();
  }
  
  public Field getTargetField()
  {
    if (this.checked) {
      return this.targetField;
    }
    this.checked = true;
    
    Class cls = this.reflectorClass.getTargetClass();
    if (cls == null) {
      return null;
    }
    try
    {
      this.targetField = cls.getDeclaredField(this.targetFieldName);
      if (!this.targetField.isAccessible()) {
        this.targetField.setAccessible(true);
      }
    }
    catch (NoSuchFieldException e)
    {
      Config.log("(Reflector) Field not present: " + cls.getName() + "." + this.targetFieldName);
    }
    catch (SecurityException e)
    {
      e.printStackTrace();
    }
    catch (Throwable e)
    {
      e.printStackTrace();
    }
    return this.targetField;
  }
  
  public Object getValue()
  {
    return Reflector.getFieldValue(null, this);
  }
  
  public void setValue(Object value)
  {
    Reflector.setFieldValue(null, this, value);
  }
  
  public boolean exists()
  {
    if (this.checked) {
      return this.targetField != null;
    }
    return getTargetField() != null;
  }
}
