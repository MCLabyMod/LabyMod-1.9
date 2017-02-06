import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;

public class ef
{
  private final Cipher a;
  private byte[] b = new byte[0];
  private byte[] c = new byte[0];
  
  protected ef(Cipher ☃)
  {
    this.a = ☃;
  }
  
  private byte[] a(ByteBuf ☃)
  {
    int ☃ = ☃.readableBytes();
    if (this.b.length < ☃) {
      this.b = new byte[☃];
    }
    ☃.readBytes(this.b, 0, ☃);
    return this.b;
  }
  
  protected ByteBuf a(ChannelHandlerContext ☃, ByteBuf ☃)
    throws ShortBufferException
  {
    int ☃ = ☃.readableBytes();
    byte[] ☃ = a(☃);
    
    ByteBuf ☃ = ☃.alloc().heapBuffer(this.a.getOutputSize(☃));
    ☃.writerIndex(this.a.update(☃, 0, ☃, ☃.array(), ☃.arrayOffset()));
    
    return ☃;
  }
  
  protected void a(ByteBuf ☃, ByteBuf ☃)
    throws ShortBufferException
  {
    int ☃ = ☃.readableBytes();
    byte[] ☃ = a(☃);
    
    int ☃ = this.a.getOutputSize(☃);
    if (this.c.length < ☃) {
      this.c = new byte[☃];
    }
    ☃.writeBytes(this.c, 0, this.a.update(☃, 0, ☃, this.c));
  }
}
