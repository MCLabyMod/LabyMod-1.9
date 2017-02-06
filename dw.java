public class dw
{
  public static final dw a = new dw(0L)
  {
    public void a(long ☃) {}
  };
  private final long b;
  private long c;
  
  public dw(long ☃)
  {
    this.b = ☃;
  }
  
  public void a(long ☃)
  {
    this.c += ☃ / 8L;
    if (this.c > this.b) {
      throw new RuntimeException("Tried to read NBT tag that was too big; tried to allocate: " + this.c + "bytes where max allowed: " + this.b);
    }
  }
}
