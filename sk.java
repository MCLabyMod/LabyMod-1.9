import com.google.common.base.Optional;
import java.util.Random;
import java.util.UUID;

public abstract class sk
  extends vw
  implements sg
{
  protected static final ke<Byte> bv = kh.a(sk.class, kg.a);
  protected static final ke<Optional<UUID>> bw = kh.a(sk.class, kg.m);
  protected um bx;
  
  public sk(aht ☃)
  {
    super(☃);
    da();
  }
  
  protected void i()
  {
    super.i();
    this.Z.a(bv, Byte.valueOf((byte)0));
    this.Z.a(bw, Optional.absent());
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    if (b() == null) {
      ☃.a("OwnerUUID", "");
    } else {
      ☃.a("OwnerUUID", b().toString());
    }
    ☃.a("Sitting", db());
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    String ☃ = "";
    if (☃.b("OwnerUUID", 8))
    {
      ☃ = ☃.l("OwnerUUID");
    }
    else
    {
      String ☃ = ☃.l("Owner");
      ☃ = ml.a(h(), ☃);
    }
    if (!☃.isEmpty()) {
      try
      {
        b(UUID.fromString(☃));
        p(true);
      }
      catch (Throwable ☃)
      {
        p(false);
      }
    }
    if (this.bx != null) {
      this.bx.a(☃.p("Sitting"));
    }
    q(☃.p("Sitting"));
  }
  
  public boolean a(zj ☃)
  {
    return (cZ()) && (d(☃));
  }
  
  protected void o(boolean ☃)
  {
    cy ☃ = cy.I;
    if (!☃) {
      ☃ = cy.l;
    }
    for (int ☃ = 0; ☃ < 7; ☃++)
    {
      double ☃ = this.S.nextGaussian() * 0.02D;
      double ☃ = this.S.nextGaussian() * 0.02D;
      double ☃ = this.S.nextGaussian() * 0.02D;
      this.l.a(☃, this.p + this.S.nextFloat() * this.G * 2.0F - this.G, this.q + 0.5D + this.S.nextFloat() * this.H, this.r + this.S.nextFloat() * this.G * 2.0F - this.G, ☃, ☃, ☃, new int[0]);
    }
  }
  
  public void a(byte ☃)
  {
    if (☃ == 7) {
      o(true);
    } else if (☃ == 6) {
      o(false);
    } else {
      super.a(☃);
    }
  }
  
  public boolean cZ()
  {
    return (((Byte)this.Z.a(bv)).byteValue() & 0x4) != 0;
  }
  
  public void p(boolean ☃)
  {
    byte ☃ = ((Byte)this.Z.a(bv)).byteValue();
    if (☃) {
      this.Z.b(bv, Byte.valueOf((byte)(☃ | 0x4)));
    } else {
      this.Z.b(bv, Byte.valueOf((byte)(☃ & 0xFFFFFFFB)));
    }
    da();
  }
  
  protected void da() {}
  
  public boolean db()
  {
    return (((Byte)this.Z.a(bv)).byteValue() & 0x1) != 0;
  }
  
  public void q(boolean ☃)
  {
    byte ☃ = ((Byte)this.Z.a(bv)).byteValue();
    if (☃) {
      this.Z.b(bv, Byte.valueOf((byte)(☃ | 0x1)));
    } else {
      this.Z.b(bv, Byte.valueOf((byte)(☃ & 0xFFFFFFFE)));
    }
  }
  
  public UUID b()
  {
    return (UUID)((Optional)this.Z.a(bw)).orNull();
  }
  
  public void b(UUID ☃)
  {
    this.Z.b(bw, Optional.fromNullable(☃));
  }
  
  public sa dc()
  {
    try
    {
      UUID ☃ = b();
      if (☃ == null) {
        return null;
      }
      return this.l.b(☃);
    }
    catch (IllegalArgumentException ☃) {}
    return null;
  }
  
  public boolean d(sa ☃)
  {
    return ☃ == dc();
  }
  
  public um dd()
  {
    return this.bx;
  }
  
  public boolean a(sa ☃, sa ☃)
  {
    return true;
  }
  
  public bbr aO()
  {
    if (cZ())
    {
      sa ☃ = dc();
      if (☃ != null) {
        return ☃.aO();
      }
    }
    return super.aO();
  }
  
  public boolean r(rr ☃)
  {
    if (cZ())
    {
      sa ☃ = dc();
      if (☃ == ☃) {
        return true;
      }
      if (☃ != null) {
        return ☃.r(☃);
      }
    }
    return super.r(☃);
  }
  
  public void a(rc ☃)
  {
    if ((!this.l.E) && (this.l.U().b("showDeathMessages")) && 
      ((dc() instanceof lr))) {
      dc().a(bU().b());
    }
    super.a(☃);
  }
}
