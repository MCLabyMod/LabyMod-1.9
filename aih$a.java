public class aih$a
{
  public aig[] a = new aig['Ā'];
  public int b;
  public int c;
  public long d;
  
  public aih$a(aih paramaih, int ☃, int ☃)
  {
    this.b = ☃;
    this.c = ☃;
    aih.a(paramaih).a(this.a, ☃ << 4, ☃ << 4, 16, 16, false);
  }
  
  public aig a(int ☃, int ☃)
  {
    return this.a[(☃ & 0xF | (☃ & 0xF) << 4)];
  }
}
