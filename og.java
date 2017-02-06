public class og
{
  private static final Integer[] a = new Integer[65535];
  
  public static Integer a(int value)
  {
    return (value > 0) && (value < a.length) ? a[value] : Integer.valueOf(value);
  }
  
  static
  {
    int i = 0;
    for (int j = a.length; i < j; i++) {
      a[i] = Integer.valueOf(i);
    }
  }
}
