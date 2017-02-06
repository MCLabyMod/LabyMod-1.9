import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class aio
  extends aik
{
  private final aig a;
  
  public aio(aig ☃)
  {
    this.a = ☃;
  }
  
  public aig a(cj ☃)
  {
    return this.a;
  }
  
  public aig[] a(aig[] ☃, int ☃, int ☃, int ☃, int ☃)
  {
    if ((☃ == null) || (☃.length < ☃ * ☃)) {
      ☃ = new aig[☃ * ☃];
    }
    Arrays.fill(☃, 0, ☃ * ☃, this.a);
    
    return ☃;
  }
  
  public aig[] b(aig[] ☃, int ☃, int ☃, int ☃, int ☃)
  {
    if ((☃ == null) || (☃.length < ☃ * ☃)) {
      ☃ = new aig[☃ * ☃];
    }
    Arrays.fill(☃, 0, ☃ * ☃, this.a);
    
    return ☃;
  }
  
  public aig[] a(aig[] ☃, int ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    return b(☃, ☃, ☃, ☃, ☃);
  }
  
  public cj a(int ☃, int ☃, int ☃, List<aig> ☃, Random ☃)
  {
    if (☃.contains(this.a)) {
      return new cj(☃ - ☃ + ☃.nextInt(☃ * 2 + 1), 0, ☃ - ☃ + ☃.nextInt(☃ * 2 + 1));
    }
    return null;
  }
  
  public boolean a(int ☃, int ☃, int ☃, List<aig> ☃)
  {
    return ☃.contains(this.a);
  }
}
