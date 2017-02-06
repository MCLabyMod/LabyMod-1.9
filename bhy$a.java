import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.List;
import java.util.Random;

class bhy$a
  implements bhu
{
  private final bbm b;
  private final kk c;
  private final List<bkv> d;
  
  public bhy$a(bhy arg1, bbm ☃)
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
