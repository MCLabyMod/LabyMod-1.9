import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.ModSettings;
import org.lwjgl.input.Mouse;

public abstract class bdq
{
  protected final bcf a;
  protected int b;
  protected int c;
  protected int d;
  protected int e;
  protected int f;
  protected int g;
  protected final int h;
  private int u;
  private int v;
  protected int i;
  protected int j;
  protected boolean k = true;
  protected int l = -2;
  protected float m;
  protected float n;
  protected int o = -1;
  protected long p;
  protected boolean q = true;
  protected boolean r = true;
  protected boolean s;
  protected int t;
  private boolean w = true;
  
  public bdq(bcf mcIn, int width, int height, int topIn, int bottomIn, int slotHeightIn)
  {
    this.a = mcIn;
    this.b = width;
    this.c = height;
    this.d = topIn;
    this.e = bottomIn;
    this.h = slotHeightIn;
    this.g = 0;
    this.f = width;
  }
  
  public void a(int widthIn, int heightIn, int topIn, int bottomIn)
  {
    this.b = widthIn;
    this.c = heightIn;
    this.d = topIn;
    this.e = bottomIn;
    this.g = 0;
    this.f = widthIn;
  }
  
  public void b(boolean showSelectionBoxIn)
  {
    this.r = showSelectionBoxIn;
  }
  
  protected void a(boolean hasListHeaderIn, int headerPaddingIn)
  {
    this.s = hasListHeaderIn;
    this.t = headerPaddingIn;
    if (!hasListHeaderIn) {
      this.t = 0;
    }
  }
  
  protected abstract int b();
  
  protected abstract void a(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3);
  
  protected abstract boolean a(int paramInt);
  
  protected int k()
  {
    return b() * this.h + this.t;
  }
  
  protected abstract void a();
  
  protected void a(int p_178040_1_, int p_178040_2_, int p_178040_3_) {}
  
  protected abstract void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
  
  protected void a(int p_148129_1_, int p_148129_2_, bnu p_148129_3_) {}
  
  protected void a(int p_148132_1_, int p_148132_2_) {}
  
  protected void b(int p_148142_1_, int p_148142_2_) {}
  
  public int c(int p_148124_1_, int p_148124_2_)
  {
    int i = this.g + this.b / 2 - c() / 2;
    int j = this.g + this.b / 2 + c() / 2;
    int k = p_148124_2_ - this.d - this.t + (int)this.n - 4;
    int l = k / this.h;
    return (p_148124_1_ < d()) && (p_148124_1_ >= i) && (p_148124_1_ <= j) && (l >= 0) && (k >= 0) && (l < b()) ? l : -1;
  }
  
  public void d(int scrollUpButtonIDIn, int scrollDownButtonIDIn)
  {
    this.u = scrollUpButtonIDIn;
    this.v = scrollDownButtonIDIn;
  }
  
  protected void l()
  {
    this.n = on.a(this.n, 0.0F, m());
  }
  
  public int m()
  {
    return Math.max(0, k() - (this.e - this.d - 4));
  }
  
  public int n()
  {
    return (int)this.n;
  }
  
  public boolean g(int p_148141_1_)
  {
    return (p_148141_1_ >= this.d) && (p_148141_1_ <= this.e) && (this.i >= this.g) && (this.i <= this.f);
  }
  
  public void h(int amount)
  {
    this.n += amount;
    l();
    this.l = -2;
  }
  
  public void a(bcz button)
  {
    if (button.l) {
      if (button.k == this.u)
      {
        this.n -= this.h * 2 / 3;
        this.l = -2;
        l();
      }
      else if (button.k == this.v)
      {
        this.n += this.h * 2 / 3;
        this.l = -2;
        l();
      }
    }
  }
  
