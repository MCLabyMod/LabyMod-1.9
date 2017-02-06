import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Callable;

public class bly
{
  private static final kk b = new kk("textures/particle/particles.png");
  protected aht a;
  private final ArrayDeque<blx>[][] c = new ArrayDeque[4][];
  private final Queue<bmm> d = Queues.newArrayDeque();
  private final bvi e;
  private final Random f = new Random();
  private final Map<Integer, blz> g = Maps.newHashMap();
  private final Queue<blx> h = Queues.newArrayDeque();
  
  public bly(aht worldIn, bvi rendererIn)
  {
    this.a = worldIn;
    this.e = rendererIn;
    for (int i = 0; i < 4; i++)
    {
      this.c[i] = new ArrayDeque[2];
      for (int j = 0; j < 2; j++) {
        this.c[i][j] = Queues.newArrayDeque();
      }
    }
    c();
  }
  
  private void c()
  {
    a(cy.a.c(), new bll.a());
    a(cy.e.c(), new blf.a());
    a(cy.f.c(), new bmh.a());
    a(cy.g.c(), new bmn.a());
    a(cy.N.c(), new bmo.a());
    a(cy.h.c(), new bmi.a());
    a(cy.i.c(), new bmj.b());
    a(cy.j.c(), new blg.c());
    a(cy.k.c(), new blg.b());
    a(cy.l.c(), new bme.a());
    a(cy.m.c(), new blt.a());
    a(cy.n.c(), new bmg.d());
    a(cy.o.c(), new bmg.b());
    a(cy.p.c(), new bmg.c());
    a(cy.q.c(), new bmg.a());
    a(cy.r.c(), new bmg.e());
    a(cy.s.c(), new bli.b());
    a(cy.t.c(), new bli.a());
    a(cy.u.c(), new blp.a());
    a(cy.v.c(), new bmj.a());
    a(cy.w.c(), new bmj.b());
    a(cy.x.c(), new blw.a());
    a(cy.y.c(), new bmb.a());
    a(cy.z.c(), new blj.a());
    a(cy.A.c(), new bln.a());
    a(cy.B.c(), new blu.a());
    a(cy.C.c(), new blo.a());
    a(cy.D.c(), new bma.a());
    a(cy.E.c(), new bmc.a());
    a(cy.F.c(), new ble.c());
    a(cy.G.c(), new bmf.a());
    a(cy.H.c(), new ble.b());
    a(cy.I.c(), new blp.b());
    a(cy.J.c(), new bld.a());
    a(cy.K.c(), new ble.a());
    a(cy.L.c(), new bml.a());
    a(cy.M.c(), new bmk.a());
    a(cy.c.c(), new blr.a());
    a(cy.b.c(), new blq.a());
    a(cy.d.c(), new blm.d());
    a(cy.P.c(), new blv.a());
    a(cy.Q.c(), new blh.a());
    a(cy.R.c(), new blk.a());
    a(cy.S.c(), new blg.a());
    a(cy.T.c(), new blc.a());
  }
  
  public void a(int id, blz particleFactory)
  {
    this.g.put(Integer.valueOf(id), particleFactory);
  }
  
  public void a(rr entityIn, cy particleTypes)
  {
    this.d.add(new bmm(this.a, entityIn, particleTypes));
  }
  
  public blx a(int particleId, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters)
  {
    blz iparticlefactory = (blz)this.g.get(Integer.valueOf(particleId));
    if (iparticlefactory != null)
    {
      blx entityfx = iparticlefactory.a(particleId, this.a, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, parameters);
      if (entityfx != null)
      {
        a(entityfx);
        return entityfx;
      }
    }
    return null;
  }
  
  public void a(blx effect)
  {
    if (effect == null) {
      return;
    }
    if (((effect instanceof blm.b)) && (!Config.isFireworkParticles())) {
      return;
    }
    this.h.add(effect);
  }
  
