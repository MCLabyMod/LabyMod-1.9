import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class p
  extends i
{
  public String c()
  {
    return "achievement";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.achievement.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 2) {
      throw new cf("commands.achievement.usage", new Object[0]);
    }
    final np ☃ = nt.a(☃[1]);
    if (((☃ == null) && (!☃[1].equals("*"))) || ((☃ != null) && (!☃.d()))) {
      throw new bz("commands.achievement.unknownAchievement", new Object[] { ☃[1] });
    }
    final lr ☃ = ☃.length >= 3 ? a(☃, ☃, ☃[2]) : a(☃);
    boolean ☃ = ☃[0].equalsIgnoreCase("give");
    boolean ☃ = ☃[0].equalsIgnoreCase("take");
    if ((!☃) && (!☃)) {
      return;
    }
    if (☃ == null)
    {
      if (☃)
      {
        for (nj ☃ : nk.e) {
          ☃.b(☃);
        }
        a(☃, this, "commands.achievement.give.success.all", new Object[] { ☃.h_() });
      }
      else if (☃)
      {
        for (nj ☃ : Lists.reverse(nk.e)) {
          ☃.a(☃);
        }
        a(☃, this, "commands.achievement.take.success.all", new Object[] { ☃.h_() });
      }
      return;
    }
    if ((☃ instanceof nj))
    {
      nj ☃ = (nj)☃;
      if (☃)
      {
        if (☃.E().a(☃)) {
          throw new bz("commands.achievement.alreadyHave", new Object[] { ☃.h_(), ☃.j() });
        }
        List<nj> ☃ = Lists.newArrayList();
        while ((☃.c != null) && (!☃.E().a(☃.c)))
        {
          ☃.add(☃.c);
          ☃ = ☃.c;
        }
        for (nj ☃ : Lists.reverse(☃)) {
          ☃.b(☃);
        }
      }
      else if (☃)
      {
        if (!☃.E().a(☃)) {
          throw new bz("commands.achievement.dontHave", new Object[] { ☃.h_(), ☃.j() });
        }
        List<nj> ☃ = Lists.newArrayList(Iterators.filter(nk.e.iterator(), new Predicate()
        {
          public boolean a(nj ☃)
          {
            return (☃.E().a(☃)) && (☃ != ☃);
          }
        }));
        List<nj> ☃ = Lists.newArrayList(☃);
        for (nj ☃ : ☃)
        {
          nj ☃ = ☃;
          boolean ☃ = false;
          while (☃ != null)
          {
            if (☃ == ☃) {
              ☃ = true;
            }
            ☃ = ☃.c;
          }
          if (!☃)
          {
            ☃ = ☃;
            while (☃ != null)
            {
              ☃.remove(☃);
              ☃ = ☃.c;
            }
          }
        }
        for (nj ☃ : ☃) {
          ☃.a(☃);
        }
      }
    }
    if (☃)
    {
      ☃.b(☃);
      a(☃, this, "commands.achievement.give.success.one", new Object[] { ☃.h_(), ☃.j() });
    }
    else if (☃)
    {
      ☃.a(☃);
      a(☃, this, "commands.achievement.take.success.one", new Object[] { ☃.j(), ☃.h_() });
    }
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if (☃.length == 1) {
      return a(☃, new String[] { "give", "take" });
    }
    if (☃.length == 2)
    {
      List<String> ☃ = Lists.newArrayList();
      for (np ☃ : nk.e) {
        ☃.add(☃.e);
      }
      return a(☃, ☃);
    }
    if (☃.length == 3) {
      return a(☃, ☃.J());
    }
    return Collections.emptyList();
  }
  
  public boolean b(String[] ☃, int ☃)
  {
    return ☃ == 2;
  }
}
