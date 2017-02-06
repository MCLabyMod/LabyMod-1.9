import java.util.Arrays;
import java.util.concurrent.Callable;

public class zi
  implements qg
{
  public final adq[] a = new adq[36];
  public final adq[] b = new adq[4];
  public final adq[] c = new adq[1];
  private final adq[][] g;
  public int d;
  public zj e;
  private adq h;
  public boolean f;
  
  public zi(zj playerIn)
  {
    this.g = new adq[][] { this.a, this.b, this.c };
    this.e = playerIn;
  }
  
  public adq h()
  {
    return e(this.d) ? this.a[this.d] : null;
  }
  
  public static int i()
  {
    return 9;
  }
  
  private boolean a(adq p_184436_1_, adq p_184436_2_)
  {
    return (p_184436_1_ != null) && (b(p_184436_1_, p_184436_2_)) && (p_184436_1_.d()) && (p_184436_1_.b < p_184436_1_.c()) && (p_184436_1_.b < w_());
  }
  
  private boolean b(adq p_184431_1_, adq p_184431_2_)
  {
    return (p_184431_1_.b() == p_184431_2_.b()) && ((!p_184431_1_.f()) || (p_184431_1_.i() == p_184431_2_.i())) && (adq.a(p_184431_1_, p_184431_2_));
  }
  
  public int j()
  {
    for (int i = 0; i < this.a.length; i++) {
      if (this.a[i] == null) {
        return i;
      }
    }
    return -1;
  }
  
  public void a(adq p_184434_1_)
  {
    int i = b(p_184434_1_);
    if (e(i))
    {
      this.d = i;
    }
    else if (i == -1)
    {
      this.d = k();
      if (this.a[this.d] != null)
      {
        int j = j();
        if (j != -1) {
          this.a[j] = this.a[this.d];
        }
      }
      this.a[this.d] = p_184434_1_;
    }
    else
    {
      d(i);
    }
  }
  
  public void d(int p_184430_1_)
  {
    this.d = k();
    adq itemstack = this.a[this.d];
    this.a[this.d] = this.a[p_184430_1_];
    this.a[p_184430_1_] = itemstack;
  }
  
  public static boolean e(int p_184435_0_)
  {
    return (p_184435_0_ >= 0) && (p_184435_0_ < 9);
  }
  
  public int b(adq p_184429_1_)
  {
    for (int i = 0; i < this.a.length; i++) {
      if ((this.a[i] != null) && (b(p_184429_1_, this.a[i]))) {
        return i;
      }
    }
    return -1;
  }
  
  public int k()
  {
    for (int i = 0; i < 9; i++)
    {
      int j = (this.d + i) % 9;
      if (this.a[j] == null) {
        return j;
      }
    }
    for (int k = 0; k < 9; k++)
    {
      int l = (this.d + k) % 9;
      if (!this.a[l].w()) {
        return l;
      }
    }
    return this.d;
  }
  
  public void f(int direction)
  {
    if (direction > 0) {
      direction = 1;
    }
    if (direction < 0) {
      direction = -1;
    }
    for (this.d -= direction; this.d < 0; this.d += 9) {}
    while (this.d >= 9) {
      this.d -= 9;
    }
  }
  
  public int a(ado itemIn, int metadataIn, int removeCount, dn itemNBT)
  {
    int i = 0;
    for (int j = 0; j < u_(); j++)
    {
      adq itemstack = a(j);
      if ((itemstack != null) && ((itemIn == null) || (itemstack.b() == itemIn)) && ((metadataIn <= -1) || (itemstack.i() == metadataIn)) && ((itemNBT == null) || (dy.a(itemNBT, itemstack.o(), true))))
      {
        int k = removeCount <= 0 ? itemstack.b : Math.min(removeCount - i, itemstack.b);
        i += k;
        if (removeCount != 0)
        {
          itemstack.b -= k;
          if (itemstack.b == 0) {
            a(j, (adq)null);
          }
          if ((removeCount > 0) && (i >= removeCount)) {
            return i;
          }
        }
      }
    }
    if (this.h != null)
    {
      if ((itemIn != null) && (this.h.b() != itemIn)) {
        return i;
      }
      if ((metadataIn > -1) && (this.h.i() != metadataIn)) {
        return i;
      }
      if ((itemNBT != null) && (!dy.a(itemNBT, this.h.o(), true))) {
        return i;
      }
      int l = removeCount <= 0 ? this.h.b : Math.min(removeCount - i, this.h.b);
      i += l;
      if (removeCount != 0)
      {
        this.h.b -= l;
        if (this.h.b == 0) {
          this.h = null;
        }
        if ((removeCount > 0) && (i >= removeCount)) {
          return i;
        }
      }
    }
    return i;
  }
  
  private int g(adq itemStackIn)
  {
    ado item = itemStackIn.b();
    int i = itemStackIn.b;
    int j = h(itemStackIn);
    if (j == -1) {
      j = j();
    }
    if (j == -1) {
      return i;
    }
    adq itemstack = a(j);
    if (itemstack == null)
    {
      itemstack = new adq(item, 0, itemStackIn.i());
      if (itemStackIn.n()) {
        itemstack.d((dn)itemStackIn.o().b());
      }
      a(j, itemstack);
    }
    int k = i;
    if (i > itemstack.c() - itemstack.b) {
      k = itemstack.c() - itemstack.b;
    }
    if (k > w_() - itemstack.b) {
      k = w_() - itemstack.b;
    }
    if (k == 0) {
      return i;
    }
    i -= k;
    itemstack.b += k;
    itemstack.c = 5;
    return i;
  }
  
  private int h(adq itemStackIn)
  {
    if (a(a(this.d), itemStackIn)) {
      return this.d;
    }
    if (a(a(40), itemStackIn)) {
      return 40;
    }
    for (int i = 0; i < this.a.length; i++) {
      if (a(this.a[i], itemStackIn)) {
        return i;
      }
    }
    return -1;
  }
  
  public void m()
  {
    for (int i = 0; i < this.g.length; i++)
    {
      adq[] aitemstack = this.g[i];
      for (int j = 0; j < aitemstack.length; j++) {
        if (aitemstack[j] != null) {
          aitemstack[j].a(this.e.l, this.e, j, this.d == j);
        }
      }
    }
  }
  
  public boolean c(final adq itemStackIn)
  {
    if ((itemStackIn != null) && (itemStackIn.b != 0) && (itemStackIn.b() != null)) {
      try
      {
        if (itemStackIn.g())
        {
          int j = j();
          if (j >= 0)
          {
            this.a[j] = adq.c(itemStackIn);
            this.a[j].c = 5;
            itemStackIn.b = 0;
            return true;
          }
          if (this.e.bJ.d)
          {
            itemStackIn.b = 0;
            return true;
          }
          return false;
        }
        int i;
        for (;;)
        {
          i = itemStackIn.b;
          itemStackIn.b = g(itemStackIn);
          if (itemStackIn.b > 0) {
            if (itemStackIn.b >= i) {
              break;
            }
          }
        }
        if ((itemStackIn.b == i) && (this.e.bJ.d))
        {
          itemStackIn.b = 0;
          return true;
        }
        return itemStackIn.b < i;
      }
      catch (Throwable throwable)
      {
        b crashreport = b.a(throwable, "Adding item to inventory");
        c crashreportcategory = crashreport.a("Item being added");
        crashreportcategory.a("Item ID", Integer.valueOf(ado.a(itemStackIn.b())));
        crashreportcategory.a("Item data", Integer.valueOf(itemStackIn.i()));
        crashreportcategory.a("Item name", new Callable()
        {
          public String a()
            throws Exception
          {
            return itemStackIn.q();
          }
        });
        throw new e(crashreport);
      }
    }
    return false;
  }
  
  public adq a(int index, int count)
  {
    adq[] aitemstack = null;
    for (adq[] aitemstack1 : this.g)
    {
      if (index < aitemstack1.length)
      {
        aitemstack = aitemstack1;
        break;
      }
      index -= aitemstack1.length;
    }
    return (aitemstack != null) && (aitemstack[index] != null) ? qh.a(aitemstack, index, count) : null;
  }
  
  public void d(adq p_184437_1_)
  {
    for (adq[] aitemstack : this.g) {
      for (int i = 0; i < aitemstack.length; i++) {
        if (aitemstack[i] == p_184437_1_)
        {
          aitemstack[i] = null;
          break;
        }
      }
    }
  }
  
  public adq b(int index)
  {
    adq[] aitemstack = null;
    for (adq[] aitemstack1 : this.g)
    {
      if (index < aitemstack1.length)
      {
        aitemstack = aitemstack1;
        break;
      }
      index -= aitemstack1.length;
    }
    if ((aitemstack != null) && (aitemstack[index] != null))
    {
      adq itemstack = aitemstack[index];
      aitemstack[index] = null;
      return itemstack;
    }
    return null;
  }
  
  public void a(int index, adq stack)
  {
    adq[] aitemstack = null;
    for (adq[] aitemstack1 : this.g)
    {
      if (index < aitemstack1.length)
      {
        aitemstack = aitemstack1;
        break;
      }
      index -= aitemstack1.length;
    }
    if (aitemstack != null) {
      aitemstack[index] = stack;
    }
  }
  
  public float a(arc p_184438_1_)
  {
    float f = 1.0F;
    if (this.a[this.d] != null) {
      f *= this.a[this.d].a(p_184438_1_);
    }
    return f;
  }
  
  public du a(du nbtTagListIn)
  {
    for (int i = 0; i < this.a.length; i++) {
      if (this.a[i] != null)
      {
        dn nbttagcompound = new dn();
        nbttagcompound.a("Slot", (byte)i);
        this.a[i].b(nbttagcompound);
        nbtTagListIn.a(nbttagcompound);
      }
    }
    for (int j = 0; j < this.b.length; j++) {
      if (this.b[j] != null)
      {
        dn nbttagcompound1 = new dn();
        nbttagcompound1.a("Slot", (byte)(j + 100));
        this.b[j].b(nbttagcompound1);
        nbtTagListIn.a(nbttagcompound1);
      }
    }
    for (int k = 0; k < this.c.length; k++) {
      if (this.c[k] != null)
      {
        dn nbttagcompound2 = new dn();
        nbttagcompound2.a("Slot", (byte)(k + 150));
        this.c[k].b(nbttagcompound2);
        nbtTagListIn.a(nbttagcompound2);
      }
    }
    return nbtTagListIn;
  }
  
  public void b(du nbtTagListIn)
  {
    Arrays.fill(this.a, null);
    Arrays.fill(this.b, null);
    Arrays.fill(this.c, null);
    for (int i = 0; i < nbtTagListIn.c(); i++)
    {
      dn nbttagcompound = nbtTagListIn.b(i);
      int j = nbttagcompound.f("Slot") & 0xFF;
      adq itemstack = adq.a(nbttagcompound);
      if (itemstack != null) {
        if ((j >= 0) && (j < this.a.length)) {
          this.a[j] = itemstack;
        } else if ((j >= 100) && (j < this.b.length + 100)) {
          this.b[(j - 100)] = itemstack;
        } else if ((j >= 150) && (j < this.c.length + 150)) {
          this.c[(j - 150)] = itemstack;
        }
      }
    }
  }
  
  public int u_()
  {
    return this.a.length + this.b.length + this.c.length;
  }
  
  public adq a(int index)
  {
    adq[] aitemstack = null;
    for (adq[] aitemstack1 : this.g)
    {
      if (index < aitemstack1.length)
      {
        aitemstack = aitemstack1;
        break;
      }
      index -= aitemstack1.length;
    }
    return aitemstack == null ? null : aitemstack[index];
  }
  
  public String h_()
  {
    return "container.inventory";
  }
  
  public boolean o_()
  {
    return false;
  }
  
  public eu i_()
  {
    return o_() ? new fa(h_()) : new fb(h_(), new Object[0]);
  }
  
  public int w_()
  {
    return 64;
  }
  
  public boolean b(arc p_184432_1_)
  {
    if (p_184432_1_.a().l()) {
      return true;
    }
    adq itemstack = a(this.d);
    return itemstack != null ? itemstack.b(p_184432_1_) : false;
  }
  
  public adq g(int slotIn)
  {
    return this.b[slotIn];
  }
  
  public void a(float damage)
  {
    damage /= 4.0F;
    if (damage < 1.0F) {
      damage = 1.0F;
    }
    for (int i = 0; i < this.b.length; i++) {
      if ((this.b[i] != null) && ((this.b[i].b() instanceof abw)))
      {
        this.b[i].a((int)damage, this.e);
        if (this.b[i].b == 0) {
          this.b[i] = null;
        }
      }
    }
  }
  
  public void n()
  {
    for (adq[] aitemstack : this.g) {
      for (int i = 0; i < aitemstack.length; i++) {
        if (aitemstack[i] != null)
        {
          this.e.a(aitemstack[i], true, false);
          aitemstack[i] = null;
        }
      }
    }
  }
  
  public void v_()
  {
    this.f = true;
  }
  
  public void e(adq itemStackIn)
  {
    this.h = itemStackIn;
  }
  
  public adq o()
  {
    return this.h;
  }
  
  public boolean a(zj player)
  {
    return !this.e.F;
  }
  
  public boolean f(adq itemStackIn)
  {
    for (adq[] aitemstack : this.g) {
      for (int i = 0; i < aitemstack.length; i++) {
        if ((aitemstack[i] != null) && (aitemstack[i].a(itemStackIn))) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean hasItem(ado itemIn)
  {
    for (adq[] aitemstack : this.g) {
      for (int i = 0; i < aitemstack.length; i++) {
        if ((aitemstack[i] != null) && (ado.a(aitemstack[i].b()) == ado.a(itemIn))) {
          return true;
        }
      }
    }
    return false;
  }
  
  public void b(zj player) {}
  
  public void c(zj player) {}
  
  public boolean b(int index, adq stack)
  {
    return true;
  }
  
  public void a(zi playerInventory)
  {
    for (int i = 0; i < u_(); i++) {
      a(i, playerInventory.a(i));
    }
    this.d = playerInventory.d;
  }
  
  public int c_(int id)
  {
    return 0;
  }
  
  public void b(int id, int value) {}
  
  public int g()
  {
    return 0;
  }
  
  public void l()
  {
    for (adq[] aitemstack : this.g) {
      for (int i = 0; i < aitemstack.length; i++) {
        aitemstack[i] = null;
      }
    }
  }
}
