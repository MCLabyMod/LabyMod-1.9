import de.labystudio.gommehd.GommeHDSign;
import java.util.ArrayList;
import net.minecraft.server.MinecraftServer;

public class aqn
  extends apv
{
  public final eu[] a = { new fa(""), new fa(""), new fa(""), new fa("") };
  public int f = -1;
  private boolean g = true;
  private zj h;
  private final n i = new n();
  
  public void b(dn compound)
  {
    super.b(compound);
    for (int i = 0; i < 4; i++)
    {
      String s = eu.a.a(this.a[i]);
      compound.a("Text" + (i + 1), s);
    }
    this.i.b(compound);
  }
  
  public void a(dn compound)
  {
    this.g = false;
    super.a(compound);
    m icommandsender = new m()
    {
      public String h_()
      {
        return "Sign";
      }
      
      public eu i_()
      {
        return new fa(h_());
      }
      
      public void a(eu component) {}
      
      public boolean a(int permLevel, String commandName)
      {
        return true;
      }
      
      public cj c()
      {
        return aqn.this.c;
      }
      
      public bbj d()
      {
        return new bbj(aqn.this.c.p() + 0.5D, aqn.this.c.q() + 0.5D, aqn.this.c.r() + 0.5D);
      }
      
      public aht e()
      {
        return aqn.this.b;
      }
      
      public rr f()
      {
        return null;
      }
      
      public boolean z_()
      {
        return false;
      }
      
      public void a(n.a type, int amount) {}
      
      public MinecraftServer h()
      {
        return aqn.this.b.u();
      }
    };
    for (int i = 0; i < 4; i++)
    {
      String s = compound.l("Text" + (i + 1));
      eu itextcomponent = eu.a.a(s);
      try
      {
        this.a[i] = ev.a(icommandsender, itextcomponent, (rr)null);
      }
      catch (bz var7)
      {
        this.a[i] = itextcomponent;
      }
    }
    this.i.a(compound);
  }
  
  public ff<?> D_()
  {
    eu[] aitextcomponent = new eu[4];
    System.arraycopy(this.a, 0, aitextcomponent, 0, 4);
    return new hy(this.b, this.c, aitextcomponent);
  }
  
  public boolean B()
  {
    return true;
  }
  
  public boolean b()
  {
    return this.g;
  }
  
  public void a(boolean isEditableIn)
  {
    this.g = isEditableIn;
    if (!isEditableIn) {
      this.h = null;
    }
  }
  
  public void a(zj playerIn)
  {
    this.h = playerIn;
  }
  
  public zj c()
  {
    return this.h;
  }
  
  public boolean b(final zj playerIn)
  {
    m icommandsender = new m()
    {
      public String h_()
      {
        return playerIn.h_();
      }
      
      public eu i_()
      {
        return playerIn.i_();
      }
      
      public void a(eu component) {}
      
      public boolean a(int permLevel, String commandName)
      {
        return permLevel <= 2;
      }
      
      public cj c()
      {
        return aqn.this.c;
      }
      
      public bbj d()
      {
        return new bbj(aqn.this.c.p() + 0.5D, aqn.this.c.q() + 0.5D, aqn.this.c.r() + 0.5D);
      }
      
      public aht e()
      {
        return playerIn.e();
      }
      
      public rr f()
      {
        return playerIn;
      }
      
      public boolean z_()
      {
        return false;
      }
      
      public void a(n.a type, int amount)
      {
        if ((aqn.this.b != null) && (!aqn.this.b.E)) {
          aqn.a(aqn.this).a(aqn.this.b.u(), this, type, amount);
        }
      }
      
      public MinecraftServer h()
      {
        return playerIn.h();
      }
    };
    for (int i = 0; i < this.a.length; i++)
    {
      ez style = this.a[i] == null ? null : this.a[i].b();
      if ((style != null) && (style.h() != null))
      {
        et clickevent = style.h();
        if (clickevent.a() == et.a.c) {
          playerIn.h().N().a(icommandsender, clickevent.b());
        }
      }
    }
    return true;
  }
  
  public n d()
  {
    return this.i;
  }
  
  public int updateSign = 0;
  ArrayList<String> text = new ArrayList();
  boolean available = false;
  boolean search = false;
  boolean size = false;
  boolean full = false;
  
  public void setText(ArrayList<String> arrayList)
  {
    this.text = GommeHDSign.getText(this);
  }
  
  public ArrayList<String> getText()
  {
    return this.text;
  }
  
  public void setAvailable(ArrayList<String> text)
  {
    this.available = GommeHDSign.isAvailable(text);
  }
  
  public boolean getAvailable()
  {
    return this.available;
  }
  
  public void setSearch(ArrayList<String> text)
  {
    this.search = GommeHDSign.search(text);
  }
  
  public boolean getSearch()
  {
    return this.search;
  }
  
  public void setSize(ArrayList<String> text)
  {
    this.size = GommeHDSign.size(text);
  }
  
  public boolean getSize()
  {
    return this.size;
  }
  
  public void setFull(ArrayList<String> text)
  {
    this.full = GommeHDSign.isFull(text);
  }
  
  public boolean isFull()
  {
    return this.full;
  }
}
