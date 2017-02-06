import de.labystudio.labymod.LabyMod;
import java.io.IOException;

public class gs
  implements ff<fi>
{
  private int a;
  private boolean b;
  private ahw.a c;
  private int d;
  private qk e;
  private int f;
  private ahy g;
  private boolean h;
  
  public gs() {}
  
  public gs(int p_i46938_1_, ahw.a p_i46938_2_, boolean p_i46938_3_, int p_i46938_4_, qk p_i46938_5_, int p_i46938_6_, ahy p_i46938_7_, boolean p_i46938_8_)
  {
    this.a = p_i46938_1_;
    this.d = p_i46938_4_;
    this.e = p_i46938_5_;
    this.c = p_i46938_2_;
    this.f = p_i46938_6_;
    this.b = p_i46938_3_;
    this.g = p_i46938_7_;
    this.h = p_i46938_8_;
  }
  
  public void a(em buf)
    throws IOException
  {
    this.a = buf.readInt();
    int i = buf.readUnsignedByte();
    this.b = ((i & 0x8) == 8);
    i &= 0xFFFFFFF7;
    this.c = ahw.a.a(i);
    this.d = (LabyMod.protocolHack ? buf.readInt() : buf.readByte());
    this.e = qk.a(buf.readUnsignedByte());
    this.f = buf.readUnsignedByte();
    this.g = ahy.a(buf.c(16));
    if (this.g == null) {
      this.g = ahy.b;
    }
    this.h = buf.readBoolean();
  }
  
  public void b(em buf)
    throws IOException
  {
    buf.writeInt(this.a);
    int i = this.c.a();
    if (this.b) {
      i |= 0x8;
    }
    buf.writeByte(i);
    if (LabyMod.protocolHack) {
      buf.writeInt(this.d);
    } else {
      buf.writeByte(this.d);
    }
    buf.writeByte(this.e.a());
    buf.writeByte(this.f);
    buf.a(this.g.a());
    buf.writeBoolean(this.h);
  }
  
  public void a(fi handler)
  {
    handler.a(this);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public boolean b()
  {
    return this.b;
  }
  
  public ahw.a c()
  {
    return this.c;
  }
  
  public int d()
  {
    return this.d;
  }
  
  public qk e()
  {
    return this.e;
  }
  
  public int f()
  {
    return this.f;
  }
  
  public ahy g()
  {
    return this.g;
  }
  
  public boolean h()
  {
    return this.h;
  }
}
