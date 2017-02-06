import java.util.Random;

class ze$g
  extends ou<Integer, Integer>
{
  public ze$g(int ☃, int ☃)
  {
    super(Integer.valueOf(☃), Integer.valueOf(☃));
  }
  
  public int a(Random ☃)
  {
    if (((Integer)a()).intValue() >= ((Integer)b()).intValue()) {
      return ((Integer)a()).intValue();
    }
    return ((Integer)a()).intValue() + ☃.nextInt(((Integer)b()).intValue() - ((Integer)a()).intValue() + 1);
  }
}
