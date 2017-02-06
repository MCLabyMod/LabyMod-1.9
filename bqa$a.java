import com.google.common.primitives.Doubles;
import com.google.common.util.concurrent.ListenableFutureTask;

class bqa$a
  implements Comparable<a>
{
  private final ListenableFutureTask<Object> b;
  private final double c;
  
  public bqa$a(ListenableFutureTask<Object> arg1, double p_i46994_2_)
  {
    this.b = p_i46994_2_;
    this.c = p_i46994_3_;
  }
  
  public int a(a p_compareTo_1_)
  {
    return Doubles.compare(this.c, p_compareTo_1_.c);
  }
}
