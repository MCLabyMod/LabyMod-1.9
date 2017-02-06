public class MathUtils
{
  public static int getAverage(int[] vals)
  {
    if (vals.length <= 0) {
      return 0;
    }
    int sum = 0;
    for (int i = 0; i < vals.length; i++)
    {
      int val = vals[i];
      sum += val;
    }
    int avg = sum / vals.length;
    
    return avg;
  }
}
