import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import org.lwjgl.util.glu.Project;

public class bgg
  extends bft
{
  private static final kk C = new kk("textures/gui/container/enchanting_table.png");
  private static final kk D = new kk("textures/entity/enchanting_table_book.png");
  private static final bil E = new bil();
  private final zi F;
  private Random G = new Random();
  private abf H;
  public int u;
  public float v;
  public float w;
  public float x;
  public float y;
  public float z;
  public float A;
  adq B;
  private final qt I;
  
  public bgg(zi ☃, aht ☃, qt ☃)
  {
    super(new abf(☃, ☃));
    this.F = ☃;
    this.H = ((abf)this.h);
    this.I = ☃;
  }
  
  protected void b(int ☃, int ☃)
  {
    this.q.a(this.I.i_().c(), 12, 5, 4210752);
    this.q.a(this.F.i_().c(), 8, this.g - 96 + 2, 4210752);
  }
  
  public void e()
  {
    super.e();
    a();
  }
  
  protected void a(int ☃, int ☃, int ☃)
  {
    super.a(☃, ☃, ☃);
    
    int ☃ = (this.l - this.f) / 2;
    int ☃ = (this.m - this.g) / 2;
    for (int ☃ = 0; ☃ < 3; ☃++)
    {
      int ☃ = ☃ - (☃ + 60);
      int ☃ = ☃ - (☃ + 14 + 19 * ☃);
      if ((☃ >= 0) && (☃ >= 0) && (☃ < 108) && (☃ < 19) && 
        (this.H.a(this.j.h, ☃))) {
        this.j.c.a(this.H.d, ☃);
      }
    }
  }
  
  protected void a(float ☃, int ☃, int ☃)
  {
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    this.j.N().a(C);
    int ☃ = (this.l - this.f) / 2;
    int ☃ = (this.m - this.g) / 2;
    b(☃, ☃, 0, 0, this.f, this.g);
    
    bni.G();
    bni.n(5889);
    bni.G();
    bni.F();
    bcx ☃ = new bcx(this.j);
    
    bni.b((☃.a() - 320) / 2 * ☃.e(), (☃.b() - 240) / 2 * ☃.e(), 320 * ☃.e(), 240 * ☃.e());
    bni.c(-0.34F, 0.23F, 0.0F);
    
    Project.gluPerspective(90.0F, 1.3333334F, 9.0F, 80.0F);
    
    float ☃ = 1.0F;
    bni.n(5888);
    bni.F();
    bcd.b();
    bni.c(0.0F, 3.3F, -16.0F);
    bni.b(☃, ☃, ☃);
    float ☃ = 5.0F;
    bni.b(☃, ☃, ☃);
    bni.b(180.0F, 0.0F, 0.0F, 1.0F);
    
    this.j.N().a(D);
    bni.b(20.0F, 1.0F, 0.0F, 0.0F);
    float ☃ = this.A + (this.z - this.A) * ☃;
    bni.c((1.0F - ☃) * 0.2F, (1.0F - ☃) * 0.1F, (1.0F - ☃) * 0.25F);
    bni.b(-(1.0F - ☃) * 90.0F - 90.0F, 0.0F, 1.0F, 0.0F);
    bni.b(180.0F, 1.0F, 0.0F, 0.0F);
    
    float ☃ = this.w + (this.v - this.w) * ☃ + 0.25F;
    float ☃ = this.w + (this.v - this.w) * ☃ + 0.75F;
    ☃ = (☃ - on.b(☃)) * 1.6F - 0.3F;
    ☃ = (☃ - on.b(☃)) * 1.6F - 0.3F;
    if (☃ < 0.0F) {
      ☃ = 0.0F;
    }
    if (☃ < 0.0F) {
      ☃ = 0.0F;
    }
    if (☃ > 1.0F) {
      ☃ = 1.0F;
    }
    if (☃ > 1.0F) {
      ☃ = 1.0F;
    }
    bni.D();
    
    E.a(null, 0.0F, ☃, ☃, ☃, 0.0F, 0.0625F);
    
    bni.E();
    bcd.a();
    bni.n(5889);
    bni.b(0, 0, this.j.d, this.j.e);
    bni.H();
    bni.n(5888);
    bni.H();
    
    bcd.a();
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    
    bgf.a().a(this.H.f);
    
    int ☃ = this.H.e();
    for (int ☃ = 0; ☃ < 3; ☃++)
    {
      int ☃ = ☃ + 60;
      int ☃ = ☃ + 20;
      
      this.e = 0.0F;
      this.j.N().a(C);
      int ☃ = this.H.g[☃];
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      if (☃ == 0)
      {
        b(☃, ☃ + 14 + 19 * ☃, 0, 185, 108, 19);
      }
      else
      {
        String ☃ = "" + ☃;
        int ☃ = 86 - this.q.a(☃);
        String ☃ = bgf.a().a(this.q, ☃);
        
        bct ☃ = this.j.l;
        int ☃ = 6839882;
        if (((☃ < ☃ + 1) || (this.j.h.bK < ☃)) && (!this.j.h.bJ.d))
        {
          b(☃, ☃ + 14 + 19 * ☃, 0, 185, 108, 19);
          b(☃ + 1, ☃ + 15 + 19 * ☃, 16 * ☃, 239, 16, 16);
          ☃.a(☃, ☃, ☃ + 16 + 19 * ☃, ☃, (☃ & 0xFEFEFE) >> 1);
          ☃ = 4226832;
        }
        else
        {
          int ☃ = ☃ - (☃ + 60);
          int ☃ = ☃ - (☃ + 14 + 19 * ☃);
          if ((☃ >= 0) && (☃ >= 0) && (☃ < 108) && (☃ < 19))
          {
            b(☃, ☃ + 14 + 19 * ☃, 0, 204, 108, 19);
            ☃ = 16777088;
          }
          else
          {
            b(☃, ☃ + 14 + 19 * ☃, 0, 166, 108, 19);
          }
          b(☃ + 1, ☃ + 15 + 19 * ☃, 16 * ☃, 223, 16, 16);
          ☃.a(☃, ☃, ☃ + 16 + 19 * ☃, ☃, ☃);
          ☃ = 8453920;
        }
        ☃ = this.j.k;
        ☃.a(☃, ☃ + 86 - ☃.a(☃), ☃ + 16 + 19 * ☃ + 7, ☃);
      }
    }
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    super.a(☃, ☃, ☃);
    
    boolean ☃ = this.j.h.bJ.d;
    int ☃ = this.H.e();
    for (int ☃ = 0; ☃ < 3; ☃++)
    {
      int ☃ = this.H.g[☃];
      
      agm ☃ = agm.c(this.H.h[☃]);
      int ☃ = this.H.i[☃];
      
      int ☃ = ☃ + 1;
      if ((c(60, 14 + 19 * ☃, 108, 17, ☃, ☃)) && (☃ > 0) && (☃ >= 0) && (☃ != null))
      {
        List<String> ☃ = Lists.newArrayList();
        
        ☃.add(a.p.toString() + a.u.toString() + bwo.a("container.enchant.clue", new Object[] { ☃.d(☃) }));
        if (!☃)
        {
          ☃.add("");
          if (this.j.h.bK < ☃)
          {
            ☃.add(a.m.toString() + "Level Requirement: " + this.H.g[☃]);
          }
          else
          {
            String ☃;
            String ☃;
            if (☃ == 1) {
              ☃ = bwo.a("container.enchant.lapis.one", new Object[0]);
            } else {
              ☃ = bwo.a("container.enchant.lapis.many", new Object[] { Integer.valueOf(☃) });
            }
            a ☃ = ☃ >= ☃ ? a.h : a.m;
            ☃.add(☃.toString() + "" + ☃);
            if (☃ == 1) {
              ☃ = bwo.a("container.enchant.level.one", new Object[0]);
            } else {
              ☃ = bwo.a("container.enchant.level.many", new Object[] { Integer.valueOf(☃) });
            }
            ☃.add(a.h.toString() + "" + ☃);
          }
        }
        a(☃, ☃, ☃);
        break;
      }
    }
  }
  
  public void a()
  {
    adq ☃ = this.h.a(0).d();
    if (!adq.b(☃, this.B))
    {
      this.B = ☃;
      do
      {
        this.x += this.G.nextInt(4) - this.G.nextInt(4);
      } while ((this.v <= this.x + 1.0F) && (this.v >= this.x - 1.0F));
    }
    this.u += 1;
    this.w = this.v;
    this.A = this.z;
    
    boolean ☃ = false;
    for (int ☃ = 0; ☃ < 3; ☃++) {
      if (this.H.g[☃] != 0) {
        ☃ = true;
      }
    }
    if (☃) {
      this.z += 0.2F;
    } else {
      this.z -= 0.2F;
    }
    this.z = on.a(this.z, 0.0F, 1.0F);
    
    float ☃ = (this.x - this.v) * 0.4F;
    float ☃ = 0.2F;
    ☃ = on.a(☃, -☃, ☃);
    this.y += (☃ - this.y) * 0.9F;
    
    this.v += this.y;
  }
}
