import java.io.IOException;

public class gn
  implements ff<fi>
{
  public static final String[] a = { "tile.bed.notValid" };
  private int b;
  private float c;
  
  public gn() {}
  
  public gn(int ☃, float ☃)
  {
    this.b = ☃;
    this.c = ☃;
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.b = ☃.readUnsignedByte();
    this.c = ☃.readFloat();
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeByte(this.b);
    ☃.writeFloat(this.c);
  }
  
  public void a(fi ☃)
  {
    ☃.a(this);
  }
  
  public int a()
  {
    return this.b;
  }
  
  public float b()
  {
    return this.c;
  }
}
