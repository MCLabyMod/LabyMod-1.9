import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.List;
import java.util.Random;

public class bhy
  implements bht, bhu
{
  private final List<bhu> a = Lists.newArrayList();
  
  public bhy()
  {
    bcf ☃ = bcf.z();
    for (bbm ☃ : ☃.f.ad().g()) {
      this.a.add(new bhy.a(☃));
    }
  }
  
  public List<bhu> a()
  {
    return this.a;
  }
  
  public eu b()
  {
    return new fa("Select a team to teleport to");
  }
  
  public void a(bhs ☃)
  {
    ☃.a(this);
  }
  
  public eu F_()
  {
    return new fa("Teleport to team member");
  }
  
  public void a(float ☃, int ☃)
  {
    bcf.z().N().a(bdu.a);
    bcv.a(0, 0, 16.0F, 0.0F, 16, 16, 256.0F, 256.0F);
  }
  
  public boolean G_()
  {
    for (bhu ☃ : this.a) {
      if (☃.G_()) {
        return true;
      }
    }
    return false;
  }
  
  class a
    implements bhu
  {
    private final bbm b;
    private final kk c;
    private final List<bkv> d;
    
    public a(bbm ☃)
    {
      this.b = ☃;
      
      this.d = Lists.newArrayList();
      for (String ☃ : ☃.d())
      {
        bkv ☃ = bcf.z().v().a(☃);
        if (☃ != null) {
          this.d.add(☃);
        }
      }
      if (!this.d.isEmpty())
      {
        String ☃ = ((bkv)this.d.get(new Random().nextInt(this.d.size()))).a().getName();
        this.c = bmq.e(☃);
        bmq.a(this.c, ☃);
      }
      else
      {
        this.c = bvw.a();
      }
    }
    
    public void a(bhs ☃)
    {
      ☃.a(new bhx(this.d));
    }
    
    public eu F_()
    {
      return new fa(this.b.c());
    }
    
    public void a(float ☃, int ☃)
    {
      int ☃ = -1;
      String ☃ = bct.b(this.b.e());
      if (☃.length() >= 2) {
        ☃ = bcf.z().k.b(☃.charAt(1));
      }
      if (☃ >= 0)
      {
        float ☃ = (☃ >> 16 & 0xFF) / 255.0F;
        float ☃ = (☃ >> 8 & 0xFF) / 255.0F;
        float ☃ = (☃ & 0xFF) / 255.0F;
        bcv.a(1, 1, 15, 15, on.b(☃ * ☃, ☃ * ☃, ☃ * ☃) | ☃ << 24);
      }
      bcf.z().N().a(this.c);
      bni.c(☃, ☃, ☃, ☃ / 255.0F);
      bcv.a(2, 2, 8.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
      bcv.a(2, 2, 40.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
    }
    
    public boolean G_()
    {
      return !this.d.isEmpty();
    }
  }
}
