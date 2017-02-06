import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class gt
  implements ff<fi>
{
  private int a;
  private byte b;
  private boolean c;
  private ayy[] d;
  private int e;
  private int f;
  private int g;
  private int h;
  private byte[] i;
  
  public gt() {}
  
  public gt(int ☃, byte ☃, boolean ☃, Collection<ayy> ☃, byte[] ☃, int ☃, int ☃, int ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ((ayy[])☃.toArray(new ayy[☃.size()]));
    this.e = ☃;
    this.f = ☃;
    this.g = ☃;
    this.h = ☃;
    
    this.i = new byte[☃ * ☃];
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      for (int ☃ = 0; ☃ < ☃; ☃++) {
        this.i[(☃ + ☃ * ☃)] = ☃[(☃ + ☃ + (☃ + ☃) * 128)];
      }
    }
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.g();
    this.b = ☃.readByte();
    this.c = ☃.readBoolean();
    this.d = new ayy[☃.g()];
    for (int ☃ = 0; ☃ < this.d.length; ☃++)
    {
      short ☃ = (short)☃.readByte();
      this.d[☃] = new ayy((byte)(☃ >> 4 & 0xF), ☃.readByte(), ☃.readByte(), (byte)(☃ & 0xF));
    }
    this.g = ☃.readUnsignedByte();
    if (this.g > 0)
    {
      this.h = ☃.readUnsignedByte();
      this.e = ☃.readUnsignedByte();
      this.f = ☃.readUnsignedByte();
      this.i = ☃.a();
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.b(this.a);
    ☃.writeByte(this.b);
    ☃.writeBoolean(this.c);
    ☃.b(this.d.length);
    for (ayy ☃ : this.d)
    {
      ☃.writeByte((☃.a() & 0xF) << 4 | ☃.d() & 0xF);
      ☃.writeByte(☃.b());
      ☃.writeByte(☃.c());
    }
    ☃.writeByte(this.g);
    if (this.g > 0)
    {
      ☃.writeByte(this.h);
      ☃.writeByte(this.e);
      ☃.writeByte(this.f);
      ☃.a(this.i);
    }
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public int a()
  {
    return this.a;
  }
  
  public void a(ayz ☃)
  {
    ☃.f = this.b;
    ☃.e = this.c;
    ☃.i.clear();
    for (int ☃ = 0; ☃ < this.d.length; ☃++)
    {
      ayy ☃ = this.d[☃];
      ☃.i.put("icon-" + ☃, ☃);
    }
    for (int ☃ = 0; ☃ < this.g; ☃++) {
      for (int ☃ = 0; ☃ < this.h; ☃++) {
        ☃.g[(this.e + ☃ + (this.f + ☃) * 128)] = this.i[(☃ + ☃ * this.g)];
      }
    }
  }
}
