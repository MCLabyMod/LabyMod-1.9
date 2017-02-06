import java.util.concurrent.Callable;

class b$4
  implements Callable<String>
{
  b$4(b paramb) {}
  
  public String a()
  {
    return System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor");
  }
}
