import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.concurrent.Callable;

class b$6
  implements Callable<String>
{
  b$6(b paramb) {}
  
  public String a()
  {
    RuntimeMXBean runtimemxbean = ManagementFactory.getRuntimeMXBean();
    List<String> list = runtimemxbean.getInputArguments();
    int i = 0;
    StringBuilder stringbuilder = new StringBuilder();
    for (String s : list) {
      if (s.startsWith("-X"))
      {
        if (i++ > 0) {
          stringbuilder.append(" ");
        }
        stringbuilder.append(s);
      }
    }
    return String.format("%d total; %s", new Object[] { Integer.valueOf(i), stringbuilder.toString() });
  }
}
