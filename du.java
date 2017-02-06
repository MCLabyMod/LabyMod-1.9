import com.google.common.collect.Lists;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class du
  extends eb
{
  private static final Logger b = ;
  private List<eb> c = Lists.newArrayList();
  private byte d = 0;
  
  void a(DataOutput ☃)
    throws IOException
  {
    if (!this.c.isEmpty()) {
      this.d = ((eb)this.c.get(0)).a();
    } else {
      this.d = 0;
    }
    ☃.writeByte(this.d);
    ☃.writeInt(this.c.size());
    for (int ☃ = 0; ☃ < this.c.size(); ☃++) {
      ((eb)this.c.get(☃)).a(☃);
    }
  }
  
  void a(DataInput ☃, int ☃, dw ☃)
    throws IOException
  {
    ☃.a(296L);
    if (☃ > 512) {
      throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
    }
    this.d = ☃.readByte();
    int ☃ = ☃.readInt();
    if ((this.d == 0) && (☃ > 0)) {
      throw new RuntimeException("Missing type on ListTag");
    }
    ☃.a(32L * ☃);
    
    this.c = Lists.newArrayListWithCapacity(☃);
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      eb ☃ = eb.a(this.d);
      ☃.a(☃, ☃ + 1, ☃);
      this.c.add(☃);
    }
  }
  
  public byte a()
  {
    return 9;
  }
  
  public String toString()
  {
    StringBuilder ☃ = new StringBuilder("[");
    for (int ☃ = 0; ☃ < this.c.size(); ☃++)
    {
      if (☃ != 0) {
        ☃.append(',');
      }
      ☃.append(☃).append(':').append(this.c.get(☃));
    }
    return ']';
  }
  
  public void a(eb ☃)
  {
    if (☃.a() == 0)
    {
      b.warn("Invalid TagEnd added to ListTag");
      return;
    }
    if (this.d == 0)
    {
      this.d = ☃.a();
    }
    else if (this.d != ☃.a())
    {
      b.warn("Adding mismatching tag types to tag list");
      return;
    }
    this.c.add(☃);
  }
  
  public void a(int ☃, eb ☃)
  {
    if (☃.a() == 0)
    {
      b.warn("Invalid TagEnd added to ListTag");
      return;
    }
    if ((☃ < 0) || (☃ >= this.c.size()))
    {
      b.warn("index out of bounds to set tag in tag list");
      return;
    }
    if (this.d == 0)
    {
      this.d = ☃.a();
    }
    else if (this.d != ☃.a())
    {
      b.warn("Adding mismatching tag types to tag list");
      return;
    }
    this.c.set(☃, ☃);
  }
  
  public eb a(int ☃)
  {
    return (eb)this.c.remove(☃);
  }
  
  public boolean c_()
  {
    return this.c.isEmpty();
  }
  
  public dn b(int ☃)
  {
    if ((☃ >= 0) && (☃ < this.c.size()))
    {
      eb ☃ = (eb)this.c.get(☃);
      if (☃.a() == 10) {
        return (dn)☃;
      }
    }
    return new dn();
  }
  
  public int c(int ☃)
  {
    if ((☃ >= 0) && (☃ < this.c.size()))
    {
      eb ☃ = (eb)this.c.get(☃);
      if (☃.a() == 3) {
        return ((dt)☃).d();
      }
    }
    return 0;
  }
  
  public int[] d(int ☃)
  {
    if ((☃ >= 0) && (☃ < this.c.size()))
    {
      eb ☃ = (eb)this.c.get(☃);
      if (☃.a() == 11) {
        return ((ds)☃).c();
      }
    }
    return new int[0];
  }
  
  public double e(int ☃)
  {
    if ((☃ >= 0) && (☃ < this.c.size()))
    {
      eb ☃ = (eb)this.c.get(☃);
      if (☃.a() == 6) {
        return ((dp)☃).g();
      }
    }
    return 0.0D;
  }
  
  public float f(int ☃)
  {
    if ((☃ >= 0) && (☃ < this.c.size()))
    {
      eb ☃ = (eb)this.c.get(☃);
      if (☃.a() == 5) {
        return ((dr)☃).h();
      }
    }
    return 0.0F;
  }
  
  public String g(int ☃)
  {
    if ((☃ < 0) || (☃ >= this.c.size())) {
      return "";
    }
    eb ☃ = (eb)this.c.get(☃);
    if (☃.a() == 8) {
      return ☃.a_();
    }
    return ☃.toString();
  }
  
  public eb h(int ☃)
  {
    if ((☃ < 0) || (☃ >= this.c.size())) {
      return new dq();
    }
    return (eb)this.c.get(☃);
  }
  
  public int c()
  {
    return this.c.size();
  }
  
  public eb b()
  {
    du ☃ = new du();
    ☃.d = this.d;
    for (eb ☃ : this.c)
    {
      eb ☃ = ☃.b();
      ☃.c.add(☃);
    }
    return ☃;
  }
  
  public boolean equals(Object ☃)
  {
    if (super.equals(☃))
    {
      du ☃ = (du)☃;
      if (this.d == ☃.d) {
        return this.c.equals(☃.c);
      }
    }
    return false;
  }
  
  public int hashCode()
  {
    return super.hashCode() ^ this.c.hashCode();
  }
  
  public int d()
  {
    return this.d;
  }
}
