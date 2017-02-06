import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.mojang.authlib.GameProfile;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.utils.Allowed;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import de.labystudio.utils.FriendsLoader;
import java.util.Comparator;
import java.util.List;
import org.lwjgl.opengl.GL11;

public class bdp
  extends bcv
{
  private static final Ordering<bkv> a = Ordering.from(new bdp.a(null));
  private final bcf f;
  private final bcu g;
  private eu h;
  private eu i;
  private long j;
  private boolean k;
  
  public bdp(bcf mcIn, bcu guiIngameIn)
  {
    this.f = mcIn;
    this.g = guiIngameIn;
  }
  
  public String a(bkv networkPlayerInfoIn)
  {
    if (networkPlayerInfoIn.l() != null) {
      return FriendsLoader.getNick(networkPlayerInfoIn.l().d(), networkPlayerInfoIn.a().getName());
    }
    return bbm.a(networkPlayerInfoIn.j(), FriendsLoader.getNick(networkPlayerInfoIn.a().getName(), networkPlayerInfoIn.a().getName()));
  }
  
  public void a(boolean willBeRendered)
  {
    if ((willBeRendered) && (!this.k)) {
      this.j = bcf.I();
    }
    this.k = willBeRendered;
  }
  
  public void a(int width, bbp scoreboardIn, bbl scoreObjectiveIn)
  {
    if ((ConfigManager.settings.oldTablist) && (Allowed.animations())) {
      oldTabOverlay(width, scoreboardIn, scoreObjectiveIn);
    } else {
      newTabOverlay(width, scoreboardIn, scoreObjectiveIn);
    }
  }
  
  private void newTabOverlay(int width, bbp scoreboardIn, bbl scoreObjectiveIn)
  {
    bks nethandlerplayclient = this.f.h.d;
    List<bkv> list = a.sortedCopy(nethandlerplayclient.d());
    int i = 0;
    int j = 0;
    for (bkv networkplayerinfo : list)
    {
      int k = this.f.k.a(a(networkplayerinfo));
      i = Math.max(i, k);
      if ((scoreObjectiveIn != null) && (scoreObjectiveIn.e() != bbv.a.b))
      {
        k = this.f.k.a(" " + scoreboardIn.c(networkplayerinfo.a().getName(), scoreObjectiveIn).c());
        j = Math.max(j, k);
      }
    }
    list = list.subList(0, Math.min(list.size(), 80));
    int l3 = list.size();
    int i4 = l3;
    for (int j4 = 1; i4 > 20; i4 = (l3 + j4 - 1) / j4) {
      j4++;
    }
    boolean flag = (this.f.D()) || (this.f.v().a().f());
    int l;
    int l;
    if (scoreObjectiveIn != null)
    {
      int l;
      if (scoreObjectiveIn.e() == bbv.a.b) {
        l = 90;
      } else {
        l = j;
      }
    }
    else
    {
      l = 0;
    }
    int i1 = Math.min(j4 * ((flag ? 9 : 0) + i + l + 13), width - 50) / j4;
    int j1 = width / 2 - (i1 * j4 + (j4 - 1) * 5) / 2;
    int k1 = 10;
    int l1 = i1 * j4 + (j4 - 1) * 5;
    List<String> list1 = null;
    List<String> list2 = null;
    if (this.i != null)
    {
      list1 = this.f.k.c(this.i.d(), width - 50);
      for (String s : list1) {
        l1 = Math.max(l1, this.f.k.a(s));
      }
    }
    if (this.h != null)
    {
      list2 = this.f.k.c(this.h.d(), width - 50);
      for (String s2 : list2) {
        l1 = Math.max(l1, this.f.k.a(s2));
      }
    }
    if (list1 != null)
    {
      a(width / 2 - l1 / 2 - 1, k1 - 1, width / 2 + l1 / 2 + 1, k1 + list1.size() * this.f.k.a, Integer.MIN_VALUE);
      for (String s3 : list1)
      {
        int i2 = this.f.k.a(s3);
        this.f.k.a(s3, width / 2 - i2 / 2, k1, -1);
        k1 += this.f.k.a;
      }
      k1++;
    }
    a(width / 2 - l1 / 2 - 1, k1 - 1, width / 2 + l1 / 2 + 1, k1 + i4 * 9, Integer.MIN_VALUE);
    for (int k4 = 0; k4 < l3; k4++)
    {
      int l4 = k4 / i4;
      int i5 = k4 % i4;
      int j2 = j1 + l4 * i1 + l4 * 5;
      int k2 = k1 + i5 * 9;
      a(j2, k2, j2 + i1, k2 + 8, 553648127);
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      bni.e();
      bni.m();
      bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
      if (k4 < list.size())
      {
        bkv networkplayerinfo1 = (bkv)list.get(k4);
        String s1 = a(networkplayerinfo1);
        GameProfile gameprofile = networkplayerinfo1.a();
        if (flag)
        {
          zj entityplayer = this.f.f.b(gameprofile.getId());
          boolean flag1 = (entityplayer != null) && (entityplayer.a(zk.a)) && ((gameprofile.getName().equals("Dinnerbone")) || (gameprofile.getName().equals("Grumm")));
          this.f.N().a(networkplayerinfo1.g());
          int l2 = 8 + (flag1 ? 8 : 0);
          int i3 = 8 * (flag1 ? -1 : 1);
          bcv.a(j2, k2, 8.0F, l2, 8, i3, 8, 8, 64.0F, 64.0F);
          if ((entityplayer != null) && (entityplayer.a(zk.g)))
          {
            int j3 = 8 + (flag1 ? 8 : 0);
            int k3 = 8 * (flag1 ? -1 : 1);
            bcv.a(j2, k2, 40.0F, j3, 8, k3, 8, 8, 64.0F, 64.0F);
          }
          j2 += 9;
        }
        if (networkplayerinfo1.b() == ahw.a.e)
        {
          s1 = a.u + s1;
          this.f.k.a(s1, j2, k2, -1862270977);
        }
        else
        {
          this.f.k.a(s1, j2, k2, -1);
        }
        if ((scoreObjectiveIn != null) && (networkplayerinfo1.b() != ahw.a.e))
        {
          int k5 = j2 + i + 1;
          int l5 = k5 + l;
          if (l5 - k5 > 5) {
            a(scoreObjectiveIn, k2, gameprofile.getName(), k5, l5, networkplayerinfo1);
          }
        }
        a(i1, j2 - (flag ? 9 : 0), k2, networkplayerinfo1);
      }
    }
    if (list2 != null)
    {
      k1 = k1 + i4 * 9 + 1;
      a(width / 2 - l1 / 2 - 1, k1 - 1, width / 2 + l1 / 2 + 1, k1 + list2.size() * this.f.k.a, Integer.MIN_VALUE);
      for (String s4 : list2)
      {
        int j5 = this.f.k.a(s4);
        this.f.k.a(s4, width / 2 - j5 / 2, k1, -1);
        k1 += this.f.k.a;
      }
    }
  }
  
  public void oldTabOverlay(int width, bbp scoreboardIn, bbl scoreObjectiveIn)
  {
    try
    {
      bks var4 = this.f.h.d;
      List var42 = a.sortedCopy(var4.d());
      int var15 = LabyMod.getInstance().mc.h.d.a;
      int var16 = var15;
      bcx var5 = new bcx(LabyMod.getInstance().mc);
      int var17 = 0;
      int var6 = var5.a();
      int var21 = 0;
      int var22 = 0;
      int var23 = 0;
      for (var17 = 1; var16 > 20; var16 = (var15 + var17 - 1) / var17) {
        var17++;
      }
      int var46 = 300 / var17;
      if (var46 > 150) {
        var46 = 150;
      }
      int var19 = (var6 - var17 * var46) / 2;
      byte var47 = 10;
      a(var19 - 1, var47 - 1, var19 + var46 * var17, var47 + 9 * var16, Integer.MIN_VALUE);
      for (var21 = 0; var21 < var15; var21++)
      {
        var22 = var19 + var21 % var17 * var46;
        var23 = var47 + var21 / var17 * 9;
        a(var22, var23, var22 + var46 - 1, var23 + 8, 553648127);
        bni.e();
        if (var21 < var42.size())
        {
          bkv var48 = (bkv)var42.get(var21);
          String name = var48.a().getName();
          bbm var49 = LabyMod.getInstance().mc.f.ad().g(name);
          String var50 = a(var48);
          LabyMod.getInstance().draw.drawString(var50, var22, var23);
          if (scoreObjectiveIn != null)
          {
            int var27 = var22 + LabyMod.getInstance().draw.getStringWidth(var50) + 5;
            int var28 = var22 + var46 - 12 - 5;
            if (var28 - var27 > 5)
            {
              bbn var29 = scoreboardIn.c(name, scoreObjectiveIn);
              String var30 = a.o + "" + var29.c();
              LabyMod.getInstance().draw.drawString(var30, var28 - LabyMod.getInstance().draw.getStringWidth(var30), var23, 1.6777215E7D);
            }
          }
          a(50, var22 + var46 - 52, var23, var48);
        }
      }
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      bni.g();
      bni.e();
    }
    catch (Exception error)
    {
      error.printStackTrace();
    }
  }
  
  protected void a(int p_175245_1_, int p_175245_2_, int p_175245_3_, bkv networkPlayerInfoIn)
  {
    if ((!ConfigManager.settings.tabPing.booleanValue()) || (!Allowed.unfairExtra()))
    {
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      this.f.N().a(d);
      int i = 0;
      int j = 0;
      if (networkPlayerInfoIn.c() < 0) {
        j = 5;
      } else if (networkPlayerInfoIn.c() < 150) {
        j = 0;
      } else if (networkPlayerInfoIn.c() < 300) {
        j = 1;
      } else if (networkPlayerInfoIn.c() < 600) {
        j = 2;
      } else if (networkPlayerInfoIn.c() < 1000) {
        j = 3;
      } else {
        j = 4;
      }
      this.e += 100.0F;
      b(p_175245_2_ + p_175245_1_ - 11, p_175245_3_, 0 + i * 10, 176 + j * 8, 10, 8);
    }
    else
    {
      this.e += 100.0F;
    }
    DrawUtils draw = LabyMod.getInstance().draw;
    GL11.glPushMatrix();
    bni.a(0.5D, 0.5D, 0.5D);
    int ping = networkPlayerInfoIn.c();
    if (ping >= 1000) {
      ping = 999;
    }
    if (ping < 0) {
      ping = 0;
    }
    if ((networkPlayerInfoIn.a() != null) && (networkPlayerInfoIn.a().getName() != null) && 
      (LabyMod.getInstance().isInGame()) && 
      (networkPlayerInfoIn.a().getName().equals(bcf.z().h.h_()))) {
      LabyMod.getInstance().playerPing = ping;
    }
    String c = "a";
    if (ping > 150) {
      c = "2";
    }
    if (ping > 300) {
      c = "c";
    }
    if (ping > 600) {
      c = "4";
    }
    String showPing = "" + ping;
    if (ping == 0) {
      showPing = "?";
    }
    if (ConfigManager.settings.tabPing.booleanValue()) {
      draw.drawCenteredString(Color.cl(c) + showPing + "", (p_175245_2_ + p_175245_1_) * 2 - 12, p_175245_3_ * 2 + 5);
    }
    GL11.glPopMatrix();
    this.e -= 100.0F;
  }
  
  private void a(bbl p_175247_1_, int p_175247_2_, String p_175247_3_, int p_175247_4_, int p_175247_5_, bkv p_175247_6_)
  {
    int i = p_175247_1_.a().c(p_175247_3_, p_175247_1_).c();
    if (p_175247_1_.e() == bbv.a.b)
    {
      this.f.N().a(d);
      if (this.j == p_175247_6_.q()) {
        if (i < p_175247_6_.m())
        {
          p_175247_6_.a(bcf.I());
          p_175247_6_.b(this.g.e() + 20);
        }
        else if (i > p_175247_6_.m())
        {
          p_175247_6_.a(bcf.I());
          p_175247_6_.b(this.g.e() + 10);
        }
      }
      if ((bcf.I() - p_175247_6_.o() > 1000L) || (this.j != p_175247_6_.q()))
      {
        p_175247_6_.b(i);
        p_175247_6_.c(i);
        p_175247_6_.a(bcf.I());
      }
      p_175247_6_.c(this.j);
      p_175247_6_.b(i);
      int j = on.f(Math.max(i, p_175247_6_.n()) / 2.0F);
      int k = Math.max(on.f(i / 2), Math.max(on.f(p_175247_6_.n() / 2), 10));
      boolean flag = (p_175247_6_.p() > this.g.e()) && ((p_175247_6_.p() - this.g.e()) / 3L % 2L == 1L);
      if (j > 0)
      {
        float f = Math.min((p_175247_5_ - p_175247_4_ - 4) / k, 9.0F);
        if (f > 3.0F)
        {
          for (int l = j; l < k; l++) {
            a(p_175247_4_ + l * f, p_175247_2_, flag ? 25 : 16, 0, 9, 9);
          }
          for (int j1 = 0; j1 < j; j1++)
          {
            a(p_175247_4_ + j1 * f, p_175247_2_, flag ? 25 : 16, 0, 9, 9);
            if (flag)
            {
              if (j1 * 2 + 1 < p_175247_6_.n()) {
                a(p_175247_4_ + j1 * f, p_175247_2_, 70, 0, 9, 9);
              }
              if (j1 * 2 + 1 == p_175247_6_.n()) {
                a(p_175247_4_ + j1 * f, p_175247_2_, 79, 0, 9, 9);
              }
            }
            if (j1 * 2 + 1 < i) {
              a(p_175247_4_ + j1 * f, p_175247_2_, j1 >= 10 ? 160 : 52, 0, 9, 9);
            }
            if (j1 * 2 + 1 == i) {
              a(p_175247_4_ + j1 * f, p_175247_2_, j1 >= 10 ? 169 : 61, 0, 9, 9);
            }
          }
        }
        else
        {
          float f1 = on.a(i / 20.0F, 0.0F, 1.0F);
          int i1 = (int)((1.0F - f1) * 255.0F) << 16 | (int)(f1 * 255.0F) << 8;
          String s = "" + i / 2.0F;
          if (p_175247_5_ - this.f.k.a(s + "hp") >= p_175247_4_) {
            s = s + "hp";
          }
          this.f.k.a(s, (p_175247_5_ + p_175247_4_) / 2 - this.f.k.a(s) / 2, p_175247_2_, i1);
        }
      }
    }
    else
    {
      String s1 = a.o + "" + i;
      this.f.k.a(s1, p_175247_5_ - this.f.k.a(s1), p_175247_2_, 16777215);
    }
  }
  
  public void a(eu footerIn)
  {
    this.h = footerIn;
  }
  
  public void b(eu headerIn)
  {
    this.i = headerIn;
  }
  
  public void a()
  {
    this.i = null;
    this.h = null;
  }
  
  static class a
    implements Comparator<bkv>
  {
    public int a(bkv p_compare_1_, bkv p_compare_2_)
    {
      bbm scoreplayerteam = p_compare_1_.j();
      bbm scoreplayerteam1 = p_compare_2_.j();
      return ComparisonChain.start().compareTrueFirst(p_compare_1_.b() != ahw.a.e, p_compare_2_.b() != ahw.a.e).compare(scoreplayerteam != null ? scoreplayerteam.b() : "", scoreplayerteam1 != null ? scoreplayerteam1.b() : "").compare(p_compare_1_.a().getName(), p_compare_2_.a().getName()).result();
    }
  }
}
