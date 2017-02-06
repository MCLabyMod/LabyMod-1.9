import java.util.List;
import java.util.UUID;

public class ev
{
  public static eu a(m ☃, eu ☃, rr ☃)
    throws bz
  {
    eu ☃ = null;
    if ((☃ instanceof ex))
    {
      ex ☃ = (ex)☃;
      String ☃ = ☃.g();
      if (o.b(☃))
      {
        List<rr> ☃ = o.b(☃, ☃, rr.class);
        if (☃.size() != 1) {
          throw new ca();
        }
        rr ☃ = (rr)☃.get(0);
        if ((☃ instanceof zj)) {
          ☃ = ☃.h_();
        } else {
          ☃ = ☃.bc().toString();
        }
      }
      ☃ = (☃ != null) && (☃.equals("*")) ? new ex(☃.h_(), ☃.h()) : new ex(☃, ☃.h());
      
      ((ex)☃).a(☃);
    }
    else if ((☃ instanceof ey))
    {
      String ☃ = ((ey)☃).g();
      ☃ = o.b(☃, ☃);
      if (☃ == null) {
        ☃ = new fa("");
      }
    }
    else if ((☃ instanceof fa))
    {
      ☃ = new fa(((fa)☃).g());
    }
    else if ((☃ instanceof fb))
    {
      Object[] ☃ = ((fb)☃).j();
      for (int ☃ = 0; ☃ < ☃.length; ☃++)
      {
        Object ☃ = ☃[☃];
        if ((☃ instanceof eu)) {
          ☃[☃] = a(☃, (eu)☃, ☃);
        }
      }
      ☃ = new fb(((fb)☃).i(), ☃);
    }
    else
    {
      return ☃;
    }
    ez ☃ = ☃.b();
    if (☃ != null) {
      ☃.a(☃.m());
    }
    for (eu ☃ : ☃.a()) {
      ☃.a(a(☃, ☃, ☃));
    }
    return ☃;
  }
}