  public void a()
  {
    for (int i = 0; i < 4; i++) {
      a(i);
    }
    if (!this.d.isEmpty())
    {
      List<bmm> list = Lists.newArrayList();
      for (bmm entityparticleemitter : this.d)
      {
        entityparticleemitter.a();
        if (!entityparticleemitter.k()) {
          list.add(entityparticleemitter);
        }
      }
      this.d.removeAll(list);
    }
    if (!this.h.isEmpty()) {
      for (blx entityfx = (blx)this.h.poll(); entityfx != null; entityfx = (blx)this.h.poll())
      {
        int j = entityfx.b();
        int k = entityfx.c() ? 0 : 1;
        if (this.c[j][k].size() >= 16384) {
          this.c[j][k].removeFirst();
        }
        this.c[j][k].add(entityfx);
      }
    }
  }
  
  private void a(int layer)
  {
    this.a.C.a(layer + "");
    for (int i = 0; i < 2; i++)
    {
      this.a.C.a(i + "");
      a(this.c[layer][i]);
      this.a.C.b();
    }
    this.a.C.b();
  }
  
  private void a(Queue<blx> p_187240_1_)
  {
    if (!p_187240_1_.isEmpty())
    {
      Iterator<blx> iterator = p_187240_1_.iterator();
      while (iterator.hasNext())
      {
        blx entityfx = (blx)iterator.next();
        b(entityfx);
        if (!entityfx.k()) {
          iterator.remove();
        }
      }
    }
  }
  
  private void b(final blx particle)
  {
    try
    {
      particle.a();
    }
    catch (Throwable throwable)
    {
      b crashreport = b.a(throwable, "Ticking Particle");
      c crashreportcategory = crashreport.a("Particle being ticked");
      final int i = particle.b();
      crashreportcategory.a("Particle", new Callable()
      {
        public String a()
          throws Exception
        {
          return particle.toString();
        }
      });
      crashreportcategory.a("Particle Type", new Callable()
      {
        public String a()
          throws Exception
        {
          return "Unknown - " + i;
        }
      });
      throw new e(crashreport);
    }
  }
  
