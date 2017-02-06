import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bfk
  extends bfb
{
  private static final Logger a = ;
  private static final kk f = new kk("textures/gui/title/minecraft.png");
  private static final kk g = new kk("textures/misc/vignette.png");
  private int h;
  private List<String> i;
  private int r;
  private float s = 0.5F;
  
  public void e()
  {
    byu ☃ = this.j.s();
    byx ☃ = this.j.U();
    if (this.h == 0)
    {
      ☃.a();
      ☃.a(byu.a.d);
      ☃.e();
    }
    ☃.c();
    this.h += 1;
    float ☃ = (this.r + this.m + this.m + 24) / this.s;
    if (this.h > ☃) {
      a();
    }
  }
  
  protected void a(char ☃, int ☃)
  {
    if (☃ == 1) {
      a();
    }
  }
  
  private void a()
  {
    this.j.h.d.a(new ik(ik.a.a));
    this.j.a(null);
  }
  
  public boolean d()
  {
    return true;
  }
  
  public void b()
  {
    if (this.i != null) {
      return;
    }
    this.i = Lists.newArrayList();
    bwf ☃ = null;
    try
    {
      String ☃ = "";
      String ☃ = "" + a.p + a.q + a.k + a.l;
      int ☃ = 274;
      ☃ = this.j.O().a(new kk("texts/end.txt"));
      InputStream ☃ = ☃.b();
      BufferedReader ☃ = new BufferedReader(new InputStreamReader(☃, Charsets.UTF_8));
      Random ☃ = new Random(8124371L);
      while ((☃ = ☃.readLine()) != null)
      {
        ☃ = ☃.replaceAll("PLAYERNAME", this.j.K().c());
        while (☃.contains(☃))
        {
          int ☃ = ☃.indexOf(☃);
          String ☃ = ☃.substring(0, ☃);
          String ☃ = ☃.substring(☃ + ☃.length());
          ☃ = ☃ + a.p + a.q + "XXXXXXXX".substring(0, ☃.nextInt(4) + 3) + ☃;
        }
        this.i.addAll(this.j.k.c(☃, ☃));
        this.i.add("");
      }
      ☃.close();
      for (int ☃ = 0; ☃ < 8; ☃++) {
        this.i.add("");
      }
      ☃ = this.j.O().a(new kk("texts/credits.txt")).b();
      ☃ = new BufferedReader(new InputStreamReader(☃, Charsets.UTF_8));
      while ((☃ = ☃.readLine()) != null)
      {
        ☃ = ☃.replaceAll("PLAYERNAME", this.j.K().c());
        ☃ = ☃.replaceAll("\t", "    ");
        
        this.i.addAll(this.j.k.c(☃, ☃));
        this.i.add("");
      }
      ☃.close();
      
      this.r = (this.i.size() * 12);
    }
    catch (Exception ☃)
    {
      a.error("Couldn't load credits", ☃);
    }
    finally
    {
      IOUtils.closeQuietly(☃);
    }
  }
  
  private void b(int ☃, int ☃, float ☃)
  {
    bnu ☃ = bnu.a();
    bmz ☃ = ☃.c();
    
    this.j.N().a(bcv.b);
    ☃.a(7, bvp.i);
    int ☃ = this.l;
    float ☃ = 0.0F - (this.h + ☃) * 0.5F * this.s;
    float ☃ = this.m - (this.h + ☃) * 0.5F * this.s;
    float ☃ = 0.015625F;
    
    float ☃ = (this.h + ☃ - 0.0F) * 0.02F;
    
    float ☃ = (this.r + this.m + this.m + 24) / this.s;
    float ☃ = (☃ - 20.0F - (this.h + ☃)) * 0.005F;
    if (☃ < ☃) {
      ☃ = ☃;
    }
    if (☃ > 1.0F) {
      ☃ = 1.0F;
    }
    ☃ *= ☃;
    ☃ = ☃ * 96.0F / 255.0F;
    ☃.b(0.0D, this.m, this.e).a(0.0D, ☃ * ☃).a(☃, ☃, ☃, 1.0F).d();
    ☃.b(☃, this.m, this.e).a(☃ * ☃, ☃ * ☃).a(☃, ☃, ☃, 1.0F).d();
    ☃.b(☃, 0.0D, this.e).a(☃ * ☃, ☃ * ☃).a(☃, ☃, ☃, 1.0F).d();
    ☃.b(0.0D, 0.0D, this.e).a(0.0D, ☃ * ☃).a(☃, ☃, ☃, 1.0F).d();
    ☃.b();
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    b(☃, ☃, ☃);
    bnu ☃ = bnu.a();
    bmz ☃ = ☃.c();
    
    int ☃ = 274;
    int ☃ = this.l / 2 - ☃ / 2;
    int ☃ = this.m + 50;
    
    float ☃ = -(this.h + ☃) * this.s;
    bni.G();
    bni.c(0.0F, ☃, 0.0F);
    this.j.N().a(f);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    b(☃, ☃, 0, 0, 155, 44);
    b(☃ + 155, ☃, 0, 45, 155, 44);
    int ☃ = ☃ + 200;
    for (int ☃ = 0; ☃ < this.i.size(); ☃++)
    {
      if (☃ == this.i.size() - 1)
      {
        float ☃ = ☃ + ☃ - (this.m / 2 - 6);
        if (☃ < 0.0F) {
          bni.c(0.0F, -☃, 0.0F);
        }
      }
      if ((☃ + ☃ + 12.0F + 8.0F > 0.0F) && (☃ + ☃ < this.m))
      {
        String ☃ = (String)this.i.get(☃);
        if (☃.startsWith("[C]"))
        {
          this.q.a(☃.substring(3), ☃ + (☃ - this.q.a(☃.substring(3))) / 2, ☃, 16777215);
        }
        else
        {
          this.q.b.setSeed(☃ * 4238972211L + this.h / 4);
          this.q.a(☃, ☃, ☃, 16777215);
        }
      }
      ☃ += 12;
    }
    bni.H();
    
    this.j.N().a(g);
    bni.m();
    bni.a(bni.r.o, bni.l.k);
    
    int ☃ = this.l;
    int ☃ = this.m;
    
    ☃.a(7, bvp.i);
    ☃.b(0.0D, ☃, this.e).a(0.0D, 1.0D).a(1.0F, 1.0F, 1.0F, 1.0F).d();
    ☃.b(☃, ☃, this.e).a(1.0D, 1.0D).a(1.0F, 1.0F, 1.0F, 1.0F).d();
    ☃.b(☃, 0.0D, this.e).a(1.0D, 0.0D).a(1.0F, 1.0F, 1.0F, 1.0F).d();
    ☃.b(0.0D, 0.0D, this.e).a(0.0D, 0.0D).a(1.0F, 1.0F, 1.0F, 1.0F).d();
    ☃.b();
    
    bni.l();
    
    super.a(☃, ☃, ☃);
  }
}
