import java.util.List;

public class bee$a
  extends bfh
{
  private bcf g = bcf.z();
  
  public bee$a(bdd p_i46749_1_)
  {
    super(p_i46749_1_, false);
  }
  
  public void a()
  {
    super.a();
    if (this.f.size() > 1)
    {
      StringBuilder stringbuilder = new StringBuilder();
      for (String s : this.f)
      {
        if (stringbuilder.length() > 0) {
          stringbuilder.append(", ");
        }
        stringbuilder.append(s);
      }
      this.g.r.d().a(new fa(stringbuilder.toString()), 1);
    }
  }
  
  public cj b()
  {
    cj blockpos = null;
    if ((this.g.t != null) && (this.g.t.a == bbi.a.b)) {
      blockpos = this.g.t.a();
    }
    return blockpos;
  }
}
