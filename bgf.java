import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;

public class bgf
{
  private static final bgf a = new bgf();
  private Random b = new Random();
  private String[] c = "the elder scrolls klaatu berata niktu xyzzy bless curse light darkness fire air earth water hot dry cold wet ignite snuff embiggen twist shorten stretch fiddle destroy imbue galvanize enchant free limited range of towards inside sphere cube self other ball mental physical grow shrink demon elemental spirit animal creature beast humanoid undead fresh stale phnglui mglwnafh cthulhu rlyeh wgahnagl fhtagnbaguette".split(" ");
  
  public static bgf a()
  {
    return a;
  }
  
  public String a(bct ☃, int ☃)
  {
    int ☃ = this.b.nextInt(2) + 3;
    String ☃ = "";
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      if (☃ > 0) {
        ☃ = ☃ + " ";
      }
      ☃ = ☃ + this.c[this.b.nextInt(this.c.length)];
    }
    List<String> ☃ = ☃.c(☃, ☃);
    return StringUtils.join(☃.size() >= 2 ? ☃.subList(0, 2) : ☃, " ");
  }
  
  public void a(long ☃)
  {
    this.b.setSeed(☃);
  }
}
