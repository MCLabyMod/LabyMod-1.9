import java.util.Random;
import java.util.UUID;

public class on
{
  public static final float a = c(2.0F);
  private static final int SIN_BITS = 12;
  private static final int SIN_MASK = 4095;
  private static final int SIN_COUNT = 4096;
  public static final float PI = 3.1415927F;
  public static final float PI2 = 6.2831855F;
  public static final float PId2 = 1.5707964F;
  private static final float radFull = 6.2831855F;
  private static final float degFull = 360.0F;
  private static final float radToIndex = 651.8986F;
  private static final float degToIndex = 11.377778F;
  public static final float deg2Rad = 0.017453292F;
  private static final float[] SIN_TABLE_FAST = new float['က'];
  public static boolean fastMath = false;
  private static final float[] b = new float[65536];
  private static final Random c = new Random();
  private static final int[] d;
  private static final double e;
  private static final double[] f;
  private static final double[] g;
  
  public static float a(float value)
  {
    if (fastMath) {
      return SIN_TABLE_FAST[((int)(value * 651.8986F) & 0xFFF)];
    }
    return b[((int)(value * 10430.378F) & 0xFFFF)];
  }
  
  public static float b(float value)
  {
    if (fastMath) {
      return SIN_TABLE_FAST[((int)((value + 1.5707964F) * 651.8986F) & 0xFFF)];
    }
    return b[((int)(value * 10430.378F + 16384.0F) & 0xFFFF)];
  }
  
  public static float c(float value)
  {
    return (float)Math.sqrt(value);
  }
  
  public static float a(double value)
  {
    return (float)Math.sqrt(value);
  }
  
  public static int d(float value)
  {
    int i = (int)value;
    return value < i ? i - 1 : i;
  }
  
  public static int b(double value)
  {
    return (int)(value + 1024.0D) - 1024;
  }
  
  public static int c(double value)
  {
    int i = (int)value;
    return value < i ? i - 1 : i;
  }
  
  public static long d(double value)
  {
    long i = value;
    return value < i ? i - 1L : i;
  }
  
  public static int e(double value)
  {
    return (int)(value >= 0.0D ? value : -value + 1.0D);
  }
  
  public static float e(float value)
  {
    return value >= 0.0F ? value : -value;
  }
  
  public static int a(int value)
  {
    return value >= 0 ? value : -value;
  }
  
  public static int f(float value)
  {
    int i = (int)value;
    return value > i ? i + 1 : i;
  }
  
  public static int f(double value)
  {
    int i = (int)value;
    return value > i ? i + 1 : i;
  }
  
  public static int a(int num, int min, int max)
  {
    return num > max ? max : num < min ? min : num;
  }
  
  public static float a(float num, float min, float max)
  {
    return num > max ? max : num < min ? min : num;
  }
  
  public static double a(double num, double min, double max)
  {
    return num > max ? max : num < min ? min : num;
  }
  
  public static double b(double lowerBnd, double upperBnd, double slide)
  {
    return slide > 1.0D ? upperBnd : slide < 0.0D ? lowerBnd : lowerBnd + (upperBnd - lowerBnd) * slide;
  }
  
  public static double a(double p_76132_0_, double p_76132_2_)
  {
    if (p_76132_0_ < 0.0D) {
      p_76132_0_ = -p_76132_0_;
    }
    if (p_76132_2_ < 0.0D) {
      p_76132_2_ = -p_76132_2_;
    }
    return p_76132_0_ > p_76132_2_ ? p_76132_0_ : p_76132_2_;
  }
  
  public static int a(int p_76137_0_, int p_76137_1_)
  {
    return p_76137_0_ < 0 ? -((-p_76137_0_ - 1) / p_76137_1_) - 1 : p_76137_0_ / p_76137_1_;
  }
  
  public static int a(Random p_76136_0_, int p_76136_1_, int p_76136_2_)
  {
    return p_76136_1_ >= p_76136_2_ ? p_76136_1_ : p_76136_0_.nextInt(p_76136_2_ - p_76136_1_ + 1) + p_76136_1_;
  }
  
  public static float a(Random p_151240_0_, float p_151240_1_, float p_151240_2_)
  {
    return p_151240_1_ >= p_151240_2_ ? p_151240_1_ : p_151240_0_.nextFloat() * (p_151240_2_ - p_151240_1_) + p_151240_1_;
  }
  
  public static double a(Random p_82716_0_, double p_82716_1_, double p_82716_3_)
  {
    return p_82716_1_ >= p_82716_3_ ? p_82716_1_ : p_82716_0_.nextDouble() * (p_82716_3_ - p_82716_1_) + p_82716_1_;
  }
  
  public static double a(long[] values)
  {
    long i = 0L;
    for (long j : values) {
      i += j;
    }
    return i / values.length;
  }
  
  public static boolean a(float p_180185_0_, float p_180185_1_)
  {
    return e(p_180185_1_ - p_180185_0_) < 1.0E-5F;
  }
  
