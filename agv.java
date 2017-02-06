public class agv
  extends agm
{
  protected agv(agm.a ☃, agn ☃, rw... ☃)
  {
    super(☃, ☃, ☃);
    if (☃ == agn.h) {
      c("lootBonusDigger");
    } else if (☃ == agn.i) {
      c("lootBonusFishing");
    } else {
      c("lootBonus");
    }
  }
  
  public int a(int ☃)
  {
    return 15 + (☃ - 1) * 9;
  }
  
  public int b(int ☃)
  {
    return super.a(☃) + 50;
  }
  
  public int b()
  {
    return 3;
  }
  
  public boolean a(agm ☃)
  {
    return (super.a(☃)) && (☃ != agq.r);
  }
}
