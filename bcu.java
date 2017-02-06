import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.utils.Allowed;
import de.labystudio.utils.ModGui;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class bcu
  extends bcv
{
  private static final kk f = new kk("textures/misc/vignette.png");
  private static final kk g = new kk("textures/gui/widgets.png");
  private static final kk h = new kk("textures/misc/pumpkinblur.png");
  private final Random i = new Random();
  private final bcf j;
  private final brz k;
  private final bda l;
  private int m;
  private String n = "";
  private int o;
  private boolean p;
  public float a = 1.0F;
  private int q;
  private adq r;
  private final bdc s;
  private final bds t;
  private final bdu u;
  private final bdp v;
  private final bcy w;
  private int x;
  private String y = "";
  private String z = "";
  private int A;
  private int B;
  private int C;
  private int D = 0;
  private int E = 0;
  private long F = 0L;
  private long G = 0L;
  
  public bcu(bcf mcIn)
  {
    this.j = mcIn;
    this.k = mcIn.ad();
    this.s = new bdc(mcIn);
    this.u = new bdu(mcIn);
    this.l = new bda(mcIn);
    this.v = new bdp(mcIn, this);
    this.w = new bcy(mcIn);
    this.t = new bds(mcIn);
    a();
  }
  
  public void a()
  {
    this.A = 10;
    this.B = 70;
    this.C = 20;
  }
  
  public void a(float partialTicks)
  {
    bcx scaledresolution = new bcx(this.j);
    int i = scaledresolution.a();
    int j = scaledresolution.b();
    bct fontrenderer = f();
    this.j.o.j();
    bni.m();
    if (bcf.x())
    {
      b(this.j.h.e(partialTicks), scaledresolution);
    }
    else
    {
      bni.k();
      bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
    }
    adq itemstack = this.j.h.br.g(3);
    if ((this.j.u.ap == 0) && (itemstack != null) && (itemstack.b() == ado.a(aju.aU))) {
      f(scaledresolution);
    }
    if (!this.j.h.a(rm.i))
    {
      float f = this.j.h.bV + (this.j.h.bU - this.j.h.bV) * partialTicks;
      if (f > 0.0F) {
        c(f, scaledresolution);
      }
    }
    if (this.j.c.a()) {
      this.u.a(scaledresolution, partialTicks);
    } else {
      a(scaledresolution, partialTicks);
    }
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    this.j.N().a(d);
    bni.m();
    a(partialTicks, scaledresolution);
    bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
    this.j.B.a("bossHealth");
    this.w.a();
    this.j.B.b();
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    this.j.N().a(d);
    if (this.j.c.b()) {
      d(scaledresolution);
    }
    e(scaledresolution);
    bni.l();
    if (this.j.h.cN() > 0)
    {
      this.j.B.a("sleep");
      bni.j();
      bni.d();
      int j1 = this.j.h.cN();
      float f1 = j1 / 100.0F;
      if (f1 > 1.0F) {
        f1 = 1.0F - (j1 - 100) / 10.0F;
      }
      int k = (int)(220.0F * f1) << 24 | 0x101020;
      a(0, 0, i, j, k);
      bni.e();
      bni.k();
      this.j.B.b();
    }
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    int k1 = i / 2 - 91;
    if (this.j.h.J()) {
      a(scaledresolution, k1);
    } else if (this.j.c.f()) {
      b(scaledresolution, k1);
    }
    if ((this.j.u.D) && (!this.j.c.a())) {
      b(scaledresolution);
    } else if (this.j.h.y()) {
      this.u.a(scaledresolution);
    }
    if (this.j.u()) {
      c(scaledresolution);
    }
    a(scaledresolution);
    if (this.j.u.aq) {
      this.s.a(scaledresolution);
    }
    if (this.o > 0)
    {
      this.j.B.a("overlayMessage");
      float f2 = this.o - partialTicks;
      int l1 = (int)(f2 * 255.0F / 20.0F);
      if (l1 > 255) {
        l1 = 255;
      }
      if (l1 > 8)
      {
        bni.G();
        bni.c(i / 2, j - 68, 0.0F);
        bni.m();
        bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
        int l = 16777215;
        if (this.p) {
          l = on.c(f2 / 50.0F, 0.7F, 0.6F) & 0xFFFFFF;
        }
        fontrenderer.a(this.n, -fontrenderer.a(this.n) / 2, -4, l + (l1 << 24 & 0xFF000000));
        bni.l();
        bni.H();
      }
      this.j.B.b();
    }
    this.t.a(scaledresolution);
    if (this.x > 0)
    {
      this.j.B.a("titleAndSubtitle");
      float f3 = this.x - partialTicks;
      int i2 = 255;
      if (this.x > this.C + this.B)
      {
        float f4 = this.A + this.B + this.C - f3;
        i2 = (int)(f4 * 255.0F / this.A);
      }
      if (this.x <= this.C) {
        i2 = (int)(f3 * 255.0F / this.C);
      }
      i2 = on.a(i2, 0, 255);
      if (i2 > 8)
      {
        bni.G();
        bni.c(i / 2, j / 2, 0.0F);
        bni.m();
        bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
        bni.G();
        bni.b(4.0F, 4.0F, 4.0F);
        int j2 = i2 << 24 & 0xFF000000;
        fontrenderer.a(this.y, -fontrenderer.a(this.y) / 2, -10.0F, 0xFFFFFF | j2, true);
        bni.H();
        bni.G();
        bni.b(2.0F, 2.0F, 2.0F);
        fontrenderer.a(this.z, -fontrenderer.a(this.z) / 2, 5.0F, 0xFFFFFF | j2, true);
        bni.H();
        bni.l();
        bni.H();
      }
      this.j.B.b();
    }
    bbp scoreboard = this.j.f.ad();
    bbl scoreobjective = null;
    bbm scoreplayerteam = scoreboard.g(this.j.h.h_());
    if (scoreplayerteam != null)
    {
      int i1 = scoreplayerteam.m().b();
      if (i1 >= 0) {
        scoreobjective = scoreboard.a(3 + i1);
      }
    }
    bbl scoreobjective1 = scoreobjective != null ? scoreobjective : scoreboard.a(1);
    if (scoreobjective1 != null) {
      a(scoreobjective1, scaledresolution);
    }
    bni.m();
    bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
    bni.d();
    bni.G();
    bni.c(0.0F, j - 48, 0.0F);
    this.j.B.a("chat");
    this.l.a(this.m);
    this.j.B.b();
    bni.H();
    scoreobjective1 = scoreboard.a(0);
    if ((!this.j.u.ad.e()) || ((this.j.D()) && (this.j.h.d.d().size() <= 1) && (scoreobjective1 == null)))
    {
      this.v.a(false);
    }
    else
    {
      this.v.a(true);
      this.v.a(i, scoreboard, scoreobjective1);
    }
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.g();
    bni.e();
    String s = this.j.d();
  }
  
  private void a(float p_184045_1_, bcx p_184045_2_)
  {
    bch gamesettings = this.j.u;
    if (gamesettings.ap == 0)
    {
      if ((this.j.c.a()) && (this.j.i == null))
      {
        bbi raytraceresult = this.j.t;
        if ((raytraceresult == null) || (raytraceresult.a != bbi.a.b)) {
          return;
        }
        cj blockpos = raytraceresult.a();
        if ((!this.j.f.o(blockpos).t().m()) || (!(this.j.f.r(blockpos) instanceof qg))) {
          return;
        }
      }
      int l = p_184045_2_.a();
      int i1 = p_184045_2_.b();
      if ((gamesettings.aq) && (!gamesettings.ao) && (!this.j.h.cX()) && (!gamesettings.v))
      {
        bni.G();
        bni.c(l / 2, i1 / 2, this.e);
        rr entity = this.j.aa();
        bni.b(entity.y + (entity.w - entity.y) * p_184045_1_, -1.0F, 0.0F, 0.0F);
        bni.b(entity.x + (entity.v - entity.x) * p_184045_1_, 0.0F, 1.0F, 0.0F);
        bni.b(-1.0F, -1.0F, -1.0F);
        bzg.m(10);
        bni.H();
      }
      else
      {
        bni.a(bni.r.i, bni.l.k, bni.r.e, bni.l.n);
        bni.e();
        b(l / 2 - 7, i1 / 2 - 7, 0, 0, 16, 16);
        if (this.j.u.M == 1)
        {
          float f = this.j.h.o(0.0F);
          if (f < 1.0F)
          {
            int i = i1 / 2 - 7 + 16;
            int j = l / 2 - 7;
            int k = (int)(f * 17.0F);
            b(j, i, 36, 94, 16, 4);
            b(j, i, 52, 94, k, 4);
          }
        }
      }
    }
  }
  
  protected void a(bcx p_184048_1_)
  {
    Collection<rl> collection = this.j.h.bO();
    int i;
    int j;
    if (!collection.isEmpty())
    {
      this.j.N().a(bft.a);
      bni.m();
      i = 0;
      j = 0;
      for (rl potioneffect : Ordering.natural().reverse().sortedCopy(collection))
      {
        rk potion = potioneffect.a();
        if ((potion.c()) && (potioneffect.e()))
        {
          int k = p_184048_1_.a();
          int l = 1;
          int i1 = potion.d();
          float f = 1.0F;
          if (potion.i())
          {
            i++;
            k -= 25 * i;
          }
          else
          {
            j++;
            k -= 25 * j;
            l += 26;
          }
          bni.c(1.0F, 1.0F, 1.0F, 1.0F);
          if (potioneffect.d())
          {
            b(k, l, 165, 166, 24, 24);
          }
          else
          {
            b(k, l, 141, 166, 24, 24);
            if (potioneffect.b() <= 200)
            {
              int j1 = 10 - potioneffect.b() / 20;
              f = on.a(potioneffect.b() / 10.0F / 5.0F * 0.5F, 0.0F, 0.5F) + on.b(potioneffect.b() * 3.1415927F / 5.0F) * on.a(j1 / 10.0F * 0.25F, 0.0F, 0.25F);
            }
          }
          bni.c(1.0F, 1.0F, 1.0F, f);
          b(k + 3, l + 3, i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18);
        }
      }
    }
  }
  
  protected void a(bcx sr, float partialTicks)
  {
    if ((this.j.aa() instanceof zj))
    {
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      this.j.N().a(g);
      zj entityplayer = (zj)this.j.aa();
      adq itemstack = entityplayer.cc();
      rz enumhandside = entityplayer.cr().a();
      int i = sr.a() / 2;
      float f = this.e;
      int j = 182;
      int k = 91;
      this.e = -90.0F;
      b(i - 91, sr.b() - 22, 0, 0, 182, 22);
      b(i - 91 - 1 + entityplayer.br.d * 20, sr.b() - 22 - 1, 0, 22, 24, 22);
      
      int move = 0;
      if ((!ConfigManager.settings.armorHudPositionOnTop) && (
        ((enumhandside == rz.a) && ((ModGui.isNotEmpty(2)) || (ModGui.isNotEmpty(3)))) || ((enumhandside == rz.b) && (
        (ModGui.isNotEmpty(0)) || (ModGui.isNotEmpty(1))))))
      {
        move -= 50;
        switch (ConfigManager.settings.hud)
        {
        case 2: 
          move -= 15;
          break;
        case 3: 
          move += 25;
        }
      }
      if (itemstack != null) {
        if (enumhandside == rz.a) {
          b(i - 91 - 29 + move, sr.b() - 23, 24, 22, 29, 24);
        } else {
          b(i + 91 - move, sr.b() - 23, 53, 22, 29, 24);
        }
      }
      this.e = f;
      bni.D();
      bni.m();
      bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
      bcd.c();
      for (int l = 0; l < 9; l++)
      {
        int i1 = i - 90 + l * 20 + 2;
        int j1 = sr.b() - 16 - 3;
        a(i1, j1, partialTicks, entityplayer, entityplayer.br.a[l]);
      }
      if (itemstack != null)
      {
        int l1 = sr.b() - 16 - 3;
        if (enumhandside == rz.a) {
          a(i - 91 - 26 + move, l1, partialTicks, entityplayer, itemstack);
        } else {
          a(i + 91 + 10 - move, l1, partialTicks, entityplayer, itemstack);
        }
      }
      if (this.j.u.M == 2)
      {
        float f1 = this.j.h.o(0.0F);
        if (f1 < 1.0F)
        {
          int i2 = sr.b() - 20;
          int j2 = i + 91 + 6;
          if (enumhandside == rz.b)
          {
            j2 = i - 91 - 22;
            if ((!ConfigManager.settings.armorHudPositionOnTop) && (
              (ModGui.isNotEmpty(2)) || (ModGui.isNotEmpty(3))))
            {
              j2 -= 50;
              switch (ConfigManager.settings.hud)
              {
              case 2: 
                j2 -= 15;
                break;
              case 3: 
                j2 += 25;
              }
            }
          }
          else if ((!ConfigManager.settings.armorHudPositionOnTop) && (
            (ModGui.isNotEmpty(0)) || (ModGui.isNotEmpty(1))))
          {
            j2 += 50;
            switch (ConfigManager.settings.hud)
            {
            case 2: 
              j2 += 15;
              break;
            case 3: 
              j2 -= 25;
            }
          }
          this.j.N().a(bcv.d);
          int k1 = (int)(f1 * 19.0F);
          bni.c(1.0F, 1.0F, 1.0F, 1.0F);
          b(j2, i2, 0, 94, 18, 18);
          b(j2, i2 + 18 - k1, 18, 112 - k1, 18, k1);
        }
      }
      bcd.a();
      bni.E();
      bni.l();
    }
  }
  
  public void a(bcx scaledRes, int x)
  {
    this.j.B.a("jumpBar");
    this.j.N().a(bcv.d);
    float f = this.j.h.K();
    int i = 182;
    int j = (int)(f * (i + 1));
    int k = scaledRes.b() - 32 + 3;
    b(x, k, 0, 84, i, 5);
    if (j > 0) {
      b(x, k, 0, 89, j, 5);
    }
    this.j.B.b();
  }
  
  public void b(bcx scaledRes, int x)
  {
    this.j.B.a("expBar");
    this.j.N().a(bcv.d);
    int i = this.j.h.cR();
    if (i > 0)
    {
      int j = 182;
      int k = (int)(this.j.h.bM * (j + 1));
      int l = scaledRes.b() - 32 + 3;
      b(x, l, 0, 64, j, 5);
      if (k > 0) {
        b(x, l, 0, 69, k, 5);
      }
    }
    this.j.B.b();
    if (this.j.h.bK > 0)
    {
      this.j.B.a("expLevel");
      int k1 = 8453920;
      String s = "" + this.j.h.bK;
      int l1 = (scaledRes.a() - f().a(s)) / 2;
      int i1 = scaledRes.b() - 31 - 4;
      int j1 = 0;
      f().a(s, l1 + 1, i1, 0);
      f().a(s, l1 - 1, i1, 0);
      f().a(s, l1, i1 + 1, 0);
      f().a(s, l1, i1 - 1, 0);
      f().a(s, l1, i1, k1);
      this.j.B.b();
    }
  }
  
  public void b(bcx scaledRes)
  {
    this.j.B.a("selectedItemName");
    if ((this.q > 0) && (this.r != null))
    {
      String s = this.r.q();
      if (this.r.s()) {
        s = a.u + s;
      }
      int i = (scaledRes.a() - f().a(s)) / 2;
      int j = scaledRes.b() - 59;
      if (!this.j.c.b()) {
        j += 14;
      }
      int k = (int)(this.q * 256.0F / 10.0F);
      if (k > 255) {
        k = 255;
      }
      if (k > 0)
      {
        bni.G();
        bni.m();
        bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
        f().a(s, i, j, 16777215 + (k << 24));
        bni.l();
        bni.H();
      }
    }
    this.j.B.b();
  }
  
  public void c(bcx scaledRes)
  {
    this.j.B.a("demo");
    String s = "";
    if (this.j.f.P() >= 120500L) {
      s = bwo.a("demo.demoExpired", new Object[0]);
    } else {
      s = bwo.a("demo.remainingTime", new Object[] { os.a((int)(120500L - this.j.f.P())) });
    }
    int i = f().a(s);
    f().a(s, scaledRes.a() - i - 10, 5.0F, 16777215);
    this.j.B.b();
  }
  
  private void a(bbl objective, bcx scaledRes)
  {
    bbp scoreboard = objective.a();
    Collection<bbn> collection = scoreboard.i(objective);
    List<bbn> list = Lists.newArrayList(Iterables.filter(collection, new Predicate()
    {
      public boolean a(bbn p_apply_1_)
      {
        return (p_apply_1_.e() != null) && (!p_apply_1_.e().startsWith("#"));
      }
    }));
    if (list.size() > 15) {
      collection = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
    } else {
      collection = list;
    }
    int i = f().a(objective.d());
    for (bbn score : collection)
    {
      bbm scoreplayerteam = scoreboard.g(score.e());
      String s = bbm.a(scoreplayerteam, score.e()) + ": " + a.m + score.c();
      i = Math.max(i, f().a(s));
    }
    int i1 = collection.size() * f().a;
    int j1 = scaledRes.b() / 2 + i1 / 3;
    int k1 = 3;
    int l1 = scaledRes.a() - i - k1;
    int j = 0;
    for (bbn score1 : collection)
    {
      j++;
      bbm scoreplayerteam1 = scoreboard.g(score1.e());
      String s1 = bbm.a(scoreplayerteam1, score1.e());
      String s2 = a.m + "" + score1.c();
      int k = j1 - j * f().a;
      int l = scaledRes.a() - k1 + 2;
      a(l1 - 2, k, l, k + f().a, 1342177280);
      f().a(s1, l1, k, 553648127);
      f().a(s2, l - f().a(s2), k, 553648127);
      if (j == collection.size())
      {
        String s3 = objective.d();
        a(l1 - 2, k - f().a - 1, l, k - 1, 1610612736);
        a(l1 - 2, k - 1, l, k, 1342177280);
        f().a(s3, l1 + i / 2 - f().a(s3) / 2, k - f().a, 553648127);
      }
    }
  }
  
  private void d(bcx scaledRes)
  {
    if ((this.j.aa() instanceof zj))
    {
      zj entityplayer = (zj)this.j.aa();
      int i = on.f(entityplayer.bQ());
      boolean flag = (this.G > this.m) && ((this.G - this.m) / 3L % 2L == 1L);
      if ((i < this.D) && (entityplayer.W > 0))
      {
        this.F = bcf.I();
        this.G = (this.m + 20);
      }
      else if ((i > this.D) && (entityplayer.W > 0))
      {
        this.F = bcf.I();
        this.G = (this.m + 10);
      }
      if (bcf.I() - this.F > 1000L)
      {
        this.D = i;
        this.E = i;
        this.F = bcf.I();
      }
      this.D = i;
      int j = this.E;
      this.i.setSeed(this.m * 312871);
      boolean flag1 = false;
      aas foodstats = entityplayer.cS();
      int k = foodstats.a();
      int l = foodstats.b();
      float saturation = foodstats.e();
      sm iattributeinstance = entityplayer.a(yt.a);
      int i1 = scaledRes.a() / 2 - 91;
      int j1 = scaledRes.a() / 2 + 91;
      int k1 = scaledRes.b() - 39;
      float f = (float)iattributeinstance.e();
      int l1 = on.f(entityplayer.cp());
      int i2 = on.f((f + l1) / 2.0F / 10.0F);
      int j2 = Math.max(10 - (i2 - 2), 3);
      int k2 = k1 - (i2 - 1) * j2 - 10;
      int l2 = k1 - 10;
      int i3 = l1;
      int j3 = entityplayer.bT();
      int k3 = -1;
      if (entityplayer.a(rm.j)) {
        k3 = this.m % on.f(f + 5.0F);
      }
      this.j.B.a("armor");
      for (int l3 = 0; l3 < 10; l3++) {
        if (j3 > 0)
        {
          int i4 = i1 + l3 * 8;
          if (l3 * 2 + 1 < j3) {
            b(i4, k2, 34, 9, 9, 9);
          }
          if (l3 * 2 + 1 == j3) {
            b(i4, k2, 25, 9, 9, 9);
          }
          if (l3 * 2 + 1 > j3) {
            b(i4, k2, 16, 9, 9, 9);
          }
        }
      }
      this.j.B.c("health");
      for (int k5 = on.f((f + l1) / 2.0F) - 1; k5 >= 0; k5--)
      {
        int l5 = 16;
        if (entityplayer.a(rm.s)) {
          l5 += 36;
        } else if (entityplayer.a(rm.t)) {
          l5 += 72;
        }
        int j4 = 0;
        if (flag) {
          j4 = 1;
        }
        int k4 = on.f((k5 + 1) / 10.0F) - 1;
        int l4 = i1 + k5 % 10 * 8;
        int i5 = k1 - k4 * j2;
        if (i <= 4) {
          i5 += this.i.nextInt(2);
        }
        if ((i3 <= 0) && (k5 == k3)) {
          i5 -= 2;
        }
        int j5 = 0;
        if (entityplayer.l.T().s()) {
          j5 = 5;
        }
        b(l4, i5, 16 + j4 * 9, 9 * j5, 9, 9);
        if (((!ConfigManager.settings.oldHearts.booleanValue()) || (!Allowed.animations())) && 
          (flag))
        {
          if (k5 * 2 + 1 < j) {
            b(l4, i5, l5 + 54, 9 * j5, 9, 9);
          }
          if (k5 * 2 + 1 == j) {
            b(l4, i5, l5 + 63, 9 * j5, 9, 9);
          }
        }
        if (i3 > 0)
        {
          if ((i3 == l1) && (l1 % 2 == 1))
          {
            b(l4, i5, l5 + 153, 9 * j5, 9, 9);
            i3--;
          }
          else
          {
            b(l4, i5, l5 + 144, 9 * j5, 9, 9);
            i3 -= 2;
          }
        }
        else
        {
          if (k5 * 2 + 1 < i) {
            b(l4, i5, l5 + 36, 9 * j5, 9, 9);
          }
          if (k5 * 2 + 1 == i) {
            b(l4, i5, l5 + 45, 9 * j5, 9, 9);
          }
        }
      }
      rr entity = entityplayer.by();
      if (entity == null)
      {
        this.j.B.c("food");
        for (int i6 = 0; i6 < 10; i6++)
        {
          int k6 = k1;
          int i7 = 16;
          int k7 = 0;
          if (entityplayer.a(rm.q))
          {
            i7 += 36;
            k7 = 13;
          }
          if ((entityplayer.cS().e() <= 0.0F) && (this.m % (k * 3 + 1) == 0)) {
            k6 = k1 + (this.i.nextInt(3) - 1);
          }
          if (flag1) {
            k7 = 1;
          }
          int i8 = j1 - i6 * 8 - 9;
          b(i8, k6, 16 + k7 * 9, 27, 9, 9);
          if (flag1)
          {
            if (i6 * 2 + 1 < l) {
              b(i8, k6, i7 + 54, 27, 9, 9);
            }
            if (i6 * 2 + 1 == l) {
              b(i8, k6, i7 + 63, 27, 9, 9);
            }
          }
          if (i6 * 2 + 1 < k) {
            b(i8, k6, i7 + 36, 27, 9, 9);
          }
          if (i6 * 2 + 1 == k) {
            b(i8, k6, i7 + 45, 27, 9, 9);
          }
        }
        if ((ConfigManager.settings.foodSaturation) && (Allowed.foodSaturation()) && (saturation != 0.0F)) {
          for (int k6 = 0; k6 < saturation / 2.0F; k6++)
          {
            int i7 = k1 - 10;
            int l7 = 16;
            int j8 = 0;
            if (entityplayer.a(rm.q))
            {
              l7 += 36;
              j8 = 13;
            }
            if ((saturation <= 0.0F) && (this.m % (saturation * 3.0F + 1.0F) == 0.0F)) {
              i7 = k1 + (this.i.nextInt(3) - 1);
            }
            if (flag1) {
              j8 = 1;
            }
            int i9 = j1 - k6 * 8 - 9;
            b(i9, i7, 16 + j8 * 9, 27, 9, 9);
            if (flag1)
            {
              if (k6 * 2 + 1 < l) {
                b(i9, i7, l7 + 54, 27, 9, 9);
              }
              if (k6 * 2 + 1 == l) {
                b(i9, i7, l7 + 63, 27, 9, 9);
              }
            }
            if (k6 * 2 + 1 < saturation) {
              b(i9, i7, l7 + 36, 27, 9, 9);
            }
            if (k6 * 2 + 1 == saturation) {
              b(i9, i7, l7 + 45, 27, 9, 9);
            }
          }
        }
      }
      this.j.B.c("air");
      if (entityplayer.a(axe.h))
      {
        boolean up = (ConfigManager.settings.foodSaturation) && (Allowed.foodSaturation()) && (saturation != 0.0F);
        int j6 = this.j.h.aP();
        int l6 = on.f((j6 - 2) * 10.0D / 300.0D);
        int j7 = on.f(j6 * 10.0D / 300.0D) - l6;
        if (up) {
          j2 -= 10;
        }
        for (int l7 = 0; l7 < l6 + j7; l7++) {
          if (l7 < l6) {
            b(j1 - l7 * 8 - 9, l2, 16, 18, 9, 9);
          } else {
            b(j1 - l7 * 8 - 9, l2, 25, 18, 9, 9);
          }
        }
      }
      this.j.B.b();
    }
  }
  
  private void e(bcx p_184047_1_)
  {
    if ((this.j.aa() instanceof zj))
    {
      zj entityplayer = (zj)this.j.aa();
      rr entity = entityplayer.by();
      if ((entity instanceof sa))
      {
        this.j.B.c("mountHealth");
        sa entitylivingbase = (sa)entity;
        int i = (int)Math.ceil(entitylivingbase.bQ());
        float f = entitylivingbase.bW();
        int j = (int)(f + 0.5F) / 2;
        if (j > 30) {
          j = 30;
        }
        int k = p_184047_1_.b() - 39;
        int l = p_184047_1_.a() / 2 + 91;
        int i1 = k;
        int j1 = 0;
        for (boolean flag = false; j > 0; j1 += 20)
        {
          int k1 = Math.min(j, 10);
          j -= k1;
          for (int l1 = 0; l1 < k1; l1++)
          {
            int i2 = 52;
            int j2 = 0;
            if (flag) {
              j2 = 1;
            }
            int k2 = l - l1 * 8 - 9;
            b(k2, i1, i2 + j2 * 9, 9, 9, 9);
            if (l1 * 2 + 1 + j1 < i) {
              b(k2, i1, i2 + 36, 9, 9, 9);
            }
            if (l1 * 2 + 1 + j1 == i) {
              b(k2, i1, i2 + 45, 9, 9, 9);
            }
          }
          i1 -= 10;
        }
      }
    }
  }
  
  private void f(bcx scaledRes)
  {
    bni.j();
    bni.a(false);
    bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.d();
    this.j.N().a(h);
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    vertexbuffer.a(7, bvp.g);
    vertexbuffer.b(0.0D, scaledRes.b(), -90.0D).a(0.0D, 1.0D).d();
    vertexbuffer.b(scaledRes.a(), scaledRes.b(), -90.0D).a(1.0D, 1.0D).d();
    vertexbuffer.b(scaledRes.a(), 0.0D, -90.0D).a(1.0D, 0.0D).d();
    vertexbuffer.b(0.0D, 0.0D, -90.0D).a(0.0D, 0.0D).d();
    tessellator.b();
    bni.a(true);
    bni.k();
    bni.e();
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
  }
  
  private void b(float lightLevel, bcx scaledRes)
  {
    lightLevel = 1.0F - lightLevel;
    lightLevel = on.a(lightLevel, 0.0F, 1.0F);
    arv worldborder = this.j.f.aj();
    float f = (float)worldborder.a(this.j.h);
    double d0 = Math.min(worldborder.o() * worldborder.p() * 1000.0D, Math.abs(worldborder.j() - worldborder.h()));
    double d1 = Math.max(worldborder.q(), d0);
    if (f < d1) {
      f = 1.0F - (float)(f / d1);
    } else {
      f = 0.0F;
    }
    this.a = ((float)(this.a + (lightLevel - this.a) * 0.01D));
    bni.j();
    bni.a(false);
    bni.a(bni.r.o, bni.l.k, bni.r.e, bni.l.n);
    if (f > 0.0F) {
      bni.c(0.0F, f, f, 1.0F);
    } else {
      bni.c(this.a, this.a, this.a, 1.0F);
    }
    this.j.N().a(f);
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    vertexbuffer.a(7, bvp.g);
    vertexbuffer.b(0.0D, scaledRes.b(), -90.0D).a(0.0D, 1.0D).d();
    vertexbuffer.b(scaledRes.a(), scaledRes.b(), -90.0D).a(1.0D, 1.0D).d();
    vertexbuffer.b(scaledRes.a(), 0.0D, -90.0D).a(1.0D, 0.0D).d();
    vertexbuffer.b(0.0D, 0.0D, -90.0D).a(0.0D, 0.0D).d();
    tessellator.b();
    bni.a(true);
    bni.k();
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
  }
  
  private void c(float timeInPortal, bcx scaledRes)
  {
    if (timeInPortal < 1.0F)
    {
      timeInPortal *= timeInPortal;
      timeInPortal *= timeInPortal;
      timeInPortal = timeInPortal * 0.8F + 0.2F;
    }
    bni.d();
    bni.j();
    bni.a(false);
    bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
    bni.c(1.0F, 1.0F, 1.0F, timeInPortal);
    this.j.N().a(bvg.g);
    bvh textureatlassprite = this.j.ab().a().a(aju.aY.u());
    float f = textureatlassprite.e();
    float f1 = textureatlassprite.g();
    float f2 = textureatlassprite.f();
    float f3 = textureatlassprite.h();
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    vertexbuffer.a(7, bvp.g);
    vertexbuffer.b(0.0D, scaledRes.b(), -90.0D).a(f, f3).d();
    vertexbuffer.b(scaledRes.a(), scaledRes.b(), -90.0D).a(f2, f3).d();
    vertexbuffer.b(scaledRes.a(), 0.0D, -90.0D).a(f2, f1).d();
    vertexbuffer.b(0.0D, 0.0D, -90.0D).a(f, f1).d();
    tessellator.b();
    bni.a(true);
    bni.k();
    bni.e();
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
  }
  
  private void a(int p_184044_1_, int p_184044_2_, float p_184044_3_, zj p_184044_4_, adq p_184044_5_)
  {
    if (p_184044_5_ != null)
    {
      float f = p_184044_5_.c - p_184044_3_;
      if (f > 0.0F)
      {
        bni.G();
        float f1 = 1.0F + f / 5.0F;
        bni.c(p_184044_1_ + 8, p_184044_2_ + 12, 0.0F);
        bni.b(1.0F / f1, (f1 + 1.0F) / 2.0F, 1.0F);
        bni.c(-(p_184044_1_ + 8), -(p_184044_2_ + 12), 0.0F);
      }
      this.k.a(p_184044_4_, p_184044_5_, p_184044_1_, p_184044_2_);
      if (f > 0.0F) {
        bni.H();
      }
      this.k.a(this.j.k, p_184044_5_, p_184044_1_, p_184044_2_);
    }
  }
  
  public void c()
  {
    LabyMod.getInstance().gameTick();
    if (this.o > 0) {
      this.o -= 1;
    }
    if (this.x > 0)
    {
      this.x -= 1;
      if (this.x <= 0)
      {
        this.y = "";
        this.z = "";
      }
    }
    this.m += 1;
    if (this.j.h != null)
    {
      adq itemstack = this.j.h.br.h();
      if (itemstack == null) {
        this.q = 0;
      } else if ((this.r != null) && (itemstack.b() == this.r.b()) && (adq.a(itemstack, this.r)) && ((itemstack.e()) || (itemstack.i() == this.r.i())))
      {
        if (this.q > 0) {
          this.q -= 1;
        }
      }
      else {
        this.q = 40;
      }
      this.r = itemstack;
    }
  }
  
  public void a(String recordName)
  {
    a(bwo.a("record.nowPlaying", new Object[] { recordName }), true);
  }
  
  public void a(String message, boolean isPlaying)
  {
    this.n = message;
    this.o = 60;
    this.p = isPlaying;
  }
  
  public void a(String title, String subTitle, int timeFadeIn, int displayTime, int timeFadeOut)
  {
    if ((title == null) && (subTitle == null) && (timeFadeIn < 0) && (displayTime < 0) && (timeFadeOut < 0))
    {
      this.y = "";
      this.z = "";
      this.x = 0;
    }
    else if (title != null)
    {
      this.y = title;
      this.x = (this.A + this.B + this.C);
    }
    else if (subTitle != null)
    {
      this.z = subTitle;
    }
    else
    {
      if (timeFadeIn >= 0) {
        this.A = timeFadeIn;
      }
      if (displayTime >= 0) {
        this.B = displayTime;
      }
      if (timeFadeOut >= 0) {
        this.C = timeFadeOut;
      }
      if (this.x > 0) {
        this.x = (this.A + this.B + this.C);
      }
    }
  }
  
  public void a(eu component, boolean isPlaying)
  {
    a(component.c(), isPlaying);
  }
  
  public bda d()
  {
    return this.l;
  }
  
  public int e()
  {
    return this.m;
  }
  
  public bct f()
  {
    return this.j.k;
  }
  
  public bdu g()
  {
    return this.u;
  }
  
  public bdp h()
  {
    return this.v;
  }
  
  public void i()
  {
    this.v.a();
    this.w.b();
  }
  
  public bcy j()
  {
    return this.w;
  }
}
