public class asa
{
  private final byte[] a;
  
  public asa()
  {
    this.a = new byte['ࠀ'];
  }
  
  public asa(byte[] ☃)
  {
    this.a = ☃;
    if (☃.length != 2048) {
      throw new IllegalArgumentException("ChunkNibbleArrays should be 2048 bytes not: " + ☃.length);
    }
  }
  
  public int a(int ☃, int ☃, int ☃)
  {
    return a(b(☃, ☃, ☃));
  }
  
  public void a(int ☃, int ☃, int ☃, int ☃)
  {
    a(b(☃, ☃, ☃), ☃);
  }
  
  private int b(int ☃, int ☃, int ☃)
  {
    return ☃ << 8 | ☃ << 4 | ☃;
  }
  
  public int a(int ☃)
  {
    int ☃ = c(☃);
    if (b(☃)) {
      return this.a[☃] & 0xF;
    }
    return this.a[☃] >> 4 & 0xF;
  }
  
  public void a(int ☃, int ☃)
  {
    int ☃ = c(☃);
    if (b(☃)) {
      this.a[☃] = ((byte)(this.a[☃] & 0xF0 | ☃ & 0xF));
    } else {
      this.a[☃] = ((byte)(this.a[☃] & 0xF | (☃ & 0xF) << 4));
    }
  }
  
  private boolean b(int ☃)
  {
    return (☃ & 0x1) == 0;
  }
  
  private int c(int ☃)
  {
    return ☃ >> 1;
  }
  
  public byte[] a()
  {
    return this.a;
  }
}
