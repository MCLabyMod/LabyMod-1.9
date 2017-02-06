public final class oo$a
  implements Comparable<a>
{
  public double a;
  public double b;
  public String c;
  
  public oo$a(String profilerName, double usePercentage, double totalUsePercentage)
  {
    this.c = profilerName;
    this.a = usePercentage;
    this.b = totalUsePercentage;
  }
  
  public int a(a p_compareTo_1_)
  {
    return p_compareTo_1_.a > this.a ? 1 : p_compareTo_1_.a < this.a ? -1 : p_compareTo_1_.c.compareTo(this.c);
  }
  
  public int a()
  {
    return (this.c.hashCode() & 0xAAAAAA) + 4473924;
  }
}
