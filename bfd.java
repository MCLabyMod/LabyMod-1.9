import de.labystudio.gui.GuiCapeSettings;
import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class bfd
  extends bfb
{
  private final bfb jdField_a_of_type_Bfb;
  private String f;
  boolean advanced = false;
  bcz jdField_a_of_type_Bcz;
  bcz b;
  bcz c;
  bcz d;
  bcz e;
  
  public bfd(bfb parentScreenIn)
  {
    this.jdField_a_of_type_Bfb = parentScreenIn;
  }
  
  public void advancedGui()
  {
    this.n.clear();
    if (this.advanced)
    {
      int var1 = 0;
      zk[] var2 = zk.values();
      int var3 = var2.length;
      zk var5 = var2[6];
      this.n.add(new bfd.a(var5.b(), this.l / 2 - 40 + var1 % 2 * 160, this.m / 6 - 24 + 25, 80, 20, var5, null));
      
      var5 = var2[1];
      this.n.add(new bfd.a(var5.b(), this.l / 2 - 40 + var1 % 2 * 160, this.m / 6 + 24 + 20, 80, 20, var5, null));
      
      var5 = var2[3];
      this.n.add(new bfd.a(var5.b(), this.l / 2 - 40 + var1 % 2 * 160 + 90, this.m / 6 + 24 + 0, 100, 20, var5, null));
      
      var5 = var2[2];
      this.n.add(new bfd.a(var5.b(), this.l / 2 - 40 + var1 % 2 * 160 - 110, this.m / 6 + 24 + 0, 100, 20, var5, null));
      
      var5 = var2[4];
      this.n.add(new bfd.a(var5.b(), this.l / 2 - 55 + var1 % 2 * 160 - 120, this.m / 6 + 24 + 50, 130, 20, var5, null));
      
      var5 = var2[5];
      this.n.add(new bfd.a(var5.b(), this.l / 2 - 35 + var1 % 2 * 160 + 80, this.m / 6 + 24 + 50, 130, 20, var5, null));
      
      var5 = var2[0];
      this.n.add(new bfd.a(var5.b(), this.l / 2 - 40 + var1 % 2 * 160, this.m / 6 + 24 + 80, 80, 20, var5, null));
      bcz b;
      this.n.add(b = new bdm(199, this.l / 2 - 50, this.m / 2 + 55, bch.a.H, this.j.u.c(bch.a.H)));
      b.a(100);
      for (int i = 0; i <= this.n.size() - 1; i++)
      {
        bcz button = (bcz)this.n.get(i);
        
        button.j = button.j.replace("ON", Color.c + "aON").replace("OFF", Color.c + "cOFF").replace("An", Color.c + "aAn").replace("Aus", Color.c + "cAus");
      }
    }
    else
    {
      this.n.add(this.jdField_a_of_type_Bcz = new bcz(1, this.l / 2 + 40, this.m / 2 - 58, 80, 20, ""));
      this.n.add(this.b = new bcz(2, this.l / 2 + 40, this.m / 2 - 33, 80, 20, ""));
      this.n.add(this.c = new bcz(3, this.l / 2 + 40, this.m / 2 - 8, 80, 20, ""));
    }
    this.n.add(this.d = new bcz(40, this.l / 2 + 40, this.m / 2 + 17, 80, 20, ""));
    this.n.add(this.e = new bcz(10, 2, 2, 80, 20, ""));
    
    refreshButton();
  }
  
  public void refreshButton()
  {
    if (this.jdField_a_of_type_Bcz != null)
    {
      this.jdField_a_of_type_Bcz.j = getStatus("Hat", zk.g);
      this.b.j = getStatus("Jacket", zk.b);
      this.c.j = getStatus("Pants", zk.f);
    }
    if (this.d != null) {
      if (!this.advanced)
      {
        this.d.j = "Advanced..";
      }
      else
      {
        this.d.j = "Simple..";
        this.d.i = (this.m / 2 + 55);
        this.d.h = (this.l / 2 + 55);
      }
    }
    if (this.e != null)
    {
      this.e.j = "Cape Settings";
      if (this.advanced)
      {
        this.e.i = (this.m / 2 + 55);
        this.e.h = (this.l / 2 - 135);
      }
      else
      {
        this.e.i = (this.m / 2 + 43);
        this.e.h = (this.l / 2 + 40);
      }
    }
  }
  
  public String getStatus(String name, zk part)
  {
    String var2;
    String var2;
    if (this.j.u.d().contains(part)) {
      var2 = Color.cl("a") + "ON";
    } else {
      var2 = Color.cl("C") + "OFF";
    }
    return name + ": " + var2 + Color.cl("f") + "";
  }
  
  public static void drawEntityOnScreen(int p_147046_0_, int p_147046_1_, int p_147046_2_, float p_147046_3_, float p_147046_4_, sa p_147046_5_)
  {
    bni.h();
    bni.G();
    bni.c(p_147046_0_, p_147046_1_, 50.0F);
    bni.b(-p_147046_2_ - 30.0F, p_147046_2_ + 30.0F, p_147046_2_);
    bni.b(180.0F, 0.0F, 0.0F, 1.0F);
    float var6 = p_147046_5_.aM;
    float var7 = p_147046_5_.v;
    float var8 = p_147046_5_.w;
    float var9 = p_147046_5_.aP;
    float var10 = p_147046_5_.aO;
    bni.b(135.0F, 0.0F, 1.0F, 0.0F);
    bcd.b();
    bni.b(-135.0F, 0.0F, 1.0F, 0.0F);
    bni.b(-(float)Math.atan(p_147046_4_ / 40.0F) * 20.0F, 1.0F, 0.0F, 0.0F);
    p_147046_5_.aM = ((float)Math.atan(p_147046_3_ / 40.0F) * 20.0F);
    p_147046_5_.v = ((float)Math.atan(p_147046_3_ / 40.0F) * 40.0F);
    p_147046_5_.w = (-(float)Math.atan(p_147046_4_ / 40.0F) * 20.0F);
    p_147046_5_.aO = p_147046_5_.v;
    p_147046_5_.aP = p_147046_5_.v;
    bni.c(0.0F, 0.0F, 0.0F);
    brm var11 = bcf.z().ac();
    var11.a(180.0F);
    var11.a(false);
    var11.a(p_147046_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
    var11.a(true);
    p_147046_5_.aM = var6;
    p_147046_5_.v = var7;
    p_147046_5_.w = var8;
    p_147046_5_.aP = var9;
    p_147046_5_.aO = var10;
    bni.H();
    bcd.a();
    bni.E();
    bni.g(bzg.r);
    bni.z();
    bni.g(bzg.q);
  }
  
  public void b()
  {
    int i = 0;
    this.f = bwo.a("options.skinCustomisation.title", new Object[0]);
    advancedGui();
    this.n.add(new bcz(200, this.l / 2 - 100, this.m / 2 + 90, bwo.a("gui.done", new Object[0])));
  }
  
  protected void a(bcz button)
    throws IOException
  {
    if (button.l)
    {
      if (!this.advanced) {
        if (button.k == 1)
        {
          this.j.u.a(zk.g);
          refreshButton();
        }
        else if (button.k == 2)
        {
          this.j.u.a(zk.b);
          this.j.u.a(zk.d);
          this.j.u.a(zk.c);
          refreshButton();
        }
        else if (button.k == 3)
        {
          this.j.u.a(zk.e);
          this.j.u.a(zk.f);
          refreshButton();
        }
      }
      if (button.k == 40)
      {
        this.advanced = (!this.advanced);
        b();
      }
      if (button.k == 10)
      {
        this.j.u.b();
        this.j.a(new GuiCapeSettings(this));
      }
      if (button.k == 200)
      {
        this.j.u.b();
        this.j.a(this.jdField_a_of_type_Bfb);
      }
      else if (button.k == 199)
      {
        this.j.u.a(bch.a.H, 1);
        button.j = this.j.u.c(bch.a.H);
        this.j.u.c();
      }
      else if ((button instanceof bfd.a))
      {
        zk var2 = bfd.a.a((bfd.a)button);
        this.j.u.a(var2);
        button.j = a(var2);
        
        button.j = button.j.replace("ON", Color.c + "aON").replace("OFF", Color.c + "cOFF").replace("An", Color.c + "aAn").replace("Aus", Color.c + "cAus");
        this.j.u.b();
      }
    }
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    c();
    a(this.q, this.f, this.l / 2, 20, 16777215);
    if (this.j.h != null)
    {
      if (this.advanced) {
        drawEntityOnScreen(40, this.m - 10, 30, 40.0F - mouseX, this.m - 10 - 120 + 75 - 50 - mouseY, this.j.h);
      } else {
        drawEntityOnScreen(this.l / 2 - 20, this.m / 2 + 60, 30, this.l / 2 - 20 - mouseX, this.m / 2 - 40 - mouseY, this.j.h);
      }
      if (this.j.E()) {
        LabyMod.getInstance().draw.drawString(Color.cl("c") + "Preview is not live!", 3.0D, this.m - 10);
      }
    }
    else if (!this.advanced)
    {
      DrawUtils.a(this.l / 2 - 110, this.m / 2 - 50, this.l / 2 + 30, this.m / 2 + 45, 1129010000);
      LabyMod.getInstance().draw.drawCenteredString("Preview not available.", this.l / 2 - 40, this.m / 2 - 15);
      LabyMod.getInstance().draw.drawCenteredString(Color.cl("c") + "You are not ingame!", this.l / 2 - 40, this.m / 2 - 5);
    }
    if (!LabyMod.getInstance().isInGame()) {
      this.e.l = false;
    }
    super.a(mouseX, mouseY, partialTicks);
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {
    if (keyCode == 1)
    {
      this.j.u.b();
      this.j.a(this.jdField_a_of_type_Bfb);
    }
  }
  
  private String a(zk playerModelParts)
  {
    String s;
    String s;
    if (this.j.u.d().contains(playerModelParts)) {
      s = bwo.a("options.on", new Object[0]);
    } else {
      s = bwo.a("options.off", new Object[0]);
    }
    return playerModelParts.d().d() + ": " + s;
  }
  
  class a
    extends bcz
  {
    private final zk p;
    
    private a(int p_i45514_2_, int p_i45514_3_, int p_i45514_4_, int p_i45514_5_, int p_i45514_6_, zk playerModelParts)
    {
      super(p_i45514_3_, p_i45514_4_, p_i45514_5_, p_i45514_6_, bfd.a(bfd.this, playerModelParts));
      this.p = playerModelParts;
    }
  }
}
