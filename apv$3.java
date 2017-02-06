import java.util.concurrent.Callable;

class apv$3
  implements Callable<String>
{
  apv$3(apv paramapv) {}
  
  public String a()
    throws Exception
  {
    arc ☃ = this.a.b.o(this.a.c);
    int ☃ = ☃.t().e(☃);
    if (☃ < 0) {
      return "Unknown? (Got " + ☃ + ")";
    }
    String ☃ = String.format("%4s", new Object[] { Integer.toBinaryString(☃) }).replace(" ", "0");
    
    return String.format("%1$d / 0x%1$X / 0b%2$s", new Object[] { Integer.valueOf(☃), ☃ });
  }
}
