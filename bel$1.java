import com.google.common.base.Predicate;
import com.google.common.primitives.Floats;

class bel$1
  implements Predicate<String>
{
  bel$1(bel parambel) {}
  
  public boolean a(String ☃)
  {
    Float ☃ = Floats.tryParse(☃);
    return (☃.isEmpty()) || ((☃ != null) && (Floats.isFinite(☃.floatValue())) && (☃.floatValue() >= 0.0F));
  }
}
