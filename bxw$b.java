import com.google.common.collect.ComparisonChain;

class bxw$b
  extends ov.a
  implements Comparable<b>
{
  protected final bxo b;
  
  public bxw$b(bxo ☃, int ☃)
  {
    super(☃);
    this.b = ☃;
  }
  
  public int a(b ☃)
  {
    return ComparisonChain.start().compare(☃.a, this.a).result();
  }
  
  public String toString()
  {
    return "MyWeighedRandomItem{weight=" + this.a + ", model=" + this.b + '}';
  }
}
