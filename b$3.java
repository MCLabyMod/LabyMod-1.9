import java.util.concurrent.Callable;

class b$3
  implements Callable<String>
{
  b$3(b paramb) {}
  
  public String a()
  {
    return System.getProperty("java.version") + ", " + System.getProperty("java.vendor");
  }
}
