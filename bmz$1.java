import com.google.common.primitives.Floats;
import java.util.Comparator;

class bmz$1
  implements Comparator<Integer>
{
  bmz$1(bmz parambmz, float[] paramArrayOfFloat) {}
  
  public int a(Integer p_compare_1_, Integer p_compare_2_)
  {
    return Floats.compare(this.val$afloat[p_compare_2_.intValue()], this.val$afloat[p_compare_1_.intValue()]);
  }
}
