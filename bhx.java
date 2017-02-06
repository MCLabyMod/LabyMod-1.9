import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.mojang.authlib.GameProfile;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class bhx
  implements bht, bhu
{
  private static final Ordering<bkv> a = Ordering.from(new Comparator()
  {
    public int a(bkv ☃, bkv ☃)
    {
      return ComparisonChain.start().compare(☃.a().getId(), ☃.a().getId()).result();
    }
  });
  private final List<bhu> b = Lists.newArrayList();
  
  public bhx()
  {
    this(a.sortedCopy(bcf.z().v().d()));
  }
  
  public bhx(Collection<bkv> ☃)
  {
    for (bkv ☃ : a.sortedCopy(☃)) {
      if (☃.b() != ahw.a.e) {
        this.b.add(new bhq(☃.a()));
      }
    }
  }
  
  public List<bhu> a()
  {
    return this.b;
  }
  
  public eu b()
  {
    return new fa("Select a player to teleport to");
  }
  
  public void a(bhs ☃)
  {
    ☃.a(this);
  }
  
  public eu F_()
  {
    return new fa("Teleport to player");
  }
  
  public void a(float ☃, int ☃)
  {
    bcf.z().N().a(bdu.a);
    bcv.a(0, 0, 0.0F, 0.0F, 16, 16, 256.0F, 256.0F);
  }
  
  public boolean G_()
  {
    return !this.b.isEmpty();
  }
}