  public static int b(int p_180184_0_, int p_180184_1_)
  {
    return (p_180184_0_ % p_180184_1_ + p_180184_1_) % p_180184_1_;
  }
  
  public static float b(float p_188207_0_, float p_188207_1_)
  {
    return (p_188207_0_ % p_188207_1_ + p_188207_1_) % p_188207_1_;
  }
  
  public static float g(float value)
  {
    value %= 360.0F;
    if (value >= 180.0F) {
      value -= 360.0F;
    }
    if (value < -180.0F) {
      value += 360.0F;
    }
    return value;
  }
  
  public static double g(double value)
  {
    value %= 360.0D;
    if (value >= 180.0D) {
      value -= 360.0D;
    }
    if (value < -180.0D) {
      value += 360.0D;
    }
    return value;
  }
  
  public static int b(int angle)
  {
    angle %= 360;
    if (angle >= 180) {
      angle -= 360;
    }
    if (angle < 65356) {
      angle += 360;
    }
    return angle;
  }
  
  public static int a(String p_82715_0_, int p_82715_1_)
  {
    try
    {
      return Integer.parseInt(p_82715_0_);
    }
    catch (Throwable var3) {}
    return p_82715_1_;
  }
  
  public static int a(String p_82714_0_, int p_82714_1_, int p_82714_2_)
  {
    return Math.max(p_82714_2_, a(p_82714_0_, p_82714_1_));
  }
  
  public static double a(String p_82712_0_, double p_82712_1_)
  {
    try
    {
      return Double.parseDouble(p_82712_0_);
    }
    catch (Throwable var4) {}
    return p_82712_1_;
  }
  
  public static double a(String p_82713_0_, double p_82713_1_, double p_82713_3_)
  {
    return Math.max(p_82713_3_, a(p_82713_0_, p_82713_1_));
  }
  
  public static int c(int value)
  {
    int i = value - 1;
    i |= i >> 1;
    i |= i >> 2;
    i |= i >> 4;
    i |= i >> 8;
    i |= i >> 16;
    return i + 1;
  }
  
  private static boolean g(int value)
  {
    return (value != 0) && ((value & value - 1) == 0);
  }
  
  public static int d(int value)
  {
    value = g(value) ? value : c(value);
    return d[((int)(value * 125613361L >> 27) & 0x1F)];
  }
  
  public static int e(int value)
  {
    return d(value) - (g(value) ? 0 : 1);
  }
  
  public static int c(int p_154354_0_, int p_154354_1_)
  {
    if (p_154354_1_ == 0) {
      return 0;
    }
    if (p_154354_0_ == 0) {
      return p_154354_1_;
    }
    if (p_154354_0_ < 0) {
      p_154354_1_ *= -1;
    }
    int i = p_154354_0_ % p_154354_1_;
    return i == 0 ? p_154354_0_ : p_154354_0_ + p_154354_1_ - i;
  }
  
  public static int b(float p_180183_0_, float p_180183_1_, float p_180183_2_)
  {
    return b(d(p_180183_0_ * 255.0F), d(p_180183_1_ * 255.0F), d(p_180183_2_ * 255.0F));
  }
  
  public static int b(int p_180181_0_, int p_180181_1_, int p_180181_2_)
  {
    int lvt_3_1_ = (p_180181_0_ << 8) + p_180181_1_;
    lvt_3_1_ = (lvt_3_1_ << 8) + p_180181_2_;
    return lvt_3_1_;
  }
  
  public static int d(int p_180188_0_, int p_180188_1_)
  {
    int i = (p_180188_0_ & 0xFF0000) >> 16;
    int j = (p_180188_1_ & 0xFF0000) >> 16;
    int k = (p_180188_0_ & 0xFF00) >> 8;
    int l = (p_180188_1_ & 0xFF00) >> 8;
    int i1 = (p_180188_0_ & 0xFF) >> 0;
    int j1 = (p_180188_1_ & 0xFF) >> 0;
    int k1 = (int)(i * j / 255.0F);
    int l1 = (int)(k * l / 255.0F);
    int i2 = (int)(i1 * j1 / 255.0F);
    return p_180188_0_ & 0xFF000000 | k1 << 16 | l1 << 8 | i2;
  }
  
  public static double h(double p_181162_0_)
  {
    return p_181162_0_ - Math.floor(p_181162_0_);
  }
  
  public static long a(df pos)
  {
    return c(pos.p(), pos.q(), pos.r());
  }
  
  public static long c(int x, int y, int z)
  {
    long i = x * 3129871 ^ z * 116129781L ^ y;
    i = i * i * 42317861L + i * 11L;
    return i;
  }
  
  public static UUID a(Random rand)
  {
    long i = rand.nextLong() & 0xFFFFFFFFFFFF0FFF | 0x4000;
    long j = rand.nextLong() & 0x3FFFFFFFFFFFFFFF | 0x8000000000000000;
    return new UUID(i, j);
  }
  
  public static UUID a()
  {
    return a(c);
  }
  
