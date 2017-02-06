import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChunkUtils
{
  private static Field fieldHasEntities = null;
  private static boolean fieldHasEntitiesMissing = false;
  
  public static boolean hasEntities(ase chunk)
  {
    if (fieldHasEntities == null)
    {
      if (fieldHasEntitiesMissing) {
        return true;
      }
      fieldHasEntities = fildFieldHasEntities(chunk);
      if (fieldHasEntities == null)
      {
        fieldHasEntitiesMissing = true;
        return true;
      }
    }
    try
    {
      return fieldHasEntities.getBoolean(chunk);
    }
    catch (Exception e)
    {
      Config.warn("Error calling Chunk.hasEntities");
      Config.warn(e.getClass().getName() + " " + e.getMessage());
      fieldHasEntitiesMissing = true;
    }
    return true;
  }
  
  private static Field fildFieldHasEntities(ase chunk)
  {
    try
    {
      List listBoolFields = new ArrayList();
      List listBoolValuesPre = new ArrayList();
      Field[] fields = ase.class.getDeclaredFields();
      for (int i = 0; i < fields.length; i++)
      {
        Field field = fields[i];
        if (field.getType() == Boolean.TYPE)
        {
          field.setAccessible(true);
          
          listBoolFields.add(field);
          
          listBoolValuesPre.add(field.get(chunk));
        }
      }
      chunk.g(false);
      
      List listBoolValuesFalse = new ArrayList();
      for (Iterator it = listBoolFields.iterator(); it.hasNext();)
      {
        Field field = (Field)it.next();
        listBoolValuesFalse.add(field.get(chunk));
      }
      chunk.g(true);
      
      List listBoolValuesTrue = new ArrayList();
      for (Iterator it = listBoolFields.iterator(); it.hasNext();)
      {
        Field field = (Field)it.next();
        listBoolValuesTrue.add(field.get(chunk));
      }
      List listMatchingFields = new ArrayList();
      for (int i = 0; i < listBoolFields.size(); i++)
      {
        Field field = (Field)listBoolFields.get(i);
        
        Boolean valFalse = (Boolean)listBoolValuesFalse.get(i);
        Boolean valTrue = (Boolean)listBoolValuesTrue.get(i);
        if ((!valFalse.booleanValue()) && (valTrue.booleanValue() == true))
        {
          listMatchingFields.add(field);
          
          Boolean valPre = (Boolean)listBoolValuesPre.get(i);
          field.set(chunk, valPre);
        }
      }
      if (listMatchingFields.size() == 1) {
        return (Field)listMatchingFields.get(0);
      }
    }
    catch (Exception e)
    {
      Config.warn(e.getClass().getName() + " " + e.getMessage());
    }
    Config.warn("Error finding Chunk.hasEntities");
    return null;
  }
}