  public void a(int mouseXIn, int mouseYIn, float p_148128_3_)
  {
    if (this.q)
    {
      this.i = mouseXIn;
      this.j = mouseYIn;
      a();
      int i = d();
      int j = i + 6;
      l();
      bni.g();
      bni.p();
      bnu tessellator = bnu.a();
      bmz vertexbuffer = tessellator.c();
      this.a.N().a(bcv.b);
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      float f = 32.0F;
      vertexbuffer.a(7, bvp.i);
      vertexbuffer.b(this.g, this.e, 0.0D).a(this.g / f, (this.e + (int)this.n) / f).b(32, 32, 32, 255).d();
      vertexbuffer.b(this.f, this.e, 0.0D).a(this.f / f, (this.e + (int)this.n) / f).b(32, 32, 32, 255).d();
      vertexbuffer.b(this.f, this.d, 0.0D).a(this.f / f, (this.d + (int)this.n) / f).b(32, 32, 32, 255).d();
      vertexbuffer.b(this.g, this.d, 0.0D).a(this.g / f, (this.d + (int)this.n) / f).b(32, 32, 32, 255).d();
      tessellator.b();
      int k = this.g + this.b / 2 - c() / 2 + 2;
      int l = this.d + 4 - (int)this.n;
      if (this.s) {
        a(k, l, tessellator);
      }
      b(k, l, mouseXIn, mouseYIn);
      bni.j();
      int i1 = 4;
      c(0, this.d, 255, 255);
      c(this.e, this.c, 255, 255);
      bni.m();
      bni.a(bni.r.l, bni.l.j, bni.r.o, bni.l.e);
      bni.d();
      bni.j(7425);
      bni.z();
      vertexbuffer.a(7, bvp.i);
      vertexbuffer.b(this.g, this.d + i1, 0.0D).a(0.0D, 1.0D).b(0, 0, 0, 0).d();
      vertexbuffer.b(this.f, this.d + i1, 0.0D).a(1.0D, 1.0D).b(0, 0, 0, 0).d();
      vertexbuffer.b(this.f, this.d, 0.0D).a(1.0D, 0.0D).b(0, 0, 0, 255).d();
      vertexbuffer.b(this.g, this.d, 0.0D).a(0.0D, 0.0D).b(0, 0, 0, 255).d();
      tessellator.b();
      vertexbuffer.a(7, bvp.i);
      vertexbuffer.b(this.g, this.e, 0.0D).a(0.0D, 1.0D).b(0, 0, 0, 255).d();
      vertexbuffer.b(this.f, this.e, 0.0D).a(1.0D, 1.0D).b(0, 0, 0, 255).d();
      vertexbuffer.b(this.f, this.e - i1, 0.0D).a(1.0D, 0.0D).b(0, 0, 0, 0).d();
      vertexbuffer.b(this.g, this.e - i1, 0.0D).a(0.0D, 0.0D).b(0, 0, 0, 0).d();
      tessellator.b();
      int j1 = m();
      if (j1 > 0)
      {
        int k1 = (this.e - this.d) * (this.e - this.d) / k();
        k1 = on.a(k1, 32, this.e - this.d - 8);
        int l1 = (int)this.n * (this.e - this.d - k1) / j1 + this.d;
        if (l1 < this.d) {
          l1 = this.d;
        }
        vertexbuffer.a(7, bvp.i);
        vertexbuffer.b(i, this.e, 0.0D).a(0.0D, 1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(j, this.e, 0.0D).a(1.0D, 1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(j, this.d, 0.0D).a(1.0D, 0.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(i, this.d, 0.0D).a(0.0D, 0.0D).b(0, 0, 0, 255).d();
        tessellator.b();
        vertexbuffer.a(7, bvp.i);
        vertexbuffer.b(i, l1 + k1, 0.0D).a(0.0D, 1.0D).b(128, 128, 128, 255).d();
        vertexbuffer.b(j, l1 + k1, 0.0D).a(1.0D, 1.0D).b(128, 128, 128, 255).d();
        vertexbuffer.b(j, l1, 0.0D).a(1.0D, 0.0D).b(128, 128, 128, 255).d();
        vertexbuffer.b(i, l1, 0.0D).a(0.0D, 0.0D).b(128, 128, 128, 255).d();
        tessellator.b();
        vertexbuffer.a(7, bvp.i);
        vertexbuffer.b(i, l1 + k1 - 1, 0.0D).a(0.0D, 1.0D).b(192, 192, 192, 255).d();
        vertexbuffer.b(j - 1, l1 + k1 - 1, 0.0D).a(1.0D, 1.0D).b(192, 192, 192, 255).d();
        vertexbuffer.b(j - 1, l1, 0.0D).a(1.0D, 0.0D).b(192, 192, 192, 255).d();
        vertexbuffer.b(i, l1, 0.0D).a(0.0D, 0.0D).b(192, 192, 192, 255).d();
        tessellator.b();
      }
      b(mouseXIn, mouseYIn);
      bni.y();
      bni.j(7424);
      bni.e();
      bni.l();
    }
  }
  
  public void p()
  {
    if (g(this.j))
    {
      if ((Mouse.getEventButton() == 0) && (Mouse.getEventButtonState()) && (this.j >= this.d) && (this.j <= this.e))
      {
        int i = (this.b - c()) / 2;
        int j = (this.b + c()) / 2;
        int k = this.j - this.d - this.t + (int)this.n - 4;
        int l = k / this.h;
        if ((l < b()) && (this.i >= i) && (this.i <= j) && (l >= 0) && (k >= 0))
        {
          a(l, false, this.i, this.j);
          this.o = l;
        }
        else if ((this.i >= i) && (this.i <= j) && (k < 0))
        {
          a(this.i - i, this.j - this.d + (int)this.n - 4);
        }
      }
      if ((Mouse.isButtonDown(0)) && (q()))
      {
        if (this.l == -1)
        {
          boolean flag1 = true;
          if ((this.j >= this.d) && (this.j <= this.e))
          {
            int j2 = (this.b - c()) / 2;
            int k2 = (this.b + c()) / 2;
            int l2 = this.j - this.d - this.t + (int)this.n - 4;
            int i1 = l2 / this.h;
            if ((i1 < b()) && (this.i >= j2) && (this.i <= k2) && (i1 >= 0) && (l2 >= 0))
            {
              boolean flag = (i1 == this.o) && (bcf.I() - this.p < 250L);
              a(i1, flag, this.i, this.j);
              this.o = i1;
              this.p = bcf.I();
            }
            else if ((this.i >= j2) && (this.i <= k2) && (l2 < 0))
            {
              a(this.i - j2, this.j - this.d + (int)this.n - 4);
              flag1 = false;
            }
            int i3 = d();
            int j1 = i3 + 6;
            if ((this.i >= i3) && (this.i <= j1))
            {
              this.m = -1.0F;
              int k1 = m();
              if (k1 < 1) {
                k1 = 1;
              }
              int l1 = (int)((this.e - this.d) * (this.e - this.d) / k());
              l1 = on.a(l1, 32, this.e - this.d - 8);
              this.m /= (this.e - this.d - l1) / k1;
            }
            else
            {
              this.m = 1.0F;
            }
            if (flag1) {
              this.l = this.j;
            } else {
              this.l = -2;
            }
          }
          else
          {
            this.l = -2;
          }
        }
        else if (this.l >= 0)
        {
          this.n -= (this.j - this.l) * this.m;
          this.l = this.j;
        }
      }
      else {
        this.l = -1;
      }
      int i2 = Mouse.getEventDWheel();
      double scroll = 0.0D;
      if (i2 != 0)
      {
        if (i2 > 0)
        {
          i2 = -1;
          scroll = -0.5D;
        }
        else if (i2 < 0)
        {
          i2 = 1;
          scroll = 0.5D;
        }
        double use = i2;
        if (ConfigManager.settings.smoothScroll.booleanValue()) {
          use = scroll;
        }
        this.n += (float)(use * this.h / 2.0D);
      }
    }
  }
  
  public void d(boolean enabledIn)
  {
    this.w = enabledIn;
  }
  
  public boolean q()
  {
    return this.w;
  }
  
  public int c()
  {
    return 220;
  }
  
  protected void b(int p_148120_1_, int p_148120_2_, int mouseXIn, int mouseYIn)
  {
    int i = b();
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    for (int j = 0; j < i; j++)
    {
      int k = p_148120_2_ + j * this.h + this.t;
      int l = this.h - 4;
      if ((k > this.e) || (k + l < this.d)) {
        a(j, p_148120_1_, k);
      }
      if ((this.r) && (a(j)))
      {
        int i1 = this.g + (this.b / 2 - c() / 2);
        int j1 = this.g + this.b / 2 + c() / 2;
        bni.c(1.0F, 1.0F, 1.0F, 1.0F);
        bni.z();
        vertexbuffer.a(7, bvp.i);
        vertexbuffer.b(i1, k + l + 2, 0.0D).a(0.0D, 1.0D).b(128, 128, 128, 255).d();
        vertexbuffer.b(j1, k + l + 2, 0.0D).a(1.0D, 1.0D).b(128, 128, 128, 255).d();
        vertexbuffer.b(j1, k - 2, 0.0D).a(1.0D, 0.0D).b(128, 128, 128, 255).d();
        vertexbuffer.b(i1, k - 2, 0.0D).a(0.0D, 0.0D).b(128, 128, 128, 255).d();
        vertexbuffer.b(i1 + 1, k + l + 1, 0.0D).a(0.0D, 1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(j1 - 1, k + l + 1, 0.0D).a(1.0D, 1.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(j1 - 1, k - 1, 0.0D).a(1.0D, 0.0D).b(0, 0, 0, 255).d();
        vertexbuffer.b(i1 + 1, k - 1, 0.0D).a(0.0D, 0.0D).b(0, 0, 0, 255).d();
        tessellator.b();
        bni.y();
      }
      a(j, p_148120_1_, k, l, mouseXIn, mouseYIn);
    }
  }
  
  protected int d()
  {
    return this.b / 2 + 124;
  }
  
  protected void c(int startY, int endY, int startAlpha, int endAlpha)
  {
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    this.a.N().a(bcv.b);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    float f = 32.0F;
    vertexbuffer.a(7, bvp.i);
    vertexbuffer.b(this.g, endY, 0.0D).a(0.0D, endY / 32.0F).b(64, 64, 64, endAlpha).d();
    vertexbuffer.b(this.g + this.b, endY, 0.0D).a(this.b / 32.0F, endY / 32.0F).b(64, 64, 64, endAlpha).d();
    vertexbuffer.b(this.g + this.b, startY, 0.0D).a(this.b / 32.0F, startY / 32.0F).b(64, 64, 64, startAlpha).d();
    vertexbuffer.b(this.g, startY, 0.0D).a(0.0D, startY / 32.0F).b(64, 64, 64, startAlpha).d();
    tessellator.b();
  }
  
  public void i(int leftIn)
  {
    this.g = leftIn;
    this.f = (leftIn + this.b);
  }
  
  public int r()
  {
    return this.h;
  }
}
