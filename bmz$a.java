public class bmz$a
{
  private final int[] b;
  private final bvr c;
  private bvh[] stateQuadSprites;
  
  public bmz$a(bmz parambmz, int[] buffer, bvr format, bvh[] quadSprites)
  {
    this.b = buffer;
    this.c = format;
    this.stateQuadSprites = quadSprites;
  }
  
  public int[] a()
  {
    return this.b;
  }
  
  public int b()
  {
    return this.b.length / this.c.f();
  }
  
  public bvr c()
  {
    return this.c;
  }
}
