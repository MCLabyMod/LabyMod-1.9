import shadersmod.client.SVertexBuilder;

public class boc
  implements bwh
{
  private bob a;
  private final boe b;
  private final boa c = new boa();
  private final bod d;
  
  public boc(bob p_i46577_1_, bco p_i46577_2_)
  {
    this.a = p_i46577_1_;
    this.b = new boe(p_i46577_2_);
    this.d = new bod(p_i46577_2_);
  }
  
  public bob a()
  {
    return this.a;
  }
  
  public void a(arc state, cj pos, bvh texture, ahx blockAccess)
  {
    if (state.i() == aob.d)
    {
      state = state.b(blockAccess, pos);
      bxo ibakedmodel = this.a.b(state);
      
      bxo ibakedmodel1 = new bxv.a(state, ibakedmodel, texture, pos).b();
      this.b.a(blockAccess, ibakedmodel1, state, pos, bnu.a().c(), true);
    }
  }
  
  public boolean a(arc state, cj pos, ahx blockAccess, bmz worldRendererIn)
  {
    try
    {
      aob enumblockrendertype = state.i();
      if (enumblockrendertype == aob.a) {
        return false;
      }
      if (blockAccess.L() != ahy.g) {
        try
        {
          state = state.b(blockAccess, pos);
        }
        catch (Exception var8) {}
      }
      switch (enumblockrendertype)
      {
      case d: 
        if (Config.isShaders()) {
          SVertexBuilder.pushEntity(state, pos, blockAccess, worldRendererIn);
        }
        boolean flagModel = this.b.a(blockAccess, a(state), state, pos, worldRendererIn, true);
        if (Config.isShaders()) {
          SVertexBuilder.popEntity(worldRendererIn);
        }
        return flagModel;
      case c: 
        return false;
      case b: 
        if (Config.isShaders()) {
          SVertexBuilder.pushEntity(state, pos, blockAccess, worldRendererIn);
        }
        boolean flagFluid = this.d.a(blockAccess, state, pos, worldRendererIn);
        if (Config.isShaders()) {
          SVertexBuilder.popEntity(worldRendererIn);
        }
        return flagFluid;
      }
      return false;
    }
    catch (Throwable throwable)
    {
      b crashreport = b.a(throwable, "Tesselating block in world");
      c crashreportcategory = crashreport.a("Block being tesselated");
      c.a(crashreportcategory, pos, state.t(), state.t().e(state));
      throw new e(crashreport);
    }
  }
  
  public boe b()
  {
    return this.b;
  }
  
  public bxo a(arc p_184389_1_)
  {
    return this.a.b(p_184389_1_);
  }
  
  public void a(arc state, float brightness)
  {
    aob enumblockrendertype = state.i();
    if (enumblockrendertype != aob.a) {
      switch (enumblockrendertype)
      {
      case d: 
        bxo ibakedmodel = a(state);
        this.b.a(ibakedmodel, state, brightness, true);
        break;
      case c: 
        this.c.a(state.t(), brightness);
      }
    }
  }
  
  public boolean a(ajt p_184388_1_)
  {
    if (p_184388_1_ == null) {
      return false;
    }
    aob enumblockrendertype = p_184388_1_.u().i();
    return enumblockrendertype != aob.d;
  }
  
  public void a(bwg resourceManager)
  {
    this.d.a();
  }
}