  public void a(rr entityIn, float partialTicks)
  {
    float f = bca.b();
    float f1 = bca.d();
    float f2 = bca.e();
    float f3 = bca.f();
    float f4 = bca.c();
    blx.D = entityIn.M + (entityIn.p - entityIn.M) * partialTicks;
    blx.E = entityIn.N + (entityIn.q - entityIn.N) * partialTicks;
    blx.F = entityIn.O + (entityIn.r - entityIn.O) * partialTicks;
    bni.m();
    bni.a(bni.r.l, bni.l.j);
    bni.a(516, 0.003921569F);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 2; j++)
      {
        final int i_f = i;
        if (!this.c[i][j].isEmpty())
        {
          switch (j)
          {
          case 0: 
            bni.a(false);
            break;
          case 1: 
            bni.a(true);
          }
          switch (i)
          {
          case 0: 
          default: 
            this.e.a(b);
            break;
          case 1: 
            this.e.a(bvg.g);
          }
          bni.c(1.0F, 1.0F, 1.0F, 1.0F);
          bnu tessellator = bnu.a();
          bmz vertexbuffer = tessellator.c();
          vertexbuffer.a(7, bvp.d);
          for (final blx entityfx : this.c[i][j]) {
            try
            {
              entityfx.a(vertexbuffer, entityIn, partialTicks, f, f4, f1, f2, f3);
            }
            catch (Throwable throwable)
            {
              b crashreport = b.a(throwable, "Rendering Particle");
              c crashreportcategory = crashreport.a("Particle being rendered");
              crashreportcategory.a("Particle", new Callable()
              {
                public String a()
                  throws Exception
                {
                  return entityfx.toString();
                }
              });
              crashreportcategory.a("Particle Type", new Callable()
              {
                public String a()
                  throws Exception
                {
                  return "Unknown - " + i_f;
                }
              });
              throw new e(crashreport);
            }
          }
          tessellator.b();
        }
      }
    }
    bni.a(true);
    bni.l();
    bni.a(516, 0.1F);
  }
  
  public void b(rr entityIn, float partialTick)
  {
    float f = 0.017453292F;
    float f1 = on.b(entityIn.v * 0.017453292F);
    float f2 = on.a(entityIn.v * 0.017453292F);
    float f3 = -f2 * on.a(entityIn.w * 0.017453292F);
    float f4 = f1 * on.a(entityIn.w * 0.017453292F);
    float f5 = on.b(entityIn.w * 0.017453292F);
    bmz vertexbuffer;
    for (int i = 0; i < 2; i++)
    {
      Queue<blx> queue = this.c[3][i];
      if (!queue.isEmpty())
      {
        bnu tessellator = bnu.a();
        vertexbuffer = tessellator.c();
        for (blx entityfx : queue) {
          entityfx.a(vertexbuffer, entityIn, partialTick, f1, f5, f2, f3, f4);
        }
      }
    }
  }
  
  public void a(aht worldIn)
  {
    this.a = worldIn;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 2; j++) {
        this.c[i][j].clear();
      }
    }
    this.d.clear();
  }
  
  public void a(cj pos, arc state)
  {
    boolean notAir;
    boolean notAir;
    if ((Reflector.ForgeBlock_addDestroyEffects.exists()) && (Reflector.ForgeBlock_isAir.exists()))
    {
      ajt block = state.t();
      if (!Reflector.callBoolean(block, Reflector.ForgeBlock_isAir, new Object[] { this.a, pos })) {}
      notAir = !Reflector.callBoolean(block, Reflector.ForgeBlock_addDestroyEffects, new Object[] { this.a, pos, this });
    }
    else
    {
      notAir = state.a() != axe.a;
    }
    if (notAir)
    {
      state = state.b(this.a, pos);
      int i = 4;
      for (int j = 0; j < i; j++) {
        for (int k = 0; k < i; k++) {
          for (int l = 0; l < i; l++)
          {
            double d0 = pos.p() + (j + 0.5D) / i;
            double d1 = pos.q() + (k + 0.5D) / i;
            double d2 = pos.r() + (l + 0.5D) / i;
            a(new bml(this.a, d0, d1, d2, d0 - pos.p() - 0.5D, d1 - pos.q() - 0.5D, d2 - pos.r() - 0.5D, state).a(pos));
          }
        }
      }
    }
  }
  
  public void a(cj pos, cq side)
  {
    arc iblockstate = this.a.o(pos);
    if (iblockstate.i() != aob.a)
    {
      int i = pos.p();
      int j = pos.q();
      int k = pos.r();
      float f = 0.1F;
      bbh axisalignedbb = iblockstate.c(this.a, pos);
      double d0 = i + this.f.nextDouble() * (axisalignedbb.d - axisalignedbb.a - f * 2.0F) + f + axisalignedbb.a;
      double d1 = j + this.f.nextDouble() * (axisalignedbb.e - axisalignedbb.b - f * 2.0F) + f + axisalignedbb.b;
      double d2 = k + this.f.nextDouble() * (axisalignedbb.f - axisalignedbb.c - f * 2.0F) + f + axisalignedbb.c;
      if (side == cq.a) {
        d1 = j + axisalignedbb.b - f;
      }
      if (side == cq.b) {
        d1 = j + axisalignedbb.e + f;
      }
      if (side == cq.c) {
        d2 = k + axisalignedbb.c - f;
      }
      if (side == cq.d) {
        d2 = k + axisalignedbb.f + f;
      }
      if (side == cq.e) {
        d0 = i + axisalignedbb.a - f;
      }
      if (side == cq.f) {
        d0 = i + axisalignedbb.d + f;
      }
      a(new bml(this.a, d0, d1, d2, 0.0D, 0.0D, 0.0D, iblockstate).a(pos).c(0.2F).d(0.6F));
    }
  }
  
  public String b()
  {
    int i = 0;
    for (int j = 0; j < 4; j++) {
      for (int k = 0; k < 2; k++) {
        i += this.c[j][k].size();
      }
    }
    return "" + i;
  }
  
  public void addBlockHitEffects(cj pos, bbi target)
  {
    ajt block = this.a.o(pos).t();
    
    boolean blockAddHitEffects = Reflector.callBoolean(block, Reflector.ForgeBlock_addHitEffects, new Object[] { this.a, target, this });
    if ((block != null) && (!blockAddHitEffects)) {
      a(pos, target.b);
    }
  }
}
