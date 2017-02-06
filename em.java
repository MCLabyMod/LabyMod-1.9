import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.ByteBufProcessor;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.util.UUID;

public class em
  extends ByteBuf
{
  private final ByteBuf a;
  
  public em(ByteBuf ☃)
  {
    this.a = ☃;
  }
  
  public static int a(int ☃)
  {
    for (int ☃ = 1; ☃ < 5; ☃++) {
      if ((☃ & -1 << ☃ * 7) == 0) {
        return ☃;
      }
    }
    return 5;
  }
  
  public em a(byte[] ☃)
  {
    b(☃.length);
    writeBytes(☃);
    
    return this;
  }
  
  public byte[] a()
  {
    byte[] ☃ = new byte[g()];
    readBytes(☃);
    
    return ☃;
  }
  
  public em a(int[] ☃)
  {
    b(☃.length);
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      b(☃[☃]);
    }
    return this;
  }
  
  public int[] b()
  {
    int[] ☃ = new int[g()];
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      ☃[☃] = g();
    }
    return ☃;
  }
  
  public em a(long[] ☃)
  {
    b(☃.length);
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      writeLong(☃[☃]);
    }
    return this;
  }
  
  public long[] b(long[] ☃)
  {
    int ☃ = g();
    if ((☃ == null) || (☃.length != ☃)) {
      ☃ = new long[☃];
    }
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      ☃[☃] = readLong();
    }
    return ☃;
  }
  
  public cj e()
  {
    return cj.a(readLong());
  }
  
  public em a(cj ☃)
  {
    writeLong(☃.g());
    return this;
  }
  
  public eu f()
  {
    return eu.a.a(c(32767));
  }
  
  public em a(eu ☃)
  {
    return a(eu.a.a(☃));
  }
  
  public <T extends Enum<T>> T a(Class<T> ☃)
  {
    return ((Enum[])☃.getEnumConstants())[g()];
  }
  
  public em a(Enum<?> ☃)
  {
    return b(☃.ordinal());
  }
  
  public int g()
  {
    int ☃ = 0;
    int ☃ = 0;
    for (;;)
    {
      byte ☃ = readByte();
      
      ☃ |= (☃ & 0x7F) << ☃++ * 7;
      if (☃ > 5) {
        throw new RuntimeException("VarInt too big");
      }
      if ((☃ & 0x80) != 128) {
        break;
      }
    }
    return ☃;
  }
  
  public long h()
  {
    long ☃ = 0L;
    int ☃ = 0;
    for (;;)
    {
      byte ☃ = readByte();
      
      ☃ |= (☃ & 0x7F) << ☃++ * 7;
      if (☃ > 10) {
        throw new RuntimeException("VarLong too big");
      }
      if ((☃ & 0x80) != 128) {
        break;
      }
    }
    return ☃;
  }
  
  public em a(UUID ☃)
  {
    writeLong(☃.getMostSignificantBits());
    writeLong(☃.getLeastSignificantBits());
    
    return this;
  }
  
  public UUID i()
  {
    return new UUID(readLong(), readLong());
  }
  
  public em b(int ☃)
  {
    for (;;)
    {
      if ((☃ & 0xFFFFFF80) == 0)
      {
        writeByte(☃);
        return this;
      }
      writeByte(☃ & 0x7F | 0x80);
      ☃ >>>= 7;
    }
  }
  
  public em b(long ☃)
  {
    for (;;)
    {
      if ((☃ & 0xFFFFFFFFFFFFFF80) == 0L)
      {
        writeByte((int)☃);
        return this;
      }
      writeByte((int)(☃ & 0x7F) | 0x80);
      ☃ >>>= 7;
    }
  }
  
  public em a(dn ☃)
  {
    if (☃ == null) {
      writeByte(0);
    } else {
      try
      {
        dx.a(☃, new ByteBufOutputStream(this));
      }
      catch (IOException ☃)
      {
        throw new EncoderException(☃);
      }
    }
    return this;
  }
  
  public dn j()
  {
    int ☃ = readerIndex();
    byte ☃ = readByte();
    if (☃ == 0) {
      return null;
    }
    readerIndex(☃);
    try
    {
      return dx.a(new ByteBufInputStream(this), new dw(2097152L));
    }
    catch (IOException ☃)
    {
      throw new EncoderException(☃);
    }
  }
  
  public em a(adq ☃)
  {
    if (☃ == null)
    {
      writeShort(-1);
    }
    else
    {
      writeShort(ado.a(☃.b()));
      writeByte(☃.b);
      writeShort(☃.i());
      
      dn ☃ = null;
      if ((☃.b().m()) || (☃.b().p())) {
        ☃ = ☃.o();
      }
      a(☃);
    }
    return this;
  }
  
  public adq k()
  {
    adq ☃ = null;
    int ☃ = readShort();
    if (☃ >= 0)
    {
      int ☃ = readByte();
      int ☃ = readShort();
      
      ☃ = new adq(ado.c(☃), ☃, ☃);
      ☃.d(j());
    }
    return ☃;
  }
  
  public String c(int ☃)
  {
    int ☃ = g();
    if (☃ > ☃ * 4) {
      throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + ☃ + " > " + ☃ * 4 + ")");
    }
    if (☃ < 0) {
      throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
    }
    String ☃ = new String(readBytes(☃).array(), Charsets.UTF_8);
    if (☃.length() > ☃) {
      throw new DecoderException("The received string length is longer than maximum allowed (" + ☃ + " > " + ☃ + ")");
    }
    return ☃;
  }
  
  public em a(String ☃)
  {
    byte[] ☃ = ☃.getBytes(Charsets.UTF_8);
    if (☃.length > 32767) {
      throw new EncoderException("String too big (was " + ☃.length() + " bytes encoded, max " + 32767 + ")");
    }
    b(☃.length);
    writeBytes(☃);
    return this;
  }
  
  public int capacity()
  {
    return this.a.capacity();
  }
  
  public ByteBuf capacity(int ☃)
  {
    return this.a.capacity(☃);
  }
  
  public int maxCapacity()
  {
    return this.a.maxCapacity();
  }
  
  public ByteBufAllocator alloc()
  {
    return this.a.alloc();
  }
  
  public ByteOrder order()
  {
    return this.a.order();
  }
  
  public ByteBuf order(ByteOrder ☃)
  {
    return this.a.order(☃);
  }
  
  public ByteBuf unwrap()
  {
    return this.a.unwrap();
  }
  
  public boolean isDirect()
  {
    return this.a.isDirect();
  }
  
  public int readerIndex()
  {
    return this.a.readerIndex();
  }
  
  public ByteBuf readerIndex(int ☃)
  {
    return this.a.readerIndex(☃);
  }
  
  public int writerIndex()
  {
    return this.a.writerIndex();
  }
  
  public ByteBuf writerIndex(int ☃)
  {
    return this.a.writerIndex(☃);
  }
  
  public ByteBuf setIndex(int ☃, int ☃)
  {
    return this.a.setIndex(☃, ☃);
  }
  
  public int readableBytes()
  {
    return this.a.readableBytes();
  }
  
  public int writableBytes()
  {
    return this.a.writableBytes();
  }
  
  public int maxWritableBytes()
  {
    return this.a.maxWritableBytes();
  }
  
  public boolean isReadable()
  {
    return this.a.isReadable();
  }
  
  public boolean isReadable(int ☃)
  {
    return this.a.isReadable(☃);
  }
  
  public boolean isWritable()
  {
    return this.a.isWritable();
  }
  
  public boolean isWritable(int ☃)
  {
    return this.a.isWritable(☃);
  }
  
  public ByteBuf clear()
  {
    return this.a.clear();
  }
  
  public ByteBuf markReaderIndex()
  {
    return this.a.markReaderIndex();
  }
  
  public ByteBuf resetReaderIndex()
  {
    return this.a.resetReaderIndex();
  }
  
  public ByteBuf markWriterIndex()
  {
    return this.a.markWriterIndex();
  }
  
  public ByteBuf resetWriterIndex()
  {
    return this.a.resetWriterIndex();
  }
  
  public ByteBuf discardReadBytes()
  {
    return this.a.discardReadBytes();
  }
  
  public ByteBuf discardSomeReadBytes()
  {
    return this.a.discardSomeReadBytes();
  }
  
  public ByteBuf ensureWritable(int ☃)
  {
    return this.a.ensureWritable(☃);
  }
  
  public int ensureWritable(int ☃, boolean ☃)
  {
    return this.a.ensureWritable(☃, ☃);
  }
  
  public boolean getBoolean(int ☃)
  {
    return this.a.getBoolean(☃);
  }
  
  public byte getByte(int ☃)
  {
    return this.a.getByte(☃);
  }
  
  public short getUnsignedByte(int ☃)
  {
    return this.a.getUnsignedByte(☃);
  }
  
  public short getShort(int ☃)
  {
    return this.a.getShort(☃);
  }
  
  public int getUnsignedShort(int ☃)
  {
    return this.a.getUnsignedShort(☃);
  }
  
  public int getMedium(int ☃)
  {
    return this.a.getMedium(☃);
  }
  
  public int getUnsignedMedium(int ☃)
  {
    return this.a.getUnsignedMedium(☃);
  }
  
  public int getInt(int ☃)
  {
    return this.a.getInt(☃);
  }
  
  public long getUnsignedInt(int ☃)
  {
    return this.a.getUnsignedInt(☃);
  }
  
  public long getLong(int ☃)
  {
    return this.a.getLong(☃);
  }
  
  public char getChar(int ☃)
  {
    return this.a.getChar(☃);
  }
  
  public float getFloat(int ☃)
  {
    return this.a.getFloat(☃);
  }
  
  public double getDouble(int ☃)
  {
    return this.a.getDouble(☃);
  }
  
  public ByteBuf getBytes(int ☃, ByteBuf ☃)
  {
    return this.a.getBytes(☃, ☃);
  }
  
  public ByteBuf getBytes(int ☃, ByteBuf ☃, int ☃)
  {
    return this.a.getBytes(☃, ☃, ☃);
  }
  
  public ByteBuf getBytes(int ☃, ByteBuf ☃, int ☃, int ☃)
  {
    return this.a.getBytes(☃, ☃, ☃, ☃);
  }
  
  public ByteBuf getBytes(int ☃, byte[] ☃)
  {
    return this.a.getBytes(☃, ☃);
  }
  
  public ByteBuf getBytes(int ☃, byte[] ☃, int ☃, int ☃)
  {
    return this.a.getBytes(☃, ☃, ☃, ☃);
  }
  
  public ByteBuf getBytes(int ☃, ByteBuffer ☃)
  {
    return this.a.getBytes(☃, ☃);
  }
  
  public ByteBuf getBytes(int ☃, OutputStream ☃, int ☃)
    throws IOException
  {
    return this.a.getBytes(☃, ☃, ☃);
  }
  
  public int getBytes(int ☃, GatheringByteChannel ☃, int ☃)
    throws IOException
  {
    return this.a.getBytes(☃, ☃, ☃);
  }
  
  public ByteBuf setBoolean(int ☃, boolean ☃)
  {
    return this.a.setBoolean(☃, ☃);
  }
  
  public ByteBuf setByte(int ☃, int ☃)
  {
    return this.a.setByte(☃, ☃);
  }
  
  public ByteBuf setShort(int ☃, int ☃)
  {
    return this.a.setShort(☃, ☃);
  }
  
  public ByteBuf setMedium(int ☃, int ☃)
  {
    return this.a.setMedium(☃, ☃);
  }
  
  public ByteBuf setInt(int ☃, int ☃)
  {
    return this.a.setInt(☃, ☃);
  }
  
  public ByteBuf setLong(int ☃, long ☃)
  {
    return this.a.setLong(☃, ☃);
  }
  
  public ByteBuf setChar(int ☃, int ☃)
  {
    return this.a.setChar(☃, ☃);
  }
  
  public ByteBuf setFloat(int ☃, float ☃)
  {
    return this.a.setFloat(☃, ☃);
  }
  
  public ByteBuf setDouble(int ☃, double ☃)
  {
    return this.a.setDouble(☃, ☃);
  }
  
  public ByteBuf setBytes(int ☃, ByteBuf ☃)
  {
    return this.a.setBytes(☃, ☃);
  }
  
  public ByteBuf setBytes(int ☃, ByteBuf ☃, int ☃)
  {
    return this.a.setBytes(☃, ☃, ☃);
  }
  
  public ByteBuf setBytes(int ☃, ByteBuf ☃, int ☃, int ☃)
  {
    return this.a.setBytes(☃, ☃, ☃, ☃);
  }
  
  public ByteBuf setBytes(int ☃, byte[] ☃)
  {
    return this.a.setBytes(☃, ☃);
  }
  
  public ByteBuf setBytes(int ☃, byte[] ☃, int ☃, int ☃)
  {
    return this.a.setBytes(☃, ☃, ☃, ☃);
  }
  
  public ByteBuf setBytes(int ☃, ByteBuffer ☃)
  {
    return this.a.setBytes(☃, ☃);
  }
  
  public int setBytes(int ☃, InputStream ☃, int ☃)
    throws IOException
  {
    return this.a.setBytes(☃, ☃, ☃);
  }
  
  public int setBytes(int ☃, ScatteringByteChannel ☃, int ☃)
    throws IOException
  {
    return this.a.setBytes(☃, ☃, ☃);
  }
  
  public ByteBuf setZero(int ☃, int ☃)
  {
    return this.a.setZero(☃, ☃);
  }
  
  public boolean readBoolean()
  {
    return this.a.readBoolean();
  }
  
  public byte readByte()
  {
    return this.a.readByte();
  }
  
  public short readUnsignedByte()
  {
    return this.a.readUnsignedByte();
  }
  
  public short readShort()
  {
    return this.a.readShort();
  }
  
  public int readUnsignedShort()
  {
    return this.a.readUnsignedShort();
  }
  
  public int readMedium()
  {
    return this.a.readMedium();
  }
  
  public int readUnsignedMedium()
  {
    return this.a.readUnsignedMedium();
  }
  
  public int readInt()
  {
    return this.a.readInt();
  }
  
  public long readUnsignedInt()
  {
    return this.a.readUnsignedInt();
  }
  
  public long readLong()
  {
    return this.a.readLong();
  }
  
  public char readChar()
  {
    return this.a.readChar();
  }
  
  public float readFloat()
  {
    return this.a.readFloat();
  }
  
  public double readDouble()
  {
    return this.a.readDouble();
  }
  
  public ByteBuf readBytes(int ☃)
  {
    return this.a.readBytes(☃);
  }
  
  public ByteBuf readSlice(int ☃)
  {
    return this.a.readSlice(☃);
  }
  
  public ByteBuf readBytes(ByteBuf ☃)
  {
    return this.a.readBytes(☃);
  }
  
  public ByteBuf readBytes(ByteBuf ☃, int ☃)
  {
    return this.a.readBytes(☃, ☃);
  }
  
  public ByteBuf readBytes(ByteBuf ☃, int ☃, int ☃)
  {
    return this.a.readBytes(☃, ☃, ☃);
  }
  
  public ByteBuf readBytes(byte[] ☃)
  {
    return this.a.readBytes(☃);
  }
  
  public ByteBuf readBytes(byte[] ☃, int ☃, int ☃)
  {
    return this.a.readBytes(☃, ☃, ☃);
  }
  
  public ByteBuf readBytes(ByteBuffer ☃)
  {
    return this.a.readBytes(☃);
  }
  
  public ByteBuf readBytes(OutputStream ☃, int ☃)
    throws IOException
  {
    return this.a.readBytes(☃, ☃);
  }
  
  public int readBytes(GatheringByteChannel ☃, int ☃)
    throws IOException
  {
    return this.a.readBytes(☃, ☃);
  }
  
  public ByteBuf skipBytes(int ☃)
  {
    return this.a.skipBytes(☃);
  }
  
  public ByteBuf writeBoolean(boolean ☃)
  {
    return this.a.writeBoolean(☃);
  }
  
  public ByteBuf writeByte(int ☃)
  {
    return this.a.writeByte(☃);
  }
  
  public ByteBuf writeShort(int ☃)
  {
    return this.a.writeShort(☃);
  }
  
  public ByteBuf writeMedium(int ☃)
  {
    return this.a.writeMedium(☃);
  }
  
  public ByteBuf writeInt(int ☃)
  {
    return this.a.writeInt(☃);
  }
  
  public ByteBuf writeLong(long ☃)
  {
    return this.a.writeLong(☃);
  }
  
  public ByteBuf writeChar(int ☃)
  {
    return this.a.writeChar(☃);
  }
  
  public ByteBuf writeFloat(float ☃)
  {
    return this.a.writeFloat(☃);
  }
  
  public ByteBuf writeDouble(double ☃)
  {
    return this.a.writeDouble(☃);
  }
  
  public ByteBuf writeBytes(ByteBuf ☃)
  {
    return this.a.writeBytes(☃);
  }
  
  public ByteBuf writeBytes(ByteBuf ☃, int ☃)
  {
    return this.a.writeBytes(☃, ☃);
  }
  
  public ByteBuf writeBytes(ByteBuf ☃, int ☃, int ☃)
  {
    return this.a.writeBytes(☃, ☃, ☃);
  }
  
  public ByteBuf writeBytes(byte[] ☃)
  {
    return this.a.writeBytes(☃);
  }
  
  public ByteBuf writeBytes(byte[] ☃, int ☃, int ☃)
  {
    return this.a.writeBytes(☃, ☃, ☃);
  }
  
  public ByteBuf writeBytes(ByteBuffer ☃)
  {
    return this.a.writeBytes(☃);
  }
  
  public int writeBytes(InputStream ☃, int ☃)
    throws IOException
  {
    return this.a.writeBytes(☃, ☃);
  }
  
  public int writeBytes(ScatteringByteChannel ☃, int ☃)
    throws IOException
  {
    return this.a.writeBytes(☃, ☃);
  }
  
  public ByteBuf writeZero(int ☃)
  {
    return this.a.writeZero(☃);
  }
  
  public int indexOf(int ☃, int ☃, byte ☃)
  {
    return this.a.indexOf(☃, ☃, ☃);
  }
  
  public int bytesBefore(byte ☃)
  {
    return this.a.bytesBefore(☃);
  }
  
  public int bytesBefore(int ☃, byte ☃)
  {
    return this.a.bytesBefore(☃, ☃);
  }
  
  public int bytesBefore(int ☃, int ☃, byte ☃)
  {
    return this.a.bytesBefore(☃, ☃, ☃);
  }
  
  public int forEachByte(ByteBufProcessor ☃)
  {
    return this.a.forEachByte(☃);
  }
  
  public int forEachByte(int ☃, int ☃, ByteBufProcessor ☃)
  {
    return this.a.forEachByte(☃, ☃, ☃);
  }
  
  public int forEachByteDesc(ByteBufProcessor ☃)
  {
    return this.a.forEachByteDesc(☃);
  }
  
  public int forEachByteDesc(int ☃, int ☃, ByteBufProcessor ☃)
  {
    return this.a.forEachByteDesc(☃, ☃, ☃);
  }
  
  public ByteBuf copy()
  {
    return this.a.copy();
  }
  
  public ByteBuf copy(int ☃, int ☃)
  {
    return this.a.copy(☃, ☃);
  }
  
  public ByteBuf slice()
  {
    return this.a.slice();
  }
  
  public ByteBuf slice(int ☃, int ☃)
  {
    return this.a.slice(☃, ☃);
  }
  
  public ByteBuf duplicate()
  {
    return this.a.duplicate();
  }
  
  public int nioBufferCount()
  {
    return this.a.nioBufferCount();
  }
  
  public ByteBuffer nioBuffer()
  {
    return this.a.nioBuffer();
  }
  
  public ByteBuffer nioBuffer(int ☃, int ☃)
  {
    return this.a.nioBuffer(☃, ☃);
  }
  
  public ByteBuffer internalNioBuffer(int ☃, int ☃)
  {
    return this.a.internalNioBuffer(☃, ☃);
  }
  
  public ByteBuffer[] nioBuffers()
  {
    return this.a.nioBuffers();
  }
  
  public ByteBuffer[] nioBuffers(int ☃, int ☃)
  {
    return this.a.nioBuffers(☃, ☃);
  }
  
  public boolean hasArray()
  {
    return this.a.hasArray();
  }
  
  public byte[] array()
  {
    return this.a.array();
  }
  
  public int arrayOffset()
  {
    return this.a.arrayOffset();
  }
  
  public boolean hasMemoryAddress()
  {
    return this.a.hasMemoryAddress();
  }
  
  public long memoryAddress()
  {
    return this.a.memoryAddress();
  }
  
  public String toString(Charset ☃)
  {
    return this.a.toString(☃);
  }
  
  public String toString(int ☃, int ☃, Charset ☃)
  {
    return this.a.toString(☃, ☃, ☃);
  }
  
  public int hashCode()
  {
    return this.a.hashCode();
  }
  
  public boolean equals(Object ☃)
  {
    return this.a.equals(☃);
  }
  
  public int compareTo(ByteBuf ☃)
  {
    return this.a.compareTo(☃);
  }
  
  public String toString()
  {
    return this.a.toString();
  }
  
  public ByteBuf retain(int ☃)
  {
    return this.a.retain(☃);
  }
  
  public ByteBuf retain()
  {
    return this.a.retain();
  }
  
  public int refCnt()
  {
    return this.a.refCnt();
  }
  
  public boolean release()
  {
    return this.a.release();
  }
  
  public boolean release(int ☃)
  {
    return this.a.release(☃);
  }
}
