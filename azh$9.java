import java.util.concurrent.Callable;

class azh$9
  implements Callable<String>
{
  azh$9(azh paramazh) {}
  
  public String a()
    throws Exception
  {
    return String.format("Game mode: %s (ID %d). Hardcore: %b. Cheats: %b", new Object[] { azh.o(this.a).b(), Integer.valueOf(azh.o(this.a).a()), Boolean.valueOf(azh.p(this.a)), Boolean.valueOf(azh.q(this.a)) });
  }
}
