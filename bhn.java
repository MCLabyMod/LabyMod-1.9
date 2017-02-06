import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bhn
  implements bdl.a
{
  private static final Logger a = ;
  private static final DateFormat b = new SimpleDateFormat();
  private static final kk c = new kk("textures/misc/unknown_server.png");
  private static final kk d = new kk("textures/gui/world_selection.png");
  private final bcf e;
  private final bhm f;
  private final azl g;
  private final kk h;
  private final bho i;
  private File j;
  private bux k;
  private long l;
  
  public bhn(bho ☃, azl ☃, azk ☃)
  {
    this.i = ☃;
    this.f = ☃.g();
    this.g = ☃;
    this.e = bcf.z();
    this.h = new kk("worlds/" + ☃.a() + "/icon");
    this.j = ☃.b(☃.a(), "icon.png");
    if (!this.j.isFile()) {
      this.j = null;
    }
    f();
  }
  
  public void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    String ☃ = this.g.b();
    String ☃ = this.g.a() + " (" + b.format(new Date(this.g.e())) + ")";
    String ☃ = "";
    if (StringUtils.isEmpty(☃)) {
      ☃ = bwo.a("selectWorld.world", new Object[0]) + " " + (☃ + 1);
    }
    if (this.g.d())
    {
      ☃ = bwo.a("selectWorld.conversion", new Object[0]) + " " + ☃;
    }
    else
    {
      ☃ = bwo.a("gameMode." + this.g.f().b(), new Object[0]);
      if (this.g.g()) {
        ☃ = a.e + bwo.a("gameMode.hardcore", new Object[0]) + a.v;
      }
      if (this.g.h()) {
        ☃ = ☃ + ", " + bwo.a("selectWorld.cheats", new Object[0]);
      }
      String ☃ = this.g.i();
      if (this.g.l())
      {
        if (this.g.m()) {
          ☃ = ☃ + ", " + bwo.a("selectWorld.version", new Object[0]) + " " + a.m + ☃ + a.v;
        } else {
          ☃ = ☃ + ", " + bwo.a("selectWorld.version", new Object[0]) + " " + a.u + ☃ + a.v;
        }
      }
      else {
        ☃ = ☃ + ", " + bwo.a("selectWorld.version", new Object[0]) + " " + ☃;
      }
    }
    this.e.k.a(☃, ☃ + 32 + 3, ☃ + 1, 16777215);
    this.e.k.a(☃, ☃ + 32 + 3, ☃ + this.e.k.a + 3, 8421504);
    this.e.k.a(☃, ☃ + 32 + 3, ☃ + this.e.k.a + this.e.k.a + 3, 8421504);
    
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    this.e.N().a(this.k != null ? this.h : c);
    bni.m();
    bcv.a(☃, ☃, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
    bni.l();
    if ((this.e.u.z) || (☃))
    {
      this.e.N().a(d);
      bcv.a(☃, ☃, ☃ + 32, ☃ + 32, -1601138544);
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      int ☃ = ☃ - ☃;
      
      int ☃ = ☃ < 32 ? 32 : 0;
      if (this.g.l())
      {
        bcv.a(☃, ☃, 32.0F, ☃, 32, 32, 256.0F, 256.0F);
        if (this.g.m())
        {
          bcv.a(☃, ☃, 96.0F, ☃, 32, 32, 256.0F, 256.0F);
          if (☃ < 32) {
            this.f.a(a.m + bwo.a("selectWorld.tooltip.fromNewerVersion1", new Object[0]) + "\n" + a.m + bwo.a("selectWorld.tooltip.fromNewerVersion2", new Object[0]));
          }
        }
        else
        {
          bcv.a(☃, ☃, 64.0F, ☃, 32, 32, 256.0F, 256.0F);
          if (☃ < 32) {
            this.f.a(a.g + bwo.a("selectWorld.tooltip.snapshot1", new Object[0]) + "\n" + a.g + bwo.a("selectWorld.tooltip.snapshot2", new Object[0]));
          }
        }
      }
      else
      {
        bcv.a(☃, ☃, 0.0F, ☃, 32, 32, 256.0F, 256.0F);
      }
    }
  }
  
  public boolean a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    this.i.d(☃);
    if ((☃ <= 32) && 
      (☃ < 32))
    {
      a();
      return true;
    }
    if (bcf.I() - this.l < 250L)
    {
      a();
      return true;
    }
    this.l = bcf.I();
    return false;
  }
  
  public void a()
  {
    if (this.g.m()) {
      this.e.a(new beh(new beg()
      {
        public void a(boolean ☃, int ☃)
        {
          if (☃) {
            bhn.a(bhn.this);
          } else {
            bhn.c(bhn.this).a(bhn.b(bhn.this));
          }
        }
      }, bwo.a("selectWorld.versionQuestion", new Object[0]), bwo.a("selectWorld.versionWarning", new Object[] { this.g.i() }), bwo.a("selectWorld.versionJoinButton", new Object[0]), bwo.a("gui.cancel", new Object[0]), 0));
    } else {
      e();
    }
  }
  
  public void b()
  {
    this.e.a(new beh(new beg()
    {
      public void a(boolean ☃, int ☃)
      {
        if (☃)
        {
          bhn.c(bhn.this).a(new bez());
          azk ☃ = bhn.c(bhn.this).g();
          ☃.d();
          ☃.e(bhn.d(bhn.this).a());
          bhn.e(bhn.this).e();
        }
        bhn.c(bhn.this).a(bhn.b(bhn.this));
      }
    }, bwo.a("selectWorld.deleteQuestion", new Object[0]), "'" + this.g.b() + "' " + bwo.a("selectWorld.deleteWarning", new Object[0]), bwo.a("selectWorld.deleteButton", new Object[0]), bwo.a("gui.cancel", new Object[0]), 0));
  }
  
  public void c()
  {
    this.e.a(new bhl(this.f, this.g.a()));
  }
  
  public void d()
  {
    this.e.a(new bez());
    bhk ☃ = new bhk(this.f);
    azi ☃ = this.e.g().a(this.g.a(), false);
    azh ☃ = ☃.d();
    ☃.a();
    
    ☃.a(☃);
    this.e.a(☃);
  }
  
  private void e()
  {
    this.e.U().a(bye.a(ng.go, 1.0F));
    if (this.e.g().f(this.g.a())) {
      this.e.a(this.g.a(), this.g.b(), null);
    }
  }
  
  private void f()
  {
    boolean ☃ = (this.j != null) && (this.j.isFile());
    if (☃)
    {
      BufferedImage ☃;
      try
      {
        ☃ = ImageIO.read(this.j);
        Validate.validState(☃.getWidth() == 64, "Must be 64 pixels wide", new Object[0]);
        Validate.validState(☃.getHeight() == 64, "Must be 64 pixels high", new Object[0]);
      }
      catch (Throwable ☃)
      {
        a.error("Invalid icon for world " + this.g.a(), ☃);
        this.j = null;
        return;
      }
      if (this.k == null)
      {
        this.k = new bux(☃.getWidth(), ☃.getHeight());
        this.e.N().a(this.h, this.k);
      }
      ☃.getRGB(0, 0, ☃.getWidth(), ☃.getHeight(), this.k.e(), 0, ☃.getWidth());
      this.k.d();
    }
    else if (!☃)
    {
      this.e.N().c(this.h);
      this.k = null;
    }
  }
  
  public void b(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃) {}
  
  public void a(int ☃, int ☃, int ☃) {}
}
