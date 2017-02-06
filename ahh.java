import java.io.IOException;
import java.util.ArrayList;

public class ahh
  extends ArrayList<ahg>
{
  public ahh() {}
  
  public ahh(dn ☃)
  {
    a(☃);
  }
  
  public ahg a(adq ☃, adq ☃, int ☃)
  {
    if ((☃ > 0) && (☃ < size()))
    {
      ahg ☃ = (ahg)get(☃);
      if ((a(☃, ☃.a())) && (((☃ == null) && (!☃.c())) || ((☃.c()) && (a(☃, ☃.b())) && 
        (☃.b >= ☃.a().b) && ((!☃.c()) || (☃.b >= ☃.b().b))))) {
        return ☃;
      }
      return null;
    }
    for (int ☃ = 0; ☃ < size(); ☃++)
    {
      ahg ☃ = (ahg)get(☃);
      if ((a(☃, ☃.a())) && (☃.b >= ☃.a().b) && (((!☃.c()) && (☃ == null)) || ((☃.c()) && (a(☃, ☃.b())) && (☃.b >= ☃.b().b)))) {
        return ☃;
      }
    }
    return null;
  }
  
  private boolean a(adq ☃, adq ☃)
  {
    return (adq.c(☃, ☃)) && ((!☃.n()) || ((☃.n()) && (dy.a(☃.o(), ☃.o(), false))));
  }
  
  public void a(em ☃)
  {
    ☃.writeByte((byte)(size() & 0xFF));
    for (int ☃ = 0; ☃ < size(); ☃++)
    {
      ahg ☃ = (ahg)get(☃);
      ☃.a(☃.a());
      ☃.a(☃.d());
      
      adq ☃ = ☃.b();
      ☃.writeBoolean(☃ != null);
      if (☃ != null) {
        ☃.a(☃);
      }
      ☃.writeBoolean(☃.h());
      ☃.writeInt(☃.e());
      ☃.writeInt(☃.f());
    }
  }
  
  public static ahh b(em ☃)
    throws IOException
  {
    ahh ☃ = new ahh();
    
    int ☃ = ☃.readByte() & 0xFF;
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      adq ☃ = ☃.k();
      adq ☃ = ☃.k();
      
      adq ☃ = null;
      if (☃.readBoolean()) {
        ☃ = ☃.k();
      }
      boolean ☃ = ☃.readBoolean();
      int ☃ = ☃.readInt();
      int ☃ = ☃.readInt();
      
      ahg ☃ = new ahg(☃, ☃, ☃, ☃, ☃);
      if (☃) {
        ☃.i();
      }
      ☃.add(☃);
    }
    return ☃;
  }
  
  public void a(dn ☃)
  {
    du ☃ = ☃.c("Recipes", 10);
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      dn ☃ = ☃.b(☃);
      add(new ahg(☃));
    }
  }
  
  public dn a()
  {
    dn ☃ = new dn();
    
    du ☃ = new du();
    for (int ☃ = 0; ☃ < size(); ☃++)
    {
      ahg ☃ = (ahg)get(☃);
      ☃.a(☃.k());
    }
    ☃.a("Recipes", ☃);
    return ☃;
  }
}
