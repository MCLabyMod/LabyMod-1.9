import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;

public class bku
  extends aht
{
  private bks b;
  private bkq c;
  private final Set<rr> I = Sets.newHashSet();
  private final Set<rr> J = Sets.newHashSet();
  private final bcf K = bcf.z();
  private final Set<ahn> L = Sets.newHashSet();
  private int M;
  protected Set<ahn> a;
  private BlockPosM randomTickPosM = new BlockPosM(0, 0, 0, 3);
  
  public bku(bks netHandler, ahw settings, int dimension, qk difficulty, oo profilerIn)
  {
    super(new azp(), new azh(settings, "MpServer"), asw.a(dimension).d(), profilerIn, true);
    this.M = this.r.nextInt(12000);
    this.a = Sets.newHashSet();
    this.b = netHandler;
    T().a(difficulty);
    
    this.s.a(this);
    A(new cj(8, 64, 8));
    
    this.v = n();
    this.z = new azr();
    H();
    I();
    
    Reflector.postForgeBusEvent(Reflector.WorldEvent_Load_Constructor, new Object[] { this });
  }
  
  public void d()
  {
    super.d();
    a(P() + 1L);
    if (U().b("doDaylightCycle")) {
      b(Q() + 1L);
    }
    this.C.a("reEntryProcessing");
    for (int i = 0; (i < 10) && (!this.J.isEmpty()); i++)
    {
      rr entity = (rr)this.J.iterator().next();
      this.J.remove(entity);
      if (!this.e.contains(entity)) {
        a(entity);
      }
    }
    this.C.c("chunkCache");
    this.c.d();
    this.C.c("blocks");
    j();
    this.C.b();
  }
  
  public void a(int x1, int y1, int z1, int x2, int y2, int z2) {}
  
  protected arz n()
  {
    this.c = new bkq(this);
    return this.c;
  }
  
  protected boolean a(int x, int z, boolean allowEmpty)
  {
    return (allowEmpty) || (!f().d(x, z).f());
  }
  
  protected void a()
  {
    this.a.clear();
    int i = this.K.u.c;
    this.C.a("buildList");
    int j = on.c(this.K.h.p / 16.0D);
    int k = on.c(this.K.h.r / 16.0D);
    for (int l = -i; l <= i; l++) {
      for (int i1 = -i; i1 <= i; i1++) {
        this.a.add(new ahn(l + j, i1 + k));
      }
    }
    this.C.b();
  }
  
  protected void j()
  {
    a();
    if (this.M > 0) {
      this.M -= 1;
    }
    this.L.retainAll(this.a);
    if (this.L.size() == this.a.size()) {
      this.L.clear();
    }
    int i = 0;
    for (ahn chunkcoordintpair : this.a) {
      if (!this.L.contains(chunkcoordintpair))
      {
        int j = chunkcoordintpair.a * 16;
        int k = chunkcoordintpair.b * 16;
        this.C.a("getChunk");
        ase chunk = a(chunkcoordintpair.a, chunkcoordintpair.b);
        a(j, k, chunk);
        this.C.b();
        this.L.add(chunkcoordintpair);
        i++;
        if (i >= 10) {
          return;
        }
      }
    }
  }
  
  public void b(int chunkX, int chunkZ, boolean loadChunk)
  {
    if (loadChunk)
    {
      this.c.c(chunkX, chunkZ);
    }
    else
    {
      this.c.a(chunkX, chunkZ);
      b(chunkX * 16, 0, chunkZ * 16, chunkX * 16 + 15, 256, chunkZ * 16 + 15);
    }
  }
  
  public boolean a(rr entityIn)
  {
    boolean flag = super.a(entityIn);
    this.I.add(entityIn);
    if (!flag) {
      this.J.add(entityIn);
    } else if ((entityIn instanceof aah)) {
      this.K.U().a(new byc((aah)entityIn));
    }
    return flag;
  }
  
  public void e(rr entityIn)
  {
    super.e(entityIn);
    this.I.remove(entityIn);
  }
  
  protected void b(rr entityIn)
  {
    super.b(entityIn);
    if (this.J.contains(entityIn)) {
      this.J.remove(entityIn);
    }
  }
  
  protected void c(rr entityIn)
  {
    super.c(entityIn);
    boolean flag = false;
    if (this.I.contains(entityIn)) {
      if (entityIn.au())
      {
        this.J.add(entityIn);
        flag = true;
      }
      else
      {
        this.I.remove(entityIn);
      }
    }
  }
  
  public void a(int entityID, rr entityToSpawn)
  {
    rr entity = a(entityID);
    if (entity != null) {
      e(entity);
    }
    this.I.add(entityToSpawn);
    entityToSpawn.f(entityID);
    if (!a(entityToSpawn)) {
      this.J.add(entityToSpawn);
    }
    this.k.a(entityID, entityToSpawn);
  }
  
  public rr a(int id)
  {
    return id == this.K.h.O() ? this.K.h : super.a(id);
  }
  
  public rr e(int entityID)
  {
    rr entity = (rr)this.k.d(entityID);
    if (entity != null)
    {
      this.I.remove(entity);
      e(entity);
    }
    return entity;
  }
  
  public boolean b(cj pos, arc state)
  {
    int i = pos.p();
    int j = pos.q();
    int k = pos.r();
    a(i, j, k, i, j, k);
    return super.a(pos, state, 3);
  }
  
  public void M()
  {
    this.b.a().a(new fa("Quitting"));
  }
  
  protected void t() {}
  
  protected void a(int p_147467_1_, int p_147467_2_, ase chunkIn)
  {
    super.a(p_147467_1_, p_147467_2_, chunkIn);
    if (this.M == 0)
    {
      this.l = (this.l * 3 + 1013904223);
      int i = this.l >> 2;
      int j = i & 0xF;
      int k = i >> 8 & 0xF;
      int l = i >> 16 & 0xFF;
      cj blockpos = new cj(j + p_147467_1_, l, k + p_147467_2_);
      arc iblockstate = chunkIn.a(blockpos);
      j += p_147467_1_;
      k += p_147467_2_;
      if ((iblockstate.a() == axe.a) && (j(blockpos) <= this.r.nextInt(8)) && (b(ahz.a, blockpos) <= 0) && (this.K.h != null) && (this.K.h.e(j + 0.5D, l + 0.5D, k + 0.5D) > 4.0D))
      {
        a(j + 0.5D, l + 0.5D, k + 0.5D, ng.a, nh.i, 0.7F, 0.8F + this.r.nextFloat() * 0.2F, false);
        this.M = (this.r.nextInt(12000) + 6000);
      }
    }
  }
  
  public void b(int posX, int posY, int posZ)
  {
    int i = 32;
    Random random = new Random();
    adq itemstack = this.K.h.cb();
    boolean flag = (this.K.c.l() == ahw.a.c) && (itemstack != null) && (ajt.a(itemstack.b()) == aju.cv);
    cj.a blockpos$mutableblockpos = new cj.a();
    for (int j = 0; j < 667; j++)
    {
      a(posX, posY, posZ, 16, random, flag, blockpos$mutableblockpos);
      a(posX, posY, posZ, 32, random, flag, blockpos$mutableblockpos);
    }
  }
  
  public void a(int p_184153_1_, int p_184153_2_, int p_184153_3_, int p_184153_4_, Random p_184153_5_, boolean p_184153_6_, cj.a p_184153_7_)
  {
    int i = p_184153_1_ + this.r.nextInt(p_184153_4_) - this.r.nextInt(p_184153_4_);
    int j = p_184153_2_ + this.r.nextInt(p_184153_4_) - this.r.nextInt(p_184153_4_);
    int k = p_184153_3_ + this.r.nextInt(p_184153_4_) - this.r.nextInt(p_184153_4_);
    p_184153_7_.c(i, j, k);
    arc iblockstate = o(p_184153_7_);
    iblockstate.t().a(iblockstate, this, p_184153_7_, p_184153_5_);
    if ((p_184153_6_) && (iblockstate.t() == aju.cv)) {
      a(cy.J, i + 0.5F, j + 0.5F, k + 0.5F, 0.0D, 0.0D, 0.0D, new int[0]);
    }
  }
  
  public void c()
  {
    this.e.removeAll(this.f);
    for (int i = 0; i < this.f.size(); i++)
    {
      rr entity = (rr)this.f.get(i);
      int j = entity.ab;
      int k = entity.ad;
      if ((entity.aa) && (a(j, k, true))) {
        a(j, k).b(entity);
      }
    }
    for (int i1 = 0; i1 < this.f.size(); i1++) {
      c((rr)this.f.get(i1));
    }
    this.f.clear();
    for (int j1 = 0; j1 < this.e.size(); j1++)
    {
      rr entity1 = (rr)this.e.get(j1);
      rr entity2 = entity1.by();
      if (entity2 != null)
      {
        if ((entity2.F) || (!entity2.w(entity1))) {
          entity1.p();
        }
      }
      else if (entity1.F)
      {
        int k1 = entity1.ab;
        int l = entity1.ad;
        if ((entity1.aa) && (a(k1, l, true))) {
          a(k1, l).b(entity1);
        }
        this.e.remove(j1--);
        c(entity1);
      }
    }
  }
  
  public c a(b report)
  {
    c crashreportcategory = super.a(report);
    crashreportcategory.a("Forced entities", new Callable()
    {
      public String a()
      {
        return bku.a(bku.this).size() + " total; " + bku.a(bku.this).toString();
      }
    });
    crashreportcategory.a("Retry entities", new Callable()
    {
      public String a()
      {
        return bku.b(bku.this).size() + " total; " + bku.b(bku.this).toString();
      }
    });
    crashreportcategory.a("Server brand", new Callable()
    {
      public String a()
        throws Exception
      {
        return bku.c(bku.this).h.E();
      }
    });
    crashreportcategory.a("Server type", new Callable()
    {
      public String a()
        throws Exception
      {
        return bku.c(bku.this).F() == null ? "Non-integrated multiplayer server" : "Integrated singleplayer server";
      }
    });
    return crashreportcategory;
  }
  
  public void a(zj player, double x, double y, double z, nf soundIn, nh category, float volume, float pitch)
  {
    if (player == this.K.h) {
      a(x, y, z, soundIn, category, volume, pitch, false);
    }
  }
  
  public void a(cj p_184156_1_, nf p_184156_2_, nh p_184156_3_, float p_184156_4_, float p_184156_5_, boolean p_184156_6_)
  {
    a(p_184156_1_.p() + 0.5D, p_184156_1_.q() + 0.5D, p_184156_1_.r() + 0.5D, p_184156_2_, p_184156_3_, p_184156_4_, p_184156_5_, p_184156_6_);
  }
  
  public void a(double x, double y, double z, nf soundIn, nh category, float volume, float pitch, boolean distanceDelay)
  {
    double d0 = this.K.aa().e(x, y, z);
    bye positionedsoundrecord = new bye(soundIn, category, volume, pitch, (float)x, (float)y, (float)z);
    if ((distanceDelay) && (d0 > 100.0D))
    {
      double d1 = Math.sqrt(d0) / 40.0D;
      this.K.U().a(positionedsoundrecord, (int)(d1 * 20.0D));
    }
    else
    {
      this.K.U().a(positionedsoundrecord);
    }
  }
  
  public void a(double x, double y, double z, double motionX, double motionY, double motionZ, dn compund)
  {
    this.K.j.a(new blm.c(this, x, y, z, motionX, motionY, motionZ, this.K.j, compund));
  }
  
  public void a(ff<?> packetIn)
  {
    this.b.a(packetIn);
  }
  
  public void a(bbp scoreboardIn)
  {
    this.D = scoreboardIn;
  }
  
  public void b(long time)
  {
    if (time < 0L)
    {
      time = -time;
      U().a("doDaylightCycle", "false");
    }
    else
    {
      U().a("doDaylightCycle", "true");
    }
    super.b(time);
  }
  
  public bkq f()
  {
    return (bkq)super.z();
  }
}
