import java.io.IOException;
import java.util.List;

public class gd
  implements ff<fi>
{
  private int a;
  private adq[] b;
  
  public gd() {}
  
  public gd(int ☃, List<adq> ☃)
  {
    this.a = ☃;
    this.b = new adq[☃.size()];
    for (int ☃ = 0; ☃ < this.b.length; ☃++)
    {
      adq ☃ = (adq)☃.get(☃);
      this.b[☃] = (☃ == null ? null : ☃.k());
    }
  }
  
  public void a(em ☃)
    throws IOException
  {
    this.a = ☃.readUnsignedByte();
    int ☃ = ☃.readShort();
    this.b = new adq[☃];
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      this.b[☃] = ☃.k();
    }
  }
  
  public void b(em ☃)
    throws IOException
  {
    ☃.writeByte(this.a);
    ☃.writeShort(this.b.length);
    for (adq ☃ : this.b) {
      ☃.a(☃);
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
  
  public adq[] b()
  {
    return this.b;
  }
}