  public static double c(double p_181160_0_, double p_181160_2_, double p_181160_4_)
  {
    return (p_181160_0_ - p_181160_2_) / (p_181160_4_ - p_181160_2_);
  }
  
  public static double b(double p_181159_0_, double p_181159_2_)
  {
    double d0 = p_181159_2_ * p_181159_2_ + p_181159_0_ * p_181159_0_;
    if (Double.isNaN(d0)) {
      return NaN.0D;
    }
    boolean flag = p_181159_0_ < 0.0D;
    if (flag) {
      p_181159_0_ = -p_181159_0_;
    }
    boolean flag1 = p_181159_2_ < 0.0D;
    if (flag1) {
      p_181159_2_ = -p_181159_2_;
    }
    boolean flag2 = p_181159_0_ > p_181159_2_;
    if (flag2)
    {
      double d1 = p_181159_2_;
      p_181159_2_ = p_181159_0_;
      p_181159_0_ = d1;
    }
    double d9 = i(d0);
    p_181159_2_ *= d9;
    p_181159_0_ *= d9;
    double d2 = e + p_181159_0_;
    int i = (int)Double.doubleToRawLongBits(d2);
    double d3 = f[i];
    double d4 = g[i];
    double d5 = d2 - e;
    double d6 = p_181159_0_ * d4 - p_181159_2_ * d5;
    double d7 = (6.0D + d6 * d6) * d6 * 0.16666666666666666D;
    double d8 = d3 + d7;
    if (flag2) {
      d8 = 1.5707963267948966D - d8;
    }
    if (flag1) {
      d8 = 3.141592653589793D - d8;
    }
    if (flag) {
      d8 = -d8;
    }
    return d8;
  }
  
  public static double i(double p_181161_0_)
  {
    double d0 = 0.5D * p_181161_0_;
    long i = Double.doubleToRawLongBits(p_181161_0_);
    i = 6910469410427058090L - (i >> 1);
    p_181161_0_ = Double.longBitsToDouble(i);
    p_181161_0_ *= (1.5D - d0 * p_181161_0_ * p_181161_0_);
    return p_181161_0_;
  }
  
  public static int c(float p_181758_0_, float p_181758_1_, float p_181758_2_)
  {
    int i = (int)(p_181758_0_ * 6.0F) % 6;
    float f = p_181758_0_ * 6.0F - i;
    float f1 = p_181758_2_ * (1.0F - p_181758_1_);
    float f2 = p_181758_2_ * (1.0F - f * p_181758_1_);
    float f3 = p_181758_2_ * (1.0F - (1.0F - f) * p_181758_1_);
    float f4;
    float f5;
    float f6;
    switch (i)
    {
    case 0: 
      f4 = p_181758_2_;
      f5 = f3;
      f6 = f1;
      break;
    case 1: 
      f4 = f2;
      f5 = p_181758_2_;
      f6 = f1;
      break;
    case 2: 
      f4 = f1;
      f5 = p_181758_2_;
      f6 = f3;
      break;
    case 3: 
      f4 = f1;
      f5 = f2;
      f6 = p_181758_2_;
      break;
    case 4: 
      f4 = f3;
      f5 = f1;
      f6 = p_181758_2_;
      break;
    case 5: 
      f4 = p_181758_2_;
      f5 = f1;
      f6 = f2;
      break;
    default: 
      throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + p_181758_0_ + ", " + p_181758_1_ + ", " + p_181758_2_);
    }
    int j = a((int)(f4 * 255.0F), 0, 255);
    int k = a((int)(f5 * 255.0F), 0, 255);
    int l = a((int)(f6 * 255.0F), 0, 255);
    return j << 16 | k << 8 | l;
  }
  
  public static int f(int p_188208_0_)
  {
    p_188208_0_ ^= p_188208_0_ >>> 16;
    p_188208_0_ *= -2048144789;
    p_188208_0_ ^= p_188208_0_ >>> 13;
    p_188208_0_ *= -1028477387;
    p_188208_0_ ^= p_188208_0_ >>> 16;
    return p_188208_0_;
  }
  
  static
  {
    for (int i = 0; i < 65536; i++) {
      b[i] = ((float)Math.sin(i * 3.141592653589793D * 2.0D / 65536.0D));
    }
    for (int i = 0; i < 4096; i++) {
      SIN_TABLE_FAST[i] = ((float)Math.sin((i + 0.5F) / 4096.0F * 6.2831855F));
    }
    for (int i = 0; i < 360; i += 90) {
      SIN_TABLE_FAST[((int)(i * 11.377778F) & 0xFFF)] = ((float)Math.sin(i * 0.017453292F));
    }
    d = new int[] { 0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9 };
    e = Double.longBitsToDouble(4805340802404319232L);
    f = new double['ā'];
    g = new double['ā'];
    for (int j = 0; j < 257; j++)
    {
      double d0 = j / 256.0D;
      double d1 = Math.asin(d0);
      g[j] = Math.cos(d1);
      f[j] = d1;
    }
  }
}
