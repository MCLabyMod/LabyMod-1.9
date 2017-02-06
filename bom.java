import java.util.Arrays;

public class bom
  extends bof
{
  private final bvh e;
  
  public bom(bof quad, bvh textureIn)
  {
    super(Arrays.copyOf(quad.b(), quad.b().length), quad.b, bon.a(quad.b()), quad.a());
    this.e = textureIn;
    f();
  }
  
  private void f()
  {
    int step = this.a.length / 4;
    for (int i = 0; i < 4; i++)
    {
      int j = step * i;
      this.a[(j + 4)] = Float.floatToRawIntBits(this.e.a(this.d.a(Float.intBitsToFloat(this.a[(j + 4)]))));
      this.a[(j + 4 + 1)] = Float.floatToRawIntBits(this.e.b(this.d.b(Float.intBitsToFloat(this.a[(j + 4 + 1)]))));
    }
  }
}
