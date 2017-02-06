import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class u
  extends i
{
  public String c()
  {
    return "clone";
  }
  
  public int a()
  {
    return 2;
  }
  
  public String b(m ☃)
  {
    return "commands.clone.usage";
  }
  
  public void a(MinecraftServer ☃, m ☃, String[] ☃)
    throws bz
  {
    if (☃.length < 9) {
      throw new cf("commands.clone.usage", new Object[0]);
    }
    ☃.a(n.a.b, 0);
    
    cj ☃ = a(☃, ☃, 0, false);
    cj ☃ = a(☃, ☃, 3, false);
    cj ☃ = a(☃, ☃, 6, false);
    
    avp ☃ = new avp(☃, ☃);
    avp ☃ = new avp(☃, ☃.a(☃.b()));
    
    int ☃ = ☃.c() * ☃.d() * ☃.e();
    if (☃ > 32768) {
      throw new bz("commands.clone.tooManyBlocks", new Object[] { Integer.valueOf(☃), Integer.valueOf(32768) });
    }
    boolean ☃ = false;
    ajt ☃ = null;
    int ☃ = -1;
    if (((☃.length < 11) || ((!☃[10].equals("force")) && (!☃[10].equals("move")))) && (☃.a(☃))) {
      throw new bz("commands.clone.noOverlap", new Object[0]);
    }
    if ((☃.length >= 11) && (☃[10].equals("move"))) {
      ☃ = true;
    }
    if ((☃.b < 0) || (☃.e >= 256) || (☃.b < 0) || (☃.e >= 256)) {
      throw new bz("commands.clone.outOfWorld", new Object[0]);
    }
    aht ☃ = ☃.e();
    if ((!☃.a(☃)) || (!☃.a(☃))) {
      throw new bz("commands.clone.outOfWorld", new Object[0]);
    }
    boolean ☃ = false;
    if (☃.length >= 10) {
      if (☃[9].equals("masked"))
      {
        ☃ = true;
      }
      else if (☃[9].equals("filtered"))
      {
        if (☃.length >= 12) {
          ☃ = b(☃, ☃[11]);
        } else {
          throw new cf("commands.clone.usage", new Object[0]);
        }
        if (☃.length >= 13) {
          ☃ = a(☃[12], 0, 15);
        }
      }
    }
    List<u.a> ☃ = Lists.newArrayList();
    List<u.a> ☃ = Lists.newArrayList();
    List<u.a> ☃ = Lists.newArrayList();
    LinkedList<cj> ☃ = Lists.newLinkedList();
    
    cj ☃ = new cj(☃.a - ☃.a, ☃.b - ☃.b, ☃.c - ☃.c);
    for (int ☃ = ☃.c; ☃ <= ☃.f; ☃++) {
      for (int ☃ = ☃.b; ☃ <= ☃.e; ☃++) {
        for (int ☃ = ☃.a; ☃ <= ☃.d; ☃++)
        {
          cj ☃ = new cj(☃, ☃, ☃);
          cj ☃ = ☃.a(☃);
          
          arc ☃ = ☃.o(☃);
          if ((!☃) || (☃.t() != aju.a)) {
            if (☃ != null)
            {
              if (☃.t() == ☃) {
                if ((☃ >= 0) && (☃.t().e(☃) != ☃)) {}
              }
            }
            else
            {
              apv ☃ = ☃.r(☃);
              if (☃ != null)
              {
                dn ☃ = new dn();
                ☃.b(☃);
                ☃.add(new u.a(☃, ☃, ☃));
                ☃.addLast(☃);
              }
              else if ((☃.b()) || (☃.h()))
              {
                ☃.add(new u.a(☃, ☃, null));
                ☃.addLast(☃);
              }
              else
              {
                ☃.add(new u.a(☃, ☃, null));
                ☃.addFirst(☃);
              }
            }
          }
        }
      }
    }
    if (☃)
    {
      for (cj ☃ : ☃)
      {
        apv ☃ = ☃.r(☃);
        if ((☃ instanceof qg)) {
          ((qg)☃).l();
        }
        ☃.a(☃, aju.cv.u(), 2);
      }
      for (cj ☃ : ☃) {
        ☃.a(☃, aju.a.u(), 3);
      }
    }
    List<u.a> ☃ = Lists.newArrayList();
    ☃.addAll(☃);
    ☃.addAll(☃);
    ☃.addAll(☃);
    
    List<u.a> ☃ = Lists.reverse(☃);
    for (u.a ☃ : ☃)
    {
      apv ☃ = ☃.r(☃.a);
      if ((☃ instanceof qg)) {
        ((qg)☃).l();
      }
      ☃.a(☃.a, aju.cv.u(), 2);
    }
    ☃ = 0;
    for (u.a ☃ : ☃) {
      if (☃.a(☃.a, ☃.b, 2)) {
        ☃++;
      }
    }
    for (u.a ☃ : ☃)
    {
      apv ☃ = ☃.r(☃.a);
      if ((☃.c != null) && (☃ != null))
      {
        ☃.c.a("x", ☃.a.p());
        ☃.c.a("y", ☃.a.q());
        ☃.c.a("z", ☃.a.r());
        ☃.a(☃.c);
        ☃.v_();
      }
      ☃.a(☃.a, ☃.b, 2);
    }
    for (u.a ☃ : ☃) {
      ☃.c(☃.a, ☃.b.t());
    }
    List<aie> ☃ = ☃.a(☃, false);
    if (☃ != null) {
      for (aie ☃ : ☃) {
        if (☃.b(☃.a))
        {
          cj ☃ = ☃.a.a(☃);
          ☃.b(☃, ☃.a(), (int)(☃.b - ☃.T().e()), ☃.c);
        }
      }
    }
    if (☃ <= 0) {
      throw new bz("commands.clone.failed", new Object[0]);
    }
    ☃.a(n.a.b, ☃);
    a(☃, this, "commands.clone.success", new Object[] { Integer.valueOf(☃) });
  }
  
  public List<String> a(MinecraftServer ☃, m ☃, String[] ☃, cj ☃)
  {
    if ((☃.length > 0) && (☃.length <= 3)) {
      return a(☃, 0, ☃);
    }
    if ((☃.length > 3) && (☃.length <= 6)) {
      return a(☃, 3, ☃);
    }
    if ((☃.length > 6) && (☃.length <= 9)) {
      return a(☃, 6, ☃);
    }
    if (☃.length == 10) {
      return a(☃, new String[] { "replace", "masked", "filtered" });
    }
    if (☃.length == 11) {
      return a(☃, new String[] { "normal", "force", "move" });
    }
    if ((☃.length == 12) && ("filtered".equals(☃[9]))) {
      return a(☃, ajt.h.c());
    }
    return Collections.emptyList();
  }
  
  static class a
  {
    public final cj a;
    public final arc b;
    public final dn c;
    
    public a(cj ☃, arc ☃, dn ☃)
    {
      this.a = ☃;
      this.b = ☃;
      this.c = ☃;
    }
  }
}
