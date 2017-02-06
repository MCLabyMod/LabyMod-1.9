public class ayb
  extends axv
{
  private axv c;
  private axv d;
  
  public ayb(long ☃, axv ☃, axv ☃)
  {
    super(☃);
    this.c = ☃;
    this.d = ☃;
  }
  
  public void a(long ☃)
  {
    this.c.a(☃);
    this.d.a(☃);
    super.a(☃);
  }
  
  public int[] a(int ☃, int ☃, int ☃, int ☃)
  {
    int[] ☃ = this.c.a(☃, ☃, ☃, ☃);
    int[] ☃ = this.d.a(☃, ☃, ☃, ☃);
    
    int[] ☃ = axt.a(☃ * ☃);
    for (int ☃ = 0; ☃ < ☃ * ☃; ☃++) {
      if ((☃[☃] == aig.a(ail.a)) || (☃[☃] == aig.a(ail.z))) {
        ☃[☃] = ☃[☃];
      } else if (☃[☃] == aig.a(ail.i))
      {
        if (☃[☃] == aig.a(ail.n)) {
          ☃[☃] = aig.a(ail.m);
        } else if ((☃[☃] == aig.a(ail.p)) || (☃[☃] == aig.a(ail.q))) {
          ☃[☃] = aig.a(ail.q);
        } else {
          ☃[☃] &= 0xFF;
        }
      }
      else {
        ☃[☃] = ☃[☃];
      }
    }
    return ☃;
  }
}
