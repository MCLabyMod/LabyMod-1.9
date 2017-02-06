import java.util.concurrent.Callable;
import org.lwjgl.input.Mouse;

class bng$3
  implements Callable<String>
{
  bng$3(bng parambng, int paramInt1, int paramInt2) {}
  
  public String a()
    throws Exception
  {
    return String.format("Scaled: (%d, %d). Absolute: (%d, %d)", new Object[] { Integer.valueOf(this.val$k1), Integer.valueOf(this.val$l1), Integer.valueOf(Mouse.getX()), Integer.valueOf(Mouse.getY()) });
  }
}
