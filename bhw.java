import com.google.common.base.Objects;
import java.util.List;

public class bhw
{
  private final bht a;
  private final List<bhu> b;
  private final int c;
  
  public bhw(bht ☃, List<bhu> ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
  }
  
  public bhu a(int ☃)
  {
    if ((☃ < 0) || (☃ >= this.b.size())) {
      return bhs.a;
    }
    return (bhu)Objects.firstNonNull(this.b.get(☃), bhs.a);
  }
  
  public int b()
  {
    return this.c;
  }
}
