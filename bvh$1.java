import java.util.concurrent.Callable;

class bvh$1
  implements Callable<String>
{
  bvh$1(bvh parambvh, int[][] paramArrayOfInt) {}
  
  public String a()
    throws Exception
  {
    StringBuilder stringbuilder = new StringBuilder();
    for (int[] aint1 : this.val$aint)
    {
      if (stringbuilder.length() > 0) {
        stringbuilder.append(", ");
      }
      stringbuilder.append(aint1 == null ? "null" : Integer.valueOf(aint1.length));
    }
    return stringbuilder.toString();
  }
}
