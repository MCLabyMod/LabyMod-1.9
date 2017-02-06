import java.util.Random;

public class atk
{
  protected int e = 8;
  protected Random f = new Random();
  protected aht g;
  
  public void a(aht ☃, int ☃, int ☃, atf ☃)
  {
    int ☃ = this.e;
    this.g = ☃;
    
    this.f.setSeed(☃.O());
    long ☃ = this.f.nextLong();
    long ☃ = this.f.nextLong();
    for (int ☃ = ☃ - ☃; ☃ <= ☃ + ☃; ☃++) {
      for (int ☃ = ☃ - ☃; ☃ <= ☃ + ☃; ☃++)
      {
        long ☃ = ☃ * ☃;
        long ☃ = ☃ * ☃;
        this.f.setSeed(☃ ^ ☃ ^ ☃.O());
        a(☃, ☃, ☃, ☃, ☃, ☃);
      }
    }
  }
  
  protected void a(aht ☃, int ☃, int ☃, int ☃, int ☃, atf ☃) {}
}
