public class axf
{
  public static final axf[] a = new axf[64];
  public static final axf b = new axf(0, 0);
  public static final axf c = new axf(1, 8368696);
  public static final axf d = new axf(2, 16247203);
  public static final axf e = new axf(3, 13092807);
  public static final axf f = new axf(4, 16711680);
  public static final axf g = new axf(5, 10526975);
  public static final axf h = new axf(6, 10987431);
  public static final axf i = new axf(7, 31744);
  public static final axf j = new axf(8, 16777215);
  public static final axf k = new axf(9, 10791096);
  public static final axf l = new axf(10, 9923917);
  public static final axf m = new axf(11, 7368816);
  public static final axf n = new axf(12, 4210943);
  public static final axf o = new axf(13, 9402184);
  public static final axf p = new axf(14, 16776437);
  public static final axf q = new axf(15, 14188339);
  public static final axf r = new axf(16, 11685080);
  public static final axf s = new axf(17, 6724056);
  public static final axf t = new axf(18, 15066419);
  public static final axf u = new axf(19, 8375321);
  public static final axf v = new axf(20, 15892389);
  public static final axf w = new axf(21, 5000268);
  public static final axf x = new axf(22, 10066329);
  public static final axf y = new axf(23, 5013401);
  public static final axf z = new axf(24, 8339378);
  public static final axf A = new axf(25, 3361970);
  public static final axf B = new axf(26, 6704179);
  public static final axf C = new axf(27, 6717235);
  public static final axf D = new axf(28, 10040115);
  public static final axf E = new axf(29, 1644825);
  public static final axf F = new axf(30, 16445005);
  public static final axf G = new axf(31, 6085589);
  public static final axf H = new axf(32, 4882687);
  public static final axf I = new axf(33, 55610);
  public static final axf J = new axf(34, 8476209);
  public static final axf K = new axf(35, 7340544);
  public int L;
  public final int M;
  
  private axf(int index, int color)
  {
    if ((index >= 0) && (index <= 63))
    {
      this.M = index;
      this.L = color;
      a[index] = this;
    }
    else
    {
      throw new IndexOutOfBoundsException("Map colour ID must be between 0 and 63 (inclusive)");
    }
  }
  
  public int a(int p_151643_1_)
  {
    int i = 220;
    if (p_151643_1_ == 3) {
      i = 135;
    }
    if (p_151643_1_ == 2) {
      i = 255;
    }
    if (p_151643_1_ == 1) {
      i = 220;
    }
    if (p_151643_1_ == 0) {
      i = 180;
    }
    int j = (this.L >> 16 & 0xFF) * i / 255;
    int k = (this.L >> 8 & 0xFF) * i / 255;
    int l = (this.L & 0xFF) * i / 255;
    return 0xFF000000 | j << 16 | k << 8 | l;
  }
}
