import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

public class bxv$a
{
  private final List<bof> a;
  private final Map<cq, List<bof>> b;
  private final boq c;
  private final boolean d;
  private bvh e;
  private boolean f;
  private bos g;
  
  public bxv$a(bok p_i46988_1_, boq p_i46988_2_)
  {
    this(p_i46988_1_.b(), p_i46988_1_.c(), p_i46988_1_.j(), p_i46988_2_);
  }
  
  public bxv$a(arc p_i46989_1_, bxo p_i46989_2_, bvh p_i46989_3_, cj p_i46989_4_)
  {
    this(p_i46989_2_.a(), p_i46989_2_.b(), p_i46989_2_.e(), p_i46989_2_.f());
    this.e = p_i46989_2_.d();
    long i = on.a(p_i46989_4_);
    for (cq enumfacing : cq.values()) {
      a(p_i46989_1_, p_i46989_2_, p_i46989_3_, enumfacing, i);
    }
    a(p_i46989_1_, p_i46989_2_, p_i46989_3_, i);
  }
  
  private bxv$a(boolean p_i46990_1_, boolean p_i46990_2_, bos p_i46990_3_, boq p_i46990_4_)
  {
    this.a = Lists.newArrayList();
    this.b = Maps.newEnumMap(cq.class);
    for (cq enumfacing : cq.values()) {
      this.b.put(enumfacing, Lists.newArrayList());
    }
    this.c = p_i46990_4_;
    this.d = p_i46990_1_;
    this.f = p_i46990_2_;
    this.g = p_i46990_3_;
  }
  
  private void a(arc p_188644_1_, bxo p_188644_2_, bvh p_188644_3_, cq p_188644_4_, long p_188644_5_)
  {
    for (bof bakedquad : p_188644_2_.a(p_188644_1_, p_188644_4_, p_188644_5_)) {
      a(p_188644_4_, new bom(bakedquad, p_188644_3_));
    }
  }
  
  private void a(arc p_188645_1_, bxo p_188645_2_, bvh p_188645_3_, long p_188645_4_)
  {
    for (bof bakedquad : p_188645_2_.a(p_188645_1_, (cq)null, p_188645_4_)) {
      a(new bom(bakedquad, p_188645_3_));
    }
  }
  
  public a a(cq facing, bof quad)
  {
    ((List)this.b.get(facing)).add(quad);
    return this;
  }
  
  public a a(bof quad)
  {
    this.a.add(quad);
    return this;
  }
  
  public a a(bvh texture)
  {
    this.e = texture;
    return this;
  }
  
  public bxo b()
  {
    if (this.e == null) {
      throw new RuntimeException("Missing particle!");
    }
    return new bxv(this.a, this.b, this.d, this.f, this.e, this.g, this.c);
  }
}
