import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;

public class gp
  implements ff<fi>
{
  private int a;
  private int b;
  private int c;
  private byte[] d;
  private boolean e;
  
  public gp() {}
  
  public gp(ase ☃, boolean ☃, int ☃)
  {
    this.a = ☃.b;
    this.b = ☃.c;
    this.e = ☃;
    
    boolean ☃ = !☃.q().s.m();
    this.d = new byte[a(☃, ☃, ☃, ☃)];
    this.c = a(new em(f()), ☃, ☃, ☃, ☃);
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readInt();
    this.b = ☃.readInt();
    this.e = ☃.readBoolean();
    this.c = ☃.g();
    
    int ☃ = ☃.g();
    if (☃ > 2097152) {
      throw new RuntimeException("Chunk Packet trying to allocate too much memory on read.");
    }
    this.d = new byte[☃];
    ☃.readBytes(this.d);
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeInt(this.a);
    ☃.writeInt(this.b);
    ☃.writeBoolean(this.e);
    ☃.b(this.c);
    ☃.b(this.d.length);
    ☃.writeBytes(this.d);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public em a()
  {
    return new em(Unpooled.wrappedBuffer(this.d));
  }
  
  private ByteBuf f()
  {
    ByteBuf ☃ = Unpooled.wrappedBuffer(this.d);
    ☃.writerIndex(0);
    return ☃;
  }
  
  public static int a(em ☃, ase ☃, boolean ☃, boolean ☃, int ☃)
  {
    int ☃ = 0;
    
    asf[] ☃ = ☃.h();
    int ☃ = 0;
    for (int ☃ = ☃.length; ☃ < ☃; ☃++)
    {
      asf ☃ = ☃[☃];
      if ((☃ != ase.a) && ((!☃) || (!☃.a())) && ((☃ & 1 << ☃) != 0))
      {
        ☃ |= 1 << ☃;
        
        ☃.g().b(☃);
        
        ☃.writeBytes(☃.h().a());
        if (☃) {
          ☃.writeBytes(☃.i().a());
        }
      }
    }
    if (☃) {
      ☃.writeBytes(☃.l());
    }
    return ☃;
  }
  
  protected static int a(ase ☃, boolean ☃, boolean ☃, int ☃)
  {
    int ☃ = 0;
    
    asf[] ☃ = ☃.h();
    int ☃ = 0;
    for (int ☃ = ☃.length; ☃ < ☃; ☃++)
    {
      asf ☃ = ☃[☃];
      if ((☃ != ase.a) && ((!☃) || (!☃.a())) && ((☃ & 1 << ☃) != 0))
      {
        ☃ += ☃.g().a();
        ☃ += ☃.h().a().length;
        if (☃) {
          ☃ += ☃.i().a().length;
        }
      }
    }
    if (☃) {
      ☃ += ☃.l().length;
    }
    return ☃;
  }
  
  public int b()
  {
    return this.a;
  }
  
  public int c()
  {
    return this.b;
  }
  
  public int d()
  {
    return this.c;
  }
  
  public boolean e()
  {
    return this.e;
  }
}
