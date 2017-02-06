import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

final class mi$2
  implements ParameterizedType
{
  public Type[] getActualTypeArguments()
  {
    return new Type[] { mi.a.class };
  }
  
  public Type getRawType()
  {
    return List.class;
  }
  
  public Type getOwnerType()
  {
    return null;
  }
}
